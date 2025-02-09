package com.ning.infrastructure.common.interceptors;

import cn.hutool.core.util.ReflectUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.InExpression;
import net.sf.jsqlparser.expression.operators.relational.ParenthesedExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.*;
import net.sf.jsqlparser.statement.update.Update;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
)})
public class IsDeleteInterceptor implements Interceptor {

    private static final Byte DELETED_VALUE = 1;
    private static final Byte NOT_DELETED_VALUE = 0;
    private static final String IS_DELETE_COLUMN_NAME = "is_deleted";

    private static final Set<String> IGNORE_TABLE_NAMES = Set.of("ignored_table");

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String originalSql = boundSql.getSql();

        Statement statement = CCJSqlParserUtil.parse(originalSql);

        if (statement instanceof Select) {
            processSelect((Select) statement);
        } else if (statement instanceof Update) {
            processUpdate((Update) statement);
        } else if (statement instanceof Delete) {
            processDelete((Delete) statement);
        } else if (statement instanceof Insert) {
            processInsert((Insert) statement);
        }

        String modifiedSql = statement.toString();
        ReflectUtil.setFieldValue(boundSql, "sql", modifiedSql);

        return invocation.proceed();
    }

    private void processSelect(Select select) {
        if (select instanceof PlainSelect) {
            processPlainSelect((PlainSelect) select);
//        } else if (selectBody instanceof WithItem) {
//            WithItem withItem = (WithItem) selectBody;
//            if (withItem.getSelectBody() != null) {
//                processSelect(withItem.getSelectBody());
//            }
//        } else {
//            SetOperationList operationList = (SetOperationList) selectBody;
//            if (operationList.getSelects() != null && operationList.getSelects().size() > 0) {
//                operationList.getSelects().forEach(this::processSelect);
//            }
        }
    }

    private void processUpdate(Update update) {
        if (this.ignore(update.getTable().getFullyQualifiedName())) {
            return;
        }

        Table table = update.getTable();
        Expression where = update.getWhere();
        update.setWhere(this.addIsDeleteCondition(table, where));
    }

    private void processDelete(Delete delete) {
        if (this.ignore(delete.getTable().getFullyQualifiedName())) {
            return;
        }

        Table table = delete.getTable();
        Expression where = delete.getWhere();
        delete.setWhere(this.addIsDeleteCondition(table, where));
    }

    private void processInsert(Insert insert) {
        if (this.ignore(insert.getTable().getFullyQualifiedName())) {
            return;
        }

        Optional<Column> columnOpt = insert.getColumns().stream()
                .filter(t -> Objects.equals(t.getColumnName(), IS_DELETE_COLUMN_NAME))
                .findAny();
        if (columnOpt.isPresent()) {
            return;
        }

        insert.getColumns().add(new Column(IS_DELETE_COLUMN_NAME));
        Select select = insert.getSelect();
        if (select != null) {
            if (select instanceof Values values) {
                if (values.getExpressions() instanceof ParenthesedExpressionList) {
                    values.addExpressions(new LongValue(NOT_DELETED_VALUE));
                } else {
                    values.getExpressions().forEach(e -> {
                        ParenthesedExpressionList p = (ParenthesedExpressionList) e;
                        p.add(new LongValue(NOT_DELETED_VALUE));
                    });
                }
                // todo: handle INSERT INTO user (name, age) SELECT XXX FROM
            }
        }
    }

    private void processPlainSelect(PlainSelect plainSelect) {
        FromItem fromItem = plainSelect.getFromItem();
        if (fromItem instanceof Table fromTable) {
            if (ignore(fromTable.getFullyQualifiedName())) {
                return;
            }

            plainSelect.setWhere(buildExpression(plainSelect.getWhere(), fromTable));
        } else {
            processFromItem(fromItem);
        }

        List<Join> joins = plainSelect.getJoins();
        if (joins != null && !joins.isEmpty()) {
            joins.forEach(j -> {
                processJoin(j);
                processFromItem(j.getRightItem());
            });
        }
    }

    private void processFromItem(FromItem fromItem) {
        if (fromItem instanceof Select select) {
            processSelect(select.getPlainSelect());
        }
//        if (fromItem instanceof SubJoin) {
//            SubJoin subJoin = (SubJoin) fromItem;
//            if (subJoin.getJoinList() != null) {
//                subJoin.getJoinList().forEach(this::processJoin);
//            }
//            if (subJoin.getLeft() != null) {
//                processFromItem(subJoin.getLeft());
//            }
//        } else if (fromItem instanceof SubSelect) {
//            SubSelect subSelect = (SubSelect) fromItem;
//            if (subSelect.getSelectBody() != null) {
//                processSelect(subSelect.getSelectBody());
//            }
//        } else if (fromItem instanceof ValuesList) {
//            log.debug("Perform a subquery, if you do not give us feedback");
//        } else if (fromItem instanceof LateralSubSelect) {
//            LateralSubSelect lateralSubSelect = (LateralSubSelect) fromItem;
//            if (lateralSubSelect.getSubSelect() != null) {
//                SubSelect subSelect = lateralSubSelect.getSubSelect();
//                if (subSelect.getSelectBody() != null) {
//                    processSelect(subSelect.getSelectBody());
//                }
//            }
//        }
    }

    protected void processJoin(Join join) {
        if (join.getRightItem() instanceof Table fromTable) {
            if (ignore(fromTable.getFullyQualifiedName())) {
                return;
            }
            Expression expression = buildExpression(join.getOnExpression(), fromTable);
            join.setOnExpressions(List.of(expression));
        }
    }

    protected Expression buildExpression(Expression currentExpression, Table table) {
        Expression appendExpression = buildIsDeletedExpression(table);
        switch (currentExpression) {
            case null -> {
                return appendExpression;
            }
            case BinaryExpression binaryExpression -> {
                doExpression(binaryExpression.getLeftExpression());
                doExpression(binaryExpression.getRightExpression());
            }
            case InExpression inExpression -> inExpression(inExpression);
            default -> {
            }
        }

        if (currentExpression instanceof OrExpression) {
            return new AndExpression(new ParenthesedExpressionList<>(currentExpression), appendExpression);
        } else {
            return new AndExpression(currentExpression, appendExpression);
        }
    }

    protected Expression buildIsDeletedExpression(Table table) {
        EqualsTo isDeleteCondition = new EqualsTo();
        isDeleteCondition.setLeftExpression(this.getAliasColumn(table));
        isDeleteCondition.setRightExpression(new LongValue(NOT_DELETED_VALUE));
        return isDeleteCondition;
    }

    protected void inExpression(InExpression expression) {
        Expression rightExpression = expression.getRightExpression();
        if (rightExpression instanceof Select select) {
            processSelect(select.getPlainSelect());
        }
    }

    protected void doExpression(Expression expression) {
        if (expression instanceof FromItem) {
            processFromItem((FromItem) expression);
        } else if (expression instanceof InExpression) {
            inExpression((InExpression) expression);
        }
    }

    private Expression addIsDeleteCondition(Table table, Expression where) {
        EqualsTo isDeleteCondition = new EqualsTo();
        isDeleteCondition.setLeftExpression(this.getAliasColumn(table));
        isDeleteCondition.setRightExpression(new LongValue(NOT_DELETED_VALUE));

        if (null != where) {
            if (where instanceof OrExpression) {
                return new AndExpression(isDeleteCondition, new ParenthesedExpressionList<>(where));
            } else {
                return new AndExpression(isDeleteCondition, where);
            }
        }

        return isDeleteCondition;
    }

    protected Column getAliasColumn(Table table) {
        StringBuilder sb = new StringBuilder();
        if (null == table.getAlias()) {
            sb.append(table.getName());
        } else {
            sb.append(table.getAlias().getName());
        }
        sb.append(".");
        sb.append(IS_DELETE_COLUMN_NAME);
        return new Column(sb.toString());
    }

    private boolean ignore(String tableName) {
        return IGNORE_TABLE_NAMES.contains(tableName.toLowerCase());
    }

    public void justForTest(String sql) throws JSQLParserException {
        Statement statement = CCJSqlParserUtil.parse(sql);

        if (statement instanceof Select) {
            processSelect((Select) statement);
        } else if (statement instanceof Update) {
            processUpdate((Update) statement);
        } else if (statement instanceof Delete) {
            processDelete((Delete) statement);
        } else if (statement instanceof Insert) {
            processInsert((Insert) statement);
        }

        System.out.println(statement.toString());
    }

}

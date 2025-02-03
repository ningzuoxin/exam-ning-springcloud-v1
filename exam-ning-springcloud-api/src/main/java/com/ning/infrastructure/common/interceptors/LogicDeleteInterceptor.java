package com.ning.infrastructure.common.interceptors;

import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SetOperationList;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.sql.Connection;

@Slf4j
@Intercepts({@Signature(
        type = StatementHandler.class,
        method = "prepare",
        args = {Connection.class, Integer.class}
), @Signature(
        type = Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class}
)})
public class LogicDeleteInterceptor implements Interceptor {

    private static final Byte DELETED_VALUE = 1;
    private static final Byte NOT_DELETED_VALUE = 0;
    private static final String LOGIC_DELETE_COLUMN = "is_deleted";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();

        if (target instanceof StatementHandler) {
//            return handleStatementHandler(invocation);
        } else if (target instanceof Executor) {
            return handleExecutor(invocation);
        }

        return invocation.proceed();
    }

//    private Object handleStatementHandler(Invocation invocation) throws Throwable {
//        StatementHandler handler = (StatementHandler) invocation.getTarget();
//        MetaObject metaObject = SystemMetaObject.forObject(handler);
//
//        // 获取SQL语句
//        BoundSql boundSql = handler.getBoundSql();
//        String sql = boundSql.getSql();
//
//        try {
//            // 解析SQL
//            Statement statement = CCJSqlParserUtil.parse(sql);
//
//            if (statement instanceof Select) {
//                sql = handleSelectStatement((Select) statement);
//                metaObject.setValue("delegate.boundSql.sql", sql);
//            }
//        } catch (JSQLParserException e) {
//            log.error("Failed to parse SQL: {}", sql, e);
//        }
//
//        return invocation.proceed();
//    }
//
//    private String handleSelectStatement(Select select) {
//        SelectBody selectBody = select.getSelectBody();
//
//        if (selectBody instanceof PlainSelect) {
//            PlainSelect plainSelect = (PlainSelect) selectBody;
//
//            // 创建逻辑删除条件
//            EqualsTo logicDeleteCondition = new EqualsTo();
//            logicDeleteCondition.setLeftExpression(new Column(LOGIC_DELETE_COLUMN)
//            );
//            logicDeleteCondition.setRightExpression(new StringValue(NOT_DELETED_VALUE)
//            );
//
//            // 添加到WHERE子句
//            Expression where = plainSelect.getWhere();
//            if (where == null) {
//                plainSelect.setWhere(logicDeleteCondition);
//            } else {
//                AndExpression andExpression = new AndExpression(where, logicDeleteCondition
//                );
//                plainSelect.setWhere(andExpression);
//            }
//        } else if (selectBody instanceof SetOperationList) {
//            // 处理UNION等集合操作
//            SetOperationList setOperationList = (SetOperationList) selectBody;
//            for (SelectBody selectBody1 : setOperationList.getSelects()) {
//                if (selectBody1 instanceof PlainSelect) {
//                    handlePlainSelect((PlainSelect) selectBody1);
//                }
//            }
//        }
//
//        return select.toString();
//    }
//
//    private void handlePlainSelect(PlainSelect plainSelect) {
//        // 创建逻辑删除条件
//        EqualsTo logicDeleteCondition = new EqualsTo();
//        logicDeleteCondition.setLeftExpression(new Column(LOGIC_DELETE_COLUMN)
//        );
//        logicDeleteCondition.setRightExpression(new StringValue(NOT_DELETED_VALUE)
//        );
//
//        // 添加到WHERE子句
//        Expression where = plainSelect.getWhere();
//        if (where == null) {
//            plainSelect.setWhere(logicDeleteCondition);
//        } else {
//            AndExpression andExpression = new AndExpression(where, logicDeleteCondition
//            );
//            plainSelect.setWhere(andExpression);
//        }
//    }

    private Object handleExecutor(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Object parameter = args[1];

        if (ms.getSqlCommandType() == SqlCommandType.INSERT) {
//            handleInsert(parameter);
        }

        return invocation.proceed();
    }

}

package com.ning.infrastructure.common.interceptors;

import net.sf.jsqlparser.JSQLParserException;

class IsDeleteInterceptorTest {

    public static void main(String[] args) {
        IsDeleteInterceptor interceptor = new IsDeleteInterceptor();
        try {
//            testInsert(interceptor);
//            testDelete(interceptor);
            testSelect(interceptor);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void testSelect(IsDeleteInterceptor interceptor) throws JSQLParserException {
        // 表名不需要忽略
        String sql = "SELECT * FROM users;";

        // FROM 子句为子查询
        sql = "SELECT * FROM (SELECT id, name FROM users) AS subquery;";

        // JOIN 右表为 Table
        sql = "SELECT * FROM users u JOIN orders o ON u.id = o.user_id;";

        // JOIN 右表为子查询
        sql = "SELECT * FROM users u JOIN (SELECT user_id FROM orders) AS o ON u.id = o.user_id;";

        // 复杂查询
        sql = "SELECT * FROM (SELECT u.id, u.name FROM users u JOIN orders o ON u.id = o.user_id) AS subquery;";

        interceptor.justForTest(sql);
    }

    public static void testDelete(IsDeleteInterceptor interceptor) throws JSQLParserException {
        // 表名不在忽略列表中，且没有 WHERE 条件
        String sql = "DELETE FROM test_table;";

        // 表名不在忽略列表中，且有复杂的 WHERE 条件
        sql = "DELETE FROM test_table WHERE id = 1 AND name = 'John';";

        interceptor.justForTest(sql);
    }

    public static void testInsert(IsDeleteInterceptor interceptor) throws JSQLParserException {
        // 表名不在忽略列表中，且is_deleted列不存在
        String sql = "INSERT INTO test_table (name, age) VALUES ('John', 30);";

//        // 表名在忽略列表中
//        sql = "INSERT INTO ignored_table (name, age) VALUES ('Jane', 25);";
//
//        // 表名不在忽略列表中，且is_deleted列已存在
//        sql = "INSERT INTO test_table (name, age, is_deleted) VALUES ('Bob', 40, 0);";
//
//        // 表名不在忽略列表中，且is_deleted列不存在，使用VALUES子句
//        sql = "INSERT INTO test_table (name, age) VALUES ('Alice', 28), ('David', 35);";
//
        // 表名不在忽略列表中，且is_deleted列不存在，使用SELECT子句
        sql = "INSERT INTO test_table (name, age) SELECT name, age FROM other_table WHERE condition;";

        interceptor.justForTest(sql);
    }

}
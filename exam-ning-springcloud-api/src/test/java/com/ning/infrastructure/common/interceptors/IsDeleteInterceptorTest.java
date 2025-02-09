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
        // 表名不在忽略列表中，且没有 WHERE 条件
        String sql = "DELETE FROM test_table;";

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
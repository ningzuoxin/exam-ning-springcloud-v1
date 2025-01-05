package com.ning.infrastructure.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

/**
 * 用户模块代码生成器
 *
 * @author zuoxin.ning
 * @since 2024-12-02 16:30
 */
public class UserModuleCodeGenerator {

    private static final String MODULE = "/exam-ning-springcloud-system-user";

    private static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/exam-ning-springcloud-user?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "123456";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir") + MODULE;

        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> {
                    builder.author("zuoxin.ning") // 设置作者
                            .disableOpenDir() // 禁止打开输出目录
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ning.infrastructure.persistence") // 设置父包名
                            .mapper("dao") // 设置 dao 目录名称
                            .entity("model") // 设置 data object 目录名称
                            .pathInfo(Collections.singletonMap(OutputFile.xml, projectPath + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.controllerBuilder().disable(); // 不生成 controller 层
                    builder.serviceBuilder().disable(); // 不生成 service 层
                    builder.entityBuilder()
                            .enableLombok() // 开启lombok
                            .enableFileOverride() // 覆盖文件
                            .enableTableFieldAnnotation() // 生成字段注解
                            .formatFileName("%sDO"); // 格式化entity名称
                    builder.mapperBuilder()
                            .enableFileOverride() // 覆盖文件
                            .enableBaseColumnList() // 启用 BaseColumnList
                            .enableBaseResultMap() // 启用 BaseResultMap 生成
                            .formatMapperFileName("%sDao") // Mapper xml 命名方式
                            .formatXmlFileName("%sMapper"); // mapper 命名方式
                    builder.addInclude("sys_menu", "sys_role", "sys_role_menu", "sys_user", "sys_user_role") // 设置需要生成的表名
                            .addTablePrefix("sys"); // 设置过滤表前缀
                }).execute();
    }

}

package com.ning.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;

import java.util.Collections;

/**
 * User模块代码生成器
 */
@SuppressWarnings("ALL")
public class UserCodeGeneration {

    private final static String MODULE = "/exam-ning-springcloud-system-user";

    private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/exam-ning-springcloud-user?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "123456";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir") + MODULE;

        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> {
                    builder.author("ningning") // 设置作者
                            .disableOpenDir() // 禁止打开输出目录
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ning") // 设置父包名
                            .mapper("dao") // 设置dao名
                            .entity("entity") // 设置entity名
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICEIMPL); // 排除controller、service、service impl
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .enableLombok() // 开启lombok
                            .enableTableFieldAnnotation() // 生成字段注解
                            .formatFileName("%s"); // 格式化entity名称
                    builder.mapperBuilder()
                            .enableBaseColumnList() // 启用 BaseColumnList
                            .enableBaseResultMap() // 启用 BaseResultMap 生成
                            .formatMapperFileName("%sDao") // Mapper xml 命名方式
                            .formatXmlFileName("%sMapper"); // mapper 命名方式
                    builder.addInclude("") // 设置需要生成的表名
                            .addTablePrefix(""); // 设置过滤表前缀
                })
                .execute();
    }

}

package com.ning.infrastructure.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.TemplateType;

import java.util.Collections;

/**
 * 考试模块代码生成器
 *
 * @author zuoxin.ning
 * @since 2024-12-02 16:30
 */
public class ExamModuleCodeGenerator {

    private final static String MODULE = "/exam-ning-springcloud-system-exam";

    private final static String DB_URL = "jdbc:mysql://127.0.0.1:3306/exam-ning-springcloud-exam?autoReconnect=true&useUnicode=true&characterEncoding=utf8";
    private final static String DB_USERNAME = "root";
    private final static String DB_PASSWORD = "123456";

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir") + MODULE;

        FastAutoGenerator.create(DB_URL, DB_USERNAME, DB_PASSWORD)
                .globalConfig(builder -> {
                    builder.author("zuoxin.ning") // 设置作者
                            .disableOpenDir() // 禁止打开输出目录
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(projectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.ning.infrastructure.persistence") // 设置父包名
                            .mapper("dao") // 设置 dao 目录名称
                            .entity("model") // 设置 data object 目录名称
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, projectPath + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
                })
                .templateConfig(builder -> {
                    builder.disable(TemplateType.CONTROLLER, TemplateType.SERVICE, TemplateType.SERVICEIMPL, TemplateType.ENTITY); // 排除controller、service、service impl
                })
                .strategyConfig(builder -> {
                    builder.entityBuilder()
                            .enableLombok() // 开启lombok
                            .enableTableFieldAnnotation() // 生成字段注解
                            .formatFileName("%sDO"); // 格式化entity名称
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

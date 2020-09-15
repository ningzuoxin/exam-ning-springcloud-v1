package com.ning.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * User模块代码生成器
 */
@SuppressWarnings("ALL")
public class UserCodeGeneration {

    private static final String MODULAR_NAME = "/exam-ning-springcloud-system-user";

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + MODULAR_NAME + "/src/main/java"); // 生成文件的输出目录
        gc.setFileOverride(true); // 是否覆盖已有文件
        gc.setActiveRecord(false); // 开启 ActiveRecord 模式
        gc.setEnableCache(false); // 是否在xml中添加二级缓存配置
        gc.setBaseResultMap(true); // 开启 BaseResultMap
        gc.setBaseColumnList(true); // 开启 baseColumnList
        gc.setAuthor("ningning"); // 开发人员
        gc.setSwagger2(true); // 开启 swagger2 模式
        gc.setOpen(false); // 是否打开输出目录

        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setControllerName("%sController"); // controller 命名方式
        gc.setServiceName("%sService"); // service 命名方式
        gc.setServiceImplName("%sServiceImpl"); // service impl 命名方式
        gc.setXmlName("%sMapper"); // Mapper xml 命名方式
        gc.setMapperName("%sDao"); // mapper 命名方式
        gc.setEntityName("%s"); // 实体命名方式
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("123456");
        dsc.setUrl("jdbc:mysql://127.0.0.1:3306/exam-ning-springcloud-user?autoReconnect=true&useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=CONVERT_TO_NULL&useSSL=false&serverTimezone=CTT");
        mpg.setDataSource(dsc);

        // 数据库表配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setTablePrefix(new String[]{"srv_"}); // 此处可以修改为您的表前缀
        strategy.setNaming(NamingStrategy.underline_to_camel);// 表名生成策略
        strategy.setInclude(new String[]{"user"}); // 需要生成的表

        strategy.setRestControllerStyle(true);
        strategy.setSuperServiceClass(null);
        strategy.setSuperServiceImplClass(null);
        strategy.setSuperMapperClass(null);
        strategy.setEntityLombokModel(true); // 【实体】是否为lombok模型
        strategy.setEntityTableFieldAnnotationEnable(true); // 是否生成实体时，生成字段注解

        mpg.setStrategy(strategy);

        // 包名配置
        PackageConfig pc = new PackageConfig();
        pc.setParent("com.ning");
        pc.setController("controller");
        pc.setService("service");
        pc.setServiceImpl("service.impl");
        pc.setXml("mapper");
        pc.setMapper("dao");
        pc.setEntity("entity");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
            }
        };

        // 自定义判断是否创建文件
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                System.out.println("fileType => " + fileType + " && filePath => " + filePath);
                if (FileType.ENTITY == fileType || FileType.MAPPER == fileType || FileType.OTHER == fileType) {
                    File file = new File(filePath);
                    boolean exist = file.exists();
                    if (!exist) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        });

        // 如果模板引擎是 velocity
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            // 调整mapper.xml输出目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + MODULAR_NAME + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        cfg.setFileOutConfigList(focList);

        mpg.setCfg(cfg);

        // 模板配置
        TemplateConfig tcg = new TemplateConfig();
        tcg.setController(null);
        tcg.setService(null);
        tcg.setServiceImpl(null);
        tcg.setXml(null);
        mpg.setTemplate(tcg);

        // 执行生成
        mpg.execute();
    }
}

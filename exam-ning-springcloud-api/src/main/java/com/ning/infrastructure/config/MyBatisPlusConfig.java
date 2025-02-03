package com.ning.infrastructure.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.ning.infrastructure.common.model.AbstractDO;
import com.ning.infrastructure.utils.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Slf4j
@Configuration
@MapperScan(value = {"com.ning.infrastructure.persistence.dao"})
public class MyBatisPlusConfig implements MetaObjectHandler {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, AbstractDO.Fields.uid, Long.class, IDUtils.getId());
        this.strictInsertFill(metaObject, AbstractDO.Fields.isDeleted, Byte.class, (byte) 0);
        this.strictInsertFill(metaObject, AbstractDO.Fields.createTime, LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, AbstractDO.Fields.updateTime, LocalDateTime.class, LocalDateTime.now());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName(AbstractDO.Fields.updateTime, LocalDateTime.now(), metaObject);
    }

}

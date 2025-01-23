package com.ning.infrastructure.common.components;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ning.infrastructure.persistence.model.AbstractDO;
import com.ning.infrastructure.utils.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
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

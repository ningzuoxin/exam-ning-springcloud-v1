package com.ning.infrastructure.common.components;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.ning.infrastructure.utils.IDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "uid", Long.class, IDUtils.getId());
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}

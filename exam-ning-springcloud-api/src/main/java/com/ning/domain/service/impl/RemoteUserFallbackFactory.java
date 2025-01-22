package com.ning.domain.service.impl;

import com.ning.domain.service.RemoteUserService;
import com.ning.domain.entity.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

/**
 * 用户服务降级处理
 */
@Slf4j
//@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {

        log.info(" ====== RemoteUserFallbackFactory # create ====== " + throwable.getMessage());

        return username -> new CurrentUser();
    }

}

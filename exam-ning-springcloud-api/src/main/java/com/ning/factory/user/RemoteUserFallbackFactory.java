package com.ning.factory.user;

import com.ning.api.user.RemoteUserService;
import com.ning.infrastructure.common.model.CurrentUser;
import com.ning.infrastructure.common.model.User;
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

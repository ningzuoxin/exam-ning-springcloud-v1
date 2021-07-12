package com.ning.factory.user;

import com.ning.api.user.RemoteUserService;
import com.ning.model.Result;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 */
@Slf4j
@Component
public class RemoteUserFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {

        log.info(" ====== RemoteUserFallbackFactory # create ====== " + throwable.getMessage());

        return username -> Result.fail("");
    }
}

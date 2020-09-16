package com.ning.factory.user;

import com.ning.api.user.RemoteUserService;
import com.ning.model.Result;
import com.ning.model.User;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 用户服务降级处理
 */
@Slf4j
@Component
public class RemoteUserIndexFallbackFactory implements FallbackFactory<RemoteUserService> {

    @Override
    public RemoteUserService create(Throwable throwable) {

        log.info(" ====== RemoteUserIndexFallbackFactory # create ====== " + throwable.getMessage());

        return new RemoteUserService() {
            @Override
            public Result<String> index() {
                return Result.fail("");
            }

            @Override
            public Result<User> selectUserByUsername(String username) {
                return Result.fail("");
            }
        };
    }
}

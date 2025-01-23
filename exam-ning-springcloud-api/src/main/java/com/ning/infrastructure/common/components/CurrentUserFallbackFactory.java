package com.ning.infrastructure.common.components;

import com.ning.domain.service.CurrentUserService;
import com.ning.domain.entity.CurrentUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CurrentUserFallbackFactory implements FallbackFactory<CurrentUserService> {

    @Override
    public CurrentUserService create(Throwable e) {
        log.error("error: {}", e.getMessage(), e);
        return username -> new CurrentUser();
    }

}

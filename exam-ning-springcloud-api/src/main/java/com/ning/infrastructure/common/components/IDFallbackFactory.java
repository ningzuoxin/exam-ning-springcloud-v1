package com.ning.infrastructure.common.components;

import com.ning.domain.service.IDService;
import com.ning.infrastructure.common.leaf.common.Result;
import com.ning.infrastructure.common.leaf.common.Status;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class IDFallbackFactory implements FallbackFactory<IDService> {

    @Override
    public IDService create(Throwable e) {
        log.error("error: {}", e.getMessage(), e);
        return () -> new Result(-1L, Status.EXCEPTION);
    }

}

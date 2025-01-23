package com.ning.domain.service;

import com.ning.infrastructure.common.components.IDFallbackFactory;
import com.ning.infrastructure.common.leaf.common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name = "iDService", path = "/system", url = "http://127.0.0.1:9201", fallbackFactory = IDFallbackFactory.class)
public interface IDService {

    @PostMapping(value = "/ids")
    Result id();

}

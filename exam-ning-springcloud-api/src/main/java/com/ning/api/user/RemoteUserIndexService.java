package com.ning.api.user;

import com.ning.constant.ServiceNameConstants;
import com.ning.factory.user.RemoteUserIndexFallbackFactory;
import com.ning.model.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@FeignClient(contextId = "remoteUserIndexService", value = ServiceNameConstants.USER_SERVICE, fallbackFactory = RemoteUserIndexFallbackFactory.class)
public interface RemoteUserIndexService {

    @GetMapping(value = "/index")
    Result<String> index();

    @GetMapping(value = "/map")
    Result<Map<String, Object>> map();

}

package com.ning.api.user;

import com.ning.constant.ServiceNameConstants;
import com.ning.factory.user.RemoteUserFallbackFactory;
import com.ning.infrastructure.common.model.CurrentUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(contextId = "remoteUserService", path = "/system", value = ServiceNameConstants.USER_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
@FeignClient(name = "remoteUserService", path = "/system", url = "http://127.0.0.1:9201", fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

    @GetMapping(value = "/users/current-user")
    CurrentUser currentUser(@RequestParam("username") String username);

}

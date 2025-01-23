package com.ning.domain.service;

import com.ning.infrastructure.common.components.CurrentUserFallbackFactory;
import com.ning.domain.entity.CurrentUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@FeignClient(contextId = "currentUserService", path = "/system", value = ServiceNameConstants.USER_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
@FeignClient(name = "currentUserService", path = "/system", url = "http://127.0.0.1:9201", fallbackFactory = CurrentUserFallbackFactory.class)
public interface CurrentUserService {

    @GetMapping(value = "/users/current-user")
    CurrentUser currentUser(@RequestParam("username") String username);

}

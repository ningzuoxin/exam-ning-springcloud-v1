package com.ning.api.user;

import com.ning.constant.ServiceNameConstants;
import com.ning.factory.user.RemoteUserFallbackFactory;
import com.ning.model.Result;
import com.ning.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(contextId = "remoteUserService", value = ServiceNameConstants.USER_SERVICE, fallbackFactory = RemoteUserFallbackFactory.class)
public interface RemoteUserService {

    @GetMapping(value = "/index")
    Result<String> index();

    @PostMapping(value = "/user/selectUserByUsername")
    Result<User> selectUserByUsername(@RequestParam("username") String username);

}
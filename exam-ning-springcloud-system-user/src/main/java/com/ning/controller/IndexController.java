package com.ning.controller;

import com.ning.manager.UserManager;
import com.ning.model.Result;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {

    @Value("${server.port}")
    private String port;

    @Resource
    UserManager userManager;
    @Resource
    TokenStore tokenStore;

    @GetMapping(value = "/index")
    public Result<String> index() {
        return Result.ok("system user index " + port);
    }

    @GetMapping(value = "/getInfo")
    public Result getInfo() {

//        // 角色集合
//        Set<String> roles = new HashSet<>();
//        // 权限集合
//        Set<String> permissions = new HashSet<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("user", userManager.getUserById(Convert.toInt(userId)));
//        map.put("roles", roles);
//        map.put("permissions", permissions);
        return Result.ok("getInfo success");
    }

}

package com.ning.controller;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.ning.manager.UserManager;
import com.ning.model.Result;
import com.ning.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class IndexController {

    @Value("${server.port}")
    private String port;

    @Resource
    UserManager userManager;

    @GetMapping(value = "/index")
    public Result<String> index() {
        return Result.ok("system user index " + port);
    }

    @GetMapping(value = "/getInfo")
    public Result getInfo() {

        Authentication authentication = SecurityUtils.getAuthentication();

//        JSON parse = JSONUtil.parse(authentication.getPrincipal());
//        Long userId = parse.getByPath("userId", Long.class);
//        System.out.println(userId);

//        // 角色集合
//        Set<String> roles = new HashSet<>();
//        // 权限集合
//        Set<String> permissions = new HashSet<>();
//        Map<String, Object> map = new HashMap<>();
//        map.put("user", userManager.getUserById(Convert.toInt(userId)));
//        map.put("roles", roles);
//        map.put("permissions", permissions);
        return Result.ok(authentication);
    }

}

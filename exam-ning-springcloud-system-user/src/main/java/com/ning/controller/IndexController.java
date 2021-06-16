package com.ning.controller;

import cn.hutool.core.convert.Convert;
import com.ning.manager.UserManager;
import com.ning.model.Result;
import com.ning.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
        Long userId = SecurityUtils.getLoginUser().getUserId();

        // 角色集合
        Set<String> roles = new HashSet<>();
        // 权限集合
        Set<String> permissions = new HashSet<>();
        Map<String, Object> map = new HashMap<>();
        map.put("user", userManager.getUserById(Convert.toInt(userId)));
        map.put("roles", roles);
        map.put("permissions", permissions);

        return Result.ok(map);
    }

}

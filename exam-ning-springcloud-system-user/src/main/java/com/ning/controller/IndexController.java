package com.ning.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.ning.manager.MenuManager;
import com.ning.manager.RoleManager;
import com.ning.manager.UserManager;
import com.ning.model.Result;
import com.ning.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @Resource
    RoleManager roleManager;
    @Resource
    MenuManager menuManager;

    @GetMapping(value = "/index")
    public Result<String> index() {
        return Result.ok("system user index " + port);
    }

    @GetMapping(value = "/getInfo")
    public Result getInfo() {
        Long userId = SecurityUtils.getLoginUser().getUserId();

        // 角色集合
        Set<String> roles = new HashSet<>();
        String role = roleManager.getRoleKeyByUserId(userId);
        roles.add(StrUtil.isNotEmpty(role) ? role : "");

        // 权限集合
        Set<String> permissions = new HashSet<>();
        permissions.addAll(menuManager.listPermissionsByUserId(userId));

        Map<String, Object> map = new HashMap<>();
        map.put("user", userManager.getUserById(Convert.toInt(userId)));
        map.put("roles", roles);
        map.put("permissions", permissions);

        return Result.ok(map);
    }

    @PreAuthorize("@ss.hasPermi('test1')")
    @GetMapping(value = "/test1")
    public String test1() {
        return "test1";
    }

    @PreAuthorize("@ss.hasPermi('test2')")
    @GetMapping(value = "/test2")
    public String test2() {
        return "test2";
    }

    @PreAuthorize("@ss.hasPermi('test3')")
    @GetMapping(value = "/test3")
    public String test3() {
        return "test3";
    }

}

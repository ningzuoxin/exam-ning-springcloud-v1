package com.ning.controller;

import com.ning.model.Result;
import com.ning.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping(value = "/list")
    public String list() {
        return "user list";
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录")
    public Result login(@RequestParam(value = "username") @ApiParam(name = "username", example = "admin") String username,
                        @RequestParam(value = "password") @ApiParam(name = "password", example = "123456") String password) {
        return userService.login(username, password);
    }

}

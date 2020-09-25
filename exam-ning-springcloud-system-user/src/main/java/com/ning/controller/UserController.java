package com.ning.controller;

import com.ning.entity.User;
import com.ning.model.Result;
import com.ning.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @PostMapping(value = "/selectUserByUsername")
    @ApiOperation(value = "根据用户名查询用户信息")
    public Result selectUserByUsername(@RequestParam(value = "username") @ApiParam(name = "username", example = "admin") String username) {
        return userService.selectUserByUsername(username);
    }

    @GetMapping(value = "/selectUsers")
    @ApiOperation(value = "查询用户列表")
    public Result<List<User>> selectUsers() {
        return userService.selectUsers();
    }

}

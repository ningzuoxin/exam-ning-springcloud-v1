package com.ning.controller;

import cn.hutool.core.date.DateUtil;
import com.ning.constant.CommonConstants;
import com.ning.entity.User;
import com.ning.model.Result;
import com.ning.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/user/")
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

    @PreAuthorize("@ss.hasPermi('system:user:page')")
    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询用户列表")
    public Result selectPage(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                             @RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                             @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return userService.selectUserPage(keyword, pNum, pSize);
    }

    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加用户")
    public Result addUser(@RequestBody @Valid User user) {
        int now = (int) DateUtil.currentSeconds();

        user.setSalt(CommonConstants.DEFAULT_SALT);
        user.setIsDelete(0);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        return userService.addUser(user);
    }

    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @PostMapping(value = "/update")
    @ApiOperation(value = "修改用户")
    public Result update(@RequestBody @Valid User user) {
        return userService.update(user);
    }

    @PreAuthorize("@ss.hasPermi('system:user:delete')")
    @PostMapping(value = "/delete")
    @ApiOperation(value = "删除用户")
    public Result delete(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Integer id) {
        return userService.delete(id);
    }

    @GetMapping(value = "/get")
    @ApiOperation(value = "获取单个用户")
    public Result get(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Integer id) {
        return userService.get(id);
    }

}

package com.ning.interfaces.controller;

import cn.hutool.core.date.DateUtil;
import com.ning.application.dto.UserDTO;
import com.ning.application.service.UserAppService;
import com.ning.constant.CommonConstants;
import com.ning.entity.User;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.common.model.Result;
import com.ning.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 用户控制器
 *
 * @author zuoxin.ning
 * @since 2024-10-16 23:00
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/users/")
public class UserController {

    private final UserAppService userAppService;
    private final UserService userService = new UserService();

    @GetMapping(value = "/by-username")
    @ApiOperation(value = "根据用户名查询用户")
    public Result<UserDTO> getByUsername(@RequestParam(value = "username") @ApiParam(name = "username", example = "admin") String username) {
        return Result.ok(userAppService.getByUsername(username));
    }

    @GetMapping(value = "/all")
    @ApiOperation(value = "查询全部用户")
    public Result<List<UserDTO>> findAll() {
        return Result.ok(userAppService.findAll());
    }

    @PreAuthorize("@ss.hasPermi('system:user:page')")
    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询用户列表")
    public Result<PageWrapper<UserDTO>> findByPage(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pageSize) {
        return Result.ok(userAppService.findByPage(keyword, pageNum, pageSize));
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

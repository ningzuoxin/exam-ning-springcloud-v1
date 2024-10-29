package com.ning.interfaces.controller;

import com.ning.application.assembler.UserAssembler;
import com.ning.application.dto.UserDTO;
import com.ning.application.service.UserAppService;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.common.model.Result;
import com.ning.interfaces.request.AddUserRequest;
import com.ning.interfaces.request.UpdateUserRequest;
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
@RequestMapping(value = "/users")
public class UserController {

    private final UserAppService userAppService;
    private final UserAssembler userAssembler = UserAssembler.INSTANCE;

    @ApiOperation(value = "根据用户名查询用户")
    @GetMapping(value = "/by-username")
    public Result<UserDTO> getByUsername(@RequestParam(value = "username") @ApiParam(name = "username", example = "admin") String username) {
        return Result.ok(userAppService.getByUsername(username));
    }

    @ApiOperation(value = "查询全部用户")
    @GetMapping(value = "/all")
    public Result<List<UserDTO>> findAll() {
        return Result.ok(userAppService.findAll());
    }

//    @PreAuthorize("@ss.hasPermi('system:user:page')")
    @ApiOperation(value = "分页查询用户列表")
    @GetMapping(value = "/page")
    public Result<PageWrapper<UserDTO>> findByPage(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                                                   @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pageSize) {
        return Result.ok(userAppService.findByPage(keyword, pageNum, pageSize));
    }

//    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @ApiOperation(value = "添加用户")
    @PostMapping(value = "")
    public Result<UserDTO> addUser(@RequestBody @Valid AddUserRequest request) {
        UserDTO userDTO = userAssembler.toDTO(request);
        return Result.ok(userAppService.add(userDTO));
    }

//    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @ApiOperation(value = "修改用户")
    @PutMapping(value = "")
    public Result<UserDTO> update(@RequestBody @Valid UpdateUserRequest request) {
        UserDTO userDTO = userAssembler.toDTO(request);
        return Result.ok(userAppService.update(userDTO));
    }

//    @PreAuthorize("@ss.hasPermi('system:user:delete')")
    @ApiOperation(value = "删除用户")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(userAppService.delete(id));
    }

    @ApiOperation(value = "获取单个用户")
    @GetMapping(value = "/{id}")
    public Result<UserDTO> get(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(userAppService.get(id));
    }

}

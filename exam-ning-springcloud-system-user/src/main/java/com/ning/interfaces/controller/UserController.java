package com.ning.interfaces.controller;

import com.ning.application.assembler.UserAssembler;
import com.ning.application.dto.UserDTO;
import com.ning.application.service.UserAppService;
import com.ning.domain.entity.CurrentUser;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.utils.SecurityUtils;
import com.ning.interfaces.request.AddUserRequest;
import com.ning.interfaces.request.UpdateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@CrossOrigin(origins = "*", maxAge = 3600)
public class UserController {

    private final UserAppService userAppService;
    private final UserAssembler userAssembler = UserAssembler.INSTANCE;

    @Operation(summary = "根据用户名查询用户")
    @GetMapping(value = "/current-user")
    public CurrentUser get(@RequestParam(value = "username") @Parameter(name = "username", example = "admin") String username) {
        return userAppService.currentUser(username);
    }

    @Operation(summary = "查询全部用户")
    @GetMapping(value = "/all")
    public List<UserDTO> all() {
        return userAppService.all();
    }

    @PreAuthorize("@ss.hasPermi('system:user:page')")
    @Operation(summary = "分页查询用户列表")
    @GetMapping(value = "/page")
    public PageWrapper<UserDTO> page(@RequestParam(value = "keyword", required = false) @Parameter(name = "keyword", example = "") String keyword,
                                     @RequestParam(value = "pageNum", defaultValue = "1") @Parameter(name = "pageNum", example = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") @Parameter(name = "pageSize", example = "10") Integer pageSize) {
        return userAppService.page(keyword, pageNum, pageSize);
    }

    @PreAuthorize("@ss.hasPermi('system:user:add')")
    @Operation(summary = "添加用户")
    @PostMapping(value = "")
    public UserDTO add(@RequestBody @Valid AddUserRequest request) {
        UserDTO userDTO = userAssembler.toDTO(request);
        return userAppService.add(userDTO);
    }

    @PreAuthorize("@ss.hasPermi('system:user:edit')")
    @Operation(summary = "修改用户")
    @PutMapping(value = "")
    public UserDTO update(@RequestBody @Valid UpdateUserRequest request) {
        UserDTO userDTO = userAssembler.toDTO(request);
        return userAppService.update(userDTO);
    }

    @PreAuthorize("@ss.hasPermi('system:user:delete')")
    @Operation(summary = "删除用户")
    @DeleteMapping(value = "/{id}")
    public Boolean delete(@PathVariable(value = "id") @Parameter(name = "id", example = "1") Long id) {
        return userAppService.delete(id);
    }

    @Operation(summary = "获取单个用户")
    @GetMapping(value = "/{id}")
    public UserDTO get(@PathVariable(value = "id") @Parameter(name = "id", example = "1") Long id) {
        return userAppService.get(id);
    }

    @Operation(summary = "获取当前登录的用户信息")
    @GetMapping(value = "/current-user-info")
    public UserDTO get() {
        return userAppService.get(SecurityUtils.userId());
    }

}

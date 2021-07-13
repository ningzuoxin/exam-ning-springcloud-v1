package com.ning.controller;

import com.ning.entity.Role;
import com.ning.model.Result;
import com.ning.service.RoleService;
import com.ning.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/role/")
public class RoleController {

    @Resource
    RoleService roleService;

    @PreAuthorize("@ss.hasPermi('system:role:page')")
    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询角色列表")
    public Result page(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                       @RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                       @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return roleService.selectPage(keyword, pNum, pSize);
    }

    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加角色")
    public Result add(@RequestBody Role role, @RequestParam List<Long> menuIds) {
        LocalDateTime now = LocalDateTime.now();

        role.setDataScope("1"); // 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
        role.setStatus("0"); // 角色状态（0正常 1停用）
        role.setDelFlag("0"); // 删除标志（0代表存在 2代表删除）
        role.setCreateTime(now);
        role.setUpdateTime(now);
        role.setCreateBy(SecurityUtils.getLoginUser().getUserId() + "");
        role.setUpdateBy(SecurityUtils.getLoginUser().getUserId() + "");
        return roleService.add(role, menuIds);
    }

    @GetMapping(value = "/get")
    @ApiOperation(value = "查询角色")
    public Result get(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return roleService.get(id);
    }

    @PreAuthorize("@ss.hasPermi('system:role:delete')")
    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除角色")
    public Result delete(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return roleService.delete(id);
    }

    @PreAuthorize("@ss.hasPermi('system:role:update')")
    @PostMapping(value = "/update")
    @ApiOperation(value = "修改角色")
    public Result update(@RequestBody Role role, @RequestParam List<Long> menuIds) {
        return roleService.update(role, menuIds);
    }

    @GetMapping(value = "/list")
    @ApiOperation(value = "查询所有角色")
    public Result list() {
        return roleService.list();
    }

}

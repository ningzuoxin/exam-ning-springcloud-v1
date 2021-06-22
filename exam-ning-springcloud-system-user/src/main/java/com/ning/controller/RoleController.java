package com.ning.controller;

import com.ning.entity.Role;
import com.ning.model.Result;
import com.ning.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping(value = "/role/")
public class RoleController {

    @Resource
    RoleService roleService;

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询角色列表")
    public Result page(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                       @RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                       @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return roleService.selectPage(keyword, pNum, pSize);
    }

    @PostMapping(value = "/add")
    @ApiOperation(value = "添加角色")
    public Result add(@RequestBody Role role, @RequestParam List<Long> menuIds) {
        role.setDataScope("1");
        role.setStatus("0");
        role.setDelFlag("0");
        role.setCreateTime(LocalDateTime.now());
        role.setUpdateTime(LocalDateTime.now());
        return roleService.add(role, menuIds);
    }

    @GetMapping(value = "/get")
    @ApiOperation(value = "查询角色")
    public Result get(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return roleService.get(id);
    }

    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除角色")
    public Result delete(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return roleService.delete(id);
    }

    @PostMapping(value = "/update")
    @ApiOperation(value = "修改角色")
    public Result update(@RequestBody Role role, @RequestParam List<Long> menuIds) {
        return roleService.update(role, menuIds);
    }

}

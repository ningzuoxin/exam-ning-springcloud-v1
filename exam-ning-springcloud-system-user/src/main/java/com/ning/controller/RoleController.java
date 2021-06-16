package com.ning.controller;

import com.ning.model.Result;
import com.ning.service.RoleService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/user/role")
public class RoleController {

    @Resource
    RoleService roleService;

    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询角色列表")
    public Result selectPage(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                             @RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                             @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return roleService.selectPage(keyword, pNum, pSize);
    }
    
}

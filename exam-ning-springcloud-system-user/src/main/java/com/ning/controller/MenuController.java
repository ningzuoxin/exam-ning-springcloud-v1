package com.ning.controller;

import com.ning.entity.Menu;
import com.ning.model.Result;
import com.ning.service.MenuService;
import com.ning.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 菜单管理
 */
@RestController
@RequestMapping(value = "/menu/")
public class MenuController {

    @Resource
    MenuService menuService;

    @PreAuthorize("@ss.hasPermi('system:menu:page')")
    @GetMapping(value = "/page")
    @ApiOperation(value = "分页查询菜单列表")
    public Result page(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                       @RequestParam(value = "pNum", defaultValue = "1") @ApiParam(name = "pNum", example = "1") Integer pNum,
                       @RequestParam(value = "pSize", defaultValue = "10") @ApiParam(name = "pSize", example = "10") Integer pSize) {
        return menuService.selectPage(keyword, pNum, pSize);
    }

    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @PostMapping(value = "/add")
    @ApiOperation(value = "添加菜单")
    public Result add(@RequestBody Menu menu) {
        LocalDateTime now = LocalDateTime.now();
        menu.setCreateBy(SecurityUtils.getLoginUser().getUserId() + "");
        menu.setUpdateBy(SecurityUtils.getLoginUser().getUserId() + "");
        menu.setCreateTime(now);
        menu.setUpdateTime(now);
        return menuService.add(menu);
    }

    @GetMapping(value = "/queryMC")
    @ApiOperation(value = "查询目录和菜单")
    public Result queryMC() {
        return menuService.queryMC();
    }

    @GetMapping(value = "/tree")
    @ApiOperation(value = "查询菜单树形列表")
    public Result tree() {
        return menuService.tree();
    }

    @GetMapping(value = "/getRouters")
    @ApiOperation(value = "查询路由")
    public Result getRouters() {
        return menuService.getRouters(SecurityUtils.getLoginUser().getUserId());
    }

    @GetMapping(value = "/get")
    @ApiOperation(value = "根据id查询菜单")
    public Result get(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return menuService.get(id);
    }

    @PreAuthorize("@ss.hasPermi('system:menu:update')")
    @PostMapping(value = "/update")
    @ApiOperation(value = "修改菜单")
    public Result update(@RequestBody Menu menu) {
        menu.setUpdateBy(SecurityUtils.getLoginUser().getUserId() + "");
        menu.setUpdateTime(LocalDateTime.now());
        return menuService.update(menu);
    }

    @PreAuthorize("@ss.hasPermi('system:menu:delete')")
    @GetMapping(value = "/delete")
    @ApiOperation(value = "删除菜单")
    public Result delete(@RequestParam(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return menuService.delete(id);
    }

}

package com.ning.interfaces.controller;

import com.ning.application.assembler.MenuAssembler;
import com.ning.application.dto.MenuDTO;
import com.ning.application.service.MenuAppService;
import com.ning.entity.Menu;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.common.model.Result;
import com.ning.interfaces.request.AddMenuRequest;
import com.ning.service.MenuService;
import com.ning.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 菜单控制器
 *
 * @author zuoxin.ning
 * @since 2024-10-31 12:00
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/menus")
public class MenuController {

    private final MenuAppService menuAppService;
    private final MenuAssembler menuAssembler = MenuAssembler.INSTANCE;

    @Resource
    MenuService menuService;

    //    @PreAuthorize("@ss.hasPermi('system:menu:page')")
    @ApiOperation(value = "分页查询菜单列表")
    @GetMapping(value = "/page")
    public Result<PageWrapper<MenuDTO>> page(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                                             @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pageSize) {
        return Result.ok(menuAppService.findByPage(keyword, pageNum, pageSize));
    }

    //    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @ApiOperation(value = "添加菜单")
    @PostMapping(value = "")
    public Result<MenuDTO> add(@RequestBody AddMenuRequest request) {
        MenuDTO menuDTO = menuAssembler.toDTO(request);
        return Result.ok(menuAppService.add(menuDTO));
    }

    @ApiOperation(value = "查询目录和菜单")
    @GetMapping(value = "/catalog-menu")
    public Result<List<MenuDTO>> findCatalogAndMenu() {
        return Result.ok(menuAppService.findCatalogAndMenu());
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

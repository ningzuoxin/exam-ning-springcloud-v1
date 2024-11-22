package com.ning.interfaces.controller;

import com.ning.application.assembler.MenuAssembler;
import com.ning.application.dto.MenuDTO;
import com.ning.application.dto.RouterDTO;
import com.ning.application.dto.TreeSelectDTO;
import com.ning.application.service.MenuAppService;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.common.model.Result;
import com.ning.interfaces.request.AddMenuRequest;
import com.ning.interfaces.request.UpdateMenuRequest;
import com.ning.utils.SecurityUtils;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

    //    @PreAuthorize("@ss.hasPermi('system:menu:page')")
    @ApiOperation(value = "分页查询菜单列表")
    @GetMapping(value = "/page")
    public Result<PageWrapper<MenuDTO>> page(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                                             @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pageNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pageSize) {
        return Result.ok(menuAppService.page(keyword, pageNum, pageSize));
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
    public Result<List<MenuDTO>> catalogAndMenu() {
        return Result.ok(menuAppService.catalogAndMenu());
    }

    @ApiOperation(value = "查询树形菜单列表")
    @GetMapping(value = "/tree")
    public Result<List<TreeSelectDTO>> tree() {
        return Result.ok(menuAppService.tree());
    }

    @ApiOperation(value = "查询路由")
    @GetMapping(value = "/routers")
    public Result<List<RouterDTO>> routers() {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        return Result.ok(menuAppService.routers(userId));
    }

    @ApiOperation(value = "根据id查询菜单")
    @GetMapping(value = "/{id:\\d+}")
    public Result<MenuDTO> get(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(menuAppService.get(id));
    }

    //    @PreAuthorize("@ss.hasPermi('system:menu:update')")
    @ApiOperation(value = "修改菜单")
    @PutMapping(value = "")
    public Result<MenuDTO> update(@RequestBody UpdateMenuRequest request) {
        MenuDTO menuDTO = menuAssembler.toDTO(request);
        return Result.ok(menuAppService.update(menuDTO));
    }

    //    @PreAuthorize("@ss.hasPermi('system:menu:delete')")
    @ApiOperation(value = "删除菜单")
    @DeleteMapping(value = "{id}")
    public Result<Boolean> delete(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(menuAppService.delete(id));
    }

}

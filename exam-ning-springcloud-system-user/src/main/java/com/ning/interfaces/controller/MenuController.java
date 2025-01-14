package com.ning.interfaces.controller;

import com.ning.application.assembler.MenuAssembler;
import com.ning.application.dto.MenuDTO;
import com.ning.application.dto.RouterDTO;
import com.ning.application.dto.TreeSelectDTO;
import com.ning.application.service.MenuAppService;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.utils.SecurityUtils;
import com.ning.interfaces.request.AddMenuRequest;
import com.ning.interfaces.request.UpdateMenuRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
@CrossOrigin(origins = "*", maxAge = 3600)
public class MenuController {

    private final MenuAppService menuAppService;
    private final MenuAssembler menuAssembler = MenuAssembler.INSTANCE;

    //    @PreAuthorize("@ss.hasPermi('system:menu:page')")
    @Operation(summary = "分页查询菜单列表")
    @GetMapping(value = "/page")
    public PageWrapper<MenuDTO> page(@RequestParam(value = "keyword", required = false) @Parameter(name = "keyword", example = "") String keyword,
                                     @RequestParam(value = "pageNum", defaultValue = "1") @Parameter(name = "pageNum", example = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") @Parameter(name = "pageSize", example = "10") Integer pageSize) {
        return menuAppService.page(keyword, pageNum, pageSize);
    }

    //    @PreAuthorize("@ss.hasPermi('system:menu:add')")
    @Operation(summary = "添加菜单")
    @PostMapping(value = "")
    public MenuDTO add(@RequestBody AddMenuRequest request) {
        MenuDTO menuDTO = menuAssembler.toDTO(request);
        return menuAppService.add(menuDTO);
    }

    @Operation(summary = "查询目录和菜单")
    @GetMapping(value = "/catalog-menu")
    public List<MenuDTO> catalogAndMenu() {
        return menuAppService.catalogAndMenu();
    }

    @Operation(summary = "查询树形菜单列表")
    @GetMapping(value = "/tree")
    public List<TreeSelectDTO> tree() {
        return menuAppService.tree();
    }

    @Operation(summary = "查询路由")
    @GetMapping(value = "/routers")
    public List<RouterDTO> routers() {
        Long userId = SecurityUtils.getLoginUser().getUserId();
        return menuAppService.routers(userId);
    }

    @Operation(summary = "根据id查询菜单")
    @GetMapping(value = "/{id:\\d+}")
    public MenuDTO get(@PathVariable(value = "id") @Parameter(name = "id", example = "1") Long id) {
        return menuAppService.get(id);
    }

    //    @PreAuthorize("@ss.hasPermi('system:menu:update')")
    @Operation(summary = "修改菜单")
    @PutMapping(value = "")
    public MenuDTO update(@RequestBody UpdateMenuRequest request) {
        MenuDTO menuDTO = menuAssembler.toDTO(request);
        return menuAppService.update(menuDTO);
    }

    //    @PreAuthorize("@ss.hasPermi('system:menu:delete')")
    @Operation(summary = "删除菜单")
    @DeleteMapping(value = "{id}")
    public Boolean delete(@PathVariable(value = "id") @Parameter(name = "id", example = "1") Long id) {
        return menuAppService.delete(id);
    }

}

package com.ning.interfaces.controller;

import com.ning.application.assembler.RoleAssembler;
import com.ning.application.dto.RoleDTO;
import com.ning.application.service.RoleAppService;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.interfaces.request.AddRoleRequest;
import com.ning.interfaces.request.UpdateRoleRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色控制器
 *
 * @author zuoxin.ning
 * @since 2024-10-31 12:00
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/roles")
@CrossOrigin(origins = "*", maxAge = 3600)
public class RoleController {

    private final RoleAppService roleAppService;
    private final RoleAssembler roleAssembler = RoleAssembler.INSTANCE;

    //    @PreAuthorize("@ss.hasPermi('system:role:page')")
    @Operation(summary = "分页查询角色列表")
    @GetMapping(value = "/page")
    public PageWrapper<RoleDTO> page(@RequestParam(value = "keyword", required = false) @Parameter(name = "keyword", example = "") String keyword,
                                     @RequestParam(value = "pageNum", defaultValue = "1") @Parameter(name = "pageNum", example = "1") Integer pageNum,
                                     @RequestParam(value = "pageSize", defaultValue = "10") @Parameter(name = "pageSize", example = "10") Integer pageSize) {
        return roleAppService.page(keyword, pageNum, pageSize);
    }

    //    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @Operation(summary = "添加角色")
    @PostMapping(value = "")
    public RoleDTO add(@RequestBody AddRoleRequest request) {
        RoleDTO roleDTO = roleAssembler.toDTO(request);
        return roleAppService.add(roleDTO);
    }

    @Operation(summary = "查询角色")
    @GetMapping(value = "/{id}")
    public RoleDTO get(@PathVariable(value = "id") @Parameter(name = "id", example = "1") Long id) {
        return roleAppService.get(id);
    }

    //    @PreAuthorize("@ss.hasPermi('system:role:delete')")
    @Operation(summary = "删除角色")
    @DeleteMapping(value = "/{id}")
    public Boolean delete(@PathVariable(value = "id") @Parameter(name = "id", example = "1") Long id) {
        return roleAppService.delete(id);
    }

    //    @PreAuthorize("@ss.hasPermi('system:role:update')")
    @Operation(summary = "修改角色")
    @PutMapping(value = "")
    public RoleDTO update(@RequestBody UpdateRoleRequest request) {
        RoleDTO roleDTO = roleAssembler.toDTO(request);
        return roleAppService.update(roleDTO);
    }

    @Operation(summary = "查询所有角色")
    @GetMapping(value = "/all")
    public List<RoleDTO> all() {
        return roleAppService.all();
    }

}

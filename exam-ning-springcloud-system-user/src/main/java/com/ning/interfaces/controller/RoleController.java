package com.ning.interfaces.controller;

import com.ning.application.assembler.RoleAssembler;
import com.ning.application.dto.RoleDTO;
import com.ning.application.service.RoleAppService;
import com.ning.infrastructure.common.model.PageWrapper;
import com.ning.infrastructure.common.model.Result;
import com.ning.interfaces.request.AddRoleRequest;
import com.ning.interfaces.request.UpdateRoleRequest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
public class RoleController {

    private final RoleAppService roleAppService;
    private final RoleAssembler roleAssembler = RoleAssembler.INSTANCE;

    //    @PreAuthorize("@ss.hasPermi('system:role:page')")
    @ApiOperation(value = "分页查询角色列表")
    @GetMapping(value = "/page")
    public Result<PageWrapper<RoleDTO>> page(@RequestParam(value = "keyword", required = false) @ApiParam(name = "keyword", example = "") String keyword,
                                             @RequestParam(value = "pageNum", defaultValue = "1") @ApiParam(name = "pageNum", example = "1") Integer pNum,
                                             @RequestParam(value = "pageSize", defaultValue = "10") @ApiParam(name = "pageSize", example = "10") Integer pSize) {
        return Result.ok(roleAppService.findByPage(keyword, pNum, pSize));
    }

    //    @PreAuthorize("@ss.hasPermi('system:role:add')")
    @ApiOperation(value = "添加角色")
    @PostMapping(value = "")
    public Result<RoleDTO> add(@RequestBody AddRoleRequest request) {
        RoleDTO roleDTO = roleAssembler.toDTO(request);
        return Result.ok(roleAppService.add(roleDTO));
    }

    @ApiOperation(value = "查询角色")
    @GetMapping(value = "/{id}")
    public Result<RoleDTO> get(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(roleAppService.get(id));
    }

    //    @PreAuthorize("@ss.hasPermi('system:role:delete')")
    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/{id}")
    public Result<Boolean> delete(@PathVariable(value = "id") @ApiParam(name = "id", example = "1") Long id) {
        return Result.ok(roleAppService.delete(id));
    }

    //    @PreAuthorize("@ss.hasPermi('system:role:update')")
    @ApiOperation(value = "修改角色")
    @PutMapping(value = "")
    public Result<RoleDTO> update(@RequestBody UpdateRoleRequest request) {
        RoleDTO roleDTO = roleAssembler.toDTO(request);
        return Result.ok(roleAppService.update(roleDTO));
    }

    @ApiOperation(value = "查询所有角色")
    @GetMapping(value = "/all")
    public Result<List<RoleDTO>> findAll() {
        return Result.ok(roleAppService.findAll());
    }

}

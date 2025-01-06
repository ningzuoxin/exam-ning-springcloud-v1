package com.ning.interfaces.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 修改角色请求
 *
 * @author zuoxin.ning
 * @since 2024-10-31 09:30
 */
@Getter
@Setter
@Schema(name = "UpdateRoleRequest", description = "修改角色请求")
public class UpdateRoleRequest {

    @NotNull(message = "ID不能为空")
    @NotEmpty(message = "ID不能为空")
    @Schema(name = "角色ID")
    private Long id;

    @NotEmpty(message = "角色名称不能为空")
    @Schema(name = "角色名称")
    private String roleName;

    @Schema(name = "排序")
    private Integer sortNum;

    @Schema(name = "状态")
    private Integer status;

    @Schema(name = "菜单ID列表")
    private List<Long> menuIdList;

}

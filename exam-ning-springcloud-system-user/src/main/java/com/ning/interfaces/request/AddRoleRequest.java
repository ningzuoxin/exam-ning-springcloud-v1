package com.ning.interfaces.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

/**
 * 添加角色请求
 *
 * @author zuoxin.ning
 * @since 2024-10-31 09:30
 */
@Getter
@Setter
@Schema(name = "AddRoleRequest", description = "添加角色请求")
public class AddRoleRequest {

    @NotEmpty(message = "角色名称不能为空")
    @Schema(name = "角色名称")
    private String roleName;

    @NotEmpty(message = "角色权限字符串不能为空")
    @Schema(name = "角色权限字符串")
    private String roleKey;

    @Schema(name = "排序")
    private Integer sortNum;

    @Schema(name = "菜单ID列表")
    private List<Long> menuIdList;

}

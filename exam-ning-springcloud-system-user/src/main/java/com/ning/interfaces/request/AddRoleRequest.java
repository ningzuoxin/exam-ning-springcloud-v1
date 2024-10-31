package com.ning.interfaces.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 添加角色请求
 *
 * @author zuoxin.ning
 * @since 2024-10-31 09:30
 */
@Getter
@Setter
@ApiModel(value = "AddRoleRequest", description = "添加角色请求")
public class AddRoleRequest {

    @NotEmpty(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @NotEmpty(message = "角色权限字符串不能为空")
    @ApiModelProperty(value = "角色权限字符串")
    private String roleKey;

    @ApiModelProperty(value = "排序")
    private Integer sortNum;

    @ApiModelProperty(value = "菜单ID列表")
    private List<Long> menuIdList;

}

package com.ning.interfaces.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * 修改角色请求
 *
 * @author zuoxin.ning
 * @since 2024-10-31 09:30
 */
@Getter
@Setter
@ApiModel(value = "UpdateRoleRequest", description = "修改角色请求")
public class UpdateRoleRequest {

    @ApiModelProperty(value = "角色ID")
    private Long id;

    @NotEmpty(message = "角色名称不能为空")
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "排序")
    private Integer sortNum;

    @ApiModelProperty(value = "状态")
    private Integer status;

    @ApiModelProperty(value = "菜单ID列表")
    private List<Long> menuIdList;

}

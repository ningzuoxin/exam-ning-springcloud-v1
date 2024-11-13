package com.ning.interfaces.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 添加菜单请求
 *
 * @author zuoxin.ning
 * @since 2024-11-13 22:30
 */
@Getter
@Setter
@ApiModel(value = "AddMenuRequest", description = "添加菜单请求")
public class AddMenuRequest {

    @NotEmpty(message = "菜单名称不能为空")
    @ApiModelProperty(value = "菜单名称")
    private String menuName;

    @NotEmpty(message = "父菜单ID不能为空")
    @ApiModelProperty(value = "父菜单ID")
    private Long parentUid;

    @ApiModelProperty(value = "排序")
    private Integer sortNum;

    @NotEmpty(message = "路由地址不能为空")
    @ApiModelProperty(value = "路由地址")
    private String path;

    @NotEmpty(message = "组件路径不能为空")
    @ApiModelProperty(value = "组件路径")
    private String component;

    @NotEmpty(message = "是否为外链不能为空")
    @ApiModelProperty(value = "是否为外链，0：否；1：是；")
    private Integer isFrame;

    @NotEmpty(message = "是否为外链不能为空")
    @ApiModelProperty(value = "菜单类型，1：目录；2：菜单；3：按钮；")
    private Integer menuType;

    @ApiModelProperty(value = "权限标识")
    private String perms;

    @ApiModelProperty(value = "菜单图标")
    private String icon;

}

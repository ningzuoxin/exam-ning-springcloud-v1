package com.ning.interfaces.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * 添加菜单请求
 *
 * @author zuoxin.ning
 * @since 2024-11-13 22:30
 */
@Getter
@Setter
@Schema(name = "AddMenuRequest", description = "添加菜单请求")
public class UpdateMenuRequest {

    @NotNull(message = "ID不能为空")
    @NotEmpty(message = "ID不能为空")
    @Schema(name = "菜单ID")
    private Long id;

    @NotEmpty(message = "菜单名称不能为空")
    @Schema(name = "菜单名称")
    private String menuName;

    @NotEmpty(message = "父菜单ID不能为空")
    @Schema(name = "父菜单ID")
    private Long parentId;

    @Schema(name = "排序")
    private Integer sortNum;

    @NotEmpty(message = "路由地址不能为空")
    @Schema(name = "路由地址")
    private String path;

    @NotEmpty(message = "组件路径不能为空")
    @Schema(name = "组件路径")
    private String component;

    @NotEmpty(message = "是否为外链不能为空")
    @Schema(name = "是否为外链，0：否；1：是；")
    private Integer isFrame;

    @NotEmpty(message = "是否为外链不能为空")
    @Schema(name = "菜单类型，1：目录；2：菜单；3：按钮；")
    private Integer menuType;

    @Schema(name = "权限标识")
    private String perms;

    @Schema(name = "菜单图标")
    private String icon;

}

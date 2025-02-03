package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.ning.infrastructure.common.model.AbstractDO;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 系统菜单表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2025-01-01 00:00:01
 */
@Getter
@Setter
@TableName("sys_menu")
@Schema(name = "MenuDO", description = "系统菜单表")
public class MenuDO extends AbstractDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单名称")
    @TableField("menu_name")
    private String menuName;

    @Schema(description = "父菜单ID")
    @TableField("parent_uid")
    private Long parentUid;

    @Schema(description = "排序")
    @TableField("sort_num")
    private Integer sortNum;

    @Schema(description = "路由地址")
    @TableField("path")
    private String path;

    @Schema(description = "组件路径")
    @TableField("component")
    private String component;

    @Schema(description = "是否为外链，0：否；1：是；")
    @TableField("is_frame")
    private Byte isFrame;

    @Schema(description = "菜单类型，1：目录；2：菜单；3：按钮；")
    @TableField("menu_type")
    private Byte menuType;

    @Schema(description = "菜单状态，0：正常；1：停用；")
    @TableField("status")
    private Byte status;

    @Schema(description = "权限标识")
    @TableField("perms")
    private String perms;

    @Schema(description = "菜单图标")
    @TableField("icon")
    private String icon;

}

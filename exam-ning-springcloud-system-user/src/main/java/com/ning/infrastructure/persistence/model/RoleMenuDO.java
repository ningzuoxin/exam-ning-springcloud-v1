package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 系统角色菜单表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2025-01-01 00:00:01
 */
@Getter
@Setter
@TableName("sys_role_menu")
@Schema(name = "RoleMenuDO", description = "系统角色菜单表")
public class RoleMenuDO extends AbstractDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色ID")
    @TableField("role_uid")
    private Long roleUid;

    @Schema(description = "菜单ID")
    @TableField("menu_uid")
    private Long menuUid;

}

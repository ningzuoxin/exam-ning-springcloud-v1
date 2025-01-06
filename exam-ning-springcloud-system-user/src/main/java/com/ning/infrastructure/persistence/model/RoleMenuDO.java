package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

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
public class RoleMenuDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "业务ID")
    @TableField("uid")
    private Long uid;

    @Schema(description = "角色ID")
    @TableField("role_uid")
    private Long roleUid;

    @Schema(description = "菜单ID")
    @TableField("menu_uid")
    private Long menuUid;

    @Schema(description = "是否删除，0：未删除；1：已删除；")
    @TableField("is_deleted")
    private Byte isDeleted;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private LocalDateTime updateTime;
}

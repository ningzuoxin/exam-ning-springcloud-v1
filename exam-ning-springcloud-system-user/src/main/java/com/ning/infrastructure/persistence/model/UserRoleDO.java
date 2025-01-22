package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 系统用户角色表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2025-01-01 00:00:01
 */
@Getter
@Setter
@TableName("sys_user_role")
@Schema(name = "UserRoleDO", description = "系统用户角色表")
public class UserRoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键自增ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "业务ID")
    @TableField(value = "uid", fill = FieldFill.INSERT)
    private Long uid;

    @Schema(description = "用户ID")
    @TableField("user_uid")
    private Long userUid;

    @Schema(description = "角色ID")
    @TableField("role_uid")
    private Long roleUid;

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

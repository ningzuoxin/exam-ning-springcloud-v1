package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

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
public class UserRoleDO extends AbstractDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "用户ID")
    @TableField("user_uid")
    private Long userUid;

    @Schema(description = "角色ID")
    @TableField("role_uid")
    private Long roleUid;

}

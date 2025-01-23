package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2025-01-01 00:00:01
 */
@Getter
@Setter
@TableName("sys_role")
@Schema(name = "RoleDO", description = "系统角色表")
public class RoleDO extends AbstractDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "角色名称")
    @TableField("role_name")
    private String roleName;

    @Schema(description = "角色权限字符串")
    @TableField("role_key")
    private String roleKey;

    @Schema(description = "排序")
    @TableField("sort_num")
    private Integer sortNum;

    @Schema(description = "角色状态，0：正常；1：停用；")
    @TableField("status")
    private Byte status;

}

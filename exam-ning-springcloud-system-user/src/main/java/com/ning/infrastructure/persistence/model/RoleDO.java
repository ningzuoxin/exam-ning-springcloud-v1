package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

/**
 * <p>
 * 系统角色表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-10-30 21:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_role")
@ApiModel(value = "RoleDO对象", description = "系统角色表")
public class RoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "角色名称")
    @TableField("role_name")
    private String roleName;

    @ApiModelProperty(value = "角色权限字符串")
    @TableField("role_key")
    private String roleKey;

    @ApiModelProperty(value = "排序")
    @TableField("sort_num")
    private Integer sortNum;

    @ApiModelProperty(value = "角色状态，0：正常；1：停用；")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "是否删除，0：未删除；1：已删除；")
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Instant createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Instant updateTime;

}

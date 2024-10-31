package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

/**
 * <p>
 * 系统用户角色表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-10-30 21:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_user_role")
@ApiModel(value = "UserRoleDO对象", description = "系统用户角色表")
public class UserRoleDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "用户ID")
    @TableField("user_uid")
    private Long userUid;

    @ApiModelProperty(value = "角色ID")
    @TableField("role_uid")
    private Long roleUid;

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

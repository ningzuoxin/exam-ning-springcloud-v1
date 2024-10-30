package com.ning.domain.entity;

import com.ning.domain.types.RoleId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色实体
 *
 * @author zuoxin.ning
 * @since 2024-10-30 21:30
 */
@Getter
@Setter
@ToString
public class Role {

    // 角色 ID
    private RoleId id;
    // 角色名称
    private String roleName;
    // 角色权限字符串
    private String roleKey;
    // 排序
    private Integer sortNum;
    // 角色状态，0：正常；1：停用；
    private Integer status;

    public Role(RoleId id, String roleName, String roleKey, Integer sortNum, Integer status) {
        this.id = id;
        this.roleName = roleName;
        this.roleKey = roleKey;
        this.sortNum = sortNum;
        this.status = status;
    }

}

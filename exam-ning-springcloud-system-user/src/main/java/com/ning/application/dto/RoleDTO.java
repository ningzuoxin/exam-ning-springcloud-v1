package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 角色 DTO
 *
 * @author zuoxin.ning
 * @since 2024-10-31 08:00
 */
@Getter
@Setter
public class RoleDTO {

    // 角色 ID
    private Long id;
    // 角色名称
    private String roleName;
    // 角色权限字符串
    private String roleKey;
    // 排序
    private Integer sortNum;
    // 角色状态，0：正常；1：停用；
    private Integer status;

}

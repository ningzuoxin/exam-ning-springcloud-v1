package com.ning.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Set;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author ningning
 * @since 2020-09-15
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private String email;
    private String mobile;
    private String salt;
    private String nickname;
    private String idcard;
    private Integer gender;
    private String avatar;
    private Integer isDelete;
    private Integer createTime;
    private Integer updateTime;
    private Set<String> roles;
    private Set<String> permissions;

}

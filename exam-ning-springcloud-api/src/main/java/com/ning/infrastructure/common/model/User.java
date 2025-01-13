package com.ning.infrastructure.common.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
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
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
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

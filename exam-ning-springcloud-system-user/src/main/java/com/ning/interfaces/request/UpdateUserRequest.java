package com.ning.interfaces.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

/**
 * 修改用户请求
 *
 * @author zuoxin.ning
 * @since 2024-10-29 21:30
 */
@Getter
@Setter
@Schema(name = "UpdateUserRequest", description = "修改用户请求")
public class UpdateUserRequest {

    @NotNull(message = "ID不能为空")
    @Schema(name = "用户ID")
    private Long id;

    @NotEmpty(message = "密码不能为空")
    @Schema(name = "密码")
    private String password;

    @NotEmpty(message = "昵称不能为空")
    @Schema(name = "昵称")
    private String nickname;

    @Schema(name = "性别")
    private Integer gender;

    @NotEmpty(message = "手机号码不能为空")
    @Schema(name = "手机号码")
    private String phoneNumber;

    @Schema(name = "身份证号")
    private String idNumber;

    @Schema(name = "电子邮箱")
    private String email;

    @Schema(name = "头像")
    private String avatar;

    @NotNull(message = "角色不能为空")
    @Schema(name = "角色ID")
    private Long roleId;

}

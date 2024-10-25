package com.ning.interfaces.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 添加用户请求
 *
 * @author zuoxin.ning
 * @since 2024-10-25 08:30
 */
@Getter
@Setter
@ApiModel(value = "AddUserRequest", description = "添加用户请求")
public class AddUserRequest {

    @NotEmpty(message = "账号不能为空")
    @ApiModelProperty(value = "账号")
    private String username;

    @NotEmpty(message = "密码不能为空")
    @ApiModelProperty(value = "密码")
    private String password;

    @NotEmpty(message = "电子邮箱不能为空")
    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @NotEmpty(message = "手机号码不能为空")
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;

}

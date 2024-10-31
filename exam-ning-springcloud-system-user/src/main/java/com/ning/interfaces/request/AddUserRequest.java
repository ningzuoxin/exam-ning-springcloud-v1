package com.ning.interfaces.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

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

    @NotEmpty(message = "昵称不能为空")
    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "性别")
    private Integer gender;

    @NotEmpty(message = "手机号码不能为空")
    @ApiModelProperty(value = "手机号码")
    private String phoneNumber;

    @ApiModelProperty(value = "身份证号")
    private String idNumber;

    @ApiModelProperty(value = "电子邮箱")
    private String email;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @NotNull(message = "角色不能为空")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

}

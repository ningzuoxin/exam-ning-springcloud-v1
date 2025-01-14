package com.ning.infrastructure.common.model;

import com.ning.constant.ErrorCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 统一错误响应
 */
@Data
@NoArgsConstructor
public class ErrorResponse implements Serializable {

    private String code;
    private String message;

    public ErrorResponse(ErrorCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}

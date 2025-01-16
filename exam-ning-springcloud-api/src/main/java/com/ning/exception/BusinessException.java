package com.ning.exception;

import com.ning.constant.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4585228147276606617L;

    private String code;
    private String message;

    private BusinessException() {
        super();
    }

    public BusinessException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BusinessException(ErrorCodeEnum errorCodeEnum) {
        super();
        this.code = errorCodeEnum.getCode();
        this.message = errorCodeEnum.getMessage();
    }

}

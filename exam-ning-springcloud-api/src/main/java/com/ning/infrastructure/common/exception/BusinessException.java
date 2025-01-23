package com.ning.infrastructure.common.exception;

import com.ning.infrastructure.common.enums.ErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;

@Getter
@Setter
public class BusinessException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -4585228147276606617L;

    private ErrorCodeEnum errorCode;

    private BusinessException() {
        super();
    }

    public BusinessException(ErrorCodeEnum errorCode) {
        super();
        this.errorCode = errorCode;
    }

}

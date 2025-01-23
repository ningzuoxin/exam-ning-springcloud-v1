package com.ning.infrastructure.common.model;

import com.ning.infrastructure.common.enums.ErrorCodeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class ErrorResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = 925269281345091642L;

    private String code;
    private String message;

    public ErrorResponse(ErrorCodeEnum errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

}

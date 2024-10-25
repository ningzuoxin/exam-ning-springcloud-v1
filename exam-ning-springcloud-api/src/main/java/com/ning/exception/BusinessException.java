package com.ning.exception;

import com.ning.constant.BusinessCodeEnum;
import lombok.Getter;
import lombok.Setter;

/**
 * 自定义业务异常
 *
 * @author zuoxin.ning
 * @since 2024-10-25 14:00
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -4585228147276606617L;

    private Integer code;
    private String message;

    private BusinessException() {
        super();
    }

    public BusinessException(int code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BusinessException(BusinessCodeEnum businessCodeEnum) {
        super();
        this.code = businessCodeEnum.getCode();
        this.message = businessCodeEnum.getMessage();
    }

}

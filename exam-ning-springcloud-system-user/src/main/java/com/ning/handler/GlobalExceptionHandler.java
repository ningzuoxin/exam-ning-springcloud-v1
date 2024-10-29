package com.ning.handler;

import com.ning.exception.BusinessException;
import com.ning.infrastructure.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 */
@Slf4j
@Order(-1)
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 处理自定义业务异常
     *
     * @param e 自定义业务异常
     * @return 统一响应结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BusinessException.class)
    public Result<String> handleBusinessException(BusinessException e) {
        return Result.fail(null, e.getCode(), e.getMessage());
    }

    /**
     * 请求方法中校验抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleConstraintViolationException(ConstraintViolationException e) {
        // 获取异常中第一个错误信息
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return Result.fail(message);
    }

    /**
     * POST请求参数校验抛出的异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 获取异常中随机一个异常信息
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(message);
    }

}

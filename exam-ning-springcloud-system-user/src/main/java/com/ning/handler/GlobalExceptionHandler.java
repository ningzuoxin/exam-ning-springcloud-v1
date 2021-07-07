package com.ning.handler;

import com.ning.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolationException;

/**
 * 全局异常处理
 */
@Slf4j
@Order(-1)
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 请求方法中校验抛出的异常
     *
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    public Result constraintViolationExceptionHandler(ConstraintViolationException e) {
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
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        // 获取异常中随机一个异常信息
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(message);
    }

}

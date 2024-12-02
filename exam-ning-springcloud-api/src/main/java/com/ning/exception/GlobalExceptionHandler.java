package com.ning.exception;

import com.ning.constant.BusinessCodeEnum;
import com.ning.constant.CommonConstants;
import com.ning.infrastructure.common.model.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
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
     * @param e ConstraintViolationException
     * @return 统一响应结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result<String> handleConstraintViolationException(ConstraintViolationException e) {
        // 获取异常中第一个错误信息
        String message = e.getConstraintViolations().iterator().next().getMessage();
        return Result.fail(null, BusinessCodeEnum.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * POST请求参数校验抛出的异常
     *
     * @param e MethodArgumentNotValidException
     * @return 统一响应结果
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        // 获取异常中随机一个异常信息
        String message = e.getBindingResult().getFieldError().getDefaultMessage();
        return Result.fail(null, BusinessCodeEnum.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 处理 InvalidGrantException
     *
     * @param e InvalidGrantException
     * @return 统一响应结果
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(value = InvalidGrantException.class)
    public Result<String> handleInvalidGrantException(InvalidGrantException e) {
        String message = e.getMessage();
        if (CommonConstants.BAD_CREDENTIALS.equals(message)) {
            message = CommonConstants.BAD_PASSWORD;
        }
        return Result.fail("", BusinessCodeEnum.VALIDATE_FAILED.getCode(), message);
    }

    /**
     * 处理基础异常
     *
     * @param e 基础异常
     * @return 统一响应结果
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = BaseException.class)
    public Result<String> handleBaseException(BaseException e) {
        log.error("error: {}.", e.getMessage(), e);
        return Result.fail(null, BusinessCodeEnum.FAILED.getCode(), BusinessCodeEnum.FAILED.getMessage());
    }

    /**
     * 处理通用异常
     *
     * @param e Exception
     * @return 统一响应结果
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result<String> handleException(Exception e) {
        log.error("error: {}.", e.getMessage(), e);
        return Result.fail(null, BusinessCodeEnum.FAILED.getCode(), BusinessCodeEnum.FAILED.getMessage());
    }

}

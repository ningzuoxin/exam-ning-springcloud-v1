package com.ning.infrastructure.common.exception;

import com.ning.infrastructure.common.enums.ErrorCodeEnum;
import com.ning.infrastructure.common.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(-1)
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException e) {
        ErrorResponse response = new ErrorResponse(e.getErrorCode());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("error: {}.", e.getMessage(), e);
        ErrorResponse response = new ErrorResponse(ErrorCodeEnum.FAILED);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("error: {}.", e.getMessage(), e);
        // 获取异常中随机一个异常信息
        // String message = e.getBindingResult().getFieldError().getDefaultMessage();
        ErrorResponse response = new ErrorResponse(ErrorCodeEnum.VALIDATE_FAILED);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

//    @ExceptionHandler(value = InvalidGrantException.class)
//    public ResponseEntity<ErrorResponse> handleInvalidGrantException(InvalidGrantException e) {
//        ErrorResponse response = new ErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
//        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e) {
        log.error("error: {}.", e.getMessage(), e);
        ErrorResponse response = new ErrorResponse(ErrorCodeEnum.FAILED);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}

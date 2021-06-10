package com.ning.handler;

import com.ning.exception.BaseException;
import com.ning.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class BaseExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = BaseException.class)
    public Result handle(BaseException e) {
        return Result.fail(e.getDefaultMessage());
    }

}

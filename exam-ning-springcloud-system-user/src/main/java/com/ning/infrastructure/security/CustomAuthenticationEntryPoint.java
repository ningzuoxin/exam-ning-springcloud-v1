package com.ning.infrastructure.security;

import com.alibaba.fastjson.JSON;
import com.ning.infrastructure.common.constant.ErrorCodeEnum;
import com.ning.infrastructure.common.model.ErrorResponse;
import com.ning.infrastructure.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * 自定义认证异常入口
 */
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
        ServletUtils.renderString(response, HttpStatus.UNAUTHORIZED, JSON.toJSONString(errorResponse));
    }

}

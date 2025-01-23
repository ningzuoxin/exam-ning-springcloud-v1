package com.ning.infrastructure.security;

import cn.hutool.json.JSONUtil;
import com.ning.infrastructure.common.enums.ErrorCodeEnum;
import com.ning.infrastructure.common.model.ErrorResponse;
import com.ning.infrastructure.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.UNAUTHORIZED);
        ServletUtils.write(response, HttpStatus.UNAUTHORIZED, JSONUtil.toJsonStr(errorResponse));
    }

}

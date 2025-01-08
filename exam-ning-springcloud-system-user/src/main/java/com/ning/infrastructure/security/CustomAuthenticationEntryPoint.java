package com.ning.infrastructure.security;

import com.alibaba.fastjson.JSON;
import com.ning.constant.CommonConstants;
import com.ning.infrastructure.common.model.Result;
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
        Result<String> result = Result.fail(HttpStatus.UNAUTHORIZED.value(), CommonConstants.INVALID_TOKEN);
        result.setData(e.getMessage());
        ServletUtils.renderString(response, JSON.toJSONString(result));
    }

}

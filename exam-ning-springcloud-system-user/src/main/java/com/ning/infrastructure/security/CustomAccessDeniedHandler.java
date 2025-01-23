package com.ning.infrastructure.security;

import cn.hutool.json.JSONUtil;
import com.ning.infrastructure.common.enums.ErrorCodeEnum;
import com.ning.infrastructure.common.model.ErrorResponse;
import com.ning.infrastructure.utils.ServletUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

/**
 * 自定义访问异常处理器
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.FORBIDDEN);
        ServletUtils.write(response, HttpStatus.FORBIDDEN, JSONUtil.toJsonStr(errorResponse));
    }

}

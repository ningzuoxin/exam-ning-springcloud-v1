package com.ning.infrastructure.common.security;

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
 * Custom AccessDeniedHandler
 *
 * @author zuoxin.ning
 * @since 2024-10-25 14:00
 */
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) {
        ErrorResponse errorResponse = new ErrorResponse(ErrorCodeEnum.FORBIDDEN);
        ServletUtils.write(response, HttpStatus.FORBIDDEN, JSONUtil.toJsonStr(errorResponse));
    }

}

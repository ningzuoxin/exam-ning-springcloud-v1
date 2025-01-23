package com.ning.infrastructure.utils;

import cn.hutool.core.exceptions.UtilException;
import cn.hutool.core.io.IoUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.io.Writer;

@Slf4j
public class ServletUtils {

    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String DEFAULT_CONTENT_TYPE = "application/json";

    public static void write(HttpServletResponse response, HttpStatus httpStatus, String text) {
        response.setCharacterEncoding(DEFAULT_CHARSET);
        response.setContentType(DEFAULT_CONTENT_TYPE);
        Writer writer = null;

        try {
            response.setStatus(httpStatus.value());
            writer = response.getWriter();
            writer.write(text);
            writer.flush();
        } catch (IOException e) {
            log.error("write response error, text: {}.", text, e);
            throw new UtilException(e);
        } finally {
            IoUtil.close(writer);
        }
    }

}

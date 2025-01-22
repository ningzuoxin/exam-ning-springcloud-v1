package com.ning.infrastructure.utils;

import cn.hutool.core.util.IdUtil;

public class IDUtils {
    public static Long getId() {
        return IdUtil.getSnowflakeNextId();
    }

}

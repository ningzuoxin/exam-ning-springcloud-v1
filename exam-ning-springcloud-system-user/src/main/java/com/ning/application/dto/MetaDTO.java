package com.ning.application.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 路由显示信息
 *
 * @author zuoxin.ning
 * @since 2024-11-15 14:30
 */
@Getter
@Setter
public class MetaDTO {

    /**
     * 设置该路由在侧边栏和面包屑中展示的名字
     */
    private String title;

    /**
     * 设置该路由的图标，对应路径src/icons/svg
     */
    private String icon;

    public MetaDTO(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

}

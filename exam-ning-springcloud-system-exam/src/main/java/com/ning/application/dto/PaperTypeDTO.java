package com.ning.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 试卷类型 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@Getter
@Setter
@Builder
public class PaperTypeDTO {

    // 类型
    private Integer type;
    // 标题
    private String title;

}

package com.ning.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 试题类型 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@Getter
@Setter
@Builder
public class QuestionTypeDTO {

    // 类型
    private String type;
    // 标题
    private String title;

}

package com.ning.domain.types;

import lombok.Getter;
import lombok.Setter;

/**
 * 试题选项
 *
 * @author zuoxin.ning
 * @since 2024-11-21 15:00
 */
@Getter
@Setter
public class QuestionOption {

    // 选项 ID
    private String id;
    // 选项内容
    private String text;

}

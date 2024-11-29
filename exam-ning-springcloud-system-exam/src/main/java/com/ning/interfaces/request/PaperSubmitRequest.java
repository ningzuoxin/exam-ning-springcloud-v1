package com.ning.interfaces.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 试卷提交请求
 *
 * @author zuoxin.ning
 * @since 2024-11-29 14:00
 */
@Getter
@Setter
@ApiModel(value = "PaperSubmitRequest", description = "试卷提交请求")
public class PaperSubmitRequest {

    // 答题耗时，单位：秒
    private Integer timeUsed;
    // 试卷试题提交请求列表
    public List<PaperQuestionSubmitRequest> questionSubmitList;

    /**
     * 试卷试题提交请求
     */
    @Getter
    @Setter
    public static class PaperQuestionSubmitRequest {

        // 试卷试题 ID
        private Long paperQuestionId;
        // 答题结果
        private String answer;

    }

}

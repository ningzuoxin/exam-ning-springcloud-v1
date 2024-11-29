package com.ning.interfaces.request;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 试卷结果评分请求
 *
 * @author zuoxin.ning
 * @since 2024-11-28 14:00
 */
@Getter
@Setter
@ApiModel(value = "PaperResultGradingRequest", description = "试卷结果评分请求")
public class PaperResultGradingRequest {

    // 试卷结果 ID
    private Long paperResultId;
    // 试卷试题评分列表
    private List<PaperQuestionResultGradingRequest> questionGradingList;

    /**
     * 试卷试题结果评分请求
     */
    @Getter
    @Setter
    public static class PaperQuestionResultGradingRequest {

        // 试卷试题结果 ID
        private Long paperQuestionResultId;
        // 分数
        private Float score;

    }

}

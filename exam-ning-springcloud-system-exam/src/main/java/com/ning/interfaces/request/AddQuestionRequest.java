package com.ning.interfaces.request;

import com.ning.interfaces.validation.constraints.QuestionTypeConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 添加试题请求
 *
 * @author zuoxin.ning
 * @since 2024-10-25 08:30
 */
@Getter
@Setter
@ApiModel(value = "AddQuestionRequest", description = "添加试题请求")
public class AddQuestionRequest {

    @NotEmpty(message = "题型不能为空")
    @QuestionTypeConstraint
    @ApiModelProperty(value = "题型")
    private String type;

    @NotEmpty(message = "题目内容不能为空")
    @ApiModelProperty(value = "题目内容")
    private String content;

    @NotEmpty(message = "正确答案不能为空")
    @ApiModelProperty(value = "正确答案")
    private String correctAnswer;

    @ApiModelProperty(value = "解析")
    private String explanation;

    @NotEmpty(message = "选项不能为空")
    @ApiModelProperty(value = "选项")
    private String options;

}

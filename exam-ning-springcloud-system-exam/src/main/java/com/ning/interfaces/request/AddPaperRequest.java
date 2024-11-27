package com.ning.interfaces.request;

import com.ning.interfaces.validation.constraints.PaperTypeConstraint;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 添加试卷请求
 *
 * @author zuoxin.ning
 * @since 2024-11-27 11:30
 */
@Getter
@Setter
@ApiModel(value = "AddPaperRequest", description = "添加试卷请求")
public class AddPaperRequest {

    @NotEmpty(message = "试卷类型不能为空")
    @PaperTypeConstraint
    @ApiModelProperty(value = "试卷类型")
    private Integer type;

    @NotEmpty(message = "试卷标题不能为空")
    @ApiModelProperty(value = "试卷标题")
    private String title;

    @NotEmpty(message = "考试时间限制不能为空")
    @ApiModelProperty(value = "考试时间限制")
    private Integer timeLimit;

    @NotEmpty(message = "考试次数限制不能为空")
    @ApiModelProperty(value = "考试次数限制")
    private Integer frequencyLimit;

    @NotEmpty(message = "总分不能为空")
    @ApiModelProperty(value = "总分")
    private Integer totalScore;

    @NotEmpty(message = "及格分不能为空")
    @ApiModelProperty(value = "及格分")
    private Integer passedScore;

    @NotEmpty(message = "试题数量不能为空")
    @ApiModelProperty(value = "试题数量")
    private Integer questionCount;

    @ApiModelProperty(value = "复制试卷ID")
    private Integer copyPaperId;

}

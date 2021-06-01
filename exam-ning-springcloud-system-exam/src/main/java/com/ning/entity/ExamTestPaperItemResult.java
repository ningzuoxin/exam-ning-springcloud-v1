package com.ning.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 试卷项目结果表
 * </p>
 *
 * @author ningning
 * @since 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_test_paper_item_result")
@ApiModel(value = "ExamTestPaperItemResult对象", description = "试卷项目结果表")
public class ExamTestPaperItemResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "试卷结果ID")
    @TableField("testpaper_result_id")
    private Integer testpaperResultId;

    @ApiModelProperty(value = "试卷ID")
    @TableField("testpaper_id")
    private Integer testpaperId;

    @ApiModelProperty(value = "试卷项目ID")
    @TableField("testpaper_item_id")
    private Integer testpaperItemId;

    @ApiModelProperty(value = "题目ID")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty(value = "答题人ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "答题结果")
    @TableField("answer")
    private String answer;

    @ApiModelProperty(value = "结果状态（none、right、partRight、wrong、noAnswer）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "得分")
    @TableField("score")
    private Float score;

    @ApiModelProperty(value = "老师评价")
    @TableField("teacher_say")
    private String teacherSay;

}

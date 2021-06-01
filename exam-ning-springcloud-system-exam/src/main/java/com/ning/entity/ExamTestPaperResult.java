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
 * 试卷结果表
 * </p>
 *
 * @author ningning
 * @since 2021-06-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_test_paper_result")
@ApiModel(value = "ExamTestPaperResult对象", description = "试卷结果表")
public class ExamTestPaperResult implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "试卷ID")
    @TableField("testpaper_id")
    private Integer testpaperId;

    @ApiModelProperty(value = "试卷名")
    @TableField("testpaper_name")
    private String testpaperName;

    @ApiModelProperty(value = "答卷人ID")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty(value = "总分")
    @TableField("score")
    private Float score;

    @ApiModelProperty(value = "主观题得分")
    @TableField("objective_score")
    private Float objectiveScore;

    @ApiModelProperty(value = "客观题得分")
    @TableField("subjective_score")
    private Float subjectiveScore;

    @ApiModelProperty(value = "老师评价")
    @TableField("teacher_say")
    private String teacherSay;

    @ApiModelProperty(value = "正确题目数")
    @TableField("right_item_count")
    private Integer rightItemCount;

    @ApiModelProperty(value = "答卷耗时（秒）")
    @TableField("used_time")
    private Integer usedTime;

    @ApiModelProperty(value = "试卷批阅状态（doing、paused、reviewing、finished）")
    @TableField("status")
    private String status;

    @ApiModelProperty(value = "批卷老师ID")
    @TableField("check_user_id")
    private Integer checkUserId;

    @ApiModelProperty(value = "批卷时间")
    @TableField("checked_time")
    private Integer checkedTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Integer updateTime;

}

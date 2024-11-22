package com.ning.infrastructure.persistence.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.Instant;

/**
 * <p>
 * 试卷表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-11-21 14:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_paper")
@ApiModel(value = "PaperDO对象", description = "试卷表")
public class PaperDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "试卷类型，1：正式考试；2：模拟考试；3：日常练习；")
    @TableField("type")
    private Integer type;

    @ApiModelProperty(value = "试卷标题")
    @TableField("title")
    private String title;

    @ApiModelProperty(value = "考试时间限制，单位：秒")
    @TableField("time_limit")
    private Integer timeLimit;

    @ApiModelProperty(value = "考试次数限制，0：不限制；")
    @TableField("frequency_limit")
    private Integer frequencyLimit;

    @ApiModelProperty(value = "总分")
    @TableField("total_score")
    private Integer totalScore;

    @ApiModelProperty(value = "及格分")
    @TableField("passed_score")
    private Integer passedScore;

    @ApiModelProperty(value = "试题数量")
    @TableField("question_count")
    private Integer questionCount;

    @ApiModelProperty(value = "复制试卷ID")
    @TableField("copy_paper_uid")
    private Long copyPaperUid;

    @ApiModelProperty(value = "试卷状态，0：正常；1：停用；")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "是否删除，0：未删除；1：已删除；")
    @TableField("is_deleted")
    private Integer isDeleted;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Instant createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Instant updateTime;

}

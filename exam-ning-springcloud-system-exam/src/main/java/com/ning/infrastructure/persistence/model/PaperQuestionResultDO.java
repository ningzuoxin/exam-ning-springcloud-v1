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
 * 试卷试题结果表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-11-27 14:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_paper_question_result")
@ApiModel(value = "PaperQuestionResultDO对象", description = "试卷试题结果表")
public class PaperQuestionResultDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "试卷结果ID")
    @TableField("paper_result_uid")
    private Long paperResultUid;

    @ApiModelProperty(value = "试卷ID")
    @TableField("paper_uid")
    private Long paperUid;

    @ApiModelProperty(value = "试卷试题ID")
    @TableField("paper_question_uid")
    private Long paperQuestionUid;

    @ApiModelProperty(value = "试题ID")
    @TableField("question_uid")
    private Long questionUid;

    @ApiModelProperty(value = "答题者用户ID")
    @TableField("user_uid")
    private Long userUid;

    @ApiModelProperty(value = "答题结果")
    @TableField("answer")
    private String answer;

    @ApiModelProperty(value = "结果状态，0：未答；1：正确；2：部分正确；3：错误；")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "得分")
    @TableField("score")
    private Float score;

    @ApiModelProperty(value = "教师点评")
    @TableField("comments")
    private String comments;

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

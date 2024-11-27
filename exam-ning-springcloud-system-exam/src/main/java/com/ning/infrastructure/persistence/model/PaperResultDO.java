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
 * 试卷结果表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-11-27 14:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_paper_result")
@ApiModel(value = "PaperResultDO对象", description = "试卷结果表")
public class PaperResultDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "试卷ID")
    @TableField("paper_uid")
    private Long paperUid;

    @ApiModelProperty(value = "试卷标题")
    @TableField("paper_title")
    private String paperTitle;

    @ApiModelProperty(value = "答题者用户ID")
    @TableField("user_uid")
    private Long userUid;

    @ApiModelProperty(value = "最终得分")
    @TableField("score")
    private Float score;

    @ApiModelProperty(value = "客观题得分")
    @TableField("objective_score")
    private Float objectiveScore;

    @ApiModelProperty(value = "主观题得分")
    @TableField("subjective_score")
    private Float subjectiveScore;

    @ApiModelProperty(value = "教师点评")
    @TableField("comments")
    private String comments;

    @ApiModelProperty(value = "答题正确数")
    @TableField("right_count")
    private Integer rightCount;

    @ApiModelProperty(value = "答题耗时，单位：秒")
    @TableField("time_used")
    private Integer timeUsed;

    @ApiModelProperty(value = "阅卷状态，0：待阅卷；1：阅卷中；2：暂停；3：阅卷结束；")
    @TableField("status")
    private Integer status;

    @ApiModelProperty(value = "阅卷老师用户ID")
    @TableField("check_user_uid")
    private Long checkUserUid;

    @ApiModelProperty(value = "批卷时间")
    @TableField("check_time")
    private Instant checkTime;

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

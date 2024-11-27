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
 * 试卷试题表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-11-27 14:30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_paper_question")
@ApiModel(value = "PaperQuestionDO对象", description = "试卷试题表")
public class PaperQuestionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "试卷ID")
    @TableField("paper_uid")
    private Long paperUid;

    @ApiModelProperty(value = "试题ID")
    @TableField("question_uid")
    private Long questionUid;

    @ApiModelProperty(value = "答对得分")
    @TableField("right_score")
    private Float rightScore;

    @ApiModelProperty(value = "漏选得分")
    @TableField("miss_score")
    private Float missScore;

    @ApiModelProperty(value = "顺序")
    @TableField("sort_num")
    private Integer sortNum;

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

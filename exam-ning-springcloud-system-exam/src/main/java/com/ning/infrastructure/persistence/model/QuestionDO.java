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
 * 试题表
 * </p>
 *
 * @author zuoxin.ning
 * @since 2024-11-21 14:00
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_question")
@ApiModel(value = "QuestionDO对象", description = "试题表")
public class QuestionDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "业务ID")
    @TableField("uid")
    private Long uid;

    @ApiModelProperty(value = "题型")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "内容")
    @TableField("content")
    private String content;

    @ApiModelProperty(value = "正确答案")
    @TableField("correct_answer")
    private String correctAnswer;

    @ApiModelProperty(value = "解析")
    @TableField("explanation")
    private String explanation;

    @ApiModelProperty(value = "选项")
    @TableField("options")
    private String options;

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

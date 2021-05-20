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
 * 试卷项目表
 * </p>
 *
 * @author ningning
 * @since 2021-05-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_test_paper_item")
@ApiModel(value = "ExamTestPaperItem对象", description = "试卷项目表")
public class ExamTestPaperItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "试卷ID")
    @TableField("test_paper_id")
    private Integer testPaperId;

    @ApiModelProperty(value = "题目顺序")
    @TableField("seq")
    private Integer seq;

    @ApiModelProperty(value = "题目ID")
    @TableField("question_id")
    private Integer questionId;

    @ApiModelProperty(value = "题目类别")
    @TableField("question_type")
    private String questionType;

    @ApiModelProperty(value = "得分")
    @TableField("score")
    private Float score;

    @ApiModelProperty(value = "漏选得分")
    @TableField("miss_score")
    private Float missScore;


}

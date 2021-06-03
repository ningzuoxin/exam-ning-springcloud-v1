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
 * 试卷表
 * </p>
 *
 * @author ningning
 * @since 2021-06-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_test_paper")
@ApiModel(value = "ExamTestPaper对象", description = "试卷表")
public class ExamTestPaper implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "试卷类型（training、mock）")
    @TableField("type")
    private String type;

    @ApiModelProperty(value = "试卷名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "限时（秒）")
    @TableField("limited_time")
    private Integer limitedTime;

    @ApiModelProperty(value = "允许考试次数 0 不限制次数")
    @TableField("times")
    private Integer times;

    @ApiModelProperty(value = "总分")
    @TableField("total_score")
    private Float totalScore;

    @ApiModelProperty(value = "及格分")
    @TableField("passed_score")
    private Float passedScore;

    @ApiModelProperty(value = "题目数量")
    @TableField("item_count")
    private Integer itemCount;

    @ApiModelProperty(value = "复制试卷ID")
    @TableField("copy_id")
    private Integer copyId;

    @ApiModelProperty(value = "是否使用 0 未使用 1 使用")
    @TableField("is_used")
    private Integer isUsed;

    @ApiModelProperty(value = "是否删除 0 未删除 1 已删除")
    @TableField("is_delete")
    private Integer isDelete;

    @ApiModelProperty(value = "创建者ID")
    @TableField("create_user_id")
    private Integer createUserId;

    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Integer createTime;

    @ApiModelProperty(value = "更新者ID")
    @TableField("update_user_id")
    private Integer updateUserId;

    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Integer updateTime;

}

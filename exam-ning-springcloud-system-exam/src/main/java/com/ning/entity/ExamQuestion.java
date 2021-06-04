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

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 考题表
 * </p>
 *
 * @author ningning
 * @since 2021-06-04
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("exam_question")
@ApiModel(value = "ExamQuestion对象", description = "考题表")
public class ExamQuestion implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @NotEmpty(message = "题目类型不能为空")
    @ApiModelProperty(value = "题目类型")
    @TableField("type")
    private String type;

    @NotEmpty(message = "题干不能为空")
    @ApiModelProperty(value = "题干")
    @TableField("stem")
    private String stem;

    @ApiModelProperty(value = "分数")
    @TableField("score")
    private Float score;

    @NotEmpty(message = "参考答案不能为空")
    @ApiModelProperty(value = "参考答案")
    @TableField("answer")
    private String answer;

    @ApiModelProperty(value = "解析")
    @TableField("analysis")
    private String analysis;

    @ApiModelProperty(value = "题目元信息")
    @TableField("metas")
    private String metas;

    @ApiModelProperty(value = "分类ID")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty(value = "被试卷使用数")
    @TableField("used_num")
    private Integer usedNum;

    @ApiModelProperty(value = "是否删除 0 未删除 1 已删除")
    @TableField("is_delete")
    private Integer isDelete;

    @ApiModelProperty(value = "是否显示 0 显示 1 不显示")
    @TableField("is_show")
    private Integer isShow;

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

    @ApiModelProperty(value = "复制题目ID")
    @TableField("copy_id")
    private Integer copyId;

}

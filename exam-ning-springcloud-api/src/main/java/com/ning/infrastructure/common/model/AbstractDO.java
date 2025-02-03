package com.ning.infrastructure.common.model;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldNameConstants;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@FieldNameConstants
public abstract class AbstractDO implements Serializable {

    @Serial
    private static final long serialVersionUID = 6307002935412526717L;

    // primary key
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // business id
    @TableField(value = "uid", fill = FieldFill.INSERT)
    private Long uid;

    // 0: not delete, 1: deleted
    @TableField(value = "is_deleted", fill = FieldFill.INSERT)
    private Byte isDeleted;

    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

}

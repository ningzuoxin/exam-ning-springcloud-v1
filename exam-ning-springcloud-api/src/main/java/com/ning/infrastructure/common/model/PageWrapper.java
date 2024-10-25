package com.ning.infrastructure.common.model;

import lombok.*;

import java.util.List;

/**
 * 分页包装器
 *
 * @param <T> 泛型对象
 * @author zuoxin.ning
 * @since 2024-10-15 18:00
 */
@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class PageWrapper<T> {

    // 记录总数
    private Long total;
    // 当前页码
    private Integer pageNum;
    // 当前页条数
    private Integer pageSize;
    // 列表数据
    private List<T> data;

}

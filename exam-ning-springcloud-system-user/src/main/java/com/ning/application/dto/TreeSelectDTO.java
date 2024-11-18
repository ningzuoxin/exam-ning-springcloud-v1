package com.ning.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ning.domain.entity.Menu;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 树形菜单 DTO
 *
 * @author zuoxin.ning
 * @since 2024-11-15 08:30
 */
@Getter
@Setter
public class TreeSelectDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 节点ID
     */
    private Long id;

    /**
     * 节点名称
     */
    private String label;

    /**
     * 子节点
     */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<TreeSelectDTO> children;

    public TreeSelectDTO(Menu menu) {
        this.id = menu.getId().getValue();
        this.label = menu.getMenuName();
        if (Objects.nonNull(menu.getChildren())) {
            this.children = menu.getChildren().stream().map(TreeSelectDTO::new).collect(Collectors.toList());
        }
    }

}

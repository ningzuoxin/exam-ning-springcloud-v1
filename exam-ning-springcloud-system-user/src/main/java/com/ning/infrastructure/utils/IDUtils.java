package com.ning.infrastructure.utils;

import com.ning.infrastructure.common.leaf.IDGen;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@ConditionalOnBean(value = IDGen.class)
public class IDUtils {

    private final static String ID = "ID";

    private final IDGen idGen;

    public Long getId() {
        return idGen.get(ID).getId();
    }

}

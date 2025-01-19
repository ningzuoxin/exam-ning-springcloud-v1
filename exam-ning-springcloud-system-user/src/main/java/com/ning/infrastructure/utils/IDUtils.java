package com.ning.infrastructure.utils;

import com.ning.infrastructure.common.leaf.IDGen;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IDUtils {

    private final IDGen idGen;

    public Long getId(String type) {
        return idGen.get(type).getId();
    }

}

package com.ning.infrastructure.common.leaf;

import com.ning.infrastructure.common.leaf.common.Result;

public interface IDGen {

    Result get(String key);

    boolean init();

}

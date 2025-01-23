package com.ning.interfaces.controller;

import com.ning.infrastructure.common.leaf.IDGen;
import com.ning.infrastructure.common.leaf.common.Result;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/ids")
@CrossOrigin(origins = "*", maxAge = 3600)
public class IDController {

    private final IDGen iDGen;
    private static final String DEFAULT_BIZ_TAG = "ID";

    @Operation(summary = "Generate unique id")
    @PostMapping
    public Result id() {
        return iDGen.get(DEFAULT_BIZ_TAG);
    }

}

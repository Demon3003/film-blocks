package com.zhurawell.blocks.common.utils.exception;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BuilderRuntimeException extends RuntimeException {

    private String code;

    private String message;

    public BuilderRuntimeException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public BuilderRuntimeException(String code, String message, Throwable ex) {
        super(ex);
        this.code = code;
        this.message = message;
    }

}
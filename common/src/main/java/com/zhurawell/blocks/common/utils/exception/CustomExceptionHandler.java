package com.zhurawell.blocks.common.utils.exception;


import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CustomExceptionHandler {

    private ErrorCodes errorCode;

    private Throwable cause;

    private String[] params = {};

    public static CustomExceptionHandler getInstance() {
        return new CustomExceptionHandler();
    }

    public CustomExceptionHandler withErrorCode(ErrorCodes code) {
        this.errorCode = code;
        return this;
    }

    public CustomExceptionHandler withParams(String ...params) {
        this.params = params;
        return this;
    }

    public CustomExceptionHandler withCause(Throwable cause) {
        this.cause = cause;
        return this;
    }

    public void buildAndThrow() {
        if (cause == null) {
            throw new BuilderRuntimeException(errorCode.getCode(), buildMessage());
        } else  {
            throw new BuilderRuntimeException(errorCode.getCode(), buildMessage(), cause);
        }
    }

    public BuilderRuntimeException build() {
        if (cause == null) {
            return new BuilderRuntimeException(errorCode.getCode(), buildMessage());
        } else  {
            return new BuilderRuntimeException(errorCode.getCode(), buildMessage(), cause);
        }

    }

    public static BuilderRuntimeException getBuilderRuntimeException(Exception ex) {
        Throwable exception = ex.getCause();
        while (exception != null && ex != exception) {
            if (exception instanceof BuilderRuntimeException) {
                return (BuilderRuntimeException) exception;
            }
            exception = exception.getCause();
        }
        return null;
    }

    private String buildMessage() {
        return this.errorCode.getMessageWithParams(this.params);
    }

}
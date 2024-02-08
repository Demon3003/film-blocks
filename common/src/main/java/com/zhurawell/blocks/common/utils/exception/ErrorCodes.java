package com.zhurawell.blocks.common.utils.exception;


public enum ErrorCodes {

    DEFAULT("100", "Default error. %s"),
    C_101("101", "User with login/email: %s doesn't exist."),
    C_102("102", "Wrong password."),
    C_300("300", "Token in blacklist."),
    C_301("301", "JWT token is expired"),
    C_302("302", "JWT token is invalid");

    private String REPLACE_SIGN = "%s";

    private String code;

    private String message;

    ErrorCodes(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getMessageWithParams(String ...params) {
        String msg = this.message;
        for (String param : params) {
            msg = msg.replaceFirst(REPLACE_SIGN, param);
        }
        return msg;
    }

}

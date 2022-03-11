package com.javaclass.mobilewalletmanagementapis.mobilewallet.data.responses;

public enum ResponseEnum {
    SUCCESS("000", "Success", "Account opened successfully"),
    FAILURE("900", "Failed", "Didn't work");

    private String code;
    private String message;
    private String description;

    ResponseEnum(
            String code,
            String message,
            String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

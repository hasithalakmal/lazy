package com.smile.lazy.beans.enums;

public enum DataSourceEnum {

    BODY("BODY"),
    RESPONSE_CODE("RESPONSE_CODE"),
    RESPONSE_CODE_NAME("RESPONSE_CODE_NAME"),
    RESPONSE_TIME("RESPONSE_TIME"),
    RESPONSE_HEADER("RESPONSE_HEADER"),
    PROVIDED("PROVIDED");

    private String value;

    DataSourceEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

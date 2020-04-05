package com.smile24es.lazy.beans.enums;

public enum DataTypeEnum {

    STRING("STRING"),
    INTEGER("INTEGER"),
    DOUBLE("DOUBLE"),
    BOOLEAN("BOOLEAN"),
    OBJECT("OBJECT");

    private String value;

    DataTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

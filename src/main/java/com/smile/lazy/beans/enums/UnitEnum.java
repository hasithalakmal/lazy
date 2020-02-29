package com.smile.lazy.beans.enums;

public enum UnitEnum {

    MILLI_SECONDS("STRING"),
    SECONDS("INTEGER");

    private String value;

    UnitEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

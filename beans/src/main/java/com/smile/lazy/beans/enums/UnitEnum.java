package com.smile.lazy.beans.enums;

public enum UnitEnum {

    MILLI_SECONDS("MILLI_SECONDS"),
    SECONDS("SECONDS");

    private String value;

    UnitEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

package com.smile.lazy.beans.enums;

public enum ActionTypeEnum {

    SET_GLOBAL_VARIABLE("SET_GLOBAL_VARIABLE"),
    SET_ENVIRONMENT_VARIABLE("SET_ENVIRONMENT_VARIABLE"),
    CLEAR_GLOBAL_VARIABLE("CLEAR_GLOBAL_VARIABLE"),
    CLEAR_ENVIRONMENT_VARIABLE("CLEAR_ENVIRONMENT_VARIABLE"),
    EXECUTE("EXECUTE");

    private String value;

    ActionTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

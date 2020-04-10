package com.smile24es.lazy.beans.enums;

public enum AssertionResultStatus {

    EXECUTED("EXECUTED"),
    FAILED("FAILED"),
    SKIPPED("SKIPPED"),
    INVALID_RULE("INVALID_RULE"),
    NOT_IMPLEMENTED("NOT_IMPLEMENTED");

    private String value;

    AssertionResultStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

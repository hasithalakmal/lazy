package com.smile24es.lazy.beans.enums;

public enum AssertionOperationEnum {

    NULL("NULL"), //only key
    NOT_NULL("NOT_NULL"), //only key
    EQUAL("EQUAL"), //key and value
    NOT_EQUAL("NOT_EQUAL"), //key and value
    GREATER_THAN("GREATER_THAN"), //key and value
    GREATER_THAN_OR_EQUAL("GREATER_THAN_OR_EQUAL"), //key and value
    LESS_THAN("LESS_THAN"), //key and value
    LESS_THAN_OR_EQUAL("LESS_THAN_OR_EQUAL"), //key and value
    CONTAINS("CONTAINS"), //key and value
    NOT_CONTAINS("NOT_CONTAINS"), //key and value
    BETWEEN("BETWEEN"), //key and value
    CONTAINS_EXACTLY("CONTAINS_EXACTLY"), //For List
    CONTAINS_ALL("CONTAINS_ALL"), //For List
    CONTAINS_ANY("CONTAINS_ANY"), //For List
    IS_KEY_AVAILABLE("IS_KEY_AVAILABLE"),
    IS_KEY_NOT_AVAILABLE("IS_KEY_NOT_AVAILABLE"),
    TRUE("TRUE"); //condition

    private String value;

    AssertionOperationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

package com.smile.lazy.beans.enums;

public enum OperationEnum {

    NULL("NULL"), //only key
    NOT_NULL("NOT_NULL"), //only key
    EQUAL("EQUAL"), //key and value
    NOT_EQUAL("NOT_EQUAL"), //key and value
    GREATER_THAN("GREATER_THAN"), //key and value
    GREATER_THAN_OR_EQUAL("GREATER_THAN_OR_EQUAL"), //key and value
    LESS_THAN("LESS_THAN"), //key and value
    LESS_THAN_OR_EQUAL("LESS_THAN_OR_EQUAL"), //key and value
    CONTAINS("CONTAINS"), //key and value
    NOT_CONTAINS("NOT_CONTAINS"); //condition

    private String value;

    OperationEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}

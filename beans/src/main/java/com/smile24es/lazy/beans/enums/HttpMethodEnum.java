package com.smile24es.lazy.beans.enums;

public enum HttpMethodEnum {

    POST("POST"),
    GET("GET"),
    PUT("PUT"),
    PATCH("PATCH"),
    DELETE("DELETE");

    private String value;

    HttpMethodEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

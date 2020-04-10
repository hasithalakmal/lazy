package com.smile24es.lazy.suite.sample0.dto;

public class ErrorTo {

    private String code;
    private String description;
    private String additionalInfo;

    public ErrorTo(String code, String description, String additionalInfo) {
        this.code = code;
        this.description = description;
        this.additionalInfo = additionalInfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    @Override
    public String toString() {
        return "ErrorTo{" +
              "code='" + code + '\'' +
              ", description='" + description + '\'' +
              ", additionalInfo='" + additionalInfo + '\'' +
              '}';
    }
}

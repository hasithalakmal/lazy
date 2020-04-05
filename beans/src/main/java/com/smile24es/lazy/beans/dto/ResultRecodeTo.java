package com.smile24es.lazy.beans.dto;

public class ResultRecodeTo {
    private String resultId;
    private String apiCallName;
    private String assertionName;
    private String expectedResult;
    private String actualResult;
    private String operation;
    private String status;
    private String isPass;

    public String getResultId() {
        return resultId;
    }

    public void setResultId(String resultId) {
        this.resultId = resultId;
    }

    public String getApiCallName() {
        return apiCallName;
    }

    public void setApiCallName(String apiCallName) {
        this.apiCallName = apiCallName;
    }

    public String getAssertionName() {
        return assertionName;
    }

    public void setAssertionName(String assertionName) {
        this.assertionName = assertionName;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }

    public String getActualResult() {
        return actualResult;
    }

    public void setActualResult(String actualResult) {
        this.actualResult = actualResult;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getIsPass() {
        return isPass;
    }

    public void setIsPass(String isPass) {
        this.isPass = isPass;
    }

    @Override
    public String toString() {
        return "ResultRecodeTo{" +
              "resultId='" + resultId + '\'' +
              ", apiCallName='" + apiCallName + '\'' +
              ", assertionName='" + assertionName + '\'' +
              ", expectedResult='" + expectedResult + '\'' +
              ", actualResult='" + actualResult + '\'' +
              ", operation='" + operation + '\'' +
              ", status='" + status + '\'' +
              ", isPass='" + isPass + '\'' +
              '}';
    }
}

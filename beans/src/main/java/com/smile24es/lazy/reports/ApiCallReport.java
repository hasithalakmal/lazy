package com.smile24es.lazy.reports;

import java.util.ArrayList;
import java.util.List;

public class ApiCallReport {

    private int id;
    private String name;
    private int totalAssertionsCount;
    private int passAssertionsCount;
    private int notPassAssertionsCount;
    private double passAssertionsPercentage;
    private double notPassAssertionsPercentage;

    private int totalExecutedAssertionsCount;
    private int totalSkippedAssertionsCount;
    private int totalFailedAssertionsCount;
    private int totalInvalidAssertionsCount;
    private double executedAssertionsPercentage;
    private double skippedAssertionsPercentage;
    private double failedAssertionsPercentage;
    private double invalidAssertionsPercentage;

    private String httpMethod;
    private String url;
    private String requestBody;
    private String headers;
    private String executionTime;
    private Integer httpStatusCode;
    private String response;
    private List<AssertionResultObject> assertions;

    public ApiCallReport(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalAssertionsCount() {
        return totalAssertionsCount;
    }

    public void setTotalAssertionsCount(int totalAssertionsCount) {
        this.totalAssertionsCount = totalAssertionsCount;
    }

    public int getPassAssertionsCount() {
        return passAssertionsCount;
    }

    public void setPassAssertionsCount(int passAssertionsCount) {
        this.passAssertionsCount = passAssertionsCount;
    }

    public int getNotPassAssertionsCount() {
        return notPassAssertionsCount;
    }

    public void setNotPassAssertionsCount(int notPassAssertionsCount) {
        this.notPassAssertionsCount = notPassAssertionsCount;
    }

    public double getPassAssertionsPercentage() {
        return passAssertionsPercentage;
    }

    public void setPassAssertionsPercentage(double passAssertionsPercentage) {
        this.passAssertionsPercentage = passAssertionsPercentage;
    }

    public double getNotPassAssertionsPercentage() {
        return notPassAssertionsPercentage;
    }

    public void setNotPassAssertionsPercentage(double notPassAssertionsPercentage) {
        this.notPassAssertionsPercentage = notPassAssertionsPercentage;
    }

    public int getTotalExecutedAssertionsCount() {
        return totalExecutedAssertionsCount;
    }

    public void setTotalExecutedAssertionsCount(int totalExecutedAssertionsCount) {
        this.totalExecutedAssertionsCount = totalExecutedAssertionsCount;
    }

    public int getTotalSkippedAssertionsCount() {
        return totalSkippedAssertionsCount;
    }

    public void setTotalSkippedAssertionsCount(int totalSkippedAssertionsCount) {
        this.totalSkippedAssertionsCount = totalSkippedAssertionsCount;
    }

    public int getTotalFailedAssertionsCount() {
        return totalFailedAssertionsCount;
    }

    public void setTotalFailedAssertionsCount(int totalFailedAssertionsCount) {
        this.totalFailedAssertionsCount = totalFailedAssertionsCount;
    }

    public int getTotalInvalidAssertionsCount() {
        return totalInvalidAssertionsCount;
    }

    public void setTotalInvalidAssertionsCount(int totalInvalidAssertionsCount) {
        this.totalInvalidAssertionsCount = totalInvalidAssertionsCount;
    }

    public double getExecutedAssertionsPercentage() {
        return executedAssertionsPercentage;
    }

    public void setExecutedAssertionsPercentage(double executedAssertionsPercentage) {
        this.executedAssertionsPercentage = executedAssertionsPercentage;
    }

    public double getSkippedAssertionsPercentage() {
        return skippedAssertionsPercentage;
    }

    public void setSkippedAssertionsPercentage(double skippedAssertionsPercentage) {
        this.skippedAssertionsPercentage = skippedAssertionsPercentage;
    }

    public double getFailedAssertionsPercentage() {
        return failedAssertionsPercentage;
    }

    public void setFailedAssertionsPercentage(double failedAssertionsPercentage) {
        this.failedAssertionsPercentage = failedAssertionsPercentage;
    }

    public double getInvalidAssertionsPercentage() {
        return invalidAssertionsPercentage;
    }

    public void setInvalidAssertionsPercentage(double invalidAssertionsPercentage) {
        this.invalidAssertionsPercentage = invalidAssertionsPercentage;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(String executionTime) {
        this.executionTime = executionTime;
    }

    public Integer getHttpStatusCode() {
        return httpStatusCode;
    }

    public void setHttpStatusCode(Integer httpStatusCode) {
        this.httpStatusCode = httpStatusCode;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public List<AssertionResultObject> getAssertions() {
        if (assertions == null) {
            assertions = new ArrayList<>();
        }
        return assertions;
    }

    public void setAssertions(List<AssertionResultObject> assertions) {
        this.assertions = assertions;
    }
}

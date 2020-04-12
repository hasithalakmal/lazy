package com.smile24es.lazy.reports;

import java.util.ArrayList;
import java.util.List;

public class TestCaseReport {

    private int id;
    private String testCaseName;
    //API Call
    private int totalApiCallCount;
    private int passApiCallCount;
    private int notPassApiCallCount;
    private double passApiCallPercentage;
    private double notPassApiCallPercentage;

    private int totalExecutedApiCallCount;
    private int totalSkippedApiCallCount;
    private int totalFailedApiCallCount;
    private int totalInvalidApiCallCount;
    private double executedApiCallPercentage;
    private double skippedApiCallPercentage;
    private double failedApiCallPercentage;
    private double invalidApiCallPercentage;

    private List<ApiCallReport> apiCallReports;

    public TestCaseReport(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public int getTotalApiCallCount() {
        return totalApiCallCount;
    }

    public void setTotalApiCallCount(int totalApiCallCount) {
        this.totalApiCallCount = totalApiCallCount;
    }

    public int getPassApiCallCount() {
        return passApiCallCount;
    }

    public void setPassApiCallCount(int passApiCallCount) {
        this.passApiCallCount = passApiCallCount;
    }

    public int getNotPassApiCallCount() {
        return notPassApiCallCount;
    }

    public void setNotPassApiCallCount(int notPassApiCallCount) {
        this.notPassApiCallCount = notPassApiCallCount;
    }

    public double getPassApiCallPercentage() {
        return passApiCallPercentage;
    }

    public void setPassApiCallPercentage(double passApiCallPercentage) {
        this.passApiCallPercentage = passApiCallPercentage;
    }

    public double getNotPassApiCallPercentage() {
        return notPassApiCallPercentage;
    }

    public void setNotPassApiCallPercentage(double notPassApiCallPercentage) {
        this.notPassApiCallPercentage = notPassApiCallPercentage;
    }

    public int getTotalExecutedApiCallCount() {
        return totalExecutedApiCallCount;
    }

    public void setTotalExecutedApiCallCount(int totalExecutedApiCallCount) {
        this.totalExecutedApiCallCount = totalExecutedApiCallCount;
    }

    public int getTotalSkippedApiCallCount() {
        return totalSkippedApiCallCount;
    }

    public void setTotalSkippedApiCallCount(int totalSkippedApiCallCount) {
        this.totalSkippedApiCallCount = totalSkippedApiCallCount;
    }

    public int getTotalFailedApiCallCount() {
        return totalFailedApiCallCount;
    }

    public void setTotalFailedApiCallCount(int totalFailedApiCallCount) {
        this.totalFailedApiCallCount = totalFailedApiCallCount;
    }

    public int getTotalInvalidApiCallCount() {
        return totalInvalidApiCallCount;
    }

    public void setTotalInvalidApiCallCount(int totalInvalidApiCallCount) {
        this.totalInvalidApiCallCount = totalInvalidApiCallCount;
    }

    public double getExecutedApiCallPercentage() {
        return executedApiCallPercentage;
    }

    public void setExecutedApiCallPercentage(double executedApiCallPercentage) {
        this.executedApiCallPercentage = executedApiCallPercentage;
    }

    public double getSkippedApiCallPercentage() {
        return skippedApiCallPercentage;
    }

    public void setSkippedApiCallPercentage(double skippedApiCallPercentage) {
        this.skippedApiCallPercentage = skippedApiCallPercentage;
    }

    public double getFailedApiCallPercentage() {
        return failedApiCallPercentage;
    }

    public void setFailedApiCallPercentage(double failedApiCallPercentage) {
        this.failedApiCallPercentage = failedApiCallPercentage;
    }

    public double getInvalidApiCallPercentage() {
        return invalidApiCallPercentage;
    }

    public void setInvalidApiCallPercentage(double invalidApiCallPercentage) {
        this.invalidApiCallPercentage = invalidApiCallPercentage;
    }

    public List<ApiCallReport> getApiCallReports() {
        if (apiCallReports == null) {
            apiCallReports = new ArrayList<>();
        }
        return apiCallReports;
    }

    public void setApiCallReports(List<ApiCallReport> apiCallReports) {
        if (apiCallReports == null) {
            apiCallReports = new ArrayList<>();
        }
        this.apiCallReports = apiCallReports;
    }
}

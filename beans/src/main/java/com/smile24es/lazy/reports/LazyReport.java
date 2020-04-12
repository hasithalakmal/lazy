package com.smile24es.lazy.reports;

import java.util.ArrayList;
import java.util.List;

public class LazyReport {

    private String date;

    private int totalTestSuitesCount;
    private int passTestSuitesCount;
    private int notPassTestSuitesCount;
    private double passTestSuitesPercentage;
    private double notPassTestSuitesPercentage;

    private int totalExecutedTestSuitesCount;
    private int totalSkippedTestSuitesCount;
    private int totalFailedTestSuitesCount;
    private int totalInvalidTestSuitesCount;
    private double executedTestSuitesPercentage;
    private double skippedTestSuitesPercentage;
    private double failedTestSuitesPercentage;
    private double invalidTestSuitesPercentage;

    //Test Scenario Data
    private int totalTestScenariosCount;
    private int passTestScenariosCount;
    private int notPassTestScenariosCount;
    private double passTestScenariosPercentage;
    private double notPassTestScenariosPercentage;

    private int totalExecutedTestScenariosCount;
    private int totalSkippedTestScenariosCount;
    private int totalFailedTestScenariosCount;
    private int totalInvalidTestScenariosCount;
    private double executedTestScenariosPercentage;
    private double skippedTestScenariosPercentage;
    private double failedTestScenariosPercentage;
    private double invalidTestScenariosPercentage;

    //Test Case
    private int totalTestCasesCount;
    private int passTestCasesCount;
    private int notPassTestCasesCount;
    private double passTestCasesPercentage;
    private double notPassTestCasesPercentage;

    private int totalExecutedTestCasesCount;
    private int totalSkippedTestCasesCount;
    private int totalFailedTestCasesCount;
    private int totalInvalidTestCasesCount;
    private double executedTestCasesPercentage;
    private double skippedTestCasesPercentage;
    private double failedTestCasesPercentage;
    private double invalidTestCasesPercentage;

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

    private List<TestSuiteReport> testSuiteReportList;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getTotalTestSuitesCount() {
        return totalTestSuitesCount;
    }

    public void setTotalTestSuitesCount(int totalTestSuitesCount) {
        this.totalTestSuitesCount = totalTestSuitesCount;
    }

    public int getPassTestSuitesCount() {
        return passTestSuitesCount;
    }

    public void setPassTestSuitesCount(int passTestSuitesCount) {
        this.passTestSuitesCount = passTestSuitesCount;
    }

    public int getNotPassTestSuitesCount() {
        return notPassTestSuitesCount;
    }

    public void setNotPassTestSuitesCount(int notPassTestSuitesCount) {
        this.notPassTestSuitesCount = notPassTestSuitesCount;
    }

    public double getPassTestSuitesPercentage() {
        return passTestSuitesPercentage;
    }

    public void setPassTestSuitesPercentage(double passTestSuitesPercentage) {
        this.passTestSuitesPercentage = passTestSuitesPercentage;
    }

    public double getNotPassTestSuitesPercentage() {
        return notPassTestSuitesPercentage;
    }

    public void setNotPassTestSuitesPercentage(double notPassTestSuitesPercentage) {
        this.notPassTestSuitesPercentage = notPassTestSuitesPercentage;
    }

    public int getTotalExecutedTestSuitesCount() {
        return totalExecutedTestSuitesCount;
    }

    public void setTotalExecutedTestSuitesCount(int totalExecutedTestSuitesCount) {
        this.totalExecutedTestSuitesCount = totalExecutedTestSuitesCount;
    }

    public int getTotalSkippedTestSuitesCount() {
        return totalSkippedTestSuitesCount;
    }

    public void setTotalSkippedTestSuitesCount(int totalSkippedTestSuitesCount) {
        this.totalSkippedTestSuitesCount = totalSkippedTestSuitesCount;
    }

    public int getTotalFailedTestSuitesCount() {
        return totalFailedTestSuitesCount;
    }

    public void setTotalFailedTestSuitesCount(int totalFailedTestSuitesCount) {
        this.totalFailedTestSuitesCount = totalFailedTestSuitesCount;
    }

    public int getTotalInvalidTestSuitesCount() {
        return totalInvalidTestSuitesCount;
    }

    public void setTotalInvalidTestSuitesCount(int totalInvalidTestSuitesCount) {
        this.totalInvalidTestSuitesCount = totalInvalidTestSuitesCount;
    }

    public double getExecutedTestSuitesPercentage() {
        return executedTestSuitesPercentage;
    }

    public void setExecutedTestSuitesPercentage(double executedTestSuitesPercentage) {
        this.executedTestSuitesPercentage = executedTestSuitesPercentage;
    }

    public double getSkippedTestSuitesPercentage() {
        return skippedTestSuitesPercentage;
    }

    public void setSkippedTestSuitesPercentage(double skippedTestSuitesPercentage) {
        this.skippedTestSuitesPercentage = skippedTestSuitesPercentage;
    }

    public double getFailedTestSuitesPercentage() {
        return failedTestSuitesPercentage;
    }

    public void setFailedTestSuitesPercentage(double failedTestSuitesPercentage) {
        this.failedTestSuitesPercentage = failedTestSuitesPercentage;
    }

    public double getInvalidTestSuitesPercentage() {
        return invalidTestSuitesPercentage;
    }

    public void setInvalidTestSuitesPercentage(double invalidTestSuitesPercentage) {
        this.invalidTestSuitesPercentage = invalidTestSuitesPercentage;
    }

    public int getTotalTestScenariosCount() {
        return totalTestScenariosCount;
    }

    public void setTotalTestScenariosCount(int totalTestScenariosCount) {
        this.totalTestScenariosCount = totalTestScenariosCount;
    }

    public int getPassTestScenariosCount() {
        return passTestScenariosCount;
    }

    public void setPassTestScenariosCount(int passTestScenariosCount) {
        this.passTestScenariosCount = passTestScenariosCount;
    }

    public int getNotPassTestScenariosCount() {
        return notPassTestScenariosCount;
    }

    public void setNotPassTestScenariosCount(int notPassTestScenariosCount) {
        this.notPassTestScenariosCount = notPassTestScenariosCount;
    }

    public double getPassTestScenariosPercentage() {
        return passTestScenariosPercentage;
    }

    public void setPassTestScenariosPercentage(double passTestScenariosPercentage) {
        this.passTestScenariosPercentage = passTestScenariosPercentage;
    }

    public double getNotPassTestScenariosPercentage() {
        return notPassTestScenariosPercentage;
    }

    public void setNotPassTestScenariosPercentage(double notPassTestScenariosPercentage) {
        this.notPassTestScenariosPercentage = notPassTestScenariosPercentage;
    }

    public int getTotalExecutedTestScenariosCount() {
        return totalExecutedTestScenariosCount;
    }

    public void setTotalExecutedTestScenariosCount(int totalExecutedTestScenariosCount) {
        this.totalExecutedTestScenariosCount = totalExecutedTestScenariosCount;
    }

    public int getTotalSkippedTestScenariosCount() {
        return totalSkippedTestScenariosCount;
    }

    public void setTotalSkippedTestScenariosCount(int totalSkippedTestScenariosCount) {
        this.totalSkippedTestScenariosCount = totalSkippedTestScenariosCount;
    }

    public int getTotalFailedTestScenariosCount() {
        return totalFailedTestScenariosCount;
    }

    public void setTotalFailedTestScenariosCount(int totalFailedTestScenariosCount) {
        this.totalFailedTestScenariosCount = totalFailedTestScenariosCount;
    }

    public int getTotalInvalidTestScenariosCount() {
        return totalInvalidTestScenariosCount;
    }

    public void setTotalInvalidTestScenariosCount(int totalInvalidTestScenariosCount) {
        this.totalInvalidTestScenariosCount = totalInvalidTestScenariosCount;
    }

    public double getExecutedTestScenariosPercentage() {
        return executedTestScenariosPercentage;
    }

    public void setExecutedTestScenariosPercentage(double executedTestScenariosPercentage) {
        this.executedTestScenariosPercentage = executedTestScenariosPercentage;
    }

    public double getSkippedTestScenariosPercentage() {
        return skippedTestScenariosPercentage;
    }

    public void setSkippedTestScenariosPercentage(double skippedTestScenariosPercentage) {
        this.skippedTestScenariosPercentage = skippedTestScenariosPercentage;
    }

    public double getFailedTestScenariosPercentage() {
        return failedTestScenariosPercentage;
    }

    public void setFailedTestScenariosPercentage(double failedTestScenariosPercentage) {
        this.failedTestScenariosPercentage = failedTestScenariosPercentage;
    }

    public double getInvalidTestScenariosPercentage() {
        return invalidTestScenariosPercentage;
    }

    public void setInvalidTestScenariosPercentage(double invalidTestScenariosPercentage) {
        this.invalidTestScenariosPercentage = invalidTestScenariosPercentage;
    }

    public int getTotalTestCasesCount() {
        return totalTestCasesCount;
    }

    public void setTotalTestCasesCount(int totalTestCasesCount) {
        this.totalTestCasesCount = totalTestCasesCount;
    }

    public int getPassTestCasesCount() {
        return passTestCasesCount;
    }

    public void setPassTestCasesCount(int passTestCasesCount) {
        this.passTestCasesCount = passTestCasesCount;
    }

    public int getNotPassTestCasesCount() {
        return notPassTestCasesCount;
    }

    public void setNotPassTestCasesCount(int notPassTestCasesCount) {
        this.notPassTestCasesCount = notPassTestCasesCount;
    }

    public double getPassTestCasesPercentage() {
        return passTestCasesPercentage;
    }

    public void setPassTestCasesPercentage(double passTestCasesPercentage) {
        this.passTestCasesPercentage = passTestCasesPercentage;
    }

    public double getNotPassTestCasesPercentage() {
        return notPassTestCasesPercentage;
    }

    public void setNotPassTestCasesPercentage(double notPassTestCasesPercentage) {
        this.notPassTestCasesPercentage = notPassTestCasesPercentage;
    }

    public int getTotalExecutedTestCasesCount() {
        return totalExecutedTestCasesCount;
    }

    public void setTotalExecutedTestCasesCount(int totalExecutedTestCasesCount) {
        this.totalExecutedTestCasesCount = totalExecutedTestCasesCount;
    }

    public int getTotalSkippedTestCasesCount() {
        return totalSkippedTestCasesCount;
    }

    public void setTotalSkippedTestCasesCount(int totalSkippedTestCasesCount) {
        this.totalSkippedTestCasesCount = totalSkippedTestCasesCount;
    }

    public int getTotalFailedTestCasesCount() {
        return totalFailedTestCasesCount;
    }

    public void setTotalFailedTestCasesCount(int totalFailedTestCasesCount) {
        this.totalFailedTestCasesCount = totalFailedTestCasesCount;
    }

    public int getTotalInvalidTestCasesCount() {
        return totalInvalidTestCasesCount;
    }

    public void setTotalInvalidTestCasesCount(int totalInvalidTestCasesCount) {
        this.totalInvalidTestCasesCount = totalInvalidTestCasesCount;
    }

    public double getExecutedTestCasesPercentage() {
        return executedTestCasesPercentage;
    }

    public void setExecutedTestCasesPercentage(double executedTestCasesPercentage) {
        this.executedTestCasesPercentage = executedTestCasesPercentage;
    }

    public double getSkippedTestCasesPercentage() {
        return skippedTestCasesPercentage;
    }

    public void setSkippedTestCasesPercentage(double skippedTestCasesPercentage) {
        this.skippedTestCasesPercentage = skippedTestCasesPercentage;
    }

    public double getFailedTestCasesPercentage() {
        return failedTestCasesPercentage;
    }

    public void setFailedTestCasesPercentage(double failedTestCasesPercentage) {
        this.failedTestCasesPercentage = failedTestCasesPercentage;
    }

    public double getInvalidTestCasesPercentage() {
        return invalidTestCasesPercentage;
    }

    public void setInvalidTestCasesPercentage(double invalidTestCasesPercentage) {
        this.invalidTestCasesPercentage = invalidTestCasesPercentage;
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

    public List<TestSuiteReport> getTestSuiteReportList() {
        if (testSuiteReportList == null) {
            testSuiteReportList = new ArrayList<>();
        }
        return testSuiteReportList;
    }

    public void setTestSuiteReportList(List<TestSuiteReport> testSuiteReportList) {
        this.testSuiteReportList = testSuiteReportList;
    }
}

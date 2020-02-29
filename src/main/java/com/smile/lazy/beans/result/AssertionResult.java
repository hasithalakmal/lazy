package com.smile.lazy.beans.result;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class AssertionResult implements Serializable {

    private int resultId;
    private String resultDisplayId;
    private String resultName;
    private String resultDescription;
    private Boolean active;
    private int testSuiteId;
    private int testScenarioId;
    private int testCaseId;
    private int apiCallId;
    private int assertionRuleId;
    private String actualValue;
    private Boolean pass;
    private String assertionStatus;

    public AssertionResult(int resultId, int apiCallId, int assertionRuleId, String actualValue) {
        this.resultId = resultId;
        this.apiCallId = apiCallId;
        this.assertionRuleId = assertionRuleId;
        this.actualValue = actualValue;
    }

    public AssertionResult(int resultId, int apiCallId, int assertionRuleId, String actualValue, Boolean pass) {
        this.resultId = resultId;
        this.apiCallId = apiCallId;
        this.assertionRuleId = assertionRuleId;
        this.actualValue = actualValue;
        this.pass = pass;
    }

    public AssertionResult(int resultId, int apiCallId, int assertionRuleId, String actualValue, Boolean pass, String assertionStatus) {
        this.resultId = resultId;
        this.apiCallId = apiCallId;
        this.assertionRuleId = assertionRuleId;
        this.actualValue = actualValue;
        this.pass = pass;
        this.assertionStatus = assertionStatus;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public String getResultDisplayId() {
        return resultDisplayId;
    }

    public void setResultDisplayId(String resultDisplayId) {
        this.resultDisplayId = resultDisplayId;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public int getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(int testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    public int getTestScenarioId() {
        return testScenarioId;
    }

    public void setTestScenarioId(int testScenarioId) {
        this.testScenarioId = testScenarioId;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public int getApiCallId() {
        return apiCallId;
    }

    public void setApiCallId(int apiCallId) {
        this.apiCallId = apiCallId;
    }

    public int getAssertionRuleId() {
        return assertionRuleId;
    }

    public void setAssertionRuleId(int assertionRuleId) {
        this.assertionRuleId = assertionRuleId;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public Boolean getPass() {
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public String getAssertionStatus() {
        return assertionStatus;
    }

    public void setAssertionStatus(String assertionStatus) {
        this.assertionStatus = assertionStatus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssertionResult that = (AssertionResult) o;

        return new EqualsBuilder()
              .append(resultId, that.resultId)
              .append(testSuiteId, that.testSuiteId)
              .append(testScenarioId, that.testScenarioId)
              .append(testCaseId, that.testCaseId)
              .append(apiCallId, that.apiCallId)
              .append(assertionRuleId, that.assertionRuleId)
              .append(resultDisplayId, that.resultDisplayId)
              .append(resultName, that.resultName)
              .append(resultDescription, that.resultDescription)
              .append(active, that.active)
              .append(actualValue, that.actualValue)
              .append(pass, that.pass)
              .append(assertionStatus, that.assertionStatus)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(resultId)
              .append(resultDisplayId)
              .append(resultName)
              .append(resultDescription)
              .append(active)
              .append(testSuiteId)
              .append(testScenarioId)
              .append(testCaseId)
              .append(apiCallId)
              .append(assertionRuleId)
              .append(actualValue)
              .append(pass)
              .append(assertionStatus)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "AssertionResult{" +
              "resultId=" + resultId +
              ", resultDisplayId='" + resultDisplayId + '\'' +
              ", resultName='" + resultName + '\'' +
              ", resultDescription='" + resultDescription + '\'' +
              ", active=" + active +
              ", testSuiteId=" + testSuiteId +
              ", testScenarioId=" + testScenarioId +
              ", testCaseId=" + testCaseId +
              ", apiCallId=" + apiCallId +
              ", assertionRuleId=" + assertionRuleId +
              ", actualValue='" + actualValue + '\'' +
              ", pass=" + pass +
              ", assertionStatus=" + assertionStatus +
              '}';
    }
}

package com.smile24es.lazy.beans.suite.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile24es.lazy.beans.enums.ActionTypeEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecuteAnotherTest extends Action implements Serializable {

    private int testSuiteId;
    private int testScenarioId;
    private int testCaseId;
    private int apiCallId;
    private int apiCallDisplayId; //Can use to execute api call directly
    private Action action;

    public ExecuteAnotherTest(ActionTypeEnum actionType) {
        super(actionType);
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

    public int getApiCallDisplayId() {
        return apiCallDisplayId;
    }

    public void setApiCallDisplayId(int apiCallDisplayId) {
        this.apiCallDisplayId = apiCallDisplayId;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExecuteAnotherTest that = (ExecuteAnotherTest) o;

        return new EqualsBuilder()
              .appendSuper(super.equals(o))
              .append(testSuiteId, that.testSuiteId)
              .append(testScenarioId, that.testScenarioId)
              .append(testCaseId, that.testCaseId)
              .append(apiCallId, that.apiCallId)
              .append(apiCallDisplayId, that.apiCallDisplayId)
              .append(action, that.action)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .appendSuper(super.hashCode())
              .append(testSuiteId)
              .append(testScenarioId)
              .append(testCaseId)
              .append(apiCallId)
              .append(apiCallDisplayId)
              .append(action)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "ExecuteAnotherTest{" +
              "testSuiteId=" + testSuiteId +
              ", testScenarioId=" + testScenarioId +
              ", testCaseId=" + testCaseId +
              ", apiCallId=" + apiCallId +
              ", apiCallDisplayId=" + apiCallDisplayId +
              ", action=" + action +
              "} " + super.toString();
    }
}

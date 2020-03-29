package com.smile.lazy.beans.suite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile.lazy.beans.suite.actions.Action;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCase implements Serializable {

    private Integer testCaseId;
    private String testCaseDisplayId;
    private String testCaseName;
    private List<String> assignGroups;
    private String testCaseDescription;
    private Boolean active;
    private List<ApiCall> apiCalls;
    private List<Action> preActions;
    private List<Action> postActions;
    private Stack stack;

    public TestCase(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseDisplayId() {
        return testCaseDisplayId;
    }

    public void setTestCaseDisplayId(String testCaseDisplayId) {
        this.testCaseDisplayId = testCaseDisplayId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public List<String> getAssignGroups() {
        return assignGroups;
    }

    public void setAssignGroups(List<String> assignGroups) {
        this.assignGroups = assignGroups;
    }

    public String getTestCaseDescription() {
        return testCaseDescription;
    }

    public void setTestCaseDescription(String testCaseDescription) {
        this.testCaseDescription = testCaseDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<ApiCall> getApiCalls() {
        if (apiCalls == null) {
            apiCalls = new ArrayList<>();
        }
        return apiCalls;
    }

    public void setApiCalls(List<ApiCall> apiCalls) {
        this.apiCalls = apiCalls;
    }

    public List<Action> getPreActions() {
        return preActions;
    }

    public void setPreActions(List<Action> preActions) {
        this.preActions = preActions;
    }

    public List<Action> getPostActions() {
        return postActions;
    }

    public void setPostActions(List<Action> postActions) {
        this.postActions = postActions;
    }

    public Stack getStack() {
        if (stack == null) {
            this.stack = new Stack();
        }
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = SerializationUtils.clone(stack);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TestCase testCase = (TestCase) o;

        return new EqualsBuilder()
              .append(testCaseId, testCase.testCaseId)
              .append(testCaseDisplayId, testCase.testCaseDisplayId)
              .append(testCaseName, testCase.testCaseName)
              .append(assignGroups, testCase.assignGroups)
              .append(testCaseDescription, testCase.testCaseDescription)
              .append(active, testCase.active)
              .append(apiCalls, testCase.apiCalls)
              .append(preActions, testCase.preActions)
              .append(postActions, testCase.postActions)
              .append(stack, testCase.stack)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(testCaseId)
              .append(testCaseDisplayId)
              .append(testCaseName)
              .append(assignGroups)
              .append(testCaseDescription)
              .append(active)
              .append(apiCalls)
              .append(preActions)
              .append(postActions)
              .append(stack)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "TestCase{" +
              "testCaseId=" + testCaseId +
              ", testCaseDisplayId='" + testCaseDisplayId + '\'' +
              ", testCaseName='" + testCaseName + '\'' +
              ", assignGroups='" + assignGroups + '\'' +
              ", testCaseDescription='" + testCaseDescription + '\'' +
              ", active=" + active +
              ", apiCalls=" + apiCalls +
              ", preActions=" + preActions +
              ", postActions=" + postActions +
              ", postActions=" + stack +
              '}';
    }
}

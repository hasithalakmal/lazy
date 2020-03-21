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
public class TestScenario implements Serializable {

    private Integer testScenarioId;
    private String testScenarioDisplayId;
    private String testScenarioName;
    private String testScenarioDescription;
    private Boolean active;
    private List<TestCase> testCases;
    private List<Action> preActions;
    private List<Action> postActions;
    private Stack stack;

    public TestScenario(String testScenarioDisplayId, String testScenarioName) {
        this.testScenarioDisplayId = testScenarioDisplayId;
        this.testScenarioName = testScenarioName;
    }

    public Integer getTestScenarioId() {
        return testScenarioId;
    }

    public void setTestScenarioId(Integer testScenarioId) {
        this.testScenarioId = testScenarioId;
    }

    public String getTestScenarioDisplayId() {
        return testScenarioDisplayId;
    }

    public void setTestScenarioDisplayId(String testScenarioDisplayId) {
        this.testScenarioDisplayId = testScenarioDisplayId;
    }

    public String getTestScenarioName() {
        return testScenarioName;
    }

    public void setTestScenarioName(String testScenarioName) {
        this.testScenarioName = testScenarioName;
    }

    public String getTestScenarioDescription() {
        return testScenarioDescription;
    }

    public void setTestScenarioDescription(String testScenarioDescription) {
        this.testScenarioDescription = testScenarioDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<TestCase> getTestCases() {
        if (testCases == null) {
            testCases = new ArrayList<>();
        }
        return testCases;
    }

    public void setTestCases(List<TestCase> testCases) {
        this.testCases = testCases;
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

        TestScenario that = (TestScenario) o;

        return new EqualsBuilder()
              .append(testScenarioId, that.testScenarioId)
              .append(testScenarioDisplayId, that.testScenarioDisplayId)
              .append(testScenarioName, that.testScenarioName)
              .append(testScenarioDescription, that.testScenarioDescription)
              .append(active, that.active)
              .append(testCases, that.testCases)
              .append(preActions, that.preActions)
              .append(postActions, that.postActions)
              .append(stack, that.stack)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(testScenarioId)
              .append(testScenarioDisplayId)
              .append(testScenarioName)
              .append(testScenarioDescription)
              .append(active)
              .append(testCases)
              .append(preActions)
              .append(postActions)
              .append(stack)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "TestScenario{" +
              "testScenarioId=" + testScenarioId +
              ", testScenarioDisplayId='" + testScenarioDisplayId + '\'' +
              ", testScenarioName='" + testScenarioName + '\'' +
              ", testScenarioDescription='" + testScenarioDescription + '\'' +
              ", active=" + active +
              ", testCases=" + testCases +
              ", preActions=" + preActions +
              ", postActions=" + postActions +
              ", stack=" + stack +
              '}';
    }
}

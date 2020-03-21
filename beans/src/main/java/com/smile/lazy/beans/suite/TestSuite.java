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
public class TestSuite implements Serializable {

    private Integer testSuiteId;
    private String testSuiteDisplayId;
    private String testSuiteName;
    private String testSuiteDescription;
    private Boolean active;
    private List<TestScenario> testScenarios;
    private List<Action> preActions;
    private List<Action> postActions;
    private Stack stack;

    public TestSuite(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public Integer getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(Integer testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    public String getTestSuiteDisplayId() {
        return testSuiteDisplayId;
    }

    public void setTestSuiteDisplayId(String testSuiteDisplayId) {
        this.testSuiteDisplayId = testSuiteDisplayId;
    }

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public String getTestSuiteDescription() {
        return testSuiteDescription;
    }

    public void setTestSuiteDescription(String testSuiteDescription) {
        this.testSuiteDescription = testSuiteDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<TestScenario> getTestScenarios() {
        if (testScenarios == null) {
            testScenarios = new ArrayList<>();
        }
        return testScenarios;
    }

    public void setTestScenarios(List<TestScenario> testScenarios) {
        this.testScenarios = testScenarios;
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

        TestSuite testSuite = (TestSuite) o;

        return new EqualsBuilder()
              .append(testSuiteId, testSuite.testSuiteId)
              .append(testSuiteDisplayId, testSuite.testSuiteDisplayId)
              .append(testSuiteName, testSuite.testSuiteName)
              .append(testSuiteDescription, testSuite.testSuiteDescription)
              .append(active, testSuite.active)
              .append(testScenarios, testSuite.testScenarios)
              .append(preActions, testSuite.preActions)
              .append(postActions, testSuite.postActions)
              .append(stack, testSuite.stack)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(testSuiteId)
              .append(testSuiteDisplayId)
              .append(testSuiteName)
              .append(testSuiteDescription)
              .append(active)
              .append(testScenarios)
              .append(preActions)
              .append(postActions)
              .append(stack)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "TestSuite{" +
              "testSuiteId=" + testSuiteId +
              ", testSuiteDisplayId='" + testSuiteDisplayId + '\'' +
              ", testSuiteName='" + testSuiteName + '\'' +
              ", testSuiteDescription='" + testSuiteDescription + '\'' +
              ", active=" + active +
              ", testScenarios=" + testScenarios +
              ", preActions=" + preActions +
              ", postActions=" + postActions +
              ", postActions=" + stack +
              '}';
    }
}

package com.smile.lazy.beans.executor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LazyExecutionGroup implements Serializable {

    private List<String> testSuiteExecutionGroupNames;
    private List<String> testScenarioExecutionGroupNames;
    private List<String> testCaseExecutionGroupNames;
    private List<String> apiCallExecutionGroupNames;

    public List<String> getTestSuiteExecutionGroupNames() {
        return testSuiteExecutionGroupNames;
    }

    public void setTestSuiteExecutionGroupNames(List<String> testSuiteExecutionGroupNames) {
        this.testSuiteExecutionGroupNames = testSuiteExecutionGroupNames;
    }

    public List<String> getTestScenarioExecutionGroupNames() {
        return testScenarioExecutionGroupNames;
    }

    public void setTestScenarioExecutionGroupNames(List<String> testScenarioExecutionGroupNames) {
        this.testScenarioExecutionGroupNames = testScenarioExecutionGroupNames;
    }

    public List<String> getTestCaseExecutionGroupNames() {
        return testCaseExecutionGroupNames;
    }

    public void setTestCaseExecutionGroupNames(List<String> testCaseExecutionGroupNames) {
        this.testCaseExecutionGroupNames = testCaseExecutionGroupNames;
    }

    public List<String> getApiCallExecutionGroupNames() {
        return apiCallExecutionGroupNames;
    }

    public void setApiCallExecutionGroupNames(List<String> apiCallExecutionGroupNames) {
        this.apiCallExecutionGroupNames = apiCallExecutionGroupNames;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LazyExecutionGroup that = (LazyExecutionGroup) o;

        return new EqualsBuilder()
              .append(testSuiteExecutionGroupNames, that.testSuiteExecutionGroupNames)
              .append(testScenarioExecutionGroupNames, that.testScenarioExecutionGroupNames)
              .append(testCaseExecutionGroupNames, that.testCaseExecutionGroupNames)
              .append(apiCallExecutionGroupNames, that.apiCallExecutionGroupNames)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(testSuiteExecutionGroupNames)
              .append(testScenarioExecutionGroupNames)
              .append(testCaseExecutionGroupNames)
              .append(apiCallExecutionGroupNames)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "LazyExecutionGroup{" +
              "testSuiteExecutionGroupNames=" + testSuiteExecutionGroupNames +
              ", testScenarioExecutionGroupNames=" + testScenarioExecutionGroupNames +
              ", testCaseExecutionGroupNames=" + testCaseExecutionGroupNames +
              ", apiCallExecutionGroupNames=" + apiCallExecutionGroupNames +
              '}';
    }
}

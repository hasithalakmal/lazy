package com.smile.lazy.beans.executor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestSuiteExecutionData {

    private Integer testSuiteId;
    private String testSuiteName;
    private List<TestScenarioExecutionData> testScenarioExecutionData;

    public TestSuiteExecutionData(Integer testSuiteId, String testSuiteName) {
        this.testSuiteId = testSuiteId;
        this.testSuiteName = testSuiteName;
    }

    public Integer getTestSuiteId() {
        return testSuiteId;
    }

    public void setTestSuiteId(Integer testSuiteId) {
        this.testSuiteId = testSuiteId;
    }

    public String getTestSuiteName() {
        return testSuiteName;
    }

    public void setTestSuiteName(String testSuiteName) {
        this.testSuiteName = testSuiteName;
    }

    public List<TestScenarioExecutionData> getTestScenarioExecutionData() {
        if(testScenarioExecutionData == null) {
            testScenarioExecutionData = new ArrayList<>();
        }
        return testScenarioExecutionData;
    }

    public void setTestScenarioExecutionData(List<TestScenarioExecutionData> testScenarioExecutionData) {
        this.testScenarioExecutionData = testScenarioExecutionData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TestSuiteExecutionData that = (TestSuiteExecutionData) o;

        return new EqualsBuilder()
              .append(testSuiteId, that.testSuiteId)
              .append(testSuiteName, that.testSuiteName)
              .append(testScenarioExecutionData, that.testScenarioExecutionData)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(testSuiteId)
              .append(testSuiteName)
              .append(testScenarioExecutionData)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "TestSuiteExecutionData{" +
              "testSuiteId=" + testSuiteId +
              ", testSuiteName=" + testSuiteName +
              ", testScenarioExecutionData=" + testScenarioExecutionData +
              '}';
    }
}

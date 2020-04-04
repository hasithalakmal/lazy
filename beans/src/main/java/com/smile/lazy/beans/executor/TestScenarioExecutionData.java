package com.smile.lazy.beans.executor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestScenarioExecutionData {

    private Integer testScenarioId;
    private String testScenarioName;
    private List<TestCaseExecutionData> testCaseExecutionDataList;

    public TestScenarioExecutionData(Integer testScenarioId, String testScenarioName) {
        this.testScenarioId = testScenarioId;
        this.testScenarioName = testScenarioName;
    }

    public Integer getTestScenarioId() {
        return testScenarioId;
    }

    public void setTestScenarioId(Integer testScenarioId) {
        this.testScenarioId = testScenarioId;
    }

    public String getTestScenarioName() {
        return testScenarioName;
    }

    public void setTestScenarioName(String testScenarioName) {
        this.testScenarioName = testScenarioName;
    }

    public List<TestCaseExecutionData> getTestCaseExecutionDataList() {
        if (testCaseExecutionDataList == null) {
            testCaseExecutionDataList = new ArrayList<>();
        }
        return testCaseExecutionDataList;
    }

    public void setTestCaseExecutionDataList(List<TestCaseExecutionData> testCaseExecutionDataList) {
        this.testCaseExecutionDataList = testCaseExecutionDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TestScenarioExecutionData that = (TestScenarioExecutionData) o;

        return new EqualsBuilder()
              .append(testScenarioId, that.testScenarioId)
              .append(testScenarioName, that.testScenarioName)
              .append(testCaseExecutionDataList, that.testCaseExecutionDataList)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(testScenarioId)
              .append(testScenarioName)
              .append(testCaseExecutionDataList)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "TestScenarioExecutionData{" +
              "testScenarioId=" + testScenarioId +
              ", testScenarioName='" + testScenarioName + '\'' +
              ", testCaseExecutionDataList=" + testCaseExecutionDataList +
              '}';
    }
}

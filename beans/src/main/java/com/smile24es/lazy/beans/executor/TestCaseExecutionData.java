package com.smile24es.lazy.beans.executor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestCaseExecutionData {

    private Integer testCaseId;
    private String testCaseName;
    private List<ApiCallExecutionData> apiCallExecutionDataList;

    public TestCaseExecutionData(Integer testCaseId, String testCaseName) {
        this.testCaseId = testCaseId;
        this.testCaseName = testCaseName;
    }

    public Integer getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(Integer testCaseId) {
        this.testCaseId = testCaseId;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public List<ApiCallExecutionData> getApiCallExecutionDataList() {
        if (apiCallExecutionDataList == null) {
            apiCallExecutionDataList = new ArrayList<>();
        }
        return apiCallExecutionDataList;
    }

    public void setApiCallExecutionDataList(List<ApiCallExecutionData> apiCallExecutionDataList) {
        this.apiCallExecutionDataList = apiCallExecutionDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TestCaseExecutionData that = (TestCaseExecutionData) o;

        return new EqualsBuilder()
              .append(testCaseId, that.testCaseId)
              .append(testCaseName, that.testCaseName)
              .append(apiCallExecutionDataList, that.apiCallExecutionDataList)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(testCaseId)
              .append(testCaseName)
              .append(apiCallExecutionDataList)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "TestCaseExecutionData{" +
              "testCaseId=" + testCaseId +
              ", testCaseName='" + testCaseName + '\'' +
              ", apiCallExecutionDataList=" + apiCallExecutionDataList +
              '}';
    }
}

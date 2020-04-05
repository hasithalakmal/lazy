package com.smile24es.lazy.beans.executor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LazyExecutionData implements Serializable {

    private List<TestSuiteExecutionData> testSuiteExecutionData;

    public List<TestSuiteExecutionData> getTestSuiteExecutionData() {
        if (testSuiteExecutionData == null) {
            testSuiteExecutionData = new ArrayList<>();
        }
        return testSuiteExecutionData;
    }

    public void setTestSuiteExecutionData(List<TestSuiteExecutionData> testSuiteExecutionData) {
        this.testSuiteExecutionData = testSuiteExecutionData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LazyExecutionData that = (LazyExecutionData) o;

        return new EqualsBuilder()
              .append(testSuiteExecutionData, that.testSuiteExecutionData)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(testSuiteExecutionData)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "AssertionResultList{" +
              ", testSuiteExecutionData=" + testSuiteExecutionData +
              '}';
    }
}

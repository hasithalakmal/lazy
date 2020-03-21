package com.smile.lazy.beans.executor;

import com.smile.lazy.beans.result.AssertionResult;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AssertionExecutionData {

    private AssertionRule assertionRule;
    private AssertionResult assertionResult;
    private String assertionType;
    private String expectedValue;
    private String actualValue;
    private String scope;

    public AssertionRule getAssertionRule() {
        return assertionRule;
    }

    public void setAssertionRule(AssertionRule assertionRule) {
        this.assertionRule = assertionRule;
    }

    public AssertionResult getAssertionResult() {
        return assertionResult;
    }

    public void setAssertionResult(AssertionResult assertionResult) {
        this.assertionResult = assertionResult;
    }

    public String getAssertionType() {
        return assertionType;
    }

    public void setAssertionType(String assertionType) {
        this.assertionType = assertionType;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AssertionExecutionData that = (AssertionExecutionData) o;

        return new EqualsBuilder()
              .append(assertionRule, that.assertionRule)
              .append(assertionResult, that.assertionResult)
              .append(assertionType, that.assertionType)
              .append(expectedValue, that.expectedValue)
              .append(actualValue, that.actualValue)
              .append(scope, that.scope)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(assertionRule)
              .append(assertionResult)
              .append(assertionType)
              .append(expectedValue)
              .append(actualValue)
              .append(scope)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "AssertionExecutionData{" +
              "assertionRule=" + assertionRule +
              ", assertionResult=" + assertionResult +
              ", assertionType='" + assertionType + '\'' +
              ", expectedValue='" + expectedValue + '\'' +
              ", actualValue='" + actualValue + '\'' +
              ", actualValue='" + scope + '\'' +
              '}';
    }
}

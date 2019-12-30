package com.smile.lazy.beans.suite.Assertions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class MatchingCondition implements Serializable {

    private String key;
    private String expectedValue;
    private String operation;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        MatchingCondition that = (MatchingCondition) o;

        return new EqualsBuilder()
              .append(key, that.key)
              .append(expectedValue, that.expectedValue)
              .append(operation, that.operation)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(key)
              .append(expectedValue)
              .append(operation)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "MatchingCondition{" +
              "key='" + key + '\'' +
              ", expectedValue='" + expectedValue + '\'' +
              ", operation='" + operation + '\'' +
              '}';
    }
}

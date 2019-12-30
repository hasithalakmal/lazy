package com.smile.lazy.beans.suite.Assertions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class AssertionValue implements Serializable {

    private String expectedValue;

    public String getExpectedValue() {
        return expectedValue;
    }

    public void setExpectedValue(String expectedValue) {
        this.expectedValue = expectedValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AssertionValue that = (AssertionValue) o;

        return new EqualsBuilder()
              .append(expectedValue, that.expectedValue)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(expectedValue)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "AssertionValue{" +
              "expectedValue='" + expectedValue + '\'' +
              '}';
    }
}

package com.smile.lazy.beans.suite.Assertions;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class ConditionAssertion extends AssertionValue implements Serializable {

    private String condition;

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ConditionAssertion that = (ConditionAssertion) o;

        return new EqualsBuilder()
              .appendSuper(super.equals(o))
              .append(condition, that.condition)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .appendSuper(super.hashCode())
              .append(condition)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "ConditionAssertion{" +
              "condition='" + condition + '\'' +
              "} " + super.toString();
    }
}

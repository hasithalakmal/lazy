package com.smile.lazy.beans.suite;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.environment.Environment;
import com.smile.lazy.beans.suite.assertions.AssertionRuleGroup;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.Map;

public class Stack implements Serializable {

    AssertionRuleGroup defaultAssertionGroup;
    private DefaultValues defaultValues;
    private HeaderGroup headerGroup;
    private Environment globalEnvironment;
    private Map<String, Environment> environments; //environmentDisplayId, environment
    private Map<String, String> attributes; //attribute key, value

    public Stack() {
    }

    public Stack(DefaultValues defaultValues) {
        this.defaultValues = defaultValues;
    }

    public Stack(DefaultValues defaultValues, Environment globalEnvironment) {
        this.defaultValues = defaultValues;
        this.globalEnvironment = globalEnvironment;
    }

    public DefaultValues getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(DefaultValues defaultValues) {
        this.defaultValues = defaultValues;
    }

    public HeaderGroup getHeaderGroup() {
        return headerGroup;
    }

    public void setHeaderGroup(HeaderGroup headerGroup) {
        this.headerGroup = headerGroup;
    }

    public AssertionRuleGroup getDefaultAssertionGroup() {
        return defaultAssertionGroup;
    }

    public void setDefaultAssertionGroup(AssertionRuleGroup defaultAssertionGroup) {
        this.defaultAssertionGroup = defaultAssertionGroup;
    }

    public Environment getGlobalEnvironment() {
        return globalEnvironment;
    }

    public void setGlobalEnvironment(Environment globalEnvironment) {
        this.globalEnvironment = globalEnvironment;
    }

    public Map<String, Environment> getEnvironments() {
        return environments;
    }

    public void setEnvironments(Map<String, Environment> environments) {
        this.environments = environments;
    }

    public Map<String, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, String> attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Stack stack = (Stack) o;

        return new EqualsBuilder()
              .append(defaultValues, stack.defaultValues)
              .append(headerGroup, stack.headerGroup)
              .append(defaultAssertionGroup, stack.defaultAssertionGroup)
              .append(globalEnvironment, stack.globalEnvironment)
              .append(environments, stack.environments)
              .append(attributes, stack.attributes)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(defaultValues)
              .append(headerGroup)
              .append(defaultAssertionGroup)
              .append(globalEnvironment)
              .append(environments)
              .append(attributes)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "Stack{" +
              "defaultValues=" + defaultValues +
              ", defaultHeaderGroup=" + headerGroup +
              ", defaultCreateAssertionGroup=" + defaultAssertionGroup +
              ", globalEnvironment=" + globalEnvironment +
              ", environments=" + environments +
              ", attributes=" + attributes +
              '}';
    }
}

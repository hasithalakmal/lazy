package com.smile.lazy.beans.suite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.environment.Environment;
import com.smile.lazy.beans.environment.EnvironmentVariable;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionRuleGroup;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Stack implements Serializable {

    private List<AssertionRule> defaultAssertions;
    private DefaultValues defaultValues;
    private HeaderGroup headerGroup;
    private Map<String, EnvironmentVariable> globalEnvironment; //key, value
    private Map<String, Environment> environments; //environmentDisplayId, environment
    private Map<String, String> attributes; //attribute key, value

    public Stack() {
    }

    public Stack(DefaultValues defaultValues) {
        this.defaultValues = defaultValues;
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

    public List<AssertionRule> getDefaultAssertions() {
        if (defaultAssertions == null) {
            defaultAssertions = new ArrayList<>();
        }
        return defaultAssertions;
    }

    public void setDefaultAssertions(List<AssertionRule> defaultAssertions) {
        this.defaultAssertions = defaultAssertions;
    }

    public Map<String, EnvironmentVariable> getGlobalEnvironment() {
        return globalEnvironment;
    }

    public void setGlobalEnvironment(Map<String, EnvironmentVariable> globalEnvironment) {
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

    //TODO - think how to remove these logics from the beans
    public void addDefaultAssertionRule(AssertionRule assertionRule) {
        if (this.defaultAssertions == null || this.defaultAssertions.isEmpty()) {
            this.defaultAssertions = new ArrayList<>();
        }
        this.defaultAssertions.add(assertionRule);
    }

    public void addDefaultAssertionGroup(AssertionRuleGroup assertionRuleGroup) {
        if (this.defaultAssertions == null || this.defaultAssertions.isEmpty()) {
            this.defaultAssertions = new ArrayList<>();
        }
        if (assertionRuleGroup != null && !assertionRuleGroup.getAssertionRules().isEmpty()) {
            for (AssertionRule newAssertionRule : assertionRuleGroup.getAssertionRules()) {
                String newAssertionRuleKey = newAssertionRule.getAssertionRuleKey();
                if (StringUtils.isNotBlank(newAssertionRuleKey)) {
                    for (AssertionRule existingRule : this.defaultAssertions) {
                        String existingAssertionRuleKey = existingRule.getAssertionRuleKey();
                        if (StringUtils.isNotBlank(existingAssertionRuleKey) && newAssertionRuleKey.equals(existingAssertionRuleKey)) {
                            existingRule.setActive(Boolean.FALSE);
                        }
                    }
                }
                this.defaultAssertions.add(newAssertionRule);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Stack stack = (Stack) o;

        return new EqualsBuilder()
              .append(defaultValues, stack.defaultValues)
              .append(headerGroup, stack.headerGroup)
              .append(defaultAssertions, stack.defaultAssertions)
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
              .append(defaultAssertions)
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
              ", defaultCreateAssertionGroup=" + defaultAssertions +
              ", globalEnvironment=" + globalEnvironment +
              ", environments=" + environments +
              ", attributes=" + attributes +
              '}';
    }
}

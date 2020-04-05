package com.smile24es.lazy.beans.suite.assertions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BodyValueAssertion extends AssertionValue implements Serializable {

    private String jsonPath;

    public BodyValueAssertion(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public BodyValueAssertion(String jsonPath, String expectedValue) {
        super(expectedValue);
        this.jsonPath = jsonPath;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BodyValueAssertion that = (BodyValueAssertion) o;

        return new EqualsBuilder()
              .appendSuper(super.equals(o))
              .append(jsonPath, that.jsonPath)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .appendSuper(super.hashCode())
              .append(jsonPath)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "BodyValueAssertion{" +
              "jsonPath='" + jsonPath + '\'' +
              "} " + super.toString();
    }
}

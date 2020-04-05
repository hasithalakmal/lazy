package com.smile24es.lazy.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile24es.lazy.beans.suite.Global;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.TestSuite;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.smile24es.lazy.common.SampleDefaultValues.createDefaultValues;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class LazySuite implements Serializable {

    private int lazySuiteId;
    private String lazySuiteDisplayId;
    private String lazySuiteName;
    private String lazySuiteDescription;
    private Boolean active;
    private List<TestSuite> testSuites;
    private Stack stack;
    private Global global;


    public LazySuite(String lazySuiteName) {
        DefaultValues defaultValues = createDefaultValues();
        Stack lazySuiteStack = new Stack(defaultValues);
        this.lazySuiteName = lazySuiteName;
        this.stack = lazySuiteStack;
    }

    public LazySuite(String lazySuiteName, Stack stack) {
        this.lazySuiteName = lazySuiteName;
        this.stack = stack;
    }

    public int getLazySuiteId() {
        return lazySuiteId;
    }

    public void setLazySuiteId(int lazySuiteId) {
        this.lazySuiteId = lazySuiteId;
    }

    public String getLazySuiteDisplayId() {
        return lazySuiteDisplayId;
    }

    public void setLazySuiteDisplayId(String lazySuiteDisplayId) {
        this.lazySuiteDisplayId = lazySuiteDisplayId;
    }

    public String getLazySuiteName() {
        return lazySuiteName;
    }

    public void setLazySuiteName(String lazySuiteName) {
        this.lazySuiteName = lazySuiteName;
    }

    public String getLazySuiteDescription() {
        return lazySuiteDescription;
    }

    public void setLazySuiteDescription(String lazySuiteDescription) {
        this.lazySuiteDescription = lazySuiteDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<TestSuite> getTestSuites() {
        if (testSuites == null) {
            testSuites = new ArrayList<>();
        }
        return testSuites;
    }

    public void setTestSuites(List<TestSuite> testSuites) {
        this.testSuites = testSuites;
    }

    public Stack getStack() {
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = stack;
    }

    public Global getGlobal() {
        if (global == null) {
            global = new Global();
        }
        return global;
    }

    public void setGlobal(Global global) {
        this.global = global;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LazySuite lazySuite = (LazySuite) o;

        return new EqualsBuilder()
              .append(lazySuiteId, lazySuite.lazySuiteId)
              .append(lazySuiteDisplayId, lazySuite.lazySuiteDisplayId)
              .append(lazySuiteName, lazySuite.lazySuiteName)
              .append(lazySuiteDescription, lazySuite.lazySuiteDescription)
              .append(active, lazySuite.active)
              .append(testSuites, lazySuite.testSuites)
              .append(stack, lazySuite.stack)
              .append(global, lazySuite.global)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(lazySuiteId)
              .append(lazySuiteDisplayId)
              .append(lazySuiteName)
              .append(lazySuiteDescription)
              .append(active)
              .append(testSuites)
              .append(stack)
              .append(global)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "LazySuite{" +
              "lazySuiteId=" + lazySuiteId +
              ", lazySuiteDisplayId='" + lazySuiteDisplayId + '\'' +
              ", lazySuiteName='" + lazySuiteName + '\'' +
              ", lazySuiteDescription='" + lazySuiteDescription + '\'' +
              ", active=" + active +
              ", testSuites=" + testSuites +
              ", stack=" + stack +
              ", global=" + global +
              '}';
    }
}

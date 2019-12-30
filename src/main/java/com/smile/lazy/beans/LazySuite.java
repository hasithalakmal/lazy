package com.smile.lazy.beans;

import com.smile.lazy.beans.environment.Environment;
import com.smile.lazy.beans.suite.TestSuite;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LazySuite implements Serializable {

    private int lazySuiteId;
    private String lazySuiteDisplayId;
    private String lazySuiteName;
    private String lazySuiteDescription;
    private Boolean active;
    private List<TestSuite> testSuites;
    private DefaultValues defaultValues;
    private Environment globalEnvironment;
    private Map<String, Environment> environments;

    public LazySuite(int lazySuiteId, String lazySuiteName, DefaultValues defaultValues) {
        this.lazySuiteId = lazySuiteId;
        this.lazySuiteName = lazySuiteName;
        this.defaultValues = defaultValues;
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

    public DefaultValues getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(DefaultValues defaultValues) {
        this.defaultValues = defaultValues;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        LazySuite lazySuite = (LazySuite) o;

        return new EqualsBuilder()
              .append(lazySuiteId, lazySuite.lazySuiteId)
              .append(lazySuiteDisplayId, lazySuite.lazySuiteDisplayId)
              .append(lazySuiteName, lazySuite.lazySuiteName)
              .append(lazySuiteDescription, lazySuite.lazySuiteDescription)
              .append(active, lazySuite.active)
              .append(testSuites, lazySuite.testSuites)
              .append(defaultValues, lazySuite.defaultValues)
              .append(globalEnvironment, lazySuite.globalEnvironment)
              .append(environments, lazySuite.environments)
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
              .append(defaultValues)
              .append(globalEnvironment)
              .append(environments)
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
              ", defaultValues=" + defaultValues +
              ", globalEnvironment=" + globalEnvironment +
              ", environments=" + environments +
              '}';
    }
}

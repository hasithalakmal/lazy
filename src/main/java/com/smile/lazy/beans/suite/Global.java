package com.smile.lazy.beans.suite;

import com.smile.lazy.beans.environment.Environment;
import com.smile.lazy.beans.environment.EnvironmentVariable;
import com.smile.lazy.beans.result.AssertionResultList;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Global implements Serializable {

    private Map<String, EnvironmentVariable> globalEnvironment; // Key, value
    private Map<String, Environment> environments; //environmentDisplayId, environment
    private AssertionResultList assertionResultList;

    public Global() {
    }

    public Global(Map<String, EnvironmentVariable> globalEnvironment) {
        this.globalEnvironment = globalEnvironment;
    }

    public Map<String, EnvironmentVariable> getGlobalEnvironment() {
        if (globalEnvironment == null) {
            globalEnvironment = new HashMap<>();
        }
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

    public AssertionResultList getAssertionResultList() {
        return assertionResultList;
    }

    public void setAssertionResultList(AssertionResultList assertionResultList) {
        this.assertionResultList = assertionResultList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Global global = (Global) o;

        return new EqualsBuilder()
              .append(globalEnvironment, global.globalEnvironment)
              .append(environments, global.environments)
              .append(assertionResultList, global.assertionResultList)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(globalEnvironment)
              .append(environments)
              .append(assertionResultList)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "Global{" +
              "globalEnvironment=" + globalEnvironment +
              ", environments=" + environments +
              ", assertionResultList=" + assertionResultList +
              '}';
    }
}

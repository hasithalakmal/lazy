package com.smile.lazy.beans.environment;

import java.io.Serializable;
import java.util.Map;

public class Environment implements Serializable {

  private int environmentId;
  private String environmentDisplayId;
  private String environmentName;
  private String environmentDescription;
  private Boolean active;
  private Map<String, EnvironmentVariable> environmentVariableMap;

  public int getEnvironmentId() {
    return environmentId;
  }

  public void setEnvironmentId(int environmentId) {
    this.environmentId = environmentId;
  }

  public String getEnvironmentDisplayId() {
    return environmentDisplayId;
  }

  public void setEnvironmentDisplayId(String environmentDisplayId) {
    this.environmentDisplayId = environmentDisplayId;
  }

  public String getEnvironmentName() {
    return environmentName;
  }

  public void setEnvironmentName(String environmentName) {
    this.environmentName = environmentName;
  }

  public String getEnvironmentDescription() {
    return environmentDescription;
  }

  public void setEnvironmentDescription(String environmentDescription) {
    this.environmentDescription = environmentDescription;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public Map<String, EnvironmentVariable> getEnvironmentVariableMap() {
    return environmentVariableMap;
  }

  public void setEnvironmentVariableMap(Map<String, EnvironmentVariable> environmentVariableMap) {
    this.environmentVariableMap = environmentVariableMap;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }

      if (o == null || getClass() != o.getClass()) {
          return false;
      }

    Environment that = (Environment) o;

    return new org.apache.commons.lang3.builder.EqualsBuilder()
        .append(environmentId, that.environmentId)
        .append(environmentDisplayId, that.environmentDisplayId)
        .append(environmentName, that.environmentName)
        .append(environmentDescription, that.environmentDescription)
        .append(active, that.active)
        .append(environmentVariableMap, that.environmentVariableMap)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
        .append(environmentId)
        .append(environmentDisplayId)
        .append(environmentName)
        .append(environmentDescription)
        .append(active)
        .append(environmentVariableMap)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "Environment{" +
        "environmentId=" + environmentId +
        ", environmentDisplayId='" + environmentDisplayId + '\'' +
        ", environmentName='" + environmentName + '\'' +
        ", environmentDescription='" + environmentDescription + '\'' +
        ", active=" + active +
        ", environmentVariableMap=" + environmentVariableMap +
        '}';
  }
}

package com.smile.lazy.beans.suite.Actions;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class VariableCleanAction extends Action implements Serializable {

  private int environmentId;
  private String variableKey;

  public int getEnvironmentId() {
    return environmentId;
  }

  public void setEnvironmentId(int environmentId) {
    this.environmentId = environmentId;
  }

  public String getVariableKey() {
    return variableKey;
  }

  public void setVariableKey(String variableKey) {
    this.variableKey = variableKey;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    VariableCleanAction that = (VariableCleanAction) o;

    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(environmentId, that.environmentId)
        .append(variableKey, that.variableKey)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .appendSuper(super.hashCode())
        .append(environmentId)
        .append(variableKey)
        .toHashCode();
  }

  @Override
  public String
  toString() {
    return "VariableCleanAction{" +
        "environmentId=" + environmentId +
        ", variableKey='" + variableKey + '\'' +
        "} " + super.toString();
  }
}

package com.smile.lazy.beans.suite;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class PreConditions implements Serializable {

  private int preConditionId;
  private String preConditionName;
  private String preConditionDescription;
  private Boolean active;

  public int getPreConditionId() {
    return preConditionId;
  }

  public void setPreConditionId(int preConditionId) {
    this.preConditionId = preConditionId;
  }

  public String getPreConditionName() {
    return preConditionName;
  }

  public void setPreConditionName(String preConditionName) {
    this.preConditionName = preConditionName;
  }

  public String getPreConditionDescription() {
    return preConditionDescription;
  }

  public void setPreConditionDescription(String preConditionDescription) {
    this.preConditionDescription = preConditionDescription;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }

      if (o == null || getClass() != o.getClass()) {
          return false;
      }

    PreConditions that = (PreConditions) o;

    return new EqualsBuilder()
        .append(preConditionId, that.preConditionId)
        .append(preConditionName, that.preConditionName)
        .append(preConditionDescription, that.preConditionDescription)
        .append(active, that.active)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(preConditionId)
        .append(preConditionName)
        .append(preConditionDescription)
        .append(active)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "PreConditions{" +
        "preConditionId=" + preConditionId +
        ", preConditionName='" + preConditionName + '\'' +
        ", preConditionDescription='" + preConditionDescription + '\'' +
        ", active=" + active +
        '}';
  }
}

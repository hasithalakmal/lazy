package com.smile.lazy.beans.suite.assertions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AssertionRuleGroup implements Serializable {

  private int assertionRuleGroupId;
  private String assertionRuleGroupDisplayId;
  private String assertionRuleGroupName;
  private String assertionRuleGroupDescription;
  private Boolean active;
  private List<AssertionRule> assertionRules;

  public AssertionRuleGroup(int assertionRuleGroupId, String assertionRuleGroupName) {
    this.assertionRuleGroupId = assertionRuleGroupId;
    this.assertionRuleGroupName = assertionRuleGroupName;
  }

  public int getAssertionRuleGroupId() {
    return assertionRuleGroupId;
  }

  public void setAssertionRuleGroupId(int assertionRuleGroupId) {
    this.assertionRuleGroupId = assertionRuleGroupId;
  }

  public String getAssertionRuleGroupDisplayId() {
    return assertionRuleGroupDisplayId;
  }

  public void setAssertionRuleGroupDisplayId(String assertionRuleGroupDisplayId) {
    this.assertionRuleGroupDisplayId = assertionRuleGroupDisplayId;
  }

  public String getAssertionRuleGroupName() {
    return assertionRuleGroupName;
  }

  public void setAssertionRuleGroupName(String assertionRuleGroupName) {
    this.assertionRuleGroupName = assertionRuleGroupName;
  }

  public String getAssertionRuleGroupDescription() {
    return assertionRuleGroupDescription;
  }

  public void setAssertionRuleGroupDescription(String assertionRuleGroupDescription) {
    this.assertionRuleGroupDescription = assertionRuleGroupDescription;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public List<AssertionRule> getAssertionRules() {
    if (assertionRules == null) {
      assertionRules = new ArrayList<>();
    }
    return assertionRules;
  }

  public void setAssertionRules(List<AssertionRule> assertionRules) {
    this.assertionRules = assertionRules;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AssertionRuleGroup that = (AssertionRuleGroup) o;

    return new EqualsBuilder()
        .append(assertionRuleGroupId, that.assertionRuleGroupId)
        .append(assertionRuleGroupDisplayId, that.assertionRuleGroupDisplayId)
        .append(assertionRuleGroupName, that.assertionRuleGroupName)
        .append(assertionRuleGroupDescription, that.assertionRuleGroupDescription)
        .append(active, that.active)
        .append(assertionRules, that.assertionRules)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(assertionRuleGroupId)
        .append(assertionRuleGroupDisplayId)
        .append(assertionRuleGroupName)
        .append(assertionRuleGroupDescription)
        .append(active)
        .append(assertionRules)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "AssertionRuleGroup{" +
        "assertionRuleGroupId=" + assertionRuleGroupId +
        ", assertionRuleGroupDisplayId='" + assertionRuleGroupDisplayId + '\'' +
        ", assertionRuleGroupName='" + assertionRuleGroupName + '\'' +
        ", assertionRuleGroupDescription='" + assertionRuleGroupDescription + '\'' +
        ", active=" + active +
        ", assertionRules=" + assertionRules +
        '}';
  }
}

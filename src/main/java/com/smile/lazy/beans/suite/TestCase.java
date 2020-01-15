package com.smile.lazy.beans.suite;

import com.smile.lazy.beans.suite.actions.Action;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TestCase implements Serializable {

  private Integer testCaseId;
  private String testCaseDisplayId;
  private String testCaseName;
  private String testCaseDescription;
  private Boolean active;
  private List<ApiCall> apiCalls;
  private List<Action> preActions;
  private List<Action> postActions;

  public TestCase(String testCaseDisplayId, String testCaseName) {
    this.testCaseDisplayId = testCaseDisplayId;
    this.testCaseName = testCaseName;
  }

  public Integer getTestCaseId() {
    return testCaseId;
  }

  public void setTestCaseId(Integer testCaseId) {
    this.testCaseId = testCaseId;
  }

  public String getTestCaseDisplayId() {
    return testCaseDisplayId;
  }

  public void setTestCaseDisplayId(String testCaseDisplayId) {
    this.testCaseDisplayId = testCaseDisplayId;
  }

  public String getTestCaseName() {
    return testCaseName;
  }

  public void setTestCaseName(String testCaseName) {
    this.testCaseName = testCaseName;
  }

  public String getTestCaseDescription() {
    return testCaseDescription;
  }

  public void setTestCaseDescription(String testCaseDescription) {
    this.testCaseDescription = testCaseDescription;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public List<ApiCall> getApiCalls() {
    if (apiCalls == null) {
      apiCalls = new ArrayList<>();
    }
    return apiCalls;
  }

  public void setApiCalls(List<ApiCall> apiCalls) {
    this.apiCalls = apiCalls;
  }

  public List<Action> getPreActions() {
    return preActions;
  }

  public void setPreActions(List<Action> preActions) {
    this.preActions = preActions;
  }

  public List<Action> getPostActions() {
    return postActions;
  }

  public void setPostActions(List<Action> postActions) {
    this.postActions = postActions;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    TestCase testCase = (TestCase) o;

    return new EqualsBuilder()
        .append(testCaseId, testCase.testCaseId)
        .append(testCaseDisplayId, testCase.testCaseDisplayId)
        .append(testCaseName, testCase.testCaseName)
        .append(testCaseDescription, testCase.testCaseDescription)
        .append(active, testCase.active)
        .append(apiCalls, testCase.apiCalls)
        .append(preActions, testCase.preActions)
        .append(postActions, testCase.postActions)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(testCaseId)
        .append(testCaseDisplayId)
        .append(testCaseName)
        .append(testCaseDescription)
        .append(active)
        .append(apiCalls)
        .append(preActions)
        .append(postActions)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "TestCase{" +
        "testCaseId=" + testCaseId +
        ", testCaseDisplayId='" + testCaseDisplayId + '\'' +
        ", testCaseName='" + testCaseName + '\'' +
        ", testCaseDescription='" + testCaseDescription + '\'' +
        ", active=" + active +
        ", apiCalls=" + apiCalls +
        ", preActions=" + preActions +
        ", postActions=" + postActions +
        '}';
  }
}

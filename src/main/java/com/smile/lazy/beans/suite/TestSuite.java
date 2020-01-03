package com.smile.lazy.beans.suite;

import com.smile.lazy.beans.suite.Actions.Action;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TestSuite implements Serializable {

  private int testSuiteId;
  private String testSuiteDisplayId;
  private String testSuiteName;
  private String testSuiteDescription;
  private Boolean active;
  private List<TestScenario> testScenarios;
  private List<Action> preActions;
  private List<Action> postActions;

  public TestSuite(int testSuiteId, String testSuiteName) {
    this.testSuiteId = testSuiteId;
    this.testSuiteName = testSuiteName;
  }

  public int getTestSuiteId() {
    return testSuiteId;
  }

  public void setTestSuiteId(int testSuiteId) {
    this.testSuiteId = testSuiteId;
  }

  public String getTestSuiteDisplayId() {
    return testSuiteDisplayId;
  }

  public void setTestSuiteDisplayId(String testSuiteDisplayId) {
    this.testSuiteDisplayId = testSuiteDisplayId;
  }

  public String getTestSuiteName() {
    return testSuiteName;
  }

  public void setTestSuiteName(String testSuiteName) {
    this.testSuiteName = testSuiteName;
  }

  public String getTestSuiteDescription() {
    return testSuiteDescription;
  }

  public void setTestSuiteDescription(String testSuiteDescription) {
    this.testSuiteDescription = testSuiteDescription;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public List<TestScenario> getTestScenarios() {
    if (testScenarios == null) {
      testScenarios = new ArrayList<>();
    }
    return testScenarios;
  }

  public void setTestScenarios(List<TestScenario> testScenarios) {
    this.testScenarios = testScenarios;
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

    TestSuite testSuite = (TestSuite) o;

    return new EqualsBuilder()
        .append(testSuiteId, testSuite.testSuiteId)
        .append(testSuiteDisplayId, testSuite.testSuiteDisplayId)
        .append(testSuiteName, testSuite.testSuiteName)
        .append(testSuiteDescription, testSuite.testSuiteDescription)
        .append(active, testSuite.active)
        .append(testScenarios, testSuite.testScenarios)
        .append(preActions, testSuite.preActions)
        .append(postActions, testSuite.postActions)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(testSuiteId)
        .append(testSuiteDisplayId)
        .append(testSuiteName)
        .append(testSuiteDescription)
        .append(active)
        .append(testScenarios)
        .append(preActions)
        .append(postActions)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "TestSuite{" +
        "testSuiteId=" + testSuiteId +
        ", testSuiteDisplayId='" + testSuiteDisplayId + '\'' +
        ", testSuiteName='" + testSuiteName + '\'' +
        ", testSuiteDescription='" + testSuiteDescription + '\'' +
        ", active=" + active +
        ", testScenarios=" + testScenarios +
        ", preActions=" + preActions +
        ", postActions=" + postActions +
        '}';
  }
}

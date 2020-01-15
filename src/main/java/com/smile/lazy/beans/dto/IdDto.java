package com.smile.lazy.beans.dto;

public class IdDto {

  private Integer testSuiteId;
  private Integer testScenarioId;
  private Integer testCaseId;
  private Integer apiCallId;
  private Integer preActionId;
  private Integer postActionId;
  private Integer assertionRuleGroupId;
  private Integer assertionRuleId;
  private Integer assertionResultId;

  public IdDto() {
    this.testSuiteId = 1;
    this.testScenarioId = 1;
    this.testCaseId = 1;
    this.apiCallId = 1;
    this.preActionId = 1;
    this.postActionId = 1;
    this.assertionRuleGroupId = 1;
    this.assertionRuleId = 1;
    this.assertionResultId = 1;
  }

  public Integer getTestSuiteId() {
    return testSuiteId;
  }

  public void setTestSuiteId(Integer testSuiteId) {
    this.testSuiteId = testSuiteId;
  }

  public Integer getTestScenarioId() {
    return testScenarioId;
  }

  public void setTestScenarioId(Integer testScenarioId) {
    this.testScenarioId = testScenarioId;
  }

  public Integer getTestCaseId() {
    return testCaseId;
  }

  public void setTestCaseId(Integer testCaseId) {
    this.testCaseId = testCaseId;
  }

  public Integer getApiCallId() {
    return apiCallId;
  }

  public void setApiCallId(Integer apiCallId) {
    this.apiCallId = apiCallId;
  }

  public Integer getPreActionId() {
    return preActionId;
  }

  public void setPreActionId(Integer preActionId) {
    this.preActionId = preActionId;
  }

  public Integer getPostActionId() {
    return postActionId;
  }

  public void setPostActionId(Integer postActionId) {
    this.postActionId = postActionId;
  }

  public Integer getAssertionRuleGroupId() {
    return assertionRuleGroupId;
  }

  public void setAssertionRuleGroupId(Integer assertionRuleGroupId) {
    this.assertionRuleGroupId = assertionRuleGroupId;
  }

  public Integer getAssertionRuleId() {
    return assertionRuleId;
  }

  public void setAssertionRuleId(Integer assertionRuleId) {
    this.assertionRuleId = assertionRuleId;
  }

  public Integer getAssertionResultId() {
    return assertionResultId;
  }

  public void setAssertionResultId(Integer assertionResultId) {
    this.assertionResultId = assertionResultId;
  }

  @Override
  public String toString() {
    return "IdDto{" +
        "testSuiteId=" + testSuiteId +
        ", testScenarioId=" + testScenarioId +
        ", testCaseId=" + testCaseId +
        ", apiCallId=" + apiCallId +
        ", preActionId=" + preActionId +
        ", postActionId=" + postActionId +
        ", assertionRuleGroupId=" + assertionRuleGroupId +
        ", assertionRuleId=" + assertionRuleId +
        ", assertionResultId=" + assertionResultId +
        '}';
  }
}

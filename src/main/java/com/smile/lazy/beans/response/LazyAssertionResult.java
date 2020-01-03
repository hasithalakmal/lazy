package com.smile.lazy.beans.response;

import com.smile.lazy.beans.suite.Assertions.AssertionRule;
import java.io.Serializable;
import java.util.Objects;

public class LazyAssertionResult implements Serializable {

  private int lazyAssertionResultId;
  private String lazyAssertionResultDisplayId;
  private String lazyAssertionResultName;
  private String lazyAssertionResultDescription;
  private Boolean active;
  private AssertionRule assertionRule;
  private Boolean failed;
  private String responseValue;

  public LazyAssertionResult(int lazyAssertionResultId, String lazyAssertionResultName, AssertionRule assertionRule,
      Boolean failed, String responseValue) {
    this.lazyAssertionResultId = lazyAssertionResultId;
    this.lazyAssertionResultName = lazyAssertionResultName;
    this.assertionRule = assertionRule;
    this.failed = failed;
    this.responseValue = responseValue;
  }

  public int getLazyAssertionResultId() {
    return lazyAssertionResultId;
  }

  public void setLazyAssertionResultId(int lazyAssertionResultId) {
    this.lazyAssertionResultId = lazyAssertionResultId;
  }

  public String getLazyAssertionResultDisplayId() {
    return lazyAssertionResultDisplayId;
  }

  public void setLazyAssertionResultDisplayId(String lazyAssertionResultDisplayId) {
    this.lazyAssertionResultDisplayId = lazyAssertionResultDisplayId;
  }

  public String getLazyAssertionResultName() {
    return lazyAssertionResultName;
  }

  public void setLazyAssertionResultName(String lazyAssertionResultName) {
    this.lazyAssertionResultName = lazyAssertionResultName;
  }

  public String getLazyAssertionResultDescription() {
    return lazyAssertionResultDescription;
  }

  public void setLazyAssertionResultDescription(String lazyAssertionResultDescription) {
    this.lazyAssertionResultDescription = lazyAssertionResultDescription;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public AssertionRule getAssertionRule() {
    return assertionRule;
  }

  public void setAssertionRule(AssertionRule assertionRule) {
    this.assertionRule = assertionRule;
  }

  public Boolean getFailed() {
    return failed;
  }

  public void setFailed(Boolean failed) {
    this.failed = failed;
  }

  public String getResponseValue() {
    return responseValue;
  }

  public void setResponseValue(String responseValue) {
    this.responseValue = responseValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LazyAssertionResult that = (LazyAssertionResult) o;
    return lazyAssertionResultId == that.lazyAssertionResultId &&
        Objects.equals(lazyAssertionResultDisplayId, that.lazyAssertionResultDisplayId) &&
        Objects.equals(lazyAssertionResultName, that.lazyAssertionResultName) &&
        Objects.equals(lazyAssertionResultDescription, that.lazyAssertionResultDescription) &&
        Objects.equals(active, that.active) &&
        Objects.equals(assertionRule, that.assertionRule) &&
        Objects.equals(failed, that.failed) &&
        Objects.equals(responseValue, that.responseValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(lazyAssertionResultId, lazyAssertionResultDisplayId, lazyAssertionResultName,
        lazyAssertionResultDescription, active, assertionRule, failed, responseValue);
  }

  @Override
  public String toString() {
    return "LazyAssertionResult{" +
        "lazyAssertionResultId=" + lazyAssertionResultId +
        ", lazyAssertionResultDisplayId='" + lazyAssertionResultDisplayId + '\'' +
        ", lazyAssertionResultName='" + lazyAssertionResultName + '\'' +
        ", lazyAssertionResultDescription='" + lazyAssertionResultDescription + '\'' +
        ", active=" + active +
        ", assertionRule=" + assertionRule +
        ", failed=" + failed +
        ", responseValue='" + responseValue + '\'' +
        '}';
  }
}

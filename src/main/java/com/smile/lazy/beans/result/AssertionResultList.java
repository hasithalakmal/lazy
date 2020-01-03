package com.smile.lazy.beans.result;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class AssertionResultList implements Serializable {

  private int resultListId;
  private String resultListDisplayId;
  private String resultListName;
  private String resultListDescription;
  private Boolean active;
  private List<AssertionResult> results;

  public int getResultListId() {
    return resultListId;
  }

  public void setResultListId(int resultListId) {
    this.resultListId = resultListId;
  }

  public String getResultListDisplayId() {
    return resultListDisplayId;
  }

  public void setResultListDisplayId(String resultListDisplayId) {
    this.resultListDisplayId = resultListDisplayId;
  }

  public String getResultListName() {
    return resultListName;
  }

  public void setResultListName(String resultListName) {
    this.resultListName = resultListName;
  }

  public String getResultListDescription() {
    return resultListDescription;
  }

  public void setResultListDescription(String resultListDescription) {
    this.resultListDescription = resultListDescription;
  }

  public Boolean getActive() {
    return active;
  }

  public void setActive(Boolean active) {
    this.active = active;
  }

  public List<AssertionResult> getResults() {
    if (results == null) {
      results = new ArrayList<>();
    }
    return results;
  }

  public void setResults(List<AssertionResult> results) {
    this.results = results;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AssertionResultList that = (AssertionResultList) o;

    return new EqualsBuilder()
        .append(resultListId, that.resultListId)
        .append(resultListDisplayId, that.resultListDisplayId)
        .append(resultListName, that.resultListName)
        .append(resultListDescription, that.resultListDescription)
        .append(active, that.active)
        .append(results, that.results)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .append(resultListId)
        .append(resultListDisplayId)
        .append(resultListName)
        .append(resultListDescription)
        .append(active)
        .append(results)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "AssertionResultList{" +
        "resultListId=" + resultListId +
        ", resultListDisplayId='" + resultListDisplayId + '\'' +
        ", resultListName='" + resultListName + '\'' +
        ", resultListDescription='" + resultListDescription + '\'' +
        ", active=" + active +
        ", results=" + results +
        '}';
  }
}

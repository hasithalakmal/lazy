package com.smile.lazy.beans.suite.Assertions;

import com.smile.lazy.beans.enums.OperationEnum;
import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ListObjectValueAssertion extends AssertionValue implements Serializable {

  private String jsonPathToList;
  private OperationEnum operationEnum;
  private String valueKey;
  private String matchingCondition;

  public String getJsonPathToList() {
    return jsonPathToList;
  }

  public void setJsonPathToList(String jsonPathToList) {
    this.jsonPathToList = jsonPathToList;
  }

  public OperationEnum getOperationEnum() {
    return operationEnum;
  }

  public void setOperationEnum(OperationEnum operationEnum) {
    this.operationEnum = operationEnum;
  }

  public String getValueKey() {
    return valueKey;
  }

  public void setValueKey(String valueKey) {
    this.valueKey = valueKey;
  }

  public String getMatchingCondition() {
    return matchingCondition;
  }

  public void setMatchingCondition(String matchingCondition) {
    this.matchingCondition = matchingCondition;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }

      if (o == null || getClass() != o.getClass()) {
          return false;
      }

    ListObjectValueAssertion that = (ListObjectValueAssertion) o;

    return new EqualsBuilder()
        .appendSuper(super.equals(o))
        .append(jsonPathToList, that.jsonPathToList)
        .append(operationEnum, that.operationEnum)
        .append(valueKey, that.valueKey)
        .append(matchingCondition, that.matchingCondition)
        .isEquals();
  }

  @Override
  public int hashCode() {
    return new HashCodeBuilder(17, 37)
        .appendSuper(super.hashCode())
        .append(jsonPathToList)
        .append(operationEnum)
        .append(valueKey)
        .append(matchingCondition)
        .toHashCode();
  }

  @Override
  public String toString() {
    return "ListObjectValueAssertion{" +
        "jsonPathToList='" + jsonPathToList + '\'' +
        ", operationEnum=" + operationEnum +
        ", valueKey='" + valueKey + '\'' +
        ", matchingCondition='" + matchingCondition + '\'' +
        "} " + super.toString();
  }
}

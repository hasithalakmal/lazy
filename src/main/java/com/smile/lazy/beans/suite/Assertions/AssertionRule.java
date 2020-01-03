package com.smile.lazy.beans.suite.Assertions;

import com.smile.lazy.beans.enums.AssertionOperationEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class AssertionRule implements Serializable {

    private int assertionRuleId;
    private int assertionRuleSequenceId;
    private String assertionRuleName;
    private String assertionRuleDescription;
    private Boolean active;
    DataSourceEnum dataSource;
    private AssertionOperationEnum assertionOperation;
    private AssertionValue assertionValue;

    public AssertionRule(DataSourceEnum dataSource, AssertionOperationEnum assertionOperation) {
        this.dataSource = dataSource;
        this.assertionOperation = assertionOperation;
    }

    public AssertionRule(int assertionRuleId, DataSourceEnum dataSource, AssertionOperationEnum assertionOperation) {
        this.assertionRuleId = assertionRuleId;
        this.dataSource = dataSource;
        this.assertionOperation = assertionOperation;
    }

    public AssertionRule(DataSourceEnum dataSource, AssertionOperationEnum assertionOperation, AssertionValue assertionValue) {
        this.dataSource = dataSource;
        this.assertionOperation = assertionOperation;
        this.assertionValue = assertionValue;
    }

    public AssertionRule(int assertionRuleId, DataSourceEnum dataSource, AssertionOperationEnum assertionOperation, AssertionValue assertionValue) {
        this.assertionRuleId = assertionRuleId;
        this.dataSource = dataSource;
        this.assertionOperation = assertionOperation;
        this.assertionValue = assertionValue;
    }

    public int getAssertionRuleId() {
        return assertionRuleId;
    }

    public void setAssertionRuleId(int assertionRuleId) {
        this.assertionRuleId = assertionRuleId;
    }

    public int getAssertionRuleSequenceId() {
        return assertionRuleSequenceId;
    }

    public void setAssertionRuleSequenceId(int assertionRuleSequenceId) {
        this.assertionRuleSequenceId = assertionRuleSequenceId;
    }

    public String getAssertionRuleName() {
        return assertionRuleName;
    }

    public void setAssertionRuleName(String assertionRuleName) {
        this.assertionRuleName = assertionRuleName;
    }

    public String getAssertionRuleDescription() {
        return assertionRuleDescription;
    }

    public void setAssertionRuleDescription(String assertionRuleDescription) {
        this.assertionRuleDescription = assertionRuleDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DataSourceEnum getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSourceEnum dataSource) {
        this.dataSource = dataSource;
    }

    public AssertionOperationEnum getAssertionOperation() {
        return assertionOperation;
    }

    public void setAssertionOperation(AssertionOperationEnum assertionOperation) {
        this.assertionOperation = assertionOperation;
    }

    public AssertionValue getAssertionValue() {
        return assertionValue;
    }

    public void setAssertionValue(AssertionValue assertionValue) {
        this.assertionValue = assertionValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        AssertionRule that = (AssertionRule) o;

        return new EqualsBuilder()
              .append(assertionRuleId, that.assertionRuleId)
              .append(assertionRuleSequenceId, that.assertionRuleSequenceId)
              .append(assertionRuleName, that.assertionRuleName)
              .append(assertionRuleDescription, that.assertionRuleDescription)
              .append(active, that.active)
              .append(dataSource, that.dataSource)
              .append(assertionOperation, that.assertionOperation)
              .append(assertionValue, that.assertionValue)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(assertionRuleId)
              .append(assertionRuleSequenceId)
              .append(assertionRuleName)
              .append(assertionRuleDescription)
              .append(active)
              .append(dataSource)
              .append(assertionOperation)
              .append(assertionValue)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "AssertionRule{" +
              "assertionRuleId=" + assertionRuleId +
              ", assertionRuleSequenceId=" + assertionRuleSequenceId +
              ", assertionRuleName='" + assertionRuleName + '\'' +
              ", assertionRuleDescription='" + assertionRuleDescription + '\'' +
              ", active=" + active +
              ", dataSource=" + dataSource +
              ", assertionOperation=" + assertionOperation +
              ", assertionValue=" + assertionValue +
              '}';
    }
}

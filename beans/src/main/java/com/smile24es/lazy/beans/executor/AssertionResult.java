package com.smile24es.lazy.beans.executor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile24es.lazy.beans.enums.AssertionResultStatus;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class AssertionResult implements Serializable {

    private int resultId;
    private String resultDisplayId;
    private String resultName;
    private String resultDescription;
    private Boolean active;
    private AssertionRule assertionRule;
    private String actualValue;
    private Boolean pass;
    private String assertionStatus;
    private String assertionNotes;

    public AssertionResult(int resultId, String actualValue) {
        this.resultId = resultId;
        this.actualValue = actualValue;
    }

    public AssertionResult(int resultId, String actualValue, Boolean pass) {
        this.resultId = resultId;
        this.actualValue = actualValue;
        this.pass = pass;
    }

    public AssertionResult(int resultId, String actualValue, Boolean pass, String assertionStatus) {
        this.resultId = resultId;
        this.actualValue = actualValue;
        this.pass = pass;
        this.assertionStatus = assertionStatus;
    }

    public int getResultId() {
        return resultId;
    }

    public void setResultId(int resultId) {
        this.resultId = resultId;
    }

    public String getResultDisplayId() {
        return resultDisplayId;
    }

    public void setResultDisplayId(String resultDisplayId) {
        this.resultDisplayId = resultDisplayId;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getResultDescription() {
        return resultDescription;
    }

    public void setResultDescription(String resultDescription) {
        this.resultDescription = resultDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getActualValue() {
        return actualValue;
    }

    public void setActualValue(String actualValue) {
        this.actualValue = actualValue;
    }

    public Boolean getPass() {
        if (pass == null) {
            pass = Boolean.FALSE;
        }
        return pass;
    }

    public void setPass(Boolean pass) {
        this.pass = pass;
    }

    public AssertionRule getAssertionRule() {
        return assertionRule;
    }

    public void setAssertionRule(AssertionRule assertionRule) {
        this.assertionRule = assertionRule;
    }

    public String getAssertionStatus() {
        if (assertionStatus == null) {
            assertionStatus = AssertionResultStatus.EXECUTED.getValue();
        }
        return assertionStatus;
    }

    public void setAssertionStatus(String assertionStatus) {
        this.assertionStatus = assertionStatus;
    }

    public String getAssertionNotes() {
        return assertionNotes;
    }

    public void setAssertionNotes(String assertionNotes) {
        this.assertionNotes = assertionNotes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssertionResult that = (AssertionResult) o;

        return new EqualsBuilder()
              .append(resultId, that.resultId)
              .append(resultDisplayId, that.resultDisplayId)
              .append(resultName, that.resultName)
              .append(resultDescription, that.resultDescription)
              .append(active, that.active)
              .append(actualValue, that.actualValue)
              .append(pass, that.pass)
              .append(assertionRule, that.assertionRule)
              .append(assertionStatus, that.assertionStatus)
              .append(assertionNotes, that.assertionNotes)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(resultId)
              .append(resultDisplayId)
              .append(resultName)
              .append(resultDescription)
              .append(active)
              .append(actualValue)
              .append(pass)
              .append(assertionRule)
              .append(assertionStatus)
              .append(assertionNotes)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "AssertionResult{" +
              "resultId=" + resultId +
              ", resultDisplayId='" + resultDisplayId + '\'' +
              ", resultName='" + resultName + '\'' +
              ", resultDescription='" + resultDescription + '\'' +
              ", active=" + active +
              ", actualValue='" + actualValue + '\'' +
              ", pass=" + pass +
              ", assertionRule=" + assertionRule +
              ", assertionStatus=" + assertionStatus +
              ", assertionNotes=" + assertionNotes +
              '}';
    }
}

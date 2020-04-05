package com.smile24es.lazy.beans.suite.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile24es.lazy.beans.enums.ActionTypeEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExecuteAnotherApiCall extends Action implements Serializable {

    private int apiCallId;

    public ExecuteAnotherApiCall(ActionTypeEnum actionType, int apiCallId) {
        super(actionType);
        this.apiCallId = apiCallId;
    }

    public int getApiCallId() {
        return apiCallId;
    }

    public void setApiCallId(int apiCallId) {
        this.apiCallId = apiCallId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ExecuteAnotherApiCall that = (ExecuteAnotherApiCall) o;

        return new EqualsBuilder()
              .appendSuper(super.equals(o))
              .append(apiCallId, that.apiCallId)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .appendSuper(super.hashCode())
              .append(apiCallId)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "ExecuteAnotherApiCall{" +
              "apiCallId=" + apiCallId +
              '}';
    }
}

package com.smile24es.lazy.beans.suite.actions;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile24es.lazy.beans.enums.ActionTypeEnum;
import com.smile24es.lazy.beans.enums.DataTypeEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class VariableCleanAction extends Action implements Serializable {

    private String environmentDisplayId;
    private String variableKey;
    private DataTypeEnum dataType;

    public VariableCleanAction(ActionTypeEnum actionType, String variableKey) {
        super(actionType);
        this.variableKey = variableKey;
    }

    public String getEnvironmentDisplayId() {
        return environmentDisplayId;
    }

    public void setEnvironmentDisplayId(String environmentId) {
        this.environmentDisplayId = environmentId;
    }

    public String getVariableKey() {
        return variableKey;
    }

    public void setVariableKey(String variableKey) {
        this.variableKey = variableKey;
    }

    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        VariableCleanAction that = (VariableCleanAction) o;

        return new EqualsBuilder()
              .appendSuper(super.equals(o))
              .append(environmentDisplayId, that.environmentDisplayId)
              .append(variableKey, that.variableKey)
              .append(dataType, that.dataType)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .appendSuper(super.hashCode())
              .append(environmentDisplayId)
              .append(variableKey)
              .append(dataType)
              .toHashCode();
    }

    @Override
    public String
    toString() {
        return "VariableCleanAction{" +
              "environmentDisplayId=" + environmentDisplayId +
              ", variableKey='" + variableKey + '\'' +
              ", dataType='" + dataType + '\'' +
              "} " + super.toString();
    }
}

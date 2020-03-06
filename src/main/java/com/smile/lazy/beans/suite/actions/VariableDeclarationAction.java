package com.smile.lazy.beans.suite.actions;

import com.smile.lazy.beans.enums.ActionTypeEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class VariableDeclarationAction extends Action implements Serializable {

    private String environmentDisplayId;
    private DataSourceEnum dataSourceEnum;
    private String jsonPath;
    private String variableValue;
    private String variableKey;
    private String dataType;

    public VariableDeclarationAction(ActionTypeEnum actionType, String variableKey) {
        super(actionType);
        this.variableKey = variableKey;
    }

    public VariableDeclarationAction(ActionTypeEnum actionType, String variableKey, String jsonPath) {
        super(actionType);
        this.variableKey = variableKey;
        this.jsonPath = jsonPath;
    }

    public String getEnvironmentDisplayId() {
        return environmentDisplayId;
    }

    public void setEnvironmentDisplayId(String environmentDisplayId) {
        this.environmentDisplayId = environmentDisplayId;
    }

    public DataSourceEnum getDataSourceEnum() {
        return dataSourceEnum;
    }

    public void setDataSourceEnum(DataSourceEnum dataSourceEnum) {
        this.dataSourceEnum = dataSourceEnum;
    }

    public String getJsonPath() {
        return jsonPath;
    }

    public void setJsonPath(String jsonPath) {
        this.jsonPath = jsonPath;
    }

    public String getVariableValue() {
        return variableValue;
    }

    public void setVariableValue(String variableValue) {
        this.variableValue = variableValue;
    }

    public String getVariableKey() {
        return variableKey;
    }

    public void setVariableKey(String variableKey) {
        this.variableKey = variableKey;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
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

        VariableDeclarationAction that = (VariableDeclarationAction) o;

        return new EqualsBuilder()
              .appendSuper(super.equals(o))
              .append(environmentDisplayId, that.environmentDisplayId)
              .append(dataSourceEnum, that.dataSourceEnum)
              .append(jsonPath, that.jsonPath)
              .append(variableValue, that.variableValue)
              .append(variableKey, that.variableKey)
              .append(dataType, that.dataType)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .appendSuper(super.hashCode())
              .append(environmentDisplayId)
              .append(dataSourceEnum)
              .append(jsonPath)
              .append(variableValue)
              .append(variableKey)
              .append(dataType)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "VariableDeclarationAction{" +
              "environmentId=" + environmentDisplayId +
              ", dataSourceEnum=" + dataSourceEnum +
              ", jsonPath='" + jsonPath + '\'' +
              ", variableValue='" + variableValue + '\'' +
              ", variableKey='" + variableKey + '\'' +
              ", dataType='" + dataType + '\'' +
              "} " + super.toString();
    }
}

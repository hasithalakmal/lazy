package com.smile24es.lazy.beans.environment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile24es.lazy.beans.enums.DataTypeEnum;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class EnvironmentVariable implements Serializable {

    private int environmentVariableId;
    private String environmentVariableDisplayId;
    private String environmentVariableName;
    private String environmentVariableDescription;
    private Boolean active;
    private DataTypeEnum dataType;
    private String key;
    private String value;

    public EnvironmentVariable(String value, DataTypeEnum dataType) {
        this.dataType = dataType;
        this.value = value;
    }

    public int getEnvironmentVariableId() {
        return environmentVariableId;
    }

    public void setEnvironmentVariableId(int environmentVariableId) {
        this.environmentVariableId = environmentVariableId;
    }

    public String getEnvironmentVariableDisplayId() {
        return environmentVariableDisplayId;
    }

    public void setEnvironmentVariableDisplayId(String environmentVariableDisplayId) {
        this.environmentVariableDisplayId = environmentVariableDisplayId;
    }

    public String getEnvironmentVariableName() {
        return environmentVariableName;
    }

    public void setEnvironmentVariableName(String environmentVariableName) {
        this.environmentVariableName = environmentVariableName;
    }

    public String getEnvironmentVariableDescription() {
        return environmentVariableDescription;
    }

    public void setEnvironmentVariableDescription(String environmentVariableDescription) {
        this.environmentVariableDescription = environmentVariableDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public DataTypeEnum getDataType() {
        return dataType;
    }

    public void setDataType(DataTypeEnum dataType) {
        this.dataType = dataType;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EnvironmentVariable that = (EnvironmentVariable) o;

        return new EqualsBuilder()
              .append(environmentVariableId, that.environmentVariableId)
              .append(environmentVariableDisplayId, that.environmentVariableDisplayId)
              .append(environmentVariableName, that.environmentVariableName)
              .append(environmentVariableDescription, that.environmentVariableDescription)
              .append(active, that.active)
              .append(dataType, that.dataType)
              .append(key, that.key)
              .append(value, that.value)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(environmentVariableId)
              .append(environmentVariableDisplayId)
              .append(environmentVariableName)
              .append(environmentVariableDescription)
              .append(active)
              .append(dataType)
              .append(key)
              .append(value)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "EnvironmentVariable{" +
              "environmentVariableId=" + environmentVariableId +
              ", environmentVariableDisplayId='" + environmentVariableDisplayId + '\'' +
              ", environmentVariableName='" + environmentVariableName + '\'' +
              ", environmentVariableDescription='" + environmentVariableDescription + '\'' +
              ", active=" + active +
              ", dataType='" + dataType + '\'' +
              ", key='" + key + '\'' +
              ", value='" + value + '\'' +
              '}';
    }
}

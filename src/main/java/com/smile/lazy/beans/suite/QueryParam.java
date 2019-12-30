package com.smile.lazy.beans.suite;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class QueryParam implements Serializable {

    private int queryParamId;
    private String queryParamName;
    private String queryParamDescription;
    private Boolean active;
    private String key;
    private String value;

    public int getQueryParamId() {
        return queryParamId;
    }

    public void setQueryParamId(int queryParamId) {
        this.queryParamId = queryParamId;
    }

    public String getQueryParamName() {
        return queryParamName;
    }

    public void setQueryParamName(String queryParamName) {
        this.queryParamName = queryParamName;
    }

    public String getQueryParamDescription() {
        return queryParamDescription;
    }

    public void setQueryParamDescription(String queryParamDescription) {
        this.queryParamDescription = queryParamDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
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
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        QueryParam that = (QueryParam) o;

        return new EqualsBuilder()
              .append(queryParamId, that.queryParamId)
              .append(queryParamName, that.queryParamName)
              .append(queryParamDescription, that.queryParamDescription)
              .append(active, that.active)
              .append(key, that.key)
              .append(value, that.value)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(queryParamId)
              .append(queryParamName)
              .append(queryParamDescription)
              .append(active)
              .append(key)
              .append(value)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "QueryParam{" +
              "queryParamId=" + queryParamId +
              ", queryParamName='" + queryParamName + '\'' +
              ", queryParamDescription='" + queryParamDescription + '\'' +
              ", active=" + active +
              ", key='" + key + '\'' +
              ", value='" + value + '\'' +
              '}';
    }
}

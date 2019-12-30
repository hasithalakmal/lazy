package com.smile.lazy.beans.suite;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

public class Header implements Serializable {

    private int headerId;
    private String headerName;
    private String headerDescription;
    private Boolean active;
    private String key;
    private String value;

    public Header(int headerId, String headerName, String key, String value) {
        this.headerId = headerId;
        this.headerName = headerName;
        this.key = key;
        this.value = value;
    }

    public int getHeaderId() {
        return headerId;
    }

    public void setHeaderId(int headerId) {
        this.headerId = headerId;
    }

    public String getHeaderName() {
        return headerName;
    }

    public void setHeaderName(String headerName) {
        this.headerName = headerName;
    }

    public String getHeaderDescription() {
        return headerDescription;
    }

    public void setHeaderDescription(String headerDescription) {
        this.headerDescription = headerDescription;
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

        Header header = (Header) o;

        return new EqualsBuilder()
              .append(headerId, header.headerId)
              .append(headerName, header.headerName)
              .append(headerDescription, header.headerDescription)
              .append(active, header.active)
              .append(key, header.key)
              .append(value, header.value)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(headerId)
              .append(headerName)
              .append(headerDescription)
              .append(active)
              .append(key)
              .append(value)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "Header{" +
              "headerId=" + headerId +
              ", headerName='" + headerName + '\'' +
              ", headerDescription='" + headerDescription + '\'' +
              ", active=" + active +
              ", key='" + key + '\'' +
              ", value='" + value + '\'' +
              '}';
    }
}

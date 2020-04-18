package com.smile24es.lazy.beans.suite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class HeaderGroup implements Serializable {

    private int headerGroupId;
    private String headerGroupName;
    private String headerGroupDescription;
    private Boolean active;
    private List<Header> headers;

    public HeaderGroup() {
    }

    public HeaderGroup(String headerGroupName) {
        this.headerGroupName = headerGroupName;
    }

    public int getHeaderGroupId() {
        return headerGroupId;
    }

    public void setHeaderGroupId(int headerGroupId) {
        this.headerGroupId = headerGroupId;
    }

    public String getHeaderGroupName() {
        return headerGroupName;
    }

    public void setHeaderGroupName(String headerGroupName) {
        this.headerGroupName = headerGroupName;
    }

    public String getHeaderGroupDescription() {
        return headerGroupDescription;
    }

    public void setHeaderGroupDescription(String headerGroupDescription) {
        this.headerGroupDescription = headerGroupDescription;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Header> getHeaders() {
        if (headers == null) {
            headers = new ArrayList<Header>();
        }
        return headers;
    }

    public void setHeaders(List<Header> headers) {
        this.headers = headers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HeaderGroup that = (HeaderGroup) o;

        return new EqualsBuilder()
              .append(headerGroupId, that.headerGroupId)
              .append(headerGroupName, that.headerGroupName)
              .append(headerGroupDescription, that.headerGroupDescription)
              .append(active, that.active)
              .append(headers, that.headers)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(headerGroupId)
              .append(headerGroupName)
              .append(headerGroupDescription)
              .append(active)
              .append(headers)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "HeaderGroup{" +
              "headerGroupId=" + headerGroupId +
              ", headerGroupName='" + headerGroupName + '\'' +
              ", headerGroupDescription='" + headerGroupDescription + '\'' +
              ", active=" + active +
              ", headers=" + headers +
              '}';
    }
}

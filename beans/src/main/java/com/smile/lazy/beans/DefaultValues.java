package com.smile.lazy.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile.lazy.beans.enums.HttpMethodEnum;
import com.smile.lazy.beans.suite.HeaderGroup;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class DefaultValues implements Serializable {

    private String protocol;
    private String hostName;
    private Integer port;
    private String contextPath;
    private HeaderGroup headerGroup;
    private String httpMethod;

    public DefaultValues(String protocol, String hostName, Integer port, String contextPath, HeaderGroup headerGroup,
                         String httpMethod) {
        this.protocol = protocol;
        this.hostName = hostName;
        this.port = port;
        this.contextPath = contextPath;
        this.headerGroup = headerGroup;
        this.httpMethod = httpMethod;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public HeaderGroup getHeaderGroup() {
        return headerGroup;
    }

    public void setHeaderGroup(HeaderGroup headerGroup) {
        this.headerGroup = headerGroup;
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DefaultValues that = (DefaultValues) o;

        return new EqualsBuilder()
              .append(protocol, that.protocol)
              .append(hostName, that.hostName)
              .append(port, that.port)
              .append(contextPath, that.contextPath)
              .append(headerGroup, that.headerGroup)
              .append(httpMethod, that.httpMethod)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(protocol)
              .append(hostName)
              .append(port)
              .append(contextPath)
              .append(headerGroup)
              .append(httpMethod)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "DefaultValues{" +
              "protocol='" + protocol + '\'' +
              ", hostName='" + hostName + '\'' +
              ", port=" + port +
              ", contextPath='" + contextPath + '\'' +
              ", headerGroup=" + headerGroup +
              ", httpMethod='" + httpMethod + '\'' +
              '}';
    }
}

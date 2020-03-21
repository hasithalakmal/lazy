package com.smile.lazy.beans.executor;

import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.suite.HeaderGroup;
import com.smile.lazy.beans.suite.QueryParam;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.net.URI;
import java.util.List;

public class ApiCallExecutionData {

    private Integer apiCallId;
    private String apiCallDisplayId;
    private String apiCallName;
    private String apiCallDescription;
    private String protocol;
    private String hostName;
    private Integer port;
    private String contextPath;
    private String uri;
    private List<QueryParam> queryParams;
    private HeaderGroup headerGroup;
    private String httpMethod;
    private String requestBody;
    private URI url;
    private LazyApiCallResponse response;

    public Integer getApiCallId() {
        return apiCallId;
    }

    public void setApiCallId(Integer apiCallId) {
        this.apiCallId = apiCallId;
    }

    public String getApiCallDisplayId() {
        return apiCallDisplayId;
    }

    public void setApiCallDisplayId(String apiCallDisplayId) {
        this.apiCallDisplayId = apiCallDisplayId;
    }

    public String getApiCallName() {
        return apiCallName;
    }

    public void setApiCallName(String apiCallName) {
        this.apiCallName = apiCallName;
    }

    public String getApiCallDescription() {
        return apiCallDescription;
    }

    public void setApiCallDescription(String apiCallDescription) {
        this.apiCallDescription = apiCallDescription;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<QueryParam> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(List<QueryParam> queryParams) {
        this.queryParams = queryParams;
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

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public URI getUrl() {
        return url;
    }

    public void setUrl(URI url) {
        this.url = url;
    }

    public LazyApiCallResponse getResponse() {
        return response;
    }

    public void setResponse(LazyApiCallResponse response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ApiCallExecutionData apiCall = (ApiCallExecutionData) o;

        return new EqualsBuilder()
              .append(apiCallId, apiCall.apiCallId)
              .append(apiCallDisplayId, apiCall.apiCallDisplayId)
              .append(apiCallName, apiCall.apiCallName)
              .append(apiCallDescription, apiCall.apiCallDescription)
              .append(protocol, apiCall.protocol)
              .append(hostName, apiCall.hostName)
              .append(port, apiCall.port)
              .append(contextPath, apiCall.contextPath)
              .append(uri, apiCall.uri)
              .append(queryParams, apiCall.queryParams)
              .append(headerGroup, apiCall.headerGroup)
              .append(httpMethod, apiCall.httpMethod)
              .append(requestBody, apiCall.requestBody)
              .append(url, apiCall.url)
              .append(response, apiCall.response)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(apiCallId)
              .append(apiCallDisplayId)
              .append(apiCallName)
              .append(apiCallDescription)
              .append(protocol)
              .append(hostName)
              .append(port)
              .append(contextPath)
              .append(uri)
              .append(queryParams)
              .append(headerGroup)
              .append(httpMethod)
              .append(requestBody)
              .append(url)
              .append(response)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "ApiCall{" +
              "apiCallId=" + apiCallId +
              ", apiCallDisplayId='" + apiCallDisplayId + '\'' +
              ", apiCallName='" + apiCallName + '\'' +
              ", apiCallDescription='" + apiCallDescription + '\'' +
              ", protocol='" + protocol + '\'' +
              ", hostName='" + hostName + '\'' +
              ", port=" + port +
              ", contextPath='" + contextPath + '\'' +
              ", uri='" + uri + '\'' +
              ", queryParams=" + queryParams +
              ", headerGroup=" + headerGroup +
              ", httpMethod='" + httpMethod + '\'' +
              ", requestBody='" + requestBody + '\'' +
              ", url='" + url + '\'' +
              ", response=" + response +
              '}';
    }
}

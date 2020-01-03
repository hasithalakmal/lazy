package com.smile.lazy.beans.suite;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.suite.Actions.Action;
import com.smile.lazy.beans.suite.Assertions.AssertionRuleGroup;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.Serializable;
import java.util.List;

public class ApiCall implements Serializable {

    private int apiCallId;
    private String apiCallDisplayId;
    private String apiCallName;
    private String apiCallDescription;
    private Boolean active;
    private List<Action> preActions;
    private List<Action> postActions;
    private String protocol;
    private String hostName;
    private Integer port;
    private String contextPath;
    private String uri;
    private List<QueryParam> queryParams;
    private HeaderGroup headerGroup;
    private String httpMethod;
    private String requestBody;
    private AssertionRuleGroup defaultAssertionRuleGroup;
    private AssertionRuleGroup assertionRuleGroup;
    private DefaultValues defaultValues;

    public ApiCall(int apiCallId, String apiCallName,  DefaultValues defaultValues, AssertionRuleGroup defaultAssertionRuleGroup) {
        this.apiCallId = apiCallId;
        this.apiCallName = apiCallName;
        this.defaultValues = defaultValues;
        this.defaultAssertionRuleGroup = defaultAssertionRuleGroup;
    }

    public ApiCall(int apiCallId, String apiCallName, DefaultValues defaultValues) {
        this.apiCallId = apiCallId;
        this.apiCallName = apiCallName;
        this.defaultValues = defaultValues;
    }

    public int getApiCallId() {
        return apiCallId;
    }

    public void setApiCallId(int apiCallId) {
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

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<Action> getPreActions() {
        return preActions;
    }

    public void setPreActions(List<Action> preActions) {
        this.preActions = preActions;
    }

    public List<Action> getPostActions() {
        return postActions;
    }

    public void setPostActions(List<Action> postActions) {
        this.postActions = postActions;
    }

    public String getProtocol() {
        if (StringUtils.isBlank(protocol)) {
            protocol = getDefaultValues().getProtocol();
        }
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostName() {
        if (StringUtils.isBlank(hostName)) {
            hostName = getDefaultValues().getHostName();
        }
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        if (port == null) {
            port = getDefaultValues().getPort();
        }
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getContextPath() {
        if (StringUtils.isBlank(contextPath)) {
            contextPath = getDefaultValues().getContextPath();
        }
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
        if (headerGroup == null) {
            headerGroup = getDefaultValues().getHeaderGroup();
        }
        return headerGroup;
    }

    public void setHeaderGroup(HeaderGroup headerGroup) {
        this.headerGroup = headerGroup;
    }

    public String getHttpMethod() {
        if (StringUtils.isBlank(httpMethod)) {
            httpMethod = getDefaultValues().getHttpMethod();
        }
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

    public AssertionRuleGroup getDefaultAssertionRuleGroup() {
        return defaultAssertionRuleGroup;
    }

    public void setDefaultAssertionRuleGroup(AssertionRuleGroup defaultAssertionRuleGroup) {
        this.defaultAssertionRuleGroup = defaultAssertionRuleGroup;
    }

    public AssertionRuleGroup getAssertionRuleGroup() {
        return assertionRuleGroup;
    }

    public void setAssertionRuleGroup(AssertionRuleGroup assertionRuleGroup) {
        this.assertionRuleGroup = assertionRuleGroup;
    }

    public DefaultValues getDefaultValues() {
        return defaultValues;
    }

    public void setDefaultValues(DefaultValues defaultValues) {
        this.defaultValues = defaultValues;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ApiCall apiCall = (ApiCall) o;

        return new EqualsBuilder()
              .append(apiCallId, apiCall.apiCallId)
              .append(apiCallDisplayId, apiCall.apiCallDisplayId)
              .append(apiCallName, apiCall.apiCallName)
              .append(apiCallDescription, apiCall.apiCallDescription)
              .append(active, apiCall.active)
              .append(preActions, apiCall.preActions)
              .append(postActions, apiCall.postActions)
              .append(protocol, apiCall.protocol)
              .append(hostName, apiCall.hostName)
              .append(port, apiCall.port)
              .append(contextPath, apiCall.contextPath)
              .append(uri, apiCall.uri)
              .append(queryParams, apiCall.queryParams)
              .append(headerGroup, apiCall.headerGroup)
              .append(httpMethod, apiCall.httpMethod)
              .append(requestBody, apiCall.requestBody)
              .append(defaultAssertionRuleGroup, apiCall.defaultAssertionRuleGroup)
              .append(assertionRuleGroup, apiCall.assertionRuleGroup)
              .append(defaultValues, apiCall.defaultValues)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(apiCallId)
              .append(apiCallDisplayId)
              .append(apiCallName)
              .append(apiCallDescription)
              .append(active)
              .append(preActions)
              .append(postActions)
              .append(protocol)
              .append(hostName)
              .append(port)
              .append(contextPath)
              .append(uri)
              .append(queryParams)
              .append(headerGroup)
              .append(httpMethod)
              .append(requestBody)
              .append(defaultAssertionRuleGroup)
              .append(assertionRuleGroup)
              .append(defaultValues)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "ApiCall{" +
              "apiCallId=" + apiCallId +
              ", apiCallDisplayId='" + apiCallDisplayId + '\'' +
              ", apiCallName='" + apiCallName + '\'' +
              ", apiCallDescription='" + apiCallDescription + '\'' +
              ", active=" + active +
              ", preActions=" + preActions +
              ", postActions=" + postActions +
              ", protocol='" + protocol + '\'' +
              ", hostName='" + hostName + '\'' +
              ", port='" + port + '\'' +
              ", contextPath='" + contextPath + '\'' +
              ", uri='" + uri + '\'' +
              ", queryParams=" + queryParams +
              ", headerGroup=" + headerGroup +
              ", httpMethod='" + httpMethod + '\'' +
              ", requestBody='" + requestBody + '\'' +
              ", defaultAssertionRuleGroup='" + defaultAssertionRuleGroup + '\'' +
              ", assertionRuleGroup=" + assertionRuleGroup +
              ", defaultValues=" + defaultValues +
              '}';
    }
}

package com.smile24es.lazy.beans.suite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.smile24es.lazy.beans.suite.actions.Action;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import com.smile24es.lazy.beans.suite.assertions.AssertionRuleGroup;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.utils.TemplateUtil;
import com.smile24es.lazy.utils.VariableManipulationUtil;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.text.MessageFormat.format;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class ApiCall implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCall.class);

    private Integer apiCallId;
    private String apiCallDisplayId;
    private String apiCallName;
    private List<String> assignGroups;
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
    private List<AssertionRule> assertionRules;
    private List<String> disabledAssertions;
    private Stack stack;

    public ApiCall(String apiCallName) {
        this.apiCallName = apiCallName;
    }

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

    public List<String> getAssignGroups() {
        return assignGroups;
    }

    public void setAssignGroups(List<String> assignGroups) {
        this.assignGroups = assignGroups;
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
        if (preActions == null) {
            preActions = new ArrayList<>();
        }
        return preActions;
    }

    public void setPreActions(List<Action> preActions) {
        this.preActions = preActions;
    }

    public List<Action> getPostActions() {
        if (postActions == null) {
            postActions = new ArrayList<>();
        }
        return postActions;
    }

    public void setPostActions(List<Action> postActions) {
        this.postActions = postActions;
    }

    public String getProtocol() {
        if (StringUtils.isBlank(protocol) && getStack().getDefaultValues() != null) {
            protocol = getStack().getDefaultValues().getProtocol();
        }
        protocol = VariableManipulationUtil.getVariableValue(protocol, stack);
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHostName() {
        if (StringUtils.isBlank(hostName) && getStack().getDefaultValues() != null) {
            hostName = getStack().getDefaultValues().getHostName();
        }
        hostName = VariableManipulationUtil.getVariableValue(hostName, stack);
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        if (port == null && getStack().getDefaultValues() != null) {
            port = getStack().getDefaultValues().getPort();
        }
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getContextPath() {
        if (StringUtils.isBlank(contextPath) && getStack().getDefaultValues() != null) {
            contextPath = getStack().getDefaultValues().getContextPath();
        }
        contextPath = VariableManipulationUtil.getVariableValue(contextPath, stack);
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getUri() {
        if (stack.getDefaultValues() != null) {
            uri = VariableManipulationUtil.getVariableValue(uri, stack);
        }
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public List<QueryParam> getQueryParams() {
        if (queryParams == null) {
            queryParams = new ArrayList<>();
        }
        return queryParams;
    }

    public void setQueryParams(List<QueryParam> queryParams) {
        this.queryParams = queryParams;
    }

    public void setQueryParams(QueryParam queryParam) {
        getQueryParams().add(queryParam);
    }

    public HeaderGroup getHeaderGroup() {
        if (headerGroup == null && getStack().getDefaultValues() != null) {
            headerGroup = getStack().getDefaultValues().getHeaderGroup();
        }
        return headerGroup;
    }

    public void setHeaderGroup(HeaderGroup headerGroup) {
        this.headerGroup = headerGroup;
    }

    public String getHttpMethod() {
        if (StringUtils.isBlank(httpMethod) && getStack().getDefaultValues() != null) {
            httpMethod = getStack().getDefaultValues().getHttpMethod();
        }
        httpMethod = VariableManipulationUtil.getVariableValue(httpMethod, stack);

        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestBody() {
        requestBody = VariableManipulationUtil.getVariableValue(requestBody, stack);
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public List<AssertionRule> getAssertionRules() {
        if (assertionRules == null) {
            assertionRules = new ArrayList<>();
        }
        return assertionRules;
    }

    public void setAssertionRules(List<AssertionRule> assertionRules) {
        if (this.assertionRules == null || this.assertionRules.isEmpty()) {
            this.assertionRules = assertionRules;
        } else {
            this.assertionRules.addAll(assertionRules);
        }
    }

    public Stack getStack() {
        if (stack == null) {
            this.stack = new Stack();
        }
        return stack;
    }

    public void setStack(Stack stack) {
        this.stack = SerializationUtils.clone(stack);
    }

    public List<String> getDisabledAssertions() {
        if (this.disabledAssertions == null) {
            this.disabledAssertions = new ArrayList<>();
        }
        return disabledAssertions;
    }

    public void setDisabledAssertions(List<String> disabledAssertions) {
        this.disabledAssertions = disabledAssertions;
    }

    public void addAssertionRule(AssertionRule assertionRule) {
        if (this.assertionRules == null || this.assertionRules.isEmpty()) {
            this.assertionRules = new ArrayList<>();
        }
        this.assertionRules.add(assertionRule);
    }

    public void addAssertionGroup(AssertionRuleGroup assertionRuleGroup) {
        if (this.assertionRules == null || this.assertionRules.isEmpty()) {
            this.assertionRules = new ArrayList<>();
        }
        if (assertionRuleGroup != null && !assertionRuleGroup.getAssertionRules().isEmpty()) {
            for (AssertionRule newAssertionRule : assertionRuleGroup.getAssertionRules()) {
                String newAssertionRuleKey = newAssertionRule.getAssertionRuleKey();
                if (StringUtils.isNotBlank(newAssertionRuleKey)) {
                    for (AssertionRule existingRule : this.assertionRules) {
                        String existingAssertionRuleKey = existingRule.getAssertionRuleKey();
                        if (StringUtils.isNotBlank(existingAssertionRuleKey) && newAssertionRuleKey.equals(existingAssertionRuleKey)) {
                            existingRule.setActive(Boolean.FALSE);
                        }
                    }
                }
                this.assertionRules.add(newAssertionRule);
            }
        }
    }

    public void disableAssertion(String assertionKey) {
        if (this.disabledAssertions == null || this.disabledAssertions.isEmpty()) {
            this.disabledAssertions = new ArrayList<>();
        }
        this.disabledAssertions.add(assertionKey);
    }

    public void enableAssertion(String assertionKey) {
        if (this.disabledAssertions == null || this.disabledAssertions.isEmpty()) {
            this.disabledAssertions = new ArrayList<>();
        }
        this.disabledAssertions.remove(assertionKey);
    }

    public void setRequestBodyFromJson(String filePath) throws LazyCoreException {
        if (StringUtils.isBlank(filePath)) {
            String error = "File path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_FILE_PATH, error);
        }
        JSONParser parser = new JSONParser();
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:" + filePath);
        } catch (FileNotFoundException e) {
            String error = format("File not found in class path [{}]", filePath);
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.INVALID_FILE_PATH, error);
        }
        try (Reader reader = new FileReader(file)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            requestBody = jsonObject.toJSONString();
        } catch (IOException e) {
            String error = format("JSON File cannot read [{}]", filePath);
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.FILE_READING_ERROR, error);
        } catch (ParseException e) {
            String error = format("Json parsing exception [{}]", filePath);
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.INVALID_JSON, error);
        }
    }

    public void setRequestBodyFromJsonTemplate(String filePath, Map<String, Object> templateData) throws LazyCoreException {
        if (StringUtils.isBlank(filePath)) {
            String error = "Template file path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_FILE_PATH, error);
        }

        if (CollectionUtils.isEmpty(templateData)) {
            String error = "Template file path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_TEMPLATE_DATA_OBJECT, error);
        }
        String generatedJson = TemplateUtil.manipulateTemplate(filePath, templateData);
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(generatedJson);
        } catch (ParseException e) {
            String error = "Template generated invalid JSON";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_JSON, error);
        }
        requestBody = jsonObject.toJSONString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ApiCall apiCall = (ApiCall) o;

        return new EqualsBuilder()
              .append(apiCallId, apiCall.apiCallId)
              .append(apiCallDisplayId, apiCall.apiCallDisplayId)
              .append(apiCallName, apiCall.apiCallName)
              .append(assignGroups, apiCall.assignGroups)
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
              .append(stack, apiCall.stack)
              .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
              .append(apiCallId)
              .append(apiCallDisplayId)
              .append(apiCallName)
              .append(assignGroups)
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
              .append(stack)
              .toHashCode();
    }

    @Override
    public String toString() {
        return "ApiCall{" +
              "apiCallId=" + apiCallId +
              ", apiCallDisplayId='" + apiCallDisplayId + '\'' +
              ", apiCallName='" + apiCallName + '\'' +
              ", assignGroups='" + assignGroups + '\'' +
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
              ", stack=" + stack +
              '}';
    }
}

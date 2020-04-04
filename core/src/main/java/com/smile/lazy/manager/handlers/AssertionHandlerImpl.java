package com.smile.lazy.manager.handlers;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.enums.AssertionOperationEnum;
import com.smile.lazy.beans.enums.AssertionResultStatus;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.executor.ApiCallExecutionData;
import com.smile.lazy.beans.executor.AssertionExecutionData;
import com.smile.lazy.beans.executor.AssertionResult;
import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionValue;
import com.smile.lazy.beans.suite.assertions.BodyValueAssertion;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.utils.JsonUtil;
import com.smile.lazy.utils.VariableManipulationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import static java.lang.Boolean.FALSE;
import static java.text.MessageFormat.format;

@Repository
public class AssertionHandlerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssertionHandlerImpl.class);

    public void executeAllAssertions(ApiCall apiCall, IdDto idDto, LazyApiCallResponse lazyApiCallResponse,
                                     ApiCallExecutionData apiCallExecutionData) throws LazyException, LazyCoreException {

        executeGlobalAssertions(apiCall, idDto, lazyApiCallResponse, apiCallExecutionData);

        executeLocalAssertions(apiCall, idDto, lazyApiCallResponse, apiCallExecutionData);
    }

    private void executeLocalAssertions(ApiCall apiCall, IdDto idDto, LazyApiCallResponse lazyApiCallResponse,
                                        ApiCallExecutionData apiCallExecutionData) throws LazyException, LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to execute all api call [{}] assertion rules...", apiCall.getApiCallName());
        }
        for (AssertionRule assertionRule : apiCall.getAssertionRules()) {

            validateAssertionRule(assertionRule);

            String assertionRuleName = VariableManipulationUtil.getVariableValue(assertionRule.getAssertionRuleName(), apiCall.getStack());
            assertionRule.setAssertionRuleName(assertionRuleName);
            LOGGER.debug("Preparing to execute assertion rule - [{}]", assertionRuleName);

            if (validateAssertionEnablement(apiCall, assertionRule)) {
                LOGGER.info("Skipped assertion rule [{}] since it is disabled", assertionRuleName);
                continue;
            }

            populateAssertionRuleId(idDto, assertionRule, assertionRuleName);

            AssertionExecutionData assertionExecutionData = executeAssertion(apiCall, idDto, lazyApiCallResponse, assertionRule);

            assertionExecutionData.setScope("LOCAL");
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Set assertion rule - [{}] - scope to LOCAL", assertionRuleName);
            }
            apiCallExecutionData.getAssertionExecutionDataList().add(assertionExecutionData);

            idDto.setAssertionRuleId(idDto.getAssertionRuleId() + 1);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Executed all api call [{}] assertion rules...", apiCall.getApiCallName());
        }
    }

    private void executeGlobalAssertions(ApiCall apiCall, IdDto idDto, LazyApiCallResponse lazyApiCallResponse,
                                         ApiCallExecutionData apiCallExecutionData) throws LazyCoreException, LazyException {
        if (apiCall.getStack().getDefaultAssertions() != null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute all global assertion rules...");
            }
            for (AssertionRule assertionRule : apiCall.getStack().getDefaultAssertions()) {

                validateAssertionRule(assertionRule);

                String assertionRuleName = VariableManipulationUtil.getVariableValue(assertionRule.getAssertionRuleName(), apiCall.getStack());
                assertionRule.setAssertionRuleName(assertionRuleName);
                LOGGER.debug("Preparing to execute assertion rule - [{}]", assertionRuleName);

                if (validateAssertionEnablement(apiCall, assertionRule)) {
                    LOGGER.info("Skipped assertion rule [{}] since it is disabled", assertionRuleName);
                    continue;
                }

                populateAssertionRuleId(idDto, assertionRule, assertionRuleName);

                AssertionExecutionData assertionExecutionData = executeAssertion(apiCall, idDto, lazyApiCallResponse, assertionRule);

                assertionExecutionData.setScope("GLOBAL");
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Set assertion rule - [{}] - scope to GLOBAL", assertionRuleName);
                }
                apiCallExecutionData.getAssertionExecutionDataList().add(assertionExecutionData);

                idDto.setAssertionRuleId(idDto.getAssertionRuleId() + 1);
            }
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Executed all global assertion rules...");
            }
        }
    }

    private void populateAssertionRuleId(IdDto idDto, AssertionRule assertionRule, String assertionRuleName) {
        Integer assertionRuleId = idDto.getAssertionRuleId();
        if (assertionRule.getAssertionRuleId() == null) {
            assertionRule.setAssertionRuleId(assertionRuleId);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No assertion rule id found for [{}] hence add assertion rule Id [{}]", assertionRuleName, assertionRuleId);
            }
        }
    }

    private void validateAssertionRule(AssertionRule assertionRule) throws LazyCoreException, LazyException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to execute assertion rule - [{}]", JsonUtil.getJsonStringFromObject(assertionRule));
        }

        if (assertionRule == null) {
            String error = "Assertion rule should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ASSERTION_RULE, error);
        }

        if (StringUtils.isBlank(assertionRule.getAssertionRuleName())) {
            String error = "Assertion rule name should not be empty";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ASSERTION_RULE, error);
        }
    }

    private boolean validateAssertionEnablement(ApiCall apiCall, AssertionRule assertionRule) {
        if (!apiCall.getDisabledAssertions().isEmpty()
              && StringUtils.isNotBlank(assertionRule.getAssertionRuleKey())
              && apiCall.getDisabledAssertions().contains(assertionRule.getAssertionRuleKey())) {
            LOGGER.info("Skipping assertion rule [{}] since it has disabled", assertionRule.getAssertionRuleKey());
            return true;
        }
        return false;
    }

    private AssertionExecutionData executeAssertion(ApiCall apiCall, IdDto idDto, LazyApiCallResponse lazyApiCallResponse, AssertionRule assertionRule) throws LazyException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Executing assertion rule - [{}]", assertionRule.getAssertionRuleName());
        }
        DataSourceEnum dataSource = assertionRule.getDataSource();
        if (dataSource == null) {
            String error = "Assertion rule data source should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ASSERTION_RULE, error);
        }
        AssertionOperationEnum operation = assertionRule.getAssertionOperation();
        if (operation == null) {
            String error = "Assertion rule operation should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ASSERTION_RULE, error);
        }

        AssertionResult assertionResult = null;
        AssertionExecutionData assertionExecutionData = new AssertionExecutionData();
        Integer assertionResultId = idDto.getAssertionResultId();

        if (lazyApiCallResponse == null) {
            assertionResult = new AssertionResult(assertionResultId, null, false,
                  AssertionResultStatus.SKIPPED.getValue());
        } else if (dataSource == DataSourceEnum.RESPONSE_CODE) {
            assertionResult = responseCodeAssertion(assertionResultId, lazyApiCallResponse, assertionRule, operation);
        } else if (dataSource == DataSourceEnum.RESPONSE_TIME) {
            assertionResult = responseTimeAssertion(assertionResultId, lazyApiCallResponse, assertionRule, operation);
        } else if (dataSource == DataSourceEnum.RESPONSE_CODE_NAME) {
            assertionResult = new AssertionResult(assertionResultId, null, false, AssertionResultStatus.NOT_IMPLEMENTED.getValue());
        } else if (dataSource == DataSourceEnum.RESPONSE_HEADER) {
            assertionResult = new AssertionResult(assertionResultId, null, false, AssertionResultStatus.NOT_IMPLEMENTED.getValue());
        } else if (dataSource == DataSourceEnum.BODY) {
            assertionResult = requestBodyAssertion(assertionResultId, apiCall, lazyApiCallResponse, assertionRule, operation);
        } else {
            assertionResult = assertionResultForInvalidDataSource(assertionRule, operation, assertionResultId);
        }

        assertionResult.setAssertionRule(assertionRule);
        assertionExecutionData.setAssertionResult(assertionResult);

        idDto.setAssertionResultId(assertionResultId + 1);
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Executed assertion rule - [{}]", assertionRule.getAssertionRuleName());
        }
        return assertionExecutionData;
    }

    private AssertionResult assertionResultForInvalidDataSource(AssertionRule assertionRule, AssertionOperationEnum operation, Integer assertionResultId) {
        AssertionResult assertionResult;
        String error = format("Assertion datasource [{0}] is invalid - rule id [{1}] - rule name [{2}]", operation,
              assertionRule.getAssertionRuleId(), assertionRule.getAssertionRuleName());
        LOGGER.error(error);
        assertionResult = new AssertionResult(assertionResultId, null, false, AssertionResultStatus.INVALID_RULE.getValue());
        assertionResult.setResultDescription(error);
        return assertionResult;
    }

    private AssertionResult requestBodyAssertion(Integer assertionResultId, ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
                                                 AssertionRule assertionRule, AssertionOperationEnum operation) {
        String responseBody = lazyApiCallResponse.getResponseBody();
        AssertionResult assertionResult = new AssertionResult(assertionResultId, responseBody);
        AssertionValue expectedValue = assertionRule.getAssertionValue();
        if (expectedValue == null) {
            if (operation == AssertionOperationEnum.NULL) {
                assertionResult.setPass(StringUtils.isBlank(responseBody));
            } else if (operation == AssertionOperationEnum.NOT_NULL) {
                assertionResult.setPass(StringUtils.isNotBlank(responseBody));
            } else {
                assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
            }
        } else {
            requestBodyValueAssertion(assertionRule, apiCall, operation, assertionResult, responseBody, expectedValue);
        }
        return assertionResult;
    }

    private void requestBodyValueAssertion(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation,
                                           AssertionResult assertionResult, String responseBody, AssertionValue assertionValue) {
        if (assertionValue instanceof BodyValueAssertion) {
            BodyValueAssertion bodyValueAssertion = (BodyValueAssertion) assertionValue;
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
            String actualValue = JsonPath.read(document, bodyValueAssertion.getJsonPath());
            String expectedValue = VariableManipulationUtil.getVariableValue(bodyValueAssertion.getExpectedValue1(), apiCall.getStack());

            if (operation == AssertionOperationEnum.NULL) {
                assertionResult.setPass(StringUtils.isBlank(actualValue));
            } else if (operation == AssertionOperationEnum.NOT_NULL) {
                assertionResult.setPass(StringUtils.isNotBlank(actualValue));
            } else if (operation == AssertionOperationEnum.EQUAL) {
                assertionResult.setPass(actualValue.equals(expectedValue));
            } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
                assertionResult.setPass(!actualValue.equals(expectedValue));
            } else if (operation == AssertionOperationEnum.CONTAINS) {
                assertionResult.setPass(actualValue.contains(expectedValue));
            } else if (operation == AssertionOperationEnum.NOT_CONTAINS) {
                assertionResult.setPass(!actualValue.contains(expectedValue));
            } else {
                assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
            }

        } else {
            assertionResultForInvalidAssertionValue(assertionRule, assertionValue, assertionResult);
        }
    }

    private AssertionResult responseTimeAssertion(Integer assertionResultId, LazyApiCallResponse lazyApiCallResponse,
                                                  AssertionRule assertionRule, AssertionOperationEnum operation) {
        AssertionValue assertionValue = assertionRule.getAssertionValue();
        String expectedValue = assertionValue.getExpectedValue1();

        long actualResponseTime = lazyApiCallResponse.getResponseTime();

        AssertionResult assertionResult = new AssertionResult(assertionResultId, Long.toString(actualResponseTime));
        if (operation == AssertionOperationEnum.LESS_THAN) {
            assertionResult.setPass(actualResponseTime < Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.LESS_THAN_OR_EQUAL) {
            assertionResult.setPass(actualResponseTime <= Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.GREATER_THAN) {
            assertionResult.setPass(actualResponseTime > Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.GREATER_THAN_OR_EQUAL) {
            assertionResult.setPass(actualResponseTime >= Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(actualResponseTime == Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(actualResponseTime != Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.BETWEEN) {
            String expectedValue2 = assertionValue.getExpectedValue2();
            assertionResult.setPass(actualResponseTime > Long.parseLong(expectedValue) && actualResponseTime < Long.parseLong(expectedValue2));
        } else {
            assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
        }
        return assertionResult;
    }

    private AssertionResult responseCodeAssertion(Integer assertionResultId, LazyApiCallResponse lazyApiCallResponse,
                                                  AssertionRule assertionRule, AssertionOperationEnum operation) {
        AssertionResult assertionResult;
        AssertionValue assertionValue = assertionRule.getAssertionValue();
        String expectedValue = assertionValue.getExpectedValue1();

        int actualResponseCode = lazyApiCallResponse.getCloseableHttpResponse().getStatusLine().getStatusCode();
        assertionResult = new AssertionResult(assertionResultId, Integer.toString(actualResponseCode));

        if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(actualResponseCode == Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(actualResponseCode != Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.BETWEEN) {
            String expectedValue2 = assertionValue.getExpectedValue2();
            assertionResult.setPass(actualResponseCode > Integer.parseInt(expectedValue) && actualResponseCode < Integer.parseInt(expectedValue2));
        } else {
            assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
        }
        return assertionResult;
    }

    private void assertionResultForInvalidAssertionValue(AssertionRule assertionRule, AssertionValue assertionValue, AssertionResult assertionResult) {
        String error = format("Assertion value [{0}] is invalid - rule id [{1}] - rule name [{2}]", assertionValue.getClass(),
              assertionRule.getAssertionRuleId(), assertionRule.getAssertionRuleName());
        LOGGER.error(error);
        assertionResult.setPass(FALSE);
        assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
        assertionResult.setResultDescription(error);
    }

    private void assertionResultForInvalidOperation(AssertionRule assertionRule, AssertionOperationEnum operation, AssertionResult assertionResult) {
        String error = format("Assertion operation [{0}] is invalid - rule id [{1}] - rule name [{2}]", operation, assertionRule.getAssertionRuleId(),
              assertionRule.getAssertionRuleName());
        LOGGER.error(error);
        assertionResult.setPass(FALSE);
        assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
        assertionResult.setResultDescription(error);
    }
}

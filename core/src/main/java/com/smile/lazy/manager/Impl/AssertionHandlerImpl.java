package com.smile.lazy.manager.Impl;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.enums.AssertionOperationEnum;
import com.smile.lazy.beans.enums.AssertionResultStatus;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.result.AssertionResult;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionValue;
import com.smile.lazy.beans.suite.assertions.BodyValueAssertion;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.utils.VariableManipulationUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
public class AssertionHandlerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssertionHandlerImpl.class);

    public void executeApiCallAssertions(ApiCall apiCall, IdDto idDto, LazyApiCallResponse lazyApiCallResponse, AssertionResultList assertionResultList) throws LazyException {

        if (apiCall.getStack().getDefaultAssertions() != null) {
            for (AssertionRule assertionRule : apiCall.getStack().getDefaultAssertions()) {
                if (validateAssertionEnablement(apiCall, assertionRule)) {
                    continue;
                }
                executeAssertion(apiCall, idDto, lazyApiCallResponse, assertionResultList, assertionRule);
            }
        }

        for (AssertionRule assertionRule : apiCall.getAssertionRules()) {
            if (validateAssertionEnablement(apiCall, assertionRule)) {
                continue;
            }
            executeAssertion(apiCall, idDto, lazyApiCallResponse, assertionResultList, assertionRule);
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

    private void executeAssertion(ApiCall apiCall, IdDto idDto, LazyApiCallResponse lazyApiCallResponse, AssertionResultList assertionResultList,
                                  AssertionRule assertionRule) throws LazyException {
        DataSourceEnum dataSource = assertionRule.getDataSource();
        AssertionOperationEnum operation = assertionRule.getAssertionOperation();
        AssertionResult assertionResult = null;
        Integer assertionResultId = idDto.getAssertionResultId();
        if (lazyApiCallResponse == null) {
            //TODO - Fix result id
            assertionResult = new AssertionResult(assertionResultId, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(), null, false,
                  AssertionResultStatus.SKIPPED.getValue());
        } else if (dataSource == DataSourceEnum.RESPONSE_CODE) {
            assertionResult = responseCodeAssertion(assertionResultId, apiCall, lazyApiCallResponse, assertionRule, operation);
        } else if (dataSource == DataSourceEnum.RESPONSE_TIME) {
            assertionResult = responseTimeAssertion(assertionResultId, apiCall, lazyApiCallResponse, assertionRule, operation);
        } else if (dataSource == DataSourceEnum.RESPONSE_CODE_NAME) {
            assertionResult = new AssertionResult(assertionResultId, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(), null, false,
                  AssertionResultStatus.NOT_IMPLEMENTED.getValue());
        } else if (dataSource == DataSourceEnum.RESPONSE_HEADER) {
            assertionResult = new AssertionResult(assertionResultId, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(), null, false, AssertionResultStatus.NOT_IMPLEMENTED.getValue());
        } else if (dataSource == DataSourceEnum.BODY) {
            assertionResult = requestBodyAssertion(assertionResultId, apiCall, lazyApiCallResponse, assertionRule, operation);
        } else {
            LOGGER.warn("Assertion datasource is not supported");
            throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, "Given dataSource has not supported yet");
        }
        assertionResult.setAssertionRule(assertionRule);
        idDto.setAssertionResultId(assertionResultId+1);
        assertionResultList.getResults().add(assertionResult);
    }

    private AssertionResult requestBodyAssertion(Integer assertionResultId, ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
                                                 AssertionRule assertionRule, AssertionOperationEnum operation) {
        AssertionResult assertionResult;
        String responseBody = lazyApiCallResponse.getResponseBody();
        assertionResult = new AssertionResult(assertionResultId, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(), responseBody);
        AssertionValue assertionValue = assertionRule.getAssertionValue();
        if (assertionValue == null) {
            if (operation == AssertionOperationEnum.NULL) {
                assertionResult.setPass(StringUtils.isBlank(responseBody));
            } else if (operation == AssertionOperationEnum.NOT_NULL) {
                assertionResult.setPass(StringUtils.isNotBlank(responseBody));
            } else {
                LOGGER.warn("Operation is not supported for given assertion values");
            }
        } else {
            requestBodyValueAssertion(apiCall, operation, assertionResult, responseBody, assertionValue);
        }
        return assertionResult;
    }

    private void requestBodyValueAssertion(ApiCall apiCall, AssertionOperationEnum operation, AssertionResult assertionResult,
                                           String responseBody, AssertionValue assertionValue) {
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
                LOGGER.warn("Operation is not supported for given body assertion values");
            }

        } else {
//            String expectedValue = VariableManipulationUtil.getVariableValue(assertionValue.getExpectedValue1(), apiCall.getStack());
//            if (operation == AssertionOperationEnum.EQUAL) {
//                assertionResult.setPass(responseBody.equals(expectedValue));
//            } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
//                assertionResult.setPass(!responseBody.equals(expectedValue));
//            }
        }
    }

    private AssertionResult responseTimeAssertion(Integer assertionResultId, ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
                                                  AssertionRule assertionRule, AssertionOperationEnum operation) {
        AssertionResult assertionResult;
        AssertionValue assertionValue = assertionRule.getAssertionValue();
        String expectedValue = assertionValue.getExpectedValue1();
        long responseTime = lazyApiCallResponse.getResponseTime();
        assertionResult = new AssertionResult(assertionResultId, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(),
              Long.toString(responseTime));
        if (operation == AssertionOperationEnum.LESS_THAN) {
            assertionResult.setPass(responseTime < Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.LESS_THAN_OR_EQUAL) {
            assertionResult.setPass(responseTime <= Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.GREATER_THAN) {
            assertionResult.setPass(responseTime > Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.GREATER_THAN_OR_EQUAL) {
            assertionResult.setPass(responseTime >= Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.BETWEEN) {
            String expectedValue2 = assertionValue.getExpectedValue2();
            assertionResult
                  .setPass(responseTime > Long.parseLong(expectedValue) && responseTime < Long.parseLong(expectedValue2));
        } else {
            LOGGER.warn("Assertion operation is not supported");
        }
        return assertionResult;
    }

    private AssertionResult responseCodeAssertion(Integer assertionResultId, ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
                                                  AssertionRule assertionRule, AssertionOperationEnum operation) {
        AssertionResult assertionResult;
        AssertionValue assertionValue = assertionRule.getAssertionValue();
        String expectedValue = assertionValue.getExpectedValue1();
        int responseCode = lazyApiCallResponse.getCloseableHttpResponse().getStatusLine().getStatusCode();
        assertionResult = new AssertionResult(assertionResultId, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(),
              Integer.toString(responseCode));
        if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(responseCode == Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(responseCode != Integer.parseInt(expectedValue));
        } else if (operation == AssertionOperationEnum.BETWEEN) {
            String expectedValue2 = assertionValue.getExpectedValue2();
            assertionResult
                  .setPass(responseCode > Integer.parseInt(expectedValue) && responseCode < Integer.parseInt(expectedValue2));
        } else {
            LOGGER.warn("Assertion operation is not supported");
        }
        return assertionResult;
    }
}

package com.smile24es.lazy.manager.handlers;

import com.smile24es.lazy.beans.dto.IdDto;
import com.smile24es.lazy.beans.enums.AssertionOperationEnum;
import com.smile24es.lazy.beans.enums.AssertionResultStatus;
import com.smile24es.lazy.beans.enums.DataSourceEnum;
import com.smile24es.lazy.beans.enums.DataTypeEnum;
import com.smile24es.lazy.beans.executor.ApiCallExecutionData;
import com.smile24es.lazy.beans.executor.AssertionExecutionData;
import com.smile24es.lazy.beans.executor.AssertionResult;
import com.smile24es.lazy.beans.response.LazyApiCallResponse;
import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import com.smile24es.lazy.beans.suite.assertions.AssertionValue;
import com.smile24es.lazy.beans.suite.assertions.BodyValueAssertion;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;
import com.smile24es.lazy.utils.JsonPathReaderUtil;
import com.smile24es.lazy.utils.JsonUtil;
import com.smile24es.lazy.utils.VariableManipulationUtil;
import net.minidev.json.JSONArray;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
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
            assertionResult = new AssertionResult(assertionResultId, null, false, AssertionResultStatus.SKIPPED.getValue());
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

    /**
     * The method to handle response body assertion
     *
     * @param assertionResultId
     * @param apiCall
     * @param lazyApiCallResponse
     * @param assertionRule
     * @param operation
     * @return
     */
    private AssertionResult requestBodyAssertion(Integer assertionResultId, ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
                                                 AssertionRule assertionRule, AssertionOperationEnum operation) {
        String responseBody = lazyApiCallResponse.getResponseBody();
        AssertionResult assertionResult = new AssertionResult(assertionResultId, responseBody);
        AssertionValue expectedValue = assertionRule.getAssertionValue();
        if (expectedValue == null) {
            hanldeEntireBodyValueAssertions(assertionRule, operation, responseBody, assertionResult);
        } else {
            if (expectedValue instanceof BodyValueAssertion) {
                BodyValueAssertion bodyValueAssertion = (BodyValueAssertion) expectedValue;
                String jsonPath = bodyValueAssertion.getJsonPath();
                requestBodyValueAssertion(assertionRule, apiCall, operation, assertionResult, responseBody, bodyValueAssertion);
            } else {
                assertionResultForInvalidAssertionValue(assertionRule, expectedValue, assertionResult);
            }
        }
        return assertionResult;
    }

    private void hanldeEntireBodyValueAssertions(AssertionRule assertionRule, AssertionOperationEnum operation, String responseBody, AssertionResult assertionResult) {
        //This is the assertion for entire response body value
        if (operation == AssertionOperationEnum.NULL) {
            assertionResult.setPass(StringUtils.isBlank(responseBody));
        } else if (operation == AssertionOperationEnum.NOT_NULL) {
            assertionResult.setPass(StringUtils.isNotBlank(responseBody));
        } else {
            assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
        }
    }

    /**
     * This method handles response body value related assertions
     *
     * @param assertionRule
     * @param apiCall
     * @param operation
     * @param assertionResult
     * @param responseBody
     * @param bodyValueAssertion
     */
    private void requestBodyValueAssertion(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation,
                                           AssertionResult assertionResult, String responseBody, BodyValueAssertion bodyValueAssertion) {
        String jsonPath = bodyValueAssertion.getJsonPath();
        boolean isEnvironmentVariable = VariableManipulationUtil.isEnvironmentVariable(bodyValueAssertion.getExpectedStringValue1(), apiCall.getStack());
        DataTypeEnum dataType = bodyValueAssertion.getDataType();

        if (dataType == null) {
            handleAssertionsWithoutDataType(assertionRule, apiCall, operation, assertionResult, responseBody, bodyValueAssertion, jsonPath, isEnvironmentVariable);
        } else {
            //Validate provided JSON path
            try {
                JsonPathReaderUtil.getValue(responseBody, jsonPath);
            } catch (Exception ex) {
                String error = format("Cannot read provided path [{0}]", jsonPath);
                LOGGER.error(error);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Exception occurred while reading JSON path.", ex);
                }
                assertionResult.setPass(FALSE);
                assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
                assertionResult.setAssertionNotes(error);
            }
            if (dataType == DataTypeEnum.STRING) {
                handleStringValueAssertion(assertionRule, apiCall, operation, assertionResult, responseBody, bodyValueAssertion, jsonPath, isEnvironmentVariable);
            } else if (dataType == DataTypeEnum.INTEGER) {
                handleIntegerValueAssertion(assertionRule, apiCall, operation, assertionResult, responseBody, bodyValueAssertion, jsonPath, isEnvironmentVariable);
            } else if (dataType == DataTypeEnum.DOUBLE) {
                handleDoubleValueAssertions(assertionRule, apiCall, operation, assertionResult, responseBody, bodyValueAssertion, jsonPath, isEnvironmentVariable);
            } else if (dataType == DataTypeEnum.BOOLEAN) {
                handleBooleanValueAssertion(assertionRule, apiCall, operation, assertionResult, responseBody, bodyValueAssertion, jsonPath, isEnvironmentVariable);
            } else if (dataType == DataTypeEnum.ARRAY) {
                handleArrayValueAssertion(assertionRule, operation, assertionResult, responseBody, bodyValueAssertion, jsonPath);
            } else {
                assertionResult.setPass(FALSE);
                assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
                assertionResult.setAssertionNotes("Provided assertion data type has invalid");
            }
        }
    }

    private void handleArrayValueAssertion(AssertionRule assertionRule, AssertionOperationEnum operation, AssertionResult assertionResult, String responseBody, BodyValueAssertion bodyValueAssertion, String jsonPath) {
        JSONArray actualValues;
        try {
            actualValues = JsonPathReaderUtil.getJSONArray(responseBody, jsonPath);
        } catch (Exception ex) {
            String error = format("Provided JSON path [{0}] not contains a JSONArray value", jsonPath);
            LOGGER.error(error);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(error, ex);
            }
            assertionResult.setPass(FALSE);
            assertionResult.setAssertionStatus(AssertionResultStatus.FAILED.getValue());
            assertionResult.setAssertionNotes(error);
            return;
        }

        List<Object> expectedValues = bodyValueAssertion.getExpectedValueList();
        if (operation == AssertionOperationEnum.NULL) {
            assertionResult.setPass(actualValues == null);
        } else if (operation == AssertionOperationEnum.NOT_NULL) {
            assertionResult.setPass(actualValues != null);
        } else if (actualValues == null) {
            assertionResult.setPass(false);
        } else if (operation == AssertionOperationEnum.CONTAINS_EXACTLY) {
            handleContainsExactly(assertionResult, actualValues, expectedValues);
        } else if (operation == AssertionOperationEnum.CONTAINS_ANY) {
            handleContainsAny(assertionResult, actualValues, expectedValues);
        } else if (operation == AssertionOperationEnum.CONTAINS_ALL) {
            containsAll(assertionResult, actualValues, expectedValues);
        } else {
            assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
        }
    }

    private void containsAll(AssertionResult assertionResult, JSONArray actualValues, List<Object> expectedValues) {
        boolean isPass = true;
        if (actualValues.size() < expectedValues.size()) {
            isPass = false;
        }
        if (isPass) {
            for (Object expectedValue : expectedValues) {
                boolean isExist = false;
                for (Object actualValue : actualValues) {
                    if (expectedValue.equals(actualValue)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    isPass = false;
                    break;
                }
            }
        }
        assertionResult.setPass(isPass);
    }

    private void handleContainsAny(AssertionResult assertionResult, JSONArray actualValues, List<Object> expectedValues) {
        boolean isPass = false;
        for (Object expectedValue : expectedValues) {
            boolean isExist = false;
            for (Object actualValue : actualValues) {
                if (expectedValue.equals(actualValue)) {
                    isExist = true;
                    break;
                }
            }
            if (isExist) {
                isPass = true;
                break;
            }
        }
        assertionResult.setPass(isPass);
    }

    private void handleContainsExactly(AssertionResult assertionResult, JSONArray actualValues, List<Object> expectedValues) {
        boolean isPass = true;
        if (actualValues.size() != expectedValues.size()) {
            isPass = false;
        }
        if (isPass) {
            for (Object expectedValue : expectedValues) {
                boolean isExist = false;
                for (Object actualValue : actualValues) {
                    if (expectedValue.equals(actualValue)) {
                        isExist = true;
                        break;
                    }
                }
                if (!isExist) {
                    isPass = false;
                    break;
                }
            }
        }
        assertionResult.setPass(isPass);
    }

    private void handleBooleanValueAssertion(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation, AssertionResult assertionResult, String responseBody, BodyValueAssertion bodyValueAssertion, String jsonPath, boolean isEnvironmentVariable) {
        Boolean expectedValue;
        if (isEnvironmentVariable) {
            String actualStringValue = VariableManipulationUtil.getVariableValue(bodyValueAssertion.getExpectedStringValue1(), apiCall.getStack());
            try {
                expectedValue = Boolean.parseBoolean(actualStringValue);
            } catch (Exception ex) {
                String error = format("Environment variable [{0}] not contains a BOOLEAN value [{1}]",
                      bodyValueAssertion.getExpectedStringValue1(), actualStringValue);
                LOGGER.error(error);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(error, ex);
                }
                assertionResult.setPass(FALSE);
                assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
                assertionResult.setAssertionNotes(error);
                return;
            }
        } else {
            expectedValue = bodyValueAssertion.getExpectedBooleanValue();
        }
        Boolean actualValue;
        try {
            actualValue = JsonPathReaderUtil.getBoolean(responseBody, jsonPath);
        } catch (Exception ex) {
            String error = format("Provided JSON path [{0}] not contains a BOOLEAN value", jsonPath);
            LOGGER.error(error);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(error, ex);
            }
            assertionResult.setPass(FALSE);
            assertionResult.setAssertionStatus(AssertionResultStatus.FAILED.getValue());
            assertionResult.setAssertionNotes(error);
            return;
        }
        handleBooleanAssertionOperations(assertionRule, operation, assertionResult, expectedValue, actualValue);
    }

    private void handleBooleanAssertionOperations(AssertionRule assertionRule, AssertionOperationEnum operation, AssertionResult assertionResult, Boolean expectedValue, Boolean actualValue) {
        if (operation == AssertionOperationEnum.NULL) {
            assertionResult.setPass(actualValue == null);
        } else if (operation == AssertionOperationEnum.NOT_NULL) {
            assertionResult.setPass(actualValue != null);
        } else if (actualValue == null) {
            assertionResult.setPass(false);
        } else if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(expectedValue.equals(actualValue));
        } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(!expectedValue.equals(actualValue));
        } else {
            assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
        }
    }

    private void handleDoubleValueAssertions(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation, AssertionResult assertionResult, String responseBody, BodyValueAssertion bodyValueAssertion, String jsonPath, boolean isEnvironmentVariable) {
        Double expectedValue;
        if (isEnvironmentVariable) {
            String actualStringValue = VariableManipulationUtil.getVariableValue(bodyValueAssertion.getExpectedStringValue1(), apiCall.getStack());
            try {
                expectedValue = Double.parseDouble(actualStringValue);
            } catch (Exception ex) {
                String error = format("Environment variable [{0}] not contains a DOUBLE value [{1}]",
                      bodyValueAssertion.getExpectedStringValue1(), actualStringValue);
                LOGGER.error(error);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(error, ex);
                }
                assertionResult.setPass(FALSE);
                assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
                assertionResult.setAssertionNotes(error);
                return;
            }
        } else {
            expectedValue = bodyValueAssertion.getExpectedDoubleValue1();
        }
        Double actualValue;
        try {
            actualValue = JsonPathReaderUtil.getDouble(responseBody, jsonPath);
        } catch (Exception ex) {
            String error = format("Provided JSON path [{0}] not contains a DOUBLE value", jsonPath);
            LOGGER.error(error);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(error, ex);
            }
            assertionResult.setPass(FALSE);
            assertionResult.setAssertionStatus(AssertionResultStatus.FAILED.getValue());
            assertionResult.setAssertionNotes(error);
            return;
        }
        handleDoubleAssertionOperations(assertionRule, apiCall, operation, assertionResult, bodyValueAssertion, expectedValue, actualValue);
    }

    private void handleDoubleAssertionOperations(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation, AssertionResult assertionResult, BodyValueAssertion bodyValueAssertion, Double expectedValue, Double actualValue) {
        if (operation == AssertionOperationEnum.NULL) {
            assertionResult.setPass(actualValue == null);
        } else if (operation == AssertionOperationEnum.NOT_NULL) {
            assertionResult.setPass(actualValue != null);
        } else if (actualValue == null) {
            assertionResult.setPass(false);
        } else if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(expectedValue.equals(actualValue));
        } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(!expectedValue.equals(actualValue));
        } else if (operation == AssertionOperationEnum.LESS_THAN) {
            assertionResult.setPass(actualValue < expectedValue);
        } else if (operation == AssertionOperationEnum.LESS_THAN_OR_EQUAL) {
            assertionResult.setPass(actualValue <= expectedValue);
        } else if (operation == AssertionOperationEnum.GREATER_THAN) {
            assertionResult.setPass(actualValue > expectedValue);
        } else if (operation == AssertionOperationEnum.GREATER_THAN_OR_EQUAL) {
            assertionResult.setPass(actualValue >= expectedValue);
        } else if (operation == AssertionOperationEnum.BETWEEN) {
            handleDoubleBetweenOperation(apiCall, assertionResult, bodyValueAssertion, expectedValue, actualValue);
        } else {
            assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
        }
    }

    private void handleDoubleBetweenOperation(ApiCall apiCall, AssertionResult assertionResult, BodyValueAssertion bodyValueAssertion, Double expectedValue, Double actualValue) {
        Double expectedValue2;
        boolean isEnvironmentVariable2 = VariableManipulationUtil.isEnvironmentVariable(bodyValueAssertion.getExpectedStringValue2(), apiCall.getStack());
        if (isEnvironmentVariable2) {
            String actualStringValue2 = VariableManipulationUtil.getVariableValue(bodyValueAssertion.getExpectedStringValue2(), apiCall.getStack());
            try {
                expectedValue2 = Double.parseDouble(actualStringValue2);
            } catch (Exception ex) {
                String error = format("Environment variable [{0}] not contains a DOUBLE value [{1}]",
                      bodyValueAssertion.getExpectedStringValue1(), actualStringValue2);
                LOGGER.error(error);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(error, ex);
                }
                assertionResult.setPass(FALSE);
                assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
                assertionResult.setAssertionNotes(error);
                return;
            }
        } else {
            expectedValue2 = bodyValueAssertion.getExpectedDoubleValue2();
        }

        if (expectedValue2 == null) {
            assertionResult.setPass(false);
        } else {
            assertionResult.setPass(actualValue >= expectedValue && actualValue <= expectedValue2);
        }
    }

    private void handleIntegerValueAssertion(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation, AssertionResult assertionResult, String responseBody, BodyValueAssertion bodyValueAssertion, String jsonPath, boolean isEnvironmentVariable) {
        Integer expectedValue;
        if (isEnvironmentVariable) {
            String actualStringValue = VariableManipulationUtil.getVariableValue(bodyValueAssertion.getExpectedStringValue1(), apiCall.getStack());
            try {
                expectedValue = Integer.parseInt(actualStringValue);
            } catch (Exception ex) {
                String error = format("Environment variable [{0}] not contains a INTEGER value [{1}]",
                      bodyValueAssertion.getExpectedStringValue1(), actualStringValue);
                LOGGER.error(error);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(error, ex);
                }
                assertionResult.setPass(FALSE);
                assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
                assertionResult.setAssertionNotes(error);
                return;
            }
        } else {
            expectedValue = bodyValueAssertion.getExpectedIntegerValue1();
        }
        Integer actualValue;
        try {
            actualValue = JsonPathReaderUtil.getInteger(responseBody, jsonPath);
        } catch (Exception ex) {
            String error = format("Provided JSON path [{0}] not contains a INTEGER value", jsonPath);
            LOGGER.error(error);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(error, ex);
            }
            assertionResult.setPass(FALSE);
            assertionResult.setAssertionStatus(AssertionResultStatus.FAILED.getValue());
            assertionResult.setAssertionNotes(error);
            return;
        }
        handleIntegerAssertionOperations(assertionRule, apiCall, operation, assertionResult, bodyValueAssertion, expectedValue, actualValue);
    }

    private void handleIntegerAssertionOperations(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation, AssertionResult assertionResult, BodyValueAssertion bodyValueAssertion, Integer expectedValue, Integer actualValue) {
        if (operation == AssertionOperationEnum.NULL) {
            assertionResult.setPass(actualValue == null);
        } else if (operation == AssertionOperationEnum.NOT_NULL) {
            assertionResult.setPass(actualValue != null);
        } else if (actualValue == null) {
            assertionResult.setPass(false);
        } else if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(expectedValue.equals(actualValue));
        } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(!expectedValue.equals(actualValue));
        } else if (operation == AssertionOperationEnum.LESS_THAN) {
            assertionResult.setPass(actualValue < expectedValue);
        } else if (operation == AssertionOperationEnum.LESS_THAN_OR_EQUAL) {
            assertionResult.setPass(actualValue <= expectedValue);
        } else if (operation == AssertionOperationEnum.GREATER_THAN) {
            assertionResult.setPass(actualValue > expectedValue);
        } else if (operation == AssertionOperationEnum.GREATER_THAN_OR_EQUAL) {
            assertionResult.setPass(actualValue >= expectedValue);
        } else if (operation == AssertionOperationEnum.BETWEEN) {
            handleIntegerBetweenOperation(apiCall, assertionResult, bodyValueAssertion, expectedValue, actualValue);
        } else {
            assertionResultForInvalidOperation(assertionRule, operation, assertionResult);
        }
    }

    private void handleIntegerBetweenOperation(ApiCall apiCall, AssertionResult assertionResult, BodyValueAssertion bodyValueAssertion, Integer expectedValue, Integer actualValue) {
        Integer expectedValue2;
        boolean isEnvironmentVariable2 = VariableManipulationUtil.isEnvironmentVariable(bodyValueAssertion.getExpectedStringValue2(), apiCall.getStack());
        if (isEnvironmentVariable2) {
            String actualStringValue2 = VariableManipulationUtil.getVariableValue(bodyValueAssertion.getExpectedStringValue2(), apiCall.getStack());
            try {
                expectedValue2 = Integer.parseInt(actualStringValue2);
            } catch (Exception ex) {
                String error = format("Environment variable [{0}] not contains a INTEGER value [{1}]",
                      bodyValueAssertion.getExpectedStringValue1(), actualStringValue2);
                LOGGER.error(error);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(error, ex);
                }
                assertionResult.setPass(FALSE);
                assertionResult.setAssertionStatus(AssertionResultStatus.INVALID_RULE.getValue());
                assertionResult.setAssertionNotes(error);
                return;
            }
        } else {
            expectedValue2 = bodyValueAssertion.getExpectedIntegerValue2();
        }

        if (expectedValue2 == null) {
            assertionResult.setPass(false);
        } else {
            assertionResult.setPass(actualValue >= expectedValue && actualValue <= expectedValue2);
        }
    }

    private void handleStringValueAssertion(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation, AssertionResult assertionResult, String responseBody, BodyValueAssertion bodyValueAssertion, String jsonPath, boolean isEnvironmentVariable) {
        String expectedValue;
        expectedValue = getStringExpectedValue(apiCall, bodyValueAssertion, isEnvironmentVariable);
        String actualValue;
        try {
            actualValue = JsonPathReaderUtil.getString(responseBody, jsonPath);
        } catch (Exception ex) {
            String error = format("Provided JSON path [{0}] not contains a STRING value", jsonPath);
            LOGGER.error(error);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug(error, ex);
            }
            assertionResult.setPass(FALSE);
            assertionResult.setAssertionStatus(AssertionResultStatus.FAILED.getValue());
            assertionResult.setAssertionNotes(error);
            return;
        }
        handleStringAssertionOperations(assertionRule, operation, assertionResult, expectedValue, actualValue);
    }

    private String getStringExpectedValue(ApiCall apiCall, BodyValueAssertion bodyValueAssertion, boolean isEnvironmentVariable) {
        String expectedValue;
        if (isEnvironmentVariable) {
            expectedValue = VariableManipulationUtil.getVariableValue(bodyValueAssertion.getExpectedStringValue1(), apiCall.getStack());
        } else {
            expectedValue = bodyValueAssertion.getExpectedStringValue1();
        }
        return expectedValue;
    }

    private void handleStringAssertionOperations(AssertionRule assertionRule, AssertionOperationEnum operation, AssertionResult assertionResult, String expectedValue, String actualValue) {
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
    }

    private void handleAssertionsWithoutDataType(AssertionRule assertionRule, ApiCall apiCall, AssertionOperationEnum operation, AssertionResult assertionResult, String responseBody, BodyValueAssertion bodyValueAssertion, String jsonPath, boolean isEnvironmentVariable) {
        String expectedValue = getStringExpectedValue(apiCall, bodyValueAssertion, isEnvironmentVariable);
        if (operation == AssertionOperationEnum.IS_KEY_AVAILABLE) {
            try {
                JsonPathReaderUtil.getAnyValueAsString(responseBody, jsonPath);
                assertionResult.setPass(TRUE);
            } catch (Exception ex) {
                assertionResult.setPass(FALSE);
            }
        } else if (operation == AssertionOperationEnum.IS_KEY_NOT_AVAILABLE) {
            try {
                JsonPathReaderUtil.getAnyValueAsString(responseBody, jsonPath);
                assertionResult.setPass(FALSE);
            } catch (Exception ex) {
                assertionResult.setPass(TRUE);
            }
        } else {
            String actualValue;
            try {
                actualValue = JsonPathReaderUtil.getAnyValueAsString(responseBody, jsonPath);
            } catch (Exception ex) {
                String error = format("Provided JSON path [{0}] not contains a STRING value", jsonPath);
                LOGGER.error(error);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug(error, ex);
                }
                assertionResult.setPass(FALSE);
                assertionResult.setAssertionStatus(AssertionResultStatus.FAILED.getValue());
                assertionResult.setAssertionNotes(error);
                return;
            }
            handleStringAssertionOperations(assertionRule, operation, assertionResult, expectedValue, actualValue);
        }
    }

    private AssertionResult responseTimeAssertion(Integer assertionResultId, LazyApiCallResponse lazyApiCallResponse,
                                                  AssertionRule assertionRule, AssertionOperationEnum operation) {
        AssertionValue assertionValue = assertionRule.getAssertionValue();
        String expectedValue = assertionValue.getExpectedStringValue1();

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
            String expectedValue2 = assertionValue.getExpectedStringValue2();
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
        Integer expectedValue = assertionValue.getExpectedIntegerValue1();

        int actualResponseCode = lazyApiCallResponse.getCloseableHttpResponse().getStatusLine().getStatusCode();
        assertionResult = new AssertionResult(assertionResultId, Integer.toString(actualResponseCode));

        if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(actualResponseCode == expectedValue);
        } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(actualResponseCode != expectedValue);
        } else if (operation == AssertionOperationEnum.BETWEEN) {
            Integer expectedValue2 = assertionValue.getExpectedIntegerValue2();
            assertionResult.setPass(actualResponseCode > expectedValue && actualResponseCode < expectedValue2);
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

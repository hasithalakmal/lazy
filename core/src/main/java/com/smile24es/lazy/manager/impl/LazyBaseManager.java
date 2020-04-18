package com.smile24es.lazy.manager.impl;

import com.smile24es.lazy.beans.DefaultValues;
import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.dto.ResultRecodeTo;
import com.smile24es.lazy.beans.dto.ResultSummeryTo;
import com.smile24es.lazy.beans.enums.HttpMethodEnum;
import com.smile24es.lazy.beans.executor.ApiCallExecutionData;
import com.smile24es.lazy.beans.executor.AssertionExecutionData;
import com.smile24es.lazy.beans.executor.AssertionResult;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.beans.executor.TestCaseExecutionData;
import com.smile24es.lazy.beans.executor.TestScenarioExecutionData;
import com.smile24es.lazy.beans.executor.TestSuiteExecutionData;
import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.beans.suite.Header;
import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;
import com.smile24es.lazy.manager.handlers.StackHandler;
import com.smile24es.lazy.utils.JsonUtil;
import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

public class LazyBaseManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(LazyBaseManager.class);

    @Autowired
    private StackHandler stackManager;

    protected void validateDefaultValues(DefaultValues defaultValues) throws LazyException {
        if (defaultValues == null) {
            String error = "Lazy suite default values should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        if (StringUtils.isBlank(defaultValues.getProtocol())
              || StringUtils.isBlank(defaultValues.getHostName())
              || StringUtils.isBlank(defaultValues.getContextPath())
              || StringUtils.isBlank(defaultValues.getHttpMethod())
              || defaultValues.getPort() == null
              || defaultValues.getHeaderGroup() == null
              || CollectionUtils.isEmpty(defaultValues.getHeaderGroup().getHeaders())) {
            String error = "Invalid default values found";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        if (!EnumUtils.isValidEnum(HttpMethodEnum.class, defaultValues.getHttpMethod())) {
            String error = "Invalid http method values found";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        for (Header header : defaultValues.getHeaderGroup().getHeaders()) {
            if (header == null || StringUtils.isBlank(header.getKey()) || StringUtils.isBlank(header.getValue())) {
                String error = "Invalid default header values found";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
            }
        }
    }

    protected void printResultTable(LazyExecutionData lazyExecutionData) throws LazyException {
        ResultSummeryTo assertionResultTo = getResultSummery(lazyExecutionData);
        try {
            String table = assertionResultTo.prettyPrintString();
            LOGGER.info(table);
        } catch (LazyCoreException e) {
            String error = "Invalid results table generated";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.INVALID_TABLE, error);
        }
    }

    private ResultSummeryTo getResultSummery(LazyExecutionData lazyExecutionData) {
        ResultSummeryTo resultSummeryTo = new ResultSummeryTo();
        for (TestSuiteExecutionData testSuiteExecutionData : lazyExecutionData.getTestSuiteExecutionData()) {
            for (TestScenarioExecutionData testScenarioExecutionData : testSuiteExecutionData.getTestScenarioExecutionData()) {
                for (TestCaseExecutionData testCaseExecutionData : testScenarioExecutionData.getTestCaseExecutionDataList()) {
                    for (ApiCallExecutionData apiCallExecutionData : testCaseExecutionData.getApiCallExecutionDataList()) {
                        for (AssertionExecutionData assertionExecutionData : apiCallExecutionData.getAssertionExecutionDataList()) {
                            AssertionResult assertionResult = assertionExecutionData.getAssertionResult();
                            ResultRecodeTo resultRecodeTo = new ResultRecodeTo();
                            resultRecodeTo.setResultId(Integer.toString(assertionResult.getResultId()));
                            resultRecodeTo.setApiCallName(apiCallExecutionData.getApiCallName());
                            resultRecodeTo.setAssertionName(assertionResult.getAssertionRule().getAssertionRuleName());
                            resultRecodeTo.setActualResult(assertionResult.getActualValue());
                            resultRecodeTo.setExpectedResult(assertionResult.getAssertionRule().getAssertionValue() == null ? null :
                                  assertionResult.getAssertionRule().getAssertionValue().getExpectedStringValue1());
                            resultRecodeTo.setStatus(assertionResult.getAssertionStatus());
                            resultRecodeTo.setIsPass(assertionResult.getPass() == null ? "UNKNOWN" : assertionResult.getPass().toString());
                            resultSummeryTo.getResultRecodeToList().add(resultRecodeTo);
                        }
                    }
                }
            }
        }
        return resultSummeryTo;
    }

    protected void mergeStack(TestSuite testSuite, TestScenario testScenario, String testScenarioName) throws LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merging two stacks for [{}] test scenario: parent stack [{}] - child stack [{}]", testScenarioName,
                  testSuite.getStack(), testScenario.getStack());
        }
        testScenario.setStack(stackManager.mergeTwoStacks(testSuite.getStack(), testScenario.getStack()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merged stacks on test scenario level for [{}] : merged [{}]", testScenarioName, testScenario.getStack());
        }
    }

    protected void mergeStack(LazySuite lazySuite, TestSuite testSuite, String testSuiteName) throws LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merging two stacks for [{}] test suite : parent stack [{}] - child stack [{}]", testSuiteName,
                  lazySuite.getStack(), testSuite.getStack());
        }
        testSuite.setStack(stackManager.mergeTwoStacks(lazySuite.getStack(), testSuite.getStack()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merged stacks on test suite level for [{}] : merged [{}]", testSuiteName, testSuite.getStack());
        }
    }

    protected void mergeStack(TestScenario testScenario, TestCase testCase, String testCaseName) throws LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merging two stacks for [{}] test case: parent stack [{}] - child stack [{}]", testCaseName,
                  testScenario.getStack(), testCase.getStack());
        }
        testCase.setStack(stackManager.mergeTwoStacks(testScenario.getStack(), testCase.getStack()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merged stacks on test case level for [{}] : merged [{}]", testCaseName, testCase.getStack());
        }
    }

    protected void mergeStack(TestCase testCase, ApiCall apiCall, String apiCallName) throws LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merging two stacks for [{}] api call: parent stack [{}] - child stack [{}]", apiCallName,
                  testCase.getStack(), apiCall.getStack());
        }
        apiCall.setStack(stackManager.mergeTwoStacks(testCase.getStack(), apiCall.getStack()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merged stacks on test case level for [{}] : merged [{}]", apiCallName, apiCall.getStack());
        }
    }
}

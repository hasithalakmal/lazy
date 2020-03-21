package com.smile.lazy.manager.Impl;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.dto.ResultRecodeTo;
import com.smile.lazy.beans.dto.ResultSummeryTo;
import com.smile.lazy.beans.environment.EnvironmentVariable;
import com.smile.lazy.beans.executor.ApiCallExecutionData;
import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.result.AssertionResult;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.Global;
import com.smile.lazy.beans.suite.Header;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.beans.suite.actions.Action;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.manager.LazyManager;
import com.smile.lazy.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class LazyManagerImpl implements LazyManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManagerImpl.class);

    @Autowired
    AssertionHandlerImpl assertionHandler;

    @Autowired
    private ApiCallHandlerImpl apiCallHandler;

    @Autowired
    private ActionHandlerImpl actionHandler;

    @Autowired
    private StackManagerImpl stackManager;

    @Override
    public AssertionResultList executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException {

        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to execute lazy suite - [{}]", JsonUtil.getJsonStringFromObject(lazySuite));
        }

        if (lazySuite == null) {
            String error = "Null lazy suite";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        String lazySuiteName = lazySuite.getLazySuiteName();
        if (StringUtils.isBlank(lazySuiteName)) {
            String error = "Lazy suite name should not be empty";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        LOGGER.debug("Preparing to execute lazy suite - [{}]", lazySuiteName);

        if (lazySuite.getStack() == null) {
            String error = "Lazy suite stack should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        DefaultValues defaultValues = lazySuite.getStack().getDefaultValues();
        validateDefaultValues(defaultValues);

        if (lazySuite.getGlobal() == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No global object found, hence adding empty global object");
            }
            lazySuite.setGlobal(new Global());
        }

        AssertionResultList assertionResultList = new AssertionResultList();
        LOGGER.info("Ready to execute lazy suite - [{}]", lazySuiteName);
        executeTestSuites(lazySuite, assertionResultList, new IdDto());
        LOGGER.info("Executed lazy suite - [{}]", lazySuiteName);

        ResultSummeryTo assertionResultTo = getResultSummery(assertionResultList);
        try {
            String table = assertionResultTo.prettyPrintString();
            LOGGER.info(table);
        } catch (LazyCoreException e) {
            String error = "Invalid results table generated";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.INVALID_TABLE, error);
        }
        return assertionResultList;
    }

    private ResultSummeryTo getResultSummery(AssertionResultList assertionResultList){
        ResultSummeryTo resultSummeryTo = new ResultSummeryTo();
        for (AssertionResult assertionResult: assertionResultList.getResults()) {
            ResultRecodeTo resultRecodeTo = new ResultRecodeTo();
            resultRecodeTo.setResultId(Integer.toString(assertionResult.getResultId()));
            resultRecodeTo.setAssertionName(assertionResult.getAssertionRule().getAssertionRuleName());
            resultRecodeTo.setActualResult(assertionResult.getActualValue());
            resultRecodeTo.setExpectedResult(assertionResult.getAssertionRule().getAssertionValue() == null ? null :
                  assertionResult.getAssertionRule().getAssertionValue().getExpectedValue1());
            resultRecodeTo.setStatus(assertionResult.getAssertionStatus());
            resultRecodeTo.setIsPass(assertionResult.getPass().toString());
            resultSummeryTo.getResultRecodeToList().add(resultRecodeTo);
        }

        return resultSummeryTo;
    }

    private void validateDefaultValues(DefaultValues defaultValues) throws LazyException {
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
        //TODO - validate http method by using ENUM

        for (Header header : defaultValues.getHeaderGroup().getHeaders()) {
            if (header == null || StringUtils.isBlank(header.getKey()) || StringUtils.isBlank(header.getValue())) {
                String error = "Invalid default header values found";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
            }
        }
    }

    private void executeTestSuites(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto) throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all test suites of lazy suite [{}]...", lazySuite.getLazySuiteName());
        for (TestSuite testSuite : lazySuite.getTestSuites()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute test suite - [{}]", JsonUtil.getJsonStringFromObject(testSuite));
            }

            if (testSuite == null) {
                String error = "Test suite should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
            }

            String testSuiteName = testSuite.getTestSuiteName();
            if (StringUtils.isBlank(testSuiteName)) {
                String error = "Test suite name should not be empty";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
            }
            LOGGER.debug("Preparing to execute test suite - [{}]", testSuiteName);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Merging two stacks for [{}] test suite : parent stack [{}] - child stack [{}]", testSuiteName,
                      JsonUtil.getJsonStringFromObject(lazySuite.getStack()), JsonUtil.getJsonStringFromObject(testSuite.getStack()));
            }
            testSuite.setStack(stackManager.mergeTwoStacks(lazySuite.getStack(), testSuite.getStack()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Merged stacks on test suite level for [{}] : merged [{}]", testSuiteName, JsonUtil.getJsonStringFromObject(testSuite.getStack()));
            }

            Integer testSuiteId = idDto.getTestSuiteId();
            if (testSuite.getTestSuiteId() == null) {
                testSuite.setTestSuiteId(testSuiteId);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("No test suite ID found for [{}] hence add test suite Id [{}]", testSuiteName, testSuite.getTestSuiteId());
                }
            }

            LOGGER.info("Ready to execute test suite - [{}] - [{}]", testSuiteId, testSuiteName);
            executeTestScenarios(lazySuite, assertionResultList, idDto, testSuite);
            LOGGER.info("Executed test suite - [{}] - [{}]", testSuiteId, testSuiteName);
            idDto.setTestSuiteId(testSuiteId + 1);
        }
        LOGGER.debug("Executed all test suites...");
    }

    private void executeTestScenarios(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestSuite testSuite)
          throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all test scenarios...");
        for (TestScenario testScenario : testSuite.getTestScenarios()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute test scenario - [{}]", JsonUtil.getJsonStringFromObject(testScenario));
            }

            if (testScenario == null) {
                String error = "Test scenario should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_SCENARIO, error);
            }

            String testScenarioName = testScenario.getTestScenarioName();
            if (StringUtils.isBlank(testScenarioName)) {
                String error = "Test scenario name should not be empty";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_SCENARIO, error);
            }

            LOGGER.debug("Preparing to execute test scenario - [{}]", testScenarioName);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Merging two stacks for [{}] test scenario: parent stack [{}] - child stack [{}]", testScenarioName,
                      JsonUtil.getJsonStringFromObject(testSuite.getStack()), JsonUtil.getJsonStringFromObject(testScenario.getStack()));
            }
            testScenario.setStack(stackManager.mergeTwoStacks(testSuite.getStack(), testScenario.getStack()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Merged stacks on test scenario level for [{}] : merged [{}]", testScenarioName, JsonUtil.getJsonStringFromObject(testScenario.getStack()));
            }

            Integer testScenarioId = idDto.getTestScenarioId();
            if (testScenario.getTestScenarioId() == null) {
                testScenario.setTestScenarioId(testScenarioId);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("No test scenario ID found for [{}] hence add test suite Id [{}]", testScenarioName, testScenario.getTestScenarioId());
                }
            }

            LOGGER.info("Executing test scenario - [{}] - [{}]", testScenarioId, testScenarioName);
            executeTestCases(lazySuite, assertionResultList, idDto, testScenario);
            LOGGER.info("Executed test scenario - [{}] - [{}]", testScenarioId, testScenarioName);
            idDto.setTestScenarioId(testScenarioId + 1);
        }
        LOGGER.debug("Executed all test scenarios...");
    }

    private void executeTestCases(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestScenario testScenario) throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all test cases...");
        for (TestCase testCase : testScenario.getTestCases()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute test case - [{}]", JsonUtil.getJsonStringFromObject(testCase));
            }

            if (testCase == null) {
                String error = "Test case should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_CASE, error);
            }

            String testCaseName = testCase.getTestCaseName();
            if (StringUtils.isBlank(testCaseName)) {
                String error = "Test case name should not be empty";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_CASE, error);
            }

            LOGGER.debug("Preparing to execute test case - [{}]", testCaseName);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Merging two stacks for [{}] test case: parent stack [{}] - child stack [{}]", testCaseName,
                      JsonUtil.getJsonStringFromObject(testScenario.getStack()), JsonUtil.getJsonStringFromObject(testCase.getStack()));
            }
            testCase.setStack(stackManager.mergeTwoStacks(testScenario.getStack(), testCase.getStack()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Merged stacks on test case level for [{}] : merged [{}]", testCaseName, JsonUtil.getJsonStringFromObject(testCase.getStack()));
            }

            Integer testCaseId = idDto.getTestCaseId();
            if (testCase.getTestCaseId() == null) {
                testCase.setTestCaseId(testCaseId);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("No test scenario ID found for [{}] hence add test suite Id [{}]", testCaseName, testCaseId);
                }
            }

            LOGGER.info("Executing test case - [{}] - [{}]", testCaseId, testCaseName);
            executeApiCalls(lazySuite, assertionResultList, idDto, testCase);
            LOGGER.info("Executed test case - [{}] - [{}]", testCaseId, testCaseName);
            idDto.setTestCaseId(testCaseId + 1);
        }
        LOGGER.info("Executed all test cases...");
    }

    private void executeApiCalls(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestCase testCase) throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all api calls...");
        for (ApiCall apiCall : testCase.getApiCalls()) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute api call - [{}]", JsonUtil.getJsonStringFromObject(testCase));
            }

            if (apiCall == null) {
                String error = "Api call should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_API_CALL, error);
            }

            String apiCallName = apiCall.getApiCallName();
            if (StringUtils.isBlank(apiCallName)) {
                String error = "Api call name should not be empty";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_API_CALL, error);
            }
            LOGGER.debug("Preparing to execute api call - [{}]", apiCallName);

            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Merging two stacks for [{}] api call: parent stack [{}] - child stack [{}]", apiCallName,
                      JsonUtil.getJsonStringFromObject(testCase.getStack()), JsonUtil.getJsonStringFromObject(apiCall.getStack()));
            }
            apiCall.setStack(stackManager.mergeTwoStacks(testCase.getStack(), apiCall.getStack()));
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Merged stacks on test case level for [{}] : merged [{}]", apiCallName, JsonUtil.getJsonStringFromObject(apiCall.getStack()));
            }

            Integer apiCallId = idDto.getApiCallId();
            if (apiCall.getApiCallId() == null) {
                apiCall.setApiCallId(apiCallId);
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("No test scenario ID found for [{}] hence add test api call Id [{}]", apiCallName, apiCallId);
                }
            }

            LOGGER.debug("Executing pre actions of the api call - [{}] - [{}]", apiCallId, apiCallName);
            executePreActions(lazySuite, apiCall);
            LOGGER.debug("Executed pre actions of the api call - [{}] - [{}]", apiCallId, apiCallName);

            //TODO - re-structure global and stack
            Map<String, EnvironmentVariable> globalEnvironment = lazySuite.getGlobal().getGlobalEnvironment();
            apiCall.getStack().setGlobalEnvironment(globalEnvironment);

            List<Action> postActions = apiCall.getPostActions();
            try {
                LOGGER.info("Executing api call - [{}] - [{}]", apiCallId, apiCallName);
                ApiCallExecutionData apiCallExecutionData = new ApiCallExecutionData();
                LazyApiCallResponse response = apiCallHandler.executeApiCall(apiCall, apiCallExecutionData);
                apiCallHandler.printExecutionData(apiCallExecutionData);
                LOGGER.info("Executed api call - [{}] - [{}]", apiCallId, apiCallName);

                LOGGER.debug("Executing assertions of the api call - [{}] - [{}]", apiCallId, apiCallName);
                assertionHandler.executeApiCallAssertions(apiCall, idDto, response, assertionResultList);
                LOGGER.debug("Executed assertions of the api call - [{}] - [{}]", apiCallId, apiCallName);

                LOGGER.debug("Executing post actions of the api call - [{}] - [{}]", apiCallId, apiCallName);
                executedPostAction(lazySuite, postActions, response);
                LOGGER.debug("Executed post actions of the api call - [{}] - [{}]", apiCallId, apiCallName);
            } catch (Exception ex) {
                LOGGER.warn("API call execution failed since skipping the assertion execution");

                LOGGER.debug("Executing assertions of the [failed]  api call - [{}] - [{}]", apiCallId, apiCallName);
                assertionHandler.executeApiCallAssertions(apiCall, idDto, null, assertionResultList);
                LOGGER.debug("Executed assertions of the api [failed]  call - [{}] - [{}]", apiCallId, apiCallName);

                LOGGER.debug("Executing post actions of the [failed] api call - [{}] - [{}]", apiCallId, apiCallName);
                executedPostAction(lazySuite, postActions, null);
                LOGGER.debug("Executed post actions of the [failed] api call - [{}] - [{}]", apiCallId, apiCallName);
            }
            LOGGER.info("Completed execution of api call - [{}] - [{}]", apiCallId, apiCallName);
            idDto.setApiCallId(apiCallId + 1);
        }
        LOGGER.debug("Executed all api cases...");
    }

    private void executedPostAction(LazySuite lazySuite, List<Action> postActions, LazyApiCallResponse response) throws LazyException, LazyCoreException {
        for (Action postAction : postActions) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute pre action - [{}]", JsonUtil.getJsonStringFromObject(postAction));
            } else {
                LOGGER.info("Ready to execute pre action...");
            }
            actionHandler.executePostAction(lazySuite, response, postAction);
        }
    }

    private void executePreActions(LazySuite lazySuite, ApiCall apiCall) throws LazyException, LazyCoreException {
        List<Action> preActions = apiCall.getPreActions();
        for (Action preAction : preActions) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute pre action - [{}]", JsonUtil.getJsonStringFromObject(preAction));
            }
            actionHandler.executePreAction(lazySuite, preAction);
        }
    }
}

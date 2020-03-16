package com.smile.lazy.manager;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.dto.ResultRecodeTo;
import com.smile.lazy.beans.dto.ResultSummeryTo;
import com.smile.lazy.beans.environment.EnvironmentVariable;
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
public class LazyManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManager.class);

    @Autowired
    AssertionHandler assertionHandler;

    @Autowired
    private ApiCallHandler apiCallHandler;

    @Autowired
    private ActionHandler actionHandler;

    @Autowired
    private StackManager stackManager;

    public AssertionResultList executeLazySuite(LazySuite lazySuite) throws LazyException {

        if (lazySuite == null) {
            String error = "Null lazy suite";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        if (lazySuite.getStack() == null) {
            String error = "Lazy suite stack should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        DefaultValues defaultValues = lazySuite.getStack().getDefaultValues();
        validateDefaultValues(defaultValues);

        if (lazySuite.getGlobal() == null) {
            lazySuite.setGlobal(new Global());
        }

        AssertionResultList assertionResultList = new AssertionResultList();
        executeTestSuites(lazySuite, assertionResultList, new IdDto());

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

        for (Header header : defaultValues.getHeaderGroup().getHeaders()) {
            if (header == null || StringUtils.isBlank(header.getKey()) || StringUtils.isBlank(header.getValue())) {
                String error = "Invalid default header values found";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
            }
        }
    }

    private void executeTestSuites(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto) throws LazyException {
        for (TestSuite testSuite : lazySuite.getTestSuites()) {

            if (testSuite == null) {
                String error = "Test suite should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
            }

            testSuite.setStack(stackManager.mergeTwoStacks(lazySuite.getStack(), testSuite.getStack()));

            if (testSuite.getTestSuiteId() == null) {
                testSuite.setTestSuiteId(idDto.getTestSuiteId());
            }

            String testSuiteName = testSuite.getTestSuiteName();
            if (StringUtils.isBlank(testSuiteName)) {
                String error = "Test suite name should not be empty";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
            }

            LOGGER.info("Executing test suite - [{}] - [{}]", idDto.getTestSuiteId(), testSuiteName);
            executeTestScenarios(lazySuite, assertionResultList, idDto, testSuite);
            idDto.setTestSuiteId(idDto.getTestSuiteId() + 1);
        }
    }

    private void executeTestScenarios(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestSuite testSuite)
          throws LazyException {
        for (TestScenario testScenario : testSuite.getTestScenarios()) {
            if (testScenario == null) {
                String error = "Test scenario should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_SCENARIO, error);
            }

            testScenario.setStack(stackManager.mergeTwoStacks(testSuite.getStack(), testScenario.getStack()));
            if (testScenario.getTestScenarioId() == null) {
                testScenario.setTestScenarioId(idDto.getTestScenarioId());
            }

            String testScenarioName = testScenario.getTestScenarioName();
            if (StringUtils.isBlank(testScenarioName)) {
                String error = "Test scenario name should not be empty";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_SCENARIO, error);
            }

            LOGGER.info("Executing test scenario - [{}] - [{}]", idDto.getTestScenarioId(), testScenarioName);
            executeTestCases(lazySuite, assertionResultList, idDto, testScenario);
            idDto.setTestScenarioId(idDto.getTestScenarioId() + 1);
        }
    }

    private void executeTestCases(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestScenario testScenario) throws LazyException {
        for (TestCase testCase : testScenario.getTestCases()) {

            if (testCase == null) {
                String error = "Test case should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_CASE, error);
            }

            testCase.setStack(stackManager.mergeTwoStacks(testScenario.getStack(), testCase.getStack()));

            if (testCase.getTestCaseId() == null) {
                testCase.setTestCaseId(idDto.getTestCaseId());
            }

            String testCaseName = testCase.getTestCaseName();
            if (StringUtils.isBlank(testCaseName)) {
                String error = "Test case name should not be empty";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_CASE, error);
            }

            LOGGER.info("Executing test case - [{}] - [{}]", idDto.getTestCaseId(), testCaseName);
            executeApiCalls(lazySuite, assertionResultList, idDto, testCase);
            idDto.setTestCaseId(idDto.getTestCaseId() + 1);
        }
    }

    private void executeApiCalls(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestCase testCase) throws LazyException {
        for (ApiCall apiCall : testCase.getApiCalls()) {

            if (apiCall == null) {
                String error = "Api call should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_API_CALL, error);
            }

            apiCall.setStack(stackManager.mergeTwoStacks(testCase.getStack(), apiCall.getStack()));

            if (apiCall.getStack() == null) {
                String error = "Api call stack should not be null";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_API_CALL, error);
            }

            if (apiCall.getApiCallId() == null) {
                apiCall.setApiCallId(idDto.getApiCallId());
            }

            String apiCallName = apiCall.getApiCallName();
            if (StringUtils.isBlank(apiCallName)) {
                String error = "Api call name should not be empty";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_API_CALL, error);
            }

            LOGGER.info("Executing api call - [{}] - [{}]", idDto.getApiCallId(), apiCallName);

            List<Action> preActions = apiCall.getPreActions();
            for (Action preAction : preActions) {
                actionHandler.executePreAction(lazySuite, preAction);
            }

            Map<String, EnvironmentVariable> globalEnvironment = lazySuite.getGlobal().getGlobalEnvironment();
            apiCall.getStack().setGlobalEnvironment(globalEnvironment);

            List<Action> postActions = apiCall.getPostActions();
            try {
                LazyApiCallResponse response = apiCallHandler.executeApiCall(apiCall);
                assertionHandler.executeApiCallAssertions(apiCall, response, assertionResultList);
                for (Action postAction : postActions) {
                    actionHandler.executePostAction(lazySuite, response, postAction);
                }
            } catch (Exception ex) {
                LOGGER.warn("API call execution failed since skipping the assertion execution");
                assertionHandler.executeApiCallAssertions(apiCall, null, assertionResultList);
                for (Action postAction : postActions) {
                    actionHandler.executePostAction(lazySuite, null, postAction);
                }
            }

            idDto.setApiCallId(idDto.getApiCallId() + 1);
        }
    }
}

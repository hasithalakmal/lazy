package com.smile.lazy.manager.impl;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.beans.executor.LazyExecutionGroup;
import com.smile.lazy.beans.executor.TestCaseExecutionData;
import com.smile.lazy.beans.executor.TestScenarioExecutionData;
import com.smile.lazy.beans.executor.TestSuiteExecutionData;
import com.smile.lazy.beans.suite.Global;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.manager.ApiCallManager;
import com.smile.lazy.manager.handlers.StackHandler;
import com.smile.lazy.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TestCaseManagerImpl extends LazyBaseManager implements com.smile.lazy.manager.TestCaseManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCaseManagerImpl.class);

    @Autowired
    private StackHandler stackManager;

    @Autowired
    private ApiCallManager apiCallManager;

    @Override
    public void executeTestCases(LazySuite lazySuite, TestScenarioExecutionData testScenarioExecutionData, IdDto idDto, TestScenario testScenario) throws LazyException,
          LazyCoreException {
        executeTestCases(lazySuite, testScenarioExecutionData, idDto, testScenario, null);
    }

    @Override
    public void executeTestCases(LazySuite lazySuite, TestScenarioExecutionData testScenarioExecutionData, IdDto idDto, TestScenario testScenario, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all test cases...");
        for (TestCase testCase : testScenario.getTestCases()) {
            validateTestCase(testCase);
            String testCaseName = testCase.getTestCaseName();

            boolean isFoundInAcceptedList = isFoundInAcceptedList(lazyExecutionGroup, testCase);

            LOGGER.debug("Preparing to execute test case - [{}]", testCaseName);
            mergeStack(testScenario, testCase, testCaseName);
            Integer testCaseId = populateTestCaseId(idDto, testCase, testCaseName);

            LOGGER.info("Executing test case - [{}] - [{}]", testCaseId, testCaseName);
            TestCaseExecutionData testCaseExecutionData = new TestCaseExecutionData(testCaseId, testCaseName);
            if (isFoundInAcceptedList) {
                apiCallManager.executeApiCalls(lazySuite, testCaseExecutionData, idDto, testCase);
            } else {
                apiCallManager.executeApiCalls(lazySuite, testCaseExecutionData, idDto, testCase, lazyExecutionGroup);
            }
            LOGGER.info("Executed test case - [{}] - [{}]", testCaseId, testCaseName);

            testScenarioExecutionData.getTestCaseExecutionDataList().add(testCaseExecutionData);

            idDto.setTestCaseId(testCaseId + 1);
        }
        LOGGER.info("Executed all test cases...");
    }

    private boolean isFoundInAcceptedList(LazyExecutionGroup lazyExecutionGroup, TestCase testCase) {
        boolean isFoundInAcceptedList = false;
        if (lazyExecutionGroup != null && !CollectionUtils.isEmpty(lazyExecutionGroup.getTestCaseExecutionGroupNames()) && !CollectionUtils.isEmpty(testCase.getAssignGroups())) {
            for (String acceptedTestSuiteName : lazyExecutionGroup.getTestCaseExecutionGroupNames()) {
                if (testCase.getAssignGroups().contains(acceptedTestSuiteName)) {
                    LOGGER.info("Find assigned test case - [{}] to execute", acceptedTestSuiteName);
                    isFoundInAcceptedList = true;
                }
            }
        }
        return isFoundInAcceptedList;
    }

    @Override
    public TestCaseExecutionData executeTestCase(TestCase testCase, Stack stack) throws LazyCoreException, LazyException {
        validateTestCase(testCase);
        String testCaseName = testCase.getTestCaseName();
        LOGGER.debug("Preparing to execute test case - [{}]", testCaseName);

        if (stack == null) {
            String error = "Test case stack should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_TEST_SUITE, error);
        }
        validateDefaultValues(stack.getDefaultValues());

        TestScenario testScenario = new TestScenario("Default test scenario");
        testScenario.setStack(stack);
        testScenario.getTestCases().add(testCase);

        TestSuite testSuite = new TestSuite("Default test suite");
        testSuite.setStack(stack);
        testSuite.getTestScenarios().add(testScenario);

        LazySuite lazySuite = new LazySuite("Default Lazy suite", stack);
        lazySuite.setGlobal(new Global());
        lazySuite.setStack(stack);
        lazySuite.getTestSuites().add(testSuite);

        mergeStack(lazySuite, testSuite, testSuite.getTestSuiteName());
        mergeStack(testSuite, testScenario, testScenario.getTestScenarioName());
        mergeStack(testScenario, testCase, testCase.getTestCaseName());
        LOGGER.debug("Populated lazy suite to execute test case - [{}]", testCaseName);

        IdDto idDto = new IdDto();
        Integer testCaseId = populateTestCaseId(idDto, testCase, testCaseName);

        LOGGER.info("Executing test case - [{}] - [{}]", testCaseId, testCaseName);
        TestCaseExecutionData testCaseExecutionData = new TestCaseExecutionData(testCaseId, testCaseName);
        apiCallManager.executeApiCalls(lazySuite, testCaseExecutionData, idDto, testCase);
        LOGGER.info("Executed test case - [{}] - [{}]", testCaseId, testCaseName);

        TestScenarioExecutionData testScenarioExecutionData = new TestScenarioExecutionData(idDto.getTestScenarioId(), testScenario.getTestScenarioName());
        testScenarioExecutionData.getTestCaseExecutionDataList().add(testCaseExecutionData);
        TestSuiteExecutionData testSuiteExecutionData = new TestSuiteExecutionData(idDto.getTestSuiteId(), testSuite.getTestSuiteName());
        testSuiteExecutionData.getTestScenarioExecutionData().add(testScenarioExecutionData);
        LazyExecutionData lazyExecutionData = new LazyExecutionData();
        lazyExecutionData.getTestSuiteExecutionData().add(testSuiteExecutionData);
        printResultTable(lazyExecutionData);

        return testCaseExecutionData;
    }

    private Integer populateTestCaseId(IdDto idDto, TestCase testCase, String testCaseName) {
        Integer testCaseId = idDto.getTestCaseId();
        if (testCase.getTestCaseId() == null) {
            testCase.setTestCaseId(testCaseId);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No test scenario ID found for [{}] hence add test suite Id [{}]", testCaseName, testCaseId);
            }
        }
        return testCaseId;
    }

    private void validateTestCase(TestCase testCase) throws LazyCoreException, LazyException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to execute test case - [{}]", JsonUtil.getJsonStringFromObject(testCase));
        }

        if (testCase == null) {
            String error = "Test case should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_CASE, error);
        }

        if (StringUtils.isBlank(testCase.getTestCaseName())) {
            String error = "Test case name should not be empty";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_CASE, error);
        }
    }

}

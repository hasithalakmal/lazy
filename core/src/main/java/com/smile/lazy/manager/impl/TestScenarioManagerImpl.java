package com.smile.lazy.manager.impl;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.beans.executor.LazyExecutionGroup;
import com.smile.lazy.beans.executor.TestScenarioExecutionData;
import com.smile.lazy.beans.executor.TestSuiteExecutionData;
import com.smile.lazy.beans.suite.Global;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.manager.TestCaseManager;
import com.smile.lazy.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

@Service
public class TestScenarioManagerImpl extends LazyBaseManager implements com.smile.lazy.manager.TestScenarioManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestScenarioManagerImpl.class);

    @Autowired
    private TestCaseManager testCaseManager;

    @Override
    public void executeTestScenarios(LazySuite lazySuite, TestSuiteExecutionData testSuiteExecutionData, IdDto idDto, TestSuite testSuite)
          throws LazyException, LazyCoreException {
        executeTestScenarios(lazySuite, testSuiteExecutionData, idDto, testSuite, null);
    }

    @Override
    public void executeTestScenarios(LazySuite lazySuite, TestSuiteExecutionData testSuiteExecutionData, IdDto idDto, TestSuite testSuite, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all test scenarios...");
        for (TestScenario testScenario : testSuite.getTestScenarios()) {
            validateTestScenario(testScenario);
            String testScenarioName = testScenario.getTestScenarioName();

            boolean isFoundInAcceptedList = isFoundInAcceptedList(lazyExecutionGroup, testScenario);

            LOGGER.debug("Preparing to execute test scenario - [{}]", testScenarioName);
            mergeStack(testSuite, testScenario, testScenarioName);
            Integer testScenarioId = populateTestScenarioId(idDto, testScenario, testScenarioName);

            LOGGER.info("Executing test scenario - [{}] - [{}]", testScenarioId, testScenarioName);
            TestScenarioExecutionData testScenarioExecutionData = new TestScenarioExecutionData(testScenarioId, testScenarioName);
            if (isFoundInAcceptedList) {
                testCaseManager.executeTestCases(lazySuite, testScenarioExecutionData, idDto, testScenario);
            } else {
                testCaseManager.executeTestCases(lazySuite, testScenarioExecutionData, idDto, testScenario, lazyExecutionGroup);
            }
            LOGGER.info("Executed test scenario - [{}] - [{}]", testScenarioId, testScenarioName);

            testSuiteExecutionData.getTestScenarioExecutionData().add(testScenarioExecutionData);

            idDto.setTestScenarioId(testScenarioId + 1);
        }
        LOGGER.debug("Executed all test scenarios...");
    }

    private boolean isFoundInAcceptedList(LazyExecutionGroup lazyExecutionGroup, TestScenario testScenario) {
        boolean isFoundInAcceptedList = false;
        if (lazyExecutionGroup != null && !CollectionUtils.isEmpty(lazyExecutionGroup.getTestScenarioExecutionGroupNames()) && !CollectionUtils.isEmpty(testScenario.getAssignGroups())) {
            for (String acceptedTestSuiteName : lazyExecutionGroup.getTestScenarioExecutionGroupNames()) {
                if (testScenario.getAssignGroups().contains(acceptedTestSuiteName)) {
                    LOGGER.info("Find assigned test suite - [{}] to execute", acceptedTestSuiteName);
                    isFoundInAcceptedList = true;
                }
            }
        }
        return isFoundInAcceptedList;
    }

    @Override
    public TestScenarioExecutionData executeTestScenario(TestScenario testScenario, Stack stack) throws LazyException, LazyCoreException {
        validateTestScenario(testScenario);
        String testScenarioName = testScenario.getTestScenarioName();
        LOGGER.debug("Preparing to execute test scenario - [{}]", testScenarioName);

        if (stack == null) {
            String error = "Test scenario stack should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_TEST_SUITE, error);
        }
        validateDefaultValues(stack.getDefaultValues());

        TestSuite testSuite = new TestSuite("Default test suite");
        testSuite.getTestScenarios().add(testScenario);
        testSuite.setStack(stack);

        LazySuite lazySuite = new LazySuite("Default Lazy suite", stack);
        lazySuite.setGlobal(new Global());
        lazySuite.setStack(stack);
        lazySuite.getTestSuites().add(testSuite);

        mergeStack(lazySuite, testSuite, testSuite.getTestSuiteName());
        mergeStack(testSuite, testScenario, testScenarioName);
        LOGGER.debug("Populated lazy suite to execute test scenario - [{}]", testScenarioName);

        IdDto idDto = new IdDto();
        Integer testScenarioId = populateTestScenarioId(idDto, testScenario, testScenarioName);

        LOGGER.info("Executing test scenario - [{}] - [{}]", testScenarioId, testScenarioName);
        TestScenarioExecutionData testScenarioExecutionData = new TestScenarioExecutionData(testScenarioId, testScenarioName);
        testCaseManager.executeTestCases(lazySuite, testScenarioExecutionData, idDto, testScenario);
        LOGGER.info("Executed test scenario - [{}] - [{}]", testScenarioId, testScenarioName);

        TestSuiteExecutionData testSuiteExecutionData = new TestSuiteExecutionData(idDto.getTestSuiteId(), testSuite.getTestSuiteName());
        testSuiteExecutionData.getTestScenarioExecutionData().add(testScenarioExecutionData);
        LazyExecutionData lazyExecutionData = new LazyExecutionData();
        lazyExecutionData.getTestSuiteExecutionData().add(testSuiteExecutionData);
        printResultTable(lazyExecutionData);

        return testScenarioExecutionData;
    }

    private Integer populateTestScenarioId(IdDto idDto, TestScenario testScenario, String testScenarioName) {
        Integer testScenarioId = idDto.getTestScenarioId();
        if (testScenario.getTestScenarioId() == null) {
            testScenario.setTestScenarioId(testScenarioId);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No test scenario ID found for [{}] hence add test suite Id [{}]", testScenarioName, testScenario.getTestScenarioId());
            }
        }
        return testScenarioId;
    }

    private void validateTestScenario(TestScenario testScenario) throws LazyCoreException, LazyException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to execute test scenario - [{}]", JsonUtil.getJsonStringFromObject(testScenario));
        }

        if (testScenario == null) {
            String error = "Test scenario should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_SCENARIO, error);
        }

        if (StringUtils.isBlank(testScenario.getTestScenarioName())) {
            String error = "Test scenario name should not be empty";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_SCENARIO, error);
        }
    }
}

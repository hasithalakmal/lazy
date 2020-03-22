package com.smile.lazy.manager.impl;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.TestScenarioExecutionData;
import com.smile.lazy.beans.executor.TestSuiteExecutionData;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.manager.TestCaseManager;
import com.smile.lazy.manager.handlers.StackHandler;
import com.smile.lazy.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TestScenarioManagerImpl implements com.smile.lazy.manager.TestScenarioManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestScenarioManagerImpl.class);

    @Autowired
    private StackHandler stackManager;

    @Autowired
    private TestCaseManager testCaseManager;

    @Override
    public void executeTestScenarios(LazySuite lazySuite, TestSuiteExecutionData testSuiteExecutionData, IdDto idDto, TestSuite testSuite)
          throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all test scenarios...");
        for (TestScenario testScenario : testSuite.getTestScenarios()) {
            validateTestScenario(testScenario);
            String testScenarioName = testScenario.getTestScenarioName();
            LOGGER.debug("Preparing to execute test scenario - [{}]", testScenarioName);
            mergeStack(testSuite, testScenario, testScenarioName);
            Integer testScenarioId = populateTestScenarioId(idDto, testScenario, testScenarioName);

            LOGGER.info("Executing test scenario - [{}] - [{}]", testScenarioId, testScenarioName);
            TestScenarioExecutionData testScenarioExecutionData = new TestScenarioExecutionData(testScenarioId, testScenarioName);
            testCaseManager.executeTestCases(lazySuite, testScenarioExecutionData, idDto, testScenario);
            LOGGER.info("Executed test scenario - [{}] - [{}]", testScenarioId, testScenarioName);

            testSuiteExecutionData.getTestScenarioExecutionData().add(testScenarioExecutionData);

            idDto.setTestScenarioId(testScenarioId + 1);
        }
        LOGGER.debug("Executed all test scenarios...");
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

    private void mergeStack(TestSuite testSuite, TestScenario testScenario, String testScenarioName) throws LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merging two stacks for [{}] test scenario: parent stack [{}] - child stack [{}]", testScenarioName,
                  JsonUtil.getJsonStringFromObject(testSuite.getStack()), JsonUtil.getJsonStringFromObject(testScenario.getStack()));
        }
        testScenario.setStack(stackManager.mergeTwoStacks(testSuite.getStack(), testScenario.getStack()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merged stacks on test scenario level for [{}] : merged [{}]", testScenarioName, JsonUtil.getJsonStringFromObject(testScenario.getStack()));
        }
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

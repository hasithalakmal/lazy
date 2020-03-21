package com.smile.lazy.manager.impl;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.beans.executor.TestScenarioExecutionData;
import com.smile.lazy.beans.executor.TestSuiteExecutionData;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.manager.TestScenarioManager;
import com.smile.lazy.manager.handlers.StackHandler;
import com.smile.lazy.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class TestSuiteManagerImpl implements com.smile.lazy.manager.TestSuiteManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestSuiteManagerImpl.class);

    @Autowired
    private StackHandler stackManager;

    @Autowired
    private TestScenarioManager testScenarioManager;

    @Override
    public void executeTestSuites(LazySuite lazySuite, LazyExecutionData lazyExecutionData, IdDto idDto) throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all test suites of lazy suite [{}]...", lazySuite.getLazySuiteName());
        for (TestSuite testSuite : lazySuite.getTestSuites()) {
            validateTestSuite(testSuite);
            String testSuiteName = testSuite.getTestSuiteName();
            LOGGER.debug("Preparing to execute test suite - [{}]", testSuiteName);
            mergeStack(lazySuite, testSuite, testSuiteName);
            Integer testSuiteId = populateTestSuiteId(idDto, testSuite, testSuiteName);

            LOGGER.info("Ready to execute test suite - [{}] - [{}]", testSuiteId, testSuiteName);
            TestSuiteExecutionData testSuiteExecutionData = new TestSuiteExecutionData(testSuiteId, testSuiteName);
            testScenarioManager.executeTestScenarios(lazySuite, testSuiteExecutionData, idDto, testSuite);
            LOGGER.info("Executed test suite - [{}] - [{}]", testSuiteId, testSuiteName);

            lazyExecutionData.getTestSuiteExecutionData().add(testSuiteExecutionData);
            idDto.setTestSuiteId(testSuiteId + 1);
        }
        LOGGER.debug("Executed all test suites...");
    }

    private void validateTestSuite(TestSuite testSuite) throws LazyCoreException, LazyException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to execute test suite - [{}]", JsonUtil.getJsonStringFromObject(testSuite));
        }

        if (testSuite == null) {
            String error = "Test suite should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        if (StringUtils.isBlank(testSuite.getTestSuiteName())) {
            String error = "Test suite name should not be empty";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }
    }

    private Integer populateTestSuiteId(IdDto idDto, TestSuite testSuite, String testSuiteName) {
        Integer testSuiteId = idDto.getTestSuiteId();
        if (testSuite.getTestSuiteId() == null) {
            testSuite.setTestSuiteId(testSuiteId);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No test suite ID found for [{}] hence add test suite Id [{}]", testSuiteName, testSuite.getTestSuiteId());
            }
        }
        return testSuiteId;
    }

    private void mergeStack(LazySuite lazySuite, TestSuite testSuite, String testSuiteName) throws LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merging two stacks for [{}] test suite : parent stack [{}] - child stack [{}]", testSuiteName,
                  JsonUtil.getJsonStringFromObject(lazySuite.getStack()), JsonUtil.getJsonStringFromObject(testSuite.getStack()));
        }
        testSuite.setStack(stackManager.mergeTwoStacks(lazySuite.getStack(), testSuite.getStack()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merged stacks on test suite level for [{}] : merged [{}]", testSuiteName, JsonUtil.getJsonStringFromObject(testSuite.getStack()));
        }
    }
}

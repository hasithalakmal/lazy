package com.smile.lazy.manager.impl;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.beans.suite.TestScenario;
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

@Service
public class TestCaseManagerImpl implements com.smile.lazy.manager.TestCaseManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestCaseManagerImpl.class);

    @Autowired
    private StackHandler stackManager;

    @Autowired
    private ApiCallManager apiCallManager;

    @Override
    public void executeTestCases(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestScenario testScenario) throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all test cases...");
        for (TestCase testCase : testScenario.getTestCases()) {
            validateTestCase(testCase);
            String testCaseName = testCase.getTestCaseName();
            LOGGER.debug("Preparing to execute test case - [{}]", testCaseName);
            mergeStack(testScenario, testCase, testCaseName);
            Integer testCaseId = populateTestCaseId(idDto, testCase, testCaseName);

            LOGGER.info("Executing test case - [{}] - [{}]", testCaseId, testCaseName);
            apiCallManager.executeApiCalls(lazySuite, assertionResultList, idDto, testCase);
            LOGGER.info("Executed test case - [{}] - [{}]", testCaseId, testCaseName);

            idDto.setTestCaseId(testCaseId + 1);
        }
        LOGGER.info("Executed all test cases...");
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

    private void mergeStack(TestScenario testScenario, TestCase testCase, String testCaseName) throws LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merging two stacks for [{}] test case: parent stack [{}] - child stack [{}]", testCaseName,
                  JsonUtil.getJsonStringFromObject(testScenario.getStack()), JsonUtil.getJsonStringFromObject(testCase.getStack()));
        }
        testCase.setStack(stackManager.mergeTwoStacks(testScenario.getStack(), testCase.getStack()));
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Merged stacks on test case level for [{}] : merged [{}]", testCaseName, JsonUtil.getJsonStringFromObject(testCase.getStack()));
        }
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

package com.smile24es.lazy.wrapper.impl;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.executor.ApiCallExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.beans.executor.TestCaseExecutionData;
import com.smile24es.lazy.beans.executor.TestScenarioExecutionData;
import com.smile24es.lazy.beans.executor.TestSuiteExecutionData;
import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;
import com.smile24es.lazy.manager.ApiCallManager;
import com.smile24es.lazy.manager.LazyManager;
import com.smile24es.lazy.manager.TestCaseManager;
import com.smile24es.lazy.manager.TestScenarioManager;
import com.smile24es.lazy.manager.TestSuiteManager;
import com.smile24es.lazy.wrapper.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ExecutorImpl implements Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorImpl.class);

    @Autowired
    private LazyManager lazyManager;

    @Autowired
    private TestSuiteManager testSuiteManager;

    @Autowired
    private TestScenarioManager testScenarioManager;

    @Autowired
    private TestCaseManager testCaseManager;

    @Autowired
    private ApiCallManager apiCallManager;

    @Override
    public LazyExecutionData executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException {
        LOGGER.info("Start lazy suite execution");
        return lazyManager.executeLazySuite(lazySuite);
    }

    @Override
    public LazyExecutionData executeLazySuite(LazySuite lazySuite, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException {
        LOGGER.info("Start lazy suite execution for selected execution groups");
        return lazyManager.executeLazySuite(lazySuite, lazyExecutionGroup);
    }

    @Override
    public TestSuiteExecutionData executeTestSuite(TestSuite testSuite, Stack stack) throws LazyCoreException, LazyException {
        LOGGER.info("Start test suite execution");
        return testSuiteManager.executeTestSuite(testSuite, stack);
    }

    @Override
    public TestScenarioExecutionData executeTestScenario(TestScenario testScenario, Stack stack) throws LazyException, LazyCoreException {
        LOGGER.info("Start test scenario execution");
        return testScenarioManager.executeTestScenario(testScenario, stack);
    }

    @Override
    public TestCaseExecutionData executeTestCase(TestCase testCase, Stack stack) throws LazyCoreException, LazyException {
        LOGGER.info("Start test case execution");
        return testCaseManager.executeTestCase(testCase, stack);
    }

    @Override
    public ApiCallExecutionData executeApiCall(ApiCall apiCall, Stack stack) throws LazyCoreException, LazyException {
        LOGGER.info("Start api call execution");
        return apiCallManager.executeApiCall(apiCall, stack);
    }
}

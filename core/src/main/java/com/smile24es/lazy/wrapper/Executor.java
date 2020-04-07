package com.smile24es.lazy.wrapper;

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

public interface Executor {

    /**
     * The method to execute complete lazy suite
     *
     * @param lazySuite
     * @return
     * @throws LazyException
     * @throws LazyCoreException
     */
    LazyExecutionData executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException;

    /**
     * The method to execute selected execution groups
     *
     * @param lazySuite
     * @return
     * @throws LazyException
     */
    LazyExecutionData executeLazySuite(LazySuite lazySuite, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException;

    /**
     * The method to execute a test suite
     *
     * @param testSuite
     * @param stack
     * @return
     * @throws LazyCoreException
     * @throws LazyException
     */
    TestSuiteExecutionData executeTestSuite(TestSuite testSuite, Stack stack) throws LazyCoreException, LazyException;

    /**
     * The method to execute test scenario
     *
     * @param testScenario
     * @param stack
     * @return
     * @throws LazyException
     * @throws LazyCoreException
     */
    TestScenarioExecutionData executeTestScenario(TestScenario testScenario, Stack stack) throws LazyException, LazyCoreException;

    /**
     * The method to execute test case
     *
     * @param testCase
     * @param stack
     * @return
     * @throws LazyCoreException
     * @throws LazyException
     */
    TestCaseExecutionData executeTestCase(TestCase testCase, Stack stack) throws LazyCoreException, LazyException;

    /**
     * The method to execute api call
     *
     * @param apiCall
     * @param stack
     * @return
     * @throws LazyCoreException
     * @throws LazyException
     */
    ApiCallExecutionData executeApiCall(ApiCall apiCall, Stack stack) throws LazyCoreException, LazyException;
}

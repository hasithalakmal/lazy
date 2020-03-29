package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionGroup;
import com.smile.lazy.beans.executor.TestCaseExecutionData;
import com.smile.lazy.beans.executor.TestScenarioExecutionData;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface TestCaseManager {
    void executeTestCases(LazySuite lazySuite, TestScenarioExecutionData testScenarioExecutionData, IdDto idDto, TestScenario testScenario) throws LazyException, LazyCoreException;

    void executeTestCases(LazySuite lazySuite, TestScenarioExecutionData testScenarioExecutionData, IdDto idDto, TestScenario testScenario,
                          LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException;

    TestCaseExecutionData executeTestCase(TestCase testCase, Stack stack) throws LazyCoreException, LazyException;
}

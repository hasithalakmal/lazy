package com.smile24es.lazy.manager;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.dto.IdDto;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.beans.executor.TestCaseExecutionData;
import com.smile24es.lazy.beans.executor.TestScenarioExecutionData;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;

public interface TestCaseManager {
    void executeTestCases(LazySuite lazySuite, TestScenarioExecutionData testScenarioExecutionData, IdDto idDto, TestScenario testScenario) throws LazyException, LazyCoreException;

    void executeTestCases(LazySuite lazySuite, TestScenarioExecutionData testScenarioExecutionData, IdDto idDto, TestScenario testScenario,
                          LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException;

    TestCaseExecutionData executeTestCase(TestCase testCase, Stack stack) throws LazyCoreException, LazyException;
}

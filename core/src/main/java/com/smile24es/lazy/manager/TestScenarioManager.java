package com.smile24es.lazy.manager;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.dto.IdDto;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.beans.executor.TestScenarioExecutionData;
import com.smile24es.lazy.beans.executor.TestSuiteExecutionData;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;

public interface TestScenarioManager {
    void executeTestScenarios(LazySuite lazySuite, TestSuiteExecutionData testSuiteExecutionData, IdDto idDto, TestSuite testSuite)
          throws LazyException, LazyCoreException;

    void executeTestScenarios(LazySuite lazySuite, TestSuiteExecutionData testSuiteExecutionData, IdDto idDto, TestSuite testSuite,
                              LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException;

    TestScenarioExecutionData executeTestScenario(TestScenario testScenario, Stack stack) throws LazyException, LazyCoreException;
}

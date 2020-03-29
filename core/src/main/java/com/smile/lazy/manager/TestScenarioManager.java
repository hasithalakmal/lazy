package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.TestScenarioExecutionData;
import com.smile.lazy.beans.executor.TestSuiteExecutionData;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface TestScenarioManager {
    void executeTestScenarios(LazySuite lazySuite, TestSuiteExecutionData testSuiteExecutionData, IdDto idDto, TestSuite testSuite)
          throws LazyException, LazyCoreException;

    TestScenarioExecutionData executeTestScenario(TestScenario testScenario, Stack stack) throws LazyException, LazyCoreException;
}

package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.beans.executor.TestScenarioExecutionData;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface TestCaseManager {
    void executeTestCases(LazySuite lazySuite, TestScenarioExecutionData testScenarioExecutionData, IdDto idDto, TestScenario testScenario) throws LazyException, LazyCoreException;
}

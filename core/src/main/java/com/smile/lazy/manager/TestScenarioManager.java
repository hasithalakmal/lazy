package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.beans.executor.TestSuiteExecutionData;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface TestScenarioManager {
    void executeTestScenarios(LazySuite lazySuite, TestSuiteExecutionData testSuiteExecutionData, IdDto idDto, TestSuite testSuite)
          throws LazyException, LazyCoreException;
}

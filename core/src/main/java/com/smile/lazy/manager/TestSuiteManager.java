package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.beans.executor.LazyExecutionGroup;
import com.smile.lazy.beans.executor.TestSuiteExecutionData;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface TestSuiteManager {
    void executeTestSuites(LazySuite lazySuite, LazyExecutionData lazyExecutionData, IdDto idDto) throws LazyException, LazyCoreException;

    void executeTestSuites(LazySuite lazySuite, LazyExecutionData lazyExecutionData, IdDto idDto, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException;

    TestSuiteExecutionData executeTestSuite(TestSuite testSuite, Stack stack) throws LazyCoreException, LazyException;
}

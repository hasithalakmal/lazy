package com.smile24es.lazy.manager;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.dto.IdDto;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.beans.executor.TestSuiteExecutionData;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;

public interface TestSuiteManager {
    void executeTestSuites(LazySuite lazySuite, LazyExecutionData lazyExecutionData, IdDto idDto) throws LazyException, LazyCoreException;

    void executeTestSuites(LazySuite lazySuite, LazyExecutionData lazyExecutionData, IdDto idDto, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException;

    TestSuiteExecutionData executeTestSuite(TestSuite testSuite, Stack stack) throws LazyCoreException, LazyException;
}

package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.ApiCallExecutionData;
import com.smile.lazy.beans.executor.LazyExecutionGroup;
import com.smile.lazy.beans.executor.TestCaseExecutionData;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface ApiCallManager {
    void executeApiCalls(LazySuite lazySuite, TestCaseExecutionData testCaseExecutionData, IdDto idDto, TestCase testCase) throws LazyException,
          LazyCoreException;

    void executeApiCalls(LazySuite lazySuite, TestCaseExecutionData testCaseExecutionData, IdDto idDto, TestCase testCase, LazyExecutionGroup lazyExecutionGroup)
          throws LazyException, LazyCoreException;

    ApiCallExecutionData executeApiCall(ApiCall apiCall, Stack stack) throws LazyCoreException, LazyException;
}

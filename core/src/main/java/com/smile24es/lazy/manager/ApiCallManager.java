package com.smile24es.lazy.manager;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.dto.IdDto;
import com.smile24es.lazy.beans.executor.ApiCallExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.beans.executor.TestCaseExecutionData;
import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;

public interface ApiCallManager {
    void executeApiCalls(LazySuite lazySuite, TestCaseExecutionData testCaseExecutionData, IdDto idDto, TestCase testCase) throws LazyException,
          LazyCoreException;

    void executeApiCalls(LazySuite lazySuite, TestCaseExecutionData testCaseExecutionData, IdDto idDto, TestCase testCase, LazyExecutionGroup lazyExecutionGroup)
          throws LazyException, LazyCoreException;

    ApiCallExecutionData executeApiCall(ApiCall apiCall, Stack stack) throws LazyCoreException, LazyException;
}

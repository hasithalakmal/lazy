package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface ApiCallManager {
    void executeApiCalls(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestCase testCase) throws LazyException,
          LazyCoreException;
}

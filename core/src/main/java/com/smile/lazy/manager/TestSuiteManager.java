package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface TestSuiteManager {
    void executeTestSuites(LazySuite lazySuite, LazyExecutionData assertionResultList, IdDto idDto) throws LazyException, LazyCoreException;
}

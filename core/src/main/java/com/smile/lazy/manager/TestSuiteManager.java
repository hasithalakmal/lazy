package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface TestSuiteManager {
    void executeTestSuites(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto) throws LazyException, LazyCoreException;
}

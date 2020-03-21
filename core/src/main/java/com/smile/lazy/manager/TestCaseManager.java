package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface TestCaseManager {
    void executeTestCases(LazySuite lazySuite, AssertionResultList assertionResultList, IdDto idDto, TestScenario testScenario) throws LazyException, LazyCoreException;
}

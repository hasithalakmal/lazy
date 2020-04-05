package com.smile24es.lazy.suite.sample;

import com.smile24es.lazy.beans.DefaultValues;
import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.suite.sample.suites.AccountApiTestSuite;

import static com.smile24es.lazy.common.SampleDefaultValues.createDefaultValues;

public class SampleLazySuite1 {

    public static LazySuite populateSampleLazySuite() throws LazyCoreException {
        LazySuite lazySuite = new LazySuite("Sample lazy suite 1");
        lazySuite.getTestSuites().add(AccountApiTestSuite.getAccountApiTestSuite());
        return lazySuite;
    }
}

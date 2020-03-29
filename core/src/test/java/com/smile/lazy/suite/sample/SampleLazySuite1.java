package com.smile.lazy.suite.sample;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.suite.sample.suites.AccountApiTestSuite;

import static com.smile.lazy.utils.SampleDefaultValues.createDefaultValues;

public class SampleLazySuite1 {

    public static LazySuite populateSampleLazySuite() throws LazyCoreException {
        DefaultValues defaultValues = createDefaultValues();
        Stack lazySuiteStack = new Stack(defaultValues);
        LazySuite lazySuite = new LazySuite("Sample lazy suite 1", lazySuiteStack);
        lazySuite.getTestSuites().add(AccountApiTestSuite.getAccountApiTestSuite());
        return lazySuite;
    }
}

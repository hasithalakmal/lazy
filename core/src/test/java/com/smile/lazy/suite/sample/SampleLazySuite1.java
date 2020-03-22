package com.smile.lazy.suite.sample;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.suite.Global;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.suite.sample.suites.AccountApiTestSuite;

import static com.smile.lazy.utils.SampleDefaultValues.createDefaultValues;

public class SampleLazySuite1 {

    public static LazySuite populateSampleTestSuite() throws LazyCoreException {
        DefaultValues defaultValues = createDefaultValues();
        Stack lazySuiteStack = new Stack(defaultValues);
        Global global = new Global();
        LazySuite lazySuite = new LazySuite("Sample lazy suite 1", lazySuiteStack, global);
        lazySuite.getTestSuites().add(AccountApiTestSuite.getAccountApiTestSuite());
        return lazySuite;
    }
}

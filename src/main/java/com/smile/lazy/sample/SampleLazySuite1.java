package com.smile.lazy.sample;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.suite.Global;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.sample.suites.AccountApiTestSuite;

import static com.smile.lazy.utils.SampleDefaultValues.createDefaultValues;

public class SampleLazySuite1 {

    public static LazySuite populateSampleTestSuite() {
        DefaultValues defaultValues = createDefaultValues();
        Stack lazySuiteStack = new Stack(defaultValues);
        Global global = new Global();
        LazySuite lazySuite = new LazySuite(1, "Sample lazy suite 1", lazySuiteStack, global);
        lazySuite.getTestSuites().add(AccountApiTestSuite.getAccountApiTestSuite());
        return lazySuite;
    }
}

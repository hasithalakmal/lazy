package com.smile.lazy.sample;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.suite.Header;
import com.smile.lazy.beans.suite.HeaderGroup;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.sample.suites.AccountApiTestSuite;

public class SampleLazySuite1 {

    public static LazySuite populateSampleTestSuite() {

        DefaultValues defaultValues = createDefaultValues();
        Stack lazySuiteStack = new Stack(defaultValues);
        LazySuite lazySuite = new LazySuite(1, "Sample lazy suite 1", lazySuiteStack);
        lazySuite.getTestSuites().add(AccountApiTestSuite.getAccountApiTestSuite(lazySuiteStack));
        return lazySuite;

    }

    private static DefaultValues createDefaultValues() {
        return new DefaultValues("http", "localhost", 8080, "account-api", createDefaultHeaderGroup(), "GET");
    }


    private static HeaderGroup createDefaultHeaderGroup() {
        HeaderGroup defaultHeaderGroup = new HeaderGroup(1, "Simple Header Group");
        Header header1 = new Header(1, "Accept Header", "accept", "application/json");
        Header header2 = new Header(2, "Content type Header", "content-type", "application/json");
        defaultHeaderGroup.getHeaders().add(header1);
        defaultHeaderGroup.getHeaders().add(header2);
        return defaultHeaderGroup;
    }
}

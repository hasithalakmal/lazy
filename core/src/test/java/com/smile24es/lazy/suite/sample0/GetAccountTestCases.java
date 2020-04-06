package com.smile24es.lazy.suite.sample0;

import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import com.smile24es.lazy.beans.suite.assertions.AssertionRuleGroup;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.suite.sample1.apicall.AccountApiCalls;
import com.smile24es.lazy.wrapper.Assert;

import java.util.List;

public class GetAccountTestCases {

    public static TestCase getCreateAccountTestCase() throws LazyCoreException {
        TestCase testCase1 = new TestCase("Create Account successfully - 1");
        testCase1.getApiCalls().add(AccountApiCall.getAccountApiCall());
        return testCase1;
    }
}

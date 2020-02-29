package com.smile.lazy.sample.testcase;

import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestCase;

import static com.smile.lazy.sample.apicall.AccountApiCalls.createAccountApiCall;
import static com.smile.lazy.sample.apicall.AccountApiCalls.getAccountApiCall;

public class CreateAccountSuccessTestCase {

    public static TestCase getCreateAccountTestCase() {
        TestCase testCase1 = new TestCase("Smile-Test-Case-1", "Create Account successfully");
        testCase1.getApiCalls().add(createAccountApiCall());
        testCase1.getApiCalls().add(getAccountApiCall());
        return testCase1;
    }
}

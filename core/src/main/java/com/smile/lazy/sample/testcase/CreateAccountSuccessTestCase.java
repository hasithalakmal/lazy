package com.smile.lazy.sample.testcase;

import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.sample.apicall.AccountApiCalls;

public class CreateAccountSuccessTestCase {

    public static TestCase getCreateAccountTestCase() {
        TestCase testCase1 = new TestCase("Smile-Test-Case-1", "Create Account successfully");
        testCase1.getApiCalls().add(AccountApiCalls.createAccountApiCall());
        testCase1.getApiCalls().add(AccountApiCalls.getAccountApiCall());
        testCase1.getApiCalls().add(AccountApiCalls.createAccountApiCallWithJsonFile());
        testCase1.getApiCalls().add(AccountApiCalls.getAccountApiCall());
        return testCase1;
    }
}

package com.smile.lazy.suite.sample.testcase;

import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.suite.sample.apicall.AccountApiCalls;

public class CreateAccountSuccessTestCase {

    public static TestCase getCreateAccountTestCase() {
        TestCase testCase1 = new TestCase( "Create Account successfully");
        testCase1.getApiCalls().add(AccountApiCalls.createAccountApiCall());
        testCase1.getApiCalls().add(AccountApiCalls.getAccountApiCall());
        testCase1.getApiCalls().add(AccountApiCalls.createAccountApiCallWithJsonFile());
        testCase1.getApiCalls().add(AccountApiCalls.getAccountApiCall());
        testCase1.getApiCalls().add(AccountApiCalls.createAccountApiCallWithTemplateFile());
        testCase1.getApiCalls().add(AccountApiCalls.getAccountApiCall());
        testCase1.getApiCalls().add(AccountApiCalls.createAccountApiCallWithTemplateFileComplex());
        testCase1.getApiCalls().add(AccountApiCalls.getAccountApiCall());
        return testCase1;
    }
}

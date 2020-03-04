package com.smile.lazy.sample.suites;

import com.smile.lazy.beans.suite.TestSuite;

import static com.smile.lazy.sample.scenarios.CreateAccountTestScenario.getAccountCreationTestScenario;

public class AccountApiTestSuite {

    public static TestSuite getAccountApiTestSuite() {
        TestSuite testSuite1 = new TestSuite("Smile-Account-API-Test-Suite-1", "Account Test Suite");
        testSuite1.getTestScenarios().add(getAccountCreationTestScenario());
        return testSuite1;
    }
}

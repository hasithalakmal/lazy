package com.smile.lazy.sample.suites;

import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestSuite;

import static com.smile.lazy.sample.scenarios.CreateAccountTestScenario.getAccountCreationTestScenario;

public class AccountApiTestSuite {

    public static TestSuite getAccountApiTestSuite() {
        TestSuite testSuite1 = new TestSuite("Smile-Test-Suite-1", "Account Test Suite");
        testSuite1.getTestScenarios().add(getAccountCreationTestScenario());
        return testSuite1;
    }
}

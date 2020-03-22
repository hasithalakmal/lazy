package com.smile.lazy.suite.sample.suites;

import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.exception.LazyCoreException;

import static com.smile.lazy.suite.sample.scenarios.CreateAccountTestScenario.getAccountCreationTestScenario;

public class AccountApiTestSuite {

    public static TestSuite getAccountApiTestSuite() throws LazyCoreException {
        TestSuite testSuite1 = new TestSuite("Account Test Suite");
        testSuite1.getTestScenarios().add(getAccountCreationTestScenario());
        return testSuite1;
    }
}

package com.smile24es.lazy.suite.sample0;

import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.exception.LazyCoreException;

import java.util.Arrays;

import static com.smile24es.lazy.suite.sample1.scenarios.CreateAccountTestScenario.getAccountCreationTestScenario;

public class AccountTestSuite {

    public static com.smile24es.lazy.beans.suite.TestSuite getAccountApiTestSuite() throws LazyCoreException {
        TestSuite testSuite1 = new TestSuite("Account Test Suite");
        testSuite1.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        testSuite1.getTestScenarios().add(GetAccountTestScenarios.getAccountTestScenario());
        return testSuite1;
    }
}

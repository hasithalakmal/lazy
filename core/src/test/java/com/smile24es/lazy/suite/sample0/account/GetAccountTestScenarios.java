package com.smile24es.lazy.suite.sample0.account;

import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.exception.LazyCoreException;

public class GetAccountTestScenarios {

    public static TestScenario createAccountTestScenario() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("get Account");
        successAccountCreation.getTestCases().add(GetAccountTestCases.createAccountSuccessTestCase());
        return successAccountCreation;
    }

    public static TestScenario mockTestScenarios() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("get Account");
        successAccountCreation.getTestCases().add(GetAccountTestCases.mockApiTest());
        return successAccountCreation;
    }

    public static TestScenario getAccountErrorScenarios() throws LazyCoreException {
        TestScenario invalidAccountGetScenarios = new TestScenario("Invalid account get scenarios");
        invalidAccountGetScenarios.getTestCases().add(GetAccountTestCases.getInvalidAccount());
        return invalidAccountGetScenarios;
    }
}

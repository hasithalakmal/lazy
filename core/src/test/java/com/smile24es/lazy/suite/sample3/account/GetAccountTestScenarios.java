package com.smile24es.lazy.suite.sample3.account;

import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.exception.LazyCoreException;

public class GetAccountTestScenarios {

    public static TestScenario createAccountTestScenario() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("Create Account Test Scenarios");
        successAccountCreation.getTestCases().add(GetAccountTestCases.createAccountSuccessTestCase());
        successAccountCreation.getTestCases().add(GetAccountTestCases.createComplexAccountSuccessTestCase());
        successAccountCreation.getTestCases().add(GetAccountTestCases.createAccountWithMinDataSuccessTestCase());
        return successAccountCreation;
    }

    public static TestScenario getAccountTestScenario() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("Get Account Test Scenarios");
        successAccountCreation.getTestCases().add(GetAccountTestCases.getAccountWithSettings());
        successAccountCreation.getTestCases().add(GetAccountTestCases.getAccountWithOutSettings());
        return successAccountCreation;
    }

    public static TestScenario updateaccountTestScenarios() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("Update Account Test Scenarios");
        successAccountCreation.getTestCases().add(GetAccountTestCases.updateAccount());
        return successAccountCreation;
    }

    public static TestScenario mixTestScenarios() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("Update Account Test Scenarios");
        successAccountCreation.getTestCases().add(GetAccountTestCases.mixTestCases());
        return successAccountCreation;
    }

    public static TestScenario getAccountErrorScenarios() throws LazyCoreException {
        TestScenario invalidAccountGetScenarios = new TestScenario("Account API Error Scenarios");
        invalidAccountGetScenarios.getTestCases().add(GetAccountTestCases.getInvalidAccount());
        invalidAccountGetScenarios.getTestCases().add(GetAccountTestCases.createAccountWithInvalidData());
        return invalidAccountGetScenarios;
    }
}

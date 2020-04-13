package com.smile24es.lazy.suite.sample3.student;

import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.exception.LazyCoreException;

public class GetStudentTestScenarios {

    public static TestScenario createAccountTestScenario() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("Create Student Test Scenarios");
        successAccountCreation.getTestCases().add(GetStudentTestCases.createAccountSuccessTestCase());
        successAccountCreation.getTestCases().add(GetStudentTestCases.createComplexAccountSuccessTestCase());
        successAccountCreation.getTestCases().add(GetStudentTestCases.createAccountWithMinDataSuccessTestCase());
        return successAccountCreation;
    }

    public static TestScenario getAccountTestScenario() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("Get Student Test Scenarios");
        successAccountCreation.getTestCases().add(GetStudentTestCases.getAccountWithSettings());
        successAccountCreation.getTestCases().add(GetStudentTestCases.getAccountWithOutSettings());
        return successAccountCreation;
    }

    public static TestScenario updateaccountTestScenarios() throws LazyCoreException {
        TestScenario successAccountCreation = new TestScenario("Update Student Test Scenarios");
        successAccountCreation.getTestCases().add(GetStudentTestCases.updateAccount());
        return successAccountCreation;
    }

    public static TestScenario getAccountErrorScenarios() throws LazyCoreException {
        TestScenario invalidAccountGetScenarios = new TestScenario("Student API Error Scenarios");
        invalidAccountGetScenarios.getTestCases().add(GetStudentTestCases.getInvalidAccount());
        invalidAccountGetScenarios.getTestCases().add(GetStudentTestCases.createAccountWithInvalidData());
        return invalidAccountGetScenarios;
    }

    public static TestScenario getInvalidRule() throws LazyCoreException {
        TestScenario invalidAccountGetScenarios = new TestScenario("Invalid Assertion Scenarios");
        invalidAccountGetScenarios.getTestCases().add(GetStudentTestCases.getInvalidAssertion());
        return invalidAccountGetScenarios;
    }

    public static TestScenario getIFailedRule() throws LazyCoreException {
        TestScenario invalidAccountGetScenarios = new TestScenario("Faild Assertion Scenarios");
        invalidAccountGetScenarios.getTestCases().add(GetStudentTestCases.getFailedAssertion());
        return invalidAccountGetScenarios;
    }

    public static TestScenario getSkippedRule() throws LazyCoreException {
        TestScenario invalidAccountGetScenarios = new TestScenario("Faild Skipped Scenarios");
        invalidAccountGetScenarios.getTestCases().add(GetStudentTestCases.getSkippedAssertion());
        return invalidAccountGetScenarios;
    }
}

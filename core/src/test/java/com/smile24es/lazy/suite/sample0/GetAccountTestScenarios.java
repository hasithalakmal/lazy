package com.smile24es.lazy.suite.sample0;

import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.exception.LazyCoreException;

public class GetAccountTestScenarios {

    public static TestScenario getAccountTestScenario() throws LazyCoreException {
        TestScenario testScenario1 = new TestScenario("get Account");
        testScenario1.getTestCases().add(GetAccountTestCases.getCreateAccountTestCase());
        return testScenario1;
    }
}

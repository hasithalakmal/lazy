package com.smile24es.lazy.suite.sample3.student;

import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.wrapper.Assert;

public class StudentTestSuite {

    public static TestSuite studentGeneralTestSuite() throws LazyCoreException {
        TestSuite accountApiTestSuite = new TestSuite("General Student Test Suite");
        accountApiTestSuite.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        accountApiTestSuite.getStack().getDefaultValues().setHostName("localhost");
        AssertionRule assertionRule = Assert.responseTimeLessThan("150");
        assertionRule.setAssertionRuleKey("account.api.max.response.time");
        accountApiTestSuite.getStack().addDefaultAssertionRule(assertionRule);
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.getAccountTestScenario());
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.createAccountTestScenario());
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.getAccountErrorScenarios());
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.updateaccountTestScenarios());
        return accountApiTestSuite;
    }

    public static TestSuite studentBuildVerificationTestSuite() throws LazyCoreException {
        TestSuite accountApiTestSuite = new TestSuite("BVT Student Test Suite");
        accountApiTestSuite.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        accountApiTestSuite.getStack().getDefaultValues().setHostName("localhost");
        AssertionRule assertionRule = Assert.responseTimeLessThan("150");
        assertionRule.setAssertionRuleKey("account.api.max.response.time");
        accountApiTestSuite.getStack().addDefaultAssertionRule(assertionRule);
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.createAccountTestScenario());
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.getAccountErrorScenarios());
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.updateaccountTestScenarios());
        return accountApiTestSuite;
    }

    public static TestSuite getInvalidSuite() throws LazyCoreException {
        TestSuite accountApiTestSuite = new TestSuite("Invalid Student Test Suite");
        accountApiTestSuite.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        accountApiTestSuite.getStack().getDefaultValues().setHostName("localhost");
        AssertionRule assertionRule = Assert.responseTimeLessThan("150");
        assertionRule.setAssertionRuleKey("account.api.max.response.time");
        accountApiTestSuite.getStack().addDefaultAssertionRule(assertionRule);
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.getInvalidRule());
        return accountApiTestSuite;
    }

    public static TestSuite failedCompleteTestSuite() throws LazyCoreException {
        TestSuite accountApiTestSuite = new TestSuite("Failed Student Test Suite");
        accountApiTestSuite.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        accountApiTestSuite.getStack().getDefaultValues().setHostName("localhost");
        AssertionRule assertionRule = Assert.responseTimeLessThan("150");
        assertionRule.setAssertionRuleKey("account.api.max.response.time");
        accountApiTestSuite.getStack().addDefaultAssertionRule(assertionRule);
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.getIFailedRule());
        return accountApiTestSuite;
    }

    public static TestSuite skippedCompleteTestSuite() throws LazyCoreException {
        TestSuite accountApiTestSuite = new TestSuite("Failed Student Test Suite");
        accountApiTestSuite.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        accountApiTestSuite.getStack().getDefaultValues().setHostName("localhost");
        AssertionRule assertionRule = Assert.responseTimeLessThan("150");
        assertionRule.setAssertionRuleKey("account.api.max.response.time");
        accountApiTestSuite.getStack().addDefaultAssertionRule(assertionRule);
        accountApiTestSuite.getTestScenarios().add(GetStudentTestScenarios.getSkippedRule());
        return accountApiTestSuite;
    }


}

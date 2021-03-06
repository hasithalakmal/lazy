package com.smile24es.lazy.suite.sample3.account;

import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.wrapper.Assert;

public class AccountTestSuite {

    public static TestSuite generalTestSuite() throws LazyCoreException {
        TestSuite accountApiTestSuite = new TestSuite("General Account Test Suite");
        accountApiTestSuite.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        accountApiTestSuite.getStack().getDefaultValues().setHostName("localhost");
        AssertionRule assertionRule = Assert.responseTimeLessThan("150");
        assertionRule.setAssertionRuleKey("account.api.max.response.time");
        accountApiTestSuite.getStack().addDefaultAssertionRule(assertionRule);
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.getAccountTestScenario());
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.createAccountTestScenario());
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.getAccountErrorScenarios());
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.updateaccountTestScenarios());
        return accountApiTestSuite;
    }

    public static TestSuite buildVerificationTestSuite() throws LazyCoreException {
        TestSuite accountApiTestSuite = new TestSuite("BVT Account Test Suite");
        accountApiTestSuite.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        accountApiTestSuite.getStack().getDefaultValues().setHostName("localhost");
        AssertionRule assertionRule = Assert.responseTimeLessThan("150");
        assertionRule.setAssertionRuleKey("account.api.max.response.time");
        accountApiTestSuite.getStack().addDefaultAssertionRule(assertionRule);
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.createAccountTestScenario());
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.getAccountErrorScenarios());
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.updateaccountTestScenarios());
        return accountApiTestSuite;
    }

    public static TestSuite completeTestSuite() throws LazyCoreException {
        TestSuite accountApiTestSuite = new TestSuite("Complete Account Test Suite");
        accountApiTestSuite.getStack().getDefaultValues().setContextPath("account-api"); //We are configuring context path
        accountApiTestSuite.getStack().getDefaultValues().setHostName("localhost");
        AssertionRule assertionRule = Assert.responseTimeLessThan("150");
        assertionRule.setAssertionRuleKey("account.api.max.response.time");
        accountApiTestSuite.getStack().addDefaultAssertionRule(assertionRule);
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.createAccountTestScenario());
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.getAccountErrorScenarios());
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.updateaccountTestScenarios());
        accountApiTestSuite.getTestScenarios().add(GetAccountTestScenarios.mixTestScenarios());
        return accountApiTestSuite;
    }
}

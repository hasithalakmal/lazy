package com.smile.lazy.suite.sample.scenarios;

import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionRuleGroup;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.suite.sample.testcase.CreateAccountSuccessTestCase;
import com.smile.lazy.wrapper.Assert;

import java.util.List;

public class CreateAccountTestScenario {

    public static TestScenario getAccountCreationTestScenario() throws LazyCoreException {
        TestScenario testScenario1 = new TestScenario( "Create Account");
        testScenario1.getStack().addDefaultAssertionGroup(createDefaultAssertionRuleGroup());
        testScenario1.getTestCases().add(CreateAccountSuccessTestCase.getCreateAccountTestCase());
        return testScenario1;
    }

    private static AssertionRuleGroup createDefaultAssertionRuleGroup() {
        AssertionRuleGroup defaultCreateAssertionGroup = new AssertionRuleGroup(1, "Default create assertion group");
        List<AssertionRule> assertionRules = defaultCreateAssertionGroup.getAssertionRules();
        assertionRules.add(Assert.responseTimeAssertionGreaterThanGivenMilliSeconds("2000"));
        AssertionRule responseCodeAssertion = Assert.responseCodeAssertion("201");
        responseCodeAssertion.setAssertionRuleKey("created.http.status.assertion");
        assertionRules.add(responseCodeAssertion);
        assertionRules.add(Assert.responseBodyNotNull());
        return defaultCreateAssertionGroup;
    }
}

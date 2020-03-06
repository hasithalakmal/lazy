package com.smile.lazy.sample.scenarios;

import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionRuleGroup;
import com.smile.lazy.wrapper.Assert;

import java.util.List;

import static com.smile.lazy.sample.testcase.CreateAccountSuccessTestCase.getCreateAccountTestCase;

public class CreateAccountTestScenario {

    public static TestScenario getAccountCreationTestScenario() {
        TestScenario testScenario1 = new TestScenario("Smile-Test-Scenario-1", "Create Account");
        testScenario1.getStack().addDefaultAssertionGroup(createDefaultAssertionRuleGroup());
        testScenario1.getTestCases().add(getCreateAccountTestCase());
        return testScenario1;
    }

    private static AssertionRuleGroup createDefaultAssertionRuleGroup() {
        AssertionRuleGroup defaultCreateAssertionGroup = new AssertionRuleGroup(1, "Default create assertion group");
        List<AssertionRule> assertionRules = defaultCreateAssertionGroup.getAssertionRules();
        assertionRules.add(Assert.responseTimeAssertion_ms_lt("2000"));
        AssertionRule responseCodeAssertion = Assert.responseCodeAssertion("201");
        responseCodeAssertion.setAssertionRuleKey("created.http.status.assertion");
        assertionRules.add(responseCodeAssertion);
        assertionRules.add(Assert.responseBodyNotNull());
        return defaultCreateAssertionGroup;
    }
}

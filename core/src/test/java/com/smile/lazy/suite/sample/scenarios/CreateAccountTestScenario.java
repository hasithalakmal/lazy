package com.smile.lazy.suite.sample.scenarios;

import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionRuleGroup;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.wrapper.Assert;

import java.util.Arrays;
import java.util.List;

import static com.smile.lazy.suite.sample.testcase.CreateAccountSuccessTestCase.getCreateAccountTestCase;
import static com.smile.lazy.suite.sample.testcase.CreateAccountSuccessTestCase.getCreateAccountTestCase2;

public class CreateAccountTestScenario {

    public static TestScenario getAccountCreationTestScenario() throws LazyCoreException {
        TestScenario testScenario1 = new TestScenario("Create Account");
        testScenario1.getStack().addDefaultAssertionGroup(createDefaultAssertionRuleGroup());
        testScenario1.getTestCases().add(getCreateAccountTestCase());
        testScenario1.getTestCases().add(getCreateAccountTestCase2());
        testScenario1.setAssignGroups(Arrays.asList("create-accounts"));
        return testScenario1;
    }

    private static AssertionRuleGroup createDefaultAssertionRuleGroup() {
        AssertionRuleGroup defaultCreateAssertionGroup = new AssertionRuleGroup(1, "Test scenario assertion group");
        List<AssertionRule> assertionRules = defaultCreateAssertionGroup.getAssertionRules();
        assertionRules.add(Assert.responseTimeAssertionGreaterThanGivenMilliSeconds("2000"));
        AssertionRule responseCodeAssertion = Assert.responseCodeAssertion("201");
        responseCodeAssertion.setAssertionRuleKey("created.http.status.assertion");
        assertionRules.add(responseCodeAssertion);
        assertionRules.add(Assert.responseBodyNotNull());

        //Performance impacted assertion
        AssertionRule responseTimeAssertion = Assert.responseTimeAssertionGreaterThanGivenMilliSeconds("1000");
        responseTimeAssertion.setAssertionRuleKey("high.performance.response.time.assertion");
        assertionRules.add(responseTimeAssertion);
        return defaultCreateAssertionGroup;
    }
}

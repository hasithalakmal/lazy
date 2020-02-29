package com.smile.lazy.sample.scenarios;

import com.smile.lazy.beans.enums.AssertionOperationEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.enums.DataTypeEnum;
import com.smile.lazy.beans.enums.UnitEnum;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionRuleGroup;
import com.smile.lazy.beans.suite.assertions.AssertionValue;

import static com.smile.lazy.sample.testcase.CreateAccountSuccessTestCase.getCreateAccountTestCase;

public class CreateAccountTestScenario {

    public static TestScenario getAccountCreationTestScenario(Stack testSuiteStack) {
        TestScenario testScenario1 = new TestScenario("Smile-Test-Scenario-1", "Create Account", testSuiteStack);
        testScenario1.getStack().setDefaultAssertionGroup(createDefaultAssertionRuleGroup());
        testScenario1.getTestCases().add(getCreateAccountTestCase(testScenario1.getStack()));
        return testScenario1;
    }

    private static AssertionRuleGroup createDefaultAssertionRuleGroup() {
        AssertionRule defaultCreateAssertionRule1 = new AssertionRule(DataSourceEnum.RESPONSE_TIME, AssertionOperationEnum.LESS_THAN, new AssertionValue("20000", DataTypeEnum.INTEGER, UnitEnum.MILLI_SECONDS));
        AssertionRule defaultCreateAssertionRule2 = new AssertionRule(DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.EQUAL, new AssertionValue("201", DataTypeEnum.INTEGER));
        AssertionRule defaultCreateAssertionRule3 = new AssertionRule(DataSourceEnum.BODY, AssertionOperationEnum.NOT_NULL);
        AssertionRuleGroup defaultCreateAssertionGroup = new AssertionRuleGroup(1, "Default create assertion group");
        defaultCreateAssertionGroup.getAssertionRules().add(defaultCreateAssertionRule1);
        defaultCreateAssertionGroup.getAssertionRules().add(defaultCreateAssertionRule2);
        defaultCreateAssertionGroup.getAssertionRules().add(defaultCreateAssertionRule3);
        return defaultCreateAssertionGroup;
    }
}

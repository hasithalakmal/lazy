package com.smile24es.lazy.utils;

import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import com.smile24es.lazy.beans.suite.assertions.AssertionRuleGroup;

import java.util.ArrayList;
import java.util.List;

public class AssertionManipulationUtil {

    private AssertionManipulationUtil() {
        //This is a private constructor
    }

    public static List<AssertionRule> getRuleListFromAssertionGroup(AssertionRuleGroup assertionRuleGroup) {
        List<AssertionRule> assertionRules = new ArrayList<>();
        if (assertionRuleGroup != null && !assertionRuleGroup.getAssertionRules().isEmpty()) {
            assertionRules.addAll(assertionRuleGroup.getAssertionRules());
        }
        return assertionRules;
    }

}

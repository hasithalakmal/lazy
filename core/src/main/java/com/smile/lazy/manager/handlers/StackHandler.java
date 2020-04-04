package com.smile.lazy.manager.handlers;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.environment.Environment;
import com.smile.lazy.beans.suite.HeaderGroup;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StackHandler {

    public Stack mergeTwoStacks(Stack parentStack, Stack childStack) {

        Stack mergedStack = SerializationUtils.clone(parentStack);

        if (childStack == null) {
            return mergedStack;
        }

        DefaultValues defaultValues = childStack.getDefaultValues();
        if (defaultValues != null) {
            mergedStack.setDefaultValues(defaultValues);
        }

        Map<String, Environment> environments = childStack.getEnvironments();
        if (environments != null) {
            mergedStack.setEnvironments(environments);
        }

        Map<String, String> attributes = childStack.getAttributes();
        if (attributes != null) {
            mergedStack.setAttributes(attributes);
        }

        List<AssertionRule> childStackDefaultAssertions = childStack.getDefaultAssertions();
        if (!childStackDefaultAssertions.isEmpty()) {
            List<AssertionRule> parentDefaultAssertions = parentStack.getDefaultAssertions();
            List<AssertionRule> mergedAssertions = new ArrayList<>();
            for (AssertionRule parentAssertionRule : parentDefaultAssertions) {
                if (StringUtils.isNotBlank(parentAssertionRule.getAssertionRuleKey())) {
                    boolean isParentOnlyAssertion = true;
                    for (AssertionRule childAssertionRule : childStackDefaultAssertions) {
                        if (StringUtils.isNotBlank(childAssertionRule.getAssertionRuleKey())
                              && childAssertionRule.getAssertionRuleKey().equals(parentAssertionRule.getAssertionRuleKey())) {
                            isParentOnlyAssertion = false;
                        }
                    }

                    if (isParentOnlyAssertion) {
                        mergedAssertions.add(parentAssertionRule);
                    }
                } else {
                    mergedAssertions.add(parentAssertionRule);
                }
            }

            mergedAssertions.addAll(childStackDefaultAssertions);
            mergedStack.setDefaultAssertions(mergedAssertions);
        }

        HeaderGroup headerGroup = childStack.getHeaderGroup();
        if (headerGroup != null) {
            mergedStack.setHeaderGroup(headerGroup);
        }

        return mergedStack;
    }
}

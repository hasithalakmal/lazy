package com.smile24es.lazy.manager.handlers;

import com.smile24es.lazy.beans.DefaultValues;
import com.smile24es.lazy.beans.environment.Environment;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
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

        DefaultValues childDefaultValues = childStack.getDefaultValues();
        if (childDefaultValues != null) {
            DefaultValues mergedDefaultValues = mergedStack.getDefaultValues();
            if (StringUtils.isNotBlank(childDefaultValues.getProtocol())) {
                mergedDefaultValues.setProtocol(childDefaultValues.getProtocol());
            }

            if (StringUtils.isNotBlank(childDefaultValues.getHostName())) {
                mergedDefaultValues.setHostName(childDefaultValues.getHostName());
            }

            if (StringUtils.isNotBlank(childDefaultValues.getContextPath())) {
                mergedDefaultValues.setContextPath(childDefaultValues.getContextPath());
            }

            if (StringUtils.isNotBlank(childDefaultValues.getHttpMethod())) {
                mergedDefaultValues.setHttpMethod(childDefaultValues.getHttpMethod());
            }

            if (childDefaultValues.getPort() != null) {
                mergedDefaultValues.setPort(childDefaultValues.getPort());
            }

            if (childDefaultValues.getHeaderGroup() != null) {
                mergedDefaultValues.setHeaderGroup(childDefaultValues.getHeaderGroup());
            }
            mergedStack.setDefaultValues(mergedDefaultValues);
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

        return mergedStack;
    }
}

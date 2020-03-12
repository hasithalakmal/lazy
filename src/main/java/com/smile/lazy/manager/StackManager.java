package com.smile.lazy.manager;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.environment.Environment;
import com.smile.lazy.beans.suite.HeaderGroup;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import org.apache.commons.lang3.SerializationUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StackManager {

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

        List<AssertionRule> defaultAssertions = childStack.getDefaultAssertions();
        if (!defaultAssertions.isEmpty()) {
            mergedStack.setDefaultAssertions(defaultAssertions);
        }

        HeaderGroup headerGroup = childStack.getHeaderGroup();
        if (headerGroup != null) {
            mergedStack.setHeaderGroup(headerGroup);
        }

        return mergedStack;
    }
}

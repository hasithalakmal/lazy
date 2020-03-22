package com.smile.lazy.utils;

import com.smile.lazy.beans.environment.EnvironmentVariable;
import com.smile.lazy.beans.suite.Stack;
import org.apache.commons.lang3.StringUtils;

public class VariableManipulationUtil {

    public static final String LAZY_START_PLACEHOLDER = "{{lazy.";
    public static final String LAZY_END_PLACEHOLDER = "}}";
    public static final String LAZY_GLOBAL_PLACEHOLDER = "global.";

    private VariableManipulationUtil() {
        //This is a private constructor
    }

    public static String getVariableValue(String givenValue, Stack stack) {
        if (StringUtils.isBlank(givenValue)) {
            return givenValue;
        }
        if (givenValue.contains(LAZY_START_PLACEHOLDER) && givenValue.contains(LAZY_END_PLACEHOLDER)) {
            String[] variables = StringUtils.substringsBetween(givenValue, LAZY_START_PLACEHOLDER, LAZY_END_PLACEHOLDER);
            for (String variable : variables) {
                String completePlaceholder = LAZY_START_PLACEHOLDER.concat(variable).concat(LAZY_END_PLACEHOLDER);
                if (completePlaceholder.startsWith(LAZY_START_PLACEHOLDER+LAZY_GLOBAL_PLACEHOLDER)) {
                    String variableKey = StringUtils.replace(variable, LAZY_GLOBAL_PLACEHOLDER, "", 1);
                    EnvironmentVariable variableValue = stack.getGlobalEnvironment().get(variableKey);
                    return StringUtils.replace(givenValue, completePlaceholder, variableValue.getValue());
                } else {
                    //TODO - should implement
                }
            }
        }

        return givenValue;
    }
}

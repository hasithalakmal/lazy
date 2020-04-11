package com.smile24es.lazy.utils;

import com.smile24es.lazy.beans.environment.EnvironmentVariable;
import com.smile24es.lazy.beans.suite.Stack;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.text.MessageFormat.format;

public class VariableManipulationUtil {

    public static final String LAZY_START_PLACEHOLDER = "{{lazy.";
    public static final String LAZY_END_PLACEHOLDER = "}}";
    public static final String LAZY_GLOBAL_PLACEHOLDER = "global.";
    private static final Logger LOGGER = LoggerFactory.getLogger(VariableManipulationUtil.class);

    private VariableManipulationUtil() {
        //This is a private constructor
    }

    public static String getVariableValue(String givenValue, Stack stack) {
        if (StringUtils.isBlank(givenValue)) {
            return givenValue;
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to manipulate variable value for given value - [{}]", givenValue);
        }
        if (givenValue.contains(LAZY_START_PLACEHOLDER) && givenValue.contains(LAZY_END_PLACEHOLDER)) {
            String[] variables = StringUtils.substringsBetween(givenValue, LAZY_START_PLACEHOLDER, LAZY_END_PLACEHOLDER);
            for (String variable : variables) {
                String completePlaceholder = LAZY_START_PLACEHOLDER.concat(variable).concat(LAZY_END_PLACEHOLDER);
                if (completePlaceholder.startsWith(LAZY_START_PLACEHOLDER + LAZY_GLOBAL_PLACEHOLDER)) {
                    String variableKey = StringUtils.replace(variable, LAZY_GLOBAL_PLACEHOLDER, "", 1);
                    EnvironmentVariable variableValue = stack.getGlobalEnvironment().get(variableKey);
                    return StringUtils.replace(givenValue, completePlaceholder, variableValue.getValue());
                } else {
                    String error = format("Invalid placeholder defined, hence no value defined [{0}]", givenValue);
                    LOGGER.error(error);
                }
            }
        }

        return givenValue;
    }

    public static boolean isEnvironmentVariable(String givenValue, Stack stack) {
        return StringUtils.isNotBlank(givenValue) && givenValue.contains(LAZY_START_PLACEHOLDER) && givenValue.contains(LAZY_END_PLACEHOLDER);
    }
}

package com.smile.lazy.utils;

import com.smile.lazy.beans.environment.EnvironmentVariable;
import com.smile.lazy.beans.suite.Stack;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

public class VariableManipulationUtil {

    public static String getVariableValue(String givenValue, Stack stack) {
        if (StringUtils.isBlank(givenValue)) {
            return givenValue;
        }
        if (givenValue.contains("{{lazy.") && givenValue.contains("}}")) {
            List<String> variables = Arrays.asList(StringUtils.substringsBetween(givenValue, "{{lazy.", "}}"));
            for (String variable : variables) {
                String completePlaceholder = "{{lazy.".concat(variable).concat("}}");
                if (completePlaceholder.startsWith("{{lazy.global.")) {
                    String variableKey = StringUtils.replace(variable, "global.", "", 1);
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

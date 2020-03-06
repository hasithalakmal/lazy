package com.smile.lazy.wrapper;

import com.smile.lazy.beans.enums.AssertionOperationEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.BodyValueAssertion;

public class Assert {

    public static AssertionRule notNullBodyValueAssertion(String jsonPath) {
        return notNullBodyValueAssertion("Body value not null assertion", jsonPath);
    }

    public static AssertionRule notNullBodyValueAssertion(String assertionName, String jsonPath) {
        return new AssertionRule(assertionName,
              DataSourceEnum.BODY, AssertionOperationEnum.NOT_NULL,
              new BodyValueAssertion(jsonPath));
    }

    public static AssertionRule equalBodyValueAssertion(String jsonPath, String value) {
        return new AssertionRule("Body value equal assertion",
              DataSourceEnum.BODY, AssertionOperationEnum.NOT_NULL,
              new BodyValueAssertion(jsonPath));
    }

    public static AssertionRule equalBodyValueAssertion(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

}

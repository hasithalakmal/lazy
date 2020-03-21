package com.smile.lazy.wrapper;

import com.smile.lazy.beans.enums.AssertionOperationEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.enums.DataTypeEnum;
import com.smile.lazy.beans.enums.UnitEnum;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionValue;
import com.smile.lazy.beans.suite.assertions.BodyValueAssertion;

public class Assert {

    private Assert() {
        //This is a private constructor
    }

    public static AssertionRule responseBodyNotNull() {
        return responseBodyNotNull("Response body not null assertion");
    }

    public static AssertionRule responseBodyNotNull(String assertionName) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.NOT_NULL);
    }

    public static AssertionRule notNullBodyValueAssertion(String jsonPath) {
        return notNullBodyValueAssertion("Body value not null assertion", jsonPath);
    }

    public static AssertionRule notNullBodyValueAssertion(String assertionName, String jsonPath) {
        return new AssertionRule(assertionName,
              DataSourceEnum.BODY, AssertionOperationEnum.NOT_NULL,
              new BodyValueAssertion(jsonPath));
    }

    public static AssertionRule equalBodyValueAssertion(String jsonPath, String value) {
        return equalBodyValueAssertion("Body value equal assertion", jsonPath, value);
    }

    public static AssertionRule equalBodyValueAssertion(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule responseTimeAssertionGreaterThanGivenMilliSeconds(String time) {
        return responseTimeAssertionGreaterThanGivenMilliSeconds("Response time assertion - milli seconds - less than", time);
    }

    public static AssertionRule responseTimeAssertionGreaterThanGivenMilliSeconds(String assertionName, String time) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_TIME,
              AssertionOperationEnum.LESS_THAN,
              new AssertionValue(time, DataTypeEnum.INTEGER, UnitEnum.MILLI_SECONDS));
    }

    public static AssertionRule responseCodeAssertion(String code) {
        return responseCodeAssertion("Response code assertion", code);
    }

    public static AssertionRule responseCodeAssertion(String assertionName, String code) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.EQUAL, new AssertionValue(code, DataTypeEnum.INTEGER));
    }

}

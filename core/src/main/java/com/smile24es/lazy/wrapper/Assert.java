package com.smile24es.lazy.wrapper;

import com.smile24es.lazy.beans.enums.AssertionOperationEnum;
import com.smile24es.lazy.beans.enums.DataSourceEnum;
import com.smile24es.lazy.beans.enums.UnitEnum;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import com.smile24es.lazy.beans.suite.assertions.AssertionValue;
import com.smile24es.lazy.beans.suite.assertions.BodyValueAssertion;

import java.util.List;

import static java.text.MessageFormat.format;

public class Assert {

    private static final String EQUAL_LOG_PATTERN = "[{0}] {1} == {2}";
    private static final String GREATER_THAN_LOG_PATTERN = "[{0}] {1} > {2}";
    private static final String GREATER_THAN_OR_EQUAL_LOG_PATTERN = "[{0}] {1} >= {2}";
    private static final String LESS_THAN_LOG_PATTERN = "[{0}] {1} < {2}";
    private static final String LESS_THAN_OR_EQUAL_LOG_PATTERN = "[{0}] {1} <= {2}";
    private static final String CONTAINS_LOG_PATTERN = "[{0}] {1} CONTAINS {2}";
    private static final String NOT_CONTAINS_LOG_PATTERN = "[{0}] {1} NOT CONTAINS {2}";
    private static final String BETWEEN_LOG_PATTERN = "[{0}] {1} <= {2}  <= {3}";
    private static final String LIST_EXACT_LOG_PATTERN = "{0} EXACTLY {1} ";
    private static final String LIST_ALL_LOG_PATTERN = "{0} ALL {1} ";
    private static final String LIST_ANY_LOG_PATTERN = "{0} ANY {1} ";
    private static final String STRING = "STRING";
    private static final String INTEGER = "INTEGER";
    private static final String DOUBLE = "DOUBLE";
    private static final String BOOLEAN = "BOOLEAN";

    private Assert() {
        //This is a private constructor
    }

    /////////////////////////////////////////////////////////////////
    //              Response Assertion                             //
    /////////////////////////////////////////////////////////////////
    public static AssertionRule responseBodyNotNull() {
        return responseBodyNotNull("Response body not null assertion");
    }

    public static AssertionRule responseBodyNotNull(String assertionName) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.NOT_NULL);
    }


    /////////////////////////////////////////////////////////////////
    //              Response Body value Assertion                  //
    /////////////////////////////////////////////////////////////////
    public static AssertionRule nullValue(String jsonPath) {
        return notNull(format("{0} == NULL", jsonPath), jsonPath);
    }

    public static AssertionRule nullValue(String assertionName, String jsonPath) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.NULL, new BodyValueAssertion(jsonPath));
    }

    public static AssertionRule notNull(String jsonPath) {
        return notNull(format("{0} != NULL", jsonPath), jsonPath);
    }

    public static AssertionRule notNull(String assertionName, String jsonPath) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.NOT_NULL, new BodyValueAssertion(jsonPath));
    }

    public static AssertionRule isKeyAvailable(String jsonPath) {
        return isKeyAvailable(format("{0} IS EXIST", jsonPath), jsonPath);
    }

    public static AssertionRule isKeyAvailable(String assertionName, String jsonPath) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.IS_KEY_AVAILABLE, new BodyValueAssertion(jsonPath));
    }

    public static AssertionRule isKeyUnavailable(String jsonPath) {
        return isKeyUnavailable(format("{0} IS NOT EXIST", jsonPath), jsonPath);
    }

    public static AssertionRule isKeyUnavailable(String assertionName, String jsonPath) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.IS_KEY_NOT_AVAILABLE, new BodyValueAssertion(jsonPath));
    }


    //Equal
    public static AssertionRule equal(String jsonPath, String value) {
        return equal(format(EQUAL_LOG_PATTERN, STRING, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule equal(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule equal(String jsonPath, Integer value) {
        return equal(format(EQUAL_LOG_PATTERN, INTEGER, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule equal(String assertionName, String jsonPath, Integer value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule equal(String jsonPath, Double value) {
        return equal(format(EQUAL_LOG_PATTERN, DOUBLE,  jsonPath, value), jsonPath, value);
    }

    public static AssertionRule equal(String assertionName, String jsonPath, Double value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule equal(String jsonPath, Boolean value) {
        return equal(format(EQUAL_LOG_PATTERN, BOOLEAN, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule equal(String assertionName, String jsonPath, Boolean value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }



    //Not Equal
    public static AssertionRule notEqual(String jsonPath, String value) {
        return notEqual(format(EQUAL_LOG_PATTERN, STRING, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule notEqual(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule notEqual(String jsonPath, Integer value) {
        return notEqual(format(EQUAL_LOG_PATTERN, INTEGER, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule notEqual(String assertionName, String jsonPath, Integer value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule notEqual(String jsonPath, Double value) {
        return notEqual(format(EQUAL_LOG_PATTERN, DOUBLE,  jsonPath, value), jsonPath, value);
    }

    public static AssertionRule notEqual(String assertionName, String jsonPath, Double value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule notEqual(String jsonPath, Boolean value) {
        return notEqual(format(EQUAL_LOG_PATTERN, BOOLEAN, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule notEqual(String assertionName, String jsonPath, Boolean value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    //Greater Than
    public static AssertionRule greaterThan(String jsonPath, String value) {
        return greaterThan(format(GREATER_THAN_LOG_PATTERN, STRING, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule greaterThan(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.GREATER_THAN,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule greaterThan(String jsonPath, Integer value) {
        return greaterThan(format(GREATER_THAN_LOG_PATTERN, INTEGER, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule greaterThan(String assertionName, String jsonPath, Integer value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.GREATER_THAN,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule greaterThan(String jsonPath, Double value) {
        return greaterThan(format(GREATER_THAN_LOG_PATTERN, DOUBLE, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule greaterThan(String assertionName, String jsonPath, Double value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.GREATER_THAN_OR_EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    //Greater Than or equal
    public static AssertionRule greaterThanOrEqual(String jsonPath, String value) {
        return greaterThanOrEqual(format(GREATER_THAN_OR_EQUAL_LOG_PATTERN, STRING, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule greaterThanOrEqual(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.GREATER_THAN_OR_EQUAL,
              new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule greaterThanOrEqual(String jsonPath, Integer value) {
        return greaterThanOrEqual(format(GREATER_THAN_OR_EQUAL_LOG_PATTERN, INTEGER, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule greaterThanOrEqual(String assertionName, String jsonPath, Integer value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.GREATER_THAN_OR_EQUAL, new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule greaterThanOrEqual(String jsonPath, Double value) {
        return greaterThanOrEqual(format(GREATER_THAN_OR_EQUAL_LOG_PATTERN, DOUBLE, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule greaterThanOrEqual(String assertionName, String jsonPath, Double value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.GREATER_THAN_OR_EQUAL, new BodyValueAssertion(jsonPath, value));
    }



    //Less Than
    public static AssertionRule lessThan(String jsonPath, String value) {
        return lessThan(format(LESS_THAN_LOG_PATTERN, STRING, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule lessThan(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.LESS_THAN, new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule lessThan(String jsonPath, Integer value) {
        return lessThan(format(LESS_THAN_LOG_PATTERN, INTEGER, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule lessThan(String assertionName, String jsonPath, Integer value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.LESS_THAN, new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule lessThan(String jsonPath, Double value) {
        return lessThan(format(LESS_THAN_LOG_PATTERN, DOUBLE, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule lessThan(String assertionName, String jsonPath, Double value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.LESS_THAN, new BodyValueAssertion(jsonPath, value));
    }



    //Less Than or equal
    public static AssertionRule lessThanOrEqual(String jsonPath, String value) {
        return lessThanOrEqual(format(LESS_THAN_OR_EQUAL_LOG_PATTERN, STRING, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule lessThanOrEqual(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.LESS_THAN_OR_EQUAL, new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule lessThanOrEqual(String jsonPath, Integer value) {
        return lessThanOrEqual(format(LESS_THAN_OR_EQUAL_LOG_PATTERN, INTEGER, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule lessThanOrEqual(String assertionName, String jsonPath, Integer value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.LESS_THAN_OR_EQUAL, new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule lessThanOrEqual(String jsonPath, Double value) {
        return lessThanOrEqual(format(LESS_THAN_OR_EQUAL_LOG_PATTERN, DOUBLE, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule lessThanOrEqual(String assertionName, String jsonPath, Double value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.LESS_THAN_OR_EQUAL, new BodyValueAssertion(jsonPath, value));
    }


    //Between
    public static AssertionRule between(String jsonPath, String value1, String value2) {
        return between(format(BETWEEN_LOG_PATTERN, STRING, value1, jsonPath, value2), jsonPath, value1, value2);
    }

    public static AssertionRule between(String assertionName, String jsonPath, String value1, String value2) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.LESS_THAN_OR_EQUAL,
              new BodyValueAssertion(jsonPath, value1, value2));
    }

    public static AssertionRule between(String jsonPath, Integer value1, Integer value2) {
        return between(format(BETWEEN_LOG_PATTERN, INTEGER, value1, jsonPath, value2), jsonPath, value1, value2);
    }

    public static AssertionRule between(String assertionName, String jsonPath, Integer value1, Integer value2) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.LESS_THAN_OR_EQUAL,
              new BodyValueAssertion(jsonPath, value1, value2));
    }

    public static AssertionRule between(String jsonPath, Double value1, Double value2) {
        return between(format(BETWEEN_LOG_PATTERN, DOUBLE, value1, jsonPath, value2), jsonPath, value1, value2);
    }

    public static AssertionRule between(String assertionName, String jsonPath, Double value1, Double value2) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.BETWEEN,
              new BodyValueAssertion(jsonPath, value1, value2));
    }

    //Contains
    public static AssertionRule contains(String jsonPath, String value) {
        return contains(format(CONTAINS_LOG_PATTERN, STRING, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule contains(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.CONTAINS, new BodyValueAssertion(jsonPath, value));
    }

    public static AssertionRule notContains(String jsonPath, String value) {
        return notContains(format(NOT_CONTAINS_LOG_PATTERN, STRING, jsonPath, value), jsonPath, value);
    }

    public static AssertionRule notContains(String assertionName, String jsonPath, String value) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.NOT_CONTAINS, new BodyValueAssertion(jsonPath, value));
    }

    /////////////////////////////////////////////////////////////////
    //              Response Body List Assertions                  //
    /////////////////////////////////////////////////////////////////
    public static AssertionRule containsExactly(String jsonPath, List values) {
        return containsExactly(format(LIST_EXACT_LOG_PATTERN, jsonPath, values), jsonPath, values);
    }

    public static AssertionRule containsExactly(String assertionName, String jsonPath, List values) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.CONTAINS_EXACTLY, new BodyValueAssertion(jsonPath, values));
    }

    public static AssertionRule containsAll(String jsonPath, List values) {
        return containsAll(format(LIST_ALL_LOG_PATTERN, jsonPath, values), jsonPath, values);
    }

    public static AssertionRule containsAll(String assertionName, String jsonPath, List values) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.CONTAINS_ALL, new BodyValueAssertion(jsonPath, values));
    }

    public static AssertionRule containsAny(String jsonPath, List values) {
        return containsAny(format(LIST_ANY_LOG_PATTERN, jsonPath, values), jsonPath, values);
    }

    public static AssertionRule containsAny(String assertionName, String jsonPath, List values) {
        return new AssertionRule(assertionName, DataSourceEnum.BODY, AssertionOperationEnum.CONTAINS_ANY, new BodyValueAssertion(jsonPath, values));
    }

    /////////////////////////////////////////////////////////////////
    //              Response Time Assertion                        //
    /////////////////////////////////////////////////////////////////
    public static AssertionRule responseTimeLessThan(String time) {
        return responseTimeLessThan(format("Response time < {0}ms", time), time);
    }

    public static AssertionRule responseTimeLessThan(String assertionName, String time) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_TIME,
              AssertionOperationEnum.LESS_THAN, new AssertionValue(time, UnitEnum.MILLI_SECONDS));
    }

    public static AssertionRule responseTimeLessThanOrEqual(String time) {
        return responseTimeLessThanOrEqual(format("Response time <= {0}ms", time), time);
    }

    public static AssertionRule responseTimeLessThanOrEqual(String assertionName, String time) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_TIME,
              AssertionOperationEnum.LESS_THAN_OR_EQUAL, new AssertionValue(time, UnitEnum.MILLI_SECONDS));
    }

    public static AssertionRule responseTimeGreaterThan(String time) {
        return responseTimeGreaterThan(format("Response time > {0}ms", time), time);
    }

    public static AssertionRule responseTimeGreaterThan(String assertionName, String time) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_TIME,
              AssertionOperationEnum.GREATER_THAN, new AssertionValue(time, UnitEnum.MILLI_SECONDS));
    }

    public static AssertionRule responseTimeGreaterThanOrEqual(String time) {
        return responseTimeGreaterThanOrEqual(format("Response time > {0}ms", time), time);
    }

    public static AssertionRule responseTimeGreaterThanOrEqual(String assertionName, String time) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_TIME,
              AssertionOperationEnum.GREATER_THAN_OR_EQUAL, new AssertionValue(time, UnitEnum.MILLI_SECONDS));
    }

    /////////////////////////////////////////////////////////////////
    //              Response Code Assertion                        //
    /////////////////////////////////////////////////////////////////
    public static AssertionRule responseCodeEqual(Integer code) {
        return responseCodeEqual(format("Response code == [{0}]", code), code);
    }

    public static AssertionRule responseCodeEqual(String assertionName, Integer code) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.EQUAL, new AssertionValue(code));
    }

    public static AssertionRule responseCodeNotEqual(Integer code) {
        return responseCodeNotEqual(format("Response code != [{0}]", code), code);
    }

    public static AssertionRule responseCodeNotEqual(String assertionName, Integer code) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.NOT_EQUAL, new AssertionValue(code));
    }

    public static AssertionRule responseCodeLessThan(Integer code) {
        return responseCodeLessThan(format("Response code < [{0}]", code), code);
    }

    public static AssertionRule responseCodeLessThan(String assertionName, Integer code) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.LESS_THAN, new AssertionValue(code));
    }

    public static AssertionRule responseCodeLessThanOrEqual(Integer code) {
        return responseCodeLessThan(format("Response code <= [{0}]", code), code);
    }

    public static AssertionRule responseCodeLessThanOrEqual(String assertionName, Integer code) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.LESS_THAN_OR_EQUAL, new AssertionValue(code));
    }

    public static AssertionRule responseCodeGreaterThan(Integer code) {
        return responseCodeGreaterThan(format("Response code > [{0}]", code), code);
    }

    public static AssertionRule responseCodeGreaterThan(String assertionName, Integer code) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.GREATER_THAN, new AssertionValue(code));
    }

    public static AssertionRule responseCodeGreaterThanOrEqual(Integer code) {
        return responseCodeGreaterThanOrEqual(format("Response code >= [{0}]", code), code);
    }

    public static AssertionRule responseCodeGreaterThanOrEqual(String assertionName, Integer code) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.GREATER_THAN_OR_EQUAL, new AssertionValue(code));
    }

    public static AssertionRule responseCodeBetween(Integer code1, Integer code2) {
        return responseCodeBetween(format("[{0}] <= response code <= [{1}]", code1, code2), code1, code2);
    }

    public static AssertionRule responseCodeBetween(String assertionName, Integer code1, Integer code2) {
        return new AssertionRule(assertionName, DataSourceEnum.RESPONSE_CODE, AssertionOperationEnum.GREATER_THAN_OR_EQUAL, new AssertionValue(code1, code2));
    }
}
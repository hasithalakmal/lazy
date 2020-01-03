package com.smile.lazy.manager;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.smile.lazy.beans.enums.AssertionOperationEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.result.AssertionResult;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.Assertions.AssertionRule;
import com.smile.lazy.beans.suite.Assertions.AssertionValue;
import com.smile.lazy.beans.suite.Assertions.BodyValueAssertion;
import com.smile.lazy.exception.LazyException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class AssertionGenerator {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApiCallGenerator.class);

  public void executeApiCall(ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
      AssertionResultList assertionResultList) throws LazyException {

    if (apiCall.getDefaultAssertionRuleGroup() != null) {
      for (AssertionRule assertionRule : apiCall.getDefaultAssertionRuleGroup().getAssertionRules()) {
        executeAssertion(apiCall, lazyApiCallResponse, assertionResultList, assertionRule);
      }
    }

    if (apiCall.getAssertionRuleGroup() != null) {
      for (AssertionRule assertionRule : apiCall.getAssertionRuleGroup().getAssertionRules()) {
        executeAssertion(apiCall, lazyApiCallResponse, assertionResultList, assertionRule);
      }
    }

  }

  private void executeAssertion(ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
      AssertionResultList assertionResultList, AssertionRule assertionRule) throws LazyException {
    DataSourceEnum dataSource = assertionRule.getDataSource();
    AssertionOperationEnum operation = assertionRule.getAssertionOperation();
    AssertionResult assertionResult = null;
    if (dataSource == DataSourceEnum.RESPONSE_CODE) {
      assertionResult = responseCodeAssertion(apiCall, lazyApiCallResponse, assertionRule, operation);
    } else if (dataSource == DataSourceEnum.RESPONSE_TIME) {
      assertionResult = responseTimeAssertion(apiCall, lazyApiCallResponse, assertionRule, operation);
    } else if (dataSource == DataSourceEnum.RESPONSE_CODE_NAME) {
      LOGGER.warn("NOT IMPLEMENTED");
    } else if (dataSource == DataSourceEnum.RESPONSE_HEADER) {
      LOGGER.warn("NOT IMPLEMENTED");
    } else if (dataSource == DataSourceEnum.BODY) {
      String responseBody = lazyApiCallResponse.getResponseBody();
      assertionResult = new AssertionResult(1, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(),
          responseBody);
      AssertionValue assertionValue = assertionRule.getAssertionValue();
      if (assertionValue == null) {
        if (operation == AssertionOperationEnum.NULL) {
          assertionResult.setPass(StringUtils.isBlank(responseBody));
        } else if (operation == AssertionOperationEnum.NOT_NULL) {
          assertionResult.setPass(StringUtils.isNotBlank(responseBody));
        } else {
          LOGGER.warn("Operation is not supported for given assertion values");
        }
      } else {
        if (assertionValue instanceof BodyValueAssertion) {
          BodyValueAssertion bodyValueAssertion = (BodyValueAssertion) assertionValue;
          Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
          String actualValue = JsonPath.read(document, bodyValueAssertion.getJsonPath());
          String expectedValue = bodyValueAssertion.getExpectedValue1();

          if (operation == AssertionOperationEnum.NULL) {
            assertionResult.setPass(StringUtils.isBlank(actualValue));
          } else if (operation == AssertionOperationEnum.NOT_NULL) {
            assertionResult.setPass(StringUtils.isNotBlank(actualValue));
          } else if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(actualValue.equals(expectedValue));
          } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(!actualValue.equals(expectedValue));
          } else if (operation == AssertionOperationEnum.CONTAINS) {
            assertionResult.setPass(actualValue.contains(expectedValue));
          } else if (operation == AssertionOperationEnum.NOT_CONTAINS) {
            assertionResult.setPass(!actualValue.contains(expectedValue));
          } else {
            LOGGER.warn("Operation is not supported for given body assertion values");
          }

        } else {
          String expectedValue = assertionValue.getExpectedValue1();
          if (operation == AssertionOperationEnum.EQUAL) {
            assertionResult.setPass(responseBody.equals(expectedValue));
          } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
            assertionResult.setPass(!responseBody.equals(expectedValue));
          }
        }
      }


    } else {
      LOGGER.warn("Assertion datasource is not supported");
    }
    assertionResultList.getResults().add(assertionResult);
  }

  private AssertionResult responseTimeAssertion(ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
      AssertionRule assertionRule, AssertionOperationEnum operation) {
    AssertionResult assertionResult;
    AssertionValue assertionValue = assertionRule.getAssertionValue();
    String expectedValue = assertionValue.getExpectedValue1();
    long responseTime = lazyApiCallResponse.getResponseTime();
    assertionResult = new AssertionResult(1, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(),
        Long.toString(responseTime));
    if (operation == AssertionOperationEnum.LESS_THAN) {
      assertionResult.setPass(responseTime < Integer.parseInt(expectedValue));
    } else if (operation == AssertionOperationEnum.LESS_THAN_OR_EQUAL) {
      assertionResult.setPass(responseTime <= Integer.parseInt(expectedValue));
    } else if (operation == AssertionOperationEnum.GREATER_THAN) {
      assertionResult.setPass(responseTime > Integer.parseInt(expectedValue));
    } else if (operation == AssertionOperationEnum.GREATER_THAN_OR_EQUAL) {
      assertionResult.setPass(responseTime >= Integer.parseInt(expectedValue));
    } else if (operation == AssertionOperationEnum.BETWEEN) {
      String expectedValue2 = assertionValue.getExpectedValue2();
      assertionResult
          .setPass(responseTime > Long.parseLong(expectedValue) && responseTime < Long.parseLong(expectedValue2));
    } else {
      LOGGER.warn("Assertion operation is not supported");
    }
    return assertionResult;
  }

  private AssertionResult responseCodeAssertion(ApiCall apiCall, LazyApiCallResponse lazyApiCallResponse,
      AssertionRule assertionRule, AssertionOperationEnum operation) {
    AssertionResult assertionResult;
    AssertionValue assertionValue = assertionRule.getAssertionValue();
    String expectedValue = assertionValue.getExpectedValue1();
    int responseCode = lazyApiCallResponse.getCloseableHttpResponse().getStatusLine().getStatusCode();
    assertionResult = new AssertionResult(1, apiCall.getApiCallId(), assertionRule.getAssertionRuleId(),
        Integer.toString(responseCode));
    if (operation == AssertionOperationEnum.EQUAL) {
      assertionResult.setPass(responseCode == Integer.parseInt(expectedValue));
    } else if (operation == AssertionOperationEnum.NOT_EQUAL) {
      assertionResult.setPass(responseCode != Integer.parseInt(expectedValue));
    } else if (operation == AssertionOperationEnum.BETWEEN) {
      String expectedValue2 = assertionValue.getExpectedValue2();
      assertionResult
          .setPass(responseCode > Integer.parseInt(expectedValue) && responseCode < Integer.parseInt(expectedValue2));
    } else {
      LOGGER.warn("Assertion operation is not supported");
    }
    return assertionResult;
  }
}

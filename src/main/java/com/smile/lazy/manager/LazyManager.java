package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.exception.LazyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LazyManager {

  private static final Logger LOGGER = LoggerFactory.getLogger(LazyManager.class);
  @Autowired
  AssertionGenerator assertionGenerator;
  @Autowired
  private ApiCallGenerator apiCallGenerator;

  public AssertionResultList test(LazySuite lazySuite) throws LazyException {
    AssertionResultList assertionResultList = new AssertionResultList();
    for (TestSuite testSuite : lazySuite.getTestSuites()) {
      for (TestScenario testScenario : testSuite.getTestScenarios()) {
        for (TestCase testCase : testScenario.getTestCases()) {
          for (ApiCall apiCall : testCase.getApiCalls()) {
            LazyApiCallResponse response = apiCallGenerator.executeApiCall(apiCall);
            assertionGenerator.executeApiCall(apiCall, response, assertionResultList);
          }
        }
      }
    }
    return assertionResultList;
  }
}

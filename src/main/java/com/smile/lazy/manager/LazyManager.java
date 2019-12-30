package com.smile.lazy.manager;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.response.LazyResponse;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.Header;
import com.smile.lazy.beans.suite.HeaderGroup;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.exception.LazyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.smile.lazy.sample.SampleTestSuite1.populateSampleTestSuite;

@Service
public class LazyManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManager.class);

    @Autowired
    private ApiCallGenerator apiCallGenerator;

    public void test(LazySuite lazySuite) throws LazyException {
        for (TestSuite testSuite : lazySuite.getTestSuites()) {
            for (TestScenario testScenario : testSuite.getTestScenarios()) {
                for (TestCase testCase : testScenario.getTestCases()) {
                    for (ApiCall apiCall : testCase.getApiCalls()) {
                        LazyResponse response = apiCallGenerator.executeApiCall(apiCall);
                    }
                }
            }
        }

    }
}

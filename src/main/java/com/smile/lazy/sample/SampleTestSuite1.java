package com.smile.lazy.sample;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.Header;
import com.smile.lazy.beans.suite.HeaderGroup;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;

public class SampleTestSuite1 {

    public static LazySuite populateSampleTestSuite() {

        HeaderGroup defaultHeaderGroup = new HeaderGroup(1, "Simple Header Group");
        Header header1 = new Header(1, "Accept Header", "accept", "application/json");
        Header header2 = new Header(2, "Content type Header", "content-type", "application/json");
        defaultHeaderGroup.getHeaders().add(header1);
        defaultHeaderGroup.getHeaders().add(header2);

        DefaultValues defaultValues = new DefaultValues("http", "localhost", 8080, "account-api", defaultHeaderGroup, "GET");


        ApiCall apiCall1 = new ApiCall(1, "Create Account", defaultValues);
        apiCall1.setUri("service/accounts");
        apiCall1.setHttpMethod("POST");
        apiCall1.setRequestBody("{\"status\":\"ACTIVE\",\"createdBy\":\"12345\",\"parentId\":\"1\",\"enterpriseId\":\"1\",\"accountName\":\"Sathara-1577641690\",\"ownerName\":\"Hasitha-1577641690\",\"versionId\":\"1.0.0\",\"settings\":[{\"key\":\"setting1\",\"value\":\"1577641690\"},{\"key\":\"setting2\",\"value\":\"1577641690\"}]}");

        ApiCall apiCall2 = new ApiCall(2, "Get Account by Id", defaultValues);
        apiCall2.setUri("service/accounts/34");

        TestCase testCase1 = new TestCase(1, "Create Account successfully");
        testCase1.getApiCalls().add(apiCall1);
        testCase1.getApiCalls().add(apiCall2);

        TestScenario testScenario1 = new TestScenario(1, "Create Account");
        testScenario1.getTestCases().add(testCase1);


        TestSuite testSuite1 = new TestSuite(1, "Account Test Suite");
        testSuite1.getTestScenarios().add(testScenario1);

        LazySuite lazySuite = new LazySuite(1, "Sample lazy suite 1", defaultValues);
        lazySuite.getTestSuites().add(testSuite1);

        return lazySuite;

    }
}

package com.smile.lazy;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.executor.ApiCallExecutionData;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.beans.executor.TestCaseExecutionData;
import com.smile.lazy.beans.executor.TestScenarioExecutionData;
import com.smile.lazy.beans.executor.TestSuiteExecutionData;
import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.Stack;
import com.smile.lazy.beans.suite.TestCase;
import com.smile.lazy.beans.suite.TestScenario;
import com.smile.lazy.beans.suite.TestSuite;
import com.smile.lazy.manager.ApiCallManager;
import com.smile.lazy.manager.LazyManager;
import com.smile.lazy.manager.TestCaseManager;
import com.smile.lazy.manager.TestScenarioManager;
import com.smile.lazy.manager.TestSuiteManager;
import com.smile.lazy.suite.sample.SampleLazySuite1;
import com.smile.lazy.suite.sample.suites.AccountApiTestSuite;
import com.smile.lazy.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import static com.smile.lazy.suite.sample.apicall.AccountApiCalls.createAccountApiCall;
import static com.smile.lazy.suite.sample.scenarios.CreateAccountTestScenario.getAccountCreationTestScenario;
import static com.smile.lazy.suite.sample.testcase.CreateAccountSuccessTestCase.getCreateAccountTestCase;
import static com.smile.lazy.utils.SampleDefaultValues.createDefaultValues;

@SpringBootTest(classes = LazyApplication.class)
public class LazyManagerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManagerTest.class);
    public static final String EXECUTION_RESULTS_LOG = "Execution results \n [{}]";
    public static final String SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED = "Success scenarios should not be failed";

    @Autowired
    private LazyManager lazyManager;

    @Autowired
    private TestSuiteManager testSuiteManager;

    @Autowired
    private TestScenarioManager testScenarioManager;

    @Autowired
    private TestCaseManager testCaseManager;

    @Autowired
    private ApiCallManager apiCallManager;

    @Test
    public void executeSampleLazySuite() {
        try {
            LazySuite sampleLazySuite = SampleLazySuite1.populateSampleLazySuite();
            LazyExecutionData results = lazyManager.executeLazySuite(sampleLazySuite);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getTestSuiteExecutionData());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.debug(EXECUTION_RESULTS_LOG, resultString);

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

    @Test
    public void executeSampleTestSuite() {
        try {
            TestSuite sampleTestSuite = AccountApiTestSuite.getAccountApiTestSuite();
            DefaultValues defaultValues = createDefaultValues();
            Stack stack = new Stack(defaultValues);
            TestSuiteExecutionData results = testSuiteManager.executeTestSuite(sampleTestSuite, stack);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getTestScenarioExecutionData());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.debug(EXECUTION_RESULTS_LOG, resultString);

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

    @Test
    public void executeSampleTestScenario() {
        try {
            TestScenario sampleTestScenario = getAccountCreationTestScenario();
            DefaultValues defaultValues = createDefaultValues();
            Stack stack = new Stack(defaultValues);
            TestScenarioExecutionData results = testScenarioManager.executeTestScenario(sampleTestScenario, stack);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getTestCaseExecutionDataList());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.debug(EXECUTION_RESULTS_LOG, resultString);

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

    @Test
    public void executeSampleTestCase() {
        try {
            TestCase sampleTestCase = getCreateAccountTestCase();
            DefaultValues defaultValues = createDefaultValues();
            Stack stack = new Stack(defaultValues);
            TestCaseExecutionData results = testCaseManager.executeTestCase(sampleTestCase, stack);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getApiCallExecutionDataList());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.debug(EXECUTION_RESULTS_LOG, resultString);

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

    @Test
    public void executeSampleApiCall() {
        try {
            ApiCall sampleApiCall = createAccountApiCall();
            DefaultValues defaultValues = createDefaultValues();
            Stack stack = new Stack(defaultValues);
            ApiCallExecutionData results = apiCallManager.executeApiCall(sampleApiCall, stack);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getAssertionExecutionDataList());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.debug(EXECUTION_RESULTS_LOG, resultString);

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

}

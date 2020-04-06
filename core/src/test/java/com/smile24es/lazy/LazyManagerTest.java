package com.smile24es.lazy;

import com.smile24es.lazy.beans.DefaultValues;
import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.executor.ApiCallExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.beans.executor.TestCaseExecutionData;
import com.smile24es.lazy.beans.executor.TestScenarioExecutionData;
import com.smile24es.lazy.beans.executor.TestSuiteExecutionData;
import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.manager.ApiCallManager;
import com.smile24es.lazy.manager.LazyManager;
import com.smile24es.lazy.manager.TestCaseManager;
import com.smile24es.lazy.manager.TestScenarioManager;
import com.smile24es.lazy.manager.TestSuiteManager;
import com.smile24es.lazy.suite.sample1.SampleLazySuite1;
import com.smile24es.lazy.suite.sample1.suites.AccountApiTestSuite;
import com.smile24es.lazy.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.util.Arrays;

import static com.smile24es.lazy.suite.sample1.apicall.AccountApiCalls.createAccountApiCall;
import static com.smile24es.lazy.suite.sample1.scenarios.CreateAccountTestScenario.getAccountCreationTestScenario;
import static com.smile24es.lazy.suite.sample1.testcase.CreateAccountSuccessTestCase.getCreateAccountTestCase;
import static com.smile24es.lazy.common.SampleDefaultValues.createDefaultValues;

@SpringBootTest(classes = LazyApplication.class)
public class LazyManagerTest {

    public static final String EXECUTION_RESULTS_LOG = "Execution results \n [{}]";
    public static final String SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED = "Success scenarios should not be failed";
    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManagerTest.class);
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
            defaultValues.setContextPath("account-api");
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
            defaultValues.setContextPath("account-api");
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
            defaultValues.setContextPath("account-api");
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

    @Test
    public void executeTestSuiteByApiCallGroupName() {
        try {
            LazySuite sampleLazySuite = SampleLazySuite1.populateSampleLazySuite();
            LazyExecutionGroup lazyExecutionGroup = new LazyExecutionGroup();
            lazyExecutionGroup.setApiCallExecutionGroupNames(Arrays.asList("BVT"));
            LazyExecutionData results = lazyManager.executeLazySuite(sampleLazySuite, lazyExecutionGroup);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getTestSuiteExecutionData());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.debug(EXECUTION_RESULTS_LOG, resultString);

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

    @Test
    public void executeTestSuiteByApiCaseGroupName() {
        try {
            LazySuite sampleLazySuite = SampleLazySuite1.populateSampleLazySuite();
            LazyExecutionGroup lazyExecutionGroup = new LazyExecutionGroup();
            lazyExecutionGroup.setTestCaseExecutionGroupNames(Arrays.asList("BVT_TC"));
            LazyExecutionData results = lazyManager.executeLazySuite(sampleLazySuite, lazyExecutionGroup);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getTestSuiteExecutionData());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.debug(EXECUTION_RESULTS_LOG, resultString);

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

    @Test
    public void executeTestSuiteCombination() {
        try {
            LazySuite sampleLazySuite = SampleLazySuite1.populateSampleLazySuite();
            LazyExecutionGroup lazyExecutionGroup = new LazyExecutionGroup();
            lazyExecutionGroup.setTestCaseExecutionGroupNames(Arrays.asList("BVT_TC"));
            lazyExecutionGroup.setApiCallExecutionGroupNames(Arrays.asList("BVT"));
            LazyExecutionData results = lazyManager.executeLazySuite(sampleLazySuite, lazyExecutionGroup);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getTestSuiteExecutionData());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.debug(EXECUTION_RESULTS_LOG, resultString);

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

}

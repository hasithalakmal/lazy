package com.smile24es.lazy.manager.handlers;

import com.smile24es.lazy.beans.enums.AssertionResultStatus;
import com.smile24es.lazy.beans.executor.ApiCallExecutionData;
import com.smile24es.lazy.beans.executor.AssertionExecutionData;
import com.smile24es.lazy.beans.executor.AssertionResult;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.beans.executor.TestCaseExecutionData;
import com.smile24es.lazy.beans.executor.TestScenarioExecutionData;
import com.smile24es.lazy.beans.executor.TestSuiteExecutionData;
import com.smile24es.lazy.beans.suite.Header;
import com.smile24es.lazy.beans.suite.assertions.AssertionValue;
import com.smile24es.lazy.manager.impl.LazyManagerImpl;
import com.smile24es.lazy.reports.ApiCallReport;
import com.smile24es.lazy.reports.AssertionResultObject;
import com.smile24es.lazy.reports.LazyReport;
import com.smile24es.lazy.reports.TestCaseReport;
import com.smile24es.lazy.reports.TestScenarioReport;
import com.smile24es.lazy.reports.TestSuiteReport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.smile24es.lazy.utils.MathsUtil.getPercentage;
import static java.text.MessageFormat.format;

@Repository
public class ReportDataHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReportDataHandler.class);

    public LazyReport populateReportData(LazyExecutionData lazyExecutionData) {
        //Populate lazy Report object
        LazyReport lazyReport = new LazyReport();

        int totalTestSuite = 0;
        int passTestSuite = 0;
        int notPassTestSuite = 0;
        int executedTestSuite = 0;
        int skippedTestSuite = 0;
        int failedTestSuite = 0;
        int invalidTestSuite = 0;

        int lazy_totalTestScenario = 0;
        int lazy_passTestScenario = 0;
        int lazy_notPassTestScenario = 0;
        int lazy_executedTestScenario = 0;
        int lazy_skippedTestScenario = 0;
        int lazy_failedTestScenario = 0;
        int lazy_invalidTestScenario = 0;

        int lazy_totalTestCases = 0;
        int lazy_passTestCases = 0;
        int lazy_notPassTestCases = 0;
        int lazy_executedTestCases = 0;
        int lazy_skippedTestCases = 0;
        int lazy_failedTestCases = 0;
        int lazy_invalidTestCases = 0;

        int lazy_totalApiCalls = 0;
        int lazy_passApiCalls = 0;
        int lazy_notPassApiCalls = 0;
        int lazy_executedApiCalls = 0;
        int lazy_skippedApiCalls = 0;
        int lazy_failedApiCalls = 0;
        int lazy_invalidApiCalls = 0;
        for (TestSuiteExecutionData testSuiteExecutionData : lazyExecutionData.getTestSuiteExecutionData()) {

            TestSuiteReport testSuiteReport = new TestSuiteReport(testSuiteExecutionData.getTestSuiteId());
            testSuiteReport.setTestSuiteName(testSuiteExecutionData.getTestSuiteName());

            int totalTestScenario = 0;
            int passTestScenario = 0;
            int notPassTestScenario = 0;
            int executedTestScenario = 0;
            int skippedTestScenario = 0;
            int failedTestScenario = 0;
            int invalidTestScenario = 0;

            int tsu_totalTestCases = 0;
            int tsu_passTestCases = 0;
            int tsu_notPassTestCases = 0;
            int tsu_executedTestCases = 0;
            int tsu_skippedTestCases = 0;
            int tsu_failedTestCases = 0;
            int tsu_invalidTestCases = 0;


            int tsu_totalApiCalls = 0;
            int tsu_passApiCalls = 0;
            int tsu_notPassApiCalls = 0;
            int tsu_executedApiCalls = 0;
            int tsu_skippedApiCalls = 0;
            int tsu_failedApiCalls = 0;
            int tsu_invalidApiCalls = 0;
            for (TestScenarioExecutionData testScenarioExecutionData : testSuiteExecutionData.getTestScenarioExecutionData()) {
                TestScenarioReport testScenarioReport = new TestScenarioReport(testScenarioExecutionData.getTestScenarioId());
                testScenarioReport.setTestScenarioName(testScenarioExecutionData.getTestScenarioName());

                int totalTestCase = 0;
                int passTestCase = 0;
                int notPassTestCase = 0;
                int executedTestCase = 0;
                int skippedTestCase = 0;
                int failedTestCase = 0;
                int invalidTestCase = 0;

                int ts_totalApiCalls = 0;
                int ts_passApiCalls = 0;
                int ts_notPassApiCalls = 0;
                int ts_executedApiCalls = 0;
                int ts_skippedApiCalls = 0;
                int ts_failedApiCalls = 0;
                int ts_invalidApiCalls = 0;
                for (TestCaseExecutionData testCaseExecutionData : testScenarioExecutionData.getTestCaseExecutionDataList()) {
                    TestCaseReport testCaseReport = new TestCaseReport(testCaseExecutionData.getTestCaseId());
                    testCaseReport.setTestCaseName(testCaseExecutionData.getTestCaseName());
                    int totalApiCalls = 0;
                    int passApiCalls = 0;
                    int notPassApiCalls = 0;
                    int executedApiCalls = 0;
                    int skippedApiCalls = 0;
                    int failedApiCalls = 0;
                    int invalidApiCalls = 0;
                    for (ApiCallExecutionData apiCallExecutionData : testCaseExecutionData.getApiCallExecutionDataList()) {
                        ApiCallReport apiCallReport = new ApiCallReport(apiCallExecutionData.getApiCallId());
                        apiCallReport.setName(apiCallExecutionData.getApiCallName());
                        apiCallReport.setHttpMethod(apiCallExecutionData.getHttpMethod());
                        apiCallReport.setUrl(apiCallExecutionData.getUri());
                        String  headerString = "";
                        for (Header header: apiCallExecutionData.getHeaderGroup().getHeaders()) {
                            headerString += format("[{0}:{1}]", header.getKey(), header.getValue());
                        }
                        apiCallReport.setHeaders(headerString);
                        apiCallReport.setRequestBody(apiCallExecutionData.getRequestBody());
                        apiCallReport.setExecutionTime(apiCallExecutionData.getResponse().getResponseTime()+"ms");
                        apiCallReport.setHttpStatusCode(apiCallExecutionData.getResponse().getCloseableHttpResponse().getStatusLine().getStatusCode());
                        apiCallReport.setResponse(apiCallExecutionData.getResponse().getResponseBody());


                        int totalAssertions = 0;
                        int passAssertions = 0;
                        int notPassAssertions = 0;
                        int executedAssertions = 0;
                        int skippedAssertions = 0;
                        int failedAssertions = 0;
                        int invalidAssertions = 0;
                        for (AssertionExecutionData assertionExecutionData : apiCallExecutionData.getAssertionExecutionDataList()) {
                            AssertionResult assertionResult = assertionExecutionData.getAssertionResult();
                            AssertionResultObject assertionResultObject = new AssertionResultObject(assertionResult.getResultId());
                            assertionResultObject.setName(assertionResult.getAssertionRule().getAssertionRuleName());
                            assertionResultObject.setActualValue(assertionResult.getActualValue());
                            AssertionValue assertionValue = assertionResult.getAssertionRule().getAssertionValue();
                            assertionResultObject.setExpectedValue(assertionValue == null ? null : assertionValue.getExpectedStringValue1());
                            String assertionStatus = assertionResult.getAssertionStatus();
                            assertionResultObject.setStatus(assertionStatus);
                            boolean isPass = assertionResult.getPass();
                            assertionResultObject.setIsPass(assertionResult.getPass().toString());
                            assertionResultObject.setNotes(assertionResult.getAssertionNotes());

                            //handle summery values
                            totalAssertions++;
                            if (isPass) {
                                passAssertions++;
                            } else {
                                notPassAssertions++;
                            }

                            if (assertionStatus.equals(AssertionResultStatus.EXECUTED.getValue())) {
                                executedAssertions++;
                            } else if (assertionStatus.equals(AssertionResultStatus.SKIPPED.getValue())){
                                skippedAssertions++;
                            } else if (assertionStatus.equals(AssertionResultStatus.FAILED.getValue())){
                                failedAssertions++;
                            } else if (assertionStatus.equals(AssertionResultStatus.INVALID_RULE.getValue())){
                                invalidAssertions++;
                            } else {
                                LOGGER.warn("Unexpected assertion execution status found");
                            }
                            
                            apiCallReport.getAssertions().add(assertionResultObject);
                        }

                        apiCallReport.setTotalAssertionsCount(totalAssertions);
                        apiCallReport.setPassAssertionsCount(passAssertions);
                        apiCallReport.setNotPassAssertionsCount(notPassAssertions);
                        apiCallReport.setPassAssertionsPercentage(getPercentage(totalAssertions, passAssertions));
                        apiCallReport.setNotPassAssertionsPercentage(getPercentage(totalAssertions, notPassAssertions));

                        apiCallReport.setTotalExecutedAssertionsCount(executedAssertions);
                        apiCallReport.setTotalSkippedAssertionsCount(skippedAssertions);
                        apiCallReport.setTotalFailedAssertionsCount(failedAssertions);
                        apiCallReport.setTotalInvalidAssertionsCount(invalidAssertions);

                        apiCallReport.setExecutedAssertionsPercentage(getPercentage(totalAssertions, executedAssertions));
                        apiCallReport.setSkippedAssertionsPercentage(getPercentage(totalAssertions, skippedAssertions));
                        apiCallReport.setFailedAssertionsPercentage(getPercentage(totalAssertions, failedAssertions));
                        apiCallReport.setInvalidAssertionsPercentage(getPercentage(totalAssertions, invalidAssertions));
                        

                        totalApiCalls++;
                        ts_totalApiCalls++;
                        tsu_totalApiCalls++;
                        lazy_totalApiCalls++;

                        if (notPassAssertions == 0) {
                            passApiCalls++;
                            ts_passApiCalls++;
                            tsu_passApiCalls++;
                            lazy_passApiCalls++;
                        } else {
                            notPassApiCalls++;
                            ts_notPassApiCalls++;
                            tsu_notPassApiCalls++;
                            lazy_notPassApiCalls++;
                        }

                        if (invalidAssertions != 0) {
                            invalidApiCalls++;
                            ts_invalidApiCalls++;
                            tsu_invalidApiCalls++;
                            lazy_invalidApiCalls++;
                        } else if (failedAssertions != 0) {
                            failedApiCalls++;
                            ts_failedApiCalls++;
                            tsu_failedApiCalls++;
                            lazy_failedApiCalls++;
                        } else if (skippedAssertions != 0) {
                            skippedApiCalls++;
                            ts_skippedApiCalls++;
                            tsu_skippedApiCalls++;
                            lazy_skippedApiCalls++;
                        } else if (totalAssertions == executedAssertions) {
                            executedApiCalls++;
                            ts_executedApiCalls++;
                            tsu_executedApiCalls++;
                            lazy_executedApiCalls++;
                        } else {
                            LOGGER.error("Unexpected result counts found");
                        }

                        testCaseReport.getApiCallReports().add(apiCallReport);
                    }

                    testCaseReport.setTotalApiCallCount(totalApiCalls);
                    testCaseReport.setPassApiCallCount(passApiCalls);
                    testCaseReport.setNotPassApiCallCount(notPassApiCalls);
                    testCaseReport.setPassApiCallPercentage(getPercentage(totalApiCalls, passApiCalls));
                    testCaseReport.setNotPassApiCallPercentage(getPercentage(totalApiCalls, notPassApiCalls));

                    testCaseReport.setTotalExecutedApiCallCount(executedApiCalls);
                    testCaseReport.setTotalSkippedApiCallCount(skippedApiCalls);
                    testCaseReport.setTotalFailedApiCallCount(failedApiCalls);
                    testCaseReport.setTotalInvalidApiCallCount(invalidApiCalls);

                    testCaseReport.setExecutedApiCallPercentage(getPercentage(totalApiCalls, executedApiCalls));
                    testCaseReport.setSkippedApiCallPercentage(getPercentage(totalApiCalls, skippedApiCalls));
                    testCaseReport.setFailedApiCallPercentage(getPercentage(totalApiCalls, failedApiCalls));
                    testCaseReport.setInvalidApiCallPercentage(getPercentage(totalApiCalls, invalidApiCalls));

                    totalTestCase++;
                    tsu_totalTestCases++;
                    lazy_totalTestCases++;

                    if (notPassApiCalls == 0) {
                        passTestCase++;
                        tsu_passTestCases++;
                        lazy_passTestCases++;
                    } else {
                        notPassTestCase++;
                        tsu_notPassTestCases++;
                        lazy_notPassTestCases++;
                    }

                    if (invalidApiCalls != 0) {
                        invalidTestCase++;
                        tsu_invalidTestCases++;
                        lazy_invalidTestCases++;
                    } else if (failedApiCalls != 0) {
                        failedTestCase++;
                        tsu_failedTestCases++;
                        lazy_failedTestCases++;
                    } else if (skippedApiCalls != 0) {
                        skippedTestCase++;
                        tsu_skippedTestCases++;
                        lazy_skippedTestCases++;
                    } else if (totalApiCalls == executedApiCalls) {
                        executedTestCase++;
                        tsu_executedTestCases++;
                        lazy_executedTestCases++;
                    } else {
                        LOGGER.info("Invalid api call count found");
                    }


                    testScenarioReport.getTestCaseReports().add(testCaseReport);
                }

                testScenarioReport.setTotalTestCasesCount(totalTestCase);
                testScenarioReport.setPassTestCasesCount(passTestCase);
                testScenarioReport.setNotPassTestCasesCount(notPassTestCase);
                testScenarioReport.setPassTestCasesPercentage(getPercentage(totalTestCase, passTestCase));
                testScenarioReport.setNotPassTestCasesPercentage(getPercentage(totalTestCase, notPassTestCase));

                testScenarioReport.setTotalExecutedTestCasesCount(executedTestCase);
                testScenarioReport.setTotalSkippedTestCasesCount(skippedTestCase);
                testScenarioReport.setTotalFailedTestCasesCount(failedTestCase);
                testScenarioReport.setTotalInvalidTestCasesCount(invalidTestCase);

                testScenarioReport.setExecutedTestCasesPercentage(getPercentage(totalTestCase, executedTestCase));
                testScenarioReport.setSkippedTestCasesPercentage(getPercentage(totalTestCase, skippedTestCase));
                testScenarioReport.setFailedTestCasesPercentage(getPercentage(totalTestCase, failedTestCase));
                testScenarioReport.setInvalidTestCasesPercentage(getPercentage(totalTestCase, invalidTestCase));

                //Populate test scenario API call data
                testScenarioReport.setTotalApiCallCount(tsu_totalApiCalls);
                testScenarioReport.setPassApiCallCount(tsu_passApiCalls);
                testScenarioReport.setNotPassApiCallCount(tsu_notPassApiCalls);
                testScenarioReport.setPassApiCallPercentage(getPercentage(tsu_totalApiCalls, tsu_passApiCalls));
                testScenarioReport.setNotPassApiCallPercentage(getPercentage(tsu_totalApiCalls, tsu_notPassApiCalls));

                testScenarioReport.setTotalExecutedApiCallCount(tsu_executedApiCalls);
                testScenarioReport.setTotalSkippedApiCallCount(tsu_skippedApiCalls);
                testScenarioReport.setTotalFailedApiCallCount(tsu_failedApiCalls);
                testScenarioReport.setTotalInvalidApiCallCount(tsu_invalidApiCalls);

                testScenarioReport.setExecutedApiCallPercentage(getPercentage(tsu_totalApiCalls, tsu_executedApiCalls));
                testScenarioReport.setSkippedApiCallPercentage(getPercentage(tsu_totalApiCalls, tsu_skippedApiCalls));
                testScenarioReport.setFailedApiCallPercentage(getPercentage(tsu_totalApiCalls, tsu_failedApiCalls));
                testScenarioReport.setInvalidApiCallPercentage(getPercentage(tsu_totalApiCalls, tsu_invalidApiCalls));

                totalTestScenario++;
                lazy_totalTestScenario++;

                if (notPassTestCase == 0) {
                    passTestScenario++;
                    lazy_passTestScenario++;
                } else {
                    notPassTestScenario++;
                    lazy_notPassTestScenario++;
                }

                if (invalidTestCase != 0) {
                    invalidTestScenario++;
                    lazy_invalidTestScenario++;
                } else if (failedTestCase != 0) {
                    failedTestScenario++;
                    lazy_failedTestScenario++;
                } else if (skippedTestCase != 0) {
                    skippedTestScenario++;
                    lazy_skippedTestScenario++;
                } else if (totalTestCase == executedTestCase) {
                    executedTestScenario++;
                    lazy_executedTestScenario++;
                } else {
                    LOGGER.info("Invalid test case count found");
                }
                
                testSuiteReport.getTestScenarioReports().add(testScenarioReport);
            }

            //Test Scenario Data
            testSuiteReport.setTotalTestScenariosCount(totalTestScenario);
            testSuiteReport.setPassTestScenariosCount(passTestScenario);
            testSuiteReport.setNotPassTestScenariosCount(notPassTestScenario);
            testSuiteReport.setPassTestScenariosPercentage(getPercentage(totalTestScenario, passTestScenario));
            testSuiteReport.setNotPassTestScenariosPercentage(getPercentage(totalTestScenario, notPassTestScenario));

            testSuiteReport.setTotalExecutedTestScenariosCount(executedTestScenario);
            testSuiteReport.setTotalSkippedTestScenariosCount(skippedTestScenario);
            testSuiteReport.setTotalFailedTestScenariosCount(failedTestScenario);
            testSuiteReport.setTotalInvalidTestScenariosCount(invalidTestScenario);

            testSuiteReport.setExecutedTestScenariosPercentage(getPercentage(totalTestScenario, executedTestScenario));
            testSuiteReport.setSkippedTestScenariosPercentage(getPercentage(totalTestScenario, skippedTestScenario));
            testSuiteReport.setFailedTestScenariosPercentage(getPercentage(totalTestScenario, failedTestScenario));
            testSuiteReport.setInvalidTestScenariosPercentage(getPercentage(totalTestScenario, invalidTestScenario));


            //Test Case Data
            testSuiteReport.setTotalTestCasesCount(lazy_totalTestCases);
            testSuiteReport.setPassTestCasesCount(lazy_passTestCases);
            testSuiteReport.setNotPassTestCasesCount(lazy_notPassTestCases);
            testSuiteReport.setPassTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_passTestCases));
            testSuiteReport.setNotPassTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_notPassTestCases));

            testSuiteReport.setTotalExecutedTestCasesCount(lazy_executedTestCases);
            testSuiteReport.setTotalSkippedTestCasesCount(lazy_skippedTestCases);
            testSuiteReport.setTotalFailedTestCasesCount(lazy_failedTestCases);
            testSuiteReport.setTotalInvalidTestCasesCount(lazy_invalidTestCases);

            testSuiteReport.setExecutedTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_executedTestCases));
            testSuiteReport.setSkippedTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_skippedTestCases));
            testSuiteReport.setFailedTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_failedTestCases));
            testSuiteReport.setInvalidTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_invalidTestCases));

            //Api Call Data
            testSuiteReport.setTotalApiCallCount(lazy_totalApiCalls);
            testSuiteReport.setPassApiCallCount(lazy_passApiCalls);
            testSuiteReport.setNotPassApiCallCount(lazy_notPassApiCalls);
            testSuiteReport.setPassApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_passApiCalls));
            testSuiteReport.setNotPassApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_notPassApiCalls));

            testSuiteReport.setTotalExecutedApiCallCount(lazy_executedApiCalls);
            testSuiteReport.setTotalSkippedApiCallCount(lazy_skippedApiCalls);
            testSuiteReport.setTotalFailedApiCallCount(lazy_failedApiCalls);
            testSuiteReport.setTotalInvalidApiCallCount(lazy_invalidApiCalls);

            testSuiteReport.setExecutedApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_executedApiCalls));
            testSuiteReport.setSkippedApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_skippedApiCalls));
            testSuiteReport.setFailedApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_failedApiCalls));
            testSuiteReport.setInvalidApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_invalidApiCalls));

            totalTestSuite++;

            if (notPassTestScenario == 0) {
                passTestSuite++;
            } else {
                notPassTestSuite++;
            }

            if (invalidTestScenario != 0) {
                invalidTestSuite++;
            } else if (failedTestScenario != 0) {
                failedTestSuite++;
            } else if (skippedTestScenario != 0) {
                skippedTestSuite++;
            } else if (totalTestScenario == executedTestScenario) {
                executedTestSuite++;
            } else {
                LOGGER.info("Invalid test case count found");
            }

            lazyReport.getTestSuiteReportList().add(testSuiteReport);
        }

        //Populate test suite data
        lazyReport.setTotalTestSuitesCount(totalTestSuite);
        lazyReport.setPassTestSuitesCount(passTestSuite);
        lazyReport.setNotPassTestSuitesCount(notPassTestSuite);
        lazyReport.setPassTestSuitesPercentage(getPercentage(totalTestSuite, passTestSuite));
        lazyReport.setNotPassTestSuitesPercentage(getPercentage(totalTestSuite, notPassTestSuite));

        lazyReport.setTotalExecutedTestSuitesCount(executedTestSuite);
        lazyReport.setTotalSkippedTestSuitesCount(skippedTestSuite);
        lazyReport.setTotalFailedTestSuitesCount(failedTestSuite);
        lazyReport.setTotalInvalidTestSuitesCount(invalidTestSuite);

        lazyReport.setExecutedTestSuitesPercentage(getPercentage(totalTestSuite, executedTestSuite));
        lazyReport.setSkippedTestSuitesPercentage(getPercentage(totalTestSuite, skippedTestSuite));
        lazyReport.setFailedTestSuitesPercentage(getPercentage(totalTestSuite, failedTestSuite));
        lazyReport.setInvalidTestSuitesPercentage(getPercentage(totalTestSuite, invalidTestSuite));

        //Populate Test Scenario Data
        lazyReport.setTotalTestScenariosCount(lazy_totalTestScenario);
        lazyReport.setPassTestScenariosCount(lazy_passTestScenario);
        lazyReport.setNotPassTestScenariosCount(lazy_notPassTestScenario);
        lazyReport.setPassTestScenariosPercentage(getPercentage(lazy_totalTestScenario, lazy_passTestScenario));
        lazyReport.setNotPassTestScenariosPercentage(getPercentage(lazy_totalTestScenario, lazy_notPassTestScenario));

        lazyReport.setTotalExecutedTestScenariosCount(lazy_executedTestScenario);
        lazyReport.setTotalSkippedTestScenariosCount(lazy_skippedTestScenario);
        lazyReport.setTotalFailedTestScenariosCount(lazy_failedTestScenario);
        lazyReport.setTotalInvalidTestScenariosCount(lazy_invalidTestScenario);

        lazyReport.setExecutedTestScenariosPercentage(getPercentage(lazy_totalTestScenario, lazy_executedTestScenario));
        lazyReport.setSkippedTestScenariosPercentage(getPercentage(lazy_totalTestScenario, lazy_skippedTestScenario));
        lazyReport.setFailedTestScenariosPercentage(getPercentage(lazy_totalTestScenario, lazy_failedTestScenario));
        lazyReport.setInvalidTestScenariosPercentage(getPercentage(lazy_totalTestScenario, lazy_invalidTestScenario));



        //Populate Test Case
        lazyReport.setTotalTestCasesCount(lazy_totalTestCases);
        lazyReport.setPassTestCasesCount(lazy_passTestCases);
        lazyReport.setNotPassTestCasesCount(lazy_notPassTestCases);
        lazyReport.setPassTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_passTestCases));
        lazyReport.setNotPassTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_notPassTestCases));

        lazyReport.setTotalExecutedTestCasesCount(lazy_executedTestCases);
        lazyReport.setTotalSkippedTestCasesCount(lazy_skippedTestCases);
        lazyReport.setTotalFailedTestCasesCount(lazy_failedTestCases);
        lazyReport.setTotalInvalidTestCasesCount(lazy_invalidTestCases);

        lazyReport.setExecutedTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_executedTestCases));
        lazyReport.setSkippedTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_skippedTestCases));
        lazyReport.setFailedTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_failedTestCases));
        lazyReport.setInvalidTestCasesPercentage(getPercentage(lazy_totalTestCases, lazy_invalidTestCases));

        //Populate API calls
        lazyReport.setTotalApiCallCount(lazy_totalApiCalls);
        lazyReport.setPassApiCallCount(lazy_passApiCalls);
        lazyReport.setNotPassApiCallCount(lazy_notPassApiCalls);
        lazyReport.setPassApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_passApiCalls));
        lazyReport.setNotPassApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_notPassApiCalls));

        lazyReport.setTotalExecutedApiCallCount(lazy_executedApiCalls);
        lazyReport.setTotalSkippedApiCallCount(lazy_skippedApiCalls);
        lazyReport.setTotalFailedApiCallCount(lazy_failedApiCalls);
        lazyReport.setTotalInvalidApiCallCount(lazy_invalidApiCalls);

        lazyReport.setExecutedApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_executedApiCalls));
        lazyReport.setSkippedApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_skippedApiCalls));
        lazyReport.setFailedApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_failedApiCalls));
        lazyReport.setInvalidApiCallPercentage(getPercentage(lazy_totalApiCalls, lazy_invalidApiCalls));


        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        lazyReport.setDate(formatter.format(date));
        return lazyReport;
    }
}

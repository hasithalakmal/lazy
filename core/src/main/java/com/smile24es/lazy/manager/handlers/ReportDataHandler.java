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

import static com.smile24es.lazy.utils.MathsUtil.getPercentage;
import static java.text.MessageFormat.format;

@Repository
public class ReportDataHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManagerImpl.class);

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

        int tsu_totalTestScenario = 0;
        int tsu_passTestScenario = 0;
        int tsu_notPassTestScenario = 0;
        int tsu_executedTestScenario = 0;
        int tsu_skippedTestScenario = 0;
        int tsu_failedTestScenario = 0;
        int tsu_invalidTestScenario = 0;

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

            int ts_totalTestCases = 0;
            int ts_passTestCases = 0;
            int ts_notPassTestCases = 0;
            int ts_executedTestCases = 0;
            int ts_skippedTestCases = 0;
            int ts_failedTestCases = 0;
            int ts_invalidTestCases = 0;


            int ts_totalApiCalls = 0;
            int ts_passApiCalls = 0;
            int ts_notPassApiCalls = 0;
            int ts_executedApiCalls = 0;
            int ts_skippedApiCalls = 0;
            int ts_failedApiCalls = 0;
            int ts_invalidApiCalls = 0;
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

                int tc_totalApiCalls = 0;
                int tc_passApiCalls = 0;
                int tc_notPassApiCalls = 0;
                int tc_executedApiCalls = 0;
                int tc_skippedApiCalls = 0;
                int tc_failedApiCalls = 0;
                int tc_invalidApiCalls = 0;
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
                        apiCallReport.setRequestBody(apiCallExecutionData.getRequestBody());


                        int totalAssertions = 0;
                        int passAssertions = 0;
                        int notPassAssertions = 0;
                        int executedAssertions = 0;
                        int skippedAssertions = 0;
                        int failedAssertions = 0;
                        int invalidAssertions = 0;
                        populateAssertionDetails(apiCallExecutionData, apiCallReport, totalAssertions, passAssertions, notPassAssertions, executedAssertions, skippedAssertions, failedAssertions, invalidAssertions);

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
                        tc_totalApiCalls++;
                        ts_totalApiCalls++;
                        tsu_totalApiCalls++;

                        if (notPassAssertions == 0) {
                            passApiCalls++;
                            tc_passApiCalls++;
                            ts_passApiCalls++;
                            tsu_passApiCalls++;
                        } else {
                            notPassApiCalls++;
                            tc_notPassApiCalls++;
                            ts_notPassApiCalls++;
                            tsu_notPassApiCalls++;
                        }

                        if (invalidAssertions != 0) {
                            invalidApiCalls++;
                            tc_invalidApiCalls++;
                            ts_invalidApiCalls++;
                            tsu_invalidApiCalls++;
                        } else if (failedAssertions != 0) {
                            failedApiCalls++;
                            tc_failedApiCalls++;
                            ts_failedApiCalls++;
                            tsu_failedApiCalls++;
                        } else if (skippedAssertions != 0) {
                            skippedApiCalls++;
                            tc_skippedApiCalls++;
                            ts_skippedApiCalls++;
                            tsu_skippedApiCalls++;
                        } else if (totalAssertions == executedAssertions) {
                            executedApiCalls++;
                            tc_executedApiCalls++;
                            ts_executedApiCalls++;
                            tsu_executedApiCalls++;
                        } else {
                            LOGGER.error("Unexpected result counts found");
                        }
                        testCaseReport.getApiCallReports().add(apiCallReport);
                    }



                    testCaseReport.setTotalApiCallCount(totalApiCalls);
                    testCaseReport.setPassApiCallCount(totalApiCalls);
                    testCaseReport.setNotPassApiCallCount(totalApiCalls);
                    testCaseReport.setPassApiCallPercentage(totalApiCalls);
                    testCaseReport.setNotPassApiCallPercentage(totalApiCalls);

                    testCaseReport.setTotalExecutedApiCallCount(executedApiCalls);
                    testCaseReport.setTotalSkippedApiCallCount(skippedApiCalls);
                    testCaseReport.setTotalFailedApiCallCount(failedApiCalls);
                    testCaseReport.setTotalInvalidApiCallCount(invalidApiCalls);

                    testCaseReport.setExecutedApiCallPercentage(getPercentage(totalApiCalls, executedApiCalls));
                    testCaseReport.setSkippedApiCallPercentage(getPercentage(totalApiCalls, skippedApiCalls));
                    testCaseReport.setFailedApiCallPercentage(getPercentage(totalApiCalls, failedApiCalls));
                    testCaseReport.setInvalidApiCallPercentage(getPercentage(totalApiCalls, invalidApiCalls));

                    totalTestCase++;
                    ts_totalTestCases++;
                    tsu_totalTestCases++;

                    if (notPassApiCalls == 0) {
                        passTestCase++;
                        ts_passTestCases++;
                        tsu_passTestCases++;
                    } else {
                        notPassTestCase++;
                        ts_notPassTestCases++;
                        tsu_notPassTestCases++;
                    }

                    if (invalidApiCalls != 0) {
                        invalidTestCase++;
                        ts_invalidTestCases++;
                        tsu_invalidTestCases++;
                    } else if (failedApiCalls != 0) {
                        failedTestCase++;
                        ts_failedTestCases++;
                        tsu_failedTestCases++;
                    } else if (skippedApiCalls != 0) {
                        skippedTestCase++;
                        ts_skippedTestCases++;
                        tsu_skippedTestCases++;
                    } else if (totalApiCalls == executedApiCalls) {
                        executedTestCase++;
                        ts_executedTestCases++;
                        tsu_executedTestCases++;
                    } else {
                        LOGGER.info("Invalid api call count found");
                    }
                    testScenarioReport.getTestCaseReports().add(testCaseReport);
                }

                testScenarioReport.setTotalTestCasesCount(totalTestCase);
                testScenarioReport.setPassTestCasesCount(totalTestCase);
                testScenarioReport.setNotPassTestCasesCount(totalTestCase);
                testScenarioReport.setPassTestCasesPercentage(totalTestCase);
                testScenarioReport.setNotPassTestCasesPercentage(totalTestCase);

                testScenarioReport.setTotalExecutedTestCasesCount(executedTestCase);
                testScenarioReport.setTotalSkippedTestCasesCount(skippedTestCase);
                testScenarioReport.setTotalFailedTestCasesCount(failedTestCase);
                testScenarioReport.setTotalInvalidTestCasesCount(invalidTestCase);

                testScenarioReport.setExecutedTestCasesPercentage(getPercentage(totalTestCase, executedTestCase));
                testScenarioReport.setSkippedTestCasesPercentage(getPercentage(totalTestCase, skippedTestCase));
                testScenarioReport.setFailedTestCasesPercentage(getPercentage(totalTestCase, failedTestCase));
                testScenarioReport.setInvalidTestCasesPercentage(getPercentage(totalTestCase, invalidTestCase));

                totalTestScenario++;
                tsu_totalTestScenario++;

                if (notPassTestCase == 0) {
                    passTestScenario++;
                    tsu_passTestScenario++;
                } else {
                    notPassTestScenario++;
                    notPassTestScenario++;
                }

                if (invalidTestCase != 0) {
                    invalidTestScenario++;
                    tsu_invalidTestScenario++;
                } else if (failedTestCase != 0) {
                    failedTestScenario++;
                    tsu_failedTestScenario++;
                } else if (skippedTestCase != 0) {
                    skippedTestScenario++;
                    tsu_skippedTestScenario++;
                } else if (totalTestCase == executedTestCase) {
                    executedTestScenario++;
                    tsu_executedTestScenario++;
                } else {
                    LOGGER.info("Invalid test case count found");
                }
                testSuiteReport.getTestScenarioReports().add(testScenarioReport);
            }

            testSuiteReport.setTotalTestScenariosCount(totalTestScenario);
            testSuiteReport.setPassTestScenariosCount(totalTestScenario);
            testSuiteReport.setNotPassTestScenariosCount(totalTestScenario);
            testSuiteReport.setPassTestScenariosPercentage(totalTestScenario);
            testSuiteReport.setNotPassTestScenariosPercentage(totalTestScenario);

            testSuiteReport.setTotalExecutedTestScenariosCount(executedTestScenario);
            testSuiteReport.setTotalSkippedTestScenariosCount(skippedTestScenario);
            testSuiteReport.setTotalFailedTestScenariosCount(failedTestScenario);
            testSuiteReport.setTotalInvalidTestScenariosCount(invalidTestScenario);

            testSuiteReport.setExecutedTestScenariosPercentage(getPercentage(totalTestScenario, executedTestScenario));
            testSuiteReport.setSkippedTestScenariosPercentage(getPercentage(totalTestScenario, skippedTestScenario));
            testSuiteReport.setFailedTestScenariosPercentage(getPercentage(totalTestScenario, failedTestScenario));
            testSuiteReport.setInvalidTestScenariosPercentage(getPercentage(totalTestScenario, invalidTestScenario));

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
                LOGGER.info("Invalid test scenario count found");
            }
            lazyReport.getTestSuiteReportList().add(testSuiteReport);
        }

        return lazyReport;
    }

    private void populateAssertionDetails(ApiCallExecutionData apiCallExecutionData, ApiCallReport apiCallReport, int totalAssertions, int passAssertions, int notPassAssertions, int executedAssertions, int skippedAssertions, int failedAssertions, int invalidAssertions) {
        for (AssertionExecutionData assertionExecutionData : apiCallExecutionData.getAssertionExecutionDataList()) {
            AssertionResult assertionResult = assertionExecutionData.getAssertionResult();
            AssertionResultObject assertionResultObject = new AssertionResultObject(assertionResult.getResultId());
            assertionResultObject.setName(assertionResult.getAssertionRule().getAssertionRuleName());
            assertionResultObject.setActualValue(assertionResult.getActualValue());
            AssertionValue assertionValue = assertionResult.getAssertionRule().getAssertionValue();
            assertionResultObject.setExpectedValue(assertionValue == null ? null : assertionValue.getExpectedStringValue1());
            String assertionStatus = assertionResult.getAssertionStatus();
            assertionResultObject.setStatus(assertionStatus);
            Boolean isPass = assertionResult.getPass();
            assertionResultObject.setIsPass(isPass.toString());
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
    }
}

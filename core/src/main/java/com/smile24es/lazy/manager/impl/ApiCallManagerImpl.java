package com.smile24es.lazy.manager.impl;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.dto.IdDto;
import com.smile24es.lazy.beans.environment.EnvironmentVariable;
import com.smile24es.lazy.beans.executor.ApiCallExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.beans.executor.TestCaseExecutionData;
import com.smile24es.lazy.beans.executor.TestScenarioExecutionData;
import com.smile24es.lazy.beans.executor.TestSuiteExecutionData;
import com.smile24es.lazy.beans.response.LazyApiCallResponse;
import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.beans.suite.Global;
import com.smile24es.lazy.beans.suite.Stack;
import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.beans.suite.TestScenario;
import com.smile24es.lazy.beans.suite.TestSuite;
import com.smile24es.lazy.beans.suite.actions.Action;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;
import com.smile24es.lazy.manager.handlers.ActionHandlerImpl;
import com.smile24es.lazy.manager.handlers.ApiCallHandlerImpl;
import com.smile24es.lazy.manager.handlers.AssertionHandlerImpl;
import com.smile24es.lazy.manager.handlers.StackHandler;
import com.smile24es.lazy.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Service
public class ApiCallManagerImpl extends LazyBaseManager implements com.smile24es.lazy.manager.ApiCallManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiCallManagerImpl.class);
    @Autowired
    AssertionHandlerImpl assertionHandler;
    @Autowired
    private ApiCallHandlerImpl apiCallHandler;
    @Autowired
    private ActionHandlerImpl actionHandler;
    @Autowired
    private StackHandler stackManager;

    @Override
    public void executeApiCalls(LazySuite lazySuite, TestCaseExecutionData testCaseExecutionData, IdDto idDto, TestCase testCase) throws LazyException,
          LazyCoreException {
        executeApiCalls(lazySuite, testCaseExecutionData, idDto, testCase, null);
    }

    @Override
    public void executeApiCalls(LazySuite lazySuite, TestCaseExecutionData testCaseExecutionData, IdDto idDto, TestCase testCase, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException {
        LOGGER.debug("Ready to executing all api calls...");
        for (ApiCall apiCall : testCase.getApiCalls()) {
            validateApiCall(apiCall);
            String apiCallName = apiCall.getApiCallName();

            boolean isFoundInAcceptedList = isFoundInAcceptedList(lazyExecutionGroup, apiCall);

            if (!isFoundInAcceptedList) {
                continue;
            }

            LOGGER.debug("Preparing to execute api call - [{}]", apiCallName);
            mergeStack(testCase, apiCall, apiCallName);
            Integer apiCallId = populateApiCallId(idDto, apiCall, apiCallName);

            executePreActions(lazySuite, apiCall, apiCallName, apiCallId);

            //TODO - re-structure global and stack
            Map<String, EnvironmentVariable> globalEnvironment = lazySuite.getGlobal().getGlobalEnvironment();
            apiCall.getStack().setGlobalEnvironment(globalEnvironment);

            ApiCallExecutionData apiCallExecutionData = null;
            LazyApiCallResponse response = null;
            try {
                LOGGER.info("Executing api call - [{}] - [{}]", apiCallId, apiCallName);
                apiCallExecutionData = new ApiCallExecutionData();
                response = apiCallHandler.executeApiCall(apiCall, apiCallExecutionData);
                apiCallHandler.printExecutionData(apiCallExecutionData);
                LOGGER.info("Executed api call - [{}] - [{}]", apiCallId, apiCallName);
            } catch (Exception ex) {
                LOGGER.warn("API call execution failed since skipping the assertion execution");
                apiCallHandler.printExecutionDataInError(apiCallExecutionData, ex);
            }

            executeAssertions(idDto, apiCall, response, apiCallExecutionData);
            executePostActions(lazySuite, apiCall, apiCallName, apiCallId, response);

            testCaseExecutionData.getApiCallExecutionDataList().add(apiCallExecutionData);
            LOGGER.info("Completed execution of api call - [{}] - [{}]", apiCallId, apiCallName);
            idDto.setApiCallId(apiCallId + 1);
        }
        LOGGER.debug("Executed all api cases...");
    }

    private boolean isFoundInAcceptedList(LazyExecutionGroup lazyExecutionGroup, ApiCall apiCall) {
        boolean isFoundInAcceptedList = false;
        if (lazyExecutionGroup != null && !CollectionUtils.isEmpty(lazyExecutionGroup.getApiCallExecutionGroupNames())) {
            for (String acceptedTestSuiteName : lazyExecutionGroup.getApiCallExecutionGroupNames()) {
                if (!CollectionUtils.isEmpty(apiCall.getAssignGroups()) && apiCall.getAssignGroups().contains(acceptedTestSuiteName)) {
                    LOGGER.info("Find assigned api call - [{}] to execute", acceptedTestSuiteName);
                    isFoundInAcceptedList = true;
                }
            }

        }

        if (lazyExecutionGroup == null) {
            isFoundInAcceptedList = true;
        }
        return isFoundInAcceptedList;
    }

    @Override
    public ApiCallExecutionData executeApiCall(ApiCall apiCall, Stack stack) throws LazyCoreException, LazyException {
        validateApiCall(apiCall);
        String apiCallName = apiCall.getApiCallName();
        LOGGER.debug("Preparing to execute api call - [{}]", apiCallName);

        if (stack == null) {
            String error = "Test case stack should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_TEST_SUITE, error);
        }
        validateDefaultValues(stack.getDefaultValues());

        TestCase testCase = new TestCase("Default test case");
        testCase.setStack(stack);
        testCase.getApiCalls().add(apiCall);

        TestScenario testScenario = new TestScenario("Default test scenario");
        testScenario.setStack(stack);
        testScenario.getTestCases().add(testCase);

        TestSuite testSuite = new TestSuite("Default test suite");
        testSuite.setStack(stack);
        testSuite.getTestScenarios().add(testScenario);

        LazySuite lazySuite = new LazySuite("Default Lazy suite", stack);
        lazySuite.setGlobal(new Global());
        lazySuite.setStack(stack);
        lazySuite.getTestSuites().add(testSuite);

        mergeStack(lazySuite, testSuite, testSuite.getTestSuiteName());
        mergeStack(testSuite, testScenario, testScenario.getTestScenarioName());
        mergeStack(testScenario, testCase, testCase.getTestCaseName());
        mergeStack(testCase, apiCall, apiCallName);
        LOGGER.debug("Populated lazy suite to execute api call - [{}]", apiCallName);

        IdDto idDto = new IdDto();
        Integer apiCallId = populateApiCallId(idDto, apiCall, apiCallName);

        executePreActions(lazySuite, apiCall, apiCallName, apiCallId);

        //TODO - re-structure global and stack
        Map<String, EnvironmentVariable> globalEnvironment = lazySuite.getGlobal().getGlobalEnvironment();
        apiCall.getStack().setGlobalEnvironment(globalEnvironment);

        ApiCallExecutionData apiCallExecutionData = null;
        try {
            LOGGER.info("Executing api call - [{}] - [{}]", apiCallId, apiCallName);
            apiCallExecutionData = new ApiCallExecutionData();
            LazyApiCallResponse response = apiCallHandler.executeApiCall(apiCall, apiCallExecutionData);
            apiCallHandler.printExecutionData(apiCallExecutionData);
            LOGGER.info("Executed api call - [{}] - [{}]", apiCallId, apiCallName);

            executeAssertions(idDto, apiCall, response, apiCallExecutionData);
            executePostActions(lazySuite, apiCall, apiCallName, apiCallId, response);
        } catch (Exception ex) {
            LOGGER.warn("API call execution failed since skipping the assertion execution");
            executeAssertions(idDto, apiCall, null, apiCallExecutionData);
            executePostActionsOnFailed(lazySuite, apiCall, apiCallName, apiCallId);
        }

        TestCaseExecutionData testCaseExecutionData = new TestCaseExecutionData(idDto.getTestCaseId(), testCase.getTestCaseName());
        testCaseExecutionData.getApiCallExecutionDataList().add(apiCallExecutionData);
        TestScenarioExecutionData testScenarioExecutionData = new TestScenarioExecutionData(idDto.getTestScenarioId(), testScenario.getTestScenarioName());
        testScenarioExecutionData.getTestCaseExecutionDataList().add(testCaseExecutionData);
        TestSuiteExecutionData testSuiteExecutionData = new TestSuiteExecutionData(idDto.getTestSuiteId(), testSuite.getTestSuiteName());
        testSuiteExecutionData.getTestScenarioExecutionData().add(testScenarioExecutionData);
        LazyExecutionData lazyExecutionData = new LazyExecutionData();
        lazyExecutionData.getTestSuiteExecutionData().add(testSuiteExecutionData);
        printResultTable(lazyExecutionData);

        return apiCallExecutionData;
    }

    private void executeAssertions(IdDto idDto, ApiCall apiCall, LazyApiCallResponse response, ApiCallExecutionData apiCallExecutionData) throws LazyException, LazyCoreException {
        LOGGER.debug("Executing assertions of the api call - [{}] - [{}]", apiCall.getApiCallId(), apiCall.getApiCallName());
        assertionHandler.executeAllAssertions(apiCall, idDto, response, apiCallExecutionData);
        LOGGER.debug("Executed assertions of the api call - [{}] - [{}]", apiCall.getApiCallId(), apiCall.getApiCallName());
    }

    private void executePostActionsOnFailed(LazySuite lazySuite, ApiCall apiCall, String apiCallName, Integer apiCallId) throws LazyCoreException, LazyException {
        LOGGER.debug("Executing post actions of the [failed] api call - [{}] - [{}]", apiCallId, apiCallName);
        List<Action> postActions = apiCall.getPostActions();
        for (Action postAction : postActions) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute post action - [{}]", postAction);
            } else {
                LOGGER.info("Ready to execute post action...");
            }
            actionHandler.executePostAction(lazySuite, null, postAction);
        }
        LOGGER.debug("Executed post actions of the [failed] api call - [{}] - [{}]", apiCallId, apiCallName);
    }

    private void executePostActions(LazySuite lazySuite, ApiCall apiCall, String apiCallName, Integer apiCallId, LazyApiCallResponse response) throws LazyCoreException, LazyException {
        LOGGER.debug("Executing post actions of the api call - [{}] - [{}]", apiCallId, apiCallName);
        List<Action> postActions = apiCall.getPostActions();
        for (Action postAction : postActions) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute post action - [{}]", postAction);
            } else {
                LOGGER.info("Ready to execute post action...");
            }
            actionHandler.executePostAction(lazySuite, response, postAction);
        }
        LOGGER.debug("Executed post actions of the api call - [{}] - [{}]", apiCallId, apiCallName);
    }

    private void executePreActions(LazySuite lazySuite, ApiCall apiCall, String apiCallName, Integer apiCallId) throws LazyCoreException, LazyException {
        LOGGER.debug("Executing pre actions of the api call - [{}] - [{}]", apiCallId, apiCallName);
        List<Action> preActions = apiCall.getPreActions();
        for (Action preAction : preActions) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Ready to execute pre action - [{}]", preAction);
            }
            actionHandler.executePreAction(lazySuite, preAction);
        }
        LOGGER.debug("Executed pre actions of the api call - [{}] - [{}]", apiCallId, apiCallName);
    }

    private Integer populateApiCallId(IdDto idDto, ApiCall apiCall, String apiCallName) {
        Integer apiCallId = idDto.getApiCallId();
        if (apiCall.getApiCallId() == null) {
            apiCall.setApiCallId(apiCallId);
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No test scenario ID found for [{}] hence add test api call Id [{}]", apiCallName, apiCallId);
            }
        }
        return apiCallId;
    }

    private void validateApiCall(ApiCall apiCall) throws LazyCoreException, LazyException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to execute api call - [{}]", apiCall);
        }

        if (apiCall == null) {
            String error = "Api call should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_API_CALL, error);
        }

        if (StringUtils.isBlank(apiCall.getApiCallName())) {
            String error = "Api call name should not be empty";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_TEST_API_CALL, error);
        }
    }


}

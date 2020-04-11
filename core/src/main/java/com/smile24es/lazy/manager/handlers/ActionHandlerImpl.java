package com.smile24es.lazy.manager.handlers;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.enums.ActionTypeEnum;
import com.smile24es.lazy.beans.enums.DataSourceEnum;
import com.smile24es.lazy.beans.environment.Environment;
import com.smile24es.lazy.beans.environment.EnvironmentVariable;
import com.smile24es.lazy.beans.response.LazyApiCallResponse;
import com.smile24es.lazy.beans.suite.actions.Action;
import com.smile24es.lazy.beans.suite.actions.ExecuteAnotherApiCall;
import com.smile24es.lazy.beans.suite.actions.ExecuteAnotherTest;
import com.smile24es.lazy.beans.suite.actions.VariableCleanAction;
import com.smile24es.lazy.beans.suite.actions.VariableDeclarationAction;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyException;
import com.smile24es.lazy.utils.JsonPathReaderUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.text.MessageFormat.format;

@Service
public class ActionHandlerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionHandlerImpl.class);

    public void executePreAction(LazySuite lazySuite, Action action) throws LazyException {
        ActionTypeEnum actionType = action.getActionType();
        if (action instanceof VariableDeclarationAction) {
            handleVariableDeclarationPreAction(lazySuite, action, actionType);
        } else if (action instanceof VariableCleanAction) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing pre action to VariableCleanAction - [{}]", action);
            }
            removeGlobalEnvironmentVariable(lazySuite, action);
        } else if (action instanceof ExecuteAnotherTest) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing pre action to ExecuteAnotherTest - [{}]", action);
            }
            actionNotImplementedYet(action);
        } else if (action instanceof ExecuteAnotherApiCall) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing pre action to ExecuteAnotherApiCall - [{}]", action);
            }
            actionNotImplementedYet(action);
        } else {
            invalidAction(action);
        }
    }

    public void executePostAction(LazySuite lazySuite, LazyApiCallResponse lazyApiCallResponse, Action action) throws LazyException {
        ActionTypeEnum actionType = action.getActionType();
        if (action instanceof VariableDeclarationAction) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing post action to VariableDeclarationAction - [{}]", action);
            }
            handleVariableDeclarationPostAction(lazySuite, lazyApiCallResponse, action, actionType);
        } else if (action instanceof VariableCleanAction) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing post action to VariableCleanAction - [{}]", action);
            }
            removeGlobalEnvironmentVariable(lazySuite, action);
        } else if (action instanceof ExecuteAnotherTest) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing post action to ExecuteAnotherTest - [{}]", action);
            }
            actionNotImplementedYet(action);
        } else if (action instanceof ExecuteAnotherApiCall) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing post action to ExecuteAnotherApiCall - [{}]", action);
            }
            actionNotImplementedYet(action);
        } else {
            invalidAction(action);
        }
    }

    private void handleVariableDeclarationPreAction(LazySuite lazySuite, Action action, ActionTypeEnum actionType) throws LazyException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.info("Executing pre action to VariableDeclarationAction - [{}]", action);
        }
        VariableDeclarationAction variableDeclarationAction = (VariableDeclarationAction) action;
        DataSourceEnum dataSource = variableDeclarationAction.getDataSourceEnum();
        if (dataSource == DataSourceEnum.PROVIDED) {
            String variableValue = variableDeclarationAction.getVariableValue();
            defineEnvironmentVariable(lazySuite, actionType, variableDeclarationAction, variableValue);
        } else {
            String error = format("Given data source has not supported [{0}] for Action [{1}] in pre action scope", actionType, action.getClass().getName());
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ACTION_DATA_SOURCE, error);
        }
    }

    private void setEnvironmentVariable(LazySuite lazySuite, VariableDeclarationAction variableDeclarationAction, String variableValue) {
        String environmentDisplayId = variableDeclarationAction.getEnvironmentDisplayId();
        if (lazySuite.getGlobal().getEnvironments().containsKey(environmentDisplayId)) {
            EnvironmentVariable environmentVariable = new EnvironmentVariable(variableValue, variableDeclarationAction.getDataType());
            Environment environment = lazySuite.getGlobal().getEnvironments().get(environmentDisplayId);
            environment.getEnvironmentVariableMap().put(variableDeclarationAction.getVariableKey(), environmentVariable);
        } else {
            LOGGER.warn("No environment found [{}], since it contains ", environmentDisplayId);
        }
    }

    private void handleVariableDeclarationPostAction(LazySuite lazySuite, LazyApiCallResponse lazyApiCallResponse, Action action, ActionTypeEnum actionType) throws LazyException {
        VariableDeclarationAction variableDeclarationAction = (VariableDeclarationAction) action;
        DataSourceEnum dataSource = variableDeclarationAction.getDataSourceEnum();
        if (dataSource == DataSourceEnum.PROVIDED) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing VariableDeclarationAction for provided value- [{}]", variableDeclarationAction);
            }
            defineEnvironmentVariable(lazySuite, actionType, variableDeclarationAction, variableDeclarationAction.getVariableValue());
        } else if (dataSource == DataSourceEnum.BODY) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.info("Executing VariableDeclarationAction for body value- [{}]", variableDeclarationAction);
            }
            declareVariableFromResponseBody(lazySuite, lazyApiCallResponse, actionType, variableDeclarationAction);
        } else {
            String error = format("Given data source has not supported [{0}] for Action [{1}] in pre action scope", actionType, action.getClass().getName());
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ACTION_DATA_SOURCE, error);
        }
    }

    private void defineEnvironmentVariable(LazySuite lazySuite, ActionTypeEnum actionType, VariableDeclarationAction variableDeclarationAction, String variableValue) throws LazyException {
        if (variableDeclarationAction.getActionType() == ActionTypeEnum.SET_GLOBAL_VARIABLE) {
            setGlobalEnvironmentVariable(lazySuite, variableDeclarationAction, variableValue);
        } else if (ActionTypeEnum.SET_ENVIRONMENT_VARIABLE == actionType) {
            setEnvironmentVariable(lazySuite, variableDeclarationAction, variableValue);
        } else {
            String error = format("Given action type has not supported for define environment variable [{0}]", actionType);
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ACTION_TYPE, error);
        }
    }

    private void declareVariableFromResponseBody(LazySuite lazySuite, LazyApiCallResponse lazyApiCallResponse, ActionTypeEnum actionType, VariableDeclarationAction variableDeclarationAction) throws LazyException {
        if (lazyApiCallResponse == null || StringUtils.isBlank(lazyApiCallResponse.getResponseBody())) {
            String error = format("Empty lazyApiCallResponse found to post action - response [{0}]", lazyApiCallResponse);
            LOGGER.warn(error);
            LOGGER.warn("Variable value set as null for key [{}], since response body is empty", variableDeclarationAction.getVariableKey());
            setGlobalEnvironmentVariable(lazySuite, variableDeclarationAction, null);
        } else {
            String actualValue = getVariableValue(lazyApiCallResponse, variableDeclarationAction);
            defineEnvironmentVariable(lazySuite, actionType, variableDeclarationAction, actualValue);
        }
    }

    private String getVariableValue(LazyApiCallResponse lazyApiCallResponse, VariableDeclarationAction variableDeclarationAction) {
        String jsonPath = variableDeclarationAction.getJsonPath();
        String actualValue;
        if (StringUtils.isNotBlank(jsonPath)) {
            actualValue = JsonPathReaderUtil.getAnyValueAsString(lazyApiCallResponse.getResponseBody(), jsonPath);
        } else {
            LOGGER.warn("JSON path is empty for key [{}], since complete response body assigned to variable",
                  variableDeclarationAction.getVariableKey());
            actualValue = lazyApiCallResponse.getResponseBody();
        }
        return actualValue;
    }

    private void setGlobalEnvironmentVariable(LazySuite lazySuite, VariableDeclarationAction variableDeclarationAction, String variableValue) {
        EnvironmentVariable environmentVariable = new EnvironmentVariable(variableValue, variableDeclarationAction.getDataType());
        lazySuite.getGlobal().getGlobalEnvironment().put(variableDeclarationAction.getVariableKey(), environmentVariable);
    }

    private void removeGlobalEnvironmentVariable(LazySuite lazySuite, Action action) throws LazyException {
        VariableCleanAction variableCleanAction = (VariableCleanAction) action;
        if (action.getActionType() == ActionTypeEnum.CLEAR_GLOBAL_VARIABLE) {
            lazySuite.getGlobal().getGlobalEnvironment().remove(variableCleanAction.getVariableKey());
            LOGGER.info("Remove variable [{}] from global environment", variableCleanAction.getVariableKey());
        } else if (action.getActionType() == ActionTypeEnum.CLEAR_ENVIRONMENT_VARIABLE) {
            String environmentDisplayId = variableCleanAction.getEnvironmentDisplayId();
            if (lazySuite.getGlobal().getEnvironments().containsKey(environmentDisplayId)) {
                EnvironmentVariable environmentVariable = new EnvironmentVariable(variableCleanAction.getVariableKey(), variableCleanAction.getDataType());
                Environment environment = lazySuite.getGlobal().getEnvironments().get(environmentDisplayId);
                environment.getEnvironmentVariableMap().put(variableCleanAction.getVariableKey(), environmentVariable);
            } else {
                LOGGER.warn("No environment found [{}], since it contains ", environmentDisplayId);
            }
            LOGGER.info("Remove variable [{}] from global environment", variableCleanAction.getVariableKey());
        } else {
            String error = format("Given action type has not supported for clean environment variable [{0}]", action.getActionType());
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ACTION_TYPE, error);
        }

    }

    private void invalidAction(Action action) throws LazyException {
        String error = format("Given action is invalid [{0}]", action.getClass().getName());
        LOGGER.error(error);
        throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_ACTION, error);
    }

    private void actionNotImplementedYet(Action action) throws LazyException {
        String error = format("Given action has not supported yet [{0}]", action.getClass().getName());
        LOGGER.error(error);
        throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
    }

}

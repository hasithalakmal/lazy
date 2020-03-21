package com.smile.lazy.manager.Impl;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.enums.ActionTypeEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.environment.Environment;
import com.smile.lazy.beans.environment.EnvironmentVariable;
import com.smile.lazy.beans.response.LazyApiCallResponse;
import com.smile.lazy.beans.suite.actions.Action;
import com.smile.lazy.beans.suite.actions.ExecuteAnotherApiCall;
import com.smile.lazy.beans.suite.actions.ExecuteAnotherTest;
import com.smile.lazy.beans.suite.actions.VariableCleanAction;
import com.smile.lazy.beans.suite.actions.VariableDeclarationAction;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.text.MessageFormat.format;

@Service
public class ActionHandlerImpl {

    private static final Logger LOGGER = LoggerFactory.getLogger(ActionHandlerImpl.class);

    public void executePostAction(LazySuite lazySuite, LazyApiCallResponse lazyApiCallResponse, Action action) throws LazyException {
        ActionTypeEnum actionType = action.getActionType();
        if (action instanceof VariableDeclarationAction) {
            VariableDeclarationAction variableDeclarationAction = (VariableDeclarationAction) action;
            DataSourceEnum dataSource = variableDeclarationAction.getDataSourceEnum();
            String variableValue = null;
            if (dataSource == DataSourceEnum.PROVIDED) {
                variableValue = variableDeclarationAction.getVariableValue();
            } else if (dataSource == DataSourceEnum.BODY) {
                if (lazyApiCallResponse == null) {
                    //TODO - should handle
                    String error = "Null lazyApiCallResponse found to post action";
                    LOGGER.error(error);
                    throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.NOT_IMPLEMENTED, error);
                }
                String responseBody = lazyApiCallResponse.getResponseBody();
                if (StringUtils.isBlank(responseBody)) {
                    //TODO - should handle
                    String error = "Empty lazyApiCallResponse body found to post action";
                    LOGGER.error(error);
                    throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.NOT_IMPLEMENTED, error);
                }
                if (variableDeclarationAction.getActionType() == ActionTypeEnum.SET_GLOBAL_VARIABLE) {
                    String jsonPath = variableDeclarationAction.getJsonPath();
                    String actualValue = null;
                    if (StringUtils.isNotBlank(jsonPath)) {
                        Object document = Configuration.defaultConfiguration().jsonProvider().parse(responseBody);
                        actualValue = JsonPath.read(document, jsonPath);
                    }
                    EnvironmentVariable environmentVariable = new EnvironmentVariable(actualValue, variableDeclarationAction.getDataType());
                    lazySuite.getGlobal().getGlobalEnvironment().put(variableDeclarationAction.getVariableKey(), environmentVariable);
                } else {
                    String error = format("Given post action type has not supported [{0}] for Action [{1}] in post action scope", actionType,
                          action.getClass().getName());
                    LOGGER.error(error);
                    throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
                }
            } else {
                String error = format("Given data source has not supported [{0}] for Action [{1}] in pre action scope", actionType, action.getClass().getName());
                LOGGER.error(error);
                throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
            }
        } else if (action instanceof VariableCleanAction) {

        } else if (action instanceof ExecuteAnotherTest) {

        } else if (action instanceof ExecuteAnotherApiCall) {

        } else {
            String error = format("Given action has not supported yet [{0}]", action.getClass().getName());
            LOGGER.error(error);
            throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
        }

    }

    public void executePreAction(LazySuite lazySuite, Action action) throws LazyException {
        ActionTypeEnum actionType = action.getActionType();
        if (action instanceof VariableDeclarationAction) {
            VariableDeclarationAction variableDeclarationAction = (VariableDeclarationAction) action;
            DataSourceEnum dataSource = variableDeclarationAction.getDataSourceEnum();
            String variableValue = null;
            if (dataSource == DataSourceEnum.PROVIDED) {
                variableValue = variableDeclarationAction.getVariableValue();
            } else {
                String error = format("Given data source has not supported [{0}] for Action [{1}] in pre action scope", actionType, action.getClass().getName());
                LOGGER.error(error);
                throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
            }

            EnvironmentVariable environmentVariable = new EnvironmentVariable(variableValue, variableDeclarationAction.getDataType());
            if (ActionTypeEnum.SET_GLOBAL_VARIABLE == actionType) {
                lazySuite.getGlobal().getGlobalEnvironment().put(variableDeclarationAction.getVariableKey(), environmentVariable);
            } else if (ActionTypeEnum.SET_ENVIRONMENT_VARIABLE == actionType) {
                String environmentDisplayId = variableDeclarationAction.getEnvironmentDisplayId();
                if (lazySuite.getGlobal().getEnvironments().containsKey(environmentDisplayId)) {
                    Environment environment = lazySuite.getGlobal().getEnvironments().get(environmentDisplayId);
                    environment.getEnvironmentVariableMap().put(variableDeclarationAction.getVariableKey(), environmentVariable);
                }
            } else {
                String error = format("Given action type has not supported [{0}] for Action [{1}] in pre action scope", actionType, action.getClass().getName());
                LOGGER.error(error);
                throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
            }
        } else if (action instanceof VariableCleanAction) {

        } else if (action instanceof ExecuteAnotherTest) {

        } else if (action instanceof ExecuteAnotherApiCall) {

        } else {
            String error = format("Given action has not supported yet [{0}]", action.getClass().getName());
            LOGGER.error(error);
            throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
        }

    }

}

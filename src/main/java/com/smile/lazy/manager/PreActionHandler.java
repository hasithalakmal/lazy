package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.enums.ActionTypeEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.environment.Environment;
import com.smile.lazy.beans.environment.EnvironmentVariable;
import com.smile.lazy.beans.suite.actions.Action;
import com.smile.lazy.beans.suite.actions.ExecuteAnotherApiCall;
import com.smile.lazy.beans.suite.actions.ExecuteAnotherTest;
import com.smile.lazy.beans.suite.actions.VariableCleanAction;
import com.smile.lazy.beans.suite.actions.VariableDeclarationAction;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static java.text.MessageFormat.format;

@Service
public class PreActionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(PreActionHandler.class);

    public void executePreAction(LazySuite lazySuite, Action preAction) throws LazyException {
        ActionTypeEnum actionType = preAction.getActionType();
        if (preAction instanceof VariableDeclarationAction) {
            VariableDeclarationAction variableDeclarationAction = (VariableDeclarationAction) preAction;
            DataSourceEnum dataSource = variableDeclarationAction.getDataSourceEnum();
            String variableValue = null;
            if (dataSource == DataSourceEnum.PROVIDED) {
                variableValue = variableDeclarationAction.getVariableValue();
            } else {
                String error = format("Given data source has not supported [{0}] for Action [{1}] in pre action scope", actionType, preAction.getClass().getName());
                LOGGER.error(error);
                throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
            }

            EnvironmentVariable environmentVariable = new EnvironmentVariable(variableDeclarationAction.getVariableKey(), variableValue, variableDeclarationAction.getDataType());
            if (ActionTypeEnum.SET_GLOBAL_VARIABLE == actionType) {
                lazySuite.getStack().getGlobalEnvironment().getEnvironmentVariableMap().put(variableDeclarationAction.getVariableKey(), environmentVariable);
            } else if (ActionTypeEnum.SET_ENVIRONMENT_VARIABLE == actionType) {
                String environmentDisplayId = variableDeclarationAction.getEnvironmentDisplayId();
                if (lazySuite.getStack().getEnvironments().containsKey(environmentDisplayId)) {
                    Environment environment = lazySuite.getStack().getEnvironments().get(environmentDisplayId);
                    environment.getEnvironmentVariableMap().put(variableDeclarationAction.getVariableKey(), environmentVariable);
                }
            } else {
                String error = format("Given action type has not supported [{0}] for Action [{1}] in pre action scope", actionType, preAction.getClass().getName());
                LOGGER.error(error);
                throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
            }
        } else if (preAction instanceof VariableCleanAction) {

        } else if (preAction instanceof ExecuteAnotherTest) {

        } else if (preAction instanceof ExecuteAnotherApiCall) {

        } else {
            String error = format("Given action has not supported yet [{0}]", preAction.getClass().getName());
            LOGGER.error(error);
            throw new LazyException(HttpStatus.NOT_IMPLEMENTED, ErrorCodes.NOT_IMPLEMENTED, error);
        }

    }

}

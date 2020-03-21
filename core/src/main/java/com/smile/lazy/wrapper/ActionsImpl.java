package com.smile.lazy.wrapper;

import com.smile.lazy.beans.enums.ActionTypeEnum;
import com.smile.lazy.beans.enums.DataSourceEnum;
import com.smile.lazy.beans.suite.actions.Action;
import com.smile.lazy.beans.suite.actions.VariableDeclarationAction;

public class ActionsImpl {

    private ActionsImpl() {
        //This is a private constructor
    }

    public static Action createGlobalVariableFromBody(String key, String jsonPath) {
        return new VariableDeclarationAction(ActionTypeEnum.SET_GLOBAL_VARIABLE, DataSourceEnum.BODY, key, jsonPath);
    }
}

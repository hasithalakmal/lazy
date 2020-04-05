package com.smile24es.lazy.wrapper;

import com.smile24es.lazy.beans.enums.ActionTypeEnum;
import com.smile24es.lazy.beans.enums.DataSourceEnum;
import com.smile24es.lazy.beans.suite.actions.Action;
import com.smile24es.lazy.beans.suite.actions.VariableDeclarationAction;

public class Actions {

    private Actions() {
        //This is a private constructor
    }

    public static Action createGlobalVariableFromBody(String key, String jsonPath) {
        return new VariableDeclarationAction(ActionTypeEnum.SET_GLOBAL_VARIABLE, DataSourceEnum.BODY, key, jsonPath);
    }
}

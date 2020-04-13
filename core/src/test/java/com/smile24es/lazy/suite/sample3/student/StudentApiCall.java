package com.smile24es.lazy.suite.sample3.student;

import com.smile24es.lazy.beans.enums.AssertionOperationEnum;
import com.smile24es.lazy.beans.enums.DataSourceEnum;
import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.beans.suite.QueryParam;
import com.smile24es.lazy.beans.suite.assertions.AssertionRule;
import com.smile24es.lazy.beans.suite.assertions.BodyValueAssertion;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.suite.sample0.dto.AccountTo;
import com.smile24es.lazy.suite.sample0.dto.ErrorTo;
import com.smile24es.lazy.wrapper.Actions;
import com.smile24es.lazy.wrapper.Assert;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static java.text.MessageFormat.format;

public class StudentApiCall {

    public static ApiCall updateCompleteAccount() {
        ApiCall getAccountApiCall = new ApiCall("Update Complete Student");
        getAccountApiCall.setUri("service/lazy/test");
        getAccountApiCall.addAssertionRule(Assert.responseCodeEqual(200));

        //Null/Not Null assertions
        getAccountApiCall.addAssertionRule(Assert.notNull("$.accountId"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.accountName"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.isActive"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.monthlyCharge"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.phone"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.phone.number"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[0].id"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[0].key"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[0].value"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[0].isActive"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[0].chargeForFeature"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].id"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].key"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].value"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].isActive"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].chargeForFeature"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].supportedMethods"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].supportedMethods[0].type"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].supportedMethods[0].minValue"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].supportedMethods[1].type"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.settings[1].supportedMethods[1].allowed"));

        getAccountApiCall.addAssertionRule(Assert.isKeyAvailable("$.accountName"));
        getAccountApiCall.addAssertionRule(Assert.isKeyUnavailable("$.invalidKey"));

        //Equal assertions
        getAccountApiCall.addAssertionRule(Assert.equal("$.accountId", 1));
        getAccountApiCall.addAssertionRule(Assert.equal("$.accountName", "Smile24"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.isActive", true));
        getAccountApiCall.addAssertionRule(Assert.equal("$.monthlyCharge", 10.75));
        getAccountApiCall.addAssertionRule(Assert.equal("$.phone.number", "12345678"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[0].id", 840));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[0].key", "payment.period"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[0].value", "monthly"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[0].isActive", true));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[0].chargeForFeature", 11.9));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[1].id", 841));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[1].key", "payment.method"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[1].value", "direct"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[1].isActive", true));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[1].chargeForFeature", 12.86));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[1].supportedMethods[0].type", "Card"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[1].supportedMethods[0].minValue", 1));
        getAccountApiCall.addAssertionRule(Assert.equal("$.settings[1].supportedMethods[1].type", "Cash"));

        //String operations
        getAccountApiCall.addAssertionRule(Assert.contains("$.accountName", "Smile"));
        getAccountApiCall.addAssertionRule(Assert.notContains("$.accountName", "Lazy"));


        //Max min - numeric logic
        getAccountApiCall.addAssertionRule(Assert.greaterThan("$.monthlyCharge", 10.00));
        getAccountApiCall.addAssertionRule(Assert.lessThan("$.monthlyCharge", 11.00));
        getAccountApiCall.addAssertionRule(Assert.greaterThanOrEqual("$.monthlyCharge", 10.75));
        getAccountApiCall.addAssertionRule(Assert.lessThanOrEqual("$.monthlyCharge", 10.75));
        getAccountApiCall.addAssertionRule(Assert.between("$.monthlyCharge", 10.00, 11.00));

        //List related logic
        getAccountApiCall.addAssertionRule(Assert.containsExactly("$.settings[*].key", Arrays.asList("payment.period", "payment.method")));
        getAccountApiCall.addAssertionRule(Assert.containsExactly("$.settings[*].id", Arrays.asList(840, 841)));
        getAccountApiCall.addAssertionRule(Assert.containsExactly("$.settings[*].chargeForFeature", Arrays.asList(11.9, 12.86)));
        getAccountApiCall.addAssertionRule(Assert.containsExactly("$.settings[*].isActive", Arrays.asList(true, true)));

        getAccountApiCall.addAssertionRule(Assert.containsAll("$.settings[*].key", Arrays.asList("payment.period")));
        getAccountApiCall.addAssertionRule(Assert.containsAll("$.settings[*].id", Arrays.asList(840)));
        getAccountApiCall.addAssertionRule(Assert.containsAll("$.settings[*].chargeForFeature", Arrays.asList(11.9)));
        getAccountApiCall.addAssertionRule(Assert.containsAll("$.settings[*].isActive", Arrays.asList(true)));

        getAccountApiCall.addAssertionRule(Assert.containsAny("$.settings[*].key", Arrays.asList("payment.period", "INVALID")));
        getAccountApiCall.addAssertionRule(Assert.containsAny("$.settings[*].id", Arrays.asList(840, 1000)));
        getAccountApiCall.addAssertionRule(Assert.containsAny("$.settings[*].chargeForFeature", Arrays.asList(11.9, 1000)));
        getAccountApiCall.addAssertionRule(Assert.containsAny("$.settings[*].isActive", Arrays.asList(true, false)));

        //Advanced list related logic
        getAccountApiCall.addAssertionRule(Assert.containsExactly("$.settings[?(@.key=='payment.period')].value", Arrays.asList("monthly")));
        getAccountApiCall.addAssertionRule(Assert.containsExactly("$.settings[?(@.supportedMethods)].id", Arrays.asList(841)));
        getAccountApiCall.addAssertionRule(Assert.containsExactly("$.settings[?(@.supportedMethods)].supportedMethods.[?(@.type == 'Card')].minValue", Arrays.asList(1)));
        getAccountApiCall.addAssertionRule(Assert.containsExactly("$.settings[?(@.supportedMethods)].supportedMethods.[?(@.type == 'Cash')].allowed[*]", Arrays.asList("SLR", "USD")));

        return getAccountApiCall;
    }

    public static ApiCall getInvalidAccountApiCall(String accountId, ErrorTo errorTo) {
        ApiCall getAccountApiCall = new ApiCall("Get invalid student by Id");
        getAccountApiCall.setUri(format("service/accounts/{0}", accountId));
        getAccountApiCall.addAssertionRule(Assert.responseCodeEqual(404));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.code"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.description"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.additionalInfo"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.code", errorTo.getCode()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.description", errorTo.getDescription()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.additionalInfo", errorTo.getAdditionalInfo()));
        return getAccountApiCall;
    }

    //Invalid assertion - invalid data type
    public static ApiCall getInvalidRuleApiCall(String accountId, ErrorTo errorTo) {
        ApiCall getAccountApiCall = new ApiCall("Get invalid student by Id");
        getAccountApiCall.setUri(format("service/accounts/{0}", accountId));
        getAccountApiCall.addAssertionRule(Assert.responseCodeEqual(404));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.codeInvalid"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.description"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.additionalInfo"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.code", errorTo.getCode()));
        AssertionRule invalidCustomRule = new AssertionRule("Custom invalid meta-rule assertion", DataSourceEnum.BODY, AssertionOperationEnum.TRUE,
              new BodyValueAssertion("$.description", "hhhh"));
        getAccountApiCall.addAssertionRule(invalidCustomRule);
        getAccountApiCall.addAssertionRule(Assert.equal("$.additionalInfo", errorTo.getAdditionalInfo()));
        return getAccountApiCall;
    }

    //Failed assertion - invalid JSON path
    public static ApiCall getFailedRuleApiCall(String accountId, ErrorTo errorTo) {
        ApiCall getAccountApiCall = new ApiCall("Get invalid student by Id");
        getAccountApiCall.setUri(format("service/accounts/{0}", accountId));
        getAccountApiCall.addAssertionRule(Assert.responseCodeEqual(404));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.codeInvalid"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.description"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.additionalInfo"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.code", errorTo.getCode()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.description", errorTo.getDescription()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.additionalInfo", errorTo.getAdditionalInfo()));
        return getAccountApiCall;
    }

    //Skipped assertion - invalid JSON path
    public static ApiCall getSkippedRuleApiCall(String accountId, ErrorTo errorTo) {
        ApiCall getAccountApiCall = new ApiCall("Get invalid student by Id");
        getAccountApiCall.setPort(5050);
        getAccountApiCall.setUri(format("service/accounts/{0}", accountId));
        getAccountApiCall.addAssertionRule(Assert.responseCodeEqual(404));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.codeInvalid"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.description"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.additionalInfo"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.code", errorTo.getCode()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.description", errorTo.getDescription()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.additionalInfo", errorTo.getAdditionalInfo()));
        return getAccountApiCall;
    }

    public static ApiCall createInvalidAccountApiCall(String accountId, ErrorTo errorTo) {
        ApiCall getAccountApiCall = new ApiCall("Create invalid student by Id");
        getAccountApiCall.setUri(format("service/accounts/{0}", accountId));
        getAccountApiCall.addAssertionRule(Assert.responseCodeEqual(404));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.code"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.description"));
        getAccountApiCall.addAssertionRule(Assert.notNull("$.additionalInfo"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.code", errorTo.getCode()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.description", errorTo.getDescription()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.additionalInfo", errorTo.getAdditionalInfo()));
        return getAccountApiCall;
    }

    public static ApiCall getValidAccountApiCallWithOutSettings(AccountTo accountTo) {
        ApiCall getAccountApiCall = new ApiCall("Get Student by Id without settings");
        getAccountApiCall.setUri("service/accounts/{{lazy.global.created.account.id}}");
        getAccountApiCall.addAssertionRule(Assert.responseCodeEqual(200));
        getAccountApiCall.addAssertionRule(Assert.notNull("Student Name not null assertion", "$.accountName"));
        getAccountApiCall.addAssertionRule(Assert.equal("$.accountName", accountTo.getAccountName()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.status", accountTo.getAccountStatus()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.enterpriseId", accountTo.getEnterpriseId()));
        getAccountApiCall.addAssertionRule(Assert.equal("$.versionId", accountTo.getVersionId()));
        getAccountApiCall.addAssertionRule(Assert.equal("custom", "$.accountName", "{{lazy.global.created.account.accountName}}"));
        return getAccountApiCall;
    }

    public static ApiCall getValidAccountApiCallWithSettings(AccountTo accountTo) {
        ApiCall getAccountApiCall = new ApiCall("Get Student by Id with settings");
        getAccountApiCall.setUri("service/accounts/{{lazy.global.created.account.id}}");
        getAccountApiCall.setQueryParams(new QueryParam("fake", "test"));
        getAccountApiCall.setQueryParams(new QueryParam("settings", "true"));
        getAccountApiCall.addAssertionRule(Assert.responseCodeEqual(200));
        accountDetailAssertion(accountTo, getAccountApiCall);
        getAccountApiCall.addAssertionRule(Assert.equal("custom", "$.accountName", "{{lazy.global.created.account.accountName}}"));
        return getAccountApiCall;
    }

    public static ApiCall createSuccessAccount(AccountTo accountTo) throws LazyCoreException {
        ApiCall createAccountApiCall = new ApiCall("Create Complete Student");
        createAccountApiCall.setUri("service/accounts");
        createAccountApiCall.setUri("service/accounts");
        createAccountApiCall.setHttpMethod("POST");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("accountName", accountTo.getAccountName());
        templateData.put("accountStatus", accountTo.getAccountStatus());
        templateData.put("enterpriseId", accountTo.getEnterpriseId());
        templateData.put("versionId", accountTo.getVersionId());
        templateData.put("settings", accountTo.getAccountSettings());

        createAccountApiCall.setRequestBodyFromJsonTemplate("request-body/account-api/templates/create-account.ftl", templateData);
        accountDetailAssertion(accountTo, createAccountApiCall);

        createAccountApiCall.disableAssertion("account.api.max.response.time");
        createAccountApiCall.addAssertionRule(Assert.responseTimeLessThan("300"));

        createAccountApiCall.getPostActions().add(Actions.createGlobalVariableFromResponseBody("created.account.id", "$.accountId"));
        createAccountApiCall.getPostActions().add(Actions.createGlobalVariableFromResponseBody("created.account.accountName", "$.accountName"));

        return createAccountApiCall;
    }


    public static ApiCall createSuccessAccountMinData(AccountTo accountTo) throws LazyCoreException {
        ApiCall createAccountApiCall = new ApiCall("Create Student Simple");
        createAccountApiCall.setUri("service/accounts");
        createAccountApiCall.setUri("service/accounts");
        createAccountApiCall.setHttpMethod("POST");

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("accountName", accountTo.getAccountName());
        templateData.put("accountStatus", accountTo.getAccountStatus());
        templateData.put("enterpriseId", accountTo.getEnterpriseId());
        templateData.put("versionId", accountTo.getVersionId());

        createAccountApiCall.setRequestBodyFromJsonTemplate("request-body/account-api/templates/create-account.ftl", templateData);
        createAccountApiCall.addAssertionRule(Assert.notNull("Student Name not null assertion", "$.accountName"));
        createAccountApiCall.addAssertionRule(Assert.equal("$.accountName", accountTo.getAccountName()));
        createAccountApiCall.addAssertionRule(Assert.equal("$.status", accountTo.getAccountStatus()));
        createAccountApiCall.addAssertionRule(Assert.equal("$.enterpriseId", accountTo.getEnterpriseId()));
        createAccountApiCall.addAssertionRule(Assert.equal("$.versionId", accountTo.getVersionId()));

        createAccountApiCall.disableAssertion("account.api.max.response.time");
        createAccountApiCall.addAssertionRule(Assert.responseTimeLessThan("300"));

        createAccountApiCall.getPostActions().add(Actions.createGlobalVariableFromResponseBody("created.account.id", "$.accountId"));
        createAccountApiCall.getPostActions().add(Actions.createGlobalVariableFromResponseBody("created.account.accountName", "$.accountName"));

        return createAccountApiCall;
    }

    private static void accountDetailAssertion(AccountTo accountTo, ApiCall accountApiCall) {
        accountApiCall.addAssertionRule(Assert.notNull("Student Name not null assertion", "$.accountName"));
        accountApiCall.addAssertionRule(Assert.equal("$.accountName", accountTo.getAccountName()));
        accountApiCall.addAssertionRule(Assert.equal("$.status", accountTo.getAccountStatus()));
        accountApiCall.addAssertionRule(Assert.equal("$.enterpriseId", accountTo.getEnterpriseId()));
        accountApiCall.addAssertionRule(Assert.equal("$.versionId", accountTo.getVersionId()));

        accountApiCall.addAssertionRule(Assert.notNull("Settings not null assertion", "$.settings"));
        accountApiCall.addAssertionRule(Assert.equal("Student setting length assertion", "$.settings.length()", accountTo.getAccountSettings().size()));

        accountApiCall.addAssertionRule(Assert.equal("setting - 1 - key", "$.settings[0].key", accountTo.getAccountSettings().get(0).getKey()));
        accountApiCall.addAssertionRule(Assert.equal("setting - 1 - value", "$.settings[0].value", accountTo.getAccountSettings().get(0).getValue()));
        accountApiCall.addAssertionRule(Assert.notNull("setting - 1 - id", "$.settings[0].id"));
        accountApiCall.addAssertionRule(Assert.equal("setting - 1 - status", "$.settings[0].settingStatus", "ACTIVE"));
        accountApiCall.addAssertionRule(Assert.equal("setting - 2 - key", "$.settings[1].key", accountTo.getAccountSettings().get(1).getKey()));
        accountApiCall.addAssertionRule(Assert.equal("setting - 2 - value", "$.settings[1].value", accountTo.getAccountSettings().get(1).getValue()));
        accountApiCall.addAssertionRule(Assert.notNull("setting - 2 - id", "$.settings[1].id"));
        accountApiCall.addAssertionRule(Assert.equal("setting - 2 - status", "$.settings[1].settingStatus", "ACTIVE"));

    }
}

package com.smile.lazy.suite.sample.apicall;

import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionRuleGroup;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.suite.sample.dto.AccountSetting;
import com.smile.lazy.wrapper.Actions;
import com.smile.lazy.wrapper.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountApiCalls {

    public static ApiCall createAccountApiCall() {
        ApiCall apiCall1 = new ApiCall(1, "Create Account - String");
        apiCall1.getStack().addDefaultAssertionGroup(createDefaultAssertionRuleGroup());
        apiCall1.setUri("service/accounts");
        apiCall1.setHttpMethod("POST");
        apiCall1.setRequestBody("{\"status\":\"ACTIVE\",\"createdBy\":\"12345\",\"parentId\":\"1\",\"enterpriseId\":\"1\","
              + "\"accountName\":\"Sathara-1577641690\",\"ownerName\":\"Hasitha-1577641690\",\"versionId\":\"1.0.0\","
              + "\"settings\":[{\"key\":\"setting1\",\"value\":\"1577641690\"},{\"key\":\"setting2\",\"value\":\"1577641690\"}]}");
        apiCall1.addAssertionGroup(accountAssertionGroup1("Sathara-1577641690"));
        apiCall1.getPostActions().add(Actions.createGlobalVariableFromBody("created.account.id", "$['accountId']"));
        apiCall1.getPostActions().add(Actions.createGlobalVariableFromBody("created.account.name", "$['accountName']"));
        return apiCall1;
    }

    private static AssertionRuleGroup createDefaultAssertionRuleGroup() {
        AssertionRuleGroup defaultCreateAssertionGroup = new AssertionRuleGroup(1, "Test case assertion group");
        List<AssertionRule> assertionRules = defaultCreateAssertionGroup.getAssertionRules();
        //Performance impacted assertion
        AssertionRule responseTimeAssertion = Assert.responseTimeAssertionGreaterThanGivenMilliSeconds("100");
        responseTimeAssertion.setAssertionRuleKey("high.performance.response.time.assertion");
        assertionRules.add(responseTimeAssertion);
        return defaultCreateAssertionGroup;
    }

    public static ApiCall createAccountApiCallWithJsonFile() throws LazyCoreException {
        ApiCall apiCall1 = new ApiCall(1, "Create Account - JSON file");
        apiCall1.setUri("service/accounts");
        apiCall1.setHttpMethod("POST");
        apiCall1.setRequestBodyFromJson("request-body/account-api/create-account/create-simple-account.json");
        apiCall1.addAssertionGroup(accountAssertionGroup1("Sathara-1577641690"));
        apiCall1.getPostActions().add(Actions.createGlobalVariableFromBody("created.account.id", "$['accountId']"));
        apiCall1.getPostActions().add(Actions.createGlobalVariableFromBody("created.account.name", "$['accountName']"));
        apiCall1.setAssignGroups(Arrays.asList("BVT", "Group2"));
        return apiCall1;
    }

    public static ApiCall createAccountApiCallWithTemplateFile() throws LazyCoreException {
        ApiCall apiCall1 = new ApiCall(1, "Create Account - Template");
        apiCall1.setUri("service/accounts");
        apiCall1.setHttpMethod("POST");

        AccountSetting accountSettings1 = new AccountSetting("setting1", "value1");
        AccountSetting accountSettings2 = new AccountSetting("setting2", "value2");

        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(accountSettings1);
        accountSettings.add(accountSettings2);

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("accountStatus", "ACTIVE");
        templateData.put("accountName", "My Account");
        templateData.put("enterpriseId", "1");
        templateData.put("versionId", "1.0.0");
        templateData.put("settings", accountSettings);

        apiCall1.setRequestBodyFromJsonTemplate("request-body/account-api/templates/create-account.ftl", templateData);
        apiCall1.addAssertionGroup(accountAssertionGroup1("My Account"));
        apiCall1.getPostActions().add(Actions.createGlobalVariableFromBody("created.account.id", "$['accountId']"));
        apiCall1.getPostActions().add(Actions.createGlobalVariableFromBody("created.account.name", "$['accountName']"));

        apiCall1.setAssignGroups(Arrays.asList("BVT"));
        return apiCall1;
    }

    public static ApiCall createAccountApiCallWithTemplateFileComplex() throws LazyCoreException {
        ApiCall apiCall1 = new ApiCall(1, "Create Account - Logical template");
        apiCall1.setUri("service/accounts");
        apiCall1.setHttpMethod("POST");

        AccountSetting accountSettings1 = new AccountSetting("complex setting1", "value1");
        AccountSetting accountSettings2 = new AccountSetting("complex setting2", "value2");

        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(accountSettings1);
        accountSettings.add(accountSettings2);

        Map<String, Object> templateData = new HashMap<>();
        templateData.put("accountStatus", "ACTIVE");
        templateData.put("accountName", "My Account");
        templateData.put("enterpriseId", "1");
        templateData.put("versionId", "1.0.0");
        templateData.put("settings", accountSettings);

        apiCall1.setRequestBodyFromJsonTemplate("request-body/account-api/templates/create-account-2.ftl", templateData);
        apiCall1.addAssertionGroup(accountAssertionGroup1("My Account"));
        apiCall1.getPostActions().add(Actions.createGlobalVariableFromBody("created.account.id", "$['accountId']"));
        apiCall1.getPostActions().add(Actions.createGlobalVariableFromBody("created.account.name", "$['accountName']"));

        apiCall1.setAssignGroups(Arrays.asList("Group2"));

        return apiCall1;
    }

    public static ApiCall getAccountApiCall() {
        ApiCall apiCall2 = new ApiCall(2, "Get Account by Id");
        apiCall2.disableAssertion("created.http.status.assertion");
        apiCall2.setUri("service/accounts/{{lazy.global.created.account.id}}");
        apiCall2.addAssertionGroup(accountAssertionGroup1("{{lazy.global.created.account.name}}"));
        apiCall2.addAssertionRule(Assert.equalBodyValueAssertion("$['accountId']", "{{lazy.global.created.account.id}}"));
        apiCall2.addAssertionRule(Assert.responseCodeAssertion("200"));
        return apiCall2;
    }

    /**
     * Temporary api call to validate assertion enablement
     *
     * @return
     */
    public static ApiCall getAccountApiCall2() {
        ApiCall apiCall2 = new ApiCall(2, "Get Account by Id");
        apiCall2.enableAssertion("created.http.status.assertion");
        apiCall2.setUri("service/accounts/{{lazy.global.created.account.id}}");
        apiCall2.addAssertionGroup(accountAssertionGroup1("{{lazy.global.created.account.name}}"));
        apiCall2.addAssertionRule(Assert.equalBodyValueAssertion("$['accountId']", "{{lazy.global.created.account.id}}"));
        apiCall2.addAssertionRule(Assert.responseCodeAssertion("200"));
        return apiCall2;
    }

    private static AssertionRuleGroup accountAssertionGroup1(String expectedAccountName) {
        AssertionRuleGroup assertionRuleGroup1 = new AssertionRuleGroup(1000, "Create Account success assertions");
        List<AssertionRule> assertionRules = assertionRuleGroup1.getAssertionRules();
        assertionRules.add(Assert.notNullBodyValueAssertion("$['accountName']"));
        assertionRules.add(Assert.equalBodyValueAssertion("$['accountName']", expectedAccountName));
        return assertionRuleGroup1;
    }
}

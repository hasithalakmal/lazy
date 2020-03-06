package com.smile.lazy.sample.apicall;

import com.smile.lazy.beans.suite.ApiCall;
import com.smile.lazy.beans.suite.assertions.AssertionRule;
import com.smile.lazy.beans.suite.assertions.AssertionRuleGroup;
import com.smile.lazy.wrapper.Assert;

import java.util.List;

import static com.smile.lazy.wrapper.Assert.notNullBodyValueAssertion;

public class AccountApiCalls {

    public static ApiCall createAccountApiCall() {
        ApiCall apiCall1 = new ApiCall(1, "Create Account");
        apiCall1.setUri("service/accounts");
        apiCall1.setHttpMethod("POST");
        apiCall1.setRequestBody("{\"status\":\"ACTIVE\",\"createdBy\":\"12345\",\"parentId\":\"1\",\"enterpriseId\":\"1\","
              + "\"accountName\":\"Sathara-1577641690\",\"ownerName\":\"Hasitha-1577641690\",\"versionId\":\"1.0.0\","
              + "\"settings\":[{\"key\":\"setting1\",\"value\":\"1577641690\"},{\"key\":\"setting2\",\"value\":\"1577641690\"}]}");
        AssertionRuleGroup assertionRuleGroup1 = createAssertionRuleGroupForAccountCreation("Sathara-1577641690");
        apiCall1.setAssertionRuleGroup(assertionRuleGroup1);
        return apiCall1;
    }

    public static ApiCall getAccountApiCall() {
        ApiCall apiCall2 = new ApiCall(2, "Get Account by Id");
        apiCall2.setUri("service/accounts/34");
        return apiCall2;
    }


    private static AssertionRuleGroup createAssertionRuleGroupForAccountCreation(String expectedAccountName) {
        AssertionRuleGroup assertionRuleGroup1 = new AssertionRuleGroup(1000, "Create Account success assertions");
        List<AssertionRule> assertionRules = assertionRuleGroup1.getAssertionRules();
        assertionRules.add(Assert.notNullBodyValueAssertion("$['accountName']"));
        assertionRules.add(Assert.equalBodyValueAssertion("$['accountName']", expectedAccountName));
        return assertionRuleGroup1;
    }
}

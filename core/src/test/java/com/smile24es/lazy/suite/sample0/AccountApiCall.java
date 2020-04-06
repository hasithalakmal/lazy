package com.smile24es.lazy.suite.sample0;

import com.smile24es.lazy.beans.suite.ApiCall;
import com.smile24es.lazy.wrapper.Assert;

public class AccountApiCall {

    public static ApiCall getAccountApiCall() {
        ApiCall apiCall2 = new ApiCall("Get Account by Id");
        apiCall2.setUri("service/accounts/1000");
        apiCall2.addAssertionRule(Assert.responseCodeAssertion("200"));
        apiCall2.addAssertionRule(Assert.notNullBodyValueAssertion("$['accountName']"));
        apiCall2.addAssertionRule(Assert.equalBodyValueAssertion("$['accountId']", "100001"));
        apiCall2.addAssertionRule(Assert.equalBodyValueAssertion("$['accountName']", "Lazy Account"));
        return apiCall2;
    }
}

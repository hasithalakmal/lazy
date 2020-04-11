package com.smile24es.lazy.suite.sample0.account;

import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.suite.sample0.dto.AccountSetting;
import com.smile24es.lazy.suite.sample0.dto.AccountTo;
import com.smile24es.lazy.suite.sample0.dto.ErrorTo;

import java.util.ArrayList;
import java.util.List;

import static com.smile24es.lazy.suite.sample0.account.AccountApiCall.mockApiCall;
import static java.text.MessageFormat.format;

public class GetAccountTestCases {

    public static TestCase createAccountSuccessTestCase() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Create Account successfully");

        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(new AccountSetting("payment.period", "monthly"));
        accountSettings.add(new AccountSetting("max.number.of.students", "100"));
        AccountTo accountTo = new AccountTo("Smile24", "ACTIVE", "1", "1.0.0", accountSettings);

        createAccountTC.getApiCalls().add(AccountApiCall.createSuccessAccount(accountTo));

        createAccountTC.getApiCalls().add(AccountApiCall.getValidAccountApiCall(accountTo));
        return createAccountTC;
    }

    public static TestCase mockApiTest() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Mock API test");
        createAccountTC.getApiCalls().add(mockApiCall());
        return createAccountTC;
    }

    public static TestCase getInvalidAccount() throws LazyCoreException {
        TestCase invalidAccountGetTC = new TestCase("Get Invalid Account");

        String invalidAccountId = "10000-invalid";
        ErrorTo errorTo = new ErrorTo("ACC_10100", format("No account found for the given account id [{0}]", invalidAccountId), "404 NOT_FOUND");

        invalidAccountGetTC.getApiCalls().add(AccountApiCall.getInvalidAccountApiCall(invalidAccountId, errorTo));
        return invalidAccountGetTC;
    }
}

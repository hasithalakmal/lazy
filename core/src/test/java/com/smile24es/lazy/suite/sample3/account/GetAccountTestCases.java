package com.smile24es.lazy.suite.sample3.account;

import com.smile24es.lazy.beans.suite.TestCase;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.suite.sample0.dto.AccountSetting;
import com.smile24es.lazy.suite.sample0.dto.AccountTo;
import com.smile24es.lazy.suite.sample0.dto.ErrorTo;

import java.util.ArrayList;
import java.util.List;

import static com.smile24es.lazy.suite.sample0.account.AccountApiCall.updateCompleteAccount;
import static com.smile24es.lazy.suite.sample0.account.AccountApiCall.updateSimpleAccount;
import static com.smile24es.lazy.suite.sample3.student.StudentApiCall.getFailedRuleApiCall;
import static com.smile24es.lazy.suite.sample3.student.StudentApiCall.getInvalidAccountApiCall;
import static com.smile24es.lazy.suite.sample3.student.StudentApiCall.getInvalidRuleApiCall;
import static com.smile24es.lazy.suite.sample3.student.StudentApiCall.getSkippedRuleApiCall;
import static java.text.MessageFormat.format;

public class GetAccountTestCases {

    //Success
    public static TestCase createAccountSuccessTestCase() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Create simple account");

        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(new AccountSetting("payment.period", "monthly"));
        accountSettings.add(new AccountSetting("max.number.of.students", "100"));
        AccountTo accountTo = new AccountTo("Smile24", "ACTIVE", "1", "1.0.0", accountSettings);

        createAccountTC.getApiCalls().add(AccountApiCall.createSuccessAccount(accountTo));

        createAccountTC.getApiCalls().add(AccountApiCall.getValidAccountApiCallWithSettings(accountTo));
        return createAccountTC;
    }

    //Success
    public static TestCase createComplexAccountSuccessTestCase() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Create complex account");

        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(new AccountSetting("payment.period", "monthly"));
        accountSettings.add(new AccountSetting("max.number.of.students", "100"));
        AccountTo accountTo = new AccountTo("Smile24", "ACTIVE", "1", "1.0.0", accountSettings);

        createAccountTC.getApiCalls().add(AccountApiCall.createSuccessAccount(accountTo));

        createAccountTC.getApiCalls().add(AccountApiCall.getValidAccountApiCallWithSettings(accountTo));
        return createAccountTC;
    }

    //Success
    public static TestCase createAccountWithMinDataSuccessTestCase() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Create complex account");

        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(new AccountSetting("payment.period", "monthly"));
        accountSettings.add(new AccountSetting("max.number.of.students", "100"));
        AccountTo accountTo = new AccountTo("Smile24", "ACTIVE", "1", "1.0.0", accountSettings);

        createAccountTC.getApiCalls().add(AccountApiCall.createSuccessAccountMinData(accountTo));

        createAccountTC.getApiCalls().add(AccountApiCall.getValidAccountApiCallWithOutSettings(accountTo));
        return createAccountTC;
    }

    //Success
    public static TestCase getAccountWithSettings() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Get Account with Settings");

        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(new AccountSetting("payment.period", "monthly"));
        accountSettings.add(new AccountSetting("max.number.of.students", "100"));
        AccountTo accountTo = new AccountTo("Smile24", "ACTIVE", "1", "1.0.0", accountSettings);

        createAccountTC.getApiCalls().add(AccountApiCall.createSuccessAccount(accountTo));

        createAccountTC.getApiCalls().add(AccountApiCall.getValidAccountApiCallWithSettings(accountTo));
        return createAccountTC;
    }

    //Success
    public static TestCase getAccountWithOutSettings() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Get Account without Settings");

        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(new AccountSetting("payment.period", "monthly"));
        accountSettings.add(new AccountSetting("max.number.of.students", "100"));
        AccountTo accountTo = new AccountTo("Smile24", "ACTIVE", "1", "1.0.0", accountSettings);

        createAccountTC.getApiCalls().add(AccountApiCall.createSuccessAccount(accountTo));

        createAccountTC.getApiCalls().add(AccountApiCall.getValidAccountApiCallWithOutSettings(accountTo));
        return createAccountTC;
    }

    //Success
    public static TestCase updateAccount() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Update Account");
        createAccountTC.getApiCalls().add(updateCompleteAccount());
        createAccountTC.getApiCalls().add(updateSimpleAccount());
        return createAccountTC;
    }

    //Mix API Calls
    public static TestCase mixTestCases() throws LazyCoreException {
        TestCase createAccountTC = new TestCase("Update Account");
        createAccountTC.getApiCalls().add(updateCompleteAccount());
        String invalidAccountId = "10000-invalid";
        ErrorTo errorTo = new ErrorTo("ACC_10100", format("No account found for the given account id [{0}]", invalidAccountId), "404 NOT_FOUND");
        createAccountTC.getApiCalls().add(getFailedRuleApiCall(invalidAccountId, errorTo));
        createAccountTC.getApiCalls().add(getSkippedRuleApiCall(invalidAccountId, errorTo));
        createAccountTC.getApiCalls().add(getInvalidRuleApiCall(invalidAccountId, errorTo));
        List<AccountSetting> accountSettings = new ArrayList<>();
        accountSettings.add(new AccountSetting("payment.period", "monthly"));
        accountSettings.add(new AccountSetting("max.number.of.students", "100"));
        AccountTo accountTo = new AccountTo("Smile24", "ACTIVE", "1", "1.0.0", accountSettings);
        createAccountTC.getApiCalls().add(AccountApiCall.createSuccessAccount(accountTo));
        createAccountTC.getApiCalls().add(AccountApiCall.getValidAccountApiCallWithOutSettings(accountTo));
        return createAccountTC;
    }

    //Success
    public static TestCase getInvalidAccount() throws LazyCoreException {
        TestCase invalidAccountGetTC = new TestCase("Get Invalid Account");

        String invalidAccountId = "10000-invalid";
        ErrorTo errorTo = new ErrorTo("ACC_10100", format("No account found for the given account id [{0}]", invalidAccountId), "404 NOT_FOUND");

        invalidAccountGetTC.getApiCalls().add(AccountApiCall.getInvalidAccountApiCall(invalidAccountId, errorTo));
        return invalidAccountGetTC;
    }

    public static TestCase createAccountWithInvalidData() throws LazyCoreException {
        TestCase invalidAccountGetTC = new TestCase("Create Invalid Account");

        String invalidAccountId = "10000-invalid";
        ErrorTo errorTo = new ErrorTo("ACC_10100", format("No account found for the given account id [{0}]", invalidAccountId), "404 NOT_FOUND");

        invalidAccountGetTC.getApiCalls().add(AccountApiCall.createInvalidAccountApiCall(invalidAccountId, errorTo));
        return invalidAccountGetTC;
    }
}

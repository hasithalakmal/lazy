package com.smile24es.lazy.suite.sample3;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.suite.sample3.account.AccountTestSuite;


public class SmileLazyReportDemoSuite {
    public static LazySuite populateSampleLazySuite() throws LazyCoreException {
        LazySuite lazySuite = new LazySuite("Smile API suite");
        lazySuite.getTestSuites().add(AccountTestSuite.generalTestSuite());
        lazySuite.getTestSuites().add(AccountTestSuite.completeTestSuite());
        lazySuite.getTestSuites().add(AccountTestSuite.buildVerificationTestSuite());
        lazySuite.setReportFilePath("/opt/lazy_reports/");
        return lazySuite;
    }
}

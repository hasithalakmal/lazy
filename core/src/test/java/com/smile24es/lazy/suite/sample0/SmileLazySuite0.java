package com.smile24es.lazy.suite.sample0;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.suite.sample0.account.AccountTestSuite;

public class SmileLazySuite0 {
    public static LazySuite populateSampleLazySuite() throws LazyCoreException {
        LazySuite lazySuite = new LazySuite("Smile API suite");
        lazySuite.getTestSuites().add(AccountTestSuite.getAccountApiTestSuite());
        lazySuite.getTestSuites().add(AccountTestSuite.getStudentTestSuite());
        lazySuite.getTestSuites().add(AccountTestSuite.getStudentTestSuite2());
        lazySuite.setReportFilePath("/opt/lazy_reports/");
        return lazySuite;
    }
}

package com.smile24es.lazy.suite.sample3;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.suite.sample3.account.AccountTestSuite;
import com.smile24es.lazy.suite.sample3.student.StudentTestSuite;


public class SmileLazyReportDemoSuite {
    public static LazySuite populateSampleLazySuite() throws LazyCoreException {
        LazySuite lazySuite = new LazySuite("Smile API suite");
        lazySuite.getTestSuites().add(AccountTestSuite.completeTestSuite());
        lazySuite.getTestSuites().add(AccountTestSuite.generalTestSuite());
        lazySuite.getTestSuites().add(AccountTestSuite.buildVerificationTestSuite());
        lazySuite.getTestSuites().add(StudentTestSuite.studentGeneralTestSuite());
        lazySuite.getTestSuites().add(StudentTestSuite.studentBuildVerificationTestSuite());
        lazySuite.getTestSuites().add(StudentTestSuite.getInvalidSuite());
        lazySuite.getTestSuites().add(StudentTestSuite.failedCompleteTestSuite());
        lazySuite.getTestSuites().add(StudentTestSuite.skippedCompleteTestSuite());
        lazySuite.setReportFilePath("/home/hasithag/lazy_reports/");
        return lazySuite;
    }
}

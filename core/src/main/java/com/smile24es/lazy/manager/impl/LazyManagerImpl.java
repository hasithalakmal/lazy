package com.smile24es.lazy.manager.impl;

import com.smile24es.lazy.beans.DefaultValues;
import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.dto.IdDto;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.beans.suite.Global;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;
import com.smile24es.lazy.manager.LazyManager;
import com.smile24es.lazy.manager.TestSuiteManager;
import com.smile24es.lazy.manager.handlers.AssertionHandlerImpl;
import com.smile24es.lazy.manager.handlers.ReportDataHandler;
import com.smile24es.lazy.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class LazyManagerImpl extends LazyBaseManager implements LazyManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManagerImpl.class);

    @Autowired
    AssertionHandlerImpl assertionHandler;

    @Autowired
    private TestSuiteManager testSuiteManager;

    @Autowired
    private ReportDataHandler reportDataHandler;

    @Override
    public LazyExecutionData executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException {
        return executeLazySuite(lazySuite, null);
    }

    @Override
    public LazyExecutionData executeLazySuite(LazySuite lazySuite, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Ready to execute lazy suite - [{}]", JsonUtil.getJsonStringFromObject(lazySuite));
        }

        if (lazySuite == null) {
            String error = "Null lazy suite";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        String lazySuiteName = lazySuite.getLazySuiteName();
        if (StringUtils.isBlank(lazySuiteName)) {
            String error = "Lazy suite name should not be empty";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        LOGGER.debug("Preparing to execute lazy suite - [{}]", lazySuiteName);

        if (lazySuite.getStack() == null) {
            String error = "Lazy suite stack should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        DefaultValues defaultValues = lazySuite.getStack().getDefaultValues();
        validateDefaultValues(defaultValues);

        if (lazySuite.getGlobal() == null) {
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("No global object found, hence adding empty global object");
            }
            lazySuite.setGlobal(new Global());
        }

        LazyExecutionData lazyExecutionData = new LazyExecutionData();
        LOGGER.info("Ready to execute lazy suite - [{}]", lazySuiteName);
        testSuiteManager.executeTestSuites(lazySuite, lazyExecutionData, new IdDto(), lazyExecutionGroup);
        LOGGER.info("Executed lazy suite - [{}]", lazySuiteName);

        printResultTable(lazyExecutionData);
        reportDataHandler.populateReportData(lazyExecutionData);

        return lazyExecutionData;
    }


}

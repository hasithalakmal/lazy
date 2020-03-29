package com.smile.lazy.manager.impl;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.beans.suite.Global;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.manager.LazyManager;
import com.smile.lazy.manager.TestSuiteManager;
import com.smile.lazy.manager.handlers.AssertionHandlerImpl;
import com.smile.lazy.utils.JsonUtil;
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

    @Override
    public LazyExecutionData executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException {
        return executeLazySuite(lazySuite, null);
    }

    @Override
    public LazyExecutionData executeLazySuite(LazySuite lazySuite, String testSuiteName) throws LazyException, LazyCoreException {
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
        testSuiteManager.executeTestSuites(lazySuite, lazyExecutionData, new IdDto());
        LOGGER.info("Executed lazy suite - [{}]", lazySuiteName);

        printResultTable(lazyExecutionData);
        return lazyExecutionData;
    }


}

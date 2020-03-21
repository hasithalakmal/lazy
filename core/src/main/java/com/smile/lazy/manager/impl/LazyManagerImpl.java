package com.smile.lazy.manager.impl;

import com.smile.lazy.beans.DefaultValues;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.dto.IdDto;
import com.smile.lazy.beans.dto.ResultRecodeTo;
import com.smile.lazy.beans.dto.ResultSummeryTo;
import com.smile.lazy.beans.result.AssertionResult;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.beans.suite.Global;
import com.smile.lazy.beans.suite.Header;
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
import org.springframework.util.CollectionUtils;

@Service
public class LazyManagerImpl implements LazyManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManagerImpl.class);

    @Autowired
    AssertionHandlerImpl assertionHandler;

    @Autowired
    private TestSuiteManager testSuiteManager;

    @Override
    public AssertionResultList executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException {

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

        AssertionResultList assertionResultList = new AssertionResultList();
        LOGGER.info("Ready to execute lazy suite - [{}]", lazySuiteName);
        testSuiteManager.executeTestSuites(lazySuite, assertionResultList, new IdDto());
        LOGGER.info("Executed lazy suite - [{}]", lazySuiteName);

        printResultTable(assertionResultList);
        return assertionResultList;
    }

    private void printResultTable(AssertionResultList assertionResultList) throws LazyException {
        ResultSummeryTo assertionResultTo = getResultSummery(assertionResultList);
        try {
            String table = assertionResultTo.prettyPrintString();
            LOGGER.info(table);
        } catch (LazyCoreException e) {
            String error = "Invalid results table generated";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCodes.INVALID_TABLE, error);
        }
    }

    private ResultSummeryTo getResultSummery(AssertionResultList assertionResultList) {
        ResultSummeryTo resultSummeryTo = new ResultSummeryTo();
        for (AssertionResult assertionResult : assertionResultList.getResults()) {
            ResultRecodeTo resultRecodeTo = new ResultRecodeTo();
            resultRecodeTo.setResultId(Integer.toString(assertionResult.getResultId()));
            resultRecodeTo.setAssertionName(assertionResult.getAssertionRule().getAssertionRuleName());
            resultRecodeTo.setActualResult(assertionResult.getActualValue());
            resultRecodeTo.setExpectedResult(assertionResult.getAssertionRule().getAssertionValue() == null ? null :
                  assertionResult.getAssertionRule().getAssertionValue().getExpectedValue1());
            resultRecodeTo.setStatus(assertionResult.getAssertionStatus());
            resultRecodeTo.setIsPass(assertionResult.getPass().toString());
            resultSummeryTo.getResultRecodeToList().add(resultRecodeTo);
        }

        return resultSummeryTo;
    }

    private void validateDefaultValues(DefaultValues defaultValues) throws LazyException {
        if (defaultValues == null) {
            String error = "Lazy suite default values should not be null";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }

        if (StringUtils.isBlank(defaultValues.getProtocol())
              || StringUtils.isBlank(defaultValues.getHostName())
              || StringUtils.isBlank(defaultValues.getContextPath())
              || StringUtils.isBlank(defaultValues.getHttpMethod())
              || defaultValues.getPort() == null
              || defaultValues.getHeaderGroup() == null
              || CollectionUtils.isEmpty(defaultValues.getHeaderGroup().getHeaders())) {
            String error = "Invalid default values found";
            LOGGER.error(error);
            throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
        }
        //TODO - validate http method by using ENUM

        for (Header header : defaultValues.getHeaderGroup().getHeaders()) {
            if (header == null || StringUtils.isBlank(header.getKey()) || StringUtils.isBlank(header.getValue())) {
                String error = "Invalid default header values found";
                LOGGER.error(error);
                throw new LazyException(HttpStatus.BAD_REQUEST, ErrorCodes.INVALID_LAZY_SUITE, error);
            }
        }
    }


}

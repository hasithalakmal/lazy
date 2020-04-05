package com.smile24es.lazy.controllers;

import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.CustomErrorBean;
import com.smile24es.lazy.exception.LazyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

public abstract class BaseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);
    private static final String UNKNOWN_ERROR_MSG = "Unknown error has been thrown. Please contact the administrator";

    @ExceptionHandler(LazyException.class)
    @ResponseBody
    public CustomErrorBean handleForecasterException(LazyException e, HttpServletResponse response) {
        LOGGER.error(e.getMessage(), e);
        return getError(e.getHttpStatusCode().value(), e.getErrorCode(), e.getMessage(), e.getMessage(), response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public CustomErrorBean handleException(Exception e, HttpServletResponse response) {
        LOGGER.error(e.getMessage(), e);
        return getError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErrorCodes.UNKNOWN_SERVER_ERROR, UNKNOWN_ERROR_MSG,
              e.getMessage(), response);
    }

    private CustomErrorBean getError(int httpStatusCode, String errorCode, String description, String additionalInfo,
                                     HttpServletResponse response) {
        CustomErrorBean error = new CustomErrorBean(errorCode, description, additionalInfo);
        response.setStatus(httpStatusCode);
        return error;
    }
}

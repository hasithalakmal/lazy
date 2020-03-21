package com.smile.lazy.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.common.ErrorCodes;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.manager.Impl.LazyManagerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private JsonUtil() {
        //This is a private constructor
    }

    public static String getJsonStringFromObject(Object obj) throws LazyCoreException {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            String error = "JsonProcessingException found";
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.JSON_HANDLING_EXCEPTION, error);
        }
    }
}

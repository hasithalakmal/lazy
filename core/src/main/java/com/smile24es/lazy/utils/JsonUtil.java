package com.smile24es.lazy.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
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

    public static String getJsonStringFromObjectProtectedAndPublic(Object obj) throws LazyCoreException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC);
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            String error = "JsonProcessingException found";
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.JSON_HANDLING_EXCEPTION, error);
        }
    }
}

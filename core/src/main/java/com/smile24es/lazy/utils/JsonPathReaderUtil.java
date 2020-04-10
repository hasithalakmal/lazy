package com.smile24es.lazy.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import net.minidev.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;

import static java.text.MessageFormat.format;

public class JsonPathReaderUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPathReaderUtil.class);

    private JsonPathReaderUtil() {
        //This is a private constructor
    }

    public static String getAnyValueAsString(String json, String path){
        Object value = getValue(json, path);
        return value.toString();
    }

    public static String getString(String json, String path) throws LazyCoreException {
        Object value = getValue(json, path);
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return (String) value;
        } else {
            String error = format("JSON value is not a string, selected value [{0}] is a [{1}]", value.toString(), value.getClass());
            LOGGER.error(error);
            throw  new LazyCoreException(ErrorCodes.INVALID_VALUE_TYPE, error);
        }
    }

    public static Integer getInteger(String json, String path) throws LazyCoreException {
        Object value = getValue(json, path);
        if (value == null) {
            return null;
        } else if (value instanceof Integer) {
            return (Integer) value;
        } else {
            String error = format("JSON value is not a Integer, selected value [{0}] is a [{1}]", value, value.getClass());
            LOGGER.error(error);
            throw  new LazyCoreException(ErrorCodes.INVALID_VALUE_TYPE, error);
        }
    }

    public static Double getDouble(String json, String path) throws LazyCoreException {
        Object value = getValue(json, path);
        if (value == null) {
            return null;
        } else if (value instanceof Double) {
            return (Double) value;
        } else {
            String error = format("JSON value is not a Double, selected value [{0}] is a [{1}]", value.toString(), value.getClass());
            LOGGER.error(error);
            throw  new LazyCoreException(ErrorCodes.INVALID_VALUE_TYPE, error);
        }
    }

    public static Boolean getBoolean(String json, String path) throws LazyCoreException {
        Object value = getValue(json, path);
        if (value == null) {
            return null;
        } else if (value instanceof Boolean) {
            return (Boolean) value;
        } else {
            String error = format("JSON value is not a Boolean, selected value [{0}] is a [{1}]", value.toString(), value.getClass());
            LOGGER.error(error);
            throw  new LazyCoreException(ErrorCodes.INVALID_VALUE_TYPE, error);
        }
    }

    public static JSONArray getJSONArray(String json, String path) throws LazyCoreException {
        Object value = getValue(json, path);
        if (value == null) {
            return null;
        } else if (value instanceof JSONArray) {
            return (JSONArray) value;
        } else {
            String error = format("JSON value is not a JSONArray, selected value [{0}] is a [{1}]", value.toString(), value.getClass());
            LOGGER.error(error);
            throw  new LazyCoreException(ErrorCodes.INVALID_VALUE_TYPE, error);
        }
    }

    public static LinkedHashMap getLinkedHashMap(String json, String path) throws LazyCoreException {
        Object value = getValue(json, path);
        if (value == null) {
            return null;
        } else if (value instanceof LinkedHashMap) {
            return (LinkedHashMap) value;
        } else {
            String error = format("JSON value is not a JSONArray, selected value [{0}] is a [{1}]", value.toString(), value.getClass());
            LOGGER.error(error);
            throw  new LazyCoreException(ErrorCodes.INVALID_VALUE_TYPE, error);
        }
    }

    public static Object getValue(String json, String path) {
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(json);
        Object value = JsonPath.read(document, path);
        LOGGER.debug("Found JSON path [{}] value [{}] with type [{}]", json, value, value == null ? null :value.getClass());
        return value;
    }
}

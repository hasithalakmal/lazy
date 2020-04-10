package com.smile24es.lazy.utils;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smile24es.lazy.common.ErrorCodes;
import com.smile24es.lazy.exception.LazyCoreException;
import org.apache.commons.lang3.StringUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import static java.text.MessageFormat.format;

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

    public static String readJsonFromFile(String filePath) throws LazyCoreException {
        String jsonString = null;
        if (StringUtils.isBlank(filePath)) {
            String error = "File path should not be blank";
            LOGGER.error(error);
            throw new LazyCoreException(ErrorCodes.INVALID_FILE_PATH, error);
        }
        JSONParser parser = new JSONParser();
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:" + filePath);
        } catch (FileNotFoundException e) {
            String error = format("File not found in class path [{}]", filePath);
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.INVALID_FILE_PATH, error);
        }
        try (Reader reader = new FileReader(file)) {
            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            jsonString = jsonObject.toJSONString();
        } catch (IOException e) {
            String error = format("JSON File cannot read [{}]", filePath);
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.FILE_READING_ERROR, error);
        } catch (ParseException e) {
            String error = format("Json parsing exception [{}]", filePath);
            LOGGER.error(error, e);
            throw new LazyCoreException(ErrorCodes.INVALID_JSON, error);
        }

        return jsonString;
    }
}

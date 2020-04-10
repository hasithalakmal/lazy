package com.smile24es.lazy.utils;

import com.smile24es.BaseTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class JsonPathReaderUtilTest  extends BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonPathReaderUtilTest.class);

    @Test
    public void getStringFromJsonTest() {
        try {
            String jsonString = JsonUtil.readJsonFromFile("test/test.json");
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.accountName"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.accountId"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.isActive"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.monthlyCharge"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.phone"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.phone.number"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.settings"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.settings[0].id"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.settings[*].id"));

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..");
            //LOGIC
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.settings.length()"));
            System.out.println(JsonPathReaderUtil.getAnyValueAsString(jsonString, "$.settings[?(@.key == 'payment.period')].value"));
        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

    @Test
    public void getValueWithSelectedType() {
        try {
            String jsonString = JsonUtil.readJsonFromFile("test/test.json");
            System.out.println(JsonPathReaderUtil.getString(jsonString, "$.accountName"));
            System.out.println(JsonPathReaderUtil.getInteger(jsonString, "$.accountId"));
            System.out.println(JsonPathReaderUtil.getBoolean(jsonString, "$.isActive"));
            System.out.println(JsonPathReaderUtil.getDouble(jsonString, "$.monthlyCharge"));


            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>..");
            //LOGIC
            System.out.println(JsonPathReaderUtil.getLinkedHashMap(jsonString, "$.phone"));
            System.out.println(JsonPathReaderUtil.getString(jsonString, "$.phone.number"));
            System.out.println(JsonPathReaderUtil.getLinkedHashMap(jsonString, "$.phone").get("number"));

            System.out.println(JsonPathReaderUtil.getJSONArray(jsonString, "$.settings"));
            System.out.println(JsonPathReaderUtil.getInteger(jsonString, "$.settings[0].id"));
            System.out.println(JsonPathReaderUtil.getJSONArray(jsonString, "$.settings[*].id"));
            System.out.println(JsonPathReaderUtil.getValue(jsonString, "$.settings[*].id"));
            System.out.println(JsonPathReaderUtil.getInteger(jsonString, "$.settings.length()"));
            System.out.println(JsonPathReaderUtil.getJSONArray(jsonString, "$.settings[?(@.key == 'payment.period')].value"));
            System.out.println(JsonPathReaderUtil.getJSONArray(jsonString, "$.settings[?(@.key == 'payment.period')].value").get(0));

        } catch (Exception ex) {
            Assert.fail(SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED, ex);
        }
    }

    @Test
    public void getStringValueFromJsonErrorScenarios(){
        try {
            String jsonString = JsonUtil.readJsonFromFile("test/test.json");
            JsonPathReaderUtil.getString(jsonString, "$.isActive");
            Assert.fail(ERROR_SCENARIOS_SHOULD_BE_FAILED);
        } catch (Exception ex) {
            LOGGER.error("Exception found as expected :", ex);
        }
    }
}

package com.smile.lazy;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.manager.LazyManager;
import com.smile.lazy.suite.sample.SampleLazySuite1;
import com.smile.lazy.utils.JsonUtil;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import java.math.BigDecimal;

import static java.text.MessageFormat.format;


@SpringBootTest(classes = LazyApplication.class)
public class LazyManagerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManagerTest.class);

    @Autowired
    private LazyManager lazyManager;

    @Test
    public void testSampleLazySuite() {
        try {
            LazySuite sampleLazySuite = SampleLazySuite1.populateSampleTestSuite();
            LazyExecutionData results = lazyManager.executeLazySuite(sampleLazySuite);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getTestSuiteExecutionData());
            String resultString = JsonUtil.getJsonStringFromObjectProtectedAndPublic(results);
            LOGGER.info("Execution results \n [{}]", resultString);

        } catch (Exception ex) {
            Assert.fail("Success scenarios should not be failed", ex);
        }
    }

    @Test
    public void simple() {
        String text = format("username={0}&password={1}&grant_type={2}", "hanzhenl+tbs@gmail.com","1234567","password");
        System.out.println(text);

        BigDecimal x = new BigDecimal("5.70");
        BigDecimal y = new BigDecimal("5");
        System.out.println(x.toPlainString());
        System.out.println(y.toPlainString());
    }

}

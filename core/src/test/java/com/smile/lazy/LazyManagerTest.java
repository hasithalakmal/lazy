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


@SpringBootTest(classes = LazyApplication.class)
public class LazyManagerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyManagerTest.class);

    @Autowired
    private LazyManager lazyManager;

    @Test
    public void testEmployee() {
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
        System.out.println("{\"status\":\"ACTIVE\",\"createdBy\":\"12345\",\"parentId\":\"1\",\"enterpriseId\":\"1\","
              + "\"accountName\":\"Sathara-1577641690\",\"ownerName\":\"Hasitha-1577641690\",\"versionId\":\"1.0.0\","
              + "\"settings\":[{\"key\":\"setting1\",\"value\":\"1577641690\"},{\"key\":\"setting2\",\"value\":\"1577641690\"}]}");
    }

}

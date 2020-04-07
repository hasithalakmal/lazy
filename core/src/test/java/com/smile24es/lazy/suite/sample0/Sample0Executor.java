package com.smile24es.lazy.suite.sample0;

import com.smile24es.lazy.LazyApplication;
import com.smile24es.lazy.LazyManagerTest;
import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.manager.LazyManager;
import com.smile24es.lazy.suite.sample1.SampleLazySuite1;
import com.smile24es.lazy.utils.JsonUtil;
import com.smile24es.lazy.wrapper.Executor;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

@SpringBootTest(classes = LazyApplication.class)
public class Sample0Executor {

    @Autowired
    private Executor executor;

    @Test
    public void executeSampleLazySuite() {
        try {
            LazyExecutionData results = executor.executeLazySuite(SmileLazySuite0.populateSampleLazySuite());
            Assert.assertNotNull(results);
        } catch (Exception ex) {
            Assert.fail("Success scenarios should not be failed", ex);
        }
    }
}

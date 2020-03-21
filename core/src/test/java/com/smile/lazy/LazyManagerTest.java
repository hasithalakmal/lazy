package com.smile.lazy;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.manager.LazyManager;
import com.smile.lazy.sample.SampleLazySuite1;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


@SpringBootTest(classes = LazyApplication.class)
public class LazyManagerTest {

    @Autowired
    private LazyManager lazyManager;

    @Test
    public void testEmployee(){
        try {
            LazySuite sampleLazySuite = SampleLazySuite1.populateSampleTestSuite();
            AssertionResultList results = lazyManager.executeLazySuite(sampleLazySuite);
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getResults());
            results.getResults().forEach(result -> {
                Assert.assertTrue(result.getPass(), result.getActualValue());
                System.out.println(result);
            });
        } catch (Exception ex) {
            Assert.fail("Success scenarios should not be failed", ex);
        }
    }

    @Test
    public void simple(){
        System.out.println("{\"status\":\"ACTIVE\",\"createdBy\":\"12345\",\"parentId\":\"1\",\"enterpriseId\":\"1\","
              + "\"accountName\":\"Sathara-1577641690\",\"ownerName\":\"Hasitha-1577641690\",\"versionId\":\"1.0.0\","
              + "\"settings\":[{\"key\":\"setting1\",\"value\":\"1577641690\"},{\"key\":\"setting2\",\"value\":\"1577641690\"}]}");
    }

}

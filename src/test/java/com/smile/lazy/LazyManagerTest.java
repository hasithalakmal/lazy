package com.smile.lazy;

import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.manager.LazyManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;

import static com.smile.lazy.sample.SampleLazySuite1.populateSampleTestSuite;


@SpringBootTest(classes = LazyApplication.class)
public class LazyManagerTest {

    @Autowired
    private LazyManager lazyManager;

    @Test
    public void testEmployee() throws Exception {
        try {
            AssertionResultList results = lazyManager.test(populateSampleTestSuite());
            Assert.assertNotNull(results);
            Assert.assertNotNull(results.getResults());
            results.getResults().forEach(result -> {
//        Assert.assertTrue(result.getPass(), result.getActualValue());
                System.out.println(result);
            });
        } catch (Exception ex) {
            Assert.fail("Success scenarios should not be failed");
        }

    }

}

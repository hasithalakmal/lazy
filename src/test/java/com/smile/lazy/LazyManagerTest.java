package com.smile.lazy;

import static com.smile.lazy.sample.SampleTestSuite1.populateSampleTestSuite;

import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.manager.LazyManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


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
        Assert.assertTrue(result.getPass(), result.getActualValue());
      });
    } catch (Exception ex) {
      Assert.fail("Success scenarios should not be failed");
    }

  }

}

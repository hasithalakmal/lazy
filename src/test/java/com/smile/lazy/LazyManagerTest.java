package com.smile.lazy;

import com.smile.lazy.manager.LazyManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static com.smile.lazy.sample.SampleTestSuite1.populateSampleTestSuite;


@SpringBootTest(classes = LazyApplication.class)
public class LazyManagerTest {

    @Autowired
    private LazyManager lazyManager;

    @Test
    public void testEmployee() throws Exception {
        lazyManager.test(populateSampleTestSuite());

    }

}

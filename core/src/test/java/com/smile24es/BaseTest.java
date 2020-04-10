package com.smile24es;

import com.smile24es.lazy.LazyApplication;
import com.smile24es.lazy.LazyManagerTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = LazyApplication.class)
public class BaseTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    public static final String EXECUTION_RESULTS_LOG = "Execution results \n [{}]";
    public static final String SUCCESS_SCENARIOS_SHOULD_NOT_BE_FAILED = "Success scenarios should not be failed";
    public static final String ERROR_SCENARIOS_SHOULD_BE_FAILED = "Error scenarios should be failed";
}

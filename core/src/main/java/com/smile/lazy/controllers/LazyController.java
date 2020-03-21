package com.smile.lazy.controllers;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;
import com.smile.lazy.manager.LazyManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LazyController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(LazyController.class);

    @Autowired
    private LazyManager lazyManager;

    @GetMapping(value = "lazy-api/services/ping")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String ping() {
        String currentTime = Long.toString(System.currentTimeMillis());
        LOGGER.info("Ping request {}", currentTime);
        return currentTime;
    }

    @PostMapping(value = "lazy-api/services/execute")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public LazyExecutionData test(@RequestBody LazySuite lazySuite) throws LazyException, LazyCoreException {
        LOGGER.info("Start lazy test suite execution [{}]", lazySuite);
        return lazyManager.executeLazySuite(lazySuite);
    }
}

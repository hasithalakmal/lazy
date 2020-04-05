package com.smile24es.lazy.wrapper.impl;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;
import com.smile24es.lazy.manager.LazyManager;
import com.smile24es.lazy.wrapper.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class ExecutorImpl implements Executor {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorImpl.class);

    @Autowired
    private LazyManager lazyManager;

    @Override
    public LazyExecutionData executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException {
        LOGGER.info("Start lazy suite execution");
        return lazyManager.executeLazySuite(lazySuite);
    }
}

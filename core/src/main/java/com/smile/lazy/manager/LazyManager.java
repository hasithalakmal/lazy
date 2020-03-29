package com.smile.lazy.manager;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

/**
 * The manger layer interface to execute lazy suite
 */
public interface LazyManager {

    /**
     * The lazy manger method to execute defined lazy suite
     *
     * @param lazySuite
     * @return
     * @throws LazyException
     */
    LazyExecutionData executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException;

    /**
     * The lazy manger method to execute defined lazy suite
     *
     * @param lazySuite
     * @return
     * @throws LazyException
     */
    LazyExecutionData executeLazySuite(LazySuite lazySuite, String testSuiteName) throws LazyException, LazyCoreException;
}

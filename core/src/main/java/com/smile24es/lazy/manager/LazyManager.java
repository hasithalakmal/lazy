package com.smile24es.lazy.manager;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.beans.executor.LazyExecutionGroup;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;

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
    LazyExecutionData executeLazySuite(LazySuite lazySuite, LazyExecutionGroup lazyExecutionGroup) throws LazyException, LazyCoreException;
}

package com.smile24es.lazy.wrapper;

import com.smile24es.lazy.beans.LazySuite;
import com.smile24es.lazy.beans.executor.LazyExecutionData;
import com.smile24es.lazy.exception.LazyCoreException;
import com.smile24es.lazy.exception.LazyException;

public interface Executor {
    LazyExecutionData executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException;
}

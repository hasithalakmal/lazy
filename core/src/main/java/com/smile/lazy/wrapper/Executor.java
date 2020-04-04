package com.smile.lazy.wrapper;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.executor.LazyExecutionData;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface Executor {
    LazyExecutionData executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException;
}

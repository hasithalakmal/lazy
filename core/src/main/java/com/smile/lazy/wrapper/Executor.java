package com.smile.lazy.wrapper;

import com.smile.lazy.beans.LazySuite;
import com.smile.lazy.beans.result.AssertionResultList;
import com.smile.lazy.exception.LazyCoreException;
import com.smile.lazy.exception.LazyException;

public interface Executor {
    AssertionResultList executeLazySuite(LazySuite lazySuite) throws LazyException, LazyCoreException;
}

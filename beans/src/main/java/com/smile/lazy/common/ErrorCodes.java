package com.smile.lazy.common;

public class ErrorCodes {

    public static final String UNKNOWN_SERVER_ERROR = "Lazy-10000";
    public static final String NOT_IMPLEMENTED = "Lazy-10010";

    //HTTP method call related exceptions
    public static final String URI_SYNTAX_ERROR = "Lazy-20000";
    public static final String IO_EXCEPTION = "Lazy-20010";

    //Test suite errors
    public static final String INVALID_LAZY_SUITE = "Lazy-30000";

    public static final String INVALID_LAZY_TEST_SCENARIO = "Lazy-40000";

    public static final String INVALID_LAZY_TEST_CASE = "Lazy-50000";

    public static final String INVALID_LAZY_TEST_API_CALL = "Lazy-60000";

    //Lazy Core related error codes
    public static final String INVALID_TABLE = "Lazy-70000";

    private ErrorCodes() {
        //This is a private constructor
    }
}

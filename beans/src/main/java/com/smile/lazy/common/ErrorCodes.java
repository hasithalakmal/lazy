package com.smile.lazy.common;

public class ErrorCodes {

    public static final String UNKNOWN_SERVER_ERROR = "Lazy-10000";
    public static final String NOT_IMPLEMENTED = "Lazy-10010";
    public static final String JSON_HANDLING_EXCEPTION = "Lazy-10020";

    //HTTP method call related exceptions
    public static final String URI_SYNTAX_ERROR = "Lazy-20000";
    public static final String IO_EXCEPTION = "Lazy-20010";

    //Test suite errors
    public static final String INVALID_LAZY_SUITE = "Lazy-30000";
    public static final String INVALID_TEST_SUITE = "Lazy-35000";

    public static final String INVALID_LAZY_TEST_SCENARIO = "Lazy-40000";

    public static final String INVALID_LAZY_TEST_CASE = "Lazy-50000";

    public static final String INVALID_LAZY_TEST_API_CALL = "Lazy-60000";
    public static final String INVALID_LAZY_ASSERTION_RULE = "Lazy-61000";
    public static final String INVALID_LAZY_ACTION = "Lazy-62000";
    public static final String INVALID_LAZY_ACTION_TYPE = "Lazy-62010";
    public static final String INVALID_LAZY_ACTION_DATA_SOURCE = "Lazy-62020";

    //Lazy Core related error codes
    public static final String INVALID_TABLE = "Lazy-70000";
    public static final String INVALID_FILE_PATH = "Lazy-70010";
    public static final String FILE_READING_ERROR = "Lazy-70011";
    public static final String INVALID_JSON = "Lazy-70020";
    public static final String INVALID_TEMPLATE = "Lazy-70030";
    public static final String TEMPLATE_PROCESSING_ERROR = "Lazy-70040";
    public static final String INVALID_TEMPLATE_DATA_OBJECT = "Lazy-70050";

    private ErrorCodes() {
        //This is a private constructor
    }
}

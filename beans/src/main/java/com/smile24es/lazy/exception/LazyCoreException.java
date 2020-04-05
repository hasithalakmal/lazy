package com.smile24es.lazy.exception;

public class LazyCoreException extends Exception {

    private static final long serialVersionUID = 3078289635627866758L;

    private final String errorCode;

    public LazyCoreException(String errorCode, String message, Throwable t) {
        super(message, t);
        this.errorCode = errorCode;
    }

    public LazyCoreException(String errorCode, String message) {
        this(errorCode, message, null);
    }

    public String getErrorCode() {
        return errorCode;
    }

}

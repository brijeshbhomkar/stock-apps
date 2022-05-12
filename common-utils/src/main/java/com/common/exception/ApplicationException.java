package com.common.exception;

/**
 *
 */
public class ApplicationException extends Exception {

    private String error;

    public ApplicationException(final String message) {
        super(message);
        this.error = message;
    }
}

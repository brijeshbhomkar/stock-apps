package com.stocks.oi.exception;

public class DataNotFoundException extends RuntimeException {

    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String s) {
        super(s);
    }

    public DataNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }

    public DataNotFoundException(Throwable throwable) {
        super(throwable);
    }

    protected DataNotFoundException(String s, Throwable throwable, boolean b, boolean b1) {
        super(s, throwable, b, b1);
    }
}

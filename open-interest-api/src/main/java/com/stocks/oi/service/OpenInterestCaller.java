package com.stocks.oi.service;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.io.IOException;

public interface OpenInterestCaller {

    @Retryable(
            value = RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    public String caller(final String url, final String cookieid) throws IOException, InterruptedException;

    @Recover
    public String callerFallback(final RuntimeException exception, final String url, final String cookieid);
}

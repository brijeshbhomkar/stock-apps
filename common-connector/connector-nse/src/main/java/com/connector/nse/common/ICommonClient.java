package com.connector.nse.common;

import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.io.IOException;
import java.io.InputStream;

public interface ICommonClient {

    @Retryable(
            value = RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    public String caller1(final String endpoint, final String symbol) throws IOException, InterruptedException;

    @Retryable(
            value = RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    public InputStream caller2(final String endpoint,final String dateFormat, final String fileType) throws IOException, InterruptedException;

    @Recover
    public String callerFallback(final RuntimeException exception, final String endpoint, final String symbol);
}

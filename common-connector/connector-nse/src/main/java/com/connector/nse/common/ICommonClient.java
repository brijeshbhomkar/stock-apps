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
    public default String caller1(final String endpoint, final String symbol) throws IOException, InterruptedException {
        return null;
    }

    @Retryable(
            value = RuntimeException.class,
            maxAttempts = 3,
            backoff = @Backoff(delay = 5000))
    public default InputStream caller2(final String endpoint, final String dateFormat, final String fileType) throws IOException, InterruptedException {
        return null;
    }

    @Recover
    public String callerFallback(final RuntimeException exception, final String endpoint, final String symbol);
}

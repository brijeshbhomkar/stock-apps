package com.connector.common.util;

import java.net.http.HttpClient;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ApplicationClient {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(3);

    private ApplicationClient() {
    }

    public static HttpClient getClient() {
        return HttpClient.newBuilder().executor(executorService).build();
    }
}

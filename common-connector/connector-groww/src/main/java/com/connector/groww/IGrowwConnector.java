package com.connector.groww;

import com.common.exception.ApplicationException;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@FunctionalInterface
public interface IGrowwConnector {

    String BASE_URL = "https://groww.in/v1/api/stocks_data/explore/v2/indices/";

    public Optional<String> connect(final String marketType, final String filterType, final int size) throws ApplicationException;

    default HttpClient setup(final int poolsize) {
        final ExecutorService executorService = Executors.newFixedThreadPool(poolsize);
        return HttpClient.newBuilder().executor(executorService).build();
    }

    static String endpoint(final String marketType, final String filterType, final int size) {
        return BASE_URL + marketType + "/" + "market_trends" + "?" + "discovery_filter_types=" + filterType + "&size=" + size;
    }

}

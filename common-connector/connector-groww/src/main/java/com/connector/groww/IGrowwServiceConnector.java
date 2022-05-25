package com.connector.groww;

import com.common.exception.ApplicationException;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@FunctionalInterface
public interface IGrowwServiceConnector {

    String BASE_URL = "https://groww.in/v1/api/stocks_data/explore/v2/indices/";

    Optional<String> connect(final String marketType, final String filterType, final int size) throws ApplicationException;

    static String endpoint(final String marketType, final String filterType, final int size) {
        return BASE_URL + marketType + "/" + "market_trends" + "?" + "discovery_filter_types=" + filterType + "&size=" + size;
    }

}

package com.connector.groww;

import com.common.exception.ApplicationException;

import java.net.http.HttpClient;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@FunctionalInterface
public interface IGrowwDataAccessConnector {

    String BASE_URL = "https://groww.in/v1/api/charting_service/v2/chart/exchange/NSE/segment/CASH/";

    public Optional<String> connect(final String symbol, final String type, final String interval, final int intervalVal) throws ApplicationException;

    default HttpClient setup(final int poolsize) {
        final ExecutorService executorService = Executors.newFixedThreadPool(poolsize);
        return HttpClient.newBuilder().executor(executorService).build();
    }

    //e.g "ICICIBANK/monthly?intervalInMinutes=5"
    static String endpoint(final String symbol, final String type, final String interval, final int intervalVal) {
        return BASE_URL + symbol + "/" + type + "?" + interval + "=" + intervalVal;
    }

}

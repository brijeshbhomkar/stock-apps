package com.stocks.oi.service;

import com.stocks.oi.util.RequestHeader;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Log4j2
@Service
public class OpenInterestCallerImpl implements OpenInterestCaller {

    private HttpClient client = HttpClient.newHttpClient();

    @Override
    public String caller(final String url, final String cookieid) throws IOException, InterruptedException {
        log.info("Fetching data for url " + url);
        String data = null;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url))
                .setHeader("Accept", "application/json")
                //.setHeader("cookie", cookieid)
                .setHeader("user-agent", RequestHeader.USER_AGENT)
                .build();
        final HttpResponse<String> response = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 401) {
            throw new RuntimeException("Failed to get the data. Retrying again!");
        }
        return response.body();
    }

    @Override
    public String callerFallback(RuntimeException exception, String url, String cookieid) {
        log.error("Failed after 3 attempts, returning no data! " + url);
        return null;
    }
}

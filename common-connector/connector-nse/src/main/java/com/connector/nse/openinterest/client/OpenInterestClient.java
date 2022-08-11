package com.connector.nse.openinterest.client;

import com.connector.common.util.ApplicationClient;
import com.connector.common.util.Endpoint;
import com.connector.common.util.RequestHeader;
import com.connector.nse.common.ICommonClient;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Log4j2
@Service
public class OpenInterestClient implements ICommonClient {

    @Override
    public String caller1(final String endpoint, final String symbol) throws IOException, InterruptedException {
        final String url = Endpoint.valueOfLabel(endpoint).getEndpoint() + symbol;
        log.info("Fetching data for url " + url);
        String data = null;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url))
                .setHeader("Accept", "application/json")
                //.setHeader("cookie", cookieid)
                .setHeader("user-agent", RequestHeader.USER_AGENT)
                .build();
        final HttpResponse<String> response = ApplicationClient.getClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
        if (response.statusCode() == 401) {
            throw new RuntimeException("Failed to get the data. Retrying again!");
        }
        return response.body();
    }

    @Override
    public InputStream caller2(String endpoint, String dateFormat, String fileType) throws IOException, InterruptedException {
        return null;
    }

    @Override
    public String callerFallback(RuntimeException exception, String endpoint, String symbol) {
        log.error("Failed after 3 attempts, returning no data! " + Endpoint.valueOfLabel(endpoint).name());
        return null;
    }
}
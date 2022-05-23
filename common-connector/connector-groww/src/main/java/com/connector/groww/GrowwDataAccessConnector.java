package com.connector.groww;

import com.common.exception.ApplicationException;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class GrowwDataAccessConnector implements IGrowwDataAccessConnector {

    @Override
    public Optional<String> connect(final String symbol, final String type, final String interval, final int intervalVal) throws ApplicationException {
        String json = null;
        HttpClient client = setup(3);
        String url = IGrowwDataAccessConnector.endpoint(symbol, type, interval,intervalVal);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        final CompletableFuture<String> response = client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).exceptionally(exception -> "Error : " + exception.getMessage());

        try {
            final String data = response.get();
            if (data != null && !data.isEmpty()) {
                json = data;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.of(json);
    }

}

package com.connector.groww;

import com.common.exception.ApplicationException;
import com.connector.common.util.ApplicationClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class GrowwServiceConnector implements IGrowwServiceConnector {

    @Override
    public Optional<String> connect(final String marketType, final String filterType, final int size) throws ApplicationException {

        String json = null;
        String url = IGrowwServiceConnector.endpoint(marketType, filterType, size);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        final CompletableFuture<String> response = ApplicationClient.getClient().sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
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

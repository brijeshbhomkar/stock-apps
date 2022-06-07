package com.connector.sgx.nifty;

import com.connector.common.util.ApplicationClient;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class SGXNiftyOptionConnectorClient implements ISGXNiftyOptionConnector {

    @Override
    public Optional<String> connect(String marketType) {
        String json = null;
        String url = ISGXNiftyOptionConnector.endpoint(marketType);
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

package com.connector.groww;

import com.common.exception.ApplicationException;
import com.connector.groww.json.common.JsonData;
import com.connector.groww.json.losers.TopLosersResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class GrowwConnector implements IGrowwConnector {

    @Override
    public Optional<String> connect(final String marketType, final String filterType, final int size) throws ApplicationException {

        //set up poolsize and httpclient
       // List<String> json = new ArrayList<>();
        String json = null;
        HttpClient client = setup(3);
        String url = IGrowwConnector.endpoint(marketType, filterType, size);
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
        final CompletableFuture<String> response = client.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body).exceptionally(exception -> "Error : " + exception.getMessage());

        try {
            final String data = response.get();
            if (data != null && !data.isEmpty()) {
//                try {
//                    TopLosersResponseWrapper wrapper = new ObjectMapper().readValue(data, TopLosersResponseWrapper.class);
//                    if (wrapper != null && wrapper.getResponseMap() != null) {
//                        JsonData jsonData = new JsonData();
//                        jsonData.setTimestamp(wrapper.getTimeStamp());
//                        jsonData.setItems(wrapper.getResponseMap().getTopLosers().getItems());
//                        json.add(jsonData);
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                json = data;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return Optional.of(json);

    }
//
//    public static void main(String[] args) throws ApplicationException {
//        GrowwConnector growwConnector = new GrowwConnector();
//        growwConnector.connect("GIDXNIFTY100", "TOP_LOSERS", 20);
//    }

}

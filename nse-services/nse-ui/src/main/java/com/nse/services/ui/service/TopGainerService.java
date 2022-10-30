package com.nse.services.ui.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.services.ui.model.TopGainer;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

@Service
public class TopGainerService {

    private String BASE_URL = "http://localhost:2005/api/nse/topgainer/";

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();

    public List<TopGainer> listTopGainers() {
        return null;
    }

    public List<TopGainer> getNiftyTopGainersAll() {
        HttpResponse<String> response = null;

        final String url = BASE_URL + "nifty" + "/all";
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (response != null) {
            try {
                TopGainer[] gainers = new ObjectMapper().readValue(response.body(), TopGainer[].class);
                return Arrays.asList(gainers);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public List<TopGainer> getNifty500TopGainers() {
        return null;
    }

    public List<TopGainer> getNiftySMCP100TopGainers() {
        return null;
    }
}

package com.stocks.oi.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.oi.response.oi.spurts.DailyHighestOpenInterestResponse;
import com.stocks.oi.response.oi.spurts.DailyOpenInterestResponse;
import com.stocks.oi.util.Endpoints;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Log4j2
@Service
public class DailyOpenInterestSpurtsService {

    private HttpClient client = HttpClient.newHttpClient();

    @SneakyThrows
    public DailyHighestOpenInterestResponse getOpenInterestSpurtsStocks() {
        log.info("Find highest open interest " + Endpoints.TOP_POSITIVE_OI_CHANGE_DATA);
        DailyHighestOpenInterestResponse response = null;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(Endpoints.TOP_POSITIVE_OI_CHANGE_DATA))
                .header("Accept", "application/json").build();
        final HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        final String data = httpResponse.body();
        if (data != null && !data.isEmpty()) {
            try {
                response = new ObjectMapper().readValue(data, DailyHighestOpenInterestResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @SneakyThrows
    public DailyOpenInterestResponse slideInPriceSlideInOpenInterestStocks() {
        log.info("Find stocks with slide in price slide in open interest (Bearish stocks) " + Endpoints.SLIDE_IN_PRICE_SLIDE_IN_OI);
        DailyOpenInterestResponse response = null;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(Endpoints.SLIDE_IN_PRICE_SLIDE_IN_OI))
                .header("Accept", "application/json").build();
        final HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        final String data = httpResponse.body();
        if (data != null && !data.isEmpty()) {
            try {
                response = new ObjectMapper().readValue(data, DailyOpenInterestResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @SneakyThrows
    public DailyOpenInterestResponse riseInPriceSlideInOpenInterestStocks() {
        log.info("Find stocks with rise in price slide in open interest (Bearish stocks) " + Endpoints.RISE_IN_PRICE_SLIDE_IN_OI);
        DailyOpenInterestResponse response = null;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(Endpoints.RISE_IN_PRICE_SLIDE_IN_OI))
                .header("Accept", "application/json").build();
        final HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        final String data = httpResponse.body();
        if (data != null && !data.isEmpty()) {
            try {
                response = new ObjectMapper().readValue(data, DailyOpenInterestResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }

    @SneakyThrows
    public DailyOpenInterestResponse riseInPriceRiseInOpenInterestStocks() {
        log.info("Find stocks with rise in price slide in open interest (Bearish stocks) " + Endpoints.RISE_IN_PRICE_RISE_IN_OI);
        DailyOpenInterestResponse response = null;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(Endpoints.RISE_IN_PRICE_RISE_IN_OI))
                .header("Accept", "application/json").build();
        final HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        final String data = httpResponse.body();
        if (data != null && !data.isEmpty()) {
            try {
                response = new ObjectMapper().readValue(data, DailyOpenInterestResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }


    @SneakyThrows
    public DailyOpenInterestResponse slideInPriceRiseInOpenInterestStocks() {
        log.info("Find stocks with rise in price slide in open interest (Bearish stocks) " + Endpoints.SLIDE_IN_PRICE_RISE_IN_OI);
        DailyOpenInterestResponse response = null;
        HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(Endpoints.SLIDE_IN_PRICE_RISE_IN_OI))
                .header("Accept", "application/json").build();
        final HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        final String data = httpResponse.body();
        if (data != null && !data.isEmpty()) {
            try {
                response = new ObjectMapper().readValue(data, DailyOpenInterestResponse.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return response;
    }


}

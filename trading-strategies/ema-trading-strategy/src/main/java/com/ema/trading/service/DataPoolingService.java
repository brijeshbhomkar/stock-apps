package com.ema.trading.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.stereotype.Service;

import com.ema.trading.jsons.CandleResponse;
import com.ema.trading.jsons.Candles;
import com.ema.trading.jsons.Request;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DataPoolingService {

	final ExecutorService executorService = Executors.newFixedThreadPool(5);

	private HttpClient client = HttpClient.newBuilder().executor(executorService).build();

	private static String BASE_URL = "https://kitecharts-aws.zerodha.com/api/chart/";

	public Candles dataExchange(final Request request) {
		Candles candles = null;
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(generateKiteEndpont(request.getSymbol(),
				request.getTimeframe(), request.getUserId(), request.getFromDate(), request.getToDate()))).build();
		final CompletableFuture<String> response = client.sendAsync(httpRequest, BodyHandlers.ofString())
				.thenApply(HttpResponse::body).exceptionally(exception -> "Error : " + exception.getMessage());
		try {
			final String data = response.get();
			if (data != null && !data.isEmpty()) {
				try {
					final CandleResponse candleResponse = new ObjectMapper().readValue(data, CandleResponse.class);
					if (candleResponse != null) {
						candles = candleResponse.getData();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return candles;
	}

	public String generateKiteEndpont(final String symboldId, final String period, final String userId,
			final String startDate, final String endDate) {
		return BASE_URL + symboldId + "/" + period + "?" + "user_id=" + userId + "&from=" + startDate + "&to="
				+ endDate;
	}
}

package com.vwap.trading.services;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vwap.trading.jsons.GainerLoserResponse;

@Service
public class LoserGainerService {

	final ExecutorService executorService = Executors.newFixedThreadPool(5);

	private HttpClient client = HttpClient.newBuilder().executor(executorService).build();

	public GainerLoserResponse getGainers(final String url) {
		GainerLoserResponse result = null;
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
		final CompletableFuture<String> response = client.sendAsync(httpRequest, BodyHandlers.ofString())
				.thenApply(HttpResponse::body).exceptionally(exception -> "Error : " + exception.getMessage());
		try {
			final String data = response.get();
			if (data != null && !data.isEmpty()) {
				try {
					result = new ObjectMapper().readValue(data, GainerLoserResponse.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public GainerLoserResponse getLosers(final String url) {
		GainerLoserResponse result = null;
		HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).build();
		final CompletableFuture<String> response = client.sendAsync(httpRequest, BodyHandlers.ofString())
				.thenApply(HttpResponse::body).exceptionally(exception -> "Error : " + exception.getMessage());
		try {
			final String data = response.get();
			if (data != null && !data.isEmpty()) {
				try {
					result = new ObjectMapper().readValue(data, GainerLoserResponse.class);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		return result;
	}
}

package com.data.service.dataservice.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestfulSupport {

	protected RestTemplate restTemplate;
	protected HttpHeaders httpHeaders;

	protected static String BASE_URL = "https://kitecharts-aws.zerodha.com/api/chart/";
	
	protected static final String NSE_NIFTY_50_URL = "https://www.nseindia.com/live_market/dynaContent/live_analysis/pre_open/nifty.json";
	protected static final String NSE_ALL_URL = "https://www.nseindia.com/live_market/dynaContent/live_analysis/pre_open/all.json";

	public RestfulSupport() {
		this.restTemplate = new RestTemplate();
		this.httpHeaders = new HttpHeaders();

		restTemplate.setMessageConverters(getMessageConverters());
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		httpHeaders.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
	}

	private List<HttpMessageConverter<?>> getMessageConverters() {
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(new MappingJackson2HttpMessageConverter());
		return converters;
	}

	public String generateKiteEndpont(final String symboldId, final String period, final String userId,
			final String startDate, final String endDate) {
		return BASE_URL + symboldId + "/" + period + "?" + "user_id=" + userId + "&from=" + startDate + "&to="
				+ endDate;
	}
}

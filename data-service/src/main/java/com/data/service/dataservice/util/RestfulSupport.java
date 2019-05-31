package com.data.service.dataservice.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestfulSupport {

	protected RestTemplate restTemplate;
	protected HttpHeaders httpHeaders;

	protected static String BASE_URL = "https://kitecharts-aws.zerodha.com/api/chart/";

	public RestfulSupport() {
		this.restTemplate = new RestTemplate();
		this.httpHeaders = new HttpHeaders();

		// cors fix
//		httpHeaders.setAccessControlAllowOrigin("*");
//		httpHeaders.setAccessControlAllowHeaders(Arrays.asList("Content-Type"));
//		httpHeaders.setAccessControlAllowMethods(
//				Arrays.asList(HttpMethod.GET, HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE, HttpMethod.OPTIONS));

		// converters
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

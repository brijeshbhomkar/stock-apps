package com.charting.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * The base restful support class which needs to be extended by services which
 * are going to call the backend services using restful.
 */
public class RestfulSupport {

	protected static RestTemplate restTemplate = new RestTemplate();
	protected static HttpHeaders httpHeaders = new HttpHeaders();

	public RestfulSupport() {
		MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON));
		restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
	}

	public RestfulSupport(final RestTemplate template, final HttpHeaders headers,
			final List<String> supportedType) {
		restTemplate = template;
		httpHeaders = headers;
	}
}

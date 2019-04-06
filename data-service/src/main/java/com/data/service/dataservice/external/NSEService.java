package com.data.service.dataservice.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.data.service.dataservice.response.StockDataResponse;
import com.data.service.dataservice.util.RestfulSupport;

@Service
public class NSEService extends RestfulSupport {

	private static final Logger logger = LoggerFactory.getLogger(NSEService.class);

	public StockDataResponse fetchDataFromNse(final String url) {
		logger.debug("Find nifty stocks ");
		StockDataResponse result = null;
		try {
			final HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
			ResponseEntity<StockDataResponse> response = restTemplate.exchange(url, HttpMethod.GET,
					entity, StockDataResponse.class);
			result = response.getBody();
		} catch (Exception e) {
			logger.error("Failed to get the nifty stocks ");
			throw new RuntimeException("Failed to get the nifty stocks ");
		}
		return result;
	}
}
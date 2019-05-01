package com.data.service.dataservice.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.data.service.dataservice.response.PreOpenResponse;
import com.data.service.dataservice.response.StockWatchResponse;
import com.data.service.dataservice.util.RestfulSupport;

@Service
public class NSEService extends RestfulSupport {

	private static final Logger logger = LoggerFactory.getLogger(NSEService.class);

	public PreOpenResponse fetchNsePreOpenStock(final String url) {
		logger.debug("Find nifty pre open stocks ");
		PreOpenResponse result = null;
		try {
			final HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
			ResponseEntity<PreOpenResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					PreOpenResponse.class);
			result = response.getBody();
		} catch (Exception e) {
			logger.error("Failed to get the nifty stocks ");
			throw new RuntimeException("Failed to get the nifty stocks ");
		}
		return result;
	}

	public StockWatchResponse fetchNseStockWatch(final String url) {
		logger.debug("Find the nifty live stock watch ");
		StockWatchResponse result = null;
		try {
			final HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
			ResponseEntity<StockWatchResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					StockWatchResponse.class);
			result = response.getBody();
		} catch (Exception e) {
			logger.error("Failed to get the nifty live stock watch ");
			throw new RuntimeException("Failed to get the nifty live stock watch ");
		}
		return result;
	}
}

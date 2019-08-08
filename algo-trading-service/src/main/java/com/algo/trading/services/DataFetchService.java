package com.algo.trading.services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.algo.trading.external.api.RestfulSupport;
import com.algo.trading.jsons.CandleResponse;
import com.algo.trading.jsons.DataRequest;

@Service
public class DataFetchService extends RestfulSupport {

	public CandleResponse exchange(final DataRequest request) {
		final String url = generateKiteEndpont(request.getSymbol(), request.getTimeframe(), request.getUserId(),
				request.getFromDate(), request.getToDate());
		HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
		ResponseEntity<CandleResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				CandleResponse.class);
		return response.getBody();
	}
}

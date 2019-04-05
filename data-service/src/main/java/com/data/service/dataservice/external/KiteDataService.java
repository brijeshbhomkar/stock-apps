package com.data.service.dataservice.external;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import com.data.service.dataservice.pojo.DataSearchCriteria;
import com.data.service.dataservice.response.CandleResponse;
import com.data.service.dataservice.util.RestfulSupport;

@Service
public class KiteDataService extends RestfulSupport {

	private static final Logger logger = LoggerFactory.getLogger(KiteDataService.class);

	public CandleResponse get(final DataSearchCriteria dataSearchCriteria, final Long id) {
		logger.debug("Find the data for the symbol ");
		CandleResponse candleResponse = null;
		try {
			HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
			final String url = generateEndpoint(Long.toString(id), dataSearchCriteria.getPeriod(),
					dataSearchCriteria.getKiteId(), dataSearchCriteria.getStartDate(), dataSearchCriteria.getEndDate());
			ResponseEntity<CandleResponse> response = restTemplate.exchange(url, HttpMethod.GET, entity,
					CandleResponse.class);
			candleResponse = response.getBody();
		} catch (HttpClientErrorException e) {
			logger.error("Failed to find data for symbol ", e);
			throw new RuntimeException("Failed to find data for symbol ", e);
		}
		return candleResponse;
	}
}

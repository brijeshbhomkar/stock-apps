package com.data.service.dataservice.external;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.data.service.dataservice.json.VolumeGainer;
import com.data.service.dataservice.response.VolumeGainerResponse;
import com.data.service.dataservice.util.EndpointUrls;
import com.data.service.dataservice.util.RestfulSupport;

@Service
public class VolumeGainerService extends RestfulSupport {

	private static final Logger log = LoggerFactory.getLogger(VolumeGainerService.class);

	public List<VolumeGainer> findVolumeGainers() {
		log.debug("Find the volume gainers");
		VolumeGainerResponse result = null;
		try {
			final HttpEntity<String> entity = new HttpEntity<String>("parameters", httpHeaders);
			ResponseEntity<VolumeGainerResponse> response = restTemplate.exchange(EndpointUrls.NSE_VOLUME_GAINER,
					HttpMethod.GET, entity, VolumeGainerResponse.class);
			result = response.getBody();
		} catch (Exception e) {
			log.error("Failed to get the nifty stocks ");
			throw new RuntimeException("Failed to get the nifty stocks ");
		}

		return result != null ? result.getData() : null;
	}
}

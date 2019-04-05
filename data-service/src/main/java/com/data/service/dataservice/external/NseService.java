package com.data.service.dataservice.external;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.service.dataservice.response.NiftyFityResponse;
import com.data.service.dataservice.util.RestfulSupport;

@Service
public class NseService extends RestfulSupport {
	
	private static final Logger logger = LoggerFactory.getLogger(NseService.class);
	
	public List<NiftyFityResponse> getNiftyFifty() {
		return null;
	}
}

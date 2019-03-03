package com.stocks.data.service.external;

import com.stocks.data.service.entity.Stock;
import com.stocks.data.service.exception.StockServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * The nse data fetcher api.
 */
@RequestMapping(value = "/api/v1/nse")
public class NSEDataController {

    private static final Logger logger = LoggerFactory.getLogger(NSEDataController.class);

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/{fityStocks}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void getNseFiftyStocks(@RequestParam final String fityStocks) {
        logger.debug(" Find the nse fifty stocks ");
        try {
            final HttpHeaders httpHeaders = new HttpHeaders();
            final HttpEntity<Stock[]> httpEntity = new HttpEntity<>(httpHeaders);
            restTemplate.exchange("", RequestMethod.GET, httpEntity, Stock[].class);
        } catch(StockServiceException e) {

        }
    }

}

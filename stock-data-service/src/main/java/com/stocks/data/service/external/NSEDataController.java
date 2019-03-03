package com.stocks.data.service.external;

import com.stocks.data.service.entity.Stock;
import com.stocks.data.service.exception.ErrorResponse;
import com.stocks.data.service.exception.StockServiceException;
import com.stocks.data.service.repository.DataRepository;
import com.stocks.data.service.utils.EndpointGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * The nse data fetcher api.
 */
@RestController
@RequestMapping(value = "/api/v1/nse")
public class NSEDataController {

    private static final Logger logger = LoggerFactory.getLogger(NSEDataController.class);

    @Autowired
    private DataRepository dataRepository;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/{stockJsonType}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> nseStocks(@RequestParam final String stockJsonType) {
        logger.debug(" Find the nse fifty stocks ");
        List<Stock> stocks = null;
        if (StringUtils.isEmpty(stockJsonType)) {
            return ResponseEntity.badRequest().body(" input parameter is not passed! ");
        }
        try {
            final ResponseEntity<Stock[]> responseEntity = restTemplate.getForEntity(EndpointGenerator.niftyFiftyStock(null,
                    null, stockJsonType), Stock[].class);
            if (responseEntity.getStatusCode() == HttpStatus.OK) {
                final Stock[] result = responseEntity.getBody();
                if (result != null) {
                    stocks = Arrays.asList(result);
                }
                //save the updated stocks in db
                dataRepository.saveAll(stocks);
            } else {
                return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, " Failed to get stocks "));
            }
            if (CollectionUtils.isEmpty(Arrays.asList(stocks))) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (StockServiceException e) {
            logger.error(" Failed to get the nifty 50 stocks : {} ", e);
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST, " Failed to get the stocks "));
        }
        return ResponseEntity.ok(stocks);
    }

    @GetMapping(value = "/")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok(" ping! ");
    }
}

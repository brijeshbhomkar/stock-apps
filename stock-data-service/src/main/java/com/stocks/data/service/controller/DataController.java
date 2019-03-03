package com.stocks.data.service.controller;

import com.stocks.data.service.entity.Stock;
import com.stocks.data.service.exception.ErrorResponse;
import com.stocks.data.service.exception.StockServiceException;
import com.stocks.data.service.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/data")
public class DataController {

    private static final Logger logger = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private DataRepository dataRepository;

    @RequestMapping(value = "/{exchange}", method = RequestMethod.GET)
    public ResponseEntity<?> getStocks(@RequestParam final String exchange) {
        logger.debug(" Get all the stocks : {} ", exchange);
        List<Stock> stocks = null;
        try {
            stocks = dataRepository.findAll();
            if (CollectionUtils.isEmpty(stocks)) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (StockServiceException e) {
            logger.error(" Failed to find stocks : {} ", e);
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST,
                    " Failed to find stocks "));
        }

        return new ResponseEntity<>(stocks, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> update(@RequestBody final List<Stock> stock) {
        try {
            dataRepository.saveAll(stock);
        } catch (StockServiceException e) {
            logger.error(" Failed to update stocks : {} ", e);
            return ResponseEntity.badRequest().body(new ErrorResponse(HttpStatus.BAD_REQUEST,
                    " Failed to update the list of stocks"));
        }
        return ResponseEntity.ok(" updated !");
    }
}

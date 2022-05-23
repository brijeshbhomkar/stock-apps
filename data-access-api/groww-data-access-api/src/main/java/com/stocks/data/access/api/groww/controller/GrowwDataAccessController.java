package com.stocks.data.access.api.groww.controller;

import com.common.exception.ApplicationException;
import com.stocks.data.access.api.groww.model.StockTick;
import com.stocks.data.access.api.groww.request.StockRequest;
import com.stocks.data.access.api.groww.service.GrowwDataAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groww")
public class GrowwDataAccessController {

    @Autowired
    private GrowwDataAccessService growwDataAccessService;

    @GetMapping("/test")
    public String test() {
        return "I am running ok!";
    }

    @PostMapping(value = "/data", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<StockTick> getStockData(@RequestBody final StockRequest request) throws ApplicationException {
        return growwDataAccessService.getStockTicks(request.getSymbol(),
                request.getType(), request.getInterval(), request.getIntervalVal());
    }
}

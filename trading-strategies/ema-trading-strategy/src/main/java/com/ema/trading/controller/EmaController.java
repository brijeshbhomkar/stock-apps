package com.ema.trading.controller;

import com.ema.trading.service.JobEnablerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ema")
public class EmaController {

    @Autowired
    private JobEnablerService jobEnablerService;

    @RequestMapping(value = "/{timeframe}/{symbol}/{symbolId}", method = RequestMethod.POST)
    public void startEma(@PathVariable final String timeframe,
                         @PathVariable final String symbol,
                         @PathVariable final String symbolId) {
        jobEnablerService.addJob(symbol, symbolId, timeframe);
    }
}

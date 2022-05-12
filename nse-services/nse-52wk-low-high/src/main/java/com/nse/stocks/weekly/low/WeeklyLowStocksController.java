package com.nse.stocks.weekly.low;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weekly/low")
public class WeeklyLowStocksController {

    @GetMapping
    public String test() {
        return "ok";
    }

}

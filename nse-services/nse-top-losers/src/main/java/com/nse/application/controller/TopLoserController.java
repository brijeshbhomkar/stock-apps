package com.nse.application.controller;

import com.connector.groww.json.common.JsonData;
import com.nse.application.criteria.RequestCriteria;
import com.nse.application.services.TopLoserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@ComponentScan("com.connector.groww.GrowwConnector")
public class TopLoserController {

    @Autowired
    private TopLoserService topLoserService;

    @PostMapping(value = "/start")
    public List<JsonData> losers(@RequestBody @Valid final RequestCriteria criteria) {
        return topLoserService.connect(criteria.getMarketType(), criteria.getFilterType(), Integer.parseInt(criteria.getSize()));
    }

    @GetMapping
    public String test() {
        return "OK";
    }
}

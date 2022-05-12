package com.nse.application.controller;

import com.connector.groww.GrowwConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TopLoserController {

    @Autowired
    private GrowwConnector connector;

//
//    @PostMapping
//    public List<JsonData> losers(@RequestBody final RequestCriteria criteria) {
//        List<JsonData> data = null;
//        try {
//            data = connector.connect(criteria.getMarketType(), criteria.getFilterType(), Integer.parseInt(criteria.getSize()));
//        } catch (ApplicationException e) {
//            e.printStackTrace();
//        }
//        return data;
//    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return "OK";
    }
}

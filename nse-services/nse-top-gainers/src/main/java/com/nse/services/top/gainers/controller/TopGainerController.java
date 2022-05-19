package com.nse.services.top.gainers.controller;

import com.common.exception.ApplicationException;
import com.nse.services.top.gainers.model.TopGainer;
import com.nse.services.top.gainers.service.TopGainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nse/topgainer")
public class TopGainerController {

    @Autowired
    private TopGainerService topGainerService;

    @GetMapping("/test")
    public String test() {
        return "I am running ok!!!";
    }

    @GetMapping("/nifty/100")
    public List<TopGainer> topGainer100() throws ApplicationException {
        return topGainerService.getTopGainersNifty100();
    }

    @GetMapping("/nifty/500")
    public List<TopGainer> topGainer500() throws ApplicationException {
        return topGainerService.getTopGainersNifty500();
    }

    @GetMapping("/smcp/100")
    public List<TopGainer> topGainerSCMP100() throws ApplicationException {
        return topGainerService.getTopGainersSMCP100();
    }
}

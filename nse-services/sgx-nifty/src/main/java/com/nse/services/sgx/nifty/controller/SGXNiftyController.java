package com.nse.services.sgx.nifty.controller;

import com.nse.services.sgx.nifty.model.SGXNiftyOpenInterest;
import com.nse.services.sgx.nifty.service.SGXNiftyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/sgx/nifty")
public class SGXNiftyController {

    @Autowired
    private SGXNiftyService sgxNiftyService;

    @GetMapping("/load/{marketType}")
    public List<SGXNiftyOpenInterest> findOpenInterest(@PathVariable final String marketType) {
        try {
            return sgxNiftyService.futureOpenInterest(marketType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

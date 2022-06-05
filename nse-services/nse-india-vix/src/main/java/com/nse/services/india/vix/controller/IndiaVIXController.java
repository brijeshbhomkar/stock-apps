package com.nse.services.india.vix.controller;

import com.nse.services.india.vix.model.IndiaVix;
import com.nse.services.india.vix.service.IndiaVIXService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/nse/vix")
public class IndiaVIXController {

    @Autowired
    private IndiaVIXService service;

    @GetMapping("/daily")
    public List<IndiaVix> findDailyIndiaVix() throws IOException, InterruptedException {
        return service.indiaVIXList();
    }

    @GetMapping("/load")
    public List<IndiaVix> load() throws Exception {
        return service.startDataLoadingJob();
    }
}


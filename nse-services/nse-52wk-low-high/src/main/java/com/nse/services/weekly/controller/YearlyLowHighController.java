package com.nse.services.weekly.controller;

import com.common.exception.ApplicationException;
import com.nse.services.weekly.model.YearlyHighStock;
import com.nse.services.weekly.model.YearlyLowStock;
import com.nse.services.weekly.service.YearlyHighService;
import com.nse.services.weekly.service.YearlyLowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/nse")
public class YearlyLowHighController {

    @Autowired
    private YearlyLowService yearlyLowService;

    @Autowired
    private YearlyHighService yearlyHighService;

    @GetMapping("/nifty100/yearly/high")
    public List<YearlyHighStock> yearlyNifty100HighStock() throws ApplicationException {
        return yearlyHighService.getYearlyNifty100HighStock();
    }

    @GetMapping("/nifty500/yearly/high")
    public List<YearlyHighStock> yearlyNifty500HighStock() throws ApplicationException {
        return yearlyHighService.getYearlyNifty500HighStock();
    }

    @GetMapping("/SMCP100/yearly/high")
    public List<YearlyHighStock> yearlySMCP100HighStock() throws ApplicationException {
        return yearlyHighService.getYearlySMCP100HighStock();
    }

    @GetMapping("/nifty100/yearly/low")
    public List<YearlyLowStock> yearlyNifty100LowStock() throws ApplicationException {
        return yearlyLowService.getYearlyNifty100LowStock();
    }

    @GetMapping("/nifty500/yearly/low")
    public List<YearlyLowStock> yearlyNifty500LowStock() throws ApplicationException {
        return yearlyLowService.getYearlyNifty500LowStock();
    }

    @GetMapping("/SMCP100/yearly/low")
    public List<YearlyLowStock> yearlySMCP100LowStock() throws ApplicationException {
        return yearlyLowService.getYearlySMCP100LowStock();
    }
}

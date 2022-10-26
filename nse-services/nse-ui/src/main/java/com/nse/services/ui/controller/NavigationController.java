package com.nse.services.ui.controller;

import com.nse.services.ui.service.TopGainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavigationController {

    @Autowired
    private TopGainerService topGainerService;

    @GetMapping("/fiidii")
    public String getFiiDii(Model model){
        model.addAttribute("activePage", "fiidii");
        return "fiidii";
    }

    @GetMapping("/openinterest")
    public String getOpenInterest(Model model){
        model.addAttribute("activePage", "openinterest");
        return "openinterest";
    }

    @GetMapping("/indiavix")
    public String getIndiaVix(Model model){
        model.addAttribute("activePage", "indiavix");
        return "indiavix";
    }

    @GetMapping("/toplosers")
    public String getTopLosers(Model model){
        model.addAttribute("activePage", "toplosers");
        return "toplosers";
    }

    @GetMapping("/topgainers")
    public String getTopGainers(Model model){
        model.addAttribute("activePage", "topgainers");
        model.addAttribute("gainers", topGainerService.getNifty100TopGainers());
        return "topgainers";
    }

    @GetMapping("/sgxnifty")
    public String getSgxNifty(Model model){
        model.addAttribute("activePage", "sgxnifty");
        return "sgxnifty";
    }

    @GetMapping("/contact")
    public String getContact(Model model){
        model.addAttribute("activePage", "contact");
        return "contact";
    }

}
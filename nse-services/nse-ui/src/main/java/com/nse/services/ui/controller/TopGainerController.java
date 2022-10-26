package com.nse.services.ui.controller;

import com.nse.services.ui.service.TopGainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/topgainers")
public class TopGainerController {

    @Autowired
    private TopGainerService service;

    @GetMapping("/nifty/100")
    public String getNifty100TopGainers(Model model) {
        model.addAttribute("gainers", service.getNifty100TopGainers());
        return "gainers";
    }

    @GetMapping("/nifty/500")
    public String getNifty500TopGainers(Model model) {
        model.addAttribute("gainers", service.getNifty500TopGainers());
        return "gainers";
    }

    @GetMapping("/smcp/100")
    public String topGainerSCMP100(Model model) {
        model.addAttribute("gainers", service.getNiftySMCP100TopGainers());
        return "gainers";
    }
}

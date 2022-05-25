package nse.services.open.interest.controller;

import nse.services.open.interest.service.OpenInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/nse/openinterest")
public class OpenInterestController {

    @Autowired
    private OpenInterestService openInterestService;

    @GetMapping("/test")
    public String test() {
        return "I am running ok!";
    }
}

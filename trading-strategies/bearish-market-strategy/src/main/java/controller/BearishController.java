package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bearish")
public class BearishController {

    @GetMapping("/test")
    public String test() {
        return "I am OK!!";
    }
}
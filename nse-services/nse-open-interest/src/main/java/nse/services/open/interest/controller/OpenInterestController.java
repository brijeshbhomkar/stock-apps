package nse.services.open.interest.controller;

import nse.services.open.interest.model.OpenInterest;
import nse.services.open.interest.model.OpenInterestRequest;
import nse.services.open.interest.service.OpenInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nse/oi")
public class OpenInterestController {

    @Autowired
    private OpenInterestService openInterestService;

    @GetMapping("/test")
    public String test() {
        return "Working!";
    }

    @PostMapping(value = "/indices", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OpenInterest> openInterestIndices(@RequestBody final OpenInterestRequest request) {
        return openInterestService.indicesOpenInterest(request.getSymbol(), request.getRange());
    }

    @PostMapping(value = "/equities", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public List<OpenInterest> openInterestEquities(@RequestBody final OpenInterestRequest request) {
        return openInterestService.equitiesOpenInterest(request.getSymbol(), request.getRange());
    }
}

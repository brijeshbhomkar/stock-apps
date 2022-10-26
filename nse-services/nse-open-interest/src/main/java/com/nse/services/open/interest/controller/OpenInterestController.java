package com.nse.services.open.interest.controller;


import com.nse.services.open.interest.api.OpeninterestApi;
import com.nse.services.open.interest.model.OpenInterest;
import com.nse.services.open.interest.model.OpenInterestRequest;
import com.nse.services.open.interest.service.OpenInterestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/v2/api")
public class OpenInterestController implements OpeninterestApi {

    @Autowired
    private OpenInterestService openInterestService;

    public OpenInterestService getOpenInterestService() {
        return openInterestService;
    }

    @Override
    public ResponseEntity<List<OpenInterest>> getIndicesOpenInterest(@Valid OpenInterestRequest request) {
        try {
            List<OpenInterest> openInterests = openInterestService.indicesOpenInterest(request.getSymbol(), request.getStrikePriceRange());
            if (!CollectionUtils.isEmpty(openInterests)) {
                return new ResponseEntity<>(openInterests, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<OpenInterest>> getEquitiesOpenInterestDay(String symbol) {
        try {
            List<OpenInterest> openInterests = openInterestService.equitiesOpenInterest(symbol, null);
            if (!CollectionUtils.isEmpty(openInterests)) {
                return new ResponseEntity<>(openInterests, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<OpenInterest>> getIndicesOpenInterestDay(String symbol) {
        try {
            List<OpenInterest> openInterests = openInterestService.indicesOpenInterest(symbol, null);
            if (!CollectionUtils.isEmpty(openInterests)) {
                return new ResponseEntity<>(openInterests, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<OpenInterest>> getEquitiesOpenInterest(@Valid OpenInterestRequest request) {
        try {
            List<OpenInterest> openInterests = openInterestService.equitiesOpenInterest(request.getSymbol(), request.getStrikePriceRange());
            if (!CollectionUtils.isEmpty(openInterests)) {
                return new ResponseEntity<>(openInterests, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }
}
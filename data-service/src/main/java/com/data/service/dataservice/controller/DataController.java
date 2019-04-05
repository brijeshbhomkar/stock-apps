package com.data.service.dataservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/data")
public class DataController {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok(" success !");
    }
}

package com.stocks.oi.controller;

import com.stocks.oi.document.DailyParticipantOpenInterestDocument;
import com.stocks.oi.mongo.repository.DailyParticipantOpenInterestRepository;
import com.stocks.oi.response.oi.fii.DailyParticipantDetails;
import com.stocks.oi.service.DailyParticipantOpenInterestService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/participant/oi")
public class DailyParticipantOpenInterestController {

    @Autowired
    private DailyParticipantOpenInterestService dailyParticipantOpenInterestService;

    @GetMapping("/daily")
    public List<DailyParticipantDetails> fii() {
        log.info("Getting participant open interest details for day " + LocalDate.now());
        List<DailyParticipantDetails> dailyParticipantDetails = dailyParticipantOpenInterestService.getFII();
        return dailyParticipantDetails;
    }
}

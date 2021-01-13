package com.stocks.oi.controller;

import com.stocks.oi.response.oi.fii.DailyParticipantDetails;
import com.stocks.oi.service.DailyParticipantVolumeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/participant/volume")
public class DailyParticipantVolumeController {

    @Autowired
    private DailyParticipantVolumeService dailyParticipantVolumeService;

    @GetMapping("/daily")
    public List<DailyParticipantDetails> fii() {
        log.info("Getting participant volume details for day " + LocalDate.now());
        List<DailyParticipantDetails> dailyParticipantDetails = dailyParticipantVolumeService.getDailyVolume();
        return dailyParticipantDetails;
    }
}

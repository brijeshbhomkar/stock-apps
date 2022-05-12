package com.stocks.oi.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Log4j2
@Service
public class DailyOpenInterestLoaderService {

    @Autowired
    private DailyOpenInterestSpurtsService dailyOpenInterestSpurtsService;

    @Autowired
    private DailyParticipantOpenInterestService dailyParticipantOpenInterestService;

    @Autowired
    private DailyParticipantVolumeService dailyParticipantVolumeService;

    @Scheduled(cron = "0 0 17 * * 1-5")
    public void dailyLoader() {
        log.info("Loading daily participant highest open interest " + LocalDateTime.now());
        dailyOpenInterestSpurtsService.getOpenInterestSpurtsStocks();

        log.info("Loading daily participant highest volume " + LocalDateTime.now());
        dailyParticipantVolumeService.getDailyVolume();

        log.info("Loading daily participant open interest "+ LocalDateTime.now());
        dailyParticipantOpenInterestService.getFII();
    }
}

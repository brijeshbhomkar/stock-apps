package com.nse.services.india.vix.service;

import com.connector.nse.vix.client.IndiaVIXClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.services.india.vix.config.JobScheduler;
import com.nse.services.india.vix.json.CurrentVixSnapshot;
import com.nse.services.india.vix.json.HistoricalVixSnapshot;
import com.nse.services.india.vix.json.IndiaVIXJson;
import com.nse.services.india.vix.model.IndiaVix;
import com.nse.services.india.vix.repository.IndiaVIXRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class IndiaVIXService {

    @Autowired
    private IndiaVIXRepository repository;

    @Autowired
    private IndiaVIXClient indiaVIXClient;

    @Autowired
    private JobScheduler jobScheduler;

    public List<IndiaVix> indiaVIXList() throws IOException, InterruptedException {
        List<IndiaVix> indiaVIXList = null;
        Optional<String> response = Optional.ofNullable(indiaVIXClient.caller1("NSE-VIX-DETAILS", null));
        if (response.isPresent()) {
            IndiaVIXJson indiaVIX = new ObjectMapper().readValue(response.get(), IndiaVIXJson.class);
            indiaVIXList = Arrays.asList(indiaVIX).stream().map(this::mapper).collect(Collectors.toList());
            indiaVIXList.stream().forEach(s -> repository.save(s));
        }
        return indiaVIXList;
    }

    private <R> IndiaVix mapper(final IndiaVIXJson json) {
        final IndiaVix indiaVIX = new IndiaVix();
        final CurrentVixSnapshot currentVixSnapshot = json.getCurrentVixSnapShot().get(0);
        final HistoricalVixSnapshot historicalVixSnapshot = json.getHistoricalVixSnapshot().get(0);
        if (currentVixSnapshot != null) {
            indiaVIX.setDate(new Date(currentVixSnapshot.getTimestamp()));
            indiaVIX.setOpen(currentVixSnapshot.getOpenPrice());
            indiaVIX.setHigh(currentVixSnapshot.getHighPrice());
            indiaVIX.setLow(currentVixSnapshot.getLowPrice());
            indiaVIX.setClose(currentVixSnapshot.getCurrentPrice());
            indiaVIX.setPrevClose(currentVixSnapshot.getPrevClose());
            indiaVIX.setPerChange(currentVixSnapshot.getPerChange());
        }

//        if (historicalVixSnapshot != null) {
//            indiaVIX.setWeekAgoPrice(historicalVixSnapshot.getWeekAgoPrice());
//            indiaVIX.setMonthAgoPrice(historicalVixSnapshot.getMonthAgoPrice());
//            indiaVIX.setYearAgoPrice(historicalVixSnapshot.getYearAgoPrice());
//        }

        DecimalFormat df = new DecimalFormat("#.##");
        indiaVIX.setMonthlyRange(Double.valueOf(df.format(currentVixSnapshot.getCurrentPrice() / Math.sqrt(12))));
        indiaVIX.setWeeklyRange(Double.valueOf(df.format(currentVixSnapshot.getCurrentPrice() / Math.sqrt(52))));
        indiaVIX.setDailyRange(Double.valueOf(df.format(currentVixSnapshot.getCurrentPrice() / Math.sqrt(250))));

        return indiaVIX;
    }

    public List<IndiaVix> startDataLoadingJob() throws Exception {
        jobScheduler.startLoadingData();
        return repository.findAll();
    }
}

package com.nse.services.india.vix.config;

import com.nse.services.india.vix.model.IndiaVix;
import com.nse.services.india.vix.model.IndiaVixItem;
import com.nse.services.india.vix.repository.IndiaVIXRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.List;

@Component
public class IndiaVixWriter implements ItemWriter<IndiaVixItem> {

    @Autowired
    private IndiaVIXRepository repository;

    @Override
    public void write(List<? extends IndiaVixItem> indiaVixItems) throws Exception {
        indiaVixItems.stream().map(this::mapper).forEach(s -> repository.save(s));
    }

    private <R> IndiaVix mapper(final IndiaVixItem item) {
        final IndiaVix indiaVix = new IndiaVix();
        indiaVix.setDate(item.getDate());
        indiaVix.setOpen(item.getOpen());
        indiaVix.setHigh(item.getHigh());
        indiaVix.setLow(item.getLow());
        indiaVix.setClose(item.getClose());
        indiaVix.setPrevClose(item.getPrevClose());
        indiaVix.setPerChange(item.getPerChange());

        DecimalFormat df = new DecimalFormat("#.##");
        indiaVix.setMonthlyRange(Double.valueOf(df.format((item.getClose() / Math.sqrt(12)))));
        indiaVix.setWeeklyRange(Double.valueOf(df.format((item.getClose() / Math.sqrt(52)))));
        indiaVix.setDailyRange(Double.valueOf(df.format((item.getClose() / Math.sqrt(253)))));

        return indiaVix;
    }
}

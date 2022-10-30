package com.nse.services.open.interest.service;

import com.nse.services.open.interest.model.OpenInterestScheduledRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@Log4j2
public class OpenInterestScheduler {

    @Autowired
    private OpenInterestService openInterestService;

    private String symbol;

    private String strikePrice;

    private AtomicBoolean schedulerState = new AtomicBoolean(Boolean.FALSE);

    private AtomicBoolean marketLive = new AtomicBoolean(Boolean.FALSE);

    private final StringBuilder CRON_BUILDER = new StringBuilder();

    public void startOpenInterestScheduleder(final OpenInterestScheduledRequest request) throws Exception {
        if (request.getScheduled() == null) {
            throw new Exception(" Scheduled cannot be empty ");
        }

        //when service type = START
        if (request.getServiceType().getValue()
                .equalsIgnoreCase(OpenInterestScheduledRequest.ServiceTypeEnum.START.getValue())) {
            //set the values and start scheduler
            this.symbol = request.getSymbol();
            this.strikePrice = request.getStrikePriceRange();
            String result = getScheduledTime(request.getScheduled());

            //start scheduler
            CRON_BUILDER.append("0 "); //seconds
            CRON_BUILDER.append("0").append("/").append(result).append(" "); //every minute
            CRON_BUILDER.append("9-15 "); //between 8 to 3 pm
            CRON_BUILDER.append("* * ");
            CRON_BUILDER.append("MON-FRI");

            //enable scheduler
            schedulerState.set(Boolean.TRUE);
            start();

        }

        //when service type = STOP
        if (request.getServiceType().getValue().
                equalsIgnoreCase(OpenInterestScheduledRequest.ServiceTypeEnum.STOP.getValue())) {
            //TODO: need to find way to stop running scheduler
            schedulerState.getAndSet(Boolean.FALSE);
        }
    }

    private String getScheduledTime(String scheduled) {
        String time = scheduled.charAt(0) + "";
        if (time != null && !time.isEmpty()) {
            if (time.equalsIgnoreCase("5")) {
                return "5";
            } else if (time.equalsIgnoreCase("10")) {
                return "10";
            } else if (time.equalsIgnoreCase("15")) {
                return "15";
            } else if (time.equalsIgnoreCase("30")) {
                return "30";
            }
            return "60";
        }
        return null;
    }

    @Scheduled(cron = "0 0/5 8-15 * * MON-FRI")
    public void start() {
        try {
            if (schedulerState.get() && marketLive.get()) {
                log.info(" Started scheduler for open interest " + symbol);
                if (symbol != null) {
                    openInterestService.indicesOpenInterest(symbol, strikePrice);
                }
            }
        } catch (Exception e) {
            log.error(" Failed to run scheduler, no data ");
        }
    }

    @Scheduled(cron = "0 0/1 9-14 * * MON-FRI")
    public void marketLive() {
        LocalTime localTime = LocalTime.now();
        if (localTime.isAfter(LocalTime.of(9, 15))
                && localTime.isBefore(LocalTime.of(15, 30))) {
            log.info(" Current time of the market is " + localTime.toString());
            marketLive.set(Boolean.TRUE);
        }
    }
}

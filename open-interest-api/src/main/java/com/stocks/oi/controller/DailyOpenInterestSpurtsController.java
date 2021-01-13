package com.stocks.oi.controller;

import com.stocks.oi.document.*;
import com.stocks.oi.mongo.repository.*;
import com.stocks.oi.response.oi.spurts.DailyHighestOpenInterest;
import com.stocks.oi.response.oi.spurts.DailyHighestOpenInterestResponse;
import com.stocks.oi.response.oi.spurts.DailyOpenInterest;
import com.stocks.oi.response.oi.spurts.DailyOpenInterestResponse;
import com.stocks.oi.service.DailyOpenInterestSpurtsService;
import com.stocks.oi.util.DateUtil;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Log4j2
@RestController
@RequestMapping("/api/oi/spurts")
public class DailyOpenInterestSpurtsController {

    @Autowired
    private DailyOpenInterestSpurtsService dailyOpenInterestSpurtsService;

    @Autowired
    private DailyHighestOpenInterestRepository dailyHighestOpenInterestRepository;

    @Autowired
    private DailySlideInPriceSlideInOIRepository dailySlideInPriceSlideInOIRepository;

    @Autowired
    private DailyRiseInPriceRiseInOIRepository dailyRiseInPriceRiseInOIRepository;

    @Autowired
    private DailyRiseInPriceSlideInOIRepository dailyRiseInPriceSlideInOIRepository;

    @Autowired
    private DailySlideInPriceRiseInOIRepository dailySlideInPriceRiseInOIRepository;

    @GetMapping("/all")
    public HttpStatus findAllDailyOpenInterest() {
        log.info("Find rise in price and slide in open interest ");
        riseInPriceSlideInOpenInterestCall();

        log.info("Find rise in price and rise in open interest ");
        riseInPriceRiseInOpenInterestCall();

        log.info("Find the slide in price and rise in open interest ");
        slideInPriceRiseInOpenInterestCall();

        log.info("Find the slide in price and slide in open interest ");
        dailySlideInPriceSlideInOpenInterestCall();

        return HttpStatus.OK;
    }

    @GetMapping("/pricerise/oislide")
    public DailyOpenInterestResponse riseInPriceSlideInOpenInterest() {
        log.info("Find daily rise in price and slide in oi ");
        return riseInPriceSlideInOpenInterestCall();
    }

    private DailyOpenInterestResponse riseInPriceSlideInOpenInterestCall() {
        DailyOpenInterestResponse response = dailyOpenInterestSpurtsService.riseInPriceSlideInOpenInterestStocks();
        if (response != null) {
            DailyRiseInPriceSlideInOIDocumentWrapper wrapper = new DailyRiseInPriceSlideInOIDocumentWrapper();
            List<DailyRiseInPriceSlideInOIDocument> dailyRiseInPriceSlideInOIDocuments = response.getData().stream()
                    .filter(s -> s!=null).map(this::riseInPriceSlideInOpenInterest).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(dailyRiseInPriceSlideInOIDocuments)) {
                wrapper.setDocuments(dailyRiseInPriceSlideInOIDocuments);
            }
            wrapper.setDate(DateUtil.getDate(new Date()));
            dailyRiseInPriceSlideInOIRepository.save(wrapper);
        }
        return response;
    }

    private <R> DailyRiseInPriceSlideInOIDocument riseInPriceSlideInOpenInterest(DailyOpenInterest oi) {
        DailyRiseInPriceSlideInOIDocument document = new DailyRiseInPriceSlideInOIDocument();
        document.setSymbol(oi.getSymbol());
        document.setExpiry(DateUtil.toDate(oi.getExpiry()));
        document.setInstrument(oi.getInstrument());
        document.setIsFO(parseInput(oi.getIsFO()));
        document.setLatestOI(parseInput(oi.getLatestOI()));
        document.setLtp(parseInput(oi.getLtp()));
        document.setOiChange(parseInput(oi.getOiChange()));
        document.setOptionType(oi.getOptionType());
        document.setPercLtpChange(parseInput(oi.getPercLtpChange()));
        document.setPremValueInCrores(parseInput(oi.getPremValueInCrores()));
        document.setPrevClose(parseInput(oi.getPrevClose()));
        document.setPreviousOI(parseInput(oi.getPreviousOI()));
        document.setStrike(parseInput(oi.getStrike()));
        document.setUnderlyValue(parseInput(oi.getUnderlyValue()));
        document.setValueInCrores(parseInput(oi.getValueInCrores()));
        document.setVolume(parseInput(oi.getVolume()));
        return document;
    }

    @GetMapping("/pricerise/oirise")
    public DailyOpenInterestResponse riseInPriceRiseInOpenInterest() {
        log.info("Find daily rise in price and oi rise stocks ");
        return riseInPriceRiseInOpenInterestCall();
    }

    private DailyOpenInterestResponse riseInPriceRiseInOpenInterestCall() {
        DailyOpenInterestResponse response = dailyOpenInterestSpurtsService.riseInPriceRiseInOpenInterestStocks();
        if (response != null) {
            DailyRiseInPriceRiseInOIDocumentWrapper wrapper = new DailyRiseInPriceRiseInOIDocumentWrapper();
            List<DailyRiseInPriceRiseInOIDocument> dailyRiseInPriceRiseInOIDocuments = response.getData().stream()
                    .filter(s -> s!=null).map(this::riseInPriceRiseInOpenInterest).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(dailyRiseInPriceRiseInOIDocuments)) {
                wrapper.setBullish(dailyRiseInPriceRiseInOIDocuments);
            }
            wrapper.setDate(DateUtil.getDate(new Date()));
            dailyRiseInPriceRiseInOIRepository.save(wrapper);
        }
        return response;
    }

    private <R> DailyRiseInPriceRiseInOIDocument riseInPriceRiseInOpenInterest(DailyOpenInterest oi) {
        DailyRiseInPriceRiseInOIDocument document = new DailyRiseInPriceRiseInOIDocument();
        document.setSymbol(oi.getSymbol());
        document.setExpiry(DateUtil.toDate(oi.getExpiry()));
        document.setInstrument(oi.getInstrument());
        document.setIsFO(parseInput(oi.getIsFO()));
        document.setLatestOI(parseInput(oi.getLatestOI()));
        document.setLtp(parseInput(oi.getLtp()));
        document.setOiChange(parseInput(oi.getOiChange()));
        document.setOptionType(oi.getOptionType());
        document.setPercLtpChange(parseInput(oi.getPercLtpChange()));
        document.setPremValueInCrores(parseInput(oi.getPremValueInCrores()));
        document.setPrevClose(parseInput(oi.getPrevClose()));
        document.setPreviousOI(parseInput(oi.getPreviousOI()));
        document.setStrike(parseInput(oi.getStrike()));
        document.setUnderlyValue(parseInput(oi.getUnderlyValue()));
        document.setValueInCrores(parseInput(oi.getValueInCrores()));
        document.setVolume(parseInput(oi.getVolume()));
        return document;
    }

    @GetMapping("/priceslide/oirise")
    public DailyOpenInterestResponse slideInPriceRiseInOpenInterest() {
        log.info("Find daily slide in price slide and oi rise stocks ");
        return slideInPriceRiseInOpenInterestCall();
    }

    private DailyOpenInterestResponse slideInPriceRiseInOpenInterestCall() {
        DailyOpenInterestResponse response = dailyOpenInterestSpurtsService.slideInPriceRiseInOpenInterestStocks();
        if (response != null) {
            DailySlideInPriceRiseInOIDocumentWrapper wrapper = new DailySlideInPriceRiseInOIDocumentWrapper();
            List<DailySlideInPriceRiseInOIDocument> dailySlideInPriceRiseInOIDocuments = response.getData().stream()
                    .filter(s -> s!=null).map(this::slideInPriceRiseInOpenInterest).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(dailySlideInPriceRiseInOIDocuments)) {
                wrapper.setDocuments(dailySlideInPriceRiseInOIDocuments);
            }
            wrapper.setDate(DateUtil.getDate(new Date()));
            dailySlideInPriceRiseInOIRepository.save(wrapper);
        }
        return response;
    }

    private <R> DailySlideInPriceRiseInOIDocument slideInPriceRiseInOpenInterest(DailyOpenInterest oi) {
        DailySlideInPriceRiseInOIDocument document = new DailySlideInPriceRiseInOIDocument();
        document.setSymbol(oi.getSymbol());
        document.setExpiry(DateUtil.toDate(oi.getExpiry()));
        document.setInstrument(oi.getInstrument());
        document.setIsFO(parseInput(oi.getIsFO()));
        document.setLatestOI(parseInput(oi.getLatestOI()));
        document.setLtp(parseInput(oi.getLtp()));
        document.setOiChange(parseInput(oi.getOiChange()));
        document.setOptionType(oi.getOptionType());
        document.setPercLtpChange(parseInput(oi.getPercLtpChange()));
        document.setPremValueInCrores(parseInput(oi.getPremValueInCrores()));
        document.setPrevClose(parseInput(oi.getPrevClose()));
        document.setPreviousOI(parseInput(oi.getPreviousOI()));
        document.setStrike(parseInput(oi.getStrike()));
        document.setUnderlyValue(parseInput(oi.getUnderlyValue()));
        document.setValueInCrores(parseInput(oi.getValueInCrores()));
        document.setVolume(parseInput(oi.getVolume()));
        return document;
    }

    @GetMapping("/priceslide/oislide")
    public DailyOpenInterestResponse slideInPriceSlideInOpenInterest() {
        log.info("Find daily slide in price slide in OI stocks ");
        return dailySlideInPriceSlideInOpenInterestCall();
    }

    private DailyOpenInterestResponse dailySlideInPriceSlideInOpenInterestCall() {
        DailyOpenInterestResponse response = dailyOpenInterestSpurtsService.slideInPriceSlideInOpenInterestStocks();
        if (response != null) {
            DailySlideInPriceSlideInOIDocumentWrapper wrapper = new DailySlideInPriceSlideInOIDocumentWrapper();
            List<DailySlideInPriceSlideInOIDocument> dailySlideInPriceSlideInOIDocuments = response.getData().stream()
                    .filter(s -> s!=null).map(this::slideInPriceSlideInOpenInterest).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(dailySlideInPriceSlideInOIDocuments)) {
                wrapper.setDocuments(dailySlideInPriceSlideInOIDocuments);
            }
            wrapper.setDate(DateUtil.getDate(new Date()));
            dailySlideInPriceSlideInOIRepository.save(wrapper);
        }
        return response;
    }

    private <R> DailySlideInPriceSlideInOIDocument slideInPriceSlideInOpenInterest(DailyOpenInterest oi) {
        DailySlideInPriceSlideInOIDocument document = new DailySlideInPriceSlideInOIDocument();
        document.setSymbol(oi.getSymbol());
        document.setExpiry(DateUtil.toDate(oi.getExpiry()));
        document.setInstrument(oi.getInstrument());
        document.setIsFO(parseInput(oi.getIsFO()));
        document.setLatestOI(parseInput(oi.getLatestOI()));
        document.setLtp(parseInput(oi.getLtp()));
        document.setOiChange(parseInput(oi.getOiChange()));
        document.setOptionType(oi.getOptionType());
        document.setPercLtpChange(parseInput(oi.getPercLtpChange()));
        document.setPremValueInCrores(parseInput(oi.getPremValueInCrores()));
        document.setPrevClose(parseInput(oi.getPrevClose()));
        document.setPreviousOI(parseInput(oi.getPreviousOI()));
        document.setStrike(parseInput(oi.getStrike()));
        document.setUnderlyValue(parseInput(oi.getUnderlyValue()));
        document.setValueInCrores(parseInput(oi.getValueInCrores()));
        document.setVolume(parseInput(oi.getVolume()));
        return document;
    }

    @GetMapping("/highest/daily")
    public DailyHighestOpenInterestResponse dailyTopOIchangeStocks() {
        log.info("controller fetching open interest spurts in daily ");
        DailyHighestOpenInterestResponse response = dailyOpenInterestSpurtsService.getOpenInterestSpurtsStocks();
        if (response != null) {
            DailyHighestOpenInterestDocumentWrapper wrapper = new DailyHighestOpenInterestDocumentWrapper();
            List<DailyHighestOpenInterestDocument> dailyHighestOpenInterestDocuments = response.getData().stream()
                    .filter(s -> s!=null).map(this::dailyTopOI).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(dailyHighestOpenInterestDocuments)) {
                wrapper.setDocuments(dailyHighestOpenInterestDocuments);
            }
            wrapper.setDate(DateUtil.getDate(new Date()));
            dailyHighestOpenInterestRepository.save(wrapper);
        }
        return response;
    }

    private <R> DailyHighestOpenInterestDocument dailyTopOI(DailyHighestOpenInterest dailyHighestOpenInterest) {
        DailyHighestOpenInterestDocument json = new DailyHighestOpenInterestDocument();
        json.setCurrentMarketDate(LocalDate.now());
        json.setFutVal(parseInput(dailyHighestOpenInterest.getFutVal()));
        json.setIsFO(parseInput(dailyHighestOpenInterest.getIsFO()));
        json.setLatestOI(parseInput(dailyHighestOpenInterest.getLatestOI()));
        json.setOiChange(parseInput(dailyHighestOpenInterest.getOiChange()));
        json.setOptVal(parseInput(dailyHighestOpenInterest.getOptVal()));
        json.setOpVal(parseInput(dailyHighestOpenInterest.getOpVal()));
        json.setPercOIchange(parseInput(dailyHighestOpenInterest.getPercOIchange()));
        json.setSymbol(dailyHighestOpenInterest.getSymbol());
        json.setPrevOI(parseInput(dailyHighestOpenInterest.getPrevOI()));
        json.setTotVal(parseInput(dailyHighestOpenInterest.getTotVal()));
        json.setUnderlying(parseInput(dailyHighestOpenInterest.getUnderlying()));
        json.setValueInLakhs(parseInput(dailyHighestOpenInterest.getValueInLakhs()));
        json.setVolume(parseInput(dailyHighestOpenInterest.getVolume()));
        return json;
    }

    @SneakyThrows
    private Double parseInput(final String num) {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        return format.parse(num).doubleValue();
    }
}

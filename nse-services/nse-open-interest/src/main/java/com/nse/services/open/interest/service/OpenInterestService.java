package com.nse.services.open.interest.service;

import com.connector.nse.openinterest.client.OpenInterestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.nse.services.open.interest.json.OpenInterestChainResponse;
import com.nse.services.open.interest.model.OpenInterest;
import com.nse.services.open.interest.model.OpenInterestEntity;
import com.nse.services.open.interest.processor.OpenInterestProcessorService;
import com.nse.services.open.interest.processor.OpenInterestRangeProcessor;
import com.nse.services.open.interest.repository.OpenInterestRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
public class OpenInterestService {

    private static final String INDICES_OPEN_INTEREST = "INDICES-OPEN-INTEREST";
    private static final String EQUITIES_OPEN_INTEREST = "EQUITIES-OPEN-INTEREST";


    @Autowired
    private OpenInterestRepository openInterestRepository;

    @Autowired
    private OpenInterestClient openInterestCaller;

    @Autowired
    private OpenInterestRangeProcessor openInterestRangeProcessor;

    @Autowired
    private OpenInterestProcessorService openInterestProcessorService;

    public List<OpenInterest> indicesOpenInterest(final String symbol, final String strikePrice) throws Exception {
        List<OpenInterestEntity> openInterestEntities = new ArrayList<>();
        OpenInterestChainResponse openInterestChainResponse = remotecall(INDICES_OPEN_INTEREST, symbol, strikePrice);
        if (openInterestChainResponse != null) {
            //indices open interest
            openInterestEntities.add(openInterestRangeProcessor.processWithRange(openInterestChainResponse, symbol, strikePrice));
            //save to db
            openInterestEntities.stream().forEach(s -> openInterestRepository.save(s));
        }
        return openInterestEntities != null ?
                openInterestEntities.stream().map(this::entityToJsonMapper).collect(Collectors.toList())
                : Arrays.asList();
    }


    public List<OpenInterest> equitiesOpenInterest(final String symbol, final String range) throws Exception {
        final List<OpenInterestEntity> openInterestEntities = new ArrayList<>();
        OpenInterestChainResponse openInterestChainResponse = remotecall(EQUITIES_OPEN_INTEREST, symbol, range);
        if (openInterestChainResponse != null) {
            //equities open interest
            openInterestEntities.add(openInterestProcessorService.processWithoutRange(openInterestChainResponse, symbol));
        }
        return openInterestEntities.stream().map(this::entityToJsonMapper).collect(Collectors.toList());
    }

    public OpenInterestChainResponse remotecall(String callerType, final String symbol, final String range) {
        OpenInterestChainResponse openInterestResponse = null;
        try {
            final String data = openInterestCaller.caller1(callerType, symbol);
            if (data != null && !data.isEmpty()) {
                openInterestResponse = new ObjectMapper().readValue(data, OpenInterestChainResponse.class);
            }
        } catch (Exception e) {
            log.error("Failed to get the data! Retrying again " + e.getMessage());
        }
        return openInterestResponse;
    }

    private <R> OpenInterest entityToJsonMapper(OpenInterestEntity openInterestEntity) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        return mapper.convertValue(openInterestEntity, OpenInterest.class);
    }

}

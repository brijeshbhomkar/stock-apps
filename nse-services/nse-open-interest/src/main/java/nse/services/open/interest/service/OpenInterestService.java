package nse.services.open.interest.service;

import com.connector.nse.openinterest.client.OpenInterestClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nse.services.open.interest.json.*;
import nse.services.open.interest.model.OpenInterest;
import nse.services.open.interest.model.OpenInterestRequest;
import nse.services.open.interest.repository.OpenInterestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@Log4j2
@Service
public class OpenInterestService {

    @Autowired
    private OpenInterestRepository openInterestRepository;

    @Autowired
    private OpenInterestClient openInterestCaller;

    private static final String INDICES_OPEN_INTEREST = "INDICES-OPEN-INTEREST";

    private static final String EQUITIES_OPEN_INTEREST = "EQUITIES-OPEN-INTEREST";

    private final ConcurrentLinkedQueue<OpenInterestRequest> retryQueue = new ConcurrentLinkedQueue<OpenInterestRequest>();

    private final BlockingQueue<OpenInterestResponse> responseQueue = new ArrayBlockingQueue<OpenInterestResponse>(50);

    public List<OpenInterest> indicesOpenInterest(final String symbol, final String range) {
        //start blocking queue mechanism for failure cases
        startQueues();
        List<OpenInterest> openInterests = new ArrayList<>();
        OpenInterestChainResponse openInterestChainResponse = remotecall(INDICES_OPEN_INTEREST, symbol, range);
        if (openInterestChainResponse != null) {
            //indices open interest
            openInterests.add(processOpenInterest(openInterestChainResponse, symbol, range));
            //save to db
            openInterests.stream().forEach(s -> openInterestRepository.save(s));
        }
        return openInterests;
    }

    private void startQueues() {
        ScheduledExecutorService sc = Executors.newScheduledThreadPool(2);
        sc.schedule(new OpenInterestProducer(), 15, TimeUnit.SECONDS);
        sc.schedule(new OpenInterestConsumer(), 30, TimeUnit.SECONDS);

        sc.shutdown();
    }

    private OpenInterest processOpenInterest(final OpenInterestChainResponse openInterestChainResponse, final String symbol, final String range) {
        final OpenInterest openInterest = new OpenInterest();
        openInterest.setSymbol(symbol);
        openInterest.setCurrentDateTime(LocalDateTime.now());
        OpenInterestChainFiltered filtered = openInterestChainResponse.getFiltered();
        if (filtered != null) {

            Double stockprice = Double.parseDouble(openInterestChainResponse.getRecords().getUnderlyingValue());
            int uppercircuit = (int) (stockprice + Integer.parseInt(range));
            int lowercircuit = (int) (stockprice - Integer.parseInt(range));

            List<OpenInterestChain> boundedOptionChains = filtered.getData().stream()
                    .filter(k -> k.getCe() != null && k.getPe() != null)
                    .filter(s -> s.getPe().getStrikePrice() != null && s.getPe().getStrikePrice() <= uppercircuit
                            && s.getCe().getStrikePrice() != null && s.getCe().getStrikePrice() >= lowercircuit)
                    .collect(Collectors.toList());

            List<OpenInterestChainJson> peOptionChain = boundedOptionChains.stream().map(OpenInterestChain::getPe).collect(Collectors.toList());
            List<OpenInterestChainJson> ceOptionChain = boundedOptionChains.stream().map(OpenInterestChain::getCe).collect(Collectors.toList());

            double peTotOI = peOptionChain.stream().mapToDouble(OpenInterestChainJson::getOpenInterest).sum();
            double ceTotOI = ceOptionChain.stream().mapToDouble(OpenInterestChainJson::getOpenInterest).sum();

            double peTotVol = peOptionChain.stream().mapToDouble(OpenInterestChainJson::getTotalTradedVolume).sum();
            double ceTotVol = peOptionChain.stream().mapToDouble(OpenInterestChainJson::getTotalTradedVolume).sum();

            // CE ce = filtered.getCe();
            //CE pe = filtered.getPe();

            double totOiPcr = peTotOI / ceTotOI;
            //dailyOpenInterestPCR.setPcrOI(totOiPcr);

            double totVolPcr = peTotVol / ceTotVol;


            DecimalFormat df = new DecimalFormat("#.##");
            df.setRoundingMode(RoundingMode.FLOOR);

            totOiPcr = new Double(df.format(totOiPcr));

            openInterest.setPcrOI(totOiPcr);
            totVolPcr = new Double(df.format(totVolPcr));

            openInterest.setPcrVolume(totVolPcr);

            // CE
            List<OpenInterestChainJson> ceList = filtered.getData().stream()
                    .filter(s -> s.getCe() != null)
                    .map(m -> m.getCe())
                    .sorted(Comparator.comparingDouble(OpenInterestChainJson::getOpenInterest).reversed()).limit(2)
                    .collect(Collectors.toList());

            // PE
            List<OpenInterestChainJson> peList = filtered.getData().stream()
                    .filter(f -> f.getPe() != null)
                    .map(m -> m.getPe())
                    .sorted(Comparator.comparingDouble(OpenInterestChainJson::getOpenInterest).reversed()).limit(2)
                    .collect(Collectors.toList());

            OpenInterestChainJson highestCEOI = ceList != null && ceList.size() > 0 ? ceList.get(0) : null;
            OpenInterestChainJson highestPEOI = peList != null && peList.size() > 0 ? peList.get(0) : null;

            OpenInterestChainJson secondHighestCEOI = ceList != null && ceList.size() > 0 ? ceList.get(1) : null;
            OpenInterestChainJson secondHighestPEOI = peList != null && peList.size() > 0 ? peList.get(1) : null;


            if (highestCEOI != null) {
                openInterest.setCallLtp(highestCEOI.getUnderlyingValue());
                openInterest.setCallStrikePrice(highestCEOI.getStrikePrice());
                openInterest.setMaxCallChangeOI(highestCEOI.getChange());
                openInterest.setMaxCallOI(highestCEOI.getOpenInterest());
                openInterest.setMaxCallVolume(highestCEOI.getTotalTradedVolume());
                openInterest.setFirstResistance(highestCEOI.getStrikePrice());
                openInterest.setSecondResistance(secondHighestCEOI.getStrikePrice());
                openInterest.setCallAskPrice(highestCEOI.getAskPrice());
            }

            if (highestPEOI != null) {
                openInterest.setPutLtp(highestPEOI.getUnderlyingValue());
                openInterest.setPutStrikePrice(highestPEOI.getStrikePrice());
                openInterest.setMaxPutChangeOI(highestPEOI.getChange());
                openInterest.setMaxPutOI(highestPEOI.getOpenInterest());
                openInterest.setMaxPutVolume(highestPEOI.getTotalTradedVolume());
                openInterest.setFirstSupport(highestPEOI.getStrikePrice());
                openInterest.setSecondSupport(secondHighestPEOI.getStrikePrice());
                openInterest.setPutAskPrice(highestPEOI.getAskPrice());
            }

            //String strategy = findStrategyBasedOnPcrRatio(totOiPcr, dailyOpenInterestPCR);
            //dailyOpenInterestPCR.setMarket(strategy);
        }

        return openInterest;
    }

    public List<OpenInterest> equitiesOpenInterest(final String symbol, final String range) {
        //start blocking queue mechanism for failure cases
        startQueues();
        final List<OpenInterest> openInterests = new ArrayList<>();
        OpenInterestChainResponse openInterestChainResponse = remotecall(EQUITIES_OPEN_INTEREST, symbol, range);
        if (openInterestChainResponse != null) {
            //equities open interest
            openInterests.add(processOpenInterest(openInterestChainResponse, symbol, range));
        }
        return openInterests;
    }

    private OpenInterestChainResponse remotecall(String callerType, final String symbol, final String range) {
        OpenInterestChainResponse openInterestResponse = null;
        try {
            final String data = openInterestCaller.caller1(callerType, symbol);
            if (data != null && !data.isEmpty()) {
                openInterestResponse = new ObjectMapper().readValue(data, OpenInterestChainResponse.class);
            } else {
                log.info(" Added to the queue to try again ");
                retryQueue.add(new OpenInterestRequest(symbol, range));
            }
        } catch (Exception e) {
            log.error("Failed to get the data! Retrying again " + e.getMessage());
        }
        return openInterestResponse;
    }

    private class OpenInterestProducer implements Runnable {

        @Override
        public void run() {
            if (!retryQueue.isEmpty()) {
                final OpenInterestRequest request = retryQueue.remove();
                log.debug(" Running open interest request " + request);
                try {
                    final String data = openInterestCaller.caller1(EQUITIES_OPEN_INTEREST, request.getSymbol());
                    if (data != null && !data.isEmpty()) {
                        OpenInterestChainResponse response = new ObjectMapper().readValue(data, OpenInterestChainResponse.class);
                        OpenInterestResponse openInterestResponse = new OpenInterestResponse(response, request.getSymbol(), request.getRange());
                        responseQueue.offer(openInterestResponse);
                    } else {
                        //add again if failed
                        retryQueue.add(request);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private class OpenInterestConsumer implements Runnable {

        @Override
        public void run() {
            if (!responseQueue.isEmpty()) {
                log.debug(" Peeking response from queue ");
                OpenInterestResponse response = responseQueue.remove();
                processOpenInterest(response.getResponse(), response.getSymbol(), response.getRange());
            }
        }
    }

    @Data
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    private class OpenInterestResponse {
        private OpenInterestChainResponse response;
        private String symbol;
        private String range;
    }

}

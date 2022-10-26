package com.nse.services.open.interest.processor;

import com.nse.services.open.interest.json.OpenInterestChain;
import com.nse.services.open.interest.json.OpenInterestChainFiltered;
import com.nse.services.open.interest.json.OpenInterestChainJson;
import com.nse.services.open.interest.json.OpenInterestChainResponse;
import com.nse.services.open.interest.model.OpenInterestEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OpenInterestRangeProcessorService implements OpenInterestRangeProcessor {

    @Override
    public OpenInterestEntity processWithRange(OpenInterestChainResponse response, String symbol, String range) throws Exception {
        final OpenInterestEntity openInterestEntity = new OpenInterestEntity();
        openInterestEntity.setSymbol(symbol);
        OpenInterestChainFiltered filtered = response.getFiltered();
        if (filtered != null) {

            Double stockprice = Double.parseDouble(response.getRecords().getUnderlyingValue());
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

            openInterestEntity.setPcrOI(totOiPcr);
            totVolPcr = new Double(df.format(totVolPcr));

            openInterestEntity.setPcrVolume(totVolPcr);

            // 1. Get the CE with highest change in open interest
            List<OpenInterestChainJson> ceList = filtered.getData().stream()
                    .filter(s -> s.getCe() != null)
                    .map(m -> m.getCe())
                    .sorted(Comparator.comparingDouble(OpenInterestChainJson::getChangeinOpenInterest).reversed()).limit(1)
                    .collect(Collectors.toList());

            // 2. Check whether Ltp change is positive

            // Get the PE with highest change in open interest
            List<OpenInterestChainJson> peList = filtered.getData().stream()
                    .filter(f -> f.getPe() != null)
                    .map(m -> m.getPe())
                    .sorted(Comparator.comparingDouble(OpenInterestChainJson::getChangeinOpenInterest).reversed()).limit(1)
                    .collect(Collectors.toList());

            OpenInterestChainJson ce = ceList != null && ceList.size() > 0 ? ceList.get(0) : null;
            OpenInterestChainJson pe = peList != null && peList.size() > 0 ? peList.get(0) : null;

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            if (ce != null) {
                openInterestEntity.setExpiryDate(LocalDate.parse(ce.getExpiryDate(), formatter));
                openInterestEntity.setIdentifier(ce.getIdentifier());
                openInterestEntity.setCallLtp(ce.getLtp());
                openInterestEntity.setCallLtpChange(ce.getLtpChange());
                openInterestEntity.setCallStrikePrice(ce.getStrikePrice());
                openInterestEntity.setCallOpenInterest(ce.getOpenInterest());
                openInterestEntity.setCallChangeOI(ce.getChangeinOpenInterest());
                openInterestEntity.setCallPerChangeOI(ce.getPchangeinOpenInterest());
                openInterestEntity.setCallVolume(ce.getTotalTradedVolume());
                openInterestEntity.setCallImpliedVolatility(ce.getImpliedVolatility());
                openInterestEntity.setResistance(ce.getStrikePrice());
            }

            if (pe != null) {
                openInterestEntity.setExpiryDate(LocalDate.parse(ce.getExpiryDate(), formatter));
                openInterestEntity.setIdentifier(pe.getIdentifier());
                openInterestEntity.setPutLtp(pe.getLtp());
                openInterestEntity.setPutLtpChange(pe.getLtpChange());
                openInterestEntity.setPutStrikePrice(pe.getStrikePrice());
                openInterestEntity.setPutChangeOI(pe.getLtpChange());
                openInterestEntity.setPutOpenInterest(pe.getOpenInterest());
                openInterestEntity.setPutPerChangeOI(pe.getPchangeinOpenInterest());
                openInterestEntity.setPutVolume(pe.getTotalTradedVolume());
                openInterestEntity.setCallImpliedVolatility(pe.getImpliedVolatility());
                openInterestEntity.setSupport(pe.getStrikePrice());
            }

            //String strategy = findStrategyBasedOnPcrRatio(totOiPcr, dailyOpenInterestPCR);
            //dailyOpenInterestPCR.setMarket(strategy);
        }

        return openInterestEntity;
    }
}

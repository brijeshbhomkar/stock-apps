package com.nse.services.open.interest.processor;

import com.nse.services.open.interest.json.OpenInterestChainJson;
import com.nse.services.open.interest.json.OpenInterestChainResponse;
import com.nse.services.open.interest.model.OpenInterestEntity;
import lombok.extern.slf4j.Slf4j;
import com.nse.services.open.interest.json.OpenInterestChainFiltered;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OpenInterestProcessorService implements OpenInterestProcessor {

    @Override
    public OpenInterestEntity processWithoutRange(OpenInterestChainResponse response, String symbol) throws Exception {
        final OpenInterestEntity openInterestEntity = new OpenInterestEntity();
        openInterestEntity.setSymbol(symbol);
        OpenInterestChainFiltered filtered = response.getFiltered();
        if (filtered != null) {
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
                openInterestEntity.setCallAskPrice(ce.getAskPrice());
                openInterestEntity.setCallLtpChange(ce.getpChange());
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
                openInterestEntity.setPutImpliedVolatility(pe.getImpliedVolatility());
                openInterestEntity.setSupport(pe.getStrikePrice());
                openInterestEntity.setPutAskPrice(pe.getAskPrice());
                openInterestEntity.setPutLtpChange(pe.getpChange());
            }
        }

        return openInterestEntity;
    }
}

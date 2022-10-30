package com.nse.services.top.gainers.service;

import com.common.exception.ApplicationException;
import com.connector.groww.GrowwServiceConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.common.json.Items;
import com.nse.common.json.JsonData;
import com.nse.services.top.gainers.json.gainers.TopGainersJsonResponseWrapper;
import com.nse.services.top.gainers.model.TopGainer;
import com.nse.services.top.gainers.repository.TopGainerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TopGainerService {

    @Autowired
    private TopGainerRepository topGainersRepository;

    @Autowired
    private GrowwServiceConnector growwServicesConnector;

    public List<TopGainer> getTopGainersNifty100() throws ApplicationException {
        List<TopGainer> topGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY100", "TOP_GAINERS", 20);
        if (data != null) {
            List<TopGainer> topGainerList = convertJsonToEntity(data, "GIDXNIFTY100");
            topGainerList.stream().forEach(s -> {
                try {
                    topGainers.add(saveTopGainer(s));
                } catch (ApplicationException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        return topGainers;
    }

    private TopGainer saveTopGainer(final TopGainer newTopGainer) throws ApplicationException {
        TopGainer tmpGainer = null;
        try {
            Optional<TopGainer> oldTopGainer = topGainersRepository.findByNseSymbol(newTopGainer.getNseSymbol());
            if (oldTopGainer.isPresent()) {
                TopGainer previousGainer = oldTopGainer.get();
                if (Double.valueOf(newTopGainer.getPercChange()).compareTo(Double.valueOf(previousGainer.getPercChange())) != 0) {
                    topGainersRepository.delete(previousGainer);
                    topGainersRepository.save(newTopGainer);
                    tmpGainer = newTopGainer;
                } else {
                    tmpGainer = previousGainer;
                }
            } else {
                topGainersRepository.save(newTopGainer);
                tmpGainer = newTopGainer;
            }
        } catch (ApplicationException e) {
            throw new ApplicationException("Failed to get the top gainer by symbol " + newTopGainer.getNseSymbol());
        }
        return tmpGainer;
    }

    private List<TopGainer> convertJsonToEntity(final JsonData data, final String marketType) {
        return data.getItems().stream().map(s -> topGainerMapper(data.getTimestamp(), s, marketType)).collect(Collectors.toList());
    }

    private <R> TopGainer topGainerMapper(final long timestamp, final Items item, final String marketType) {
        final TopGainer topGainer = new TopGainer();
        topGainer.setDate(new Date(timestamp));
        topGainer.setCompanyName(item.getCompany().getCompanyName());
        topGainer.setEquityType(item.getCompany().getEquityType());
        topGainer.setNseSymbol(item.getCompany().getNseScriptCode());
        topGainer.setLtp(item.getStats().getLtp());
        topGainer.setYearHighPrice(item.getStats().getYearHighPrice());
        topGainer.setYearLowPrice(item.getStats().getYearLowPrice());
        Double high = Double.valueOf(item.getStats().getYearHighPrice());
        Double low = Double.valueOf(item.getStats().getYearLowPrice());
        Double perc = (high) / (high + low);
        DecimalFormat df = new DecimalFormat("#.##");
        topGainer.setPercChange(Double.valueOf(df.format(perc)));
        topGainer.setMarketType(marketType);
        return topGainer;
    }

    private JsonData makeApiCall(String marketType, String filterType, int size) throws ApplicationException {
        Optional<String> response = growwServicesConnector.connect(marketType, filterType, size);
        JsonData jsonData = new JsonData();
        try {
            TopGainersJsonResponseWrapper wrapper = new ObjectMapper().readValue(response.get(), TopGainersJsonResponseWrapper.class);
            if (wrapper != null && wrapper.getTopGainersJsonResponse() != null) {
                jsonData.setTimestamp(wrapper.getTimeStamp());
                jsonData.setItems(wrapper.getTopGainersJsonResponse().getTopGainersJson().getItems());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

    public List<TopGainer> getTopGainersNifty500() throws ApplicationException {
        List<TopGainer> topGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY500", "TOP_GAINERS", 20);
        if (data != null) {
            List<TopGainer> topGainerList = convertJsonToEntity(data, "GIDXNIFTY500");
            topGainerList.stream().forEach(s -> topGainers.add(topGainersRepository.save(s)));
        }
        return topGainers;
    }

    public List<TopGainer> getTopGainersSMCP100() throws ApplicationException {
        List<TopGainer> topGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFSMCP100", "TOP_GAINERS", 20);
        if (data != null) {
            List<TopGainer> topGainerList = convertJsonToEntity(data, "GIDXNIFSMCP100");
            topGainerList.stream().forEach(s -> topGainers.add(topGainersRepository.save(s)));
        }
        return topGainers;
    }

    public List<TopGainer> findTopGainersToday() throws ApplicationException {
        try {
            return topGainersRepository.findByDate(new Date());
        } catch (ApplicationException e) {
            throw new ApplicationException("Failed to get top gainers for the day");
        }
    }

    public List<TopGainer> getAllTopGainers() throws ApplicationException {

        //get nifty top 100
        getTopGainersNifty100();

        //get nifty top 500
        getTopGainersNifty500();

        //get smcp 100
        getTopGainersSMCP100();

        return topGainersRepository.findAll();
    }
}

package com.nse.services.top.gainers.service;

import com.common.exception.ApplicationException;
import com.connector.groww.GrowwConnector;
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
    private GrowwConnector growwConnector;

    public List<TopGainer> getTopGainersNifty100() throws ApplicationException {
        List<TopGainer> topGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY100", "TOP_GAINERS", 20);
        if (data != null) {
            List<TopGainer> topGainerList = convertJsonToEntity(data);
            topGainerList.stream().forEach(s -> topGainers.add(topGainersRepository.save(s)));
        }
        return topGainers;
    }

    private List<TopGainer> convertJsonToEntity(final JsonData data) {
        return data.getItems().stream().map(s -> topGainerMapper(data.getTimestamp(), s)).collect(Collectors.toList());
    }

    private <R> TopGainer topGainerMapper(final long timestamp, final Items item) {
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
        return topGainer;
    }

    private JsonData makeApiCall(String marketType, String filterType, int size) throws ApplicationException {
        Optional<String> response = growwConnector.connect(marketType, filterType, size);
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
            List<TopGainer> topGainerList = convertJsonToEntity(data);
            topGainerList.stream().forEach(s -> topGainers.add(topGainersRepository.save(s)));
        }
        return topGainers;
    }

    public List<TopGainer> getTopGainersSMCP100() throws ApplicationException {
        List<TopGainer> topGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFSMCP100", "TOP_GAINERS", 20);
        if (data != null) {
            List<TopGainer> topGainerList = convertJsonToEntity(data);
            topGainerList.stream().forEach(s -> topGainers.add(topGainersRepository.save(s)));
        }
        return topGainers;
    }

}

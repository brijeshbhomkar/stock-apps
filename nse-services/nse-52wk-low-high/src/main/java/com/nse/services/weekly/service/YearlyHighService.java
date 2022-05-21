package com.nse.services.weekly.service;

import com.common.exception.ApplicationException;
import com.connector.groww.GrowwConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.common.json.Items;
import com.nse.common.json.JsonData;
import com.nse.services.weekly.json.YearlyHighStockJsonResponseWrapper;
import com.nse.services.weekly.model.YearlyHighStock;
import com.nse.services.weekly.repository.YearlyHighStockRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class YearlyHighService {

    @Autowired
    private YearlyHighStockRepository yearlyHighStockRepository;

    @Autowired
    private GrowwConnector growwConnector;

    public List<YearlyHighStock> getYearlyNifty100HighStock() throws ApplicationException {
        List<YearlyHighStock> yearlyHighStocks = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY100", "YEARLY_HIGH", 20);
        if (data != null) {
            List<YearlyHighStock> yearlyHighStockList = convertJsonToEntity(data);
            yearlyHighStockList.stream().forEach(s -> yearlyHighStocks.add(yearlyHighStockRepository.save(s)));
        }
        return yearlyHighStocks;
    }

    public List<YearlyHighStock> getYearlyNifty500HighStock() throws ApplicationException {
        List<YearlyHighStock> yearlyHighStocks = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY500", "YEARLY_HIGH", 20);
        if (data != null) {
            List<YearlyHighStock> yearlyHighStockList = convertJsonToEntity(data);
            yearlyHighStockList.stream().forEach(s -> yearlyHighStocks.add(yearlyHighStockRepository.save(s)));
        }
        return yearlyHighStocks;
    }

    public List<YearlyHighStock> getYearlySMCP100HighStock() throws ApplicationException {
        List<YearlyHighStock> topGainers = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFSMCP100", "YEARLY_HIGH", 20);
        if (data != null) {
            List<YearlyHighStock> yearlyHighStockList = convertJsonToEntity(data);
            yearlyHighStockList.stream().forEach(s -> topGainers.add(yearlyHighStockRepository.save(s)));
        }
        return topGainers;
    }

    private List<YearlyHighStock> convertJsonToEntity(final JsonData data) {
        return data.getItems().stream().map(s -> topGainerMapper(data.getTimestamp(), s)).collect(Collectors.toList());
    }

    private <R> YearlyHighStock topGainerMapper(final long timestamp, final Items item) {
        final YearlyHighStock yearlyHighStock = new YearlyHighStock();
        yearlyHighStock.setDate(new Date(timestamp));
        yearlyHighStock.setCompanyName(item.getCompany().getCompanyName());
        yearlyHighStock.setEquityType(item.getCompany().getEquityType());
        yearlyHighStock.setNseSymbol(item.getCompany().getNseScriptCode());
        yearlyHighStock.setLtp(item.getStats().getLtp());
        yearlyHighStock.setYearHighPrice(item.getStats().getYearHighPrice());
        yearlyHighStock.setYearLowPrice(item.getStats().getYearLowPrice());
        Double high = Double.valueOf(item.getStats().getYearHighPrice());
        Double low = Double.valueOf(item.getStats().getYearLowPrice());
        Double perc = (high) / (high + low);
        DecimalFormat df = new DecimalFormat("#.##");
        yearlyHighStock.setPercChange(Double.valueOf(df.format(perc)));
        return yearlyHighStock;
    }

    private JsonData makeApiCall(String marketType, String filterType, int size) throws ApplicationException {
        Optional<String> response = growwConnector.connect(marketType, filterType, size);
        JsonData jsonData = new JsonData();
        try {
            YearlyHighStockJsonResponseWrapper wrapper = new ObjectMapper().readValue(response.get(), YearlyHighStockJsonResponseWrapper.class);
            if (wrapper != null && wrapper.getYearlyHighStockJsonResponse() != null) {
                jsonData.setTimestamp(wrapper.getTimeStamp());
                jsonData.setItems(wrapper.getYearlyHighStockJsonResponse().getYearlyHighStockJson().getItems());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

}

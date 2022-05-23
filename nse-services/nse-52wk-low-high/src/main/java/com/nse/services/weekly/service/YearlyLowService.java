package com.nse.services.weekly.service;

import com.common.exception.ApplicationException;
import com.connector.groww.GrowwServiceConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nse.common.json.Items;
import com.nse.common.json.JsonData;
import com.nse.services.weekly.json.YearlyLowStockJsonResponseWrapper;
import com.nse.services.weekly.model.YearlyLowStock;
import com.nse.services.weekly.repository.YearlyLowStockRepository;
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
public class YearlyLowService {

    @Autowired
    private YearlyLowStockRepository yearlyLowStockRepository;

    @Autowired
    private GrowwServiceConnector growwServicesConnector;

    public List<YearlyLowStock> getYearlyNifty100LowStock() throws ApplicationException {
        List<YearlyLowStock> yearlyLowStocks = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY100", "YEARLY_LOW", 20);
        if (data != null) {
            List<YearlyLowStock> yearlyHighStockList = convertJsonToEntity(data);
            yearlyHighStockList.stream().forEach(s -> yearlyLowStocks.add(yearlyLowStockRepository.save(s)));
        }
        return yearlyLowStocks;
    }

    public List<YearlyLowStock> getYearlyNifty500LowStock() throws ApplicationException {
        List<YearlyLowStock> yearlyLowStocks = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFTY500", "YEARLY_LOW", 20);
        if (data != null) {
            List<YearlyLowStock> yearlyHighStockList = convertJsonToEntity(data);
            yearlyHighStockList.stream().forEach(s -> yearlyLowStocks.add(yearlyLowStockRepository.save(s)));
        }
        return yearlyLowStocks;
    }

    public List<YearlyLowStock> getYearlySMCP100LowStock() throws ApplicationException {
        List<YearlyLowStock> yearlyLowStocks = new ArrayList<>();
        JsonData data = makeApiCall("GIDXNIFSMCP100", "YEARLY_LOW", 20);
        if (data != null) {
            List<YearlyLowStock> yearlyLowStockList = convertJsonToEntity(data);
            yearlyLowStockList.stream().forEach(s -> yearlyLowStocks.add(yearlyLowStockRepository.save(s)));
        }
        return yearlyLowStocks;
    }

    private List<YearlyLowStock> convertJsonToEntity(final JsonData data) {
        return data.getItems().stream().map(s -> yearlyLowStockMapper(data.getTimestamp(), s)).collect(Collectors.toList());
    }

    private <R> YearlyLowStock yearlyLowStockMapper(final long timestamp, final Items item) {
        final YearlyLowStock yearlyHighStock = new YearlyLowStock();
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
        Optional<String> response = growwServicesConnector.connect(marketType, filterType, size);
        JsonData jsonData = new JsonData();
        try {
            YearlyLowStockJsonResponseWrapper wrapper = new ObjectMapper().readValue(response.get(), YearlyLowStockJsonResponseWrapper.class);
            if (wrapper != null && wrapper.getYearlyLowStockJsonResponse() != null) {
                jsonData.setTimestamp(wrapper.getTimeStamp());
                jsonData.setItems(wrapper.getYearlyLowStockJsonResponse().getYearlyLowStockJson().getItems());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonData;
    }

}

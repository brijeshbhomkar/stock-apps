package com.stocks.data.access.api.groww.service;

import com.common.exception.ApplicationException;
import com.connector.groww.GrowwDataAccessConnector;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.stocks.data.access.api.groww.json.StockData;
import com.stocks.data.access.api.groww.json.StockDataWrapper;
import com.stocks.data.access.api.groww.model.StockTick;
import com.stocks.data.access.api.groww.repository.GrowwDataAccessRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Log4j2
@Service
public class GrowwDataAccessService {

    @Autowired
    private GrowwDataAccessConnector growwDataAccessConnector;

    @Autowired
    private GrowwDataAccessRepository growwDataAccessRepository;

    public List<StockTick> getStockTicks(String symbol, String type, String interval, int intervalVal) throws ApplicationException {
        List<StockTick> stockTicks = null;
        StockDataWrapper wrapper = null;
        Optional<String> response = growwDataAccessConnector.connect(symbol, type, interval, intervalVal);
        if (response.isPresent()) {
            try {
                wrapper = new ObjectMapper().readValue(response.get(), StockDataWrapper.class);
                if (wrapper != null) {
                    List<StockData> stockData = wrapper.getCandles();
                    stockTicks = stockData.stream().map(s -> convertJsonToEntity(s, symbol)).collect(Collectors.toList());
                    stockTicks.stream().forEach(s -> growwDataAccessRepository.save(s));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stockTicks;
    }

    private <R> StockTick convertJsonToEntity(final StockData stockData, String symbol) {
        final StockTick tick = new StockTick();
        tick.setTimstamp(stockData.getTimestamp());
        tick.setSymbol(symbol);
        tick.setOpen(stockData.getOpen());
        tick.setHigh(stockData.getHigh());
        tick.setLow(stockData.getLow());
        tick.setClose(stockData.getClose());
        tick.setVolume(stockData.getVolume());
        return tick;
    }

}

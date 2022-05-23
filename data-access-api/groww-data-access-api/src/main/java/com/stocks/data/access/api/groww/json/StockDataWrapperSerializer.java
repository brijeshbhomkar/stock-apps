package com.stocks.data.access.api.groww.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StockDataWrapperSerializer extends JsonDeserializer {

    @Override
    public Object deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        ObjectCodec oc = jsonParser.getCodec();
        JsonNode node = oc.readTree(jsonParser);
        JsonNode candles = node.get("candles");
        List<StockData> ohlcList = new ArrayList<>();
        for (int i = 0; i < candles.size(); i++) {
            JsonNode tick = candles.get(i);
            final StockData stockData = new StockData();
            stockData.setTimestamp(tick.get(0).asText());
            stockData.setOpen(tick.get(1).asDouble());
            stockData.setHigh(tick.get(2).asDouble());
            stockData.setLow(tick.get(3).asDouble());
            stockData.setClose(tick.get(4).asDouble());
            stockData.setVolume(tick.get(5).asDouble());
            ohlcList.add(stockData);
        }
        return new StockDataWrapper(ohlcList);
    }
}

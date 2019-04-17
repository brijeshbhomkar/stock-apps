package com.data.service.dataservice.response.parsing;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.data.service.dataservice.json.StockData;
import com.data.service.dataservice.response.StockDataResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class StockDataDeserializer extends JsonDeserializer<StockDataResponse> {

	@Override
	public StockDataResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		Long declines = node.get("declines").asLong();
		Long noChange = node.get("noChange").asLong();
		JsonNode data = node.get("data");
		List<StockData> dataList = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			JsonNode record = data.get(i);
			final StockData stock = new StockData();
			stock.setSymbol(record.get("symbol").asText());
			stock.setSeries(record.get("series").asText());
			stock.setPrice(getDouble(record.get("iep").asText()));
			stock.setChange(getDouble(record.get("chn").asText()));
			stock.setPerChange(getDouble(record.get("perChn").asText()));
			stock.setPrevClose(getDouble(record.get("pCls").asText()));
			stock.setTradedQuantity(getDouble(record.get("trdQnty").asText()));
			stock.setMarketCap(getDouble(record.get("mktCap").asText()));
			stock.setYearHigh(getDouble(record.get("yHigh").asText()));
			stock.setYearLow(getDouble(record.get("yLow").asText()));
			stock.setSumValue(getDouble(record.get("sumVal").asText()));
			dataList.add(stock);
		}
		return new StockDataResponse(declines, noChange, dataList);
	}

	private Double getDouble(final String value) {
		NumberFormat format = NumberFormat.getInstance(Locale.US);
		Number number = 0;
		try {
			number = format.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return Double.valueOf(number.doubleValue());
	}
}

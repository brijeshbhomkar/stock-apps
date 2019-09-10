package com.data.service.dataservice.response.parsing;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.data.service.dataservice.json.StockWatch;
import com.data.service.dataservice.response.StockWatchResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class StockWatchDeserializer extends JsonDeserializer<StockWatchResponse> {

	@Override
	public StockWatchResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		Double declines = node.get("declines").asDouble();
		JsonNode data = node.get("data");
		List<StockWatch> dataList = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			JsonNode record = data.get(i);
			final StockWatch stock = new StockWatch();
			stock.setSymbol(record.get("symbol").asText());
			stock.setOpen(getDouble(record.get("open").asText()));
			stock.setHigh(getDouble(record.get("high").asText()));
			stock.setLow(getDouble(record.get("low").asText()));
			stock.setLtP(getDouble(record.get("ltP").asText()));
		//	stock.setPtsC(getDouble(record.get("ptsC").asText()));
		//	stock.setPer(getDouble(record.get("per").asText()));
			stock.setTrdVol(getDouble(record.get("trdVol").asText()));
			stock.setTrdVolM(getDouble(record.get("trdVolM").asText()));
//			stock.setNtP(getDouble(record.get("ntP").asText()));
//			stock.setmVal(getDouble(record.get("mVal").asText()));
//			stock.setWkhi(getDouble(record.get("wkhi").asText()));
//			stock.setWklo(getDouble(record.get("wklo").asText()));
//			stock.setWklo(getDouble(record.get("wklo").asText()));
			stock.setPreviousClose(getDouble(record.get("previousClose").asText()));
			//stock.setDayEndClose(getDouble(record.get("dayEndClose").asText()));
			//stock.setIislPtsChange(getDouble(record.get("iislPtsChange").asText()));
			//stock.setIislPercChange(getDouble(record.get("iislPercChange").asText()));
			dataList.add(stock);
		}
		return new StockWatchResponse(declines, dataList);
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

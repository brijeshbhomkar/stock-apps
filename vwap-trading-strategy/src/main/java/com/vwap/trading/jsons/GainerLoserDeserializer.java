package com.vwap.trading.jsons;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.vwap.trading.models.GainerLoser;

public class GainerLoserDeserializer extends JsonDeserializer<GainerLoserResponse> {

	@Override
	public GainerLoserResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		JsonNode data = node.get("data");
		List<GainerLoser> dataList = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			final JsonNode record = data.get(i);
			final GainerLoser stock = new GainerLoser();
			stock.setSymbol(record.get("symbol").asText());
			stock.setOpen(getDouble(record.get("openPrice").asText()));
			stock.setHigh(getDouble(record.get("highPrice").asText()));
			stock.setLow(getDouble(record.get("lowPrice").asText()));
			stock.setLtp(getDouble(record.get("ltp").asText()));
			stock.setPrev(getDouble(record.get("previousPrice").asText()));
			stock.setPerChange(getDouble(record.get("netPrice").asText()));
			//stock.setClose(getDouble(record.get("netPrice").asText()));
			stock.setTradedQty(getDouble(record.get("tradedQuantity").asText()));
			stock.setQtyValueInLacs(getDouble(record.get("turnoverInLakhs").asText()));
			dataList.add(stock);
		}
		return new GainerLoserResponse(dataList);
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

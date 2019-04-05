package com.data.service.dataservice.response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		JsonNode data = node.get("data");
		List<StockData> dataList = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			JsonNode record = data.get(i);
			for (int j = 0; j < record.size(); j++) {
				// JsonNode data = tick.get(j);
				final StockData stock = new StockData();
				stock.setSymbol(record.get("symbol").asText());
				stock.setSeries(record.get("series").asText());

				dataList.add(stock);
			}
		}
		return new StockDataResponse(dataList);
	}

}

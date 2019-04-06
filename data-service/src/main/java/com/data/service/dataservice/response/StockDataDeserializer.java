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
		Long declines = node.get("declines").asLong();
		Long noChange = node.get("noChange").asLong();
		JsonNode data = node.get("data");
		List<StockData> dataList = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			JsonNode record = data.get(i);
			//for (int j = 0; j < record.size(); j++) {
				// JsonNode data = tick.get(j);
				final StockData stock = new StockData();
				stock.setSymbol(record.get("symbol").asText());
				stock.setSeries(record.get("series").asText());
				stock.setPrice(record.get("iep").asText());
				stock.setChange(record.get("chn").asText());
				stock.setPerChange(record.get("perChn").asText());
				stock.setPrevClose(record.get("pCls").asText());
				stock.setTradedQuantity(record.get("trdQnty").asText());
				stock.setMarketCap(record.get("mktCap").asText());
				stock.setYearHigh(record.get("yHigh").asText());
				stock.setYearLow(record.get("yLow").asText());
				stock.setSumValue(record.get("sumVal").asText());
				stock.setSumQuantity(record.get("sumQnty").asText());
				stock.setFinQnty(record.get("finQnty").asText());
				stock.setSumfinQnty(record.get("sumfinQnty").asText());
				dataList.add(stock);

				// {"symbol":"CIPLA","series":"EQ","xDt":"-","caAct":"-","iep":"531.40","chn":"10.40",
				// "perChn":"2.00","pCls":"521.00","trdQnty":"14,636","iVal":"77.78","mktCap":"26,445.46",
				// "yHigh":"678.45","yLow":"483.75","sumVal":"2,368.08","sumQnty":"3,76,536","finQnty":"14,636",
				// "sumfinQnty":"4,18,357"}

			}
		//}
		return new StockDataResponse(declines, noChange, dataList);
	}

}

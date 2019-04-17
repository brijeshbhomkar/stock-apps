package com.data.service.dataservice.response.parsing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.data.service.dataservice.json.Candles;
import com.data.service.dataservice.json.Ohlc;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class CandlesDeserializer extends JsonDeserializer<Candles> {

	@Override
	public Candles deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		JsonNode candles = node.get("candles");
		List<Ohlc> ohlcList = new ArrayList<>();
		for (int i = 0; i < candles.size(); i++) {
			JsonNode tick = candles.get(i);
			for (int j = 0; j < tick.size(); j++) {
				//JsonNode data = tick.get(j);
				final Ohlc ohlc = new Ohlc();
				ohlc.setTime(tick.get(0).asText());
				ohlc.setOpen(tick.get(1).asLong());
				ohlc.setHigh(tick.get(2).asLong());
				ohlc.setLow(tick.get(3).asLong());
				ohlc.setClose(tick.get(4).asLong());
				ohlc.setVolume(tick.get(5).asLong());
				ohlcList.add(ohlc);
			}
		}
		return new Candles(ohlcList);
	}

}

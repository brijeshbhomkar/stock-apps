package com.vwap.trading.jsons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		List<Candle> ohlcList = new ArrayList<>();
		for (int i = 0; i < candles.size(); i++) {
			JsonNode tick = candles.get(i);
			final Candle ohlc = new Candle();
			ohlc.setDate(tick.get(0).asText());
			ohlc.setOpen(tick.get(1).asDouble());
			ohlc.setHigh(tick.get(2).asDouble());
			ohlc.setLow(tick.get(3).asDouble());
			ohlc.setClose(tick.get(4).asDouble());
			ohlc.setVolume(tick.get(5).asDouble());
			ohlcList.add(ohlc);
		}
		return new Candles(ohlcList);
	}
}

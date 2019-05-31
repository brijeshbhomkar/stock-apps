package com.charting.json;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.charting.pojo.Symbol;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class SymbolDeserializer extends JsonDeserializer<SymbolResponse> {
	
	@Override
	public SymbolResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		JsonNode symbols = node.get("symbols");
		List<Symbol> symList = new ArrayList<Symbol>();
		for (int i = 0; i < symbols.size(); i++) {
			final Symbol symbol = new Symbol();
			JsonNode sym = symbols.get(i);
			symbol.setSymbolId(sym.get("symbolId").asLong());
			symbol.setSymbol(sym.get("symbol").asText());
			symbol.setExchange(sym.get("exchange").asText());
			symList.add(symbol);
		}
		return new SymbolResponse(symList);
	}

}

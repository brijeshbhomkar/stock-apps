package com.data.service.dataservice.response.parsing;

import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.data.service.dataservice.json.VolumeGainer;
import com.data.service.dataservice.response.VolumeGainerResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class VolumeGainerDeserializer extends JsonDeserializer<VolumeGainerResponse> {

	@Override
	public VolumeGainerResponse deserialize(JsonParser jsonParser, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		JsonNode data = node.get("data");
		List<VolumeGainer> volumes = new ArrayList<>();
		for (int i = 0; i < data.size(); i++) {
			JsonNode record = data.get(i);
			final VolumeGainer volumeGainer = new VolumeGainer();
			volumeGainer.setSymbol(record.get("sym").asText());
			volumeGainer.setName(record.get("name").asText());
			volumeGainer.setTurnoverLakhs(getDouble(record.get("turn_lkh").asText()));
			volumeGainer.setWeekOneAvgVolume(getDouble(record.get("week1a").asText()));
			volumeGainer.setWeekTwoAvgVolume(getDouble(record.get("week2a").asText()));
			volumeGainer.setWeekOneAvgVolumeChange(getDouble(record.get("week1vc").asText()));
			volumeGainer.setWeekTwoAvgVolumeChange(getDouble(record.get("week2vc").asText()));
			volumeGainer.setTurnOverCrs(getDouble(record.get("value").asText()));
			volumeGainer.setLtp(getDouble(record.get("ltp").asText()));
			volumeGainer.setNetChangePercentage(getDouble(record.get("netpr").asText()));
			volumes.add(volumeGainer);
		}
		return new VolumeGainerResponse(volumes);
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

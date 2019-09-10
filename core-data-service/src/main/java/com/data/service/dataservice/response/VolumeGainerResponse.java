package com.data.service.dataservice.response;

import java.io.Serializable;
import java.util.List;

import com.data.service.dataservice.json.VolumeGainer;
import com.data.service.dataservice.response.parsing.VolumeGainerDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = VolumeGainerDeserializer.class)
public class VolumeGainerResponse implements Serializable {

	private static final long serialVersionUID = -5831935084384351828L;

	@JsonProperty(value = "data")
	private List<VolumeGainer> data;
	
	public VolumeGainerResponse() {}

	public VolumeGainerResponse(final List<VolumeGainer> volumeGainers) {
		this.data = volumeGainers;
	}

	public List<VolumeGainer> getData() {
		return data;
	}

	public void setData(List<VolumeGainer> data) {
		this.data = data;
	}
}

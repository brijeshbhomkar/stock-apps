package com.vwap.trading.jsons;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vwap.trading.models.GainerLoser;

@JsonDeserialize(using = GainerLoserDeserializer.class)
public class GainerLoserResponse implements Serializable {

	private static final long serialVersionUID = -2540028591185376066L;
	
	private List<GainerLoser> data;
	
	public GainerLoserResponse() {}
	
	public GainerLoserResponse(final List<GainerLoser> data) {
		this.data = data;
	}

	public List<GainerLoser> getData() {
		return data;
	}

	public void setData(List<GainerLoser> data) {
		this.data = data;
	}
}

package com.data.service.dataservice.response;

import java.io.Serializable;
import java.util.List;

import com.data.service.dataservice.json.GainerLoser;
import com.data.service.dataservice.response.parsing.GainerLoserDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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

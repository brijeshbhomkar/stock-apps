package com.data.service.dataservice.response;

import java.io.Serializable;
import java.util.List;

import com.data.service.dataservice.json.PreOpen;
import com.data.service.dataservice.response.parsing.PreOpenDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = PreOpenDeserializer.class)
public class PreOpenResponse implements Serializable {
	private static final long serialVersionUID = -849223705162170891L;

	private long declines;
	private long noChange;
	private List<PreOpen> data;

	public PreOpenResponse() {
	}

	public PreOpenResponse(final long declines, final long noChange, final List<PreOpen> data) {
		this.data = data;
		this.declines = declines;
		this.noChange = noChange;
	}

	public long getDeclines() {
		return declines;
	}

	public void setDeclines(long declines) {
		this.declines = declines;
	}

	public long getNoChange() {
		return noChange;
	}

	public void setNoChange(long noChange) {
		this.noChange = noChange;
	}

	public List<PreOpen> getData() {
		return data;
	}

	public void setData(List<PreOpen> data) {
		this.data = data;
	}

}

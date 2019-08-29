package com.vwap.trading.steps;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.vwap.trading.models.GainerLoser;

@Component
public class StockReader implements Serializable {

	private static final long serialVersionUID = 2091011267374879935L;

	private long id;
	private Map<String, List<GainerLoser>> maps;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Map<String, List<GainerLoser>> getMaps() {
		return maps;
	}

	public void setMaps(Map<String, List<GainerLoser>> maps) {
		this.maps = maps;
	}

}

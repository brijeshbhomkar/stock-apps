package com.charting.views;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

@ManagedBean
public class ChartingView implements Serializable {

	private static final long serialVersionUID = 4521276778065012560L;

	private String symbolId = "NIFTY50";

	@PostConstruct
	public void init() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		symbolId = (String) sessionMap.get("symbolId");
	}

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}

	@PreDestroy
	public void destroy() {
		symbolId = null;
	}
}

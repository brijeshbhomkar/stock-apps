package com.data.service.dataservice.response;

import java.io.Serializable;
import java.util.List;

import com.data.service.dataservice.entity.Symbol;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolResponse implements Serializable {

	private static final long serialVersionUID = -1131808760754270748L;
	
	private List<Symbol> symbols;
	
	public SymbolResponse() {}
	
	public SymbolResponse(final List<Symbol> symbols) {
		this.symbols = symbols;
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<Symbol> symbols) {
		this.symbols = symbols;
	}
}

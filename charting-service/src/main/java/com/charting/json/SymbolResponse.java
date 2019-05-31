package com.charting.json;

import java.io.Serializable;
import java.util.List;

import com.charting.pojo.Symbol;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = SymbolDeserializer.class)
public class SymbolResponse implements Serializable {

	private static final long serialVersionUID = 8929426532810923412L;

	private List<Symbol> symbols;

	public SymbolResponse() {
	}

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

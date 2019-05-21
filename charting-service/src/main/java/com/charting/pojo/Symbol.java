package com.charting.pojo;

import java.io.Serializable;
import java.util.List;

import com.charting.json.SymbolDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = SymbolDeserializer.class)
public class Symbol implements Serializable {

	private static final long serialVersionUID = -3752643643240322276L;

	private List<Symbol> symbols;
	
	private String symbolId;
	
	private String symbolName;

	public Symbol() {
	}

	public Symbol(final List<Symbol> symbols) {
		this.symbols = symbols;
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<Symbol> symbols) {
		this.symbols = symbols;
	}

	public String getSymbolName() {
		return symbolName;
	}

	public void setSymbolName(String symbolName) {
		this.symbolName = symbolName;
	}

	public String getSymbolId() {
		return symbolId;
	}

	public void setSymbolId(String symbolId) {
		this.symbolId = symbolId;
	}
}

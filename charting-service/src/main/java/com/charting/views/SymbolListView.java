package com.charting.views;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.charting.pojo.Symbol;
import com.charting.services.SymbolService;

@ManagedBean
public class SymbolListView implements Serializable {

	private static final long serialVersionUID = 4661936682192914234L;

	private Symbol symbol;

	private List<Symbol> symbols;

	@ManagedProperty("#{symbolService}")
	private SymbolService symbolService;

	@PostConstruct
	void init() {
		symbols = symbolService.getSymbols();
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

	public void setSymbols(List<Symbol> symbols) {
		this.symbols = symbols;
	}

	public SymbolService getSymbolService() {
		return symbolService;
	}

	public void setSymbolService(SymbolService symbolService) {
		this.symbolService = symbolService;
	}

	public Symbol getSymbol() {
		return symbol;
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
	}

	public void showChart() {
		System.out.println("selected item " + symbol.getSymbol());
	}
}

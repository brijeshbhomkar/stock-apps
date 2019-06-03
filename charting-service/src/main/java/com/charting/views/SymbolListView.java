package com.charting.views;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import com.charting.pojo.Symbol;
import com.charting.services.SymbolService;

@ManagedBean
public class SymbolListView implements Serializable {

	private static final long serialVersionUID = 4661936682192914234L;

	private Symbol selectedSymbol;

	private List<Symbol> symbols;

	@ManagedProperty("#{symbolService}")
	private SymbolService symbolService;

	@PostConstruct
	void init() {
		if (symbols == null) {
			symbols = symbolService.getSymbols();
		}
	}

	public List<Symbol> getSymbols() {
		return symbols;
	}

	public Symbol getSelectedSymbol() {
		return selectedSymbol;
	}

	public void setSelectedSymbol(Symbol selectedSymbol) {
		this.selectedSymbol = selectedSymbol;
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

	public void showChart() {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		Map<String, Object> sessionMap = externalContext.getSessionMap();
		sessionMap.put("symbolId", selectedSymbol.getSymbol());
		try {
			externalContext.redirect("./dashboard.xhtml");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Symbol getSymbolById(Long id) {
		for (Symbol symbol : symbols) {
			if (id.equals(symbol.getSymbolId())) {
				return symbol;
			}
		}
		return null;
	}
}

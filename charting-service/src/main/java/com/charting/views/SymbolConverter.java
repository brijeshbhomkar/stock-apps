package com.charting.views;

import javax.el.ValueExpression;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.charting.pojo.Symbol;

@FacesConverter(value = "symbolConverter")
public class SymbolConverter implements Converter {

	public Object getAsObject(FacesContext ctx, UIComponent uiComponent, String symbolId) {
		ValueExpression vex = ctx.getApplication().getExpressionFactory().createValueExpression(ctx.getELContext(),
				"#{symbolListView}", SymbolListView.class);

		SymbolListView symbols = (SymbolListView) vex.getValue(ctx.getELContext());
		return symbols.getSymbolById(Long.valueOf(symbolId));
	}

	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object symbol) {
		return ((Symbol) symbol).getSymbolId().toString();
	}

}

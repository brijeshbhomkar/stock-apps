package com.data.service.dataservice.util;

import java.util.HashMap;
import java.util.Map;

public final class StockSymbolMapper {
	
	private static final Map<String, String> symbolMapper = new HashMap<>();
	
	private static void init() {
		symbolMapper.put("IOC", "415745");
		symbolMapper.put("SUNPHARMA", "857857");
		symbolMapper.put("HINDALCO", "348929");
		symbolMapper.put("COALINDIA", "5215745");
		symbolMapper.put("VEDL", "784129");
		symbolMapper.put("SBIN", "779521");
		symbolMapper.put("ICICIBANK", "1270529");
		symbolMapper.put("CIPLA", "177665");
		symbolMapper.put("HINDPETRO", "359937");
		symbolMapper.put("BPCL", "134657");
		symbolMapper.put("NTPC", "2977281");
		symbolMapper.put("BHEL", "112129");
		symbolMapper.put("ONGC", "633601");
		symbolMapper.put("TATAMOTORS", "884737");
		symbolMapper.put("ITC", "424961");
		symbolMapper.put("POWERGRID", "3834113");
		symbolMapper.put("TATASTEEL", "895745");
		symbolMapper.put("GAIL", "1207553");
		symbolMapper.put("YESBANK", "3050241");
		symbolMapper.put("WIPRO", "969473");
		symbolMapper.put("JSWSTEEL", "3001089");
		symbolMapper.put("INFRATEL", "7458561");
		symbolMapper.put("BHARTIARTL", "2714625");
		symbolMapper.put("IBULHSGFIN", "7712001");
		symbolMapper.put("BAJAJ-AUTO", "4267265");
		symbolMapper.put("KOTAKBANK", "492033");
		symbolMapper.put("TITAN", "897537");
		symbolMapper.put("HEROMOTOCO", "345089");
		symbolMapper.put("BAJAJFINSV", "4268801");
		symbolMapper.put("HDFCBANK", "341249");
		symbolMapper.put("UPL", "2889473");
		symbolMapper.put("RELIANCE", "738561");
		symbolMapper.put("AXISBANK", "1510401");
		symbolMapper.put("MARUTI", "2815745");
		symbolMapper.put("LT", "2939649");
		symbolMapper.put("ASIANPAINT", "60417");
		symbolMapper.put("M&M", "519937");
		symbolMapper.put("ITC", "424961");
		symbolMapper.put("COALINDIA", "5215745");
		symbolMapper.put("GRASIM", "315393");
		symbolMapper.put("VEDL", "784129");
		symbolMapper.put("INDUSINDBK", "1346049");
		symbolMapper.put("DRREDDY", "225537");
		symbolMapper.put("CIPLA", "177665");
		symbolMapper.put("GAIL", "1207553");
		symbolMapper.put("BAJFINANCE", "81153");
		symbolMapper.put("WIPRO", "969473");
		symbolMapper.put("HDFC", "340481");
		symbolMapper.put("INFY", "408065");
		symbolMapper.put("EICHERMOT", "232961");
		symbolMapper.put("ULTRACEMCO", "2952193");
		symbolMapper.put("TATASTEEL", "895745");
		symbolMapper.put("IOC", "415745");
		symbolMapper.put("HCLTECH", "1850625");
		symbolMapper.put("ZEEL", "975873");
		symbolMapper.put("HINDUNILVR", "356865");
		symbolMapper.put("ADANIPORTS", "3861249");
		symbolMapper.put("TECHM", "3465729");
		symbolMapper.put("BRITANNIA", "140033");
		symbolMapper.put("TCS", "2953217");

	}

	public static Map<String, String> getSymbolMapper() {
		init();
		return symbolMapper;
	}
}

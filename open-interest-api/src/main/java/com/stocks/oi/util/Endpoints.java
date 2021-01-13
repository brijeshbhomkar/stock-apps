package com.stocks.oi.util;

public interface Endpoints {
    String SLIDE_IN_PRICE_RISE_IN_OI = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/slideInPriceRiseInOI.json";
    String RISE_IN_PRICE_RISE_IN_OI = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/riseInPriceRiseInOI.json";
    String RISE_IN_PRICE_SLIDE_IN_OI = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/riseInPriceSlideInOI.json";
    String SLIDE_IN_PRICE_SLIDE_IN_OI = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/slideInPriceSlideInOI.json";
    String TOP_POSITIVE_OI_CHANGE_DATA = "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/topPositiveOIChangeData.json";
    String MARKET_ORDER_BOOK = "https://www.nseindia.com/api/quote-equity?symbol=TATAMOTORS&section=trade_info";
    String MARKET_TOP_20_CONTRACTS = "https://www.nseindia.com/api/liveEquity-derivatives?index=top20_contracts";
    String INDICES_OPEN_INTEREST = "https://www.nseindia.com/api/option-chain-indices?symbol=";
    String EQUITIES_OPEN_INTEREST = "https://www.nseindia.com/api/option-chain-equities?symbol=";
    String MASTER_QUOTE = "https://www.nseindia.com/api/master-quote";
}

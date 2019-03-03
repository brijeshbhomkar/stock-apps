package com.stocks.data.service.utils;

import lombok.Builder;

@Builder
public class EndpointGenerator {
    private static String protocol = "https";
    private static String url = "www.nseindia.com/live_market/dynaContent/live_analysis/pre_open";
    private String json = "";

    public static String niftyFiftyStock(final String protocol, final String url, final String json) {
        return new ClientUrl(protocol, url, json).toString();
    }
}

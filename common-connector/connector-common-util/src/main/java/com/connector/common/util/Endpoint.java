package com.connector.common.util;

import java.util.HashMap;
import java.util.Map;

public enum Endpoint {
    SLIDE_IN_PRICE_RISE_IN_OI("PRICE-SLIDE-OI-RISE", "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/slideInPriceRiseInOI.json"),
    RISE_IN_PRICE_RISE_IN_OI("PRICE-RISE-OI-RISE", "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/riseInPriceRiseInOI.json"),
    RISE_IN_PRICE_SLIDE_IN_OI("PRICE-RISE-OI-SLIDE", "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/riseInPriceSlideInOI.json"),
    SLIDE_IN_PRICE_SLIDE_IN_OI("PRICE-SLIDE-OI-SLIDE", "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/slideInPriceSlideInOI.json"),
    TOP_POSITIVE_OI_CHANGE_DATA("TOP-POSITIVE-OI-CHANGE", "https://www1.nseindia.com/live_market/dynaContent/live_analysis/oi_spurts/topPositiveOIChangeData.json"),
    INDICES_OPEN_INTEREST("INDICES-OPEN-INTEREST", "https://www.nseindia.com/api/option-chain-indices?symbol="),
    EQUITIES_OPEN_INTEREST("EQUITIES-OPEN-INTEREST", "https://www.nseindia.com/api/option-chain-equities?symbol="),
    MASTER_QUOTE("MASTER-QUOTE", "https://www.nseindia.com/api/master-quote"),
    PARTICIPANT_URL("FII-DII-PARTICIPANT", "https://www1.nseindia.com/content/nsccl/fao_participant_oi_");

    private static final Map<String, Endpoint> BY_LABEL = new HashMap<>();

    static {
        for (Endpoint e : values()) {
            BY_LABEL.put(e.label, e);
        }
    }

    public static Endpoint valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }

    private String label;
    private String endpoint;

    Endpoint(String label, String endpoint) {
        this.label = label;
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getLabel() {
        return label;
    }
}

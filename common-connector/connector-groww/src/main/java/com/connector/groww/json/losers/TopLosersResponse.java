package com.connector.groww.json.losers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties("categoryResponseMap")
public class TopLosersResponse implements Serializable {

    @JsonProperty(value = "TOP_LOSERS")
    private TopLosers topLosers;

    public TopLosers getTopLosers() {
        return topLosers;
    }

    public void setTopLosers(TopLosers topLosers) {
        this.topLosers = topLosers;
    }
}

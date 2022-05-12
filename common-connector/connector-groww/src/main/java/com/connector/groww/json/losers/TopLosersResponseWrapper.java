package com.connector.groww.json.losers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties
public class TopLosersResponseWrapper implements Serializable {

    @JsonProperty(value = "categoryResponseMap")
    private TopLosersResponse responseMap;

    @JsonProperty(value = "timeStamp")
    private long timeStamp;

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public TopLosersResponse getResponseMap() {
        return responseMap;
    }

    public void setResponseMap(TopLosersResponse responseMap) {
        this.responseMap = responseMap;
    }
}

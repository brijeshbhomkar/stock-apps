package com.stocks.oi.response.oi.chain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.io.Serializable;

@JsonDeserialize(using = OpenInterestChainDeserializer.class)
public class OpenInterestChainResponse implements Serializable {

    @JsonProperty(value = "records")
    private OpenInterestChainRecords records;

    @JsonProperty(value = "filtered")
    private OpenInterestChainFiltered filtered;

    public OpenInterestChainFiltered getFiltered() {
        return filtered;
    }

    public OpenInterestChainRecords getRecords() {
        return records;
    }

    public void setFiltered(OpenInterestChainFiltered filtered) {
        this.filtered = filtered;
    }

    public void setRecords(OpenInterestChainRecords records) {
        this.records = records;
    }
}

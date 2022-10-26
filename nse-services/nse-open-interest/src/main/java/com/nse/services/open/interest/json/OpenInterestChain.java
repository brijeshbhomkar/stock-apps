package com.nse.services.open.interest.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenInterestChain implements Serializable {

    @JsonProperty(value = "strikePrice")
    private Double strikePrice;

    @JsonProperty(value = "expiryDate")
    private String expiryDate;

    @JsonProperty(value = "PE")
    private OpenInterestChainJson pe;

    @JsonProperty(value = "CE")
    private OpenInterestChainJson ce;

    public Double getStrikePrice() {
        return strikePrice;
    }

    public OpenInterestChainJson getCe() {
        return ce;
    }

    public OpenInterestChainJson getPe() {
        return pe;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setPe(OpenInterestChainJson pe) {
        this.pe = pe;
    }

    public void setCe(OpenInterestChainJson ce) {
        this.ce = ce;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public void setStrikePrice(Double strikePrice) {
        this.strikePrice = strikePrice;
    }
}

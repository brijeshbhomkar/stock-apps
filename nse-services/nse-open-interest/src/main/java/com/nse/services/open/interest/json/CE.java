package com.nse.services.open.interest.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CE implements Serializable  {

    @JsonProperty(value = "totOI")
    private String totOI;

    @JsonProperty(value = "totVol")
    private String totVol;

    public String getTotOI() {
        return totOI;
    }

    public void setTotOI(String totOI) {
        this.totOI = totOI;
    }

    public String getTotVol() {
        return totVol;
    }

    public void setTotVol(String totVol) {
        this.totVol = totVol;
    }
}

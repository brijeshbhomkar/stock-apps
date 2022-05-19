package com.nse.services.top.gainers.json.common;

import com.connector.groww.json.common.Company;
import com.connector.groww.json.common.Stats;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
public class Items implements Serializable {

    @JsonProperty(value = "gsin")
    private String gsin;

    @JsonProperty(value = "company")
    private Company company;

    @JsonProperty(value = "stats")
    private Stats stats;

}

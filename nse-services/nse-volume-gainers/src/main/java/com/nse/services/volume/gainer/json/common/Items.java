package com.nse.services.volume.gainer.json.common;

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

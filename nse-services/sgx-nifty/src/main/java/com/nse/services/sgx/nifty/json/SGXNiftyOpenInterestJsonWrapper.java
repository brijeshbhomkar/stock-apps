package com.nse.services.sgx.nifty.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class SGXNiftyOpenInterestJsonWrapper implements Serializable {

    @JsonProperty(value = "data")
    private List<SGXNiftyOpenInterestJson> data;
}

package com.nse.services.top.gainers.json.gainers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties("categoryResponseMap")
public class TopGainersJsonResponse implements Serializable {

    @JsonProperty(value = "TOP_GAINERS")
    private TopGainersJson topGainersJson;

}

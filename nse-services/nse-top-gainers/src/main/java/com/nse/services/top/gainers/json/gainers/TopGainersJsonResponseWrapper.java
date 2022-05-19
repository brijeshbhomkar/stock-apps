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
@JsonIgnoreProperties
public class TopGainersJsonResponseWrapper implements Serializable {

    @JsonProperty(value = "categoryResponseMap")
    private TopGainersJsonResponse topGainersJsonResponse;

    @JsonProperty(value = "timeStamp")
    private long timeStamp;

}

package com.nse.services.volume.gainer.json.gainers;

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
public class VolumeGainerJsonResponseWrapper implements Serializable {

    @JsonProperty(value = "categoryResponseMap")
    private VolumeGainerJsonResponse volumeGainerJsonResponse;

    @JsonProperty(value = "timeStamp")
    private long timeStamp;

}

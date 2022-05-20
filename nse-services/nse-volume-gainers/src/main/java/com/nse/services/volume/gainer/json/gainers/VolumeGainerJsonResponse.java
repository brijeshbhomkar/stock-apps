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
@JsonIgnoreProperties("categoryResponseMap")
public class VolumeGainerJsonResponse implements Serializable {

    @JsonProperty(value = "TRADED_BY_VOLUME")
    private VolumeGainersJson volumeGainerJson;

}

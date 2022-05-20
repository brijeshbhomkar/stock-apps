package com.nse.services.volume.gainer.json.gainers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nse.services.volume.gainer.json.common.Items;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties
public class VolumeGainersJson implements Serializable {

    @JsonProperty(value = "items")
    private Items[] items;

    public List<com.nse.services.volume.gainer.json.common.Items> getItems() {
        return Arrays.asList(items);
    }

    public void setItems(Items[] items) {
        this.items = items;
    }
}

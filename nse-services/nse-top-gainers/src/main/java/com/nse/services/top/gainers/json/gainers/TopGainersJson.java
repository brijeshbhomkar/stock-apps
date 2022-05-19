package com.nse.services.top.gainers.json.gainers;

import com.connector.groww.json.common.Items;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties
public class TopGainersJson implements Serializable {

    @JsonProperty(value = "items")
    private Items[] items;

    public List<Items> getItems() {
        return Arrays.asList(items);
    }

    public void setItems(Items[] items) {
        this.items = items;
    }
}

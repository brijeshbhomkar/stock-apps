package com.nse.services.weekly.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.nse.common.json.Items;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@JsonIgnoreProperties
public class YearlyLowStockJson implements Serializable {

    @JsonProperty(value = "items")
    private Items[] items;

    public List<Items> getItems() {
        return items != null && items.length > 0 ? Arrays.asList(items) : new ArrayList<>();
    }

    public void setItems(Items[] items) {
        this.items = items;
    }
}

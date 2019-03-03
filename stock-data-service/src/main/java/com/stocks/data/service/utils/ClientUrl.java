package com.stocks.data.service.utils;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.io.Serializable;

@Value
@AllArgsConstructor
public class ClientUrl implements Serializable  {
    private String protocol;
    private String url;
    private String json;

    @Override
    public String toString() {
        return "protocol" + "://" + url + "/" + json + ".json";
    }
}

package com.nse.services.top.gainers.json.common;

import com.connector.groww.json.common.Items;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
public class JsonData implements Serializable {

    private long timestamp;
    private List<Items> items;

}

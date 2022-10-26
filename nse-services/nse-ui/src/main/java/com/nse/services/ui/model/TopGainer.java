package com.nse.services.ui.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class TopGainer implements Serializable {

    private Date date;

    private String companyName;

    private String nseSymbol;

    private String equityType;

    private double ltp;

    private double yearHighPrice;

    private double yearLowPrice;

    private double percChange;
}

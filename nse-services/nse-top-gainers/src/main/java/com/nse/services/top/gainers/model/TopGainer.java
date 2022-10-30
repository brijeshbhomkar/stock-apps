package com.nse.services.top.gainers.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TOP_GAINER")
public class TopGainer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Temporal(TemporalType.DATE)
    private Date date;

    private String companyName;

    private String nseSymbol;

    private String equityType;

    private double ltp;

    private double yearHighPrice;

    private double yearLowPrice;

    private double percChange;

    private String marketType;
}

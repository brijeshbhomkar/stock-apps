package com.nse.services.volume.gainer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "VOLUME_GAINER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VolumeGainer implements Serializable {

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
}

package com.nse.services.sgx.nifty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SGX_NIFTY_OI")
public class SGXNiftyOpenInterest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private double putOpenInterest;
    private double putChangePercentage;
    private double callOpenInterest;
    private double putTotalVolume;
    private String category;

}

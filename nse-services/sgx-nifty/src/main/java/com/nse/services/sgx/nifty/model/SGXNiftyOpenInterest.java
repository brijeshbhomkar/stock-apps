package com.nse.services.sgx.nifty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "SGX_NIFTY_OI")
public class SGXNiftyOpenInterest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long totalVolume;
    private Date date;
    private long openInterest;
    private String type;

}

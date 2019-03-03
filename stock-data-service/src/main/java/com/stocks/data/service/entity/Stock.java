package com.stocks.data.service.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The stock entity.
 */
@Data
@Entity
@Table(name = "stock")
public class Stock implements Serializable {

    private Long id;
    private Symbol symbol;
    private String price;
    private String change;
    private String perChange;
    private String prevClose;
    private String quantity;
    private String week52Low;
    private String week52High;

    public Stock() {
    }
}

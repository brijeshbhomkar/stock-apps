package com.stocks.data.service.entity;

import javax.persistence.*;

/**
 * The stock entity.
 */
@Entity
@Table(name = "stock")
public class Stock extends AuditModel {

    @Id
    @GeneratedValue(generator = "stock_generator")
    @SequenceGenerator(
            name = "stock_generator",
            sequenceName = "stock_sequence",
            initialValue = 1000
    )
    private Long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "series")
    private String series;

    @Column(name = "price")
    private String price;

    @Column(name = "change")
    private String change;

    @Column(name = "st_per_change")
    private String perChange;

    @Column(name = "prev_close")
    private String prevClose;

    @Column(name = "quantity")
    private String quantity;

    @Column(name = "52_week_low")
    private String week52Low;

    @Column(name = "52_week_high")
    private String week52High;

    public Stock() {
        //this is for jpa
    }
}

package com.stocks.data.service.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The symbol entity.
 */
@Data
@Table(name = "symbol")
public class Symbol extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "exchange")
    private String exchange;

    @Column(name = "name")
    private String name;


    public Symbol() {
    }
}

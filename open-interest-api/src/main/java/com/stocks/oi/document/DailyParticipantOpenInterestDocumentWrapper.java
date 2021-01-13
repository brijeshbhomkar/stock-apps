package com.stocks.oi.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;

@Document(collection = "PARTICIPANTS-OI")
public class DailyParticipantOpenInterestDocumentWrapper implements Serializable {

    @MongoId
    private String date;
    private DailyParticipantOpenInterestDocument client;
    private DailyParticipantOpenInterestDocument dii;
    private DailyParticipantOpenInterestDocument fii;
    private DailyParticipantOpenInterestDocument pro;
    private DailyParticipantOpenInterestDocument total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DailyParticipantOpenInterestDocument getClient() {
        return client;
    }

    public void setClient(DailyParticipantOpenInterestDocument client) {
        this.client = client;
    }

    public DailyParticipantOpenInterestDocument getDii() {
        return dii;
    }

    public void setDii(DailyParticipantOpenInterestDocument dii) {
        this.dii = dii;
    }

    public DailyParticipantOpenInterestDocument getFii() {
        return fii;
    }

    public void setFii(DailyParticipantOpenInterestDocument fii) {
        this.fii = fii;
    }

    public DailyParticipantOpenInterestDocument getPro() {
        return pro;
    }

    public void setPro(DailyParticipantOpenInterestDocument pro) {
        this.pro = pro;
    }

    public DailyParticipantOpenInterestDocument getTotal() {
        return total;
    }

    public void setTotal(DailyParticipantOpenInterestDocument total) {
        this.total = total;
    }
}

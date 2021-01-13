package com.stocks.oi.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;

@Document(collection = "PARTICIPANTS-VOLUME")
public class DailyParticipantVolumeDocumentWrapper implements Serializable {

    @MongoId
    private String date;
    private DailyParticipantVolumeDocument client;
    private DailyParticipantVolumeDocument dii;
    private DailyParticipantVolumeDocument fii;
    private DailyParticipantVolumeDocument pro;
    private DailyParticipantVolumeDocument total;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public DailyParticipantVolumeDocument getClient() {
        return client;
    }

    public void setClient(DailyParticipantVolumeDocument client) {
        this.client = client;
    }

    public DailyParticipantVolumeDocument getDii() {
        return dii;
    }

    public void setDii(DailyParticipantVolumeDocument dii) {
        this.dii = dii;
    }

    public DailyParticipantVolumeDocument getFii() {
        return fii;
    }

    public void setFii(DailyParticipantVolumeDocument fii) {
        this.fii = fii;
    }

    public DailyParticipantVolumeDocument getPro() {
        return pro;
    }

    public void setPro(DailyParticipantVolumeDocument pro) {
        this.pro = pro;
    }

    public DailyParticipantVolumeDocument getTotal() {
        return total;
    }

    public void setTotal(DailyParticipantVolumeDocument total) {
        this.total = total;
    }
}

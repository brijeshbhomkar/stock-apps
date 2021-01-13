package com.stocks.oi.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Document(collection = "PRICE-UP-OI-DOWN")
public class DailyRiseInPriceSlideInOIDocumentWrapper implements OpenInterestDocument {

    @MongoId
    private String date;

    private List<DailyRiseInPriceSlideInOIDocument> documents;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DailyRiseInPriceSlideInOIDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DailyRiseInPriceSlideInOIDocument> documents) {
        this.documents = documents;
    }
}

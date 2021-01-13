package com.stocks.oi.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.Date;
import java.util.List;

@Document(collection = "PRICE-DOWN-OI-UP")
public class DailySlideInPriceRiseInOIDocumentWrapper implements OpenInterestDocument {

    @MongoId
    private String date;
    private List<DailySlideInPriceRiseInOIDocument> documents;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DailySlideInPriceRiseInOIDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DailySlideInPriceRiseInOIDocument> documents) {
        this.documents = documents;
    }
}

package com.stocks.oi.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "PRICE-DOWN-OI-DOWN")
public class DailySlideInPriceSlideInOIDocumentWrapper implements OpenInterestDocument{

    @MongoId
    private String date;
    private List<DailySlideInPriceSlideInOIDocument> documents;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DailySlideInPriceSlideInOIDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DailySlideInPriceSlideInOIDocument> documents) {
        this.documents = documents;
    }
}

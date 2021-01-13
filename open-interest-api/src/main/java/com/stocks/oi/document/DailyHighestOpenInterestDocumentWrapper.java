package com.stocks.oi.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.List;

@Document(collection = "HIGHEST-OI")
public class DailyHighestOpenInterestDocumentWrapper implements Serializable {

    @MongoId
    private String date;

    private List<DailyHighestOpenInterestDocument> documents;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DailyHighestOpenInterestDocument> getDocuments() {
        return documents;
    }

    public void setDocuments(List<DailyHighestOpenInterestDocument> documents) {
        this.documents = documents;
    }
}

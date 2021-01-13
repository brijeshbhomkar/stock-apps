package com.stocks.oi.document;

import org.bson.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.List;

public class OpenInterestChainDocumentWrapper implements Serializable  {

    @MongoId
    private String expiryDate;

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    private List<org.bson.Document> chains;

    public List<Document> getChains() {
        return chains;
    }

    public void setChains(List<Document> chains) {
        this.chains = chains;
    }
}

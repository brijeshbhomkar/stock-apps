package com.stocks.oi.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;

@Document(collection = "PRICE-UP-OI-UP")
public class DailyRiseInPriceRiseInOIDocumentWrapper implements OpenInterestDocument {

    @MongoId
    private String date;

    private List<DailyRiseInPriceRiseInOIDocument> bullish;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<DailyRiseInPriceRiseInOIDocument> getBullish() {
        return bullish;
    }

    public void setBullish(List<DailyRiseInPriceRiseInOIDocument> bullish) {
        this.bullish = bullish;
    }
}

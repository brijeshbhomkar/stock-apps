package com.stocks.oi.response;

import java.io.Serializable;
import java.util.List;

public class MasterQuotes implements Serializable {
    private List<String> quotes;

    public List<String> getQuotes() {
        return quotes;
    }

    public void setQuotes(List<String> quotes) {
        this.quotes = quotes;
    }
}

package com.nse.services.ui.service;

import com.nse.services.ui.model.TopGainer;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class TopGainerService {

    private String BASE_URL = "http://localhost:2005/api/nse/topgainer/";

    public List<TopGainer> listTopGainers() {
        return null;
    }

    public List<TopGainer> getNifty100TopGainers() {
        final String url = BASE_URL + "nifty" + "/" + "100";
        TopGainer gainer = new TopGainer();
        gainer.setCompanyName("ICICIBANK");
        gainer.setDate(new Date());
        gainer.setNseSymbol("ICICIBANK");
        gainer.setEquityType("NIFTY 100");
        return Arrays.asList(gainer);
    }

    public List<TopGainer> getNifty500TopGainers() {
        return null;
    }

    public List<TopGainer> getNiftySMCP100TopGainers() {
        return null;
    }
}

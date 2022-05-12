package com.nse.application.scheduler;

import com.connector.groww.json.common.Items;
import com.connector.groww.json.common.JsonData;
import com.nse.application.services.TopLoserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class TopLoserServiceScheduler {

    private long interval = 0;

    @Autowired
    private TopLoserService topLoserService;

    public long getInterval() {
        this.interval += 10000;
        return this.interval;
    }

    public void start(final String marketType, final String filterType, final int size) {
        System.out.println(" Calling remote service for market type " + marketType);
        List<JsonData> data = topLoserService.connect(marketType, filterType, size);
        data.stream().forEach(s -> {
            List<Items> items = s.getItems();
            if (!CollectionUtils.isEmpty(items)) {
                items.stream().forEach(j -> System.out.println(j.toString()));
            }
        });
    }
}

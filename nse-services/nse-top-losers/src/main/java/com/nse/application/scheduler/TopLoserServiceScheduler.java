package com.nse.application.scheduler;

import com.nse.application.services.TopLoserService;
import com.nse.common.json.Items;
import com.nse.common.json.JsonData;
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
        List<String> data = topLoserService.connect(marketType, filterType, size);
//        data.stream().forEach(s -> {
//            List<Items> items = s.getItems();
//            if (!CollectionUtils.isEmpty(items)) {
//                items.stream().forEach(j -> System.out.println(j.toString()));
//            }
//        });
    }
}

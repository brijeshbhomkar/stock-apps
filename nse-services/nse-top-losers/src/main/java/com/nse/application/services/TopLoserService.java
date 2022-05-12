package com.nse.application.services;

import com.common.exception.ApplicationException;
import com.connector.groww.GrowwConnector;
import com.connector.groww.json.common.JsonData;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopLoserService {

    @Bean
    private GrowwConnector growwConnector() {
        return new GrowwConnector();
    }

    public List<JsonData> connect(final String marketType, final String filterType, final int parseInt) {
        try {
            Optional<List<JsonData>> optionalJsonData = growwConnector().connect(marketType, filterType, parseInt);
            if (optionalJsonData.isPresent()) {
                return optionalJsonData.get();
            }
        } catch (ApplicationException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}

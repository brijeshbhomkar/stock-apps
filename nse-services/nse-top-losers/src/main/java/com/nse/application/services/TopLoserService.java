package com.nse.application.services;

import com.common.exception.ApplicationException;
import com.connector.groww.GrowwServiceConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopLoserService {

    @Bean
    private GrowwServiceConnector growwConnector() {
        return new GrowwServiceConnector();
    }

    public List<String> connect(final String marketType, final String filterType, final int parseInt) {
        try {
            Optional<String> optionalJsonData = growwConnector().connect(marketType, filterType, parseInt);
            if (optionalJsonData.isPresent()) {
                return null; // optionalJsonData.get();
            }
        } catch (ApplicationException exception) {
            exception.printStackTrace();
        }
        return null;
    }
}

package com.nse.services.weekly;

import com.connector.groww.GrowwConnector;
import com.nse.services.weekly.controller.YearlyLowHighController;
import com.nse.services.weekly.repository.YearlyHighStockRepository;
import com.nse.services.weekly.repository.YearlyLowStockRepository;
import com.nse.services.weekly.service.YearlyHighService;
import com.nse.services.weekly.service.YearlyLowService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableJpaRepositories
@ComponentScan(basePackageClasses = {YearlyLowHighController.class, GrowwConnector.class, YearlyHighStockRepository.class, YearlyLowStockRepository.class,
        YearlyLowService.class, YearlyHighService.class})
@SpringBootApplication
public class YearlyLowHighApplication {

    public static void main(String[] args) {
        SpringApplication.run(YearlyLowHighApplication.class, args);
    }
}

package com.nse.services.top.gainers;

import com.connector.groww.GrowwConnector;
import com.nse.services.top.gainers.controller.TopGainerController;
import com.nse.services.top.gainers.repository.TopGainerRepository;
import com.nse.services.top.gainers.service.TopGainerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableJpaRepositories
@ComponentScan(basePackageClasses = {TopGainerController.class, TopGainerService.class, TopGainerRepository.class, GrowwConnector.class})
@SpringBootApplication
public class TopGainersApplication {
    public static void main(String[] args) {
        SpringApplication.run(TopGainersApplication.class, args);
    }
}

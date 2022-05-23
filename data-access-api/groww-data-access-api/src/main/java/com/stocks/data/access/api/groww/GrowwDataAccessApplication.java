package com.stocks.data.access.api.groww;

import com.connector.groww.GrowwDataAccessConnector;
import com.stocks.data.access.api.groww.controller.GrowwDataAccessController;
import com.stocks.data.access.api.groww.repository.GrowwDataAccessRepository;
import com.stocks.data.access.api.groww.service.GrowwDataAccessService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan(basePackageClasses = {GrowwDataAccessController.class, GrowwDataAccessConnector.class, GrowwDataAccessRepository.class, GrowwDataAccessService.class})
@SpringBootApplication
public class GrowwDataAccessApplication {
    public static void main(String[] args) {
        SpringApplication.run(GrowwDataAccessApplication.class, args);
    }
}

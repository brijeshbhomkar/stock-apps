package com.nse.services.india.vix;

import com.connector.nse.vix.client.IndiaVIXClient;
import com.nse.services.india.vix.config.BatchConfig;
import com.nse.services.india.vix.config.JobScheduler;
import com.nse.services.india.vix.controller.IndiaVIXController;
import com.nse.services.india.vix.repository.IndiaVIXRepository;
import com.nse.services.india.vix.service.IndiaVIXService;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@EnableBatchProcessing
@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan(basePackageClasses = {IndiaVIXController.class, JobScheduler.class, BatchConfig.class, IndiaVIXRepository.class, IndiaVIXService.class, IndiaVIXClient.class})
@SpringBootApplication
public class IndiaVIXApplication {

    public static void main(String[] args) {
        SpringApplication.run(IndiaVIXApplication.class);
    }
}

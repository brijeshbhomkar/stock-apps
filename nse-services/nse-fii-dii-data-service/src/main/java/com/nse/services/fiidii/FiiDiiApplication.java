package com.nse.services.fiidii;

import com.nse.services.fiidii.controller.FIIDIIController;
import com.nse.services.fiidii.repository.FIIDIIRepository;
import com.nse.services.fiidii.service.FIIDIIService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackageClasses = {FIIDIIController.class,
        FIIDIIService.class, FIIDIIRepository.class})
public class FiiDiiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FiiDiiApplication.class);
    }
}

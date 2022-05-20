package com.nse.services.volume.gainer;

import com.connector.groww.GrowwConnector;
import com.nse.services.volume.gainer.controller.VolumeGainerController;
import com.nse.services.volume.gainer.repository.VolumeGainerRepository;
import com.nse.services.volume.gainer.service.VolumeGainerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@EnableJpaRepositories
@ComponentScan(basePackageClasses = {VolumeGainerController.class, GrowwConnector.class, VolumeGainerService.class, VolumeGainerRepository.class})
@SpringBootApplication
public class VolumeGainerApplication {

    public static void main(String[] args) {
        SpringApplication.run(VolumeGainerApplication.class, args);
    }
}

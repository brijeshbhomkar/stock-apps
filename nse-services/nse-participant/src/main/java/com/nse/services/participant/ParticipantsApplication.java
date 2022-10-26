package com.nse.services.participant;

import com.connector.nse.participant.client.ParticipantClient;
import com.nse.services.participant.controller.ParticipantController;
import com.nse.services.participant.repository.ParticipantRepository;
import com.nse.services.participant.service.ParticipantService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackageClasses = {ParticipantController.class,
        ParticipantService.class, ParticipantRepository.class, ParticipantClient.class})
@EnableSwagger2
public class ParticipantsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParticipantsApplication.class);
    }
}

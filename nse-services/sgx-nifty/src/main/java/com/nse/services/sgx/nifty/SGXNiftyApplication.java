package com.nse.services.sgx.nifty;

import com.connector.sgx.nifty.SGXNiftyOptionConnectorClient;
import com.nse.services.sgx.nifty.controller.SGXNiftyController;
import com.nse.services.sgx.nifty.repository.SGXNiftyOpenInterestRepository;
import com.nse.services.sgx.nifty.service.SGXNiftyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = {MongoDataAutoConfiguration.class, MongoAutoConfiguration.class})
@ComponentScan(basePackageClasses = {SGXNiftyController.class, SGXNiftyOpenInterestRepository.class, SGXNiftyService.class, SGXNiftyOptionConnectorClient.class})
@SpringBootApplication
public class SGXNiftyApplication {

    public static void main(String[] args) {
        SpringApplication.run(SGXNiftyApplication.class);
    }
}

package com.data.service.dataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class DataServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataServiceApplication.class, args);
	}

}

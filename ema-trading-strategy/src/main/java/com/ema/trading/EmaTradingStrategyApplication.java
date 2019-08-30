package com.ema.trading;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
@EnableBatchProcessing
public class EmaTradingStrategyApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmaTradingStrategyApplication.class, args);
	}

}

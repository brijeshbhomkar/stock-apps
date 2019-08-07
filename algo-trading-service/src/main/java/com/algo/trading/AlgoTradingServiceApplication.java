package com.algo.trading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class AlgoTradingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoTradingServiceApplication.class, args);
	}

}

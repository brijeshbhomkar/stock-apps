package com.stocks.oi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

//commented rabbit mq
@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.stocks.oi.mongo.repository")
@EnableScheduling
@EnableRetry
public class OpenInterestApiApplication {

	static {
		System.setProperty("jdk.tls.trustNameService", "true");
	}
	public static void main(String[] args) {
		SpringApplication.run(OpenInterestApiApplication.class, args);
	}

}

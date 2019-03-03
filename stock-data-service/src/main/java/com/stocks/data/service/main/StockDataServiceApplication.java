package com.stocks.data.service.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.stocks.data.service.*")
@EnableJpaAuditing
public class StockDataServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(StockDataServiceApplication.class, args);
    }

}


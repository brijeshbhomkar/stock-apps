package nse.services.open.interest;

import com.connector.nse.openinterest.client.OpenInterestClient;
import nse.services.open.interest.controller.OpenInterestController;
import nse.services.open.interest.repository.OpenInterestRepository;
import nse.services.open.interest.service.OpenInterestScheduler;
import nse.services.open.interest.service.OpenInterestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaRepositories
@EnableAutoConfiguration(exclude = {MongoDataAutoConfiguration.class, MongoAutoConfiguration.class})
@ComponentScan(basePackageClasses = {OpenInterestController.class, OpenInterestService.class,
        OpenInterestRepository.class, OpenInterestClient.class, OpenInterestScheduler.class})
@EnableScheduling
@EnableRetry
@SpringBootApplication
public class OpenInterestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenInterestApplication.class, args);
    }
}

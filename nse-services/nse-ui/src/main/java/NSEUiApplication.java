import com.nse.services.ui.controller.NavigationController;
import com.nse.services.ui.controller.TopGainerController;
import com.nse.services.ui.service.TopGainerService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
@ComponentScan(basePackageClasses = {NavigationController.class, TopGainerController.class, TopGainerService.class})
@SpringBootApplication
public class NSEUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(NSEUiApplication.class, args);
    }
}

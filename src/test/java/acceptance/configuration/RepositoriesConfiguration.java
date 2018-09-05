package acceptance.configuration;

import com.racalbalb.demo.adapters.InMemoryDriverRepository;
import com.racalbalb.demo.repository.DriverRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RepositoriesConfiguration {

    @Bean
    @Scope("cucumber-glue") // l'instance n'est valable que pour un scenario
    public DriverRepository driverRepository() {
        return new InMemoryDriverRepository();
    }
}

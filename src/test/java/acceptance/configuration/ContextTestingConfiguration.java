package acceptance.configuration;

import cucumber.api.java8.En;
import org.springframework.test.context.ContextConfiguration;

/**
 * Cette classe permet de faire le mapping
 * entre Spring et Cucumber
 */
@ContextConfiguration(classes = {
        RepositoriesConfiguration.class
})
public class ContextTestingConfiguration implements En {
}

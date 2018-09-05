package acceptance;

import com.racalbalb.demo.domain.Driver;
import com.racalbalb.demo.repository.DriverRepository;
import cucumber.api.PendingException;
import cucumber.api.java8.En;
import io.cucumber.datatable.DataTable;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class DriverSteps implements En {
    public DriverSteps() {
    }

    public DriverSteps(DriverRepository driverRepository) {
        Given("^drivers exist:$", (DataTable dataTable) -> {
            List<Map<String, String>> dataMaps = dataTable.asMaps(String.class, String.class);
            dataMaps.forEach(dataMap -> {
                Driver d = new Driver(Long.parseLong(dataMap.get("id")), dataMap.get("firstname"), dataMap.get("lastname"));
                driverRepository.save(d);
                Assert.assertEquals(d, driverRepository.findAll());
            });

        });
        Given("^as new <driver>$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        Then("^add my profile into the system$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^modify it$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
        And("^delete it$", () -> {
            // Write code here that turns the phrase above into concrete actions
            throw new PendingException();
        });
    }
}

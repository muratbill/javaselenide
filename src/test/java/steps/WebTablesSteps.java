package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import pages.WebTablesPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class WebTablesSteps {
    private WebTablesPage webTablesPage;

    @Before
    public void setup(){
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // Get properties
        String appUrl = properties.getProperty("APP_URL");
        String browser = properties.getProperty("BROWSER", "CHROME");
        boolean headless = properties.getProperty("HEADLESS", "N").equalsIgnoreCase("Y");

        // Set Selenide configuration
        Configuration.browser = browser;
        Configuration.headless = headless;

        // Initialize WebTablesPage with the URL from config.properties
        webTablesPage = new WebTablesPage(appUrl);

        // Open the WebTablesPage
        webTablesPage.open();
    }

    @Step("Opening the web tables page")
    @Severity(SeverityLevel.NORMAL)
    @Given("I open the web tables page")
    public void iOpenTheWebTablesPage() {
        webTablesPage.open();
        webTablesPage.clickWebTables();
    }
    @Step("Adding a new row with the provided data")
    @When("I add a new row with the below data")
    @Severity(SeverityLevel.CRITICAL)
    public void iAddANewRowWithTheBelowData(List<Map<String, String>> tableData) {
        Map<String, String> data = tableData.get(0);
        webTablesPage.addBtn();
        webTablesPage.addTableData(data.get("firstName"), data.get("lastName"), data.get("age"), data.get("email"), data.get("salary"), data.get("department"));
    }
    @Step("Verifying the added row in the table")
    @Then("I should see the added row in the table")
    @Severity(SeverityLevel.NORMAL)
    public void iShouldSeeTheAddedRowInTheTable() {
        List<String> expectedData = List.of("Can", "Bar", "23", "cb@example.com", "12330", "Sales");
        List<String> actualData = webTablesPage.getRowData(4);
        Assertions.assertThat(actualData).containsExactlyElementsOf(expectedData);
    }
    @Step("Editing the last row with the provided data")
    @When("I edit the last row with the below data")
    @Severity(SeverityLevel.CRITICAL)
    public void iEditTheLastRowWithTheBelowData(List<Map<String, String>> tableData) {
        Map<String, String> data = tableData.get(0);
        webTablesPage.editRow(4, data.get("firstName"), data.get("lastName"), data.get("age"), data.get("email"), data.get("salary"), data.get("department"));
    }
    @Step("Verifying the edited row in the table")
    @Then("I should see the edited row in the table")
    @Severity(SeverityLevel.NORMAL)
    public void iShouldSeeTheEditedRowInTheTable() {
        List<String> expectedData = List.of("Karl", "Marx", "30", "km@example.com", "17400", "Engineering");
        List<String> actualData = webTablesPage.getRowData(4);
        Assertions.assertThat(actualData).containsExactlyElementsOf(expectedData);
    }
}

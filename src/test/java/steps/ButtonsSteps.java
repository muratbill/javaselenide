package steps;
import com.codeborne.selenide.Configuration;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import pages.ButtonsPage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;

public class ButtonsSteps {
    private ButtonsPage buttonsPage;
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


        buttonsPage = new ButtonsPage(appUrl);


        buttonsPage.open();
    }
    @Description("Open the web tables page")
    @Severity(SeverityLevel.BLOCKER)
    @Step("I open the buttons page")
    @Given("I open the buttons page")
    public void iOpenTheButtonsPage(){
      buttonsPage.open();
      buttonsPage.clickButton();
    }

    @Step("Click the dynamic button")
    @When("I click the dynamic button")
    @Severity(SeverityLevel.NORMAL)
    public void iClickTheDynamicButton(){
        buttonsPage.clickMeButton();
    }

    @Step("You should see the message")
    @Then("I should see the dynamic click message")
    @Severity(SeverityLevel.CRITICAL)
    public void iShouldSeeTheDynamicCLickMessage(){
        assertThat(buttonsPage.isMessageDisplayed()).isTrue();
        String expectedMessage = "You have done a dynamic click";
        assertThat(buttonsPage.getMessageText()).isEqualTo(expectedMessage);
        
    }


}

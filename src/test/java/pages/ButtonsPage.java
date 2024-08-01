package pages;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
public class ButtonsPage extends BasePage {

    public SelenideElement clickButton = $(By.xpath("//span[contains(text(),'Buttons')]"));

    public SelenideElement clickMeButton =  $(By.xpath("//button[text()='Click Me']"));
    public SelenideElement message = $("#dynamicClickMessage");

    public ButtonsPage(String pageUrl) {
        super(pageUrl);
    }

    public void clickButton() {
        clickButton.shouldBe(visible, enabled).click();
    }

    public void clickMeButton(){
        clickMeButton.shouldBe(visible,enabled).click();
    }

    // Method to verify if the message is displayed
    public boolean isMessageDisplayed() {
        return message.shouldBe(visible).isDisplayed();
    }

    // Method to get the message text
    public String getMessageText() {
        return message.getText();
    }
}


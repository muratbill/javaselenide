package tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;
public class ButtonsPageTest extends BaseTest {
    @BeforeMethod
    public void before(){
        pageManager.buttonspage.open();

    }
    @Test
    public void clickButton(){
        pageManager.buttonspage.clickButton();
        pageManager.buttonspage.clickMeButton();
        assertThat(pageManager.buttonspage.isMessageDisplayed()).isTrue();
        String expectedMessage = "You have done a dynamic click";
        assertThat(pageManager.buttonspage.getMessageText()).isEqualTo(expectedMessage);
    }

}

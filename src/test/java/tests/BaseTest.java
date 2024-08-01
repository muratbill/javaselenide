package tests;
import com.codeborne.selenide.Selenide;
import driver.DriverFactory;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import common.PageManager;
public class BaseTest {
    protected PageManager pageManager;
    protected SoftAssert softAssert;
    @BeforeClass
    public void setUp() {
        pageManager = new PageManager();
        softAssert = new SoftAssert();
    }
    @BeforeMethod
    public void openDriver() {
        Selenide.open();
    }

    @AfterMethod
    public void clearCookies() {
        DriverFactory.clearCookies();
    }

    @AfterClass
    public void tearDown() {
        DriverFactory.close();
    }
}

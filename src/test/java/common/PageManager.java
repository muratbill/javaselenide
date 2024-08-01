package common;
import driver.DriverFactory;
import pages.ButtonsPage;
import pages.WebTablesPage;

public class PageManager {

    public ButtonsPage buttonspage;
    public WebTablesPage webTablesPage;
    public PageManager() {

        DriverFactory.initDriver();

        buttonspage = PageFactory.buildButtonsPage();
        webTablesPage = PageFactory.buildWebTablesPage();


    }
}

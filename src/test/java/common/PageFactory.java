package common;

import pages.ButtonsPage;
import pages.WebTablesPage;

public class PageFactory {

    public static ButtonsPage buildButtonsPage(){
        return new ButtonsPage("/");
    }

    public static WebTablesPage buildWebTablesPage(){return new WebTablesPage("/webtables");}
}

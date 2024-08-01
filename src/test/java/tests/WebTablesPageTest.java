package tests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
public class WebTablesPageTest extends BaseTest {
    @BeforeMethod
    public void before() {
        pageManager.webTablesPage.open();
    }
    @Test(priority = 1)
    public void addandEditWebTableData(){
        pageManager.webTablesPage.clickWebTables();
        pageManager.webTablesPage.addBtn();
        pageManager.webTablesPage.addTableData("Can","Bar","23","cb@example.com","12330","Sales");
        List<String> expectedData = Arrays.asList("Can", "Bar", "23","cb@example.com", "12330", "Sales");
        List<String> actualData = pageManager.webTablesPage.getRowData(4);
        assertThat(actualData).containsExactlyElementsOf(expectedData);
        pageManager.webTablesPage.editRow(4, "Martin", "Eden", "30" , "me@example.com","50000", "Engineering");

        // Verify the edited row at index 4
        List<String> expectedEditedData = Arrays.asList("Martin", "Eden","30","me@example.com", "50000", "Engineering");
        List<String> actualEditedData = pageManager.webTablesPage.getRowData(4 );
        assertThat(actualEditedData).containsExactlyElementsOf(expectedEditedData);
    }

}

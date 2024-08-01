package pages;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import java.util.List;

import static com.codeborne.selenide.Condition.enabled;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class WebTablesPage extends BasePage {
  public SelenideElement clickWebTables = $(By.xpath("//span[contains(text(),'Web Tables')]"));
  public SelenideElement addBtn =$(By.xpath("//button[@id='addNewRecordButton']"));
  public SelenideElement firstNameField = $(By.xpath("//input[@id='firstName']"));
  public SelenideElement lastNameField = $(By.xpath("//input[@id='lastName']"));
  public SelenideElement emailField = $(By.xpath("//input[@id='userEmail']"));
  public SelenideElement ageField = $(By.xpath("//input[@id='age']"));
  public SelenideElement salaryField = $(By.xpath("//input[@id='salary']"));
  public SelenideElement departmentField = $(By.xpath("//input[@id='department']"));
  public SelenideElement submitButton = $(By.xpath("//button[@id='submit']"));
  public ElementsCollection allRows = $$(".rt-tbody .rt-tr-group");

  public WebTablesPage(String pageUrl){super(pageUrl);}

  public void clickWebTables(){
    clickWebTables.shouldBe(visible, enabled).click();
  }

  public void addBtn() {
    addBtn.shouldBe(visible, enabled).click();
  }
  public void addTableData(String firstname, String lastname,String age, String email,String salary,String dep){
    firstNameField.setValue(firstname);
    lastNameField.setValue(lastname);
    emailField.setValue(email);
    ageField.setValue(age);
    salaryField.setValue(salary);
    departmentField.setValue(dep);
    submitButton.click();
    $(".rt-tbody .rt-tr-group").shouldBe(Condition.visible);
  }

  public List<String> getRowData(int rowIndex) {
    SelenideElement row = allRows.get(rowIndex - 1).shouldBe(Condition.visible);
    List<String> cellTexts = row.$$(".rt-td").texts();
    while (!cellTexts.isEmpty() && cellTexts.get(cellTexts.size() - 1).isEmpty()) {
      cellTexts.remove(cellTexts.size() - 1);
    }
    System.out.println("Row data: " + cellTexts);
    return cellTexts;
  }
  public void editRow(int rowIndex, String firstName, String lastName, String age,String email, String salary, String department) {
    SelenideElement row = allRows.get(rowIndex - 1).shouldBe(Condition.visible);
    SelenideElement editButton = row.$("span[id^='edit-record']").shouldBe(visible);
    executeJavaScript("arguments[0].click();", editButton);

    firstNameField.shouldBe(Condition.visible).shouldBe(Condition.editable).setValue(firstName);
    lastNameField.shouldBe(Condition.visible).shouldBe(Condition.editable).setValue(lastName);
    emailField.shouldBe(Condition.visible).shouldBe(Condition.editable).setValue(email);
    ageField.shouldBe(Condition.visible).shouldBe(Condition.editable).setValue(age);
    salaryField.shouldBe(Condition.visible).shouldBe(Condition.editable).setValue(salary);
    departmentField.shouldBe(Condition.visible).shouldBe(Condition.editable).setValue(department);
    submitButton.shouldBe(Condition.visible).click();
  }


}

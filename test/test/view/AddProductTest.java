package test.view;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AddProductTest {
    private WebDriver driver;
    private String path = "http://localhost:8080/Controller";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Google Drive\\Documents\\School\\web3\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(path + "?action=addproduct");
    }

    @After
    public void clean() {
        driver.quit();
    }

    @Test
    public void test_AddProduct_AllFieldsFilledInCorrectly_ProductIsAdded() {
        submitForm("Camel", "Pakje sigaretten", "6");

        String title = driver.getTitle();
        assertEquals("Product Overview", title);

        driver.get(path + "?action=productoverview");

        ArrayList<WebElement> listItems = (ArrayList<WebElement>) driver.findElements(By.cssSelector("table tr"));
        boolean found = false;
        for (WebElement listItem : listItems) {
            if (listItem.getText().contains("Camel Pakje sigaretten 6.0")) {
                found = true;
            }
        }
        assertTrue(found);
    }

    @Test
    public void test_AddProduct_NameNotFilledIn_ErrorMessageGivenForNameAndOtherFieldsValueKept() {
        submitForm("", "Een stevige houten stoel", "30");

        String title = driver.getTitle();
        assertEquals("Add Product", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No name given", errorMsg.getText());

        WebElement fieldUserid = driver.findElement(By.id("name"));
        assertEquals("", fieldUserid.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("description"));
        assertEquals("Een stevige houten stoel", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("price"));
        assertEquals("30", fieldLastName.getAttribute("value"));
    }

    @Test
    public void test_AddProduct_DescriptionNotFilledIn_ErrorMessageGivenForDescriptionAndOtherFieldsValueKept() {
        submitForm("Es Stoel", "", "30");

        String title = driver.getTitle();
        assertEquals("Add Product", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No description given", errorMsg.getText());

        WebElement fieldUserid = driver.findElement(By.id("name"));
        assertEquals("Es Stoel", fieldUserid.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("description"));
        assertEquals("", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("price"));
        assertEquals("30", fieldLastName.getAttribute("value"));
    }

    @Test
    public void test_AddProduct_PriceNotFilledIn_ErrorMessageGivenForPriceAndOtherFieldsValueKept() {
        submitForm("Es Stoel", "Een stevige houten stoel", "");

        String title = driver.getTitle();
        assertEquals("Add Product", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("No price given", errorMsg.getText());

        WebElement fieldUserid = driver.findElement(By.id("name"));
        assertEquals("Es Stoel", fieldUserid.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("description"));
        assertEquals("Een stevige houten stoel", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("price"));
        assertEquals("", fieldLastName.getAttribute("value"));
    }

    @Test
    public void test_AddProduct_PriceInvalidFilledIn_ErrorMessageGivenForPriceAndOtherFieldsValueKept() {
        submitForm("Es Stoel", "Een stevige houten stoel", "dertig");

        String title = driver.getTitle();
        assertEquals("Add Product", title);

        WebElement errorMsg = driver.findElement(By.cssSelector("div.alert-danger ul li"));
        assertEquals("Give a valid price", errorMsg.getText());

        WebElement fieldUserid = driver.findElement(By.id("name"));
        assertEquals("Es Stoel", fieldUserid.getAttribute("value"));

        WebElement fieldFirstName = driver.findElement(By.id("description"));
        assertEquals("Een stevige houten stoel", fieldFirstName.getAttribute("value"));

        WebElement fieldLastName = driver.findElement(By.id("price"));
        assertEquals("dertig", fieldLastName.getAttribute("value"));
    }

    private void fillOutField(String name, String value) {
        WebElement field = driver.findElement(By.id(name));
        field.clear();
        field.sendKeys(value);
    }

    private void submitForm(String name, String description, String price) {
        fillOutField("name", name);
        fillOutField("description", description);
        fillOutField("price", price);

        WebElement button = driver.findElement(By.id("addProduct"));
        button.click();
    }

}

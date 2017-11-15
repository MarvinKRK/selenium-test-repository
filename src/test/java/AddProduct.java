import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AddProduct {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test()
    public void addProduct() {
        String product = "product";

        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(visibilityOfElementLocated(By.className("fa-sign-out")));

        driver.findElement(By.cssSelector("#box-apps-menu li:nth-child(2)")).click();
        driver.findElement(By.cssSelector("#content a.button:nth-child(2)")).click();

        driver.findElement(By.cssSelector("[name='status'][value='1']:not([checked])")).click();
        driver.findElement(By.cssSelector("[name='name[en]']")).sendKeys(product);
        driver.findElement(By.cssSelector("[name='code']")).sendKeys("code");
        driver.findElement(By.cssSelector("[name='product_groups[]'][value='1-3']")).click();
        driver.findElement(By.cssSelector("[name='quantity']")).clear();
        driver.findElement(By.cssSelector("[name='quantity']")).sendKeys("1");
        driver.findElement(By.cssSelector("[name='date_valid_from']")).sendKeys("01.01.2000");
        driver.findElement(By.cssSelector("[name='date_valid_to']")).sendKeys("31.12.2000");

        driver.findElement(By.cssSelector(".index li:nth-child(2)")).click();
        (new Select(driver.findElement(By.cssSelector("[name='manufacturer_id']")))).selectByIndex(1);
        driver.findElement(By.cssSelector("[name='keywords']")).sendKeys("keywords");
        driver.findElement(By.cssSelector("[name='short_description[en]']")).sendKeys("short_description");
        driver.findElement(By.cssSelector(".trumbowyg-editor")).sendKeys("description");
        driver.findElement(By.cssSelector("[name='head_title[en]']")).sendKeys("head_title");
        driver.findElement(By.cssSelector("[name='meta_description[en]']")).sendKeys("meta_description");

        driver.findElement(By.cssSelector(".index li:nth-child(4)")).click();
        driver.findElement(By.cssSelector("[name='purchase_price']")).clear();
        driver.findElement(By.cssSelector("[name='purchase_price']")).sendKeys("1,23");
        (new Select(driver.findElement(By.cssSelector("[name='purchase_price_currency_code']")))).selectByIndex(2);
        driver.findElement(By.cssSelector("[name='gross_prices[USD]']")).clear();
        driver.findElement(By.cssSelector("[name='gross_prices[USD]']")).sendKeys("1,23");
        driver.findElement(By.cssSelector("[name='gross_prices[EUR]']")).clear();
        driver.findElement(By.cssSelector("[name='gross_prices[EUR]']")).sendKeys("1,23");
        driver.findElement(By.cssSelector("[name='save'")).click();

        wait.until(ExpectedConditions.textToBe(By.cssSelector(".row a[href *= 'product_id']:nth-child(2)"), product));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

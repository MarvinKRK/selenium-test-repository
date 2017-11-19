import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;

public class Cart {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @Test()
    public void addAndDeleteProducts() {
        driver.get("http://localhost/litecart/");

        for (int i = 1; i < 4; i++) {
            driver.findElement(By.cssSelector("#main .link")).click();

            if (driver.findElements(By.cssSelector(".buy_now td")).size() > 1) {
                (new Select(driver.findElement(By.cssSelector("[name='options[Size]']")))).selectByIndex(1);
            }

            driver.findElement(By.cssSelector("[name='add_cart_product']")).click();
            wait.until(textToBe(By.cssSelector("#cart .quantity"), Integer.toString(i)));

            driver.findElement(By.cssSelector("#logotype-wrapper")).click();
        }

        wait.until(textToBe(By.cssSelector("#cart .quantity"), "3"));
        driver.findElement(By.cssSelector("#cart .link")).click();

        int items = driver.findElements(By.cssSelector("td.item")).size();
        for (int i = 0; i < items; i++) {
            WebElement table = driver.findElement(By.cssSelector(".dataTable tr:not(.header)"));
            driver.findElement(By.cssSelector("[name='remove_cart_item']")).click();
            wait.until(stalenessOf(table));
        }

        wait.until(textToBe(By.cssSelector("#checkout-cart-wrapper em"), "There are no items in your cart."));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

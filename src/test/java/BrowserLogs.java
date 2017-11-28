import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertEquals;

public class BrowserLogs {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @Test()
    public void browserLogs() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(visibilityOfElementLocated(By.className("fa-sign-out")));

        driver.findElement(By.cssSelector("#box-apps-menu li:nth-child(2)")).click();
        driver.findElement(By.cssSelector(".row td > a")).click();
        driver.findElement(By.cssSelector(".row td:nth-child(3) > a")).click();

        List<WebElement> products = driver.findElements(By.cssSelector(".row td:nth-child(3) > a"));
        for (int i = 0; i < products.size(); i++) {
            driver.findElements(By.cssSelector(".row td:nth-child(3) > a")).get(i).click();
            assertEquals(driver.manage().logs().get("browser").getAll().size(), 0);
            driver.navigate().back();
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

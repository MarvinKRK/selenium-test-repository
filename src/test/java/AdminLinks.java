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

public class AdminLinks {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @Test()
    public void adminLinks() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(visibilityOfElementLocated(By.className("fa-sign-out")));

        List<WebElement> links = driver.findElements(By.cssSelector("ul#box-apps-menu > li"));
        for (int i = 1; i <= links.size(); ++i) {
            driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ")")).click();

            List<WebElement> links2 = driver.findElements(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") li"));
            if (links2.size() > 0) {
                for (int j = 1; j <= links2.size(); ++j) {
                    driver.findElement(By.cssSelector("ul#box-apps-menu > li:nth-child(" + i + ") li:nth-child(" + j + ")")).click();
                    wait.until(visibilityOfElementLocated(By.tagName("h1")));
                }
            } else {
                wait.until(visibilityOfElementLocated(By.tagName("h1")));
            }
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

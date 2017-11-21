import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class NewWindows {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @Test()
    public void openNewWindows() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(visibilityOfElementLocated(By.className("fa-sign-out")));

        driver.findElement(By.cssSelector("#sidebar li a[href *= 'countries']")).click();
        wait.until(textToBe(By.cssSelector("h1"), "Countries"));
        driver.findElement(By.cssSelector(".button")).click();

        String url = driver.getCurrentUrl();
        String mainWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        List<WebElement> externalLinks = driver.findElements(By.cssSelector(".fa-external-link"));

        for (WebElement link : externalLinks) {
            link.click();
            wait.until(numberOfWindowsToBe(allWindows.size() + 1));

            Set<String> allNewWindows = driver.getWindowHandles();
            allNewWindows.removeAll(allWindows);

            driver.switchTo().window(allNewWindows.iterator().next());
            wait.until(not(urlToBe(url)));

            driver.close();
            driver.switchTo().window(mainWindow);
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

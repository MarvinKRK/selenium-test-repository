import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class AddUser {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test()
    public void addUser() {
        int id = 0;
        String email = "test" + (++id) + "@example.com";
        String password = "password123";
        driver.get("http://localhost/litecart/");

        driver.findElement(By.cssSelector("#box-account-login a")).click();
        driver.findElement(By.cssSelector("[name='firstname']")).sendKeys("firstname");
        driver.findElement(By.cssSelector("[name='lastname']")).sendKeys("lastname");
        driver.findElement(By.cssSelector("[name='address1']")).sendKeys("address1");
        driver.findElement(By.cssSelector("[name='postcode']")).sendKeys("11-222");
        driver.findElement(By.cssSelector("[name='city']")).sendKeys("city");
        driver.findElement(By.cssSelector("[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("[name='phone']")).sendKeys("123456789");
        driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='confirmed_password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='create_account']")).click();

        while (driver.findElements(By.cssSelector(".notice.errors")).size() > 0) {
            email = "test" + (++id) + "@example.com";
            driver.findElement(By.cssSelector("[name='email']")).clear();
            driver.findElement(By.cssSelector("[name='email']")).sendKeys(email);
            driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
            driver.findElement(By.cssSelector("[name='confirmed_password']")).sendKeys(password);
            driver.findElement(By.cssSelector("[name='create_account']")).click();
        }

        wait.until(visibilityOfElementLocated(By.cssSelector("#box-account")));

        driver.findElement(By.cssSelector("#box-account li:nth-child(4) > a")).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#box-account-login")));

        driver.findElement(By.cssSelector("[name='email']")).sendKeys(email);
        driver.findElement(By.cssSelector("[name='password']")).sendKeys(password);
        driver.findElement(By.cssSelector("[name='login']")).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#box-account")));

        driver.findElement(By.cssSelector("#box-account li:nth-child(4) > a")).click();
        wait.until(visibilityOfElementLocated(By.cssSelector("#box-account-login")));
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

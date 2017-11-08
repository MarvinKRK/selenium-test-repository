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

import static org.testng.Assert.assertEquals;

public class ArticleLabel {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @Test()
    public void articleLabel() {
        driver.get("http://localhost/litecart/");
        List<WebElement> articles = driver.findElements(By.cssSelector(".box .link"));
        for (WebElement article : articles) {
            List<WebElement> stickers = article.findElements(By.cssSelector(".sticker"));
            assertEquals(stickers.size(), 1, "Too many stickers on article.");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

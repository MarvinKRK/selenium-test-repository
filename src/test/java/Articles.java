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

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.testng.Assert.assertEquals;

public class Articles {
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
        List<WebElement> articles = driver.findElements(By.cssSelector("#box-campaigns .link"));
        if (articles.size() > 0) {
            WebElement article = articles.get(0);
            WebElement regularPrice = article.findElement(By.cssSelector(".regular-price"));
            WebElement campaignPrice = article.findElement(By.cssSelector(".campaign-price"));

            String articleName = article.findElement(By.cssSelector(".name")).getText();
            String articleRegularPrice = regularPrice.getText();
            String articleCampaignPrice = campaignPrice.getText();

            assertEquals(regularPrice.getCssValue("color"), "rgba(119, 119, 119, 1)", "Color is wrong.");
            assertEquals(regularPrice.getCssValue("text-decoration"), "line-through solid rgb(119, 119, 119)", "Price is not strikethrough.");
            assertEquals(campaignPrice.getCssValue("color"), "rgba(204, 0, 0, 1)", "Color is wrong.");
            assertEquals(campaignPrice.getCssValue("font-weight"), "700", "Price is not bold.");

            article.click();
            wait.until(textToBe(By.cssSelector("h1"), articleName));
            wait.until(textToBe(By.cssSelector(".regular-price"), articleRegularPrice));
            wait.until(textToBe(By.cssSelector(".campaign-price"), articleCampaignPrice));

            regularPrice = driver.findElement(By.cssSelector(".regular-price"));
            campaignPrice = driver.findElement(By.cssSelector(".campaign-price"));

            assertEquals(regularPrice.getCssValue("color"), "rgba(102, 102, 102, 1)", "Color is wrong.");
            assertEquals(regularPrice.getCssValue("text-decoration"), "line-through solid rgb(102, 102, 102)", "Price is not strikethrough.");
            assertEquals(campaignPrice.getCssValue("color"), "rgba(204, 0, 0, 1)", "Color is wrong.");
            assertEquals(campaignPrice.getCssValue("font-weight"), "700", "Price is not bold.");
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

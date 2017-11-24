import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class CloudTest {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() throws MalformedURLException {
        driver = new RemoteWebDriver(new URL("http://xxx:yyy@hub-cloud.browserstack.com/wd/hub"), DesiredCapabilities.chrome());
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
    }

    @Test()
    public void cloudTest() {
        driver.get("https://google.pl");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBe;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static org.testng.Assert.assertTrue;

public class Countries {
    private WebDriver driver;
    private WebDriverWait wait;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 5);
    }

    @Test()
    public void countries() {
        driver.get("http://localhost/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        wait.until(visibilityOfElementLocated(By.className("fa-sign-out")));

        driver.findElement(By.cssSelector("#sidebar li a[href *= 'countries']")).click();
        wait.until(textToBe(By.cssSelector("h1"), "Countries"));

        List<WebElement> countriesList = driver.findElements(By.cssSelector(".dataTable .row"));
        List<String> countries = new ArrayList<>();
        List<String> countriesWithZones = new ArrayList<>();
        for (WebElement country : countriesList) {
            countries.add(country.findElement(By.cssSelector("td:nth-child(5) a")).getText());
            if (!country.findElement(By.cssSelector("td:nth-child(6)")).getText().equals("0")) {
                countriesWithZones.add(country.findElement(By.cssSelector("td:nth-child(4)")).getText());
            }
        }

        List<String> countriesCopy = new ArrayList<>(countries);
        Collections.sort(countriesCopy);
        assertTrue(countriesCopy.equals(countries), "Countries are not sorted.");

        for (String code : countriesWithZones) {
            driver.findElement(By.cssSelector(".dataTable a[href *= 'country_code=" + code + "']")).click();

            List<WebElement> zonesList = driver.findElements(By.cssSelector("#table-zones td:nth-child(3)"));
            List<String> zones = new ArrayList<>();
            for (WebElement zone : zonesList) {
                if (!zone.findElement(By.cssSelector("input")).getAttribute("value").equals("")) {
                    zones.add(zone.getText());
                }
            }

            List<String> zonesCopy = new ArrayList<>(zones);
            Collections.sort(zonesCopy);
            assertTrue(zonesCopy.equals(zones), "Zones are not sorted for country code " + code + ".");

            driver.navigate().back();
        }
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

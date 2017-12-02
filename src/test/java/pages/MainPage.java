package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MainPage extends Page {
    @FindBy(css = "#main .link")
    private WebElement product;

    @FindBy(css = "#logotype-wrapper")
    private WebElement logo;

    public MainPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void open() {
        driver.get("http://localhost/litecart/");
    }

    public void openProduct() {
        product.click();
    }

    public void returnToMainPage() {
        logo.click();
    }
}

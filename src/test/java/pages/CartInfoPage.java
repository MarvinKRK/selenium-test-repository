package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartInfoPage extends Page {
    @FindBy(css = "#cart .link")
    private WebElement cart;

    @FindBy(css = "#cart .quantity")
    private WebElement cartQuantity;

    public CartInfoPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void openCartPage() {
        cart.click();
    }

    public WebElement getQuantity() {
        return cartQuantity;
    }
}

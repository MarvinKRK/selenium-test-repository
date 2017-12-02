package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.stalenessOf;

public class CartPage extends Page {
    @FindBy(css = "td.item")
    private List<WebElement> productsList;

    @FindBy(css = "[name='remove_cart_item']")
    private WebElement removeProduct;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public int getQuantity() {
        return productsList.size();
    }

    public void removeProduct() {
        WebElement table = driver.findElement(By.cssSelector(".dataTable tr:not(.header)"));
        removeProduct.click();
        wait.until(stalenessOf(table));
    }
}

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class ProductPage extends Page {
    @FindBy(css = ".buy_now td")
    private List<WebElement> sizeOptionList;

    @FindBy(css = "[name='options[Size]']")
    private WebElement sizeOption;

    @FindBy(css = "[name='add_cart_product']")
    private WebElement addProduct;

    public ProductPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public ProductPage setSize() {
        if (sizeOptionList.size() > 1) {
            (new Select(sizeOption)).selectByIndex(1);
        }
        return this;
    }

    public void addProductToCart() {
        WebElement quantityElement = new CartInfoPage(driver).getQuantity();
        int count = Integer.parseInt(quantityElement.getText());

        addProduct.click();
        wait.until(textToBePresentInElement(quantityElement, String.valueOf(count + 1)));
    }
}

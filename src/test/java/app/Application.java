package app;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.CartInfoPage;
import pages.CartPage;
import pages.MainPage;
import pages.ProductPage;

import java.util.concurrent.TimeUnit;

public class Application {
    private WebDriver driver;

    private MainPage mainPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private CartInfoPage cartInfoPage;

    public Application() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        mainPage = new MainPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        cartInfoPage = new CartInfoPage(driver);
    }

    public void openShop() {
        mainPage.open();
    }

    public void addProducts(int count) {
        for (int i = 1; i < count + 1; i++) {
            mainPage.openProduct();
            productPage.setSize().addProductToCart();
            mainPage.returnToMainPage();
        }
    }

    public int getQuantity() {
        return Integer.parseInt(cartInfoPage.getQuantity().getText());
    }

    public void deleteAllProducts() {
        cartInfoPage.openCartPage();

        int items = cartPage.getQuantity();
        for (int i = 0; i < items; i++) {
            cartPage.removeProduct();
        }
    }

    public void quit() {
        driver.quit();
    }
}

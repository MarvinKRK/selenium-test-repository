package test;

import app.Application;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CartTest {
    private Application app;
    private int quantity = 3;

    @BeforeTest
    public void setUp() {
        app = new Application();
    }

    @Test()
    public void addAndDeleteProducts() {
        app.openShop();
        app.addProducts(quantity);
        assertEquals(app.getQuantity(), quantity);
        app.deleteAllProducts();
    }

    @AfterTest
    public void tearDown() {
        app.quit();
    }
}

package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage{

    WebDriverWait wait;

    public SearchPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // Quantity field
    @FindBy(id = "input-quantity")
    WebElement quantityField;

    // Add to cart button
    @FindBy(id = "button-cart")
    WebElement btnAddToCart;

    // Success message
    @FindBy(xpath = "//div[contains(@class,'alert-success')]")
    WebElement successMsg;

    // ===============================
    // Verify Product Exists
    // ===============================
    public boolean isProductExists(String productName) {
        try {
            return driver.findElement(
                    By.xpath("//a[contains(text(),'" + productName + "')]"))
                    .isDisplayed();
        } catch (Exception e) {
            return false; 
        }
    }

    // ===============================
    // Select Product
    // ===============================
    public void selectProduct(String productName) {
        driver.findElement(
                By.xpath("//a[contains(text(),'" + productName + "')]"))
                .click();
    }

    // ===============================
    // Set Quantity
    // ===============================
    public void setQuantity(String qty) {
        quantityField.clear();
        quantityField.sendKeys(qty);
    }

    // ===============================
    // Click Add To Cart
    // ===============================
    public void clickAddToCart() {
        btnAddToCart.click();
    }

    // ===============================
    // Verify Success Message
    // ===============================
    
    public boolean checkConfMsg() {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMsg));
            String msg = successMsg.getText();
            return msg.contains("Success");
        } catch (Exception e) {
            return false; 
        }
    }
    
    /*public boolean checkConfMsg(String productName) {
        try {
            wait.until(ExpectedConditions.visibilityOf(successMsg));
            String message = successMsg.getText();
            return message.contains(productName);
        } catch (Exception e) {
            return false;
        }
    }*/
}
package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutPage extends BasePage{
	
	WebDriverWait wait;

    // Constructor
    public CheckoutPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //locators

    By txtFirstName = By.id("input-payment-firstname");
    By txtLastName = By.id("input-payment-lastname");
    By txtAddress1 = By.id("input-payment-address-1");
    By txtAddress2 = By.id("input-payment-address-2");
    By txtCity = By.id("input-payment-city");
    By txtPin = By.id("input-payment-postcode");
    By drpCountry = By.id("input-payment-country");
    By drpState = By.id("input-payment-zone");

    By btnBillingContinue = By.id("button-payment-address");
    By btnDeliveryAddressContinue = By.id("button-shipping-address");

    By txtDeliveryComment = By.name("comment");
    By btnDeliveryMethodContinue = By.id("button-shipping-method");

    By chkTerms = By.name("agree");
    By btnPaymentMethodContinue = By.id("button-payment-method");

    By lblTotalPrice = By.xpath("//table[@class='table table-bordered table-hover']//tr[last()]/td[2]");
    By btnConfirmOrder = By.id("button-confirm");
    

    //methods

    public void setFirstName(String fname) {
        driver.findElement(txtFirstName).clear();
        driver.findElement(txtFirstName).sendKeys(fname);
    }

    public void setLastName(String lname) {
        driver.findElement(txtLastName).clear();
        driver.findElement(txtLastName).sendKeys(lname);
    }

    public void setAddress1(String address1) {
        driver.findElement(txtAddress1).sendKeys(address1);
    }

    public void setAddress2(String address2) {
        driver.findElement(txtAddress2).sendKeys(address2);
    }

    public void setCity(String city) {
        driver.findElement(txtCity).sendKeys(city);
    }

    public void setpin(String pin) {
        driver.findElement(txtPin).sendKeys(pin);
    }

    public void selectCountry(String country) {
        WebElement countryElement = driver.findElement(drpCountry);
        Select select = new Select(countryElement);
        select.selectByVisibleText(country);
    }

    public void setState(String state) {
        WebElement stateElement = driver.findElement(drpState);
        Select select = new Select(stateElement);
        select.selectByVisibleText(state);
    }

    public void clickOnContinueAfterBillingAddress() {
        wait.until(ExpectedConditions.elementToBeClickable(btnBillingContinue)).click();
    }

    public void clickOnContinueAfterDeliveryAddress() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(btnDeliveryAddressContinue)).click();
        } catch (Exception e) {
            System.out.println("Delivery address step skipped.");
        }
    }

    public void setDeliveryMethodComment(String comment) {
        driver.findElement(txtDeliveryComment).sendKeys(comment);
    }

    public void clickOnContinueAfterDeliveryMethod() {

        try {

            // check if shipping section exists
            if(driver.findElements(By.id("button-shipping-method")).size() > 0) {

                System.out.println("Shipping method section available.");

                // select shipping radio if present
                if(driver.findElements(By.name("shipping_method")).size() > 0) {
                    driver.findElement(By.name("shipping_method")).click();
                }

                driver.findElement(By.id("button-shipping-method")).click();
            }
            else {
                System.out.println("Shipping method step skipped.");
            }

        } catch (Exception e) {
            System.out.println("Shipping method not required.");
        }
    }

    public void selectTermsAnsConditions() {
        driver.findElement(chkTerms).click();
    }

    public void clickOnContinueAfterPaymentMethod() {
        driver.findElement(btnPaymentMethodContinue).click();
    }

    public String getTotalPriceBeforeConfOrder() {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // wait for confirm section to appear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("collapse-checkout-confirm")));

        By totalLocator = By.xpath("//div[@id='collapse-checkout-confirm']//tr[last()]/td[2]");

        return driver.findElement(totalLocator).getText();
    }

    public void clickConfirmOrder() {
        driver.findElement(btnConfirmOrder).click();
    }
}





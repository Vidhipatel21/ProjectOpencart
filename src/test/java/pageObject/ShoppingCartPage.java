package pageObject;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage extends BasePage{
	
	WebDriverWait wait;
	 
	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	@FindBy(id="cart-total")
	WebElement cartButton;
	
	@FindBy(xpath="//strong[normalize-space()='View Cart']")
	WebElement viewCartBtn;
	
	@FindBy(xpath="//a[contains(@class,'btn-primary') and contains(text(),'Checkout')]")
	WebElement clickOnCheckout;
	
	//@FindBy(xpath="//tfoot//strong[contains(text(),'Total')]/parent::td/following-sibling::td")
	//WebElement cartTotalPrice;
	
	public void clickItemsToNavigateToCart() {
	    wait.until(ExpectedConditions.elementToBeClickable(cartButton)).click();
	}
	 
	public void clickViewCart() {
	    wait.until(ExpectedConditions.visibilityOf(viewCartBtn));
	    viewCartBtn.click();
	}
	
	public void clickOnCheckout() {

	    wait.until(ExpectedConditions.visibilityOf(clickOnCheckout));

	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    js.executeScript("arguments[0].scrollIntoView(true);", clickOnCheckout);

	    wait.until(ExpectedConditions.elementToBeClickable(clickOnCheckout)).click();
	}
	
	public String getTotalPrice() {

	    WebElement total = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("(//td[contains(@class,'text-right')])[last()]")
	        )
	    );

	    return total.getText().trim();
	}
}

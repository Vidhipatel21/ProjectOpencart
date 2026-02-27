package pageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{
	
	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	WebElement lnkMyaccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText = "Login")   //login link added
	WebElement linkLogin;
	
	@FindBy(xpath="//input[@placeholder='Search']")
	WebElement enterProductName;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	WebElement clickSearch;
	
	public void clickMyAccount() {
		lnkMyaccount.click();
	}
	
	public void clickRegister() {
		lnkRegister.click();
	}
	
	public void clickLogin() {
		linkLogin.click();
	}
	
	public void enterProductName(String Search) {
		enterProductName.sendKeys(Search);
	}
	
	public void clickSearch() {
		clickSearch.click();
	}
} 
 
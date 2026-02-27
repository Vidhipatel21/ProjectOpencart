package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import TestBase.BaseClass;
import pageObject.AccountRegistrationPage;
import pageObject.CheckoutPage;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import pageObject.SearchPage;
import pageObject.ShoppingCartPage;

public class TC006_EndToEndTest extends BaseClass{
	
	@Test
	public void endToendTest() throws InterruptedException{
		
		//soft assertion
		SoftAssert myassert = new SoftAssert();
		
		//Account Registration
		System.out.println("Account Registration................");
		
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		regpage.setFirstName(randomString().toUpperCase());
		regpage.setLastName(randomString().toUpperCase());
		
		String email = randomString() + "@gmail.com";
		regpage.setEmail(email);  //randomly generated email;
		
		regpage.setTelephone(randomString());
		
		String password = randomAlphaNumeric();
		
		regpage.setPassword(password); 
		regpage.setConfirmPassword(password);
		regpage.setPrivacyPolicy();
		regpage.clickContinue(); 
		
		String confmsg = regpage.getConfirmationMsg();
		System.out.println(confmsg);
		Assert.assertEquals(confmsg, "Your Account Has Been Created!");
		
		MyAccountPage mc = new MyAccountPage(driver);
		mc.clickLogout();
		Thread.sleep(3000);
		
		//login
		
		System.out.println("Login to Application...................");
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(password);
		lp.clickLogin();
		
		System.out.println("Going to My Account Page?" + mc.isMyAccountPageExists());
		myassert.assertEquals(mc.isMyAccountPageExists(), true);  //validation
		
		//search and add product to cart
		System.out.println("Search & Add Product to Cart.................");
		String product = p.getProperty("productName");
		hp.enterProductName(p.getProperty("enterProductName"));
		hp.clickSearch();
		
		SearchPage sp = new SearchPage(driver);
		if(sp.isProductExists(product)) {
		    sp.selectProduct(product);
			sp.setQuantity("2");
			sp.clickAddToCart();
		}
		
		Thread.sleep(3000);
		System.out.println("Added product to cart?" + sp.checkConfMsg());
		myassert.assertEquals(sp.checkConfMsg(),true);
		
		//shopping cart
		System.out.println("Shopping cart..........");
		
		ShoppingCartPage sc = new ShoppingCartPage(driver);
		sc.clickItemsToNavigateToCart();
		sc.clickViewCart();
		
		Thread.sleep(3000);
		
		String total_price_inOrderPage = sc.getTotalPrice();

		System.out.println("total price is shopping cart: " + total_price_inOrderPage);

		myassert.assertEquals(total_price_inOrderPage, "$1,204.00");  // fix expected value
		
		sc.clickOnCheckout();
		
		System.out.println("After Checkout Click URL: " + driver.getCurrentUrl());
		//Thread.sleep(3000);
		
		//checkout product
		
		System.out.println("Checkout Product....................");
		
		CheckoutPage ch = new CheckoutPage(driver);
		
		ch.setFirstName(randomString().toUpperCase());
		ch.setLastName(randomString().toUpperCase());
		ch.setAddress1("address1");
		ch.setAddress2("addreess2");
		ch.setCity("Kitchener");
		ch.setpin("M1G3S6");
		ch.selectCountry("Canada");
		ch.setState("Ontario");
		ch.clickOnContinueAfterBillingAddress();
		ch.clickOnContinueAfterDeliveryAddress();
		ch.setDeliveryMethodComment("Do not have access to move forword");
		ch.clickOnContinueAfterDeliveryMethod();
		ch.selectTermsAnsConditions();
		ch.clickOnContinueAfterPaymentMethod();
		
		String total_priceinOrderPage = ch.getTotalPriceBeforeConfOrder();
		System.out.println("total price in confirmed order page:" + total_priceinOrderPage);
		myassert.assertEquals(total_priceinOrderPage, "$1,204.00");
		
		//ch.clickConfirmOrder();
		
		
		
		myassert.assertAll();
	}
 
}





















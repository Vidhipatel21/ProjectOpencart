package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObject.HomePage;
import pageObject.SearchPage;

public class TC005_AddTOCartPageTest extends BaseClass {
	
	@Test(groups= {"Master"})
	public void verify_addToCart() throws InterruptedException {
		 
		logger.info("************* Starting TC005_AddTOCartPageTest **************");
		
		try {
			
			HomePage hp = new HomePage(driver);
			
			hp.enterProductName("MacBook");
			hp.clickSearch();
			
			SearchPage sp = new SearchPage(driver);
			
			if(sp.isProductExists("MacBook")) {
				sp.selectProduct("MacBook");
				sp.setQuantity("2");
				sp.clickAddToCart();
				
			}
			
			Assert.assertEquals(sp.checkConfMsg(),true); 
			
		} catch (Exception e) {
			Assert.fail();
		} 
		
		logger.info("************* Finished TC005_AddTOCartPageTest **************");
		
	}
	
	
}

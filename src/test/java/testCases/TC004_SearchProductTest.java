package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import TestBase.BaseClass;
import pageObject.HomePage;
import pageObject.SearchPage;

public class TC004_SearchProductTest extends BaseClass{
	
	@Test(groups= {"Master"})
	public void verify_productSearch() {
		
		logger.info("************ Starting TC004_SearchProductTest ************");
		
		try {
			
			HomePage hm = new HomePage(driver);
			hm.enterProductName("MacBook");
			hm.clickSearch();
			
			SearchPage sp = new SearchPage(driver);
			
			Assert.assertTrue(sp.isProductExists("MacBook"));
	
	} catch (Exception e) {
			Assert.fail(); 
		}
	
	logger.info("************ Finished TC004_SearchProductTest ************");
	
	}
} 

package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import TestBase.BaseClass;
import pageObject.HomePage;
import pageObject.LoginPage;
import pageObject.MyAccountPage;
import utilities.DataProviders;

public class TC003_LoginDDT extends BaseClass { 
	
	@Test(dataProvider="LoginData", dataProviderClass = DataProviders.class, groups= {"DataDriven"})  //getting data provider from different class
	public void verify_LoginDDT(String Email, String Password, String exp) {
		
		logger.info("********* Starting TC003_LoginDDT ***********");
		
		try {
			
		//HomePage
		HomePage hp = new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
			
		//LoginPage
		LoginPage lp = new LoginPage(driver);
		lp.setEmail(Email);
		lp.setPassword(Password);
		lp.clickLogin();
			
		//MyAccount
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage = macc.isMyAccountPageExists();
		
		/*Negative testing 
		 Data is valid - login success - test pass - logout
		 Data is valid - login failed - test fail
		 
		 Data is invalid - login success - test fail - logout
		 Data is invalid - login failed - test pass 
		 */
	
		if(exp.equalsIgnoreCase("Valid")) 
		{
			if(targetPage == true) 
			{
				macc.clickLogout();
				Assert.assertTrue(true);  //pass
			}
			else
			{
				Assert.assertTrue(false);  //fail (valid data but login failed)
			}
		}
		
		if(exp.equalsIgnoreCase("InValid")) 
		{
			if(targetPage == true) 
			{
				macc.clickLogout();
				Assert.assertTrue(false);  //pass
			}
			else
			{
				Assert.assertTrue(true);  //fail (valid data but login failed)
			}
		} 
		
		} catch (Exception e) {
		    Assert.fail();
		}
		
		logger.info("********* Finished TC003_LoginDDT ***********");
		
	}
}















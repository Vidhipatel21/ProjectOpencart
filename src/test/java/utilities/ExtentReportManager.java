package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.ImageHtmlEmail;
import org.apache.commons.mail.resolver.DataSourceUrlResolver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import TestBase.BaseClass;

	public class ExtentReportManager implements ITestListener{
		public ExtentSparkReporter sparkReporter;
		public ExtentReports extent;
		public ExtentTest test;
		
		String repName;
		
		public void onStart(ITestContext testContext) {
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());   //time stamp
			repName = "Test-Report-" + timeStamp + ".html";
			
			sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName);      //specify location of the report
			
			sparkReporter.config().setDocumentTitle("Opencart Automation Project");     //Title of report
			sparkReporter.config().setReportName("Opencart Functional Testing");  //Name of the Report
			sparkReporter.config().setTheme(Theme.DARK);
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			extent.setSystemInfo("Application", "Opencart");   //project specific details
			extent.setSystemInfo("Module", "Admin");		   //project specific details
			extent.setSystemInfo("Sub Module", "Customers");   //project specific details
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			
			String os = testContext.getCurrentXmlTest().getParameter("os");
			extent.setSystemInfo("Operating Syatem", os);
			
			String browser = testContext.getCurrentXmlTest().getParameter("browser");
			extent.setSystemInfo("Browser", browser);
			
			List<String> includeGroups = testContext.getCurrentXmlTest().getIncludedGroups();
			if(!includeGroups.isEmpty()) {
				extent.setSystemInfo("Groups", includeGroups.toString());
			}
		}
		
		public void onTestSuccess(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());   //to display groups in report 
			test.log(Status.PASS, result.getName() + "got successfully executed");
		}
		
		public void onTestFailure(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			
			test.log(Status.FAIL, result.getName() + "got failed");
			test.log(Status.INFO, result.getThrowable().getMessage());
		
			
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			test.addScreenCaptureFromPath(imgPath);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
		
		public void onTestSkipped(ITestResult result) {
			test = extent.createTest(result.getTestClass().getName());
			test.assignCategory(result.getMethod().getGroups());
			test.log(Status.SKIP, result.getName() + "got Skipped");
			test.log(Status.INFO, result.getThrowable().getMessage());
		}
		
		public void onFinish(ITestContext testContext) {
			extent.flush();
			
			String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
			File extentReport = new File(pathOfExtentReport);
			
			try {
				Desktop.getDesktop().browse(extentReport.toURI());
			} catch (IOException e){
				e.printStackTrace();
			}
		
		}
	}
		
		/*
		//To send report automatically to the team or group
		
		 try {
			 URL url = new URL ("file:///" + System.getProperty("user.dir") + "\\reports\\" + repName);
		  
		 //Create the email message
		 
		 ImageHtmlEmail email = new ImageHtmlEmail();
		 email.setDataSourceResolver (new DataSourceUrlResolver(url));
		 email.setHostName("smtp.googlemail.com"); //keeps changing as based on corporate 
		 email.setSmtpPort(465);
		 email.setAuthenticator(new DefaultAuthenticator("patelvidhi1949@gmail.com", "password")); //corporateID, Password
		 email.setSSLOnConnect(true);
		 email.setFrom("patelvidhi1949@gmail.com"); //sender  //corporateID
		 email.setSubject("Test Results");
		 email.setMsg("Please Find Attached Report...");
		 email.addTo("patelvidhi.ca@gmail.com"); //receiver
		 email.attach(url, "extent report", "Please Check Report...");
		 email.send();  //send the email
		 }
		 catch(Exception e) {
			 e.printStackTrace();
		 }
		 
		  
		 } */
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	


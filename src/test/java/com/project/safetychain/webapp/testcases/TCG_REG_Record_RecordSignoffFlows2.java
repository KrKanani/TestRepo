package com.project.safetychain.webapp.testcases;


import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.FILTERTYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;


public class TCG_REG_Record_RecordSignoffFlows2 extends TestBase{
	
	ControlActions controlActions;
	LoginPage lp;
	HomePage hp;
	RecordSignoffPage rsp;
	public static String formName;
	public static String voidRecCmmt;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String signOffCmmt;
	public static String totalCount = null;
	public static String compliantCount = null;
	public static String nonCompliantCount = null;
	public static int totalCnt = 0;
	public static int compliantCnt = 0;
	public static int nonCompliantCnt = 0;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("RSignoff");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");	
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");	
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		signOffCmmt = CommonMethods.dynamicText("Cmmt1");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		rsp = new RecordSignoffPage(driver);
				
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description = "Record Signoff - Verify The Totals analytic shows metrics of all records in the selected set")
	public void TestCase_32232() {
		
		hp.clickFSQABrowserMenu();
		rsp = hp.clickRecordSignoffMenu();
		TestValidation.IsFalse(rsp.error, 
							  "NAVIGATED to Record Signoff page", 
				  			  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		totalCount = rsp.TotalRecordCount.getText().trim();
		TestValidation.IsTrue((totalCount!=null || totalCount!=""), 
							  "VERIFIED Total Count of records are - " + totalCount, 
				  			  "Failed to verify Total Count of records");
		
	}
	
	@Test(description = "Record Signoff - Verify The Compliant analytic shows metrics of compliant records in the selected set",
			dependsOnMethods = { "TestCase_32232" })
	public void TestCase_32233() {
		
		compliantCount = rsp.CompliantRecsCount.getText().trim();
		TestValidation.IsTrue((compliantCount!=null || compliantCount!=""), 
							  "VERIFIED Compliant Count of records are - " + compliantCount, 
				  			  "Failed to verify Compliant Count of records");
		
	}
	
	@Test(description = "Record Signoff -Verify The Non-Compliant analytic shows metrics of non-compliant records in the "
			+ "selected set", dependsOnMethods = { "TestCase_32233" })
	public void TestCase_32234() {
		
		totalCnt = Integer.parseInt(totalCount);
		compliantCnt = Integer.parseInt(compliantCount);
		
		nonCompliantCount = rsp.NonCompliantRecsCount.getText().trim();
		nonCompliantCnt = Integer.parseInt(nonCompliantCount);
		
		TestValidation.IsTrue((totalCnt == (compliantCnt+nonCompliantCnt)), 
							  "VERIFIED Non Compliant Count of records are - " + nonCompliantCnt, 
				  			  "Failed to verify Non Compliant Count of records");
		
	}
	
	@Test(description = "Record Signoff || Show Corrections || Verify Show Corrections button should be visible")
	public void TestCase_37062() {
		
		hp.clickFSQABrowserMenu();
		rsp = hp.clickRecordSignoffMenu();
		TestValidation.IsFalse(rsp.error, 
							  "NAVIGATED to Record Signoff page", 
				  			  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean correctnsBtnDisp1 = rsp.ShowCorrectnsBtn.isDisplayed();
		TestValidation.IsTrue(correctnsBtnDisp1, 
							  "VERIFIED Show corrections button is displayed", 
				  			  "Failed to verify Show corrections button is displayed");
		
		boolean clickResTab = rsp.clickResolvedTab();
		TestValidation.IsTrue(clickResTab, 
							  "CLICKED on Corrections > Resolved tab", 
				  			  "Failed to click on Corrections > Resolved tab");
		
		boolean correctnsBtnDisp2 = rsp.ShowCorrectnsBtn.isDisplayed();
		TestValidation.IsTrue(correctnsBtnDisp2, 
							  "VERIFIED Show corrections button is displayed", 
				  			  "Failed to verify Show corrections button is displayed");
		
		boolean clickUnresTab = rsp.clickUnresolvedTab();
		TestValidation.IsTrue(clickUnresTab, 
							  "CLICKED on Corrections > Unresolved tab", 
				  			  "Failed to click on Corrections > Unresolved tab");
		
		boolean correctnsBtnDisp3 = rsp.ShowCorrectnsBtn.isDisplayed();
		TestValidation.IsTrue(correctnsBtnDisp3, 
							  "VERIFIED Show corrections button is displayed", 
				  			  "Failed to verify Show corrections button is displayed");
		
	}
	
	@Test(description = "Record Signoff || Filtering by 'Completed On' should give the desired results")
	public void TestCase_37671() {
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectAllRecord = fbp.clickSelectAllChk();
		TestValidation.IsTrue(selectAllRecord, 
				 			  "SELECTED all records in grid", 
				 			  "Failed to select all records in grid");
		
		RecordSignoffPage rsp = fbp.clickSignRecordsBtn();
		TestValidation.Equals(false,
							  rsp.error, 
							  "NAVIGATED to Record Signoff page", 
							  "Failed to navigate to Record Signoff page");
		
		boolean appliedFilter = rsp.applyFilterBySearch(FILTERTYPES.COMPLETEDON,null);
		TestValidation.IsTrue(appliedFilter, 
							  "APPLIED Completed on Filter", 
				  			  "Failed to verify Show corrections button is displayed");
		
		String totalCount = rsp.TotalRecordCount.getText().trim();
		TestValidation.IsTrue((totalCount!=null || totalCount!=""), 
							  "VERIFIED Total Count of records are - " + totalCount, 
				  			  "Failed to verify Total Count of records");
		
		String compliantCount = rsp.CompliantRecsCount.getText().trim();
		TestValidation.IsTrue((compliantCount!=null || compliantCount!=""), 
							  "VERIFIED Compliant Count of records are - " + compliantCount, 
				  			  "Failed to verify Compliant Count of records");
		
		int totalCnt = Integer.parseInt(totalCount);
		int compliantCnt = Integer.parseInt(compliantCount);
		
		String nonCompliantCount = rsp.NonCompliantRecsCount.getText().trim();
		int nonCompliantCnt = Integer.parseInt(nonCompliantCount);
		TestValidation.IsTrue((totalCnt == (compliantCnt+nonCompliantCnt)), 
							  "VERIFIED Non Compliant Count of records are - " + nonCompliantCnt, 
				  			  "Failed to verify Non Compliant Count of records");
		
		String unsignedCount = rsp.UnsignedRecsCount.getText().trim();
		TestValidation.IsTrue((unsignedCount!=null || unsignedCount!=""), 
							  "VERIFIED Unsigned Count of records are - " + unsignedCount, 
				  			  "Failed to verify Unsigned Count of records");
		
		int unsignedCnt = Integer.parseInt(unsignedCount);
		
		String signedCount = rsp.SignedRecsCount.getText().trim();
		int signedCnt = Integer.parseInt(signedCount);
		TestValidation.IsTrue((totalCnt == (unsignedCnt+signedCnt)), 
							  "VERIFIED Signed Count of records are - " + signedCnt, 
				  			  "Failed to verify Signed Count of records");
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


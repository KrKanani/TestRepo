package com.project.safetychain.webapp.testcases;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage;
import com.project.safetychain.webapp.pageobjects.CommonPage.TIMEZONE;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;
import com.project.utilities.DateTime.ComparisonValue;

public class TCG_REG_Record_GenericFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	DateTime dt = new DateTime();
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		hp = new HomePage(driver);

		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
				
	}

	@Test(description = "Records || Edit || Submitting record gives an popup warning if no changes are made")
	public void TestCase_38331() {
		
		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");

		boolean openRecord = fbp.openForDetails(1);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record from grid", 
							  "Failed to open record from grid");

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean editRecord = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord, 
							  "CLICKED on edit record button", 
							  "Failed to click on edit record button");
		
		boolean submitRecord = frp.clickSubmitRecordBtn();
		TestValidation.IsTrue(submitRecord, 
							  "CLICKED on submit record button", 
							  "Failed to click on submit record button");
		
		String popupMsg = "One or more changes required to save the data";
		boolean verifyMsg = frp.NoChngeRecPopup.getText().contains(popupMsg);
		TestValidation.IsTrue(verifyMsg, 
							  "VERIFIED message displayed on the Information popup", 
							  "Failed to verify message displayed on the Information popup");
		
		boolean closePopup = frp.clickOkOnInfoPopup();
		TestValidation.IsTrue(closePopup, 
							  "CLOSED the Information popup", 
							  "Failed to close Information popup");
		
		boolean backToRecords = fbp.clickRecordsBreadCrumb();
		TestValidation.IsTrue(backToRecords, 
							  "NAVIGATED back to FSQABrowser > Records tab", 
							  "Failed to navigate back to FSQABrowser > Records tab");
	
	}
	
	@Test(description = "Records || Edit || Clicking on Cancel button navigates to record read only mode")
	public void TestCase_38332() {
		
		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");

		boolean openRecord = fbp.openForDetails(1);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record from grid", 
							  "Failed to open record from grid");

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean editRecord = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord, 
							  "CLICKED on edit record button", 
							  "Failed to click on edit record button");
		
		boolean cancelRecord = frp.clickCancelRecordBtn();
		TestValidation.IsTrue(cancelRecord, 
							  "CLICKED on cancel record button", 
							  "Failed to click on cancel record button");
		
		boolean verifyBtnDisplayed = controlActions.isElementDisplayed(frp.SubmitBtn);
		TestValidation.IsFalse(verifyBtnDisplayed, 
							  "VERIFIED record is displayed in Read mode", 
							  "Failed to verify record is displayed in Read mode");
		
	}
	
	@Test(description = "Records Tab - Verify Users can view or sign multiple selected records")
	public void TestCase_32230() {
		
		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectRecord1 = fbp.selectRecordByIndex(1);
		TestValidation.IsTrue(selectRecord1, 
							  "SELECTED record from grid at index 1", 
							  "Failed to select record from grid");
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(true,
							  frp.error, 
							  "View button is NOT DISPLAYED", 
							  "Failed to verify View button is displayed");	
		
		RecordSignoffPage rsp = fbp.clickSignRecordsBtn();
		TestValidation.Equals(true,
							  rsp.error, 
							  "Sign button is NOT DISPLAYED", 
							  "Failed to verify Sign button is displayed");
	
		boolean selectRecord2 = fbp.selectRecordByIndex(2);
		TestValidation.IsTrue(selectRecord2, 
							  "SELECTED record from grid at index 2", 
							  "Failed to select record from grid");
		
		frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(false,
							  frp.error, 
							  "NAVIGATED to FSQABrowser > Records tab > Record Viewer", 
							  "Failed to navigate to FSQABrowser > Records tab > Record Viewer");
		
		int headerDates = frp.compareCardLayoutHeaderDates();
		boolean verifyHeaderDates = (headerDates==2);
		if(verifyHeaderDates) {
			TestValidation.IsTrue(verifyHeaderDates, 
					  			  "VERIFIED records ordered by Completed On and in ascending order", 
					  			  "Could NOT verify records ordered by Completed On"); 
		}
		else if (headerDates==1) {
			boolean verifyRecordTimeAsc = frp.compareCardLayoutCompletedOnDates(2);
			TestValidation.IsTrue(verifyRecordTimeAsc, 
		  			  			  "VERIFIED records ordered by Time in ascending order", 
		  			  			  "Could NOT verify records ordered by Time in ascending order"); 
		}
		
		boolean backToRecords = fbp.clickRecordsBreadCrumb();
		TestValidation.IsTrue(backToRecords, 
							  "NAVIGATED back to FSQABrowser > Records tab", 
							  "Failed to navigate back to FSQABrowser > Records tab");
		
		boolean selectRecord3 = fbp.selectRecordByIndex(1);
		TestValidation.IsTrue(selectRecord3, 
							  "SELECTED record from grid at index 1", 
							  "Failed to select record from grid");
		
		boolean selectRecord4 = fbp.selectRecordByIndex(2);
		TestValidation.IsTrue(selectRecord4, 
							  "SELECTED record from grid at index 2", 
							  "Failed to select record from grid");
		
		rsp = fbp.clickSignRecordsBtn();
		TestValidation.Equals(false,
							  rsp.error, 
							  "NAVIGATED to Record Signoff page", 
							  "Failed to navigate to Record Signoff page");
		
		String totalCount = rsp.TotalRecordCount.getText().trim();
		TestValidation.IsTrue((totalCount.equals("2")), 
							  "VERIFIED Total Count of records are - " + totalCount, 
				  			  "Failed to verify Total Count of records");
		

		String compliantCount = rsp.CompliantRecsCount.getText().trim();
		TestValidation.IsTrue((compliantCount!=null || compliantCount!=""), 
							  "VERIFIED Compliant Count of records are - " + compliantCount, 
				  			  "Failed to verify Compliant Count of records");

		String noncompliantCount = rsp.NonCompliantRecsCount.getText().trim();
		TestValidation.IsTrue((noncompliantCount!=null || noncompliantCount!=""), 
							  "VERIFIED Compliant Count of records are - " + noncompliantCount, 
				  			  "Failed to verify Compliant Count of records");
		
		String signedRecCount = rsp.SignedRecsCount.getText().trim();
		TestValidation.IsTrue((signedRecCount!=null || signedRecCount!=""), 
							  "VERIFIED Signed Count of records are - " + signedRecCount, 
				  			  "Failed to verify Signed Count of records");
		
		String unsignedCount = rsp.UnsignedRecsCount.getText().trim();
		TestValidation.IsTrue((unsignedCount!=null || unsignedCount!=""), 
							  "VERIFIED Unsigned Count of records are - " + unsignedCount, 
				  			  "Failed to verify Unsigned Count of records");
	}
	
	@Test(description = "Records || Date Range || For column Completed On, there no longer should be filter provision")
	public void TestCase_40814() {
		
		try {
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, 
								  "NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
	
			boolean applyfilter = fbp.clickColumnDropDown(COLUMNHEADER.COMPLETEDON);
			TestValidation.IsTrue(applyfilter, 
								   "OPENED column settings for " + COLUMNHEADER.COMPLETEDON, 
								   "Failed to open column settings for " + COLUMNHEADER.COMPLETEDON);
			
			List<WebElement> PopupOptionMnu = controlActions.perform_GetListOfElementsByXPath(FSQABrowserPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION", COLUMNSETTING.FILTER);
			TestValidation.IsTrue(PopupOptionMnu.size()==0, 
								  "Should NOT display filter settings for " + COLUMNHEADER.COMPLETEDON, 
								  "Failed to verify whether filter setting is or is not displayed for " + COLUMNHEADER.COMPLETEDON);
			
			hp.SCLogo.click();
			boolean applySetting = fbp.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDON,COLUMNSETTING.SORTASCENDING, null);
			TestValidation.IsTrue(applySetting, 
								  "APPLIED ascending order sort to records",
								  "Failed to apply ascending order sort to records");
			
			String ascDate = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON,1);
			TestValidation.IsTrue(ascDate!=null, 
								  "The ascending date found is " + ascDate, 
								  "Failed to get ascending date instead found " + ascDate);
	
			String ascDateFromSC[] = CommonMethods.splitAndGetString(ascDate, " ");
			String dateFormat = "MM/dd/yyyy";
			String currentDate = dt.getTodayDate(dateFormat, TIMEZONE.UTC);
			String updatedDate = dt.addSubtractDaysFromDate(dt.getDate(currentDate, dateFormat),-30, dateFormat);
	
			ComparisonValue comparisonVal = dt.compareDates(ascDateFromSC[0], updatedDate, null, null);
			TestValidation.IsTrue((comparisonVal!=ComparisonValue.LESSER), 
								  "VERIFIED that records of last 30 days are displayed", 
								  "Failed to verify that the records of last 30 days are displayed");
		}
		finally {
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			if(fbp.navigateToFSQATab(FSQATAB.RECORDS))
				fbp.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDON, COLUMNSETTING.SORTDESCENDING, null);
		}
	}
	
	@Test(description = "Records || Date Range || UI Design")
	public void TestCase_40819() {
		
		String fromDate = "2/9/2021";
		String toDate = "2/10/2021";
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean openDateRangePopup = fbp.clickDateRangeCalendarIcon();
		TestValidation.IsTrue(openDateRangePopup, 
							  "OPENED Date Range's popup", 
							  "Failed to open Date Range's popup");
		
		boolean setFromDate1 = fbp.setDateRangeFromText(fromDate);
		TestValidation.IsTrue(setFromDate1, 
							  "Date Range's from textbox set to " + fromDate, 
							  "Failed to set Date Range's from textbox to " + fromDate);
		
		boolean clickFromLabel1 = fbp.clickDateRangeFromLabel();
		boolean verifyFilterBtn1 = fbp.DateRangeFilterBtn.getAttribute("class").contains("disable");
		TestValidation.IsTrue(clickFromLabel1 && verifyFilterBtn1, 
							  "Date Range's filter button is DISABLED", 
							  "Failed to verify whether Date Range's filter button is disabled or not");
		
		fbp.DateRangeFromTxt.clear();
		boolean setToDate = fbp.setDateRangeToText(toDate);
		TestValidation.IsTrue(setToDate, 
							  "Date Range's to textbox set to " + toDate, 
							  "Failed to set Date Range's to textbox to " + toDate);
		
		boolean clickFromLabel2 = fbp.clickDateRangeFromLabel();
		boolean verifyFilterBtn2 = fbp.DateRangeFilterBtn.getAttribute("class").contains("disable");
		TestValidation.IsTrue(clickFromLabel2 && verifyFilterBtn2, 
							  "Date Range's filter button is DISABLED", 
							  "Failed to verify whether Date Range's filter button is disabled or not");
		
		boolean setFromDate2 = fbp.setDateRangeFromText(fromDate);
		TestValidation.IsTrue(setFromDate2, 
							  "Date Range's from textbox set to " + fromDate, 
							  "Failed to set Date Range's from textbox to " + fromDate);
		
		boolean clickFromLabel3 = fbp.clickDateRangeFromLabel();
		boolean verifyFilterBtn3 = fbp.DateRangeFilterBtn.getAttribute("class").contains("disable");
		TestValidation.IsTrue(clickFromLabel3 && !verifyFilterBtn3, 
							  "Date Range's filter button is ENABLED", 
							  "Failed to verify whether Date Range's filter button is enabled or not");
		
	}
		
	@Test(description = "Records || Date Range || Apply filter")
	public void TestCase_40827() {
		
		String dateFormat = "MM/dd/yyyy";
		String currentDate = dt.getTodayDate(dateFormat, TIMEZONE.UTC);
		String fromDate = dt.addSubtractDaysFromDate(dt.getDate(currentDate, dateFormat),-16, dateFormat);
		String toDate = dt.addSubtractDaysFromDate(dt.getDate(currentDate, dateFormat),-10, dateFormat);
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean applyFilter = fbp.applyDateRangeFilter(fromDate, toDate);
		TestValidation.IsTrue(applyFilter, 
							  "APPLIED Date Range's From date as - " + fromDate + " and To date as - " + toDate, 
							  "Failed to apply Date Range's From date as - " + fromDate + " and To date as - " + toDate);
		
		boolean clearLinkDisplayed = controlActions.isElementDisplayed(fbp.DateRangeClearLnk);
		TestValidation.IsTrue(clearLinkDisplayed, 
							  "Clear link is DISPLAYED", 
							  "Failed to verify whether clear link is displayed or not");
		
	}
	
	@Test(description = "Records || Date Range || Clear link functionality")
	public void TestCase_40825() {
		
		try {
			
			String dateFormat = "MM/dd/yyyy";
			String currentDate = dt.getTodayDate(dateFormat, TIMEZONE.UTC);
			String fromDate = dt.addSubtractDaysFromDate(dt.getDate(currentDate, dateFormat),-16, dateFormat);
			String toDate = dt.addSubtractDaysFromDate(dt.getDate(currentDate, dateFormat),-10, dateFormat);
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, 
								  "NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
			
			boolean applySetting = fbp.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDON,COLUMNSETTING.SORTASCENDING, null);
			TestValidation.IsTrue(applySetting, 
								  "APPLIED ascending order sort to records",
								  "Failed to apply ascending order sort to records");
			
			String ascDateBefore = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON,1);
			TestValidation.IsTrue(ascDateBefore!=null, 
								  "The ascending date found is " + ascDateBefore, 
								  "Failed to get ascending date instead found " + ascDateBefore);
			
			boolean applyFilter = fbp.applyDateRangeFilter(fromDate, toDate);
			TestValidation.IsTrue(applyFilter, 
								  "APPLIED Date Range's From date as - " + fromDate + " and To date as - " + toDate, 
								  "Failed to apply Date Range's From date as - " + fromDate + " and To date as - " + toDate);
			
			String appliedFilterDate = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON,1);
			TestValidation.IsTrue(appliedFilterDate!=null, 
								  "The date found after filter is " + appliedFilterDate, 
								  "Failed to get date after filter is applied");
			
			String appliedDateFromSC[] = CommonMethods.splitAndGetString(appliedFilterDate, " ");
			ComparisonValue comparisonVal = dt.compareDates(appliedDateFromSC[0], fromDate, null, null);
			TestValidation.IsTrue((comparisonVal!=ComparisonValue.LESSER), 
								  "VERIFIED that displayed records are as per applied filter", 
								  "Failed to verify that displayed records are as per applied filter");
			
			boolean clickedClearLink = fbp.clickDateRangeClearLink();
			TestValidation.IsTrue(clickedClearLink, 
								  "CLICKED Clear link", 
								  "Failed to click on Clear link");
			
			String ascDateAfterClear = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON,1);
			TestValidation.IsTrue(ascDateBefore.equalsIgnoreCase(ascDateAfterClear), 
								  "The ascending date found after clearing filter is " + ascDateBefore, 
								  "Failed to get correct ascending date instead found " + ascDateBefore);

		}
		finally {
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			if(fbp.navigateToFSQATab(FSQATAB.RECORDS))
				fbp.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDON, COLUMNSETTING.SORTDESCENDING, null);
		}
	}
	
	@Test(description = "Records || Date Range || Popup Clear button functionality")
	public void TestCase_40826() {
		
		try {
			
			String dateFormat = "MM/dd/yyyy";
			String currentDate = dt.getTodayDate(dateFormat, TIMEZONE.UTC);
			String fromDate = dt.addSubtractDaysFromDate(dt.getDate(currentDate, dateFormat),-16, dateFormat);
			String toDate = dt.addSubtractDaysFromDate(dt.getDate(currentDate, dateFormat),-10, dateFormat);
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, 
								  "NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
			
			boolean applySetting = fbp.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDON,COLUMNSETTING.SORTASCENDING, null);
			TestValidation.IsTrue(applySetting, 
								  "APPLIED ascending order sort to records",
								  "Failed to apply ascending order sort to records");
			
			String ascDateBefore = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON,1);
			TestValidation.IsTrue(ascDateBefore!=null, 
								  "The ascending date found is " + ascDateBefore, 
								  "Failed to get ascending date instead found " + ascDateBefore);
			
			boolean applyFilter = fbp.applyDateRangeFilter(fromDate, toDate);
			TestValidation.IsTrue(applyFilter, 
								  "APPLIED Date Range's From date as - " + fromDate + " and To date as - " + toDate, 
								  "Failed to apply Date Range's From date as - " + fromDate + " and To date as - " + toDate);
			
			String appliedFilterDate = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON,1);
			TestValidation.IsTrue(appliedFilterDate!=null, 
								  "The date found after filter is " + appliedFilterDate, 
								  "Failed to get date after filter is applied");
			
			String appliedDateFromSC[] = CommonMethods.splitAndGetString(appliedFilterDate, " ");
			ComparisonValue comparisonVal = dt.compareDates(appliedDateFromSC[0], fromDate, null, null);
			TestValidation.IsTrue((comparisonVal!=ComparisonValue.LESSER), 
								  "VERIFIED that displayed records are as per applied filter", 
								  "Failed to verify that displayed records are as per applied filter");
			
			boolean openDateRangePopup1 = fbp.clickDateRangeCalendarIcon();
			TestValidation.IsTrue(openDateRangePopup1, 
								  "OPENED Date Range's popup", 
								  "Failed to open Date Range's popup");
			
			boolean clearDateRange1 = fbp.clickDateRangeClearBtn();
			TestValidation.IsTrue(clearDateRange1, 
								  "CLICKED Clear button", 
								  "Failed to click on Clear button");
			
			String ascDateAfterClear = fbp.getGridValueForColumn(COLUMNHEADER.COMPLETEDON,1);
			TestValidation.IsTrue(ascDateBefore.equalsIgnoreCase(ascDateAfterClear), 
								  "The ascending date found after clearing filter is " + ascDateBefore, 
								  "Failed to get correct ascending date instead found " + ascDateBefore);

			boolean openDateRangePopup2 = fbp.clickDateRangeCalendarIcon();
			TestValidation.IsTrue(openDateRangePopup2, 
								  "OPENED Date Range's popup", 
								  "Failed to open Date Range's popup");
			
			boolean setFromDate = fbp.setDateRangeFromText(fromDate);
			TestValidation.IsTrue(setFromDate, 
								  "Date Range's from textbox set to " + fromDate, 
								  "Failed to set Date Range's from textbox to " + fromDate);
			
			boolean setToDate = fbp.setDateRangeToText(toDate);
			TestValidation.IsTrue(setToDate, 
								  "Date Range's to textbox set to " + toDate, 
								  "Failed to set Date Range's to textbox to " + toDate);
			
			boolean clickFromLabel = fbp.clickDateRangeFromLabel();
			boolean verifyFilterBtn = fbp.DateRangeClearBtn.getAttribute("class").contains("disable");
			TestValidation.IsTrue(clickFromLabel && !verifyFilterBtn, 
								  "Date Range's clear button is ENABLED", 
								  "Failed to verify whether Date Range's clear button is enabled or not");
			
			boolean clearDateRange2 = fbp.clickDateRangeClearBtn();
			TestValidation.IsTrue(clearDateRange2, 
								  "CLICKED Clear button", 
								  "Failed to click on Clear button");
			
			boolean openDateRangePopup3 = fbp.clickDateRangeCalendarIcon();
			TestValidation.IsTrue(openDateRangePopup3, 
								  "OPENED Date Range's popup", 
								  "Failed to open Date Range's popup");
			
			boolean verifyClearFromTxt = fbp.DateRangeFromTxt.getText().equals("");
			TestValidation.IsTrue(verifyClearFromTxt, 
								  "CLEARED Date range's From textbox", 
								  "Failed to clear Date range's From textbox");
			
			boolean verifyClearToTxt = fbp.DateRangeToTxt.getText().equals("");
			TestValidation.IsTrue(verifyClearToTxt, 
								  "CLEARED Date range's From textbox", 
								  "Failed to clear Date range's From textbox");
			
		}
		finally {
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			if(fbp.navigateToFSQATab(FSQATAB.RECORDS))
				fbp.openAndApplySettingsForColumn(COLUMNHEADER.COMPLETEDON, COLUMNSETTING.SORTDESCENDING, null);
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

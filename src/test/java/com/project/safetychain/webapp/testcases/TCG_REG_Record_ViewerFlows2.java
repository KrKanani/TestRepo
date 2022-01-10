package com.project.safetychain.webapp.testcases;

import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.GROUPBY_OPTION;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.RecordFormDetails;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;


public class TCG_REG_Record_ViewerFlows2 extends TestBase{
	
	ControlActions controlActions;
	public static String formName;
	public static String voidRecCmmt;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String signOffCmmt;
	public static String displayName = null;
	public static String usrTimezone = null;
	public static String timezoneCode = null;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("RViewer");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");	
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");	
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		signOffCmmt = CommonMethods.dynamicText("Cmmt1");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		
		LoginPage lp = new LoginPage(driver);
		HomePage hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		displayName = hp.getLoggedInUserDetails();
		if(displayName == ""){
			TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		UserManagerPage ump = hp.clickUsersMenu();
		if(!ump.searchUsrWithDetails(USERFIELDS.USERNAME, adminUsername)) {
			TCGFailureMsg = "Failed to search user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		usrTimezone = ump.getUsrDetails(USERFIELDS.TIMEZONE);
		if(usrTimezone == ""){
			TCGFailureMsg = "Failed to get timezone for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		timezoneCode = ump.getTimezoneCode(usrTimezone);
		if(timezoneCode == ""){
			TCGFailureMsg = "Failed to convert timezone - " + usrTimezone+ " for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		hp.clickFSQABrowserMenu();
		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Location instance
		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();
		if(!mlp.createLocation(lip)) {
			TCGFailureMsg = "Could NOT create Location instance";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Resource creation
		HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
		instances.put(customersInstanceValue, true);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = customersCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;	
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + customersCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		FormDesignerPage fdp = new FormDesignerPage(driver);
		if(!fdp.createAndReleaseForm("Check", formName,"Customers", customersCategoryValue,
				customersInstanceValue)) {
			TCGFailureMsg = "Could NOT create and release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS)) {
			TCGFailureMsg = "For customer category, could NOT navigate to FSQABrowser > Forms tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	
		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName)) {
			TCGFailureMsg = "Could not filter form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
		if(!ffp.submitData(false, false, false, true,false)) {
			TCGFailureMsg = "Could NOT submit form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description = "9043 : Multi-Record Signoff - Record Viewer - Users can search the card list using "
			+ "universal search")
	public void TestCase_16471() {
		
		RecordFormDetails rfd = new RecordFormDetails();
		rfd.FormName = formName;
		rfd.Location = locationInstanceValue;
		rfd.Resource = customersInstanceValue;
		
		FSQABrowserPage fbp = new FSQABrowserPage(driver);
		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
	  			  			  "NAVIGATED to FSQA Records page", 
	  			  			  "Could NOT navigate to FSQA Records page");
		
		boolean selectAll = fbp.clickSelectAllChk();
		TestValidation.IsTrue(selectAll, 
				  			  "CLICKED on Select All records checkbox successfully", 
				  			  "Could NOT click on Select All checkbox"); 
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(false,
							  frp.error, 
							  "NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean searchByRes = frp.searchAndSelectRecordInViewSignMode(customersInstanceValue);
		TestValidation.IsTrue(searchByRes, 
				  			  "SEARCHED record for form - " + formName + " using resource - " + customersInstanceValue, 
				  			  "Failed to search record for form - " + formName);
		
		boolean verifyForm1Details = frp.verifyRecordsDetailsInView(rfd);
		TestValidation.IsTrue(verifyForm1Details, 
				  			  "VERIFIED record details for form - " + rfd.FormName, 
				  			  "Failed to verify record details for form - " + rfd.FormName);
		
		boolean searchByRandomTxt = frp.searchAndSelectRecordInViewSignMode("XYZ any text");
		TestValidation.IsFalse(searchByRandomTxt, 
				  			  "NO RECORD displayed for random text input", 
				  			  "Failed to verify record should not be displayed for random text input");
		
		boolean searchByName = frp.searchRecordInViewSignMode(displayName);
		TestValidation.IsTrue(searchByName, 
				  			  "SEARCHED records using name - " + displayName, 
				  			  "Failed to search records using name - " + displayName);
		
		boolean selectByFormNm = frp.searchAndSelectRecordInViewSignMode(formName);
		TestValidation.IsTrue(selectByFormNm, 
				  			  "SELECTED record for form - " + formName + " using name - " + displayName, 
				  			  "Failed to select record for form - " + formName);
		
		boolean verifyForm2Details = frp.verifyRecordsDetailsInView(rfd);
		TestValidation.IsTrue(verifyForm2Details, 
				  			  "VERIFIED record details for form - " + rfd.FormName, 
				  			  "Failed to verify record details for form - " + rfd.FormName);
		
		frp.SearchTxt.clear();
		boolean clearSearch = frp.clickSearchBtn();
		String topPanelDetails = frp.RViewerRecordsTitle.getText();
		int recordCount = frp.RViewerRecordCards.size();
		boolean verifyCount = topPanelDetails.contains(Integer.toString(recordCount));
		TestValidation.IsTrue(clearSearch && verifyCount, 
	  			  			  "Search returns an UNFILTERED record set when the search field is cleared", 
	  			  			  "Could NOT verify for unfiltered record set when search field is cleared"); 
	}

	@Test(description = "FSQA Browser || Records || Verify the slide-in page when user clicks on the sign-off logo "
			+ "for viewing the record sign-off history.")
	public void TestCase_30370() {
		
		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + formName, 
							  "Failed to open record for form - " + formName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean signOffRecord = frp.signoffRecord(signOffCmmt);
		TestValidation.IsTrue(signOffRecord, 
							  "SIGNED record for form - " + formName, 
							  "Failed to sign record for form " + formName);
		
		boolean backToRecords = fbp.clickRecordsBreadCrumb();
		TestValidation.IsTrue(backToRecords, 
							  "For customer category, NAVIGATED back to FSQABrowser > Records tab", 
							  "Failed to navigate back to FSQABrowser > Records tab");

		String signDetails = fbp.clickRecordsSignIcon();
		boolean verifyName = signDetails.contains("Signed by " + displayName);
		boolean verifyTimezone = signDetails.contains(timezoneCode);
		TestValidation.IsTrue(verifyName && verifyTimezone, 
							  "VERIFIED details for signed record - " + formName, 
							  "Failed to verify details for signed record - " + formName);


		boolean closePopup = fbp.closeRecordSignoffPopup();
		TestValidation.IsTrue(closePopup, 
							  "CLOSED Signoff History popup", 
							  "Failed to close Signoff History popup");
	}
	
	@Test(description = "Multi-Record Signoff - Record Viewer - Users can modify the way records "
			+ "are grouped in the card list(9046)")
	public void TestCase_16738() {
		
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
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(false,
							  frp.error, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean openGroupByMenu = frp.clickGroupByInRecViewer();
		TestValidation.IsTrue(openGroupByMenu, 
	  			  			  "CLICKED on the Group By menu button", 
	  			  			  "Could NOT click on the Group By menu button"); 
		
		boolean verifyGroupTitle = frp.RViewerGroupbyMnuTitle.getText().contains("Group By");
		TestValidation.IsTrue(verifyGroupTitle, 
	  			  			  "VERIFIED menu title is Group By", 
	  			  			  "Could NOT verify menu title"); 
		
		boolean verifyDivider = controlActions.isElementDisplayed(frp.RViewerGroupbyDivider);
		TestValidation.IsTrue(verifyDivider, 
	  			  			  "VERIFIED the presence of horizontal divider line in menu", 
	  			  			  "Could NOT verify the presence of horizontal divider line in menu");
		
		boolean verifyResource = frp.RViewerGroupbyLst.get(0).getText().contains("Resource");
		boolean verifyFormNm = frp.RViewerGroupbyLst.get(1).getText().contains("Form Name");
		boolean verifyCmpltdBy = frp.RViewerGroupbyLst.get(2).getText().contains("Completed By");
		boolean verifyCmpltdOn = frp.RViewerGroupbyLst.get(3).getText().contains("Completed On");
		TestValidation.IsTrue((verifyResource && verifyFormNm && verifyCmpltdBy && verifyCmpltdOn), 
	  			  			  "VERIFIED options in the menu are - Resource, Form Name, Completed By and Completed On", 
	  			  			  "Could NOT verify options in th menu"); 
		
		boolean verifyDefaultSelectn = frp.RViewerGroupbySlctdOptn.getText().contains("Completed On");
		TestValidation.IsTrue(verifyDefaultSelectn, 
	  			  			  "VERIFIED menu title is Group By", 
	  			  			  "Could NOT verify menu title"); 
		
		boolean setByResource = frp.selectAndSetGroupByOption(GROUPBY_OPTION.RESOURCE);
		TestValidation.IsTrue(setByResource, 
	  			  			  "Records are GROUPED BY Resource", 
	  			  			  "Could NOT group records by Resource"); 
		
		boolean verifyHeaderDetail1 = frp.verifyHeaderDetail(customersInstanceValue);
		TestValidation.IsTrue(verifyHeaderDetail1, 
	  			  			  "VERIFIED header detail for Resource - " + customersInstanceValue, 
	  			  			  "Could NOT verify header detail for Resource"); 
		
		boolean setByFormNm = frp.selectAndSetGroupByOption(GROUPBY_OPTION.FORMNAME);
		TestValidation.IsTrue(setByFormNm, 
	  			  			  "Records are GROUPED BY Form name", 
	  			  			  "Could NOT group records by Form name"); 
		
		boolean verifyHeaderDetail2 = frp.verifyHeaderDetail(formName);
		TestValidation.IsTrue(verifyHeaderDetail2, 
	  			  			  "VERIFIED header detail for Form name - " + formName, 
	  			  			  "Could NOT verify header detail for Form name"); 
		
		boolean setByCmpltdBy = frp.selectAndSetGroupByOption(GROUPBY_OPTION.COMPLETED_BY);
		TestValidation.IsTrue(setByCmpltdBy, 
	  			  			  "Records are GROUPED BY Completed by", 
	  			  			  "Could NOT group records by Completed by"); 
		
		boolean verifyHeaderDetail3 = frp.verifyHeaderDetail(displayName);
		TestValidation.IsTrue(verifyHeaderDetail3, 
	  			  			  "VERIFIED header detail for Completed by - " + displayName, 
	  			  			  "Could NOT verify header detail for Completed by"); 
		
	}
	
	@Test(description = "Verify record edit functionality when form is having only One resource while editing the resource.")
	public void TestCase_30520() {
		
		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + formName, 
							  "Failed to open record for form - " + formName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean editRecord = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord, 
							  "EDITED record for form - " + formName, 
							  "Failed to edit for form " + formName);
		
		boolean editRsrcMode = frp.enableEditResource(); 
		TestValidation.IsTrue(editRsrcMode, 
			  		   		  "ENTERED Edit resource mode for record", 
			  		   		  "Failed to enter Edit resource mode for record");
		
		boolean compareRsrcList = (frp.ResrcDropdwnLst.size() == 1);
		TestValidation.IsTrue(compareRsrcList,
		   		  			  "VERIFIED 1 resource found for the record", 
		   		  			  "Failed since resource count found for the record - " + frp.ResrcDropdwnLst.size());

		boolean submitRec = frp.clickSubmitRecordBtn();
		TestValidation.IsFalse(submitRec, 
							  "VERIFIED unable to submit record if no resource is changed", 
							  "Failed to verify submit record works/does not work if no resource is changed");
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


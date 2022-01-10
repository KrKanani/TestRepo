package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.HashMap;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.FILTERTYPES;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.pageobjects.VerificationsPage;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;


public class TCG_REG_Record_VerficationFlows extends TestBase{
	
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
	public static String verificationName;
	public static String verificationComment1;
	public static String verificationComment2;
	public static String rolename = "SuperAdmin";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("Verificatn");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");	
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");	
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		signOffCmmt = CommonMethods.dynamicText("Cmmt1");
		verificationName = CommonMethods.dynamicText("AutoVN");
		verificationComment1 = CommonMethods.dynamicText("AutoComment1");
		verificationComment2 = CommonMethods.dynamicText("AutoComment2");

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
		
		VerificationsPage vp = hp.clickVerificationsMenu();			  
		VerificationDetsParams vdp = new VerificationDetsParams();
		vdp.VerificationName = verificationName;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.AddComments.add(verificationComment2);
		vdp.Rolename = rolename;
		if (!vp.createVerification(vdp)){
			TCGFailureMsg = "Could NOT able to create verification"; 
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg); 
		}

		FormsManagerPage fmp = hp.clickFormsManagerMenu();
		if(!fmp.linkVerificationwithForm(verificationName,formName)) {
			TCGFailureMsg = "Could NOT Link verification " + verificationName + " to form " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description = "Record Signoff || Review Records || Sign Records || Verify the slide-in page when user "
			+ "clicks on the sign-off logo for viewing the record sign-off history.")
	public void TestCase_30374() {
		
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
		boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName,verificationComment1);
		TestValidation.IsTrue(signOffRecord, 
							  "SIGNED record for form - " + formName, 
							  "Failed to sign record for form " + formName);
		
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.Equals(false,
				  			  rsp.error, 
				  			  "NAVIGATED to Record Signoff page", 
				  		 	  "Failed to navigate to Record Signoff page");
		
		boolean closeMsgPopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closeMsgPopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName);
		TestValidation.IsTrue(applyFilter, 
							  "APPLIED filter for forms for form - " + formName, 
				  			  "Failed to apply filter for forms for form - " + formName);

		boolean clickNextBtn = rsp.clickNextBtn();
		TestValidation.IsTrue(clickNextBtn, 
	  			  			  "CLICKED on Next button", 
				  			  "Failed to click on Next button");
		
		String signDetails = rsp.clickRecordsSignIcon();
		boolean verifyName = signDetails.contains(verificationName + " by " + displayName);
		boolean verifyTimezone = signDetails.contains(timezoneCode);
		TestValidation.IsTrue(verifyName && verifyTimezone, 
							  "VERIFIED details for signed record - " + formName, 
							  "Failed to verify details for signed record - " + formName);

		boolean closeSignoffPopup = rsp.closeRecordSignoffPopup();
		TestValidation.IsTrue(closeSignoffPopup, 
							  "CLOSED Signoff History popup", 
							  "Failed to close Signoff History popup");
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


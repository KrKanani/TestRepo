package com.project.safetychain.webapp.testcases;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;


public class TCG_REG_Record_ViewerFlows3 extends TestBase{
	
	ControlActions controlActions;
	LoginPage lp;
	HomePage hp;
	RecordSignoffPage rsp;
	FSQABrowserPage fbp;
	public static String formName;
	public static String voidRecCmmt;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String numFieldName = "Numeric";
	public static String documentName = "upload.png";
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
	public static String displayName = null;
	public static String usrTimezone = null;
	public static String timezoneCode = null;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("RViewr");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");	
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");	
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		rsp = new RecordSignoffPage(driver);
		fbp = new FSQABrowserPage(driver);
				
		hp = lp.performLogin(adminUsername, adminPassword);
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

		//Resource creation and linking
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
		
		//FORM - Creation and Release
		HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldName));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;

		HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
		resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(customersCategoryValue));

		FormDesignParams fdpDets = new FormDesignParams();
		fdpDets.FormType = FORMTYPES.CHECK;
		fdpDets.FormName = formName;
		fdpDets.SelectResources = resource;
		fdpDets.DesignFields = Arrays.asList(ffp);
		fdpDets.ReleaseForm = false;

		FormDesignerPage fdp = hp.clickFormDesignerMenu();
		if(!fdp.createAndReleaseForm(fdpDets)) {
			TCGFailureMsg = "Could NOT create unreleased form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if (!fdp.navigateToReleaseForm_EditForm(formName)) {
			TCGFailureMsg = "Could NOT open edit form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		ElementProperties settings = new ElementProperties();
		settings.COMPLIANCE_CONFIG = true;
		settings.COMPLIANCE_VALUES = new String[] { fieldMin, fieldTar, fieldMax, null };
		settings.ALLOW_CORRECTION = true;
		settings.MARK_RESOLVED = true;
		settings.ALLOW_ATTATHMENTS = true;
		
		HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>();
		fieldSettings.put(numFieldName, settings);

		if (!fdp.setFieldProperties(fieldSettings)) {
			TCGFailureMsg = "Could NOT set compliance properties for field - " + numFieldName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if (!fdp.releaseForm(formName)) {
			TCGFailureMsg = "Could NOT release form - " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		hp.clickFSQABrowserMenu();
		if(!fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.CUSTOMERS,FSQATAB.FORMS)) {
			TCGFailureMsg = "Could NOT navigate to FSQA Browser > Customers > Records tab";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);			
		}
		
		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName)) {
			TCGFailureMsg = "Could NOT open form " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);				
		}

		HashMap<String, List<String>> fillData1 = new HashMap<String, List<String>>();
		fillData1.put(numFieldName,Arrays.asList("5"));
		
		//Field Property objects
		FieldProperties fp1 = new FieldProperties();
		fp1.fieldName = numFieldName;
		fp1.allowCorrectionsCheck = true;
		fp1.allowCorrectionsValue = "Test Value 1";
		fp1.isResolvedCheck = true;
		fp1.isResolvedStatus = "Yes";
		fp1.allowAttachmenstCheck = true;
		fp1.uploadFilePath = filePath;
		fp1.complianceStatusCheck = true;
		fp1.complianceStatus = "Non-Compliant";

		//Form object
		FormDetails fd1 = new FormDetails();
		//Form level attachment
		fd1.formLevelAttachmenttCheck = true;
		fd1.fieldDetails = fillData1;
		//Field property assignment  
		fd1.fieldProperties = Arrays.asList(fp1);

		FormOperations fo = new FormOperations(driver);
		if(!fo.submitData(fd1)) {
			TCGFailureMsg = "Could NOT enter data and submit form " + formName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);			
		}
		
	}

	@Test(description = "Multi-Record Signoff - Record Viewer - Details of each record are shown in a card layout")
	public void TestCase_16466() {
		
		HomePage hp = new HomePage(driver);
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.CUSTOMERS,FSQATAB.RECORDS);
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
		
		boolean selectByFormNm = frp.searchAndSelectRecordInViewSignMode(formName);
		TestValidation.IsTrue(selectByFormNm, 
				  			  "SELECTED record for form - " + formName, 
				  			  "Failed to select record for form - " + formName);
		
		DateTime dt = new DateTime(driver);
		String currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
		boolean verifyDate = frp.RViewerCardUsrnmDets.getText().contains(currentDate);
		boolean verifyName = frp.RViewerCardUsrnmDets.getText().contains(displayName);
		TestValidation.IsTrue((verifyDate && verifyName), 
				  			  "VERIFIED 1st row's card details for form - " + formName, 
				  			  "Failed to verify 1st row's card details for form - " + formName);
		
		boolean verifyCompliance = !frp.RViewerCompliantIcon.getAttribute("class").contains("hide");
		boolean verifyFormNm = frp.RViewerCardFormDets.getText().contains(formName);
		TestValidation.IsTrue((verifyCompliance && verifyFormNm), 
				  			  "VERIFIED 2nd row's card details for form - " + formName, 
				  			  "Failed to verify 2nd row's card details for form - " + formName);
		
		boolean verifyResrc = frp.RViewerCardResrceDets.getText().contains(customersInstanceValue);
		boolean verifyAttchmnt = controlActions.isElementDisplayed(frp.RViewerAttchmntIcon);
		boolean verifySign = controlActions.isElementDisplayed(frp.RViewerSignIcon);
		TestValidation.IsTrue((verifyResrc && !verifyAttchmnt && verifySign), 
				  			  "VERIFIED 3rd row's card details for form - " + formName, 
				  			  "Failed to verify 3rd row's card details for form - " + formName);
		
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


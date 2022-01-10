	package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormSubmission_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ProgramDesignerPage;
import com.project.safetychain.webapp.pageobjects.ProgramViewerPage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage;
import com.project.safetychain.webapp.pageobjects.UserManagerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FIELDTYPE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FORMFIELDS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.RecordFormDetails;
import com.project.safetychain.webapp.pageobjects.ProgramViewerPage.PGVIEWTABS;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.FILTERTYPES;
import com.project.safetychain.webapp.pageobjects.UserManagerPage.USERFIELDS;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;


public class TCG_SMK_Record_ViewAndSignFlow extends TestBase{
	
	ControlActions controlActions;
	public static String formName1;
	public static String formName2;
	public static String signOffCmmt1;
	public static String signOffCmmt2;
	public static String signOffAllCmmt;
	public static String submitRecordCmmt;
	public static String locationCategoryValue;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String locationInstanceValue;
	public static String displayName = null;
	public static String usrTimezone = null;
	public static String timezoneCode = null;
	public static String numericFN = "Numeric";
	public static String programName; 
	public static String programElementName;
	public static List<String> formsNMLst = new ArrayList<String>();
	

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName1 = CommonMethods.dynamicText("Chk1");
		formName2 = CommonMethods.dynamicText("Chk2");
		signOffCmmt1 = CommonMethods.dynamicText("Cmmt1");
		signOffCmmt2 = CommonMethods.dynamicText("Cmmt2");
		signOffAllCmmt = CommonMethods.dynamicText("AllCmmt");
		submitRecordCmmt = CommonMethods.dynamicText("SubCmmt");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		programName = CommonMethods.dynamicText("PD");
		programElementName = CommonMethods.dynamicText("PE");
		formsNMLst.add(formName1);
		formsNMLst.add(formName2);

		// ------------------------------------------------------------------------------------------------
		// API Implementation
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_FormSubmission_Wrapper formSubmissionWrapper = new TCG_FormSubmission_Wrapper();
		ApiUtils apiUtils = new ApiUtils();

		// ------------------------------------------------------------------------------------------------
		// API - Location & Resource Creation and Linking
		List<String> userList = Arrays.asList(adminUsername);

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstanceValue));

		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check
		// API - Add fields to form
		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = numericFN;

		// API - Form details
		// API - Generate unique ID for form to be created
		String formId1 = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpForm1 = new FormParams();
		fpForm1.FormId = formId1;
		fpForm1.FormName = formName1;
		fpForm1.type = DPT_FORM_TYPES.CHECK;
		fpForm1.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpForm1.ResourceCategoryNm = customersCategoryValue;
		fpForm1.ResourceInstanceNm = customersInstanceValue;
		fpForm1.formElements = Arrays.asList(numericField);
		fpForm1.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fpForm1);

		// ------------------------------------------------------------------------------------------------
		// API - Submit form

		// API - Add values to fields in form
		FormFieldParams submitNumericField = new FormFieldParams();
		submitNumericField.Name = numericFN;
		submitNumericField.Value = "20";

		// API - Form layout details
		FormParams fpSubmitForm1 = new FormParams();
		fpSubmitForm1.FormName = formName1;
		fpSubmitForm1.ResourceInstanceNm = customersInstanceValue;
		fpSubmitForm1.LocationInstanceNm = locationInstanceValue;
		fpSubmitForm1.formElements = Arrays.asList(submitNumericField);

		formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmitForm1);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check

		// API - Form details
		// API - Generate unique ID for form to be created
		String formId2 = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpForm2 = new FormParams();
		fpForm2.FormId = formId2;
		fpForm2.FormName = formName2;
		fpForm2.type = DPT_FORM_TYPES.CHECK;
		fpForm2.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpForm2.ResourceCategoryNm = customersCategoryValue;
		fpForm2.ResourceInstanceNm = customersInstanceValue;
		fpForm2.formElements = Arrays.asList(numericField);
		fpForm2.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fpForm2);

		// ------------------------------------------------------------------------------------------------
		// API - Submit form

		// API - Form layout details
		FormParams fpSubmitForm2 = new FormParams();
		fpSubmitForm2.FormName = formName2;
		fpSubmitForm2.ResourceInstanceNm = customersInstanceValue;
		fpSubmitForm2.LocationInstanceNm = locationInstanceValue;
		fpSubmitForm2.formElements = Arrays.asList(submitNumericField);

		formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmitForm2);

		// ------------------------------------------------------------------------------------------------
		// WEB Application code starts here
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
// 		//Category creation
//		HashMap<String,String> categories = new HashMap<String, String>();
//		categories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);
//		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
//		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
//		if(!rdp.createCategory(categories)) {
//			TCGFailureMsg = "Could NOT create Resource categories";
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//
//		//Location instance
//		HashMap<String,Boolean> locInstance = new HashMap<String, Boolean>();
//		locInstance.put(locationInstanceValue, true);
//		LocationInstanceParams lip = new LocationInstanceParams();
//		lip.CategoryName = locationCategoryValue;
//		lip.NumberFieldValue = 10;
//		lip.TextFieldValue = "XYZ";
//		lip.InstanceName = locInstance;
//		ManageLocationPage mlp = hp.clickLocationsMenu();
//		if(!mlp.createLocation(lip)) {
//			TCGFailureMsg = "Could NOT create Location instance";
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//
//		//Resource creation
//		HashMap<String,Boolean> instances = new HashMap<String, Boolean>();
//		instances.put(customersInstanceValue, true);
//		ResourceDetailParams rd = new ResourceDetailParams();
//		rd.CategoryName = customersCategoryValue;
//		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
//		rd.NumberFieldValue = 30;
//		rd.TextFieldValue = "ABC";
//		rd.InstanceNames = instances;
//		rd.LocationInstanceValue = locationInstanceValue;	
//		ManageResourcePage mrp = hp.clickResourcesMenu();
//		if(!mrp.createResourceLinkLocation(rd)) {
//			TCGFailureMsg = "Could NOT create Instances for resource - " + customersCategoryValue;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//
//		FormDesignerPage fdp = new FormDesignerPage(driver);
//		if(!fdp.createAndReleaseForm("Check", formName1,"Customers", customersCategoryValue,
//				customersInstanceValue)) {
//			TCGFailureMsg = "Could NOT create and release form - " + formName1;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//		
//		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
//		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS)) {
//			TCGFailureMsg = "For customer category, could NOT navigate to FSQABrowser > Forms tab";
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//	
//		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName1)) {
//			TCGFailureMsg = "Could not filter form - " + formName1;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//		
//		FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
//		if(!ffp.submitData(false, false, false, true,false)) {
//			TCGFailureMsg = "Could NOT submit form - " + formName1;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//		
//		if(!fdp.createAndReleaseForm("Check", formName2,"Customers", customersCategoryValue,
//				customersInstanceValue)) {
//			TCGFailureMsg = "Could NOT create and release form - " + formName2;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//		
//		fbp = hp.clickFSQABrowserMenu();
//		if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS)) {
//			TCGFailureMsg = "For customer category, could NOT navigate to FSQABrowser > Forms tab";
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//	
//		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName2)) {
//			TCGFailureMsg = "Could not filter form - " + formName2;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//		
//		ffp = new FSQABrowserFormsPage(driver);
//		if(!ffp.submitData(false, false, false, true,false)) {
//			TCGFailureMsg = "Could NOT submit form - " + formName2;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
		
		ProgramDesignerPage pdp = hp.clickProgramDesignerMenu();
		if(!pdp.setProgramName(programName)) {
			TCGFailureMsg = "Could NOT set Program name as - " + programName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		if(!pdp.addProgramElement(programElementName)){
			TCGFailureMsg = "Could NOT set Program element as - " + programElementName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!pdp.selectForms(formsNMLst)){
			TCGFailureMsg = "Could NOT add Forms " + formsNMLst + " to Program Designer";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description="Verify Single-record Sign-off Page")
	public void TestCase_31163() throws Exception{
		
		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName1);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + formName1, 
							  "Failed to open record for form - " + formName1);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean signOffRecord = frp.signoffRecord(signOffCmmt1);
		TestValidation.IsTrue(signOffRecord, 
							  "SIGNED record for form - " + formName1, 
							  "Failed to sign record for form " + formName1);

		String signByValue = displayName + "|" + timezoneCode;
		boolean signByVal = frp.verifyRecordsDetails(FORMFIELDS.SIGNEDBY, signByValue);
		TestValidation.IsTrue(signByVal, 
							  "Verified Signed by value " + signByValue, 
							  "Failed to verify signed by value" + signByValue);

	}

	@Test(description="Viewing Multiple records - Record Viewer page (by clicking VIEW button)")
	public void TestCase_31165() throws Exception{
		
		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectRecord1 = fbp.filterAndSelectRecordInGrid(COLUMNHEADER.FORMNAME, formName1, false);
		TestValidation.IsTrue(selectRecord1, 
				 			  "SELECTED record with form name - " + formName1, 
				 			  "Failed to select record with form name - " + formName1);
		
		boolean selectRecord2 = fbp.filterAndSelectRecordInGrid(COLUMNHEADER.FORMNAME, formName2, false);
		TestValidation.IsTrue(selectRecord2, 
							  "SELECTED record with form name - " + formName2, 
						  	  "Failed to select record with form name - " + formName2);
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(false,
							  frp.error, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		 
		RecordFormDetails rfd1 = new RecordFormDetails();
		rfd1.FormName = formName1;
		rfd1.Resource = customersInstanceValue;
		rfd1.Location = locationInstanceValue;
		rfd1.CompletedBy = displayName;
		boolean verifyForm1Details = frp.searchAndVerifyRecordsDetailsInView(rfd1);
		TestValidation.IsTrue(verifyForm1Details, 
				  			  "VERIFIED record details for form - " + rfd1.FormName, 
				  			  "Failed to verify record details for form - " + rfd1.FormName);
		
		RecordFormDetails rfd2 = new RecordFormDetails();
		rfd2.FormName = formName2;
		rfd2.Resource = customersInstanceValue;
		rfd2.Location = locationInstanceValue;
		rfd2.CompletedBy = displayName;
		boolean verifyForm2Details = frp.searchAndVerifyRecordsDetailsInView(rfd2);
		TestValidation.IsTrue(verifyForm2Details, 
	  			  "VERIFIED record details for form - " + rfd2.FormName, 
	  			  "Failed to verify record details for form - " + rfd2.FormName);
		
	}
	
	@Test(description="Viewing Multiple records sign off functionality (By Clicking SIGN button)")
	public void TestCase_31166() throws Exception{
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectRecord1 = fbp.filterAndSelectRecordInGrid(COLUMNHEADER.FORMNAME, formName1, false);
		TestValidation.IsTrue(selectRecord1, 
				 			  "SELECTED record with form name - " + formName1, 
				 			  "Failed to select record with form name - " + formName1);
		
		boolean selectRecord2 = fbp.filterAndSelectRecordInGrid(COLUMNHEADER.FORMNAME, formName2, false);
		TestValidation.IsTrue(selectRecord2, 
							  "SELECTED record with form name - " + formName2, 
						  	  "Failed to select record with form name - " + formName2);
		
		RecordSignoffPage rsp = fbp.clickSignRecordsBtn();
		TestValidation.Equals(false,
							  rsp.error, 
							  "NAVIGATED to Record Signoff page", 
							  "Failed to navigate to Record Signoff page");
		
		FSQABrowserRecordsPage frp = rsp.clickTotalRecordsCount();
		TestValidation.Equals(false,
							  frp.error, 
							  "CLICKED on Total records count on Record Sign off page", 
				  			  "Failed to click on Total records count on Record Sign off page");
		
		boolean selectForm1 = frp.searchAndSelectRecordInViewSignMode(formName1);
		if(!selectForm1){
			TestValidation.Fail("Failed to search adn select form - " + formName1);
		}
		boolean signForm1 = frp.signoffRecord(signOffCmmt1);
		TestValidation.IsTrue(signForm1, 
				  			  "SIGNED record for form - " + formName1, 
				  			  "Failed to sign record for form - " + formName1);
		
		RecordFormDetails rfd1 = new RecordFormDetails();
		rfd1.FormName = formName1;
		rfd1.SignedBy = displayName + "|" + timezoneCode;
		boolean verifyForm1Details = frp.searchAndVerifyRecordsDetailsInView(rfd1);
		TestValidation.IsTrue(verifyForm1Details, 
				  			  "VERIFIED record details for form - " + rfd1.FormName, 
				  			  "Failed to verify record details for form - " + rfd1.FormName);
		
		boolean selectForm2 = frp.searchAndSelectRecordInViewSignMode(formName2);
		if(!selectForm2){
			TestValidation.Fail("Failed to search adn select form - " + formName2);
		}
		boolean signForm2 = frp.signoffRecord(signOffCmmt2);
		TestValidation.IsTrue(signForm2, 
				  			  "SIGNED record for form - " + formName2, 
				  			  "Failed to sign record for form - " + formName2);
		
		RecordFormDetails rfd2 = new RecordFormDetails();
		rfd2.FormName = formName2;
		rfd2.CompletedBy = displayName + "|" + timezoneCode;
		boolean verifyForm2Details = frp.searchAndVerifyRecordsDetailsInView(rfd2);
		TestValidation.IsTrue(verifyForm2Details, 
							  "VERIFIED record details for form - " + rfd2.FormName, 
							  "Failed to verify record details for form - " + rfd2.FormName);
		
	}

	@Test(description="Verify Edit record functionality")
	public void TestCase_31168() throws Exception{
		
		HomePage hp = new HomePage(driver);	
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName1);
		TestValidation.IsTrue(openRecord, 
				 			  "OPENED record with form name - " + formName1, 
				 			  "Failed to open record with form name - " + formName1);
		
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean editRecord = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord, 
							  "CLICKED on edit record", 
							  "Failed to click on edit");
		
		String fieldValue = "34";
		boolean updateValue = frp.updateFieldValues(numericFN, FIELDTYPE.NUMERIC, fieldValue, false);
		TestValidation.IsTrue(updateValue, 
			  	  			  "UPDATED value for field type Numeric as " + fieldValue, 
				  			  "Failed to update value for field type Numeric as " + fieldValue);
		
		boolean submitRecord = frp.submitRecord(submitRecordCmmt);
		TestValidation.IsTrue(submitRecord, 
	  			  			  "SUBMITTED record with comment " + submitRecordCmmt, 
	  			  			  "Failed to submit record with comment " + submitRecordCmmt);

		List<String> fieldVal = new ArrayList<String>();
		fieldVal.add(fieldValue);
		boolean verifyUpdatedValue = frp.verifyFieldValues(numericFN, fieldVal);
		TestValidation.IsTrue(verifyUpdatedValue, 
	  			  			  "VERIFIED updated value for field type Numeric as " + fieldValue, 
	  			  			  "Failed to verify updated value for field type Numeric as " + fieldValue);		
	}
	
	@Test(description="Verify Multi-record Sign-off functionality from Main menu > Record Sign-off page")
	public void TestCase_32575() throws Exception{
		
		HomePage hp = new HomePage(driver);		
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.Equals(false,
				  			  rsp.error, 
				  			  "NAVIGATED to Record Signoff page", 
				  		 	  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName1);
		TestValidation.IsTrue(applyFilter1, 
							  "APPLIED filter for forms for form - " + formName1, 
				  			  "Failed to apply filter for forms for form - " + formName1);

		boolean applyFilter2 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName2);
		TestValidation.IsTrue(applyFilter2, 
							  "APPLIED filter for forms for form - " + formName2, 
				  			  "Failed to apply filter for forms for form - " + formName2);
		
		boolean clickNextBtn = rsp.clickNextBtn();
		TestValidation.IsTrue(clickNextBtn, 
	  			  			  "CLICKED on Next button", 
				  			  "Failed to click on Next button");
		
		FSQABrowserPage fbp = rsp.signoffAllRecords(signOffAllCmmt);
		TestValidation.Equals(false,
							  fbp.error, 
	  			  			  "SIGNED OFF records on Record Signoff page", 
	  		 	  			  "Failed to Sign off records on Record Signoff page");
		
		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean selectRecord1 = fbp.filterAndSelectRecordInGrid(COLUMNHEADER.FORMNAME, formName1, false);
		TestValidation.IsTrue(selectRecord1, 
				 			  "SELECTED record with form name - " + formName1, 
				 			  "Failed to select record with form name - " + formName1);
		
		boolean selectRecord2 = fbp.filterAndSelectRecordInGrid(COLUMNHEADER.FORMNAME, formName2, false);
		TestValidation.IsTrue(selectRecord2, 
							  "SELECTED record with form name - " + formName2, 
						  	  "Failed to select record with form name - " + formName2);
		
		FSQABrowserRecordsPage frp = fbp.clickViewRecordsBtn();
		TestValidation.Equals(false,
							  frp.error, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
	
		DateTime dt = new DateTime(driver);
		String currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
		RecordFormDetails rfd1 = new RecordFormDetails();
		rfd1.FormName = formName1;
		rfd1.HistoryDetail = signOffAllCmmt + "|" + displayName + "|" + currentDate + "|" + timezoneCode;
		boolean verifyForm1Details = frp.searchAndVerifyRecordsDetailsInView(rfd1);
		TestValidation.IsTrue(verifyForm1Details, 
				  			  "VERIFIED record details for form - " + rfd1.FormName, 
				  			  "Failed to verify record details for form - " + rfd1.FormName);
		
		RecordFormDetails rfd2 = new RecordFormDetails();
		rfd2.FormName = formName2;
		rfd2.HistoryDetail = signOffAllCmmt + "|" + displayName + "|" + currentDate + "|" + timezoneCode;
		boolean verifyForm2Details = frp.searchAndVerifyRecordsDetailsInView(rfd2);
		TestValidation.IsTrue(verifyForm2Details, 
							  "VERIFIED record details for form - " + rfd2.FormName, 
							  "Failed to verify record details for form - " + rfd2.FormName);
		
	}
	
	@Test(description = "Program Management - Program Viewer: Tenant user can view selected records in the "
			+ "Record Viewer(12132)")
	public void TestCase_17319() throws Exception {

		HomePage hp = new HomePage(driver);
		hp.clickFSQABrowserMenu();
		ProgramViewerPage pvp = hp.clickProgramsMenu();
		TestValidation.Equals(pvp.error, 
			      			  false, 
			      			  "CLICKED on Programs  menu", 
			      			  "Could NOT click on Programs menu");
		
		boolean pdSelectPrgram = pvp.selectPrograms(programName);
		TestValidation.IsTrue(pdSelectPrgram,
							  "SELECTED program " + programName + " from the Program list",
							  "Could NOT select program " + programName + " from the Program list");

		boolean pdSelectTab = pvp.clickProgramViewTab(PGVIEWTABS.RECORDS);
		TestValidation.IsTrue(pdSelectTab,
							  "SELECTED program tab as " + PGVIEWTABS.RECORDS + " for the Program",
							  "Could NOT select program tab as " + PGVIEWTABS.RECORDS + " for the Program");
		
		FSQABrowserRecordsPage frp = pvp.selectRecordsForViewer();
		TestValidation.Equals(frp.error, 
							  false,
							  "SELECTED records for viewing records of form " + formsNMLst,
							  "Could NOT select records for viewing records of form " + formsNMLst);
		
		//Create a new page maybe for Program Viewer
		RecordFormDetails rfd1 = new RecordFormDetails();
		rfd1.FormName = formName1;
		rfd1.CompletedBy = displayName;
		boolean verifyFormDetails1 = frp.searchAndVerifyRecordsDetailsInView(rfd1);
		TestValidation.IsTrue(verifyFormDetails1, 
				  			  "VERIFIED record details for form - " + rfd1.FormName, 
				  			  "Could NOT verify record details for form - " + rfd1.FormName);
		
		RecordFormDetails rfd2 = new RecordFormDetails();
		rfd2.FormName = formName2;
		rfd2.CompletedBy = displayName;
		boolean verifyFormDetails2 = frp.searchAndVerifyRecordsDetailsInView(rfd2);
		TestValidation.IsTrue(verifyFormDetails2, 
				  			  "VERIFIED record details for form - " + rfd2.FormName, 
				  			  "Could NOT verify record details for form - " + rfd2.FormName);

	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


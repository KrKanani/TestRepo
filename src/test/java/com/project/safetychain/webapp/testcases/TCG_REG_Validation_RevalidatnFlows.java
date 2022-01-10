package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormSubmission_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_EVENTS;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_RESULT;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TABS;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TYPE;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VldtnDetsParams;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FORMFIELDS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_FIELDS;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_Validation_RevalidatnFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customerCategoryValue;
	public static String customerInstanceValue;
	public static String formName;
	public static String singleLineTxtFN = "Text";
	public static String singleLineTxtValue = "Random Automation Text";
	public static String section = "Section";
	public static String vldtnName;
	public static String vldtnSummary;
	public static String vldtnStartDate, vldtnEndDate;
	HashMap<String,String> identifierDets;
	public static String rolename = "SuperAdmin";
	public static String displayName = null;
	public static String usrTimezone = null;
	public static String timezoneCode = null;
	public static String currentDate;
	public static String signOffCmmt;
	public static String updatedValidationName;
	public static String newValidationName;
	public static String voidRecCmmt;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		formName = CommonMethods.dynamicText("RevldtnRec");
		vldtnName = CommonMethods.dynamicText("Revldtn");
		vldtnSummary = CommonMethods.dynamicText("RSummary");
		signOffCmmt = CommonMethods.dynamicText("Cmmt");
		updatedValidationName = vldtnName + "_1";
		newValidationName = CommonMethods.dynamicText("New Revldtn");
		voidRecCmmt = CommonMethods.dynamicText("VoidCmmt");
		
		usrTimezone = adminTimezone;
		timezoneCode = dt.getTimezone(usrTimezone);
		
		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {

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
			resourceCatMap.put(customerCategoryValue, Arrays.asList(customerInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.CUSTOMERS);

			// ------------------------------------------------------------------------------------------------
			// API - Form Creation - Audit

			// Section
			Element_Types Section = new Element_Types();
			Section.ElementType = DPT_FIELD_TYPES.SECTION;
			Section.Name = section;

			// API - Add fields to form
			FormFieldParams SLTField = new FormFieldParams();
			SLTField.DPTFieldType = DPT_FIELD_TYPES.SINGLE_LINE_TEXT;
			SLTField.Name = singleLineTxtFN;

			Section.formFieldParams = Arrays.asList(SLTField);

			// API - Form details
			// API - Generate unique ID for form to be created
			String formIdAdt = apiUtils.getUUID();

			// API - Form layout details
			FormParams fpFormAdt = new FormParams();
			fpFormAdt.FormId = formIdAdt;
			fpFormAdt.FormName = formName;
			fpFormAdt.type = DPT_FORM_TYPES.AUDIT;
			fpFormAdt.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fpFormAdt.ResourceCategoryNm = customerCategoryValue;
			fpFormAdt.ResourceInstanceNm = customerInstanceValue;
			fpFormAdt.CustomerResources = resourceCatMap;
			fpFormAdt.SectionElements = Arrays.asList(Section);

			formCreationWrapper.API_Wrapper_Forms(fpFormAdt);

			// ------------------------------------------------------------------------------------------------
			// API - Submit form

			// API - Add values to fields in form
			FormFieldParams submitSLTField = new FormFieldParams();
			submitSLTField.Name = singleLineTxtFN;
			submitSLTField.Value = singleLineTxtValue;

			// API - Form layout details
			FormParams fpSubmit = new FormParams();
			fpSubmit.FormName = formName; 
			fpSubmit.type = DPT_FORM_TYPES.AUDIT;
			fpSubmit.ResourceInstanceNm = customerInstanceValue;
			fpSubmit.LocationInstanceNm = locationInstanceValue;
			fpSubmit.formElements = Arrays.asList(submitSLTField);

			formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmit);

			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here

			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
			hp = new HomePage(driver);
			lp = new LoginPage(driver);

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

			ValidationsPage vp = hp.clickValidationsMenu();
			AddEditValidationsPage aevp = vp.createNewValidation();
			if(aevp.error) {
				TCGFailureMsg = "Could NOT navigate to create new Validations page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			vldtnStartDate = dt.AddDaystoToddaysDate(-2, "MM/dd/YYYY HH:mm");
			vldtnEndDate = dt.AddDaystoToddaysDate(0, "MM/dd/YYYY HH:mm");

			VldtnDetsParams vdp = new VldtnDetsParams();
			vdp.Name = vldtnName;
			vdp.Type = VALIDATION_TYPE.NEW_PRODUCT;
			vdp.Location = locationInstanceValue;
			vdp.Resource = customerInstanceValue;
			vdp.Forms = Arrays.asList(formName);
			vdp.StartDate = vldtnStartDate;
			vdp.EndDate = vldtnEndDate;
			if(!aevp.createAndAddValidation(vdp)) {
				TCGFailureMsg = "Could NOT add validation - " + vldtnName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!aevp.completeValidationWithResult(VALIDATION_RESULT.PASS, vldtnSummary)) {
				TCGFailureMsg = "Could NOT complete Validation " + vldtnName + " with status " + VALIDATION_RESULT.PASS;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
		else if(executionMode.equalsIgnoreCase(ExecutionMode.UI)) {
			// ------------------------------------------------------------------------------------------------
			// Only WEB Application code starts here
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
			hp = new HomePage(driver);
			lp = new LoginPage(driver);

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

			hp.clickFSQABrowserMenu();
			//Category creation
			HashMap<String,String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue);
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
			instances.put(customerInstanceValue, true);
			ResourceDetailParams rd = new ResourceDetailParams();
			rd.CategoryName = customerCategoryValue;
			rd.CategoryType = RESOURCETYPES.CUSTOMERS;
			rd.NumberFieldValue = 30;
			rd.TextFieldValue = "ABC";
			rd.InstanceNames = instances;
			rd.LocationInstanceValue = locationInstanceValue;	
			ManageResourcePage mrp = hp.clickResourcesMenu();
			if(!mrp.createResourceLinkLocation(rd)) {
				TCGFailureMsg = "Could NOT create Instances for resource - " + customerInstanceValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Create Audit Form
			HashMap<String, List<String>> selectResources = new HashMap<String, List<String>>();
			selectResources.put(FORMRESOURCETYPES.CUSTOMERS,Arrays.asList(customerCategoryValue));

			HashMap<String,List<String>> fieldDetails = new HashMap<String,List<String>>();
			fieldDetails.put(FIELD_TYPES.SINGLE_LINE_TEXT, Arrays.asList(singleLineTxtFN));

			com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams ffparams = 
					new com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams();
			ffparams.FieldDetails = fieldDetails;		
			ffparams.SectionName = section;

			FormDesignParams fdparams = new FormDesignParams();
			fdparams.FormType = FORMTYPES.AUDIT;
			fdparams.FormName = formName;
			fdparams.SelectResources = selectResources;
			fdparams.DesignFields = Arrays.asList(ffparams);

			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			if(!fdp.createAndReleaseForm(fdparams)) {
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

			HashMap<String, List<String>> fillDetails = new HashMap<String, List<String>>();
			fillDetails.put(singleLineTxtFN,Arrays.asList(singleLineTxtValue));

			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillDetails;

			FormOperations fo = new FormOperations(driver);
			if(!fo.submitData(fd)) {
				TCGFailureMsg = "Failed to submit form";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			ValidationsPage vp = hp.clickValidationsMenu();
			AddEditValidationsPage aevp = vp.createNewValidation();
			if(aevp.error) {
				TCGFailureMsg = "Could NOT navigate to create new Validations page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			vldtnStartDate = dt.AddDaystoToddaysDate(-2, "MM/dd/YYYY HH:mm");
			vldtnEndDate = dt.AddDaystoToddaysDate(0, "MM/dd/YYYY HH:mm");

			VldtnDetsParams vdp = new VldtnDetsParams();
			vdp.Name = vldtnName;
			vdp.Type = VALIDATION_TYPE.NEW_PRODUCT;
			vdp.Location = locationInstanceValue;
			vdp.Resource = customerInstanceValue;
			vdp.Forms = Arrays.asList(formName);
			vdp.StartDate = vldtnStartDate;
			vdp.EndDate = vldtnEndDate;
			if(!aevp.createAndAddValidation(vdp)) {
				TCGFailureMsg = "Could NOT add validation - " + vldtnName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			if(!aevp.completeValidationWithResult(VALIDATION_RESULT.PASS, vldtnSummary)) {
				TCGFailureMsg = "Could NOT complete Validation " + vldtnName + " with status " + VALIDATION_RESULT.PASS;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
		else {
			TCGFailureMsg = "Improper Execution Mode is set - " + executionMode;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description="Validation || Verify on clicking of Re-validated button the fields data is copied.")
	public void TestCase_33883() {
		WebElement SelectFormChk = null;
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateRecs = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateRecs, 
	  			  			  "NAVIGATED to FSQA Browser > Customers > Records tab", 
	  			  			  "Could NOT navigate to FSQA Browser > Customers > Records tab");
		
		
		boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openForm, 
	  			  			  "OPENED record for form " + formName, 
	  			  		  	  "Could NOT open record for form " + formName);
		
		String vldtnMsg = "Record cannot be edited";
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean verifyLock = frp.verifyRecordsDetails(FORMFIELDS.VALIDATIONLOCK, vldtnMsg);
		TestValidation.IsTrue(verifyLock, 
	  			  			  "VERIFIED record is locked", 
	  			  			  "Could NOT verify record is locked");
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 

		AddEditValidationsPage aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.Equals(aevp.error, 
							  false, 
							  "OPENED validtion " + vldtnName, 
							  "Could NOT open validation " + vldtnName);
		
		boolean clickHistory = aevp.selectValidationTab(VALIDATION_TABS.HISTORY);
		TestValidation.IsTrue(clickHistory, 
							  "OPENED History tab for validation", 
							  "Failed to open History tab for validation");
		
		List<String> details = new ArrayList<String>();
		details.add(VALIDATION_EVENTS.VALIDATION_COMPLETE + "|" + timezoneCode + "|" + adminUsername);
		details.add(VALIDATION_EVENTS.VALIDATION_CREATED + "|" + timezoneCode + "|" + adminUsername);
		boolean verifyHistory = aevp.verifyHistoryDetails(details);
		TestValidation.IsTrue(verifyHistory, 
							  "VERIFIED History details displayed for validation as Created and Complete", 
							  "Failed to verify History details displayed for validation");
		
		boolean clickDetails = aevp.selectValidationTab(VALIDATION_TABS.DETAILS);
		TestValidation.IsTrue(clickDetails, 
							  "OPENED Details tab for validation", 
							  "Failed to open Details tab for validation");
		
		boolean revalidate = aevp.revalidateValidation();
		TestValidation.IsTrue(revalidate, 
							  "REVALIDATED the validation and released locked records", 
							  "Could NOT verify whether validation is revalidated");
		
		boolean verifyTitleAfter = aevp.VldtnTitle.getText().contains(updatedValidationName);
		TestValidation.IsTrue(verifyTitleAfter, 
							  "VERIFIED the title has value as " + updatedValidationName, 
							  "Failed to verify that the title has value as " + updatedValidationName);
		
		boolean verifyType = aevp.VldtnTypeVal.getText().contains(VALIDATION_TYPE.NEW_PRODUCT);
		TestValidation.IsTrue(verifyType, 
			  			      "VERIFIED Validation type field's value is " + VALIDATION_TYPE.NEW_PRODUCT, 
			  			      "Failed to verify Validation type field's value");
		
		boolean verifyLocn = aevp.VldtnLocationVal.getText().contains(locationInstanceValue);
		TestValidation.IsTrue(verifyLocn, 
			  			      "VERIFIED Validation location field's value is " + locationInstanceValue, 
			  			      "Failed to verify Validation location field's value");
		
		boolean verifyRes = aevp.VldtnResourceVal.getText().contains(customerInstanceValue);
		TestValidation.IsTrue(verifyRes, 
			  			      "VERIFIED Validation resource field's value is " + customerInstanceValue, 
			  			      "Failed to verify Validation resource field's value");
		
		SelectFormChk = controlActions.perform_GetElementByXPath(AddEditValidationsPageLocators.SELECT_FORM_CHK, 
						"FORMNAME", formName);
		boolean verifyForm = SelectFormChk.isSelected();
		TestValidation.IsTrue(verifyForm, 
			  			      "VERIFIED Validation form section's has selected form - " + formName, 
			  			      "Failed to verify Validation form section's selected form");
		
	}
	
	
	@Test(description="Validation || Verify on clicking of Re-validated button the existing validation is deleted.",
			dependsOnMethods = { "TestCase_33883" })
	public void TestCase_33884() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 

		boolean applyNameFilter = vp.setFilterToColumn(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.IsTrue(applyNameFilter, 
							  "APPLIED grid filter to Name column with value " + vldtnName, 
							  "Failed to set grid filter to Name column");
		
		int cntAfterFilter = vp.VldtnGridNameLst.size();
		TestValidation.IsTrue(cntAfterFilter==1, 
							  "Only 1 validation is DISPLAYED", 
							  "Failed to verify whether validations are displayed or not");
		
		String verifyVldtnName = vp.getValidationDetails(VLDTN_FIELDS.NAME);
		TestValidation.IsTrue(verifyVldtnName.equalsIgnoreCase(updatedValidationName), 
				  			  "Validation with name " + updatedValidationName + " is DISPLAYED ", 
				  			  "Failed to verify whether validation " + updatedValidationName + " is displayed or not");
		
	}
	
	@Test(description="Validation || Verify on clicking of Re-validated button records associated with "
			+ "the existing validation are unlocked.",
			dependsOnMethods = { "TestCase_33883" })
	public void TestCase_33885() {
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateRecs = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateRecs, 
	  			  			  "NAVIGATED to FSQA Browser > Customers > Records tab", 
	  			  			  "Could NOT navigate to FSQA Browser > Customers > Records tab");
		
		
		boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openForm, 
	  			  			  "OPENED record for form " + formName, 
	  			  		  	  "Could NOT open record for form " + formName);
		
		String vldtnMsg = "Record cannot be edited";
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean verifyLock = frp.verifyRecordsDetails(FORMFIELDS.VALIDATIONLOCK, vldtnMsg);
		TestValidation.IsFalse(verifyLock, 
	  			  			  "VERIFIED record locked message is not displayed", 
	  			  			  "Could NOT verify record locked message is displayed or not");
		
		boolean signOffRecord = frp.signoffRecord(signOffCmmt);
		TestValidation.IsTrue(signOffRecord, 
							  "SIGNED record for form - " + formName, 
							  "Failed to sign record for form " + formName);

		currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
		String signByValue = displayName + "|" + currentDate + "|" + timezoneCode;
		boolean signByVal = frp.verifyRecordsDetails(FORMFIELDS.SIGNEDBY, signByValue);
		TestValidation.IsTrue(signByVal, 
							  "Verified Signed by value " + signByValue, 
							  "Failed to verify signed by value" + signByValue);
		
		boolean editRecord = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord, 
							  "EDITED record for form - " + formName, 
							  "Failed to edit for form " + formName);
		
		fbp = frp.clickAndPerformVoidRecord(voidRecCmmt);
		TestValidation.Equals(fbp.error,
							  false,
							  "Record has been turned to VOID for form - " + formName, 
							  "Failed to void record for form " + formName);

		String verifyRecordExists = fbp.getGridValueForColumn(COLUMNHEADER.FORMNAME);
		TestValidation.IsTrue(verifyRecordExists==null, 
							  "VERIFIED FSQA records tab has no entry for form - " + formName, 
							  "Failed to verify FSQA records tab has no entry for form - " + formName);
		
	}
	
	@Test(description="Validation || Verify on clicking of Re-validated button a history event is"
			+ " added to the new Validation referencing the previous Validation.",
			dependsOnMethods = { "TestCase_33883" })
	public void TestCase_33887() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 

		AddEditValidationsPage aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.Equals(aevp.error, 
							  false, 
							  "OPENED validtion " + vldtnName, 
							  "Could NOT open validation " + vldtnName);
		
		boolean clickHistory = aevp.selectValidationTab(VALIDATION_TABS.HISTORY);
		TestValidation.IsTrue(clickHistory, 
							  "OPENED History tab for validation", 
							  "Failed to open History tab for validation");
		
		List<String> details = new ArrayList<String>();
		details.add(VALIDATION_EVENTS.REVALIDATION_CREATED + "|" + timezoneCode + "|" + adminUsername);
		boolean verifyHistory = aevp.verifyHistoryDetails(details);
		TestValidation.IsTrue(verifyHistory, 
							  "VERIFIED History details displayed for validation as Revalidation Createds", 
							  "Failed to verify History details displayed for validation");
		
	}
	
	@Test(description="Validation || Verify that after re validation the new validation should be edited as any "
			+ "other validations.", dependsOnMethods = { "TestCase_33883" })
	public void TestCase_33888() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 

		AddEditValidationsPage aevp = vp.searchAndOpenVldtn(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.Equals(aevp.error, 
							  false, 
							  "OPENED validation " + vldtnName, 
							  "Could NOT open validation " + vldtnName);
		
		boolean verifyTitleBefore = aevp.VldtnTitle.getText().contains(updatedValidationName);
		TestValidation.IsTrue(verifyTitleBefore, 
							  "VERIFIED the title has value as " + updatedValidationName, 
							  "Failed to verify that the title has value as " + updatedValidationName);
		
		boolean setName = aevp.setValidationName(newValidationName);
		TestValidation.IsTrue(setName, 
				  			   "The Validation name is SET as " + newValidationName, 
				  			   "Failed to set Validation name as " + newValidationName);
		
		boolean setType = aevp.setValidationType(VALIDATION_TYPE.NEW_EQUIPMENT);
		TestValidation.IsTrue(setType, 
				  			  "The Validation type is SET as " + VALIDATION_TYPE.NEW_EQUIPMENT, 
				  			  "Failed to set Validation type as " + VALIDATION_TYPE.NEW_EQUIPMENT);
		
		boolean setResource = aevp.setValidationResource("ANY");
		TestValidation.IsTrue(setResource, 
				  			  "The Validation resource is SET as 'ANY'", 
				  			  "Failed to set Validation resource as 'ANY'");
		
		boolean updateVldtn = aevp.clickUpdateValidation();
		TestValidation.IsTrue(updateVldtn, 
			  			   	  "UPDATE the Validation details", 
			  			      "Failed to update Validation details");
		
		boolean verifyTitleAfter = aevp.VldtnTitle.getText().contains(newValidationName);
		TestValidation.IsTrue(verifyTitleAfter, 
							  "VERIFIED the title has value as " + newValidationName, 
							  "Failed to verify that the title has value as " + newValidationName);
		
		boolean verifyType = aevp.VldtnTypeVal.getText().contains(VALIDATION_TYPE.NEW_EQUIPMENT);
		TestValidation.IsTrue(verifyType, 
			  			      "VERIFIED Validation type field's value is " + VALIDATION_TYPE.NEW_EQUIPMENT, 
			  			      "Failed to verify Validation type field's value");
		
		boolean verifyRes = aevp.VldtnResourceVal.getText().contains("ANY");
		TestValidation.IsTrue(verifyRes, 
			  			      "VERIFIED Validation resource field's value is ANY", 
			  			      "Failed to verify Validation resource field's value");
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}

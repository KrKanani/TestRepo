package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
//import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FM_FIELDS;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FM_STATUS;
import com.project.safetychain.webapp.pageobjects.ManageRequirementPage.REQUIREMENT_RESOURCE_TYPE;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_FormManager extends TestBase {
	ControlActions controlActions;
	FSQABrowserPage fbp;
	CommonPage mainPage;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	VerificationsPage verification;
	HomePage hp;
	ResourceDesignerPage resourceDesigner;
	LoginPage lp;
	InboxPage inp;
	WorkGroupsPage wgp;
	FormsManagerPage fm;
	FSQABrowserRecordsPage frp;
	VerificationDetsParams vdp;
	FormDetails formDetails;
	FormFieldParams ffp;
	FormOperations formOperations;
	FormDesignParams fp;
	ManageRequirementPage mrp;


	public static String verificationName;
	public static String rolename ="SuperAdmin";
	public static String verificationComment1;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String supptempname;
	public static String formreqname;
	public static String workGroupName;
	public static String formName35049;
	public static String formName35053;
	public static String formName40864;
	public static String formName35052;
	public static String formName40896;


	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		supptempname = CommonMethods.dynamicText("SuppTemp"); 
		workGroupName = CommonMethods.dynamicText("WG");
		formreqname = CommonMethods.dynamicText("Form");
		verificationComment1 = CommonMethods.dynamicText("AutoComment1");
		verificationName = CommonMethods.dynamicText("AutoVN");
		formName35049 = CommonMethods.dynamicText("FM_35049");
		formName35053 = CommonMethods.dynamicText("FM_35053");
		formName40864 = CommonMethods.dynamicText("FM_40864");
		formName35052 = CommonMethods.dynamicText("FM_35052");
		formName40896 = CommonMethods.dynamicText("QFM_40896");


		// ------------------------------------------------------------------------------------------------
		// API Implementation
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
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
		// API - Form Creation formName35049 - Check
		// API - Add fields to form
		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = "Numeric";

		// API - Form details
		// API - Generate unique ID for form to be created
		String formId1 = apiUtils.getUUID();

		// API - Form layout details
		FormParams fp1 = new FormParams();
		fp1.FormId = formId1;
		fp1.FormName = formName35049;
		fp1.type = DPT_FORM_TYPES.CHECK;
		fp1.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp1.ResourceCategoryNm = customersCategoryValue;
		fp1.ResourceInstanceNm = customersInstanceValue;
		fp1.formElements = Arrays.asList(numericField);
		fp1.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fp1);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation formName35053 - Check
		// API - Add fields to form
		
		String formId2 = apiUtils.getUUID();

		// API - Form layout details
		FormParams fp2 = new FormParams();
		fp2.FormId = formId2;
		fp2.FormName = formName35053;
		fp2.type = DPT_FORM_TYPES.CHECK;
		fp2.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp2.ResourceCategoryNm = customersCategoryValue;
		fp2.ResourceInstanceNm = customersInstanceValue;
		fp2.formElements = Arrays.asList(numericField);
		fp2.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fp2);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation formName40864 - Check
		// API - Add fields to form

		String formId3 = apiUtils.getUUID();

		// API - Form layout details
		FormParams fp3 = new FormParams();
		fp3.FormId = formId3;
		fp3.FormName = formName40864;
		fp3.type = DPT_FORM_TYPES.CHECK;
		fp3.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp3.ResourceCategoryNm = customersCategoryValue;
		fp3.ResourceInstanceNm = customersInstanceValue;
		fp3.formElements = Arrays.asList(numericField);
		fp3.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fp3);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation formName35052 - Check
		// API - Add fields to form

		String formId4 = apiUtils.getUUID();

		// API - Form layout details
		FormParams fp4 = new FormParams();
		fp4.FormId = formId4;
		fp4.FormName = formName35052;
		fp4.type = DPT_FORM_TYPES.CHECK;
		fp4.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp4.ResourceCategoryNm = customersCategoryValue;
		fp4.ResourceInstanceNm = customersInstanceValue;
		fp4.formElements = Arrays.asList(numericField);
		fp4.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fp4);

		// ------------------------------------------------------------------------------------------------
		// API - Form Creation formName40896 - Check
		// API - Add fields to form

		String formId5 = apiUtils.getUUID();

		// API - Form layout details
		FormParams fp5 = new FormParams();
		fp5.FormId = formId5;
		fp5.FormName = formName40896;
		fp5.type = DPT_FORM_TYPES.QUESTIONNAIRE;
		fp5.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp5.ResourceCategoryNm = customersCategoryValue;
		fp5.ResourceInstanceNm = customersInstanceValue;
		fp5.formElements = Arrays.asList(numericField);
		fp5.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fp5);

		// ------------------------------------------------------------------------------------------------
		// WEB Application code starts here

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		controlActions.getUrl(applicationUrl);

		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		fdp = new FormDesignerPage(driver);
		fbp = new FSQABrowserPage(driver);
		frp = new FSQABrowserRecordsPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		locations = new ManageLocationPage(driver);
		mrp = new ManageRequirementPage(driver);
		fm = new FormsManagerPage(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		vdp = new VerificationDetsParams();

		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		verification = hp.clickVerificationsMenu();	

		vdp.VerificationName = verificationName;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		vdp.Rolename = rolename;
		if (!verification.createVerification(vdp)){
			TCGFailureMsg = "Could NOT able to create verification"; 
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg); 
		}
		
//		
//		hp.clickFSQABrowserMenu();
//		ManageLocationPage mlp = new ManageLocationPage(driver);
//		if(!mlp.createLocation(locationCategoryValue,locationInstanceValue)) {
//			TCGFailureMsg = "Could NOT create location category - " + locationCategoryValue;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//
//		ManageResourcePage mrp = new ManageResourcePage(driver);
//		if(!mrp.createResourceLinkLocation(FORMRESOURCETYPES.CUSTOMERS, customersCategoryValue, 
//				customersInstanceValue,locationInstanceValue)) {
//			TCGFailureMsg = "Could NOT create resource " + customersInstanceValue + " & link Location - "+ locationInstanceValue;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
		
	}


	@Test(description="Association & verification of Verification Template with Forms")
	public void TestCase_35049() throws Exception{
		try {

			hp.clickFormsManagerMenu();
			boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName,formName35049);
			TestValidation.IsTrue(formLinkedwithVerifications, 
					"LINKED Verification with Form " + formName35049,
					"Could NOT Link Verification with Form " +formName35049);

			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
					"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
					"Failed to navigate to FSQABrowser > Forms tab");

			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName35049);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
					"Verify form is displayed in Forms Tab", 
					"Form is NOT displayed in Forms Tab");

			boolean submitData = fbForms.submitData(false,false,false,true,false);
			TestValidation.IsTrue(submitData, 
					"Able to submit the form with data: " +formName35049,
					"Could NOT able to submit the form with data: " +formName35049);

			boolean navigateToRecords1 = fbp.navigateToFSQATab(FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords1, 
								  "Navigated to FSQABrowser > Records Tab", 
								  "Failed to navigate to FSQABrowser > Records Tab");

			boolean openRecord1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName35049);
			TestValidation.IsTrue(openRecord1, 
					"OPENED record for form - " + formName35049, 
					"Failed to open record for form - " + formName35049);

			boolean signOffRecord1 = frp.signoffRecordWithVerfication(verificationName,verificationComment1);
			TestValidation.IsTrue(signOffRecord1, 
					"SIGNED record for form - " + formName35049, 
					"Failed to sign record for form " + formName35049);

			hp.clickFormsManagerMenu();			
			boolean deselectVerification = fm.delinkVerificationwithForm(verificationName,formName35049);
			TestValidation.IsTrue(deselectVerification, 
					"Verify form is deselected with verification", 
					"Form is NOT is deselected with verification");

			fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords2, 
					"For customer category, NAVIGATED to FSQABrowser > Records tab", 
					"Failed to navigate to FSQABrowser > Records tab");

			boolean openRecord2 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName35049);
			TestValidation.IsTrue(openRecord2, 
					"OPENED record for form - " + formName35049, 
					"Failed to open record for form - " + formName35049);

			boolean signOffRecord2 = frp.signoffRecordWithVerfication(verificationName,verificationComment1);
			TestValidation.IsFalse(signOffRecord2, 
					"CANNOT sign the record with verification as it is not associated with form " + formName35049, 
					"Failed to Verify whether we can sign record for form " + formName35049 + " or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	} 

	@Test(description="Verification of Details Tab, Enable & Disable Form Functionality ")
	public void TestCase_35053() throws Exception{

		fm = hp.clickFormsManagerMenu();
		boolean searchingForm = fm.searchForm(formName35053);
		TestValidation.IsTrue(searchingForm, 
				"OPENED the search form - " + formName35053, 
				"Failed to open searched form - " + formName35053);

		String formStatus1 = fm.getFormManagerDetails(FM_FIELDS.STATUS);
		boolean verifyFormStatus = formStatus1.contains(FM_STATUS.ENABLED);
		TestValidation.IsTrue(verifyFormStatus,
				"Verified Form is in Enabled state", 
				"Failed to verify Form is in Enabled state");

		boolean formSelect = fm.selectForm(formName35053);
		TestValidation.IsTrue(formSelect, 
				"Form selection is done", 
				"Failed to select the form");

		boolean formDisabled = fm.performEnableDisableAction(formStatus1);
		TestValidation.IsTrue(formDisabled, 
				"Form is get Disabled", 
				"Failed to disable the form");

		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForDetails1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName35053);
		TestValidation.IsFalse(applyFilterAndOpenForDetails1,
				"Verify form is not displayed in Forms Tab", 
				"Form is displayed in Forms Tab");

		fm = hp.clickFormsManagerMenu();
		boolean searchFormfm = fm.searchForm(formName35053);
		TestValidation.IsTrue(searchFormfm, 
				"OPENED the search form - " + formName35053, 
				"Failed to open searched form - " + formName35053);

		String formStatus2 = fm.getFormManagerDetails(FM_FIELDS.STATUS);
		boolean verifyFormStatusNew = formStatus2.contains(FM_STATUS.DISABLED);
		TestValidation.IsTrue(verifyFormStatusNew,
				"Verified the form is in disabled state ", 
				"Failed to Verify the form is in disabled state");

		boolean formSelect_New = fm.selectForm(formName35053);
		TestValidation.IsTrue(formSelect_New, 
				"Form selection is done", 
				"Failed to select the form");

		boolean formEnabled = fm.performEnableDisableAction(formStatus2);
		TestValidation.IsTrue(formEnabled, 
				"Form is Enabled", 
				"Failed to enable the form");

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms2, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName35053);
		TestValidation.IsTrue(applyFilterAndOpenForm, 
				"Verify form is displayed in Forms Tab", 
				"Form is NOT displayed in Forms Tab");

		boolean submitData = fbForms.submitData(false,false,false,true,false);
		TestValidation.IsTrue(submitData, 
				"Able to submit the form with data: " +formName35053,
				"Could NOT able to submit the form with data: " +formName35053);

		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "Navigated to FSQABrowser > Records Tab", 
							  "Failed to navigate to FSQABrowser > Records Tab");

		boolean applyFilterAndOpenForDetails2 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName35053);
		TestValidation.IsTrue(applyFilterAndOpenForDetails2, 
				"Verify form is displayed in Records Tab", 
				"Form is NOT displayed in Records Tab");

	} 

	@Test(description="Enable Verification toggle functionality")
	public void TestCase_40864() throws Exception{

		fm = hp.clickFormsManagerMenu();			
		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms menu", 
				"Could NOT click on Forms menu");

		boolean verifyToggle = fm.verificationToggle(formName40864, verificationName);
		TestValidation.IsTrue(verifyToggle, 
				"Enabled the verification with Form - " + formName40864, 
				"Failed to enabled the verification with Form " + formName40864);

		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName40864);
		TestValidation.IsTrue(applyFilterAndOpenForDetails, 
				"Verify form is displayed in Forms Tab", 
				"Form is NOT displayed in Forms Tab");

		boolean submitData = fbForms.submitData(false,false,false,true,false);
		TestValidation.IsTrue(submitData, 
				"Able to submit the form with data: " +formName40864,
				"Could NOT able to submit the form with data: " +formName40864);

		boolean navigateToRecords = fbp.navigateToFSQATab(FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "Navigated to FSQABrowser > Records Tab", 
							  "Failed to navigate to FSQABrowser > Records Tab");

		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName40864);
		TestValidation.IsTrue(openRecord, 
				"OPENED record for form - " + formName40864, 
				"Failed to open record for form - " + formName40864);

		boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName,verificationComment1);
		TestValidation.IsTrue(signOffRecord, 
				"SIGNED record for form - " + formName40864, 
				"Failed to sign record for form " + formName40864);

	} 

	@Test(description="Verification of Location Filtering")

	public void TestCase_35052() throws Exception{

		fm = hp.clickFormsManagerMenu();			
		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms menu", 
				"Could NOT click on Forms menu");

		boolean togglelocON_New = fm.LocationFormtoggleON(formName35052);
		TestValidation.IsTrue(togglelocON_New, 
				"Location is SELECTED for Form " + formName35052, 
				"Failed to Select Location for Form " + formName35052);

		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToforms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToforms1, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForDetails1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName35052);
		TestValidation.IsFalse(applyFilterAndOpenForDetails1, 
				"VERIFIED form is NOT displayed in Forms Tab", 
				"Failed to verify whether Form is or is not displayed in Forms Tab");

		fm = hp.clickFormsManagerMenu();			
		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms menu", 
				"Could NOT click on Forms menu");

		boolean togglelocON_Form = fm.FormtoggleONLocation(formName35052);
		TestValidation.IsTrue(togglelocON_Form, 
				"Location is DESELECTED for Form " + formName35052, 
				"Failed to Deselect Location for Form " + formName35052);

		fbp = hp.clickFSQABrowserMenu();
		boolean navigateToForms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
		TestValidation.IsTrue(navigateToForms2, 
				"For customer category, NAVIGATED to FSQABrowser > Forms tab", 
				"Failed to navigate to FSQABrowser > Forms tab");

		boolean applyFilterAndOpenForDetails2 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName35052);
		TestValidation.IsTrue(applyFilterAndOpenForDetails2, 
				"Verify form is NOT displayed in Forms Tab", 
				"Failed to verify whether Form is or is not displayed in Forms Tab");
		
	} 

	@Test(description = "Verification of Location Filtering for questionnaire form")
	public void TestCase_40896() throws Exception {

		fm = hp.clickFormsManagerMenu();
		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms menu", 
				"Could NOT click on Forms menu");

		boolean togglelocOFF = fm.toggleOFFLocation(formName40896);
		TestValidation.IsTrue(togglelocOFF, 
				"Toggle off location is done", 
				"Failed to toggle off the location");

		hp.clickFSQABrowserMenu();
		mrp = hp.clickRequirementsMenu(); 
		boolean addtemp = mrp.addSupplierTemplate(supptempname);
		TestValidation.IsTrue(addtemp, 
				"Supplier Template Created", 
				"Failed to create supplier template");

		boolean formreq1 = mrp.addCompleteFormRequirementblank(formName40896,formreqname);
		TestValidation.IsFalse(formreq1, 
				"Complete Form Requirement is not Created", 
				"Failed to create Complete Form Requirement");

		fm = hp.clickFormsManagerMenu(); 
		TestValidation.Equals(fm.error, 
				false, 
				"CLICKED on Forms menu", 
				"Could NOT click on Forms menu");

		boolean togglelocON = fm.toggleONLocation(formName40896);
		TestValidation.IsTrue(togglelocON, 
				"Toggle ON location is done", 
				"Failed to toggle ON the location");

		hp.clickFSQABrowserMenu();
		hp.clickRequirementsMenu();   
		boolean addtemp11 = mrp.searchtemplate(supptempname,REQUIREMENT_RESOURCE_TYPE.SUPPLIERS);   
		TestValidation.IsTrue(addtemp11, 
				"Supplier Template selected", 
				"Failed to search Supplier Template"); 

		boolean formreq = mrp.addCompleteFormRequirement(formName40896,formreqname,null);
		TestValidation.IsTrue(formreq, 
				"Complete Form Requirement Created", 
				"FORM is not displayed on the GRID");

	} 

	@AfterClass(alwaysRun = true) 
	public void closeBrowser() throws InterruptedException { 
		driver.close(); 
	} 

}

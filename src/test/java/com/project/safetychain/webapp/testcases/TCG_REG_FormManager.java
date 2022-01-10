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

import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
// added -PUSHKAR
import com.project.safetychain.webapp.pageobjects.*;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.HistoryDetails;

import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.COLUMN_SETTING;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FM_FIELDS;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FM_STATUS;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.FM_FORMTYPE;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage.PGN_SIZE;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.CommonMethods.SORTING;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_FormManager extends TestBase {
	String av = null;
	ControlActions controlActions;
	FSQABrowserPage fbp;
	CommonPage mainPage;
	FSQABrowserFormsPage fbForms;
	ManageResourcePage manageResource;
	// ManageLocationPage locations;
	FormDesignerPage fdp;
	VerificationsPage verification;
	HomePage hp;
	ResourceDesignerPage resourceDesigner;
	LoginPage lp;
	InboxPage inp;
	WorkGroupsPage wgp;
	FormsManagerPage fm;
	FSQABrowserRecordsPage frp;
	public static String formName;
	String Status = "Enabled";
	VerificationDetsParams vdp;
	FormDetails formDetails;
	FormFieldParams ffp;
	FormOperations formOperations;
	FormDesignParams fp;
	ManageRequirementPage mrp;
	ManageLocationPage mlp;

	// Data

	public static String verificationName;
	public static String rolename = "SuperAdmin";
	public static String formName1;
	public static String formName2;
	public static String verificationComment1;
	public static String locationCategoryValue1;
	LocationInstanceParams locationInstance1, locationInstance2;
	public static String locationInstanceValue1, locationInstanceValue2;
	public static String locationInstanceValue3, locationInstanceValue4;
	public static String equipmentCategoryValue1, equipmentCategoryValue2;
	public static String equipmentResourceInstanceValue1, customerResourceInstanceValue2;
	public static String chkFreeTxt, ParaTxt, chkSelectOne, SelectMultiple, Numeric, chkDate, chkTime, chkDateTime;
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String changeResourceCmmt;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customersCategoryValue;

	public static String resourceList;
	public static String resourceList1;
	public static String customersInstanceValue;
	public static String customersInstance2Value;
	public static String displayName = null;
	public static String programName;
	public static String programElementName;
	public static List<String> formsNMLst = new ArrayList<String>();
	public static String checkFormFM;
	public static String checkFormNamefm;
	public static String checkFormNamefm1;
	public static String checkFormNamefm2;
	public static String checkFormNamefm3;
	public static String checkFormNamefm4;
	public static String checkFormNamefm5;
	public static String checkRegFormName;
	public static String checkFormNamefmFormVersion;
	public static String checkFormNameFMSorting;
	public static String checkFormName38351;
	public static String checkFormLocation;
	public static String checkFormOverView;
	public static String checkFormOverViewLocFilter;

	public static String CheckFormVerification;

	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue, resourceInstanceValue1, resourceInstanceValue2;
	public static String signOffCmmt1;
	public static String questionnaireFormName1;
	public static String numFieldName;
	public static String supptempname;
	public static String supptempname1;
	public static String supplier;
	public static String doctype;
	public static String formreqname;
	public static String workGroupName;
	public static String locationname;
	public static String adminusername = "autouser01";
	public static String adminverificationPinTxt = "12345";
	public static String directobservation = "Direct Observation";
	public static String checkFormNamefmFormViewComment;
	public static String checkdisableFormNameVerification;

	public static String questionnaireFormNamelocation;
	public static String checkFormOverview;

	public static String checkformNameFilter;
	public static String checkFormLocation1;
	public static String questionnaireFormNamelocation1;
	public static String customersCategoryValue1;
	public static String customersInstanceValue1;
	public static String questionnaireFormNameOverview;
	public static String checkFormNameOverview;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		formName = CommonMethods.dynamicText("Chk");
		changeResourceCmmt = CommonMethods.dynamicText("ChangeResCmmt");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("AutoLocation_Inst");
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		customersInstance2Value = CommonMethods.dynamicText("Cust_Inst2");
		programName = CommonMethods.dynamicText("PD");
		programElementName = CommonMethods.dynamicText("PE");
		formsNMLst.add(formName);
		equipmentCategoryValue1 = CommonMethods.dynamicText("EquipmentCat1");
		equipmentCategoryValue2 = CommonMethods.dynamicText("EquipmentCat2");
		supptempname = CommonMethods.dynamicText("SuppTemp");
		supptempname = CommonMethods.dynamicText("SuppTemp1");
		locationname = CommonMethods.dynamicText("Auto_Location");
		workGroupName = CommonMethods.dynamicText("WG");
		formreqname = CommonMethods.dynamicText("Form");
		signOffCmmt1 = CommonMethods.dynamicText("Cmmt1");
		questionnaireFormName1 = CommonMethods.dynamicText("Auto_QuestionnaireForm1");
		verificationComment1 = CommonMethods.dynamicText("AutoComment1");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationCategoryValue1 = CommonMethods.dynamicText("LocCat11");
		locationInstanceValue = CommonMethods.dynamicText("Location_Inst");
		locationInstanceValue1 = CommonMethods.dynamicText("Location_Inst_11");
		locationInstanceValue2 = CommonMethods.dynamicText("Location_Inst_22");
		locationInstanceValue3 = CommonMethods.dynamicText("Location_Inst_1111");
		locationInstanceValue4 = CommonMethods.dynamicText("Location_Inst_1001");
		locationInstance1 = new LocationInstanceParams();
		verificationName = CommonMethods.dynamicText("AutoVN");
		checkFormNamefm = CommonMethods.dynamicText("Auto_CheckForm_FormMgr_PD");

		checkFormFM = CommonMethods.dynamicText("Auto_ChkForm_FM38629");

		checkFormNamefm1 = CommonMethods.dynamicText("Auto_ChkForm_FMChk1_PD");
		checkFormNamefm2 = CommonMethods.dynamicText("Auto_ChkForm_FMChk2_PD1001");
		checkFormNamefm4 = CommonMethods.dynamicText("Auto_ChkForm_FM_PD");
		checkFormNamefm5 = CommonMethods.dynamicText("Auto_FM_PD_CheckForm");
		checkFormNamefmFormVersion = CommonMethods.dynamicText("Auto_FM_PD_Chk_ClickFormVersion");
		CheckFormVerification = CommonMethods.dynamicText("Auto_FM_PD_Chk_verificationName");
		questionnaireFormName1 = CommonMethods.dynamicText("Auto_QuestionnaireForm1");
		// checkFormNameVerification=
		// CommonMethods.dynamicText("Auto_FormManager_PD_CHECK_verificationName");
		locationCategoryValue = CommonMethods.dynamicText("Location_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Resource_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Resource_Inst");
		resourceInstanceValue1 = CommonMethods.dynamicText("Resource_Inst1");
		resourceInstanceValue2 = CommonMethods.dynamicText("Resource_Inst2");
		locationInstanceValue = CommonMethods.dynamicText("Location_Inst");
		customersCategoryValue1 = CommonMethods.dynamicText("Customer_Inst");

		checkRegFormName = CommonMethods.dynamicText("Auto_CheckForm_REG_Form_A");
		checkformNameFilter = CommonMethods.dynamicText("Auto_FormManager_FM_checkform_filters");

		// checkFormName1 = = CommonMethods.dynamicText("Auto_CheckForm_FormMGR_REG");
		checkFormNamefmFormViewComment = CommonMethods.dynamicText("Auto_FM_PD_Chk_fmFormViewCommt");
		checkFormNameFMSorting = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_sorting");
		// checkdisableFormNameVerification =
		// CommonMethods.dynamicText("Auto_FormManager_PD_checkform_DisableVerification");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		mainPage = new CommonPage(driver);
		controlActions.getUrl(applicationUrl);

		lp = new LoginPage(driver);
		// FormsManagerPage fm = new FormsManagerPage(driver);
		fdp = new FormDesignerPage(driver);
		fbp = new FSQABrowserPage(driver);
		frp = new FSQABrowserRecordsPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		// locations = new ManageLocationPage(driver);
		mrp = new ManageRequirementPage(driver);
		fm = new FormsManagerPage(driver);
		mlp = new ManageLocationPage(driver);

		fbForms = new FSQABrowserFormsPage(driver);
		vdp = new VerificationDetsParams();

		HashMap<String, List<String>> resourceList = new HashMap<String, List<String>>();
		resourceList.put(CATEGORYTYPES.CUSTOMERS, Arrays.asList(customersInstanceValue));
		HashMap<String, List<String>> resourceList1 = new HashMap<String, List<String>>();
		resourceList1.put(CATEGORYTYPES.CUSTOMERS, Arrays.asList(customersInstanceValue));

		hp = lp.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		displayName = hp.getLoggedInUserDetails();
		if (displayName == "") {
			TCGFailureMsg = "Could NOT get display name for user - " + adminUsername;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		threadsleep(2000);
		
		verification = hp.clickVerificationsMenu(); 
		//To check
		verification.openExistingVerification(directobservation);
		verification.setRoleForVerification(rolename);
		//verification.searchAndSetRole(rolename); 
		//verification.searchAndSetRole(rolename);
		////verification.searchAndSetRole(rolename);
		
		

		vdp.VerificationName = verificationName;
		vdp.EnableVerification = true;
		vdp.AddComments = new ArrayList<String>();
		vdp.AddComments.add(verificationComment1);
		// vdp.AddComments.add(verificationComment2);
		vdp.Rolename = rolename;
		
		

		threadsleep(2000);
		if (!verification.createVerification(vdp)) {
			TCGFailureMsg = "Could NOT able to create verification";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);

		}

		hp.clickFSQABrowserMenu();
		ManageLocationPage mlp = new ManageLocationPage(driver);
		if (!mlp.createLocation(locationCategoryValue, locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create location category - " + locationCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		ManageResourcePage mrp = new ManageResourcePage(driver);
		if (!mrp.createResourceLinkLocation("Customers", customersCategoryValue, customersInstanceValue,
				locationInstanceValue)) {
			TCGFailureMsg = "Could NOT create resource " + customersInstanceValue + " & link Location - "
					+ locationInstanceValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

	}

	@Test(description = "Verification of the Forms Designer & Create Task Scheduler Links")
	public void TestCase_36144() throws Exception {

		hp.clickFSQABrowserMenu();

		// FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

		hp.clickFormsManagerMenu();

		boolean clickFormDesigner = fm.clickFormDesignerNew();
		TestValidation.IsTrue(clickFormDesigner, "Able to click Form Designer button",
				"Failed to navigate to Form Designer button");

		// FSQABrowserPage fbp1 = hp.clickFSQABrowserMenu();

		hp.clickFormsManagerMenu();

		boolean clickTaskSchdulerBtn = fm.clickTaskSchdulerBtn();
		TestValidation.IsTrue(clickTaskSchdulerBtn, "Able to click Task Scheduler button",
				"Failed to navigate to Task Scheduler button");

	}

	@Test(description = "Verification of Pagination")
	public void TestCase_38633() throws Exception {

		try {

			hp.clickFSQABrowserMenu();

			fm = hp.clickFormsManagerMenu();

			boolean verification = fm.clickVerificationBtn();
			TestValidation.IsTrue(verification, "Able to click on Verification", "Unable to click on Verification");

			boolean setPageTo40 = controlActions.selectDropdown(PGN_SIZE.SIZE_40, fm.Verification_PaginationDrd);
			hp.Sync();

			TestValidation.IsTrue(setPageTo40, "Able to set on Pagination to 40", "Unable to set on Pagination to 40");

			boolean setPageTo50 = controlActions.selectDropdown(PGN_SIZE.SIZE_50, fm.Verification_PaginationDrd);
			hp.Sync();
			TestValidation.IsTrue(setPageTo50, "Able to set on Pagination to 50", "Unable to set on Pagination to 50");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of listing of all the location in the location tab")
	public void TestCase_38635() throws Exception {

		try {

			hp.clickFSQABrowserMenu();

			fm = hp.clickFormsManagerMenu();

			boolean location = fm.clickLocationTab();
			TestValidation.IsTrue(location, "Able to click on Location", "Unable to click on Location");

		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of listing of all the location in the location tab")
	public void TestCase_38636() throws Exception {

		try {

			hp.clickFSQABrowserMenu();

			fm = hp.clickFormsManagerMenu();

			boolean location = fm.selectAndSetLocation(locationInstanceValue);
			TestValidation.IsTrue(location, "Able to search and highlight on Location",
					"Unable to search and highlight on Location");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of Bulk association of Forms with Verification Type")
	public void TestCase_38634() throws Exception {

		try {

			hp.clickFSQABrowserMenu();

			fm = hp.clickFormsManagerMenu();

			boolean selectallforms = fm.linkingVerificationWithAllForms(verificationName);
			TestValidation.IsTrue(selectallforms, "Able to select location forms",
					"Unable to search and highlight on Location");

			boolean clickOnNextPage = fm.clickOnNextPageArrow();
			TestValidation.IsTrue(clickOnNextPage, "Able to click on next Page with Arrow",
					"Unable to click on next page ");

			boolean selectallforms1 = fm.linkingVerificationWithAllFormsNextPageArrow(verificationName);
			TestValidation.IsTrue(selectallforms1, "Able to select location forms",
					"Unable to search and highlight on Location");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of association of Verification Type with the forms")
	public void TestCase_38629() throws Exception {
		try {

			hp.clickFSQABrowserMenu();

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormFM, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormFM,
					"Could NOT able to create and release forms:" + checkFormFM);

			hp.clickFormsManagerMenu();
			boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName, checkFormFM);
			if (!formLinkedwithVerifications) {
				TCGFailureMsg = "Could NOT Linked verification"
						+ fm.linkVerificationwithForm(verificationName, checkFormFM);
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			// Calling function for Direct observation linking

			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
					"Failed to navigate to FSQABrowser > Forms tab");

			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkFormFM);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
					"Form is NOT displayed in Forms Tab");

			boolean submitFormWithDirectObservation = fbp.clickDirectObservationVerification(adminUsername,
					adminverificationPinTxt);
			TestValidation.IsTrue(submitFormWithDirectObservation,
					"Verify form is saved with Direct Observation in Forms Tab",
					"Form is NOT saved with Direct Observation in Forms Tab");

			boolean submitData = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitData, "Able to submit the form with data:" + checkFormFM,
					"Could NOT able to submit the form with data:" + checkFormFM);

			hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, "For customer category, NAVIGATED to FSQABrowser > Records tab",
					"Failed to navigate to FSQABrowser > Records tab");

			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormFM);
			TestValidation.IsTrue(openRecord, "OPENED record for form - " + checkFormFM,
					"Failed to open record for form - " + checkFormFM);

			boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName, verificationComment1);
			TestValidation.IsTrue(signOffRecord, "SIGNED record for form - " + checkFormFM,
					"Failed to sign record for form " + checkFormFM);

			// FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			boolean openHistPopup1 = frp.clickHistoryIcon();
			TestValidation.IsTrue(openHistPopup1, "OPENED History popup for record - " + checkFormFM,
					"Failed to History popup for record - " + checkFormFM);
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of disabling the form from the forms tab ")
	public void TestCase_38647() throws Exception {
		try {
			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			// TCG_FormSubmission_Wrapper formSubmissionWrapper = new
			// TCG_FormSubmission_Wrapper();
			ApiUtils apiUtils = new ApiUtils();

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstanceValue));

			com.project.safetychain.api.models.support.Support.FormFieldParams numericField = new com.project.safetychain.api.models.support.Support.FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = "Numeric";

			// API - Form details
			// API - Generate unique ID for form to be created
			String formId = apiUtils.getUUID();

			// API - Form layout details
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = checkFormNamefm1;
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = customersCategoryValue;
			fp.ResourceInstanceNm = customersInstanceValue;
			fp.formElements = Arrays.asList(numericField);
			fp.CustomerResources = resourceCatMap;
			formCreationWrapper.API_Wrapper_Forms(fp);
			
			hp.clickFSQABrowserMenu();

//
//		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNamefm1,"Customers", customersCategoryValue,
//				customersInstanceValue);  

//		TestValidation.IsTrue(createAndReleaseForm, 
//				"Able to create and release form:" + checkFormNamefm1,
//				"Could NOT able to create and release forms:" +checkFormNamefm1);

			fm = hp.clickFormsManagerMenu();
			boolean searchingForm = fm.searchForm(checkFormNamefm1);
			TestValidation.IsTrue(searchingForm, "OPENED the search form - " + checkFormNamefm1,
					"Failed to open searched form - " + checkFormNamefm1);

			String formStatus = fm.getFormManagerDetails(FM_FIELDS.STATUS);
			boolean verifyFormStatus = formStatus.contains(FM_STATUS.ENABLED);
			TestValidation.IsTrue(verifyFormStatus, "Verified Form is in  Enabled state",
					"Failed to verify Form is in  Enabled state");

			boolean formSelect = fm.selectForm(checkFormNamefm1);
			TestValidation.IsTrue(formSelect, "Form selection is done", "Failed to select the form");

			boolean formDisabled = fm.performEnableDisableAction(formStatus);
			TestValidation.IsTrue(formDisabled, " Form  is get Disabled", "Failed to disable the form");

			HomePage hp = new HomePage(driver);

			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Customers", "Forms");
			TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
					"Failed to navigate to FSQABrowser>Forms Tab");

			boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormNamefm1);
			TestValidation.IsFalse(applyFilterAndOpenForDetails, "Verify form is not displayed in Forms Tab",
					"Form is displayed in Forms Tab");

		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of enabling the form from the forms tab ")
	public void TestCase_38648() throws Exception {
		try {

			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			// TCG_FormSubmission_Wrapper formSubmissionWrapper = new
			// TCG_FormSubmission_Wrapper();
			ApiUtils apiUtils = new ApiUtils();

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstanceValue));

			com.project.safetychain.api.models.support.Support.FormFieldParams numericField = new com.project.safetychain.api.models.support.Support.FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = "Numeric";

			// API - Form details
			// API - Generate unique ID for form to be created
			String formId = apiUtils.getUUID();

			// API - Form layout details
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = checkFormNamefm2;
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = customersCategoryValue;
			fp.ResourceInstanceNm = customersInstanceValue;
			fp.formElements = Arrays.asList(numericField);
			fp.CustomerResources = resourceCatMap;

			formCreationWrapper.API_Wrapper_Forms(fp);
			hp.clickFSQABrowserMenu();
			

//			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNamefm2, "Customers",
//					customersCategoryValue, customersInstanceValue);
//
//			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormNamefm2,
//					"Could NOT able to create and release forms:" + checkFormNamefm2);

			fm = hp.clickFormsManagerMenu();
			boolean searchingForm = fm.searchForm(checkFormNamefm2);
			TestValidation.IsTrue(searchingForm, "OPENED the search form - " + checkFormNamefm2,
					"Failed to open searched form - " + checkFormNamefm2);

			String formStatus = fm.getFormManagerDetails(FM_FIELDS.STATUS);
			boolean verifyFormStatus = formStatus.contains(FM_STATUS.ENABLED);
			TestValidation.IsTrue(verifyFormStatus, "Verified Form is in  Enabled state",
					"Failed to verify Form is in  Enabled state");

			boolean formSelect = fm.selectForm(checkFormNamefm2);
			TestValidation.IsTrue(formSelect, "Form selection is done", "Failed to select the form");

			boolean formDisabled = fm.performEnableDisableAction(formStatus);
			TestValidation.IsTrue(formDisabled, " Form  is get Disabled", "Failed to disable the form");

			HomePage hp = new HomePage(driver);

			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

			boolean navigate = fbp.selectResourceDropDownandNavigate("Customers", "Forms");
			TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
					"Failed to navigate to FSQABrowser>Forms Tab");

			boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormNamefm2);
			TestValidation.IsFalse(applyFilterAndOpenForDetails, "Verify form is displayed in Forms Tab",
					"Form is NOT displayed in Forms Tab");

			fm = hp.clickFormsManagerMenu();

			fm.searchForm(checkFormNamefm2);
			String formStatus1 = fm.getFormManagerDetails(FM_FIELDS.STATUS);

			boolean verifyFormStatusNew = formStatus1.contains(FM_STATUS.DISABLED);
			TestValidation.IsTrue(verifyFormStatusNew, "Verified the form is in disabled state ",
					"Failed to Verify the form is in disabled stat");

			boolean formSelect_New = fm.selectForm(checkFormNamefm2);
			TestValidation.IsTrue(formSelect_New, "Form selection is done", "Failed to select the form");

			boolean formEnabled = fm.performEnableDisableAction(formStatus1);
			TestValidation.IsTrue(formEnabled, " Form is get Enabled", "Failed to enable the form");
			//// From here
			FSQABrowserPage fbp1 = hp.clickFSQABrowserMenu();

			boolean navigateToforms = fbp1.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
					"Failed to navigate to FSQABrowser > Forms tab");

			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkFormNamefm2);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
					"Form is NOT displayed in Forms Tab");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of disassociation of Verification Type with the forms")
	public void TestCase_38630() throws Exception {

		try {

			hp.clickFSQABrowserMenu();

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNamefm5, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormNamefm5,
					"Could NOT able to create and release forms:" + checkFormNamefm5);

			hp.clickFormsManagerMenu();
			boolean formLinkedwithVerifications = fm.linkVerificationwithForm(verificationName, checkFormNamefm5);
			if (!formLinkedwithVerifications) {
				TCGFailureMsg = "Could NOT Linked verification"
						+ fm.linkVerificationwithForm(verificationName, checkFormNamefm5);
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			// Calling function for Direct observation delinking
			// --delinkVerificationwithForm
			hp.clickFormsManagerMenu();
			boolean delinkDirectObservationtoForm = fm.delinkVerificationwithForm(directobservation, checkFormNamefm5);
			TestValidation.IsTrue(delinkDirectObservationtoForm,
					"Verification Direct observation is delinked with Form",
					"Failed to delink the Verification Direct observation with Form");

			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
					"Failed to navigate to FSQABrowser > Forms tab");

			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkFormNamefm5);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
					"Form is NOT displayed in Forms Tab");

			boolean submitData = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitData, "Able to submit the form with data:" + checkFormNamefm5,
					"Could NOT able to submit the form with data:" + checkFormNamefm5);

			hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, "For customer category, NAVIGATED to FSQABrowser > Records tab",
					"Failed to navigate to FSQABrowser > Records tab");

			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormNamefm5);
			TestValidation.IsTrue(openRecord, "OPENED record for form - " + checkFormNamefm5,
					"Failed to open record for form - " + checkFormNamefm5);

			boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName, verificationComment1);
			TestValidation.IsTrue(signOffRecord, "SIGNED record for form - " + checkFormNamefm5,
					"Failed to sign record for form " + checkFormNamefm5);

			// FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);

			// boolean openHistPopup1 = frp.clickHistoryIcon();
			// TestValidation.IsTrue(openHistPopup1,
			// "OPENED History popup for record - " + checkFormNamefm5,
			// "Failed to History popup for record - " + checkFormNamefm5);
			// //close
			DateTime dt = new DateTime();
			String currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
			HashMap<String, String> histDetail = new HashMap<String, String>();
			histDetail.put(null, verificationName + "|" + displayName + "|" + verificationComment1 + "|" + currentDate);

			HistoryDetails hd = new HistoryDetails();
			hd.HeaderDetail = histDetail;

			boolean verifyHistory = frp.verifyHistoryPopupDetails(hd);
			TestValidation.IsTrue(verifyHistory,
					"VERIFIED the record has been signed by Verification " + verificationName,
					"Failed to verify whether record is signed by Verification " + verificationName + " or not");

			// trycatch

		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = " Verification of Details tab")
	public void TestCase_38644() throws Exception {
		try {

			hp.clickFSQABrowserMenu();

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNamefm4, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormNamefm4,
					"Could NOT able to create and release forms:" + checkFormNamefm4);

			fm = hp.clickFormsManagerMenu();
			// boolean searchingForm = fm.searchAndFormSelection(checkFormNamefm4);
			// //searchAndSelectForm
			boolean searchingForm = fm.searchAndFormSelection(checkFormNamefm4);
			TestValidation.IsTrue(searchingForm, "OPENED the search form - " + checkFormNamefm4,
					"Failed to open searched form - " + checkFormNamefm4);

		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = " Verification of Version No & its form definition")
	public void TestCase_38645() throws Exception {
		try {
			hp.clickFSQABrowserMenu();

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNamefmFormVersion, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormNamefmFormVersion,
					"Could NOT able to create and release forms:" + checkFormNamefmFormVersion);

			fm = hp.clickFormsManagerMenu();
			boolean searchingForm = fm.searchAndFormSelection(checkFormNamefmFormVersion);
			TestValidation.IsTrue(searchingForm, "OPENED the search form - " + checkFormNamefmFormVersion,
					"Failed to open searched form - " + checkFormNamefmFormVersion);

			boolean clickOnFormVersion = fm.clickOnFormVersion(checkFormNamefmFormVersion);
			TestValidation.IsTrue(clickOnFormVersion, "OPENED the search form - " + checkFormNamefmFormVersion,
					"Failed to open searched form - " + checkFormNamefmFormVersion);

		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of Comment validation")
	public void TestCase_38646() throws Exception {
		hp.clickFSQABrowserMenu();

		try {

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNamefmFormViewComment,
					"Customers", customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm,
					"Able to create and release form:" + checkFormNamefmFormViewComment,
					"Could NOT able to create and release forms:" + checkFormNamefmFormViewComment);

			fm = hp.clickFormsManagerMenu();
			boolean searchingForm = fm.searchAndFormSelection(checkFormNamefmFormViewComment);
			TestValidation.IsTrue(searchingForm, "OPENED the search form - " + checkFormNamefmFormViewComment,
					"Failed to open searched form - " + checkFormNamefmFormViewComment);

			boolean clickOnFormVersion = fm.clickOnFormViewComment(checkFormNamefmFormViewComment);
			TestValidation.IsTrue(clickOnFormVersion, "OPENED the search form - " + checkFormNamefmFormViewComment,
					"Failed to open searched form - " + checkFormNamefmFormViewComment);

			boolean clickOnCloseButton = fm.clickOverviewFormClose();
			TestValidation.IsTrue(clickOnCloseButton, "Able to Close the page", "Failed to Close the page ");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of all the locations in the grid")
	public void TestCase_38654() throws Exception {
		try {

			hp.clickFSQABrowserMenu();

			fm = hp.clickFormsManagerMenu();

			boolean location = fm.clickLocationTab();
			TestValidation.IsTrue(location, "Able to click on Location", "Unable to click on Location");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of all Verification Type List ")
	public void TestCase_38650() throws Exception {

		try {
			
			// ------------------------------------------------------------------------------------------------
						// API Implementation
						TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
						// TCG_FormSubmission_Wrapper formSubmissionWrapper = new
						// TCG_FormSubmission_Wrapper();
						ApiUtils apiUtils = new ApiUtils();

						HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
						resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstanceValue));

						com.project.safetychain.api.models.support.Support.FormFieldParams numericField = new com.project.safetychain.api.models.support.Support.FormFieldParams();
						numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
						numericField.Name = "Numeric";

						// API - Form details
						// API - Generate unique ID for form to be created
						String formId = apiUtils.getUUID();

						// API - Form layout details
						FormParams fp = new FormParams();
						fp.FormId = formId;
						fp.FormName = CheckFormVerification;
						fp.type = DPT_FORM_TYPES.CHECK;
						fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
						fp.ResourceCategoryNm = customersCategoryValue;
						fp.ResourceInstanceNm = customersInstanceValue;
						fp.formElements = Arrays.asList(numericField);
						fp.CustomerResources = resourceCatMap;

						formCreationWrapper.API_Wrapper_Forms(fp);
						hp.clickFSQABrowserMenu();

//			hp.clickFSQABrowserMenu();
//
//			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", CheckFormVerification, "Customers",
//					customersCategoryValue, customersInstanceValue);
//
//			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + CheckFormVerification,
//					"Could NOT able to create and release forms:" + CheckFormVerification);

			hp.clickFormsManagerMenu();
			boolean formVerification = fm.verificationStatus(verificationName, CheckFormVerification);
			TestValidation.IsTrue(formVerification, "OPENED the searched verification - " + verificationName,
					"Failed to open verificationName - " + verificationName);

			hp.clickFormsManagerMenu();
			boolean formVerificationDirObser = fm.verificationStatus(directobservation, CheckFormVerification);
			TestValidation.IsTrue(formVerificationDirObser, "OPENED the searched verification - " + directobservation,
					"Failed to open verificationName - " + directobservation);
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of enabling the verification type for the given form.")
	public void TestCase_38651() throws Exception {
		try {
			
			checkFormName38351 = CommonMethods.dynamicText("Auto_ChkFm_verificationName");
			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
			// TCG_FormSubmission_Wrapper formSubmissionWrapper = new
			// TCG_FormSubmission_Wrapper();
			ApiUtils apiUtils = new ApiUtils();

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstanceValue));

			com.project.safetychain.api.models.support.Support.FormFieldParams numericField = new com.project.safetychain.api.models.support.Support.FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = "Numeric";

			// API - Form details
			// API - Generate unique ID for form to be created
			String formId = apiUtils.getUUID();

			// API - Form layout details
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = checkFormName38351;
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = customersCategoryValue;
			fp.ResourceInstanceNm = customersInstanceValue;
			fp.formElements = Arrays.asList(numericField);
			fp.CustomerResources = resourceCatMap;

			formCreationWrapper.API_Wrapper_Forms(fp);
			hp.clickFSQABrowserMenu();


			
//
//			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormName38351, "Customers",
//					customersCategoryValue, customersInstanceValue);
//
//			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormName38351,
//					"Could NOT able to create and release forms:" + checkFormName38351);

			hp.clickFormsManagerMenu();
			boolean formVerificationEnable = fm.verificationToggle(checkFormName38351, verificationName);
			TestValidation.IsTrue(formVerificationEnable,
					"OPENED the searched form and verificationName enabled - " + verificationName,
					"Failed to open searched form and verificationName enabled - " + verificationName);

			// Calling function for Direct observation linking

			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
					"Failed to navigate to FSQABrowser > Forms tab");

			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name", checkFormName38351);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
					"Form is NOT displayed in Forms Tab");

			boolean submitFormWithDirectObservation = fbp.clickDirectObservationVerification(adminUsername,
					adminverificationPinTxt);
			TestValidation.IsTrue(submitFormWithDirectObservation,
					"Verify form is saved with Direct Observation in Forms Tab",
					"Form is NOT saved with Direct Observation in Forms Tab");

			boolean submitData = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitData, "Able to submit the form with data:" + checkFormName38351,
					"Could NOT able to submit the form with data:" + checkFormName38351);

			hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, "For customer category, NAVIGATED to FSQABrowser > Records tab",
					"Failed to navigate to FSQABrowser > Records tab");

			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName38351);
			TestValidation.IsTrue(openRecord, "OPENED record for form - " + checkFormName38351,
					"Failed to open record for form - " + checkFormName38351);

			boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName, verificationComment1);
			TestValidation.IsTrue(signOffRecord, "SIGNED record for form - " + checkFormName38351,
					"Failed to sign record for form " + checkFormName38351);

//			boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName, verificationComment1);
//			TestValidation.IsTrue(signOffRecord, "SIGNED record for form - " + checkFormFM,
//					"Failed to sign record for form " + checkFormFM);

			// FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
//		boolean openHistPopup1 = frp.clickHistoryIcon();
//		TestValidation.IsTrue(openHistPopup1, 
//				"OPENED History popup for record - " + checkFormName38351, 
//				"Failed to History popup for record - " + checkFormName38351);
			DateTime dt = new DateTime();
			String currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(adminTimezone));
			HashMap<String, String> histDetail = new HashMap<String, String>();
			histDetail.put(null, verificationName + "|" + displayName + "|" + verificationComment1 + "|" + currentDate);

			HistoryDetails hd = new HistoryDetails();
			hd.HeaderDetail = histDetail;

			boolean verifyHistory = frp.verifyHistoryPopupDetails(hd);
			TestValidation.IsTrue(verifyHistory,
					"VERIFIED the record has been signed by Verification " + verificationName,
					"Failed to verify whether record is signed by Verification " + verificationName + " or not");

		}

		finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of disabling the verification type for the given form.")
	public void TestCase_38652() throws Exception {

		try {

			hp.clickFSQABrowserMenu();

			checkdisableFormNameVerification = CommonMethods
					.dynamicText("Auto_FormManager_PD_CHECK_DisableverificationName");

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkdisableFormNameVerification,
					"Customers", customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm,
					"Able to create and release form:" + checkdisableFormNameVerification,
					"Could NOT able to create and release forms:" + checkdisableFormNameVerification);

			hp.clickFormsManagerMenu();
			boolean userVerificationEnabled = fm.verificationToggle(checkdisableFormNameVerification, verificationName);
			TestValidation.IsTrue(userVerificationEnabled,
					"OPENED the searched form and verificationName enabled - " + verificationName,
					"Failed to open searched form and verificationName enabled - " + verificationName);

			hp.clickFormsManagerMenu();
			boolean userVerificationDisabled = fm.verificationToggle(checkdisableFormNameVerification,
					verificationName);
			TestValidation.IsTrue(userVerificationDisabled,
					"OPENED the searched form and verificationName disabled - " + verificationName,
					"Failed to open searched form and verificationName disabled - " + verificationName);

			hp.clickFormsManagerMenu();
			boolean formVerificationDisable = fm.verificationToggle(checkdisableFormNameVerification,
					directobservation);
			TestValidation.IsTrue(formVerificationDisable,
					"OPENED the searched form and verificationName enabled - " + directobservation,
					"Failed to open searched form and verificationName enabled - " + directobservation);

			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();

			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, "For customer category, NAVIGATED to FSQABrowser > Forms tab",
					"Failed to navigate to FSQABrowser > Forms tab");

			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails("Form Name",
					checkdisableFormNameVerification);
			TestValidation.IsTrue(applyFilterAndOpenForm, "Verify form is displayed in Forms Tab",
					"Form is NOT displayed in Forms Tab");

			// boolean submitFormWithDirectObservation =
			// fbp.clickDirectObservationVerification(adminUsername,
			// adminverificationPinTxt);
			// TestValidation.IsTrue(submitFormWithDirectObservation,
			// "Verify form is saved with Direct Observation in Forms Tab",
			// "Form is NOT saved with Direct Observation in Forms Tab");

			boolean submitData = fbForms.submitData(false, false, false, true, false);
			TestValidation.IsTrue(submitData, "Able to submit the form with data:" + checkdisableFormNameVerification,
					"Could NOT able to submit the form with data:" + checkdisableFormNameVerification);

			hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, "For customer category, NAVIGATED to FSQABrowser > Records tab",
					"Failed to navigate to FSQABrowser > Records tab");

			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,
					checkdisableFormNameVerification);
			TestValidation.IsTrue(openRecord, "OPENED record for form - " + checkdisableFormNameVerification,
					"Failed to open record for form - " + checkdisableFormNameVerification);

			// boolean signOffRecord =
			// frp.signoffRecordWithVerfication(verificationName,verificationComment1);
			// TestValidation.IsTrue(signOffRecord,
			// "SIGNED record for form - " + checkFormFM,
			// "Failed to sign record for form " + checkFormFM);

			boolean signOffRecord = frp.signoffRecord(verificationComment1);
			TestValidation.IsTrue(signOffRecord, "SIGNED record for form - " + checkdisableFormNameVerification,
					"Failed to sign record for form " + checkdisableFormNameVerification);

			// FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			boolean openHistoryPopup = frp.clickHistoryIcon();
			TestValidation.IsTrue(openHistoryPopup,
					"OPENED History popup for record - " + checkdisableFormNameVerification,
					"Failed to History popup for record - " + checkdisableFormNameVerification);
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Forms Manager || Verification of sorting order")
	public void TestCase_38632() { // passed

		hp.clickFSQABrowserMenu();

		try {

			FormsManagerPage fm = hp.clickFormsManagerMenu();
			TestValidation.Equals(fm.error, false, "CLICKED on FormsManager menu",
					"Could NOT click on FormsManager menu");

			boolean clickVerification = fm.verificationClick(verificationName);
			TestValidation.IsTrue(clickVerification, "OPENED the search form - " + verificationName,
					"Failed to open searched form - " + verificationName);

			boolean sortFormAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.FORM, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortFormAsc,
					"For Form column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Form column");

			String verifySortFormAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.FORM));
			TestValidation.IsTrue(verifySortFormAsc.equals(SORTING.ASC),
					"VERIFIED values of the column Form are sorted in Ascending",
					"Failed to verify that values of the column Form are sorted in Ascending");

			boolean sortFormDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.FORM, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortFormDsc,
					"For Form column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Form column");

			String verifySortFormDsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.FORM));
			TestValidation.IsTrue(verifySortFormDsc.equals(SORTING.DSC),
					"VERIFIED values of the column Form are sorted in Descending",
					"Failed to verify that values of the column Form are sorted in Descending");
			///////////// Type/////////////
			boolean sortTypeAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.TYPE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortTypeAsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Type column");

			String verifySortTypeAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.TYPE));
			TestValidation.IsTrue(verifySortTypeAsc.equals(SORTING.ASC) || verifySortTypeAsc.equals(SORTING.EQL),
					"VERIFIED values of the column Type are sorted in Ascending",
					"Failed to verify that values of the column Type are sorted in Ascending");

			boolean sortTypeDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.TYPE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortTypeDsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Type column");

			String SORTDSC = CommonMethods.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.TYPE));
			System.out.println(SORTDSC + "Received Value");
			TestValidation.IsTrue(SORTDSC.equals(SORTING.DSC) || SORTDSC.equals(SORTING.EQL),
					"VERIFIED values of the column Type are sorted in Descending",
					"Failed to verify that values of the column Type are sorted in Descending");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Forms Manager || Verification of sorting order")
	public void TestCase_38638() {

		try {
			hp.clickFSQABrowserMenu();

			checkFormLocation = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_LOCATION");
			questionnaireFormNamelocation = CommonMethods
					.dynamicText("Automation_FormManager_PD_QuestionnaireForm_LOCATION");

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormLocation, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormLocation,
					"Could NOT able to create and release forms:" + checkFormLocation);

			hp.clickFormsManagerMenu();

			TestValidation.Equals(fm.error, false, "CLICKED on Forms menu", "Could NOT click on Forms menu");

			boolean togglelocON = fm.LocationFormtoggleONLinking(checkFormLocation);
			TestValidation.IsTrue(togglelocON, "Toggle ON location is done", "Failed to toggle ON the location");

			hp.clickFSQABrowserMenu();

			boolean createAndReleaseQuestForm = fdp.createAndReleaseForm("Questionnaire", questionnaireFormNamelocation,
					"Customers", customersCategoryValue, customersInstanceValue);
			TestValidation.IsTrue(createAndReleaseQuestForm,
					"Able to create and release form:" + questionnaireFormNamelocation,
					"Could NOT able to create and release forms:" + questionnaireFormNamelocation);
			hp.clickFormsManagerMenu();
			TestValidation.Equals(fm.error, false, "CLICKED on Forms menu", "Could NOT click on Forms menu");

			boolean toggleQuestFormLocON = fm.LocationFormtoggleONLinking(questionnaireFormNamelocation);
			TestValidation.IsTrue(toggleQuestFormLocON, "Toggle ON location is done",
					"Failed to toggle ON the location");

			FormsManagerPage fm = hp.clickFormsManagerMenu();
			TestValidation.Equals(fm.error, false, "CLICKED on FormsManager menu",
					"Could NOT click on FormsManager menu");
			// String locationName = null;

			// boolean clickVerification = fm.selectAndSetLocation(locationname);

			boolean clickLocation = fm.selectAndSetLocation(locationInstanceValue);
			TestValidation.IsTrue(clickLocation, "OPENED the search form - " + locationInstanceValue,
					"Failed to open searched form - " + locationInstanceValue);

			boolean sortFormAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.FORM, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortFormAsc,
					"For Form column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Form column");

			String verifySortFormAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.FORM));
			TestValidation.IsTrue(verifySortFormAsc.equals(SORTING.ASC),
					"VERIFIED values of the column Form are sorted in Ascending",
					"Failed to verify that values of the column Form are sorted in Ascending");

			boolean sortFormDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.FORM, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortFormDsc,
					"For Form column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Form column");

			String verifySortFormDsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.FORM));
			TestValidation.IsTrue(verifySortFormDsc.equals(SORTING.DSC),
					"VERIFIED values of the column Form are sorted in Descending",
					"Failed to verify that values of the column Form are sorted in Descending");
			///////////// Type/////////////
			boolean sortTypeAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.TYPE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortTypeAsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Type column");

			String verifySortTypeAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.TYPE));
			TestValidation.IsTrue(verifySortTypeAsc.equals(SORTING.ASC) || verifySortTypeAsc.equals(SORTING.EQL),
					"VERIFIED values of the column Type are sorted in Ascending",
					"Failed to verify that values of the column Type are sorted in Ascending");

			boolean sortTypeDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.TYPE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortTypeDsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Type column");

			String SORTDSC = CommonMethods.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.TYPE));
			System.out.println(SORTDSC + "Received Value");
			TestValidation.IsTrue(SORTDSC.equals(SORTING.DSC) || SORTDSC.equals(SORTING.EQL),
					"VERIFIED values of the column Type are sorted in Descending",
					"Failed to verify that values of the column Type are sorted in Descending");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Forms Manager || Verification of sorting order")
	public void TestCase_38628() {

		try {

			hp.clickFSQABrowserMenu();

			checkFormOverview = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_overview");
			// questionnaireFormNamelocation =
			// CommonMethods.dynamicText("Automation_QuestionnaireForm_FM");

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormOverview, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormOverview,
					"Could NOT able to create and release forms:" + checkFormOverview);

			hp.clickFormsManagerMenu();
			// FormsManagerPage fm = hp.clickFormsManagerMenu();

			TestValidation.Equals(fm.error, false, "CLICKED on Forms menu", "Could NOT click on Forms menu");
			///////////// LastModified//////////////////////////
			boolean sortLastModifiedAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.LASTMODIFIED,
					COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortLastModifiedAsc,
					"For LastModified column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING
							+ " for LastModified column");

			String verifySortLastModifiedAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.LASTMODIFIED));
			TestValidation.IsTrue(
					verifySortLastModifiedAsc.equals(SORTING.ASC) || verifySortLastModifiedAsc.equals(SORTING.EQL),
					"VERIFIED values of the column LastModified are sorted in Ascending",
					"Failed to verify that values of the column LastModified are sorted in Ascending");

			boolean sortLastModifiedDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.LASTMODIFIED,
					COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortLastModifiedDsc,
					"For LastModified column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING
							+ " for LastModified column");

			String SORTDSC4 = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.LASTMODIFIED));
			// System.out.println(SORTDSC +"Received Value");
			TestValidation.IsTrue(SORTDSC4.equals(SORTING.DSC) || SORTDSC4.equals(SORTING.EQL),
					"VERIFIED values of the column LastModified are sorted in Descending",
					"Failed to verify that values of the column LastModified are sorted in Descending");

			boolean sortFormAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.FORM, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortFormAsc,
					"For Form column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Form column");

			String verifySortFormAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.FORM));
			TestValidation.IsTrue(verifySortFormAsc.equals(SORTING.ASC),
					"VERIFIED values of the column Form are sorted in Ascending",
					"Failed to verify that values of the column Form are sorted in Ascending");

			boolean sortFormDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.FORM, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortFormDsc,
					"For Form column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Form column");

			String verifySortFormDsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.FORM));
			TestValidation.IsTrue(verifySortFormDsc.equals(SORTING.DSC),
					"VERIFIED values of the column Form are sorted in Descending",
					"Failed to verify that values of the column Form are sorted in Descending");
			///////////// Type/////////////
			boolean sortTypeAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.TYPE, COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortTypeAsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Type column");

			String verifySortTypeAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.TYPE));
			TestValidation.IsTrue(verifySortTypeAsc.equals(SORTING.ASC) || verifySortTypeAsc.equals(SORTING.EQL),
					"VERIFIED values of the column Type are sorted in Ascending",
					"Failed to verify that values of the column Type are sorted in Ascending");

			boolean sortTypeDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.TYPE, COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortTypeDsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Type column");

			String SORTDSC = CommonMethods.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.TYPE));
			System.out.println(SORTDSC + "Received Value");
			TestValidation.IsTrue(SORTDSC.equals(SORTING.DSC) || SORTDSC.equals(SORTING.EQL),
					"VERIFIED values of the column Type are sorted in Descending",
					"Failed to verify that values of the column Type are sorted in Descending");

			///////////// Version//////////////////////////
			boolean sortVersionAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.VERSION, COLUMN_SETTING.SORTASCENDING,
					null);
			TestValidation.IsTrue(sortVersionAsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Version column");

			String verifySortVersionAsc = CommonMethods
					.verifySortingForColumnDoubleType(fm.getListOfElementsForFormManager(FM_FIELDS.VERSION));
			TestValidation.IsTrue(verifySortVersionAsc.equals(SORTING.ASC) || verifySortVersionAsc.equals(SORTING.EQL),
					"VERIFIED values of the column Status are sorted in Ascending",
					"Failed to verify that values of the column Status are sorted in Ascending");

			boolean sortVersionDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.VERSION, COLUMN_SETTING.SORTDESCENDING,
					null);
			TestValidation.IsTrue(sortVersionDsc,
					"For Version column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Version column");

			String SORTDSC1 = CommonMethods
					.verifySortingForColumnDoubleType(fm.getListOfElementsForFormManager(FM_FIELDS.VERSION));
			// System.out.println(SORTDSC +"Received Value");
			TestValidation.IsTrue(SORTDSC1.equals(SORTING.DSC) || SORTDSC1.equals(SORTING.EQL),
					"VERIFIED values of the column Version are sorted in Descending",
					"Failed to verify that values of the column Version are sorted in Descending");
			///////////// Draft//////////////////////////
			boolean sortDraftAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.DRAFT, COLUMN_SETTING.SORTASCENDING,
					null);
			TestValidation.IsTrue(sortDraftAsc,
					"For Draft column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Draft column");

			String verifySortDraftAsc = CommonMethods
					.verifySortingForColumnDoubleType(fm.getListOfElementsForFormManager(FM_FIELDS.DRAFT));
			TestValidation.IsTrue(verifySortDraftAsc.equals(SORTING.ASC) || verifySortVersionAsc.equals(SORTING.EQL),
					"VERIFIED values of the column Draft are sorted in Ascending",
					"Failed to verify that values of the column Draft are sorted in Ascending");

			boolean sortDraftDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.DRAFT, COLUMN_SETTING.SORTDESCENDING,
					null);
			TestValidation.IsTrue(sortDraftDsc,
					"For Draft column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Draft column");

			String SORTDSC3 = CommonMethods
					.verifySortingForColumnDoubleType(fm.getListOfElementsForFormManager(FM_FIELDS.DRAFT));
			// System.out.println(SORTDSC +"Received Value");
			TestValidation.IsTrue(SORTDSC3.equals(SORTING.DSC) || SORTDSC3.equals(SORTING.EQL),
					"VERIFIED values of the column Draft are sorted in Descending",
					"Failed to verify that values of the column Draft are sorted in Descending");
			///////////// LastModified//////////////////////////

			// TO BE UNCOMMENTED
			// boolean sortLastModifiedAsc =
			// fm.openAndApplySettingsForColumn(FM_FIELDS.LASTMODIFIED,
			// COLUMN_SETTING.SORTASCENDING, null);
			// TestValidation.IsTrue(sortLastModifiedAsc,
			// "For LastModified column, APPLIED the column setting as " +
			// COLUMN_SETTING.SORTASCENDING,
			// "Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + "
			// for LastModified column");

			// String verifySortLastModifiedAsc =
			// CommonMethods.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.LASTMODIFIED));
			// TestValidation.IsTrue(verifySortLastModifiedAsc.equals(SORTING.ASC) ||
			// verifySortLastModifiedAsc.equals(SORTING.EQL),
			// "VERIFIED values of the column LastModified are sorted in Ascending",
			// "Failed to verify that values of the column LastModified are sorted in
			// Ascending");

			// boolean sortLastModifiedDsc =
			// fm.openAndApplySettingsForColumn(FM_FIELDS.LASTMODIFIED,
			// COLUMN_SETTING.SORTDESCENDING, null);
			// TestValidation.IsTrue(sortLastModifiedDsc,
			// "For LastModified column, APPLIED the column setting as " +
			// COLUMN_SETTING.SORTDESCENDING,
			// "Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + "
			// for LastModified column");

			// String SORTDSC4 =
			// CommonMethods.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.LASTMODIFIED));
			//// System.out.println(SORTDSC +"Received Value");
			// TestValidation.IsTrue(SORTDSC4.equals(SORTING.DSC) ||
			// SORTDSC4.equals(SORTING.EQL),
			// "VERIFIED values of the column LastModified are sorted in Descending",
			// "Failed to verify that values of the column LastModified are sorted in
			// Descending");

			///////////// ModifiedBy//////////////////////////
			// controlActions.refreshPage();
			boolean setHR = fm.clickMovetoViewColumn(FM_FIELDS.MODIFIEDBY);
			TestValidation.IsTrue(setHR, "Done HR" + COLUMN_SETTING.SORTASCENDING, "Failed to apply to HR");

			boolean sortModifiedByAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.MODIFIEDBY,
					COLUMN_SETTING.SORTASCENDING, null);
			TestValidation.IsTrue(sortModifiedByAsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for ModifiedBy column");

			String verifySortModifiedByAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.MODIFIEDBY));
			TestValidation.IsTrue(
					verifySortModifiedByAsc.equals(SORTING.ASC) || verifySortModifiedByAsc.equals(SORTING.EQL),
					"VERIFIED values of the column Modified By are sorted in Ascending",
					"Failed to verify that values of the column Modified By are sorted in Ascending");

			boolean sortModifiedByDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.MODIFIEDBY,
					COLUMN_SETTING.SORTDESCENDING, null);
			TestValidation.IsTrue(sortModifiedByDsc,
					"For Modified By column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING
							+ " for Modified By column");

			String SORTDSC5 = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.MODIFIEDBY));
			// System.out.println(SORTDSC +"Received Value");
			TestValidation.IsTrue(SORTDSC5.equals(SORTING.DSC) || SORTDSC5.equals(SORTING.EQL),
					"VERIFIED values of the column Modified By are sorted in Descending",
					"Failed to verify that values of the column Modified By are sorted in Descending");

			///////////// Status//////////////////////////

			boolean setHR1 = fm.clickMovetoViewColumn(FM_FIELDS.STATUS);
			TestValidation.IsTrue(setHR1, "Done HR1" + COLUMN_SETTING.SORTASCENDING, "Failed to apply to HR1");

			boolean sortStatusAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.STATUS, COLUMN_SETTING.SORTASCENDING,
					null);
			TestValidation.IsTrue(sortStatusAsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Type column");

			String verifySortStatusAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.STATUS));
			TestValidation.IsTrue(verifySortStatusAsc.equals(SORTING.ASC) || verifySortStatusAsc.equals(SORTING.EQL),
					"VERIFIED values of the column Status are sorted in Ascending",
					"Failed to verify that values of the column Status are sorted in Ascending");

			boolean sortStatusDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.STATUS, COLUMN_SETTING.SORTDESCENDING,
					null);
			TestValidation.IsTrue(sortStatusDsc,
					"For Type column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Status column");

			String SORTDSC2 = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.STATUS));
			// System.out.println(SORTDSC +"Received Value");
			TestValidation.IsTrue(SORTDSC2.equals(SORTING.DSC) || SORTDSC2.equals(SORTING.EQL),
					"VERIFIED values of the column Status are sorted in Descending",
					"Failed to verify that values of the column Status are sorted in Descending");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of all the filters")
	public void TestCase_38631() { // Passed

		hp.clickFSQABrowserMenu();
		try {

			checkformNameFilter = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_filter");
			// questionnaireFormNamelocation =
			// CommonMethods.dynamicText("Automation_QuestionnaireForm_FM");

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkformNameFilter, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkformNameFilter,
					"Could NOT able to create and release forms:" + checkformNameFilter);

			hp.clickFormsManagerMenu();

			TestValidation.Equals(fm.error, false, "CLICKED on Forms Manager menu",
					"Could NOT click on Forms Manager menu");

			boolean verificationbtn = fm.clickVerificationBtn();
			TestValidation.IsTrue(verificationbtn, "CLEARED applied filter on validation grid's Name column",
					"Failed to clear applied filter on Form Manager grid's Name column");

			boolean selectverification = fm.selectVerification(verificationName);
			TestValidation.IsTrue(selectverification, "CLEARED applied filter on Form manager grid's Name column",
					"Failed to clear applied filter on Form Manager grid's Name column");

			boolean applyFormFilter = fm.setFilterToColumn(FM_FIELDS.FORM, checkformNameFilter);
			TestValidation.IsTrue(applyFormFilter,
					"APPLIED grid filter to Form column with value " + checkformNameFilter,
					"Failed to set grid filter to Form column");

			// boolean verifyFormName= fm.verifyValidationDetail(FM_FIELDS.FORM,
			// checkformNameFilter);
			// TestValidation.IsTrue(verifyFormName,
			// "Validation with name " + checkformNameFilter + " is DISPLAYED",
			// "Failed to verify whether validation " + checkformNameFilter + " is displayed
			// or not");

			boolean clearFilter = fm.clearAppliedFilter(FM_FIELDS.FORM);
			TestValidation.IsTrue(clearFilter, "CLEARED applied filter on Form Manager grid's Form column",
					"Failed to clear applied filter on Form Manager grid's Form column");

			boolean applyTypeFilter = fm.setFilterToColumn(FM_FIELDS.TYPE, FM_FORMTYPE.AUDIT);
			TestValidation.IsTrue(applyTypeFilter, "APPLIED grid filter to Type column with value " + FM_FORMTYPE.AUDIT,
					"Failed to set grid filter to Type column");

			boolean refreshPage = controlActions.refreshDisplayedPage();
			TestValidation.IsTrue(refreshPage, "REFRESHED Form Manager page to remove filter on grid's Type column",
					"Failed to refresh Manager page to remove filter on grid's Type column");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Forms Manager || Verification of filter of location order")
	public void TestCase_38637() { // Passed

		hp.clickFSQABrowserMenu();

		try {

			checkFormLocation1 = CommonMethods.dynamicText("Auto_FormManager_PD_chk_LOCATION");
			questionnaireFormNamelocation1 = CommonMethods.dynamicText("Automation_Questform_FM");

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormLocation1, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormLocation1,
					"Could NOT able to create and release forms:" + checkFormLocation1);

			hp.clickFormsManagerMenu();

			TestValidation.Equals(fm.error, false, "CLICKED on Forms menu", "Could NOT click on Forms menu");

			boolean togglelocON = fm.LocationFormtoggleONLinking(checkFormLocation1);
			TestValidation.IsTrue(togglelocON, "Toggle ON location is done", "Failed to toggle ON the location");

			hp.clickFSQABrowserMenu();

			boolean createAndReleaseQuestForm = fdp.createAndReleaseForm("Questionnaire",
					questionnaireFormNamelocation1, "Customers", customersCategoryValue, customersInstanceValue);
			TestValidation.IsTrue(createAndReleaseQuestForm,
					"Able to create and release form:" + questionnaireFormNamelocation1,
					"Could NOT able to create and release forms:" + questionnaireFormNamelocation1);
			hp.clickFormsManagerMenu();
			TestValidation.Equals(fm.error, false, "CLICKED on Forms menu", "Could NOT click on Forms menu");

			boolean toggleQuestFormLocON = fm.LocationFormtoggleONLinking(questionnaireFormNamelocation1);
			TestValidation.IsTrue(toggleQuestFormLocON, "Toggle ON location is done",
					"Failed to toggle ON the location");

			FormsManagerPage fm = hp.clickFormsManagerMenu();
			TestValidation.Equals(fm.error, false, "CLICKED on FormsManager menu",
					"Could NOT click on FormsManager menu");
			// String locationName = null;

			// boolean clickVerification = fm.selectAndSetLocation(locationname);

			boolean clickLocation = fm.selectAndSetLocation(locationInstanceValue);
			TestValidation.IsTrue(clickLocation, "OPENED the search location - " + locationInstanceValue,
					"Failed to open searched location - " + locationInstanceValue);

			boolean applyFormFilter = fm.setFilterToColumnLocation(FM_FIELDS.FORM, checkFormLocation1);
			TestValidation.IsTrue(applyFormFilter,
					"APPLIED grid filter to Form column with value " + checkFormLocation1,
					"Failed to set grid filter to Form column");

			boolean clearFilter = fm.clearAppliedFilterLocationTab(FM_FIELDS.FORM);
			TestValidation.IsTrue(clearFilter, "CLEARED applied filter on Form Manager grid's Form column",
					"Failed to clear applied filter on Form Manager grid's Form column");

			boolean applyTypeFilter = fm.setFilterToColumnLocation(FM_FIELDS.TYPE, FM_FORMTYPE.CHECK);
			TestValidation.IsTrue(applyTypeFilter, "APPLIED grid filter to Type column with value " + FM_FORMTYPE.CHECK,
					"Failed to set grid filter to Type column");

			boolean refreshPage = controlActions.refreshDisplayedPage();
			TestValidation.IsTrue(refreshPage, "REFRESHED Form Manager page to remove filter on grid's Type column",
					"Failed to refresh Manager page to remove filter on grid's Type column");

		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Forms Manager || Verification of sorting order")
	public void TestCase_38656() {// Passed

		try {

			hp.clickFSQABrowserMenu();

			HashMap<String, List<String>> resourceList = new HashMap<String, List<String>>();
			resourceList.put(RESOURCE_TYPES.CUSTOMERS, Arrays.asList(customersInstanceValue));

			boolean createLocation = mlp.createLocationlinkresource(locationCategoryValue, locationInstanceValue1,
					adminusername, resourceList);
			TestValidation.IsTrue(createLocation,
					"Created LocationCategory" + locationCategoryValue + "and Location " + locationCategoryValue,
					"Could NOT create LocationCategory" + locationCategoryValue + "and Location "
							+ locationCategoryValue);

			boolean createLocation2 = mlp.createLocationlinkresource(locationCategoryValue, locationInstanceValue2,
					adminusername, resourceList);
			TestValidation.IsTrue(createLocation2,
					"Created LocationCategory" + locationCategoryValue + "and Location " + locationCategoryValue,
					"Could NOT create LocationCategory" + locationCategoryValue + "and Location "
							+ locationCategoryValue);

			hp.clickFSQABrowserMenu();
			checkFormOverView = CommonMethods.dynamicText("Auto_FormManager_PD_checkform_overview");
			// questionnaireFormNamelocation =
			// CommonMethods.dynamicText("Automation_FormManager_PD_QuestionnaireForm_LOCATION");

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormOverView, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormOverView,
					"Could NOT able to create and release forms:" + checkFormOverView);

			hp.clickFormsManagerMenu();

			TestValidation.Equals(fm.error, false, "CLICKED on Forms manager menu",
					"Could NOT click on Forms manager menu");
			// boolean searchingForm = fm.searchAndFormSelection(checkFormNamefm4);
			// TestValidation.IsTrue(searchingForm,
			// "OPENED the search form - " + checkFormNamefm4,
			// "Failed to open searched form - " + checkFormNamefm4);

			boolean togglelocON = fm.LocationFormtoggleONLinking(checkFormOverView);
			TestValidation.IsTrue(togglelocON, "Toggle ON location is done", "Failed to toggle ON the location");

			boolean sortFormAsc = fm.openAndApplySettingsForColumn(FM_FIELDS.LOCATION, COLUMN_SETTING.SORTASCENDING,
					null);
			TestValidation.IsTrue(sortFormAsc,
					"For Form column, APPLIED the column setting as " + COLUMN_SETTING.SORTASCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTASCENDING + " for Form column");

			String verifySortFormAsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.LOCATION));
			TestValidation.IsTrue(verifySortFormAsc.equals(SORTING.ASC),
					"VERIFIED values of the column Form are sorted in Ascending",
					"Failed to verify that values of the column Form are sorted in Ascending");

			boolean sortFormDsc = fm.openAndApplySettingsForColumn(FM_FIELDS.LOCATION, COLUMN_SETTING.SORTDESCENDING,
					null);
			TestValidation.IsTrue(sortFormDsc,
					"For Form column, APPLIED the column setting as " + COLUMN_SETTING.SORTDESCENDING,
					"Failed to apply the column setting as " + COLUMN_SETTING.SORTDESCENDING + " for Form column");

			String verifySortFormDsc = CommonMethods
					.verifySortingForColumn(fm.getListOfElementsForFormManager(FM_FIELDS.LOCATION));
			TestValidation.IsTrue(verifySortFormDsc.equals(SORTING.DSC),
					"VERIFIED values of the column Form are sorted in Descending",
					"Failed to verify that values of the column Form are sorted in Descending");

		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Forms Manager || Verification of sorting order AT form level location tab")
	public void TestCase_38655() {

		try {

			hp.clickFSQABrowserMenu();

			hp.clickFSQABrowserMenu();
			checkFormOverViewLocFilter = CommonMethods.dynamicText("Auto_Fmg_PD_chkform_overview_Loc_Ftr");
			// questionnaireFormNamelocation =
			// CommonMethods.dynamicText("Automation_FormManager_PD_QuestionnaireForm_LOCATION");

			boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormOverViewLocFilter, "Customers",
					customersCategoryValue, customersInstanceValue);

			TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormOverViewLocFilter,
					"Could NOT able to create and release forms:" + checkFormOverViewLocFilter);

			hp.clickFormsManagerMenu();

			TestValidation.Equals(fm.error, false, "CLICKED on Forms manager menu",
					"Could NOT click on Forms manager menu");
			// boolean searchingForm = fm.searchAndFormSelection(checkFormNamefm4);
			// TestValidation.IsTrue(searchingForm,
			// "OPENED the search form - " + checkFormNamefm4,
			// "Failed to open searched form - " + checkFormNamefm4);

			boolean togglelocON = fm.LocationFormtoggleONLinking(checkFormOverViewLocFilter);
			TestValidation.IsTrue(togglelocON, "Toggle ON location is done", "Failed to toggle ON the location");

			boolean applyFormFilter = fm.setFilterToColumnOverviewFormLocation(FM_FIELDS.LOCATION,
					locationInstanceValue);
			TestValidation.IsTrue(applyFormFilter,
					"APPLIED grid filter to Location column with value " + locationInstanceValue,
					"Failed to set grid filter to Location column");

			boolean clearFilter = fm.clearAppliedFilteroOverviewLocationTab(FM_FIELDS.LOCATION);
			TestValidation.IsTrue(clearFilter, "CLEARED applied filter on Form Manager grid's Location column",
					"Failed to clear applied filter on Form Manager grid's Location column");
		} finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description = "Verification of bulk enable/disable from Option dropdown")
	public void TestCase_36145() throws Exception {
		
		try {

		hp.clickFSQABrowserMenu();
		checkFormNameOverview = CommonMethods.dynamicText("Auto_Chk_Bulk_36145");

		boolean createAndReleaseForm = fdp.createAndReleaseForm("Check", checkFormNameOverview, "Customers",
				customersCategoryValue, customersInstanceValue);

		TestValidation.IsTrue(createAndReleaseForm, "Able to create and release form:" + checkFormNameOverview,
				"Could NOT able to create and release forms:" + checkFormNameOverview);

		hp.clickFSQABrowserMenu();

		questionnaireFormNameOverview = CommonMethods.dynamicText("Auto_Quest_Bulk_36145");

		boolean createAndReleaseQuestForm = fdp.createAndReleaseForm("Questionnaire", questionnaireFormNameOverview,
				"Customers", customersCategoryValue, customersInstanceValue);
		TestValidation.IsTrue(createAndReleaseQuestForm,
				"Able to create and release form:" + questionnaireFormNameOverview,
				"Could NOT able to create and release forms:" + questionnaireFormNameOverview);

		fm = hp.clickFormsManagerMenu();

		TestValidation.Equals(fm.error, false, "CLICKED on Forms manager menu",
				"Could NOT click on Forms manager menu");

		//boolean searchCheckformn = fm.searchAndSelectOverviewForm("Bulk", true);
		boolean searchCheckform = fm.searchOverviewForm("Bulk", true);
		TestValidation.IsTrue(searchCheckform, "Opened Form" + checkFormNameOverview + questionnaireFormNameOverview,
				"Unable to open the Form" + checkFormNameOverview + questionnaireFormNameOverview);
		
		boolean clickBulkFormCheckbox = fm.clickBulkFormCheckBox();
		TestValidation.IsTrue(clickBulkFormCheckbox, "Able to Click checbox for bulk form selection",
				"Unable to Click chebox for bulk form selection");
		
		boolean disable = fm.performDisableFormOverview();
		TestValidation.IsTrue(disable, "Form " + checkFormNameOverview + questionnaireFormNameOverview +"get disabled",
				"Unable to disable the Form" +checkFormNameOverview + questionnaireFormNameOverview);

		hp.clickFSQABrowserMenu();

		boolean navigate = fbp.selectResourceDropDownandNavigate("Customers", "Forms");
		TestValidation.IsTrue(navigate, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormNameOverview);
		TestValidation.IsFalse(applyFilterAndOpenForDetails, "Verify form is not displayed in Forms Tab",
				"Form is displayed in Forms Tab");
		
		fm = hp.clickFormsManagerMenu();

		TestValidation.Equals(fm.error, false, "CLICKED on Forms manager menu",
				"Could NOT click on Forms manager menu");

		boolean searchBulkform = fm.searchOverviewForm("Bulk", true);
		TestValidation.IsTrue(searchBulkform, "Opened Form" + checkFormNameOverview + questionnaireFormNameOverview,
				"Unable to open the Form" + checkFormNameOverview + questionnaireFormNameOverview);
		
		boolean clickBulkFormCheckbox1 = fm.clickBulkFormCheckBox();
		TestValidation.IsTrue(clickBulkFormCheckbox1, "Able to Click checbox for bulk form selection",
				"Unable to Click chebox for bulk form selection");
		
		

		boolean enable = fm.performEnableFormOverview();
		TestValidation.IsTrue(enable, "Form " + checkFormNameOverview + questionnaireFormNameOverview +"get Enabled",
				"Unable to Enable the Form" +checkFormNameOverview + questionnaireFormNameOverview);


		hp.clickFSQABrowserMenu();

		boolean navigate1 = fbp.selectResourceDropDownandNavigate("Customers", "Forms");
		TestValidation.IsTrue(navigate1, "Navigated to FSQABrowser>FormsTab",
				"Failed to navigate to FSQABrowser>Forms Tab");

		boolean applyFilterAndOpenForDetails1 = fbp.applyFilterAndOpenForDetails("Form Name", checkFormNameOverview);
		TestValidation.IsTrue(applyFilterAndOpenForDetails1, "Verify form is  displayed in Forms Tab",
				"Form is not displayed in Forms Tab");
		} 
		
		finally {
			if (!controlActions.refreshDisplayedPage()) {
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

		
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}

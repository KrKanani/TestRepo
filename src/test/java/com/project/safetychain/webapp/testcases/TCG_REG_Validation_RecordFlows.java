package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.project.safetychain.api.models.support.Support.IDENTIFIER_TYPES;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_RESULT;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VALIDATION_TYPE;
import com.project.safetychain.webapp.pageobjects.AddEditValidationsPage.VldtnDetsParams;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FORMFIELDS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.HistoryDetails;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ValidationsPage;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_FIELDS;
import com.project.safetychain.webapp.pageobjects.ValidationsPage.VLDTN_STATUS;
import com.project.safetychain.webapp.pageobjects.VerificationsPage;
import com.project.safetychain.webapp.pageobjects.VerificationsPage.VerificationDetsParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_Validation_RecordFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customerCategoryValue;
	public static String customerInstanceValue;
	public static String formName;
	public static String numericFN = "Number";
	public static String identifierFN = "ID";
	public static String section = "Section";
	public static String vldtnName;
	public static String vldtnSummary;
	public static String vldtnStartDate, vldtnEndDate;
	HashMap<String,String> identifierDets;
	public static String verificationName;
	public static String verificationComment1;
	public static String verificationComment2;
	public static String rolename = "SuperAdmin";
	public static String displayName = null;
	public static String usrTimezone = null;
	public static String timezoneCode = null;
	public static String currentDate;
	public static String signOffCmmt;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		formName = CommonMethods.dynamicText("VldtnRecs");
		vldtnName = CommonMethods.dynamicText("Vldtn");
		vldtnSummary = CommonMethods.dynamicText("Summary");
		verificationName = CommonMethods.dynamicText("AutoVN");
		verificationComment1 = CommonMethods.dynamicText("AutoComment1");
		verificationComment2 = CommonMethods.dynamicText("AutoComment2");
		signOffCmmt = CommonMethods.dynamicText("Cmmt");
		
		usrTimezone = adminTimezone;
		timezoneCode = dt.getTimezone(usrTimezone);
		
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
		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = numericFN;
		
		FormFieldParams identifierField = new FormFieldParams();
		identifierField.DPTFieldType = DPT_FIELD_TYPES.IDENTIFIER;
		identifierField.Name = identifierFN;
		identifierField.identifierId = IDENTIFIER_TYPES.SELECT_ONE;
		identifierField.IdentifierOption = Arrays.asList("LINE1","LINE2");

		Section.formFieldParams = Arrays.asList(numericField, identifierField);

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
		FormFieldParams submitNumericField = new FormFieldParams();
		submitNumericField.Name = numericFN;
		submitNumericField.Value = "4";
		
		FormFieldParams submitIdentifierField = new FormFieldParams();
		submitIdentifierField.Name = identifierFN;
		submitIdentifierField.Value = "LINE1";

		// API - Form layout details
		FormParams fpSubmit = new FormParams();
		fpSubmit.FormName = formName; 
		fpSubmit.type = DPT_FORM_TYPES.AUDIT;
		fpSubmit.ResourceInstanceNm = customerInstanceValue;
		fpSubmit.LocationInstanceNm = locationInstanceValue;
		fpSubmit.formElements = Arrays.asList(submitNumericField, submitIdentifierField);

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

		identifierDets = new HashMap<String, String>();
		identifierDets.put(identifierFN, "LINE1");

		vldtnStartDate = dt.AddDaystoToddaysDate(-1, "MM/dd/yyyy HH:mm");
		vldtnEndDate = dt.AddDaystoToddaysDate(0, "MM/dd/yyyy HH:mm");
		
		VldtnDetsParams vdp = new VldtnDetsParams();
		vdp.Name = vldtnName;
		vdp.Type = VALIDATION_TYPE.NEW_PROCEDURES;
		vdp.Location = locationInstanceValue;
		vdp.Resource = customerInstanceValue;
		vdp.Forms = Arrays.asList(formName);
		vdp.StartDate = vldtnStartDate;
		vdp.EndDate = vldtnEndDate;
		vdp.Identifiers = identifierDets;
		if(!aevp.createAndAddValidation(vdp)) {
			TCGFailureMsg = "Could NOT add validation - " + vldtnName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		if(!aevp.completeValidationWithResult(VALIDATION_RESULT.FAIL, vldtnSummary)) {
			TCGFailureMsg = "Could NOT complete Validation " + vldtnName + " with status " + VALIDATION_RESULT.FAIL;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description="Records || Verify the records should not be edited by clicking on edit logo on the record. ")
	public void TestCase_33898() {
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openRecord, 
				 			  "OPENED record with form name - " + formName, 
				 			  "Failed to open record with form name - " + formName);
		
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		String classDetail = frp.EditRecordBtnDets.getAttribute("class");
		boolean editRecord = classDetail.contains("disabled");
		TestValidation.IsTrue(editRecord, 
	  			  			  "VERIFIED editing of record is not possible", 
	  			  			  "Could NOT verify editing of record");
	}
	
	@Test(description="Records || Verify verification should be allowed to the locked records.")
	public void TestCase_34005() {
		
		try {
			VerificationsPage vp = hp.clickVerificationsMenu();			  
			VerificationDetsParams vdp = new VerificationDetsParams();
			vdp.VerificationName = verificationName;
			vdp.EnableVerification = true;
			vdp.AddComments = new ArrayList<String>();
			vdp.AddComments.add(verificationComment1);
			vdp.AddComments.add(verificationComment2);
			vdp.Rolename = rolename;
			boolean createVerification = vp.createVerification(vdp);
			TestValidation.IsTrue(createVerification, 
					  			  "CREATED verification named - " + verificationName, 
					  			  "Failed to create verification named - " + verificationName);
			
			FormsManagerPage fmp = hp.clickFormsManagerMenu();
			boolean linkVerification = fmp.linkVerificationwithForm(verificationName,formName);
			TestValidation.IsTrue(linkVerification, 
								  "LINKED verification " + verificationName + " to form " + formName, 
								  "Failed to link verification " + verificationName + " to form " + formName);
	
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.CUSTOMERS,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, 
								  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
			
			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
			TestValidation.IsTrue(openRecord, 
					 			  "OPENED record with form name - " + formName, 
					 			  "Failed to open record with form name - " + formName);
			
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			boolean signOffRecord = frp.signoffRecordWithVerfication(verificationName,verificationComment1);
			TestValidation.IsTrue(signOffRecord, 
								  "SIGNED record for form - " + formName + " using verification", 
								  "Failed to sign record for form " + formName + " using verification");
			
			currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
			HashMap<String, String> histDetail = new HashMap<String, String>();
			histDetail.put(null,
					verificationName + "|" + displayName + "|" + verificationComment1 + "|" + currentDate);
			
			HistoryDetails hd = new HistoryDetails();
			hd.HeaderDetail = histDetail;
			
			boolean verifyHistory = frp.verifyHistoryPopupDetails(hd);	
			TestValidation.IsTrue(verifyHistory, 
					  			  "VERIFIED the record has been signed by Verification " + verificationName, 
					  			  "Failed to verify whether record is signed by Verification " + verificationName + " or not");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
		
	}
	
	@Test(description="Validations || Verify the list of Validations for the selected location")
	public void TestCase_34017() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 
		
		boolean verifyLocn = vp.VldtnLocnFilter.getText().contains("ALL");
		TestValidation.IsTrue(verifyLocn, 
							  "VERIFIED the if no default location is set, location is set to 'ALL'", 
							  "Failed to verify the location that is set");
		
		boolean verifyValdtnList = vp.VldtnGridNameLst.isEmpty();
		int cntBeforeFilter = vp.VldtnGridNameLst.size();
		TestValidation.IsFalse(verifyValdtnList || cntBeforeFilter<1, 
							  "A total of " + cntBeforeFilter + " validations are DISPLAYED", 
							  "Failed to verify whether validations are displayed or not");
		
		boolean setLocation = vp.selectLocationForGrid(locationInstanceValue);
		TestValidation.IsTrue(setLocation, 
							  "Location SET to value " + locationInstanceValue, 
							  "Failed to set location value to " + locationInstanceValue);
		
		boolean searchVldtnName = vp.searchVldtnWithDetails(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.IsTrue(searchVldtnName, 
							  "SEARCHED for validtion " + vldtnName, 
							  "Failed to Search for validation " + vldtnName);
		
		int cntAfterFilter = vp.VldtnGridNameLst.size();
		TestValidation.IsTrue(cntAfterFilter==1, 
							  "Only 1 validation is DISPLAYED", 
							  "Failed to verify whether validations are displayed or not");
		
		String verifyVldtnName = vp.getValidationDetails(VLDTN_FIELDS.NAME);
		TestValidation.IsTrue(verifyVldtnName.contains(vldtnName), 
				  			  "Validation with name " + verifyVldtnName + " is DISPLAYED ", 
				  			  "Failed to verify whether validation " + vldtnName + " is displayed or not");
		
		String verifyVldtnStatus = vp.getValidationDetails(VLDTN_FIELDS.STATUS);
		TestValidation.IsTrue(verifyVldtnStatus.contains(VLDTN_STATUS.COMPLETE), 
				  			  "Validation with status " + verifyVldtnStatus + " is DISPLAYED ", 
				  			  "Failed to verify whether validation's status " + VLDTN_STATUS.COMPLETE + " is displayed or not");
		
		String verifyVldtnCreated = vp.getValidationDetails(VLDTN_FIELDS.CREATED);
		String updateStartDateFormat = vldtnEndDate.replace('/', '-');
		String[] dateParts = CommonMethods.splitAndGetString(updateStartDateFormat, " ");
		TestValidation.IsTrue((verifyVldtnCreated.contains(dateParts[0]) && verifyVldtnCreated.contains(dateParts[2])), 
				  			  "Validation with created date " + verifyVldtnCreated + " is DISPLAYED ", 
				  			  "Failed to verify whether validation's created date " + dateParts[0] + " " + dateParts[2] + " is displayed or not");
		
		String verifyVldtnLastModified = vp.getValidationDetails(VLDTN_FIELDS.LAST_MODIFIED);
		TestValidation.IsTrue((verifyVldtnLastModified.contains(dateParts[0]) && verifyVldtnLastModified.contains(dateParts[2])), 
	  			  			  "Validation with last modified date " + verifyVldtnLastModified + " is DISPLAYED ", 
	  			  			  "Failed to verify whether validation's last modified date " + updateStartDateFormat
	  			  			  + " is displayed or not");
		
		String identifierValue = null;
		for(Map.Entry<String, String> entry  : identifierDets.entrySet()){ 
			identifierValue = entry.getKey() + " = " + entry.getValue();
		}  
		String verifyVldtnIdentifiers = vp.getValidationDetails(VLDTN_FIELDS.IDENTIFIERS);
		TestValidation.IsTrue(verifyVldtnIdentifiers.contains(identifierValue), 
				  			  "Validation with identifier " + verifyVldtnIdentifiers + " is DISPLAYED ", 
				  			  "Failed to verify whether validation's identifier " + identifierValue + " is displayed or not");

	}
	
	@Test(description="Validations || Verify data using the validation grid column filters", priority = -1)
	public void TestCase_34019() {
		
		ValidationsPage vp = hp.clickValidationsMenu();
		TestValidation.Equals(vp.error, 
							  false, 
							  "CLICKED on Validations menu", 
							  "Could NOT click on Validations menu"); 
		
		boolean applyNameFilter = vp.setFilterToColumn(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.IsTrue(applyNameFilter, 
							  "APPLIED grid filter to Name column with value " + vldtnName, 
							  "Failed to set grid filter to Name column");
		
		boolean verifyVldtnName1 = vp.verifyValidationDetail(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.IsTrue(verifyVldtnName1, 
				  			  "Validation with name " + vldtnName + " is DISPLAYED", 
				  			  "Failed to verify whether validation " + vldtnName + " is displayed or not"); 
		
		boolean clearFilter1 = vp.clearAppliedFilter();
		TestValidation.IsTrue(clearFilter1, 
	  			  			  "CLEARED applied filter on validation grid's Name column", 
	  			  			  "Failed to clear applied filter on validation grid's Name column"); 
		
		boolean applyStatusFilter = vp.setFilterToColumn(VLDTN_FIELDS.STATUS, VLDTN_STATUS.COMPLETE);
		TestValidation.IsTrue(applyStatusFilter, 
							  "APPLIED grid filter to Status column with value " + VLDTN_STATUS.COMPLETE, 
							  "Failed to set grid filter to Status column");
		
		boolean verifyVldtnName2 = vp.verifyValidationDetail(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.IsTrue(verifyVldtnName2, 
				  			  "Validation with name " + vldtnName + " is DISPLAYED after Status filter is applied", 
				  			  "Failed to verify whether validation " + vldtnName + " is displayed or not after Status filter is applied"); 
		
		boolean refreshPage = controlActions.refreshDisplayedPage();
		TestValidation.IsTrue(refreshPage, 
	  			  			  "REFRESHED validation page to remove filter on grid's Status column", 
	  			  			  "Failed to refresh validation page to remove filter on grid's Status column"); 
		
		String[] date = CommonMethods.splitAndGetString(vldtnEndDate, " ");
		boolean applyCreatedFilter = vp.setFilterToColumn(VLDTN_FIELDS.CREATED, date[0]);
		TestValidation.IsTrue(applyCreatedFilter, 
							  "APPLIED grid filter to Created column with value " + date[0], 
							  "Failed to set grid filter to Created column");
		
		boolean verifyVldtnName3 = vp.verifyValidationDetail(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.IsTrue(verifyVldtnName3, 
				  			  "Validation with name " + vldtnName + " is DISPLAYED after Created filter is applied", 
				  			  "Failed to verify whether validation " + vldtnName + " is displayed or not after Created filter is applied"); 
		
		boolean clearFilter3 = vp.clearAppliedFilter();
		TestValidation.IsTrue(clearFilter3, 
	  			  			  "CLEARED applied filter on validation grid's Created column", 
	  			  			  "Failed to clear applied filter on validation grid's Created column"); 
		
		boolean applyLastModifiedFilter = vp.setFilterToColumn(VLDTN_FIELDS.LAST_MODIFIED, date[0]);
		TestValidation.IsTrue(applyLastModifiedFilter, 
							  "APPLIED grid filter to Last Modified column with value " + date[0], 
							  "Failed to set grid filter to Last Modified column");
		
		boolean verifyVldtnName4 = vp.verifyValidationDetail(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.IsTrue(verifyVldtnName4, 
				  			  "Validation with name " + vldtnName + " is DISPLAYED after Last Modified filter is applied", 
				  			  "Failed to verify whether validation " + vldtnName + " is displayed or not after Last Modified filter is applied"); 
		
		boolean clearFilter4 = vp.clearAppliedFilter();
		TestValidation.IsTrue(clearFilter4, 
	  			  			  "CLEARED applied filter on validation grid's Last Modified column", 
	  			  			  "Failed to clear applied filter on validation grid's Last Modified column"); 
		
		String identifierValue = null;
		for(Map.Entry<String, String> entry  : identifierDets.entrySet()){ 
			identifierValue = entry.getValue();
		}  

		boolean applyIdentifiersFilter = vp.setFilterToColumn(VLDTN_FIELDS.IDENTIFIERS, identifierValue);
		TestValidation.IsTrue(applyIdentifiersFilter, 
							  "APPLIED grid filter to Identifiers column with value " + identifierValue, 
							  "Failed to set grid filter to Identifiers column");
		
		boolean verifyVldtnName5 = vp.verifyValidationDetail(VLDTN_FIELDS.NAME, vldtnName);
		TestValidation.IsTrue(verifyVldtnName5, 
				  			  "Validation with name " + vldtnName + " is DISPLAYED after Identifiers filter is applied", 
				  			  "Failed to verify whether validation " + vldtnName + " is displayed or not after Identifiers filter is applied"); 
		
	}
	
	@Test(description="Validation || Verify on clicking Complete button the edit validation view should be "
			+ "visible in read only mode.")
	public void TestCase_33894() {
			
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

		boolean setName = aevp.setValidationName("Updated "+ vldtnName);
		TestValidation.IsFalse(setName, 
							   "The Validation name is in READ ONLY mode", 
							   "Failed to verify whether Validation name is or is not in Read Only mode");

		boolean setType = aevp.setValidationType(VALIDATION_TYPE.NEW_PRODUCT);
		TestValidation.IsFalse(setType, 
							   "The Validation type is in READ ONLY mode", 
							   "Failed to verify whether Validation type is or is not in Read Only mode");

		boolean setLocation = aevp.setValidationLocation(locationInstanceValue);
		TestValidation.IsFalse(setLocation, 
							   "The Validation location is in READ ONLY mode", 
							   "Failed to verify whether Validation location is or is not in Read Only mode");

		boolean setIdentifiers = aevp.setValidationIdentifiers(identifierDets);
		TestValidation.IsFalse(setIdentifiers, 
							   "The Validation identifiers is in READ ONLY mode", 
							   "Failed to verify whether Validation identifiers is or is not in Read Only mode");
			
	}
	
	@Test(description="Validation || Verify the records associated with the Validation "
			+ "(via Forms, Filters and Dates) are “locked” after completion of validation.")
	public void TestCase_33895() {
		
		FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");
		
		boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openRecord, 
				 			  "OPENED record with form name - " + formName, 
				 			  "Failed to open record with form name - " + formName);
		
		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
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

		String vldtnMsg = "Record cannot be edited";
		boolean verifyLock1 = frp.verifyRecordsDetails(FORMFIELDS.VALIDATIONLOCK, vldtnMsg);
		TestValidation.IsTrue(verifyLock1, 
	  			  			  "VERIFIED record is locked", 
	  			  			  "Could NOT verify record is locked");
	}

	@Test(description="Validation || Verify after the complete process is completed the user should be able"
			+ " to see two bottom options in the bottom of the screen - Copy and Re-validate")
	public void TestCase_33896() {
		
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
		
		boolean verifyRevldtnBtnDisplayed = controlActions.isElementDisplayed(aevp.RevalidateVldtnBtn);
		TestValidation.IsTrue(verifyRevldtnBtnDisplayed, 
							  "VERIFIED Revalidation button is displayed", 
							  "Failed to verify Revalidation button is displayed");
		
		boolean verifyCopyBtnDisplayed = controlActions.isElementDisplayed(aevp.CopyVldtnBtn);
		TestValidation.IsTrue(verifyCopyBtnDisplayed, 
							  "VERIFIED Copy button is displayed", 
							  "Failed to verify Copy button is displayed");

	}
	
	@Test(description="Validation || Verify that on clicking \"cancel\" button on the pop up will close "
			+ "the modal and return to the “Complete Validation” view.")
	public void TestCase_33882() {
		try {
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

			boolean revalidate = aevp.clickRevalidateValidation();
			TestValidation.IsTrue(revalidate, 
								  "CLICKED on Revalidate button for complete validation", 
								  "Failed to click on Revalidate button for complete validation");
			
			String popupMsg = "Revalidate will delete this validation and release associated records";
			boolean verifyMsg = aevp.RevldtnPopupMsg.getText().equalsIgnoreCase(popupMsg); 
			TestValidation.IsTrue(verifyMsg, 
								  "VERIFIED the message displayed on Revalidation popup", 
								  "Failed to verify the message displayed on Revalidation popup");

			boolean verifyRevldtnPopupBtnDisplayed = controlActions.isElementDisplayed(aevp.RevalidateVldtnPopupBtn);
			TestValidation.IsTrue(verifyRevldtnPopupBtnDisplayed, 
								  "VERIFIED Revalidation button is displayed on popup", 
								  "Failed to verify Revalidation button is displayed on popup");

			boolean cancelClicked = aevp.clickCancelRevalidatnPopup();
			TestValidation.IsTrue(cancelClicked, 
								  "CLICKED on Cancel button on Revalidation popup", 
								  "Failed to click on Cancel button on Revalidation popup");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could refresh Validations page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}

	}

	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}

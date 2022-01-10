package com.project.safetychain.webapp.testcases;

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
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.RecordFormDetails;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.FILTERTYPES;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.RECORD_ANALYTIC_PANELS;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.RECORD_SUMMARY_DETS;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;


public class TCG_REG_Record_RecordSignoffFlows extends TestBase{
	
	ControlActions controlActions;
	DateTime dt = new DateTime();

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

		formName = CommonMethods.dynamicText("RSignoff");
		locationCategoryValue = locationName;	
		locationInstanceValue = locationName;	
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		signOffCmmt = CommonMethods.dynamicText("Cmmt1");
		
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
			resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.CUSTOMERS);

			// ------------------------------------------------------------------------------------------------
			// API - Form Creation - Check
			// API - Add fields to form
			FormFieldParams numericField = new FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = "Numeric";

			// API - Form details
			// API - Generate unique ID for form to be created
			String formId = apiUtils.getUUID();

			// API - Form layout details
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = formName;
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = customersCategoryValue;
			fp.ResourceInstanceNm = customersInstanceValue;
			fp.formElements = Arrays.asList(numericField);
			fp.CustomerResources = resourceCatMap;

			formCreationWrapper.API_Wrapper_Forms(fp);

			// ------------------------------------------------------------------------------------------------
			// API - Submit form

			// API - Add values to fields in form
			FormFieldParams submitNumericField = new FormFieldParams();
			submitNumericField.Name = "Numeric";
			submitNumericField.Value = "20";

			// API - Form layout details
			FormParams fpSubmit = new FormParams();
			fpSubmit.FormName = formName;
			fpSubmit.ResourceInstanceNm = customersInstanceValue;
			fpSubmit.LocationInstanceNm = locationInstanceValue;
			fpSubmit.formElements = Arrays.asList(submitNumericField);

			formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmit);

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
		}
		else if(executionMode.equalsIgnoreCase(ExecutionMode.UI)) {
			// ------------------------------------------------------------------------------------------------
			// Only WEB Application code starts here

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

			hp.clickFSQABrowserMenu();
			//Category creation
			HashMap<String,String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);
			ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
			if(!rdp.createCategory(categories)) {
				TCGFailureMsg = "Could NOT create Resource categories";
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
		else {
			TCGFailureMsg = "Improper Execution Mode is set - " + executionMode;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description = "Record Signoff - Verify The “Record Summary” panel shows a summary of the "
			+ "selected record set")
	public void TestCase_32231() {
		
		HomePage hp = new HomePage(driver);		
		hp.clickFSQABrowserMenu();
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.Equals(false,
				  			  rsp.error, 
				  			  "NAVIGATED to Record Signoff page", 
				  		 	  "Failed to navigate to Record Signoff page");
		
		boolean closeMsgPopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closeMsgPopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean verifyResource = rsp.verifyRecordSummaryResourceDets(RECORD_SUMMARY_DETS.CUSTOMERS, customersInstanceValue);
		TestValidation.IsTrue(verifyResource, 
							  "VERIFIED resource - customers - " + customersInstanceValue + " in Resource Summary", 
				  			  "Failed to verify resource - customers - " + customersInstanceValue + " in Resource Summary");

		boolean verifyLocation = rsp.verifyRecordSummaryResourceDets(RECORD_SUMMARY_DETS.LOCATION, locationInstanceValue);
		TestValidation.IsTrue(verifyLocation, 
							  "VERIFIED location - " + locationInstanceValue + " in Resource Summary", 
				  			  "Failed to verify location - " + locationInstanceValue + " in Resource Summary");
		
		DateTime dt = new DateTime();
		String currentDate = dt.getTodayDate("MM/dd/YYYY", dt.getTimezone(usrTimezone));
		boolean verifyDate = rsp.verifyRecordSummaryResourceDets(RECORD_SUMMARY_DETS.DATE_RANGE, currentDate);
		TestValidation.IsTrue(verifyDate, 
							  "VERIFIED date for record in Resource Summary", 
				  			  "Failed to verify date for record in Resource Summary");
		
		boolean verifyFormNm = rsp.verifyRecordSummaryResourceDets(RECORD_SUMMARY_DETS.FORMS, formName);
		TestValidation.IsTrue(verifyFormNm, 
							  "VERIFIED form name - " + formName + " in Resource Summary", 
				  			  "Failed to verify form name - " + formName + " in Resource Summary");
		
		boolean verifyName = rsp.verifyRecordSummaryResourceDets(RECORD_SUMMARY_DETS.COMPLETEDBY, displayName);
		TestValidation.IsTrue(verifyName, 
							  "VERIFIED Completed By has - " + displayName + " in Resource Summary", 
				  			  "Failed to verify Completed By has - " + displayName + " in Resource Summary");
		
	}
	
	@Test(description = "Multi-Record Signoff - Record Signoff - Selected filters affect the data set shown "
			+ "when drilling down on any analytic(9050)")
	public void TestCase_33197() {
		
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
		
		boolean applyFilter1 = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName);
		TestValidation.IsTrue(applyFilter1, 
							  "APPLIED filter for forms for form - " + formName, 
				  			  "Failed to apply filter for forms for form - " + formName);
		
		String signedRecCount = rsp.SignedRecsCount.getText().trim();
		TestValidation.IsTrue((signedRecCount.equals("0")), 
							  "VERIFIED Signed records are " + signedRecCount, 
				  			  "Failed to verify Sign records count");
		
		FSQABrowserRecordsPage drillSignedRecs = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.SIGNED);
		TestValidation.Equals(true,
							  drillSignedRecs.error, 
							  "As sign records = "+ signedRecCount +", we CANNOT drill down", 
							  "Failed to verify whether we can drill down for Signed panel or not");
		
		String unsignedRecCount = rsp.UnsignedRecsCount.getText().trim();
		FSQABrowserRecordsPage drillUnsignedRecs = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.UNSIGNED);
		TestValidation.Equals(false,
							  drillUnsignedRecs.error, 
							  "As unsigned records = "+ unsignedRecCount +", we CAN drill down", 
							  "Failed to verify whether we can drill down for Unsigned panel or not");
		
		boolean editBtn = drillUnsignedRecs.EditRecordBtn.isDisplayed();
		boolean rviewerTitle = drillUnsignedRecs.RViewerRecordsTitle.isDisplayed();
		TestValidation.IsTrue((editBtn && !rviewerTitle), 
							  "VERIFIED we have landed on full page view of the record", 
				  			  "Failed to verify we landed on full page view of the record");
	}
	
	@Test(description = "Multi-Record Signoff - Record Signoff - Users are shown all records when "
			+ "drilling down on the Totals analytic(9052)")
	public void TestCase_33198() {
		
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
		
		String totalRecCountRSP = rsp.TotalRecordCount.getText().trim();
		FSQABrowserRecordsPage frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.TOTALS);
		TestValidation.Equals(false,
							  frp.error, 
							  "As total records = "+ totalRecCountRSP +", we CAN drill down", 
							  "Failed to verify whether we can drill down for Totals panel or not");
		
		
		boolean compareCountInRV = frp.RViewerRecordsTitle.getText().contains(totalRecCountRSP);
		TestValidation.IsTrue(compareCountInRV, 
							  totalRecCountRSP + " number of RECORDS are displayed in Record Viewer", 
							  "Failed to verify records displayed in Record Viewer");
		
		String selectedCardClass = frp.RViewerRecordCards.get(0).getAttribute("class");
		boolean verifySelectn = selectedCardClass.toLowerCase().contains("selected");
		TestValidation.IsTrue(verifySelectn, 
				  			  "By default, the first card in the list is SELECTED", 
				  			  "Could NOT verify that by default, the first card in the list should be selected"); 
		
		int headerDates = frp.compareCardLayoutHeaderDates();
		boolean verifyHeaderDates = (headerDates==2);
		TestValidation.IsTrue(verifyHeaderDates, 
	  			  			  "VERIFIED records ordered by Completed On and in ascending order", 
	  			  			  "Could NOT verify records ordered by Completed On"); 
		
		boolean verifyRecordTimeAsc = frp.compareCardLayoutCompletedOnDates(2);
		TestValidation.IsTrue(verifyRecordTimeAsc, 
	  			  			  "VERIFIED records ordered by Time in ascending order", 
	  			  			  "Could NOT verify records ordered by Time in ascending order"); 
		
		RecordFormDetails rfd = new RecordFormDetails();
		rfd.FormName = formName;
		rfd.Resource = customersInstanceValue;
		rfd.Location = locationInstanceValue;
		rfd.CompletedBy = displayName;
		boolean verifyFormDetails = frp.searchAndVerifyRecordsDetailsInView(rfd);
		TestValidation.IsTrue(verifyFormDetails, 
				  			  "VERIFIED record details for form - " + rfd.FormName, 
				  			  "Failed to verify record details for form - " + rfd.FormName);
	}
	
	@Test(description = "Multi-Record Signoff - Record Signoff - Users are shown only unsigned records "
			+ "when drilling down on the Unsigned analytic(9055)")
	public void TestCase_33207() {
		
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
		
		String unsignedRecCountRSP = rsp.UnsignedRecsCount.getText().trim();
		FSQABrowserRecordsPage frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.UNSIGNED);
		TestValidation.Equals(false,
							  frp.error, 
							  "As unsigned records = "+ unsignedRecCountRSP +", we CAN drill down", 
							  "Failed to verify whether we can drill down for Unsigned panel or not");
		
		boolean compareCountInRV = frp.RViewerRecordsTitle.getText().contains(unsignedRecCountRSP);
		TestValidation.IsTrue(compareCountInRV, 
							  unsignedRecCountRSP + " number of RECORDS are displayed in Record Viewer", 
							  "Failed to verify records displayed in Record Viewer");
		
		String selectedCardClass = frp.RViewerRecordCards.get(0).getAttribute("class");
		boolean verifySelectn = selectedCardClass.toLowerCase().contains("selected");
		TestValidation.IsTrue(verifySelectn, 
				  			  "By default, the first card in the list is SELECTED", 
				  			  "Could NOT verify that by default, the first card in the list should be selected"); 
		
		int headerDates = frp.compareCardLayoutHeaderDates();
		boolean verifyHeaderDates = (headerDates==2);
		TestValidation.IsTrue(verifyHeaderDates, 
	  			  			  "VERIFIED records ordered by Completed On and in ascending order", 
	  			  			  "Could NOT verify records ordered by Completed On"); 
		
		boolean verifyRecordTimeAsc = frp.compareCardLayoutCompletedOnDates(2);
		TestValidation.IsTrue(verifyRecordTimeAsc, 
	  			  			  "VERIFIED records ordered by Time in ascending order", 
	  			  			  "Could NOT verify records ordered by Time in ascending order"); 
		
		RecordFormDetails rfd = new RecordFormDetails();
		rfd.FormName = formName;
		rfd.Resource = customersInstanceValue;
		rfd.Location = locationInstanceValue;
		rfd.CompletedBy = displayName;
		boolean verifyFormDetails = frp.searchAndVerifyRecordsDetailsInView(rfd);
		TestValidation.IsTrue(verifyFormDetails, 
				  			  "VERIFIED record details for form - " + rfd.FormName, 
				  			  "Failed to verify record details for form - " + rfd.FormName);
	}
	
	@Test(description = "Multi-Record Signoff - Record Signoff - Users are shown only signed records "
			+ "when drilling down on the Signed analytic(9056)")
	public void TestCase_33209() {
		
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
		
		boolean clearFilter = fbp.openAndClearAppliedFilterForColumn(COLUMNHEADER.FORMNAME);
		TestValidation.IsTrue(clearFilter, 
							  "CLEARED applied filter for form column of Records tab", 
							  "Failed to clear applied filter for form column of Records tab");
		
		boolean selectAllRecord = fbp.clickSelectAllChk();
		TestValidation.IsTrue(selectAllRecord, 
				 			  "SELECTED all records in grid", 
				 			  "Failed to select all records in grid");
		
		RecordSignoffPage rsp = fbp.clickSignRecordsBtn();
		TestValidation.Equals(false,
							  rsp.error, 
							  "NAVIGATED to Record Signoff page", 
							  "Failed to navigate to Record Signoff page");

		String signedRecCountRSP = rsp.SignedRecsCount.getText().trim();
		frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.SIGNED);
		TestValidation.Equals(false,
							  frp.error, 
							  "As signed records = "+ signedRecCountRSP +", we CAN drill down", 
							  "Failed to verify whether we can drill down for Signed panel or not");
		
		boolean compareCountInRV = frp.RViewerRecordsTitle.getText().contains(signedRecCountRSP);
		TestValidation.IsTrue(compareCountInRV, 
				     	 	  signedRecCountRSP + " number of RECORDS are displayed in Record Viewer", 
							  "Failed to verify records displayed in Record Viewer");
		
		String selectedCardClass = frp.RViewerRecordCards.get(0).getAttribute("class");
		boolean verifySelectn = selectedCardClass.toLowerCase().contains("selected");
		TestValidation.IsTrue(verifySelectn, 
				  			  "By default, the first card in the list is SELECTED", 
				  			  "Could NOT verify that by default, the first card in the list should be selected"); 
		
		int headerDates = frp.compareCardLayoutHeaderDates();
		boolean verifyHeaderDates = (headerDates==2);
		if(verifyHeaderDates) {
			TestValidation.IsTrue(verifyHeaderDates, 
					  			  "VERIFIED records ordered by Completed On and in ascending order", 
					  			  "Could NOT verify records ordered by Completed On"); 
		}
		else if (headerDates==1) {
			TestValidation.IsFalse(verifyHeaderDates, 
					  			  "Not enough records headers to verify ordered by Completed On is in ascending order", 
					  			  "Could NOT verify records ordered by Completed On"); 
		}
		
		
		boolean verifyRecordTimeAsc = frp.compareCardLayoutCompletedOnDates(2);
		TestValidation.IsTrue(verifyRecordTimeAsc, 
	  			  			  "VERIFIED records ordered by Time in ascending order", 
	  			  			  "Could NOT verify records ordered by Time in ascending order"); 
		
		RecordFormDetails rfd = new RecordFormDetails();
		rfd.FormName = formName;
		rfd.Resource = customersInstanceValue;
		rfd.Location = locationInstanceValue;
		rfd.CompletedBy = displayName;
		boolean verifyFormDetails = frp.searchAndVerifyRecordsDetailsInView(rfd);
		TestValidation.IsTrue(verifyFormDetails, 
				  			  "VERIFIED record details for form - " + rfd.FormName, 
				  			  "Failed to verify record details for form - " + rfd.FormName);
	}
	
	@Test(description = "Record Signoff || Show Corrections || Verify when applied filters not comply to any corrections records,"
			+ " none corrections are displayed")
	public void TestCase_37474() {
		
		HomePage hp = new HomePage(driver);
		hp.clickFSQABrowserMenu();
		RecordSignoffPage rsp = hp.clickRecordSignoffMenu();
		TestValidation.IsFalse(rsp.error, 
							  "NAVIGATED to Record Signoff page", 
				  			  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean applyFilter = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName);
		TestValidation.IsTrue(applyFilter, 
							  "APPLIED filter for forms for form - " + formName, 
				  			  "Failed to apply filter for forms for form - " + formName);
		
		String totalCount = rsp.TotalRecordCount.getText().trim();
		TestValidation.IsTrue((totalCount.equals("1")), 
							  "VERIFIED Total Count of records are - " + totalCount, 
				  			  "Failed to verify Total Count of records");
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		boolean unresolveCount = rsp.UnresolvdRecsCount.getText().trim().equals("0");
		TestValidation.IsTrue(unresolveCount, 
				  			  "VERIFIED count of unresolved records as 0", 
			  				  "Failed since count of unresolved records is found as " + rsp.UnresolvdRecsCount.getText().trim());
		
		boolean resolveCount = rsp.ResolvdRecsCount.getText().trim().equals("0");
		TestValidation.IsTrue(resolveCount, 
				  			  "VERIFIED count of resolved records as 0", 
			  				  "Failed since count of resolved records is found as " + rsp.ResolvdRecsCount.getText().trim());
		
		boolean clickReslvTab = rsp.clickResolvedTab();
		TestValidation.IsTrue(clickReslvTab,
							  "CLICKED on Corrections > Resolved tab", 
							  "Failed to click on Corrections > Resolved tab");
		
		String resTitle = "No Resolved Corrections";
		String resMsg = "There are no Resolved corrections related to any of the records in this set";
		boolean verifyResMsg = (rsp.CorrectnListPanel.getText().contains(resTitle)) && 
				(rsp.CorrectnListPanel.getText().contains(resMsg));
		TestValidation.IsTrue(verifyResMsg,
							  "VERIFIED message displayed for Resolved Tab for 0 records", 
							  "Failed to verify message displayed for Resolved Tab");
		
		boolean clickUnreslvTab = rsp.clickUnresolvedTab();
		TestValidation.IsTrue(clickUnreslvTab,
							  "CLICKED on Corrections > Unresolved tab", 
							  "Failed to click on Corrections > Unresolved tab");
		
		String unresTitle = "No Unresolved Corrections";
		String unresMsg = "There are no Unresolved corrections related to any of the records in this set";
		boolean verifyUnresMsg = (rsp.CorrectnListPanel.getText().contains(unresTitle)) && 
				(rsp.CorrectnListPanel.getText().contains(unresMsg));
		TestValidation.IsTrue(verifyUnresMsg,
							  "VERIFIED message displayed for Unresolved Tab for 0 records", 
							  "Failed to verify message displayed for Unresolved Tab");
		
	}
	
	@Test(description = "Multi-Record Signoff - Record Signoff - Users are shown only compliant records "
			+ "when drilling down on the Compliant analytic(9053)")
	public void TestCase_33204() {
		
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

		String compliantRecCountRSP = rsp.CompliantRecsCount.getText().trim();
		FSQABrowserRecordsPage frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.COMPLIANT);
		TestValidation.Equals(false,
							  frp.error, 
							  "As Compliant records = "+ compliantRecCountRSP +", we CAN drill down", 
							  "Failed to verify whether we can drill down for Compliant panel or not");
		
		boolean compareCountInRV = frp.RViewerRecordsTitle.getText().contains(compliantRecCountRSP);
		TestValidation.IsTrue(compareCountInRV, 
							  compliantRecCountRSP + " number of RECORDS are displayed in Record Viewer", 
							  "Failed to verify records displayed in Record Viewer");
		
		String selectedCardClass = frp.RViewerRecordCards.get(0).getAttribute("class");
		boolean verifySelectn = selectedCardClass.toLowerCase().contains("selected");
		TestValidation.IsTrue(verifySelectn, 
				  			  "By default, the first card in the list is SELECTED", 
				  			  "Could NOT verify that by default, the first card in the list should be selected"); 
		
		int headerDates = frp.compareCardLayoutHeaderDates();
		boolean verifyHeaderDates = (headerDates==2);
		if(verifyHeaderDates) {
			TestValidation.IsTrue(verifyHeaderDates, 
					  			  "VERIFIED records ordered by Completed On and in ascending order", 
					  			  "Could NOT verify records ordered by Completed On"); 
		}
		else if (headerDates==1) {
			TestValidation.IsFalse(verifyHeaderDates, 
					  			  "Not enough records headers to verify ordered by Completed On is in ascending order", 
					  			  "Could NOT verify records ordered by Completed On"); 
		}
		
		boolean verifyRecordTimeAsc = frp.compareCardLayoutCompletedOnDates(2);
		TestValidation.IsTrue(verifyRecordTimeAsc, 
	  			  			  "VERIFIED records ordered by Time in ascending order", 
	  			  			  "Could NOT verify records ordered by Time in ascending order"); 
		
		RecordFormDetails rfd = new RecordFormDetails();
		rfd.FormName = formName;
		rfd.Resource = customersInstanceValue;
		rfd.Location = locationInstanceValue;
		rfd.CompletedBy = displayName;
		boolean verifyFormDetails = frp.searchAndVerifyRecordsDetailsInView(rfd);
		TestValidation.IsTrue(verifyFormDetails, 
				  			  "VERIFIED record details for form - " + rfd.FormName, 
				  			  "Failed to verify record details for form - " + rfd.FormName);
		
	}
		
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


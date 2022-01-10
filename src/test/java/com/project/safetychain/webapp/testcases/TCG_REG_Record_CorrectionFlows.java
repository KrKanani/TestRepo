package com.project.safetychain.webapp.testcases;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FORMFIELDS;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.RecordFormDetails;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.FILTERTYPES;
import com.project.safetychain.webapp.pageobjects.RecordSignoffPage.RECORD_ANALYTIC_PANELS;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;


public class TCG_REG_Record_CorrectionFlows extends TestBase{
	
	ControlActions controlActions;
	LoginPage lp;
	HomePage hp;
	RecordSignoffPage rsp;
	FSQABrowserPage fbp;
	public static String formName;
	public static String displayName = null;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String numFieldName = "Numeric";
	public static String documentName = "upload.png";
	public static String fieldMin = "1", fieldTar = "2", fieldMax = "3", fieldUOM = "KG";
	public static String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("RSignoff");
		locationCategoryValue = locationName;	
		locationInstanceValue = locationName;	
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");

		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {

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
			// API - Form Creation - Check
			// API - Add fields to form
			com.project.safetychain.api.models.support.Support.FormFieldParams numericField 
				= new com.project.safetychain.api.models.support.Support.FormFieldParams();
			numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
			numericField.Name = "Numeric";
			numericField.AllowCorrection = "true";
			numericField.ShowIsResolved = "true";
			numericField.AllowAttachments = "true";
			
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
			// API - Form Compliance - Check - Numeric
			String resource = RESOURCE_TYPES.CUSTOMERS + " > " + customersCategoryValue 
									+ " > " + customersInstanceValue;

			HashMap<String, Compliance> complianceValuesMap = new HashMap<String, Compliance>();
			Compliance numCompliance1 = new Compliance();
			numCompliance1.Min = fieldMin;
			numCompliance1.Max = fieldMax;
			numCompliance1.Target = fieldTar;
			numCompliance1.Name = numFieldName;
			numCompliance1.fieldType = DPT_FIELD_TYPES.NUMERIC;
			
			complianceValuesMap.put(resource, numCompliance1);
			complianceValuesMap.put("Default", numCompliance1);

			FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
			ffpc.fieldNames = Arrays.asList(numFieldName);
			ffpc.complianceList = complianceValuesMap;
			formCreationWrapper.API_Wrapper_Forms_Compliance(fp, ffpc);

			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here

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
			
			hp.clickFSQABrowserMenu();
			if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS)) {
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

			if(!fbp.openForDetails()) {
				TCGFailureMsg = "Could NOT open form " + formName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);			
			}

			HashMap<String, List<String>> fillData2 = new HashMap<String, List<String>>();
			fillData2.put(numFieldName,Arrays.asList("6"));

			//Field Property objects
			FieldProperties fp2 = new FieldProperties();
			fp2.fieldName = numFieldName;
			fp2.allowCorrectionsCheck = true;
			fp2.allowCorrectionsValue = "Test Value 2";
			fp2.isResolvedCheck = true;
			fp2.isResolvedStatus = "No";
			fp2.allowAttachmenstCheck = true;
			fp2.uploadFilePath = filePath;
			fp2.complianceStatusCheck = true;
			fp2.complianceStatus = "Non-Compliant";

			//Form object
			FormDetails fd2 = new FormDetails();
			//Form level attachment
			fd2.fieldDetails = fillData2;
			//Field property assignment  
			fd2.fieldProperties = Arrays.asList(fp2);
			if(!fo.submitData(fd2)) {
				TCGFailureMsg = "Could NOT enter data and submit form for second time " + formName;
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

			//Category creation
			HashMap<String,String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.CUSTOMERS, customersCategoryValue);
			ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
			if(!rdp.createCategory(categories)) {
				TCGFailureMsg = "Could NOT create Resource categories";
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
			if(!fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS)) {
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

			if(!fbp.openForDetails()) {
				TCGFailureMsg = "Could NOT open form " + formName;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);			
			}

			HashMap<String, List<String>> fillData2 = new HashMap<String, List<String>>();
			fillData2.put(numFieldName,Arrays.asList("6"));

			//Field Property objects
			FieldProperties fp2 = new FieldProperties();
			fp2.fieldName = numFieldName;
			fp2.allowCorrectionsCheck = true;
			fp2.allowCorrectionsValue = "Test Value 2";
			fp2.isResolvedCheck = true;
			fp2.isResolvedStatus = "No";
			fp2.allowAttachmenstCheck = true;
			fp2.uploadFilePath = filePath;
			fp2.complianceStatusCheck = true;
			fp2.complianceStatus = "Non-Compliant";

			//Form object
			FormDetails fd2 = new FormDetails();
			//Form level attachment
			fd2.fieldDetails = fillData2;
			//Field property assignment  
			fd2.fieldProperties = Arrays.asList(fp2);
			if(!fo.submitData(fd2)) {
				TCGFailureMsg = "Could NOT enter data and submit form for second time " + formName;
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

	@Test(description = "Record Signoff || Show Corrections || Verify records are displayed "
			+ "when Resolved/Unresolved correction records are available")
	public void TestCase_37064() {
		
		hp.clickFSQABrowserMenu();
		rsp = hp.clickRecordSignoffMenu();
		TestValidation.IsFalse(rsp.error, 
							  "NAVIGATED to Record Signoff page", 
				  			  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		String unresolveCount = rsp.UnresolvdRecsCount.getText().trim();
		TestValidation.IsTrue((unresolveCount!="0" || unresolveCount!="" || unresolveCount!=null), 
				  			  "VERIFIED count of unresolved records as " + unresolveCount, 
			  				  "Failed since count of unresolved records is found as " + unresolveCount);
		
		String resolveCount = rsp.ResolvdRecsCount.getText().trim();
		TestValidation.IsTrue((resolveCount!="0" || resolveCount!="" || resolveCount!=null), 
				  			  "VERIFIED count of resolved records as " + resolveCount, 
			  				  "Failed since count of resolved records is found as " + resolveCount);
		
	}
	
	@Test(description = "Record Signoff || Show Corrections || Verify when navigated from Record Signoff > Sign Records tab "
			+ "to Previous page, displays 'Show Correction' button")
	public void TestCase_37065() {
		
		hp.clickFSQABrowserMenu();
		rsp = hp.clickRecordSignoffMenu();
		TestValidation.IsFalse(rsp.error, 
							  "NAVIGATED to Record Signoff page", 
				  			  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		String unresolveCount = rsp.UnresolvdRecsCount.getText().trim();
		TestValidation.IsTrue((unresolveCount!="0" || unresolveCount!="" || unresolveCount!=null), 
				  			  "VERIFIED count of unresolved records as " + unresolveCount, 
			  				  "Failed since count of unresolved records is found as " + unresolveCount);
		
		String resolveCount = rsp.ResolvdRecsCount.getText().trim();
		TestValidation.IsTrue((resolveCount!="0" || resolveCount!="" || resolveCount!=null), 
				  			  "VERIFIED count of resolved records as " + resolveCount, 
			  				  "Failed since count of resolved records is found as " + resolveCount);
		
		boolean nextPage = rsp.clickNextBtn();
		TestValidation.IsTrue(nextPage, 
				  			  "CLICKED on Next button and landed on Sign Records page", 
			  				  "Failed to click on Next button and land on Sign Records page");
		
		boolean navigateBack = rsp.clickReviewRecsLink();
		TestValidation.IsTrue(navigateBack, 
				  			  "NAVIGATED back to Record Signoff > Review Records", 
							  "Failed to navigate back to Record Signoff > Review Records");
		
		boolean verifyShowCorrBtn = controlActions.isElementDisplayed(rsp.ShowCorrectnsBtn);
		TestValidation.IsTrue(verifyShowCorrBtn, 
							  "VERIFIED Show Corrections button is displayed", 
				  			  "Failed to verify Show Corrections button is displayed");
		
	}
	
	@Test(description = "Record Signoff || Show Corrections || Verify when navigated from Record Viewer "
			+ "to Record Signoff page, displays 'Show Correction' button")
	public void TestCase_37066() {
		
		hp.clickFSQABrowserMenu();
		rsp = hp.clickRecordSignoffMenu();
		TestValidation.IsFalse(rsp.error, 
							  "NAVIGATED to Record Signoff page", 
				  			  "Failed to navigate to Record Signoff page");
		
		boolean closePopup = rsp.closeMsgPopup();
		TestValidation.IsTrue(closePopup, 
				  			  "CLOSED the 24 hours message popup", 
			  				  "Failed to close the 24 hours message popup");
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		String unresolveCount = rsp.UnresolvdRecsCount.getText().trim();
		TestValidation.IsTrue((unresolveCount!="0" || unresolveCount!="" || unresolveCount!=null), 
				  			  "VERIFIED count of unresolved records as " + unresolveCount, 
			  				  "Failed since count of unresolved records is found as " + unresolveCount);
		
		String resolveCount = rsp.ResolvdRecsCount.getText().trim();
		TestValidation.IsTrue((resolveCount!="0" || resolveCount!="" || resolveCount!=null), 
				  			  "VERIFIED count of resolved records as " + resolveCount, 
			  				  "Failed since count of resolved records is found as " + resolveCount);
		
		String totalRecCountRSP = rsp.TotalRecordCount.getText().trim();
		FSQABrowserRecordsPage frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.TOTALS);
		TestValidation.Equals(false,
							  frp.error, 
							  "As total records = " + totalRecCountRSP + ", we CAN drill down", 
							  "Failed to verify whether we can drill down for Totals panel or not");
		
		boolean navigateBack = rsp.clickReviewRecsLink();
		TestValidation.IsTrue(navigateBack, 
				  			  "NAVIGATED back to Record Signoff > Review Records", 
			  				  "Failed to navigate back to Record Signoff > Review Records");
		
		boolean verifyShowCorrBtn = controlActions.isElementDisplayed(rsp.ShowCorrectnsBtn);
		TestValidation.IsTrue(verifyShowCorrBtn, 
							  "VERIFIED Show Corrections button is displayed", 
				  			  "Failed to verify Show Corrections button is displayed");
		
	}
	
	@Test(description = "Record Signoff || Show Corrections || Verify on applied filters, "
			+ "the corrections are displayed accordingly")
	public void TestCase_37468() {
		
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
		
		String totalCount = rsp.TotalRecordCount.getText().trim();
		TestValidation.IsTrue((totalCount!="" || totalCount!=null), 
							  "VERIFIED Total Count of records are - " + totalCount, 
				  			  "Failed to verify Total Count of records");
		
		boolean applyFilter = rsp.applyFilterBySearch(FILTERTYPES.LOCATION, locationInstanceValue);
		TestValidation.IsTrue(applyFilter, 
							  "APPLIED filter for Location instance for form - " + formName, 
				  			  "Failed to apply filter for Location instance for form - " + formName);
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		String filtrdtotalCount = rsp.TotalRecordCount.getText().trim();
		TestValidation.IsTrue((filtrdtotalCount.equals("2")), 
							  "VERIFIED filtered Total Count of records are - " + filtrdtotalCount, 
				  			  "Failed to verify filtered Total Count of records");
		
		String filtrdUnresolveCount = rsp.UnresolvdRecsCount.getText().trim();
		TestValidation.IsTrue((filtrdUnresolveCount.equals("1")), 
				  			  "VERIFIED filtered count of unresolved records as " + filtrdUnresolveCount, 
							  "Failed since filtered count of unresolved records is found as " + filtrdUnresolveCount);
		
		String filtrdtResolveCount = rsp.ResolvdRecsCount.getText().trim();
		TestValidation.IsTrue((filtrdtResolveCount.equals("1")), 
				  			  "VERIFIED filtered count of resolved records as " + filtrdtResolveCount, 
							  "Failed since filtered count of resolved records is found as " + filtrdtResolveCount);
		
		boolean clearFilter = rsp.clearAppliedFilterForType(FILTERTYPES.LOCATION);
		TestValidation.IsTrue(clearFilter, 
							  "CLICKED on Clear Filter button for Location instance", 
				  			  "Failed to click on Clear Filter button for Location instance");
		
		boolean verifyTotalCount = rsp.TotalRecordCount.getText().trim().equals(totalCount);
		TestValidation.IsTrue(verifyTotalCount, 
				  			  "VERIFIED that post clearing of filter the count of total records is " + totalCount, 
			  				  "Failed since count of total records is found as " + rsp.TotalRecordCount.getText().trim());
		
		int unresolveCount = Integer.parseInt(rsp.UnresolvdRecsCount.getText().trim());
		TestValidation.IsTrue((unresolveCount >= 1), 
				  			  "VERIFIED that post clearing of filter the count of unresolved records is " + unresolveCount, 
			  				  "Failed since count of unresolved records is found as " + unresolveCount);
		
		int resolveCount = Integer.parseInt(rsp.ResolvdRecsCount.getText().trim());
		TestValidation.IsTrue((resolveCount >= 1), 
				  			  "VERIFIED that post clearing of filter the count of resolved records is " + resolveCount, 
			  				  "Failed since count of resolved records is found as " + resolveCount);
		
	}
	
	@Test(description = "Record Signoff || Show Corrections || Verify when filters are applied,"
			+ " the corrections are displayed accordingly")
	public void TestCase_37469() {
		
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
		
		String totalCount = rsp.TotalRecordCount.getText().trim();
		TestValidation.IsTrue((totalCount!="" || totalCount!=null), 
							  "VERIFIED Total Count of records are - " + totalCount, 
				  			  "Failed to verify Total Count of records");
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		String unresolveCount = rsp.UnresolvdRecsCount.getText().trim();
		TestValidation.IsTrue((unresolveCount!="" || unresolveCount!=null), 
				  			  "VERIFIED count of unresolved records as " + unresolveCount, 
			  				  "Failed since count of unresolved records is found as " + unresolveCount);
		
		String resolveCount = rsp.ResolvdRecsCount.getText().trim();
		TestValidation.IsTrue((resolveCount!="" || resolveCount!=null), 
				  			  "VERIFIED count of resolved records as " + resolveCount, 
			  				  "Failed since count of resolved records is found as " + resolveCount);
		
		boolean applyFilter = rsp.applyFilterBySearch(FILTERTYPES.FORMS, formName);
		TestValidation.IsTrue(applyFilter, 
							  "APPLIED filter for forms for form - " + formName, 
				  			  "Failed to apply filter for forms for form - " + formName);
		
		String filtrdtotalCount = rsp.TotalRecordCount.getText().trim();
		TestValidation.IsTrue((filtrdtotalCount.equals("2")), 
							  "VERIFIED filtered Total Count of records are - " + filtrdtotalCount, 
				  			  "Failed to verify filtered Total Count of records");
		
		String filtrdUnresolveCount = rsp.UnresolvdRecsCount.getText().trim();
		TestValidation.IsTrue((filtrdUnresolveCount.equals("1")), 
				  			  "VERIFIED filtered count of unresolved records as " + filtrdUnresolveCount, 
							  "Failed since filtered count of unresolved records is found as " + filtrdUnresolveCount);
		
		String filtrdtResolveCount = rsp.ResolvdRecsCount.getText().trim();
		TestValidation.IsTrue((filtrdtResolveCount.equals("1")), 
				  			  "VERIFIED filtered count of resolved records as " + filtrdtResolveCount, 
							  "Failed since filtered count of resolved records is found as " + filtrdtResolveCount);
		
		boolean clearFilter = rsp.clickClearAllFilterBtn();
		TestValidation.IsTrue(clearFilter, 
							  "CLICKED on Clear All Filter button", 
				  			  "Failed to click on Clear All Filter button");
		
		boolean verifyTotalCount = rsp.TotalRecordCount.getText().trim().equals(totalCount);
		TestValidation.IsTrue(verifyTotalCount, 
				  			  "VERIFIED that post clearing of filter the count of total records is " + totalCount, 
			  				  "Failed since count of total records is found as " + rsp.TotalRecordCount.getText().trim());
		
		boolean verifyUnresolveCount = rsp.UnresolvdRecsCount.getText().trim().equals(unresolveCount);
		TestValidation.IsTrue(verifyUnresolveCount, 
				  			  "VERIFIED that post clearing of filter the count of unresolved records is " + unresolveCount, 
			  				  "Failed since count of unresolved records is found as " + rsp.UnresolvdRecsCount.getText().trim());
		
		boolean verifyResolveCount = rsp.ResolvdRecsCount.getText().trim().equals(resolveCount);
		TestValidation.IsTrue(verifyResolveCount, 
				  			  "VERIFIED that post clearing of filter the count of resolved records is " + resolveCount, 
			  				  "Failed since count of resolved records is found as " + rsp.ResolvdRecsCount.getText().trim());
		
	}
	
	@Test(description = "FSQA Records || Show Corrections || Verify 'Show Corrections' button works fine"
			+ " when few records are selected for Sign off")
	public void TestCase_37068() {
		
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
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");
		
		String unresolveCount = rsp.UnresolvdRecsCount.getText().trim();
		TestValidation.IsTrue((unresolveCount!="0" || unresolveCount!="" || unresolveCount!=null), 
				  			  "VERIFIED count of unresolved records as " + unresolveCount, 
			  				  "Failed since count of unresolved records is found as " + unresolveCount);
		
		String resolveCount = rsp.ResolvdRecsCount.getText().trim();
		TestValidation.IsTrue((resolveCount!="0" || resolveCount!="" || resolveCount!=null), 
				  			  "VERIFIED count of resolved records as " + resolveCount, 
			  				  "Failed since count of resolved records is found as " + resolveCount);
		
	}
	
	@Test(description = "Multi-Record Signoff - Record Signoff - Users are shown only non-compliant records "
			+ "when drilling down on the Non-Compliant analytic(9054)")
	public void TestCase_33206() {
		
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

		String nonCompliantRecCountRSP = rsp.NonCompliantRecsCount.getText().trim();
		FSQABrowserRecordsPage frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.NON_COMPLIANT);
		TestValidation.Equals(false,
							  frp.error, 
							  "As Non compliant records = "+ nonCompliantRecCountRSP +", we CAN drill down", 
							  "Failed to verify whether we can drill down for Non compliant panel or not");
		
		boolean compareCountInRV = frp.RViewerRecordsTitle.getText().contains(nonCompliantRecCountRSP);
		TestValidation.IsTrue(compareCountInRV, 
							  nonCompliantRecCountRSP + " number of RECORDS are displayed in Record Viewer", 
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
		
		boolean searchAndSelect = frp.searchAndSelectRecordInViewSignMode(formName,1);
		TestValidation.IsTrue(searchAndSelect, 
				  			  "SELECTED first record in Record Viewer",
				  			  "Could NOT select first record in Record Viewer"); 
		
		RecordFormDetails rfd = new RecordFormDetails();
		rfd.FormName = formName;
		rfd.Resource = customersInstanceValue;
		rfd.Location = locationInstanceValue;
		rfd.CompletedBy = displayName;
		boolean verifyFormDetails = frp.verifyRecordsDetailsInView(rfd);
		TestValidation.IsTrue(verifyFormDetails, 
				  			  "VERIFIED record details for form - " + rfd.FormName, 
				  			  "Failed to verify record details for form - " + rfd.FormName);
		
	}
	
	@Test(description = "Multi-Record Signoff - Record Signoff - Users are shown only unresolved corrections "
			+ "when drilling down on a record on the Unresolved tab(9058)")
	public void TestCase_33214() {
		
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
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");

		String unreslvdRecCountRSP = rsp.UnresolvdRecsCount.getText().trim();
		FSQABrowserRecordsPage frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.UNRESOLVED);
		TestValidation.Equals(false,
							  frp.error, 
							  "As Unresolved records = "+ unreslvdRecCountRSP +", we CAN drill down", 
							  "Failed to verify whether we can drill down for Unresolved panel or not");
	
		int unreslvdRecCountValueRSP = Integer.parseInt(unreslvdRecCountRSP);
		if(unreslvdRecCountValueRSP == 1) {
			
			boolean formNameVal = frp.verifyRecordsDetails(FORMFIELDS.FORMNAME, formName);
			TestValidation.IsTrue(formNameVal, 
								  "Navigated to Record as just 1 record was found for unresolved corrections."
								  + "Verified Form Name value " + formName, 
								  "Failed to verify Form Name value" + formName);
			
			boolean resourceVal = frp.verifyRecordsDetails(FORMFIELDS.RESOURCE, customersInstanceValue);
			TestValidation.IsTrue(resourceVal, 
								  "Verified Resource value " + customersInstanceValue, 
								  "Failed to verify Resource value" + customersInstanceValue);
			
			boolean locationVal = frp.verifyRecordsDetails(FORMFIELDS.LOCATION, locationInstanceValue);
			TestValidation.IsTrue(locationVal, 
								  "Verified Location value " + locationInstanceValue, 
								  "Failed to verify Location value" + locationInstanceValue);
			
			boolean displayNameVal = frp.verifyRecordsDetails(FORMFIELDS.COMPLETEDBY, displayName);
			TestValidation.IsTrue(displayNameVal, 
								  "Verified Completed By value " + displayName, 
								  "Failed to verify Completed By value" + displayName);
			
		}
		else if(unreslvdRecCountValueRSP > 1)
		{
			boolean compareCountInRV = frp.RViewerRecordsTitle.getText().contains(unreslvdRecCountRSP);
			TestValidation.IsTrue(compareCountInRV, 
								  unreslvdRecCountRSP + " number of RECORDS are displayed in Record Viewer", 
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
	}
	
	@Test(description = "Multi-Record Signoff - Record Signoff - Users are shown only resolved corrections "
			+ "when drilling down on a record on the Resolved tab(9059)")
	public void TestCase_33215() {
		
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
		
		boolean clickShowCorrBtn = rsp.clickShowCorrectionsBtn();
		TestValidation.IsTrue(clickShowCorrBtn, 
							  "CLICKED on Show Corrections button", 
				  			  "Failed to click on Show Corrections button");

		String reslvdRecCountRSP = rsp.ResolvdRecsCount.getText().trim();
		FSQABrowserRecordsPage frp = rsp.drilldownAnalytics(RECORD_ANALYTIC_PANELS.RESOLVED);
		TestValidation.Equals(false,
							  frp.error, 
							  "As Resolved records = "+ reslvdRecCountRSP +", we CAN drill down", 
							  "Failed to verify whether we can drill down for Resolved panel or not");
		
		boolean compareCountInRV = frp.RViewerRecordsTitle.getText().contains(reslvdRecCountRSP);
		TestValidation.IsTrue(compareCountInRV, 
				reslvdRecCountRSP + " number of RECORDS are displayed in Record Viewer", 
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


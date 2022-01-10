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
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.ProgramDesignerPage;
import com.project.safetychain.webapp.pageobjects.ProgramViewerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.PDFReader;


public class TCG_SMK_Record_EditAndDownload extends TestBase{
	
	ControlActions controlActions;
	public static String formName;
	public static String changeResourceCmmt;
	public static String locationCategoryValue;
	public static String locationInstanceValue;
	public static String customersCategoryValue;
	public static String customersInstance1Value;
	public static String customersInstance2Value;
	public static String displayName = null;
	public static String programName; 
	public static String programElementName;
	public static String numericFN = "Numeric";
	public static List<String> formsNMLst = new ArrayList<String>();

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		
		formName = CommonMethods.dynamicText("Chk");
		changeResourceCmmt = CommonMethods.dynamicText("ChangeResCmmt");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstance1Value = CommonMethods.dynamicText("Cust_Inst1");
		customersInstance2Value = CommonMethods.dynamicText("Cust_Inst2");
		programName = CommonMethods.dynamicText("PD");
		programElementName = CommonMethods.dynamicText("PE");
		formsNMLst.add(formName);

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
		resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstance1Value,customersInstance2Value));

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
		String formId = apiUtils.getUUID();

		// API - Form layout details
		FormParams fp = new FormParams();
		fp.FormId = formId;
		fp.FormName = formName;
		fp.type = DPT_FORM_TYPES.CHECK;
		fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fp.ResourceCategoryNm = customersCategoryValue;
		fp.ResourceInstanceNm = customersInstance1Value;
		fp.formElements = Arrays.asList(numericField);
		fp.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fp);

		// ------------------------------------------------------------------------------------------------
		// API - Submit form

		// API - Add values to fields in form
		FormFieldParams submitNumericField = new FormFieldParams();
		submitNumericField.Name = numericFN;
		submitNumericField.Value = "20";

		// API - Form layout details
		FormParams fpSubmitForm = new FormParams();
		fpSubmitForm.FormName = formName;
		fpSubmitForm.ResourceInstanceNm = customersInstance1Value;
		fpSubmitForm.LocationInstanceNm = locationInstanceValue;
		fpSubmitForm.formElements = Arrays.asList(submitNumericField);

		formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmitForm);

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
		
//		//Category creation
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
//		instances.put(customersInstance1Value, true);
//		instances.put(customersInstance2Value, true);
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
//		List<String> addInstances = new ArrayList<String>();
//		addInstances.add(customersInstance1Value);
//		addInstances.add(customersInstance2Value);
//		FormDesignerPage fdp = new FormDesignerPage(driver);
//		if(!fdp.createAndReleaseForm("Check", formName,"Customers", customersCategoryValue, addInstances)) {
//			TCGFailureMsg = "Could NOT create and release form - " + formName;
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
//		if(!fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName)) {
//			TCGFailureMsg = "Could not filter form - " + formName;
//			logFatal(TCGFailureMsg);
//			throw new SkipException(TCGFailureMsg);
//		}
//		
//		FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
//		if(!ffp.submitData(false, false, false, true, customersInstance1Value)) {
//			TCGFailureMsg = "Could NOT submit form - " + formName;
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

	@Test(description="Verify Edit Record -Resource Edit functionality")
	public void TestCase_31167() throws Exception{
		
		HomePage hp = new HomePage(driver);			
		FSQABrowserPage fbp1 = hp.clickFSQABrowserMenu();
		boolean navigateToRecords = fbp1.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
		TestValidation.IsTrue(navigateToRecords, 
							  "For customer category, NAVIGATED to FSQABrowser > Records tab", 
							  "Failed to navigate to FSQABrowser > Records tab");

		boolean openRecord = fbp1.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsTrue(openRecord, 
							  "OPENED record for form - " + formName, 
							  "Failed to open record for form - " + formName);

		FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
		boolean editRecord = frp.clickEditRecordBtn();
		TestValidation.IsTrue(editRecord, 
							  "EDITED record for form - " + formName, 
							  "Failed to edit for form " + formName);
		
		boolean editResource = frp.editResourceForRecord(customersInstance2Value); 
		TestValidation.IsTrue(editResource, 
			  		   		  "EDITED resource for record to - " + customersInstance2Value, 
			  		   		  "Failed to edit resource for record to " + customersInstance2Value);
		
		FSQABrowserPage fbp2 = frp.submitEditResourceRecord(changeResourceCmmt);
		TestValidation.Equals(fbp2.error,
							  false,
		   		  			  "SUBMITTED record after change of resource for record", 
		   		  			  "Failed to submit record after change of resource for record");

		boolean verifyResource = fbp2.verifyGridValuesForColumn(COLUMNHEADER.RESOURCE, customersInstance2Value);
		TestValidation.IsTrue(verifyResource, 
							  "VERIFIED resource value as - " + customersInstance2Value, 
							  "Failed to verify resource value as " + customersInstance2Value);

	}
	
	@Test(description="Verify Download Record functionality")
	public void TestCase_32572() throws Exception{
		
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
		boolean downloadRecord = frp.clickDownloadRecordLnk();
		TestValidation.IsTrue(downloadRecord, 
							  "DOWNLOADED record for form - " + formName, 
							  "Failed to download record for form " + formName);
		
		String pdfData = PDFReader.getTextFromPDF(downloadPath+formName+".pdf");
		boolean verifyFormName = pdfData.contains(formName);
		boolean verifyInstanceName = pdfData.contains(locationInstanceValue);
		boolean verifyUserName = pdfData.contains(displayName);
		TestValidation.IsTrue((verifyFormName && verifyInstanceName && verifyUserName), 
				  			  "VERIFIED values like " + formName + " ," + locationInstanceValue + " ," + displayName + " in downloaded pdf", 
				  			  "Failed to verify values like " + formName + " ," + locationInstanceValue + " ," + displayName + " in downloaded pdf");

	}
	
	@Test(description = "Program Management - Program Viewer: Tenant user can select records associated "
			+ "with a program element in the Records Tab(12130)")
	public void TestCase_17306() throws Exception {

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

		boolean pdVerifyDesc = pvp.verifyRecordsFromPrograms(formsNMLst);
		TestValidation.IsTrue(pdVerifyDesc, 
						 	  "VERIFIED Records " + formsNMLst + " to Program  - " + programName,
						 	  "Could NOT verify Records " + formsNMLst + " to Program  - " + programName);

	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


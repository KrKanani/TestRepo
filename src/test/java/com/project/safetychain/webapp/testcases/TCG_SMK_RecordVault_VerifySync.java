package com.project.safetychain.webapp.testcases;

import java.util.*;

import org.testng.*;
import org.testng.annotations.*;

import com.project.safetychain.api.models.support.*;
import com.project.safetychain.api.models.support.Support.*;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.*;
import com.project.safetychain.webapp.pageobjects.LinkRecordsPage.RecordFieldNames;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.*;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.*;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.*;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.testbase.*;
import com.project.utilities.*;


public class TCG_SMK_RecordVault_VerifySync extends TestBase{
	
	ControlActions controlActions;
	public static String formName;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String numericFN = "Number";
	public static String displayName = null;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("RecVault");
		locationCategoryValue = locationName;	
		locationInstanceValue = locationName;	
		customersCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		customersInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		
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
			fpSubmit.FormName = formName; fpSubmit.type = DPT_FORM_TYPES.CHECK;
			fpSubmit.ResourceInstanceNm = customersInstanceValue;
			fpSubmit.LocationInstanceNm = locationInstanceValue;
			fpSubmit.formElements = Arrays.asList(submitNumericField);

			formSubmissionWrapper.API_Form_Submit_Wrapper(fpSubmit);

			// ------------------------------------------------------------------------------------------------
			// Supporting WEB Application code starts here
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
		}
		else {
			TCGFailureMsg = "Improper Execution Mode is set - " + executionMode;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description="Record Vault || Verify records are getting synced in Record Vault")
	public void TestCase_53148() {
		
		HomePage hp = new HomePage(driver);			
		LinkPage lp = hp.clickLinkMenu();
		TestValidation.Equals(lp.error, 
							  false, 
							  "CLICKED on Link menu", 
							  "Could NOT click on Link menu"); 
		
		LinkRecordsPage lrp = lp.clickLinkRecords();
		TestValidation.Equals(lrp.error, 
							  false, 
							  "CLICKED on Link - Records menu", 
							  "Could NOT click on Link - Records menu"); 
		
		boolean setTimeDsc = lrp.clickTimeColumnToPerformDescending();
		TestValidation.IsTrue(setTimeDsc, 
							  "Time column SET to Descending order", 
							  "Failed to Set Time column to Descending order");

		boolean selectForm = lrp.findAndClickFormToSelect(formName);
		TestValidation.IsTrue(selectForm, 
							  "SELECTED the Form " + formName, 
							  "Failed to Select the Form " + formName);
		
		boolean verifyLocation = lrp.verifyRecordsDetail(RecordFieldNames.LOCATION, locationInstanceValue);
		TestValidation.IsTrue(verifyLocation, 
							  "VERIFIED Location of the form as " + locationInstanceValue, 
							  "Failed to Verify Location of the form as " + locationInstanceValue);
		
		boolean verifyResource = lrp.verifyRecordsDetail(RecordFieldNames.RESOURCE, customersInstanceValue);
		TestValidation.IsTrue(verifyResource, 
							  "VERIFIED Resource of the form as " + customersInstanceValue, 
							  "Failed to Verify Resource of the form as " + customersInstanceValue);
		
		boolean verifyFormName = lrp.verifyRecordsDetail(RecordFieldNames.FORM, formName);
		TestValidation.IsTrue(verifyFormName, 
							  "VERIFIED the Form name as " + formName, 
							  "Failed to Verify the Form name as " + formName);
		
		boolean verifyDisplayName = lrp.verifyRecordsDetail(RecordFieldNames.USER, displayName);
		TestValidation.IsTrue(verifyDisplayName, 
							  "VERIFIED Display name of the form as " + displayName, 
							  "Failed to Verify Display name of the form as " + displayName);		
		
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


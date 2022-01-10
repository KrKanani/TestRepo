package com.project.safetychain.webapp.testcases;

import java.util.*;

import org.testng.*;
import org.testng.annotations.*;

import com.project.safetychain.api.models.support.*;
import com.project.safetychain.api.models.support.Support.*;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.*;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.*;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.*;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.*;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.testbase.SupportingClasses.PrereqData;
import com.project.testbase.SupportingClasses.SheetNames;
import com.project.testbase.*;
import com.project.utilities.*;


public class TCG_SMK_Record_VoidFlow extends TestBase{
	
	ControlActions controlActions;
	public static String formName;
	public static String voidRecCmmt;
	public static String locationCategoryValue;	
	public static String locationInstanceValue;	
	public static String customersCategoryValue;
	public static String customersInstanceValue;
	public static String numericFN = "Number";

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {

		formName = CommonMethods.dynamicText("Void");
		voidRecCmmt = CommonMethods.dynamicText("VoidCmmt");
		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");	
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");	
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
			fpSubmit.FormName = formName;
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
		else if(executionMode.equalsIgnoreCase(ExecutionMode.EXISTING)) {
			/*
			 * AVOID THIS ELSE IF BLOCK; 
			 * This implementation is just a Sample. This is not encouraged as it will increase dependency on TCs of prerequisite data.
			 * Even if we invest time in adding this logic, it won't be much beneficial because of follows:
			 * 1) For created data in last run, we will have to update excel (programmatically or manually).
			 * 		There are chances this data is not created properly or gets updated by other script 
			 * 2) Error/Failure rate may increase due to involvement of Excel files and it's data entries
			 */
			String filePath = System.getProperty("user.dir") + "\\test-data-files\\" + smokePrereqExcel;
			String className = this.getClass().getSimpleName();
			HashMap<String, HashMap<String, String>> dataFromExcel = 
												ExcelReader.ReadAllDataFromExcelSheets(filePath, SheetNames.SMOKE_FLOWS);
			
			formName = CommonMethods.getPrereqVariableValue(dataFromExcel, className, PrereqData.FORM_NAME);// form name of record
			voidRecCmmt = CommonMethods.dynamicText("VoidCmmt"); // these two are required
			
			// ------------------------------------------------------------------------------------------------
			// verify value to variables are set
			if(formName != "") {
				
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
			}
			else {
				TCGFailureMsg = "All variables are not set successfully from excel - " + filePath;
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

	@Test(description="Verify VOID Record functionality")
	public void TestCase_31169() throws Exception{
		
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
		
		FSQABrowserPage fbp2 = frp.clickAndPerformVoidRecord(voidRecCmmt);
		TestValidation.Equals(fbp2.error,
							  false,
							  "Record has been turned to VOID for form - " + formName, 
							  "Failed to void record for form " + formName);

		boolean verifyRecordExists = fbp2.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, formName);
		TestValidation.IsFalse(verifyRecordExists, 
							  "VERIFIED FSQA records tab has no entry for form - " + formName, 
							  "Failed to verify FSQA records tab has no entry for form - " + formName);
		
	}

	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}


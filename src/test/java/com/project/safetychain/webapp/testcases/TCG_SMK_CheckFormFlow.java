package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPageLocator;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_SMK_CheckFormFlow extends TestBase{
	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	HomePage hp;

	//Data
	public static String checkFormName;
	public static String checkFormName1;
	public static String checkFormName2;
	public static String checkFormName3;
	public static String checkFormName4;
	public static String checkFormName5;
	public static String locationCategoryValue;
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1,resourceInstanceValue2;
	public static String locationInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		checkFormName = CommonMethods.dynamicText("Automation_CheckForm");
		checkFormName1 = CommonMethods.dynamicText("Automation_CheckForm1");
		checkFormName2 = CommonMethods.dynamicText("Automation_CheckForm2");
		checkFormName3 = CommonMethods.dynamicText("Automation_CheckForm3");
		checkFormName4 = CommonMethods.dynamicText("Automation_CheckForm4");
		checkFormName5 = CommonMethods.dynamicText("Automation_CheckForm5");
		locationCategoryValue = locationName;
		locationInstanceValue = locationName;

		resourceCategoryValue = CommonMethods.dynamicText("Resource_Cat");
		resourceInstanceValue1 = CommonMethods.dynamicText("Resource_Inst1");
		resourceInstanceValue2 = CommonMethods.dynamicText("Resource_Inst2");
		
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
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1,resourceInstanceValue2));
			
			HashMap<String, String> resourceStatus = new HashMap<String, String>();
			resourceStatus.put(resourceInstanceValue2, "false");
	
			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.CUSTOMERS, resourceStatus);
	
			// ------------------------------------------------------------------------------------------------
			// API - Form Creation - Check
			
			// API - Form details
			// API - Generate unique ID for form to be created
			String formId = apiUtils.getUUID();
	
			// API - Form layout details
			FormParams fp = new FormParams();
			fp.FormId = formId;
			fp.FormName = checkFormName;
			fp.type = DPT_FORM_TYPES.CHECK;
			fp.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			fp.ResourceCategoryNm = resourceCategoryValue;
			fp.ResourceInstanceNm = resourceInstanceValue1;
			fp.CustomerResources = resourceCatMap;
	
			formCreationWrapper.API_Wrapper_CreateUnreleasedForms(fp);

			// ------------------------------------------------------------------------------------------------
			// WEB Application code starts here
	
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
			mainPage = new CommonPage(driver);
			lgnPge = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			manageResource = new ManageResourcePage(driver);
			locations = new ManageLocationPage(driver);
			fdp = new FormDesignerPage(driver);

			//login
			HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
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
			mainPage = new CommonPage(driver);
			lgnPge = new LoginPage(driver);
			resourceDesigner = new ResourceDesignerPage(driver);
			manageResource = new ManageResourcePage(driver);
			locations = new ManageLocationPage(driver);
			fdp = new FormDesignerPage(driver);

			//login
			HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
			
			//Category creation
			HashMap<String,String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.CUSTOMERS, resourceCategoryValue);
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
			instances.put(resourceInstanceValue1, true);
			instances.put(resourceInstanceValue2, false);
			ResourceDetailParams rd = new ResourceDetailParams();
			rd.CategoryName = resourceCategoryValue;
			rd.CategoryType = RESOURCETYPES.CUSTOMERS;
			rd.NumberFieldValue = 30;
			rd.TextFieldValue = "ABC";
			rd.InstanceNames = instances;
			rd.LocationInstanceValue = locationInstanceValue;
			ManageResourcePage mrp = hp.clickResourcesMenu();
			if(!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + resourceCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
			}
			
			//Open 'Form Designer'
			if(mainPage.clickFormDesignerMenu().error) {
				TCGFailureMsg = "Could NOT Open Form Designer";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
	
			controlActions.clickElement(fdp.SelectCheckFormLnk);
	
			//Selection of resource
			if(!fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1)) {
				TCGFailureMsg = "Could NOT select resource";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
	
			//Go to 'Form Design'
			if(!fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form")) {
				TCGFailureMsg = "Could NOT Navigate to 'Design Form'";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
	
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName);
	
			//Save the form
			if(!fdp.clickSaveButton()) {
				TCGFailureMsg = "Could NOT Save Form";
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
	

	@Test(description="Add fields at form level of check form")
	public void TestCase_31126() {
		
		boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(checkFormName);
		TestValidation.IsTrue(editFormDesign, 
				"OPENED form - '"+checkFormName+"' in edit mode", 
				"Could NOT open form - '"+checkFormName+"' in edit mode"); 

		String freeTextFieldName = "FreeText1";
		WebElement FreeTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextFieldType, fdp.FormLevel);
		boolean setFreeText = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);		
		TestValidation.IsTrue(setFreeText, 
				"'Free Text field' added successfully", 
				"Failed to Add Free Text Field");
		
		String paragraphTextFieldName = "ParagraphText1";
		WebElement ParagraphTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.PARAGRAPH_TEXT);
		controlActions.dragdrop(ParagraphTextFieldType, fdp.FormLevel);
		boolean setParagraphText = fdp.setTextBoxValue(FIELD_TYPES.PARAGRAPH_TEXT, paragraphTextFieldName);		
		TestValidation.IsTrue(setParagraphText, 
				"'Paragraph Text field' added successfully", 
				"Failed to Add Paragraph Text Field");

		String selectOneFieldName = "SelectOne1";
		WebElement SelectOneFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SELECT_ONE);
		controlActions.dragdrop(SelectOneFieldType, fdp.FormLevel);
		boolean setSelectOne = fdp.setTextBoxValue(FIELD_TYPES.SELECT_ONE, selectOneFieldName);		
		TestValidation.IsTrue(setSelectOne, 
				"'Select One field' added successfully", 
				"Failed to Add Select One Field");

		boolean setValues1 = fdp.setValuesToElement("1");
		boolean setValues2 = fdp.setValuesToElement("2");
		boolean setValues3 = fdp.setValuesToElement("3");
		TestValidation.IsTrue(setValues1&&setValues2&&setValues3, 
				"Successfully set values to Select One", 
				"Failed to set values to Select One");

		String selectMultipleFieldName = "SelectMultiple1";
		WebElement SelectMultipleFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SELECT_MULTIPLE);
		controlActions.dragdrop(SelectMultipleFieldType, fdp.FormLevel);
		boolean setSelectMultiple = fdp.setTextBoxValue(FIELD_TYPES.SELECT_MULTIPLE, selectMultipleFieldName);		
		TestValidation.IsTrue(setSelectMultiple, 
				"'Select Multiple field' added successfully", 
				"Failed to Add Select Multiple Field");

		boolean setValues4 = fdp.setValuesToElement("4");
		boolean setValues5 = fdp.setValuesToElement("5");
		boolean setValues6 = fdp.setValuesToElement("6");
		TestValidation.IsTrue(setValues4&&setValues5&&setValues6, 
				"Successfully set values to Select Multiple", 
				"Failed to set values to Select Multiple");

		String numericFieldName = "Numeric1";
		WebElement NumericFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericFieldType, fdp.FormLevel);
		boolean setNumeric = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
		TestValidation.IsTrue(setNumeric, 
				"'Numeric field' added successfully", 
				"Failed to Add Numeric Field");

		String dateFieldName = "Date1";
		WebElement DateFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.DATE);
		controlActions.dragdrop(DateFieldType, fdp.FormLevel);
		boolean setDate = fdp.setTextBoxValue(FIELD_TYPES.DATE, dateFieldName);		
		TestValidation.IsTrue(setDate, 
				"'Date field' added successfully", 
				"Failed to Add Date Field");

		String timeFieldName = "Time1";
		WebElement TimeFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE","Time");
		controlActions.dragdrop(TimeFieldType, fdp.FormLevel);
		boolean setTime = fdp.setTextBoxValue(FIELD_TYPES.TIME, timeFieldName);		
		TestValidation.IsTrue(setTime, 
				"'Time field' added successfully", 
				"Failed to Add Time Field");		

		String dateTimeFieldName = "DateTime1";
		WebElement DateTimeFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.DATE_TIME);
		controlActions.dragdrop(DateTimeFieldType, fdp.FormLevel);
		boolean setDateTime = fdp.setTextBoxValue(FIELD_TYPES.DATE_TIME, dateTimeFieldName);		
		TestValidation.IsTrue(setDateTime, 
				"'DateTime field' added successfully", 
				"Failed to Add setDateTime Field");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");

		boolean verifyInPreviewForm = fdp.verifyInPreviewForm(checkFormName);
		TestValidation.IsTrue(verifyInPreviewForm, 
				"Verified fields in 'Preview Form'", 
				"Could NOT verify fields in the 'Preview Form'");
	}

	@Test(description="Form Designer || Check user should be able to create and release \"Check\" type of form.")
	public void TestCase_31120() {

		try {
			FormDesignerPage fdp = new FormDesignerPage(driver);
	
			//Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
	
			controlActions.clickElement(fdp.SelectCheckFormLnk);
	
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, 
					"Selected Resource successfully", 
					"Failed to Select Resource");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
					"Clicked On Next Button successfully", 
					"Failed to Click On Next Button");
	
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName1);
	
			String numericFieldName = "Numeric1";
			WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
			TestValidation.IsTrue(setNumericFieldValue1, 
					"'Numeric' field added successfully", 
					"Failed to Add Numeric field");		
	
			boolean saveForm = fdp.clickSaveButton();
			TestValidation.IsTrue(saveForm, 
					"Saved form Successfully", 
					"Could NOT Save Form");
	
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
			TestValidation.IsTrue(nextToReleaseForm, 
					"NAVIGATED to 'Release Form'", 
					"Could NOT Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(checkFormName1);
			TestValidation.IsTrue(releaseForm, 
					"RELEASED form - "+checkFormName1, 
					"Could NOT release form'");
	
			FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();
	
			boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Forms");	
			TestValidation.IsTrue(selectResourceDropDownandNavigate, 
					"NAVIGATED to FSQA -> Forms Tab", 
					"Could NOT Navigate to FSQA -> Forms Tab");
	
			boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormName1);
			TestValidation.IsTrue(applyFilterAndOpenForDetails, 
					"Verify form is displayed in Forms Tab", 
					"Form is NOT displayed in Forms Tab");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}	

	@Test(description="Verify disabled resource should not be shown in 'Select Resource' step in form designer")
	public void TestCase_31147() {

		try {
			FormDesignerPage opnFormDesigner = fdp.clickFormDesignerMenu();
			TestValidation.IsTrue(!(opnFormDesigner.error), 
					"OPENED 'Form Designer'", 
					"Could NOT open the 'Form Designer'");
	
			controlActions.clickElement(fdp.SelectCheckFormLnk);
	
			boolean verifyEnabledResource = fdp.verifyAddedResourceStatus(resourceType, resourceCategoryValue, resourceInstanceValue1);
			TestValidation.IsTrue(verifyEnabledResource, 
					"VERIFIED Enabled resource in SHOWN", 
					"Could NOT verify enabled resource");
	
			boolean verifyDisabledResource = fdp.verifyAddedResourceStatus(resourceType, resourceCategoryValue, resourceInstanceValue2);
			TestValidation.IsFalse(verifyDisabledResource, 
					"VERIFIED Disabled resource in NOT-SHOWN", 
					"Could NOT verify disabled resource");
	
			boolean exitFromFormDesigner = fdp.exitFormDesigner();
			TestValidation.IsTrue(exitFromFormDesigner, 
					"EXITED from 'Form Designer'", 
					"Could NOT exit from 'Form Designer'");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}

	}

	@Test(description="Verify repeated field inside repeated group should not be repeated in 'Design Form' step in form designer")
	public void TestCase_31178() {
		String groupName = "Repeated Group";
		String groupInnerFieldName = "Field inside group";

		try {
			boolean editFormDesign = fdp.navigateToReleaseForm_EditForm(checkFormName);
			TestValidation.IsTrue(editFormDesign, 
					"OPENED form - '"+checkFormName+"' in edit mode", 
					"Could NOT open form - '"+checkFormName+"' in edit mode"); 
	
			controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
			boolean setGroupName = fdp.setFieldName("Field Group",groupName);
			TestValidation.IsTrue(setGroupName, 
					"Setted 'Group' name", 
					"Could NOT set 'Group' name");
	
			boolean setGroupRepeatCount = fdp.setRepeatCountForField(groupName,"2");
			TestValidation.IsTrue(setGroupRepeatCount, 
					"Setted 'Group' repeat count", 
					"Could NOT set 'Group' repeat count");
	
			boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
			TestValidation.IsTrue(numericFieldInGroup, 
					"Added 'Numeric' field in 'Group'", 
					"Could NOT add 'Numeric' field in 'Group'");
			
			boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,groupInnerFieldName);
			TestValidation.IsTrue(setNumericFieldName, 
					"Setted 'Numeric' field name", 
					"Could NOT set 'Numeric' field name");
	
			boolean verifyNoRepeatStatus = fdp.verifyNoRepeatStatus(groupInnerFieldName);
			TestValidation.IsTrue(verifyNoRepeatStatus, 
					"VERIFIED field's No- Repeat status", 
					"Could NOT verify field's No- Repeat status");
	
			boolean exitFromFormDesigner = fdp.exitFormDesigner();
			TestValidation.IsTrue(exitFromFormDesigner, 
					"EXITED from 'Form Designer'", 
					"Could NOT exit from 'Form Designer'");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	

	@Test(description = "Rule Builder || Add compliance, dependency and expression rule on single field and verify in the form")
	public void TestCase_31248() {
		
		try {
			//Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
	
			controlActions.clickElement(fdp.SelectCheckFormLnk);
	
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, 
					"Selected Resource successfully", 
					"Failed to Select Resource");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
					"Clicked On Next Button successfully", 
					"Failed to Click On Next Button");
	
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName2);
			
			String numericFieldName = "Numeric1";
			WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
			TestValidation.IsTrue(setNumericFieldValue1, 
					"'Numeric' field added successfully", 
					"Failed to Add Numeric field");		
			
			String numericFieldName1 = "Numeric2";
			WebElement NumericField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField1, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue2 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName1);		
			TestValidation.IsTrue(setNumericFieldValue2, 
					"'Numeric' field added successfully", 
					"Failed to Add Numeric field");	
			
			boolean DependencyRule = fdp.addDependencyRuleUsingValue(numericFieldName, "5");
			TestValidation.IsTrue(DependencyRule, 
					"Field Type Added", 
					"Could NOT Add Dependency Rule");
			
			boolean setComplianceForNumericField2 = fdp.setComplianceForNumericAggragateField("Numeric2", "1", "3", "5", null);
			TestValidation.IsTrue(setComplianceForNumericField2, 
					"Set Compliance For Numeric Field - Numeric2 successfully", 
					"Failed to Set Compliance For Numeric Field - Numeric2");
	
			boolean setExprRule = fdp.ifExpressionRuleUsingValue("Numeric1", "5", "10", "15");
			TestValidation.IsTrue(setExprRule, 
					  			  "Successfully Added IF expression rule on field - Numeric2", 
					  			  "Failed to add IF expression rule on field - Numeric2");
			
			boolean saveForm = fdp.clickSaveButton();
			TestValidation.IsTrue(saveForm, 
					"Saved form Successfully", 
					"Could NOT Save Form");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
			TestValidation.IsTrue(nextToReleaseForm, 
					"NAVIGATED to 'Release Form'", 
					"Could NOT Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(checkFormName2);
			TestValidation.IsTrue(releaseForm, 
					"RELEASED form - "+checkFormName2, 
					"Could NOT release form'");
	
			FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();
	
			boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Forms");	
			TestValidation.IsTrue(selectResourceDropDownandNavigate, 
					"NAVIGATED to FSQA -> Forms Tab", 
					"Could NOT Navigate to FSQA -> Forms Tab");
	
			boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormName2);
			TestValidation.IsTrue(applyFilterAndOpenForDetails, 
					"Verify form is displayed in Forms Tab", 
					"Form is NOT displayed in Forms Tab");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "Rule Builder || Add compliance rule on a field and verify the form")
	public void TestCase_31247() {
		
		try {
			//Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
	
			controlActions.clickElement(fdp.SelectCheckFormLnk);
	
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, 
					"Selected Resource successfully", 
					"Failed to Select Resource");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
					"Clicked On Next Button successfully", 
					"Failed to Click On Next Button");
	
	
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName3);
			
			String numericFieldName = "Numeric1";
			WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
			TestValidation.IsTrue(setNumericFieldValue1, 
					"'Numeric' field added successfully", 
					"Failed to Add Numeric field");		
	
			String dateFieldName = "Date1";
			WebElement DateField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.DATE);
			controlActions.dragdrop(DateField, fdp.FieldTypeDropAreaFormLevel);
			boolean setDateFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.DATE, dateFieldName);		
			TestValidation.IsTrue(setDateFieldValue1, 
					"'Date' field added successfully", 
					" Failed to Add Date field");		
	
			boolean setCompliance = fdp.setComplianceForDateandTimeFieldv2(dateFieldName, resourceInstanceValue1, "9/7/2020");
			TestValidation.IsTrue(setCompliance, 
					"Set Compliance For Date Field Successfully", 
					"Could NOT Set Compliance For Date Field");
			
			boolean saveForm = fdp.clickSaveButton();
			TestValidation.IsTrue(saveForm, 
					"Saved form Successfully", 
					"Could NOT Save Form");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
			TestValidation.IsTrue(nextToReleaseForm, 
					"NAVIGATED to 'Release Form'", 
					"Could NOT Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(checkFormName3);
			TestValidation.IsTrue(releaseForm, 
					"RELEASED form - "+checkFormName3, 
					"Could NOT release form'");
	
			FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();
	
			boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Forms");	
			TestValidation.IsTrue(selectResourceDropDownandNavigate, 
					"NAVIGATED to FSQA -> Forms Tab", 
					"Could NOT Navigate to FSQA -> Forms Tab");
	
			boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormName3);
			TestValidation.IsTrue(applyFilterAndOpenForDetails, 
					"Verify form is displayed in Forms Tab", 
					"Form is NOT displayed in Forms Tab");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
		
	}
	
	@Test(description = "Rule Builder || Add dependency rule on Group and an expression rule on a field which is inside a Group")
	public void TestCase_31249() {

		try {
			//Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
	
			controlActions.clickElement(fdp.SelectCheckFormLnk);
	
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, 
					"Selected Resource successfully", 
					"Failed to Select Resource");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
					"Clicked On Next Button successfully", 
					"Failed to Click On Next Button");
	
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName4);
	
			String numericFieldName = "Numeric1";
			WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);		
			TestValidation.IsTrue(setNumericFieldValue1, 
					"'Numeric' field added successfully", 
					"Failed to Add Numeric field");		
	
			String groupName = "Group1";
			controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
			boolean setGroupName = fdp.setFieldName("Field Group",groupName);
			TestValidation.IsTrue(setGroupName, 
					"Setted 'Group' name", 
					"Could NOT set 'Group' name");
			
			boolean DependencyRule = fdp.addDependencyRuleUsingValue(numericFieldName, "5");
			TestValidation.IsTrue(DependencyRule, 
					"Field Type Added", 
					"Could NOT Add Dependency Rule");
			
			boolean numericFieldInGroup = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, groupName);
			TestValidation.IsTrue(numericFieldInGroup, 
					"Added 'Numeric' field in 'Group'", 
					"Could NOT add 'Numeric' field in 'Group'");
			
			String groupInnerFieldName ="Numeric2";
			boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,groupInnerFieldName);
			TestValidation.IsTrue(setNumericFieldName, 
					"Setted 'Numeric' field name", 
					"Could NOT set 'Numeric' field name");		
			
			boolean setExprRule = fdp.ifExpressionRuleUsingValue("Numeric1", "5", "10", "15");
			TestValidation.IsTrue(setExprRule, 
					  			  "Successfully Added IF expression rule on field - Numeric2", 
					  			  "Failed to add IF expression rule on field - Numeric2");
			
			boolean saveForm = fdp.clickSaveButton();
			TestValidation.IsTrue(saveForm, 
					"Saved form Successfully", 
					"Could NOT Save Form");
	
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
			TestValidation.IsTrue(nextToReleaseForm, 
					"NAVIGATED to 'Release Form'", 
					"Could NOT Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(checkFormName4);
			TestValidation.IsTrue(releaseForm, 
					"RELEASED form - "+checkFormName4, 
					"Could NOT release form'");
	
			FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();
	
			boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Forms");	
			TestValidation.IsTrue(selectResourceDropDownandNavigate, 
					"NAVIGATED to FSQA -> Forms Tab", 
					"Could NOT Navigate to FSQA -> Forms Tab");
	
			boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormName4);
			TestValidation.IsTrue(applyFilterAndOpenForDetails, 
					"Verify form is displayed in Forms Tab", 
					"Form is NOT displayed in Forms Tab");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}	

	@Test(description = "Rule Builder || Add a field having a expression rule & use the same field in the another rule, and verify the form")
	public void TestCase_31250() {
			
		try {
			//Open 'Form Designer'
			fdp = mainPage.clickFormDesignerMenu();
	
			controlActions.clickElement(fdp.SelectCheckFormLnk);
	
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue1);
			TestValidation.IsTrue(selectResource, 
					"Selected Resource successfully", 
					"Failed to Select Resource");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
					"Clicked On Next Button successfully", 
					"Failed to Click On Next Button");
	
	
			controlActions.sendKeys(fdp.FormNameTxt, checkFormName5);
		
			String numericFieldName1 = "Numeric1";
			WebElement NumericField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField1, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName1);		
			TestValidation.IsTrue(setNumericFieldValue1, 
					"'Numeric' field added successfully", 
					"Failed to Add Numeric field");		
	
			String numericFieldName2 = "Numeric2";
			WebElement NumericField2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField2, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue2 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName2);		
			TestValidation.IsTrue(setNumericFieldValue2	, 
					"'Numeric' field added successfully", 
					"Failed to Add Numeric field");					
			
			boolean setExprRule = fdp.ifExpressionRuleUsingValue("Numeric1", "5", "10", "15");
			TestValidation.IsTrue(setExprRule, 
					  			  "Successfully Added IF expression rule on field - Numeric2", 
					  			  "Failed to add IF expression rule on field - Numeric2");
			
			String numericFieldName3 = "Numeric3";
			WebElement NumericField3 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericField3, fdp.FieldTypeDropAreaFormLevel);
			boolean setNumericFieldValue3 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName3);		
			TestValidation.IsTrue(setNumericFieldValue3	, 
					"'Numeric' field added successfully", 
					"Failed to Add Numeric field");	
			
			boolean DependencyRule = fdp.addDependencyRuleUsingValue(numericFieldName2, "10");
			TestValidation.IsTrue(DependencyRule, 
					"Field Type Added", 
					"Could NOT Add Dependency Rule");
			
			boolean saveForm = fdp.clickSaveButton();
			TestValidation.IsTrue(saveForm, 
					"Saved form Successfully", 
					"Could NOT Save Form");
	
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
			TestValidation.IsTrue(nextToReleaseForm, 
					"NAVIGATED to 'Release Form'", 
					"Could NOT Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(checkFormName5);
			TestValidation.IsTrue(releaseForm, 
					"RELEASED form - "+checkFormName5, 
					"Could NOT release form'");
	
			FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();
	
			boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Forms");	
			TestValidation.IsTrue(selectResourceDropDownandNavigate, 
					"NAVIGATED to FSQA -> Forms Tab", 
					"Could NOT Navigate to FSQA -> Forms Tab");
	
			boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", checkFormName5);
			TestValidation.IsTrue(applyFilterAndOpenForDetails, 
					"Verify form is displayed in Forms Tab", 
					"Form is NOT displayed in Forms Tab");
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
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

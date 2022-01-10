package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
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
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_INPUT_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_INPUT_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_ELEMENTS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_NEXT_PAGE;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_NegativeFlows extends TestBase{
	ControlActions controlActions;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	HomePage hp;

	public static String locationCategoryValue;
	public static String fieldGroupName = "Test Group";
	public static String fieldSectionName = "Test Section";
	public static String fieldSelectOneName = "Test Select One";
	public static String fieldSelectMultName = "Test Select Multiple";
	public static String fieldNumericName = "Test Numeric";
	public static String fieldDateName = "Test Date";
	public static String fieldTime257CharsName = "Form Name Resource Location Completed By Completed On Non complaint "
			+ "Fields Identifier Fields Designer Records Form Name Resource Location Completed By Completed On Non complaint "
			+ "Fields Identifier Fields Designer Records Starting Form Name Resource Locations";
	public static String fieldTime255CharsName = "Form Name Resource Location Completed By Completed On Non complaint "
			+ "Fields Identifier Fields Designer Records Form Name Resource Location Completed By Completed On Non complaint "
			+ "Fields Identifier Fields Designer Records Starting Form Name Resource Locatio";
	public static String fieldDateTimeName = "Test Date Time";
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue;
	public static String locationInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		locationCategoryValue = CommonMethods.dynamicText("Loc_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		locationInstanceValue = CommonMethods.dynamicText("Loc_Inst");
		
		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		lgnPge = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations = new ManageLocationPage(driver);
		fdp = new FormDesignerPage(driver);

		//login
		hp = lgnPge.performLogin(adminUsername, adminPassword);
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
		instances.put(resourceInstanceValue, true);
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
		
	}

	@Test(description=" Check Form  - Verify user is able to release form with blank field group")
	public void TestCase_5150() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkNegtv_Group");
			
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectChk = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectChk, 
								  "SELECTED Check form type successfully", 
								  "Failed to Select Check form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(checkFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + checkFormName, 
					  			  "Failed to Set form name as " + checkFormName);
			
			//Add Group to Form
			WebElement GroupElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.FIELD_GROUP_TXT);
			controlActions.dragdrop(GroupElement, fdp.FormLevel);
			boolean setGroupFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.FIELD_GROUP_TXT, fieldGroupName);		
			TestValidation.IsTrue(setGroupFieldValue, 
								  "ADDED Field Group " + fieldGroupName + " successfully", 
								  "Failed to Add Field Group " + fieldGroupName + " successfully");
		
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsFalse(releaseForm, 
							       "Should NOT release form - " + checkFormName, 
								   "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			String expectedErrorMsg = fieldGroupName + "|No fields inside group";
			boolean verifyErrorMsg = fdp.verifyErrorMsgReleasePg(expectedErrorMsg);
			TestValidation.IsTrue(verifyErrorMsg, 
								  "VERIFIED error message '" + expectedErrorMsg + "' displayed on Release page", 
								  "Failed to verify error message displayed on Release page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description=" Check Form Elements - Verify Section Element functionality (Negative testing)")
	public void TestCase_5181() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkNegtv_Section");
			
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectChk = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectChk, 
								  "SELECTED Check form type successfully", 
								  "Failed to Select Check form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(checkFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + checkFormName, 
					  			  "Failed to Set form name as " + checkFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
		
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsFalse(releaseForm, 
							       "Should NOT release form - " + checkFormName, 
								   "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			String expectedErrorMsg = fieldSectionName + "|No fields inside section";
			boolean verifyErrorMsg = fdp.verifyErrorMsgReleasePg(expectedErrorMsg);
			TestValidation.IsTrue(verifyErrorMsg, 
								  "VERIFIED error message '" + expectedErrorMsg + "' displayed on Release page", 
								  "Failed to verify error message displayed on Release page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Check  Form- Select One functionality (Negative testing)")
	public void TestCase_5231() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkNegtv_SltOne");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectChk = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectChk, 
								  "SELECTED Check form type successfully", 
								  "Failed to Select Check form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(checkFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + checkFormName, 
					  			  "Failed to Set form name as " + checkFormName);
			
			//Add Select One Field 
			WebElement SelectOneFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.SELECT_ONE);
			controlActions.dragdrop(SelectOneFieldType, fdp.FormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Select One field successfully without name", 
								  "Failed to Add Select One field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement SelectOneFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.SELECT_ONE);
			boolean verifyFieldErrorMsg = SelectOneFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Select One error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Select One error message");
			
			boolean setSelectOneFldName = fdp.setTextBoxValue(FIELD_TYPES.SELECT_ONE, fieldSelectOneName);	
			TestValidation.IsTrue(setSelectOneFldName, 
								  "ADDED name " + fieldSelectOneName + " to Select One field successfully", 
								  "Failed to Add name " + fieldSelectOneName + " Select One field");
			
			boolean setValues1 = fdp.setValuesToElement("1");
			boolean setValues2 = fdp.setValuesToElement("2");
			TestValidation.IsTrue((setValues1 && setValues2), 
								  "Successfully SET values - 1 and 2 to Select One field", 
								  "Failed to set values - 1 and 2 to Select One field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsTrue(releaseForm, 
							      "SHOULD release form - " + checkFormName, 
								  "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + checkFormName, 
								  "Failed to open form - " + checkFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}

	@Test(description="Check Form General - Verify Select Multiple functionality (Negative testing)")
	public void TestCase_5232() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkNegtv_SltMult");
			
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectChk = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectChk, 
								  "SELECTED Check form type successfully", 
								  "Failed to Select Check form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(checkFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + checkFormName, 
					  			  "Failed to Set form name as " + checkFormName);
			
			//Add Select Multiple Field 
			WebElement SelectMultFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.SELECT_MULTIPLE);
			controlActions.dragdrop(SelectMultFieldType, fdp.FormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Select Multiple field successfully without name", 
								  "Failed to Add Select Multiple field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement SelectMultFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.SELECT_MULTIPLE);
			boolean verifyFieldErrorMsg = SelectMultFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Select Multiple error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Select Multiple error message");
			
			boolean setSelectMultipleFldName = fdp.setTextBoxValue(FIELD_TYPES.SELECT_MULTIPLE, fieldSelectMultName);	
			TestValidation.IsTrue(setSelectMultipleFldName, 
								  "ADDED name '" + fieldSelectMultName + "' to Select Multiple field successfully", 
								  "Failed to Add name '" + fieldSelectMultName + "' Select Multiple field");
			
			boolean setValues1 = fdp.setValuesToElement("1");
			boolean setValues2 = fdp.setValuesToElement("2");
			TestValidation.IsTrue((setValues1 && setValues2), 
								  "Successfully SET values - 1 and 2 to Select Multiple field", 
								  "Failed to set values - 1 and 2 to Select Multiple field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsTrue(releaseForm, 
							      "SHOULD release form - " + checkFormName, 
								  "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + checkFormName, 
								  "Failed to open form - " + checkFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Check Form Verify Numeric field functionality (Negative testing)")
	public void TestCase_5233() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkNegtv_Numeric");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectChk = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectChk, 
								  "SELECTED Check form type successfully", 
								  "Failed to Select Check form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(checkFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + checkFormName, 
					  			  "Failed to Set form name as " + checkFormName);
			
			//Add Numeric Field 
			WebElement NumericFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericFieldType, fdp.FormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Numeric field successfully without name", 
								  "Failed to Add Numeric field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement NumericFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.NUMERIC);
			boolean verifyFieldErrorMsg = NumericFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Numeric error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Numeric error message");
			
			boolean setNumericFldName = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, fieldNumericName);	
			TestValidation.IsTrue(setNumericFldName, 
								  "ADDED name '" + fieldNumericName + "' to Numeric field successfully", 
								  "Failed to Add name '" + fieldNumericName + "' Numeric field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + checkFormName, 
								   "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + checkFormName, 
								  "Failed to open form - " + checkFormName);
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpha@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldNumericName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field " + fieldNumericName, 
								  "Failed to apply value to field " + fieldNumericName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Check Form - Verify Date field functionality (Negative testing)")
	public void TestCase_5234() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkNegtv_Date");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectChk = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectChk, 
								  "SELECTED Check form type successfully", 
								  "Failed to Select Check form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(checkFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + checkFormName, 
					  			  "Failed to Set form name as " + checkFormName);
			
			//Add Date Field 
			WebElement DateFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.DATE);
			controlActions.dragdrop(DateFieldType, fdp.FormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Date field successfully without name", 
								  "Failed to Add Date field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement DateFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.DATE);
			boolean verifyFieldErrorMsg = DateFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Date error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Date error message");
			
			boolean setDateFldName = fdp.setTextBoxValue(FIELD_TYPES.DATE, fieldDateName);	
			TestValidation.IsTrue(setDateFldName, 
								  "ADDED name '" + fieldDateName + "' to Date field successfully", 
								  "Failed to Add name '" + fieldDateName + "' Date field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + checkFormName, 
								   "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + checkFormName, 
								  "Failed to open form - " + checkFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldDateName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field " + fieldDateName, 
								  "Failed to apply value to field " + fieldDateName);
			
			String expectedInvalidPopupMsg = "Please enter a valid date";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Check Form - Verify Time field functionality (Negative testing)")
	public void TestCase_5235() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkNegtv_Time");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectChk = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectChk, 
								  "SELECTED Check form type successfully", 
								  "Failed to Select Check form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(checkFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + checkFormName, 
					  			  "Failed to Set form name as " + checkFormName);
			
			//Add Time Field 
			WebElement TimeFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.TIME);
			controlActions.dragdrop(TimeFieldType, fdp.FormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Time field successfully without name", 
								  "Failed to Add Time field");
		
			
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement TimeFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.TIME);
			boolean verifyFieldErrorMsg = TimeFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Time error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Time error message");
			
			boolean setTimeFldName = fdp.setTextBoxValue(FIELD_TYPES.TIME, fieldTime257CharsName);	
			TestValidation.IsTrue(setTimeFldName, 
								  "ADDED name '" + fieldTime257CharsName + "' to Time field successfully", 
								  "Failed to Add name '" + fieldTime257CharsName + "' Time field");
			
			boolean verifyTimeFldName = fdp.FormLvlFieldName.getText().equalsIgnoreCase(fieldTime255CharsName);	
			TestValidation.IsTrue(verifyTimeFldName, 
								  "VERFIFIED that added name got truncated to '" + fieldTime255CharsName + " as there is 255 character limit", 
								  "Failed to verify added name got truncated to 255 character limit or not");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + checkFormName, 
								   "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + checkFormName, 
								  "Failed to open form - " + checkFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldTime255CharsName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field - " + fieldTime255CharsName, 
								  "Failed to apply value to field - " + fieldTime255CharsName);
			
			String expectedInvalidPopupMsg = "Please enter a valid time";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Check Form - Verify Date and Time field functionality (Negative testing)")
	public void TestCase_5236() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkNegtv_DateTime");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectChk = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectChk, 
								  "SELECTED Check form type successfully", 
								  "Failed to Select Check form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(checkFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + checkFormName, 
					  			  "Failed to Set form name as " + checkFormName);
			
			//Add Time Field 
			WebElement DateTimeFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.DATE_TIME);
			controlActions.dragdrop(DateTimeFieldType, fdp.FormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Date Time field successfully without name", 
								  "Failed to Add Date Time field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement DateTimeFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.DATE_TIME);
			boolean verifyFieldErrorMsg = DateTimeFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Date Time error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Date Time error message");
			
			boolean setDateTimeFldName = fdp.setTextBoxValue(FIELD_TYPES.DATE_TIME, fieldDateTimeName);	
			TestValidation.IsTrue(setDateTimeFldName, 
								  "ADDED name '" + fieldDateTimeName + "' to Date Time field successfully", 
								  "Failed to Add name '" + fieldDateTimeName + "' Date Time field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + checkFormName, 
								   "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + checkFormName, 
								  "Failed to open form - " + checkFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldDateTimeName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field - " + fieldDateTimeName, 
								  "Failed to apply value to field - " + fieldDateTimeName);
			
			String expectedInvalidPopupMsg = "Please enter a valid date time";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Questionnaire  Form Elements - Verify Section Element functionality (Negative testing)")
	public void TestCase_38479() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QustNegtv_Sec");
			
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectQuest = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQuest, 
								  "SELECTED Questionnaire form type successfully", 
								  "Failed to Select Questionnaire form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(questFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + questFormName, 
					  			  "Failed to Set form name as " + questFormName);
			
			//Add Section to Header
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.HeaderLevel);
			WebElement DragSectionToHeader = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TEXT_AREA,
					"FIELDTYPENAME",ELEMENT_INPUT_TYPES.SECTION);
			TestValidation.IsTrue(DragSectionToHeader==null, 
		  			  			  "CANNOT drag and drop Section element to Header level", 
		  			  			  "Failed to verify whether drag and drop of Section element happened or not to Header level");
			
			//Add Section to Form
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			WebElement DragSectionToForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TEXT_AREA,
					"FIELDTYPENAME",ELEMENT_INPUT_TYPES.SECTION);
			boolean sectionDisplayed = controlActions.isElementDisplayed(DragSectionToForm);
			TestValidation.IsTrue(sectionDisplayed, 
					  			  "COULD drag and drop Section element to Form level", 
					  			  "Failed to verify whether drag and drop of Section element happened or not to Form level");
			
			String symbolsInName = "@#$%%^&!*";
			boolean setSectionFieldValue1 = fdp.setElementValueTextArea(ELEMENT_INPUT_TYPES.SECTION, symbolsInName);
			TestValidation.IsTrue(setSectionFieldValue1, 
					  			  "Section field SET as " + symbolsInName, 
					  			  "Failed to set Section field as " + symbolsInName);
			
			boolean saveForm = fdp.clickFormNameTxt();
			TestValidation.IsTrue(saveForm, 
								  "CLICKED on Form Name textbox successfully", 
								  "Failed to Click On Form Name textbox");
			
			String expectedInvlidFldNameMsg = "field name must contain at least one letter or number";
			boolean verifyInvlidFldNameMsg = fdp.ScsFrmPopupMsg.getText().contains(expectedInvlidFldNameMsg);
			TestValidation.IsTrue(verifyInvlidFldNameMsg, 
								  "VERIFIED error message '" + expectedInvlidFldNameMsg + "' displayed for Invalid field name", 
								  "Failed to verify error message displayed for Invalid field name");
			
			boolean clickOk = fdp.clickOkOnPopup();
			TestValidation.IsTrue(clickOk, 
								  "CLICKED on Ok button on popup successfully", 
								  "Failed to Click On Ok button on popup");
			
			DragSectionToForm.click();
			boolean setShortName = controlActions.clearAndSetText(fdp.HighlightedFldShrtnm, fieldSectionName);
			TestValidation.IsTrue(setShortName, 
					  			  "Section field's short name SET as " + setShortName, 
					  			  "Failed to set Section field's short name as " + setShortName);
			
			DragSectionToForm.clear();
			boolean setSectionFieldValue2 = fdp.setElementValueTextArea(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue2, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");

			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsFalse(releaseForm, 
							       "Should NOT release form - " + questFormName, 
								   "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			String expectedErrorMsg = fieldSectionName + "|No fields inside section";
			boolean verifyErrorMsg = fdp.verifyErrorMsgReleasePg(expectedErrorMsg);
			TestValidation.IsTrue(verifyErrorMsg, 
								  "VERIFIED error message '" + expectedErrorMsg + "' displayed on Release page", 
								  "Failed to verify error message displayed on Release page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Questionnaire Form- Select One functionality (Negative testing)")
	public void TestCase_38483() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QustNegtv_SltOne");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectQuest = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQuest, 
								  "SELECTED Questionnaire form type successfully", 
								  "Failed to Select Questionnaire form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(questFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + questFormName, 
					  			  "Failed to Set form name as " + questFormName);
			
			//Add Select One Field 
			WebElement SelectOneFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.SELECT_ONE);
			controlActions.dragdrop(SelectOneFieldType, fdp.FieldTypeDropAreaFormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Select One field successfully without name", 
								  "Failed to Add Select One field");
			
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement SelectOneFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.SELECT_ONE);
			boolean verifyFieldErrorMsg = SelectOneFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Select One error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Select One error message");
			
			boolean setSelectOneFldName = fdp.setElementValueTextArea(FIELD_TYPES.SELECT_ONE, fieldSelectOneName);	
			TestValidation.IsTrue(setSelectOneFldName, 
								  "ADDED name " + fieldSelectOneName + " to Select One field successfully", 
								  "Failed to Add name " + fieldSelectOneName + " Select One field");
			
			boolean setValues1 = fdp.setValuesToElement("1");
			boolean setValues2 = fdp.setValuesToElement("2");
			TestValidation.IsTrue((setValues1 && setValues2), 
								  "Successfully SET values - 1 and 2 to Select One field", 
								  "Failed to set values - 1 and 2 to Select One field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							      "SHOULD release form - " + questFormName, 
								  "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			// Workaround to ensure that on entering any value other than given Select One options, the textbox gets cleared 
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + questFormName, 
								  "Failed to open form - " + questFormName);
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("3");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldSelectOneName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field " + fieldSelectOneName, 
								  "Failed to apply value to field " + fieldSelectOneName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Questionnaire Form General - Verify Select Multiple functionality (Negative testing)")
	public void TestCase_38485() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QustNegtv_SltMult");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectQuest = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQuest, 
								  "SELECTED Questionnaire form type successfully", 
								  "Failed to Select Questionnaire form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(questFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + questFormName, 
					  			  "Failed to Set form name as " + questFormName);
			
			//Add Select Multiple Field 
			WebElement SelectMultFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.SELECT_MULTIPLE);
			controlActions.dragdrop(SelectMultFieldType, fdp.FieldTypeDropAreaFormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Select Multiple field successfully without name", 
								  "Failed to Add Select Multiple field");
			
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement SelectMultFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.SELECT_MULTIPLE);
			boolean verifyFieldErrorMsg = SelectMultFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Select Multiple error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Select Multiple error message");
			
			boolean setSelectMultFldName = fdp.setElementValueTextArea(FIELD_TYPES.SELECT_MULTIPLE, fieldSelectMultName);	
			TestValidation.IsTrue(setSelectMultFldName, 
								  "ADDED name " + fieldSelectMultName + " to Select Multiple field successfully", 
								  "Failed to Add name " + fieldSelectMultName + " Select Multiple field");
			
			boolean setValues1 = fdp.setValuesToElement("1");
			boolean setValues2 = fdp.setValuesToElement("2");
			TestValidation.IsTrue((setValues1 && setValues2), 
								  "Successfully SET values - 1 and 2 to Select Multiple field", 
								  "Failed to set values - 1 and 2 to Select Multiple field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							      "SHOULD release form - " + questFormName, 
								  "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			// Workaround to ensure that on entering any value other than given Select Multiple options, the textbox gets cleared 
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + questFormName, 
								  "Failed to open form - " + questFormName);
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("10");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldSelectMultName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field " + fieldSelectMultName, 
								  "Failed to apply value to field " + fieldSelectMultName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Questionnaire Form Verify Numeric field functionality (Negative testing)")
	public void TestCase_38487() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QustNegtv_Numeric");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectQuest = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQuest, 
								  "SELECTED Questionnaire form type successfully", 
								  "Failed to Select Questionnaire form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(questFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + questFormName, 
					  			  "Failed to Set form name as " + questFormName);
			
			//Add Numeric Field 
			WebElement NumericFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(NumericFieldType, fdp.FieldTypeDropAreaFormLevel);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Numeric field successfully without name", 
								  "Failed to Add Numeric field");
			
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement NumericFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.NUMERIC);
			boolean verifyFieldErrorMsg = NumericFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Numeric error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Numeric error message");
			
			boolean setNumericFldName = fdp.setElementValueTextArea(FIELD_TYPES.NUMERIC, fieldNumericName);	
			TestValidation.IsTrue(setNumericFldName, 
								  "ADDED name '" + fieldNumericName + "' to Numeric field successfully", 
								  "Failed to Add name '" + fieldNumericName + "' Numeric field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + questFormName, 
								   "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Questionnaire  Form - Verify Date field functionality (Negative testing)")
	public void TestCase_38489() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QustNegtv_Date");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectQuest = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQuest, 
								  "SELECTED Questionnaire form type successfully", 
								  "Failed to Select Questionnaire form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(questFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + questFormName, 
					  			  "Failed to Set form name as " + questFormName);
			
			//Add Date Field to Header
			WebElement DateFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.DATE);
			controlActions.dragdrop(DateFieldType, fdp.HeaderLevel);
			WebElement DragDateToHeader = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TXT,
					"FIELDTYPENAME",FIELD_TYPES.DATE);
			TestValidation.IsTrue(DragDateToHeader==null, 
					  			  "CANNOT drag and drop Date element to Header level", 
					  			  "Failed to verify whether drag and drop of Date element happened or not to Header level");
			
			//Add Date field to Form
			controlActions.dragdrop(DateFieldType, fdp.FieldTypeDropAreaFormLevel);
			WebElement DragDateToForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TEXT_AREA,
					"FIELDTYPENAME",FIELD_TYPES.DATE);
			boolean dateDisplayed = controlActions.isElementDisplayed(DragDateToForm);
			TestValidation.IsTrue(dateDisplayed, 
					  			  "COULD drag and drop Date element to Form level", 
					  			  "Failed to verify whether drag and drop of Date element happened or not to Form level");
			
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Date field successfully without name", 
								  "Failed to Add Date field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement DateFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.DATE);
			boolean verifyFieldErrorMsg = DateFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Date error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Date error message");
			
			boolean setDateFldName = fdp.setElementValueTextArea(FIELD_TYPES.DATE, fieldDateName);	
			TestValidation.IsTrue(setDateFldName, 
								  "ADDED name '" + fieldDateName + "' to Date field successfully", 
								  "Failed to Add name '" + fieldDateName + "' Date field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + questFormName, 
								   "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + questFormName, 
								  "Failed to open form - " + questFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldDateName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field " + fieldDateName, 
								  "Failed to apply value to field " + fieldDateName);
			
			String expectedInvalidPopupMsg = "Please enter a valid date";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Questionnaire Form - Verify Time field functionality (Negative testing)")
	public void TestCase_38491() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QustNegtv_Time");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectQuest = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQuest, 
								  "SELECTED Questionnaire form type successfully", 
								  "Failed to Select Questionnaire form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(questFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + questFormName, 
					  			  "Failed to Set form name as " + questFormName);
			
			//Add Time Field to Header
			WebElement TimeFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.TIME);
			controlActions.dragdrop(TimeFieldType, fdp.HeaderLevel);
			WebElement DragTimeToHeader = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TXT,
					"FIELDTYPENAME",FIELD_TYPES.TIME);
			TestValidation.IsTrue(DragTimeToHeader==null, 
					  			  "CANNOT drag and drop Time element to Header level", 
					  			  "Failed to verify whether drag and drop of Time element happened or not to Header level");
			
			//Add Time field to Form
			controlActions.dragdrop(TimeFieldType, fdp.FieldTypeDropAreaFormLevel);
			WebElement DragTimeToForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TEXT_AREA,
					"FIELDTYPENAME",FIELD_TYPES.TIME);
			boolean timeDisplayed = controlActions.isElementDisplayed(DragTimeToForm);
			TestValidation.IsTrue(timeDisplayed, 
					  			  "COULD drag and drop Time element to Form level", 
					  			  "Failed to verify whether drag and drop of Time element happened or not to Form level");
			
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Time field successfully without name", 
								  "Failed to Add Time field");
			
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement TimeFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.TIME);
			boolean verifyFieldErrorMsg = TimeFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Time error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Time error message");
			
			boolean setTimeFldName = fdp.setElementValueTextArea(FIELD_TYPES.TIME, fieldTime257CharsName);	
			TestValidation.IsTrue(setTimeFldName, 
								  "ADDED name '" + fieldTime257CharsName + "' to Time field successfully", 
								  "Failed to Add name '" + fieldTime257CharsName + "' Time field");
			
			boolean verifyTimeFldName = fdp.FormLvlFieldName.getText().equalsIgnoreCase(fieldTime255CharsName);	
			TestValidation.IsTrue(verifyTimeFldName, 
								  "VERFIFIED that added name got truncated to '" + fieldTime255CharsName + " as there is 255 character limit", 
								  "Failed to verify added name got truncated to 255 character limit or not");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + questFormName, 
								   "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + questFormName, 
								  "Failed to open form - " + questFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldTime255CharsName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field - " + fieldTime255CharsName, 
								  "Failed to apply value to field - " + fieldTime255CharsName);
			
			String expectedInvalidPopupMsg = "Please enter a valid time";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Questionnaire Form - Verify Date and Time field functionality (Negative testing)")
	public void TestCase_38493() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QustNegtv_DateTime");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectQuest = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQuest, 
								  "SELECTED Questionnaire form type successfully", 
								  "Failed to Select Questionnaire form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(questFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + questFormName, 
					  			  "Failed to Set form name as " + questFormName);
			
			//Add Date Time Field to Header
			WebElement DateTimeFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
					"FIELDTYPE",FIELD_TYPES.DATE_TIME);
			controlActions.dragdrop(DateTimeFieldType, fdp.HeaderLevel);
			WebElement DragDateTimeToHeader = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TXT,
					"FIELDTYPENAME",FIELD_TYPES.DATE_TIME);
			TestValidation.IsTrue(DragDateTimeToHeader==null, 
					  			  "CANNOT drag and drop Date Time element to Header level", 
					  			  "Failed to verify whether drag and drop of Date Time element happened or not to Header level");
			
			//Add Date Time field to Form
			controlActions.dragdrop(DateTimeFieldType, fdp.FieldTypeDropAreaFormLevel);
			WebElement DragDateTimeToForm = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_NAME_TEXT_AREA,
					"FIELDTYPENAME",FIELD_TYPES.DATE_TIME);
			boolean datetimeDisplayed = controlActions.isElementDisplayed(DragDateTimeToForm);
			TestValidation.IsTrue(datetimeDisplayed, 
					  			  "COULD drag and drop Date Time element to Form level", 
					  			  "Failed to verify whether drag and drop of Date Time element happened or not to Form level");
			
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(saveForm, 
								  "ADDED Date Time field successfully without name", 
								  "Failed to Add Date Time field");
			
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement DateTimeFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.DATE_TIME);
			boolean verifyFieldErrorMsg = DateTimeFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Date Time error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Date Time error message");
			
			boolean setDateTimeFldName = fdp.setElementValueTextArea(FIELD_TYPES.DATE_TIME, fieldDateTimeName);	
			TestValidation.IsTrue(setDateTimeFldName, 
								  "ADDED name '" + fieldDateTimeName + "' to Date Time field successfully", 
								  "Failed to Add name '" + fieldDateTimeName + "' Date Time field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + questFormName, 
								   "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + questFormName, 
								  "Failed to open form - " + questFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldDateTimeName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field - " + fieldDateTimeName, 
								  "Failed to apply value to field - " + fieldDateTimeName);
			
			String expectedInvalidPopupMsg = "Please enter a valid date time";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Audit form Form Elements - Verify Section Element functionality (Negative testing)")
	public void TestCase_38480() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtNegtv_Section");
			
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
		
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsFalse(releaseForm, 
							       "Should NOT release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			String expectedErrorMsg = fieldSectionName + "|No fields inside section";
			boolean verifyErrorMsg = fdp.verifyErrorMsgReleasePg(expectedErrorMsg);
			TestValidation.IsTrue(verifyErrorMsg, 
								  "VERIFIED error message '" + expectedErrorMsg + "' displayed on Release page", 
								  "Failed to verify error message displayed on Release page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Audit Form- Select One functionality (Negative testing)")
	public void TestCase_38484() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtNegtv_SltOne");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Select One Field 
			boolean addSelectOneFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.SELECT_ONE, fieldSectionName);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(addSelectOneFieldInSection && saveForm, 
								  "ADDED Select One field successfully without name", 
								  "Failed to Add Select One field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement SelectOneFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.SELECT_ONE);
			boolean verifyFieldErrorMsg = SelectOneFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Select One error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Select One error message");
			
			boolean setSelectOneFldName = fdp.setTextBoxValue(FIELD_TYPES.SELECT_ONE, fieldSelectOneName);	
			TestValidation.IsTrue(setSelectOneFldName, 
								  "ADDED name " + fieldSelectOneName + " to Select One field successfully", 
								  "Failed to Add name " + fieldSelectOneName + " Select One field");
			
			boolean setValues1 = fdp.setValuesToElement("1");
			boolean setValues2 = fdp.setValuesToElement("2");
			TestValidation.IsTrue((setValues1 && setValues2), 
								  "Successfully SET values - 1 and 2 to Select One field", 
								  "Failed to set values - 1 and 2 to Select One field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							      "SHOULD release form - " + auditFormName, 
								  "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + auditFormName, 
								  "Failed to open form - " + auditFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Audit Form General - Verify Select Multiple functionality (Negative testing)")
	public void TestCase_38486() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtNegtv_SltMult");
			
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Select Multiple Field 
			boolean addSelectMultFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.SELECT_MULTIPLE, fieldSectionName);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(addSelectMultFieldInSection && saveForm, 
								  "ADDED Select Multiple field successfully without name", 
								  "Failed to Add Select Multiple field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement SelectMultFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.SELECT_MULTIPLE);
			boolean verifyFieldErrorMsg = SelectMultFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Select Multiple error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Select Multiple error message");
			
			boolean setSelectMultipleFldName = fdp.setTextBoxValue(FIELD_TYPES.SELECT_MULTIPLE, fieldSelectMultName);	
			TestValidation.IsTrue(setSelectMultipleFldName, 
								  "ADDED name '" + fieldSelectMultName + "' to Select Multiple field successfully", 
								  "Failed to Add name '" + fieldSelectMultName + "' Select Multiple field");
			
			boolean setValues1 = fdp.setValuesToElement("1");
			boolean setValues2 = fdp.setValuesToElement("2");
			TestValidation.IsTrue((setValues1 && setValues2), 
								  "Successfully SET values - 1 and 2 to Select Multiple field", 
								  "Failed to set values - 1 and 2 to Select Multiple field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							      "SHOULD release form - " + auditFormName, 
								  "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + auditFormName, 
								  "Failed to open form - " + auditFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Audit Form Verify Numeric field functionality (Negative testing)")
	public void TestCase_38488() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtNegtv_Numeric");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Numeric Field 
			boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.NUMERIC, fieldSectionName);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(addNumericFieldInSection && saveForm, 
								  "ADDED Numeric field successfully without name", 
								  "Failed to Add Numeric field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement NumericFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.NUMERIC);
			boolean verifyFieldErrorMsg = NumericFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Numeric error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Numeric error message");
			
			boolean setNumericFldName = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, fieldNumericName);	
			TestValidation.IsTrue(setNumericFldName, 
								  "ADDED name '" + fieldNumericName + "' to Numeric field successfully", 
								  "Failed to Add name '" + fieldNumericName + "' Numeric field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + auditFormName, 
								  "Failed to open form - " + auditFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Audit Form - Verify Date field functionality (Negative testing)")
	public void TestCase_38490() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtNegtv_Date");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Date Field 
			boolean addDateFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.DATE, fieldSectionName);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(addDateFieldInSection && saveForm, 
								  "ADDED Date field successfully without name", 
								  "Failed to Add Date field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement DateFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.DATE);
			boolean verifyFieldErrorMsg = DateFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Date error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Date error message");
			
			boolean setDateFldName = fdp.setTextBoxValue(FIELD_TYPES.DATE, fieldDateName);	
			TestValidation.IsTrue(setDateFldName, 
								  "ADDED name '" + fieldDateName + "' to Date field successfully", 
								  "Failed to Add name '" + fieldDateName + "' Date field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + auditFormName, 
								  "Failed to open form - " + auditFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldDateName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field " + fieldDateName, 
								  "Failed to apply value to field " + fieldDateName);
			
			String expectedInvalidPopupMsg = "Please enter a valid date";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Audit Form - Verify Time field functionality (Negative testing)")
	public void TestCase_38492() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtNegtv_Time");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.CHECK);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Time Field 
			boolean addTimeFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.TIME, fieldSectionName);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(addTimeFieldInSection && saveForm, 
								  "ADDED Time field successfully without name", 
								  "Failed to Add Time field");
			
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement TimeFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.TIME);
			boolean verifyFieldErrorMsg = TimeFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Time error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Time error message");
			
			boolean setTimeFldName = fdp.setTextBoxValue(FIELD_TYPES.TIME, fieldTime257CharsName);	
			TestValidation.IsTrue(setTimeFldName, 
								  "ADDED name '" + fieldTime257CharsName + "' to Time field successfully", 
								  "Failed to Add name '" + fieldTime257CharsName + "' Time field");
			
			boolean verifyTimeFldName = fdp.SectionFrmLvlFieldName.getText().equalsIgnoreCase(fieldTime255CharsName);	
			TestValidation.IsTrue(verifyTimeFldName, 
								  "VERFIFIED that added name got truncated to '" + fieldTime255CharsName + " as there is 255 character limit", 
								  "Failed to verify added name got truncated to 255 character limit or not");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + auditFormName, 
								  "Failed to open form - " + auditFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldTime255CharsName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field - " + fieldTime255CharsName, 
								  "Failed to apply value to field - " + fieldTime255CharsName);
			
			String expectedInvalidPopupMsg = "Please enter a valid time";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Audit Form - Verify Date and Time field functionality (Negative testing)")
	public void TestCase_38494() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtNegtv_DateTime");

			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED Audit form type successfully", 
								  "Failed to Select Audit form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(auditFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + auditFormName, 
					  			  "Failed to Set form name as " + auditFormName);
			
			//Add Section to Form
			WebElement SectionElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.SECTION);
			controlActions.dragdrop(SectionElement, fdp.FieldTypeDropAreaFormLevel);
			boolean setSectionFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.SECTION, fieldSectionName);
			TestValidation.IsTrue(setSectionFieldValue, 
								  "ADDED Section " + fieldSectionName + " successfully", 
								  "Failed to Add Section " + fieldSectionName + " successfully");
			
			//Add Date Time Field 
			boolean addDateTimeFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.DATE_TIME, fieldSectionName);
			boolean saveForm = fdp.clickFormSaveButton();
			TestValidation.IsTrue(addDateTimeFieldInSection && saveForm, 
								  "ADDED Date Time field successfully without name", 
								  "Failed to Add Date Time field");
		
			String fieldNameExpectErrMsg = "You must enter a Field Name before proceeding";
			WebElement DateTimeFieldTypeErrMsg = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE_ERR_MSG,
					"TYPEOFFIELD",FIELD_INPUT_TYPES.DATE_TIME);
			boolean verifyFieldErrorMsg = DateTimeFieldTypeErrMsg.getText().contains(fieldNameExpectErrMsg);
			TestValidation.IsTrue(verifyFieldErrorMsg, 
								  "VERIFIED Date Time error message '" + fieldNameExpectErrMsg + "'", 
								  "Failed to verify displayed Date Time error message");
			
			boolean setDateTimeFldName = fdp.setTextBoxValue(FIELD_TYPES.DATE_TIME, fieldDateTimeName);	
			TestValidation.IsTrue(setDateTimeFldName, 
								  "ADDED name '" + fieldDateTimeName + "' to Date Time field successfully", 
								  "Failed to Add name '" + fieldDateTimeName + "' Date Time field");
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
			
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToforms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS, FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToforms, 
								  "For customer category, NAVIGATED to FSQABrowser > Forms tab", 
								  "Failed to navigate to FSQABrowser > Forms tab");
					
			boolean applyFilterAndOpenForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(applyFilterAndOpenForm, 
								  "OPENED form - " + auditFormName, 
								  "Failed to open form - " + auditFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean clickSubmit1 = ffp.clickSubmitFormBtn();
			TestValidation.IsTrue(clickSubmit1, 
								  "CLICKED on Submit button", 
								  "Failed to click on Submit button");
			
			String popupExpectedMsg = "Please complete all required fields";
			boolean verifyPopupMsg = ffp.SubmitFormPopupMsg.getText().contains(popupExpectedMsg);
			TestValidation.IsTrue(verifyPopupMsg, 
								  "VERIFIED error message '" + popupExpectedMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
			boolean clickPopupOkBtn = ffp.clickOkPopupSubmitFormBtn();
			TestValidation.IsTrue(clickPopupOkBtn, 
								  "CLICKED on Submit Form's Ok popup button", 
								  "Failed to click on Submit Form's Ok popup button");
			
			List<String> submitData = new ArrayList<String>();
			submitData.add("alpaw233@#$%!()_+=&");
			
			FormOperations fo = new FormOperations(driver);
			boolean fillFormData = fo.fillData(fieldDateTimeName, submitData, null, false);
			TestValidation.IsTrue(fillFormData, 
								  "APPLIED value " + submitData + " to field - " + fieldDateTimeName, 
								  "Failed to apply value to field - " + fieldDateTimeName);
			
			String expectedInvalidPopupMsg = "Please enter a valid date time";
			boolean verifyDatePopupMsg = ffp.SubmitFrmPopupMsg.getText().contains(expectedInvalidPopupMsg);
			TestValidation.IsTrue(verifyDatePopupMsg, 
								  "VERIFIED error message '" + expectedInvalidPopupMsg + "' on Submit Form page", 
								  "Failed to verify error message displayed on Submit Form page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description=" Audit  - Verify user is able to release form with blank field group ")
	public void TestCase_38477() {

		try {

			String sectionName = "Section";
			String groupName = "Question Group";
			String adtFormName = CommonMethods.dynamicText("AdtNegtv_Group");
			
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectAdt = fdp.selectFormType(FORMTYPES.AUDIT);
			TestValidation.IsTrue(selectAdt, 
								  "SELECTED AUDIT form type successfully", 
								  "Failed to Select AUDIT form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(adtFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + adtFormName, 
					  			  "Failed to Set form name as " + adtFormName);
			
			
			WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
			controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
			
			boolean setSectionName = fdp.setFieldName("Section",sectionName);
			TestValidation.IsTrue(setSectionName, 
					"Setted 'Section' name",  
					"Could NOT set 'Section' name");

			// Add Section
			boolean addQuesGroupInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.QUESTION_GROUP, sectionName);
			TestValidation.IsTrue(addQuesGroupInSection, 
					"Added 'Numeric' field in 'Section'", 
					"Could NOT add 'Numeric' field in 'Section'");

			// Add Group in that section
			boolean setQuesGroupName = fdp.setFieldName(FORM_ELEMENTS.QUESTION_GROUP,groupName);
			TestValidation.IsTrue(setQuesGroupName, 
					"Setted 'Numeric' field name", 
					"Could NOT set 'Numeric' field name");
		
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(adtFormName);
			TestValidation.IsFalse(releaseForm, 
							       "Should NOT release form - " + adtFormName, 
								   "Failed since uncertain that the form " + adtFormName + " did/did not release'");
			
			String expectedErrorMsg = sectionName+" > "+groupName + "|No fields inside group";
			boolean verifyErrorMsg = fdp.verifyErrorMsgReleasePg(expectedErrorMsg);
			TestValidation.IsTrue(verifyErrorMsg, 
								  "VERIFIED error message '" + expectedErrorMsg + "' displayed on Release page", 
								  "Failed to verify error message displayed on Release page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description=" Questionnaire Form  - Verify user is able to release form with blank field group ")
	public void TestCase_38478() {

		try {
			
			String qstnFormName = CommonMethods.dynamicText("QstnNegtv_Group");
			
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();
			boolean selectQstn = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQstn, 
								  "SELECTED QUESTIONNAIRE form type successfully", 
								  "Failed to Select QUESTIONNAIRE form type");
			
			//Selection of resource
			boolean selectResource = fdp.selectResource(resourceType,resourceCategoryValue,resourceInstanceValue);
			TestValidation.IsTrue(selectResource, 
								  "SELECTED Resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "' successfully", 
								  "Failed to Select resource '" + resourceCategoryValue + " > " + resourceInstanceValue + "'");
	
			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, FORM_NEXT_PAGE.DESIGN_FORM);
			TestValidation.IsTrue(clickOnNextButton, 
								  "CLICKED On Next Button successfully", 
								  "Failed to Click On Next Button");
	
			boolean setName = fdp.setFormName(qstnFormName);
			TestValidation.IsTrue(setName, 
					  			  "Form name SET as " + qstnFormName, 
					  			  "Failed to Set form name as " + qstnFormName);
			
			//Add Group to Form
			WebElement GroupElement = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
					"FORMELEMENT",ELEMENT_INPUT_TYPES.FIELD_GROUP_TXT);
			controlActions.dragdrop(GroupElement, fdp.FormLevel);
			boolean setGroupFieldValue = fdp.setFieldName(ELEMENT_INPUT_TYPES.FIELD_GROUP_TXT, fieldGroupName);		
			TestValidation.IsTrue(setGroupFieldValue, 
								  "ADDED Field Group " + fieldGroupName + " successfully", 
								  "Failed to Add Field Group " + fieldGroupName + " successfully");
		
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(qstnFormName);
			TestValidation.IsFalse(releaseForm, 
							       "Should NOT release form - " + qstnFormName, 
								   "Failed since uncertain that the form " + qstnFormName + " did/did not release'");
			
			String expectedErrorMsg = fieldGroupName + "|No fields inside group";
			boolean verifyErrorMsg = fdp.verifyErrorMsgReleasePg(expectedErrorMsg);
			TestValidation.IsTrue(verifyErrorMsg, 
								  "VERIFIED error message '" + expectedErrorMsg + "' displayed on Release page", 
								  "Failed to verify error message displayed on Release page");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
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

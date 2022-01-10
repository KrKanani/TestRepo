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
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserDocumentsPage.FLD_OTHER_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPageLocator;
import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FIELD_OTHER_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.AGGREGATE_FUNCTION;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_INPUT_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_SETTINGS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_NEXT_PAGE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.ControlActions.WINDOW_TAB;
import com.project.utilities.DateTime;

public class TCG_REG_FormDesigner_FieldsFunc extends TestBase{
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
	
	
	public static String fieldParagraphName = "Test Paragraph";
	public static String fieldFreeTxtName = "Test Free Text";
	public static String fieldSingleLineTxtName = "Test Single Line Text";
	public static String fieldIdentifierName = "Test Identifier";
	public static String fieldSelectOneName = "Test Select One";
	public static String fieldSelectMultName = "Test Select Multiple";
	public static String fieldAggregateName = "Test Aggregate";
	public static String fieldNumericName = "Test Numeric";
	public static String fieldDateName = "Test Date";
	public static String fieldTimeName = "Test Time";
	public static String fieldDateTimeName = "Test Date Time";
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue;
	public static String locationInstanceValue;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		resourceCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		resourceInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		
		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {
			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			// ------------------------------------------------------------------------------------------------
			// API - Location & Resource Creation and Linking
			List<String> userList = Arrays.asList(adminUsername);

			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

			HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
			resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
					RESOURCE_TYPES.CUSTOMERS);

			// ------------------------------------------------------------------------------------------------
			// Supporting WEB Application code starts here
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
		}
		else if(executionMode.equalsIgnoreCase(ExecutionMode.UI)) {
			// ------------------------------------------------------------------------------------------------
			// Only WEB Application code starts here

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
			ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
			if(!rdp.createCategory(categories)) {
				TCGFailureMsg = "Could NOT create Resource categories";
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
		else {
			TCGFailureMsg = "Improper Execution Mode is set - " + executionMode;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	}

	@Test(description="Form Designer can save / update Field Properties on Settings tab whenever a Field is selected (feature 326)")
	public void TestCase_5217() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtFrm_AllwCorrectn");
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
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
			
			// Add Numeric field to Section
			boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.NUMERIC, fieldSectionName);
			TestValidation.IsTrue(addNumericFieldInSection, 
								  "ADDED 'Numeric' field to 'Section' " + fieldSectionName, 
								  "Failed to Add 'Numeric' field to 'Section' " + fieldSectionName);

			boolean setNumericFldName = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, fieldNumericName);	
			TestValidation.IsTrue(setNumericFldName, 
								  "ADDED name '" + fieldNumericName + "' to Numeric field successfully", 
								  "Failed to Add name '" + fieldNumericName + "' Numeric field");
			
			boolean clickSettings = fdp.clickSettingsTab();
			TestValidation.IsTrue(clickSettings, 
								  "CLICKED on Settings tab for Numeric field " + fieldNumericName, 
								  "Failed to Click On Settings tab for Numeric field " + fieldNumericName);
			
			// IS_REQUIRED
			WebElement IsRequiredOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.IS_REQUIRED);
			boolean verifyIsRequiredOffLbl = IsRequiredOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyIsRequiredOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.IS_REQUIRED + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.IS_REQUIRED + "' Off label/button is displayed or not");
			
			WebElement IsRequiredOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.IS_REQUIRED);
			boolean verifyIsRequiredOnLbl = IsRequiredOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyIsRequiredOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.IS_REQUIRED + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.IS_REQUIRED + "' On label/button is displayed or not");
			
			// ALLOW_COMMENTS
			WebElement AllowCommentsOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.ALLOW_COMMENTS);
			boolean verifyAllowCommentsOffLbl = AllowCommentsOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyAllowCommentsOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.ALLOW_COMMENTS + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.ALLOW_COMMENTS + "' Off label/button is displayed or not");
			
			WebElement AllowCommentsOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.ALLOW_COMMENTS);
			boolean verifyAllowCommentsOnLbl = AllowCommentsOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyAllowCommentsOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.ALLOW_COMMENTS + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.ALLOW_COMMENTS + "' On label/button is displayed or not");
				
			// ALLOW_ATTACHMENTS
			WebElement AllowAttchmntsOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.ALLOW_ATTACHMENTS);
			boolean verifyAllowAttchmntsOffLbl = AllowAttchmntsOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyAllowAttchmntsOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.ALLOW_ATTACHMENTS + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.ALLOW_ATTACHMENTS + "' Off label/button is displayed or not");
			
			WebElement AllowAttchmntsOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.ALLOW_ATTACHMENTS);
			boolean verifyAllowAttchmntsOnLbl = AllowAttchmntsOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyAllowAttchmntsOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.ALLOW_ATTACHMENTS + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.ALLOW_ATTACHMENTS + "' On label/button is displayed or not");
				
			// SHOW_ON_COA
			WebElement ShowOnCOAOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_ON_COA);
			boolean verifyShowOnCOAOffLbl = ShowOnCOAOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyShowOnCOAOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.SHOW_ON_COA + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.SHOW_ON_COA + "' Off label/button is displayed or not");
			
			WebElement ShowOnCOAOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_ON_COA);
			boolean verifyShowOnCOAOnLbl = ShowOnCOAOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyShowOnCOAOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.SHOW_ON_COA + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.SHOW_ON_COA + "' On label/button is displayed or not");
			
			// SHOW_HINT
			WebElement ShowHintOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_HINT);
			boolean verifyShowHintOffLbl = ShowHintOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyShowHintOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.SHOW_HINT + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.SHOW_HINT + "' Off label/button is displayed or not");
			
			WebElement ShowHintOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_HINT);
			boolean verifyShowHintOnLbl = ShowHintOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyShowHintOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.SHOW_HINT + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.SHOW_HINT + "' On label/button is displayed or not");
			
			// SHOW_MINMAX
			WebElement ShowMinMaxOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_MINMAX);
			boolean verifyShowMinMaxOffLbl = ShowMinMaxOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyShowMinMaxOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.SHOW_MINMAX + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.SHOW_MINMAX + "' Off label/button is displayed or not");
			
			WebElement ShowMinMaxOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_MINMAX);
			boolean verifyShowMinMaxOnLbl = ShowMinMaxOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyShowMinMaxOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.SHOW_MINMAX + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.SHOW_MINMAX + "' On label/button is displayed or not");
			
			// SHOW_TARGET
			WebElement ShowTargetOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_TARGET);
			boolean verifyShowTargetOffLbl = ShowTargetOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyShowTargetOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.SHOW_TARGET + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.SHOW_TARGET + "' Off label/button is displayed or not");
			
			WebElement ShowTargetOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_TARGET);
			boolean verifyShowTargetOnLbl = ShowTargetOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyShowTargetOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.SHOW_TARGET + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.SHOW_TARGET + "' On label/button is displayed or not");
				
			// FAILS_CHECK
			WebElement FailsCheckOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.FAILS_AUDIT);
			boolean verifyFailsCheckOffLbl = FailsCheckOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyFailsCheckOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.FAILS_AUDIT + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.FAILS_AUDIT + "' Off label/button is displayed or not");
			
			WebElement FailsCheckOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.FAILS_AUDIT);
			boolean verifyFailsCheckOnLbl = FailsCheckOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyFailsCheckOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.FAILS_AUDIT + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.FAILS_AUDIT + "' On label/button is displayed or not");

			// TEMP_PROBE
			WebElement TempProbeOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.TEMP_PROBE);
			boolean verifyTempProbeOffLbl = TempProbeOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyTempProbeOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.TEMP_PROBE + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.TEMP_PROBE + "' Off label/button is displayed or not");
			
			WebElement TempProbeOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.TEMP_PROBE);
			boolean verifyTempProbeOnLbl = TempProbeOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyTempProbeOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.TEMP_PROBE + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.TEMP_PROBE + "' On label/button is displayed or not");
			
			// CARRYOVER_FIELD
			WebElement CarryovrFldOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.CARRYOVER_FIELD);
			boolean verifyCarryovrFldOffLbl = CarryovrFldOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyCarryovrFldOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.CARRYOVER_FIELD + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.CARRYOVER_FIELD + "' Off label/button is displayed or not");
			
			WebElement CarryovrFldOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.CARRYOVER_FIELD);
			boolean verifyCarryovrFldOnLbl = CarryovrFldOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyCarryovrFldOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.CARRYOVER_FIELD + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.CARRYOVER_FIELD + "' On label/button is displayed or not");

			// ALLOW_CORRECTION
			WebElement AllowCorrctnOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.ALLOW_CORRECTION);
			boolean verifyAllowCorrctnOffLbl = AllowCorrctnOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyAllowCorrctnOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.ALLOW_CORRECTION + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.ALLOW_CORRECTION + "' Off label/button is displayed or not");
			
			WebElement AllowCorrctnOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.ALLOW_CORRECTION);
			boolean verifyAllowCorrctnOnLbl = AllowCorrctnOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyAllowCorrctnOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.ALLOW_CORRECTION + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.ALLOW_CORRECTION + "' On label/button is displayed or not");
			
			controlActions.perform_clickElement_ByElement(AllowCorrctnOnLbl);
			boolean verifyPredefinedBtn = fdp.PredefinedCorrectionAddBtn.isDisplayed();
			TestValidation.IsTrue(verifyPredefinedBtn, 
								  "VERFIED Add New button is displayed for 'Predefined Corrections'", 
								  "Failed to Verify whether Add New button is/is not displayed for 'Predefined Corrections'");

			// ALLOW_CORRECTION
			WebElement MrkReslvdOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.MARK_RESOLVED);
			boolean verifyMrkReslvdOffLbl = MrkReslvdOffLbl.isDisplayed();
			TestValidation.IsTrue(verifyMrkReslvdOffLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.MARK_RESOLVED + "' Off label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.MARK_RESOLVED + "' Off label/button is displayed or not");
			
			WebElement MrkReslvdOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.MARK_RESOLVED);
			boolean verifyMrkReslvdOnLbl = MrkReslvdOnLbl.isDisplayed();
			TestValidation.IsTrue(verifyMrkReslvdOnLbl, 
								  "VERIFIED for setting '" + FIELD_SETTINGS.MARK_RESOLVED + "' On label/button is displayed", 
								  "Failed to Verify for setting '" + FIELD_SETTINGS.MARK_RESOLVED + "' On label/button is displayed or not");

			boolean setComplianceForNumericField = fdp.setComplianceForNumericAggragateField(fieldNumericName, "1", "3", "5", null);
			TestValidation.IsTrue(setComplianceForNumericField, 
								  "SET Compliance For Numeric Field - " + fieldNumericName + " successfully", 
								  "Failed to Set Compliance For Numeric Field - " + fieldNumericName);
			
			boolean nextToReleaseForm1 = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm1, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm1 = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm1, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			// Verify compliance while submitting
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms1 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms1, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(openForm1, 
					 			  "OPENED form - " + auditFormName, 
					 			  "Failed to open form - " + auditFormName);

			HashMap<String, List<String>> fillData1 = new HashMap<String, List<String>>();
			fillData1.put(fieldNumericName,Arrays.asList("15"));
			
			//Field Property objects
			FieldProperties fp = new FieldProperties();
			fp.fieldName = fieldNumericName;
			fp.allowCorrectionsCheck = true;
			fp.allowCorrectionsValue = "Test Value";
			fp.complianceStatusCheck = true;
			fp.complianceStatus = "Non-Compliant";

			//Form object
			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillData1;
			//Field property assignment  
			fd.fieldProperties = Arrays.asList(fp);

			FormOperations fo = new FormOperations(driver);
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + auditFormName, 
								  "Failed to submit form" + auditFormName);
			
			// Edit form and set Allow Correction as off
			boolean editForm = fbp.editSelectForm(auditFormName);
			TestValidation.IsTrue(editForm, 
								  "OPENED form in edit mode", 
								  "Failed to Open form in edit mode");
			
			boolean selectField = fdp.selectField(fieldNumericName);
			TestValidation.IsTrue(selectField, 
								  "SELECTED field successfully - " + fieldNumericName, 
								  "Failed to Select field " + fieldNumericName);
			
			boolean updateSettings = fdp.setSettingsProperty(fdp.AllowCorrectionsOffBtn,FIELD_SETTINGS.ALLOW_CORRECTION,"OFF");
			TestValidation.IsTrue(updateSettings, 
								  "UPDATED numeric field's " + FIELD_SETTINGS.ALLOW_CORRECTION + " property to Off", 
								  "Failed to update numeric field's " + FIELD_SETTINGS.ALLOW_CORRECTION + " property to Off");
			
			boolean nextToReleaseForm2 = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm2, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm2 = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm2, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			// Verify no correction textbox displayed on compliance failing
			fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms2 = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms2, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm2 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(openForm2, 
					 			  "OPENED form - " + auditFormName, 
					 			  "Failed to open form - " + auditFormName);
			
			boolean setNumField = fo.setInputFieldValue(fieldNumericName, Arrays.asList("20","20"), null);
			TestValidation.IsTrue(setNumField, 
								  "Value set for Numeric field as 20",
								  "Failed to Set value for Numeric field as 20");
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean verifyNonComplnce = ffp.NonCompliance.isDisplayed();
			boolean verifyCorrctnsTxa = controlActions.performGetElementByXPath(FSQABrowserFormsPageLocators.EXIST_CORRECTION_TXA)==null;
			TestValidation.IsTrue(verifyNonComplnce && verifyCorrctnsTxa, 
								  "VERIFIED for Numeric field, Non Compliance found and no Correction textarea found",
								  "Failed to verify Numeric field's Non Compliance and Correction settings");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Check Form - Validate Carry over fields Setting functionality for all 3 form types and Validate Submit and repeat functionality")
	public void TestCase_37699() {

		try {
			
			String carryOverValue = "200";
			String checkFormName = CommonMethods.dynamicText("ChkFrm_CarryOvr");
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
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
			boolean setNumericFldName = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, fieldNumericName);	
			TestValidation.IsTrue(setNumericFldName, 
								  "ADDED name '" + fieldNumericName + "' to Numeric field successfully", 
								  "Failed to Add name '" + fieldNumericName + "' Numeric field");
			
			boolean setCarryOverForNumericField = fdp.carryoverField();
			TestValidation.IsTrue(setCarryOverForNumericField, 
								  "SET Carry Over setting For Numeric Field - " + fieldNumericName + " successfully", 
								  "Failed to Set Carry over setting For Numeric Field - " + fieldNumericName);
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + checkFormName, 
								   "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			// Verify carry over functionality while submitting
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(openForm, 
					 			  "OPENED form - " + checkFormName, 
					 			  "Failed to open form - " + checkFormName);

			FormOperations fo = new FormOperations(driver);
			boolean setNumField = fo.setInputFieldValue(fieldNumericName, Arrays.asList(carryOverValue), null);
			TestValidation.IsTrue(setNumField, 
								  "Value set for Numeric field as " + carryOverValue,
								  "Failed to Set value for Numeric field as " + carryOverValue);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean verifySubmitRepeatData = ffp.verifySubmitRepeatValue(fieldNumericName, carryOverValue);
			TestValidation.IsTrue(verifySubmitRepeatData, 
								  "VERIFIED carryover field value as " + carryOverValue +" for numeric field " + fieldNumericName, 
								  "Failed to verify carryover field value for numeric field " + fieldNumericName);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Questionnaire Form - Validate Carry over fields Setting functionality for all 3 form types and Validate Submit and repeat functionality")
	public void TestCase_38496() {

		try {
			
			String carryOverValue = "200";
			String questFormName = CommonMethods.dynamicText("QstFrm_CarryOvr");
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean selectQst = fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
			TestValidation.IsTrue(selectQst, 
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
			boolean setNumericFldName = fdp.setElementValueTextArea(FIELD_TYPES.NUMERIC, fieldNumericName);	
			TestValidation.IsTrue(setNumericFldName, 
								  "ADDED name '" + fieldNumericName + "' to Numeric field successfully", 
								  "Failed to Add name '" + fieldNumericName + "' Numeric field");
			
			boolean setCarryOverForNumericField = fdp.carryoverField();
			TestValidation.IsTrue(setCarryOverForNumericField, 
								  "SET Carry Over setting For Numeric Field - " + fieldNumericName + " successfully", 
								  "Failed to Set Carry over setting For Numeric Field - " + fieldNumericName);
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + questFormName, 
								   "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			// Verify carry over functionality while submitting
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questFormName);
			TestValidation.IsTrue(openForm, 
					 			  "OPENED form - " + questFormName, 
					 			  "Failed to open form - " + questFormName);

			FormOperations fo = new FormOperations(driver);
			boolean setNumField = fo.setInputFieldValue(fieldNumericName, Arrays.asList(carryOverValue), null);
			TestValidation.IsTrue(setNumField, 
								  "Value set for Numeric field as " + carryOverValue,
								  "Failed to Set value for Numeric field as " + carryOverValue);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean verifySubmitRepeatData = ffp.verifySubmitRepeatValue(fieldNumericName, carryOverValue);
			TestValidation.IsTrue(verifySubmitRepeatData, 
								  "VERIFIED carryover field value as " + carryOverValue +" for numeric field " + fieldNumericName, 
								  "Failed to verify carryover field value for numeric field " + fieldNumericName);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}
	
	@Test(description="Audit Form - Validate Carry over fields Setting functionality for all 3 form types and Validate Submit and repeat functionality")
	public void TestCase_38497() {

		try {
			
			String carryOverValue = "200";
			String auditFormName = CommonMethods.dynamicText("AdtFrm_CarryOvr");
			
			//Open 'Form Designer'
			FormDesignerPage fdp = hp.clickFormDesignerMenu();
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
			
			// Add Numeric field to Section
			boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,
					FIELD_TYPES.NUMERIC, fieldSectionName);
			TestValidation.IsTrue(addNumericFieldInSection, 
								  "ADDED 'Numeric' field to 'Section' " + fieldSectionName, 
								  "Failed to Add 'Numeric' field to 'Section' " + fieldSectionName);

			boolean setNumericFldName = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, fieldNumericName);	
			TestValidation.IsTrue(setNumericFldName, 
								  "ADDED name '" + fieldNumericName + "' to Numeric field successfully", 
								  "Failed to Add name '" + fieldNumericName + "' Numeric field");
			
			boolean setCarryOverForNumericField = fdp.carryoverField();
			TestValidation.IsTrue(setCarryOverForNumericField, 
								  "SET Carry Over setting For Numeric Field - " + fieldNumericName + " successfully", 
								  "Failed to Set Carry over setting For Numeric Field - " + fieldNumericName);
			
			boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, FORM_NEXT_PAGE.RELEASE_FORM);
			TestValidation.IsTrue(nextToReleaseForm, 
								  "NAVIGATED to 'Release Form'", 
								  "Failed to Navigate to 'Release Form'");
	
			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			// Verify carry over functionality while submitting
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(openForm, 
					 			  "OPENED form - " + auditFormName, 
					 			  "Failed to open form - " + auditFormName);

			FormOperations fo = new FormOperations(driver);
			boolean setNumField = fo.setInputFieldValue(fieldNumericName, Arrays.asList(carryOverValue), null);
			TestValidation.IsTrue(setNumField, 
								  "Value set for Numeric field as " + carryOverValue,
								  "Failed to Set value for Numeric field as " + carryOverValue);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			boolean verifySubmitRepeatData = ffp.verifySubmitRepeatValue(fieldNumericName, carryOverValue);
			TestValidation.IsTrue(verifySubmitRepeatData, 
								  "VERIFIED carryover field value as " + carryOverValue +" for numeric field " + fieldNumericName, 
								  "Failed to verify carryover field value for numeric field " + fieldNumericName);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Check Form -  Validate attachment functionality for all form types")
	public void TestCase_37727() {

		try {
			
			String checkFormName = CommonMethods.dynamicText("ChkFrm_AllAttchmnt");
			String docTypeName = CommonMethods.dynamicText("Doc");
			
			String documentName = "upload.png";
			String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
			
			// Create Document in Doc Type
			DocumentManagementPage dmp = hp.clickdocumentsmenu();
			boolean createDocType = dmp.docUploadinDocType(docTypeName,filePath);
			TestValidation.IsTrue(createDocType, 
						          "CREATED Document type - " + docTypeName, 
							      "Failed to create Document type " + docTypeName);

			//FORM - Creation and Release
			HashMap<String, List<String>> numfield = new HashMap<String, List<String>>();
			numfield.put(FIELD_TYPES.NUMERIC, Arrays.asList(fieldNumericName)); // in setDetails - code is for repeat fields, use that

			HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
			fields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(fieldAggregateName));
			fields.put(FIELD_TYPES.DATE, Arrays.asList(fieldDateName));
			fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(fieldDateTimeName));
			fields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(fieldFreeTxtName));
			fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(fieldParagraphName));
			fields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(fieldSelectMultName));
			fields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(fieldSelectOneName));
			fields.put(FIELD_TYPES.TIME, Arrays.asList(fieldTimeName));

			FormFieldParams ffpNum = new FormFieldParams();
			ffpNum.FieldDetails = numfield;
			ffpNum.Repeat = Arrays.asList(Arrays.asList(fieldNumericName,"2"));
			
			FormFieldParams ffpAll = new FormFieldParams();
			ffpAll.FieldDetails = fields;
			ffpAll.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
			ffpAll.AgrregateSource = fieldNumericName;
			ffpAll.SelectOneMultipleOptions = Arrays.asList("20","30");
			
			HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
			resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(resourceCategoryValue));

			FormDesignParams fdpDets = new FormDesignParams();
			fdpDets.FormType = FORMTYPES.CHECK;
			fdpDets.FormName = checkFormName;
			fdpDets.SelectResources = resource;
			fdpDets.HeaderDocTypeName = docTypeName;
			fdpDets.HeaderDocName = documentName;
			fdpDets.DesignFields = Arrays.asList(ffpNum, ffpAll);
			fdpDets.ReleaseForm = false;

			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean saveForm = fdp.createAndReleaseForm(fdpDets);
			TestValidation.IsTrue(saveForm, 
						          "CREATED saved unreleased form - " + checkFormName, 
							      "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			boolean editForm = fdp.navigateToReleaseForm_EditForm(checkFormName);
			TestValidation.IsTrue(editForm, 
								  "OPENED form in edit mode", 
								  "Failed to Open form in edit mode");
			
			ElementProperties settings = new ElementProperties();
			settings.ALLOW_ATTATHMENTS = true;
			
			HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>();
			fieldSettings.put(fieldNumericName, settings);
			fieldSettings.put(fieldAggregateName, settings);
			fieldSettings.put(fieldDateName, settings);
			fieldSettings.put(fieldDateTimeName, settings);
			fieldSettings.put(fieldFreeTxtName, settings);
			fieldSettings.put(fieldParagraphName, settings);
			fieldSettings.put(fieldSelectMultName, settings);
			fieldSettings.put(fieldSelectOneName, settings);
			fieldSettings.put(fieldTimeName, settings);

			boolean updateSettings = fdp.setFieldProperties(fieldSettings);
			TestValidation.IsTrue(updateSettings, 
								  "'Allow Attachments' setting is SET for all fields present in form " + checkFormName, 
								  "Failed to set 'Allow Attachments' setting for all fields present in form " + checkFormName);

			boolean releaseForm = fdp.releaseForm(checkFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + checkFormName, 
								   "Failed since uncertain that the form " + checkFormName + " did/did not release'");
			
			// Submit form with 'Allow Attachments' setting
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(openForm, 
					 			  "OPENED form - " + checkFormName, 
					 			  "Failed to open form - " + checkFormName);

			HashMap<String, List<String>> fillData = new HashMap<String, List<String>>();
			fillData.put(fieldNumericName,Arrays.asList("5", "5"));
			fillData.put(fieldAggregateName,Arrays.asList("10"));
			fillData.put(fieldDateName,Arrays.asList("5/1/2021"));
			fillData.put(fieldDateTimeName,Arrays.asList("5/1/2021 2:30"));
			fillData.put(fieldFreeTxtName,Arrays.asList("Test Attachment"));
			fillData.put(fieldParagraphName,Arrays.asList("This is a paragraph hence a longer text as compared to Free Text"));
			fillData.put(fieldSelectMultName,Arrays.asList("20"));
			fillData.put(fieldSelectOneName,Arrays.asList("30"));
			fillData.put(fieldTimeName,Arrays.asList("2:30"));

			//Field Property objects
			FieldProperties attchToNum = new FieldProperties();
			attchToNum.fieldName = fieldNumericName;
			attchToNum.allowAttachmenstCheck = true;
			attchToNum.uploadFilePath = filePath;
			
			FieldProperties attchToAggrgt = new FieldProperties();
			attchToAggrgt.fieldName = fieldAggregateName;
			attchToAggrgt.allowAttachmenstCheck = true;
			attchToAggrgt.uploadFilePath = filePath;
			
			FieldProperties attchToDate = new FieldProperties();
			attchToDate.fieldName = fieldDateName;
			attchToDate.allowAttachmenstCheck = true;
			attchToDate.uploadFilePath = filePath;
			
			FieldProperties attchToDT = new FieldProperties();
			attchToDT.fieldName = fieldDateTimeName;
			attchToDT.allowAttachmenstCheck = true;
			attchToDT.uploadFilePath = filePath;
			
			FieldProperties attchToFT = new FieldProperties();
			attchToFT.fieldName = fieldFreeTxtName;
			attchToFT.allowAttachmenstCheck = true;
			attchToFT.uploadFilePath = filePath;
			
			FieldProperties attchToPara = new FieldProperties();
			attchToPara.fieldName = fieldParagraphName;
			attchToPara.allowAttachmenstCheck = true;
			attchToPara	.uploadFilePath = filePath;
			
			FieldProperties attchToSM = new FieldProperties();
			attchToSM.fieldName = fieldSelectMultName;
			attchToSM.allowAttachmenstCheck = true;
			attchToSM.uploadFilePath = filePath;
			
			FieldProperties attchToSO = new FieldProperties();
			attchToSO.fieldName = fieldSelectOneName;
			attchToSO.allowAttachmenstCheck = true;
			attchToSO.uploadFilePath = filePath;
			
			FieldProperties attchToTime = new FieldProperties();
			attchToTime.fieldName = fieldTimeName;
			attchToTime.allowAttachmenstCheck = true;
			attchToTime.uploadFilePath = filePath;

			//Form object
			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillData;
			//Field property assignment  
			fd.fieldProperties = Arrays.asList(attchToNum, attchToAggrgt, attchToDate, attchToDT, attchToFT,
									attchToPara, attchToSM, attchToSO, attchToTime);

			FormOperations fo = new FormOperations(driver);
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + checkFormName, 
								  "Failed to submit form" + checkFormName);
			
			// Verify the values and attachment in record
			fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, 
								  "For customers category, NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
			
			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, checkFormName);
			TestValidation.IsTrue(openRecord, 
					 			  "OPENED record with form name - " + checkFormName, 
					 			  "Failed to open record with form name - " + checkFormName);
			
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			boolean openDoc = frp.clickAndOpenHeaderDoc(documentName);
			TestValidation.IsTrue(openDoc, 
					  			  "OPENED document - " + documentName , 
					  			  "Failed to open document - " + documentName);
			
			boolean switchTab = controlActions.closeCurrentAndSwitchToTab(WINDOW_TAB.SECOND);
			TestValidation.IsTrue(switchTab, 
					  			  "SWITCHED to main page/tab", 
					  			  "Failed to switch to main page/tab");
			
			// Numeric
			String numFieldValue = frp.getFieldSpecificValue(fieldNumericName,FIELD_OTHER_TYPES.FIELD);
			String[] fieldVals = CommonMethods.splitAndGetString(numFieldValue);
			boolean verifyNumFieldValue1 = fieldVals[0].contains("5");
			boolean verifyNumFieldValue2 = fieldVals[1].contains("5");
			TestValidation.IsTrue(verifyNumFieldValue1 && verifyNumFieldValue2, 
								  "RETRIEVED value for the field " + fieldNumericName + " as " + numFieldValue, 
								  "Failed to retrieve value for the field " + fieldNumericName );
			
			String numAttachmentValue = frp.getFieldSpecificValue(fieldNumericName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyNumAttachmentValue = numAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyNumAttachmentValue, 
								  "RETRIEVED value for the field " + fieldNumericName + " for attachment as " + numAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldNumericName + " for attachment");
			
			// Aggregate
			String aggFieldValue = frp.getFieldSpecificValue(fieldAggregateName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyAggFieldValue = aggFieldValue.contains("10");
			TestValidation.IsTrue(verifyAggFieldValue, 
								  "RETRIEVED value for the field " + fieldAggregateName + " as " + aggFieldValue, 
								  "Failed to retrieve value for the field " + fieldAggregateName );
			
			String aggAttachmentValue = frp.getFieldSpecificValue(fieldAggregateName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyAggAttachmentValue = aggAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyAggAttachmentValue, 
								  "RETRIEVED value for the field " + fieldAggregateName + " for attachment as " + aggAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldAggregateName + " for attachment");
			
			// Date
			String dateFieldValue = frp.getFieldSpecificValue(fieldDateName,FIELD_OTHER_TYPES.FIELD);
			DateTime dt = new DateTime();
			String modifiedDate = dt.addSubtractDaysFromDate(dt.getDate("05/01/2021", "MM/dd/yyyy"), -1, "MM/dd/yyyy");
			boolean verifyDateFieldValue = dateFieldValue.contains(modifiedDate);
			TestValidation.IsTrue(verifyDateFieldValue, 
								  "RETRIEVED value for the field " + fieldDateName + " as " + dateFieldValue, 
								  "Failed to retrieve value for the field " + fieldDateName );
			
			String dateAttachmentValue = frp.getFieldSpecificValue(fieldDateName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyDateAttachmentValue = dateAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyDateAttachmentValue, 
								  "RETRIEVED value for the field " + fieldDateName + " for attachment as " + dateAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldDateName + " for attachment");
			
			// Date Time
			String DTFieldValue = frp.getFieldSpecificValue(fieldDateTimeName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyDTFieldValue = DTFieldValue.contains("05/01/2021 02:30");
			TestValidation.IsTrue(verifyDTFieldValue, 
								  "RETRIEVED value for the field " + fieldDateTimeName + " as " + DTFieldValue, 
								  "Failed to retrieve value for the field " + fieldDateTimeName );
			
			String DTAttachmentValue = frp.getFieldSpecificValue(fieldDateTimeName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyDTAttachmentValue = DTAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyDTAttachmentValue, 
								  "RETRIEVED value for the field " + fieldDateTimeName + " for attachment as " + DTAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldDateTimeName + " for attachment");
			
			// Free Text
			String FTFieldValue = frp.getFieldSpecificValue(fieldFreeTxtName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyFTFieldValue = FTFieldValue.contains("Test Attachment");
			TestValidation.IsTrue(verifyFTFieldValue, 
								  "RETRIEVED value for the field " + fieldFreeTxtName + " as " + FTFieldValue, 
								  "Failed to retrieve value for the field " + fieldFreeTxtName );
			
			String FTAttachmentValue = frp.getFieldSpecificValue(fieldFreeTxtName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyFTAttachmentValue = FTAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyFTAttachmentValue, 
								  "RETRIEVED value for the field " + fieldFreeTxtName + " for attachment as " + FTAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldFreeTxtName + " for attachment");
			
			// Paragraph
			String paraFieldValue = frp.getFieldSpecificValue(fieldParagraphName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyParaFieldValue = paraFieldValue.contains("This is a paragraph hence a longer text as compared to Free Text");
			TestValidation.IsTrue(verifyParaFieldValue, 
								  "RETRIEVED value for the field " + fieldParagraphName + " as " + paraFieldValue, 
								  "Failed to retrieve value for the field " + fieldParagraphName );
			
			String paraAttachmentValue = frp.getFieldSpecificValue(fieldParagraphName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyParaAttachmentValue = paraAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyParaAttachmentValue, 
								  "RETRIEVED value for the field " + fieldParagraphName + " for attachment as " + paraAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldParagraphName + " for attachment");
			
			// Select Multiple
			String SMFieldValue = frp.getFieldSpecificValue(fieldSelectMultName,FIELD_OTHER_TYPES.FIELD);
			boolean verifySMFieldValue = SMFieldValue.contains("20");
			TestValidation.IsTrue(verifySMFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectMultName + " as " + SMFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectMultName );
			
			String SMAttachmentValue = frp.getFieldSpecificValue(fieldSelectMultName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifySMAttachmentValue = SMAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifySMAttachmentValue, 
								  "RETRIEVED value for the field " + fieldSelectMultName + " for attachment as " + SMAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldSelectMultName + " for attachment");
			
			// Select One
			String SOFieldValue = frp.getFieldSpecificValue(fieldSelectOneName,FIELD_OTHER_TYPES.FIELD);
			boolean verifySOFieldValue = SOFieldValue.contains("30");
			TestValidation.IsTrue(verifySOFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectOneName + " as " + SOFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectOneName );
			
			String SOAttachmentValue = frp.getFieldSpecificValue(fieldSelectOneName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifySOAttachmentValue = SOAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifySOAttachmentValue, 
								  "RETRIEVED value for the field " + fieldSelectOneName + " for attachment as " + SOAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldSelectOneName + " for attachment");
			
			// Time
			String TimeFieldValue = frp.getFieldSpecificValue(fieldTimeName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyTimeFieldValue = DTFieldValue.contains("02:30");
			TestValidation.IsTrue(verifyTimeFieldValue, 
								  "RETRIEVED value for the field " + fieldTimeName + " as " + TimeFieldValue, 
								  "Failed to retrieve value for the field " + fieldTimeName );
			
			String TimeAttachmentValue = frp.getFieldSpecificValue(fieldTimeName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyTimeAttachmentValue = TimeAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyTimeAttachmentValue, 
								  "RETRIEVED value for the field " + fieldTimeName + " for attachment as " + TimeAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldTimeName + " for attachment");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Questionnaire Form -  Validate attachment functionality for all form types")
	public void TestCase_38502() {

		try {
			
			String questFormName = CommonMethods.dynamicText("QstFrm_AllAttchmnt");
			String docTypeName = CommonMethods.dynamicText("Doc");
			
			String documentName = "upload.png";
			String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
			
			// Create Document in Doc Type
			DocumentManagementPage dmp = hp.clickdocumentsmenu();
			boolean createDocType = dmp.docUploadinDocType(docTypeName,filePath);
			TestValidation.IsTrue(createDocType, 
						          "CREATED Document type - " + docTypeName, 
							      "Failed to create Document type " + docTypeName);

			//FORM - Creation and Release
			HashMap<String, List<String>> numfield = new HashMap<String, List<String>>();
			numfield.put(FIELD_TYPES.NUMERIC, Arrays.asList(fieldNumericName)); // in setDetails - code is for repeat fields, use that

			HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
			fields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(fieldAggregateName));
			fields.put(FIELD_TYPES.DATE, Arrays.asList(fieldDateName));
			fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(fieldDateTimeName));
			fields.put(FIELD_TYPES.SINGLE_LINE_TEXT, Arrays.asList(fieldSingleLineTxtName));
			fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(fieldParagraphName));
			fields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(fieldSelectMultName));
			fields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(fieldSelectOneName));
			fields.put(FIELD_TYPES.TIME, Arrays.asList(fieldTimeName));

			FormFieldParams ffpNum = new FormFieldParams();
			ffpNum.FieldDetails = numfield;
			ffpNum.Repeat = Arrays.asList(Arrays.asList(fieldNumericName,"2"));
			
			FormFieldParams ffpAll = new FormFieldParams();
			ffpAll.FieldDetails = fields;
			ffpAll.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
			ffpAll.AgrregateSource = fieldNumericName;
			ffpAll.SelectOneMultipleOptions = Arrays.asList("20","30");
			
			HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
			resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(resourceCategoryValue));

			FormDesignParams fdpDets = new FormDesignParams();
			fdpDets.FormType = FORMTYPES.QUESTIONNAIRE;
			fdpDets.FormName = questFormName;
			fdpDets.SelectResources = resource;
			fdpDets.HeaderDocTypeName = docTypeName;
			fdpDets.HeaderDocName = documentName;
			fdpDets.DesignFields = Arrays.asList(ffpNum, ffpAll);
			fdpDets.ReleaseForm = false;

			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean saveForm = fdp.createAndReleaseForm(fdpDets);
			TestValidation.IsTrue(saveForm, 
						          "CREATED saved unreleased form - " + questFormName, 
							      "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			boolean editForm = fdp.navigateToReleaseForm_EditForm(questFormName);
			TestValidation.IsTrue(editForm, 
								  "OPENED form in edit mode", 
								  "Failed to Open form in edit mode");
			
			ElementProperties settings = new ElementProperties();
			settings.ALLOW_ATTATHMENTS = true;
			
			HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>();
			fieldSettings.put(fieldNumericName, settings);
			fieldSettings.put(fieldAggregateName, settings);
			fieldSettings.put(fieldDateName, settings);
			fieldSettings.put(fieldDateTimeName, settings);
			fieldSettings.put(fieldSingleLineTxtName, settings);
			fieldSettings.put(fieldParagraphName, settings);
			fieldSettings.put(fieldSelectMultName, settings);
			fieldSettings.put(fieldSelectOneName, settings);
			fieldSettings.put(fieldTimeName, settings);

			boolean updateSettings = fdp.setFieldProperties(fieldSettings);
			TestValidation.IsTrue(updateSettings, 
								  "'Allow Attachments' setting is SET for all fields present in form " + questFormName, 
								  "Failed to set 'Allow Attachments' setting for all fields present in form " + questFormName);

			boolean releaseForm = fdp.releaseForm(questFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + questFormName, 
								   "Failed since uncertain that the form " + questFormName + " did/did not release'");
			
			// Submit form with 'Allow Attachments' setting
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, questFormName);
			TestValidation.IsTrue(openForm, 
					 			  "OPENED form - " + questFormName, 
					 			  "Failed to open form - " + questFormName);

			HashMap<String, List<String>> fillData = new HashMap<String, List<String>>();
			fillData.put(fieldNumericName,Arrays.asList("5", "5"));
			fillData.put(fieldAggregateName,Arrays.asList("10"));
			fillData.put(fieldDateName,Arrays.asList("5/1/2021"));
			fillData.put(fieldDateTimeName,Arrays.asList("5/1/2021 2:30"));
			fillData.put(fieldSingleLineTxtName,Arrays.asList("Test Attachment"));
			fillData.put(fieldParagraphName,Arrays.asList("This is a paragraph hence a longer text as compared to Single Line Text"));
			fillData.put(fieldSelectMultName,Arrays.asList("20"));
			fillData.put(fieldSelectOneName,Arrays.asList("30"));
			fillData.put(fieldTimeName,Arrays.asList("2:30"));

			//Field Property objects
			FieldProperties attchToNum = new FieldProperties();
			attchToNum.fieldName = fieldNumericName;
			attchToNum.allowAttachmenstCheck = true;
			attchToNum.uploadFilePath = filePath;
			
			FieldProperties attchToAggrgt = new FieldProperties();
			attchToAggrgt.fieldName = fieldAggregateName;
			attchToAggrgt.allowAttachmenstCheck = true;
			attchToAggrgt.uploadFilePath = filePath;
			
			FieldProperties attchToDate = new FieldProperties();
			attchToDate.fieldName = fieldDateName;
			attchToDate.allowAttachmenstCheck = true;
			attchToDate.uploadFilePath = filePath;
			
			FieldProperties attchToDT = new FieldProperties();
			attchToDT.fieldName = fieldDateTimeName;
			attchToDT.allowAttachmenstCheck = true;
			attchToDT.uploadFilePath = filePath;
			
			FieldProperties attchToFT = new FieldProperties();
			attchToFT.fieldName = fieldSingleLineTxtName;
			attchToFT.allowAttachmenstCheck = true;
			attchToFT.uploadFilePath = filePath;
			
			FieldProperties attchToPara = new FieldProperties();
			attchToPara.fieldName = fieldParagraphName;
			attchToPara.allowAttachmenstCheck = true;
			attchToPara	.uploadFilePath = filePath;
			
			FieldProperties attchToSM = new FieldProperties();
			attchToSM.fieldName = fieldSelectMultName;
			attchToSM.allowAttachmenstCheck = true;
			attchToSM.uploadFilePath = filePath;
			
			FieldProperties attchToSO = new FieldProperties();
			attchToSO.fieldName = fieldSelectOneName;
			attchToSO.allowAttachmenstCheck = true;
			attchToSO.uploadFilePath = filePath;

			FieldProperties attchToTime = new FieldProperties();
			attchToTime.fieldName = fieldTimeName;
			attchToTime.allowAttachmenstCheck = true;
			attchToTime.uploadFilePath = filePath;
			
			//Form object
			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillData;
			//Field property assignment  
			fd.fieldProperties = Arrays.asList(attchToNum, attchToAggrgt, attchToDate, attchToDT, attchToFT,
									attchToPara, attchToSM, attchToSO, attchToTime);

			FormOperations fo = new FormOperations(driver);
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + questFormName, 
								  "Failed to submit form" + questFormName);
			
			// Verify the values and attachment in record
			fbp = hp.clickFSQABrowserMenu();
			boolean navigateToDocs = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.DOCUMENTS);
			TestValidation.IsTrue(navigateToDocs, 
								  "For customers category, NAVIGATED to FSQABrowser > Documents tab", 
								  "Failed to navigate to FSQABrowser > Documents tab");
			
			boolean openDocument = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.DOCUMENTNAME, questFormName);
			TestValidation.IsTrue(openDocument, 
					 			  "OPENED document with form name - " + questFormName, 
					 			  "Failed to open document with form name - " + questFormName);
			
			
			FSQABrowserDocumentsPage fbdp = new FSQABrowserDocumentsPage(driver);
			boolean openDoc = fbdp.clickAndOpenHeaderDoc(documentName);
			TestValidation.IsTrue(openDoc, 
					  			  "OPENED document - " + documentName , 
					  			  "Failed to open document - " + documentName);
			
			boolean switchTab = controlActions.closeCurrentAndSwitchToTab(WINDOW_TAB.SECOND);
			TestValidation.IsTrue(switchTab, 
					  			  "SWITCHED to main page/tab", 
					  			  "Failed to switch to main page/tab");
			
			// Numeric
			String numFieldValue = fbdp.getFieldSpecificValue(fieldNumericName,FLD_OTHER_TYPES.FIELD);
			String[] fieldVals = CommonMethods.splitAndGetString(numFieldValue);
			boolean verifyNumFieldValue1 = fieldVals[0].contains("5");
			boolean verifyNumFieldValue2 = fieldVals[1].contains("5");
			TestValidation.IsTrue(verifyNumFieldValue1 && verifyNumFieldValue2, 
								  "RETRIEVED value for the field " + fieldNumericName + " as " + numFieldValue, 
								  "Failed to retrieve value for the field " + fieldNumericName );
			
			String numAttachmentValue = fbdp.getFieldSpecificValue(fieldNumericName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifyNumAttachmentValue = numAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyNumAttachmentValue, 
								  "RETRIEVED value for the field " + fieldNumericName + " for attachment as " + numAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldNumericName + " for attachment");
			
			// Aggregate
			String aggFieldValue = fbdp.getFieldSpecificValue(fieldAggregateName,FLD_OTHER_TYPES.FIELD);
			boolean verifyAggFieldValue = aggFieldValue.contains("10");
			TestValidation.IsTrue(verifyAggFieldValue, 
								  "RETRIEVED value for the field " + fieldAggregateName + " as " + aggFieldValue, 
								  "Failed to retrieve value for the field " + fieldAggregateName );
			
			String aggAttachmentValue = fbdp.getFieldSpecificValue(fieldAggregateName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifyAggAttachmentValue = aggAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyAggAttachmentValue, 
								  "RETRIEVED value for the field " + fieldAggregateName + " for attachment as " + aggAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldAggregateName + " for attachment");
			
			// Date
			String dateFieldValue = fbdp.getFieldSpecificValue(fieldDateName,FLD_OTHER_TYPES.FIELD);
			DateTime dt = new DateTime();
			String modifiedDate = dt.addSubtractDaysFromDate(dt.getDate("05/01/2021", "MM/dd/yyyy"), -1, "MM/dd/yyyy");
			boolean verifyDateFieldValue = dateFieldValue.contains(modifiedDate);
			TestValidation.IsTrue(verifyDateFieldValue, 
								  "RETRIEVED value for the field " + fieldDateName + " as " + dateFieldValue, 
								  "Failed to retrieve value for the field " + fieldDateName );
			
			String dateAttachmentValue = fbdp.getFieldSpecificValue(fieldDateName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifyDateAttachmentValue = dateAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyDateAttachmentValue, 
								  "RETRIEVED value for the field " + fieldDateName + " for attachment as " + dateAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldDateName + " for attachment");
			
			// Date Time
			String DTFieldValue = fbdp.getFieldSpecificValue(fieldDateTimeName,FLD_OTHER_TYPES.FIELD);
			boolean verifyDTFieldValue = DTFieldValue.contains("05/01/2021 02:30");
			TestValidation.IsTrue(verifyDTFieldValue, 
								  "RETRIEVED value for the field " + fieldDateTimeName + " as " + DTFieldValue, 
								  "Failed to retrieve value for the field " + fieldDateTimeName );
			
			String DTAttachmentValue = fbdp.getFieldSpecificValue(fieldDateTimeName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifyDTAttachmentValue = DTAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyDTAttachmentValue, 
								  "RETRIEVED value for the field " + fieldDateTimeName + " for attachment as " + DTAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldDateTimeName + " for attachment");
			
			// Single Line Text
			String SLTFieldValue = fbdp.getFieldSpecificValue(fieldSingleLineTxtName,FLD_OTHER_TYPES.FIELD);
			boolean verifySLTFieldValue = SLTFieldValue.contains("Test Attachment");
			TestValidation.IsTrue(verifySLTFieldValue, 
								  "RETRIEVED value for the field " + fieldSingleLineTxtName + " as " + SLTFieldValue, 
								  "Failed to retrieve value for the field " + fieldSingleLineTxtName );
			
			String SLTAttachmentValue = fbdp.getFieldSpecificValue(fieldSingleLineTxtName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifySLTAttachmentValue = SLTAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifySLTAttachmentValue, 
								  "RETRIEVED value for the field " + fieldSingleLineTxtName + " for attachment as " + SLTAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldSingleLineTxtName + " for attachment");
			
			// Paragraph
			String paraFieldValue = fbdp.getFieldSpecificValue(fieldParagraphName,FLD_OTHER_TYPES.FIELD);
			boolean verifyParaFieldValue = paraFieldValue.contains("This is a paragraph hence a longer text as compared to Single Line Text");
			TestValidation.IsTrue(verifyParaFieldValue, 
								  "RETRIEVED value for the field " + fieldParagraphName + " as " + paraFieldValue, 
								  "Failed to retrieve value for the field " + fieldParagraphName );
			
			String paraAttachmentValue = fbdp.getFieldSpecificValue(fieldParagraphName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifyParaAttachmentValue = paraAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyParaAttachmentValue, 
								  "RETRIEVED value for the field " + fieldParagraphName + " for attachment as " + paraAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldParagraphName + " for attachment");
			
			// Select Multiple
			String SMFieldValue = fbdp.getFieldSpecificValue(fieldSelectMultName,FLD_OTHER_TYPES.FIELD);
			boolean verifySMFieldValue = SMFieldValue.contains("20");
			TestValidation.IsTrue(verifySMFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectMultName + " as " + SMFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectMultName );
			
			String SMAttachmentValue = fbdp.getFieldSpecificValue(fieldSelectMultName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifySMAttachmentValue = SMAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifySMAttachmentValue, 
								  "RETRIEVED value for the field " + fieldSelectMultName + " for attachment as " + SMAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldSelectMultName + " for attachment");
			
			// Select One
			String SOFieldValue = fbdp.getFieldSpecificValue(fieldSelectOneName,FLD_OTHER_TYPES.FIELD);
			boolean verifySOFieldValue = SOFieldValue.contains("30");
			TestValidation.IsTrue(verifySOFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectOneName + " as " + SOFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectOneName );
			
			String SOAttachmentValue = fbdp.getFieldSpecificValue(fieldSelectOneName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifySOAttachmentValue = SOAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifySOAttachmentValue, 
								  "RETRIEVED value for the field " + fieldSelectOneName + " for attachment as " + SOAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldSelectOneName + " for attachment");
			
			// Time
			String TimeFieldValue = fbdp.getFieldSpecificValue(fieldTimeName,FLD_OTHER_TYPES.FIELD);
			boolean verifyTimeFieldValue = DTFieldValue.contains("02:30");
			TestValidation.IsTrue(verifyTimeFieldValue, 
								  "RETRIEVED value for the field " + fieldTimeName + " as " + TimeFieldValue, 
								  "Failed to retrieve value for the field " + fieldTimeName );
			
			String TimeAttachmentValue = fbdp.getFieldSpecificValue(fieldTimeName,FLD_OTHER_TYPES.ATTACHMENT);
			boolean verifyTimeAttachmentValue = TimeAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyTimeAttachmentValue, 
								  "RETRIEVED value for the field " + fieldTimeName + " for attachment as " + TimeAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldTimeName + " for attachment");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}
		}
	}	
	
	@Test(description="Audit  Form -  Validate attachment functionality for all form types")
	public void TestCase_38503() {

		try {
			
			String auditFormName = CommonMethods.dynamicText("AdtFrm_AllAttchmnt");
			String docTypeName = CommonMethods.dynamicText("Doc");
			
			String documentName = "upload.png";
			String filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
			
			// Create Document in Doc Type
			DocumentManagementPage dmp = hp.clickdocumentsmenu();
			boolean createDocType = dmp.docUploadinDocType(docTypeName,filePath);
			TestValidation.IsTrue(createDocType, 
						          "CREATED Document type - " + docTypeName, 
							      "Failed to create Document type " + docTypeName);

			//FORM - Creation and Release
			HashMap<String, List<String>> fields = new HashMap<String, List<String>>();
			fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(fieldNumericName)); 
			fields.put(FIELD_TYPES.DATE, Arrays.asList(fieldDateName));
			fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(fieldDateTimeName));
			fields.put(FIELD_TYPES.SINGLE_LINE_TEXT, Arrays.asList(fieldSingleLineTxtName));
			fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(fieldParagraphName));
			fields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(fieldSelectMultName));
			fields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(fieldSelectOneName));
			fields.put(FIELD_TYPES.TIME, Arrays.asList(fieldTimeName));
			
			FormFieldParams ffpAll = new FormFieldParams();
			ffpAll.FieldDetails = fields;
			ffpAll.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
			ffpAll.AgrregateSource = fieldNumericName;
			ffpAll.SelectOneMultipleOptions = Arrays.asList("20","30");
			ffpAll.SectionName = fieldSectionName;
			
			HashMap<String, List<String>> resource = new HashMap<String, List<String>>();
			resource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(resourceCategoryValue));

			FormDesignParams fdpDets = new FormDesignParams();
			fdpDets.FormType = FORMTYPES.AUDIT;
			fdpDets.FormName = auditFormName;
			fdpDets.SelectResources = resource;
			fdpDets.HeaderDocTypeName = docTypeName;
			fdpDets.HeaderDocName = documentName;
			fdpDets.DesignFields = Arrays.asList(ffpAll);
			fdpDets.ReleaseForm = false;

			FormDesignerPage fdp = hp.clickFormDesignerMenu();
			boolean saveForm = fdp.createAndReleaseForm(fdpDets);
			TestValidation.IsTrue(saveForm, 
						          "CREATED saved unreleased form - " + auditFormName, 
							      "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			boolean editForm = fdp.navigateToReleaseForm_EditForm(auditFormName);
			TestValidation.IsTrue(editForm, 
								  "OPENED form in edit mode", 
								  "Failed to Open form in edit mode");
			
			ElementProperties settings = new ElementProperties();
			settings.ALLOW_ATTATHMENTS = true;
			
			HashMap<String, ElementProperties> fieldSettings = new HashMap<String, ElementProperties>();
			fieldSettings.put(fieldNumericName, settings);
			fieldSettings.put(fieldDateName, settings);
			fieldSettings.put(fieldDateTimeName, settings);
			fieldSettings.put(fieldSingleLineTxtName, settings);
			fieldSettings.put(fieldParagraphName, settings);
			fieldSettings.put(fieldSelectMultName, settings);
			fieldSettings.put(fieldSelectOneName, settings);
			fieldSettings.put(fieldTimeName, settings);

			boolean updateSettings = fdp.setFieldProperties(fieldSettings);
			TestValidation.IsTrue(updateSettings, 
								  "'Allow Attachments' setting is SET for all fields present in form " + auditFormName, 
								  "Failed to set 'Allow Attachments' setting for all fields present in form " + auditFormName);

			boolean releaseForm = fdp.releaseForm(auditFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + auditFormName, 
								   "Failed since uncertain that the form " + auditFormName + " did/did not release'");
			
			// Submit form with 'Allow Attachments' setting
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(openForm, 
					 			  "OPENED form - " + auditFormName, 
					 			  "Failed to open form - " + auditFormName);

			HashMap<String, List<String>> fillData = new HashMap<String, List<String>>();
			fillData.put(fieldNumericName,Arrays.asList("5"));
			fillData.put(fieldDateName,Arrays.asList("5/1/2021"));
			fillData.put(fieldDateTimeName,Arrays.asList("5/1/2021 2:30"));
			fillData.put(fieldSingleLineTxtName,Arrays.asList("Test Attachment"));
			fillData.put(fieldParagraphName,Arrays.asList("This is a paragraph hence a longer text as compared to Single Line Text"));
			fillData.put(fieldSelectMultName,Arrays.asList("20"));
			fillData.put(fieldSelectOneName,Arrays.asList("30"));
			fillData.put(fieldTimeName,Arrays.asList("2:30"));

			//Field Property objects
			FieldProperties attchToNum = new FieldProperties();
			attchToNum.fieldName = fieldNumericName;
			attchToNum.allowAttachmenstCheck = true;
			attchToNum.uploadFilePath = filePath;
			
			FieldProperties attchToDate = new FieldProperties();
			attchToDate.fieldName = fieldDateName;
			attchToDate.allowAttachmenstCheck = true;
			attchToDate.uploadFilePath = filePath;
			
			FieldProperties attchToDT = new FieldProperties();
			attchToDT.fieldName = fieldDateTimeName;
			attchToDT.allowAttachmenstCheck = true;
			attchToDT.uploadFilePath = filePath;
			
			FieldProperties attchToFT = new FieldProperties();
			attchToFT.fieldName = fieldSingleLineTxtName;
			attchToFT.allowAttachmenstCheck = true;
			attchToFT.uploadFilePath = filePath;
			
			FieldProperties attchToPara = new FieldProperties();
			attchToPara.fieldName = fieldParagraphName;
			attchToPara.allowAttachmenstCheck = true;
			attchToPara	.uploadFilePath = filePath;
			
			FieldProperties attchToSM = new FieldProperties();
			attchToSM.fieldName = fieldSelectMultName;
			attchToSM.allowAttachmenstCheck = true;
			attchToSM.uploadFilePath = filePath;
			
			FieldProperties attchToSO = new FieldProperties();
			attchToSO.fieldName = fieldSelectOneName;
			attchToSO.allowAttachmenstCheck = true;
			attchToSO.uploadFilePath = filePath;
			
			FieldProperties attchToTime = new FieldProperties();
			attchToTime.fieldName = fieldTimeName;
			attchToTime.allowAttachmenstCheck = true;
			attchToTime.uploadFilePath = filePath;

			//Form object
			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillData;
			//Field property assignment  
			fd.fieldProperties = Arrays.asList(attchToNum, attchToDate, attchToDT, attchToFT,
									attchToPara, attchToSM, attchToSO, attchToTime);

			FormOperations fo = new FormOperations(driver);
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + auditFormName, 
								  "Failed to submit form" + auditFormName);
			
			// Verify the values and attachment in record
			fbp = hp.clickFSQABrowserMenu();
			boolean navigateToRecords = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateToRecords, 
								  "For customers category, NAVIGATED to FSQABrowser > Records tab", 
								  "Failed to navigate to FSQABrowser > Records tab");
			
			boolean openRecord = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, auditFormName);
			TestValidation.IsTrue(openRecord, 
					 			  "OPENED record with form name - " + auditFormName, 
					 			  "Failed to open record with form name - " + auditFormName);
			
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			boolean openDoc = frp.clickAndOpenHeaderDoc(documentName);
			TestValidation.IsTrue(openDoc, 
					  			  "OPENED document - " + documentName , 
					  			  "Failed to open document - " + documentName);
			
			boolean switchTab = controlActions.closeCurrentAndSwitchToTab(WINDOW_TAB.SECOND);
			TestValidation.IsTrue(switchTab, 
					  			  "SWITCHED to main page/tab", 
					  			  "Failed to switch to main page/tab");
			
			// Numeric
			String numFieldValue = frp.getFieldSpecificValue(fieldNumericName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyNumFieldValue = numFieldValue.contains("5");
			TestValidation.IsTrue(verifyNumFieldValue, 
								  "RETRIEVED value for the field " + fieldNumericName + " as " + numFieldValue, 
								  "Failed to retrieve value for the field " + fieldNumericName );
			
			String numAttachmentValue = frp.getFieldSpecificValue(fieldNumericName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyNumAttachmentValue = numAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyNumAttachmentValue, 
								  "RETRIEVED value for the field " + fieldNumericName + " for attachment as " + numAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldNumericName + " for attachment");
			
			// Date
			String dateFieldValue = frp.getFieldSpecificValue(fieldDateName,FIELD_OTHER_TYPES.FIELD);
			DateTime dt = new DateTime();
			String modifiedDate = dt.addSubtractDaysFromDate(dt.getDate("05/01/2021", "MM/dd/yyyy"), -1, "MM/dd/yyyy");
			boolean verifyDateFieldValue = dateFieldValue.contains(modifiedDate);
			TestValidation.IsTrue(verifyDateFieldValue, 
								  "RETRIEVED value for the field " + fieldDateName + " as " + dateFieldValue, 
								  "Failed to retrieve value for the field " + fieldDateName );
			
			String dateAttachmentValue = frp.getFieldSpecificValue(fieldDateName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyDateAttachmentValue = dateAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyDateAttachmentValue, 
								  "RETRIEVED value for the field " + fieldDateName + " for attachment as " + dateAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldDateName + " for attachment");
			
			// Date Time
			String DTFieldValue = frp.getFieldSpecificValue(fieldDateTimeName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyDTFieldValue = DTFieldValue.contains("05/01/2021 02:30");
			TestValidation.IsTrue(verifyDTFieldValue, 
								  "RETRIEVED value for the field " + fieldDateTimeName + " as " + DTFieldValue, 
								  "Failed to retrieve value for the field " + fieldDateTimeName );
			
			String DTAttachmentValue = frp.getFieldSpecificValue(fieldDateTimeName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyDTAttachmentValue = DTAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyDTAttachmentValue, 
								  "RETRIEVED value for the field " + fieldDateTimeName + " for attachment as " + DTAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldDateTimeName + " for attachment");
			
			// Single Line Text
			String SLTFieldValue = frp.getFieldSpecificValue(fieldSingleLineTxtName,FIELD_OTHER_TYPES.FIELD);
			boolean verifySLTFieldValue = SLTFieldValue.contains("Test Attachment");
			TestValidation.IsTrue(verifySLTFieldValue, 
								  "RETRIEVED value for the field " + fieldSingleLineTxtName + " as " + SLTFieldValue, 
								  "Failed to retrieve value for the field " + fieldSingleLineTxtName );
			
			String SLTAttachmentValue = frp.getFieldSpecificValue(fieldSingleLineTxtName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifySLTAttachmentValue = SLTAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifySLTAttachmentValue, 
								  "RETRIEVED value for the field " + fieldSingleLineTxtName + " for attachment as " + SLTAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldSingleLineTxtName + " for attachment");
			
			// Paragraph
			String paraFieldValue = frp.getFieldSpecificValue(fieldParagraphName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyParaFieldValue = paraFieldValue.contains("This is a paragraph hence a longer text as compared to Single Line Text");
			TestValidation.IsTrue(verifyParaFieldValue, 
								  "RETRIEVED value for the field " + fieldParagraphName + " as " + paraFieldValue, 
								  "Failed to retrieve value for the field " + fieldParagraphName );
			
			String paraAttachmentValue = frp.getFieldSpecificValue(fieldParagraphName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyParaAttachmentValue = paraAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyParaAttachmentValue, 
								  "RETRIEVED value for the field " + fieldParagraphName + " for attachment as " + paraAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldParagraphName + " for attachment");
			
			// Select Multiple
			String SMFieldValue = frp.getFieldSpecificValue(fieldSelectMultName,FIELD_OTHER_TYPES.FIELD);
			boolean verifySMFieldValue = SMFieldValue.contains("20");
			TestValidation.IsTrue(verifySMFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectMultName + " as " + SMFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectMultName );
			
			String SMAttachmentValue = frp.getFieldSpecificValue(fieldSelectMultName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifySMAttachmentValue = SMAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifySMAttachmentValue, 
								  "RETRIEVED value for the field " + fieldSelectMultName + " for attachment as " + SMAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldSelectMultName + " for attachment");
			
			// Select One
			String SOFieldValue = frp.getFieldSpecificValue(fieldSelectOneName,FIELD_OTHER_TYPES.FIELD);
			boolean verifySOFieldValue = SOFieldValue.contains("30");
			TestValidation.IsTrue(verifySOFieldValue, 
								  "RETRIEVED value for the field " + fieldSelectOneName + " as " + SOFieldValue, 
								  "Failed to retrieve value for the field " + fieldSelectOneName );
			
			String SOAttachmentValue = frp.getFieldSpecificValue(fieldSelectOneName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifySOAttachmentValue = SOAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifySOAttachmentValue, 
								  "RETRIEVED value for the field " + fieldSelectOneName + " for attachment as " + SOAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldSelectOneName + " for attachment");
			
			// Time
			String TimeFieldValue = frp.getFieldSpecificValue(fieldTimeName,FIELD_OTHER_TYPES.FIELD);
			boolean verifyTimeFieldValue = DTFieldValue.contains("02:30");
			TestValidation.IsTrue(verifyTimeFieldValue, 
								  "RETRIEVED value for the field " + fieldTimeName + " as " + TimeFieldValue, 
								  "Failed to retrieve value for the field " + fieldTimeName );
			
			String TimeAttachmentValue = frp.getFieldSpecificValue(fieldTimeName,FIELD_OTHER_TYPES.ATTACHMENT);
			boolean verifyTimeAttachmentValue = TimeAttachmentValue.contains(documentName);
			TestValidation.IsTrue(verifyTimeAttachmentValue, 
								  "RETRIEVED value for the field " + fieldTimeName + " for attachment as " + TimeAttachmentValue, 
								  "Failed to retrieve value for the field " + fieldTimeName + " for attachment");
			
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

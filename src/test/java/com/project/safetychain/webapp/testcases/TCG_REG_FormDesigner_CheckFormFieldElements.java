package com.project.safetychain.webapp.testcases;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
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
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_ELEMENTS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.PreviewFormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_CheckFormFieldElements extends TestBase {
	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	HomePage hp;
	DocumentManagementPage dmp;

	// Data
	public static String checkFormName1;
	public static String checkFormName2;
	public static String checkFormName3;
	public static String checkFormName4;
	public static String checkFormName5;
	public static String checkFormName6;
	public static String checkFormName7;
	public static String checkFormName8;
	public static String checkFormName9;
	public static String checkFormName10;
	public static String checkFormName11;
	public static String checkFormName12;
	public static String checkFormName13;
	public static String checkFormName14;
	public static String checkFormName15;
	public static String checkFormName16;
	public static String locationCategoryValue;
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1,resourceInstanceValue2,resourceInstanceValue3;
	public static String resourceInstanceValue4,resourceInstanceValue5,resourceInstanceValue6,resourceInstanceValue7;
	public static String locationInstanceValue;
	public static String docTypeName;
	public static String documentName;
	public static String filePath;
	public static HashMap<String, ElementProperties> fieldSettings = new LinkedHashMap<String, ElementProperties>(); 
	public static ElementProperties settings = new ElementProperties();

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		checkFormName1 = CommonMethods.dynamicText("Automation_CheckForm1");
		checkFormName2 = CommonMethods.dynamicText("Automation_CheckForm2");
		checkFormName3 = CommonMethods.dynamicText("Automation_CheckForm3");
		checkFormName4 = CommonMethods.dynamicText("Automation_CheckForm4");
		checkFormName5 = CommonMethods.dynamicText("Automation_CheckForm5");
		checkFormName6 = CommonMethods.dynamicText("Automation_CheckForm6");
		checkFormName7 = CommonMethods.dynamicText("Automation_CheckForm7");
		checkFormName8 = CommonMethods.dynamicText("Automation_CheckForm8");
		checkFormName9 = CommonMethods.dynamicText("Automation_CheckForm9");
		checkFormName10 = CommonMethods.dynamicText("Automation_CheckForm10");
		checkFormName11 = CommonMethods.dynamicText("Automation_CheckForm11");
		checkFormName12 = CommonMethods.dynamicText("Automation_CheckForm12");
		checkFormName13 = CommonMethods.dynamicText("Automation_CheckForm13");
		checkFormName14 = CommonMethods.dynamicText("Automation_CheckForm14");
		checkFormName15 = CommonMethods.dynamicText("Automation_CheckForm15");
		checkFormName16 = CommonMethods.dynamicText("Automation_CheckForm16");
		
		docTypeName = CommonMethods.dynamicText("Doc");
		documentName = "upload.png";
		filePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
		
		locationCategoryValue = CommonMethods.dynamicText("Location_Cat");
		resourceCategoryValue = CommonMethods.dynamicText("Resource_Cat");
		resourceInstanceValue1 = CommonMethods.dynamicText("Resource_Inst1");
		resourceInstanceValue2 = CommonMethods.dynamicText("Resource_Inst2");
		resourceInstanceValue3 = CommonMethods.dynamicText("Resource_Inst3");
		resourceInstanceValue4 = CommonMethods.dynamicText("Resource_Inst4");
		resourceInstanceValue5 = CommonMethods.dynamicText("Resource_Inst5");
		resourceInstanceValue6 = CommonMethods.dynamicText("Resource_Inst6");
		resourceInstanceValue7 = CommonMethods.dynamicText("Resource_Inst7");
		locationInstanceValue = CommonMethods.dynamicText("Location_Inst");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		mainPage = new CommonPage(driver);
		lgnPge = new LoginPage(driver);
		resourceDesigner = new ResourceDesignerPage(driver);
		manageResource = new ManageResourcePage(driver);
		locations = new ManageLocationPage(driver);
		fdp = new FormDesignerPage(driver);

		// login
		HomePage hp = lgnPge.performLogin(adminUsername, adminPassword);
		if (hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		
		// Category creation
		HashMap<String, String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.CUSTOMERS, resourceCategoryValue);
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if (!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		// Location instance
		HashMap<String, Boolean> locInstance = new HashMap<String, Boolean>();
		locInstance.put(locationInstanceValue, true);
		LocationInstanceParams lip = new LocationInstanceParams();
		lip.CategoryName = locationCategoryValue;
		lip.NumberFieldValue = 10;
		lip.TextFieldValue = "XYZ";
		lip.InstanceName = locInstance;
		ManageLocationPage mlp = hp.clickLocationsMenu();
		if (!mlp.createLocation(lip)) {
			TCGFailureMsg = "Could NOT create Location instance";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		// Resource creation
		HashMap<String, Boolean> instances = new HashMap<String, Boolean>();
		instances.put(resourceInstanceValue1, true);
		instances.put(resourceInstanceValue4, true);
		instances.put(resourceInstanceValue5, true);
		instances.put(resourceInstanceValue6, true);
		instances.put(resourceInstanceValue7, true);
		instances.put(resourceInstanceValue2, false);
		ResourceDetailParams rd = new ResourceDetailParams();
		rd.CategoryName = resourceCategoryValue;
		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd.NumberFieldValue = 30;
		rd.TextFieldValue = "ABC";
		rd.InstanceNames = instances;
		rd.LocationInstanceValue = locationInstanceValue;
		ManageResourcePage mrp = hp.clickResourcesMenu();
		if (!mrp.createResourceLinkLocation(rd)) {
			TCGFailureMsg = "Could NOT create Instances for resource - " + resourceCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		//Resource instance creation not linking to location
		HashMap<String, Boolean> instances1 = new HashMap<String, Boolean>();
		instances1.put(resourceInstanceValue3, true);
		ResourceDetailParams rd1 = new ResourceDetailParams();
		rd1.CategoryName = resourceCategoryValue;
		rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
		rd1.NumberFieldValue = 30;
		rd1.TextFieldValue = "ABC";
		rd1.InstanceNames = instances1;
		rd1.LinkLocation=false;
		mrp = hp.clickResourcesMenu();
		if (!mrp.createResourceLinkLocation(rd1)) {
			TCGFailureMsg = "Could NOT create Instances "+resourceInstanceValue3+" for resource - " + resourceCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		

		dmp = mainPage.clickdocumentsmenu();
		if(dmp.error) {
			TCGFailureMsg = "Could NOT open 'Documents'";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		if(!dmp.docUploadinDocType(docTypeName,filePath)) {
			TCGFailureMsg = "Could NOT add document type";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		
	}

	@Test(description = "Form Designer - Check Forms - User can add Free Text (Check) and Single Line Text (Questionnaire / Audit) fields to the form")
	public void TestCase_6850() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource

		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName1);

		String freeTextFieldName = "FreeText1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		WebElement FreeTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setFreeTextFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);
		TestValidation.IsTrue(setFreeTextFieldValue1, "'Free Text' field added successfully",
				"Failed to Add Free Text field");

		fdp.FormNameLabel.click();

		String freeTextFieldShortName = "FreeText1Abcdefghijklmnop";
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(freeTextFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean defaultField = fdp.inputFieldExistInGeneralTab(freeTextFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, "'Default' field is displayed", "'Default' field is NOT displayed");

		boolean repeatField = fdp.inputFieldExistInGeneralTab(freeTextFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, "'Repeat' field is displayed", "'Repeat' field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(freeTextFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(freeTextFieldShortName, "Allow Comments");
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, "Default state of 'Allow Comments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(freeTextFieldShortName, "Allow Attachments");
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, "Default state of 'Allow Attachments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");

		String showOnCOAToggleButton = fdp.getStatusOfToggleButton(freeTextFieldShortName, "Show on COA");
		boolean compare4 = showOnCOAToggleButton.contains("On");
		TestValidation.IsTrue(compare4, "Default state of 'Show on COA' toggle button is 'On'",
				"Failed to validate default state of 'Show on COA' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(freeTextFieldShortName, "Show Hint");
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(freeTextFieldShortName, "Allow Correction");
		boolean compare6 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare6, "Default state of 'Allow Correction' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Correction' toggle button");

		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(freeTextFieldShortName, "Carryover Field");
		boolean compare7 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, "Default state of 'Carryover Field' toggle button is 'Off'",
				"Failed to validate default state of 'Carryover Field' toggle button");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

	}

	@Test(description = "Form Designer - Check Forms - User can add Paragraph Text fields to the form")
	public void TestCase_6851() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName2);

		String paragraphTextFieldName = "ParagraphField1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdef";
		WebElement ParagraphTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.PARAGRAPH_TEXT);
		controlActions.dragdrop(ParagraphTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setparagraphTextFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.PARAGRAPH_TEXT, paragraphTextFieldName);
		TestValidation.IsTrue(setparagraphTextFieldValue1, "'Paragraph Text' field added successfully",
				"Failed to Add Paragraph Text field");

		fdp.FormNameLabel.click();

		String paragraphTextFieldShortName = "ParagraphField1Abcdefghij";
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(paragraphTextFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean defaultField = fdp.inputFieldExistInGeneralTab(paragraphTextFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, "'Default' field is displayed", "'Default' field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName, "Allow Comments");
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, "Default state of 'Allow Comments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName,
				"Allow Attachments");
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, "Default state of 'Allow Attachments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");
		String showHintToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName, "Show Hint");
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, "Add New Button for Dependency Rule creation is displayed",
				"Add New Button for Dependency Rule creation is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

	}

	@Test(description = "Form Designer - Check Forms - User can add Select One fields to the form")
	public void TestCase_6852() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName3);

		String SOFieldName = "SelectOne1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijk";
		WebElement SOField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE, "FIELDTYPE",
				FIELD_TYPES.SELECT_ONE);
		controlActions.dragdrop(SOField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSOFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.SELECT_ONE, SOFieldName);
		TestValidation.IsTrue(setSOFieldValue1, "'Select One' field added successfully",
				"Failed to Add Select One Text field");

		fdp.FormNameLabel.click();

		String soValue1 = "Value1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmno";
		String soValue2 = "Value2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmno";

		boolean setValues1 = fdp.setValuesToElement(soValue1);
		boolean setValues2 = fdp.setValuesToElement(soValue2);
		TestValidation.IsTrue(setValues1 && setValues2, "Successfully set values to Select One",
				"Failed to set values to Select One");

		String SOFieldShortName = "SelectOne1Abcdefghijklmno";
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(SOFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean defaultField = fdp.inputFieldExistInGeneralTab(SOFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, "'Default' field is displayed", "'Default' field is NOT displayed");

		boolean repeatField = fdp.inputFieldExistInGeneralTab(SOFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, "'Repeat' field is displayed", "'Repeat' field is NOT displayed");

		boolean setValue = fdp.setValuesToElement("abc");
		TestValidation.IsTrue(setValue, "'Add New Value' button and 'Value' text field is displayed",
				"'Add New Value' button and 'Value' text field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Allow Comments");
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, "Default state of 'Allow Comments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Allow Attachments");
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, "Default state of 'Allow Attachments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Show Hint"); // Off
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Allow Correction");
		boolean compare6 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare6, "Default state of 'Allow Correction' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Correction' toggle button");

		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Carryover Field");
		boolean compare7 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, "Default state of 'Carryover Field' toggle button is 'Off'",
				"Failed to validate default state of 'Carryover Field' toggle button");

		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, "Add New Button for Dependency Rule creation is displayed",
				"Add New Button for Dependency Rule creation is NOT displayed");

		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, "Add New Button for Expression Rule creation is displayed",
				"Add New Button for Expression Rule creation is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

	}

	@Test(description = "Form Designer - Check Forms - User can add Select Multiple fields to the form")
	public void TestCase_6853() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName4);

		String SMFieldName = "SelectMultipleAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefg";
		WebElement SMField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE, "FIELDTYPE",
				FIELD_TYPES.SELECT_MULTIPLE);
		controlActions.dragdrop(SMField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSMFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.SELECT_MULTIPLE, SMFieldName);
		TestValidation.IsTrue(setSMFieldValue1, "'Select Multiple' field added successfully",
				"Failed to Add Select Multiple Text field");

		fdp.FormNameLabel.click();

		String smValue1 = "Value3AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmno";
		String smValue2 = "Value4AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmno";

		boolean setValues3 = fdp.setValuesToElement(smValue1);
		boolean setValues4 = fdp.setValuesToElement(smValue2);
		TestValidation.IsTrue(setValues3 && setValues4, "Successfully set values to Select Multiple",
				"Failed to set values to Select Multiple");

		String SMFieldShortName = "SelectMultipleAbcdefghijk";
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(SMFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean defaultField = fdp.inputFieldExistInGeneralTab(SMFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, "'Default' field is displayed", "'Default' field is NOT displayed");

		boolean repeatField = fdp.inputFieldExistInGeneralTab(SMFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, "'Repeat' field is displayed", "'Repeat' field is NOT displayed");

		boolean setValue = fdp.setValuesToElement("abc");
		TestValidation.IsTrue(setValue, "'Add New Value' button and 'Value' text field is displayed",
				"'Add New Value' button and 'Value' text field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Allow Comments");
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, "Default state of 'Allow Comments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Allow Attachments");
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, "Default state of 'Allow Attachments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Allow Correction");
		boolean compare6 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare6, "Default state of 'Allow Correction' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Correction' toggle button");

		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Carryover Field");
		boolean compare7 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, "Default state of 'Carryover Field' toggle button is 'Off'",
				"Failed to validate default state of 'Carryover Field' toggle button");

		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, "Add New Button for Dependency Rule creation is displayed",
				"Add New Button for Dependency Rule creation is NOT displayed");

		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, "Add New Button for Expression Rule creation is displayed",
				"Add New Button for Expression Rule creation is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");
	}

	@Test(description = "Form Designer - Check Forms - User can add Numeric fields to the form")
	public void TestCase_6854() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName5);

		String NumFieldName = "NumericAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmn";
		WebElement NumField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE, "FIELDTYPE",
				FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, NumFieldName);
		TestValidation.IsTrue(setNumFieldValue1, "'Numeric' field added successfully",
				"Failed to Add Numeric Text field");

		fdp.FormNameLabel.click();

		String NumFieldShortName = "NumericAbcdefghijklmnopqr";
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(NumFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean defaultField = fdp.inputFieldExistInGeneralTab(NumFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, "'Default' field is displayed", "'Default' field is NOT displayed");

		boolean repeatField = fdp.inputFieldExistInGeneralTab(NumFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, "'Repeat' field is displayed", "'Repeat' field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Allow Comments");
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, "Default state of 'Allow Comments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Allow Attachments");
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, "Default state of 'Allow Attachments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");

		String showOnCOAToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Show on COA");
		boolean compare4 = showOnCOAToggleButton.contains("On");
		TestValidation.IsTrue(compare4, "Default state of 'Show on COA' toggle button is 'On'",
				"Failed to validate default state of 'Show on COA' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Show Hint");
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		String showMinMaxToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Show Min/Max");
		boolean compare6 = showMinMaxToggleButton.contains("On");
		TestValidation.IsTrue(compare6, "Default state of 'Show Min/Max' toggle button is 'On'",
				"Failed to validate default state of 'Show Min/Max' toggle button");

		String showTargetToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Show Target");
		boolean compare7 = showTargetToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, "Default state of 'Show Target' toggle button is 'Off'",
				"Failed to validate default state of 'Show Target' toggle button");

		String failsCheckToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Fails Check");
		boolean compare8 = failsCheckToggleButton.contains("On");
		TestValidation.IsTrue(compare8, "Default state of 'Fails Check' toggle button is 'On'",
				"Failed to validate default state of 'Fails Check' toggle button");

		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Allow Correction");
		boolean compare9 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare9, "Default state of 'Allow Correction' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Correction' toggle button");

		String tempProbeToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Temp Probe");
		boolean compare10 = tempProbeToggleButton.contains("Off");
		TestValidation.IsTrue(compare10, "Default state of 'Temp Probe' toggle button is 'Off'",
				"Failed to validate default state of 'Temp Probe' toggle button");

		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Carryover Field");
		boolean compare11 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare11, "Default state of 'Carryover Field' toggle button is 'Off'",
				"Failed to validate default state of 'Carryover Field' toggle button");

		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, "Add New Button for Dependency Rule creation is displayed",
				"Add New Button for Dependency Rule creation is NOT displayed");

		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, "Add New Button for Expression Rule creation is displayed",
				"Add New Button for Expression Rule creation is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");
	}

	@Test(description = "Form Designer - Check Forms - User can add Date fields to the form")
	public void TestCase_6855() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName6);

		String DateFieldName = "DateAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopq";
		WebElement DateField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE, "FIELDTYPE",
				FIELD_TYPES.DATE);
		controlActions.dragdrop(DateField, fdp.FieldTypeDropAreaFormLevel);
		boolean setDateFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.DATE, DateFieldName);
		TestValidation.IsTrue(setDateFieldValue1, "'Date' field added successfully", "Failed to Add Date Text field");

		fdp.FormNameLabel.click();

		String DateFieldShortName = "DateAbcdefghijklmnopqrstu";
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(DateFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean defaultField = fdp.inputFieldExistInGeneralTab(DateFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, "'Default' field is displayed", "'Default' field is NOT displayed");

		boolean repeatField = fdp.inputFieldExistInGeneralTab(DateFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, "'Repeat' field is displayed", "'Repeat' field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(DateFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(DateFieldShortName, "Allow Comments");
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, "Default state of 'Allow Comments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(DateFieldShortName, "Allow Attachments");
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, "Default state of 'Allow Attachments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");

		String showOnCOAToggleButton = fdp.getStatusOfToggleButton(DateFieldShortName, "Show on COA");
		boolean compare4 = showOnCOAToggleButton.contains("On");
		TestValidation.IsTrue(compare4, "Default state of 'Show on COA' toggle button is 'On'",
				"Failed to validate default state of 'Show on COA' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(DateFieldShortName, "Show Hint");
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		String failsCheckToggleButton = fdp.getStatusOfToggleButton(DateFieldShortName, "Fails Check");
		boolean compare8 = failsCheckToggleButton.contains("On");
		TestValidation.IsTrue(compare8, "Default state of 'Fails Check' toggle button is 'On'",
				"Failed to validate default state of 'Fails Check' toggle button");

		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(DateFieldShortName, "Allow Correction");
		boolean compare9 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare9, "Default state of 'Allow Correction' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Correction' toggle button");

		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(DateFieldShortName, "Carryover Field");
		boolean compare11 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare11, "Default state of 'Carryover Field' toggle button is 'Off'",
				"Failed to validate default state of 'Carryover Field' toggle button");

		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, "Add New Button for Dependency Rule creation is displayed",
				"Add New Button for Dependency Rule creation is NOT displayed");

		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, "Add New Button for Expression Rule creation is displayed",
				"Add New Button for Expression Rule creation is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");
	}

	@Test(description = "Form Designer - Check Forms - User can add Time fields to the form")
	public void TestCase_6856() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName7);

		String TimeFieldName = "TimeAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopq";
		WebElement TimeField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE, "FIELDTYPE",
				FIELD_TYPES.TIME);
		controlActions.dragdrop(TimeField, fdp.FieldTypeDropAreaFormLevel);
		boolean setTimeFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.TIME, TimeFieldName);
		TestValidation.IsTrue(setTimeFieldValue1, "'Time' field added successfully", "Failed to Add Time Text field");

		fdp.FormNameLabel.click();

		String TimeFieldShortName = "TimeAbcdefghijklmnopqrstu";
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(TimeFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean defaultField = fdp.inputFieldExistInGeneralTab(TimeFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, "'Default' field is displayed", "'Default' field is NOT displayed");

		boolean repeatField = fdp.inputFieldExistInGeneralTab(TimeFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, "'Repeat' field is displayed", "'Repeat' field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(TimeFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(TimeFieldShortName, "Allow Comments");
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, "Default state of 'Allow Comments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(TimeFieldShortName, "Allow Attachments");
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, "Default state of 'Allow Attachments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");

		String showOnCOAToggleButton = fdp.getStatusOfToggleButton(TimeFieldShortName, "Show on COA");
		boolean compare4 = showOnCOAToggleButton.contains("On");
		TestValidation.IsTrue(compare4, "Default state of 'Show on COA' toggle button is 'On'",
				"Failed to validate default state of 'Show on COA' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(TimeFieldShortName, "Show Hint");
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		String failsCheckToggleButton = fdp.getStatusOfToggleButton(TimeFieldShortName, "Fails Check");
		boolean compare8 = failsCheckToggleButton.contains("On");
		TestValidation.IsTrue(compare8, "Default state of 'Fails Check' toggle button is 'On'",
				"Failed to validate default state of 'Fails Check' toggle button");

		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(TimeFieldShortName, "Allow Correction");
		boolean compare9 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare9, "Default state of 'Allow Correction' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Correction' toggle button");

		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(TimeFieldShortName, "Carryover Field");
		boolean compare11 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare11, "Default state of 'Carryover Field' toggle button is 'Off'",
				"Failed to validate default state of 'Carryover Field' toggle button");

		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, "Add New Button for Dependency Rule creation is displayed",
				"Add New Button for Dependency Rule creation is NOT displayed");

		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, "Add New Button for Expression Rule creation is displayed",
				"Add New Button for Expression Rule creation is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");
	}

	@Test(description = "Form Designer - Check Forms - User can add Date Time fields to the form")
	public void TestCase_6857() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName8);

		String DateTimeFieldName = "DateTimeAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklm";
		WebElement DateTimeField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.DATE_TIME);
		controlActions.dragdrop(DateTimeField, fdp.FieldTypeDropAreaFormLevel);
		boolean setDateTimeFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.DATE_TIME, DateTimeFieldName);
		TestValidation.IsTrue(setDateTimeFieldValue1, "'Date Time' field added successfully",
				"Failed to Add Time Text field");

		fdp.FormNameLabel.click();

		String DateTimeFieldShortName = "DateTimeAbcdefghijklmnopq";
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(DateTimeFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean defaultField = fdp.inputFieldExistInGeneralTab(DateTimeFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, "'Default' field is displayed", "'Default' field is NOT displayed");

		boolean repeatField = fdp.inputFieldExistInGeneralTab(DateTimeFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, "'Repeat' field is displayed", "'Repeat' field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(DateTimeFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(DateTimeFieldShortName, "Allow Comments");
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, "Default state of 'Allow Comments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(DateTimeFieldShortName, "Allow Attachments");
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, "Default state of 'Allow Attachments' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");

		String showOnCOAToggleButton = fdp.getStatusOfToggleButton(DateTimeFieldShortName, "Show on COA");
		boolean compare4 = showOnCOAToggleButton.contains("On");
		TestValidation.IsTrue(compare4, "Default state of 'Show on COA' toggle button is 'On'",
				"Failed to validate default state of 'Show on COA' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(DateTimeFieldShortName, "Show Hint");
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		String failsCheckToggleButton = fdp.getStatusOfToggleButton(DateTimeFieldShortName, "Fails Check");
		boolean compare8 = failsCheckToggleButton.contains("On");
		TestValidation.IsTrue(compare8, "Default state of 'Fails Check' toggle button is 'On'",
				"Failed to validate default state of 'Fails Check' toggle button");

		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(DateTimeFieldShortName, "Allow Correction");
		boolean compare9 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare9, "Default state of 'Allow Correction' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Correction' toggle button");

		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(DateTimeFieldShortName, "Carryover Field");
		boolean compare11 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare11, "Default state of 'Carryover Field' toggle button is 'Off'",
				"Failed to validate default state of 'Carryover Field' toggle button");

		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, "Add New Button for Dependency Rule creation is displayed",
				"Add New Button for Dependency Rule creation is NOT displayed");

		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, "Add New Button for Expression Rule creation is displayed",
				"Add New Button for Expression Rule creation is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");
	}

	@Test(description = "Form Designer -  Check Forms - User can add Sections to the form")
	public void TestCase_6847() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName9);

		String freeTextFieldName = "FreeText1";
		WebElement FreeTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setFreeTextFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);
		TestValidation.IsTrue(setFreeTextFieldValue1, "'Free Text' field added successfully",
				"Failed to Add Free Text field");

		fdp.FormNameLabel.click();

		// Add Field in Section

		String section1Name = "Section1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		String section1FieldShortName = "Section1Abcdefghijklmnopq";

		String section2Name = "Section2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		String section2FieldShortName = "Section2Abcdefghijklmnopq";

		controlActions.dragdrop(fdp.SectionDbl, fdp.FormLevel);
		boolean setSectionName1 = fdp.setFieldName("Section", section1Name);
		TestValidation.IsTrue(setSectionName1, "Setted 'Section' name", "Could NOT set 'Section' name");

		boolean dragDropTimeField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.TIME,
				section1FieldShortName);
		TestValidation.IsTrue(dragDropTimeField, "Drag-Drop Time field in Section successfully",
				"Failed to Drag-Drop Time field in Section");

		boolean setTimeFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.TIME, "Time1");
		TestValidation.IsTrue(setTimeFieldValue1, " Set name to 'Time' field successfully",
				"Failed to set name to 'Time' field");

		fdp.FormNameLabel.click();

		// Add Field in Group in Section

		WebElement SectionElement2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
				"FORMELEMENT", "Section");
		controlActions.dragdrop(SectionElement2, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName2 = fdp.setFieldName("Section", section2Name);
		TestValidation.IsTrue(setSectionName2, "Setted 'Section' name", "Could NOT set 'Section' name");

		boolean dragDropFieldGroupElement = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,
				FORM_ELEMENTS.FIELD_GROUP, section2FieldShortName);
		TestValidation.IsTrue(dragDropFieldGroupElement, "Drag-Drop Field Group in Section successfully",
				"Failed to Drag-Drop Field Group in Section");

		boolean setFieldGroupName2 = fdp.setFieldName("Field Group", "FieldGroup1");
		TestValidation.IsTrue(setFieldGroupName2, "Setted 'Field Group' name", "Could NOT set 'Field Group' name");

		boolean dragDropDateField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.DATE,
				"FieldGroup1");
		TestValidation.IsTrue(dragDropDateField, "Drag-Drop Date field in Field Group successfully",
				"Failed to Drag-Drop Date field in Field Group");

		boolean setDateFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.DATE, "Date1");
		TestValidation.IsTrue(setDateFieldValue1, "Set name to 'Date' field successfully",
				"Failed to set name to Date field");

		fdp.FormNameLabel.click();

		WebElement ShortNameField = controlActions.perform_GetElementByXPath(
				FormDesignerPageLocator.GENERAL_TAB_INPUT_FIELDS_TXT, "FIELD_NAME", "Short Name");
		boolean isShortNameFieldDisplayed = ShortNameField.isDisplayed();
		TestValidation.IsTrue(isShortNameFieldDisplayed, "'Short Name' field is displayed",
				"'Short Name' field is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

	}

	@Test(description = "Form Designer - Check Form - User can add a Note element to the form")
	public void TestCase_6746() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName10);

		String freeTextFieldName = "FreeText1";
		WebElement FreeTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setFreeTextFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);
		TestValidation.IsTrue(setFreeTextFieldValue1, "'Free Text' field added successfully",
				"Failed to Add Free Text field");

		fdp.FormNameLabel.click();

		String NoteData = "NoteDataAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnop";

		controlActions.dragdrop(fdp.NoteDbl, fdp.HeaderLevel);
		controlActions.sendKeys(fdp.NoteTxt, NoteData);

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

	}

	@Test(description = "Form Designer - Check Forms - User can add Field/Question Groups to the form")
	public void TestCase_6848() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName11);

		String freeTextFieldName = "FreeText1";
		WebElement FreeTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setFreeTextFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);
		TestValidation.IsTrue(setFreeTextFieldValue1, "'Free Text' field added successfully",
				"Failed to Add Free Text field");

		fdp.FormNameLabel.click();

		// Add Field in Section

		String fieldgroup1Name = "FieldGroup1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		String group1ShortName = "FieldGroup1Abcdefghijklmn";

		String section1Name = "Section1";
		String section1FieldShortName = "Section1";

		controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
		boolean setFieldGroupName1 = fdp.setFieldName(FORM_ELEMENTS.FIELD_GROUP, fieldgroup1Name);
		TestValidation.IsTrue(setFieldGroupName1, "Setted 'Field Group' name", "Could NOT set 'Field Group' name");

		boolean dragDropTimeField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.TIME,
				group1ShortName);
		TestValidation.IsTrue(dragDropTimeField, "Drag-Drop Time field in Field Group successfully",
				"Failed to Drag-Drop Time field in Field Group");

		boolean setTimeFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.TIME, "Time1");
		TestValidation.IsTrue(setTimeFieldValue1, " Set name to 'Time' field successfully",
				"Failed to set name to 'Time' field");

		fdp.FormNameLabel.click();

		// Add Field in Group in Section

		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
				"FORMELEMENT", "Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName1 = fdp.setFieldName("Section", section1Name);
		TestValidation.IsTrue(setSectionName1, "Setted 'Section' name", "Could NOT set 'Section' name");

		String fieldgroup2Name = "FieldGroup2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		String fieldgroup2ShortName = "FieldGroup2Abcdefghijklmn";

		boolean dragDropFieldGroupElement = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,
				FORM_ELEMENTS.FIELD_GROUP, section1FieldShortName);
		TestValidation.IsTrue(dragDropFieldGroupElement, "Drag-Drop Field Group in Section successfully",
				"Failed to Drag-Drop Field Group in Section");

		boolean setFieldGroupName2 = fdp.setFieldName("Field Group", fieldgroup2Name);
		TestValidation.IsTrue(setFieldGroupName2, "Setted 'Field Group' name", "Could NOT set 'Field Group' name");

		boolean dragDropDateField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.DATE,
				fieldgroup2ShortName);
		TestValidation.IsTrue(dragDropDateField, "Drag-Drop Date field in Field Group successfully",
				"Failed to Drag-Drop Date field in Field Group");

		boolean setDateFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.DATE, "Date1");
		TestValidation.IsTrue(setDateFieldValue1, "Set name to 'Date' field successfully",
				"Failed to set name to Date field");

		fdp.FormNameLabel.click();

		boolean shortNameField = fdp.inputFieldExistInGeneralTab(group1ShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		boolean repeatField = fdp.inputFieldExistInGeneralTab(group1ShortName, "Repeat");
		TestValidation.IsTrue(repeatField, "'Repeat' field is displayed", "'Repeat' field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(group1ShortName, "Show Group Name");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(group1ShortName, "Show Hint");
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

	}

	@Test(description = "Check - Identifier field functionality")
	public void TestCase_38314() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName12);

		String Identifier1FieldName = "Identifier1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		WebElement IdentifierField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
				"FORMELEMENT", "Identifier");
		controlActions.dragdrop(IdentifierField1, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue1 = fdp.setTextBoxValue("Identifier", Identifier1FieldName);
		TestValidation.IsTrue(setIdentifierFieldValue1, "'Identifier' field added successfully",
				"Failed to Add Identifier Text field");

		boolean selectIdentifierInputType1 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.FREE_TEXT);
		TestValidation.IsTrue(selectIdentifierInputType1, "Selected Identifier Input Type successfully",
				"Failed to Select Identifier Input Type");

		String Identifier2FieldName = "Identifier2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";

		WebElement IdentifierField2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
				"FORMELEMENT", "Identifier");
		controlActions.dragdrop(IdentifierField2, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue2 = fdp.setTextBoxValue("Identifier", Identifier2FieldName);
		TestValidation.IsTrue(setIdentifierFieldValue2, "'Identifier' field added successfully",
				"Failed to Add Identifier Text field");

		boolean selectIdentifierInputType2 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.SELECT_ONE);
		TestValidation.IsTrue(selectIdentifierInputType2, "Selected Identifier Input Type successfully",
				"Failed to Select Identifier Input Type");

		boolean setValuesToElement1 = fdp.setValuesToElement("ID1");
		TestValidation.IsTrue(setValuesToElement1, "Set value for Identifier field successfully",
				"Failed to Set value for Identifier field");

		boolean setValuesToElement2 = fdp.setValuesToElement("ID2");
		TestValidation.IsTrue(setValuesToElement2, "Set value for Identifier field successfully",
				"Failed to Set value for Identifier field");

		String Identifier3FieldName = "Identifier3AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		String Identifier3ShortName = "Identifier3Abcdefghijklmn";

		WebElement IdentifierField3 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,
				"FORMELEMENT", "Identifier");
		controlActions.dragdrop(IdentifierField3, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue3 = fdp.setTextBoxValue("Identifier", Identifier3FieldName);
		TestValidation.IsTrue(setIdentifierFieldValue3, "'Identifier' field added successfully",
				"Failed to Add Identifier Text field");

		boolean selectIdentifierInputType3 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.FREE_TEXT);
		TestValidation.IsTrue(selectIdentifierInputType3, "Selected Identifier Input Type successfully",
				"Failed to Select Identifier Input Type");

		boolean setIdentifierInputMask = fdp.setIdentifierInputMask("L0");
		TestValidation.IsTrue(setIdentifierInputMask, "Identifier Input Mask set successfully",
				"Failed to set Identifier Input Mask");

		boolean shortNameField = fdp.inputFieldExistInGeneralTab(Identifier3ShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, "'Short Name' field is displayed", "'Short Name' field is NOT displayed");

		String isRequiredToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, "Default state of 'Is Required' toggle button is 'On'",
				"Failed to validate default state of 'Is Required' toggle button");

		String isFilterableToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Is Filterable");
		boolean compare2 = isFilterableToggleButton.contains("On");
		TestValidation.IsTrue(compare2, "Default state of 'Is Filterable' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Comments' toggle button");

		String showOnCOAToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Show on COA");
		boolean compare3 = showOnCOAToggleButton.contains("On");
		TestValidation.IsTrue(compare3, "Default state of 'Show on COA' toggle button is 'Off'",
				"Failed to validate default state of 'Allow Attachments' toggle button");

		String showHintToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Show Hint");
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, "Default state of 'Show Hint' toggle button is 'Off'",
				"Failed to validate default state of 'Show Hint' toggle button");

		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Carryover Field");
		boolean compare11 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare11, "Default state of 'Carryover Field' toggle button is 'Off'",
				"Failed to validate default state of 'Carryover Field' toggle button");

		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, "Add New Button for Dependency Rule creation is displayed",
				"Add New Button for Dependency Rule creation is NOT displayed");

		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg, "Release Form");
		TestValidation.IsTrue(nextToReleaseForm, "NAVIGATED to 'Release Form'", "Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(checkFormName12);
		TestValidation.IsTrue(releaseForm, "RELEASED form - " + checkFormName12, "Could NOT release form'");

	}

	@Test(description = "Dynamic Forms - Form Designer can Preview a Check Form. Verify the correct UI and Functionality for Preview of Check Form")
	public void TestCase_5122() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName13);
		String freeTextFieldName = "FreeText1";
		WebElement FreeTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextFieldType, fdp.FormLevel);
		boolean setFreeText = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);
		TestValidation.IsTrue(setFreeText, "'Free Text field' added successfully", "Failed to Add Free Text Field");

		String paragraphTextFieldName = "ParagraphText1";
		WebElement ParagraphTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.PARAGRAPH_TEXT);
		controlActions.dragdrop(ParagraphTextFieldType, fdp.FormLevel);
		boolean setParagraphText = fdp.setTextBoxValue(FIELD_TYPES.PARAGRAPH_TEXT, paragraphTextFieldName);
		TestValidation.IsTrue(setParagraphText, "'Paragraph Text field' added successfully",
				"Failed to Add Paragraph Text Field");

		String selectOneFieldName = "SelectOne1";
		WebElement SelectOneFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.SELECT_ONE);
		controlActions.dragdrop(SelectOneFieldType, fdp.FormLevel);
		boolean setSelectOne = fdp.setTextBoxValue(FIELD_TYPES.SELECT_ONE, selectOneFieldName);
		TestValidation.IsTrue(setSelectOne, "'Select One field' added successfully", "Failed to Add Select One Field");

		boolean setValues1 = fdp.setValuesToElement("1");
		boolean setValues2 = fdp.setValuesToElement("2");
		boolean setValues3 = fdp.setValuesToElement("3");
		TestValidation.IsTrue(setValues1 && setValues2 && setValues3, "Successfully set values to Select One",
				"Failed to set values to Select One");

		String selectMultipleFieldName = "SelectMultiple1";
		WebElement SelectMultipleFieldType = controlActions.perform_GetElementByXPath(
				FormDesignerPageLocator.FIELD_TYPE, "FIELDTYPE", FIELD_TYPES.SELECT_MULTIPLE);
		controlActions.dragdrop(SelectMultipleFieldType, fdp.FormLevel);
		boolean setSelectMultiple = fdp.setTextBoxValue(FIELD_TYPES.SELECT_MULTIPLE, selectMultipleFieldName);
		TestValidation.IsTrue(setSelectMultiple, "'Select Multiple field' added successfully",
				"Failed to Add Select Multiple Field");

		boolean setValues4 = fdp.setValuesToElement("4");
		boolean setValues5 = fdp.setValuesToElement("5");
		boolean setValues6 = fdp.setValuesToElement("6");
		TestValidation.IsTrue(setValues4 && setValues5 && setValues6, "Successfully set values to Select Multiple",
				"Failed to set values to Select Multiple");

		String numericFieldName = "Numeric1";
		WebElement NumericFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericFieldType, fdp.FormLevel);
		boolean setNumeric = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, numericFieldName);
		TestValidation.IsTrue(setNumeric, "'Numeric field' added successfully", "Failed to Add Numeric Field");

		String dateFieldName = "Date1";
		WebElement DateFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.DATE);
		controlActions.dragdrop(DateFieldType, fdp.FormLevel);
		boolean setDate = fdp.setTextBoxValue(FIELD_TYPES.DATE, dateFieldName);
		TestValidation.IsTrue(setDate, "'Date field' added successfully", "Failed to Add Date Field");

		String timeFieldName = "Time1";
		WebElement TimeFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", "Time");
		controlActions.dragdrop(TimeFieldType, fdp.FormLevel);
		boolean setTime = fdp.setTextBoxValue(FIELD_TYPES.TIME, timeFieldName);
		TestValidation.IsTrue(setTime, "'Time field' added successfully", "Failed to Add Time Field");

		String dateTimeFieldName = "DateTime1";
		WebElement DateTimeFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.DATE_TIME);
		controlActions.dragdrop(DateTimeFieldType, fdp.FormLevel);
		boolean setDateTime = fdp.setTextBoxValue(FIELD_TYPES.DATE_TIME, dateTimeFieldName);
		TestValidation.IsTrue(setDateTime, "'DateTime field' added successfully", "Failed to Add setDateTime Field");


		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, "Saved form Successfully", "Could NOT Save Form");

		boolean clickPreviewButton = fdp.clickPreviewButton();
		TestValidation.IsTrue(clickPreviewButton, "Clicked Preview button Successfully",
				"Could NOT click Preview button");

		PreviewFormDetails pfd = new PreviewFormDetails();
		LinkedHashMap<String, List<String>> map1 = new LinkedHashMap<String, List<String>>();
		map1.put("FreeText1", Arrays.asList("Free Text Field"));
		map1.put("ParagraphText1", Arrays.asList("Paragraph Text Field"));
		map1.put("SelectOne1", Arrays.asList("1"));
		map1.put("SelectMultiple1", Arrays.asList("4"));
		map1.put("Numeric1", Arrays.asList("5"));
		map1.put("Date1", Arrays.asList("6/3/2021"));
		map1.put("Time1", Arrays.asList("1:30 AM"));
		map1.put("DateTime1", Arrays.asList("6/3/2021 12:00 AM"));

		pfd.fieldDetails = map1;
		pfd.resourceName = resourceInstanceValue1;
		boolean enterDataForPreviewForm = fdp.enterDataForPreviewForm(pfd);
		TestValidation.IsTrue(enterDataForPreviewForm, "Entered data for Preview form successfully",	
				"Could NOT Enter data for Preview form");
	}

	@Test(description = "Form Designer - Local Storage - Any form that is being designed should be cleared from memory when the user navigates away (feature 1486)")
	public void TestCase_5117() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName14);

		String freeTextFieldName = "FreeText1";
		WebElement FreeTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setFreeTextFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);
		TestValidation.IsTrue(setFreeTextFieldValue1, "'Free Text' field added successfully",
				"Failed to Add Free Text field");

		fdp.FormNameLabel.click();

		boolean clickPreviewButton = fdp.clickPreviewButton();
		TestValidation.IsTrue(clickPreviewButton, "Clicked Preview button Successfully",
				"Could NOT click Preview button");
		
		boolean clickClosePreview = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(clickClosePreview, "CLICKED on Close Preview button Successfully", 
							  "Failed to Click on Close Preview button");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", "Could NOT Save Form");

		boolean clickSelectFormTypeLink = fdp.clickSelectFormTypeLink();
		TestValidation.IsTrue(clickSelectFormTypeLink, "Clicked Select Form Type link Successfully", 
				"Could NOT click Select Form Type link");
		
		boolean selectFormType = fdp.selectFormType(FORMTYPES.CHECK);
		TestValidation.IsTrue(selectFormType, "Clicked CHECK FORM Successfully", 
				"Could NOT click CHECK FORM");
		

		boolean selectResource1 = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource1, "Selected Resource successfully", 
				"Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton1 = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton1, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");
		
		boolean exitFromFormDesigner = fdp.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
				"EXITED from 'Form Designer'", 
				"Could NOT exit from 'Form Designer'");

		
	}
	
	@Test(description="Form Builder - Select Resources -Disabled resource / \"Search\" panel to filter Resource.")
	public void TestCase_5220() {

		try {
		FormDesignerPage opnFormDesigner = fdp.clickFormDesignerMenu();
		TestValidation.IsTrue(!(opnFormDesigner.error), 
				"OPENED 'Form Designer'", 
				"Could NOT open the 'Form Designer'");

		controlActions.clickElement(fdp.SelectCheckFormLnk);
		
		boolean searchAndClickOnResourceInstance1 = fdp.searchAndClickOnResourceInstance(resourceInstanceValue1);
		TestValidation.IsTrue(searchAndClickOnResourceInstance1, 
				"Searched and clicked on resource successfully - " +resourceInstanceValue1, 
				"Could NOT Search and click on resource");

		boolean verifyEnabledResource = fdp.verifyAddedResourceStatus(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(verifyEnabledResource, 
				"VERIFIED Enabled resource in SHOWN", 
				"Could NOT verify enabled resource");

		boolean searchAndClickOnResourceInstance2 = fdp.searchAndClickOnResourceInstance(resourceInstanceValue2);
		TestValidation.IsFalse(searchAndClickOnResourceInstance2, 
				"VERIFIED Disabled resource is not available - " +resourceInstanceValue2, 
				"Could NOT verify availability of disabled resource");
		
		boolean verifyDisabledResource = fdp.verifyAddedResourceStatus(resourceType, resourceCategoryValue, resourceInstanceValue2);
		TestValidation.IsFalse(verifyDisabledResource, 
				"VERIFIED Disabled resource in NOT-SHOWN", 
				"Could NOT verify disabled resource");
		
		boolean searchAndClickOnResourceInstance3 = fdp.searchAndClickOnResourceInstance(resourceInstanceValue3);
		TestValidation.IsFalse(searchAndClickOnResourceInstance3, 
				"VERIFIED Disabled resource is not available - " +resourceInstanceValue3, 
				"Could NOT verify availability of disabled resource");
		
		
		boolean verifyResourceNotLinkedToLocation = fdp.verifyAddedResourceStatus(resourceType, resourceCategoryValue, resourceInstanceValue3);
		TestValidation.IsFalse(verifyResourceNotLinkedToLocation, 
				"VERIFIED resource not linked to location is NOT-SHOWN", 
				"Could NOT verify resource");


		boolean exitFromFormDesigner = fdp.exitFormDesigner();
		TestValidation.IsTrue(exitFromFormDesigner, 
				"EXITED from 'Form Designer'", 
				"Could NOT exit from 'Form Designer'");
		}finally
		{
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}

	}

	@Test(description="Dynamic Forms -  Form Designer can mark related docs hidden or visible for certain resources using the Related Resources Panel")
	public void TestCase_5132() {

		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName15);

		String freeTextFieldName = "FreeText1";
		WebElement FreeTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,
				"FIELDTYPE", FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setFreeTextFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextFieldName);
		TestValidation.IsTrue(setFreeTextFieldValue1, "'Free Text' field added successfully",
				"Failed to Add Free Text field");

		fdp.FormNameLabel.click();

		String NoteData = "NoteData";

		controlActions.dragdrop(fdp.NoteDbl, fdp.HeaderLevel);
		controlActions.sendKeys(fdp.NoteTxt, NoteData);		
		
		boolean addRelatedDocs = fdp.addRelatedDocument(docTypeName, documentName);
		TestValidation.IsTrue(addRelatedDocs, 
				"Document is added in 'Related Docs'", 
				"Could NOT add document 'Related Docs'"); 

		List<String> resourceInstances = Arrays.asList(resourceInstanceValue1, resourceInstanceValue5, resourceInstanceValue6);
		
		boolean resourceSelected = fdp.selectResourcesForRelatedDocuments(documentName,resourceInstances);
		TestValidation.IsTrue(resourceSelected, 
				"Selected Resources for Related Docs Successfully", 
				"Could NOT Select Resources for Related Docs"); 
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");		

		boolean clickPreviewButton = fdp.clickPreviewButton();
		TestValidation.IsTrue(clickPreviewButton, "Clicked Preview button Successfully",
				"Could NOT click Preview button");
		
		boolean selectResourceInPreviewForm1 = fdp.selectResourceInPreviewForm(resourceInstanceValue1);
		TestValidation.IsTrue(selectResourceInPreviewForm1, 
				"Selected Resources in Preview Form Successfully", 
				"Could NOT Select Resources in Preview Form");
		
		boolean verifyHeaderFields1 = fdp.verifyInPreviewFormNameNoteReltdDoc(checkFormName15 ,NoteData, documentName);
		TestValidation.IsTrue(verifyHeaderFields1, 
				"Document is displayed", 
				"Could NOT verify Document");
		
		boolean selectResourceInPreviewForm2 = fdp.selectResourceInPreviewForm(resourceInstanceValue4);
		TestValidation.IsTrue(selectResourceInPreviewForm2, 
				"Selected Resources in Preview Form Successfully", 
				"Could NOT Select Resources in Preview Form");
		
		boolean verifyHeaderFields = fdp.verifyInPreviewFormNameNoteReltdDoc(checkFormName15 ,NoteData, documentName);
		TestValidation.IsFalse(verifyHeaderFields, 
				"Document is NOT displayed", 
				"Could NOT verify Document");
	}
	
	

	@Test(description="Form Designer || Verify that Comments and Attachment icons are clickable on Preview")
	public void TestCase_5124() {
		
		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectCheckFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, checkFormName16);

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
		
		settings.ALLOW_COMMENTS = true;
		settings.ALLOW_ATTATHMENTS = true;
		
		fieldSettings.put(freeTextFieldName, settings);
		fieldSettings.put(paragraphTextFieldName, settings);
		fieldSettings.put(selectOneFieldName, settings);
		fieldSettings.put(selectMultipleFieldName, settings);
		fieldSettings.put(numericFieldName, settings);
		fieldSettings.put(dateFieldName, settings);
		fieldSettings.put(timeFieldName, settings);
		fieldSettings.put(dateTimeFieldName, settings);
		
		boolean setFieldProperties = fdp.setFieldProperties(fieldSettings);
		TestValidation.IsTrue(setFieldProperties, 
				"'Set field properties successfully", 
				"Failed to set field properties");		
		
		boolean clickPreviewOnReleasePage = fdp.clickPreviewOnReleasePage(checkFormName16);
		TestValidation.IsTrue(clickPreviewOnReleasePage, 
				"Clicked Preview Button On ReleasePage Successfully", 
				"Could NOT Click Preview Button On ReleasePage");
		
		boolean selectResourceInPreviewForm1 = fdp.selectResourceInPreviewForm(resourceInstanceValue1);
		TestValidation.IsTrue(selectResourceInPreviewForm1, 
				"Selected Resources in Preview Form Successfully", 
				"Could NOT Select Resources in Preview Form");
		
		List<String>fieldNames = new ArrayList<String>();
		fieldNames.add(freeTextFieldName);
		fieldNames.add(paragraphTextFieldName);
		fieldNames.add(selectOneFieldName);
		fieldNames.add(selectMultipleFieldName);
		fieldNames.add(numericFieldName);
		fieldNames.add(dateFieldName);
		fieldNames.add(timeFieldName);
		fieldNames.add(dateTimeFieldName);
		
		boolean verifyCommentsandAttachmentinPreview = fdp.verifyCommentsandAttachmentinPreview(fieldNames, true, true);
		TestValidation.IsTrue(verifyCommentsandAttachmentinPreview, 
				"Verified Comments and Attachment on Preview Page Successfully", 
				"Could NOT Verify Comments and Attachment on Preview Page");
		
		boolean clickClosePreviewBtn = fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(clickClosePreviewBtn, 
				"Clicked Close Button on Preview Page Successfully", 
				"Could NOT Click Close Button on Preview Page");
	}

	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

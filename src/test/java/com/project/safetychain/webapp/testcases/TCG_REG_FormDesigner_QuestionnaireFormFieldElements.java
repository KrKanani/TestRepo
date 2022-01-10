package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.CommonPage;
import com.project.safetychain.webapp.pageobjects.DocumentManagementPage;
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
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORM_ELEMENTS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_INPUT_TYPE;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_QuestionnaireFormFieldElements extends TestBase{
	ControlActions controlActions;
	CommonPage mainPage;
	LoginPage lgnPge;
	ResourceDesignerPage resourceDesigner;
	ManageResourcePage manageResource;
	ManageLocationPage locations;
	FormDesignerPage fdp;
	HomePage hp;
	DocumentManagementPage dmp;
	
	//Data
	public static String questionnaireFormName1;
	public static String questionnaireFormName2;
	public static String questionnaireFormName3;
	public static String questionnaireFormName4;
	public static String questionnaireFormName5;
	public static String questionnaireFormName6;
	public static String questionnaireFormName7;
	public static String questionnaireFormName8;
	public static String questionnaireFormName9;
	public static String questionnaireFormName10;
	public static String questionnaireFormName11;
	public static String questionnaireFormName12;
	public static String questionnaireFormName13;
	public static String locationCategoryValue;
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1,resourceInstanceValue2,resourceInstanceValue3;
	public static String resourceInstanceValue4,resourceInstanceValue5,resourceInstanceValue6,resourceInstanceValue7;
	public static String locationInstanceValue;
	public static String docTypeName;
	public static String documentName;
	public static String filePath;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		questionnaireFormName1 = CommonMethods.dynamicText("Automation_QuestionnaireForm1");
		questionnaireFormName2 = CommonMethods.dynamicText("Automation_QuestionnaireForm2");
		questionnaireFormName3 = CommonMethods.dynamicText("Automation_QuestionnaireForm3");
		questionnaireFormName4 = CommonMethods.dynamicText("Automation_QuestionnaireForm4");
		questionnaireFormName5= CommonMethods.dynamicText("Automation_QuestionnaireForm5");
		questionnaireFormName6= CommonMethods.dynamicText("Automation_QuestionnaireForm6");
		questionnaireFormName7= CommonMethods.dynamicText("Automation_QuestionnaireForm7");
		questionnaireFormName8 = CommonMethods.dynamicText("Automation_QuestionnaireForm8");
		questionnaireFormName9 = CommonMethods.dynamicText("Automation_QuestionnaireForm9");
		questionnaireFormName10 = CommonMethods.dynamicText("Automation_QuestionnaireForm10");
		questionnaireFormName11 = CommonMethods.dynamicText("Automation_QuestionnaireForm11");
		questionnaireFormName12 = CommonMethods.dynamicText("Automation_QuestionnaireForm12");
		questionnaireFormName13 = CommonMethods.dynamicText("Automation_QuestionnaireForm13");
		

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

	@Test(description = "Form Designer - Questionnaire Forms - User can add Free Text (Check) and Single Line Text (Questionnaire / Audit) fields to the form")
	public void TestCase_38402() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName1);
		
		String singleLineTextFieldName = "SingleLineTest1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdef";
		WebElement SingleLineTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SINGLE_LINE_TEXT);
		controlActions.dragdrop(SingleLineTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSingleLineTextFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.SINGLE_LINE_TEXT, singleLineTextFieldName);		
		TestValidation.IsTrue(setSingleLineTextFieldValue1, 
				"'Single Line Text' field added successfully", 
				"Failed to Add Single Line Text field");	
				
		fdp.FormNameLabel.click();
		
		String singleLineTextFieldShortName = "SingleLineTest1Abcdefghij";		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(singleLineTextFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		boolean defaultField = fdp.inputFieldExistInGeneralTab(singleLineTextFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, 
				"'Default' field is displayed", 
				"'Default' field is NOT displayed");		
		
		boolean repeatField = fdp.inputFieldExistInGeneralTab(singleLineTextFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, 
				"'Repeat' field is displayed", 
				"'Repeat' field is NOT displayed");	
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(singleLineTextFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(singleLineTextFieldShortName, "Allow Comments"); 
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, 
				"Default state of 'Allow Comments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(singleLineTextFieldShortName, "Allow Attachments"); 
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Allow Attachments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(singleLineTextFieldShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
		
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(singleLineTextFieldShortName, "Fails Questionnaire"); 
		boolean compare6 = failsQuestionnaireToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Fails Questionnaire' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Questionnaire' toggle button");
		
		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(singleLineTextFieldShortName, "Allow Correction"); 
		boolean compare7 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, 
				"Default state of 'Allow Correction' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Correction' toggle button");
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(singleLineTextFieldShortName, "Carryover Field"); 
		boolean compare8 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare8, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		
	}

	@Test(description = "Form Designer - Questionnaire Forms - User can add Paragraph Text fields to the form")
	public void TestCase_38404() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName2);
		
		String paragraphTextFieldName = "ParagraphField1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdef";
		WebElement ParagraphTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.PARAGRAPH_TEXT);
		controlActions.dragdrop(ParagraphTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setparagraphTextFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.PARAGRAPH_TEXT, paragraphTextFieldName);		
		TestValidation.IsTrue(setparagraphTextFieldValue1, 
				"'Paragraph Text' field added successfully", 
				"Failed to Add Paragraph Text field");	
				
		fdp.FormNameLabel.click();
		
		String paragraphTextFieldShortName = "ParagraphField1Abcdefghij";		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(paragraphTextFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		boolean defaultField = fdp.inputFieldExistInGeneralTab(paragraphTextFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, 
				"'Default' field is displayed", 
				"'Default' field is NOT displayed");		
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName, "Allow Comments"); 
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, 
				"Default state of 'Allow Comments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName, "Allow Attachments"); 
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Allow Attachments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
	
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(paragraphTextFieldShortName, "Carryover Field"); 
		boolean compare8 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare8, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
		
		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, 
				"Add New Button for Dependency Rule creation is displayed", 
				"Add New Button for Dependency Rule creation is NOT displayed");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		
	}

	@Test(description = "Form Designer - Questionnaire Forms - User can add Select One fields to the form")
	public void TestCase_38406() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName3);
		
		String SOFieldName = "SelectOne1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijk";
		WebElement SOField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SELECT_ONE);
		controlActions.dragdrop(SOField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSOFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.SELECT_ONE, SOFieldName);		
		TestValidation.IsTrue(setSOFieldValue1, 
				"'Select one' field added successfully", 
				"Failed to Add Select One field");	
				
		fdp.FormNameLabel.click();
		

		String soValue1 = "Value1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmno";
		String soValue2 = "Value2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmno";
		
		List<String> Values = Arrays.asList(soValue1, soValue2);
		
		boolean SelectDrpDwn = fdp.addValuesToSelectDropDown(Values);
		TestValidation.IsTrue(SelectDrpDwn, 
				"Add Values To the DropDown", 
				"Could NOT Add Values to Value Field Type");
		
		String SOFieldShortName = "SelectOne1Abcdefghijklmno";		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(SOFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		boolean defaultField = fdp.inputFieldExistInGeneralTab(SOFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, 
				"'Default' field is displayed", 
				"'Default' field is NOT displayed");	
		
		boolean repeatField = fdp.inputFieldExistInGeneralTab(SOFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, 
				"'Repeat' field is displayed", 
				"'Repeat' field is NOT displayed");	
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String includeInExportToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Include In Export");
		boolean compare2 = includeInExportToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, 
				"Default state of 'Include In Export' toggle button is 'Off'", 
				"Failed to validate default state of 'Include In Export' toggle button");
		
		
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Allow Comments"); 
		boolean compare3 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Allow Comments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Allow Attachments"); 
		boolean compare4 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare4, 
				"Default state of 'Allow Attachments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
		
		String showPtsPossibleToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Show Pts. Possible"); 
		boolean compare5 = showPtsPossibleToggleButton.contains("On");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Pts. Possible' toggle button is 'On'", 
				"Failed to validate default state of 'Show Pts. Possible' toggle button");
		
		String showPtsEarnedToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Show Pts. Earned"); 
		boolean compare6 = showPtsEarnedToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Show Pts. Earned' toggle button is 'On'", 
				"Failed to validate default state of 'Show Pts. Earned' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Show Hint"); 
		boolean compare7 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
	
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Fails Questionnaire"); 
		boolean compare8 = failsQuestionnaireToggleButton.contains("On");
		TestValidation.IsTrue(compare8, 
				"Default state of 'Fails Questionnaire' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Questionnaire' toggle button");
		
		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Allow Correction"); 
		boolean compare9 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare9, 
				"Default state of 'Allow Correction' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Correction' toggle button");
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Carryover Field"); 
		boolean compare10 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare10, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
		
		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, 
				"Add New Button for Dependency Rule creation is displayed", 
				"Add New Button for Dependency Rule creation is NOT displayed");
		
		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, 
				"Add New Button for Expression Rule creation is displayed", 
				"Add New Button for Expression Rule creation is NOT displayed");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		
	}

	@Test(description = "Form Designer - Questionnaire Forms - User can add Select Multiple fields to the form")
	public void TestCase_38408() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName4);
		
		String SMFieldName = "SelectMultipleAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefg";
		WebElement SMField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SELECT_MULTIPLE);
		controlActions.dragdrop(SMField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSMFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.SELECT_MULTIPLE, SMFieldName);		
		TestValidation.IsTrue(setSMFieldValue1, 
				"'Select Multiple' field added successfully", 
				"Failed to Add Select Multiple field");	
				
		fdp.FormNameLabel.click();
		

		String smValue1 = "Value1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmno";
		String smValue2 = "Value2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmno";
		
		List<String> Values = Arrays.asList(smValue1, smValue2);
		
		boolean SelectDrpDwn = fdp.addValuesToSelectDropDown(Values);
		TestValidation.IsTrue(SelectDrpDwn, 
				"Add Values To the DropDown", 
				"Could NOT Add Values to Value Field Type");
		
		String SMFieldShortName = "SelectMultipleAbcdefghijk";		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(SMFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		boolean defaultField = fdp.inputFieldExistInGeneralTab(SMFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, 
				"'Default' field is displayed", 
				"'Default' field is NOT displayed");	
		
		boolean repeatField = fdp.inputFieldExistInGeneralTab(SMFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, 
				"'Repeat' field is displayed", 
				"'Repeat' field is NOT displayed");	
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Allow Comments"); 
		boolean compare3 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Allow Comments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Allow Attachments"); 
		boolean compare4 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare4, 
				"Default state of 'Allow Attachments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
		
		String showPtsPossibleToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Show Pts. Possible"); 
		boolean compare5 = showPtsPossibleToggleButton.contains("On");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Pts. Possible' toggle button is 'On'", 
				"Failed to validate default state of 'Show Pts. Possible' toggle button");
		
		String showPtsEarnedToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Show Pts. Earned"); 
		boolean compare6 = showPtsEarnedToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Show Pts. Earned' toggle button is 'On'", 
				"Failed to validate default state of 'Show Pts. Earned' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Show Hint"); 
		boolean compare7 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
	
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Fails Questionnaire"); 
		boolean compare8 = failsQuestionnaireToggleButton.contains("On");
		TestValidation.IsTrue(compare8, 
				"Default state of 'Fails Questionnaire' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Questionnaire' toggle button");
		
		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Allow Correction"); 
		boolean compare9 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare9, 
				"Default state of 'Allow Correction' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Correction' toggle button");
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Carryover Field"); 
		boolean compare10 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare10, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
		
		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, 
				"Add New Button for Dependency Rule creation is displayed", 
				"Add New Button for Dependency Rule creation is NOT displayed");
		
		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, 
				"Add New Button for Expression Rule creation is displayed", 
				"Add New Button for Expression Rule creation is NOT displayed");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		
	}

	@Test(description = "Form Designer - Questionnaire Forms - User can add Date fields to the form")
	public void TestCase_38412() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName5);
		
		String DateFieldName = "DateAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopq";
		WebElement DateField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.DATE);
		controlActions.dragdrop(DateField, fdp.FieldTypeDropAreaFormLevel);
		boolean setDateFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.DATE, DateFieldName);		
		TestValidation.IsTrue(setDateFieldValue1, 
				"'Date' field added successfully", 
				"Failed to Add Date field");	
				
		fdp.FormNameLabel.click();
		
		String dateFieldShortName = "DateAbcdefghijklmnopqrstu";		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(dateFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		boolean defaultField = fdp.inputFieldExistInGeneralTab(dateFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, 
				"'Default' field is displayed", 
				"'Default' field is NOT displayed");		
		
		boolean repeatField = fdp.inputFieldExistInGeneralTab(dateFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, 
				"'Repeat' field is displayed", 
				"'Repeat' field is NOT displayed");	
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(dateFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(dateFieldShortName, "Allow Comments"); 
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, 
				"Default state of 'Allow Comments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(dateFieldShortName, "Allow Attachments"); 
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Allow Attachments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(dateFieldShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
		
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(dateFieldShortName, "Fails Questionnaire"); 
		boolean compare6 = failsQuestionnaireToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Fails Questionnaire' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Questionnaire' toggle button");
		
		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(dateFieldShortName, "Allow Correction"); 
		boolean compare7 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, 
				"Default state of 'Allow Correction' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Correction' toggle button");
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(dateFieldShortName, "Carryover Field"); 
		boolean compare8 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare8, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
		
		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, 
				"Add New Button for Dependency Rule creation is displayed", 
				"Add New Button for Dependency Rule creation is NOT displayed");
		
		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, 
				"Add New Button for Expression Rule creation is displayed", 
				"Add New Button for Expression Rule creation is NOT displayed");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		
	}

	@Test(description = "Form Designer - Questionnaire Forms - User can add Time fields to the form")
	public void TestCase_38414() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName6);
		
		String TimeFieldName = "TimeAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopq";
		WebElement TimeField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.TIME);
		controlActions.dragdrop(TimeField, fdp.FieldTypeDropAreaFormLevel);
		boolean setTimeFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.TIME, TimeFieldName);		
		TestValidation.IsTrue(setTimeFieldValue1, 
				"'Time' field added successfully", 
				"Failed to Add Time field");	
				
		fdp.FormNameLabel.click();
		
		String timeFieldShortName = "TimeAbcdefghijklmnopqrstu";		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(timeFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		boolean defaultField = fdp.inputFieldExistInGeneralTab(timeFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, 
				"'Default' field is displayed", 
				"'Default' field is NOT displayed");		
		
		boolean repeatField = fdp.inputFieldExistInGeneralTab(timeFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, 
				"'Repeat' field is displayed", 
				"'Repeat' field is NOT displayed");	
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(timeFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(timeFieldShortName, "Allow Comments"); 
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, 
				"Default state of 'Allow Comments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(timeFieldShortName, "Allow Attachments"); 
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Allow Attachments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(timeFieldShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
		
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(timeFieldShortName, "Fails Questionnaire"); 
		boolean compare6 = failsQuestionnaireToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Fails Questionnaire' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Questionnaire' toggle button");
		
		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(timeFieldShortName, "Allow Correction"); 
		boolean compare7 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, 
				"Default state of 'Allow Correction' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Correction' toggle button");
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(timeFieldShortName, "Carryover Field"); 
		boolean compare8 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare8, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
		
		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, 
				"Add New Button for Dependency Rule creation is displayed", 
				"Add New Button for Dependency Rule creation is NOT displayed");
		
		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, 
				"Add New Button for Expression Rule creation is displayed", 
				"Add New Button for Expression Rule creation is NOT displayed");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		
	}

	@Test(description = "Form Designer - Questionnaire Forms - User can add Date-Time fields to the form")
	public void TestCase_38416() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName7);
		
		String DateTimeFieldName = "DateTimeAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklm";
		WebElement DateTimeField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.DATE_TIME);
		controlActions.dragdrop(DateTimeField, fdp.FieldTypeDropAreaFormLevel);
		boolean setDateTimeFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.DATE_TIME, DateTimeFieldName);		
		TestValidation.IsTrue(setDateTimeFieldValue1, 
				"'Date Time' field added successfully", 
				"Failed to Add Date Time field");	
				
		fdp.FormNameLabel.click();
		
		String dateTimeFieldShortName = "DateTimeAbcdefghijklmnopq";		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(dateTimeFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		boolean defaultField = fdp.inputFieldExistInGeneralTab(dateTimeFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, 
				"'Default' field is displayed", 
				"'Default' field is NOT displayed");		
		
		boolean repeatField = fdp.inputFieldExistInGeneralTab(dateTimeFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, 
				"'Repeat' field is displayed", 
				"'Repeat' field is NOT displayed");	
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(dateTimeFieldShortName, "Is Required");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(dateTimeFieldShortName, "Allow Comments"); 
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, 
				"Default state of 'Allow Comments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(dateTimeFieldShortName, "Allow Attachments"); 
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Allow Attachments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(dateTimeFieldShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
		
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(dateTimeFieldShortName, "Fails Questionnaire"); 
		boolean compare6 = failsQuestionnaireToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Fails Questionnaire' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Questionnaire' toggle button");
		
		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(dateTimeFieldShortName, "Allow Correction"); 
		boolean compare7 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, 
				"Default state of 'Allow Correction' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Correction' toggle button");
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(dateTimeFieldShortName, "Carryover Field"); 
		boolean compare8 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare8, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
		
		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, 
				"Add New Button for Dependency Rule creation is displayed", 
				"Add New Button for Dependency Rule creation is NOT displayed");
		
		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, 
				"Add New Button for Expression Rule creation is displayed", 
				"Add New Button for Expression Rule creation is NOT displayed");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		
	}
	
	@Test(description = "Form Designer - Questionnaire Forms - User can add Numeric fields to the form")
	public void TestCase_38410() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName8);
		
		String NumFieldName = "NumericAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmn";
		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", NumFieldName);		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");			
		
		fdp.FormNameLabel.click();
		
		String NumFieldShortName = "NumericAbcdefghijklmnopqr";		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(NumFieldShortName, "Short Name");
		TestValidation.IsTrue(shortNameField,
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		boolean defaultField = fdp.inputFieldExistInGeneralTab(NumFieldShortName, "Default");
		TestValidation.IsTrue(defaultField, 
				"'Default' field is displayed", 
				"'Default' field is NOT displayed");		
		
		boolean repeatField = fdp.inputFieldExistInGeneralTab(NumFieldShortName, "Repeat");
		TestValidation.IsTrue(repeatField, 
				"'Repeat' field is displayed", 
				"'Repeat' field is NOT displayed");	
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Is Required"); 
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String allowCommentsToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Allow Comments"); 
		boolean compare2 = allowCommentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare2, 
				"Default state of 'Allow Comments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String allowAttachmentsToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Allow Attachments"); 
		boolean compare3 = allowAttachmentsToggleButton.contains("Off");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Allow Attachments' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
		
		String showMinMaxToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Show Min/Max"); 
		boolean compare6 = showMinMaxToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Show Min/Max' toggle button is 'On'", 
				"Failed to validate default state of 'Show Min/Max' toggle button");
		
		String showTargetToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Show Target"); 
		boolean compare7 = showTargetToggleButton.contains("Off");
		TestValidation.IsTrue(compare7, 
				"Default state of 'Show Target' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Target' toggle button");
				
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Fails Questionnaire"); 
		boolean compare8 = failsQuestionnaireToggleButton.contains("On");
		TestValidation.IsTrue(compare8, 
				"Default state of 'Fails Questionnaire' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Questionnaire' toggle button");

		String allowCorrectionToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Allow Correction"); 
		boolean compare9 = allowCorrectionToggleButton.contains("Off");
		TestValidation.IsTrue(compare9, 
				"Default state of 'Allow Correction' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Correction' toggle button");
		
		String tempProbeToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Temp Probe"); 
		boolean compare10 = tempProbeToggleButton.contains("Off");
		TestValidation.IsTrue(compare10, 
				"Default state of 'Temp Probe' toggle button is 'Off'", 
				"Failed to validate default state of 'Temp Probe' toggle button");
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Carryover Field"); 
		boolean compare11 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare11, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
				
		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, 
				"Add New Button for Dependency Rule creation is displayed", 
				"Add New Button for Dependency Rule creation is NOT displayed");
		
		boolean expressionsAddNewButton = fdp.AddNewExpressions.isDisplayed();
		TestValidation.IsTrue(expressionsAddNewButton, 
				"Add New Button for Expression Rule creation is displayed", 
				"Add New Button for Expression Rule creation is NOT displayed");
				
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");		
	}

	@Test(description = "Form Designer -  Questionnaire Forms - User can add Sections to the form")
	public void TestCase_38397() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName9);
		
		String NumFieldName = "Numeric1";
		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", NumFieldName);		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");			
		
		fdp.FormNameLabel.click();
		
		//Add Field in Section
		
		String section1Name = "Section1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		String section1FieldShortName = "Section1Abcdefghijklmnopq";

		String section2Name = "Section2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		String section2FieldShortName = "Section2Abcdefghijklmnopq";

		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
		boolean setSectionFieldValue1 = fdp.setElementValueTextArea("Section", section1Name);		
		TestValidation.IsTrue(setSectionFieldValue1, 
				"'Section' added successfully", 
				"Failed to Add Section");

		boolean dragDropSingleLineTextField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.SINGLE_LINE_TEXT, section1FieldShortName);
		TestValidation.IsTrue(dragDropSingleLineTextField, 
				"Drag-Drop Single Line Text field in Section successfully", 
				"Failed to Drag-Drop Single Line Text field in Section");

		boolean setSingleLineTextFieldValue = fdp.setElementValueTextArea(FIELD_TYPES.SINGLE_LINE_TEXT, "SingleLineText1");
		TestValidation.IsTrue(setSingleLineTextFieldValue, 
				"Set name to Single Line Text field successfully", 
				"Failed to Set name to Single Line Text field");


		//Add Field in Group in Section

		WebElement SectionElement2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement2, fdp.FieldTypeDropAreaFormLevel);
		boolean setSectionFieldValue2 = fdp.setElementValueTextArea("Section", section2Name);		
		TestValidation.IsTrue(setSectionFieldValue2, 
				"'Section' added successfully", 
				"Failed to Add Section");		

		boolean dragDropFieldGroupElement = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.FIELD_GROUP, section2FieldShortName);
		TestValidation.IsTrue(dragDropFieldGroupElement, 
				"Drag-Drop Field Group in Section successfully", 
				"Failed to Drag-Drop Field Group in Section");

		boolean setFieldGroupValue = fdp.setElementValueTextArea(FORM_ELEMENTS.FIELD_GROUP, "FieldGroup1");
		TestValidation.IsTrue(setFieldGroupValue, 
				"Set name to Field Group Element successfully", 
				"Failed to Set name to Field Group Element");		

		boolean dragDropDateField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.DATE, "FieldGroup1");
		TestValidation.IsTrue(dragDropDateField, 
				"Drag-Drop Date field in Field Group successfully", 
				"Failed to Drag-Drop Date field in Field Group");

		boolean setDateFieldValue = fdp.setElementValueTextArea(FIELD_TYPES.DATE, "Date1");
		TestValidation.IsTrue(setDateFieldValue, 
				"Set name to Date field successfully", 
				"Failed to Set name to Date field");		

		fdp.FormNameLabel.click();
		
		WebElement ShortNameField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.GENERAL_TAB_INPUT_FIELDS_TXT,"FIELD_NAME","Short Name");
		boolean isShortNameFieldDisplayed = ShortNameField.isDisplayed();
		TestValidation.IsTrue(isShortNameFieldDisplayed, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
				
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		}

	@Test(description = "Form Designer - Questionnaire Form - User can add a Note element to the form")
	public void TestCase_38388() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName10);
		
		String NumFieldName = "Numeric1";
		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", NumFieldName);		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");			
		
		fdp.FormNameLabel.click();

		String NoteData = "NoteDataAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnop";
				
		controlActions.dragdrop(fdp.NoteDbl, fdp.HeaderLevel);
		controlActions.sendKeys(fdp.NoteTxt,NoteData);
				
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
							
	}

	@Test(description = "Form Designer - Questionnaire - User can add Field/Question Groups to the form")
	public void TestCase_38399() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);
		
				
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

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName11);
		
		String NumFieldName = "Numeric1";
		WebElement NumericField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
		controlActions.dragdrop(NumericField, fdp.FieldTypeDropAreaFormLevel);
		boolean setNumericFieldValue1 = fdp.setElementValueTextArea("Numeric", NumFieldName);		
		TestValidation.IsTrue(setNumericFieldValue1, 
				"'Numeric' field added successfully", 
				"Failed to Add Numeric field");			
		
		fdp.FormNameLabel.click();
		
		//Add Field in Section
		
		String group1Name = "FieldGroup1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		String group1ShortName = "FieldGroup1Abcdefghijklmn";

		String section1Name = "Section1";
		String section1FieldShortName = "Section1";

		WebElement FieldGroup1Element = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT",FORM_ELEMENTS.FIELD_GROUP);
		controlActions.dragdrop(FieldGroup1Element, fdp.FieldTypeDropAreaFormLevel);
		boolean setFieldGroupValue1 = fdp.setElementValueTextArea(FORM_ELEMENTS.FIELD_GROUP, group1Name);		
		TestValidation.IsTrue(setFieldGroupValue1, 
				"'Field Group' added successfully", 
				"Failed to Add Field Group");

		boolean dragDropSingleLineTextField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.SINGLE_LINE_TEXT, group1ShortName);
		TestValidation.IsTrue(dragDropSingleLineTextField, 
				"Drag-Drop Single Line Text field in Field Group successfully", 
				"Failed to Drag-Drop Single Line Text field in Field Group");

		boolean setSingleLineTextFieldValue = fdp.setElementValueTextArea(FIELD_TYPES.SINGLE_LINE_TEXT, "SingleLineText1");
		TestValidation.IsTrue(setSingleLineTextFieldValue, 
				"Set name to Single Line Text field successfully", 
				"Failed to Set name to Single Line Text field");


		//Add Field in Group in Section

		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
		boolean setSectionFieldValue1 = fdp.setElementValueTextArea("Section", section1Name);		
		TestValidation.IsTrue(setSectionFieldValue1, 
				"'Section' added successfully", 
				"Failed to Add Section");		

		String group2Name = "FieldGroup2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		String group2ShortName = "FieldGroup2Abcdefghijklmn";
		
		boolean dragDropFieldGroupElement = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.FIELD_GROUP, section1FieldShortName);
		TestValidation.IsTrue(dragDropFieldGroupElement, 
				"Drag-Drop Field Group in Section successfully", 
				"Failed to Drag-Drop Field Group in Section");

		boolean setFieldGroupValue = fdp.setElementValueTextArea(FORM_ELEMENTS.FIELD_GROUP, group2Name);
		TestValidation.IsTrue(setFieldGroupValue, 
				"Set name to Field Group Element successfully", 
				"Failed to Set name to Field Group Element");		

		boolean dragDropDateField = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES, FIELD_TYPES.DATE, group2ShortName);
		TestValidation.IsTrue(dragDropDateField, 
				"Drag-Drop Date field in Field Group successfully", 
				"Failed to Drag-Drop Date field in Field Group");

		boolean setDateFieldValue = fdp.setElementValueTextArea(FIELD_TYPES.DATE, "Date1");
		TestValidation.IsTrue(setDateFieldValue, 
				"Set name to Date field successfully", 
				"Failed to Set name to Date field");		

		fdp.FormNameLabel.click();
		
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(group1ShortName, "Short Name");
		TestValidation.IsTrue(shortNameField, 
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");			
		
		boolean repeatField = fdp.inputFieldExistInGeneralTab(group1ShortName, "Repeat");
		TestValidation.IsTrue(repeatField, 
				"'Repeat' field is displayed", 
				"'Repeat' field is NOT displayed");	
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(group1ShortName, "Show Group Name");
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String showHintToggleButton = fdp.getStatusOfToggleButton(group1ShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
		
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
		}

	@Test(description="Questionnaire - Identifier field functionality")
	public void TestCase_38504() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);
		
		//Selection of resource
		boolean selectResource = fdp.selectResource("Customers",resourceCategoryValue,resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, 
				"Selected Resource successfully", 
				"Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
				"Clicked On Next Button successfully", 
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName12);

		String Identifier1FieldName = "Identifier1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		
		WebElement IdentifierField1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Identifier");
		controlActions.dragdrop(IdentifierField1, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue1 = fdp.setElementValueTextArea("Identifier", Identifier1FieldName);		
		TestValidation.IsTrue(setIdentifierFieldValue1, 
				"'Identifier' field added successfully", 
				"Failed to Add Identifier field");		
		
		boolean selectIdentifierInputType1 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.FREE_TEXT);		
		TestValidation.IsTrue(selectIdentifierInputType1, 
				"Selected Identifier Input Type successfully", 
				"Failed to Select Identifier Input Type");

		String Identifier2FieldName = "Identifier2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		
		WebElement IdentifierField2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Identifier");
		controlActions.dragdrop(IdentifierField2, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue2 = fdp.setElementValueTextArea("Identifier", Identifier2FieldName);		
		TestValidation.IsTrue(setIdentifierFieldValue2, 
				"'Identifier' field added successfully", 
				"Failed to Add Identifier field");		

		boolean selectIdentifierInputType2 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.SELECT_ONE);		
		TestValidation.IsTrue(selectIdentifierInputType2, 
				"Selected Identifier Input Type successfully", 
				"Failed to Select Identifier Input Type");

		boolean setValuesToElement1 = fdp.setValuesToElement("ID1");
		TestValidation.IsTrue(setValuesToElement1, 
				"Set value for Identifier field successfully", 
				"Failed to Set value for Identifier field");

		boolean setValuesToElement2 = fdp.setValuesToElement("ID2");
		TestValidation.IsTrue(setValuesToElement2, 
				"Set value for Identifier field successfully", 
				"Failed to Set value for Identifier field");

		String Identifier3FieldName = "Identifier3AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		String Identifier3ShortName = "Identifier3Abcdefghijklmn";
		
		WebElement IdentifierField3 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Identifier");
		controlActions.dragdrop(IdentifierField3, fdp.FieldTypeDropAreaFormLevel);
		boolean setIdentifierFieldValue3 = fdp.setElementValueTextArea("Identifier", Identifier3FieldName);		
		TestValidation.IsTrue(setIdentifierFieldValue3, 
				"'Identifier' field added successfully", 
				"Failed to Add Identifier field");		

		boolean selectIdentifierInputType3 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.FREE_TEXT);		
		TestValidation.IsTrue(selectIdentifierInputType3, 
				"Selected Identifier Input Type successfully", 
				"Failed to Select Identifier Input Type");

		boolean setIdentifierInputMask = fdp.setIdentifierInputMask("L0");		
		TestValidation.IsTrue(setIdentifierInputMask, 
				"Identifier Input Mask set successfully", 
				"Failed to set Identifier Input Mask");
			
		boolean shortNameField = fdp.inputFieldExistInGeneralTab(Identifier3ShortName, "Short Name");
		TestValidation.IsTrue(shortNameField,
				"'Short Name' field is displayed", 
				"'Short Name' field is NOT displayed");		
		
		String isRequiredToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Is Required"); 
		boolean compare1 = isRequiredToggleButton.contains("On");
		TestValidation.IsTrue(compare1, 
				"Default state of 'Is Required' toggle button is 'On'", 
				"Failed to validate default state of 'Is Required' toggle button");
		
		String isFilterableToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Is Filterable"); 
		boolean compare2 = isFilterableToggleButton.contains("On");
		TestValidation.IsTrue(compare2, 
				"Default state of 'Is Filterable' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Comments' toggle button");
		
		String showOnCOAToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Show on COA"); 
		boolean compare3 = showOnCOAToggleButton.contains("On");
		TestValidation.IsTrue(compare3, 
				"Default state of 'Show on COA' toggle button is 'Off'", 
				"Failed to validate default state of 'Allow Attachments' toggle button");
				
		String showHintToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Show Hint"); 
		boolean compare5 = showHintToggleButton.contains("Off");
		TestValidation.IsTrue(compare5, 
				"Default state of 'Show Hint' toggle button is 'Off'", 
				"Failed to validate default state of 'Show Hint' toggle button");
		
		String carryOverFieldToggleButton = fdp.getStatusOfToggleButton(Identifier3ShortName, "Carryover Field"); 
		boolean compare11 = carryOverFieldToggleButton.contains("Off");
		TestValidation.IsTrue(compare11, 
				"Default state of 'Carryover Field' toggle button is 'Off'", 
				"Failed to validate default state of 'Carryover Field' toggle button");
				
		fdp.AdvancedTab.click();
		boolean addNewButton = fdp.AddNewBtn.isDisplayed();
		TestValidation.IsTrue(addNewButton, 
				"Add New Button for Dependency Rule creation is displayed", 
				"Add New Button for Dependency Rule creation is NOT displayed");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		boolean releaseForm = fdp.releaseForm(questionnaireFormName12);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+questionnaireFormName12, 
				"Could NOT release form'");

	}	


	@Test(description="Dynamic Forms -  Form Designer can mark document links as hidden or visible for different resources using the Related Resources Panel and Properties Panel")
	public void TestCase_5135() {


		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectQuestionnaireFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, questionnaireFormName13);

		String singleLineTextFieldName = "SingleLineText";
		WebElement SingleLineTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SINGLE_LINE_TEXT);
		controlActions.dragdrop(SingleLineTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSingleLineTextFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.SINGLE_LINE_TEXT, singleLineTextFieldName);		
		TestValidation.IsTrue(setSingleLineTextFieldValue1, 
				"'Single Line Text' field added successfully", 
				"Failed to Add Single Line Text field");	
				
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

		boolean nextToReleaseForm = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(nextToReleaseForm, 
				"NAVIGATED to 'Release Form'", 
				"Could NOT Navigate to 'Release Form'");

		
		boolean releaseForm = fdp.releaseForm(questionnaireFormName13);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+questionnaireFormName13, 
				"Could NOT release form'");

		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Forms");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Forms Tab", 
				"Could NOT Navigate to FSQA -> Forms Tab");

		boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", questionnaireFormName13);
		TestValidation.IsTrue(applyFilterAndOpenForDetails, 
				"Verify form is displayed in Forms Tab", 
				"Form is NOT displayed in Forms Tab");
		
		FSQABrowserFormsPage fbfp = new FSQABrowserFormsPage(driver);
		
		boolean setResourceForForm1 = fbfp.setResourceForForm(resourceInstanceValue1);
		TestValidation.IsTrue(setResourceForForm1, 
				"Set Resource for Form Successfully", 
				"Could NOT Set Resource for Form");
		
		boolean verifyRelatedDocuments1 = fbfp.verifyRelatedDocuments(documentName);
		TestValidation.IsTrue(verifyRelatedDocuments1, 
				"Verified Related Documents Successfully", 
				"Could NOT Verify Related Documents - " + documentName);
		
		fbfp.FormCancelBtn.click();
		
		mainPage.Sync();
		
		boolean applyFilterAndOpenForDetails1 = fbp.applyFilterAndOpenForDetails("Form Name", questionnaireFormName13);
		TestValidation.IsTrue(applyFilterAndOpenForDetails1, 
				"Verify form is displayed in Forms Tab", 
				"Form is NOT displayed in Forms Tab");
		
		boolean setResourceForForm2 = fbfp.setResourceForForm(resourceInstanceValue4);
		TestValidation.IsTrue(setResourceForForm2, 
				"Set Resource for Form Successfully", 
				"Could NOT Set Resource for Form");
		
		boolean verifyRelatedDocuments2 = fbfp.verifyRelatedDocuments(documentName);
		TestValidation.IsFalse(verifyRelatedDocuments2, 
				"Related Documents is not displayed", 
				"Could NOT Verify Related Documents - " + documentName);
		
		fbfp.FormCancelBtn.click();
		
		
	}

	
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

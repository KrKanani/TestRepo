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
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.PreviewFormDetails;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_AuditFormFieldElements extends TestBase{
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
	public static String auditFormName1;
	public static String auditFormName2;
	public static String auditFormName3;
	public static String auditFormName4;
	public static String auditFormName5;
	public static String auditFormName6;
	public static String auditFormName7;
	public static String auditFormName8;
	public static String auditFormName9;
	public static String auditFormName10;
	public static String auditFormName11;
	public static String auditFormName12;
	public static String auditFormName13;
	public static String auditFormName14;
	public static String locationCategoryValue;
	public static String resourceType = "Customers";
	public static String resourceCategoryValue;
	public static String resourceInstanceValue1,resourceInstanceValue2,resourceInstanceValue3;
	public static String resourceInstanceValue4,resourceInstanceValue5,resourceInstanceValue6,resourceInstanceValue7;
	public static String locationInstanceValue;
	public static String docTypeName;
	public static String documentName1;
	public static String documentName2;
	public static String filePath1;
	public static String filePath2;
	List <String> documentNames = new ArrayList<String>();

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		auditFormName1 = CommonMethods.dynamicText("Automation_AuditForm1");
		auditFormName2 = CommonMethods.dynamicText("Automation_AuditForm2");
		auditFormName3 = CommonMethods.dynamicText("Automation_AuditForm3");
		auditFormName4 = CommonMethods.dynamicText("Automation_AuditForm4");
		auditFormName5 = CommonMethods.dynamicText("Automation_AuditForm5");
		auditFormName6 = CommonMethods.dynamicText("Automation_AuditForm6");
		auditFormName7 = CommonMethods.dynamicText("Automation_AuditForm7");
		auditFormName8 = CommonMethods.dynamicText("Automation_AuditForm8");
		auditFormName9 = CommonMethods.dynamicText("Automation_AuditForm9");
		auditFormName10 = CommonMethods.dynamicText("Automation_AuditForm10");
		auditFormName11 = CommonMethods.dynamicText("Automation_AuditForm11");
		auditFormName12 = CommonMethods.dynamicText("Automation_AuditForm12");
		auditFormName13 = CommonMethods.dynamicText("Automation_AuditForm13");
		auditFormName14 = CommonMethods.dynamicText("Automation_AuditForm14");
		

		docTypeName = CommonMethods.dynamicText("Doc");
		documentName1 = "upload.png";
		documentName2 = "uploadnew.jpg";
		
		documentNames.add(documentName1);
		documentNames.add(documentName2);

		filePath1 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName1;
		filePath2 = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName2;
		
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
		if(!dmp.docUploadinDocType(docTypeName,filePath1)) {
			TCGFailureMsg = "Could NOT add document type";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
	
		if(!dmp.uploadDocument(filePath2,docTypeName)) {
			TCGFailureMsg = "Could NOT add document type";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description = "Form Designer - Audit Forms - User can add Free Text (Check) and Single Line Text (Questionnaire / Audit) fields to the form")
	public void TestCase_38403() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName1);
		
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addSingleLineTextFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SINGLE_LINE_TEXT, section1Name);
		TestValidation.IsTrue(addSingleLineTextFieldInSection, 
				"'Single Line Text' field added successfully in 'Section'", 
				"Failed to Add Single Line Text field");

		String singleLineTextFieldName = "SingleLineTest1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdef";
		boolean setSingleLineTextFieldName = fdp.setFieldName(FIELD_TYPES.SINGLE_LINE_TEXT,singleLineTextFieldName);
		TestValidation.IsTrue(setSingleLineTextFieldName, 
				"Setted 'Single Line Text' field name", 
				"Could NOT set 'Single Line Text' field name");

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
		
		String failsAuditToggleButton = fdp.getStatusOfToggleButton(singleLineTextFieldShortName, "Fails Audit"); 
		boolean compare6 = failsAuditToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Fails Audit' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Audit' toggle button");
		
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

	@Test(description = "Form Designer - Audit form Forms - User can add Paragraph Text fields to the form")
	public void TestCase_38405() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName2);
		
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addParagraphTextFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.PARAGRAPH_TEXT, section1Name);
		TestValidation.IsTrue(addParagraphTextFieldInSection, 
				"'Paragraph Text' field added successfully in 'Section'", 
				"Failed to Add Paragraph Text field");

		String paragraphTextFieldName = "ParagraphField1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdef";
		boolean setParagraphTextFieldName = fdp.setFieldName(FIELD_TYPES.PARAGRAPH_TEXT,paragraphTextFieldName);
		TestValidation.IsTrue(setParagraphTextFieldName, 
				"Setted 'Paragraph Text' field name", 
				"Could NOT set 'Paragraph Text' field name");

		fdp.FormNameLabel.click();

		String paragraphTextFieldShortName = "ParagraphField1Abcdefghij";			boolean shortNameField = fdp.inputFieldExistInGeneralTab(paragraphTextFieldShortName, "Short Name");
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

	@Test(description = "Form Designer - Audit Forms - User can add Select One fields to the form")
	public void TestCase_38407() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName3);
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addSOFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SELECT_ONE, section1Name);
		TestValidation.IsTrue(addSOFieldInSection, 
				"'Select one' field added successfully in 'Section'", 
				"Failed to Add Select one field");

		String SOFieldName = "SelectOne1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijk";
		boolean setSOFieldName = fdp.setFieldName(FIELD_TYPES.SELECT_ONE,SOFieldName);
		TestValidation.IsTrue(setSOFieldName, 
				"Setted 'Select One' field name", 
				"Could NOT set 'Select One' field name");

				
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

		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(SOFieldShortName, "Fails Audit"); 
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

	@Test(description = "Form Designer - Audit Forms - User can add Select Multiple fields to the form")
	public void TestCase_38409() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName4);
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addSMFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SELECT_MULTIPLE, section1Name);
		TestValidation.IsTrue(addSMFieldInSection, 
				"'Select Multiple' field added successfully in 'Section'", 
				"Failed to Add Select Multiple field");

		String SMFieldName = "SelectMultipleAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefg";
		boolean setSMFieldName = fdp.setFieldName(FIELD_TYPES.SELECT_MULTIPLE,SMFieldName);
		TestValidation.IsTrue(setSMFieldName, 
				"Setted 'Select Multiple' field name", 
				"Could NOT set 'Select Multiple' field name");

				
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
	
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(SMFieldShortName, "Fails Audit"); 
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

	@Test(description = "Form Designer -  Audit  Forms - User can add Numeric fields to the form")
	public void TestCase_38411() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName5);
		
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, section1Name);
		TestValidation.IsTrue(addNumericFieldInSection, 
				"'Numeric' field added successfully in 'Section'", 
				"Failed to Add Numeric field");

		String NumFieldName = "NumericAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmn";
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,NumFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
				"Setted 'Numeric' field name", 
				"Could NOT set 'Numeric' field name");

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
				
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(NumFieldShortName, "Fails Audit"); 
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

	@Test(description = "Form Designer -  Audit Forms - User can add Date fields to the form")
	public void TestCase_38413() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName6);
		
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addDateFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.DATE, section1Name);
		TestValidation.IsTrue(addDateFieldInSection, 
				"'Date' field added successfully in 'Section'", 
				"Failed to Add Date field");

		String DateFieldName = "DateAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopq";
		boolean setDateFieldName = fdp.setFieldName(FIELD_TYPES.DATE,DateFieldName);
		TestValidation.IsTrue(setDateFieldName, 
				"Setted 'Date' field name", 
				"Could NOT set 'Date' field name");

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
		
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(dateFieldShortName, "Fails Audit"); 
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

	@Test(description = "Form Designer -  Audit Forms - User can add Time fields to the form")
	public void TestCase_38415() {
		

		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);
			
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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName7);
		
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addTimeFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.TIME, section1Name);
		TestValidation.IsTrue(addTimeFieldInSection, 
				"'Time' field added successfully in 'Section'", 
				"Failed to Add Time field");

		String TimeFieldName = "TimeAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopq";
		boolean setTimeFieldName = fdp.setFieldName(FIELD_TYPES.TIME,TimeFieldName);
		TestValidation.IsTrue(setTimeFieldName, 
				"Setted 'Time' field name", 
				"Could NOT set 'Time' field name");

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
		
		String failsQuestionnaireToggleButton = fdp.getStatusOfToggleButton(timeFieldShortName, "Fails Audit"); 
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

	@Test(description = "Form Designer -  Audit Forms - User can add DateTime fields to the form")
	public void TestCase_38417() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName8);
		
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addTimeFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.DATE_TIME, section1Name);
		TestValidation.IsTrue(addTimeFieldInSection, 
				"'Time' field added successfully in 'Section'", 
				"Failed to Add Time field");

		String DateTimeFieldName = "DateTimeAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklm";
		boolean setDateTimeFieldName = fdp.setFieldName(FIELD_TYPES.DATE_TIME,DateTimeFieldName);
		TestValidation.IsTrue(setDateTimeFieldName, 
				"Setted 'DateTime' field name", 
				"Could NOT set 'DateTime' field name");

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
		
		String failsAuditToggleButton = fdp.getStatusOfToggleButton(dateTimeFieldShortName, "Fails Audit"); 
		boolean compare6 = failsAuditToggleButton.contains("On");
		TestValidation.IsTrue(compare6, 
				"Default state of 'Fails Audit' toggle button is 'On'", 
				"Failed to validate default state of 'Fails Audit' toggle button");
		
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

	@Test(description = "Form Designer -  Audit Forms - User can add Sections to the form")
	public void TestCase_38398() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName9);
		
		
		String section1Name = "Section1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijkl";
		String section1FieldShortName = "Section1Abcdefghijklmnopq";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");
		
		fdp.FormNameLabel.click();

		boolean dragDropQuestionnaireGroupElement = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.QUESTION_GROUP, section1FieldShortName);
		TestValidation.IsTrue(dragDropQuestionnaireGroupElement, 
				"Drag-Drop Question Group in Section successfully", 
				"Failed to Drag-Drop Question Group in Section");
		
		String group1Name = "QuestionGroup1";
		boolean setGroupName = fdp.setFieldName("Question Group",group1Name);
		TestValidation.IsTrue(setGroupName, 
				"Setted 'Question Group' name", 
				"Could NOT set 'Question Group' name");
		
		fdp.FormNameLabel.click();
		
		boolean addSingleLineTextFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SINGLE_LINE_TEXT, group1Name);
		TestValidation.IsTrue(addSingleLineTextFieldInSection, 
				"'Single Line Text' field added successfully in 'Section'", 
				"Failed to Add Single Line Text field");

		String singleLineTextFieldName = "SingleLineTest1";
		boolean setSingleLineTextFieldName = fdp.setFieldName(FIELD_TYPES.SINGLE_LINE_TEXT,singleLineTextFieldName);
		TestValidation.IsTrue(setSingleLineTextFieldName, 
				"Setted 'Single Line Text' field name", 
				"Could NOT set 'Single Line Text' field name");
		
		fdp.FormNameLabel.click();

		boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, section1FieldShortName);
		TestValidation.IsTrue(addNumericFieldInSection, 
				"'Numeric' field added successfully in 'Section'", 
				"Failed to Add Numeric field");

		String NumFieldName = "Numeric1";
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,NumFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
				"Setted 'Numeric' field name", 
				"Could NOT set 'Numeric' field name");

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

	@Test(description="Form Designer - Audit Form - User can add a Note element to the form")
	public void TestCase_38389() {


		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName10);
		
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, section1Name);
		TestValidation.IsTrue(addNumericFieldInSection, 
				"'Numeric' field added successfully in 'Section'", 
				"Failed to Add Numeric field");

		String NumFieldName = "Numeric";
		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,NumFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
				"Setted 'Numeric' field name", 
				"Could NOT set 'Numeric' field name");

		fdp.FormNameLabel.click();

		String NoteData = "NoteDataAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnop";
		
		controlActions.dragdrop(fdp.NoteDbl, fdp.HeaderLevel);
		controlActions.sendKeys(fdp.NoteTxt,NoteData);
				
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
				"Saved form Successfully", 
				"Could NOT Save Form");
		
	}

	@Test(description = "Form Designer - Audit Forms - User can add Field/Question Groups to the form")
	public void TestCase_38400() {
		
		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName11);
		
		
		String section1Name = "Section1";
		String section1FieldShortName = "Section1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");
		
		fdp.FormNameLabel.click();

		boolean dragDropQuestionnaireGroupElement = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FORM_ELEMENTS.QUESTION_GROUP, section1FieldShortName);
		TestValidation.IsTrue(dragDropQuestionnaireGroupElement, 
				"Drag-Drop Question Group in Section successfully", 
				"Failed to Drag-Drop Question Group in Section");
		
		String group1Name = "QuestionGroup1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdef";
		boolean setGroupName = fdp.setFieldName("Question Group",group1Name);
		TestValidation.IsTrue(setGroupName, 
				"Setted 'Question Group' name", 
				"Could NOT set 'Question Group' name");
		
		String group1ShortName = "QuestionGroup1Abcdefghijk";
		fdp.FormNameLabel.click();
		
		boolean addSingleLineTextFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SINGLE_LINE_TEXT, group1ShortName);
		TestValidation.IsTrue(addSingleLineTextFieldInSection, 
				"'Single Line Text' field added successfully in 'Section'", 
				"Failed to Add Single Line Text field");

		String singleLineTextFieldName = "SingleLineTest1";
		boolean setSingleLineTextFieldName = fdp.setFieldName(FIELD_TYPES.SINGLE_LINE_TEXT,singleLineTextFieldName);
		TestValidation.IsTrue(setSingleLineTextFieldName, 
				"Setted 'Single Line Text' field name", 
				"Could NOT set 'Single Line Text' field name");
		
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

	@Test(description = "Audit - Identifier field functionality")
	public void TestCase_38505() {
		

		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);
		
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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName12);
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");		

		boolean addIdentifierFieldInSection1 = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FIELD_TYPES.IDENTIFIER, section1Name);
		TestValidation.IsTrue(addIdentifierFieldInSection1, 
				"'Identifier' field added successfully in 'Section'", 
				"Failed to Add Identifier field");

		String Identifier1FieldName = "Identifier1AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		boolean setIdentifierFieldValue1 = fdp.setFieldName("Identifier",Identifier1FieldName);
		TestValidation.IsTrue(setIdentifierFieldValue1, 
				"Setted 'Identifier' field name", 
				"Could NOT set 'Identifier' field name");
		
		boolean selectIdentifierInputType1 = fdp.selectIdentifierInputType(IDENTIFIER_INPUT_TYPE.FREE_TEXT);		
		TestValidation.IsTrue(selectIdentifierInputType1, 
				"Selected Identifier Input Type successfully", 
				"Failed to Select Identifier Input Type");

		String Identifier2FieldName = "Identifier2AbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghijklmnopqrstuvwxyzAbcdefghij";
		
		boolean addIdentifierFieldInSection2 = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FIELD_TYPES.IDENTIFIER, section1Name);
		TestValidation.IsTrue(addIdentifierFieldInSection2, 
				"'Identifier' field added successfully in 'Section'", 
				"Failed to Add Identifier field");

		boolean setIdentifierFieldValue2 = fdp.setFieldName("Identifier",Identifier2FieldName);
		TestValidation.IsTrue(setIdentifierFieldValue2, 
				"Setted 'Identifier' field name", 
				"Could NOT set 'Identifier' field name");	

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
		
		boolean addIdentifierFieldInSection3 = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FORM_ELEMENTS,FIELD_TYPES.IDENTIFIER, section1Name);
		TestValidation.IsTrue(addIdentifierFieldInSection3, 
				"'Identifier' field added successfully in 'Section'", 
				"Failed to Add Identifier field");

		boolean setIdentifierFieldValue3 = fdp.setFieldName("Identifier",Identifier3FieldName);
		TestValidation.IsTrue(setIdentifierFieldValue3, 
				"Setted 'Identifier' field name", 
				"Could NOT set 'Identifier' field name");	

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

		boolean releaseForm = fdp.releaseForm(auditFormName12);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+auditFormName12, 
				"Could NOT release form'");
				
	}
	
	@Test(description="Dynamic Forms - Form Designer can Preview a Audit Form. Verify the correct UI and Functionality for Preview of Check Form")
	public void TestCase_5125() {

		//Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

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

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName13);
		
		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");		

		boolean addSingleLineTextFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SINGLE_LINE_TEXT, section1Name);
		TestValidation.IsTrue(addSingleLineTextFieldInSection, 
				"Added 'Single Line Text' field in 'Section'", 
				"Could NOT add 'Single Line Text' field in 'Section'");

		boolean setSingleLineTextFieldFieldName = fdp.setFieldName("Single Line Text","SingleLineText1");
		TestValidation.IsTrue(setSingleLineTextFieldFieldName, 
				"Setted 'Single Line Text' field name", 
				"Could NOT set 'Single Line Text' field name");
		
		boolean addParagraphTextFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.PARAGRAPH_TEXT, section1Name);
		TestValidation.IsTrue(addParagraphTextFieldInSection, 
				"Added 'Paragraph Text' field in 'Section'", 
				"Could NOT add 'Paragraph Text' field in 'Section'");

		boolean setParagraphTextFieldName = fdp.setFieldName("Paragraph Text","ParagraphText1");
		TestValidation.IsTrue(setParagraphTextFieldName, 
				"Setted 'Paragraph Text' field name", 
				"Could NOT set 'Paragraph Text' field name");
		
		boolean addSelectOneFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SELECT_ONE, section1Name);
		TestValidation.IsTrue(addSelectOneFieldInSection, 
				"Added 'Select One' field in 'Section'", 
				"Could NOT add 'Select One' field in 'Section'");

		boolean setSelectOneFieldName = fdp.setFieldName("Select One","SelectOne1");
		TestValidation.IsTrue(setSelectOneFieldName, 
				"Setted 'Select One' field name", 
				"Could NOT set 'Select One' field name");

		controlActions.sendKeys(fdp.QuestionWeightInp, "200");

		boolean setValues1 = fdp.addOption("1", "150");
		boolean setValues2 = fdp.addOption("2", "200");
		TestValidation.IsTrue(setValues1&&setValues2, 
				"Successfully added values in Select One field", 
				"Failed to add value in Select One field");


		boolean addSelectMultipleFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SELECT_MULTIPLE, section1Name);
		TestValidation.IsTrue(addSelectMultipleFieldInSection, 
				"Added 'Select Multiple' field in 'Section'", 
				"Could NOT add 'Select Multiple' field in 'Section'");

		boolean setSelectMultipleFieldName = fdp.setFieldName("Select Multiple","SelectMultiple1");
		TestValidation.IsTrue(setSelectMultipleFieldName, 
				"Setted 'Select Multiple' field name", 
				"Could NOT set 'Select Multiple' field name");


		controlActions.sendKeys(fdp.QuestionWeightInp, "200");

		setValues1 = fdp.addOption("1", "80");
		setValues2 = fdp.addOption("2", "120");
		TestValidation.IsTrue(setValues1&&setValues2, 
				"Successfully added values in Select Multiple field", 
				"Failed to add value in Select Multiple field");
		
		boolean addNumericFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, section1Name);
		TestValidation.IsTrue(addNumericFieldInSection, 
				"Added 'Numeric' field in 'Section'", 
				"Could NOT add 'Numeric' field in 'Section'");

		boolean setNumericFieldName = fdp.setFieldName("Numeric","Numeric1");
		TestValidation.IsTrue(setNumericFieldName, 
				"Setted 'Numeric' field name", 
				"Could NOT set 'Numeric' field name");
		
		boolean addDateFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.DATE, section1Name);
		TestValidation.IsTrue(addDateFieldInSection, 
				"Added 'Date' field in 'Section'", 
				"Could NOT add 'Date' field in 'Section'");

		boolean setDateFieldName = fdp.setFieldName("Date","Date1");
		TestValidation.IsTrue(setDateFieldName, 
				"Setted 'Date' field name", 
				"Could NOT set 'Date' field name");
		
		boolean addTimeFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.TIME, section1Name);
		TestValidation.IsTrue(addTimeFieldInSection, 
				"Added 'Time' field in 'Section'", 
				"Could NOT add 'Time' field in 'Section'");

		boolean setTimeFieldName = fdp.setFieldName("Time","Time1");
		TestValidation.IsTrue(setTimeFieldName, 
				"Setted 'Time' field name", 
				"Could NOT set 'Time' field name");
		
		boolean addDateTimeFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.DATE_TIME, section1Name);
		TestValidation.IsTrue(addDateTimeFieldInSection, 
				"Added 'Date Time' field in 'Section'", 
				"Could NOT add 'Date Time' field in 'Section'");

		boolean setDateTimeFieldName = fdp.setFieldName("Date & Time","DateTime1");
		TestValidation.IsTrue(setDateTimeFieldName, 
				"Setted 'Date Time' field name", 
				"Could NOT set 'Date Time' field name");
		
		boolean clickSaveButton = fdp.clickSaveButton();
		TestValidation.IsTrue(clickSaveButton, 
				"Clicked On Save Button successfully", 
				"Failed to Click On Save Button");

		boolean clickPreviewButton = fdp.clickPreviewButton();
		TestValidation.IsTrue(clickPreviewButton, "Clicked Preview button Successfully",
				"Could NOT click Preview button");

		PreviewFormDetails pfd = new PreviewFormDetails();
		LinkedHashMap<String, List<String>> map1 = new LinkedHashMap<String, List<String>>();
		map1.put("SingleLineText1", Arrays.asList("Single Line Text Field"));
		map1.put("ParagraphText1", Arrays.asList("Paragraph Text Field"));
		map1.put("SelectOne1", Arrays.asList("1"));
		map1.put("SelectMultiple1", Arrays.asList("2"));
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
	
	

	@Test(description="Form Designer || Verify that  only selected(checked) documents are reflected in Header Related Documents")
	public void TestCase_5136() {


		// Open 'Form Designer'
		fdp = mainPage.clickFormDesignerMenu();

		controlActions.clickElement(fdp.SelectAuditFormLnk);

		// Selection of resource
		boolean selectResource = fdp.selectResource(resourceType, resourceCategoryValue, resourceInstanceValue1);
		TestValidation.IsTrue(selectResource, "Selected Resource successfully", "Failed to Select Resource");

		// Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg, "Design Form");
		TestValidation.IsTrue(clickOnNextButton, "Clicked On Next Button successfully",
				"Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, auditFormName14);

		String section1Name = "Section 1";
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);

		boolean setSectionName = fdp.setFieldName("Section",section1Name);
		TestValidation.IsTrue(setSectionName, 
				"Setted 'Section' name", 
				"Could NOT set 'Section' name");

		boolean addSingleLineTextFieldInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SINGLE_LINE_TEXT, section1Name);
		TestValidation.IsTrue(addSingleLineTextFieldInSection, 
				"'Single Line Text' field added successfully in 'Section'", 
				"Failed to Add Single Line Text field");

		String singleLineTextFieldName = "SingleLineText";
		boolean setSingleLineTextFieldName = fdp.setFieldName(FIELD_TYPES.SINGLE_LINE_TEXT,singleLineTextFieldName);
		TestValidation.IsTrue(setSingleLineTextFieldName, 
				"Setted 'Single Line Text' field name", 
				"Could NOT set 'Single Line Text' field name");

		fdp.FormNameLabel.click();


		String NoteData = "NoteData";

		controlActions.dragdrop(fdp.NoteDbl, fdp.HeaderLevel);
		controlActions.sendKeys(fdp.NoteTxt, NoteData);		
		
		boolean addRelatedDocs1 = fdp.addRelatedDocuments(docTypeName, documentNames);
		TestValidation.IsTrue(addRelatedDocs1, 
				"Documents is added in 'Related Docs'", 
				"Could NOT add documents 'Related Docs'"); 
		

		List<String> resourceInstances1 = Arrays.asList(resourceInstanceValue1, resourceInstanceValue5, resourceInstanceValue6);
		
		boolean resourceSelected1 = fdp.selectResourcesForRelatedDocuments(documentName1,resourceInstances1);
		TestValidation.IsTrue(resourceSelected1, 
				"Selected Resources for Related Docs Successfully", 
				"Could NOT Select Resources for Related Docs"); 
		
		List<String> resourceInstances2 = Arrays.asList(resourceInstanceValue4, resourceInstanceValue7);
		boolean resourceSelected2 = fdp.selectResourcesForRelatedDocuments(documentName2,resourceInstances2);
		TestValidation.IsTrue(resourceSelected2, 
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

		
		boolean releaseForm = fdp.releaseForm(auditFormName14);
		TestValidation.IsTrue(releaseForm, 
				"RELEASED form - "+auditFormName14, 
				"Could NOT release form'");

		FSQABrowserPage fbp = mainPage.clickFSQABrowserMenu();

		boolean selectResourceDropDownandNavigate =  fbp.selectResourceDropDownandNavigate(resourceType,"Forms");	
		TestValidation.IsTrue(selectResourceDropDownandNavigate, 
				"NAVIGATED to FSQA -> Forms Tab", 
				"Could NOT Navigate to FSQA -> Forms Tab");

		boolean applyFilterAndOpenForDetails = fbp.applyFilterAndOpenForDetails("Form Name", auditFormName14);
		TestValidation.IsTrue(applyFilterAndOpenForDetails, 
				"Verify form is displayed in Forms Tab", 
				"Form is NOT displayed in Forms Tab");
		
		FSQABrowserFormsPage fbfp = new FSQABrowserFormsPage(driver);
		
		boolean setResourceForForm1 = fbfp.setResourceForForm(resourceInstanceValue1);
		TestValidation.IsTrue(setResourceForForm1, 
				"Set Resource for Form Successfully", 
				"Could NOT Set Resource for Form");
		
		boolean verifyRelatedDocuments1 = fbfp.verifyRelatedDocuments(documentName1);
		TestValidation.IsTrue(verifyRelatedDocuments1, 
				"Verified Related Documents Successfully", 
				"Could NOT Verify Related Documents - " + documentName1);
		
		fbfp.FormCancelBtn.click();
		
		mainPage.Sync();
		
		boolean applyFilterAndOpenForDetails1 = fbp.applyFilterAndOpenForDetails("Form Name", auditFormName14);
		TestValidation.IsTrue(applyFilterAndOpenForDetails1, 
				"Verify form is displayed in Forms Tab", 
				"Form is NOT displayed in Forms Tab");
		
		boolean setResourceForForm2 = fbfp.setResourceForForm(resourceInstanceValue4);
		TestValidation.IsTrue(setResourceForForm2, 
				"Set Resource for Form Successfully", 
				"Could NOT Set Resource for Form");
		
		boolean verifyRelatedDocuments2 = fbfp.verifyRelatedDocuments(documentName2);
		TestValidation.IsTrue(verifyRelatedDocuments2, 
				"Verified Related Documents Successfully", 
				"Could NOT Verify Related Documents - " + documentName2);
		
		fbfp.FormCancelBtn.click();	
		
	}


	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

package com.project.safetychain.webapp.testcases;


import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.*;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.AGGREGATE_FUNCTION;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ELEMENT_TYPE;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.ElementProperties;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_SETTINGS;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FIELD_TYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMRESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FORMTYPES;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormDesignParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.FormFieldParams;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage.IDENTIFIER_TYPE;
import com.project.safetychain.webapp.pageobjects.ManageLocationPage.LocationInstanceParams;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class TCG_REG_FormDesigner_OtherFlows extends TestBase{

	ControlActions controlActions;
	HomePage hp;
	UserManagerPage ump;
	WorkGroupsPage wgp;
	RolesManagerPage rmp;
	FormDesignerPage fdp;
	LoginPage lp;
	FSQABrowserPage fbp;
	FSQABrowserFormsPage fbForms;
	FSQABrowserRecordsPage fbRecords;
	FormOperations formoperations;
	DocumentManagementPage documentManagement;

	public static String locationCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, supplierInstanceValue;
	public static String chkFormName, adtFormName, qstFormName;

	@BeforeClass(alwaysRun = true)
	public void groupInit() throws InterruptedException {
		
		locationCategoryValue = CommonMethods.dynamicText("LocCat_");
		supplierCategoryValue = CommonMethods.dynamicText("SuppCat_");//"SC_07.09_11.14.37";//
		locationInstanceValue = CommonMethods.dynamicText("LocInst_");
		supplierInstanceValue = CommonMethods.dynamicText("SuppInst_");//"SI_07.09_11.14.37";
		chkFormName =CommonMethods.dynamicText("CHK");
		adtFormName = CommonMethods.dynamicText("AuditForm_");
		qstFormName = CommonMethods.dynamicText("Qstn_");

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);

		hp = new HomePage(driver);
		ump = new UserManagerPage(driver);
		wgp = new WorkGroupsPage(driver);
		rmp = new RolesManagerPage(driver);
		fdp = new FormDesignerPage(driver);
		fbp = new FSQABrowserPage(driver);
		fbForms = new FSQABrowserFormsPage(driver);
		lp = new LoginPage(driver);
		formoperations = new FormOperations(driver);
		fbRecords = new FSQABrowserRecordsPage(driver);

		LoginPage lp = new LoginPage(driver);
		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

		//Category creation
		HashMap<String,String> categories = new HashMap<String, String>();
		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
		categories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue);
		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
		if(!rdp.createCategory(categories)) {
			TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue + supplierCategoryValue;
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

		//Resource creation - Supplier
		HashMap<String,Boolean> suppInstance = new HashMap<String, Boolean>();
		suppInstance.put(supplierInstanceValue, true);

		ResourceDetailParams rd2 = new ResourceDetailParams();
		rd2.CategoryName = supplierCategoryValue;
		rd2.CategoryType = RESOURCETYPES.SUPPLIERS;
		rd2.NumberFieldValue = 15;
		rd2.TextFieldValue = "LMN";
		rd2.InstanceNames = suppInstance;
		rd2.LocationInstanceValue = locationInstanceValue;	

		ManageResourcePage mrp = hp.clickResourcesMenu();
		if(!mrp.createResourceLinkLocation(rd2)) {
			TCGFailureMsg = "Could NOT create Supplier Instance for resource - " + supplierCategoryValue;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}
	
	
	// After Release form 
	@Test(description = " Check-FSQA - Form Tab -- Validate Save functionality and error messages displayed in case of missing required fields and errors ")
	public void TestCase_37726() throws Exception {
		String formName = CommonMethods.dynamicText("F_");
		String FT1_value = "Data";
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		// select check form 
		fdp.selectFormType(FORMTYPES.CHECK);
		
		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");
		
		// give form name
		controlActions.sendKeys(fdp.FormNameTxt, formName);
		
		// add 2 free text and name them
		String freeTextField1Name = "FreeText1";
		WebElement FreeTextFieldType1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextFieldType1, fdp.FormLevel);
		boolean setFreeText1 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextField1Name);		
		TestValidation.IsTrue(setFreeText1, 
							  "'Free Text field' added successfully" + freeTextField1Name, 
							   "Failed to Add Free Text Field" + freeTextField1Name);
		
		String freeTextField2Name = "FreeText2";
		WebElement FreeTextFieldType2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextFieldType2, fdp.FormLevel);
		boolean setFreeText2 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextField2Name);		
		TestValidation.IsTrue(setFreeText2, 
							  "'Free Text field' added successfully" + freeTextField2Name, 
							  "Failed to Add Free Text Field" +  freeTextField2Name);
		
		// click next button
		boolean clivkNextBtn1 = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn1, 
							 "Clicked on next button and Navigated to Release Page", 
				  			 "Could not click on next button and not Navigate to Release Page");
		// release form
		boolean releaseForm = fdp.releaseForm(formName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + formName, 
							 "Could Not release form -" + formName);
		// goto Homepage
		hp.clickHomepageLink();
		
		// go to fsqa - Forms
		boolean navigate1 = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate1, 
							"Navigated to FSQABrowser>FormsTab", 
							"Failed to navigate to FSQABrowser>Forms Tab");

		// find the form
		boolean applyfilter1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,formName);
		TestValidation.IsTrue(applyfilter1, 
	             			  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
	                          "Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		// fill only Freetext field
		// submit form
		// catch the error 
		// save the form 
		boolean partialFillSubmitNSave = formoperations.partialFillSubmitNSave(freeTextField1Name, FT1_value);
		TestValidation.IsTrue(partialFillSubmitNSave, 
							  "Partially filled and submit form, handling Error message and then saved the form", 
							  "Could Not partially fill and submit form");
	
		// fsqa-> forms neche there should be saved forms
		boolean openSavedForm = fbForms.openSavedForm_WithUniqueName(formName);
		TestValidation.IsTrue(openSavedForm, 
							  "Successfully opened Saved form with unique name -" + formName, 
				  			  "Failed to open saved form -" + formName);
	
		// open and verify the saved data persists
		boolean verifySavedData = formoperations.verifyFieldValue(freeTextField1Name, FT1_value);
		TestValidation.IsTrue(verifySavedData, 
			  				  "Verified the saved data as  -" + FT1_value + " for field -" + freeTextField1Name, 
			  			  	  "Failed to Verify the saved data as  -" + FT1_value + " for field -" + freeTextField1Name);
		
	}
	
	@Test(description = " Audit - FSQA - Form Tab -- Validate Save functionality and error messages displayed in case of missing required fields and errors")
	public void TestCase_38500() throws Exception {
		String formName = CommonMethods.dynamicText("AF_");
		String SLT1_value = "Data";
		String SingleLineTextFieldName = "SingleLineText";
		String numericFieldName = "Numeric";
		String sectionField = "Section";
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		// select check form 
		fdp.selectFormType(FORMTYPES.AUDIT);

		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");

		controlActions.sendKeys(fdp.FormNameTxt, formName);
		
		WebElement SectionElement1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FORM_ELEMENT,"FORMELEMENT","Section");
		controlActions.dragdrop(SectionElement1, fdp.FieldTypeDropAreaFormLevel);
		boolean setSectionName = fdp.setFieldName("Section",sectionField);
		TestValidation.IsTrue(setSectionName, 
							  "Setted 'Section' name",  
							  "Could NOT set 'Section' name");
		
		boolean addSLTextInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.SINGLE_LINE_TEXT, sectionField);
		TestValidation.IsTrue(addSLTextInSection, 
							  "Added 'Single Line Text' field in 'Section'", 
							  "Could NOT add 'Single Line Text' field in 'Section'");

		boolean setSLTName = fdp.setFieldName(FIELD_TYPES.SINGLE_LINE_TEXT,SingleLineTextFieldName);
		TestValidation.IsTrue(setSLTName, 
							  "Setted 'Single Line Text' field name ->" + SingleLineTextFieldName, 
							  "Could NOT set 'Single Line Text' field name -> " + SingleLineTextFieldName);
		
		boolean addNumericFieldTextInSection = fdp.dragDropElementInSectionGroup(ELEMENT_TYPE.FIELD_TYPES,FIELD_TYPES.NUMERIC, sectionField);
		TestValidation.IsTrue(addNumericFieldTextInSection, 
							  "Added 'Numeric' field in 'Section'", 
							  "Could NOT add 'Numeric' field in 'Section'");

		boolean setNumericFieldName = fdp.setFieldName(FIELD_TYPES.NUMERIC,numericFieldName);
		TestValidation.IsTrue(setNumericFieldName, 
							  "Setted 'Numeric' field name ->" + numericFieldName, 
							  "Could NOT set 'Numeric' field name -> " + numericFieldName);
		
		// click next button
		boolean clivkNextBtn1 = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn1, 
							 "Clicked on next button and Navigated to Release Page", 
				  			 "Could not click on next button and not Navigate to Release Page");
		// release form
		boolean releaseForm = fdp.releaseForm(formName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + formName, 
							 "Could Not release form -" + formName);
		// goto Homepage
		hp.clickHomepageLink();
		
		// go to fsqa - Forms
		boolean navigate1 = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate1, 
							 "Navigated to FSQABrowser>FormsTab", 
							 "Failed to navigate to FSQABrowser>Forms Tab");

		// find the form
		boolean applyfilter1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,formName);
		TestValidation.IsTrue(applyfilter1, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		boolean partialFillSubmitNSave = formoperations.partialFillSubmitNSave(SingleLineTextFieldName, SLT1_value);
		TestValidation.IsTrue(partialFillSubmitNSave, 
							  "Partially filled and submit form, handling Error message and then saved the form", 
							  "Could Not partially fill and submit form");
		
		// fsqa-> forms neche there should be saved forms
		boolean openSavedForm = fbForms.openSavedForm_WithUniqueName(formName);
		TestValidation.IsTrue(openSavedForm, 
							  "Successfully opened Saved form with unique name -" + formName, 
				  			  "Failed to open saved form -" + formName);
		
		// open and verify the saved data persists
		boolean verifySavedData = formoperations.verifyFieldValue(SingleLineTextFieldName, SLT1_value);
		TestValidation.IsTrue(verifySavedData, 
				  			  "Verified the saved data as  -" + SLT1_value + " for field -" + SingleLineTextFieldName, 
				  			  "Failed to Verify the saved data as  -" + SLT1_value + " for field -" + SingleLineTextFieldName);
	}
	
	@Test(description = "Questionnaire- FSQA - Document Tab  -- Validate Save functionality and error messages displayed in case of missing required fields and errors",enabled=true)
	public void TestCase_38501() throws Exception {
		String formName = CommonMethods.dynamicText("Q_");
		String FT1_value = "Data";
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		// select check form 
		fdp.selectFormType(FORMTYPES.QUESTIONNAIRE);
		
		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");
		
		// give form name
		controlActions.sendKeys(fdp.FormNameTxt, formName);
		
		// add 2 free text and name them
		String singleLineTextFieldName = "SingleLineText1";
		WebElement SingleLineTextField = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SINGLE_LINE_TEXT);
		controlActions.dragdrop(SingleLineTextField, fdp.FieldTypeDropAreaFormLevel);
		boolean setSingleLineTextFieldValue1 = fdp.setElementValueTextArea(FIELD_TYPES.SINGLE_LINE_TEXT, singleLineTextFieldName);		
		TestValidation.IsTrue(setSingleLineTextFieldValue1, 
							  "'Single Line Text' field added successfully", 
							   "Failed to Add Single Line Text field");
		
		String singleLineTextFieldName2 = "SingleLineText2";
		WebElement SingleLineTextField2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.SINGLE_LINE_TEXT);
		controlActions.dragdrop(SingleLineTextField2, fdp.FieldTypeDropAreaFormLevel);
		boolean setSingleLineTextFieldValue2 = fdp.setElementValueTextArea(FIELD_TYPES.SINGLE_LINE_TEXT, singleLineTextFieldName2);		
		TestValidation.IsTrue(setSingleLineTextFieldValue2, 
							  "'Single Line Text' field added successfully", 
							   "Failed to Add Single Line Text field");
		
		// click next button
		boolean clivkNextBtn1 = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn1, 
							 "Clicked on next button and Navigated to Release Page", 
				  			 "Could not click on next button and not Navigate to Release Page");
		// release form
		boolean releaseForm = fdp.releaseForm(formName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + formName, 
							 "Could Not release form -" + formName);
		// goto Homepage
		hp.clickHomepageLink();
		
		// go to fsqa - Forms
		boolean navigate1 = fbp.selectResourceDropDownandNavigate("Suppliers","Forms");
		TestValidation.IsTrue(navigate1, 
							"Navigated to FSQABrowser>FormsTab", 
							"Failed to navigate to FSQABrowser>Forms Tab");

		// find the form
		boolean applyfilter1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME,formName);
		TestValidation.IsTrue(applyfilter1, 
	             			  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
	                          "Failed to apply filter on" + COLUMNHEADER.FORMNAME);

		// fill only Freetext field
		// submit form
		// catch the error 
		// save the form 
		boolean partialFillSubmitNSave = formoperations.partialFillSubmitNSave(singleLineTextFieldName, FT1_value);
		TestValidation.IsTrue(partialFillSubmitNSave, 
							  "Partially filled and submit form, handling Error message and then saved the form", 
							  "Could Not partially fill and submit form");
	
		// fsqa-> forms neche there should be saved forms
		boolean openSavedForm = fbForms.openSavedForm_WithUniqueName(formName);
		TestValidation.IsTrue(openSavedForm, 
							  "Successfully opened Saved form with unique name -" + formName, 
				  			  "Failed to open saved form -" + formName);
	
		// open and verify the saved data persists
		boolean verifySavedData = formoperations.verifyFieldValue(singleLineTextFieldName, FT1_value);
		TestValidation.IsTrue(verifySavedData, 
			  				  "Verified the saved data as  -" + FT1_value + " for field -" + singleLineTextFieldName, 
			  			  	  "Failed to Verify the saved data as  -" + FT1_value + " for field -" + singleLineTextFieldName);
		
	}
	
	@Test(description = "Dynamic Forms - Form Designer can edit a previously saved form design")
	public void TestCase_5131() throws Exception {
		
		String formName = CommonMethods.dynamicText("F_");
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		// select check form 
		fdp.selectFormType(FORMTYPES.CHECK);
		
		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");
		
		// give form name
		controlActions.sendKeys(fdp.FormNameTxt, formName);
		
		String freeTextField1Name = "FreeText1";
		WebElement FreeTextFieldType1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextFieldType1, fdp.FormLevel);
		boolean setFreeText1 = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, freeTextField1Name);		
		TestValidation.IsTrue(setFreeText1, 
							  "'Free Text field' added successfully" + freeTextField1Name, 
							   "Failed to Add Free Text Field" + freeTextField1Name);
		
//		// Save Form
//		fdp.clickSaveButton();
//		
		// click next button
		boolean clivkNextBtn1 = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn1, 
							 "Clicked on next button and Navigated to Release Page", 
				  			 "Could not click on next button and not Navigate to Release Page");
		// release form
		boolean releaseForm = fdp.releaseForm(formName);
		TestValidation.IsTrue(releaseForm, 
							 "Successfully released form -" + formName, 
							 "Could Not release form -" + formName);
		
		fdp.navigateToHome();
		
		boolean navigate = fbp.selectResourceDropDownandNavigate(FORMRESOURCETYPES.SUPPLIERS,"Forms");
		TestValidation.IsTrue(navigate, 
							  "Navigated to FSQABrowser>FormsTab", 
					          "Failed to navigate to FSQABrowser>Forms Tab");
		
		boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER,formName);
		TestValidation.IsTrue(applyfilter, 
							  "Applied Filter on" + COLUMNHEADER.FORMNAME, 
							  "Failed to apply filter on" + COLUMNHEADER.FORMNAME);
		
		boolean editform = fbp.editSelectForm(formName);
		TestValidation.IsTrue(editform, 
							  "Form Edited", 
							  "Failed to edit form");
		
		hp.clickHomepageLink();
	}
	
	@Test(description = "Dynamic Forms - Form Designer can Save a designed Form as a Work In Process (WIP)")
	public void TestCase_5128() throws Exception {
		
		
		String formName = CommonMethods.dynamicText("Q_");
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		// select check form 
		fdp.selectFormType(FORMTYPES.CHECK);
		
		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");
		
		// give form name
		controlActions.sendKeys(fdp.FormNameTxt, formName);
		
		// Save Form
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
							 "Form Saved Successfully - " +formName, 
							 "Could NOT Save Form - " +formName);

		// go to release page
		boolean clivkNextBtn1 = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn1, 
							 "Clicked on next button and Navigated to Release Page", 
				  			 "Could not click on next button and not Navigate to Release Page");
		
		// Verify Saved Form
		boolean verifyFormOnReleasePage = fdp.isformPresentOnReleasePage(formName);
		TestValidation.IsTrue(verifyFormOnReleasePage, 
				 			  "Verified Saved Form " +formName + " on release Page", 
				 			  "Could NOT Verify Saved Form - " +formName+ " on release Page");
	}
	
	@Test(description = "Dynamic Forms - Form Designer can drag-n-dop Form Elements and Fields to the form, sections or fieldgroups")
	public void TestCase_5121() throws Exception {
		
		
		String formName = CommonMethods.dynamicText("F_");
		
		String docTypeName = CommonMethods.dynamicText("TDoc");
		String documentName = "Sample - Records.pdf";
		String docFilePath = System.getProperty("user.dir") + "\\test-data-files\\UploadDocuments\\"+documentName;
		
		DocumentManagementPage dmp = hp.clickdocumentsmenu();

		boolean uploadDocument = dmp.docUploadinDocType(docTypeName,docFilePath);
		TestValidation.IsTrue(uploadDocument, 
				  "Pass", 
				  "Fail");
		
		// ========== Form Fields ===========
		String numFieldForlLevel = CommonMethods.dynamicText("NUM");
		HashMap<String, List<String>> fields = new LinkedHashMap<String, List<String>>();
		fields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(CommonMethods.dynamicText("FT")));
		fields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(CommonMethods.dynamicText("PT")));
		fields.put(FIELD_TYPES.SELECT_ONE, Arrays.asList(CommonMethods.dynamicText("SO")));
		fields.put(FIELD_TYPES.SELECT_MULTIPLE, Arrays.asList(CommonMethods.dynamicText("SM")));
		fields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numFieldForlLevel));
		fields.put(FIELD_TYPES.DATE, Arrays.asList(CommonMethods.dynamicText("D")));
		fields.put(FIELD_TYPES.TIME, Arrays.asList(CommonMethods.dynamicText("T")));
		fields.put(FIELD_TYPES.DATE_TIME, Arrays.asList(CommonMethods.dynamicText("DT")));
		fields.put(FIELD_TYPES.AGGREGATE, Arrays.asList(CommonMethods.dynamicText("AGG")));
		fields.put(FIELD_TYPES.IDENTIFIER, Arrays.asList(CommonMethods.dynamicText("IDEN")));

		FormFieldParams ffp = new FormFieldParams();
		ffp.FieldDetails = fields;
		ffp.AgrregateFunction = AGGREGATE_FUNCTION.SUM;
		ffp.AgrregateSource = numFieldForlLevel;
		ffp.SetAggregateFunction = true;
		ffp.Repeat = Arrays.asList(Arrays.asList(numFieldForlLevel,"3"));
		ffp.IdentiferType = IDENTIFIER_TYPE.FREETEXT;
		ffp.IdentifierValue = Arrays.asList("L??");
		
		// ========== Field Group ===========
		HashMap<String, List<String>> group = new HashMap<String, List<String>>();
		group.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(CommonMethods.dynamicText("InFG")));
		

		FormFieldParams ffpGrp = new FormFieldParams();
		ffpGrp.FieldDetails = group;
		ffpGrp.GroupName = "Field Group";
		
		// ========== Section  ===========
		HashMap<String, List<String>> section = new HashMap<String, List<String>>();
		section.put(FIELD_TYPES.NUMERIC, Arrays.asList(CommonMethods.dynamicText("InSec")));
		
		FormFieldParams ffpSec= new FormFieldParams();
		ffpSec.FieldDetails = section;
		ffpSec.SectionName = "Section";
		
		
		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fp = new FormDesignParams();
		fp.FormType = FORMTYPES.CHECK;
		fp.FormName = formName;
		fp.SelectResources = suppResource;
		fp.DesignFields = Arrays.asList(ffp, ffpSec, ffpGrp);
		fp.HeaderNoteText = "A note";
		fp.HeaderDocTypeName = docTypeName;
		fp.HeaderDocName = documentName;
		
		try {
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fp);
		TestValidation.IsTrue(designForm, 
							 "Successfully Drag and Drop all Form element - Note, Related Docs, Section, Field Group to the Form" , 
				 			 "Could Not Drag and Drop all Form element to the Form");
		
		TestValidation.IsTrue(designForm, 
				 			  "Successfully Drag and Drop all Field types to the Form" , 
							  "Could Not Drag and Drop all Field types to the Form");
		} finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
	}
	
	
	// FRM UI_DISPLAY
	@Test(description = " Form Designer can edit Field \"Short Name\", \"Default Value\"and \"Repeat\" number on General Tab whenever Field is selected. (feature 326)")
	public void TestCase_5216() throws Exception {
		
		String formName = CommonMethods.dynamicText("Fm_");
		String fieldName = "AField";
		
		String shortNameInputFieldValue = "aNewName";
		String defaultInputValue = "DefaultInputField";
		String repeatInputValue = "3";
		
		// A form in edit mode
		fdp = hp.clickFormDesignerMenu();

		fdp.selectFormType(FORMTYPES.CHECK);

		//Selection of resource
		boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
		TestValidation.IsTrue(selectResource, 
							 "Selected Resource successfully", 
							 "Failed to Select Resource");

		//Go to 'Form Design'
		boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
		TestValidation.IsTrue(clickOnNextButton, 
							 "Clicked On Next Button successfully", 
							 "Failed to Click On Next Button");
		
		// enter form name
		controlActions.sendKeys(fdp.FormNameTxt, formName);
		
		// add a field at form level
		// name it 
		WebElement FreeTextFieldType = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.FREE_TEXT);
		controlActions.dragdrop(FreeTextFieldType, fdp.FormLevel);
		boolean setFieldName = fdp.setTextBoxValue(FIELD_TYPES.FREE_TEXT, fieldName);		
		TestValidation.IsTrue(setFieldName, 
							  "'Free Text field' added successfully - " + fieldName, 
							   "Failed to Add Free Text Field " + fieldName);
		
		
		controlActions.actionEnter();
		
		// select that field (probably already selected)
		// select general tab (probably already selected)
		
		// verify short name field and lable display
		boolean verifyShortNameFieldnLabel = fdp.isShortNameLabelNInputDisplayed();
		TestValidation.IsTrue(verifyShortNameFieldnLabel, 
							  "Verified Display of Short Name lable and input text box", 
							  "Could not verify Display of Short Name lable and input text box");
		
		// verify short name field editable
		boolean verifyShortNameInputEditable = fdp.setShortNameInputFieldValue(shortNameInputFieldValue);
		TestValidation.IsTrue(verifyShortNameInputEditable, 
							  "Verified the short name input text box is editable", 
							  "Failed to verify short name input text box is editable");
		
		// verify Default field and lable display
		boolean verifyDeafultFieldnLabel = fdp.isDefaultLabelNInputDisplay();
		TestValidation.IsTrue(verifyDeafultFieldnLabel, 
						      "Verified Display of Default Lable and Input text box", 
						      "Could NOT verify Display of Default Lable and Input text box");
		
		
		// verify Default field editable 
		boolean verifyDefaultInputEditable = fdp.setDefaultInputFieldValue(defaultInputValue);
		TestValidation.IsTrue(verifyDefaultInputEditable, 
							  "Verified the Default input text box is editable", 
							  "Failed to verify Default input text box is editable");
		
		
		// verify Repeat field and lable display
		boolean verifyRepeatFieldnLabel = fdp.isRepeatLabelNInputDisplay();
		TestValidation.IsTrue(verifyRepeatFieldnLabel, 
			      			  "Verified Display of Repeat Lable and Input text box", 
			      			   "Could NOT verify Display of Repeat Lable and Input text box");
		
		// verify Repeat field editable
		boolean verifyRepeatInputEditable = fdp.setRepeatCountForField(shortNameInputFieldValue, repeatInputValue);
		TestValidation.IsTrue(verifyRepeatInputEditable, 
				 			  "Verified the Repeat input text box is editable", 
				  			  "Failed to verify Repeat input text box is editable");
		
		// Save Form
		fdp.clickSaveButton();
		
		// click on next - navigate to release page
		boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		TestValidation.IsTrue(clivkNextBtn, 
							  "Clicked on next button and Navigated to Release Page", 
							  "Could not click on next button and not Navigate to Release Page");
		
		// edit form form release page
		boolean navigateBackToFormDesign = fdp.backToFormDesignFrmRelease(formName);
		TestValidation.IsTrue(navigateBackToFormDesign, 
							 "Successfully Navigated Back to Form Design from Release Page", 
							 "Could Not Navigate Back to Form Design from Release Page");
		
		boolean selectField = fdp.selectField(shortNameInputFieldValue);
		TestValidation.IsTrue(selectField, 
							  "Selected form field " + shortNameInputFieldValue, 
							  "Failed to select form field " + shortNameInputFieldValue);
		
		// get short name input text value and verify 
		boolean verifyShortNameInputValue = fdp.verifyShortNameInputFieldValue(shortNameInputFieldValue);
		TestValidation.IsTrue(verifyShortNameInputValue, 
							 "Verified when the form is saved the short name value - >" + shortNameInputFieldValue + " is saved to server as a part of field defination. Which is part of Form Defination", 
							 "Failed to verify, when the form is saved the short name value - >" + shortNameInputFieldValue + " is saved to server as a part of field defination. Which is part of Form Defination");
		
		// get Default Field input text value and verify 
		boolean verifyDefaultInputValue = fdp.verifyDefaultInputFieldValue(defaultInputValue);
		TestValidation.IsTrue(verifyDefaultInputValue, 
							 "Verified when the form is saved the Default input value - >" + defaultInputValue + " is saved to server as a part of field defination. Which is part of Form Defination", 
							 "Failed to verify, when the form is saved the Default input value - >" + defaultInputValue + " is saved to server as a part of field defination. Which is part of Form Defination");
		
		// get Repeat Field input text value and verify 
		boolean verifyRepeatInputValue = fdp.verifyRepeatInputFieldValue(repeatInputValue);
		TestValidation.IsTrue(verifyRepeatInputValue, 
							"Verified when the form is saved the Repeat value - >" + repeatInputValue + " is saved to server as a part of field defination. Which is part of Form Defination",  
							"Failed to verify, when the form is saved the Repeat value - >" + repeatInputValue + " is saved to server as a part of field defination. Which is part of Form Defination");
	}
	
	@Test(description = "Form Designer can edit Field Group properties on the Settings tab whenever Field Group is selected")
	public void TestCase_5213() throws Exception {
			
			String groupName = "AGroup";
			String tabName = "Settings";
			String chkFrmName = CommonMethods.dynamicText("FN_");
			
		    class Local {
		    	void saveNextBackSelectFieldNSettings() {
		    		boolean saveForm = fdp.clickSaveButton();
		    		TestValidation.IsTrue(saveForm, 
		    							 "Form Saved Successfully", 
		    							 "Could NOT Save Form");
		    		
		    		boolean clivkNextBtn = fdp.clickOnNextButton(fdp.ReleaseFormPg,"Release Form");
		    		TestValidation.IsTrue(clivkNextBtn, 
		    							  "Clicked on next button and Navigated to Release Page", 
		    							  "Could not click on next button and not Navigate to Release Page");
		    		
		    		boolean navigateBackToFormDesign = fdp.backToFormDesignFrmRelease(chkFrmName);
		    		TestValidation.IsTrue(navigateBackToFormDesign, 
		    							 "Successfully Navigated Back to Form Designer from Release Page", 
		    							 "Could Not Navigate Back to Form Designer from Release Page");
		    		
		    		boolean selectSettingsTab = fdp.selectPropertiesTabForField(groupName, tabName);
		    		TestValidation.IsTrue(selectSettingsTab, 
		    							 "Selected Properties Tab - " + tabName + "For field - " + groupName,
		    							 "Could Not Select Properties Tab - " + tabName + "For field - " + groupName);
		    	}
		    }
		    Local a = new Local();
			
		try {
			//Open 'Form Designer'
			fdp = hp.clickFormDesignerMenu();

			fdp.selectFormType(FORMTYPES.CHECK);

			//Selection of resource
			boolean selectResource = fdp.selectResource(FORMRESOURCETYPES.SUPPLIERS,supplierCategoryValue,supplierInstanceValue);
			TestValidation.IsTrue(selectResource, 
								 "Selected Resource successfully", 
								 "Failed to Select Resource");

			//Go to 'Form Design'
			boolean clickOnNextButton = fdp.clickOnNextButton(fdp.DesignFormPg,"Design Form");
			TestValidation.IsTrue(clickOnNextButton, 
								 "Clicked On Next Button successfully", 
								 "Failed to Click On Next Button");
			
			controlActions.sendKeys(fdp.FormNameTxt, chkFrmName);
			
			controlActions.dragdrop(fdp.GroupDbl, fdp.FormLevel);
			boolean setGroupName = fdp.setFieldName("Field Group",groupName);
			TestValidation.IsTrue(setGroupName, 
								 "Added Group - " + groupName + " at form level", 
								 "Could Not Add Group - " + groupName + " at form level")		;	
			
			boolean gotoSettingsTab = fdp.selectPropertiesTabs(tabName);
			TestValidation.IsTrue(gotoSettingsTab, 
								  "Selected setttings Tab from Properties Panel", 
								  "Could NOT Select setttings Tab from Properties Panel");
			
			boolean showGrpNameOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.SHOW_GROUP_NAME);
			TestValidation.IsTrue(showGrpNameOnOffCtrl, 
								  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_GROUP_NAME, 
								  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_GROUP_NAME);
			
			boolean showHintOnOffCtrl = fdp.fieldSettingOnOffTogglePresent(FIELD_SETTINGS.SHOW_HINT);
			TestValidation.IsTrue(showHintOnOffCtrl, 
								  "Verified Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_HINT, 
								  "Could Not verify Toggle Contorls On/Off Displayed on Settings Tab for - " + FIELD_SETTINGS.SHOW_HINT);
			
			// now change setting and verify effect
			WebElement showHintOnLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_ON_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_HINT);
			
			boolean turnHintOn = fdp.setSettingsProperty(showHintOnLbl,FIELD_SETTINGS.SHOW_HINT,"ON");
			TestValidation.IsTrue(turnHintOn, 
								  "Turned  ON setting " + FIELD_SETTINGS.SHOW_HINT + "for field - " + groupName , 
								  "Could Not turn ON setting " + FIELD_SETTINGS.SHOW_HINT + "for field - " + groupName);
			
			boolean hintTextAreaDisplayed = fdp.HintTxa.isDisplayed();
			TestValidation.IsTrue(hintTextAreaDisplayed, 
								  "Verified 'Show Hint Text Area' is Displayed when setting - " + FIELD_SETTINGS.SHOW_HINT + " is turned ON" , 
								  "Could NOT verify 'Show Hint Text Area' being Displayed when setting - " + FIELD_SETTINGS.SHOW_HINT + " is turned ON");
			
			a.saveNextBackSelectFieldNSettings();
			
		    boolean hintTextAreaDisplayed1 = fdp.HintTxa.isDisplayed(); 
		    TestValidation.IsTrue(hintTextAreaDisplayed1, 
		    					  "Verified that saved changes were saved as a part of Form Defination AS 'Show Hint Text Area' is still Displayed because Show Hint Setting was ON",
					  			  "Could Not Verify that saved changes were saved as a part of Form Defination AS 'Show Hint Text Area' is NOT Displayed");
		    // Round 2
			WebElement showHintOffLbl = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FLD_SETTING_OFF_LBL,
					"FIELDSETTING",FIELD_SETTINGS.SHOW_HINT);
			
		    boolean turnHintOff = fdp.setSettingsProperty(showHintOffLbl,FIELD_SETTINGS.SHOW_HINT,"OFF");
		    TestValidation.IsTrue(turnHintOff, 
		    					  "Turned  OFF setting " + FIELD_SETTINGS.SHOW_HINT + " for field - " + groupName , 
		    					  "Could Not turn OFF setting " + FIELD_SETTINGS.SHOW_HINT + " for field - " + groupName);

		   
		    boolean hintTextAreaDisplayed2 = fdp.HintTxa.isDisplayed();
		    TestValidation.IsFalse(hintTextAreaDisplayed2, 
					  			 "Verified 'Show Hint Text Area' is NOT Displayed when setting - " + FIELD_SETTINGS.SHOW_HINT + " is turned OFF" , 
					  			 "Could NOT verify 'Show Hint Text Area' NOT being Displayed when setting - " + FIELD_SETTINGS.SHOW_HINT + " is turned OFF");

		    a.saveNextBackSelectFieldNSettings();
			
		    boolean hintTextAreaDisplayed3 = fdp.HintTxa.isDisplayed();
		    TestValidation.IsFalse(hintTextAreaDisplayed3, 
					  			 "Verified that saved changes were saved as a part of Form Defination AS 'Show Hint Text Area' is still NOT Displayed because Show Hint Setting was OFF",
		  			  			 "Could Not Verify that saved changes were saved as a part of Form Defination AS 'Show Hint Text Area' is Displayed ");

			
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
	}

	@Test(description = "Field Bank Functionality")
	public void TestCase_38322() throws Exception {
		String formName = CommonMethods.dynamicText("FM_");
		String freeTextFieldName = CommonMethods.dynamicText("FT_");
		String paragraphTextFieldName = CommonMethods.dynamicText("PT_");
		String numericFieldName = CommonMethods.dynamicText("NF_");
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(freeTextFieldName));
		chkFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));
		chkFields.put(FIELD_TYPES.PARAGRAPH_TEXT, Arrays.asList(paragraphTextFieldName));

		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = formName;
		fdpChk.SelectResources = suppResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
							 "Successfully designed form with 3 Fields - Free Text, Paragraph Text and Numeric Field" , 
				 			 "Could Not design Form");
		
		boolean saveForm = fdp.clickSaveButton();
		TestValidation.IsTrue(saveForm, 
							 "Form Saved Successfully - " +formName, 
							 "Could NOT Save Form - " +formName);
		
		boolean verifyFieldBankFunctionality = fdp.verifyFieldNamesPresentInFieldBank(Arrays.asList(freeTextFieldName,paragraphTextFieldName,numericFieldName));
		TestValidation.IsTrue(verifyFieldBankFunctionality, 
							 "Verified Field Bank Functionality", 
							 "Could NOT Verify Field Bank Functionality");
		
	}

	@Test(description = " Dynamic Forms - Verify the Preview functionality for Fields Group that repeats more than once")
	public void TestCase_5123() throws Exception {
		
		String formName = CommonMethods.dynamicText("F_");
		String groupName = "Field Group";
		String freeTextFieldName = "FT";
		String numericFieldName = "PT";
		int repeatCount = 3;
		
		// ========== Field Group ===========
		HashMap<String, List<String>> group = new HashMap<String, List<String>>();
		group.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(freeTextFieldName));
		group.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));
		

		FormFieldParams ffpGrp = new FormFieldParams();
		ffpGrp.FieldDetails = group;
		ffpGrp.GroupName = groupName;
		ffpGrp.Repeat = Arrays.asList(Arrays.asList("Field Group","3"));
		
		
		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fp = new FormDesignParams();
		fp.FormType = FORMTYPES.CHECK;
		fp.FormName = formName;
		fp.SelectResources = suppResource;
		fp.DesignFields = Arrays.asList(ffpGrp);
		
		try {
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		
		boolean designForm = fdp.designForm(fp);
		TestValidation.IsTrue(designForm, 
							 "Successfully Drag and Drop all Form element to the Form" , 
				 			 "Could Not Drag and Drop all Form element to the Form");
		
		boolean clickPreviewButton = fdp.clickPreviewButton();
		TestValidation.IsTrue(clickPreviewButton, 
							  "Clicked Preview button Successfully", 
							  "Could NOT click Preview button");
		
		boolean verifyCountForGroup = formoperations.verifyRepeatCountForGroup(groupName, repeatCount);
		TestValidation.IsTrue(verifyCountForGroup, 
				  			 "Successfully verified that Field Group " +groupName+ " is repeated -" + repeatCount + " times in Form Preview", 
				  			 "Could Not verify that Field Group " +groupName+ " is repeated -" + repeatCount + " times in Form Preview");
		
		boolean verifyCountForFTField = formoperations.verifyRepeatCountForField(freeTextFieldName, repeatCount);
		TestValidation.IsTrue(verifyCountForFTField, 
				  			  "Successfully verified that Free Text field -" +freeTextFieldName +" in the Field Group " +groupName+ " is repeated -" + repeatCount + " times in Form Preview", 
				  			  "Could Not verify  that Free Text field -" +freeTextFieldName +" in the Field Group " +groupName+ " is repeated -" + repeatCount + " times in Form Preview");
		
		boolean verifyCountForNumericalField = formoperations.verifyRepeatCountForField(numericFieldName, repeatCount);
		TestValidation.IsTrue(verifyCountForNumericalField, 
				  			  "Successfully verified that Numeric field -" +numericFieldName +" in the Field Group " +groupName+ " is also repeated -" + repeatCount + " times in Form Preview", 
				  			  "Could Not verify  that Numeric field -" +numericFieldName +" in the Field Group " +groupName+ " is also repeated -" + repeatCount + " times in Form Preview");
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
	}

	@Test(description = "Form Designer can Jump to any Field or Form Element on a form via the Field Navigator panel")
	public void TestCase_5204() throws Exception {
		
		String formName = CommonMethods.dynamicText("FM_");
		List<String> fieldsInitialList = Arrays.asList("AField","BField","CField","DField","EField","FField");
		List<String> fieldsAfterAddList = Arrays.asList("AField","BField","CField","DField","EField","FField","GField","HField");
		List<String> fieldsAfterDeleteList = Arrays.asList("AField","DField","EField","FField","GField","HField");
		
		String fieldAdd1 = "GField";
		String fieldAdd2 = "HField";
		
		String deleteField1 = "BField";
		String deleteField2 = "CField";
		
		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.FREE_TEXT,fieldsInitialList );

		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = formName;
		fdpChk.SelectResources = suppResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		
		try {
			// navigate to form designer
			fdp = hp.clickFormDesignerMenu();
			
			boolean designForm = fdp.designForm(fdpChk);
			TestValidation.IsTrue(designForm, 
								 "Successfully designed form with 3 Fields - Free Text, Paragraph Text and Numeric Field" , 
					 			 "Could Not design Form");
			
			boolean selectFieldFormNavigator = fdp.selectFieldFormNavigator(fieldsInitialList.get(5));
			TestValidation.IsTrue(selectFieldFormNavigator, 
								  "Successfully Clicked Field - " + fieldsInitialList.get(5) + " from Form Navigator" , 
								  "Failed to Click Field - " + fieldsInitialList.get(5) + " from Form Navigator");
			
			boolean isFieldSelected = fdp.isFieldSelected(fieldsInitialList.get(5));
			TestValidation.IsTrue(isFieldSelected, 
								  "Verified Layout panel is automatically scrolled to that part of the form and field -" + fieldsInitialList.get(5) + " is automatically selected", 
								  "Failed to verify that > Layout panel is automatically scrolled to that part of the form and field -" + fieldsInitialList.get(5) + " is automatically selected");
			
			
			WebElement num1 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(num1, fdp.FormLevel);
			boolean setNumFieldValue1 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, fieldAdd1);		
			TestValidation.IsTrue(setNumFieldValue1, 
								 "Added numeric field, named it : - " + fieldAdd1, 
								 "Could Not Add numeric field - " + fieldAdd1 );
			
			WebElement num2 = controlActions.perform_GetElementByXPath(FormDesignerPageLocator.FIELD_TYPE,"FIELDTYPE",FIELD_TYPES.NUMERIC);
			controlActions.dragdrop(num2, fdp.FormLevel);
			boolean setNumFieldValue2 = fdp.setTextBoxValue(FIELD_TYPES.NUMERIC, fieldAdd2);		
			TestValidation.IsTrue(setNumFieldValue2, 
								 "Added numeric field, named it : - " + fieldAdd2, 
								 "Could Not Add numeric field - " + fieldAdd2);
			
			List<String> afterFieldsAdded = fdp.getListOfFrmLvlFieldsFormNavigator();
			TestValidation.IsTrue(afterFieldsAdded.equals(fieldsAfterAddList), 
								 "Verified if Fields are added on the Form using the Form Layout Panel, the Field Navigator automatically updates", 
								 "Failed to verify > if Fields are added on the Form using the Form Layout Panel, the Field Navigator automatically updates");
			
			boolean toDeleteField1 = fdp.deleteField(deleteField1);
			TestValidation.IsTrue(toDeleteField1, 
								  "Successfully Deleted Field - " + deleteField1, 
								  "Failed to Delete field - " + deleteField1);
			
			boolean toDeleteField2 = fdp.deleteField(deleteField2);
			TestValidation.IsTrue(toDeleteField2, 
								  "Successfully Deleted Field - " + deleteField2, 
								  "Failed to Delete field - " + deleteField2);
			
			List<String> afterFieldsDeleted = fdp.getListOfFrmLvlFieldsFormNavigator();
			TestValidation.IsTrue(afterFieldsDeleted.equals(fieldsAfterDeleteList), 
								  "Verified if Fields are Removed from the Form using the Form Layout Panel, the Field Navigator automatically updates", 
								  "Failed to verify > if Fields are Removed from the Form using the Form Layout Panel, the Field Navigator automatically updates");

		} finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
	}
	
	@Test(description = "Dynamic Forms -  Form Designer - Verify that scroll bar should working in Related Resources panel", priority = -1)
	public void TestCase_5134() throws Exception {
		String locationCategoryValue = CommonMethods.dynamicText("LCat");
		String locationInstanceValue = CommonMethods.dynamicText("LInst");
		
		String resourceCategoryValue = CommonMethods.dynamicText("RCat");
		String resourceInstanceValue1 = CommonMethods.dynamicText("Inst1");
		String resourceInstanceValue2 = CommonMethods.dynamicText("Inst2");
		String resourceInstanceValue3 = CommonMethods.dynamicText("Inst3");
		String resourceInstanceValue4 = CommonMethods.dynamicText("Inst4");
		String resourceInstanceValue5 = CommonMethods.dynamicText("Inst5");
		
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		
		ApiUtils apiUtils = new ApiUtils();
		
		// DEFIING RESOURCES AND LOCATIONS
		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(resourceCategoryValue, Arrays.asList(resourceInstanceValue1, resourceInstanceValue2,resourceInstanceValue3,resourceInstanceValue4,resourceInstanceValue5));

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

		// CREATING and LINKING RESOURECES LOCATIONS and USERS
		List<String> userList = Arrays.asList(adminUsername);
		
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,RESOURCE_TYPES.CUSTOMERS);

//		// Category creation
//		HashMap<String, String> categories = new HashMap<String, String>();
//		categories.put(CATEGORYTYPES.CUSTOMERS, resourceCategoryValue);
//		categories.put(CATEGORYTYPES.LOCATION, locationCategoryValue);
//		ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
//		
//		boolean createCategory = rdp.createCategory(categories);
//		TestValidation.IsTrue(createCategory, 
//							  "Created Customer Category - " + resourceCategoryValue + " and Location Category - " + locationCategoryValue , 
//					          "Could NOT create Resource categories");
//
//		// Location instance
//		HashMap<String, Boolean> locInstance = new HashMap<String, Boolean>();
//		locInstance.put(locationInstanceValue, true);
//		LocationInstanceParams lip = new LocationInstanceParams();
//		lip.CategoryName = locationCategoryValue;
//		lip.NumberFieldValue = 10;
//		lip.TextFieldValue = "XYZ";
//		lip.InstanceName = locInstance;
//		ManageLocationPage mlp = hp.clickLocationsMenu();
//		
//		
//		boolean locationInst = mlp.createLocation(lip);
//		TestValidation.IsTrue(locationInst, 
//				  			  "Created Location Instance - " + locationInstanceValue + " for Location Category - "+ locationCategoryValue, 
//				  			  "Could NOT create Location instance for Location - " + locationCategoryValue);
//
//
//		// Resource creation
//		HashMap<String, Boolean> instances = new HashMap<String, Boolean>();
//		instances.put(resourceInstanceValue1, true);
//		instances.put(resourceInstanceValue2, true);
//		instances.put(resourceInstanceValue3, true);
//		instances.put(resourceInstanceValue4, true);
//		instances.put(resourceInstanceValue5, true);
//		ResourceDetailParams rd = new ResourceDetailParams();
//		rd.CategoryName = resourceCategoryValue;
//		rd.CategoryType = RESOURCETYPES.CUSTOMERS;
//		rd.NumberFieldValue = 30;
//		rd.TextFieldValue = "ABC";
//		rd.InstanceNames = instances;
//		rd.LocationInstanceValue = locationInstanceValue;
//		ManageResourcePage mrp = hp.clickResourcesMenu();
//		
//		boolean resourceInstanceCreation = mrp.createResourceLinkLocation(rd);
//		TestValidation.IsTrue(resourceInstanceCreation, 
//				  "Created Instances for resources", 
//				  "Could NOT create Instances for resource - " + resourceCategoryValue);
//		
//		controlActions.refreshDisplayedPage();
		//===================== FORM DESIGNER =====================//
		String chkFrmName = CommonMethods.dynamicText("CF_");
		String numericFieldName = "Num1";
		String fieldMin1 = "10", fieldTar1 = "20", fieldMax1 = "30";
		String fieldMin2 = "1", fieldTar2 = "2", fieldMax2 = "3";
		
		HashMap<String, List<String>> custResource = new HashMap<String, List<String>>();
		custResource.put(FORMRESOURCETYPES.CUSTOMERS, Arrays.asList(resourceCategoryValue));
		
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericFieldName));
		
		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;
		
		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = chkFrmName;
		fdpChk.SelectResources = custResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		
		// navigate to form designer
		
		fdp = hp.clickFormDesignerMenu();

		try {
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
	                          "Successfully Designed Form - " +chkFrmName + " with Numeric Fields -" + numericFieldName, 
	                          "Failed to design from with Numeric Field");
		
		boolean setCompliance1 =  fdp.setNumericAggragateFieldComplianceForAResourceInstance(numericFieldName, resourceInstanceValue1, fieldMin1, fieldMax1, fieldTar1, null);
		TestValidation.IsTrue(setCompliance1, 
				  			  "Set Compliance for field - " + numericFieldName + " for Resource - " + resourceInstanceValue1, 
				              "Failed to set compliance for field  - " + numericFieldName + " for Resource - " + resourceInstanceValue1);
		
		boolean setCompliance2 = fdp.setNumericAggragateFieldComplianceForAResourceInstance(numericFieldName, resourceInstanceValue5, fieldMin2, fieldMax2, fieldTar2, null);
		TestValidation.IsTrue(setCompliance2, 
							  "Set Compliance for field - " + numericFieldName + " for Resource - " + resourceInstanceValue5, 
					          "Failed to set compliance for field  - " + numericFieldName + " for Resource - " + resourceInstanceValue5);

		boolean clickPreview = fdp.clickPreviewButton();
		TestValidation.IsTrue(clickPreview, 
				  			  "Clicked Preview Buttom", 
				              "Failed to click Prview Button");
		
		boolean selectResource1 = fdp.selectResourceInPreviewForm(resourceInstanceValue1);
		TestValidation.IsTrue(selectResource1, 
							  "Selected Resource - "+ resourceInstanceValue1, 
				  			  "Failed to select Resource - " + resourceInstanceValue1);
		
		boolean verifyMinMax1 = formoperations.verifyShowMinMaxForNumField(numericFieldName, fieldMin1, fieldMax1);
		TestValidation.IsTrue(verifyMinMax1, 
				  		     "Verified Correct Min/Max Compliance for field - " + numericFieldName + " of Resource - " + resourceInstanceValue1 , 
				  		     "Failed to verify Correct Min/Max Compliance for field - " + numericFieldName + " of Resource - " + resourceInstanceValue1);
		
		
		boolean selectResource2 = fdp.selectResourceInPreviewForm(resourceInstanceValue5);
		TestValidation.IsTrue(selectResource2, 
				  			  "Selected Resource - "+ resourceInstanceValue5, 
				  			  "Failed to select Resource - " + resourceInstanceValue5);
		
		boolean verifyMinMax2 = formoperations.verifyShowMinMaxForNumField(numericFieldName, fieldMin2, fieldMax2);
		TestValidation.IsTrue(verifyMinMax2, 
	  		     			 "Verified Correct Min/Max Compliance for field - " + numericFieldName + " of Resource - " + resourceInstanceValue5 , 
	  		     			 "Failed to verify Correct Min/Max Compliance for field - " + numericFieldName + " of Resource - " + resourceInstanceValue5);

		
		boolean closePreview =  fdp.clickClosePreviewBtn();
		TestValidation.IsTrue(closePreview, 
				  			  "Closed Preview", 
				  			  "Failed to Close Preview");
		
	}finally {
		boolean navHome = fdp.navigateToHome();
		TestValidation.IsTrue(navHome, 
				  			 "Navigated to Homepage", 
				  			 "Failed to navigate to hompage");
		}
		
	}
	
	@Test(description = "Form Designer can reorder Fields and Form Elements using the Field Navigator (feature 325)")
	public void TestCase_5205() throws Exception {
		
		String formName = CommonMethods.dynamicText("F");
		String FTField = "FT123";
		String numericField = "Num321";

		// Form Level
		HashMap<String, List<String>> chkFields = new HashMap<String, List<String>>();
		chkFields.put(FIELD_TYPES.FREE_TEXT, Arrays.asList(FTField) );
		chkFields.put(FIELD_TYPES.NUMERIC, Arrays.asList(numericField));

		FormFieldParams ffpChk = new FormFieldParams();
		ffpChk.FieldDetails = chkFields;

		HashMap<String, List<String>> suppResource = new HashMap<String, List<String>>();
		suppResource.put(FORMRESOURCETYPES.SUPPLIERS, Arrays.asList(supplierCategoryValue));

		FormDesignParams fdpChk = new FormDesignParams();
		fdpChk.FormType = FORMTYPES.CHECK;
		fdpChk.FormName = formName;
		fdpChk.SelectResources = suppResource;
		fdpChk.DesignFields = Arrays.asList(ffpChk);
		try {
		// navigate to form designer
		fdp = hp.clickFormDesignerMenu();
		boolean designForm = fdp.designForm(fdpChk);
		TestValidation.IsTrue(designForm, 
				"Successfully Designed Form - " +formName + " with Numeric Fields -" + formName, 
				"Failed to design from with Numeric and Free Text Field");
		
		List<String> beforeReorderList = fdp.getListOfFrmLvlFieldsFormNavigator();
		
		boolean reorder = fdp.dragFieldAboveOtherFieldInFormNavigator(beforeReorderList.get(1),beforeReorderList.get(0));
		TestValidation.IsTrue(reorder, 
				  			  "Pass", 
				  			  "Fail");
		
		List<String> afterReorderList = fdp.getListOfFrmLvlFieldsFormNavigator();
		
		Collections.reverse(beforeReorderList);
		TestValidation.IsTrue((afterReorderList.equals(beforeReorderList)), 
				   		     "Pass", 
				             "Fail");
		}finally {
			boolean navHome = fdp.navigateToHome();
			TestValidation.IsTrue(navHome, 
					  			 "Navigated to Homepage", 
					  			 "Failed to navigate to hompage");
		}
		
		
	}
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}

}

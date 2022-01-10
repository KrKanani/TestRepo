package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.project.safetychain.webapp.pageobjects.HomePage;
import com.project.safetychain.webapp.pageobjects.LoginPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.COLUMN_SETTING;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ColumnNames;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExportFormDefinitionFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.IMPORT_STATUS;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportFormDefinitionFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.FormDefinitionFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.FormDeftnFieldName;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DPT_FormDefinitionFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	
	public static String locationCategoryValue, customerCategoryValue;
	public static String locationInstanceValue, customerInstanceValue;
	// Form and Fields
	public static String chkFormName, qstFormName, adtFormName;
	public static String paragraphFieldNm = "Paragraph", dateTimeFieldNm = "DateTime", 
			SOFieldNm = "SelectOne", SMFieldNm = "SelectMultiple", sectionAFieldNm = "Section A", 
			dateFieldNm = "Date", timeFieldNm = "Time", sectionBFieldNm = "Section B", 
			numericFieldNm = "Numeric", SLTFieldNm = "SingleLineText", groupAFieldNm = "Group A", 
			groupBFieldNm = "Group B", SLTFieldHint = "Sample Hint for Single Line Text", freeTextFieldNm = "FreeText";
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		
		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		customerCategoryValue = CommonMethods.dynamicText("CCat1");
		customerInstanceValue = CommonMethods.dynamicText("CInst1");
		chkFormName = CommonMethods.dynamicText("ChkForm");
		qstFormName = CommonMethods.dynamicText("QstForm");
		adtFormName = CommonMethods.dynamicText("AdtForm");
		
		// ------------------------------------------------------------------------------------------------
		// API Implementation
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		ApiUtils apiUtils = new ApiUtils();
		Auth auth = new Auth();
		
		// ------------------------------------------------------------------------------------------------
		// API - Location & Resource Creation and Linking
		List<String> userList = Arrays.asList(adminUsername);

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(customerCategoryValue, Arrays.asList(customerInstanceValue));
		
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check
		// API - Add fields to form
		FormFieldParams paragraphField = new FormFieldParams();
		paragraphField.DPTFieldType = DPT_FIELD_TYPES.PARAGRAPH_TEXT; 
		paragraphField.Name = paragraphFieldNm;

		FormFieldParams dateTimeField = new FormFieldParams();
		dateTimeField.DPTFieldType = DPT_FIELD_TYPES.DATE_TIME; 
		dateTimeField.Name = dateTimeFieldNm;
		
		FormFieldParams SOField = new FormFieldParams();
		SOField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		SOField.Name = SOFieldNm;
		SOField.SelectOptions = Arrays.asList("1","2","3","4");

		FormFieldParams SMField = new FormFieldParams();
		SMField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		SMField.Name = SMFieldNm;
		SMField.SelectOptions = Arrays.asList("10","20","30","40");
		
		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdChk = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormChk = new FormParams();
		fpFormChk.FormId = formIdChk;
		fpFormChk.FormName = chkFormName;
		fpFormChk.type = DPT_FORM_TYPES.CHECK;
		fpFormChk.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpFormChk.ResourceCategoryNm = customerCategoryValue;
		fpFormChk.ResourceInstanceNm = customerInstanceValue;
		fpFormChk.formElements = Arrays.asList(paragraphField, dateTimeField, SOField, SMField);
		fpFormChk.CustomerResources = resourceCatMap;

		formCreationWrapper.API_Wrapper_Forms(fpFormChk);
		
		// ------------------------------------------------------------------------------------------------
		// Reset Access Token 
		auth.setAccessToken();
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Audit
		// API - Add fields to form
		
		// Section A
		Element_Types SectionA = new Element_Types();
		SectionA.ElementType = DPT_FIELD_TYPES.SECTION;
		SectionA.Name = sectionAFieldNm;

		FormFieldParams dateField = new FormFieldParams();
		dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
		dateField.Name = dateFieldNm;
		
		FormFieldParams timeField = new FormFieldParams();
		timeField.DPTFieldType = DPT_FIELD_TYPES.TIME;
		timeField.Name = timeFieldNm;

		SectionA.formFieldParams = Arrays.asList(dateField, timeField);
		
		// Section B
		Element_Types SectionB = new Element_Types();
		SectionB.ElementType = DPT_FIELD_TYPES.SECTION;
		SectionB.Name = sectionBFieldNm;

		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = numericFieldNm;
		
		FormFieldParams singleLineTextField = new FormFieldParams();
		singleLineTextField.DPTFieldType = DPT_FIELD_TYPES.SINGLE_LINE_TEXT;
		singleLineTextField.Name = SLTFieldNm;
		singleLineTextField.ShowHint = "true";
		singleLineTextField.Hint = SLTFieldHint;

		SectionB.formFieldParams = Arrays.asList(numericField, singleLineTextField);

		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdAdt = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormAdt = new FormParams();
		fpFormAdt.FormId = formIdAdt;
		fpFormAdt.FormName = adtFormName;
		fpFormAdt.type = DPT_FORM_TYPES.AUDIT;
		fpFormAdt.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpFormAdt.ResourceCategoryNm = customerCategoryValue;
		fpFormAdt.ResourceInstanceNm = customerInstanceValue;
		fpFormAdt.CustomerResources = resourceCatMap;
		fpFormAdt.SectionElements = Arrays.asList(SectionA, SectionB);

		formCreationWrapper.API_Wrapper_Forms(fpFormAdt);

		// ------------------------------------------------------------------------------------------------
		// Reset Access Token 
		auth.setAccessToken();
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Questionnaire
		// API - Add fields to form
		
		Element_Types fieldGroup1 = new Element_Types();
		fieldGroup1.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
		fieldGroup1.Name = groupAFieldNm;
		fieldGroup1.formFieldParams = Arrays.asList(SMField, SOField);

		Element_Types fieldGroup2 = new Element_Types();
		fieldGroup2.ElementType = DPT_FIELD_TYPES.FIELD_GROUP;
		fieldGroup2.Name = groupBFieldNm;
		fieldGroup2.formFieldParams = Arrays.asList(singleLineTextField);
		
		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdQst = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormQst = new FormParams();
		fpFormQst.FormId = formIdQst;
		fpFormQst.FormName = qstFormName;
		fpFormQst.type = DPT_FORM_TYPES.QUESTIONNAIRE;
		fpFormQst.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpFormQst.ResourceCategoryNm = customerCategoryValue;
		fpFormQst.ResourceInstanceNm = customerInstanceValue;
		fpFormQst.formElements = Arrays.asList(numericField);
		fpFormQst.CustomerResources = resourceCatMap;
		fpFormQst.SectionElements = Arrays.asList(fieldGroup1, fieldGroup2);

		formCreationWrapper.API_Wrapper_Forms(fpFormQst);

		// ------------------------------------------------------------------------------------------------
		// WEB Application code starts here

		driver = launchbrowser();
		controlActions = new ControlActions(driver);
		controlActions.getUrl(applicationUrl);
		
		hp = new HomePage(driver);
		lp = new LoginPage(driver);

		hp = lp.performLogin(adminUsername, adminPassword);
		if(hp.error) {
			TCGFailureMsg = "Could NOT login to application";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
	}

	@Test(description = "DPT || Verify single and multiple data extract for \"Form Definition\" extract type")
	public void TestCase_38290() {
		
		try {
			
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn1 = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn1, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup1 = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup1, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType1 = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType1, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);

			List<String> items1 = Arrays.asList(adtFormName);
			WebElement element1 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectSingleForm = diep.selectItemsInGrid(element1,items1);
			TestValidation.IsTrue(selectSingleForm, 
								  "SELECTED form " + adtFormName, 
								  "Failed to Select form " + adtFormName);
			
			boolean clickExtract1 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract1, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts1 = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts1, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			boolean exportBtn2 = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn2, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup2 = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup2, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType2 = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType2, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			List<String> items2 = Arrays.asList(chkFormName, adtFormName, qstFormName);
			WebElement element2 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectMultipleForms = diep.selectItemsInGrid(element2,items2);
			TestValidation.IsTrue(selectMultipleForms, 
								  "SELECTED forms - " + chkFormName + ", " + adtFormName + " and " + qstFormName, 
								  "Failed to Select forms - " + chkFormName + ", " + adtFormName + " and " + qstFormName);
			
			boolean clickExtract2 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract2, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts2 = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts2, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for \"Form Definition\" for correct "
			+ "& incorrect data in same file")
	public void TestCase_38293() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			List<String> items = Arrays.asList(chkFormName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectChkForm = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectChkForm, 
								  "SELECTED form " + chkFormName, 
								  "Failed to Select form " + chkFormName);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			boolean importTab = diep.selectImportTab();
			TestValidation.IsTrue(importTab, 
								  "SWITCHED to DPT Import tab", 
								  "Failed to Switch to DPT Import Tab");
			
			boolean openImportPopup = diep.openNewImportDataPopUp();
			TestValidation.IsTrue(openImportPopup, 
								  "OPENED Import data popup", 
								  "Failed to Open Import data popup");
			
			boolean setImportType = diep.selectImportType(ImportType.FORM_DEFINITION);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.FORM_DEFINITION, 
								  "Failed to Set Import type to " + ImportType.FORM_DEFINITION);
			
			HashMap<String, String> fieldProp1 = new HashMap<String, String>(); 
			fieldProp1.put(FormDeftnFieldName.IS_REQUIRED, "FALSE");
			// Update
			FormDefinitionFields fdf1 = new FormDefinitionFields();
			fdf1.fieldName = paragraphFieldNm;
			fdf1.fieldProperties = fieldProp1;
			
			HashMap<String, String> fieldProp2 = new HashMap<String, String>(); 
			fieldProp2.put(FormDeftnFieldName.TYPE, "Select");
			fieldProp2.put(FormDeftnFieldName.HINT, "Hint for Select One");
			// Update
			FormDefinitionFields fdf2 = new FormDefinitionFields();
			fdf2.fieldName = SOFieldNm;
			fdf2.fieldProperties = fieldProp2;
			
			HashMap<String, String> fieldProp3 = new HashMap<String, String>(); 
			fieldProp3.put(FormDeftnFieldName.SHORT_NAME, freeTextFieldNm);
			fieldProp3.put(FormDeftnFieldName.TYPE, "FreeText");
			fieldProp3.put(FormDeftnFieldName.ELEMENT_TYPE, "Field");
			fieldProp3.put(FormDeftnFieldName.PARENT, "N/A");
			fieldProp3.put(FormDeftnFieldName.IS_REQUIRED, "TRUE");
			// New Entry
			FormDefinitionFields fdf3 = new FormDefinitionFields();
			fdf3.newEntry = true;
			fdf3.fieldName = freeTextFieldNm;
			fdf3.fieldProperties = fieldProp3;

			HashMap<String, List<FormDefinitionFields>> formDetails = new HashMap<String, List<FormDefinitionFields>>();
			formDetails.put(chkFormName, Arrays.asList(fdf1, fdf2, fdf3));
			
			ImportFormDefinitionFields ifdf = new ImportFormDefinitionFields();
			ifdf.fileName = filePath;
			ifdf.formDetails = formDetails;
			
			boolean updateExcel = diep.updateFormDefinitionExcelWithFields(ifdf);
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of new and existing data", 
								  "Failed to Update excel sheet");
			
			boolean uploadExcel = diep.UploadFileImport(filePath);
			boolean clickUploadBtn = diep.clickUpload();
			TestValidation.IsTrue(uploadExcel && clickUploadBtn, 
								  "IMPORTED the updated excel with required information of new and existing data", 
								  "Failed to Import excel sheet");
			
			String fileName[] = CommonMethods.splitAndGetString(filePath, "\\\\");
			String excelDocName = fileName[fileName.length-1];
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus(IMPORT_STATUS.FAILURE);
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of resource has FAILED", 
								  "Failed to Verify Import of resource is done or not");
			
			String expectedErrMsg = "Object reference not set to an instance of an object.";
			String actualErrMsg = diep.getImportTableDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg = expectedErrMsg.equalsIgnoreCase(actualErrMsg);
			TestValidation.IsTrue(compareErrMsg, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			FormDesignerPage fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			boolean releaseForm = fdp.releaseForm(chkFormName);
			TestValidation.IsFalse(releaseForm, 
							       "Form " + chkFormName + " should NOT be found hence won't be released", 
								   "Failed since uncertain that the form " + chkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, chkFormName);
			TestValidation.IsTrue(openForm, 
					 			  "OPENED form - " + chkFormName, 
					 			  "Failed to open form - " + chkFormName);
			
			WebElement SOFieldLbl = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_LBL,
					"FIELD_NAME", SOFieldNm);
			TestValidation.IsTrue(SOFieldLbl!=null, 
					 			   "VERIFIED Select One field " + SOFieldNm + " is displayed", 
					 			   "Failed to Verify Select One field " + SOFieldNm + " is displayed or not");
			
			WebElement FTFieldLbl = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_LBL,
					"FIELD_NAME", freeTextFieldNm);
			TestValidation.IsTrue(FTFieldLbl==null, 
					 			   "VERIFIED Free Text field " + freeTextFieldNm + " is NOT displayed", 
					 			   "Failed to Verify Free Text field " + freeTextFieldNm + " is displayed or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify import functionality for \"Form Definition\" with new data "
			+ "and update in old data")
	public void TestCase_38294() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			List<String> items = Arrays.asList(qstFormName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectChkForm = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectChkForm, 
								  "SELECTED form " + qstFormName, 
								  "Failed to Select form " + qstFormName);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			boolean importTab = diep.selectImportTab();
			TestValidation.IsTrue(importTab, 
								  "SWITCHED to DPT Import tab", 
								  "Failed to Switch to DPT Import Tab");
			
			boolean openImportPopup = diep.openNewImportDataPopUp();
			TestValidation.IsTrue(openImportPopup, 
								  "OPENED Import data popup", 
								  "Failed to Open Import data popup");
			
			boolean setImportType = diep.selectImportType(ImportType.FORM_DEFINITION);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.FORM_DEFINITION, 
								  "Failed to Set Import type to " + ImportType.FORM_DEFINITION);
			
			HashMap<String, String> fieldProp1 = new HashMap<String, String>(); 
			fieldProp1.put(FormDeftnFieldName.IS_REQUIRED, "FALSE");
			// Update
			FormDefinitionFields fdf1 = new FormDefinitionFields();
			fdf1.fieldName = numericFieldNm;
			fdf1.fieldProperties = fieldProp1;
			
			HashMap<String, String> fieldProp2 = new HashMap<String, String>(); 
			fieldProp2.put(FormDeftnFieldName.REPEAT_FIELD, "TRUE");
			fieldProp2.put(FormDeftnFieldName.REPEAT, "2");
			// Update
			FormDefinitionFields fdf2 = new FormDefinitionFields();
			fdf2.fieldName = SOFieldNm;
			fdf2.fieldProperties = fieldProp2;
			
			HashMap<String, String> fieldProp3 = new HashMap<String, String>(); 
			fieldProp3.put(FormDeftnFieldName.SHORT_NAME, dateFieldNm);
			fieldProp3.put(FormDeftnFieldName.TYPE, dateFieldNm);
			fieldProp3.put(FormDeftnFieldName.ELEMENT_TYPE, "Field");
			fieldProp3.put(FormDeftnFieldName.PARENT, "N/A");
			fieldProp3.put(FormDeftnFieldName.IS_REQUIRED, "TRUE");
			// New Entry
			FormDefinitionFields fdf3 = new FormDefinitionFields();
			fdf3.newEntry = true;
			fdf3.fieldName = dateFieldNm;
			fdf3.fieldProperties = fieldProp3;

			HashMap<String, List<FormDefinitionFields>> formDetails = new HashMap<String, List<FormDefinitionFields>>();
			formDetails.put(qstFormName, Arrays.asList(fdf1, fdf2, fdf3));
			
			ImportFormDefinitionFields ifdf = new ImportFormDefinitionFields();
			ifdf.fileName = filePath;
			ifdf.formDetails = formDetails;
			
			boolean updateExcel = diep.updateFormDefinitionExcelWithFields(ifdf);
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of new and existing data", 
								  "Failed to Update excel sheet");
			
			boolean uploadExcel = diep.UploadFileImport(filePath);
			boolean clickUploadBtn = diep.clickUpload();
			TestValidation.IsTrue(uploadExcel && clickUploadBtn, 
								  "IMPORTED the updated excel with required information of new and existing data", 
								  "Failed to Import excel sheet");
			
			String fileName[] = CommonMethods.splitAndGetString(filePath, "\\\\");
			String excelDocName = fileName[fileName.length-1];
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus();
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of resource has FAILED", 
								  "Failed to Verify Import of resource is done or not");
			
			FormDesignerPage fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			boolean releaseForm = fdp.releaseForm(qstFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + qstFormName, 
								   "Failed since uncertain that the form " + qstFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, qstFormName);
			TestValidation.IsTrue(openForm1, 
					 			  "OPENED form - " + qstFormName, 
					 			  "Failed to open form - " + qstFormName);
			
			FormOperations fo = new FormOperations(driver);
			boolean checkIsFieldRequiredPrvw = fo.isFieldRequired(numericFieldNm);
			TestValidation.IsFalse(checkIsFieldRequiredPrvw, 
					  			  "VERIFIED that Numeric type field -" + numericFieldNm + " is NOT Marked as required", 
					              "Failed to Verify that Numeric type field -" + numericFieldNm + " is Marked as required or not");
			
			boolean hintDisplay = fo.isHintDisplayedForField(SLTFieldNm, SLTFieldHint);
			TestValidation.IsTrue(hintDisplay, 
					  			  "Verified hint Displayed for " + SLTFieldNm + " field -" + SLTFieldNm + " in Form", 
					  			  "FAILED to very hint display for  " + SLTFieldNm + " field -" + SLTFieldNm + " in Form");
			
			List<WebElement> FieldLbl = controlActions.perform_GetListOfElementsByXPath(FSQABrowserFormsPageLocators.FIELD_LBL, 
					"FIELD_NAME", SOFieldNm);
			int labelPresent = FieldLbl.size();
			TestValidation.IsTrue(labelPresent==2, 
					  			  "VERIFIED that Select One type field -" + SOFieldNm + " is REPEATED TWICE", 
					              "Failed to Verify that Select One type field -" + SOFieldNm + " is repeated or not");
			
			HashMap<String, List<String>> fillData = new HashMap<String, List<String>>();
			fillData.put(SMFieldNm,Arrays.asList("10;30"));
			fillData.put(SOFieldNm,Arrays.asList("1","3"));
			fillData.put(SLTFieldNm,Arrays.asList("Testing Automation for DPT Form"));
			fillData.put(dateFieldNm,Arrays.asList("10/19/2025"));
			
			//Form object
			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillData;
			fd.resourceName = customerInstanceValue;
			
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + qstFormName, 
								  "Failed to submit form" + qstFormName);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify download,inspect error and reprocess of the failed files for \"Form Definition\".")
	public void TestCase_38295() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			List<String> items = Arrays.asList(adtFormName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectChkForm = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectChkForm, 
								  "SELECTED form " + adtFormName, 
								  "Failed to Select form " + adtFormName);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			boolean importTab = diep.selectImportTab();
			TestValidation.IsTrue(importTab, 
								  "SWITCHED to DPT Import tab", 
								  "Failed to Switch to DPT Import Tab");
			
			boolean openImportPopup = diep.openNewImportDataPopUp();
			TestValidation.IsTrue(openImportPopup, 
								  "OPENED Import data popup", 
								  "Failed to Open Import data popup");
			
			boolean setImportType = diep.selectImportType(ImportType.FORM_DEFINITION);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.FORM_DEFINITION, 
								  "Failed to Set Import type to " + ImportType.FORM_DEFINITION);
			
			HashMap<String, String> fieldProp = new HashMap<String, String>(); 
			fieldProp.put(FormDeftnFieldName.IS_REQUIRED, "FALSEXXSSD");
			// Update
			FormDefinitionFields fdf = new FormDefinitionFields();
			fdf.fieldName = dateFieldNm;
			fdf.fieldProperties = fieldProp;
			
			HashMap<String, List<FormDefinitionFields>> formDetails = new HashMap<String, List<FormDefinitionFields>>();
			formDetails.put(adtFormName, Arrays.asList(fdf));
			
			ImportFormDefinitionFields ifdf = new ImportFormDefinitionFields();
			ifdf.fileName = filePath;
			ifdf.formDetails = formDetails;
			
			boolean updateExcel = diep.updateFormDefinitionExcelWithFields(ifdf);
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of new and existing data", 
								  "Failed to Update excel sheet");
			
			boolean uploadExcel = diep.UploadFileImport(filePath);
			boolean clickUploadBtn = diep.clickUpload();
			TestValidation.IsTrue(uploadExcel && clickUploadBtn, 
								  "IMPORTED the updated excel with required information of new and existing data", 
								  "Failed to Import excel sheet");
			
			String fileName[] = CommonMethods.splitAndGetString(filePath, "\\\\");
			String excelDocName = fileName[fileName.length-1];
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus(IMPORT_STATUS.FAILURE);
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of resource has FAILED", 
								  "Failed to Verify Import of resource is done or not");
			
			String expectedErrMsg = "String was not recognized as a valid Boolean.";
			String actualErrMsg = diep.getImportTableDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg = expectedErrMsg.equalsIgnoreCase(actualErrMsg);
			TestValidation.IsTrue(compareErrMsg, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean reprocessFile = diep.reprocessFailedImportOnce();
			TestValidation.IsFalse(reprocessFile, 
								  "As expected, the reprocess of file FAILED", 
								  "Failed to verify if reprocess of file failed or not");
			
			String actualErrMsg2 = diep.getImportTableDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg2 = expectedErrMsg.equalsIgnoreCase(actualErrMsg2);
			TestValidation.IsTrue(compareErrMsg2, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean downloadFile = diep.downloadDataFromLatestImport();
			TestValidation.IsTrue(downloadFile, 
								  "DOWNLOADED excel sheet used for Import", 
								  "Failed to Download excel sheet used for Import");
			
			String filename[] = CommonMethods.splitAndGetString(excelDocName, ".xlsx");
			String verifyExcelName = filename[0] + " (1).xlsx";
			boolean verifyFileDownloaded = controlActions.verifyFileDownloaded(downloadPath, verifyExcelName);
			TestValidation.IsTrue(verifyFileDownloaded, 
								  "Verified " + verifyExcelName + " file is downloaded successfully ", 
								  "Failed to verify that document " + verifyExcelName + "  is downloadeded");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify download of Form definition data.")
	public void TestCase_38428() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			List<String> items = Arrays.asList(adtFormName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectChkForm = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectChkForm, 
								  "SELECTED form " + adtFormName, 
								  "Failed to Select form " + adtFormName);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			String filePath = diep.downloadExportFileAndGetFilePath();
			TestValidation.IsTrue(filePath!=null, 
								  "The exported file HAS BEEN downloaded successfully", 
								  "Failed to Download the exported file");
			
			HashMap<String, String> fieldProp1 = new HashMap<String, String>(); 
			fieldProp1.put(FormDeftnFieldName.IS_REQUIRED, "TRUE");
			fieldProp1.put(FormDeftnFieldName.REPEAT, "N/A");
			fieldProp1.put(FormDeftnFieldName.ALLOW_ATTACHMENTS, "FALSE");
			// Verify
			FormDefinitionFields fdf1 = new FormDefinitionFields();
			fdf1.fieldName = timeFieldNm;
			fdf1.fieldProperties = fieldProp1;
			
			HashMap<String, String> fieldProp2 = new HashMap<String, String>(); 
			fieldProp2.put(FormDeftnFieldName.TYPE, "SingleLineText");
			fieldProp2.put(FormDeftnFieldName.ELEMENT_TYPE, "Field");
			fieldProp2.put(FormDeftnFieldName.HINT, SLTFieldHint);
			// Verify
			FormDefinitionFields fdf2 = new FormDefinitionFields();
			fdf2.fieldName = SLTFieldNm;
			fdf2.fieldProperties = fieldProp2;
			
			HashMap<String, List<FormDefinitionFields>> formDetails = new HashMap<String, List<FormDefinitionFields>>();
			formDetails.put(adtFormName, Arrays.asList(fdf1, fdf2));
			
			ExportFormDefinitionFields efdf = new ExportFormDefinitionFields();
			efdf.fileName = filePath;
			efdf.verifyFields = formDetails;
			
			boolean updateExcel = diep.verifyExportedFormDefinition(efdf);
			TestValidation.IsTrue(updateExcel, 
								  "UPDATED excel with required information of new and existing data", 
								  "Failed to Update excel sheet");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Form Definition.")
	public void TestCase_38528() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			boolean searchTxt = diep.searchExtractItem(chkFormName);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + chkFormName, 
								  "Failed to Search for text " + chkFormName);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.SELECT_VALUE_INP, 
												"VALUE", chkFormName);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + chkFormName, 
								  "Failed to Verify whether element " + chkFormName + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the Filter functionality for form defination.")
	public void TestCase_38541() {
		try {
			HomePage hp = new HomePage(driver);			
			DataImportExportPage diep = hp.clickDataImportExportMenu();
			
			boolean exportBtn = diep.selectExportTab();
			TestValidation.IsTrue(exportBtn, 
								  "CLICKED on Export tab", 
								  "Failed to Click on Export tab");
			
			boolean openPopup = diep.openExtractDataPopUp();
			TestValidation.IsTrue(openPopup, 
								  "OPENED Export Data popup", 
								  "Failed to Open Export Data popup");
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_DEFINITION);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_DEFINITION, 
								  "Failed to Set Extract type to " + ExtractType.FORM_DEFINITION);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, COLUMN_SETTING.FILTER, qstFormName);
			TestValidation.IsTrue(applyFilter, 
								  "For Resource column, APPLIED filter using text " + qstFormName, 
								  "Failed to apply filter on Resource with text " + qstFormName);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.SELECT_VALUE_INP, 
												"VALUE", qstFormName);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + qstFormName, 
								  "Failed to Verify whether element " + qstFormName + " is present or not");
			
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

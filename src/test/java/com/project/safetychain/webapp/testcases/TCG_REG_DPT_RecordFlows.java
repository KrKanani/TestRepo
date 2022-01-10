package com.project.safetychain.webapp.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.IMPORT_STATUS;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportRecordDetails;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.RecordFields;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FIELD_OTHER_TYPES;
import com.project.safetychain.webapp.pageobjects.FSQABrowserRecordsPage.FORMFIELDS;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DPT_RecordFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	
	public static String locationCategoryValue, customersCategoryValue;
	public static String locationInstanceValue, customersInstanceValue1, customersInstanceValue2;
	
	// Form and Fields
	public static String chkFormName, adtFormName;
	public static String sectionAFieldNm = "Section A", sectionBFieldNm = "Section B", 
			numericFieldNm1 = "Numeric1", numericFieldNm2 = "Numeric2", SLTFieldNm = "Single Line Text";
	public static HashMap<String, String> locResMap  = null;
	public static LinkedHashMap<String, String> adtShortNames = null;
	public static LinkedHashMap<String, String> chkShortNames = null;
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		
		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		customersCategoryValue = CommonMethods.dynamicText("CCat");
		customersInstanceValue1 = CommonMethods.dynamicText("CInst1");
		customersInstanceValue2 = CommonMethods.dynamicText("CInst2");
		chkFormName = CommonMethods.dynamicText("DPTRec_Chk");
		adtFormName = CommonMethods.dynamicText("DPTRec_Adt");
		
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
		resourceCatMap.put(customersCategoryValue, Arrays.asList(customersInstanceValue1, customersInstanceValue2));
		
		locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, 
				true, userList, RESOURCE_TYPES.CUSTOMERS);
		
		if(locResMap.isEmpty()) {
			TCGFailureMsg = "API - Could NOT create to resources and categories";
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check
		// API - Add fields to form
		FormFieldParams numericField1 = new FormFieldParams();
		numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField1.Name = numericFieldNm1;
		numericField1.Hint = "If " + numericFieldNm1 + " is set to 7 then Show " + numericFieldNm2;

		FormFieldParams numericField2 = new FormFieldParams();
		numericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField2.Name = numericFieldNm2;
		numericField2.DependencyRule = "if("+numericFieldNm1+"=7;Show)else(Hide)";
		
		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdChk = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormChk = new FormParams();
		fpFormChk.FormId = formIdChk;
		fpFormChk.FormName = chkFormName;
		fpFormChk.type = DPT_FORM_TYPES.CHECK;
		fpFormChk.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpFormChk.ResourceCategoryNm = customersCategoryValue;
		fpFormChk.ResourceInstanceNm = customersInstanceValue1;
		fpFormChk.formElements = Arrays.asList(numericField1, numericField2);
		fpFormChk.CustomerResources = resourceCatMap;

		chkShortNames = formCreationWrapper.API_Wrapper_Forms(fpFormChk);
		if(chkShortNames.isEmpty()) {
			TCGFailureMsg = "API - Could NOT create to form " + chkFormName;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}
		
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
		SectionA.formFieldParams = Arrays.asList(numericField1); // This does not have Dependency
		
		// Section B
		FormFieldParams numericField3 = new FormFieldParams();
		numericField3.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField3.Name = numericFieldNm2;
		numericField3.IsRequired = "false";
		
		FormFieldParams SLTField = new FormFieldParams();
		SLTField.DPTFieldType = DPT_FIELD_TYPES.SINGLE_LINE_TEXT;
		SLTField.Name = SLTFieldNm;
		SLTField.AllowComments = "true";
		
		Element_Types SectionB = new Element_Types();
		SectionB.ElementType = DPT_FIELD_TYPES.SECTION;
		SectionB.Name = sectionBFieldNm;
		SectionB.formFieldParams = Arrays.asList(numericField3, SLTField);
		
		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdAdt = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormAdt = new FormParams();
		fpFormAdt.FormId = formIdAdt;
		fpFormAdt.FormName = adtFormName;
		fpFormAdt.type = DPT_FORM_TYPES.AUDIT;
		fpFormAdt.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpFormAdt.ResourceCategoryNm = customersCategoryValue;
		fpFormAdt.ResourceInstanceNm = customersInstanceValue2;
		fpFormAdt.CustomerResources = resourceCatMap;
		fpFormAdt.SectionElements = Arrays.asList(SectionA, SectionB);

		adtShortNames = formCreationWrapper.API_Wrapper_Forms(fpFormAdt);
		if(adtShortNames.isEmpty()) {
			TCGFailureMsg = "API - Could NOT create to form " + adtShortNames;
			logFatal(TCGFailureMsg);
			throw new SkipException(TCGFailureMsg);
		}

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

	@Test(description = "DPT || Verify single and multiple data extract of \"Records\"")
	public void TestCase_24268() {
		
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
			
			boolean setType1 = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType1, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			WebElement ResourceName = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE_NAME);
			boolean applyFilter1 = diep.applyFilter(ResourceName,customersInstanceValue1);
			TestValidation.IsTrue(applyFilter1, 
								  "APPLIED filter on Resource for " + customersInstanceValue1, 
								  "Failed to Apply filter on Resource for " + customersInstanceValue1);
			
			List<String> items1 = Arrays.asList(chkFormName);
			WebElement element1 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectSingleForm = diep.applyFilterAndSelectItem(element1,items1);
			TestValidation.IsTrue(selectSingleForm, 
								  "SELECTED record for " + chkFormName, 
								  "Failed to Select record for " + chkFormName);
			
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
			
			boolean setType2 = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType2, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			List<String> items2 = Arrays.asList(chkFormName, adtFormName);
			WebElement element2 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectMultipleForms = diep.selectItemsInGrid(element2,items2);
			TestValidation.IsTrue(selectMultipleForms, 
								  "SELECTED records for - " + chkFormName + " and " + adtFormName, 
								  "Failed to Select records for - " + chkFormName + " and " + adtFormName);
			
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
	
	@Test(description = "DPT || Verify the import functionality for \"Records\" having correct "
			+ "and incorrect data in one file and select \"Commit with Partial Success\".")
	public void TestCase_24288() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			List<String> items = Arrays.asList(chkFormName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectForms = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectForms, 
								  "SELECTED form : " + chkFormName, 
								  "Failed to Select form : " + chkFormName);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.RECORDS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.RECORDS, 
								  "Failed to Set Import type to " + ImportType.RECORDS);
			
			boolean setPartialSuccess = diep.selectPartialSuccessOnImportPopup();
			TestValidation.IsTrue(setPartialSuccess, 
								  "SELECTED 'Commit with Partial Success' checkbox successfully", 
								  "Failed to Select 'Commit with Partial Success' checkbox");
			
			String fieldValue1 = "7";
			String fieldValue2 = "ABC";
			String fieldValue3 = "4";
			String fieldValue4 = "3";
			
			RecordFields rf1 = new RecordFields();
			rf1.fieldName = numericFieldNm1;
			rf1.fieldValue = fieldValue1;
			
			RecordFields rf2 = new RecordFields();
			rf2.fieldName = numericFieldNm2;
			rf2.fieldValue = fieldValue2;
			
			RecordFields rf3 = new RecordFields();
			rf3.fieldName = numericFieldNm1;
			rf3.fieldValue = fieldValue3;
			
			RecordFields rf4 = new RecordFields();
			rf4.fieldName = numericFieldNm2;
			rf4.fieldValue = fieldValue4;
			
			HashMap<String, List<RecordFields>> updateFields = new HashMap<String, List<RecordFields>>();
			updateFields.put(customersInstanceValue1,Arrays.asList(rf1, rf2));
			updateFields.put(customersInstanceValue2,Arrays.asList(rf3, rf4));
			
			ImportRecordDetails ird = new ImportRecordDetails();
			ird.fileName = filePath;
			ird.updateFields = updateFields;
			
			boolean updateExcel = diep.updateRecordsForSameFormUsingExcelAsPerResource(ird);
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
			
			boolean verifyImport = diep.verifyFileImportStatus(IMPORT_STATUS.PARTIAL_SUCCESS);
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Partial Import of resource is done sucessfully", 
								  "Failed to Verify Partial Import of resource is done or not");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateRecs = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateRecs, 
		  			  			  "NAVIGATED to FSQA Browser > Customers > Records tab", 
		  			  			  "Could NOT navigate to FSQA Browser > Customers > Records tab");
			
			boolean openForm = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, chkFormName);
			TestValidation.IsTrue(openForm, 
		  			  			  "OPENED record for form " + chkFormName, 
		  			  		  	  "Could NOT open record for form " + chkFormName);
			
			FSQABrowserRecordsPage frp = new FSQABrowserRecordsPage(driver);
			boolean formNameVal = frp.verifyRecordsDetails(FORMFIELDS.FORMNAME, chkFormName);
			TestValidation.IsTrue(formNameVal, 
								  "Verified Form Name value as " + chkFormName, 
								  "Failed to verify Form Name value as " + chkFormName);
			
			boolean resourceVal = frp.verifyRecordsDetails(FORMFIELDS.RESOURCE, customersInstanceValue2);
			TestValidation.IsTrue(resourceVal, 
								  "Verified Resource value " + customersInstanceValue2, 
								  "Failed to verify Resource value" + customersInstanceValue2);
			
			boolean locationVal = frp.verifyRecordsDetails(FORMFIELDS.LOCATION, locationInstanceValue);
			TestValidation.IsTrue(locationVal, 
								  "Verified Location value " + locationInstanceValue, 
								  "Failed to verify Location value" + locationInstanceValue);
			
			// Numeric 1
			String numFieldValue1 = frp.getFieldSpecificValue(numericFieldNm1,FIELD_OTHER_TYPES.FIELD);
			boolean verifyNumFieldValue = numFieldValue1.contains(fieldValue3);
			TestValidation.IsTrue(verifyNumFieldValue, 
								  "RETRIEVED value for the field " + numericFieldNm1 + " as " + numFieldValue1, 
								  "Failed to retrieve value for the field " + numericFieldNm1 );
			// Numeric 2
			String numFieldValue2 = frp.getFieldSpecificValue(numericFieldNm2,FIELD_OTHER_TYPES.FIELD);
			TestValidation.IsTrue(numFieldValue2 == null, 
								  "Did not retrieve value of the field " + numericFieldNm2 + " as it is HIDDEN by dependency", 
								  "Failed to Determine whether the field " + numericFieldNm2 + " is displayed or not");
			
			boolean backToRecords = fbp.clickRecordsBreadCrumb();
			TestValidation.IsTrue(backToRecords, 
								  "NAVIGATED back to FSQABrowser > Records tab", 
								  "Failed to navigate back to FSQABrowser > Records tab");
			
			int recordSize1 = fbp.TotalRecordsLst.size();
			TestValidation.IsTrue(recordSize1 == 1, 
								  "Verified 1 Record is displayed for form " + chkFormName,
								  "Failed to verify Record count displayed for resource " + chkFormName);

			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.RESOURCE, COLUMNSETTING.FILTER,customersInstanceValue1);
			TestValidation.IsTrue(applyfilter, 
								  "Applied Filter on " + COLUMNHEADER.RESOURCE, 
								  "Failed to apply filter on " + COLUMNHEADER.RESOURCE);
			
			int recordSize2 = fbp.TotalRecordsLst.size();
			TestValidation.IsTrue(recordSize2 == 0, 
								  "Verified NO Records are displayed for resource " + customersInstanceValue1,
								  "Failed to verify Record count displayed for resource " + customersInstanceValue1);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}

	@Test(description = "DPT || Verify the import functionality for \"Records\" for correct "
			+ "& incorrect data in same file without selecting \"Commit with Partial Success\".")
	public void TestCase_24277() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			List<String> items = Arrays.asList(adtFormName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectForms = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectForms, 
								  "SELECTED form : " + adtFormName, 
								  "Failed to Select form : " + adtFormName);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.RECORDS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.RECORDS, 
								  "Failed to Set Import type to " + ImportType.RECORDS);
			
			String fieldNum1Value = "7";
			String fieldNum2Value = "3";
			String fieldSLTValue1 = "ABC";
			String fieldSLTValue2 = "@@@@@";
			
			RecordFields rf1 = new RecordFields();
			rf1.fieldName = numericFieldNm1;
			rf1.fieldValue = fieldNum1Value;
			
			RecordFields rf2 = new RecordFields();
			rf2.fieldName = SLTFieldNm;
			rf2.fieldValue = fieldSLTValue1;
			
			RecordFields rf3 = new RecordFields();
			rf3.fieldName = numericFieldNm2;
			rf3.fieldValue = fieldNum2Value;
			
			RecordFields rf4 = new RecordFields();
			rf4.fieldName = SLTFieldNm;
			rf4.fieldValue = fieldSLTValue2;
			
			HashMap<String, List<RecordFields>> updateFields = new HashMap<String, List<RecordFields>>();
			updateFields.put(customersInstanceValue1,Arrays.asList(rf1, rf2));
			updateFields.put(customersInstanceValue2,Arrays.asList(rf1, rf3, rf4));
			
			ImportRecordDetails ird = new ImportRecordDetails();
			ird.fileName = filePath;
			ird.updateFields = updateFields;
			
			boolean updateExcel = diep.updateRecordsForSameFormUsingExcelAsPerResource(ird);
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

			boolean openInspErrPopup = diep.openInspectErrorsPopupForLatestImport();
			TestValidation.IsTrue(openInspErrPopup, 
								  "OPENED Inspect Errors popup for the Import done", 
								  "Failed to Open Inspect Errors popup for the Import done");

			String expectedErrMsg = "Invalid Numeric value";
			String actualErrMsg = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg = expectedErrMsg.equalsIgnoreCase(actualErrMsg);
			TestValidation.IsTrue(compareErrMsg, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);

			boolean closePopup = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateRecs = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.RECORDS);
			TestValidation.IsTrue(navigateRecs, 
		  			  			  "NAVIGATED to FSQA Browser > Customers > Records tab", 
		  			  			  "Could NOT navigate to FSQA Browser > Customers > Records tab");
			
			boolean applyfilter = fbp.openAndApplySettingsForColumn(COLUMNHEADER.FORMNAME, COLUMNSETTING.FILTER, adtFormName);
			TestValidation.IsTrue(applyfilter, 
								  "Applied Filter on " + COLUMNHEADER.FORMNAME, 
								  "Failed to apply filter on " + COLUMNHEADER.FORMNAME);
			
			int recordSize = fbp.TotalRecordsLst.size();
			TestValidation.IsTrue(recordSize == 0, 
								  "Verified NO Records are displayed for form " + adtFormName,
								  "Failed to verify Record count displayed for form " + adtFormName);

		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify download,inspect error and reprocess of the failed files for any type of \"Records\".")
	public void TestCase_24282() {
		
		String newResource = customersInstanceValue2 + "_11";
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
			
			boolean setType = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			WebElement ResourceName = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE_NAME);
			boolean applyFilter1 = diep.applyFilter(ResourceName,customersInstanceValue2);
			TestValidation.IsTrue(applyFilter1, 
								  "APPLIED filter on Resource for " + customersInstanceValue2, 
								  "Failed to Apply filter on Resource for " + customersInstanceValue2);
			
			List<String> items1 = Arrays.asList(chkFormName);
			WebElement element1 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectSingleForm = diep.applyFilterAndSelectItem(element1,items1);
			TestValidation.IsTrue(selectSingleForm, 
								  "SELECTED record for " + chkFormName, 
								  "Failed to Select record for " + chkFormName);
			
			boolean clickExtract1 = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract1, 
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
			
			boolean setImportType = diep.selectImportType(ImportType.RECORDS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.RECORDS, 
								  "Failed to Set Import type to " + ImportType.RECORDS);
			
			String fieldValue1 = "7";
			String fieldValue2 = "3";
			
			RecordFields rf1 = new RecordFields();
			rf1.fieldName = numericFieldNm1;
			rf1.fieldValue = fieldValue1;
			
			RecordFields rf2 = new RecordFields();
			rf2.fieldName = numericFieldNm2;
			rf2.fieldValue = fieldValue2;
			
			HashMap<String, List<RecordFields>> updateFields = new HashMap<String, List<RecordFields>>();
			updateFields.put(customersInstanceValue2,Arrays.asList(rf1, rf2));
			
			ImportRecordDetails ird = new ImportRecordDetails();
			ird.fileName = filePath;
			ird.resourceName = newResource;
			ird.updateFields = updateFields;
			
			boolean updateExcel = diep.updateRecordsForSameFormUsingExcelAsPerResource(ird);
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
			boolean applyFilter2 = diep.openAndApplySettingsForColumn(ColumnNames.FILENAME, COLUMN_SETTING.FILTER, excelDocName);
			TestValidation.IsTrue(applyFilter2, 
								  "For Filename column, APPLIED filter using text " + excelDocName, 
								  "Failed to apply filter on Filename with text " + excelDocName);
			
			boolean verifyImport = diep.verifyFileImportStatus(IMPORT_STATUS.FAILURE);
			TestValidation.IsTrue(verifyImport, 
								  "VERIFIED Import of resource has FAILED", 
								  "Failed to Verify Import of resource is done or not");
			
			boolean openInspErrPopup1 = diep.openInspectErrorsPopupForLatestImport();
			TestValidation.IsTrue(openInspErrPopup1, 
								  "OPENED Inspect Errors popup for the Import done", 
								  "Failed to Open Inspect Errors popup for the Import done");
			
			String expectedErrMsg = "Resource does not exists";
			String actualErrMsg1 = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg1 = expectedErrMsg.equalsIgnoreCase(actualErrMsg1);
			TestValidation.IsTrue(compareErrMsg1, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean closePopup1 = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup1, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
			boolean reprocessFile = diep.reprocessFailedImportOnce();
			TestValidation.IsFalse(reprocessFile, 
								  "As expected, the reprocess of file FAILED", 
								  "Failed to verify if reprocess of file failed or not");
			
			boolean openInspErrPopup2 = diep.openInspectErrorsPopupForLatestImport();
			TestValidation.IsTrue(openInspErrPopup2, 
								  "OPENED Inspect Errors popup for the Import done", 
								  "Failed to Open Inspect Errors popup for the Import done");
			
			String actualErrMsg2 = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg2 = expectedErrMsg.equalsIgnoreCase(actualErrMsg2);
			TestValidation.IsTrue(compareErrMsg2, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean closePopup2 = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup2, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
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
	
	@Test(description = "DPT || Export - Verify download of Records data.")
	public void TestCase_38429() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			WebElement ResourceName = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE_NAME);
			boolean applyFilter1 = diep.applyFilter(ResourceName,customersInstanceValue1);
			TestValidation.IsTrue(applyFilter1, 
								  "APPLIED filter on Resource for " + customersInstanceValue1, 
								  "Failed to Apply filter on Resource for " + customersInstanceValue1);
			
			List<String> items1 = Arrays.asList(adtFormName);
			WebElement element1 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectSingleForm = diep.applyFilterAndSelectItem(element1,items1);
			TestValidation.IsTrue(selectSingleForm, 
								  "SELECTED record for " + adtFormName, 
								  "Failed to Select record for " + adtFormName);
				
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
			
			String fieldGroupValue = "N.A";
			
			RecordFields rf1 = new RecordFields();
			rf1.fieldName = numericFieldNm1;
			rf1.sectionName = sectionAFieldNm;
			rf1.fieldGroupName = fieldGroupValue;
			rf1.shortName = adtShortNames.get(numericFieldNm1);
			
			RecordFields rf2 = new RecordFields();
			rf2.fieldName = numericFieldNm2;
			rf2.sectionName = sectionBFieldNm;
			rf2.fieldGroupName = fieldGroupValue;
			rf2.shortName = adtShortNames.get(numericFieldNm2);
			
			RecordFields rf3 = new RecordFields();
			rf3.fieldName = SLTFieldNm;
			rf3.fieldGroupName = fieldGroupValue;
			rf3.shortName = adtShortNames.get(SLTFieldNm);
			
			HashMap<String, RecordFields> verifyFields = new HashMap<String, RecordFields>();
			verifyFields.put(numericFieldNm1,rf1);
			verifyFields.put(numericFieldNm2,rf2);
			verifyFields.put(SLTFieldNm,rf3);
			
			ImportRecordDetails ird = new ImportRecordDetails();
			ird.fileName = filePath;
			ird.formName = adtFormName;
			ird.formType = FORM_TYPES.AUDIT;
			ird.resourceName = customersInstanceValue1;
			ird.locationName = locationInstanceValue;
			ird.verifyFields = verifyFields;
			
			boolean verifyExcel = diep.verifyExportedRecord(ird);
			TestValidation.IsTrue(verifyExcel, 
								  "VERFIED downloaded excel with information for " + adtFormName, 
								  "Failed to Verify downloaded excel sheet for " + adtFormName);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Records.")
	public void TestCase_38540() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			boolean searchTxt = diep.searchExtractItem(adtFormName);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + adtFormName, 
								  "Failed to Search for text " + adtFormName);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==2, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.SELECT_VALUE_INP, 
												"VALUE", adtFormName);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + adtFormName, 
								  "Failed to Verify whether element " + adtFormName + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the Filter functionality for records.")
	public void TestCase_38542() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.RECORDS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RECORDS, 
								  "Failed to Set Extract type to " + ExtractType.RECORDS);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.FORM_NAME, 
					COLUMN_SETTING.FILTER, chkFormName);
			TestValidation.IsTrue(applyFilter, 
								  "For Form Name column, APPLIED filter using text " + chkFormName, 
								  "Failed to apply filter on Form Name with text " + chkFormName);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==2, 
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
	
	@AfterClass(alwaysRun = true)
	public void closeBrowser() throws InterruptedException {
		driver.close();
	}
}

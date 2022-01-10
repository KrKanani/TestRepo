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
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExportFormComplianceFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.IMPORT_STATUS;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportFormCompliance;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.FormComplianceFields;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNHEADER;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQARESOURCE;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.FSQATAB;
import com.project.safetychain.webapp.testcases.support.FormOperations;
import com.project.safetychain.webapp.testcases.support.FormOperations.FieldProperties;
import com.project.safetychain.webapp.testcases.support.FormOperations.FormDetails;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.Compliance;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FORM_TYPES;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormFieldParamsCompliance;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPage;
import com.project.safetychain.webapp.pageobjects.FSQABrowserFormsPageLocators;
import com.project.safetychain.webapp.pageobjects.FSQABrowserPage;
import com.project.safetychain.webapp.pageobjects.FormDesignerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DPT_FormComplianceFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	
	public static String locationCategoryValue, customerCategoryValue1, customerCategoryValue2;
	public static String locationInstanceValue, customerInstanceValue1, customerInstanceValue2;
	// Form and Fields
	public static String chkFormName, qstFormName, adtFormName;
	public static String dateFieldNm = "Date", numericFieldNm = "Numeric", SMFieldNm = "SelectMultiple",
			SLTFieldNm = "SingleLineText", SOFieldNm = "SelectOne", FTFieldNm = "FreeText";
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		
		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		customerCategoryValue1 = CommonMethods.dynamicText("CCat1");
		customerCategoryValue2 = CommonMethods.dynamicText("CCat2");
		customerInstanceValue1 = CommonMethods.dynamicText("CInst1");
		customerInstanceValue2 = CommonMethods.dynamicText("CInst2");
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

		HashMap<String, List<String>> resourceCatMap1 = new HashMap<String, List<String>>();
		resourceCatMap1.put(customerCategoryValue1, Arrays.asList(customerInstanceValue1));
		
		HashMap<String, List<String>> resourceCatMap2 = new HashMap<String, List<String>>();
		resourceCatMap2.put(customerCategoryValue2, Arrays.asList(customerInstanceValue2));

		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap1, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);
		
		formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap2, LocationMap, true, userList,
				RESOURCE_TYPES.CUSTOMERS);
		
		// ------------------------------------------------------------------------------------------------
		// Reset Access Token 
		auth.setAccessToken();
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check
		// API - Add fields to form
		FormFieldParams numericField = new FormFieldParams();
		numericField.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField.Name = numericFieldNm;

		FormFieldParams freeTextField = new FormFieldParams();
		freeTextField.DPTFieldType = DPT_FIELD_TYPES.FREE_TEXT;
		freeTextField.Name = FTFieldNm;

		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdChk = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormChk = new FormParams();
		fpFormChk.FormId = formIdChk;
		fpFormChk.FormName = chkFormName;
		fpFormChk.type = DPT_FORM_TYPES.CHECK;
		fpFormChk.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpFormChk.ResourceCategoryNm = customerCategoryValue1;
		fpFormChk.ResourceInstanceNm = customerInstanceValue1;
		fpFormChk.formElements = Arrays.asList(numericField, freeTextField);
		fpFormChk.CustomerResources = resourceCatMap1;

		formCreationWrapper.API_Wrapper_Forms(fpFormChk);
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Compliance - Check - Numeric
		String resource = RESOURCE_TYPES.CUSTOMERS + " > " + customerCategoryValue1 + " > " + customerInstanceValue1;

		HashMap<String, Compliance> complianceValuesMap = new HashMap<String, Compliance>();
		Compliance numCompliance1 = new Compliance();
		numCompliance1.Min = "10";
		numCompliance1.Max = "100";
		numCompliance1.Target = "50";
		numCompliance1.UOM = "KG";
		numCompliance1.Name = numericFieldNm;
		numCompliance1.fieldType = DPT_FIELD_TYPES.NUMERIC;
		
		complianceValuesMap.put(resource, numCompliance1);
		complianceValuesMap.put("Default", numCompliance1);

		FormFieldParamsCompliance ffpc = new FormFieldParamsCompliance();
		ffpc.fieldNames = Arrays.asList(numericFieldNm);
		ffpc.complianceList = complianceValuesMap;
		formCreationWrapper.API_Wrapper_Forms_Compliance(fpFormChk, ffpc);
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Compliance - Check - Free Text

		complianceValuesMap.clear();
		
		Compliance FTCompliance = new Compliance();
		FTCompliance.ComplianceValue = "cust";
		FTCompliance.Name = FTFieldNm;
		FTCompliance.fieldType = DPT_FIELD_TYPES.FREE_TEXT;
		FTCompliance.IsDefault = "true";

		complianceValuesMap.put(resource, FTCompliance);
		complianceValuesMap.put("Default", FTCompliance);

		FormFieldParamsCompliance ffpc2 = new FormFieldParamsCompliance();
		ffpc2.fieldNames = Arrays.asList(FTFieldNm);
		ffpc2.complianceList = complianceValuesMap;
		formCreationWrapper.API_Wrapper_Forms_Compliance(fpFormChk, ffpc2);

		logInfo("resource List " + resource);
		logInfo("Compliance Values " + complianceValuesMap);
		
		// ------------------------------------------------------------------------------------------------
		// Reset Access Token 
		auth.setAccessToken();
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Audit
		// API - Add fields to form
		
		Element_Types Section = new Element_Types();
		Section.ElementType = DPT_FIELD_TYPES.SECTION;
		Section.Name = "Section A";

		FormFieldParams dateField = new FormFieldParams();
		dateField.DPTFieldType = DPT_FIELD_TYPES.DATE;
		dateField.Name = dateFieldNm;

		FormFieldParams selectMultipleField = new FormFieldParams();
		selectMultipleField.DPTFieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		selectMultipleField.Name = SMFieldNm;
		selectMultipleField.SelectOptions = Arrays.asList("70", "80", "90", "100");
		
		Section.formFieldParams = Arrays.asList(dateField, numericField, selectMultipleField);

		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdAdt = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormAdt = new FormParams();
		fpFormAdt.FormId = formIdAdt;
		fpFormAdt.FormName = adtFormName;
		fpFormAdt.type = DPT_FORM_TYPES.AUDIT;
		fpFormAdt.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpFormAdt.ResourceCategoryNm = customerCategoryValue1;
		fpFormAdt.ResourceInstanceNm = customerInstanceValue1;
		fpFormAdt.CustomerResources = resourceCatMap1;
		fpFormAdt.SectionElements = Arrays.asList(Section);

		formCreationWrapper.API_Wrapper_Forms(fpFormAdt);

		// ------------------------------------------------------------------------------------------------
		// API - Form Compliance - Audit - Numeric

		complianceValuesMap.clear();
		Compliance numCompliance2 = new Compliance();
		numCompliance2.Min = "1";
		numCompliance2.Max = "5";
		numCompliance2.Target = "3";
		numCompliance2.UOM = "Litres";
		numCompliance2.Name = numericFieldNm;
		numCompliance2.fieldType = DPT_FIELD_TYPES.NUMERIC;
		
		complianceValuesMap.put(resource, numCompliance2);
		complianceValuesMap.put("Default", numCompliance2);

		FormFieldParamsCompliance ffpc3 = new FormFieldParamsCompliance();
		ffpc3.fieldNames = Arrays.asList(numericFieldNm);
		ffpc3.complianceList = complianceValuesMap;
		
		formCreationWrapper.API_Wrapper_Forms_Compliance(fpFormAdt, ffpc3);
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Compliance - Audit - Select Multiple

		complianceValuesMap.clear();
		Compliance smCompliance = new Compliance();
		smCompliance.ComplianceValue = "70,90";
		smCompliance.Name = SMFieldNm;
		smCompliance.fieldType = DPT_FIELD_TYPES.SELECT_MULTIPLE;
		
		complianceValuesMap.put(resource, smCompliance);
		complianceValuesMap.put("Default", smCompliance);

		FormFieldParamsCompliance ffpc4 = new FormFieldParamsCompliance();
		ffpc4.fieldNames = Arrays.asList(SMFieldNm);
		ffpc4.complianceList = complianceValuesMap;
		
		formCreationWrapper.API_Wrapper_Forms_Compliance(fpFormAdt, ffpc4);

		// ------------------------------------------------------------------------------------------------
		// Reset Access Token 
		auth.setAccessToken();
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Questionnaire
		// API - Add fields to form
		
		FormFieldParams singleLineTxtField = new FormFieldParams();
		singleLineTxtField.DPTFieldType = DPT_FIELD_TYPES.SINGLE_LINE_TEXT;
		singleLineTxtField.Name = SLTFieldNm;

		FormFieldParams selectOneField = new FormFieldParams();
		selectOneField.DPTFieldType = DPT_FIELD_TYPES.SELECT_ONE;
		selectOneField.Name = SOFieldNm;
		selectOneField.SelectOptions = Arrays.asList("Apple", "Banana", "Kiwi", "Cherries");
		
		Section.formFieldParams = Arrays.asList(singleLineTxtField, selectOneField);

		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdQst = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormQst = new FormParams();
		fpFormQst.FormId = formIdQst;
		fpFormQst.FormName = qstFormName;
		fpFormQst.type = DPT_FORM_TYPES.QUESTIONNAIRE;
		fpFormQst.ResourceType = RESOURCE_TYPES.CUSTOMERS;
		fpFormQst.ResourceCategoryNm = customerCategoryValue1;
		fpFormQst.ResourceInstanceNm = customerInstanceValue1;
		fpFormQst.formElements = Arrays.asList(singleLineTxtField, selectOneField);
		fpFormQst.CustomerResources = resourceCatMap1;

		formCreationWrapper.API_Wrapper_Forms(fpFormQst);
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Compliance - Questionnaire - Single Line Text

		complianceValuesMap.clear();
		Compliance SLTCompliance = new Compliance();
		SLTCompliance.ComplianceValue = "cust";
		SLTCompliance.Name = SLTFieldNm;
		SLTCompliance.fieldType = DPT_FIELD_TYPES.SINGLE_LINE_TEXT;

		complianceValuesMap.put(resource, SLTCompliance);
		complianceValuesMap.put("Default", SLTCompliance);
		
		FormFieldParamsCompliance ffpc5 = new FormFieldParamsCompliance();
		ffpc5.fieldNames = Arrays.asList(SLTFieldNm);
		ffpc5.complianceList = complianceValuesMap;
		
		formCreationWrapper.API_Wrapper_Forms_Compliance(fpFormQst, ffpc5);
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Compliance - Questionnaire - Select One

		complianceValuesMap.clear();
		Compliance soCompliance = new Compliance();
		soCompliance.ComplianceValue = "Apple";
		soCompliance.Name = SOFieldNm;
		soCompliance.fieldType = DPT_FIELD_TYPES.SELECT_ONE;
		
		complianceValuesMap.put(resource, soCompliance);
		complianceValuesMap.put("Default", soCompliance);

		FormFieldParamsCompliance ffpc6 = new FormFieldParamsCompliance();
		ffpc6.fieldNames = Arrays.asList(SOFieldNm);
		ffpc6.complianceList = complianceValuesMap;
		
		formCreationWrapper.API_Wrapper_Forms_Compliance(fpFormQst, ffpc6);

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

	@Test(description = "DPT || Verify single and multiple data extract for \"Form Compliance\" extract type")
	public void TestCase_24266() {
		
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
			
			boolean setType1 = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType1, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);

			List<String> items = Arrays.asList(chkFormName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectSingleForm = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectSingleForm, 
								  "SELECTED form " + chkFormName, 
								  "Failed to Select form " + chkFormName);
			
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
			
			boolean setType2 = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType2, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
			List<String> items2 = Arrays.asList(adtFormName, qstFormName);
			WebElement element2 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectMultipleForms = diep.selectItemsInGrid(element2,items2);
			TestValidation.IsTrue(selectMultipleForms, 
								  "SELECTED forms - " + adtFormName + " and " + qstFormName, 
								  "Failed to Select forms - " + adtFormName + " and " + qstFormName);
			
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
	
	@Test(description = "DPT || Verify the import functionality for \"Form Compliance\" having new data "
			+ "and updated data in one file and select \"Update only\".")
	public void TestCase_24278() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.FORM_COMPLIANCE, 
								  "Failed to Set Import type to " + ImportType.FORM_COMPLIANCE);
			
			boolean setUpdateOnly = diep.selectUpdateOnlyOnImportPopup();
			TestValidation.IsTrue(setUpdateOnly, 
								  "SELECTED 'Update only' checkbox successfully", 
								  "Failed to Select 'Update only' checkbox");
			
			String resource = RESOURCE_TYPES.CUSTOMERS + " > " + customerCategoryValue2 + " > " + customerInstanceValue2;
			String FTFldTrgtCmplnce1 = "Arzoo FT";
			String numFldMinCmplnce = "1000";
			String numFldTrgtCmplnce = "1500";
			String numFldMaxCmplnce = "2000";
			String numFldUOMCmplnce = "Tonnes";
			String FTFldTrgtCmplnce2 = "NEW NEW";
			
			HashMap<String, String> formInfo = new HashMap<String, String>();
			formInfo.put(chkFormName, FORM_TYPES.CHECK);
			
			FormComplianceFields fcf1 = new FormComplianceFields();
			fcf1.fieldName = FTFieldNm;
			fcf1.resourceName = "Default";
			fcf1.Default = "TRUE";
			fcf1.Visible = "TRUE";
			fcf1.Target_Compliance = FTFldTrgtCmplnce1;
			
			FormComplianceFields fcf2 = new FormComplianceFields();
			fcf2.fieldName = numericFieldNm;
			fcf2.resourceName = customerInstanceValue1;
			fcf2.Min = numFldMinCmplnce;
			fcf2.Target_Compliance = numFldTrgtCmplnce;
			fcf2.Max = numFldMaxCmplnce;
			fcf2.UOM = numFldUOMCmplnce;
			
			FormComplianceFields ufcf3 = new FormComplianceFields();
			ufcf3.fieldName = FTFieldNm;
			ufcf3.resourceName = resource;
			ufcf3.Default = "TRUE";
			ufcf3.Visible = "TRUE";
			ufcf3.Target_Compliance = FTFldTrgtCmplnce2;
			
			HashMap<String, List<FormComplianceFields>> updateFields = new HashMap<String, List<FormComplianceFields>>();
			updateFields.put(chkFormName,Arrays.asList(fcf1, fcf2, ufcf3));
			
			ImportFormCompliance ifc = new ImportFormCompliance();
			ifc.fileName = filePath;
			ifc.formInfo = formInfo;
			ifc.updateFields = updateFields;
			
			boolean updateExcel = diep.updateFormComplianceExcelWithFields(ifc);
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
								  "VERIFIED Import of form is done sucessfully", 
								  "Failed to Verify Import of form is done or not");
			
			FormDesignerPage fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			boolean releaseForm = fdp.releaseForm(chkFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + chkFormName, 
								   "Failed since uncertain that the form " + chkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, chkFormName);
			TestValidation.IsTrue(openForm1, 
					 			  "OPENED form - " + chkFormName, 
					 			  "Failed to open form - " + chkFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			int resourceCount = ffp.FormResourceLst.size();
			TestValidation.IsTrue(resourceCount==1, 
					 			  "Only ONE resource found for form - " + chkFormName, 
					 			  "Failed to verify resources found for form - " + chkFormName);
			
			boolean verifyResource = ffp.FormResourceLst.get(0).getAttribute("innerText").equalsIgnoreCase(customerInstanceValue1);
			TestValidation.IsTrue(verifyResource, 
					 			  "VERIFIED the ONE resource found is - " + customerInstanceValue1, 
					 			  "Failed to verify resources found for form is - " + customerInstanceValue1);
			
			HashMap<String, List<String>> fillData1 = new HashMap<String, List<String>>();
			fillData1.put(numericFieldNm,Arrays.asList("1590"));
			fillData1.put(FTFieldNm,Arrays.asList(FTFldTrgtCmplnce1));
			
			//Field Property objects
			FieldProperties fp1 = new FieldProperties();
			fp1.fieldName = numericFieldNm;
			fp1.complianceStatusCheck = true;
			fp1.complianceStatus = "Compliant";
			
			FieldProperties fp2 = new FieldProperties();
			fp2.fieldName = FTFieldNm;
			fp2.complianceStatusCheck = true;
			fp2.complianceStatus = "Compliant";

			//Form object
			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillData1;
			//Field property assignment  
			fd.fieldProperties = Arrays.asList(fp1, fp2);

			FormOperations fo = new FormOperations(driver);
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + chkFormName, 
								  "Failed to submit form" + chkFormName);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for \"Form Compliance\" having correct "
			+ "and incorrect data in one file with multiple forms")
	public void TestCase_24279() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
			List<String> items = Arrays.asList(chkFormName, qstFormName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.FORM_NAME);
			boolean selectForms = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectForms, 
								  "SELECTED forms : " + chkFormName + ", " + qstFormName, 
								  "Failed to Select forms : " + chkFormName + ", " + qstFormName);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.FORM_COMPLIANCE, 
								  "Failed to Set Import type to " + ImportType.FORM_COMPLIANCE);
			
			String resource = RESOURCE_TYPES.CUSTOMERS + " > " + customerCategoryValue2 + " > " + "XYZ";
			String numFldUOM = "Litres";
			String SLTFldTrgtCmplnce = "NEW NEW";
			
			HashMap<String, String> formInfo = new HashMap<String, String>();
			formInfo.put(chkFormName, FORM_TYPES.CHECK);
			formInfo.put(qstFormName, FORM_TYPES.QUESTIONNAIRE);
			
			FormComplianceFields fcf1 = new FormComplianceFields();
			fcf1.fieldName = numericFieldNm;
			fcf1.resourceName = customerInstanceValue1;
			fcf1.UOM = numFldUOM;
			
			FormComplianceFields fcf2 = new FormComplianceFields();
			fcf2.fieldName = SLTFieldNm;
			fcf2.resourceName = resource;
			fcf2.Target_Compliance = SLTFldTrgtCmplnce;
			
			HashMap<String, List<FormComplianceFields>> updateFields = new HashMap<String, List<FormComplianceFields>>();
			updateFields.put(chkFormName,Arrays.asList(fcf1));
			updateFields.put(qstFormName,Arrays.asList(fcf2));
			
			ImportFormCompliance ifc = new ImportFormCompliance();
			ifc.fileName = filePath;
			ifc.formInfo = formInfo;
			ifc.updateFields = updateFields;
			
			boolean updateExcel = diep.updateFormComplianceExcelWithFields(ifc);
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
			
			FormDesignerPage fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			boolean releaseForm = fdp.releaseForm(chkFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + chkFormName, 
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
			
			WebElement FieldUOMLbl = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_UOM_LBL,
					"FIELD_NAME", numericFieldNm);
			if(FieldUOMLbl!=null) {
				String UOMValue = FieldUOMLbl.getText();
				boolean verifyUOMValue = UOMValue.contains(numFldUOM);
				TestValidation.IsTrue(verifyUOMValue, 
						 			  "VERIFIED UOM value for field " + numericFieldNm + " as " + UOMValue, 
						 			  "Failed to Verify UOM value for field " + numericFieldNm);
				
			}
			else {
				TestValidation.Fail("Failed to Verify UOM value for field " + numericFieldNm 
						+ " as the value retrieved is null");
			}
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for \"Form Compliance\" for correct "
			+ "& incorrect data in same file with single form")
	public void TestCase_24285() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.FORM_COMPLIANCE, 
								  "Failed to Set Import type to " + ImportType.FORM_COMPLIANCE);
			
			String resource = RESOURCE_TYPES.CUSTOMERS + " > " + customerCategoryValue2 + " > " + "XYZ";
			String numFldUOM = "Gallons";
			
			HashMap<String, String> formInfo = new HashMap<String, String>();
			formInfo.put(chkFormName, FORM_TYPES.CHECK);
			
			FormComplianceFields ufcf = new FormComplianceFields();
			ufcf.fieldName = numericFieldNm;
			ufcf.resourceName = resource;
			ufcf.UOM = numFldUOM;
			
			HashMap<String, List<FormComplianceFields>> updateFields = new HashMap<String, List<FormComplianceFields>>();
			updateFields.put(chkFormName,Arrays.asList(ufcf));
			
			ImportFormCompliance ifc = new ImportFormCompliance();
			ifc.fileName = filePath;
			ifc.formInfo = formInfo;
			ifc.updateFields = updateFields;
			
			boolean updateExcel = diep.updateFormComplianceExcelWithFields(ifc);
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
			
			String expectedErrMsg = "Invalid Resource(s) found";
			String actualErrMsg = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg = expectedErrMsg.equalsIgnoreCase(actualErrMsg);
			TestValidation.IsTrue(compareErrMsg, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean closePopup = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
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
			
			WebElement FieldUOMLbl = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_UOM_LBL,
					"FIELD_NAME", numericFieldNm);
			if(FieldUOMLbl!=null) {
				String UOMValue = FieldUOMLbl.getText();
				boolean verifyUOMValue = UOMValue.contains(numFldUOM);
				TestValidation.IsFalse(verifyUOMValue, 
						 			  "VERIFIED UOM value for field " + numericFieldNm + " as " + UOMValue, 
						 			  "Failed to Verify UOM value for field " + numericFieldNm);
				
			}
			else {
				TestValidation.Fail("Failed to Verify UOM value for field " + numericFieldNm 
						+ " as the value retrieved is null");
			}
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify import functionality for \"Form Compliance\" with new data "
			+ "and update in old data without selecting \"Update Only\".")
	public void TestCase_38281() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.FORM_COMPLIANCE, 
								  "Failed to Set Import type to " + ImportType.FORM_COMPLIANCE);
			
			String resource = RESOURCE_TYPES.CUSTOMERS + " > " + customerCategoryValue2 + " > " + customerInstanceValue2;
			String SMFldTrgtCmplnce = "90,100";
			String numFldMaxCmplnce = "200";
			String numFldUOMCmplnce = "Pounds";
			
			HashMap<String, String> formInfo = new HashMap<String, String>();
			formInfo.put(adtFormName, FORM_TYPES.AUDIT);
			
			FormComplianceFields fcf1 = new FormComplianceFields();
			fcf1.fieldName = SMFieldNm;
			fcf1.resourceName = resource;
			fcf1.Default = "FALSE";
			fcf1.Visible = "TRUE";
			fcf1.Target_Compliance = SMFldTrgtCmplnce;
			
			FormComplianceFields fcf2 = new FormComplianceFields();
			fcf2.fieldName = numericFieldNm;
			fcf2.resourceName = resource;
			fcf2.Default = "FALSE";
			fcf2.Visible = "TRUE";
			fcf2.Max = numFldMaxCmplnce;
			fcf2.UOM = numFldUOMCmplnce;
			
			HashMap<String, List<FormComplianceFields>> updateFields = new HashMap<String, List<FormComplianceFields>>();
			updateFields.put(adtFormName,Arrays.asList(fcf1, fcf2));
			
			ImportFormCompliance ifc = new ImportFormCompliance();
			ifc.fileName = filePath;
			ifc.formInfo = formInfo;
			ifc.updateFields = updateFields;
			
			boolean updateExcel = diep.updateFormComplianceExcelWithFields(ifc);
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
								  "VERIFIED Import of form is done sucessfully", 
								  "Failed to Verify Import of form is done or not");
			
			FormDesignerPage fdp = new FormDesignerPage(driver);
			boolean formReleasePage = fdp.navigateToReleaseForm();
			TestValidation.IsTrue(formReleasePage, 
								  "Navigated to Release form successfully", 
								  "Could NOT Navigate to Release form"); 
			
			boolean releaseForm = fdp.releaseForm(adtFormName);
			TestValidation.IsTrue(releaseForm, 
							       "SHOULD release form - " + chkFormName, 
								   "Failed since uncertain that the form " + chkFormName + " did/did not release'");
			
			FSQABrowserPage fbp = hp.clickFSQABrowserMenu();
			boolean navigateToFSQAForms = fbp.selectResourceDropDownandNavigate(FSQARESOURCE.CUSTOMERS,FSQATAB.FORMS);
			TestValidation.IsTrue(navigateToFSQAForms, 
								  "For Customers resource, NAVIGATED to FSQABrowser > Forms tab",
								  "Failed to Navigate to FSQABrowser > Forms tab");

			boolean openForm1 = fbp.applyFilterAndOpenForDetails(COLUMNHEADER.FORMNAME, adtFormName);
			TestValidation.IsTrue(openForm1, 
					 			  "OPENED form - " + adtFormName, 
					 			  "Failed to open form - " + adtFormName);
			
			FSQABrowserFormsPage ffp = new FSQABrowserFormsPage(driver);
			int resourceCount = ffp.FormResourceLst.size();
			TestValidation.IsTrue(resourceCount==2, 
					 			  "TWO resources found for form - " + adtFormName, 
					 			  "Failed to verify resources found for form - " + adtFormName);
			
			boolean setResourceForForm = ffp.setResourceForForm(customerInstanceValue2);
			TestValidation.IsTrue(setResourceForForm, 
								  "Set Resource for Form successfully to " + customerInstanceValue2, 
								  "Could NOT Set Resource for Form to " + customerInstanceValue2);
			
			WebElement FieldUOMLbl = controlActions.perform_GetElementByXPath(FSQABrowserFormsPageLocators.FIELD_UOM_LBL,
					"FIELD_NAME", numericFieldNm);
			if(FieldUOMLbl!=null) {
				String UOMValue = FieldUOMLbl.getText();
				boolean verifyUOMValue = UOMValue.contains(numFldUOMCmplnce);
				TestValidation.IsTrue(verifyUOMValue, 
						 			  "VERIFIED UOM value for field " + numericFieldNm + " as " + UOMValue, 
						 			  "Failed to Verify UOM value for field " + numericFieldNm);
				
			}
			else {
				TestValidation.Fail("Failed to Verify UOM value for field " + numericFieldNm 
						+ " as the value retrieved is null");
			}
			
			HashMap<String, List<String>> fillData1 = new HashMap<String, List<String>>();
			fillData1.put(numericFieldNm,Arrays.asList("201"));
			fillData1.put(SMFieldNm,Arrays.asList("100"));
			fillData1.put(dateFieldNm,Arrays.asList("9/15/2021"));
			
			//Field Property objects
			FieldProperties fp1 = new FieldProperties();
			fp1.fieldName = numericFieldNm;
			fp1.complianceStatusCheck = true;
			fp1.complianceStatus = "Non-Compliant";
			
			FieldProperties fp2 = new FieldProperties();
			fp2.fieldName = SMFieldNm;
			fp2.complianceStatusCheck = true;
			fp2.complianceStatus = "Compliant";
			
			FieldProperties fp3 = new FieldProperties();
			fp3.fieldName = dateFieldNm;
			fp3.complianceStatusCheck = true;
			fp3.complianceStatus = "No Compliance";

			//Form object
			FormDetails fd = new FormDetails();
			fd.fieldDetails = fillData1;
			//Field property assignment  
			fd.fieldProperties = Arrays.asList(fp1, fp2, fp3);

			FormOperations fo = new FormOperations(driver);
			boolean submit = fo.submitData(fd);
			TestValidation.IsTrue(submit, 
								  "Form submitted" + adtFormName, 
								  "Failed to submit form" + adtFormName);
		
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify download,inspect error and reprocess of the failed files for \"Form Compliance\".")
	public void TestCase_24291() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.FORM_COMPLIANCE, 
								  "Failed to Set Import type to " + ImportType.FORM_COMPLIANCE);
			
			String SOFldTrgtCmplnce = "Strawberry";
			
			HashMap<String, String> formInfo = new HashMap<String, String>();
			formInfo.put(qstFormName, FORM_TYPES.QUESTIONNAIRE);
			
			FormComplianceFields ufcf = new FormComplianceFields();
			ufcf.fieldName = SOFieldNm;
			ufcf.resourceName = customerInstanceValue1;
			ufcf.Target_Compliance = SOFldTrgtCmplnce;
			
			HashMap<String, List<FormComplianceFields>> updateFields = new HashMap<String, List<FormComplianceFields>>();
			updateFields.put(qstFormName,Arrays.asList(ufcf));
			
			ImportFormCompliance ifc = new ImportFormCompliance();
			ifc.fileName = filePath;
			ifc.formInfo = formInfo;
			ifc.updateFields = updateFields;
			
			boolean updateExcel = diep.updateFormComplianceExcelWithFields(ifc);
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
			
			boolean openInspErrPopup1 = diep.openInspectErrorsPopupForLatestImport();
			TestValidation.IsTrue(openInspErrPopup1, 
								  "OPENED Inspect Errors popup for the Import done", 
								  "Failed to Open Inspect Errors popup for the Import done");
			
			String expectedErrMsg = "Compliance value selected must be from within the set of field values.";
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
	
	@Test(description = "DPT || Export - Verify download of Form Compliance data.")
	public void TestCase_24270() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
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
			
			Boolean D_SLTDefaultVal = false;
			Boolean D_SLTVisibleVal = false;
			String D_SLTTrgtCmplnceVal = "cust";
			Boolean D_SODefaultVal = false;
			Boolean D_SOVisibleVal = false;
			String D_SOTrgtCmplnceVal = "Apple";
			Boolean R_SLTDefaultVal = false;
			Boolean R_SLTVisibleVal = true;
			String R_SLTTrgtCmplnceVal = "cust";
			Boolean R_SODefaultVal = false;
			Boolean R_SOVisibleVal = true;
			String R_SOTrgtCmplnceVal = "Apple";
			
			FormComplianceFields fcf1 = new FormComplianceFields();
			fcf1.fieldName = SLTFieldNm;
			fcf1.resourceName = "Default";
			fcf1.vDefault = D_SLTDefaultVal;
			fcf1.vVisible = D_SLTVisibleVal;
			fcf1.Target_Compliance = D_SLTTrgtCmplnceVal;
			
			FormComplianceFields fcf2 = new FormComplianceFields();
			fcf2.fieldName = SOFieldNm;
			fcf2.resourceName = "Default";
			fcf2.vDefault = D_SODefaultVal;
			fcf2.vVisible = D_SOVisibleVal;
			fcf2.Target_Compliance = D_SOTrgtCmplnceVal;
			
			FormComplianceFields fcf3 = new FormComplianceFields();
			fcf3.fieldName = SLTFieldNm;
			fcf3.resourceName = customerInstanceValue1;
			fcf3.vDefault = R_SLTDefaultVal;
			fcf3.vVisible = R_SLTVisibleVal;
			fcf3.Target_Compliance = R_SLTTrgtCmplnceVal;
			
			FormComplianceFields fcf4 = new FormComplianceFields();
			fcf4.fieldName = SOFieldNm;
			fcf4.resourceName = customerInstanceValue1;
			fcf4.vDefault = R_SODefaultVal;
			fcf4.vVisible = R_SOVisibleVal;
			fcf4.Target_Compliance = R_SOTrgtCmplnceVal;
			
			HashMap<String, List<FormComplianceFields>> verifyFields = new HashMap<String, List<FormComplianceFields>>();
			verifyFields.put(qstFormName, Arrays.asList(fcf1, fcf2, fcf3, fcf4));
			
			ExportFormComplianceFields efcf = new ExportFormComplianceFields();
			efcf.fileName = filePath;
			efcf.formName = qstFormName;
			efcf.verifyFields = verifyFields;
			
			boolean updateExcel = diep.verifyExportedFormCompliance(efcf);
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
	
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Form Compliance.")
	public void TestCase_24290() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
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
	
	@Test(description = "DPT || Export>Data Extract - Verify the Filter functionality for form compliance.")
	public void TestCase_24274() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.FORM_COMPLIANCE);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.FORM_COMPLIANCE, 
								  "Failed to Set Extract type to " + ExtractType.FORM_COMPLIANCE);
			
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

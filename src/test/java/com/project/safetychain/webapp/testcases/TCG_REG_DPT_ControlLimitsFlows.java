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
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ChartFieldSettingNames;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ColumnNames;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ControlLimitFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.IMPORT_STATUS;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportType;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.CHARTS_FIELDS;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage.SubTabs;
import com.project.safetychain.api.models.Auth;
import com.project.safetychain.api.models.support.TCG_ChartBuilder_Wrapper;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.ChartBuilder;
import com.project.safetychain.api.models.support.Support.ChartResources;
import com.project.safetychain.api.models.support.Support.Chart_Rules;
import com.project.safetychain.api.models.support.Support.Chart_Template_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FIELD_TYPES;
import com.project.safetychain.api.models.support.Support.DPT_FORM_TYPES;
import com.project.safetychain.api.models.support.Support.DefaultChart;
import com.project.safetychain.api.models.support.Support.Element_Types;
import com.project.safetychain.api.models.support.Support.FormFieldParams;
import com.project.safetychain.api.models.support.Support.FormParams;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPageLocators;
import com.project.safetychain.webapp.pageobjects.FormsManagerChartsPage;
import com.project.safetychain.webapp.pageobjects.FormsManagerPage;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.utilities.ApiUtils;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DPT_ControlLimitsFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	LoginPage lp;
	DateTime dt = new DateTime();
	
	public static String locationCategoryValue, equipCategoryValue;
	public static String locationInstanceValue, equipInstanceValue1, equipInstanceValue2;
	public static String chkChartName1, adtChartName, chkChartName2;
	
	// Form and Fields
	public static String chkFormName, qstFormName, adtFormName;
	public static String sectionAFieldNm = "Section A", sectionBFieldNm = "Section B", 
			numericFieldNm1 = "Numeric1", numericFieldNm2 = "Numeric2";
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {
		
		// TCG IN PROGRESS
		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		equipCategoryValue = CommonMethods.dynamicText("ECat");
		equipInstanceValue1 = CommonMethods.dynamicText("EInst1");
		equipInstanceValue2 = CommonMethods.dynamicText("EInst2");
		chkFormName = CommonMethods.dynamicText("ChkForm");
		adtFormName = CommonMethods.dynamicText("AdtForm");
		chkChartName1 = CommonMethods.dynamicText("CChrt1");
		adtChartName = CommonMethods.dynamicText("AChrt");
		chkChartName2 = CommonMethods.dynamicText("CChrt2");
		
		// ------------------------------------------------------------------------------------------------
		// API Implementation
		TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();
		TCG_ChartBuilder_Wrapper chartBuilderWrapper = new TCG_ChartBuilder_Wrapper();
		ApiUtils apiUtils = new ApiUtils();
		Auth auth = new Auth();
		
		// ------------------------------------------------------------------------------------------------
		// API - Location & Resource Creation and Linking
		List<String> userList = Arrays.asList(adminUsername);

		HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
		LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

		HashMap<String, List<String>> resourceCatMap = new HashMap<String, List<String>>();
		resourceCatMap.put(equipCategoryValue, Arrays.asList(equipInstanceValue1, equipInstanceValue2));
		
		HashMap<String, String> locResMap = formCreationWrapper.Resource_Location_CreationWrapper(resourceCatMap, LocationMap, 
				true, userList, RESOURCE_TYPES.EQUIPMENT);
		
		// ------------------------------------------------------------------------------------------------
		// API - Form Creation - Check
		// API - Add fields to form
		FormFieldParams numericField1 = new FormFieldParams();
		numericField1.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField1.Name = numericFieldNm1;

		FormFieldParams numericField2 = new FormFieldParams();
		numericField2.DPTFieldType = DPT_FIELD_TYPES.NUMERIC;
		numericField2.Name = numericFieldNm2;
		
		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdChk = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormChk = new FormParams();
		fpFormChk.FormId = formIdChk;
		fpFormChk.FormName = chkFormName;
		fpFormChk.type = DPT_FORM_TYPES.CHECK;
		fpFormChk.ResourceType = RESOURCE_TYPES.EQUIPMENT;
		fpFormChk.ResourceCategoryNm = equipCategoryValue;
		fpFormChk.ResourceInstanceNm = equipInstanceValue1;
		fpFormChk.formElements = Arrays.asList(numericField1, numericField2);
		fpFormChk.EquipmentResources = resourceCatMap;

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
		SectionA.formFieldParams = Arrays.asList(numericField1);
		
		// Section B
		Element_Types SectionB = new Element_Types();
		SectionB.ElementType = DPT_FIELD_TYPES.SECTION;
		SectionB.Name = sectionBFieldNm;
		SectionB.formFieldParams = Arrays.asList(numericField2);

		// API - Form details
		// API - Generate unique ID for form to be created
		String formIdAdt = apiUtils.getUUID();

		// API - Form layout details
		FormParams fpFormAdt = new FormParams();
		fpFormAdt.FormId = formIdAdt;
		fpFormAdt.FormName = adtFormName;
		fpFormAdt.type = DPT_FORM_TYPES.AUDIT;
		fpFormAdt.ResourceType = RESOURCE_TYPES.EQUIPMENT;
		fpFormAdt.ResourceCategoryNm = equipCategoryValue;
		fpFormAdt.ResourceInstanceNm = equipInstanceValue2;
		fpFormAdt.EquipmentResources = resourceCatMap;
		fpFormAdt.SectionElements = Arrays.asList(SectionA, SectionB);

		formCreationWrapper.API_Wrapper_Forms(fpFormAdt);

		// ------------------------------------------------------------------------------------------------
		// Reset Access Token 
		auth.setAccessToken();
		
		// ------------------------------------------------------------------------------------------------
		// API - XiMr Chart creation and linking to a Form - Check
		// Sets :
		// 		Mean, Variation for Default
		// 		Mean, Variation for equipInstanceValue1

		// Get Resources ID
		String locInstId = locResMap.get(locationInstanceValue);
		String resInstId1 = locResMap.get(equipInstanceValue1);
		String resInstId2 = locResMap.get(equipInstanceValue2);
		
		// Chart Default
		DefaultChart defaultChart = new DefaultChart();
		defaultChart.Variation = "10";
		defaultChart.Mean = "20";
		
		// Chart Resources
		ChartResources chartResources1 = new ChartResources();
		chartResources1.ResourceName = equipInstanceValue1;
		chartResources1.ResourceId = resInstId1;
		chartResources1.Mean = "50";
		chartResources1.Variation = "25";

		ChartBuilder cb1 = new ChartBuilder();
		cb1.chartConfList = Arrays.asList(Chart_Rules.Rule_5, Chart_Rules.Rule_8);
		cb1.Name = chkChartName1;
		cb1.chartLinkingName = chkChartName1;
		cb1.formId = formIdChk;
		cb1.formName = chkFormName;
		cb1.locationId = locInstId;
		cb1.chartTemplateType = Chart_Template_TYPES.XiMr;
		cb1.chartResourceList = Arrays.asList(chartResources1);
		cb1.ChartFieldsList = Arrays.asList(numericFieldNm1, numericFieldNm2);
		cb1.defaultChart = defaultChart;

		chartBuilderWrapper.create_Chart_Wrapper(cb1);
		
		// ------------------------------------------------------------------------------------------------
		// API - Chart creation and linking to a Form - Check
		// Sets :
		// 		Mean, Variation for Default
		// 		Mean, Variation for equipInstanceValue2
		// 		Mean, Variation for equipInstanceValue1
		
		ChartResources chartResources2 = new ChartResources();
		chartResources2.ResourceName = equipInstanceValue2;
		chartResources2.ResourceId = resInstId2;
		chartResources2.Mean = "56";
		chartResources2.Variation = "31";
		
		ChartResources chartResources3 = new ChartResources();
		chartResources3.ResourceName = equipInstanceValue1;
		chartResources3.ResourceId = resInstId1;
		chartResources3.Mean = "6";
		chartResources3.Variation = "3";

		ChartBuilder cb2 = new ChartBuilder();
		cb2.chartConfList = Arrays.asList(Chart_Rules.Rule_1, Chart_Rules.Rule_2, Chart_Rules.Sec_Rule_4);
		cb2.Name = chkChartName2;
		cb2.chartLinkingName = chkChartName2;
		cb2.formId = formIdChk;
		cb2.formName = chkFormName;
		cb2.locationId = locInstId;
		cb2.chartTemplateType = Chart_Template_TYPES.XBarR;
		cb2.chartResourceList = Arrays.asList(chartResources2, chartResources3);
		cb2.ChartFieldsList = Arrays.asList(numericFieldNm1, numericFieldNm2);
		cb2.defaultChart = defaultChart;

		chartBuilderWrapper.create_Chart_Wrapper(cb2);
		
		// ------------------------------------------------------------------------------------------------
		// API - Chart creation and linking to a Form - Audit
		// Sets - Nothing just for default; just associates chart with form

		ChartBuilder cb3 = new ChartBuilder();
		cb3.chartConfList = Arrays.asList(Chart_Rules.Rule_9);
		cb3.Name = adtChartName;
		cb3.chartLinkingName = adtChartName;
		cb3.formId = formIdAdt;
		cb3.formName = adtFormName;
		cb3.locationId = locInstId;
		cb3.chartTemplateType = Chart_Template_TYPES.CChart;
		cb3.ChartFieldsList = Arrays.asList(numericFieldNm1, numericFieldNm2);
		cb3.defaultChart = defaultChart;

		chartBuilderWrapper.create_Chart_Wrapper(cb3);
				
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

	@Test(description = "DPT || Verify single and multiple data extract for \"Control Limits\" extract type")
	public void TestCase_37840() {
		
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
			
			boolean setType1 = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType1, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);

			List<String> items1 = Arrays.asList(chkChartName1);
			WebElement element1 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.CHART_NAME);
			boolean selectSingleForm = diep.selectItemsInGrid(element1,items1);
			TestValidation.IsTrue(selectSingleForm, 
								  "SELECTED chart " + chkChartName1, 
								  "Failed to Select chart " + chkChartName1);
			
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
			
			boolean setType2 = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType2, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
			List<String> items2 = Arrays.asList(chkChartName1, adtChartName);
			WebElement element2 = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.CHART_NAME);
			boolean selectMultipleForms = diep.selectItemsInGrid(element2,items2);
			TestValidation.IsTrue(selectMultipleForms, 
								  "SELECTED charts - " + chkChartName1 + " and " + adtChartName, 
								  "Failed to Select charts - " + chkChartName1 + " and " + adtChartName);
			
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
	
	@Test(description = "DPT || Verify the import functionality for \"Control Limits\" "
			+ "having new data and updated data in one file")
	public void TestCase_37848() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
			List<String> items = Arrays.asList(chkChartName1);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.CHART_NAME);
			boolean selectChkForm = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectChkForm, 
								  "SELECTED chart " + chkChartName1, 
								  "Failed to Select chart " + chkChartName1);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.CONTROL_LIMITS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.CONTROL_LIMITS, 
								  "Failed to Set Import type to " + ImportType.CONTROL_LIMITS);
			
			String equipInst2Applicable = "TRUE";
			String equipInst2MeanVal = "41.5";
			String equipInst2VariationVal = "13.5";
			String equipInst1MeanVal = "110";
			String equipInst1CmmtVal = "This is Comment";
			
			HashMap<String, String> chartSettings1 = new HashMap<String, String>();
			chartSettings1.put(ChartFieldSettingNames.IS_APPLICABLE, equipInst2Applicable);
			chartSettings1.put(ChartFieldSettingNames.MEAN, equipInst2MeanVal);
			chartSettings1.put(ChartFieldSettingNames.VARIATION, equipInst2VariationVal);
			
			HashMap<String, String> chartSettings2 = new HashMap<String, String>();
			chartSettings2.put(ChartFieldSettingNames.MEAN, equipInst1MeanVal);
			chartSettings2.put(ChartFieldSettingNames.COMMENT, equipInst1CmmtVal);
			
			HashMap<String,HashMap<String, String>> updateFields = new HashMap<String,HashMap<String, String>>();
			updateFields.put(equipInstanceValue2,chartSettings1);
			updateFields.put(equipInstanceValue1,chartSettings2);
			
			ControlLimitFields clf = new ControlLimitFields();
			clf.fileName = filePath;
			clf.fieldsForChart = updateFields;
			
			boolean updateExcel = diep.updateControlLimitsExcelWithSettings(clf);
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
			
			FormsManagerPage fmp = hp.clickFormsManagerMenu();
			boolean navigateToChartsTab = fmp.selectFormAndNavigaToCharts(chkFormName);
			TestValidation.IsTrue(navigateToChartsTab, 
								  "NAVIGATED to Charts tab for form " + chkFormName, 
								  "Failed to Navigate to Charts tab for form " + chkFormName);
			
			FormsManagerChartsPage fmcp = new FormsManagerChartsPage(driver);
			boolean openChart = fmcp.selectChartForForm(chkChartName1); 
			TestValidation.IsTrue(openChart, 
								  "OPENED Chart " + chkChartName1, 
								  "Failed to Open Chart " + chkChartName1);
			
			boolean selectResourcesTab = fmcp.selectChartsTab(SubTabs.RESOURCES); 
			TestValidation.IsTrue(selectResourcesTab, 
								  "OPENED Chart's tab " + SubTabs.RESOURCES, 
								  "Failed to Open Chart's tab " + SubTabs.RESOURCES);
			
			String equipInst2Selected = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.RESOURCE_SELECT_CHK, equipInstanceValue2);
			boolean verifyEquipInst2Selected = equipInst2Selected.equalsIgnoreCase(equipInst2Applicable);
			TestValidation.IsTrue(verifyEquipInst2Selected, 
								  "VERIFIED equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.RESOURCE_SELECT_CHK + " is " + equipInst2Selected, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.RESOURCE_SELECT_CHK + " is " + equipInst2Selected);
			
			String equipInst2Default = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.DEFAULT, equipInstanceValue2);
			boolean verifyEquipInst2Default = equipInst2Default.equalsIgnoreCase("FALSE");
			TestValidation.IsTrue(verifyEquipInst2Default, 
								  "VERIFIED equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.DEFAULT + " is " + equipInst2Default, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.DEFAULT + " is " + equipInst2Default);	
			
			String equipInst2Mean = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.MEAN, equipInstanceValue2);
			boolean verifyEquipInst2Mean = equipInst2Mean.contains(equipInst2MeanVal);
			TestValidation.IsTrue(verifyEquipInst2Mean, 
								  "VERIFIED equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.MEAN + " is " + equipInst2Mean, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.MEAN + " is " + equipInst2Mean);	
			
			String equipInst2Variation = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.VARIATION, equipInstanceValue2);
			boolean verifyEquipInst2Variation = equipInst2Variation.contains(equipInst2VariationVal);
			TestValidation.IsTrue(verifyEquipInst2Variation, 
								  "VERIFIED equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.VARIATION + " is " + equipInst2Variation, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.VARIATION + " is " + equipInst2Variation);	

			String equipInst1Selected = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.RESOURCE_SELECT_CHK, equipInstanceValue1);
			boolean verifyEquipInst1Selected = equipInst1Selected.equalsIgnoreCase("TRUE");
			TestValidation.IsTrue(verifyEquipInst1Selected, 
								  "VERIFIED equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.RESOURCE_SELECT_CHK + " is " + equipInst1Selected, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.RESOURCE_SELECT_CHK + " is " + equipInst1Selected);	

			String equipInst1Default = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.DEFAULT, equipInstanceValue1);
			boolean verifyEquipInst1Default = equipInst1Default.equalsIgnoreCase("FALSE");
			TestValidation.IsTrue(verifyEquipInst1Default, 
								  "VERIFIED equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.DEFAULT + " is " + equipInst1Default, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.DEFAULT + " is " + equipInst1Default);	

			String equipInst1Mean = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.MEAN, equipInstanceValue1);
			boolean verifyEquipInst1Mean = equipInst1Mean.contains(equipInst1MeanVal);
			TestValidation.IsTrue(verifyEquipInst1Mean, 
								  "VERIFIED equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.MEAN + " is " + equipInst1Mean, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.MEAN + " is " + equipInst1Mean);	

			String equipInst1Variation = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.VARIATION, equipInstanceValue1);
			boolean verifyEquipInst1Variation = equipInst1Variation.contains("25");
			TestValidation.IsTrue(verifyEquipInst1Variation, 
								  "VERIFIED equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.VARIATION + " is " + equipInst1Variation, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.VARIATION + " is " + equipInst1Variation);	

			String defaultMean = fmcp.getChartResourceHeaderDetails(CHARTS_FIELDS.MEAN);
			boolean verifyDefaultMean = defaultMean.contains("20");
			TestValidation.IsTrue(verifyDefaultMean, 
								  "VERIFIED Default value for " + CHARTS_FIELDS.MEAN + " is " + defaultMean, 
								  "Failed to Verify Default value for " + CHARTS_FIELDS.MEAN + " is " + defaultMean);	

			String defaultVariation = fmcp.getChartResourceHeaderDetails(CHARTS_FIELDS.VARIATION);
			boolean verifyDefaultVariation = defaultVariation.contains("10");
			TestValidation.IsTrue(verifyDefaultVariation, 
								  "VERIFIED Default value for " + CHARTS_FIELDS.VARIATION + " is " + defaultVariation, 
								  "Failed to Verify Default value for " + CHARTS_FIELDS.VARIATION + " is " + defaultVariation);
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for \"Control Limits\" "
			+ "for correct & incorrect data in same file")
	public void TestCase_37874() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
			List<String> items = Arrays.asList(adtChartName);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.CHART_NAME);
			boolean selectChkForm = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectChkForm, 
								  "SELECTED chart " + adtChartName, 
								  "Failed to Select chart " + adtChartName);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.CONTROL_LIMITS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.CONTROL_LIMITS, 
								  "Failed to Set Import type to " + ImportType.CONTROL_LIMITS);
			
			String equipInst2Applicable = "TRUE";
			String equipInst2MeanVal = "2";
			String equipInst1MeanVal = "ABC";
			String equipInst1CmmtVal = "This is Comment";
			
			HashMap<String, String> chartSettings1 = new HashMap<String, String>();
			chartSettings1.put(ChartFieldSettingNames.IS_APPLICABLE, equipInst2Applicable);
			chartSettings1.put(ChartFieldSettingNames.MEAN, equipInst2MeanVal);
			
			HashMap<String, String> chartSettings2 = new HashMap<String, String>();
			chartSettings2.put(ChartFieldSettingNames.MEAN, equipInst1MeanVal);
			chartSettings2.put(ChartFieldSettingNames.COMMENT, equipInst1CmmtVal);
			
			HashMap<String,HashMap<String, String>> updateFields = new HashMap<String,HashMap<String, String>>();
			updateFields.put(equipInstanceValue2,chartSettings1);
			updateFields.put(equipInstanceValue1,chartSettings2);
			
			ControlLimitFields clf = new ControlLimitFields();
			clf.fileName = filePath;
			clf.fieldsForChart = updateFields;
			
			boolean updateExcel = diep.updateControlLimitsExcelWithSettings(clf);
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
			
//			String expectedErrMsg = "Invalid Control Limits Configuration values Uploaded";
			String expectedErrMsg = "Value must Numeric for Mean";
			String actualErrMsg = diep.getImportTableDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg = expectedErrMsg.equalsIgnoreCase(actualErrMsg);
			TestValidation.IsTrue(compareErrMsg, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			FormsManagerPage fmp = hp.clickFormsManagerMenu();
			boolean navigateToChartsTab = fmp.selectFormAndNavigaToCharts(adtFormName);
			TestValidation.IsTrue(navigateToChartsTab, 
								  "NAVIGATED to Charts tab for form " + adtFormName, 
								  "Failed to Navigate to Charts tab for form " + adtFormName);
			
			FormsManagerChartsPage fmcp = new FormsManagerChartsPage(driver);
			boolean openChart = fmcp.selectChartForForm(adtChartName); 
			TestValidation.IsTrue(openChart, 
								  "OPENED Chart " + adtChartName, 
								  "Failed to Open Chart " + adtChartName);
			
			boolean selectResourcesTab = fmcp.selectChartsTab(SubTabs.RESOURCES); 
			TestValidation.IsTrue(selectResourcesTab, 
								  "OPENED Chart's tab " + SubTabs.RESOURCES, 
								  "Failed to Open Chart's tab " + SubTabs.RESOURCES);
			
			String equipInst2Selected = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.RESOURCE_SELECT_CHK, equipInstanceValue2);
			boolean verifyEquipInst2Selected = equipInst2Selected.equalsIgnoreCase("FALSE");
			TestValidation.IsTrue(verifyEquipInst2Selected, 
								  "VERIFIED equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.RESOURCE_SELECT_CHK + " is " + equipInst2Selected, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.RESOURCE_SELECT_CHK + " is " + equipInst2Selected);
			
			String equipInst2Default = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.DEFAULT, equipInstanceValue2);
			boolean verifyEquipInst2Default = equipInst2Default.equalsIgnoreCase("FALSE");
			TestValidation.IsTrue(verifyEquipInst2Default, 
								  "VERIFIED equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.DEFAULT + " is " + equipInst2Default, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.DEFAULT + " is " + equipInst2Default);	
			
			String equipInst2Mean = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.MEAN, equipInstanceValue2);
			TestValidation.IsTrue(equipInst2Mean == null, 
								  "VERIFIED equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.MEAN + " is blank", 
								  "Failed to Verify equipment resource's - " + equipInstanceValue2 
								  	+ ", value for " + CHARTS_FIELDS.MEAN + " is blank");	
			
			String equipInst1Selected = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.RESOURCE_SELECT_CHK, equipInstanceValue1);
			boolean verifyEquipInst1Selected = equipInst1Selected.equalsIgnoreCase("FALSE");
			TestValidation.IsTrue(verifyEquipInst1Selected, 
								  "VERIFIED equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.RESOURCE_SELECT_CHK + " is " + equipInst1Selected, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.RESOURCE_SELECT_CHK + " is " + equipInst1Selected);	

			String equipInst1Default = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.DEFAULT, equipInstanceValue1);
			boolean verifyEquipInst1Default = equipInst1Default.equalsIgnoreCase("FALSE");
			TestValidation.IsTrue(verifyEquipInst1Default, 
								  "VERIFIED equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.DEFAULT + " is " + equipInst1Default, 
								  "Failed to Verify equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.DEFAULT + " is " + equipInst1Default);	

			String equipInst1Mean = fmcp.getChartResourceGridDetails(CHARTS_FIELDS.MEAN, equipInstanceValue1);
			TestValidation.IsTrue(equipInst1Mean == null, 
								  "VERIFIED equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.MEAN + " is blank", 
								  "Failed to Verify equipment resource's - " + equipInstanceValue1 
								  	+ ", value for " + CHARTS_FIELDS.MEAN + " is blank");	

			String defaultMean = fmcp.getChartResourceHeaderDetails(CHARTS_FIELDS.MEAN);
			boolean verifyDefaultMean = defaultMean.contains("20");
			TestValidation.IsTrue(verifyDefaultMean, 
								  "VERIFIED Default value for " + CHARTS_FIELDS.MEAN + " is " + defaultMean, 
								  "Failed to Verify Default value for " + CHARTS_FIELDS.MEAN + " is " + defaultMean);	

		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}

	@Test(description = "DPT || Verify download,inspect error and reprocess of the failed files for \"Control Limits\". ")
	public void TestCase_37881() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
			List<String> items = Arrays.asList(chkChartName2);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.CHART_NAME);
			boolean selectChkForm = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectChkForm, 
								  "SELECTED chart " + chkChartName2, 
								  "Failed to Select chart " + chkChartName2);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.CONTROL_LIMITS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.CONTROL_LIMITS, 
								  "Failed to Set Import type to " + ImportType.CONTROL_LIMITS);

			ControlLimitFields clf = new ControlLimitFields();
			clf.fileName = filePath;
			clf.chartName = chkChartName2 + "1";
			
			boolean updateExcel = diep.updateControlLimitsExcelWithSettings(clf);
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
			
			String expectedErrMsg = "Invalid Chart Name";
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
	
	@Test(description = "DPT || Export - Verify download of Control Limits data.")
	public void TestCase_38430() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
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
			
			String defaultRsrcIsDefaultVal = "FALSE";
			String defaultRsrcMeanVal = "20.0";
			String equipInst1IsApplicableVal = "FALSE";
			String equipInst1IsDefaultVal = "FALSE";
			
			HashMap<String, String> chartSettings1 = new HashMap<String, String>();
			chartSettings1.put(ChartFieldSettingNames.IS_DEFAULT, defaultRsrcIsDefaultVal);
			chartSettings1.put(ChartFieldSettingNames.MEAN, defaultRsrcMeanVal);
			
			HashMap<String, String> chartSettings2 = new HashMap<String, String>();
			chartSettings2.put(ChartFieldSettingNames.IS_APPLICABLE, equipInst1IsApplicableVal);
			chartSettings2.put(ChartFieldSettingNames.IS_DEFAULT, equipInst1IsDefaultVal);
			chartSettings2.put(ChartFieldSettingNames.COMMENT, "");
			
			HashMap<String, String> chartSettings3 = new HashMap<String, String>();
			chartSettings3.put(ChartFieldSettingNames.MEAN, "");
			chartSettings3.put(ChartFieldSettingNames.COMMENT, "");
			
			HashMap<String,HashMap<String, String>> verifyFields = new HashMap<String,HashMap<String, String>>();
			verifyFields.put(equipInstanceValue2,chartSettings3);
			verifyFields.put("Default",chartSettings1);
			verifyFields.put(equipInstanceValue1,chartSettings2);
			
			ControlLimitFields clf = new ControlLimitFields();
			clf.fileName = filePath;
			clf.fieldsForChart = verifyFields;
			clf.formName = adtFormName;
			clf.locationName = locationInstanceValue;
			
			boolean verifyExcel = diep.verifyExportedControlLimits(clf);
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
	
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Control Limits.")
	public void TestCase_38538() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
			boolean searchTxt = diep.searchExtractItem(chkChartName1);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + chkChartName1, 
								  "Failed to Search for text " + chkChartName1);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.SELECT_VALUE_INP, 
												"VALUE", chkFormName);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + chkChartName1, 
								  "Failed to Verify whether element " + chkChartName1 + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract- Verify the Filter functionality for control limits.")
	public void TestCase_38543() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.CONTROL_LIMITS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.CONTROL_LIMITS, 
								  "Failed to Set Extract type to " + ExtractType.CONTROL_LIMITS);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.CHART_NAME, 
					COLUMN_SETTING.FILTER, chkChartName2);
			TestValidation.IsTrue(applyFilter, 
								  "For Chart Name column, APPLIED filter using text " + chkChartName2, 
								  "Failed to apply filter on Chart Name with text " + chkChartName2);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.SELECT_VALUE_INP, 
												"VALUE", chkFormName);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + chkChartName2, 
								  "Failed to Verify whether element " + chkChartName2 + " is present or not");
			
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

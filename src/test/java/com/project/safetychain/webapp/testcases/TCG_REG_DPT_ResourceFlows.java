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
import com.project.safetychain.webapp.pageobjects.ManageResourcePage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.COLUMN_SETTING;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ColumnNames;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExportLocationFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExportResourceFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ExtractType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.IMPORT_STATUS;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportResourceFields;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ImportType;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage.ResourceFieldValues;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.RESOURCETYPES;
import com.project.safetychain.webapp.pageobjects.ManageResourcePage.ResourceDetailParams;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage;
import com.project.safetychain.webapp.pageobjects.ResourceDesignerPage.CATEGORYTYPES;
import com.project.safetychain.api.models.support.TCG_FormCreation_Wrapper;
import com.project.safetychain.api.models.support.Support.RESOURCE_TYPES;
import com.project.safetychain.webapp.pageobjects.DataImportExportPage;
import com.project.safetychain.webapp.pageobjects.DataImportExportPageLocators;
import com.project.testbase.TestBase;
import com.project.testbase.TestValidation;
import com.project.testbase.SupportingClasses.ExecutionMode;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;
import com.project.utilities.DateTime;

public class TCG_REG_DPT_ResourceFlows extends TestBase{
	ControlActions controlActions;
	HomePage hp;
	DateTime dt = new DateTime();
	
	public static String locationCategoryValue, customerCategoryValue, 
							equipmentCategoryValue, itemCategoryValue, supplierCategoryValue;
	public static String locationInstanceValue, customerInstanceValue,
							equipmentInstanceValue, itemInstanceValue, supplierInstanceValue;
	
	@BeforeClass(alwaysRun = true)
	public void groupInit() throws Exception {

		locationCategoryValue = locationName;
		locationInstanceValue = locationName;
		customerCategoryValue = CommonMethods.dynamicText("Cust_Cat");
		equipmentCategoryValue = CommonMethods.dynamicText("Equip_Cat");
		itemCategoryValue = CommonMethods.dynamicText("Item_Cat");
		supplierCategoryValue = CommonMethods.dynamicText("Supp_Cat");
		customerInstanceValue = CommonMethods.dynamicText("Cust_Inst");
		equipmentInstanceValue = CommonMethods.dynamicText("Equip_Inst");
		itemInstanceValue = CommonMethods.dynamicText("Item_Inst");
		supplierInstanceValue = CommonMethods.dynamicText("Supp_Inst");
		
		if(executionMode.equalsIgnoreCase(ExecutionMode.API)) {
			// ------------------------------------------------------------------------------------------------
			// API Implementation
			TCG_FormCreation_Wrapper formCreationWrapper = new TCG_FormCreation_Wrapper();

			// ------------------------------------------------------------------------------------------------
			// API - Location & Resource Creation and Linking
			List<String> userList = Arrays.asList(adminUsername);

			// For Customer creation
			HashMap<String, List<String>> LocationMap = new HashMap<String, List<String>>();
			LocationMap.put(locationCategoryValue, Arrays.asList(locationInstanceValue));

			HashMap<String, List<String>> resourceCustMap = new HashMap<String, List<String>>();
			resourceCustMap.put(customerCategoryValue, Arrays.asList(customerInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceCustMap, LocationMap, true, userList,
					RESOURCE_TYPES.CUSTOMERS);

			// For Equipment creation
			HashMap<String, List<String>> resourceEquipMap = new HashMap<String, List<String>>();
			resourceEquipMap.put(equipmentCategoryValue, Arrays.asList(equipmentInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceEquipMap, LocationMap, true, userList,
					RESOURCE_TYPES.EQUIPMENT);

			// For Item creation
			HashMap<String, List<String>> resourceItemMap = new HashMap<String, List<String>>();
			resourceItemMap.put(itemCategoryValue, Arrays.asList(itemInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceItemMap, LocationMap, true, userList,
					RESOURCE_TYPES.ITEMS);

			// For Supplier creation
			HashMap<String, List<String>> resourceSuppMap = new HashMap<String, List<String>>();
			resourceSuppMap.put(supplierCategoryValue, Arrays.asList(supplierInstanceValue));

			formCreationWrapper.Resource_Location_CreationWrapper(resourceSuppMap, LocationMap, true, userList,
					RESOURCE_TYPES.SUPPLIERS);

			// ------------------------------------------------------------------------------------------------
			// Supporting WEB Application code starts here
			driver = launchbrowser();
			controlActions = new ControlActions(driver);
			controlActions.getUrl(applicationUrl);
			hp = new HomePage(driver);

			LoginPage lp = new LoginPage(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
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
			hp = new HomePage(driver);

			LoginPage lp = new LoginPage(driver);
			hp = lp.performLogin(adminUsername, adminPassword);
			if(hp.error) {
				TCGFailureMsg = "Could NOT login to application";
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Category creation
			HashMap<String,String> categories = new HashMap<String, String>();
			categories.put(CATEGORYTYPES.CUSTOMERS, customerCategoryValue);
			categories.put(CATEGORYTYPES.EQUIPMENT, equipmentCategoryValue);
			categories.put(CATEGORYTYPES.ITEMS, itemCategoryValue);
			categories.put(CATEGORYTYPES.SUPPLIERS, supplierCategoryValue);
			ResourceDesignerPage rdp = hp.clickResourceDesignerMenu();
			if(!rdp.createCategory(categories)) {
				TCGFailureMsg = "Could NOT create Resource categories for - " + locationCategoryValue + " "
						+ customerCategoryValue + " " + equipmentCategoryValue + " " + itemCategoryValue
						+ " " + supplierCategoryValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Resource creation - Customer
			HashMap<String,Boolean> custInstance = new HashMap<String, Boolean>();
			custInstance.put(customerInstanceValue, true);

			ResourceDetailParams rd1 = new ResourceDetailParams();
			rd1.CategoryName = customerCategoryValue;
			rd1.CategoryType = RESOURCETYPES.CUSTOMERS;
			rd1.NumberFieldValue = 15;
			rd1.TextFieldValue = "LMN";
			rd1.InstanceNames = custInstance;
			rd1.LocationInstanceValue = locationInstanceValue;

			ManageResourcePage mrp = hp.clickResourcesMenu();
			if(!mrp.createResourceLinkLocation(rd1)) {
				TCGFailureMsg = "Could NOT create Customer Instance for category - " + customerCategoryValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Resource creation - Equipment
			HashMap<String,Boolean> equipInstance = new HashMap<String, Boolean>();
			equipInstance.put(equipmentInstanceValue, true);

			ResourceDetailParams rd2 = new ResourceDetailParams();
			rd2.CategoryName = equipmentCategoryValue;
			rd2.CategoryType = RESOURCETYPES.EQUIPMENT;
			rd2.NumberFieldValue = 15;
			rd2.TextFieldValue = "LMN";
			rd2.InstanceNames = equipInstance;
			rd2.LocationInstanceValue = locationInstanceValue;	

			mrp = hp.clickResourcesMenu();
			if(!mrp.createResourceLinkLocation(rd2)) {
				TCGFailureMsg = "Could NOT create Equipment Instance for category - " + equipmentCategoryValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Resource creation - Item
			HashMap<String,Boolean> itemInstance = new HashMap<String, Boolean>();
			itemInstance.put(itemInstanceValue, true);

			ResourceDetailParams rd3 = new ResourceDetailParams();
			rd3.CategoryName = itemCategoryValue;
			rd3.CategoryType = RESOURCETYPES.ITEMS;
			rd3.NumberFieldValue = 15;
			rd3.TextFieldValue = "LMN";
			rd3.InstanceNames = itemInstance;
			rd3.LocationInstanceValue = locationInstanceValue;	

			mrp = hp.clickResourcesMenu();
			if(!mrp.createResourceLinkLocation(rd3)) {
				TCGFailureMsg = "Could NOT create Item Instance for category - " + itemCategoryValue;
				logFatal(TCGFailureMsg);
				throw new SkipException(TCGFailureMsg);
			}

			//Resource creation - Supplier
			HashMap<String,Boolean> suppInstance = new HashMap<String, Boolean>();
			suppInstance.put(supplierInstanceValue, true);

			ResourceDetailParams rd4 = new ResourceDetailParams();
			rd4.CategoryName = supplierCategoryValue;
			rd4.CategoryType = RESOURCETYPES.SUPPLIERS;
			rd4.NumberFieldValue = 15;
			rd4.TextFieldValue = "LMN";
			rd4.InstanceNames = suppInstance;
			rd4.LocationInstanceValue = locationInstanceValue;

			mrp = hp.clickResourcesMenu();
			if(!mrp.createResourceLinkLocation(rd4)) {
				TCGFailureMsg = "Could NOT create Supplier Instance for category - " + supplierCategoryValue;
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

	@Test(description = "DPT || Verify single and multiple data extract for any \"Resource\" extract type.")
	public void TestCase_24267() {
		
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
			
			boolean setType1 = diep.selectExtractType(ExtractType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setType1, 
								  "Extract type SET to " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_CUSTOMERS);

			List<String> items = Arrays.asList(customerInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectCustResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectCustResource, 
								  "SELECTED resource " + customerInstanceValue, 
								  "Failed to Select resource " + customerInstanceValue);
			
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
			
			boolean setType2 = diep.selectExtractType(ExtractType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setType2, 
								  "Extract type SET to " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_CUSTOMERS);
			
			boolean selectAllCustResource = diep.selectAllOnDataExtractPopup();
			TestValidation.IsTrue(selectAllCustResource, 
								  "SELECTED All resources under Extract type " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Select all resources under Extract type " + ExtractType.RESOURCE_CUSTOMERS);
			
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
	
	@Test(description = "DPT || Verify the import functionality for any type of \"Resource\" having new data "
			+ "and updated data in one file and select \"Update only\".")
	public void TestCase_24276() {
		String newEquipmentInstanceValue = CommonMethods.dynamicText("NewEquip");
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_EQUIPMENTS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_EQUIPMENTS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_EQUIPMENTS);
			
			List<String> items = Arrays.asList(equipmentInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectEquipResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectEquipResource, 
								  "SELECTED resource " + equipmentInstanceValue, 
								  "Failed to Select resource " + equipmentInstanceValue);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.RESOURCE_EQUIPMENTS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.RESOURCE_EQUIPMENTS, 
								  "Failed to Set Import type to " + ImportType.RESOURCE_EQUIPMENTS);
			
			boolean setUpdateOnly = diep.selectUpdateOnlyOnImportPopup();
			TestValidation.IsTrue(setUpdateOnly, 
								  "SELECTED 'Update only' checkbox successfully", 
								  "Failed to Select 'Update only' checkbox");
			
			//Value set 1
			ResourceFieldValues rfv1 = new ResourceFieldValues();
			rfv1.resourceInstance = equipmentInstanceValue;
			rfv1.status = "False";
			
			//Value set 2
			ResourceFieldValues rfv2 = new ResourceFieldValues();
			rfv2.resourceInstance = newEquipmentInstanceValue;
			
			HashMap<String,List<ResourceFieldValues>> values = new HashMap<String, List<ResourceFieldValues>>();
			values.put(equipmentCategoryValue, Arrays.asList(rfv1,rfv2));
			
			ImportResourceFields irf =  new ImportResourceFields();
			irf.fileName = filePath;
			irf.ResourceType = RESOURCE_TYPES.EQUIPMENT;
			irf.setFieldsForResource = values;
			
			boolean updateExcel = diep.updateResourcesExcelWithFields(irf);
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
								  "VERIFIED Import of resource is done sucessfully", 
								  "Failed to Verify Import of resource is done or not");
			
			ManageResourcePage mrp = hp.clickResourcesMenu();
			boolean searchAndOpenInst1 = mrp.searchResourceAsPerType(RESOURCETYPES.EQUIPMENT, equipmentInstanceValue);
			TestValidation.IsTrue(searchAndOpenInst1, 
								  "OPENED Equipment resource instance " + equipmentInstanceValue, 
								  "Failed to Open Equipment resource instance " + equipmentInstanceValue);

			WebElement DisabledRsrcIcon = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.DISABLED_RSRC_ICON, 
					"INSTANCE_NAME", equipmentInstanceValue);
			TestValidation.IsTrue(DisabledRsrcIcon!=null, 
								  "VERIFIED the existing Equipment instance " + equipmentInstanceValue + " is diabled post import", 
								  "Failed to Verify the status of Equipment instance " + equipmentInstanceValue);
			
			boolean searchAndOpenInst2 = mrp.searchResourceAsPerType(RESOURCETYPES.EQUIPMENT, newEquipmentInstanceValue);
			TestValidation.IsFalse(searchAndOpenInst2, 
								  "New Equipment instance " + newEquipmentInstanceValue + " IS NOT imported", 
								  "Failed to Verify if new Equipment instance " + newEquipmentInstanceValue + " is or is not imported");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for any type of \"Resource\" having correct"
			+ " and incorrect data in one file and select \"Commit with Partial Success\".")
	public void TestCase_24280() {
		String newItemInstanceValue1 = CommonMethods.dynamicText("NewItem1");
		String newItemInstanceValue2 = CommonMethods.dynamicText("NewItem2");
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_ITEMS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_ITEMS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_ITEMS);
			
			List<String> items = Arrays.asList(itemInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectItemResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectItemResource, 
								  "SELECTED resource " + itemInstanceValue, 
								  "Failed to Select resource " + itemInstanceValue);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.RESOURCE_ITEMS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.RESOURCE_ITEMS, 
								  "Failed to Set Import type to " + ImportType.RESOURCE_ITEMS);
			
			boolean setPartialSuccess = diep.selectPartialSuccessOnImportPopup();
			TestValidation.IsTrue(setPartialSuccess, 
								  "SELECTED 'Commit with Partial Success' checkbox successfully", 
								  "Failed to Select 'Commit with Partial Success' checkbox");
			
			//Value set 1
			ResourceFieldValues rfv1 = new ResourceFieldValues();
			rfv1.resourceInstance = itemInstanceValue;
			
			//Value set 2
			ResourceFieldValues rfv2 = new ResourceFieldValues();
			rfv2.resourceInstance = newItemInstanceValue1;
			
			//Value set 3
			ResourceFieldValues rfv3 = new ResourceFieldValues();
			rfv3.resourceInstance = newItemInstanceValue2;
			
			HashMap<String,List<ResourceFieldValues>> values = new HashMap<String, List<ResourceFieldValues>>();
			values.put(itemCategoryValue, Arrays.asList(rfv1,rfv2));
			values.put(itemCategoryValue+"000", Arrays.asList(rfv3));
			
			ImportResourceFields irf =  new ImportResourceFields();
			irf.fileName = filePath;
			irf.ResourceType = RESOURCE_TYPES.ITEMS;
			irf.setFieldsForResource = values;
			
			boolean updateExcel = diep.updateResourcesExcelWithFields(irf);
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
			
			ManageResourcePage mrp = hp.clickResourcesMenu();
			boolean searchAndOpenInst1 = mrp.searchResourceAsPerType(RESOURCETYPES.ITEMS, itemInstanceValue);
			TestValidation.IsTrue(searchAndOpenInst1, 
								  "OPENED Item resource instance " + itemInstanceValue, 
								  "Failed to Open Item resource instance " + itemInstanceValue);

			boolean searchAndOpenInst2 = mrp.searchResourceAsPerType(RESOURCETYPES.ITEMS, newItemInstanceValue1);
			TestValidation.IsTrue(searchAndOpenInst2, 
								  "OPENED Item resource instance " + newItemInstanceValue1, 
								  "Failed to Open Item resource instance " + newItemInstanceValue1);
			
			boolean searchAndOpenInst3 = mrp.searchResourceAsPerType(RESOURCETYPES.ITEMS, newItemInstanceValue2);
			TestValidation.IsFalse(searchAndOpenInst3, 
								  "New Item instance " + newItemInstanceValue2 + " with incorrect Category IS NOT imported", 
								  "Failed to Verify if new Item instance " + newItemInstanceValue2 + " with incorrect Category is or is not imported");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify the import functionality for any type of \"Resource\" for correct "
			+ "& incorrect data in same file without selecting \"Commit with Partial Success\".")
	public void TestCase_24281() {
		String newSuppInstanceValue = CommonMethods.dynamicText("NewSupp");
		String updateExistingSuppInst = supplierInstanceValue + "1";
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_SUPPLIERS);
			
			List<String> items = Arrays.asList(supplierInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectItemResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectItemResource, 
								  "SELECTED resource " + supplierInstanceValue, 
								  "Failed to Select resource " + supplierInstanceValue);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Import type to " + ImportType.RESOURCE_SUPPLIERS);
			
			//Value set 1
			ResourceFieldValues rfv1 = new ResourceFieldValues();
			rfv1.resourceInstance = updateExistingSuppInst;
			
			//Value set 2
			ResourceFieldValues rfv2 = new ResourceFieldValues();
			rfv2.resourceInstance = newSuppInstanceValue;
			
			HashMap<String,List<ResourceFieldValues>> values = new HashMap<String, List<ResourceFieldValues>>();
			values.put(supplierCategoryValue, Arrays.asList(rfv1));
			values.put(supplierCategoryValue+"000", Arrays.asList(rfv2));
			
			ImportResourceFields irf =  new ImportResourceFields();
			irf.fileName = filePath;
			irf.ResourceType = RESOURCE_TYPES.SUPPLIERS;
			irf.setFieldsForResource = values;
			
			boolean updateExcel = diep.updateResourcesExcelWithFields(irf);
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
			
			String expectedErrMsg = "Invalid Category";
			String actualErrMsg = diep.getInspctErrsDetails(ColumnNames.ERROR_MESSAGE);
			boolean compareErrMsg = expectedErrMsg.equalsIgnoreCase(actualErrMsg);
			TestValidation.IsTrue(compareErrMsg, 
								  "VERIFIED the error message for Import failure as " + expectedErrMsg, 
								  "Failed to Verify the error message for Import failure as " + expectedErrMsg);
			
			boolean closePopup = diep.closeImportInspectErrsPopup();
			TestValidation.IsTrue(closePopup, 
								  "CLOSED Import Tab > Inspect Error popup", 
								  "Failed to Close Import Tab > Inspect Error popup");
			
			ManageResourcePage mrp = hp.clickResourcesMenu();
			boolean searchAndOpenInst1 = mrp.searchResourceAsPerType(RESOURCETYPES.SUPPLIERS, newSuppInstanceValue);
			TestValidation.IsFalse(searchAndOpenInst1, 
								  "New Supplier instance " + newSuppInstanceValue + " with incorrect Category IS NOT imported", 
								  "Failed to Verify if new Supplier instance " + newSuppInstanceValue + " with incorrect Category is or is not imported");
			
			boolean searchAndOpenInst2 = mrp.searchResourceAsPerType(RESOURCETYPES.SUPPLIERS, updateExistingSuppInst);
			TestValidation.IsFalse(searchAndOpenInst2, 
								  "Existing Supplier instance " + updateExistingSuppInst + " IS NOT updated by Import", 
								  "Failed to Verify if existing Supplier instance " + updateExistingSuppInst + " is or is not updated by Import");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify import functionality for any type of \"Resource\" with new data "
			+ "and update in old data without selecting \"Update Only\".")
	public void TestCase_24286() {
		String newCustomerInstanceValue = CommonMethods.dynamicText("NewCust");
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_CUSTOMERS);
			
			List<String> items = Arrays.asList(customerInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectEquipResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectEquipResource, 
								  "SELECTED resource " + customerInstanceValue, 
								  "Failed to Select resource " + customerInstanceValue);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Import type to " + ImportType.RESOURCE_CUSTOMERS);
			
			//Value set 1
			ResourceFieldValues rfv1 = new ResourceFieldValues();
			rfv1.resourceInstance = customerInstanceValue;
			rfv1.status = "False";
			
			//Value set 2
			ResourceFieldValues rfv2 = new ResourceFieldValues();
			rfv2.resourceInstance = newCustomerInstanceValue;
			
			HashMap<String,List<ResourceFieldValues>> values = new HashMap<String, List<ResourceFieldValues>>();
			values.put(customerCategoryValue, Arrays.asList(rfv1,rfv2));
			
			ImportResourceFields irf =  new ImportResourceFields();
			irf.fileName = filePath;
			irf.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			irf.setFieldsForResource = values;
			
			boolean updateExcel = diep.updateResourcesExcelWithFields(irf);
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
								  "VERIFIED Import of resource is done sucessfully", 
								  "Failed to Verify Import of resource is done or not");
			
			ManageResourcePage mrp = hp.clickResourcesMenu();
			boolean searchAndOpenInst1 = mrp.searchResourceAsPerType(RESOURCETYPES.CUSTOMERS, customerInstanceValue);
			TestValidation.IsTrue(searchAndOpenInst1, 
								  "OPENED Customer resource instance " + customerInstanceValue, 
								  "Failed to Open Customer resource instance " + customerInstanceValue);

			WebElement DisabledRsrcIcon = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.DISABLED_RSRC_ICON, 
					"INSTANCE_NAME", customerInstanceValue);
			TestValidation.IsTrue(DisabledRsrcIcon!=null, 
								  "VERIFIED the existing Customer instance " + customerInstanceValue + " is diabled post import", 
								  "Failed to Verify the status of Customer instance " + customerInstanceValue);
			
			boolean searchAndOpenInst2 = mrp.searchResourceAsPerType(RESOURCETYPES.CUSTOMERS, newCustomerInstanceValue);
			TestValidation.IsTrue(searchAndOpenInst2, 
								  "New Customer instance " + newCustomerInstanceValue + " IS imported", 
								  "Failed to Verify if new Customer instance " + newCustomerInstanceValue + " is or is not imported");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Verify download,inspect error and reprocess of the failed files for any type of \"Resource\".")
	public void TestCase_24287() {
		String newSuppInstanceValue = CommonMethods.dynamicText("NewSupp");
		String updateExistingSuppInst = supplierInstanceValue + "1";
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_SUPPLIERS);
			
			List<String> items = Arrays.asList(supplierInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectItemResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectItemResource, 
								  "SELECTED resource " + supplierInstanceValue, 
								  "Failed to Select resource " + supplierInstanceValue);
			
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
			
			boolean setImportType = diep.selectImportType(ImportType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setImportType, 
								  "Import type SET to " + ImportType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Import type to " + ImportType.RESOURCE_SUPPLIERS);
			
			//Value set 1
			ResourceFieldValues rfv1 = new ResourceFieldValues();
			rfv1.resourceInstance = updateExistingSuppInst;
			
			//Value set 2
			ResourceFieldValues rfv2 = new ResourceFieldValues();
			rfv2.resourceInstance = newSuppInstanceValue;
			
			HashMap<String,List<ResourceFieldValues>> values = new HashMap<String, List<ResourceFieldValues>>();
			values.put(supplierCategoryValue, Arrays.asList(rfv1));
			values.put(supplierCategoryValue+"000", Arrays.asList(rfv2));
			
			ImportResourceFields irf =  new ImportResourceFields();
			irf.fileName = filePath;
			irf.ResourceType = RESOURCE_TYPES.SUPPLIERS;
			irf.setFieldsForResource = values;
			
			boolean updateExcel = diep.updateResourcesExcelWithFields(irf);
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
			
			String expectedErrMsg = "Invalid Category";
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
	
	@Test(description = "DPT || Export - Verify download of Resource-Customers data.")
	public void TestCase_38423() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_CUSTOMERS);
			
			List<String> items = Arrays.asList(customerInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectEquipResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectEquipResource, 
								  "SELECTED resource " + customerInstanceValue, 
								  "Failed to Select resource " + customerInstanceValue);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			boolean downloadFile = diep.downloadDataFromLatestExport();
			TestValidation.IsTrue(downloadFile, 
								  "DOWNLOADED excel sheet from Export tab", 
								  "Failed to Download excel sheet from Export tab");
			
			ExportResourceFields exportResourceData =  new ExportResourceFields();
			exportResourceData.ResourceType = RESOURCE_TYPES.CUSTOMERS;
			exportResourceData.Category = customerCategoryValue;
			exportResourceData.Resources = Arrays.asList(customerInstanceValue);
			exportResourceData.isDownload = false;
			
			boolean verifyDataInExportedExcel = diep.verifyExportedResources(exportResourceData);
			TestValidation.IsTrue(verifyDataInExportedExcel, 
								  "VERIFIED Customer resource " + customerInstanceValue + " details from downloaded sheet", 
								  "Failed to Verify Customer resource " + customerInstanceValue + " details from downloaded sheet");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify download of Resource-Equipment data.")
	public void TestCase_38424() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_EQUIPMENTS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_EQUIPMENTS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_EQUIPMENTS);
			
			List<String> items = Arrays.asList(equipmentInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectEquipResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectEquipResource, 
								  "SELECTED resource " + equipmentInstanceValue, 
								  "Failed to Select resource " + equipmentInstanceValue);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			boolean downloadFile = diep.downloadDataFromLatestExport();
			TestValidation.IsTrue(downloadFile, 
								  "DOWNLOADED excel sheet from Export tab", 
								  "Failed to Download excel sheet from Export tab");
			
			ExportResourceFields exportResourceData =  new ExportResourceFields();
			exportResourceData.ResourceType = RESOURCE_TYPES.EQUIPMENT;
			exportResourceData.Category = equipmentCategoryValue;
			exportResourceData.Resources = Arrays.asList(equipmentInstanceValue);
			exportResourceData.isDownload = false;
			
			boolean verifyDataInExportedExcel = diep.verifyExportedResources(exportResourceData);
			TestValidation.IsTrue(verifyDataInExportedExcel, 
								  "VERIFIED Equipment resource " + equipmentInstanceValue + " details from downloaded sheet", 
								  "Failed to Verify Equipment resource " + equipmentInstanceValue + " details from downloaded sheet");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify download of Resource-Items data.")
	public void TestCase_38425() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_ITEMS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_ITEMS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_ITEMS);
			
			List<String> items = Arrays.asList(itemInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectEquipResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectEquipResource, 
								  "SELECTED resource " + itemInstanceValue, 
								  "Failed to Select resource " + itemInstanceValue);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			boolean downloadFile = diep.downloadDataFromLatestExport();
			TestValidation.IsTrue(downloadFile, 
								  "DOWNLOADED excel sheet from Export tab", 
								  "Failed to Download excel sheet from Export tab");
			
			ExportResourceFields exportResourceData =  new ExportResourceFields();
			exportResourceData.ResourceType = RESOURCE_TYPES.ITEMS;
			exportResourceData.Category = itemCategoryValue;
			exportResourceData.Resources = Arrays.asList(itemInstanceValue);
			exportResourceData.isDownload = false;
			
			boolean verifyDataInExportedExcel = diep.verifyExportedResources(exportResourceData);
			TestValidation.IsTrue(verifyDataInExportedExcel, 
								  "VERIFIED Item resource " + itemInstanceValue + " details from downloaded sheet", 
								  "Failed to Verify Item resource " + itemInstanceValue + " details from downloaded sheet");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify download of Resource-Suppliers data.")
	public void TestCase_38426() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_SUPPLIERS);
			
			List<String> items = Arrays.asList(supplierInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.RESOURCE);
			boolean selectEquipResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectEquipResource, 
								  "SELECTED resource " + supplierInstanceValue, 
								  "Failed to Select resource " + supplierInstanceValue);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			boolean downloadFile = diep.downloadDataFromLatestExport();
			TestValidation.IsTrue(downloadFile, 
								  "DOWNLOADED excel sheet from Export tab", 
								  "Failed to Download excel sheet from Export tab");
			
			ExportResourceFields exportResourceData =  new ExportResourceFields();
			exportResourceData.ResourceType = RESOURCE_TYPES.SUPPLIERS;
			exportResourceData.Category = supplierCategoryValue;
			exportResourceData.Resources = Arrays.asList(supplierInstanceValue);
			exportResourceData.isDownload = false;
			
			boolean verifyDataInExportedExcel = diep.verifyExportedResources(exportResourceData);
			TestValidation.IsTrue(verifyDataInExportedExcel, 
								  "VERIFIED Supplier resource " + supplierInstanceValue + " details from downloaded sheet", 
								  "Failed to Verify Supplier resource " + supplierInstanceValue + " details from downloaded sheet");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify download of Locations data.")
	public void TestCase_38427() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.LOCATIONS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.LOCATIONS, 
								  "Failed to Set Extract type to " + ExtractType.LOCATIONS);
			
			List<String> items = Arrays.asList(locationInstanceValue);
			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, 
					"COLUMN_NAME", ColumnNames.LOCATION_NAME);
			boolean selectEquipResource = diep.selectItemsInGrid(element,items);
			TestValidation.IsTrue(selectEquipResource, 
								  "SELECTED resource " + locationInstanceValue, 
								  "Failed to Select resource " + locationInstanceValue);
			
			boolean clickExtract = diep.clickDataExtract();
			TestValidation.IsTrue(clickExtract, 
								  "CLICKED on Data Extract button on popup", 
								  "Failed to Click on Data Extract button on popup");
			
			boolean verifyExtractSts = diep.verifyFileExportStatus();
			TestValidation.IsTrue(verifyExtractSts, 
								  "VERIFIED data extraction went successful", 
								  "Failed to Verify whether data extraction went successful or not");
			
			boolean downloadFile = diep.downloadDataFromLatestExport();
			TestValidation.IsTrue(downloadFile, 
								  "DOWNLOADED excel sheet from Export tab", 
								  "Failed to Download excel sheet from Export tab");
			
			ExportLocationFields exportLocationData =  new ExportLocationFields();
			exportLocationData.locationCategory = locationCategoryValue;
			exportLocationData.locations = Arrays.asList(locationInstanceValue);
			exportLocationData.isDownload = false;
			
			boolean verifyDataInExportedExcel = diep.verifyExportedLocation(exportLocationData);
			TestValidation.IsTrue(verifyDataInExportedExcel, 
								  "VERIFIED Location resource " + locationInstanceValue + " details from downloaded sheet", 
								  "Failed to Verify Location resource " + locationInstanceValue + " details from downloaded sheet");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Resource_Customer.")
	public void TestCase_38529() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_CUSTOMERS);
			
			boolean searchTxt = diep.searchExtractItem(customerInstanceValue);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + customerInstanceValue, 
								  "Failed to Search for text " + customerInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", customerInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + customerInstanceValue, 
								  "Failed to Verify whether element " + customerInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Resource_Items.")
	public void TestCase_38530() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_ITEMS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_ITEMS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_ITEMS);
			
			boolean searchTxt = diep.searchExtractItem(itemInstanceValue);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + itemInstanceValue, 
								  "Failed to Search for text " + itemInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", itemInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + itemInstanceValue, 
								  "Failed to Verify whether element " + itemInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Resource_suppliers.")
	public void TestCase_38531() {
		
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_SUPPLIERS);
			
			boolean searchTxt = diep.searchExtractItem(supplierInstanceValue);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + supplierInstanceValue, 
								  "Failed to Search for text " + supplierInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", supplierInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + supplierInstanceValue, 
								  "Failed to Verify whether element " + supplierInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Resource_Equipements.")
	public void TestCase_38532() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_EQUIPMENTS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_EQUIPMENTS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_EQUIPMENTS);
			
			boolean searchTxt = diep.searchExtractItem(equipmentInstanceValue);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + equipmentInstanceValue, 
								  "Failed to Search for text " + equipmentInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", equipmentInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + equipmentInstanceValue, 
								  "Failed to Verify whether element " + equipmentInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for locations.")
	public void TestCase_38533() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.LOCATIONS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.LOCATIONS, 
								  "Failed to Set Extract type to " + ExtractType.LOCATIONS);
			
			boolean searchTxt = diep.searchExtractItem(locationInstanceValue);
			TestValidation.IsTrue(searchTxt, 
								  "SEARCHED for text " + locationInstanceValue, 
								  "Failed to Search for text " + locationInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", locationInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + locationInstanceValue, 
								  "Failed to Verify whether element " + locationInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the Filter functionality for resource_customers.")
	public void TestCase_38546() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_CUSTOMERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_CUSTOMERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_CUSTOMERS);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.FILTER, customerInstanceValue);
			TestValidation.IsTrue(applyFilter, 
								  "For Resource column, APPLIED filter using text " + customerInstanceValue, 
								  "Failed to apply filter on Resource with text " + customerInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", customerInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + customerInstanceValue, 
								  "Failed to Verify whether element " + customerInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export - Verify functionality of Universal search for Resource_Items.")
	public void TestCase_38548() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_ITEMS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_ITEMS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_ITEMS);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.FILTER, itemInstanceValue);
			TestValidation.IsTrue(applyFilter, 
								  "For Resource column, APPLIED filter using text " + itemInstanceValue, 
								  "Failed to apply filter on Resource with text " + itemInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", itemInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + itemInstanceValue, 
								  "Failed to Verify whether element " + itemInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	@Test(description = "DPT || Export>Data Extract - Verify the Filter functionality for resource_suppliers.")
	public void TestCase_38549() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_SUPPLIERS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_SUPPLIERS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_SUPPLIERS);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.FILTER, supplierInstanceValue);
			TestValidation.IsTrue(applyFilter, 
								  "For Resource column, APPLIED filter using text " + supplierInstanceValue, 
								  "Failed to apply filter on Resource with text " + supplierInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", supplierInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + supplierInstanceValue, 
								  "Failed to Verify whether element " + supplierInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	
	@Test(description = "DPT || Export>Data Extract - Verify the Filter functionality for resource_equipments.")
	public void TestCase_38547() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.RESOURCE_EQUIPMENTS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.RESOURCE_EQUIPMENTS, 
								  "Failed to Set Extract type to " + ExtractType.RESOURCE_EQUIPMENTS);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.RESOURCE, COLUMN_SETTING.FILTER, equipmentInstanceValue);
			TestValidation.IsTrue(applyFilter, 
								  "For Resource column, APPLIED filter using text " + equipmentInstanceValue, 
								  "Failed to apply filter on Resource with text " + equipmentInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", equipmentInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + equipmentInstanceValue, 
								  "Failed to Verify whether element " + equipmentInstanceValue + " is present or not");
			
		}
		finally {
			if(!controlActions.refreshDisplayedPage()){
				TCGFailureMsg = "Could NOT refresh application's page";
				TestValidation.Fail(TCGFailureMsg);
			}
		}
	}
	
	
	@Test(description = "DPT || Export>Data Extract - Verify the Filter functionality for locations.")
	public void TestCase_38550() {
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
			
			boolean setType = diep.selectExtractType(ExtractType.LOCATIONS);
			TestValidation.IsTrue(setType, 
								  "Extract type SET to " + ExtractType.LOCATIONS, 
								  "Failed to Set Extract type to " + ExtractType.LOCATIONS);
			
			boolean applyFilter = diep.openAndApplySettingsForColumn(ColumnNames.LOCATION_NAME, COLUMN_SETTING.FILTER, locationInstanceValue);
			TestValidation.IsTrue(applyFilter, 
								  "For Resource column, APPLIED filter using text " + locationInstanceValue, 
								  "Failed to apply filter on Resource with text " + locationInstanceValue);
			
			int rowCount = diep.DataExtractPopupRowsTbl.size();
			TestValidation.IsTrue(rowCount==1, 
								  "VERIFIED that a Row in Data Extract popup table IS DISPLAYED", 
								  "Failed since " + rowCount + " rows were found in Data Extract popup table");
			
			WebElement ExtractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
												"ITEMNAME", locationInstanceValue);
			TestValidation.IsTrue(ExtractItemCheckbox!=null, 
								  "VERIFIED element is present for " + locationInstanceValue, 
								  "Failed to Verify whether element " + locationInstanceValue + " is present or not");
			
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

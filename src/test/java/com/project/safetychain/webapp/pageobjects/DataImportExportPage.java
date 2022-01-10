package com.project.safetychain.webapp.pageobjects;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.project.safetychain.webapp.pageobjects.FSQABrowserPage.COLUMNSETTING;
import com.project.utilities.CommonMethods;
import com.project.utilities.ControlActions;

public class DataImportExportPage extends CommonPage{
	WebDriver driver;
	WebDriverWait wait;
	ControlActions controlActions;


	public DataImportExportPage() {

	}

	public DataImportExportPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 40);
		controlActions = new ControlActions(driver);
	}

	@FindBy(id = DataImportExportPageLocators.SELECT_ALL_DATA_EXTRACT_POPUP)
	public WebElement SelectAllDataExtractPopup;

	@FindBy(xpath = DataImportExportPageLocators.EXPORT_TAB)
	public WebElement ExportTab;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_TAB)
	public WebElement Import_tab;

	@FindBy(xpath = DataImportExportPageLocators.EXTRACT_DATA_BTN)
	public WebElement ExtractDataBtn;

	@FindBy(xpath = DataImportExportPageLocators.EXTRACT_TYPE_DDL)
	public WebElement ExtractTypeDdl;

	@FindBy(xpath = DataImportExportPageLocators.DATA_EXTRACT_POP_UP_BTN)
	public WebElement DataExtractPopUpBtn;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_DATA_BTN)
	public WebElement NewImportBtn;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_TYPE_DDL)
	public WebElement ImporTypeDdl;

	@FindBy(xpath = DataImportExportPageLocators.DATA_IMPORT_UPLOAD_POP_UP_BTN)
	public WebElement DataImportUploadPopUpBtn;

	@FindBy(xpath = DataImportExportPageLocators.UPLOAD_FILE_INP)
	public WebElement ImportFileInp;

	@FindBy(xpath = DataImportExportPageLocators.UPLOADED_FILE_STS)
	public WebElement ImportFileStatus;

	@FindBy(xpath = DataImportExportPageLocators.EXTRACTED_FILE_STS)
	public WebElement ExportFileStatus;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_GRID_REFRESH_BTN)
	public WebElement ImportGridRefreshBtn;

	@FindBy(xpath = DataImportExportPageLocators.EXPORT_GRID_REFRESH_BTN)
	public WebElement ExportGridRefreshBtn;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_ERROR_MESSAGE_TXT)
	public WebElement ImportErrorMsgTxt;

	@FindBy(xpath = DataImportExportPageLocators.EXPORT_ERROR_MESSAGE_TXT)
	public WebElement ExportErrorMsgTxt;

	@FindBy(xpath = DataImportExportPageLocators.FIRST_EXTRACTED_FILE_TXT)
	public WebElement FirstExtractedFileTxt;

	@FindBy(xpath = DataImportExportPageLocators.RESOURCE_SETTINGS_CLM)
	public WebElement ResourceColumnSettings;

	@FindBy(xpath = DataImportExportPageLocators.FILTER_ICON)
	public WebElement FilterIcon;

	@FindBy(xpath = DataImportExportPageLocators.FILTER_INP)
	public WebElement FilterInp;

	@FindBy(xpath = DataImportExportPageLocators.FILTER_BTN)
	public WebElement FilterBtn;

	@FindBy(xpath = DataImportExportPageLocators.FILTER_ICON)
	public List<WebElement> FilterIcons;

	@FindBy(xpath = DataImportExportPageLocators.FILTER_INP)
	public List<WebElement> FilterInps;

	@FindBy(xpath = DataImportExportPageLocators.FILTER_BTN)
	public List<WebElement> FilterBtns;

	@FindBy(xpath = DataImportExportPageLocators.SEARCH_TEXTBOX)
	public WebElement SearchTextBox;

	@FindBy(xpath = DataImportExportPageLocators.SEARCH_LNK)
	public WebElement SearchLnk;

	@FindBy(xpath = DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX)
	public WebElement ExportItemCheckbox;


	@FindBy(xpath = DataImportExportPageLocators.EXPORT_TYPE_LNK)
	public WebElement ExportTypeLnk;

	@FindBy(xpath = DataImportExportPageLocators.FORM_NAME_SETTINGS_CLM)
	public WebElement FormNameColumnSettings;

	@FindBy(xpath = DataImportExportPageLocators.CALL_OUT_MENU)
	public WebElement CallOutMenu;
	@FindBy(xpath = DataImportExportPageLocators.REPROCESS_DDL)
	public WebElement ReProcessDdl;
	@FindBy(xpath = DataImportExportPageLocators.REPROCESS_YES_BTN)
	public WebElement ReProcessYesBtn;

	@FindBy(xpath = DataImportExportPageLocators.POPUP_CLOSE_BTN)
	public List<WebElement> PopupCloseBtn;

	@FindBy(xpath = DataImportExportPageLocators.POPUP_MODAL_TITLE)
	public WebElement PopupModalTitle;

	@FindBy(xpath = DataImportExportPageLocators.POPUP_MODAL_MSG)
	public WebElement PopupModalMsg;

	@FindBy(xpath = DataImportExportPageLocators.EXTRCT_DATA_COLUMN_NAMES_TBL)
	public List<WebElement> ExtrctDataColumnNamesTbl;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_POPUP_UPDATE_CHK)
	public WebElement ImportPopupUpdateChk;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_POPUP_PARTIAL_CHK)
	public WebElement ImportPopupPartialChk;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_INSPECT_ERRORS_DDL)
	public WebElement ImportInspectErrorsDdl;

	@FindBy(xpath = DataImportExportPageLocators.INSPECT_ERRS_COLUMN_NAMES_TBL)
	public List<WebElement> InspectErrsColumnNamesTbl;

	@FindBy(xpath = DataImportExportPageLocators.INSPECT_ERRS_POPUP_CANCEL_BTN)
	public WebElement InspectErrsPopupCancelBtn;

	@FindBy(xpath = DataImportExportPageLocators.IMPORT_EXPORT_DOWNLOAD_DDL)
	public WebElement ImportExportDownloadDdl;

	@FindBy(xpath = DataImportExportPageLocators.GRID_FILTER_TXT)
	public List<WebElement> GridFilterTxt;

	@FindBy(xpath = DataImportExportPageLocators.GRID_FILTER_BTN)
	public List<WebElement> GridFilterBtn;

	@FindBy(xpath = DataImportExportPageLocators.EXPORT_CALL_OUT_MENU)
	public WebElement ExportCallOutMenu;

	@FindBy(xpath = DataImportExportPageLocators.DATA_EXTRACT_POPUP_ROWS_TBL)
	public List<WebElement> DataExtractPopupRowsTbl;

	@FindBy(xpath = DataImportExportPageLocators.DATA_EXTRACT_PGN_PRVS_BTN)
	public WebElement DataExtractPgnPrvsBtn;

	@FindBy(xpath = DataImportExportPageLocators.DATA_EXTRACT_PGN_NXT_BTN)
	public WebElement DataExtractPgnNxtBtn;

	@FindBy(xpath = DataImportExportPageLocators.DATA_EXTRACT_PGN_FRST_BTN)
	public WebElement DataExtractPgnFrstBtn;

	@FindBy(xpath = DataImportExportPageLocators.DPT_IMPORT_COLUMN_NAMES_TBL)
	public List<WebElement> DptImportColumnNamesTbl;



	/** This method is used to select "Export" tab in "Data Provisioning"
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean selectExportTab() {
		try {
			controlActions.clickElement(ExportTab);
			controlActions.refreshPage();
			logInfo("Clicked on 'Export' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'Export' tab - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to open "Extract Data" pop up from "Export" tab in "Data Provisioning"
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean openExtractDataPopUp() {
		try {
			controlActions.clickElement(ExtractDataBtn);
			Sync();
			logInfo("Clicked on 'Extract Data' button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'Extract Data' button - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to select extract type in "Data extract" pop up from "Export" tab in "Data Provisioning"
	 * @author pashine_a
	 * @param extractType
	 * @return boolean status
	 */	
	public boolean selectExtractType(String extractType) {
		WebElement selectExtractType = null;
		try {
			controlActions.clickElement(ExtractTypeDdl);
			selectExtractType = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXTRACT_TYPE_DDV, "EXTRACT_TYPE", extractType);
			controlActions.clickElement(selectExtractType);
			Sync();
			logInfo("Selected '"+extractType+"' extract type");
			return true;
		}catch(Exception e) {
			logError("Failed to select - '"+extractType+"' extract type"+e.getMessage());
			return false;
		}
	}

	/** This method is used to click on "Data Extract" button in "Data extract" pop up from "Export" tab in "Data Provisioning"
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean clickDataExtract() {
		try {
			controlActions.clickElement(DataExtractPopUpBtn);
			Sync();
			logInfo("Clicked on  'Data Extract' button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'Data Extract' button - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to verify that data file extract status in "Export" tab in "Data Provisioning"
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean verifyFileExportStatus() {
		String fileStatus = "scs-status-tooltip scs-inprocess-status";
		try {
			fileStatus = ExportFileStatus.getAttribute("class");
			while(fileStatus!="scs-status-tooltip scs-success-status") {
				controlActions.clickElement(ExportGridRefreshBtn);
				Sync();
				fileStatus = ExportFileStatus.getAttribute("class");
				if(fileStatus.equals("scs-status-tooltip scs-success-status")) {
					break;
				}
				if(fileStatus.equals("scs-status-tooltip scs-failure-status")) {
					logInfo("Error Message - "+ExportErrorMsgTxt.getText());
					logError("Error while exporting a file");
					return false;			
				}
			}
			logInfo("Extracted data sucessfully & file is ready.");
			return true;
		}catch(Exception e) {
			logError("Failed to extract the data - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to select the resource in grid
	 * @author pashine_a
	 * @param resources
	 * @return boolean status
	 */	
	public boolean selectResources(List<String> resources) {
		WebElement selectResource = null;
		try {
			for(int i=0;i<resources.size();i++) {
				controlActions.clickElement(ResourceColumnSettings);
				controlActions.clickElement(FilterIcon);
				controlActions.sendKeys(FilterInp,resources.get(i));
				controlActions.clickElement(FilterBtn);
				Sync();
				selectResource = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.SELECT_RESOURCE_INP, "RESOURCE_NAME", resources.get(i));
				controlActions.click(selectResource);
			}
			logInfo("Selected resources(s)");
			return true;
		}catch(Exception e) {
			logError("Failed to select resource(s) - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to verify the details in extracted file for resource type
	 * @author pashine_a
	 * @param exportFields
	 * @return boolean status
	 */	
	@SuppressWarnings("resource")
	public boolean verifyExportedResources(ExportResourceFields exportFields) {
		InputStream fis;
		Workbook wb = null;
		String categoryValue = null, resourceValue = null;
		int lastCell = 0;
		Sheet sheet = null;
		String fileDetails = null;
		String currentRowData;
		int foundFlag = 0;
		try {
			fileDetails = downloadPath+"//"+FirstExtractedFileTxt.getText();

			if(exportFields.isDownload) {
				controlActions.doubleClick(FirstExtractedFileTxt);
				threadsleep(5000);
			}

			fis = new FileInputStream(fileDetails);
			wb = new XSSFWorkbook(fis);  

			categoryValue = exportFields.ResourceType+" > "+exportFields.Category;

			sheet= wb.getSheet("Categories");
			lastCell = sheet.getLastRowNum();

			for(int i=1;i<lastCell+1;i++) {
				currentRowData = sheet.getRow(i).getCell(0).getStringCellValue();
				if(currentRowData.equals(categoryValue)) {
					foundFlag = 1;
					break;
				}
			}
			if(foundFlag==0) {
				return false;
			}

			sheet= wb.getSheet("Resources");
			lastCell = sheet.getLastRowNum();
			foundFlag = 0;
			for(int i=5;i<lastCell+1;i++) {
				currentRowData = sheet.getRow(i).getCell(0).getStringCellValue();
				if(currentRowData.equals(categoryValue)) {
					foundFlag = 1;
				}else {
					foundFlag = 0;
					break;
				}
			}
			if(foundFlag==0) {
				return false;
			}

			for(int i=5;i<lastCell+1;i++) {
				foundFlag = 0;
				currentRowData = sheet.getRow(i).getCell(1).getStringCellValue();
				for(int j=0;j<exportFields.Resources.size();j++) {
					resourceValue = exportFields.Resources.get(j);
					if(currentRowData.equals(resourceValue)) {
						foundFlag = 1;
					}else {
						foundFlag = 0;
						break;
					}	
					if(foundFlag==0) {
						return false;
					}
				}
			}
			wb.close();
			fis.close();
			logInfo("Verified exported data from the application");
			return true;
		}catch (Exception e) {
			logError("Failed to verify exported data - ");
			e.printStackTrace();
			return false;
		} 
	}

	/** This method is used to export the data for the parameters provided
	 * @author pashine_a
	 * @param extractType
	 * @param columnName
	 * @param items
	 * @return boolean status
	 */	
	public boolean exportData(String extractType, String columnName, List<String> items) {
		try {

			if(!selectExportTab()) {
				return false;
			}
			if(!openExtractDataPopUp()) {
				return false;

			}
			if(!selectExtractType(extractType)) {
				return false;

			}

			WebElement element = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS, "COLUMN_NAME", columnName);
			if(!selectItemsInGrid(element,items)){
				return false;
			}

			if(!clickDataExtract()) {
				return false;

			}

			if(!verifyFileExportStatus()) {
				return false;
			}

			logInfo("Extracted data for extract type - "+extractType);
			return true;
		}catch(Exception e) {
			logError("Failed to extract data for extract type - "+extractType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to extract the resource type
	 * @author pashine_a
	 * @param extractType
	 * @param columnName
	 * @param exportFields
	 * @return boolean status
	 */	
	public boolean exportResource(String extractType, String columnName,ExportResourceFields exportFields) {
		try {
			if(!exportData(extractType,columnName, exportFields.Resources)){
				return false;
			}
			if(!verifyExportedResources(exportFields)) {
				return false;
			}
			logInfo("Extracted & Verified data for extract type - "+extractType);
			return true;
		}catch(Exception e) {
			logError("Failed to extract/Verify  data for extract type - "+extractType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to export the data for the parameters provided
	 * @author thakker_k
	 * @param extractType
	 * @param itemNames - Forms
	 * @return boolean status
	 */	
	public boolean exportDataFormCompliance(String extractType, List<String> itemNames) {
		try {

			if(!selectExportTab()) {
				return false;
			}
			if(!openExtractDataPopUp()) {
				return false;

			}
			if(!selectExtractType(extractType)) {
				return false;
			}

			if (!searchAndSelectExtractItems(itemNames)){
				return false;
			}

			if(!clickDataExtract()) {
				return false;

			}
			logInfo("Extracted data for extract type - "+extractType);
			return true;
		}catch(Exception e) {
			logError("Failed to extract data for extract type - "+extractType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to select "New Import" tab
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean selectImportTab() {
		try {
			controlActions.clickElement(Import_tab);
			controlActions.refreshPage();
			logInfo("Clicked to 'Import' Tab");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'Import' tab - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to open New Data Import pop up
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean openNewImportDataPopUp() {
		try {
			controlActions.clickElement(NewImportBtn);
			Sync();
			logInfo("Clicked on 'New Import' button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'New Import' button - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to select import type
	 * @author pashine_a
	 * @param importType
	 * @return boolean status
	 */	
	public boolean selectImportType(String importType) {
		WebElement selectImportType = null;
		try {
			controlActions.clickElement(ImporTypeDdl);
			selectImportType = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.IMPORT_TYPE_DDV, "IMPORT_TYPE",  importType);
			controlActions.clickElement(selectImportType);
			Sync();
			logInfo("Selected '"+importType+"' import type");
			return true;
		}catch(Exception e) {
			logError("Failed to select - '"+importType+"' import type"+e.getMessage());
			return false;
		}
	}

	/** This method is used to click upload button
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean clickUpload() {
		try {
			controlActions.clickElement(DataImportUploadPopUpBtn);
			Sync();
			logInfo("Clicked on  'Upload' button");
			return true;
		}catch(Exception e) {
			logError("Failed to click on 'Upload' button - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to upload a file
	 * @author pashine_a
	 * @param fileDetails
	 * @return boolean status
	 */	
	public boolean UploadFileImport(String fileDetails) {
		try {
			controlActions.sendKeys(ImportFileInp, fileDetails);
			logInfo("File is uploaded sucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to upload a file - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to verify that the file import is successful
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean verifyFileImportStatus() {
		String fileStatus = "scs-status-tooltip scs-inprocess-status";
		try {
			fileStatus = ImportFileStatus.getAttribute("class");
			while(fileStatus!="scs-status-tooltip scs-success-status") {
				controlActions.clickElement(ImportGridRefreshBtn);
				Sync();
				fileStatus = ImportFileStatus.getAttribute("class");
				if(fileStatus.equals("scs-status-tooltip scs-success-status")) {
					break;
				}
				if(fileStatus.equals("scs-status-tooltip scs-failure-status")) {
					logInfo("Error Message - "+ImportErrorMsgTxt.getText());
					logError("Error while importing a file, trying for reprocess");
					return reprocess();
				}
			}
			logInfo("File is imported sucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to import a file - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to update data in file for the resource
	 * @author pashine_a
	 * @param importFields
	 * @return boolean status
	 */	
	@SuppressWarnings("static-access")
	public boolean importResources(ImportResourceFields importFields) {
		String importFilePath = null;
		InputStream fis;
		Workbook wb = null;
		String categoryValue = null;
		int lastCell = 0;
		String fieldType = null;
		Sheet sheet = null;
		Row row = null;

		try {
			//			if(importFields.Resources.size()==1) {
			//				importFileName = "DataImport_SingleResource.xlsx";
			//			}else {
			//				if(importFields.Resources.size()>1) {
			//					importFileName = "DataImport_MultipleResource.xlsx";
			//				}else {
			//					logError("Import failed. Incomplete Data.");
			//					return false;
			//				}
			//			}

			importFilePath = importFields.fileName;

			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			categoryValue = importFields.ResourceType+" > "+importFields.NewCategory;

			sheet= wb.getSheet("Categories");
			row  = sheet.createRow(1);
			row.createCell(0).setCellValue(categoryValue);

			sheet= wb.getSheet("Resources");

			for(int i=0;i<importFields.NewResources.size();i++) {
				row  = sheet.createRow(5+i);
				row.createCell(0).setCellValue(importFields.ResourceType+" > "+importFields.NewCategory);
				row.createCell(1).setCellValue(importFields.NewResources.get(i));
				lastCell = sheet.getRow(0).getLastCellNum();

				for(int j=3;j<lastCell;j++) {
					fieldType = sheet.getRow(1).getCell(j).getStringCellValue();

					switch(fieldType) {

					case "Numeric":
						row.createCell(j).setCellValue(importFields.resourcedetailsfields.numeric);
						break;

					case "SingleSelect":
						row.createCell(j).setCellValue(importFields.resourcedetailsfields.selectOne);
						break;

					case "Textbox":
						row.createCell(j).setCellValue(importFields.resourcedetailsfields.singleLineText);
						break;

					case "ToggleButton":
						row.createCell(j).setCellValue("True");
						break;

					default:
						logError("Field type not found");
						return false;
					}
				}
			}

			logInfo("Resource(s) & their field values has has been set");

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 

			if(!UploadFileImport(importFilePath)) {
				return false;
			}
			logInfo("Import file has been created successfully");
			return true;

		}catch (Exception e) {
			logError("Failed to create import file - ");
			e.printStackTrace();
			return false;
		} 
	}

	/** This method is used to update data in file for the resource
	 * @author pashine_a
	 * @param importFields
	 * @return boolean status
	 */	
	public boolean importResourceData(String importType, ImportResourceFields importResourceData) {
		try {

			importResourceData.fileName = downloadPath+"//"+FirstExtractedFileTxt.getText();;

			controlActions.doubleClick(FirstExtractedFileTxt);
			threadsleep(5000);

			if(!selectImportTab()) {
				return false;
			}

			if(!openNewImportDataPopUp()) {
				return false;

			}
			if(!selectImportType(importType)) {
				return false;
			}

			if(!importResources(importResourceData)) {
				return false;
			}

			if(!clickUpload()) {
				return false;
			}

			if(!verifyFileImportStatus()) {
				return false;
			}

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Failed to import data for import type - "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import the data for the parameters provided for resource import
	 * @author pashine_a
	 * @param importType
	 * @param columnName
	 * @param importResourceData
	 * @return boolean status
	 */	
	public boolean importResource(String importType, String columnName, ImportResourceFields importResourceData) {
		try {
			if(!exportData(importType,columnName, importResourceData.Resources)){
				return false;
			}
			if(!importResourceData(importType, importResourceData)) {
				return false;
			}

			logInfo("Imported the exported data data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Failed to import the extracted data - "+importType+" - "+e.getMessage());
			return false;
		}
	}




	/** This method is used to search Extract Item 
	 * @author thakker_k
	 * @param itemName - Name of item
	 * @return boolean status
	 */	
	public boolean searchExtractItem(String itemName) {

		try {
			SearchTextBox.clear();
			SearchTextBox.sendKeys(itemName);
			SearchLnk.click();
			Sync();
			logInfo("Searched data - " + itemName + ", Successfully");
			return true;

		}
		catch(Exception e) {
			logError("Failed to Search in Export Data Section - " + itemName + " " +e.getMessage());
			return false;
		}
	}

	/** This method is used to select Extract Item 
	 * @author thakker_k
	 * @param itemName - Name of item
	 * @return boolean status
	 */	
	public boolean selectExtractItem(String itemName) {
		WebElement extractItemCheckbox = null;
		try {	

			extractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_ITEM_CHECKBOX, 
					"ITEMNAME", itemName);
			extractItemCheckbox.click();
			logInfo("Selected item - " + itemName + ", Successfully");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Select Item in Export Data Section - " + itemName + " " + e.getMessage());
			return false;
		}

	}

	/** This method is used to search and select Extract Item 
	 * @author thakker_k
	 * @param itemName - Name of item
	 * @return boolean status
	 */	
	public boolean searchAndSelectExtractItems(List<String> itemNames) {

		try {			
			for (String item : itemNames) {				
				searchExtractItem(item);
				selectExtractItem(item);				
			}			
			logInfo("Searched and Selected items Successfully");
			return true;			
		}
		catch(Exception e) {
			logError("Failed to Search and Select items in Export Data Section - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to download exported data 
	 * @author thakker_k
	 * @param extractType - Extract type
	 * @return boolean status
	 */	
	public boolean downloadExportedData(String extractType) {		
		try {
			if (verifyFileExportStatus())
			{
				WebElement extractItemCheckbox = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.EXPORT_TYPE_LNK, "EXPORTTYPE", extractType);			
				controlActions.doubleClick(extractItemCheckbox);
			}
			logInfo("Downloaded exported data Successfully - " + extractType);
			return true;
		}
		catch(Exception e) {
			logError("Failed to download exported data - " + e.getMessage());
			return false;
		}
	}


	/** This method is used to filter & select the entries(item) by the column in the grid
	 * @author pashine_a
	 * @param element
	 * @param values
	 * @return boolean status
	 */	
	public boolean selectItemsInGrid(WebElement element, List<String> values) {
		List<WebElement> selectEntry = null;
		String columnval = null;

		try {
			for(int i=0;i<values.size();i++) {
				controlActions.clickElement(element);
				controlActions.clickElement(FilterIcon);
				controlActions.sendKeys(FilterInp,values.get(i));
				controlActions.clickElement(FilterBtn);
				Sync();
				columnval = controlActions.perform_GetDynamicXPath(DataImportExportPageLocators.SELECT_VALUE_INP, "VALUE", values.get(i));
				selectEntry = controlActions.perform_GetListOfElementsByXPath(columnval);

				for(int j=0;j<selectEntry.size();j++) {
					controlActions.click(selectEntry.get(j));
				}
			}
			logInfo("Selected items(s)");
			return true;
		}catch(Exception e) {
			logError("Failed to select item(s) - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import Records
	 * @author choubey_a
	 * @param importType
	 * @param columnName
	 * @param importRecordData
	 * @return boolean status
	 */	
	public boolean importRecords(String importType, String columnName, ImportRecordFields importRecordData) {
		try {
			if(!exportData(importType,columnName, importRecordData.FormName)){
				return false;
			}

			importRecordData.RecordfileName = downloadPath+"//"+FirstExtractedFileTxt.getText();;

			controlActions.doubleClick(FirstExtractedFileTxt);
			threadsleep(5000);

			if(!selectImportTab()) {
				return false;
			}

			if(!openNewImportDataPopUp()) {
				return false;

			}
			if(!selectImportType(importType)) {
				return false;
			}

			if(!imporRecordData(importType, columnName, importRecordData)) {
				return false;
			}

			if(!UploadFileImport(importRecordData.RecordfileName)) {
				return false;
			}

			if(!clickUpload()) {
				return false;
			}

			if(!verifyFileImportStatus()) {
				return false;
			}

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Imported data to extract data for extract type - "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import the data for the parameters provided -  for Records
	 * @author choubey_a
	 * @param importType
	 * @param columnName
	 * @param importRecordData
	 * @return boolean status
	 */	
	public boolean imporRecordData(String importType, String columnName, ImportRecordFields importRecordData) {
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;

		try {

			fis = new FileInputStream(importRecordData.RecordfileName);
			wb = new XSSFWorkbook(fis);  

			sheet= wb.getSheet("1");
			row  = sheet.getRow(7);
			row.getCell(4).setCellValue(importRecordData.FieldValue.get(0));

			sheet= wb.getSheet("2");
			row  = sheet.getRow(7);
			row.getCell(4).setCellValue(importRecordData.FieldValue.get(1));


			sheet= wb.getSheet("3");
			row  = sheet.getRow(7);
			row.getCell(4).setCellValue(importRecordData.FieldValue.get(2));

			logInfo("Values are inserted for record creation");

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importRecordData.RecordfileName);
			wb.write(fileOut); 
			fileOut.close(); 

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Failed to import data of import type-"+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import the data for the parameters provided for internal user import
	 * @author pashine_a
	 * @param importType
	 * @param username
	 * @param location
	 * @return boolean status
	 */	
	public boolean importInternalUser(String importType, String username, String location) {
		try {
			if(!selectImportTab()) {
				return false;
			}

			if(!openNewImportDataPopUp()) {
				return false;

			}
			if(!selectImportType(importType)) {
				return false;
			}

			if(!importUserModification(username, location)) {
				return false;
			}

			if(!clickUpload()) {
				return false;
			}

			if(!verifyFileImportStatus()) {
				return false;
			}

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Failed to import the data for import type - "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to update data in file for the resource
	 * @author pashine_a
	 * @param username
	 * @param location
	 * @return boolean status
	 */	
	public boolean importUserModification(String username, String location) {
		String importFilePath = uploadDPTFilePath+"InternalUserImport.xlsx";
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;

		try {
			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			sheet= wb.getSheet("Users");
			row  = sheet.createRow(1);
			row.createCell(0).setCellValue(username);
			row.createCell(1).setCellValue(UserInfo.FirstName);
			row.createCell(2).setCellValue(UserInfo.LastName);
			row.createCell(3).setCellValue(UserInfo.Email);
			row.createCell(4).setCellValue(UserInfo.TimeZone);
			row.createCell(5).setCellValue("Y");


			sheet= wb.getSheet("User_Location");
			row  = sheet.createRow(1);
			row.createCell(0).setCellValue(username);
			row.createCell(1).setCellValue(location);

			sheet= wb.getSheet("User_Role");
			row  = sheet.createRow(1);
			row.createCell(0).setCellValue(username);
			row.createCell(1).setCellValue(UserInfo.Role);

			logInfo("User(s) & their field values has has been set");

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 

			if(!UploadFileImport(importFilePath)) {
				return false;
			}
			logInfo("Modified file & import ucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to modify & import file - "+e.getMessage());
			return false;
		}
	}



	/** This method is used to reprocess the file -- TO DO
	 * @author pashine_a
	 * @param null
	 * @return boolean status
	 */	
	public boolean reprocess() {
		int uploadSuccessFlag = 0;
		String fileStatus = "scs-status-tooltip scs-inprocess-status";
		try {
			for(int i=0;i<4;i++) {
				controlActions.hoverElement(ImportFileStatus);
				controlActions.clickElement(CallOutMenu);
				controlActions.clickElement(ReProcessDdl);
				controlActions.clickElement(ReProcessYesBtn);
				Sync();
				controlActions.clickElement(ImportGridRefreshBtn);
				Sync();
				fileStatus = ImportFileStatus.getAttribute("class");
				if(fileStatus.equals("scs-status-tooltip scs-success-status")) {
					uploadSuccessFlag = 1;
					break;
				}
				logInfo("Error Message - "+ImportErrorMsgTxt.getText());
				logError("Error while importing a file, trying for reprocess");
			}
			if(uploadSuccessFlag==1) {
				logInfo("File is imported on reprocess sucessfully");
				return true;
			}else {
				logError("File is not imported on reprocess");
				return false;
			}
		}catch(Exception e) {
			logError("Failed to reprocess a file - "+e.getMessage());
			return false;
		}
	}


	/** This method is used to extract & verify the location
	 * @author pashine_a
	 * @param extractType
	 * @param columnName
	 * @param exportFields
	 * @return boolean status
	 */	
	public boolean exportLocations(String extractType, String columnName,ExportLocationFields exportFields) {
		try {
			if(!exportData(extractType,columnName, exportFields.locations)){
				return false;
			}
			if(!verifyExportedLocation(exportFields)) {
				return false;
			}

			logInfo("Extracted & Verified data for extract type - "+extractType);
			return true;
		}catch(Exception e) {
			logError("Failed to extract/Verify  data for extract type - "+extractType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to verify the details in extracted file for location type
	 * @author pashine_a
	 * @param exportFields
	 * @return boolean status
	 */	
	@SuppressWarnings("resource")
	public boolean verifyExportedLocation(ExportLocationFields exportFields) {
		InputStream fis;
		Workbook wb = null;
		String hierarchyValue = null;
		int lastCell = 0;
		Sheet sheet = null;
		String fileDetails = null;
		String currentRowData;
		int foundFlag = 0;
		try {
			fileDetails = downloadPath+"//"+FirstExtractedFileTxt.getText();

			if(exportFields.isDownload) {
				controlActions.doubleClick(FirstExtractedFileTxt);
				threadsleep(5000);
			}

			fis = new FileInputStream(fileDetails);
			wb = new XSSFWorkbook(fis);  

			hierarchyValue = "Locations > "+exportFields.locationCategory;

			sheet= wb.getSheet("Locations");
			lastCell = sheet.getLastRowNum();

			for(int i=1;i<lastCell+1;i++) {
				currentRowData = sheet.getRow(i).getCell(0).getStringCellValue();
				if(!currentRowData.equals(hierarchyValue)) {
					return false;
				}
				logInfo("Verified Location category");
			}

			for(int i=1;i<lastCell+1;i++) {
				currentRowData = sheet.getRow(i).getCell(1).getStringCellValue();
				foundFlag = 0;
				for(int j=0;j<exportFields.locations.size();j++) {
					if(currentRowData.equals(exportFields.locations.get(j))) {
						foundFlag = 1;
						logInfo("Verified Location instance - "+i);
						break;
					}
				}
				if(foundFlag==0) {
					return false;
				}
			}
			wb.close();
			fis.close();
			logInfo("Verified exported data from the application");
			return true;
		}catch (Exception e) {
			logError("Failed to verify exported data");
			e.printStackTrace();
			return false;
		} 
	}


	/** This method is used to import the data for the parameters provided for user import
	 * @author pashine_a
	 * @param importType
	 * @param username
	 * @param location
	 * @return boolean status
	 */	
	public boolean importLocations(String importType, String columnName, ImportLocationFields locationData) {
		String importFilePath = null;
		try {

			if(!exportData(importType,columnName, locationData.locations)){
				return false;
			}

			importFilePath = downloadPath+"//"+FirstExtractedFileTxt.getText();
			controlActions.doubleClick(FirstExtractedFileTxt);
			threadsleep(5000);

			if(!selectImportTab()) {
				return false;
			}

			if(!openNewImportDataPopUp()) {
				return false;

			}
			if(!selectImportType(importType)) {
				return false;
			}

			if(!updateimportLocation(locationData.locationCategory, locationData.newLocations, importFilePath)) {
				return false;
			}

			if(!clickUpload()) {
				return false;
			}

			if(!verifyFileImportStatus()) {
				return false;
			}

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Failed to import the data for import type - "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to update data in file for the location
	 * @author pashine_a
	 * @param username
	 * @param location
	 * @return boolean status
	 */	
	public boolean updateimportLocation(String locationCategoryValue, List<String> locationValue, String importFilePath) {
		String locationCategoryName = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;

		try {
			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  
			locationCategoryName = "Locations > "+locationCategoryValue;
			sheet= wb.getSheet("Locations");

			for(int i=0;i<locationValue.size();i++) {
				row  = sheet.createRow(i+1);
				row.createCell(0).setCellValue(locationCategoryName);
				row.createCell(1).setCellValue(locationValue.get(i));
				row.createCell(2).setCellValue("Yes");
			}

			logInfo("Locations(s) & their field values has has been set");

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 

			if(!UploadFileImport(importFilePath)) {
				return false;
			}

			logInfo("Modified file & import sucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to modify & import file - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import the data for the parameters provided for user import
	 * @author pashine_a
	 * @param importType
	 * @param username
	 * @param supplierInstance
	 * @return boolean status
	 */	
	public boolean importSupplierUser(String importType, String username, String supplierInstance) {
		try {
			if(!selectImportTab()) {
				return false;
			}

			if(!openNewImportDataPopUp()) {
				return false;

			}
			if(!selectImportType(importType)) {
				return false;
			}

			if(!importSupplierUserModification(username, supplierInstance)) {
				return false;
			}

			if(!clickUpload()) {
				return false;
			}
			if(!verifyFileImportStatus()) {
				return false;
			}

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Failed to import the data for import type - "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to update data in file for the resource
	 * @author pashine_a
	 * @param username
	 * @param supplierInstance
	 * @return boolean status
	 */	
	public boolean importSupplierUserModification(String username, String supplierInstance) {
		String importFilePath = uploadDPTFilePath+"SupplierUserImport.xlsx";
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;

		try {
			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			sheet= wb.getSheet("Users");
			row  = sheet.createRow(1);
			row.createCell(0).setCellValue(username);
			row.createCell(1).setCellValue(UserInfo.FirstName);
			row.createCell(2).setCellValue(UserInfo.LastName);
			row.createCell(3).setCellValue(UserInfo.Email);
			row.createCell(4).setCellValue(UserInfo.Title);
			row.createCell(5).setCellValue(UserInfo.TimeZone);
			row.createCell(6).setCellValue("Y");		
			row.createCell(7).setCellValue("Y");

			sheet= wb.getSheet("User_Supplier");
			row  = sheet.createRow(1);
			row.createCell(0).setCellValue(username);
			row.createCell(1).setCellValue(supplierInstance);

			logInfo("User(s) & their field values has has been set");

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 

			if(!UploadFileImport(importFilePath)) {
				return false;
			}
			logInfo("Modified file & import ucessfully");
			return true;
		}catch(Exception e) {
			logError("Failed to modify & import file - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import the data for the parameters provided -  for from definition
	 * @author choubey_a
	 * @param importType
	 * @param columnName
	 * @param importFormDefinationData
	 * @return boolean status
	 */	
	public boolean imporFormDefinationData(String importType, String columnName, ImportFormDefinitionFields importFormDefinationData) {
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;

		try {

			fis = new FileInputStream(importFormDefinationData.FormDefinationfileName);
			wb = new XSSFWorkbook(fis);  

			sheet= wb.getSheet(importFormDefinationData.FormName.get(0));
			row  = sheet.getRow(0);
			row.getCell(1).setCellValue(importFormDefinationData.newFormName);

			logInfo("Values are inserted for form defination creation");

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFormDefinationData.FormDefinationfileName);
			wb.write(fileOut); 
			fileOut.close(); 

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Failed to import data of import type- "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import Form Definition file
	 * @author choubey_a
	 * @param importType
	 * @param columnName
	 * @param importFormDefinationData
	 * @return boolean status
	 */	
	public boolean importFormDefination(String importType, String columnName, ImportFormDefinitionFields importFormDefinationData) {
		try {
			if(!exportData(importType,columnName, importFormDefinationData.FormName)){
				return false;
			}

			importFormDefinationData.FormDefinationfileName = downloadPath+"//"+FirstExtractedFileTxt.getText();;

			controlActions.doubleClick(FirstExtractedFileTxt);
			threadsleep(5000);

			if(!selectImportTab()) {
				return false;
			}

			if(!openNewImportDataPopUp()) {
				return false;

			}
			if(!selectImportType(importType)) {
				return false;
			}

			if(!imporFormDefinationData(importType,columnName,importFormDefinationData)) {
				return false;
			}

			if(!UploadFileImport(importFormDefinationData.FormDefinationfileName)) {
				return false;
			}

			if(!clickUpload()) {
				return false;
			}

			if(!verifyFileImportStatus()) {
				return false;
			}

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Imported data to extract data for extract type - "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import the data for the parameters provided -  for from compliance
	 * @author choubey_a
	 * @param importType
	 * @param columnName
	 * @param importFormComplianceData
	 * @return boolean status
	 */	
	public boolean importFormComplianceData(String importType, String columnName, ImportFormCompliance importFormComplainceData) {
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;

		try {

			fis = new FileInputStream(importFormComplainceData.FormComplaincefileName);
			wb = new XSSFWorkbook(fis);  

			sheet= wb.getSheet(importFormComplainceData.FormName.get(0));
			row  = sheet.getRow(11);
			row.getCell(3).setCellValue("FALSE");
			row.getCell(4).setCellValue("100");
			row.getCell(5).setCellValue("150");
			row.getCell(6).setCellValue("200");
			row.getCell(7).setCellValue("KG");
			row  = sheet.getRow(12);
			row.getCell(2).setCellValue("TRUE");
			row.getCell(4).setCellValue("100");
			row.getCell(5).setCellValue("150");
			row.getCell(6).setCellValue("200");
			row.getCell(7).setCellValue("KG");
			logInfo("Values are inserted for form compliance creation");
			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFormComplainceData.FormComplaincefileName);
			wb.write(fileOut); 
			fileOut.close(); 
			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Failed to import data of import type- "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to import Form Compliance file
	 * @author choubey_a
	 * @param importType
	 * @param columnName
	 * @param importFormComplianceData
	 * @return boolean status
	 */	
	public boolean importFormCompliance(String importType, String columnName, ImportFormCompliance importFormComplainceData) {
		try {
			if(!exportData(importType,columnName, importFormComplainceData.FormName)){
				return false;
			}

			importFormComplainceData.FormComplaincefileName = downloadPath+"//"+FirstExtractedFileTxt.getText();;

			controlActions.doubleClick(FirstExtractedFileTxt);
			threadsleep(5000);

			if(!selectImportTab()) {
				return false;
			}

			if(!openNewImportDataPopUp()) {
				return false;

			}
			if(!selectImportType(importType)) {
				return false;
			}

			if(!importFormComplianceData(importType,columnName,importFormComplainceData)) {
				return false;
			}

			if(!UploadFileImport(importFormComplainceData.FormComplaincefileName)) {
				return false;
			}

			if(!clickUpload()) {
				return false;
			}

			if(!verifyFileImportStatus()) {
				return false;
			}

			logInfo("Imported data for import type - "+importType);
			return true;
		}catch(Exception e) {
			logError("Imported data to extract data for extract type - "+importType+" - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Close button of Modal popup
	 * @author hingorani_a
	 * @return boolean This returns true if Close button is clicked successfully
	 */	
	public boolean closeModalPopup() {
		try {
			for(WebElement element : PopupCloseBtn) {
				if(controlActions.isElementDisplay(element)) {
					controlActions.WaitforelementToBeClickable(element);
					element.click();
				}
			}
			logInfo("Clicked on Close popup button");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Close popup button - "
					+ e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to click on Settings dropdown link for a column
	 * @author hingorani_a
	 * @param columnName Use Class ColumnNames to set value for column name
	 * @return boolean This returns true once we click on Settings dropdown
	 */
	public boolean clickColumnDropDown(String columnName) {
		WebElement ColumnSettingsLnk = null;
		try {
			ColumnSettingsLnk = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.COLUMN_SETTINGS_LNK, 
					"COLUMNNAME", columnName);
			controlActions.clickOnElement(ColumnSettingsLnk);
			Sync();
			logInfo("Clicked on column : " + columnName + " settings link");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on column : " + columnName + " settings link " + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to apply settings like Sorting on column
	 * @author hingorani_a
	 * @param settingName Use Class ColumnNames to set value for column name
	 * @param settingValue If you want to check/uncheck any columns, provide the text
	 * to be used with separator '|' else for other settings keep it as null.
	 * Example: "Name-Check|Status-Uncheck"
	 * @return boolean This returns true after applying setting
	 */
	public boolean applySettingsForColumn(String settingName, String settingValue) {
		List<WebElement> PopupOptionMnu = null;
		try {
			PopupOptionMnu = controlActions.perform_GetListOfElementsByXPath(DataImportExportPageLocators.POPUP_OPTION_MNU, 
					"POPUPOPTION", settingName);

			switch(settingName) {
			case COLUMN_SETTING.SORTASCENDING:
			case COLUMN_SETTING.SORTDESCENDING:
				for(WebElement option : PopupOptionMnu) {
					if(controlActions.isElementDisplay(option)) {
						controlActions.WaitforelementToBeClickable(option);
						option.click();
						Sync();
						logInfo("Applied " + settingName + " to column");
						return true;
					}
				}

			case COLUMNSETTING.FILTER:
				for(WebElement option : PopupOptionMnu) {
					if(controlActions.isElementDisplay(option)) {
						controlActions.WaitforelementToBeClickable(option);
						controlActions.clickOnElement(option);
					}
				}
				for(WebElement filterTxt : GridFilterTxt) {
					if(controlActions.isElementDisplay(filterTxt)) {
						controlActions.WaitforelementToBeClickable(filterTxt);
						filterTxt.clear();
						filterTxt.sendKeys(settingValue);
					}
				}
				for(WebElement filterBtn : GridFilterBtn) {
					if(controlActions.isElementDisplay(filterBtn)) {
						controlActions.WaitforelementToBeClickable(filterBtn);
						filterBtn.click();
					}
				}
				logInfo("Applied " + settingName + " on column with " + settingValue);
				return true;

			default:
				logInfo("Incorrect setting provided : " + settingValue);
				return false;
			}
		}
		catch(Exception e) {
			logError("Failed to apply : " + settingName + " on column " + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to open settings popup and then apply settings
	 *  like Sorting, Filtering on column
	 * @author hingorani_a
	 * @param columnName Use Class ColumnNames to set value for column name
	 * @param settingName Use Class COLUMN_SETTING to set value for setting
	 * @param settingValue If you want to check/uncheck any columns, provide the text
	 * to be used with separator '|' else for other settings keep it as null.
	 * Example: "Name-Check|Status-Uncheck"
	 * @return boolean This returns true after applying setting
	 */
	public boolean openAndApplySettingsForColumn(String columnName, String settingName, String settingValue) {
		try {
			if(!clickColumnDropDown(columnName)) {
				return false;
			}

			if(!applySettingsForColumn(settingName, settingValue)) {
				return false;
			}

			Sync();
			logInfo("Successfully applied setting " + settingName + " to column " + columnName);
			return true;
		}
		catch(Exception e) {
			logError("Failed to apply setting " + settingName + " to column " + columnName + " - "
					+ e.getMessage());
			return false;
		}	
	}

	/**
	 * This method is used to find Column Index for the column present in DPT Grid
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class SUPP_PRTL_FIELDS to pass column name.
	 * This Index is in turn used in creating dynamic xpaths.
	 * @param ColumnNamesTbl The List of WebElements to be passed as Column headers for a Grid 
	 * @return int This returns value of column index. 
	 * Returns 0, if column is not found
	 */
	public int getColumnIndex(String columnName, List<WebElement> ColumnNamesTbl) {
		int columnIndex = 0; 
		try {
			for(WebElement column : ColumnNamesTbl) {
				controlActions.action.moveToElement(column).build().perform();
				if(controlActions.perform_CheckIfElementTextContains(column, columnName)) 
				{
					columnIndex++;
					break;
				}
				else if (controlActions.perform_CheckIfElementTextEquals(column, "")) {
					//Do nothing
				}
				else {
					columnIndex++;
				}
			}		
			logInfo("For column : " + columnName + " the index is : " + columnIndex);
			return columnIndex;
		}
		catch(Exception e) {
			logError("Failed to get index value for column : " + columnName + " - "
					+ e.getMessage());
			return columnIndex;
		}	
	}


	/**
	 * This method is used to get list of Elements for a column of DPT Extract Data grid
	 * @author hingorani_a
	 * @param columnName Use Class ColumnNames to set value for column name
	 * @return List<WebElement> This returns list of WebElements for given column
	 */
	public List<WebElement> getListOfElementsForDPTExtractData(String columnName) {
		List<WebElement> ColumnValue = null;
		try {
			int columnIndex = getColumnIndex(columnName, ExtrctDataColumnNamesTbl);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return ColumnValue;
			}
			else {
				ColumnValue = controlActions.perform_GetListOfElementsByXPath(
						DataImportExportPageLocators.EXTRACT_DATA_POPUP_COLUMN_VALUE, "COLUMNINDEXNO", 
						Integer.toString(columnIndex + 1));
				logInfo("Retrieved list of elements for column : " + columnName);
				return ColumnValue;
			}
		}
		catch(Exception e) {
			logError("Failed to get list of elements for column : " + columnName + " - "
					+ e.getMessage());
			return ColumnValue;
		}	
	}

	/** This method is used to Select All the records displayed for any type on Data Extract popup 
	 * @author hingorani_a
	 * @return boolean This returns true once Select All checkbox is selected
	 */	
	public boolean selectAllOnDataExtractPopup() {
		try {
			controlActions.WaitforelementToBeClickable(SelectAllDataExtractPopup);
			if(SelectAllDataExtractPopup.isSelected())
				logInfo("Select All checkbox is already selected");
			else
				SelectAllDataExtractPopup.click();

			logInfo("Select All checkbox is clicked and selected");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on 'Select All' checkbox - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to Update the existing excel workbook and it's sheets
	 * @author hingorani_a
	 * @param irf Use Class ImportResourceFields to set information for filepath, Category type 
	 * and instance type, status, numeric data type value, etc
	 * @return boolean This returns true once workbook is updated
	 */	
	@SuppressWarnings("static-access")
	public boolean updateResourcesExcelWithFields(ImportResourceFields irf) {
		String importFilePath = null;
		InputStream fis;
		Workbook wb = null;
		String categoryValue = null;
		int lastCell = 0;
		String fieldType = null;
		Sheet sheet = null;
		Row row = null;

		try {
			importFilePath = irf.fileName;

			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			if(irf.oldCategory!=null)
				logInfo("The category and resource already exists in the Categories sheet");

			if(irf.NewCategory!=null) {
				categoryValue = irf.ResourceType+" > "+irf.NewCategory;
				sheet= wb.getSheet("Categories");
				row  = sheet.createRow(1);
				row.createCell(0).setCellValue(categoryValue);
			}

			sheet= wb.getSheet("Resources");

			if(irf.setFieldsForResource != null)
			{
				int count = 0;

				for(Map.Entry<String,List<ResourceFieldValues>> entry : irf.setFieldsForResource.entrySet()) {
					String resourceCategory = entry.getKey();
					List<ResourceFieldValues> listOfValues = entry.getValue();

					for(ResourceFieldValues rfv : listOfValues) {
						row  = sheet.createRow(5+count);
						row.createCell(0).setCellValue(irf.ResourceType+" > "+resourceCategory);
						row.createCell(1).setCellValue(rfv.resourceInstance);

						// Fill other details for resource
						lastCell = sheet.getRow(0).getLastCellNum();
						for(int j=3;j<lastCell;j++) {
							fieldType = sheet.getRow(1).getCell(j).getStringCellValue();

							switch(fieldType) {

							case "Numeric":
								row.createCell(j).setCellValue(rfv.numeric);
								break;

							case "SingleSelect":
								String options = sheet.getRow(2).getCell(j).getStringCellValue();
								String listOfOptions[] = CommonMethods.splitAndGetString(options,",");
								row.createCell(j).setCellValue(listOfOptions[0]);
								break;

							case "Textbox":
								row.createCell(j).setCellValue(rfv.singleLineText);
								break;

							case "ToggleButton":
								row.createCell(j).setCellValue(rfv.status);
								break;

							default:
								logError("Field type not found");
								return false;
							}
						}
						count++;
					}
				}
			}

			logInfo("Resource(s) & their field values has has been set");

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 

			logInfo("Import file has been created successfully");
			return true;

		}
		catch (Exception e) {
			logError("Failed to create import file - ");
			e.printStackTrace();
			return false;
		} 
	}

	/** This method is used to Download the most recent/top most extracted excel from DPT's Export tab 
	 * @author hingorani_a
	 * @return String This returns value of the filepath that is downloaded
	 */	
	public String downloadExportFileAndGetFilePath() {
		String filePath = null;
		try {
			filePath = getLatestExportFilePath();
			if(filePath==null)
				return null;

			controlActions.doubleClick(FirstExtractedFileTxt);
			threadsleep(5000);

			logInfo("Downloaded the extracted file " +  filePath);
		}
		catch(Exception e) {
			logError("Failed to click on 'Select All' checkbox - "+e.getMessage());
		}
		return filePath;
	}

	/** This method is used to select 'Update Only' checkbox on Import tab popup
	 * @author hingorani_a
	 * @return boolean This returns true once 'Update Only' checkbox is selected
	 */	
	public boolean selectUpdateOnlyOnImportPopup() {
		try {
			controlActions.WaitforelementToBeClickable(ImportPopupUpdateChk);
			if(ImportPopupUpdateChk.isSelected())
				logInfo("'Update Only' checkbox is already selected");
			else
				ImportPopupUpdateChk.click();

			logInfo("'Update Only' checkbox is clicked and selected");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on ''Update Only' checkbox - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to select 'Commit with Partial Success' checkbox on Import tab popup
	 * @author hingorani_a
	 * @return boolean This returns true once 'Commit with Partial Success' checkbox is selected
	 */	
	public boolean selectPartialSuccessOnImportPopup() {
		try {
			controlActions.WaitforelementToBeClickable(ImportPopupPartialChk);
			if(ImportPopupPartialChk.isSelected())
				logInfo("'Commit with Partial Success' checkbox is already selected");
			else
				ImportPopupPartialChk.click();

			logInfo("'Commit with Partial Success' checkbox is clicked and selected");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on 'Commit with Partial Success' checkbox - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to verify that the status of file import
	 * @author hingorani_a
	 * @param status The expected status that is the actual status to be compared against
	 * @return boolean This returns true if Status passed as parameter to this function
	 *  and actual status matches else false
	 */	
	public boolean verifyFileImportStatus(String status) {
		String fileStatus = "scs-status-tooltip scs-inprocess-status";
		String desiredStatusClass = null;
		try {
			switch(status) {
			case IMPORT_STATUS.SUCCESS:
				desiredStatusClass = "scs-status-tooltip scs-success-status";
				break;

			case IMPORT_STATUS.PARTIAL_SUCCESS:
				desiredStatusClass = "scs-status-tooltip scs-partial-success-status";
				break;

			case IMPORT_STATUS.FAILURE:
				desiredStatusClass = "scs-status-tooltip scs-failure-status";
				break;
			}
			fileStatus = ImportFileStatus.getAttribute("class");
			while(fileStatus!=desiredStatusClass) {
				controlActions.clickElement(ImportGridRefreshBtn);
				Sync();
				fileStatus = ImportFileStatus.getAttribute("class");
				if(fileStatus.equals(desiredStatusClass)) {
					break;
				}
				if(fileStatus.equals("scs-status-tooltip scs-failure-status")) {
					logInfo("Error Message - "+ImportErrorMsgTxt.getText());
					logError("Error while importing a file, trying for reprocess");
					return reprocess();
				}
			}
			logInfo("Verified File import status as " + status);
			return true;
		}
		catch(Exception e) {
			logError("Failed to verify File import status as - " + status
					+e.getMessage());
			return false;
		}
	}

	/** This method is used to open the Import tab > Inspect Errors 
	 * @author hingorani_a
	 * @return boolean This returns true after opening Inspect Errors popup
	 */	
	public boolean openInspectErrorsPopupForLatestImport() {
		try {
			controlActions.hoverElement(ImportFileStatus);
			controlActions.clickElement(CallOutMenu);
			controlActions.clickElement(ImportInspectErrorsDdl);
			Sync();
			logInfo("Opened Inspect Errors for latest Import");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Open Inspect Errors for latest Import - "+e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search and get Inspect Errors detail as per column
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class ColumnNames to pass column name.
	 * @return String This returns string value with the Inspect Error detail if found; else null
	 */
	public String getInspctErrsDetails(String columnName) {
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName, InspectErrsColumnNamesTbl);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				ColumnValue = controlActions.perform_GetElementByXPath(DataImportExportPageLocators.INSPECT_ERRS_POPUP_COLUMN_VALUE, 
						"COLUMNINDEXNO", Integer.toString(columnIndex));

				String value = ColumnValue.getText();
				logInfo("For column : " + columnName + " value retrieved as - " + value);
				return value;
			}
		}
		catch(Exception e) {
			logError("Failed to get value for column : " + columnName + " - "
					+ e.getMessage());
			return null;
		}	
	}

	/** This method is used to close the Import tab > Inspect Errors 
	 * @author hingorani_a
	 * @return boolean This returns true after closing Inspect Errors popup
	 */	
	public boolean closeImportInspectErrsPopup() {
		try {
			controlActions.WaitforelementToBeClickable(InspectErrsPopupCancelBtn);
			InspectErrsPopupCancelBtn.click();
			logInfo("Closed Import tab > Inspect Errors popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Close Import tab > Inspect Errors popup - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to Reprocess the file for given Import entry
	 * @author hingorani_a
	 * @return boolean This returns true if the Reprocess of Import entry results into Success
	 * else false
	 */	
	public boolean reprocessFailedImportOnce() {
		String fileStatus = "scs-status-tooltip scs-inprocess-status";
		try {
			controlActions.hoverElement(ImportFileStatus);
			controlActions.WaitforelementToBeClickable(CallOutMenu);
			CallOutMenu.click();
			ReProcessDdl = controlActions.WaitUntilElementIsStale(DataImportExportPageLocators.REPROCESS_DDL);
			ReProcessDdl.click();
			controlActions.clickElement(ReProcessYesBtn);
			Sync();
			controlActions.clickElement(ImportGridRefreshBtn);
			Sync();
			fileStatus = ImportFileStatus.getAttribute("class");
			if(fileStatus.equals("scs-status-tooltip scs-success-status")) {
				logInfo("File is imported on reprocess sucessfully");
				return true;
			}
			logError("File is not imported on reprocess");
			return false;
		}
		catch(Exception e) {
			logError("Failed to reprocess a file - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to Download the file from given Import entry
	 * @author hingorani_a 
	 * @return boolean This returns true if the download works fine for given Import entry
	 */	
	public boolean downloadDataFromLatestImport() {
		try {
			controlActions.hoverElement(ImportFileStatus);
			controlActions.WaitforelementToBeClickable(CallOutMenu);
			CallOutMenu.click();
			ImportExportDownloadDdl = controlActions.WaitUntilElementIsStale(DataImportExportPageLocators.IMPORT_EXPORT_DOWNLOAD_DDL);
			ImportExportDownloadDdl.click();
			Sync();
			logInfo("Downloaded the data for latest Import");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Download the data for latest Import - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to get path for recent/top most extracted excel from DPT's Export tab 
	 * @author hingorani_a
	 * @return String This returns value of the filepath
	 */	
	public String getLatestExportFilePath() {
		String filePath = null;
		try {
			filePath = downloadPath+"\\"+FirstExtractedFileTxt.getText();
			logInfo("The extracted file path is " +  filePath);
		}
		catch(Exception e) {
			logError("Failed to get extracted file path - "+e.getMessage());
		}
		return filePath;
	}

	/** This method is used to Download the file from given Export entry
	 * @author hingorani_a 
	 * @return boolean This returns true if the download works fine for given Export entry
	 */	
	public boolean downloadDataFromLatestExport() {
		try {
			controlActions.hoverElement(ExportFileStatus);
			controlActions.WaitforelementToBeClickable(ExportCallOutMenu);
			ExportCallOutMenu.click();
			ImportExportDownloadDdl = controlActions.WaitUntilElementIsStale(DataImportExportPageLocators.IMPORT_EXPORT_DOWNLOAD_DDL);
			ImportExportDownloadDdl.click();
			threadsleep(5000);
			logInfo("Downloaded the data for latest Export");
			return true;
		}
		catch(Exception e) {
			logError("Failed to Download the data for latest Export - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to click on First page navigation button on Export tab > Extract Data popup
	 * @author hingorani_a
	 * @return boolean This returns true if First page navigation button is clicked successfully
	 */	
	public boolean clickDataExtractFirstPgNav() {
		try {
			controlActions.WaitforelementToBeClickable(DataExtractPgnFrstBtn);
			DataExtractPgnFrstBtn.click();
			Sync();
			logInfo("Clicked on First page navigation button on Export tab > Extract Data popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on First page navigation button on Export tab > Extract Data popup - "
					+ e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Next page navigation button on Export tab > Extract Data popup
	 * @author hingorani_a
	 * @return boolean This returns true if Next page navigation button is clicked successfully
	 */	
	public boolean clickDataExtractNextPgNav() {
		try {
			controlActions.WaitforelementToBeClickable(DataExtractPgnNxtBtn);
			DataExtractPgnNxtBtn.click();
			Sync();
			logInfo("Clicked on Next page navigation button on Export tab > Extract Data popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Next page navigation button on Export tab > Extract Data popup - "
					+ e.getMessage());
			return false;
		}
	}

	/** This method is used to click on Previous page navigation button on Export tab > Extract Data popup
	 * @author hingorani_a
	 * @return boolean This returns true if Previous page navigation button is clicked successfully
	 */	
	public boolean clickDataExtractPreviousPgNav() {
		try {
			controlActions.WaitforelementToBeClickable(DataExtractPgnPrvsBtn);
			DataExtractPgnPrvsBtn.click();
			Sync();
			logInfo("Clicked on Previous page navigation button on Export tab > Extract Data popup");
			return true;
		}
		catch(Exception e) {
			logError("Failed to click on Previous page navigation button on Export tab > Extract Data popup - "
					+ e.getMessage());
			return false;
		}
	}

	/** This method is used to click 'n' times on Next page navigation button on Export tab > Extract Data popup
	 * @author hingorani_a
	 * @param noOfClicks The number of times you want to click on Next page navigation button
	 * @return boolean This returns true if Next page navigation button is clicked successfully
	 */	
	public boolean clickDataExtractNextPgNav(int noOfClicks) {
		int nTimesClicked = 0;
		try {
			if(noOfClicks == 1) {
				if(!clickDataExtractNextPgNav())
					return false;
			}
			else if (noOfClicks > 1) {
				for(int i = 0; i < noOfClicks; i++) {
					if(!DataExtractPgnNxtBtn.getAttribute("class").contains("disabled")) {
						controlActions.WaitforelementToBeClickable(DataExtractPgnNxtBtn);
						DataExtractPgnNxtBtn.click();
						Sync();
						nTimesClicked = i;
					}
				}
				logInfo("Clicked on Next pagination button for " + nTimesClicked + " times");
				return true;
			}

			logError("Invalid operation is being performed");
			return false;
		}
		catch(Exception e) {
			logError("Failed to click on Next pagination button for " + noOfClicks + " times"
					+ e.getMessage());
			return false;
		}
	}

	/** This method is used to click 'n' times on Next page navigation button on Export tab > Extract Data popup
	 * @author hingorani_a
	 * @param noOfClicks The number of times you want to click on Next page navigation button
	 * @return boolean This returns true if Next page navigation button is clicked successfully
	 */	
	public int verifyDataExtractNextPgNavClicked(int noOfClicks) {
		int nTimesClicked = 0;
		try {
			if(noOfClicks == 1) {
				if(!clickDataExtractNextPgNav())
					return 0;
			}
			else if (noOfClicks > 1) {
				for(int i = 0; i < noOfClicks; i++) {
					if(!DataExtractPgnNxtBtn.getAttribute("class").contains("disabled")) {
						controlActions.WaitforelementToBeClickable(DataExtractPgnNxtBtn);
						DataExtractPgnNxtBtn.click();
						Sync();
						nTimesClicked = i+1;
					}
				}

				logInfo("Clicked on Next pagination button for " + nTimesClicked + " times");
				return nTimesClicked;
			}

			logError("Invalid operation is being performed");
			return 0;
		}
		catch(Exception e) {
			logError("Failed to click on Next pagination button for " + noOfClicks + " times"
					+ e.getMessage());
			return 0;
		}
	}

	/** This method is used to update excel with the data for the parameters provided for Form Compliance
	 * @author hingorani_a
	 * @param ifc Use Class ImportFormCompliance to set properties like Form name, type, compliance values
	 * @return boolean This returns as true if the excel file is written properly
	 */	
	public boolean updateFormComplianceExcelWithFields(ImportFormCompliance ifc) {
		String importFilePath = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String formName = null; 
		String resourceName = null;
		List<String> fieldNamesInSheet = new ArrayList<String>();
		int cellValue = 2;
		boolean newEntry = false;

		try {

			importFilePath = ifc.fileName;

			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			for(Map.Entry<String, String> entry : ifc.formInfo.entrySet()) {

				formName = entry.getKey(); //Form Name

				// Get list of updates to be done
				List<FormComplianceFields> ufcf = ifc.updateFields.get(formName);

				sheet = wb.getSheet(formName);
				logInfo("Reading sheet :: " + formName);

				boolean lastRowFlag = true;
				int lastRow = 0;
				while(lastRowFlag) {
					row = sheet.getRow(lastRow);
					if(row.getCell(0).getStringCellValue()!="")
						lastRow++;
					else
						lastRowFlag = false;
				}

				// Get fields
				int noOfFields = sheet.getRow(6).getLastCellNum();
				row  = sheet.getRow(6);
				for(int i = 1; i < noOfFields; i++) {
					if(row.getCell(i).getStringCellValue()!="")
						fieldNamesInSheet.add(row.getCell(i).getStringCellValue());
				}

				if(ufcf != null) {
					for(FormComplianceFields updateInfo : ufcf) {

						for(int i = 11; i < lastRow; i++) {
							row  = sheet.getRow(i);
							resourceName = row.getCell(0).getStringCellValue();
							if(resourceName.contains(updateInfo.resourceName)) {
								newEntry = false;
								break;
							}
							newEntry = true;
						}

						int pos = fieldNamesInSheet.indexOf(updateInfo.fieldName);

						if(pos==0)
							cellValue = 2;
						else if(pos!=0)
							cellValue = (pos) * 6 + 2;

						if(newEntry) {
							row = sheet.getRow(lastRow);
							row.getCell(0).setCellValue(updateInfo.resourceName);
							newEntry = false;
						}


						//Default,	Visible,	Min,	Target/Compliance Value,	Max,	UOM
						if(updateInfo.Default != null)
							row.getCell(cellValue).setCellValue(updateInfo.Default);

						if(updateInfo.Visible != null)
							row.getCell(cellValue+1).setCellValue(updateInfo.Visible);

						if(updateInfo.Min != null)
							row.getCell(cellValue+2).setCellValue(updateInfo.Min);

						if(updateInfo.Target_Compliance != null)
							row.getCell(cellValue+3).setCellValue(updateInfo.Target_Compliance);

						if(updateInfo.Max != null)
							row.getCell(cellValue+4).setCellValue(updateInfo.Max);

						if(updateInfo.UOM != null)
							row.getCell(cellValue+5).setCellValue(updateInfo.UOM);

						logInfo("Values are inserted for form compliance creation");

					}
				}
				fieldNamesInSheet.clear();
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 
			logInfo("Updated excel with data for import of Form Compliance");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update excel with data for import of Form Compliance - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to verify the details in extracted file for form compliance type
	 * @author hingorani_a
	 * @param efcf Use Class ExportFormComplianceFields to verify properties like Form name, type, compliance values
	 * @return boolean This returns as true if all the values are verified
	 */	
	public boolean verifyExportedFormCompliance(ExportFormComplianceFields efcf) {
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String currentRowData;
		boolean currentRowValue;
		String resourceName = null;
		List<String> fieldNamesInSheet = new ArrayList<String>();
		int cellValue = 2;
		boolean newEntry = false;

		try {
			fis = new FileInputStream(efcf.fileName);
			wb = new XSSFWorkbook(fis);  

			sheet = wb.getSheet(efcf.formName);
			logInfo("Reading sheet :: " + efcf.formName);

			// Get list of verifications to be done for form 
			List<FormComplianceFields> fcf = efcf.verifyFields.get(efcf.formName);

			boolean lastRowFlag = true;
			int lastRow = 0;
			while(lastRowFlag) {
				row = sheet.getRow(lastRow);
				if(row.getCell(0).getStringCellValue()!="")
					lastRow++;
				else
					lastRowFlag = false;
			}

			// Get fields
			int noOfFields = sheet.getRow(6).getLastCellNum();
			row  = sheet.getRow(6);
			for(int i = 1; i < noOfFields; i++) {
				if(row.getCell(i).getStringCellValue()!="")
					fieldNamesInSheet.add(row.getCell(i).getStringCellValue());
			}

			if(fcf != null) {
				for(FormComplianceFields verifyInfo : fcf) {

					for(int i = 11; i < lastRow; i++) {
						row  = sheet.getRow(i);
						resourceName = row.getCell(0).getStringCellValue();
						if(resourceName.contains(verifyInfo.resourceName)) {
							newEntry = false;
							break;
						}
						newEntry = true;
					}

					int pos = fieldNamesInSheet.indexOf(verifyInfo.fieldName);

					if(pos==0)
						cellValue = 2;
					else if(pos!=0)
						cellValue = (pos) * 6 + 2;

					if(newEntry) {
						logError("Verifying details for an Unknown resource " + resourceName);
						wb.close();
						return false;
					}


					//Default,	Visible,	Min,	Target/Compliance Value,	Max,	UOM
					if(verifyInfo.vDefault != null) {
						currentRowValue = row.getCell(cellValue).getBooleanCellValue();
						if(!currentRowValue==verifyInfo.vDefault) {
							wb.close();
							return false;
						}


						logInfo("For field " + verifyInfo.fieldName + ", Verified 'Default' value as " + verifyInfo.Default);
					}

					if(verifyInfo.vVisible != null) {
						currentRowValue = row.getCell(cellValue+1).getBooleanCellValue();
						if(!currentRowValue==verifyInfo.vVisible){
							wb.close();
							return false;
						}

						logInfo("For field " + verifyInfo.fieldName + ", Verified 'Visible' value as " + verifyInfo.Visible);
					}

					if(verifyInfo.Min != null) {
						currentRowData = row.getCell(cellValue+2).getStringCellValue();
						if(!currentRowData.equals(verifyInfo.Min)){
							wb.close();
							return false;
						}

						logInfo("For field " + verifyInfo.fieldName + ", Verified 'Min' value as " + verifyInfo.Min);
					}

					if(verifyInfo.Target_Compliance != null) {
						currentRowData = row.getCell(cellValue+3).getStringCellValue();
						if(!currentRowData.equals(verifyInfo.Target_Compliance)){
							wb.close();
							return false;
						}

						logInfo("For field " + verifyInfo.fieldName + ", Verified 'Target Compliance' value as " + verifyInfo.Target_Compliance);
					}

					if(verifyInfo.Max != null) {
						currentRowData = row.getCell(cellValue+4).getStringCellValue();
						if(!currentRowData.equals(verifyInfo.Max)) {
							wb.close();
							return false;
						}

						logInfo("For field " + verifyInfo.fieldName + ", Verified 'Max' value as " + verifyInfo.Max);
					}

					if(verifyInfo.UOM != null) {
						currentRowData = row.getCell(cellValue+5).getStringCellValue();
						if(!currentRowData.equals(verifyInfo.UOM)) {
							wb.close();
							return false;
						}

						logInfo("For field " + verifyInfo.fieldName + ", Verified 'UOM' value as " + verifyInfo.UOM);
					}

					logInfo("Values are inserted for form compliance creation");

				}
			}

			wb.close();
			fis.close();
			logInfo("Verified exported data from the application");
			return true;
		}
		catch (Exception e) {
			logError("Failed to verify exported data - " + e.getMessage());
			return false;
		} 
	}

	/** This method is used to update excel with the data for the parameters provided for Form Definition
	 * @author hingorani_a
	 * @param ifdf Use Class ImportFormDefinitionFields to set properties like Form name, type, field values
	 * @return boolean This returns as true if the excel file is written properly
	 */	
	public boolean updateFormDefinitionExcelWithFields(ImportFormDefinitionFields ifdf) {
		String importFilePath = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String formName = null; 
		String fieldPropertyName = null;
		List<String> fieldNamesInSheet = new ArrayList<String>();
		int cellValue = 0, newEntryCount = 1;

		try {

			importFilePath = ifdf.fileName;

			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			for(Entry<String, List<FormDefinitionFields>> entry : ifdf.formDetails.entrySet()) {

				formName = entry.getKey(); //Form Name
				// Get list of updates to be done
				List<FormDefinitionFields> fdf = entry.getValue();

				sheet = wb.getSheet(formName);
				logInfo("Reading sheet :: " + formName);

				boolean lastRowFlag = true;
				int lastRow = 0;
				while(lastRowFlag) {
					row = sheet.getRow(lastRow);
					if(row==null) 
						lastRowFlag = false;
					else if(row.getCell(0).getStringCellValue()!="")
						lastRow++;
					else
						lastRowFlag = false;
				}

				// Get field properties
				int noOfFields = sheet.getRow(4).getLastCellNum();
				row  = sheet.getRow(4);
				for(int i = 1; i < noOfFields; i++) {
					if(row.getCell(i).getStringCellValue()!="")
						fieldNamesInSheet.add(row.getCell(i).getStringCellValue());
				}

				if(fdf != null) {
					for(FormDefinitionFields updateInfo : fdf) {

						int pos = fieldNamesInSheet.indexOf(updateInfo.fieldName);

						if(updateInfo.newEntry) {
							cellValue = fieldNamesInSheet.size() + newEntryCount;
							newEntryCount++;
						}
						else {
							cellValue = pos + 1;
						}

						if(updateInfo.fieldName != null && updateInfo.newEntry)
							sheet.getRow(4).getCell(cellValue).setCellValue(updateInfo.fieldName);

						for(Map.Entry<String, String> prop : updateInfo.fieldProperties.entrySet()) {

							String fieldProperty = prop.getKey();
							String fieldPropertyValue = prop.getValue();

							for(int i = 4; i < lastRow; i++) {
								row  = sheet.getRow(i);
								fieldPropertyName = row.getCell(0).getStringCellValue();
								if(fieldPropertyName.equalsIgnoreCase(fieldProperty)) {
									break;
								}
							}

							if(fieldPropertyValue != null)
								row.getCell(cellValue).setCellValue(fieldPropertyValue);
						}

						logInfo("Values are inserted for form definition creation/updation");
					}
				}
				fieldNamesInSheet.clear();
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 
			logInfo("Updated excel with data for import of Form Definition");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update excel with data for import of Form Definition - " + e.getMessage());
			return false;
		}
	}

	/**
	 * This method is used to search and get DPT Import detail as per column
	 * @author hingorani_a
	 * @param columnName Column name whose Index you need. Use Class ColumnNames to pass column name.
	 * @return String This returns string value with the DPT Import detail if found; else null
	 */
	public String getImportTableDetails(String columnName) {
		WebElement ColumnValue = null; 
		try {
			int columnIndex = getColumnIndex(columnName, DptImportColumnNamesTbl);
			if(columnIndex == 0) {
				logError("Failed to get correct column index : " + columnIndex + " for column " + columnName);
				return null;
			}
			else {
				ColumnValue = controlActions.perform_GetElementByXPath(
						DataImportExportPageLocators.DPT_IMPORT_COLUMN_VALUE, "COLUMNINDEXNO", 
						Integer.toString(columnIndex+1));

				String value = ColumnValue.getText();
				logInfo("For column : " + columnName + " value retrieved as - " + value);
				return value;
			}
		}
		catch(Exception e) {
			logError("Failed to get value for column : " + columnName + " - "
					+ e.getMessage());
			return null;
		}	
	}

	/** This method is used to verify the details in extracted file for form definition type
	 * @author hingorani_a
	 * @param efdf Use Class ExportFormDefinitionFields to verify field properties value like 
	 * Hint, Allow Attachments is set or not, etc
	 * @return boolean This returns as true if all the values are verified
	 */	
	public boolean verifyExportedFormDefinition(ExportFormDefinitionFields efdf) {
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		List<String> fieldNamesInSheet = new ArrayList<String>();
		int cellValue = 2;
		String formName = null, fieldPropertyName = null, fieldPropertyValueFromExcel = null;
		Boolean fieldPropertyValFromExcel = null;
		int count = 0;
		int sizeOfValuesToBeVerified = 0;

		try {
			fis = new FileInputStream(efdf.fileName);
			wb = new XSSFWorkbook(fis);  

			for(Entry<String, List<FormDefinitionFields>> entry : efdf.verifyFields.entrySet()) {

				formName = entry.getKey(); //Form Name
				// Get list of updates to be done
				List<FormDefinitionFields> fdf = entry.getValue();

				sheet = wb.getSheet(formName);
				logInfo("Reading sheet :: " + formName);

				boolean lastRowFlag = true;
				int lastRow = 0;
				while(lastRowFlag) {
					row = sheet.getRow(lastRow);
					if(row==null) 
						lastRowFlag = false;
					else if(row.getCell(0).getStringCellValue()!="")
						lastRow++;
					else
						lastRowFlag = false;
				}

				// Get field properties
				int noOfFields = sheet.getRow(4).getLastCellNum();
				row  = sheet.getRow(4);
				for(int i = 1; i < noOfFields; i++) {
					if(row.getCell(i).getStringCellValue()!="")
						fieldNamesInSheet.add(row.getCell(i).getStringCellValue());
				}

				if(fdf != null) {
					for(FormDefinitionFields updateInfo : fdf) {

						int pos = fieldNamesInSheet.indexOf(updateInfo.fieldName);
						cellValue = pos + 1;

						sizeOfValuesToBeVerified += updateInfo.fieldProperties.size();
						for(Map.Entry<String, String> prop : updateInfo.fieldProperties.entrySet()) {

							String fieldProperty = prop.getKey();
							String fieldPropertyValue = prop.getValue();

							for(int i = 4; i < lastRow; i++) {
								row  = sheet.getRow(i);
								fieldPropertyName = row.getCell(0).getStringCellValue();
								if(fieldPropertyName.equalsIgnoreCase(fieldProperty)) {
									break;	
								}
							}

							if(fieldPropertyValue != null) {
								switch(fieldProperty) {
								case FormDeftnFieldName.IS_REQUIRED:
								case FormDeftnFieldName.ALLOW_COMMENTS:
								case FormDeftnFieldName.REPEAT_FIELD:
								case FormDeftnFieldName.FAILS_FORM:
								case FormDeftnFieldName.ALLOW_CORRECTION:
								case FormDeftnFieldName.SHOW_HINT:
								case FormDeftnFieldName.SHOW_MIN_MAX:
								case FormDeftnFieldName.SHOW_TARGET:
								case FormDeftnFieldName.SHOW_ON_COA:
								case FormDeftnFieldName.ALLOW_ATTACHMENTS:
									fieldPropertyValFromExcel = row.getCell(cellValue).getBooleanCellValue();
									fieldPropertyValueFromExcel = String.valueOf(fieldPropertyValFromExcel);
									break;

								default:
									fieldPropertyValueFromExcel = row.getCell(cellValue).getStringCellValue();
									break;
								}

								if(fieldPropertyValue.equalsIgnoreCase(fieldPropertyValueFromExcel))
									count++;
							}

						}

						logInfo("Values are inserted for form definition creation/updation");
					}
				}
				fieldNamesInSheet.clear();
			}

			wb.close();
			fis.close();

			if(sizeOfValuesToBeVerified == count) {
				logInfo("Verified exported data from the application");
				return true;
			}

			logInfo("Could not verify exported data from the application");
			return false;
		}
		catch (Exception e) {
			logError("Failed to verify exported data - " + e.getMessage());
			return false;
		} 
	}

	/** This method is used to Update the existing excel workbook and it's sheets
	 * @author hingorani_a
	 * @param clf Use Class ControlLimitFields to set information for filepath, Resource Name 
	 * , Mean, Variation, etc
	 * @return boolean This returns true once workbook is updated
	 */	
	public boolean updateControlLimitsExcelWithSettings(ControlLimitFields clf) {
		String importFilePath = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String excelResourceName = null;
		String resourceName = null;
		List<String> settingsNamesInSheet = new ArrayList<String>();
		int cellValue = 1;
		boolean newEntry = false;

		try {

			importFilePath = clf.fileName;

			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			int numberOfSheets = wb.getNumberOfSheets();
			if(numberOfSheets == 1)
				sheet = wb.getSheetAt(0);

			if(clf.formName != null) {
				// To Do
			}

			if(clf.chartName != null) {
				row  = sheet.getRow(1);
				row.getCell(cellValue).setCellValue(clf.chartName);
			}

			if(clf.fieldsForChart != null) {
				for(Map.Entry<String, HashMap<String, String>> entry : clf.fieldsForChart.entrySet()) {

					resourceName = entry.getKey(); // Resource Name

					boolean lastRowFlag = true;
					int lastRow = 0;
					while(lastRowFlag) {
						row = sheet.getRow(lastRow);

						if(row != null) {
							if(row.getCell(0).getStringCellValue()!="")
								lastRow++;
							else
								lastRowFlag = false;
						}
						else
							lastRowFlag = false;
					}

					// Get settings
					int noOfFields = sheet.getRow(3).getLastCellNum();
					row  = sheet.getRow(3);
					for(int i = 1; i < noOfFields; i++) {
						if(row.getCell(i).getStringCellValue()!="")
							settingsNamesInSheet.add(row.getCell(i).getStringCellValue());
					}

					if(resourceName != null) {
						for(int i = 4; i < lastRow; i++) {
							row  = sheet.getRow(i);
							excelResourceName = row.getCell(0).getStringCellValue();
							if(excelResourceName.contains(resourceName)) {
								newEntry = false;
								break;
							}
							newEntry = true;
						}
					}

					if(newEntry) {
						row = sheet.createRow(lastRow);
						row.getCell(0).setCellValue(resourceName);
						newEntry = false;
					}

					for(Map.Entry<String, String> settings : entry.getValue().entrySet()) {

						String settingsName = settings.getKey();
						String settingsValue = settings.getValue();

						int pos = settingsNamesInSheet.indexOf(settingsName);

						if(pos==0)
							cellValue = 1;
						else if(pos!=0)
							cellValue += pos;

						if(settingsValue != null)
							row.getCell(cellValue).setCellValue(settingsValue);

						logInfo("Values are inserted for Control Limits");
						cellValue = 1;
					}
				}
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 
			logInfo("Updated excel with data for import of Control Limits");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update excel with data for import of Control Limits - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to verify the details in extracted file for control limits type
	 * @author hingorani_a
	 * @param clf Use Class ControlLimitFields to set information for filepath, Resource Name 
	 * , Mean, Variation, etc
	 * @return boolean This returns as true if all the values are verified
	 */	
	public boolean verifyExportedControlLimits(ControlLimitFields clf) {
		String importFilePath = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String excelResourceName = null;
		String formName = null, chartName = null, locationName = null, resourceName = null;
		List<String> settingsNamesInSheet = new ArrayList<String>();
		int cellValue = 1, expectedCount = 0, verifiedCount = 0;
		String fieldPropertyValueFromExcel = null;

		try {

			importFilePath = clf.fileName;

			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			int numberOfSheets = wb.getNumberOfSheets();
			if(numberOfSheets == 1)
				sheet = wb.getSheetAt(0);

			if(clf.formName != null) {
				expectedCount++;
				row  = sheet.getRow(0);
				formName = row.getCell(cellValue).getStringCellValue();
				if(formName.contains(clf.formName)) {
					verifiedCount++;
					logInfo("Verfied form name as " + clf.formName);
				}
			}

			if(clf.chartName != null) {
				expectedCount++;
				row  = sheet.getRow(1);
				chartName = row.getCell(cellValue).getStringCellValue();
				if(chartName.contains(clf.chartName)) {
					verifiedCount++;
					logInfo("Verfied chart name as " + clf.chartName);
				}
			}

			if(clf.locationName != null) {
				expectedCount++;
				row  = sheet.getRow(2);
				locationName = row.getCell(cellValue).getStringCellValue();
				if(locationName.contains(clf.locationName)) {
					verifiedCount++;
					logInfo("Verfied location name as " + clf.locationName);
				}
			}

			if(clf.fieldsForChart != null) {
				for(Map.Entry<String, HashMap<String, String>> entry : clf.fieldsForChart.entrySet()) {

					resourceName = entry.getKey(); // Resource Name

					boolean lastRowFlag = true;
					int lastRow = 0;
					while(lastRowFlag) {
						row = sheet.getRow(lastRow);

						if(row != null) {
							if(row.getCell(0).getStringCellValue()!="")
								lastRow++;
							else
								lastRowFlag = false;
						}
						else
							lastRowFlag = false;
					}

					// Get settings
					int noOfFields = sheet.getRow(3).getLastCellNum();
					row  = sheet.getRow(3);
					for(int i = 1; i < noOfFields; i++) {
						if(row.getCell(i).getStringCellValue()!="")
							settingsNamesInSheet.add(row.getCell(i).getStringCellValue());
					}

					if(resourceName != null) {
						for(int i = 4; i < lastRow; i++) {
							row  = sheet.getRow(i);
							excelResourceName = row.getCell(0).getStringCellValue();
							if(excelResourceName.contains(resourceName)) {
								logInfo("Found resource " + resourceName);
								break;
							}
						}
					}

					for(Map.Entry<String, String> settings : entry.getValue().entrySet()) {

						expectedCount++;
						String settingsName = settings.getKey();
						String settingsValue = settings.getValue();

						int pos = settingsNamesInSheet.indexOf(settingsName);

						if(pos==0)
							cellValue = 1;
						else if(pos!=0)
							cellValue += pos;

						fieldPropertyValueFromExcel = row.getCell(cellValue).getStringCellValue();
						if(settingsValue.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
							verifiedCount++;
							logInfo("Value for " + settingsName + " is verfied for Control Limits");
						}

						cellValue = 1;
					}
				}
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 

			if(expectedCount == verifiedCount) {
				logInfo("Verified exported data from the application");
				return true;
			}

			logInfo("Could not verify exported data from the application");
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify excel with data for Control Limits - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to apply filter using a value on Export - Data Extract Popup
	 * @author hingorani_a
	 * @param element The column element on which we need to apply filter
	 * @param value The value using which filter is to be applied
	 * @return boolean This returns true if filter is applied successfully
	 */	
	public boolean applyFilter(WebElement element, String value) {
		try {
			controlActions.clickElement(element);
			for(WebElement icon : FilterIcons) {
				if(controlActions.isElementDisplay(icon)) {
					controlActions.WaitforelementToBeClickable(icon);
					icon.click();
					Sync();
					logInfo("Clicked on Filter icon");
				}
			}

			for(WebElement input : FilterInps) {
				if(controlActions.isElementDisplay(input)) {
					controlActions.WaitforelementToBeClickable(input);
					controlActions.sendKeys(input,value);
					logInfo("Filter value set as " + value);
				}
			}

			for(WebElement button : FilterBtns) {
				if(controlActions.isElementDisplay(button)) {
					controlActions.WaitforelementToBeClickable(button);
					button.click();
					logInfo("Clicked on Filter button");
				}
			}

			Sync();
			logInfo("Filtered item " + value);
			return true;
		}catch(Exception e) {
			logError("Failed to filter item - " + value + " - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to apply filter and select using values on Export - Data Extract Popup 
	 * @author hingorani_a
	 * @param element The column element on which we need to apply filter
	 * @param value A list of values using which filter is to be applied
	 * @return boolean This returns true if values are selected successfully
	 */	
	public boolean applyFilterAndSelectItem(WebElement element, List<String> values) {
		List<WebElement> selectEntry = null;
		String columnval = null;

		try {
			for(int i=0;i<values.size();i++) {
				if(!applyFilter(element, values.get(i)))
					return false;

				columnval = controlActions.perform_GetDynamicXPath(DataImportExportPageLocators.SELECT_VALUE_INP,
						"VALUE", values.get(i));
				selectEntry = controlActions.perform_GetListOfElementsByXPath(columnval);

				for(int j=0;j<selectEntry.size();j++) {
					controlActions.click(selectEntry.get(j));
				}
			}
			logInfo("Selected items(s)");
			return true;
		}catch(Exception e) {
			logError("Failed to select item(s) - "+e.getMessage());
			return false;
		}
	}

	/** This method is used to update excel with the data for the parameters provided for Records
	 * using FieldNames (Not ShortNames)
	 * @author hingorani_a
	 * @param ird Use Class ImportRecordDetails to set properties like Resource name, Field Name, Field values
	 * @return boolean This returns as true if the excel file is written properly
	 */	
	public boolean updateRecordsForSameFormUsingExcelAsPerResource(ImportRecordDetails ird) {
		String importFilePath = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String fieldName = null; 
		String resourceName = null;
		int numberOfSheets = 0;

		try {

			importFilePath = ird.fileName;

			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			numberOfSheets = wb.getNumberOfSheets();
			if(numberOfSheets == 1)
				sheet = wb.getSheetAt(0);

			if(ird.resourceName != null) {
				row  = sheet.getRow(3);
				row.getCell(1).setCellValue(ird.resourceName);
			}

			if(ird.updateFields != null) {
				for(Map.Entry<String, List<RecordFields>> entry : ird.updateFields.entrySet()) {

					resourceName = entry.getKey(); //Resource Name

					// Get list of updates to be done
					List<RecordFields> rf = ird.updateFields.get(resourceName);

					if(numberOfSheets ==1 )
						System.out.println("Do nothing as the 1 sheet is available which is already selected");
					else {
						numberOfSheets = wb.getNumberOfSheets();
						for(int i = 0; i < numberOfSheets ; i++) {
							sheet = wb.getSheetAt(i);
							row = sheet.getRow(3);
							if(row.getCell(1).getStringCellValue().contains(resourceName))
								break;
						}
						logInfo("Reading sheet for form with resource :: " + resourceName);
					}

					int lastRow = getLastRowCount(sheet, row);

					if(rf != null) {
						for(RecordFields updateInfo : rf) {

							for(int i = 7; i < lastRow; i++) {
								row  = sheet.getRow(i);
								fieldName = row.getCell(2).getStringCellValue();
								if(fieldName.contains(updateInfo.fieldName)) {
									break;
								}
							}

							// Field Value
							if(updateInfo.fieldValue != null)
								row.getCell(4).setCellValue(updateInfo.fieldValue);

							logInfo("Values are inserted for record creation");

						}
					}
				}
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 
			logInfo("Updated excel with data for import of Record");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update excel with data for import of Record - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to verify the details in extracted file for Record type
	 * @author hingorani_a
	 * @param ird Use Class ImportRecordDetails to verify properties like 
	 * Resource name, Field Name, Field values
	 * @return boolean This returns as true if all the values are verified
	 */	
	public boolean verifyExportedRecord(ImportRecordDetails ird) {
		String importFilePath = null;
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String formName = null, formType = null, locationName = null, 
				resourceName = null, fieldName = null;
		int cellValue = 1, expectedCount = 0, verifiedCount = 0;
		String fieldPropertyValueFromExcel = null;


		try {

			importFilePath = ird.fileName;

			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			int numberOfSheets = wb.getNumberOfSheets();
			if(numberOfSheets == 1)
				sheet = wb.getSheetAt(0);

			if(ird.formName != null) {
				expectedCount++;
				row  = sheet.getRow(0);
				formName = row.getCell(cellValue).getStringCellValue();
				if(formName.contains(ird.formName)) {
					verifiedCount++;
					logInfo("Verfied form name as " + ird.formName);
				}
			}

			if(ird.formType != null) {
				expectedCount++;
				row  = sheet.getRow(1);
				formType = row.getCell(cellValue).getStringCellValue();
				if(formType.contains(ird.formType)) {
					verifiedCount++;
					logInfo("Verfied form name as " + ird.formType);
				}
			}

			if(ird.resourceName != null) {
				expectedCount++;
				row  = sheet.getRow(3);
				resourceName = row.getCell(cellValue).getStringCellValue();
				if(resourceName.contains(ird.resourceName)) {
					verifiedCount++;
					logInfo("Verfied chart name as " + ird.resourceName);
				}
			}

			if(ird.locationName != null) {
				expectedCount++;
				row  = sheet.getRow(4);
				locationName = row.getCell(cellValue).getStringCellValue();
				if(locationName.contains(ird.locationName)) {
					verifiedCount++;
					logInfo("Verfied chart name as " + ird.locationName);
				}
			}

			if(ird.verifyFields != null) {
				for(Map.Entry<String, RecordFields> entry : ird.verifyFields.entrySet()) {

					fieldName = entry.getKey(); // Resource Name
					RecordFields rf = entry.getValue(); // Details of Record

					int lastRow = getLastRowCount(sheet, row);

					if(rf != null) {

						for(int i = 7; i < lastRow; i++) {
							row  = sheet.getRow(i);
							fieldName = row.getCell(2).getStringCellValue();
							if(fieldName.contains(rf.fieldName)) {
								break;
							}
						}

						// Verify Field Values
						if(rf.sectionName != null) {
							expectedCount++;
							fieldPropertyValueFromExcel = row.getCell(0).getStringCellValue();
							if(rf.sectionName.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
								verifiedCount++;
								logInfo("Value of Section for field " + rf.sectionName + " is verfied for Record");
							}
						}

						if(rf.fieldGroupName != null) {
							expectedCount++;
							fieldPropertyValueFromExcel = row.getCell(1).getStringCellValue();
							if(rf.fieldGroupName.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
								verifiedCount++;
								logInfo("Value of Field Group for field " + rf.fieldName + " is verfied for Record");
							}
						}

						if(rf.shortName != null) {
							expectedCount++;
							fieldPropertyValueFromExcel = row.getCell(3).getStringCellValue();
							if(rf.shortName.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
								verifiedCount++;
								logInfo("Value of Short name for field " + rf.fieldName + " is verfied for Record");
							}
						}
					}

				}
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 

			if(expectedCount == verifiedCount) {
				logInfo("Verified exported data from the application");
				return true;
			}

			logInfo("Could not verify exported data from the application");
			return false;
		}
		catch(Exception e) {
			logError("Failed to verify excel with data for Record - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to update excel with the data for the parameters provided for Users
	 * @author hingorani_a
	 * @param importFilePath The file path of excel to be updated
	 * @param existingEntriesCount Comes in handy when you need to add new User details with existing users. 
	 * This count is nothing but the existing users count in the excel
	 * @param listOfUID The list of objects of Class UserInfoDetails to update details like username, first name
	 * , last name, phone number, etc
	 * @return boolean This returns as true if the excel file is written properly
	 */	
	public boolean updateUsersExcelWithDetails(String importFilePath, int existingEntriesCount, List<UserInfoDetails> listOfUID) {
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		boolean newEntry = false;
		String username = null;
		int addNewEntryCount = 0;
		try {
			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			for(UserInfoDetails uid : listOfUID) {

				// Sheet named as 'Users' having basic user details
				sheet= wb.getSheet("Users");

				int users_lastRow = getLastRowCount(sheet, row);

				if(uid.newEntriesOnly) {
					for(int i = existingEntriesCount + 1; i < users_lastRow; i++) {
						row = sheet.getRow(i);
						sheet.removeRow(row);
					}
					newEntry = true;
					users_lastRow = existingEntriesCount + 1;
					logInfo("Removed all entries from 'Users' sheet");
				}

				for(int i = 1; i < users_lastRow; i++) {
					row  = sheet.getRow(i);
					username = row.getCell(0).getStringCellValue();
					if(username.contains(uid.userName)) {
						newEntry = false;
						break;
					}
					newEntry = true;
				}

				logInfo("For 'Users' sheet :: ");

				if(newEntry) {
					int updatedNewEntry = users_lastRow + addNewEntryCount;
					row = sheet.getRow(updatedNewEntry);

					row  = sheet.createRow(updatedNewEntry);

					if(uid.userName != null) {
						row.createCell(0).setCellValue(uid.userName);
						logInfo("Added New user : " + uid.userName);
					}

					if(uid.firstName != null) {
						row.createCell(1).setCellValue(uid.firstName);
						logInfo("Added First name for New user as : " + uid.firstName);
					}

					if(uid.lastName != null) {
						row.createCell(2).setCellValue(uid.lastName);
						logInfo("Added Last name for New user as : " + uid.lastName);
					}

					if(uid.email != null) {
						row.createCell(3).setCellValue(uid.email);
						logInfo("Added Email for New user as : " + uid.email);
					}

					if(uid.timeZone != null) {
						row.createCell(4).setCellValue(uid.timeZone);
						logInfo("Added Timezone for New user as : " + uid.timeZone);
					}

					if(uid.status != null) {
						row.createCell(5).setCellValue(uid.status);
						logInfo("Added Status for New user as : " + uid.status);
					}

					if(uid.employeeId != null) {
						row.createCell(6).setCellValue(uid.employeeId);
						logInfo("Added Employee Id for New user as : " + uid.employeeId);
					}

					if(uid.phone != null) {
						row.createCell(7).setCellValue(uid.phone);
						logInfo("Added Phone for New user as : " + uid.phone);
					}

					if(uid.defaultLocation != null) {
						row.createCell(8).setCellValue(uid.defaultLocation);
						logInfo("Added Default Location for New user as : " + uid.defaultLocation);
					}
					
					addNewEntryCount++;
				}
				else {

					if(uid.firstName != null) {
						row.getCell(1).setCellValue(uid.firstName);
						logInfo("Updated First name for Existing user as : " + uid.firstName);
					}

					if(uid.lastName != null) {
						row.getCell(2).setCellValue(uid.lastName);
						logInfo("Updated Last name for Existing user as : " + uid.lastName);
					}

					if(uid.email != null) {
						row.getCell(3).setCellValue(uid.email);
						logInfo("Updated Email for Existing user as : " + uid.email);
					}

					if(uid.timeZone != null) {
						row.getCell(4).setCellValue(uid.timeZone);
						logInfo("Updated Timezone for Existing user as : " + uid.timeZone);
					}

					if(uid.status != null) {
						row.getCell(5).setCellValue(uid.status);
						logInfo("Updated Status for Existing user as : " + uid.status);
					}

					if(uid.employeeId != null) {
						row.getCell(6).setCellValue(uid.employeeId);
						logInfo("Updated Employee Id for Existing user as : " + uid.employeeId);
					}

					if(uid.phone != null) {
						row.getCell(7).setCellValue(uid.phone);
						logInfo("Updated Phone for Existing user as : " + uid.phone);
					}

					if(uid.defaultLocation != null) {
						row.getCell(8).setCellValue(uid.defaultLocation);
						logInfo("Updated Default Location for Existing user as : " + uid.defaultLocation);
					}
				}

				// reset newEntry to false
				newEntry = false;
				addNewEntryCount = 0;

				// Sheet named as 'User_Location' having basic user details
				sheet= wb.getSheet("User_Location");

				int usersLoc_lastRow = getLastRowCount(sheet, row);

				if(uid.newEntriesOnly) {
					for(int i = existingEntriesCount + 1; i < usersLoc_lastRow; i++) {
						row = sheet.getRow(i);
						sheet.removeRow(row);
					}
					newEntry = true;
					usersLoc_lastRow = existingEntriesCount + 1;
					logInfo("Removed all entries from 'User_Location' sheet");
				}

				for(int i = 1; i < usersLoc_lastRow; i++) {
					row  = sheet.getRow(i);
					username = row.getCell(0).getStringCellValue();
					if(username.contains(uid.userName)) {
						newEntry = false;
						break;
					}
					newEntry = true;
				}

				logInfo("For 'User_Location' sheet :: ");

				if(newEntry) {
					int updatedNewEntry = usersLoc_lastRow + addNewEntryCount;
					row = sheet.getRow(updatedNewEntry);

					row  = sheet.createRow(updatedNewEntry);

					if(uid.userName != null) {
						row.createCell(0).setCellValue(uid.userName);
						logInfo("Added New user entry for user : " + uid.userName);
					}

					if(uid.location != null) {
						row.createCell(1).setCellValue(uid.location);
						logInfo("Added Location for New user as : " + uid.location);
					}
					
					addNewEntryCount++;
				}
				else {
					if(uid.location != null) {
						row.getCell(1).setCellValue(uid.location);
						logInfo("Updated Location for Existing user as : " + uid.location);
					}
				}

				// reset newEntry to false
				newEntry = false;
				addNewEntryCount = 0;

				// Sheet named as 'User_Role' having basic user details
				sheet= wb.getSheet("User_Role");

				int usersRole_lastRow = getLastRowCount(sheet, row);

				if(uid.newEntriesOnly) {
					for(int i = existingEntriesCount + 1; i < usersRole_lastRow; i++) {
						row = sheet.getRow(i);
						sheet.removeRow(row);
					}
					newEntry = true;
					usersRole_lastRow = existingEntriesCount + 1;
					logInfo("Removed all entries from 'User_Role' sheet");
				}

				for(int i = 1; i < usersRole_lastRow; i++) {
					row  = sheet.getRow(i);
					username = row.getCell(0).getStringCellValue();
					if(username.contains(uid.userName)) {
						newEntry = false;
						break;
					}
					newEntry = true;
				}

				logInfo("For 'User_Role' sheet :: ");

				if(newEntry) {
					int updatedNewEntry = usersRole_lastRow + addNewEntryCount;
					row = sheet.getRow(updatedNewEntry);

					row  = sheet.createRow(updatedNewEntry);

					if(uid.userName != null) {
						row.createCell(0).setCellValue(uid.userName);
						logInfo("Added New user entry for user : " + uid.userName);
					}

					if(uid.role != null) {
						row.createCell(1).setCellValue(uid.role);
						logInfo("Added Role for New user as : " + uid.role);
					}
					
					addNewEntryCount++;
				}
				else {
					if(uid.role != null) {
						row.getCell(1).setCellValue(uid.role);
						logInfo("Updated Role for Existing user as : " + uid.role);
					}
				}
				logInfo("Field values has has been set for User(s) " + uid.userName);
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 
			logInfo("Updated excel with data for import of Users");
			return true;
		}
		catch(Exception e) {
			logError("Failed to update excel with data for import of Users - " + e.getMessage());
			return false;
		}
	}

	/** This method is used to get the Last Row count given the sheet and row as input parameters
	 * @author hingorani_a
	 * @param sheet The sheet on which last row count you need information on
	 * @param row An object of Row type in order to iterate through rows of sheet
	 * @return int This returns the value of Last Row count in the sheet
	 */	
	public int getLastRowCount(Sheet sheet , Row row) {

		boolean lastRowFlag = true;
		int lastRow = 0;

		try {
			while(lastRowFlag) {
				row = sheet.getRow(lastRow);

				if(row!=null) {

					try {
						if(row.getCell(0).getStringCellValue() == null)
							lastRowFlag = false;
						else if(row.getCell(0).getStringCellValue()!="")
							lastRow++;
					}
					catch(NullPointerException npe) {
						logInfo("Null data found while finding last row" + npe.getMessage());
						row = sheet.getRow(lastRow-1);
						lastRowFlag = false;
					}
				}

				else
					lastRowFlag = false;
			}
			logInfo("Last row count found as : " + lastRow);
		}
		catch(Exception e) {
			logError("Failed to get Last row count - "+e.getMessage());
		}
		return lastRow;
	}
	
	/** This method is used to verify excel with the data for the parameters provided for Users
	 * @author hingorani_a
	 * @param importFilePath The file path of excel to be verified
	 * @param listOfUID The list of objects of Class UserInfoDetails to verify details like username, first name
	 * , last name, email, etc
	 * @return boolean This returns as true if the excel file is verified properly
	 */	
	public boolean verifyUsersExcelDetails(String importFilePath, List<UserInfoDetails> listOfUID) {
		InputStream fis;
		Workbook wb = null;
		Sheet sheet = null;
		Row row = null;
		String username = null, fieldPropertyValueFromExcel = null;
		int fieldPropertyValFromExcel = 0;
		int expectedCount = 0, verifiedCount = 0;

		try {
			fis = new FileInputStream(importFilePath);
			wb = new XSSFWorkbook(fis);  

			for(UserInfoDetails uid : listOfUID) {

				// Sheet named as 'Users' having basic user details
				sheet= wb.getSheet("Users");

				int users_lastRow = getLastRowCount(sheet, row);
				for(int i = 1; i < users_lastRow; i++) {
					row  = sheet.getRow(i);
					username = row.getCell(0).getStringCellValue();
					if(username.contains(uid.userName)) {
						break;
					}
				}

				logInfo("For 'Users' sheet :: ");

				if(uid.firstName != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(1).getStringCellValue();
					if(uid.firstName.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of First name is verified as " + uid.firstName);
					}
				}

				if(uid.lastName != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(2).getStringCellValue();
					if(uid.lastName.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of Last name is verified as " + uid.lastName);
					}
				}

				if(uid.email != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(3).getStringCellValue();
					if(uid.email.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of Email is verified as " + uid.email);
					}
				}

				if(uid.timeZone != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(4).getStringCellValue();
					if(uid.timeZone.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of Timezone is verified as " + uid.timeZone);
					}
				}

				if(uid.status != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(5).getStringCellValue();
					if(uid.status.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of Last name is verified as " + uid.status);
					}
				}

				if(uid.employeeId != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(6).getStringCellValue();
					if(uid.employeeId.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of Employee Id is verified as " + uid.employeeId);
					}
				}

				if(uid.phone != null) {
					expectedCount++;
					fieldPropertyValFromExcel = (int) row.getCell(7).getNumericCellValue();
					if(Integer.parseInt(uid.phone) == fieldPropertyValFromExcel) {
						verifiedCount++;
						logInfo("Value of Phone is verified as " + uid.phone);
					}
				}

				if(uid.defaultLocation != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(8).getStringCellValue();
					if(uid.defaultLocation.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of Default Location is verified as " + uid.defaultLocation);
					}
				}

				// Sheet named as 'User_Location' having basic user details
				sheet= wb.getSheet("User_Location");

				int usersLoc_lastRow = getLastRowCount(sheet, row);

				for(int i = 1; i < usersLoc_lastRow; i++) {
					row  = sheet.getRow(i);
					username = row.getCell(0).getStringCellValue();
					if(username.contains(uid.userName)) {
						break;
					}
				}

				logInfo("For 'User_Location' sheet :: ");

				if(uid.location != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(1).getStringCellValue();
					if(uid.location.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of Location is verified as " + uid.location);
					}
				}

				// Sheet named as 'User_Role' having basic user details
				sheet= wb.getSheet("User_Role");

				int usersRole_lastRow = getLastRowCount(sheet, row);

				for(int i = 1; i < usersRole_lastRow; i++) {
					row  = sheet.getRow(i);
					username = row.getCell(0).getStringCellValue();
					if(username.contains(uid.userName)) {
						break;
					}
				}

				logInfo("For 'User_Role' sheet :: ");

				if(uid.role != null) {
					expectedCount++;
					fieldPropertyValueFromExcel = row.getCell(1).getStringCellValue();
					if(uid.role.equalsIgnoreCase(fieldPropertyValueFromExcel)) {
						verifiedCount++;
						logInfo("Value of Role is verified as " + uid.role);
					}
				}
				logInfo("Field values has has been set for User(s) " + uid.userName);
			}

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(importFilePath);
			wb.write(fileOut); 
			fileOut.close(); 
			
			if(expectedCount == verifiedCount) {
				logInfo("Verified exported data from the application");
				return true;
			}

			logInfo("Could not verify exported data from the application");
			return false;
		}
		catch(Exception e) {
			logError("Failed to update excel with data for import of Users - " + e.getMessage());
			return false;
		}
	}

	public static class UserInfoDetails{
		public boolean newEntriesOnly = false;
		public String userName;
		public String firstName;
		public String lastName;
		public String email;
		public String timeZone;
		public String status;
		public String employeeId;
		public String phone;
		public String defaultLocation;
		public String location;
		public String role;

	}

	public static class ImportRecordDetails{

		public String fileName;
		public String formName;
		public String formType;
		public String locationName;
		public String resourceName;

		public HashMap<String, List<RecordFields>> updateFields; // resourceName and fields
		public HashMap<String, RecordFields> verifyFields; // fieldName and details

	}

	public static class RecordFields{
		public String sectionName;
		public String fieldGroupName;
		public String fieldName;
		public String shortName;
		public String fieldValue;
		public String comments;

	}

	public static class ControlLimitFields{
		public String formName;
		public String locationName;
		public String chartName;
		public HashMap<String,HashMap<String, String>> fieldsForChart;
		public String fileName;
	}

	public static class ChartFieldSettingNames{
		public static final String IS_APPLICABLE = "IsApplicable";
		public static final String IS_DEFAULT = "IsDefault";
		public static final String MEAN = "Mean";
		public static final String VARIATION = "Variation";
		public static final String SAMPLE_SIZE = "Sample Size";
		public static final String COMMENT = "Comment";
	}

	public static class ResourceFieldValues{
		public String resourceInstance;
		public static String singleLineText = "Test";
		public static int numeric = 2;
		public static String selectOne = "1";
		public String status = "True";
	}

	public static class ExportResourceFields{
		public String ResourceType;
		public String Category;
		public List<String> Resources;
		public String ExtractedFileName;
		public boolean isDownload=true;
	}

	public static class ImportResourceFields{
		public String ResourceType;
		public String Category;
		public String NewCategory;
		public String oldCategory;
		public List<String> Resources;
		public List<String> NewResources;
		ResourceFieldValues resourcedetailsfields;
		public HashMap<String,List<ResourceFieldValues>> setFieldsForResource;
		public String fileName;
	}

	public static class ImportRecordFields{

		public List<String> FieldValue;
		public String RecordfileName;
		public List<String> FormName;	
	}

	public static class ImportFormDefinitionFields{

		public List<String> FieldValue;
		public String FormDefinationfileName;
		public List<String>FormName;	
		public String newFormName;

		// String is name of Form and List<FormDefinitionFields> is the list of objects
		// where all the field detail for one field is mentioned 
		public HashMap<String, List<FormDefinitionFields>> formDetails;
		public String fileName;

	}

	public static class FormDeftnFieldName{
		public static final String FIELD_NAME = "Field Name";
		public static final String SHORT_NAME = "Short Name";
		public static final String TYPE = "Type";
		public static final String ELEMENT_TYPE = "Element Type";
		public static final String PARENT = "Parent";
		public static final String OPTIONS = "Options";
		public static final String DEPENDENCY_RULE = "Dependency Rule";
		public static final String EXPRESSION_RULE = "Expression Rule";
		public static final String IS_REQUIRED = "IsRequired";
		public static final String ALLOW_COMMENTS = "AllowComments";
		public static final String REPEAT_FIELD = "RepeatField";
		public static final String REPEAT = "Repeat";
		public static final String DATA_SOURCE_NAME = "DataSourceName";
		public static final String FAILS_FORM = "FailsForm";
		public static final String ALLOW_CORRECTION = "Allow Correction";
		public static final String SHOW_IS_RESOLVED = "Show IsResolved";
		public static final String FUNCTION = "Function";
		public static final String FUNCTION_PARAMETERS = "Function Parameters";
		public static final String SOURCE = "Source";
		public static final String DEFAULT = "Default";
		public static final String SHOW_GROUPNAME = "Show GroupName";
		public static final String SHOW_HINT = "Show Hint";
		public static final String HINT = "Hint";
		public static final String SHOW_MIN_MAX = "Show Min/Max";
		public static final String SHOW_TARGET = "Show Target";
		public static final String SHOW_ON_COA = "Show on COA";
		public static final String PREDEFINED_CORRECTIONS = "Predefined Corrections";
		public static final String ALLOW_ATTACHMENTS = "Allow Attachments";
	}

	public static class ExportFormDefinitionFields {

		public String fileName;
		public String formName;
		public HashMap<String, List<FormDefinitionFields>> verifyFields; // formName and fields
	}

	public static class FormDefinitionFields{

		public boolean newEntry = false;
		public String fieldName;

		// property and value to be set
		public HashMap<String, String> fieldProperties;

	}

	public static class ImportFormCompliance{

		public String fileName;
		public List<String> FormName;
		public HashMap<String, String> formInfo; // formName and Type
		public HashMap<String, List<FormComplianceFields>> updateFields; // formName and fields

		public String FormComplaincefileName;
	}

	public static class FormComplianceFields{
		public String sectionName;
		public String fieldName;
		public String fieldType;
		public String resourceName;
		public String Default;
		public Boolean vDefault;
		public String Visible;
		public Boolean vVisible;
		public String Min;
		public String Target_Compliance;
		public String Value;
		public String Max;
		public String UOM;

	}

	public static class ExportFormComplianceFields {

		public String fileName;
		public String formName;
		public HashMap<String, List<FormComplianceFields>> verifyFields; // formName and fields
	}

	public static class ImportLocationFields{
		public String locationCategory;
		public List<String> locations;
		public List<String> newLocations;
	}

	public static class ExportLocationFields{
		public String locationCategory;
		public List<String> locations;
		public boolean isDownload=true;
	}

	public static class ExtractType{
		public static final String FORM_COMPLIANCE = "Form Compliance";
		public static final String RESOURCE_CUSTOMERS = "Resource-Customers";
		public static final String RESOURCE_EQUIPMENTS = "Resource-Equipment";
		public static final String RESOURCE_ITEMS = "Resource-Items";
		public static final String RESOURCE_SUPPLIERS = "Resource-Suppliers";
		public static final String RECORDS = "Records";
		public static final String USERS = "Users";
		public static final String SUPPLIER_USERS = "Supplier Users";
		public static final String WORKGROUPS = "Workgroups";
		public static final String LOCATIONS = "Locations";
		public static final String ROLES = "Roles";
		public static final String FORM_DEFINITION = "Forms Definition";
		public static final String CONTROL_LIMITS = "Control Limits";
	}

	public static class ImportType{
		public static final String FORM_COMPLIANCE = "Form Compliance";
		public static final String RESOURCE_ITEMS = "Resource-Items";
		public static final String RECORDS = "Records";
		public static final String INTERNAL_USER = "Users";
		public static final String LOCATIONS = "Locations";
		public static final String SUPPLIER_USER = "Supplier Users";
		public static final String FORM_DEFINITION = "Forms Definition";
		public static final String RESOURCE_EQUIPMENTS = "Resource-Equipment";
		public static final String RESOURCE_SUPPLIERS = "Resource-Suppliers";
		public static final String RESOURCE_CUSTOMERS = "Resource-Customers";
		public static final String CONTROL_LIMITS = "Control Limits";

	}

	public static class ColumnNames{
		public static final String FORM_NAME = "Form Name";
		public static final String CHART_NAME = "Chart Name";
		public static final String FORM_TYPE = "Form Type";
		public static final String MODIFIED_BY = "Modified By";
		public static final String MODIFIED_ON = "Modified On";
		public static final String VERSION = "Version";
		public static final String RESOURCE = "Resource";
		public static final String LOCATION = "Location";
		public static final String LOCATION_NAME = "Location Name";
		public static final String RESOURCE_NAME = "Resource Name";
		public static final String ROLE_NAME = "Role Name";
		public static final String USER_NAME = "User Name";
		public static final String WORKGROUP_NAME = "WorkGroup Name";
		public static final String HIERARCHY = "Hierarchy";
		public static final String EMPLOYEE_ID = "Employee ID";
		public static final String FIRST_NAME = "First Name";
		public static final String LAST_NAME = "Last Name";
		public static final String TIMEZONE = "TimeZone";
		public static final String EMAIL = "Email";
		public static final String LOCATIONS = "Locations";
		public static final String ROLES = "Roles";
		public static final String WORKGROUPS = "Workgroups";
		public static final String SECURITY_LEVEL = "Security Level";
		public static final String DEFAULT_LOCATION = "Default Location";
		public static final String PHONE = "Phone";
		public static final String TITLE = "Title";
		public static final String SUPPLIER_NAMES = "Supplier Names(s)";
		public static final String IS_ENABLE = "IsEnable";
		public static final String ERROR_MESSAGE = "Error Message";
		public static final String FILENAME = "Filename";

	}

	public static class UserInfo{
		public static String FirstName = "Automation";
		public static String LastName = "User";
		public static String Email = "demo.user@automations.com";
		public static String TimeZone = "U.S. Pacific";
		public static String Role = "SuperAdmin";
		public static String Title = "Test User";

	}

	public static class COLUMN_SETTING{
		public static final String SORTASCENDING = "Sort Ascending";
		public static final String SORTDESCENDING = "Sort Descending";
		public static final String COLUMNS = "Columns";
		public static final String CHECK = "check";
		public static final String UNCHECK = "uncheck";
		public static final String FILTER = "Filter";

	}

	public static class IMPORT_STATUS{
		public static final String IN_PROGRESS = "IN PROGRESS";
		public static final String PARTIAL_SUCCESS = "PARTIAL SUCCESS";
		public static final String SUCCESS = "SUCCESS";
		public static final String FAILURE = "FAILURE";

	}
}

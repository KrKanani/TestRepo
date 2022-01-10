package com.project.safetychain.webapp.pageobjects;

public class DataImportExportPageLocators {
	
	/** id */
	public static final String SELECT_ALL_DATA_EXTRACT_POPUP = "scs-grid-select-all";
	
	/** xpath */
	public static final String EXPORT_TAB = "//*[@id='dataprovisioning-tabstrip']//li[@data-hash='Export']/span[normalize-space(.)='Export']";
	public static final String IMPORT_TAB = "//*[@id='dataprovisioning-tabstrip']//li[@data-hash='Import']/span[normalize-space(.)='Import']";

	public static final String EXTRACT_DATA_BTN = "//button[normalize-space(.)='Extract Data']";
	public static final String EXTRACT_TYPE_DDL = "//span[@aria-owns='scs-dpt-export-resource-type_listbox']//i[@class='fa fa-sort-desc']";
	public static final String EXTRACT_TYPE_DDV  = "//div[@class='k-animation-container']//div[@title='EXTRACT_TYPE']";
	public static final String DATA_EXTRACT_POP_UP_BTN = "//button[text()='Data Extract']";
	public static final String SEARCH_TEXTBOX = "//input[@id='filterText']";
	public static final String SEARCH_LNK = "//div[@id='filterDataSource']";
	public static final String EXPORT_ITEM_CHECKBOX = "//div[@id='scs-dpt-filters-grid']//td[text()='ITEMNAME']/../td/input[contains(@class,'checkbox')]";
	public static final String EXPORT_TYPE_LNK ="//div[@id='scs-data-provisioning-export-grid-container']//tr[1]/td[text()='EXPORTTYPE']"; 
	
	public static final String IMPORT_DATA_BTN = "//button[normalize-space(.)='New Import']";
	public static final String IMPORT_TYPE_DDL = "//span[@aria-owns='scs-dpt-resource-type_listbox']//i[@class='fa fa-sort-desc']";
	public static final String IMPORT_TYPE_DDV  = "//div[@class='k-animation-container']//div[@title='IMPORT_TYPE']";
	public static final String DATA_IMPORT_UPLOAD_POP_UP_BTN = "//div[@class='ui-dialog-buttonset']//button[text()='Upload']";

	public static final String UPLOAD_FILE_INP = "//input[@id='scs-dpt-file-upload-input'][1]";
	public static final String UPLOADED_FILE_STS = "//*[@id='scs-data-provisioning-import-grid-container']//tr[1]/td[1]/span";
	public static final String IMPORT_GRID_REFRESH_BTN = "//*[@id='scs-data-provisioning-import-grid-container']//a[@class='k-pager-refresh k-link']";
	public static final String IMPORT_ERROR_MESSAGE_TXT  = "//*[@id='scs-data-provisioning-import-grid-container']//tr[1]/td[6]";

	public static final String EXTRACTED_FILE_STS = "//*[@id='scs-data-provisioning-export-grid-container']//tr[1]/td[1]/span";
	public static final String EXPORT_GRID_REFRESH_BTN = "//*[@id='scs-data-provisioning-export-grid-container']//a[@class='k-pager-refresh k-link']";
	public static final String EXPORT_ERROR_MESSAGE_TXT = "//*[@id='scs-data-provisioning-export-grid-container']//tr[1]/td[5]";
	public static final String FIRST_EXTRACTED_FILE_TXT  = "//*[@id='scs-data-provisioning-export-grid-container']//tr[1]/td[3]";

	public static final String RESOURCE_SETTINGS_CLM =  "//th[@data-field='Resource']//a[1]/i";
	public static final String FILTER_ICON = "//li//span[@class='k-icon k-i-filter']";
	public static final String FILTER_INP = "//input[@class='k-filter-text']";
	public static final String FILTER_BTN = "//button[text()='Filter']";
	public static final String SELECT_RESOURCE_INP  = "//tr[td[text()='RESOURCE_NAME']]/td[1]/input";
	
	public static final String FORM_NAME_SETTINGS_CLM = "//th[@data-field='FormName']//a[1]/i";
	public static final String COLUMN_SETTINGS = "//th[@data-title='COLUMN_NAME']//a[i]/i";
	public static final String SELECT_VALUE_INP  = "//tr[td[text()='VALUE']]/td[1]/input";
	
	public static final String CALL_OUT_MENU = "//tr[1]//i[@class='fa fa-caret-square-o-down scs-callout-button']";
	public static final String REPROCESS_DDL = "//div[@class='cr-calloutContent']//span[text()='Reprocess']";
	public static final String REPROCESS_YES_BTN = "//div[@class='scs-modal-dialog-action-panel']/input[@value=' Yes']";
	
	public static final String COLUMN_SETTINGS_LNK = "//a[@class='k-link'][text()='COLUMNNAME']/preceding-sibling::a";
	public static final String POPUP_OPTION_MNU = "//div[contains(@class, 'column-menu')]//span[contains(text(),'POPUPOPTION')]";
	public static final String EXTRACT_DATA_POPUP_COLUMN_VALUE = "//*[@id='scs-dpt-filters-grid']//div[contains(@class,'k-grid-content')]//td[COLUMNINDEXNO]";
	public static final String POPUP_CLOSE_BTN = "//*[@class='k-window-actions']//span[contains(@class, 'close')]";
	public static final String POPUP_MODAL_TITLE = "//*[contains(@class,'k-window')]//span[contains(@class, 'title')]";
	public static final String POPUP_MODAL_MSG = "//*[contains(@class,'k-window')]//div[contains(@class, 'dialog-text')]";
	public static final String EXTRCT_DATA_COLUMN_NAMES_TBL = "//*[@id='scs-dpt-filters-grid']//table//th";
	public static final String IMPORT_POPUP_UPDATE_CHK = "//*[@id='scs-popup']//div[contains(.,'Update')]/input";
	public static final String IMPORT_POPUP_PARTIAL_CHK = "//*[@id='scs-popup']//div[contains(.,'Partial')]/input";
	public static final String DISABLED_RSRC_ICON = "//span[contains(text(), 'INSTANCE_NAME')]/following-sibling::i";
	public static final String IMPORT_INSPECT_ERRORS_DDL = "//div[@class='cr-calloutContent']//span[text()='Inspect errors']";
	public static final String INSPECT_ERRS_COLUMN_NAMES_TBL = "//*[@id='scs-dpt-inspect-error-grid']//table//th";
	public static final String INSPECT_ERRS_POPUP_COLUMN_VALUE = "//*[@id='scs-dpt-inspect-error-grid']//div[contains(@class,'k-grid-content')]//td[COLUMNINDEXNO]";
	public static final String INSPECT_ERRS_POPUP_CANCEL_BTN = "//div[@class='ui-dialog-buttonset']/button[text()='Cancel']";
	public static final String IMPORT_EXPORT_DOWNLOAD_DDL = "//div[@class='cr-calloutContent']//span[text()='Download']";
	public static final String GRID_FILTER_TXT = "//*[@class='k-filter-menu']//input[@title='Value']";
	public static final String GRID_FILTER_BTN = "//*[@class='k-filter-menu']//button[@type='submit']";
	public static final String EXPORT_CALL_OUT_MENU = "//tr[1]//i[@class='fa fa-caret-square-o-down scs-callout-button scs-button-display']";
	public static final String DATA_EXTRACT_POPUP_ROWS_TBL = "//div[@id='scs-dpt-filters-grid']//div[contains(@class, 'k-grid-content')]//tr";
	
	public static final String DATA_EXTRACT_PGN_PRVS_BTN = "//*[@id='scs-dpt-filters-grid']//div[contains(@class,'grid-pager')]/a[contains(@title,'previous')]";
	public static final String DATA_EXTRACT_PGN_NXT_BTN = "//*[@id='scs-dpt-filters-grid']//div[contains(@class,'grid-pager')]/a[contains(@title,'next')]";
	public static final String DATA_EXTRACT_PGN_FRST_BTN = "//*[@id='scs-dpt-filters-grid']//div[contains(@class,'grid-pager')]/a[contains(@title,'first')]";
	public static final String DPT_IMPORT_COLUMN_NAMES_TBL = "//*[@id='scs-data-provisioning-import-grid-container']//table//th";
	public static final String DPT_IMPORT_COLUMN_VALUE = "//*[@id='scs-data-provisioning-import-grid-container']//div[contains(@class,'k-grid-content')]//td[COLUMNINDEXNO]";

	
}


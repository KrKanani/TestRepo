package com.project.safetychain.webapp.pageobjects;

public class FSQABrowserDocumentsPageLocators {
	
	//Resource Selection
	public static final String OPEN_RESOURCE_DDL = "//div[@class='scs-hierarchy-dropdown']//span[@class='k-icon k-i-arrow-60-down']";
	public static final String SELECT_RESOURCE_DDL = "//*[@id='scs-product-dropdown_listbox']/li[text()='RESOURCE']";
	public static final String EXPAND_COLLAPSE_RESOURCE_CATEGORY = "//div[@id='scs-left-panel-treeview']//span[text()='RESOURCE_CATEGORY']/../span[contains(@class,\"k-icon\")]";
	public static final String RESOURCE_INSTANCE = "//div[@id='scs-left-panel-treeview']//span[text()='RESOURCE_INSTANCE']";
	public static final String UPLOAD_BUTTON = "//*[@class='k-button k-upload-button']/span";
	public static final String UPLOAD_INPUT ="//*[@id='scs-document-file-upload-input']";
	public static final String TOOLS_BUTTON = "//div[@id=\"scs-resources-document-grid\"]//td[text()=\"DOCUMENTNAME\"]/../td/i";
	public static final String TOOLS_OPTIONS = "//div[contains(@class,\"documentstab-menu\")]/div[contains(@class,\"calloutContent\")]//span[contains(text(),\"TOOLSOPTION\")]";
	public static final String UPLOADED_DOC = "//div[@id=\"scs-resources-document-grid\"]//td[contains(text(),\"DOCNAME\")]";	
	public static final String DOCUMENT_TYPE = "//div[@id=\"scs-edit-doc-dms-body-popup\"]//div[text()=\"Document Type\"]/..//span[contains(@class,\"k-icon k-i-arrow-60-down\")]";
	public static final String SELECT_DOCUMENT_TYPE = "//ul[@id=\"scs-document-type_listbox\"]/li[text()=\"DOCUMENTTYPE\"]";
	public static final String SAVE_BUTTON = "//button[text()=\"SAVE\"]";
	public static final String DOCUMENT_TYPE_COL_VALUE = "//div[@id=\"scs-resources-document-grid\"]//td[text()=\"DOCUMENTNAME\"]/../td[4]/span";
	public static final String INFO_ICON = "//div[@id=\"doc-data-action-icons\"]/ul/li[contains(@class,\"info-icon\")]/i[contains(@class,\"info\")]";
	public static final String DOC_DETAILS_TAB_NAME = "//ul[contains(@class,\"doc-action-items\")]//span[contains(text(),\"TABNAME\")]";
	public static final String CONTENT = "//div[@class='html_page_contents page-image chrome']";
	public static final String FILE_NAME_VALUE_DOC_DETAILS = "//div[@id=\"doc-details-container\"]//div[contains(@class,\"doc-detail\")]//div[contains(text(),\"File Name\")]/../div[contains(@class,\"doc-detail-right\")]";
	public static final String FIELD_VALUE_FORM_DETAILS = "//*[@id=\"scs-form-level\"]//field-template-view//label[contains(text(),\"FIELDNAME\")]/../../div[contains(@class,\"field-detail-right\")]/div[contains(@class,\"ng-binding\")]";
	public static final String VIEW_DOCUMENT_SECTION_TITLE = "//div[@id=\"menuBar\"]//div[text()=\"DOCUMENT WEB PREVIEW\"]";
	
	public static final String DOCUMENT_VERSION = "//div[@id='scs-resources-document-grid']//td[contains(text(),'DOCEMENT_NAME')]/following-sibling::td[5]";
	public static final String DOCUMENT_NAME = "//div[@id='scs-resources-document-grid']//td[text()='DOCUMENT_NAME']";
	public static final String DOC_VIEWER_CONTENT = "//div[contains(@class,'html_page_contents')]";
	public static final String DOC_VIEWER_LOADING_OVERLAY_MSG = "//div[@class='loading_overlay_message']";
	
	
	//EDIT DOCUMENT POP UP
	public static final String EDIT_DOC_POP_UP = "scs-edit-doc-dms-popup";
	public static final String EDIT_DOC_NAME = "//input[@id='scs-dms-popup-edit-doc-name']";
	public static final String EDIT_DOC_DESC = "//textarea";
	public static final String EDIT_DOC_EXPD = "//input[@id='scs-expiry-date-picker']";	

	//MANAGE LINKS POP UP
	public static final String MANAGE_LINKS_POP_UP = "scs-add-link-tabstrip-popup";
	public static final String SEARCH_MANAGE_POPUP = "//input[@class='scs-popup-search-input']";
	public static final String RES_TYPE_MANAGE_POPUP = "//li[@data-res-name='RESOURCE_TYPE']";
	public static final String RES_INST_TO_LINK = "//td[contains(text(),'INSTANCE_NAME')]/..//input";
	
	//UPLOAD NEW VERSION
	public static final String UPLOAD_NEW_POP_UP = "scs-upload-new-doc-dms-popup";
	public static final String UPLOAD_NEW_BROWSE = "//div[@id='scs-upload-new-doc-dms-body-popup']//input[@type='file']";	
	public static final String UPLOAD_NEW_FILE_NAME = "//div[@class='scs-new-upload-doc-name ng-binding']";
	public static final String UPLOAD_NEW_COMMENT = "//div[@class='scs-doc-popup-right']//textarea";
	public static final String UPLOAD_NEW_EXPD = "//input[@id='expirationDatePicker']";
	public static final String FILE_EXTENSION_MISMATCH_YES = "//div[@class='ui-dialog-buttonset']//button[@type='OK']";
	public static final String UPLOAD_NEW_UPLOAD_BTN = "//button[text()='UPLOAD']";
	
	//ASSIGN TASK
	public static final String ASSIGN_TASK_POP_UP ="scs-popup";
	public static final String ASSIGN_TASK_FIELDS = "//div[@class='scs-assign-task-left' and contains(text(),'FILED_NAME')]/../div[@class='scs-assign-task-right']//input"; 
	public static final String ASS_TSK_LOCATION = "//ul[@id='scs-task-assign-location_listbox']/li[text()='LOCATION']";
	public static final String ASS_TSK_WRKGP = "//ul[@class='k-list k-reset']/li[text()='WORKGROUP']";
	public static final String ASSIGN_BUTTON = "//button[text()='ASSIGN']";
	
	// FSQA->DOCUMENT VIEWER
	public static final String DOC_VIEWER_MANAGE_SECTION = "doc-links-container";
	
	//DOCUMENT DETAILS SECTION
	public static final String DOC_DETAILS_HEADER= "//div[@id='doc-data-container']//div[@class='doc-data-header']";
	public static final String DOC_INFO_SECTION = "doc-data-container";
	public static final String DOC_INFO_CROSS = "//div[@id='doc-data-container']/div[@class='doc-data-header']/span[@class='doc-data-panel-close']/i";
	//For Document Name
	public static final String DOC_DETAILS_DOC_NAME="//span[@class='scs-selected-doc-name']//span"; 
	// For type, Size, fileName, Description
	public static final String DOC_DETAILS_FIELD = "//div[contains(text(),'DETAIL_FIELD')]/following-sibling::div[@class='scs-doc-detail-right ng-binding']";
	// For Date
	public static final String DOC_DETAILS_EXPD = "//div[contains(text(),'EXP_DATE_FIELD')]/following-sibling::div[@class='scs-doc-detail-right']//span";
	//For Expiry - status
	public static final String DOC_DETAILS_EXPS = "//div[@class='scs-doc-detail-right']//sp";
	
	
	// Verticle toolbar options
	public static final String VERTICLE_TOOLBAR_OPTIONS = "//div[@id='doc-data-left-panel']//li[@class='doc-action-icon ng-scope'][OPTION]";
	
	// INFO ICON - > LINKS TAB
	//Manage Document Links Button
	public static final String MANAGE_DOC_LINKS_BTN = "//input[@value='Manage Document Links']";
	//Links
	public static final String LINKED_INSTANCES = "//div[@id='doc-links-container']//li//span[contains(text(),'INSTANCE_NAME')]";
	
	// INFO ICON - > Tabs verify
	public static final String TABS_CONTAINER = "//div[@id='doc-data-container']/div[@class='k-tabstrip-wrapper']/div";
	
	public static final String SELECTED_DOCS_GRID_LST = "//*[@id='scs-resources-document-grid']//div[contains(@class,'k-grid-content')]//tr";
	
	public static final String ATTCHMNT_FIELD_VAL = "//label[text()='FIELD_NAME']/ancestor::div[contains(@class,'scs-field-view')]//label[contains(text(),'Attachment')]/../following-sibling::div/span";
	public static final String HEADER_DOC_LNK = "//*[@id='scs-header-level']//span[contains(@class,'open-doc-href')]";

}

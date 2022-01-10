package com.project.safetychain.webapp.pageobjects;

public class DocumentManagementPageLocators {
	
	
	public static final String PLUS_BUTTON = "//*[@id='scs-add-new-type-btn']//span[@class='fa fa-plus-circle']";
	public static final String DOC_TYPE_TEXTBOX = "//input[@class='scs-category-text scs-edit-mode-textbox ng-pristine ng-untouched ng-valid ng-empty' or @class='scs-category-text scs-edit-mode-textbox ng-untouched ng-valid ng-not-empty ng-dirty ng-valid-parse']";
	public static final String UPLOAD_BUTTON = "//*[@class='k-button k-upload-button']/span";
	public static final String UPLOAD_INPUT ="//*[@id='scs-document-file-upload-input']";
	public static final String UPLOADED_DOC = "//*[@id=\"scs-documents-grid\"]//td[text()='DOCNAME']";
	public static final String CONTENT = "//div[@class='html_page_contents page-image chrome']";
	public static final String DOWNLOAD_BTN = "//*[@id='doc-data-action-icons']//i[@class='fa fa-download']";
	public static final String DOCTYPE_SELECT = "//*[@id='scs-documentmgmt-left-panel']//span[contains(text(),'DOCTYPE')]";
	public static final String HEADER_ICONS = "//*[@class=\"scs-document-header-icons\"]/span[@k-content=\"TOOLTIP\"]";
	public static final String BROWSE_INPUT = "//*[@id='scs-upload-new-doc-dms-popup']//input[@k-options='uploadNewVersionOptions' and last()]";
	public static final String UPLOAD_BTN = "//button[contains(text(),'UPLOAD')]";
	public static final String VERSION_TD = "//*[@id='scs-documents-grid']//tr[td[text()='DOCUMENT']]/td[text()='2.0']";
	public static final String DELETE_YES_BTN = "//button[contains(text(),'Yes')]";
	public static final String SCSDRD_LIST = "//*[@aria-owns='scs-product-dropdown_listbox']//span[@class='k-select']";
	public static final String SCSDRD_SELECT = "//*[@id='scs-product-dropdown_listbox']//span[contains(text(),'DROPDOWN')]";
	public static final String RESTORE_YES_BTN = "//button[contains(text(),'YES')]";
	public static final String TASKNAME_INPUT = "//*[@id='scs-popup']//input[@ng-model='assignTaskInfo.taskName']";
	public static final String ASSIGN_BTN = "//button[contains(text(),'ASSIGN')]";	
	public static final String TABS_OPTIONS = "//*[@class='k-tabstrip-items k-reset']//li";
	public static final String GRID_VALUE_SELECTION = "//*[@class='scs-popup-data-container k-content k-state-active']//table[@class='k-selectable']//tr[1]/td[1]/input";
	public static final String SAVE_BTN = "//button[contains(text(),'SAVE')]";
	public static final String DOCUMENT_NAME_COLUMN = "//*[@data-title='Document Name']//i[@class='fa fa-chevron-down']";
	public static final String FILTER_SELECT = "//li[@role='menuitem'and contains(.,'Filter')]//span[@class='k-icon  k-i-arrow-60-right']";
	public static final String FILTER_INPUT = "//*[@title='Show items with value that:']//input";
	public static final String FILTER_BTN = "//button[contains(text(),'Filter')]";
	public static final String DOCUMENTNAME_COLUMNDATA = "//*[@class='k-grid-content k-auto-scrollable']//td[contains(text(),'DOCUMENT')]";

	public static final String SEARCH_BOX = "scs-dms-tree-search-input";
	public static final String SEARCH_RESULT = "//ul[@id='scs-dms-tree-search-input_listbox']/li[1]/span[@class='scs-node-flattened-name' and contains(text(),'SEARCH_RESULT')]";
	
	//=================== Document Header =============================//
	//Document Type Name
	public static final String DOCUMENT_TYPE_NAME = "//div[@class='scs-document-header-title']/span[1]";
	//Manage Document Type
	public static final String MANAGE_DOC_TYPE_GEAR_BTN = "//span[contains(@class,'scs-document-settings')]/i";
	public static final String MANAGE_DOC_TYPE_OPTIONS = "//ul[@class='scs-menu']/li/span[contains(text(),'OPTION')]";
	public static final String DOC_NEW_NAME = "//input[contains(@class,'scs-edit-mode-textbox')]";
	
	//Document Enable/Disable dialogue (comes after clicking disable/enable option from Manage Doc Type(Gear Button))
	public static final String YES_BTN = "//button[contains(text(),'YES')]";
	public static final String CANCEL_BTN = "//button[contains(text(),'CANCEL')]";
	//Disable Dialogue
	public static final String DISABLE_DIALOG = "//div[@id='scs-popup']//div[contains(text(),'Disble Document Type')]";
	//Enable Dialogue
	public static final String ENABLE_DIALOG = "//div[@id='scs-popup']//div[contains(text(),'Enable Document Type')]";
	
	//================= Document Grid (where the documents are present) =========//
	public static final String DOCUMENT_VERSION = "//div[@id='scs-documents-grid']//td[contains(text(),'DOCEMENT_NAME')]/following-sibling::td[4]";
	public static final String DOCUMENT_NAME = "//div[@id='scs-documents-grid']//td[text()='DOCUMENT_NAME']";
	public static final String ALL_DOC_DETAILS_FROM_GRID = "//*[@class='k-grid-content k-auto-scrollable']//td[contains(text(),'DOCUMENT_NAME')]/following-sibling::td";
	
	// Files Upload Section
	public static final String FILES_UPLOAD_SECTION = "//div[@id='scs-document-files-upload']";
	
	//================ Document Toolbar Pop Ups  =======================//
	//EDIT Details POP UP
	public static final String EDIT_DOC_POP_UP = "scs-edit-doc-dms-popup";
	public static final String EDIT_DOC_NAME = "//input[@id='scs-dms-popup-edit-doc-name']";
	public static final String EDIT_DOC_TYPE = "//div[@id=\"scs-edit-doc-dms-body-popup\"]//div[text()=\"Document Type\"]/..//span[contains(@class,\"k-icon k-i-arrow-60-down\")]";
	public static final String SELECT_DOCUMENT_TYPE = "//ul[@id=\"scs-document-type_listbox\"]/li[text()=\"DOCUMENTTYPE\"]";
	public static final String EDIT_DOC_DESC = "//textarea";
	public static final String EDIT_DOC_EXPD = "//input[@id='scs-expiry-date-picker']";	
	
	//ALL POP UP Dialogue Title
	public static final String POPUP_TITLE = "//span[@class='ui-dialog-title']";
	
	//MANAGE LINKS POP UP
	public static final String MANAGE_LINKS_POP_UP = "scs-add-link-tabstrip-popup";
	public static final String SEARCH_MANAGE_POPUP = "//input[@class='scs-popup-search-input']";
	public static final String RES_TYPE_MANAGE_POPUP = "//li[@data-res-name='RESOURCE_TYPE']";
	public static final String RES_INST_TO_LINK = "//td[contains(text(),'INSTANCE_NAME')]/..//input";
	public static final String GRID_FIRST_ROW = "//*[@class='scs-popup-data-container k-content k-state-active']//table[@class='k-selectable']//tr[1]/td";
		
	//UPLOAD NEW VERSION
	public static final String UPLOAD_NEW_POP_UP = "scs-upload-new-doc-dms-popup";
	public static final String UPLOAD_NEW_BROWSE = "//div[@id='scs-upload-new-doc-dms-body-popup']//input[@type='file']";	
	public static final String UPLOAD_NEW_FILE_NAME = "//div[@class='scs-new-upload-doc-name ng-binding']";
	public static final String UPLOAD_NEW_COMMENT = "//div[@class='scs-doc-popup-right']//textarea";
	public static final String UPLOAD_NEW_EXPD = "//input[@id='expirationDatePicker']";
	public static final String FILE_EXTENSION_MISMATCH_YES = "//div[@class='ui-dialog-buttonset']//button[@type='OK']";
	public static final String UPLOAD_NEW_UPLOAD_BTN = "//button[text()='UPLOAD']";
	
	//ASSIGN TASK
	public static final String ASSIGN_TASK_FIELDS = "//div[@class='scs-assign-task-left' and contains(text(),'FILED_NAME')]/../div[@class='scs-assign-task-right']//input"; 
	public static final String ASS_TSK_LOCATION = "//ul[@id='scs-task-assign-location_listbox']/li[text()='LOCATION']";
	public static final String ASS_TSK_WRKGP = "//ul[@class='k-list k-reset']/li[text()='WORKGROUP']";
	public static final String ASSIGN_BUTTON = "//button[text()='ASSIGN']";
	
	
	//================ DMS -> DOCUMENT VIEWER =======================//
	public static final String DOC_VIEWER_CONTENT = "//div[contains(@class,'html_page_contents')]";
	public static final String DOC_VIEWER_LOADING_OVERLAY_MSG = "//div[@class='loading_overlay_message']";
	
	public static final String INFO_ICON = "//div[@id=\"doc-data-action-icons\"]/ul/li[contains(@class,\"info-icon\")]/i[contains(@class,\"info\")]";
	public static final String DOC_VIEWER_MANAGE_SECTION = "doc-links-container";
	
	//DOCUMENT DETAILS SECTION (Info Icon Clicked)(Details Tab)
	public static final String DOC_DETAILS = "doc-data-container";
	public static final String DOC_DETAILS_HEADER = "//div[@id='doc-data-container']//div[@class='doc-data-header']";
	public static final String DOC_DETAILS_TAB_NAME = "//ul[contains(@class,\"doc-action-items\")]//span[contains(text(),\"TABNAME\")]";
	public static final String DOC_DETAILS_CROSS = "//div[@id='doc-data-container']/div[@class='doc-data-header']/span[@class='doc-data-panel-close']/i\"";
	//For Document Name
	public static final String DOC_DETAILS_DOC_NAME="//span[@class='scs-selected-doc-name']//span"; 
	// For type, Size, fileName, Description
	public static final String DOC_DETAILS_FIELD = "//div[contains(text(),'DETAIL_FIELD')]/following-sibling::div[@class='scs-doc-detail-right ng-binding']";
	// For Date
	public static final String DOC_DETAILS_EXPD = "//div[contains(text(),'EXP_DATE_FIELD')]/following-sibling::div[@class='scs-doc-detail-right']//span";
	//For Expiry - status
	public static final String DOC_DETAILS_EXPS = "//div[@class='scs-doc-detail-right']//sp";
	
	//Document Details History Tab
	public static final String HIS_TAB_DETAIL_FIELDS = "(//div[contains(@class,'scs-det-left-panel')]/span[contains(text(),'FIELD')]/../following-sibling::div/span[1])[1]";
	public static final String HIS_TAB_FILE_NAME = "(//span[@class='document-link ng-scope'])[1]";
	public static final String HIS_TAB_MODIFIED_BY = "(//div[contains(@class,'scs-det-left-panel')]/span[contains(text(),'Modified By')]/../following-sibling::div/span)[1]";
	
	// Verticle toolbar  options
	public static final String VERTICLE_TOOLBAR_OPTIONS = "//div[@id='doc-data-left-panel']//li[@class='doc-action-icon ng-scope'][OPTION]";
		
	// INFO ICON - > LINKS TAB
	//Manage Document Links Button
	public static final String MANAGE_DOC_LINKS_BTN = "//input[@value='Manage Document Links']";
	//Links
	public static final String LINKED_INSTANCES = "//div[@id='doc-links-container']//li//span[contains(text(),'INSTANCE_NAME')]";
	
	// INFO ICON - > Tabs verify
	public static final String TABS_CONTAINER = "//div[@id='doc-data-container']/div[@class='k-tabstrip-wrapper']/div";
	
	public static final String DOCS_GRID_LST = "//*[@id='scs-left-panel-treeview']//li";
	
	// == Questionnaire Document Details ====
	public static final String FIELD_REQUIRED_ASTERISK = "//label[contains(text(),'FIELD_NAME')]/span[@class='scs-required-field']";
	public static final String FIELD_HINT = "//label[contains(text(),'FIELD_NAME')]/../../following-sibling::div[@class='hintText ng-scope']/div";
	public static final String FIELD_COMMENT = "//label[contains(text(),'FIELD_NAME')]//following::div[@ng-bind='field.Comment']";
	public static final String FIELD_ATTACHMENTS_NAME = "//label[contains(text(),'FIELD_NAME')]//following::span[@class='scs-form-level-attachment ng-scope']//a";
	public static final String FIELD_CORRECTION = "//label[contains(text(),'FIELD_NAME')]//following::div[contains(@ng-bind,'Correction')]";
	public static final String FIELD_RESOLVED = "//label[contains(text(),'FIELD_NAME')]//following::div[@ng-bind='field.ResolvedText']";

	public static final String SEARCH_MANAGELINK = "//div[9]/div[3]//div[contains(@class,'search')]/input";
	
	public static final String CUSTOMERS_MANAGELINK = "//div[@id='scs-add-link-tabstrip-popup']//span[text()='Customers']";
	public static final String ITEM_MANAGELINK = "//div[@id='scs-add-link-tabstrip-popup']//span[text()='Items']";
	public static final String SUPPLIERS_MANAGELINK = "//div[@id='scs-add-link-tabstrip-popup']//span[text()='Suppliers']";
	public static final String EQUIPMENTS_MANAGELINK = "//div[@id='scs-add-link-tabstrip-popup']//span[text()='Equipment']";
	public static final String LOCATIONS_MANAGELINK = "//div[@id='scs-add-link-tabstrip-popup']//span[text()='Locations']";
	public static final String USERS_MANAGELINK = "//div[@id='scs-add-link-tabstrip-popup']//span[text()='Users']";
	public static final String SAVE_MANAGELINK = "//button[text()='SAVE']";
	public static final String TOTAL_DOCUMENT_LIST = "//*[@id='scs-documents-grid']//tbody/tr";
	
}

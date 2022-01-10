package com.project.safetychain.webapp.pageobjects;

public class ProgramDesignerPageLocators {

	/**id*/
	
	public static final String FRMS_SELECT_ALL_CHK = "scs-grid-select-all";
	
	/**className*/

	/**xpath*/
	public static final String ADD_PROGRAM_LNK = "//*[@id='scs-programviews-list-view-data']//i[@class='fa fa-plus-circle']";
	public static final String ADD_PROGRAM_TXT = "//*[@id='scs-programviews-list-view-data']//input[contains(@class,'view-name-input')]";
	public static final String CREATE_ELEMENT_OPTN = "//*[@id='{{settings.treeId}}_tv_active']//div[contains(@class,'scs-treeview-item ng-scope')]/i";	
	public static final String CREATE_PROGRAM_ELEMENT = "//div[contains(@class,'cr-calloutContent')]//ul//span[contains(text(),'Add Program Element')]";
	public static final String CREATE_PROGRAM_HEADER = "//div[contains(@class,'cr-calloutContent')]//ul//span[contains(text(),'Add Heading')]";
	public static final String HEADER_EXPAND = "//*[@id='{{settings.treeId}}_tv_active']//span[contains(@class,'k-icon k-i-expand')]";
	public static final String EXPAND_PROG_NODE = "//*[@id='{{settings.treeId}}_tv_active']//span[contains(text(),'ELEMENTNAME')]/preceding::span[contains(@class,'expand')]";
//	public static final String EXPAND_COLLAPSE_NODE = "//*[@id='{{settings.treeId}}_tv_active']//span[contains(text(),'ELEMENTNAME')]/ancestor::span/preceding-sibling::span";
	
	public static final String PROGRAMS_DDL = "//*[@id='scs-program-viewer-dropdown']/option[text()='PROGRAMNAME']";
	public static final String PROGRAM_ELEMENT_TXT = "//*[@id='{{settings.treeId}}_tv_active']//input[@type='text']";
	public static final String SEARCH_PROGRAM_TXT = "scs-list-view-search-input";
	public static final String SEARCH_PRGM_LST = "//*[@id='scs-list-view-search-input_listbox']/li[@class='k-item']";
	public static final String SELCTD_PROG_ELE_NAME = "//*[@id='{{settings.treeId}}_tv_active']//span[contains(text(),'ELEMENTNAME')]";
	
	public static final String DETAILS_TAB_FRA = "//iframe[contains(@title,'Editable area. Press F10 for toolbar.')]";
	public static final String DETAILS_TAB_TXT = "/html/body";
	public static final String DETAILS_TAB_SAVE_BTN = "//*[@id='scs-details-save-btn']";
	
	public static final String DOCUMENTS_TAB = "//*[@id='tabstrip']//li[contains(@data-res-name,'Documents')]/span[2]";
	public static final String DOCUMENTS_TYPE_CHK = "//*[@id='scs-pd-doc-type-grid']//table//td[text()='DOCUMENTTYPE']//ancestor::tr/td[1]/input";
	public static final String DOCUMENT_TYPE_SAVE_BTN = "scs-dm-doctype-save-btn";	
	public static final String DOCUMENT_TYPE_CANCEL_BTN = "//*[@id='scs-dm-doctype-cancel-btn']";
	
	public static final String DOCUMENTS_SUB_TAB = "//*[@id='docs']/span[contains(text(),'Documents')]";
	public static final String DOCUMENTS_SELECT_NEW_TAB = "//*[(@id='scs-programviews-link-btn')  and (@value='SELECT NEW')]";
	public static final String SELECT_DOCUMENTS_CHK = "//*[text()='DOCUMENTNAME']/preceding-sibling::td/input[@type='checkbox']";	
	public static final String DOCUMENTS_SAVE_BTN = "//div[@class='ui-dialog-buttonset']/button[text()='SAVE']";
	public static final String CANCEL_BTN = "//div[@class='ui-dialog-buttonset']/div[text()='Cancel']";
	
	
	public static final String FORMS_TAB = "//*[@id='tabstrip']//li[contains(@data-res-name,'Forms')]/span[2]";
	public static final String FORMS_CHK = "//*[@id='scs-pd-forms-grid']//table//td[text()='FORMSNAME']//ancestor::tr/td[1]/input";
	public static final String FORMS_SAVE_BTN = "//*[@id='scs-forms-save-btn']";	
	public static final String FORMS_CANCEL_BTN = "//*[@id='scs-forms-cancel-btn']";
	public static final String FORMS_LST = "//*[@id='scs-program-viewer-forms-grid']//div[contains(@class,'k-grid-content k-auto-scrollable')]/table//tr";
	
	//Pagination for Documents
	
	public static final String DOCS_NXT_PAGE= "//*[@id='scs-pd-doc-grid']//a[contains(@title,'Go to the next page')]/span";
	public static final String DOCS_PRE_PAGE= "//*[@id='scs-pd-doc-grid']//a[contains(@title,'Go to the previous page')]/span";
	public static final String DOCS_FRST_PAGE= "//*[@id='scs-pd-doc-grid']//a[contains(@title,'Go to the first page')]/span";
	public static final String DOCS_PAGINATION_DDL= "//*[@id='scs-pd-doc-grid']//span[contains(@class,'k-widget k-dropdown k-header')]//span[@class='k-select']/span";
	public static final String DOCS_PAGE_DDL= "//*[@id='scs-pd-doc-grid']//select[@data-role='dropdownlist']/option";
	public static final String DOCS_GRID= "//*[@id='scs-pd-doc-grid']//div[contains(@class,'k-grid-content k-auto-scrollable')]//table[@role='grid']//tr";
	public static final String DOCS_NXT_PG_CHK= "//*[@id='scs-pd-doc-grid']//a[@title='Go to the next page']";
	
	
	//Delete Documents
	public static final String SELCTD_DOC_LST= "//div[@id='scs-pd-doc-tabstrip-2']/ul/li";
	public static final String SELCTD_DOC_NAME= "//*[@id='scs-pd-doc-tabstrip-2']//span[text()='DOCNAME']";
	public static final String REMV_SELCTD_DOC= "//*[@id='scs-pd-doc-tabstrip-2']//span[text()='DOCNAME']/..//i[contains(@class,'scs-doc-remove')]";
	public static final String POPUP_CONFRM_BTN= "//div[contains(@class,'ui-dialog-buttons')]//div[contains(@class,'ui-dialog-buttonset')]/button[text()='YES']";
	
	public static final String SELCT_DOCS_GRID_COLUMNS = "//*[@id='scs-pd-doc-grid']//table//th";
	public static final String COLUMN_SETTINGS_LNK = "//table//a[@class='k-link'][text()='COLUMNNAME']/preceding-sibling::a";
	public static final String POPUP_OPTION_MNU = "//ul[contains(@class, 'k-menu-vertical')]/li[contains(@class, 'k-item')]//span[text()='POPUPOPTION']";
	public static final String FILTER_TXT = "//*[@class='k-filter-menu']//input[@title='Value']";
	public static final String FILTER_BTN = "//*[@class='k-filter-menu']//button[@type='submit']";
	public static final String SELCTD_DOCS_NAME = "//div[@id='scs-pd-doc-tabstrip-2']/ul/li[COUNT]/span";
	public static final String VIEWREQUIREMENTLST = "//div[contains(@id,'scs-manage-supplier-grid-container')][contains(.,'SUPPLIERNAME')]";

	public static final String FRMS_SEARCH_TXT = "//div[@class='program-element-forms']//input[@id='filterText']";
	public static final String FRMS_SEARCH_BTN = "//div[@class='program-element-forms']//div[@id='filterDataSource']";
	public static final String HEADING_NAME = "//*[@id='scs-program-views-treeview']/ul/li/ul/li/div/span[2]/div/div//span[contains(text(),'HEADINGNAME')]";
	
}



package com.project.safetychain.webapp.pageobjects;

public class ProgramViewerPageLocators {

	/** id */
	public static final String PRGRMS_RECORD_ALL_CHK = "scs-grid-select-all";
	public static final String PROGRAMS_RCRDS_VIEW_BTN = "scs-view-records-btn";
	
	/** className */

	/** xpath */
	
	public static final String PROGRAM_NAME_ON_DDL = "//*[@id='scs-program-viewer-left-panel']//span[contains(@class,'k-widget k-dropdown k-header')]";
	public static final String SELECT_PROGRAMS = "//*[@id='scs-program-viewer-left-panel']//span[@class='k-select']/span";
	public static final String PROGRAMS_DOCUMENTS_TAB= "//*[@id='scs-tab-strip']//div[contains(text(),'Documents')]";
	public static final String DOCUMENT_LST = "//*[@id='scs-program-viewer-docs-grid']//table[@class='k-selectable']//tr";
	public static final String DOCS_COLUMN_NAMES_TBL = "//*[@id='scs-program-viewer-docs-grid']//table//th";
	public static final String FORMS_COLUMN_NAMES_TBL = "//*[@id='scs-program-viewer-forms-grid']//table//th";
	public static final String RECS_COLUMN_NAMES_TBL = "//*[@id='scs-program-viewer-records-grid']//table//th";
	public static final String DOCS_GRID_DATA = "//*[@id='scs-program-viewer-docs-grid']//table[@class='k-selectable']//td[COLUMNINDEXNO]";
	public static final String FORMS_GRID_DATA = "//*[@id='scs-program-viewer-forms-grid']//table[@class='k-selectable']//td[COLUMNINDEXNO]";
	public static final String RECORDS_GRID_DATA = "//*[@id='scs-program-viewer-records-grid']//table[@class='k-selectable']//td[COLUMNINDEXNO]";
	public static final String PROGRAMS_FORMS_TAB= "//*[@id='scs-tab-strip']//div[contains(text(),'Forms')]";

	public static final String VIEW_DETAILS_OPTN = "//*[@id='scs-program-viewer-right-panel']/button/i[contains(@class,'fa-circle-thin')]";
	public static final String VIEW_PROGRAM_NAME = "//*[@id='scs-program-viewer-window']//div[contains(@class,'scs-pv-details-program-name')]";
	public static final String VIEW_PRGRM_DETAILS = "//*[@id='scs-program-viewer-window']//div[contains(@class,'program-details')]";

	public static final String PROGRAMS_RECORDS_TAB = "//*[@id='scs-tab-strip']//div[contains(text(),'Records')]";
	public static final String COLUMN_SETTINGS_LNK = "//table//a[@class='k-link'][text()='COLUMNNAME']/preceding-sibling::a";
	public static final String POPUP_OPTION_MNU = "//ul[contains(@class, 'k-menu-vertical')]/li[contains(@class, 'k-item')]//span[text()='POPUPOPTION']";
	public static final String FILTER_TXT = "//*[@class='k-filter-menu']//input[@title='Value']";
	public static final String FILTER_BTN = "//*[@class='k-filter-menu']//button[@type='submit']";
	public static final String FORM_SELECTION = "//div[@id='scs-program-viewer-forms-grid']//td[text()='FORM_NAME']";
	public static final String RECORD_SELECTION = "//*[@id='scs-program-viewer-records-grid']//td[text()='FORM_NAME']";
	public static final String FORM_OPENED = "//*[@id='form-editor-panel']//div[@class='scs-form-title ng-binding' and text()='FORM_NAME']";
	public static final String FORM_CLOSE = "//*[@id='scs-close-form-button']";
	public static final String RECORDS_IN_HEADER = "//*[@id='breadCrumb']//a[text()='Records']";
	public static final String SEARCH_TEXT = "filterText"; 
	public static final String PE_SEARCH_RESULT = "//ul[@id='filterText_listbox']/li[@class='k-item']/span[@class='scs-node-flattened-name' and contains(text(),'PENAME')]"; 
	
	
}

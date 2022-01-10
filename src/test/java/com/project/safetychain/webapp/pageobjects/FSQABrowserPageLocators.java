	package com.project.safetychain.webapp.pageobjects;

public class FSQABrowserPageLocators {

	/**id*/
	public static final String VIEW_RECORDS_BTN = "scs-view-records-btn";
	public static final String SIGN_RECORDS_BTN = "scs-sign-records-btn";
	public static final String SELECT_ALL_CHK = "scs-grid-select-all";
	public static final String UPDATE_DUEBY_INPUT = "scs-assign-task-datepicker";
	public static final String DATE_RANGE_CALNDR_BTN = "scs-date-range-filter-menu";
	public static final String DATE_RANGE_FROM_TXT = "scs-recorddatetimepicker-from";
	public static final String DATE_RANGE_TO_TXT = "scs-recorddatetimepicker-to";
	
	
	/**className*/
	public static final String DATE_RANGE_CLEAR_LNK = "scs-recorddatetimepicker-clear";
	
	/**xpath*/
	public static final String NAVIGATION_TABS = "//*[@id='scs-tab-strip']//div[contains(@class,'tabItem ng-scope')]";
	public static final String ACTIVE_TAB = "//*[@id='scs-tab-strip']//div[contains(@class,'tabItem ng-scope')][text()='TABNAME']";
	public static final String COLUMN_SETTINGS_LNK = "//table//a[@class='k-link'][text()='COLUMNNAME']/preceding-sibling::a";
	public static final String GRID_COLUMNS = "//table//th[contains(@class,'k-header')]";
	public static final String POPUP_OPTION_MNU = "//ul[contains(@class, 'k-menu-vertical')]/li[contains(@class, 'k-item')]//span[text()='POPUPOPTION']";
	public static final String FILTER_TXT = "//*[@class='k-filter-menu']//input[@title='Value']";
	public static final String FILTER_BTN = "//*[@class='k-filter-menu']//button[@type='submit']";
	public static final String CLEAR_BTN = "//*[@class='k-filter-menu']//button[@type='reset']";
	public static final String GRID_COLUMN_VALUE = "//div[contains(@class, 'k-grid-content')]//table//td[COLUMNINDEXNO][not(contains(@class,'scs-saved-form-link'))]";
	public static final String DROPDOWN_ARROW = "//*[@class='scs-hierarchy-dropdown']//span[@class='k-select']";
	public static final String DROPDOWN_LST = "//*[@class='k-animation-container']//li[contains(text(),'RESOURCES')]"; 
	public static final String FORM_ROW = "//*[@id=\"scs-forms-grid\"]/div[2]//tr/td[1]";
	public static final String CALLOUT_MNU = "//*[@class='k-grid-content k-auto-scrollable']//td/i";
	public static final String EDIT_FORM_MNU = "//*[@class='cr-calloutContent']//span[contains(.,'Edit Form')]";
	public static final String ASSIGN_TASK_MNU = "//*[@class='cr-calloutContent']//span[contains(.,'Assign Tasks')]";  
	public static final String GRID_RECORD_CHK = "//*[@id='scs-record-grid']//table//td[text()='TASKPARAMETER']/..//input";	
	public static final String YES_BTN = "//button[contains(.,'YES')]";
	public static final String CALLOUT_BTN = "//*[@id='scs-resources-document-grid']//tbody/tr[1]//i[@class='fa fa-caret-square-o-down scs-callout-button scs-button-display']";
    public static final String MANAGE_LINK_BTN = "//*[@class='cr-calloutContent']//span[contains(text(),'Manage Links')]";
    public static final String ASSIGN_BTN = "//button[contains(text(),'ASSIGN')]";
    public static final String TASK_ROW = "//*[@id='scs-tasks-grid']/div[2]//tr[td[contains(.,'TASKNAME')]]";
    public static final String RECALL_MNU = "//*[@class='cr-calloutContent']//span[contains(.,'Recall Task')]";
    public static final String RECALL_YES_BTN = "//button[contains(.,'Yes')]";
    public static final String SAVED_FORM_DGD = "//*[@id='scs-saved-forms-grid']//tr[td/text()='FORM_NAME'][1]";
	public static final String DELETE_BTN = "//*[@id='scs-saved-forms-grid']//tr[td/text()='FORM_NAME'][1]//span[@class='fa fa-trash-o']";
	public static final String FORM_TASKNAME_INPUT = "//*[@id='scs-popup']//input[@ng-model='assignTaskInfo.taskName']";
	public static final String TOTAL_FORM_LST = "//*[@id='scs-forms-grid']//tbody/tr";
	public static final String FIRST_FORM_DATA = "//*[@id='scs-forms-grid']//tbody/tr[1]/td[text()='FORM']";
	public static final String TOTAL_RECORD_LST = "//*[@id='scs-record-grid']//tbody/tr";
	public static final String FIRST_RECORD_DATA = "//*[@id='scs-record-grid']//tbody/tr[1]/td[text()='RECORD']";
	public static final String TOTAL_TASK_LST = "//*[@id='scs-tasks-grid']//tbody/tr";
	public static final String FIRST_TASK_DATA = "//*[@id='scs-tasks-grid']//tbody/tr[1]/td/span[text()='TASK']";
	public static final String RECORD_SIGN_ICON = "//*[@id='scs-record-grid']//i[contains(@class, 'scs-grid-signature-icon')]";
	public static final String RECORD_SIGNOFF_DETAILS = "//div[@id='scs-records-signoff-window']/div";
	public static final String RECORD_SIGNOFF_CLOSE_BTN = "//div[contains(@class,'k-window-titlebar')]//a[contains(@class,'Close')]";
	public static final String FORM_TASK_NOTES_INPUT = "//*[@id='scs-popup']//textarea";
	public static final String RECORDS_BREADCRUMB = "//*[@id='breadCrumb']//a[contains(.,'Records')]";
	public static final String DUEBY_INPUT = "//*[@id='scs-assigntask-datetime-picker']";
	
	public static final String CHNGE_DUE_BY_TASK_MNU = "//*[@class='cr-calloutContent']//span[contains(.,'Change Due')]";  
	public static final String UPDATE_TASK_NAME_TXT = "//*[@id='scs-popup']//input[contains(@class, 'scs-task-name')]";  
	public static final String SAVE_TASK_BTN = "//button[contains(text(),'SAVE')]";  
	public static final String REASSIGN_TASK_MNU = "//*[@class='cr-calloutContent']//span[contains(.,'Reassign Task')]";  
	public static final String REASSIGN_TASK_BTN = "//button[contains(text(),'REASSIGN')]";  
	public static final String RECORD_GRID_CHK = "//*[@id='scs-record-grid']//table//input[@class='scs-grid-row-checkbox']";  
	public static final String INSTANCE_SEARCH_RESULT = "//ul[@id='filterText_listbox']/li[@class='k-item']/span[@class='scs-node-flattened-name' and contains(text(),'INSTANCE_NAME')]";

	public static final String DATE_RANGE_CLEAR_BTN = "//*[@id='scs-records-daterangepicker']//button[contains(@class,'clear-record-filter')]";  
	public static final String DATE_RANGE_FILTER_BTN = "//*[@id='scs-records-daterangepicker']//button[contains(@class,'filter-record')]";  
	public static final String DATE_RANGE_FROM_LBL = "//span[@class='scs-date-range-heading'][contains(.,'From')]";  
	public static final String DATE_RANGE_TO_LBL = "//span[@class='scs-date-range-heading'][contains(.,'To')]";
	
	public static final String GRIDHEADERS = "//*[@id='scs-tasks-grid']//th/a[2]";
	public static final String COLUMN_LST = "//div[contains(@class,'k-grid-content k-auto-scrollable')]//td[COLUMN_INDEX]/span";
	public static final String DUEBYDATE_GRD = "//tr[td[span[contains(@class,'scs-expiry-tooltip scs-current-task')]]]/td/span[contains(.,'DATE')]";
	public static final String DIR_OBS_VERIFY_SAVE_BTN = "//*[@class= 'scs-verify-save-button']";
	
    public static final String TASK_ROW_DATE = "//*[@id='scs-tasks-grid']/div[2]//tr[td[contains(.,'TASKNAME')]]/td[contains(.,'UPDATEDATE')]";
    
    public static final String LOCATIONS_Tab = "//*[@id='scs-details-link-tab']//span[text()='Locations']";
    public static final String LOCATIONS_LISTS = "//td[@class='scs-relatedtolinks-display-resources']";
    public static final String NO_DATA_FOUND = "//*[@id='filterText-list']";
    public static final String SEARCH_TEXT = "filterText";
    public static final String TASK_TOOL_TIP = "//*[@id='scs-tasks-grid']/div[2]//tr[td[contains(.,'TASKNAME')]]//ancestor::span[contains(@class,'expiry-tooltip')]";
    public static final String TASK_UPLOAD = "//*[@id='scs-tasks-grid']/div[2]//tr[td[contains(.,'TASKNAME')]]//ancestor::span/i[contains(@class,'fa-upload')]";
    public static final String TOOL_TIP_CONTENT = "//div[@role='tooltip']/div[@class='k-tooltip-content']";
    
    public static final String FIRST_DOC_DATA = "//*[@id='scs-resources-document-grid']/div[2]/div[1]/table/tbody/tr/td[text()='DOCUMENT']";
    public static final String FORM_IN_SAVED_FORMS = "//*[@id='scs-saved-forms-grid']//td[text()='FORM']";
    
}

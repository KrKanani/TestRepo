package com.project.safetychain.webapp.pageobjects;

public class InboxPageLocators {
	/**id*/
	public static final String HIST_WINDOW_TITLE = "inbox-history-window_wnd_title";

	/**className*/
	public static final String TASK_HIST_DETAILS = "scs-inbox-task-event-item";

	/**xpath*/
	public static final String SEARCH_TXTBOX = "//*[@class='scs-inbox-search-container']//input[@placeholder='Search']";
	public static final String CLICK_SEARCH_BTN = "//*[@class='scs-inbox-search-container']//div[@class='scs-inbox-search-icon-container']";
	public static final String TASKNAME_COL_DATA = "//*[@class='k-grid-content k-auto-scrollable']//tr[td/*[text()='TASK']]";
	public static final String BROWSE_INPUT = "//*[@id='scs-upload-new-doc-dms-popup']//input[@k-options='uploadNewVersionOptions' and last()]";
	public static final String UPLOAD_BTN = "//button[contains(text(),'UPLOAD')]";
	public static final String CONTENT = "//div[@class='html_page_contents page-image chrome']";
	public static final String APPROVE_BTN = "//*[@id='scs-doc-view-approve-button']";
	public static final String YES_BTN = "//button[contains(text(),'YES')]";
	public static final String REJECT_BTN = "//*[@id='scs-doc-view-reject-button']";
	public static final String NOTE_INPUT = "//*[@id='scs-doc-rejection-note']";
	public static final String NOTE_BTN = "//button[contains(text(),'SEND')]";
	public static final String TOTAL_TASK_LST = "//*[@id='scs-inbox-grid-container']//tbody/tr";
	public static final String FIRST_TASK_DATA = "//*[@id='scs-inbox-grid-container']//tbody/tr[1]//b[text()='TASK']";

	public static final String HIST_ICON = "//span[@class='scs-inbox-task-hist-icon']/i";
	public static final String HIST_CLOSE_BTN = "//div[@class='k-window-actions']//i[text()='clear']";

	public static final String TASK_SAVE_ICON = "//*[@id='scs-inbox-grid-container']//tr[td/span[text()='TASK_NAME']]/td[7]//span[@class='scs-saved-icon']/i";

	public static final String DELETE_SAVED_TASK_ICON = "//*[@id='scs-delete-form-button']/span[@class='fa fa-trash-o']";
	public static final String DELETE_SAVED_FORM_POP_UP_YES_BTN = "//div[div/span[text()='Delete Saved Form']]//button[text()='YES']";
	public static final String REFRESH_BTN = "//*[@id='content']//span[contains(@class,'refresh-button')]";
	
	public static final String EDIT_ICON = "//*[@id='scs-inbox-grid-container']//td[contains(.,'TASK_NAME')]/..//td/span[contains(@class,'scs-doc-type-icon')]";
	public static final String CANCEL_BTN = "//*[@id='scs-cancel-form-button']";
	
	public static final String APPROVAL_FILTER_NAME ="//*[@id='filter-panel']//span/span[contains(.,'Approval')]";
	public static final String COMPLETE_FORM_FILTER_NAME ="//*[@id='filter-panel']//span/span[contains(.,'Complete Form')]";
	public static final String DOCUMENT_UPLOAD_FILTER_NAME ="//*[@id='filter-panel']//span/span[contains(.,'Document Upload')]";
	
	
	public static final String APPROVAL_TYPE_FILTER_CHECKBOX ="//*[@id='filter-panel']//span/span[contains(.,'Approval')]/../input[@type='checkbox']";
	public static final String COMPLETE_FORM_TYPE_FILTER_CHECKBOX ="//*[@id='filter-panel']//span/span[contains(.,'Complete Form')]/../input[@type='checkbox']";
	public static final String DOCUMENT_UPLOAD_TYPE_FILTER_CHECKBOX ="//*[@id='filter-panel']//span/span[contains(.,'Document Upload')]/../input[@type='checkbox']";
	
	public static final String WORK_GROUP_FILTER_CHECKBOX ="//*[@id='filter-panel']//span/span[contains(.,'WG_NAME')]/../input[@type='checkbox']";
	public static final String STATUS_FILTER_CHECKBOX ="//*[@id='filter-panel']//span/span[contains(.,'STATUS_TYPE')]/../input[@type='checkbox']";
	
	public static final String FILTER_BTN ="//*[@id='content']//div[contains(@class,'filter-button')]";
	public static final String CLEAR_BTN ="//*[@id='content']//div[contains(@class,'clear-button')]";
	
	public static final String PREV_BTN ="//*[@id='scs-inbox-grid-container']//a[contains(@title,'previous')]";
	public static final String NEXT_BTN ="//*[@id='scs-inbox-grid-container']//a[contains(@title,'next')]";
	public static final String FIRST_BTN ="//*[@id='scs-inbox-grid-container']//a[contains(@title,'first')]";
	public static final String LAST_BTN ="//*[@id='scs-inbox-grid-container']//a[contains(@title,'last')]";
	
	public static final String PAGE_DRPDWN ="//*[@id='scs-inbox-grid-container']//span[contains(@class,'k-widget k-dropdown')]//span[contains(@class,'k-select')]";
	public static final String PAGE_DRPDWN_OPTIONS ="//div[@class='k-animation-container']//li[text()='VALUE']";
	public static final String TASK_TOTAL ="//*[@id='content']//div[contains(@class,'task-grid-total')]";
	public static final String TASK_NAME ="//*[@id='scs-inbox-grid-container']//tr/td[3]/b[contains(.,'TASK_NAME')]";
	
	public static final String RESOURCE_NAME = "//*[@id='scs-inbox-grid-container']//tr/td[4]/span[contains(.,'RESOURCE_NAME')]";


	public static final String DUE_BY_FROM_TXTBOX = "//*[@id='filter-panel']/li[4]/ul/li/ul/li[1]/span/span/span/input";
	public static final String DUE_BY_TO_TXTBOX = "//*[@id='filter-panel']/li[4]/ul/li/ul/li[1]/span/span/span/input";
}

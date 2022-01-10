package com.project.safetychain.webapp.pageobjects;

public class TaskSchedulerPageLocators {

	/** id */
	public static final String RECALL_BTN = "scs-ts-details-bulk-recall";

	/** xpath */
	public static final String ALL_BTN = "//div[@id='scs-ts-All-toggle']//label[1]";
	public static final String BYDATE_BTN = "//div[@id='scs-ts-All-toggle']//label[2]";
	public static final String PREVIOUSDAY_BTN = "//body//div[@id='scs-ts-header-components']//div//div//button[1]";
	public static final String NEXTDAY_BTN = "//body//div[@id='scs-ts-header-components']//div//div//button[2]";
	public static final String DATEPICKER_BTN = "//div[@id='scs-ts-header-components']/div/div/span/span/span/span[1]";
	public static final String SCHEDULETASK_BTN = "//button[@id='scs-ts-schedule-task']";
	public static final String NEXT_BTN = "//span[contains(text(),'NEXT')]";
	public static final String BACK_BTN = "//span[contains(text(),'BACK')]";
	public static final String CANCEL_BTN = "//span[contains(text(),'CANCEL')]";
	public static final String SAVE_BTN = "//span[contains(text(),'SAVE')]";
	public static final String TASKEND_DATE = "//label[contains(text(),'Task has end date')]";
	public static final String INCREASEEVERY_BTN = "//*[@id=\"scs-ts-step3\"]/div[3]/div[1]/span[2]/span/span/span[1]/span";

	public static final String VIEWBYTASK_LINK = "//span[contains(text(),'View By Task')]";
	public static final String VIEWBYSCHEDULE_LINK = "//span[contains(text(),'View By Schedule')]";

	public static final String OPTION_DDL = "//div[@id='scs-ts-option-menu']";
	public static final String lOCATION_DDL = "//*[@id=\"scs-ts-step1\"]/div[2]/div[2]/single-select-dropdown-list/div/span/span/span[1]";
	public static final String LOCATION_GRD = "//li[contains(.,'LOCATION')]/div";

	public static final String SELECTFORM_DDL = "//*[@id=\"scs-ts-step1\"]/div[4]/div/div[2]/single-select-dropdown-list/div/span/span/span[1]";
	public static final String SELECTDOCUMENT_DDL = "//span[contains(text(),'Select Document')]";
	public static final String SELECTRESOURCE_DDL = "//span[contains(text(),'Select Resource')]";
	public static final String SELECTWORKGROUP_DDL = "//span[contains(text(),'Select Workgroup')]";
	public static final String TASKOCCURS_DDL = "//*[@id=\"scs-ts-step3\"]/div[2]/div[2]/single-select-dropdown-list/div/span/span/span[2]";
	public static final String TIMEZONE_DDL = "//form[@id='scs-ts-step3']/div/div/div/single-select-dropdown-list/div/span/span/span[1]";
	public static final String OCCURRED_VALUE = "//*[@id=\"scs-add-schedule-taskoccurs_listbox\"]/li//div[contains(text(),'occurrence')]";


	public static final String PROCESSFORM_RB = "//label[contains(text(),'Process Form')]";
	public static final String PROCESDOCUMENT_RB ="//label[contains(text(),'Process Document')]";
	//"//label[contains(text(),'Process Document')]/../input";
			

	public static final String TASKNAME_TXT = "//form[@id='scs-ts-step2']//div[2]//input[1]";
	public static final String TASKDESCRIPTION_TXT = "//form[@id='scs-ts-step2']//div//div//textarea";
	public static final String ASSIGNHOUR_TXT = "//*[@id=\"scs-ts-step3\"]/div[3]/div[3]/div[2]/span[1]/span/input[1]";
	public static final String ASSIGNMIN_TXT = "//*[@id=\"scs-ts-step3\"]/div[3]/div[3]/div[2]/span[3]/span/input[1]";
	public static final String LOCAIONSEARCH_TXT = "//*[@id=\"scs-add-schedule-location-list\"]/span/input";
	public static final String FORMSEARCH_TXT = "//*[@id=\"scs-add-schedule-form-list\"]/span/input";
	public static final String RESOURCESEARCH_TXT = "//*[@id='scs-add-schedule-Resource-list']/span/input";
	public static final String WORKGROUPSEARCH_TXT = "//*[@id='scs-add-schedule-Workgroup-list']/span/input";  //*[@id="scs-add-schedule-Workgroup-list"]/span/input 
	public static final String STEP3DUEDATETIME_TXT = "//input[@id='scs-ts-due-date-time']";
	public static final String TIMEZONESEARCH_TXT = "//*[@id=\"scs-add-schedule-timezone-list\"]/span/input";
	public static final String STEP3FIRSTDUEDATE_TXT = "//*[@id='scs-ts-due-date']";
	public static final String STEP3DAILYASSIGNHOUR_TXT = "//*[@id=\"scs-ts-step3\"]/div[3]/div[4]/div[2]/span[1]/span/input[1]";
	public static final String STEP3DAILYASSIGNMIN_TXT = "//*[@id=\"scs-ts-step3\"]/div[3]/div[4]/div[2]/span[3]/span/input[1]";
	public static final String STEP3_YEARLY_ENDDATE_INPUT = "//*[@id='scs-ts-step3']/div[3]/div[4]/div[2]/span/span/input[1]";
	public static final String STEP3TASKENDDATE_TXT = "//input[@id='scs-ts-end-date']";
	public static final String STEP3EVERY_TXT = "//span[2]//span[1]//input[1]";
	public static final String ENDDATE_TXT = "//input[@id='scs-ts-end-date']";
	public static final String TASK_DUE_TIME = "//*[@id='scs-ts-due-time']";
	public static final String STEP3DATE_MONTHLY = "//ul[contains(@class,'scs-common-timeview')]/li[./text()='duetimein24format']";
	public static final String DUE_TIME_INPUT = "//*[@id='scs-ts-step3']//div[@class='scs-ts-due-time-container']//input";
	public static final String INTERVAL_STARTDATE_INPUT = "//*[@id='scs-ts-interval-start-date']";
	public static final String INTERVAL_ENDDATE_INPUT = "//*[@id='scs-ts-interval-end-date']";
	public static final String INTERVAL_STARTTIME_INPUT = "//*[@id='scs-ts-interval-start-time']";
	public static final String INTERVAL_ENDDADTE_INPUT = "//*[@id='scs-ts-interval-end-time']";
	public static final String INTERVAL_DDL = "//*[@class='scs-ts-interval-unit-container scs-ts-interval-field-right']//span[@class='single-select-caret-down']";
	public static final String INTERVAL_INPUT = "//*[@class='k-animation-container']//input[@aria-owns='scs-add-schedule-Intervals_listbox']";

	public static final String STEP3CALENDAR_ICN = "//span[@class='k-link k-link-date']//span[@class='k-icon k-i-calendar']";
	public static final String STEP3TIME_ICN = "//span[@class='k-icon k-i-clock']";
	public static final String STEP3_TIME = "//*[@id=\"scs-ts-due-date-time_timeview\"]/li[contains(text(),'duetimein24format')]";
	public static final String CALENDARMAINPAGE = "//*[@id=\"scs-ts-header-components\"]/div[1]/div[3]/span";
	public static final String DATEPICCERMAINPAGE = "//div[@id='scs-ts-date-picker_dateview']";
	public static final String STEP3DAILYDUETIME = "//input[@id='scs-ts-due-date']";
	public static final String MAINPAGECALENDEA_ICN = "//span[@class='k-icon k-i-calendar']";
	public static final String HOMECALENDARMONTH = "//a[@class='k-link k-nav-fast']";
	public static final String CALENDARNEXT_BTN = "//span[@class='k-icon k-i-arrow-60-right']";
	public static final String CALENDAR_DATE = "//a[text()=\"DATE\"]";
	public static final String STEP3FIRSTDUEDATE_ICN = "//*[@id='scs-ts-step3']/div[3]/div[2]/span/span/span/span";
	public static final String STEP3ENDDATE_ICN = "//div[@class='scs-ts-end-date-picker ng-scope']//span[@class='k-select']";
	public static final String STEP3DATE_ICN = "//*[@id=\"scs-ts-due-time_timeview\"]//li[contains(text(),\"duetimein24format\")]";

	public static final String DUEDATE_ICN = "//*[@id='scs-ts-due-date']/ancestor::span[1]/span/span[contains(@class,'k-icon k-i-calendar')]";
	public static final String DAY_CHCKBX = "//label[./text()='DAY']";

	public static final String DUE_DATE = "//*[@id='scs-ts-due-date']";
	public static final String DUE_TIME = "//*[//*[@id='scs-ts-due-time']";

	public static final String NEWTASK_NAME = "//span[contains(text(),\"taskname\")]";
	public static final String DATE = "//*[@id=\"scs-ts-header-components\"]/div[1]/div[3]/label";
	public static final String DATELBL = "//*[@id='scs-ts-header-components']//label[contains(@class,'scs-ts-date-string ng-binding')]";
	public static final String ENDDATE_FLAG = "//label[contains(text(),'Task has end date')]";

	public static final String TASKSCHEDULE_DTR = "//*[@id='scs-ts-schedule-grid-container']//span[./text()='TASKNAME']/ancestor::tr/td";
	public static final String TASKSCHEDULEDONTASKTR = "//*[@id='scs-ts-task-grid-container']//span[contains(text(),'TASKNAME')]/ancestor::tr/td";
	public static final String SCHEDULEGRIDHEADERTR = "//*[@id='scs-ts-schedule-grid-parent']//th[contains(@role,'columnheader')]";
	public static final String TASKSGRIDHEADERTR = "//*[@id='scs-ts-task-grid-container']//th[contains(@role,'columnheader')]";
	public static final String WEEKLY_TAB = "//*[@id='scs-ts-daily-weekly-toggle']/label[contains(@ng-class,'weekly')]";
	public static final String DAILY_TAB = "//*[@id='scs-ts-daily-weekly-toggle']/label[contains(@ng-class,'daily')]";

	public static final String VIEW_BY_TASK_BTN = "//*[@id='scs-ts-header-components']//span[text()='View By Task']";
	public static final String SCHEDULE_SELECT = "//*[@class='k-grid-content k-auto-scrollable']//span[contains(.,'SCHEDULENAME')]";
	public static final String TASK_SELECT = "//*[@id='scs-ts-details-grid']/div[2]//tr[1]/td[1]";
	public static final String RECALLED_STATUS = "//*[@id='scs-ts-details-grid']//tr[contains(.,'Recalled')]";
	public static final String TASK_SCHEDULE_VIEW = "//*[@id='scs-ts-schedule-grid-container']//td[contains(.,'SCHEDULENAME')]";
	public static final String TASK_VIEW = "//*[@id='scs-ts-task-grid-container']//span[contains(.,'TASKNAME')]";
	public static final String GRID_LOCATION_DDL = "//span[@class='k-select']/span[@class='single-select-caret-down']";
	public static final String LOCATION_INPUT = "//*[@id='scs-task-scheduler-location-list']//input";
	public static final String SCHEDULE_VIEW = "//*[@id='scs-ts-schedule-grid-parent']//span[contains(.,'SCHEDULE')]";
	public static final String RANDOMIZE_INPUT = "//*[@id='IsRandom']";
	public static final String UPDATE_BTN = "//button[contains(.,'UPDATE')]";
	public static final String CALENDER_NEXT_BTN = "//button[@class='scs-ts-nav-next scs-ts-nav-btn']";
	public static final String VERIFY_EDITED_SCHEDULE = "//tr[td[contains(.,'SCHEDULE')]]/td[contains(.,'DATE')]";
	public static final String RECALL_POPUP_OK_BTN = "//div[contains(@class, 'k-window')]//input[contains(@value,'Ok')]";
	public static final String DELETE_BTN = "//*[@id='scs-ts-edit-schedule-buttons']/button[text()='DELETE']";
	public static final String EDIT_CANCEL_BTN = "//*[@id='scs-ts-edit-schedule-buttons']/button[text()='CANCEL']";

	public static final String TASK_STATUS = "//*[@id=\'scs-ts-schedule-grid-container\']//tr[td[contains(.,'TASKNAME')]]/td[contains(.,'COLUMNINDEXNO')]";
	public static final String TASK_STATUS1 = "//*[@id=\'scs-ts-task-grid-container']//tr[td[contains(.,'TASKNAME')]]/td[contains(.,'COLUMNINDEXNO')]";
	public static final String TASK_DUE_DATE = "//*[@id='scs-ts-task-grid-container']//tr[td[span[contains(.,'TASKNAME')]]]//td[contains(.,'DATEDUEBY')][2]";
	public static final String EVERY_DAY_UP_ARROW = "//*[@id='scs-ts-step3']//div[1]//span[@class='k-select']/span[1]/span";

	public static final String SELECT_DOCUMENT_DRPDWN="//*[@id=\'scs-ts-step1\']//single-select-dropdown-list//span[contains(@class,\'k-dropdown k-header k-invalid\')]/span[1]";
	public static final String SELECT_DOCUMENT_WORKGROUP_SEARCHBOX="//div[23]/div[@id='scs-add-schedule-Workgroup-list']//span/input";
	public static final String CALENDER_PREV_BTN="//button[@class='scs-ts-nav-back scs-ts-nav-btn']";
	
	public static final String SKIP_BTN="//*[@id='scs-ts-details-recall']";
	public static final String SKIPPED_STATUS="//*[@id='scs-ts-task-grid-container']//tr[contains(.,'STATUS')]";
	public static final String COMMENT_TXT_BOX="//*[@id='scs-ts-skip-task']/div/textarea";
	public static final String SKIP_POPUP_SKIP_BTN="//div[@class='scs-modal-dialog-action-panel scs-modal-base-action-panel']/input[2]";
	public static final String CLOSE_BTN="//*[@id='scs-ts-details-clsoe']";
	public static final String TASK_GRID_STATUS="//*[@id=\'scs-ts-details-grid\']/div[2]/table/tbody/tr[1]/td[contains(.,'STATUS')]";
	public static final String WORK_GROUP_EDIT_SEARCH_TEXT="//*[@id=\'scs-edit-schedule-Workgroup-list\']/span/input";
	public static final String WORK_GROUP_SEARCH_TEXT_SELECT="//*[@id='scs-edit-schedule-Workgroup_listbox']/li/div/div[contains(.,'WORKGROUP')]";
	
	public static final String SCHEDULE_VIEW_WORKGROUP = "//*[@id=\"scs-ts-task-grid-container\"]//tr[contains(.,'TASKNAME')]//td[contains(.,'WORKGROUPNAME')]";
	
	
}

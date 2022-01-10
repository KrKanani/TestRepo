package com.project.safetychain.webapp.pageobjects;

public class FSQABrowserTaskPageLocators {

	public static final String TASKHISTORY_MNU = "//div[@class='cr-calloutContent']//li[1]/span";
	public static final String HISTORY_ACTIONS_HEADING = "//*[@id='scs-task-history-container']//span[contains(@class,'scs-task-hist-event')][contains(.,'HEADER')]/parent::div//span";
	public static final String HISTORY_DUEBY = "//*[@id='scs-task-history-container']//span[contains(@class,'scs-task-hist-event')][contains(.,'HEADER')]/parent::div/following-sibling::div//span/span";
	public static final String CLOSE_POPUP = "//i[@class='material-icons scs-popup-close']";
	public static final String CALLOUT_MNU = "//*[@class='k-grid-content k-auto-scrollable']//td/i";
	public static final String TASK_ROW = "//*[@id='scs-tasks-grid']/div[2]//tr[td[contains(.,'TASKNAME')]]";
	public static final String TASK_STARTED = "//*[@id='scs-tasks-grid']/div[2]//tr[td[contains(.,'TASKNAME')]]/td[contains(.,'Yes')]";
	public static final String CARET = "//*[@id='scs-tasks-grid']/div[2]//tr[td[contains(.,'TASKNAME')]]//td[i[contains(@class,'caret')]]";
	public static final String CARET_DRPDWN = "//ul[@class='scs-menu']//span[text()='CARET_ITEM']";
	public static final String TASK_SUMMARY_TITLE = "//*[@id=\'scs-tasks-summary-container\']/div[1]";
	public static final String TASK_STATUS = "//*[@id=\'scs-tasks-analytical-chart\']/svg/g/g[2]/g/text";
	public static final String OUTER_DONUT = "//*[@id='scs-tasks-analytical-chart']//*[local-name() = 'svg']/*[local-name() = 'g']/*[local-name() = 'g'][5]/*[local-name() = 'g']/*[local-name() = 'g'][2]/*[local-name() = 'path'][1]";
	public static final String OUTER_DONUT1 = "//*[@id='scs-tasks-analytical-chart']/svg/g/g[5]/g/g[2]/path[2]";
	public static final String INNER_DONUT = "//*[@id=\'scs-tasks-analytical-chart\']/svg/g/g[5]/g/g[2]/path[2]";
	public static final String TOOL_TIP = "//div[@class=\'k-tooltip k-chart-tooltip\']";
	public static final String STARTED = "//*[@id='scs-tasks-analytical-chart']/svg/g/g[4]/g/g[1]/g/text";
	public static final String NON_STARTED ="//*[@id='scs-tasks-analytical-chart']/svg/g/g[4]/g/g[2]/g/text";
	public static final String PAST_DUE = "//*[@id='scs-tasks-analytical-chart']/svg/g/g[4]/g/g[3]/g/text";
	public static final String DUE_TODAY = "//*[@id='scs-tasks-analytical-chart']/svg/g/g[4]/g/g[4]/g/text";
	public static final String DUE_LATER = "//*[@id='scs-tasks-analytical-chart']/svg/g/g[4]/g/g[5]/g/text";
	public static final String NO_DUE_DATE ="//*[@id='scs-tasks-analytical-chart']/svg/g/g[4]/g/g[6]/g/text";
	public static final String DUE_BY ="//*[@id=\'scs-assign-task-datepicker\']";
	public static final String SAVE_BTN ="//div[@class=\'ui-dialog-buttonset\']/button[contains(.,'SAVE')]";
	
	
}

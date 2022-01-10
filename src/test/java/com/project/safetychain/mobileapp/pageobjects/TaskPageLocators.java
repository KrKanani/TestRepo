package com.project.safetychain.mobileapp.pageobjects;

public class TaskPageLocators {

	public static final String Form_Name = "//android.widget.TextView[@text='FormNameLocator']";

	// Task Filter

	public static final String FILTER_BTN = "handle";
	public static final String ASSIGNEDTO_BTN = "inboxFilterGroupView";   /*"assignedToBtn";*/
	public static final String STATUS_BTN = "statusBtn";
	public static final String STATUS_BTN1 = "//android.widget.Button[@text='STATUS']";

	public static final String DUEBY_BTN = "//android.widget.Button[@text='DUE BY']";
	public static final String CLEAR_BTN = "clearBtn";
	public static final String CAL_FILTER_BTN = "filterBtn";

	// Calender
	public static final String CURRENTMONTH_TXT = "currentMonth";
	public static final String NXT_MONTH_BTN = "nextMonth";
	public static final String DAY_TOBE_BTN = "//android.widget.Button[@text='DAY']";
	 
	// Status Filter

	public static final String STATUS_FILTER = "//android.widget.CheckBox[contains(@text,'STATUS')]";// Past Due ,Due
																										// Later
	// Status Filter
	public static final String ASSIGNTO_FILTER = "//android.widget.CheckBox[contains(@text,'WG')]";

	// Result Grid
	public static final String DUEDATE_GRID = "resDueDate";
	public static final String GROUPNAME_GRID = "groupName";

	// Limit Data
	public static final String LIMIT_DATA = "enablelogtoggle";
	public static final String LIMIT_DATA_DEFAULT = "limitdataselectedoption";
	public static final String DATA_OPTION_TOBE_SELECTED = "//android.widget.Button[@text='DAYS']";// 14 DAYS , 7 DAYS

	// Task

	public static final String TASK_SUBMIT = "//android.widget.Button[@text='SUBMIT']";

}

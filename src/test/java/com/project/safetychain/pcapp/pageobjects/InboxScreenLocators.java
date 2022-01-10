package com.project.safetychain.pcapp.pageobjects;

public class InboxScreenLocators {

	public static final String INBOX_SEARCH_TXT = "//*[@Name='Search Inbox']";
	public static final String SELECT_TASK = "//*[@ClassName='TextBlock' and @Name='TASK_NAME']";
	public static final String NEXT_BTN = "//*[@ClassName='Button' and @Name='NEXT']";

	public static final String TASK_ASSIGNED_TO = "//*[@Name='Assigned To :']/following-sibling::*[@ClassName='TextBlock']";
	public static final String TASK_RESOURCE = "//*[@Name='Resource :']/following-sibling::*[@ClassName='TextBlock']";
	public static final String INBOX_TASK_COUNT = "//*[@Name='Inbox']/following-sibling::*[@ClassName='TextBlock']";

	public static final String INBOX_REFRESH_BTN = "//*[@AutomationId='Inbox_Refresh' and @ClassName='Button']";
	public static final String INBOX_REFRESH_BTN1 = "//*[@Name='No Inbox Data to Display']/preceding-sibling::*[@ClassName='Button'][1]";
	public static final String REFRESH_YES_BTN = "//*[@ClassName='Button' and @Name='Yes']";
	public static final String SAVE_BTN = "//*[@ClassName='Button' and @Name='SAVE']";

	public static final String PAST_DUE_LBL = "//*[@ClassName='TextBlock' and @Name='Past Due']";
	public static final String DUE_TODAY_LBL = "//*[@ClassName='TextBlock' and @Name='Due Today']";
	public static final String DUE_LATER_LBL = "//*[@ClassName='TextBlock' and @Name='Due Later']";

	public static final String WORKGROUP_BTN = "//*[@AutomationId='Sort_Workgroup' and @Name='Workgroup']";
	public static final String STATUS_BTN = "//*[@AutomationId='Sort_Status' and @Name='Status']";
	public static final String DUEBY_BTN = "//*[@AutomationId='Sort_DueBy' and @Name='Due By']";

	public static final String WORKGROUP_SORT_DRD = "//*[@Name='Workgroup']/following-sibling::*[@AutomationId='ComboBoxOrderBy']";
	public static final String WORKGROUP_DRD_SELECT = "//*[@ClassName='TextBlock' and @Name ='WGNAME']";
	public static final String WORKGROUP_LBL = "//*[@ClassName='TextBlock' and @Name='WGNAME']";

	public static final String STATUS_SORT_DRD = "//*[@Name='Status']/following-sibling::*[@AutomationId='ComboBoxOrderBy']";
	public static final String DUEBY_SELECT = "//*[@AutomationId='SelectedDateText' and @ClassName='TextBlock']";
	public static final String DATE_SELECT = "//*[@ClassName='CalendarViewDayItem' and @Name='DATE']";

	public static final String PAST_DUE_SELECT = "//*[@ClassName='TextBlock' and @Name ='Past Due']/ancestor::*[@Name='Past Due']";
	public static final String DUE_TODAY_SELECT = "//*[@ClassName='TextBlock' and @Name ='Due Today']/ancestor::*[@Name='Due Today']";
	public static final String DUE_LATER_SELECT = "//*[@ClassName='TextBlock' and @Name ='Due Later']/ancestor::*[@Name='Due Later']";

	public static final String INBOX_LISTVIEW = "//*[@Name='No Inbox Data to Display']/following-sibling::*[@AutomationId='ListView']";
	public static final String INVALID_TASK_POPUP = "//*[@AutomationId='PrimaryButton']";
	public static final String INVALID_TASK = "//*[@Name='Invalid Task']";
	public static final String DELETE_BTN = "//*[@ClassName='Button' and @Name='DELETE']";
	public static final String YES_BTN = "//*[@AutomationId='PrimaryButton' and @Name='Yes']";

	public static final String INBOX_BACK_BTN = "//*[@Name='Inbox']/preceding-sibling::*[@ClassName='Button']";
	public static final String RESOURCELOCATION_BACK_BTN = "//*[@Name='Location/Resource Selection']/preceding-sibling::*[@ClassName='Button']";
	public static final String FORMS_POPUP = "//*[@AutomationId = 'SecondaryButton' and @Name='Discard']";
	public static final String PASSWORD_RESET_INPUT = "//*[@AutomationId = 'PasswordBox']";
	public static final String PASSWORD_INPUT_LBL = "//*[@Name = 'Please input your password']";
	public static final String LOGIN_BTN = "//*[@ClassName='Button' and @Name= 'LOGIN']";

	public static final String NO_TASK_LBL = "//*[@Name='No Inbox Data to Display']";

	public static final String INBOX_TASK_DUE_LBL = "//*[@Name='FORM_NAME']/following-sibling::*[@ClassName='TextBlock']";

	public static final String RECALLED_TASK = "//*[@Name = 'Task Recalled' and @ClassName= 'TextBlock']";
	public static final String OK_BTN = "//*[@AutomationId='PrimaryButton' and @Name = 'Ok']";
	public static final String REASSIGNED_TASK = "//*[@Name = 'Task Reassigned' and @ClassName= 'TextBlock']";

	public static final String TASK_DETAILS_NO_DUE_DATE = "//*[@Name='Assigned To :']/preceding-sibling::*/preceding-sibling::*";


}

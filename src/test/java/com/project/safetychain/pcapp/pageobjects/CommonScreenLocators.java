package com.project.safetychain.pcapp.pageobjects;

public class CommonScreenLocators {

	public static final String SSO_LOGIN_POPUP_CLOSE_BTN = "//*[@Name='Close' and @LocalizedControlType='button']";
	public static final String DOWNLOAD_DATA_POPUP_LBL = "//*[@Name='Downloading Data...']";  
	public static final String USER_DETAILS_LBL = "//*[@AutomationId='UserName']";

	public static final String INBOX_MNU = "//*[@Name='Inbox']";
	public static final String SUBMISSIONS_MNU = "//*[@Name='Submissions']";
	public static final String SAVED_MNU = "//*[@Name='Saved']";
	public static final String FAVORITES_MNU = "//*[@Name='Favorites']";
	public static final String FORM_MNU = "//*[@Name='Forms']";
	public static final String DEVICE_MANAGEMENT_MNU = "//*[@Name='Device Management']";
	public static final String LINK_MNU = "//*[@Name='LINK']";
	public static final String SETTINGS_MNU = "//*[@Name='Settings']";
	public static final String TASK_CARD = "//*[@Name='Scheduled Tasks']";

	public static final String LOGOUT_BTN = "//*[@Name='Logout']";
	public static final String CONFIRM_LOGOUT_BTN = "//*[@Name='Yes']";

	public static final String OPEN_POPUP_ALL_LOCATIONS_BTN = "//*[@Name='All locations']";
	public static final String OPEN_POPUP_ADDRESS_LINK = "//*[@Name='Address']";
	public static final String OPEN_POPUP_FILE_BTN = "//*[@Name='upload']";
	public static final String OPEN_POPUP_OPEN_BTN = "//*[@Name='Cancel']/preceding-sibling::*[@Name='Open' and @ClassName='Button']";

	public static final String SPC_NEXT_BTN = "//*[@AutomationId='SPCNext' and @ClassName='Button']";
	public static final String SPC_CLOSE_BTN = "//*[@AutomationId='SPCClose' and @ClassName='Button']";

	public static final String SSO_WEB_VIEW_LOGIN_POPUP_CLOSE_BTN = "//*[@Name='Close' and @ClassName='Button']";
	public static final String SSO_LOGIN_LNK = "//*[@Name='Use Single Sign On' and @ClassName='Hyperlink']";

	public static final String CONFIRM_LOGOUT_LBL = "//*[@Name='Confirm Logout' and @ClassName='TextBlock']";
	public static final String LOGOUT_CONFIRMATION_LBL = "//*[@Name='Are you sure you want to logout?' and @ClassName='TextBlock']";

	public static final String HAMBURGER_MENU = "//*[@Name='']/preceding-sibling::*[@ClassName='Button']";
	public static final String SC_VERSION = "//*[@Name='VERSION' and @ClassName = 'TextBlock']";
	public static final String USERNAME ="//*[@AutomationId='UserName' and @Name='USERNAME']";
	public static final String MINIMIZE = "//*[@AutomationId='Minimize' and @Name='Minimize SafetyChain']";

	public static final String LOADING_PLEASE_WAIT_LBL = "//*[@AutomationId='TxtMessage' and @Name='Loading Please Wait..']";

	public static final String OPEN_POPUP_SELECT_FILE_BTN = "//*[@Name='FILE_NAME']";

	public static final String SSO_INCORRECT_USER_LBL = "//*[@Name='Unable to sign in']";
	public static final String AUTOMATIC_REFRESH_LBL = "//*[@Name='Checking for New Task']";

}

package com.project.safetychain.pcapp.pageobjects;

public class SettingsScreenLocators {

	public static final String SHOW_NUMERIC_KEYPAD_TGL = "//*[@Name='Show Numeric Keypad']/following-sibling::*[@AutomationId='KeypadStatus']";
	public static final String OFFLINE_MODE_TGL = "//*[@Name='Offline Mode']/following-sibling::*[@ClassName='ToggleSwitch']";
	public static final String LIMIT_DATA_KEYPAD_TGL = "//*[@Name='Limit Data']/following-sibling::*[@ClassName='ToggleSwitch']";
	public static final String CLEAR_SUBMISSION_BTN = "//*[@Name='Clear submissions for all users.']/following-sibling::*[@Name = 'Clear' and @ClassName='Button']";
	public static final String YES_POPUP_BTN = "//*[@Name='Yes' and @ClassName='Button']";
	public static final String ACCESSIBILITY_LBL = "//*[@Name='Accessibility' and @ClassName='TextBox']";
	public static final String CONNECTIVITY_LBL = "//*[@Name='Connectivity' and @ClassName='TextBox']";
	public static final String LOGS_MANAGEMENT_LBL = "//*[@Name='Logs Management' and @ClassName='TextBox']";
	public static final String SUBMISSIONS_LBL = "//*[@Name='Submissions' and @ClassName='TextBox']";

}

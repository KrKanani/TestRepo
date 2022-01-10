package com.project.safetychain.pcapp.pageobjects;

public class SavedScreenLocators {

	public static final String SAVED_FORM_SEARCH_TXT = "//*[@Name='Search']";
	public static final String SAVED_SEARCH = "//*[@AutomationId='SearchText']";
	public static final String SAVED_FORM = "//*[@LocalizedControlType='text' and @Name='FORM_NAME']";
	public static final String SELECT_FORM = "//*[@ClassName='TextBlock' and @Name='FORM_NAME']";
	public static final String NEXT_BTN = "//*[@ClassName='Button' and @Name='NEXT']";

	public static final String DELETE_BTN = "//*[@Name='DELETE' and @ClassName='Button']";
	public static final String DELETE_YES_BTN = "//*[@AutomationId='PrimaryButton' and @Name='Yes']";

	public static final String DUSTBIN_BTN = "//*[@Name='Location :']/preceding-sibling::*[@ClassName='Button']";

	public static final String SAVE_COUNT = "//*[@Name='Saved']/following-sibling::*[@ClassName='TextBlock']";
	public static final String NO_FORMS_LBL = "//*[@Name='No forms found.']";

	public static final String SAVED_FORM_COUNT = "//*[@Name='Saved']/following-sibling::*[@ClassName='TextBlock']";
	public static final String SAVE_BTN = "//*[@ClassName='Button' and @Name='SAVE']";

	public static final String SAVED_FORM_REFRESH_BTN = "//*[@AutomationId='Refresh_Button']";
	public static final String REFRESH_YES_BTN = "//*[@ClassName='Button' and @Name='Yes']";

	public static final String FIELD_DATA_LBL = "//*[contains(@Name,'FIELD_NAME')][1]//following-sibling::*[@Name='FIELD_VALUE']";

	public static final String DATE_LBL = "//*[@Name='DATE' and @ClassName='TextBlock']";

	public static final String SAVED_FORM_NAME_LBL = "//*[@Name='Saved On :']/preceding-sibling::*[@ClassName='TextBlock']";
	public static final String SAVED_ON_DATA = "//*[@Name='Saved On :']/following-sibling::*[@ClassName='TextBlock']";
	public static final String LOCATION_DATA = "//*[@Name='Location :']/following-sibling::*[@ClassName='TextBlock']";
	public static final String RESOURCE_DATA = "//*[@Name='Resource :']/following-sibling::*[@ClassName='TextBlock']";

	public static final String PASSWORD_INPUT_LBL = "//*[@Name = 'Please input your password']";
	public static final String PASSWORD_RESET_INP = "//*[@AutomationId = 'PasswordBox']";
	public static final String LOGIN_BTN = "//*[@ClassName='Button' and @Name= 'LOGIN']";

}

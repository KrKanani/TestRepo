package com.project.safetychain.pcapp.pageobjects;

public class FormViewScreenLocators {

	public static final String LOCATION_TAB = "//*[@ClassName='TextBlock' and @Name='Location :']";
	public static final String LOCATION_SEARCH_INP = "//*[@AutomationId='LocationSearchText']";
	public static final String LOCATION_VALUE = "//*[@ClassName='ListViewItem' and @Name='LOCATION_NAME']";
	public static final String RESOURCE_TAB = "//*[@ClassName='TextBlock' and @Name='Resource :']";
	public static final String RESOURCE_SEARCH_INP = "//*[@AutomationId='ResourceSearchText']";

	public static final String RESOURCE_VALUE = "//*[@ClassName='ListViewItem' and @Name='RESOURCE_NAME']";

	public static final String NEXT_BTN = "//*[@ClassName='Button' and @Name='NEXT']";

	public static final String SELECT_FIELD_LBL = "//*[@ClassName='TextBlock' and @Name='FIELD_NAME *']";

	public static final String EDIT_FIELD_INP = "//*[@LocalizedControlType='edit']";

	public static final String SELECT_OPTION_FIELD_INP = "//*[@LocalizedControlType='edit' and @AutomationId='SearchText']";
	public static final String SELECT_OPTION_FIELD_VALUE = "//*[@LocalizedControlType='list item' and @Name='OPTION_VALUE']";


	public static final String DATE_FIELD_INP = "//*[@ClassName='CalendarViewDayItem']";
	public static final String DATE_FIELD_VALUE = "//*[@ClassName='CalendarViewDayItem' and @Name='DAY'][INDEX]";

	public static final String TIME_FIELD_INP = "//*[@AutomationId='FlyoutButton']";
	public static final String DATE_TIME_FIELD_INP = "//*[@AutomationId='DatePickerControl']";
	public static final String TIME_FLYOUT_ACCEPT_BTN = "//*[@LocalizedControlType='button' and @Name='Accept']";

	public static final String LOCATION_RESOURCE_SELECTION = "//*[@Name='Location : LOCATION_NAME ,  Resource : RESOURCE_NAME']";

	public static final String SUBMIT_BTN = "//*[@ClassName='Button' and @Name='SUBMIT']";
	public static final String SAVE_BTN = "//*[@ClassName='Button' and @Name='SAVE']";
	public static final String SUBMIT_REPEAT_BTN = "//*[@ClassName='Button' and @Name='SUBMIT & REPEAT']";
	public static final String DELETE_BTN = "//*[@ClassName='Button' and @Name= 'DELETE']";


	public static final String NOTE =  "//*[@LocalizedControlType='text' and @Name='NOTE']";
	public static final String RELATED_DOC_DOCS =  "//*[@ClassName='TextBlock' and @Name='DOCUMENT_NAME']";
	public static final String SUMMARY_RESULT =  "//*[@LocalizedControlType='text' and @Name='100.00  %']";

	public static final String FORM_LEVEL_VERIFICATION_BTN = "//*[@AutomationId='Form_Verification']";
	public static final String VERIFICATION_USERNAME_INP = "//*[@AutomationId='UsernameTextBox']";
	public static final String VERIFICATION_PIN_INP = "//*[@ClassName='PasswordBox']";
	public static final String VERIFICATION_COMPLIANT_BTN = "//*[@AutomationId='Compliant']";
	public static final String VERIFICATION_VERIFY_BTN = "//*[@Name='Verify' and @ClassName='Button']";
	public static final String FORM_LEVEL_ATTACHMENT_BTN = "//*[@AutomationId='Form_Attachment']";
	public static final String FORM_LEVEL_ADDED_ATTACHMENT_TXT = "//*[@Name='upload.png']";
	public static final String FORM_LEVEL_COMMENT_BTN = "//*[@AutomationId='RecordLevelComment']";
	public static final String FORM_LEVEL_COMMENT_INP = "//*[@AutomationId='RecordComment']";
	public static final String FORM_LEVEL_COMMENT_SAVE_BTN = "//*[@Name='Save']";
	public static final String FIELD_LEVEL_COMMENTS_INP = "//*[@AutomationId='Field_Comment']";
	public static final String FIELD_LEVEL_CORRECTION_INP = "//*[@AutomationId='CorrectionText']";
	public static final String FIELD_LEVEL_ATTACHMENT_BTN = "//*[@AutomationId='FileUpload']";
	public static final String FIELD_LEVEL_HINT_TXT = "//*[@Name='PC App Automation field']";

	public static final String SINGLE_DIGIT_CLEAR_BTN = "//*[@AutomationId='BtnClearSingleDigit']";

	public static final String FORM_ELEMENT = "//*[@Name='FIELD' and @ClassName='TextBlock']";

	public static final String FORM_BACK_BTN = "//*[@Name='Location/Resource Selection']/preceding-sibling::*[@ClassName='Button']";
	public static final String DISCARD_BTN = "//*[@Name='Discard' and @ClassName='Button']";
	public static final String DIRECT_FORM_BACK_BTN = "//*[@Name='Forms']/preceding-sibling::*[@ClassName='Button']";

	public static final String FORM_LEVEL_ATTACHMENT_CLOSE_BTN = "//*[@Name='Close' and @AutomationId='Close' and @ClassName='Button']";

	public static final String OK_BTN = "//*[@Name='Ok' and @ClassName='Button']";
	public static final String ERROR_POP_UP_LBL = "//*[@Name='Error']";
	public static final String RECORD_ID_DELETED_ERROR_LBL = "//*[@Name='RecordId Already Deleted']";

	public static final String FORM_VALIDATION_FAILED_LBL = "//*[@Name='Form Validation Failed' and @ClassName='TextBlock']";
	public static final String FORM_VALIDATION_MESSAGE_LBL = "//*[@Name='There were some errors found on the form' and @ClassName='TextBlock']";
	public static final String ERRORS_LBL = "//*[@Name='Errors' and @ClassName='TextBlock']";
	public static final String ENTER_VALUE_WARNING_LBL = "//*[@Name='Please enter a value.' and @ClassName='TextBlock']";

	public static final String FORM_LEVEL_CAMERA_ICON = "//*[@AutomationId='Form_Camera']";
	public static final String FIELD_LEVEL_CAMERA_ICON = "//*[@AutomationId='btnCamera']";
	public static final String CLOSE_CAMERA_BTN = "//*[@Name='Close Camera' and @AutomationId='Close']";

	public static final String FIELD_DATA_LBL = "//*[contains(@Name,'FIELD_NAME')][1]//following-sibling::*[@Name='FIELD_VALUE']";
	public static final String FIELD_LBL = "//*[contains(@Name,'FIELD_NAME')][1]";

	public static final String SCROLL_UP = "//*[@AutomationId='ScrollUp']";
	public static final String SCROLL_DOWN = "//*[@AutomationId='ScrollDown']";
	public static final String PAGE_SCROLL_UP = "//*[@AutomationId='ScrollPageUp']";
	public static final String PAGE_SCROLL_DOWN = "//*[@AutomationId='ScrollPageDown']";
	
	public static final String RECORD_SUBMISSION_DIALOG = "//*[contains(@Name,'Record Submitted on')]";

}

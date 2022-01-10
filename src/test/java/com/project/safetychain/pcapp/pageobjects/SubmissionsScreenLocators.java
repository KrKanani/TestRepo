package com.project.safetychain.pcapp.pageobjects;

public class SubmissionsScreenLocators {

	public static final String SUBMISSIONS_SEARCH = "//*[@AutomationId='SearchText']";
	public static final String SUBMISSIONS_FORM = "//*[@LocalizedControlType='text' and @Name='FORM_NAME']";
	public static final String SELECT_SUBMISSION = "//*[@ClassName='TextBlock' and @Name='FORM_NAME']";
	public static final String SUBMISSION_LOCATION = "//*[contains(@Name,'Submitted On')]/following-sibling::*[contains(@Name,'Location')]";
	public static final String SUBMISSION_BACK_BTN = "//*[@Name='Submission']/preceding-sibling::*[@ClassName='Button']";
	public static final String SUBMISSIONS_PENDING_COUNT = "//*[@Name='Submissions']/following-sibling::*[@ClassName='TextBlock']";

	public static final String CLEAR_ALL_BTN = "//*[@ClassName='Button' and @Name='CLEAR ALL']";
	public static final String RESUBMIT_ALL_BTN = "//*[@ClassName='Button' and @Name='RESUBMIT ALL']";

	public static final String FIELD_NAME_TXT = "//*[@Name='FIELD_NAME *']";
	public static final String FIELD_VALUE_TXT = "//*[@Name='FIELD_VALUE']";

	//public static final String FIELD_VALUE_TXT = "//*[@Name='FIELD_NAME *' and @ClassName='TextBlock']//following-sibling::*[@Name='FIELD_VALUE']";
	//public static final String LAST_FIELD_VALUE_TXT = "//*[@Name='FIELD_VALUE' and @LocalizedControlType='text']";

	public static final String VIEW_CHART_BTN = "//*[@Name='View Chart' and @ClassName='Button']";

	public static final String FIELD_FILE_LNK = "//*[@AutomationId='ViewAttachment' and @Name='FILE_NAME']";
	public static final String FIELD_LEVEL_ATTACHMENT_CLOSE_BTN = "//*[@Name='Close' and @AutomationId='Close' and @ClassName='Button']";
	
	public static final String NO_SUBMISSIONS_LBL = "//*[@Name='No submissions found.']";

}

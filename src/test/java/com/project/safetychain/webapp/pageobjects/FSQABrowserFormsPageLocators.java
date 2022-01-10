package com.project.safetychain.webapp.pageobjects;

public class FSQABrowserFormsPageLocators {


	/**id*/

	/**className*/
	public static final String SUBMIT_FRM_POPUP_MSG = "scs-popup-main-body";

	/**xpath*/
	public static final String FORM_SELECTION = "//*[@id='scs-forms-grid']//td[text()='FORM_NAME']";
	public static final String FORM_OPENED = "//*[@id='form-editor-panel']//div[@class='scs-form-title ng-binding' and text()='FORM_NAME']";
	public static final String LOCATION_INPUT = "//input[@class='k-input scs-form-select-location']";
	public static final String RESOURCE_DDL =  "//span[@class='k-widget k-dropdown k-header scs-form-select-resource']";
	public static final String FORM_LEVEL_ATTACHMENT = "//*[@id='attachments']/i[@class='fa fa-paperclip']";
	public static final String FORM_LEVEL_ATTACHMENT_SELECT_BTN = "//div[input[@id='formlevelfiles']]/span[text()='Select files...']";
	public static final String FORM_LEVEL_ATTACHMENT_CLOSE_BTN = "//button[text()='CLOSE']";
	public static final String FORM_LEVEL_ATTACHMENT_INP = "//input[@id='formlevelfiles']";
	public static final String FORM_LEVEL_ATTACHMENT_STATUS = "//*[@class='k-upload-files k-reset']/li[last()]";
	public static final String FORM_LEVEL_ATTACHMENT_RETRY = "//span[@class='k-icon k-i-reload k-i-retry']";
	public static final String ALL_FIELDS = "//field-template/div";
	public static final String FIELDS_LBL = "//*[@id='scs-form-level']//label[@for='field-FIELDID']";
	public static final String ALL_FIELDS_ID = "//*[@id='field-FIELDID']";
	public static final String FIELDS_TXT = "//div[@class='scs-field-for-FIELDID']//input[1]";
	public static final String PARAGRAPH_FIELD_STATUS = "//textarea[@id='field-FIELDID']";
	public static final String FIELDS_FILL_STATUS = "//div[div[@class='scs-field-for-FIELDID']]//span[@class='compliancemode']/i";
	public static final String CORRECTIONS_TXA = "//*[@id='correction-field-FIELDID' and @required='required']";
	public static final String CORRECTIONS_PREDEIFNED_DDL = "//div[contains(@class,'FIELDID')]//div[contains(@class,'scs-predef-container')]//input[@type='text']";
	public static final String FIELDS_SETTINGS_STATUS = "//div[@class='scs-field-for-FIELDID']//i[@class='fa fa-plus-square']";
	public static final String OTHR_FIELDS_SETTINGS_STATUS = "//div[@class='scs-field-for-FIELDID']/..//i[@class='fa fa-plus-square']";
	public static final String FIELDS_SETTINGS_MINUS_STATUS = "//div[@class='scs-field-for-FIELDID']//i[class='fa fa-minus-square']";
	public static final String FIELDS_ATTACHMENTS = "//div[@data-field='FIELDID']//div[@aria-label='Select files...']";
	public static final String FIELDS_ATTACHMENTS_INP = "//div[@data-field='FIELDID']//input[@type='file']";
	public static final String UPLOADED_ATTACHMENT_LNK = "//div[@data-field='FIELDID']//div[@class='scs-field-level-attachment']//span[1]/span/a";
	public static final String CHECK_FIELD_DISPLAYED = "//div[@data-field='FIELDID']";
	public static final String FIELDS_COMMENTS = "//textarea[@id='comment-field-FIELDID']";
	public static final String SUBMIT_FORM_BTN = "//*[@id='scs-submit-form-button']";
	public static final String POPUP_OK_BTN = "//*[@class='ui-dialog-buttonset']/button[last()]";
	public static final String SAVE_BTN ="//*[@id='scs-save-form-button']";
	public static final String NON_COMPLIANCE = "//*[@class='compliancemode']//i[@class='fa fa-close']";
	public static final String COMPLIANCE = "//*[@class='compliancemode']//i[@class='fa fa-check']";
	public static final String SUBMIT_REPEAT_BUTTON =  "//input[@type='submit' and @value='SUBMIT & REPEAT']";
	public static final String SUBMIT_REPEAT_FIELD = "//field-template/div[div/label[contains(text(),'REPEAT_FIELD')]]//input[1]";
	public static final String SUBMISSION_POP_UP_OK_BTN =  "//div[@role='dialog' and //div[contains(text(),'Submitted successfully')]]//button[text()='OK']";
	public static final String FORM_CANCEL_BTN = "//span[text()='Cancel']";
	public static final String SET_FORM_FIELD = "//*[@id='scs-form-level']//label[contains(text(),'FIELDLABEL')]/ancestor::div[@data-fieldtype='FIELDTYPE']//input";
	public static final String SAVED_FORM_DGD = "//*[@id='scs-saved-forms-grid']//tr[td/text()='FORM_NAME'][1]";
	public static final String SAVED_FORM = "//*[@id='scs-saved-forms-grid']//tr/td[text()=\"FORM_NAME\"]/../td[contains(text(),\"DATE_TIME_STAMP\")]/..";
	public static final String FIELD_TYPE = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]";
	public static final String FIELD_FILLED_STATUS_LBL = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']]//span[@class='compliancemode']/i";
	public static final String FIELD_LBL = "//field-template/div//label[normalize-space(text())='FIELD_NAME']";
	public static final String LOCATION_DDL = "//div[div[text()='Location:']]//span[@class='k-icon k-i-arrow-60-down']"; 
	public static final String SELECT_RESOURCE_DDL = "//div[div[text()='Select Resource:']]//span[@class='k-icon k-i-arrow-60-down']"; 
	public static final String LIST_VALUE = "//li[text()='NAME']";
	public static final String SELCTED_LOCATION_LBL = "//div[div[text()='Location:']]//div[@ng-bind='selectedLocation.Name']";
	public static final String SELCTED_RESOURCE_LBL = "//div[div[text()='Select Resource:']]//div[@ng-bind='selectedResource.Name']";
	public static final String IS_RESOLVED_BTN = "//div[contains(@class,'FIELDID')]//label[normalize-space(.)='STATUS']";
	public static final String SUBMIT_FORM_POPUP_MSG = "//*[@id='scs-popup']/span";
	public static final String EXIST_CORRECTION_TXA = "//textarea[contains(@id,'correction-field')]";
	public static final String FORM_HEADERLEVEL_DETAILS = "//*[@id='form-editor-panel']//div[contains(text(),'DETAILS')]";
	public static final String NUMERIC_FIELD_VALUE = "//div[div/label[contains(text(),'FIELD_NAME')]]//input[1]";

	public static final String DISPLAYED_FIELD_TYPE = "//field-template/div[div/label[normalize-space(text())='FIELD_NAME']][not(contains(@class, 'hide'))]";
	public static final String INCOMPLETE_FORM_SUBMIT_ALERT = "//span[text()='Alert!']";
	public static final String INCOMPLETE_FORM_SUBMIT_ALERT_OKBTN = "//div[@class='ui-dialog-buttonset']/button[text()='Ok']";
	public static final String SAVED_FORM_NAME = "//div[@id='scs-saved-forms-grid']//td[text()='FORM_NAME']";

	// FSQABrowser Forms and FromDesigner Preview
	public static final String FIELD_REQUIRED_ASTERISK = "//label[contains(text(),'FIELD_NAME')]/span[@class='scs-required-field']";
	public static final String FIELD_HINT = "//label[contains(text(),'FIELD_NAME')]/../../div[@class='hintText ng-scope']/div";
	public static final String FIELD_SELECT_FILES_BTN = "//label[contains(text(),'FIELD_NAME')]/../..//div[@class='k-button k-upload-button']";
	public static final String FIELD_ATTACHMENT_INPUT = "//label[contains(text(),'FIELD_NAME')]/../..//input[@data-role='upload']";
	public static final String FIELD_ATTACHMENT_UPLD_DONE = "//label[contains(text(),'FIELD_NAME')]/../..//strong[text()='Done']";
	public static final String FIELD_COMMENT = "//label[contains(text(),'FIELD_NAME')]/../..//textarea[contains(@id,'comment')]";
	public static final String FIELD_CORRECTION_TXTAREA = "//label[contains(text(),'FIELD_NAME')]/../..//textarea[contains(@id,'correction')]";
	public static final String FIELD_PREFFERED_CORRCTN_INPUT = "//label[contains(text(),'FIELD_NAME')]/../..//div[contains(@class,'scs-predef-container')]//input[@role='combobox']";
	public static final String FILED_ATTACHMENTS = "//label[contains(text(),'FIELD_NAME')]/../..//div[@class='scs-field-level-attachment']//a";
	public static final String FIELD_ATCHMNTS_WITH_UPLD_STATUS = "//label[contains(text(),'FIELD_NAME')]/../..//li[@class='k-file k-file-success']//span[@class='k-file-name-size-wrapper']/span[1]";
	public static final String FIELD_PREDEF_CORRCTN_DRPDWN = "//label[contains(text(),'FIELD_NAME')]/../..//span[@class='k-icon k-i-arrow-60-down']";//label[contains(text(),'FT')]/../..//input[@role='combobox']/..//span[@class='k-icon k-i-arrow-60-down']
	public static final String FIELD_PREDEF_CORRCTN_DRPDWN_OPTNS = "//div[@class='k-animation-container']//li[text()='OPTION']";

	public static final String FIELD_MARK_AS_RESOLVED_YES = "//label[contains(text(),'FIELD_NAME')]/../..//div[contains(@id,'resolved')]//label[contains(@class,'res-on')]";
	public static final String FIELD_MARK_AS_RESOLVED_NO = "//label[contains(text(),'FIELD_NAME')]/../..//div[contains(@id,'resolved')]//label[contains(@class,'res-off')]";

	public static final String DOCUMENT_NAME_LNK = "//div[@id=\"scs-header-level\"]//div[text()=\"Related Documents:\"]/..//span[contains(text(),\"DOCUMENTNAME\")]";

	public static final String MIN_MAX_TARGET_LBL = "//label[contains(text(),'FIELD_NAME')]/../following-sibling::div//span[contains(text(),'MIN_MAX_TARGET')]";

	public static final String POINTS_EARNED = "//label[contains(text(),'FIELD_NAME')]/../..//span[@class='scs-points-earned ng-scope']/span";
	public static final String POINTS_POSSIBLE = "//label[contains(text(),'FIELD_NAME')]/../..//span[@class='scs-points-possible ng-scope']/span";

	public static final String FORM_RESOURCE_LST = "//*[@id='resrcDrpdown-list']//li[@role='option']";
	public static final String FIELD_UOM_LBL = "//label[contains(text(),'FIELD_NAME')]/../..//span[contains(@class, 'compliance-unit')]";

	public static final String DELETE_SAVED_FORM_ICON = "//*[@id='scs-delete-form-button']/span[@class='fa fa-trash-o']";
	public static final String DELETE_SAVED_FORM_POP_UP_YES_BTN = "//div[div/span[text()='Delete Saved Form']]//button[text()='YES']";
	
	public static final String NO_DATA_FOUND = "//*[@id='resrcDrpdown-list']//div[text()='No data found.']";
	public static final String DROPDOWN_LIST_FILTER = "//*[@id='resrcDrpdown-list']/span/input";

}

package com.project.safetychain.webapp.pageobjects;

public class CommonPageLocators {

	/**id*/
	public static final String HAMBURGER_MNU = "//div[@id='scs-global-nav']/*[local-name()='svg']";
	public static final String USER_SETTINGS = "scs-user-settings";
	public static final String SC_LOGO = "scs-logo";

	/**className*/

	/**xpath*/
	
	public static final String USER_ICON = "//*[@class='scsIcon scs-headeruser scsIconUser']";
	public static final String LOGOUT = "//span[contains(.,'Log')]";

	//Hamburger Menu Items
	public static final String DASHBOARD_MNU = "//*[@id='scs-main-navigation']//div[text()='Dashboard']";
	public static final String LINK_MNU = "//*[@id='scs-main-navigation']//div[text()='LINK']";
	public static final String BROWSER_MNU = "//*[@id='scs-main-navigation']//div[text()='Browser']";
	public static final String RECORD_SIGNOFF_MNU = "//*[@id='scs-main-navigation']//div[text()='Record Signoff']";
	public static final String DOCUMENT_MANAGEMENT = "//*[@id='scs-main-navigation']//div[text()='Documents']";
	public static final String INBOX = "//*[@id='scs-main-navigation']//div[text()='Inbox']";
	public static final String PROGRAMS_MNU = "//*[@id='scs-main-navigation']//div[contains(text(),'Programs')]";
	
	public static final String ADMINTOOLS_MNU = "//*[@id='scs-main-navigation']//div[text()='Admin Tools']";
	public static final String ADMINTOOLS_CONTAINER = "//*[@id='scs-main-navigation']//div[text()='Admin Tools']/following-sibling::div[contains(@class, 'sub-menu-container')]";

	//Admin Tools Menu Items
	public static final String USERS_MNU = "//*[@id=\"scs-main-navigation\"]//div[text()=\"Users\"]";
	public static final String ROLES_MNU = "//*[@id='scs-main-navigation']//div[text()='Roles']";
	public static final String WORKGROUP_MNU = "//*[@id='scs-main-navigation']//div[contains(@class,'scs-main-menu-sub-menu-item-title') and text()='Work Groups']";

	public static final String RESOURCE_DESIGNER_MNU = "//*[@id='scs-main-navigation']//div[text()='Resource Designer']";
	public static final String RESOURCES_MNU = "//*[@id='scs-main-navigation']//div[text()='Resources']";
	public static final String REQUIREMENTS_MNU = "//*[@id='scs-main-navigation']//div[text()='Requirements']";
	public static final String LOCATIONS_MNU = "//*[@id='scs-main-navigation']//div[text()='Locations']";

	public static final String FORM_DESIGNER_MNU = "//*[@id='scs-main-navigation']//div[text()='Form Designer']";

	public static final String USER_DETAILS_LBL = "//*[@id='scs-user-settings' or @class='scs-pp-userdata']/*[contains(@class,'scs-user-name')]";

	public static final String VALIDATIONS_MNU = "//*[@id='scs-main-navigation']//div[text()='Validations']";
	public static final String VERIFICATIONS_MNU = "//*[@id='scs-main-navigation']//div[text()='Verifications']";
	public static final String FORMS_MANAGER_MNU = "//*[@id='scs-main-navigation']//div[text()='Forms Manager']";
	public static final String CHART_BUILDER_MNU = "//*[@id='scs-main-navigation']//div[text()='Chart Builder']";
	public static final String DEVICES_MNU = "//*[@id='scs-main-navigation']//div[text()='Devices']";
	public static final String TASKSCHEDULER_MNU = "//*[@id='scs-main-navigation']//div[contains(text(),'Task Scheduler')]";
	
	public static final String FORM_SELECTION = "//*[@id='scs-forms-grid']//td[text()='FORM_NAME']";
	public static final String FORM_OPENED = "//*[@id='form-editor-panel']//div[@class='scs-form-title ng-binding' and text()='FORM_NAME']";

	public static final String LOCATION_INPUT = "//input[@class='k-input scs-form-select-location']";
	public static final String RESOURCE_DDL =  "//span[@class='k-widget k-dropdown k-header scs-form-select-resource']";
	public static final String PROGRAM_DESIGNER_MNU = "//*[@id=\"scs-main-navigation\"]//div[text()='Program Designer']";

	public static final String DATA_IMPORT_EXPORT_MNU = "//*[@id='scs-main-navigation']//div[text()='Data Import / Export']";

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
	public static final String FIELDS_FILL_STATUS = "//div[@class='scs-field-for-FIELDID']//span[@class='compliancemode']/i";
	public static final String CORRECTIONS_TXA = "//*[@id='correction-field-FIELDID' and @required='required']";
	public static final String CORRECTIONS_PREDEIFNED_DDL = "//div[contains(@class,'FIELDID')]//div[contains(@class,'scs-predef-container')]//input[@type='text']";
	public static final String FIELDS_SETTINGS_STATUS = "//div[@class='scs-field-for-FIELDID']//i[@class='fa fa-plus-square']";
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
	
	public static final String HAMBURGER_MNU_LST = "//div[contains(@class, 'scs-main-menu-item-title')]";
	public static final String HAMBURGER_MNU_IMG = "//div[contains(@class, 'scs-main-menu-item-title')][text()='MENU_ITEM']/../div[@class='scs-main-menu-item-image']";
	public static final String INBOX_FILTERS_TEXT = "//*[@id='content']//div[@class='scs-inbox-left-panel']/div[contains(.,'Inbox Filters')]";
	
	//Change Password
	public static final String CHNG_PSSWRD_CALLOUT_OPTN = "//div[@class='cr-calloutContent']//span[text()='Change Password']";
	public static final String CHNG_PSSWRD_POPUP = "//div[@class='scs-popup-inner-title' and text()='Change Password']/../../..";
	public static final String NEW_PASSWORD_INPUT = "//input[@ng-model='password']";
	public static final String CONFIRM_PASSWORD_INPUT = "//input[@ng-model='confirmPassword']";
	public static final String CHNG_PSSWRD_YES_BUTTON = "//button[text()='YES']";  
}

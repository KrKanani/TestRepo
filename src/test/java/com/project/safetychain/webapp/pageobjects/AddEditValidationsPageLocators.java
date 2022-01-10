package com.project.safetychain.webapp.pageobjects;

public class AddEditValidationsPageLocators {

	/**id*/
	public static final String CLOSE_VLDTN_BTN = "scs-close-validation-btn";
	public static final String REVALIDATE_VLDTN_BTN = "scs-complete-revalidate-btn";
	public static final String COMPLETE_VLDTN_DETS = "scs-validation-completion-details";
	public static final String DELETE_VLDTN_BTN = "scs-delete-validation-btn";
	public static final String COPY_VLDTN_BTN = "scs-copy-validation-btn";
	public static final String CLEAR_VLDTN_BTN = "scs-clear-validation-btn";
	public static final String UPDATE_VLDTN_BTN = "scs-update-validation-btn";
	
	public static final String VLDTN_TITLE = "scs-selected-validation-title";
	public static final String VLDTN_TYPE_VAL = "scs-validation-type-input";
	public static final String VLDTN_LOCATION_VAL = "scs-validation-location-input";
	public static final String VLDTN_RESOURCE_VAL = "scs-validation-resource-input";

	/**className*/
	
	public static final String VLDTN_SUBDETS_TITLES = "scs-subdetail-title";

	/**xpath*/
	public static final String NAME_TXT = "//*[@id='scs-validation-details-content']//label[text()='NAME ']/..//input";
	public static final String COMMON_DDL_VALUE = "//div[contains(@class,'k-list-container')]//span[text()='DDLOPTIONVALUE']";
	public static final String FILTER_TXT = "//*[@id='scs-validation-forms-filter']/input";
	public static final String SEARCH_FILTER_BTN = "//*[@id='scs-validation-forms-filter']/span[contains(@class,'search')]";
	public static final String SELECT_FORM_CHK = "//*[@id='scs-validation-forms']//label[text()='FORMNAME']/preceding-sibling::input";
	public static final String START_DATE_TXT = "//*[@name='validationStartDate']//input";
	public static final String END_DATE_TXT = "//*[@name='validationEndDate']//input";
	public static final String IDENTIFIER_NAME_TXT = "//*[@id='scs-validation-date-col']//label[text()='IDENTIFIER FIELD NAME']/..//input";
	public static final String IDENTIFIER_VALUE_TXT = "//*[@id='scs-validation-date-col']//label[text()='VALUE']/..//input";
	public static final String COMPLETE_VLDTN_POPUP_TXA = "//*[@id='scs-complete-validation-popup-content']//textarea";
	public static final String COMPLETE_VLDTN_POPUP_BTN = "//div[contains(@class,'dialogSlideInAppear')]//button[text()='COMPLETE']";
	public static final String REVALIDATE_VLDTN_POPUP_BTN = "//div[contains(@class,'dialogSlideInAppear')]//button[contains(text(),'REVALIDATE')]";
	public static final String CANCEL_VLDTN_POPUP_BTN = "//div[contains(@class,'dialogSlideInAppear')]//button[text()='CANCEL']";
	public static final String RSLT_SUMM_POPUP_LBL = "//*[@id='scs-complete-validation-popup-content']//label[contains(text(),'RESULT SUMMARY')]/..";
	public static final String VLDTN_RSLT_POPUP_LBL = "//*[@id='scs-complete-validation-popup-content']//label[text()='VALIDATION RESULT']/..";
	public static final String CMPLT_VLDTN_OPTNS = "//*[@class='k-list-scroller']//li";
	public static final String REQD_VLDTN_LBL = "//*[@id='scs-validation-details-content']//span[contains(@class,'required')]/..";
	public static final String VLDTN_DESCPTN_LBL = "//*[@id='scs-validation-desc-input']/label";
	public static final String VLDTN_FILTER_LBL = "//*[@class='scs-subdetail-title'][text()='Filters']/..//label";
	public static final String COMMON_POPUP_LST = "//div[contains(@class,'scs-dropdown')]//span[@class='ng-star-inserted']";
	public static final String DISPLYD_FORMS_LBL = "//input[contains(@id,'scs-validation-form')]/following-sibling::label";
	public static final String COMPLETE_VLDTN_BTN = "//*[@id='scs-complete-validation-btn'][text()='COMPLETE']";
	public static final String ADD_VLDTN_BTN = "//*[@id='scs-complete-validation-btn'][text()='ADD']";
	public static final String REVLDTN_POPUP_MSG = "//div[contains(@class,'dialogSlideInAppear')]/div[contains(@class,'content')]";
	public static final String CANCEL_REVALIDATE_POPUP_BTN = "//div[contains(@class,'dialogSlideInAppear')]//button[contains(text(),'CANCEL')]";
	public static final String SET_VLDTN_TAB = "//*[contains(@id,'k-tabstrip-tab')]/span[text()='TABNAME']";
	public static final String HISTORY_DETAILS = "//div[contains(@class,'scs-validation-history-event')]";
	public static final String FORM_REQD_MSG = "//*[@id='scs-validation-forms-subdetail']//span[contains(@class,'scs-validations-required')]";
	public static final String DELETE_VLDTN_POPUP_BTN = "//div[contains(@class,'dialogSlideInAppear')]//button[contains(text(),'DELETE')]";
	public static final String ADD_RSRC_VLDTN_POPUP_TXT = "//kendo-popup//span[contains(@class, 'k-list-filter')]/input[contains(@class, 'textbox')]";

}

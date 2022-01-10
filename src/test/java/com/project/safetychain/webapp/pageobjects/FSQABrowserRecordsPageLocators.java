package com.project.safetychain.webapp.pageobjects;

public class FSQABrowserRecordsPageLocators {

	/**id*/
	public static final String SIGNOFF_LNK = "scs-signRecordIcon";
	public static final String SIGNOFF_BTN = "scs-signoffpopup-button-sign";
	public static final String SEARCH_TXT = "scs-search";
	public static final String SEARCH_BTN = "scs-search-icon";
	public static final String VOID_CMMT_TXA = "scs-void-record-commentsText";
	public static final String SUBMIT_BTN = "scs-submit-form-button";
	public static final String SUBMIT_CMMT_TXA = "scs-signoffpopup-commentsText";
	public static final String HISTORY_ICON = "scs-recordHistoryIcon";
	public static final String CLOSE_HISTORY_POPUP = "scs-signoffpopup-close";
	public static final String CHANGE_RESOURCE_WARNING_BTN = "scs-changeResource-popup-button-ok";
	public static final String CHANGE_RESOURCE_CMMT_TXA = "scs-changeResource-commentsText";
	public static final String CHANGE_RESOURCE_POPUP_OK_BTN = "scs-submit-changeResource-popup-button-ok";
	public static final String RVIEWER_RECORDS_TITLE = "scs-selected-records-title";
	public static final String RVIEWER_GROUPBY_BTN = "scs-sort-by";
	
	public static final String UPDATE_CMMNT_TXA = "comment-field-DATA_FIELD";
	public static final String UPDATE_CRRCTN_TXA = "correction-field-DATA_FIELD";
	
	/**className*/
	
	public static final String RVIEWER_GROUPBY_MNU_TITLE = "scs-menu-title";

	
	/**xpath*/
	public static final String SIGNOFF_CMMT_TXA = "//*[@id='scs-signoffpopup-commentsfield']/textarea";
	public static final String HEADER_FIELD_VALUE = "//*[@id='scs-header-level']//label[contains(text(),'HEADERFIELDNAME')]/../following-sibling::div[contains(@class,'scs-field-detail-right')]";
	public static final String SIGNED_BY_HEADER_VALUE = "//*[@id='scs-header-level']//label[contains(text(),'Signed By')]/../following-sibling::div[@class='scs-field-detail-right']/div";
	public static final String RECORDS_LST = "//*[@id='scs-selecred-records-container']//div[contains(@class,'scs-record-card')]";
	public static final String FORM_NAME = "//*[@id='scs-record-viewer-right-panel']//div[contains(@class,'scs-form-title')]";
	public static final String EDIT_RECORD_BTN = "//div[@class='scs-form-header-icons']//i[@class='fa fa-pencil']";
	public static final String EDIT_RECORD_BTN_DETS = "//div[@class='scs-form-header-icons']//span[@ng-if='editRecord']";
	public static final String DOWNLOAD_RECORD_BTN = "//div[@class='scs-form-header-icons']//i[contains(@class,'fa-download')]";
	public static final String VOID_RECORD_BTN = "//*[@id='scs-cancel-form-button'][text()='VOID']";
	public static final String CANCEL_RECORD_BTN = "//*[@id='scs-cancel-form-button'][text()='Cancel']";
	public static final String CONFIRM_VOID_RECORD_BTN = "//*[@class='ui-dialog-buttonset']/button[text()='VOID']";	
	public static final String UPDATE_FORM_FIELD = "//*[@id='scs-form-level']//label[contains(text(),'FIELDLABEL')]/ancestor::div[@data-fieldtype='FIELDTYPE']//input";
	public static final String READ_FORM_FIELD = "//*[@id='scs-form-level']//label[contains(text(),'FIELDLABEL')]/ancestor::div[contains(@class,'scs-field')]/div[contains(@class,'field-detail-right')]";
	public static final String ACCEPT_SUBMIT_BTN = "//*[@id='scs-signall-container']//button[@id='scs-submit-form-button']";
	public static final String SUBMIT_SUCCESS_BTN = "//*[@id='scs-popup']/following-sibling::div//button[text()='OK']";
	public static final String HISTORY_DETAILS = "//*[@id='record-history']/div[contains(@class,'scs-history-field')]";
	public static final String HISTORY_DATE_DETAIL = "//*[@id='record-history']/div[INDEXNO]//div[@class='scs-record-right-panel']";
	public static final String HISTORY_USR_ACTIVITY_DETAIL = "//*[@id='record-history']/div[INDEXNO]//div[@class='scs-left-panel']";
	public static final String EDIT_RESOURCE_LNK = "//*[@id='scs-header-level']//span[contains(@class,'edit-resource')]";
	public static final String SIGNOFF_WITH_VERIFICATION_DDL = "//*[@id='scs-signall-container']//div[@class='scs-signoffpopup-row']//span[@aria-owns=\"scs-signoff-dropdown_listbox\"]//span[text()='Select']";
	public static final String VERIFICATION_SELECT = "//*[@id='scs-signoff-dropdown_listbox']//li[contains(text(),'VERIFICATION_NAME')]";
	public static final String VERIFICATION_COMMENT_DDL ="//*[@id='scs-signall-container']//div[@class='scs-signoffpopup-row']//span[@aria-owns='scs-comments-dropdown_listbox']//span[text()='Select']";
	public static final String VERIFICATION_COMMENT_SELECT = "//*[@id='scs-comments-dropdown_listbox']/li[text()='VERIFICATION_COMMENT']";
	public static final String RVIEWER_CARD_DATE_HEADER = "//*[@id='scs-selecred-records-container']//div[contains(@class,'scs-date-header')]";
	public static final String RVIEWER_CARD_CMPLTEDON_DATE = "//*[@id='scs-selecred-records-container']//div[@class='scs-record-detail-completedon']";
	public static final String RVIEWER_RECORD_CARDS = "//*[@id='scs-selecred-records-container']//div[contains(@class,'scs-record-card')]";
	public static final String RVIEWER_CARD_FORMNM = "//div[contains(@class, 'scs-record-card')]//div[contains(@class, 'scs-record-detail-formName')][text()[contains(.,'FORMNAME')]]";
	public static final String RVIEWER_CARD_USRNM_DETS = "//*[@id='scs-selecred-records-container']//div[contains(@class, 'scs-record-detail-username')]";
	public static final String RVIEWER_CARD_FORM_DETS = "//*[@id='scs-selecred-records-container']//div[contains(@class, 'scs-record-detail-formName')]";
	public static final String RVIEWER_CARD_RESRCE_DETS = "//*[@id='scs-selecred-records-container']//div[contains(@class, 'scs-record-detail-resource')]";
	public static final String RVIEWER_GROUPBY_SLCTD_OPTN = "//div[contains(@class,'scs-orderBy-menu')]//li[contains(@class,'selected')]";
	public static final String RVIEWER_GROUPBY_DIVIDER = "//div[contains(@class,'scs-orderBy-menu')]//li[contains(@class,'Divider')]";
	public static final String RVIEWER_GROUPBY_LST = "//div[contains(@class,'scs-orderBy-menu')]//li[not(contains(@class,'Divider')) and not(contains(@class,'title'))]";
	public static final String RVIEWER_SIGN_ICON = "//*[@id='scs-selecred-records-container']//span[@class='ng-hide']/i[contains(@class,'pencil-square-o')]";
	public static final String RVIEWER_ATTCHMNT_ICON = "//*[@id='scs-selecred-records-container']//span[@class='ng-hide']/i[contains(@class,'paperclip')]";
	public static final String RVIEWER_GROUPBY_OPTN = "//*[@class='scs-menu']//span[text()='OPTIONNAME']";
	public static final String RVIEWER_COMPLIANT_ICON = "//*[@id='scs-selecred-records-container']//div[@class='scs-info-container']/div[contains(@class,'scs-compliant-icon')][1]";
	public static final String NO_CHNGE_REC_POPUP = "//*[@id='scs-popup']//div[contains(@class,'main-body')]";
	public static final String CONFIRM_OK_RECORD_BTN = "//*[@class='ui-dialog-buttonset']/button[text()='OK']";
	public static final String RESRC_DROPDWN_LST = "//*[@id='scs-Resource-dropdown_listbox']/li";
	public static final String SHOW_MORE_HIST_BTN = "//*[@id='record-history']//div[contains(@class, 'scs-history-hide')]";
	public static final String SHOW_MORE_HIST_LST = "//div[@class='scs-detail-summary-wrapper' and not(@style='display:none;')]/div[I_INDEX_NO]//div[contains(@ng-repeat,'fielddetail')]";
	public static final String SHOW_MORE_HIST_HEADER = "//div[@class='scs-detail-summary-wrapper' and not(@style='display:none;')]//tr[@class='scs-table-header']";
	public static final String SHOW_MORE_HIST_LABEL = "//div[@class='scs-detail-summary-wrapper' and not(@style='display:none;')]/div[I_INDEX_NO]/div[J_INDEX_NO]//div[@class='scs-edited-label']";
	public static final String SHOW_MORE_HIST_ATTCH_NEW_VAL = "//div[@class='scs-detail-summary-wrapper' and not(@style='display:none;')]/div[I_INDEX_NO]//td[contains(@ng-if,'Attachment')]";
	public static final String SHOW_MORE_HIST_OTHR_NEW_VAL = "//div[@class='scs-detail-summary-wrapper' and not(@style='display:none;')]/div[I_INDEX_NO]/div[J_INDEX_NO]//td[@class='ng-binding']";
	public static final String SHOW_MORE_HIST_OLD_VAL = "//div[@class='scs-detail-summary-wrapper' and not(@style='display:none;')]/div[I_INDEX_NO]/div[J_INDEX_NO]//td[contains(@class,'old')]";
	public static final String OTHR_FIELD_VAL = "//label[text()='FIELD_NAME']/ancestor::div[contains(@class,'scs-field-view')]//label[contains(text(),'FIELD_TYPE')]/../following-sibling::div";
	public static final String ATTCHMNT_FIELD_VAL = "//label[text()='FIELD_NAME']/ancestor::div[contains(@class,'scs-field-view')]//label[contains(text(),'Attachment')]/../following-sibling::div/span";
	public static final String FIELD_VAL = "//label[text()='FIELD_NAME']/../following-sibling::div/div[contains(@class,'field')]";
	public static final String FIELD_CMPLIANCE_VAL = "//label[text()='FIELD_NAME']/../following-sibling::div/span[contains(@class,'compliance')]";
	public static final String FIELD_IN_RECORD = "//label[contains(text(),'FIELD_NAME')]/ancestor::div[@class='scs-field']";
	public static final String UPDATE_FIELD_TXT = "//div[@class='scs-field-for-DATA_FIELD']//input";
	public static final String UPDATE_TO_NO_RSLV = "//*[@id='resolved-DATA_FIELD-false']/parent::label";
	public static final String UPDATE_TO_YES_RSLV = "//*[@id='resolved-DATA_FIELD-true']/parent::label";
	public static final String HEADER_DOC_LNK = "//*[@id='scs-header-level']//span[contains(@class,'open-doc-href')]";
	public static final String LOAD_DOC = "//*[contains(@id,'docViewer')]//div[@class='loading_overlay_message']/parent::div[contains(@style, 'transparent')]";
	public static final String FORM_LEVEL_ATTCHMNT_LNK = "//*[@id='scs-attachments-section']//a";
	
	public static final String TASK_HISTORY_DETAILS = "//*[@id='scs-history-field-wrapper']//div[@class='scs-task-history-row']";
	
//	public static final String UPDATE_CORRTN_CMMTS_TXA = "//label[contains(text(),'Numeric')]/ancestor::div[@class='scs-field']//div//div[contains(text(),'Comment')]/following-sibling::div//textarea";
// For Forms manager TCG
	
	public static final String VERIFY_FORM_BTN = "//*[@id='scs-verify-form']/i";
	//*[@id="scs-verificaton-toggler"]/label[1]
	
	public static final String ISCOMPLIANT_TOGGLE_YES = "//*[@class='scs-toggle-btn scs-toggle-btn-compliant']";
	public static final String DIRECTOBS_USERNAME_TXT = "//*[@id='scs-verification-username']";
	public static final String VERIFICATION_PIN_TXT = "//*[@id='scs-verification-validate-pin']";
	
	public static final String RECORD_COMPLIANT_ICON = "//td[text()='FORM_NAME']/preceding-sibling::td[2]/i";
	public static final String FIELD_REQUIRED_ASTERISK = "//label[contains(text(),'FIELD_NAME')]/span[@class='scs-required-field']";
	public static final String FIELD_HINT = "//label[contains(text(),'FIELD_NAME')]/../../following-sibling::div[@class='hintText ng-scope']/div";
	public static final String FIELD_COMMENT = "//label[contains(text(),'FIELD_NAME')]//following::div[@ng-bind='field.Comment']";
	public static final String FIELD_ATTACHMENTS_NAME = "//label[contains(text(),'FIELD_NAME')]//following::span[@class='scs-form-level-attachment ng-scope']//a";
	public static final String FIELD_CORRECTION = "//label[contains(text(),'FIELD_NAME')]//following::div[contains(@ng-bind,'Correction')]";
	public static final String FIELD_RESOLVED = "//label[contains(text(),'FIELD_NAME')]//following::div[@ng-bind='field.ResolvedText']";
	public static final String MIN_MAX_TARGET_LBL = "//span[contains(text(),'MIN_MAX_TARGET')]";
	public static final String POINTS_EARNED = "//label[contains(text(),'FIELD_NAME')]/../../preceding-sibling::div//span[@ng-bind='field.PointsEarned']";
	public static final String POINTS_POSSIBLE = "//label[contains(text(),'FIELD_NAME')]/../../preceding-sibling::div//span[@ng-bind='field.QuestionWeight']";
	public static final String RECORD_IN_RECORDS_TAB = "//*[@id='scs-record-grid']//tbody//td[text()='RES_INST']/following-sibling::td[position()=1 and text()='FORM_NAME']";
	public static final String SELECT_SIGNOFF_VERIFICATION_DDL = "//span[@class='k-widget k-dropdown k-header scs-select-signoff-list']/span";
	public static final String SELECT_VERIFICATION_NEW = "//*[@id='scs-select-signoff_listbox']//li[text()='VERIFICATION_NAME']";

}


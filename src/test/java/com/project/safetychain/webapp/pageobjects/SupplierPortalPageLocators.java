package com.project.safetychain.webapp.pageobjects;
import org.openqa.selenium.By;

public class SupplierPortalPageLocators {

	/**id*/
	public static final String SUBMIT_FORM_BTN = "scs-submit-form-button";
	public static final String SUBMIT_POPUP_CMMT_TXA = "scs-form-resubmission-note";
	public static final String HIST_WINDOW_TITLE = "inbox-history-window_wnd_title";
	public static final String SAVE_FORM_BTN = "scs-save-form-button";
	
	/**css selector*/
	public static final String FORM_REQ_ICON = ".fa.fa-file-text-o";
	public static final String DOC_UPLOAD_REQ_ICON = ".fa.fa-upload";
	public static final String ACK_REQ_ICON = ".fa.fa-flag-o";
	
	/**className*/
	public static final String RSRC_INST_OPTNS = "scs-single-select-item-text";
	public static final String SUPP_PRTL_TOTAL_CNT_HEADING = "supplier-portal-total-task-heading";
	public static final String SUBMIT_POPUP_TITLE = "scs-popup-inside-title";
	public static final String SUBMIT_POPUP_MSG = "scs-resubmission-reason-message";
	public static final String SUBMIT_POPUP_NOTE = "scs-resubmission-reason-note";
	public static final String HIST_DATE_INFO = "scs-task-hist-datefield";
	public static final String HIST_TIME_INFO = "scs-task-hist-timefield";

	/**xpath*/
	public static String TASK_NAME_TXT = "//*[@id='supplier-inbox-grid']//tr[td//*[text()='TASK_NAME']]/td[3]";
	//public static String TASK_RESOURCE_NAME_TXT = "//*[@id='supplier-inbox-grid']//tr[td//*[text()='TASK_NAME']]/td[4]/span";
	public static String TASK_RESOURCE_NAME_TXT = "//*[@id='supplier-inbox-grid']//tr[td//*[text()='TASK_NAME']]/td[contains(.,'RESOURCE')]";
	public static String TASK_TYPE_ICON = "//*[@id='supplier-inbox-grid']//tr[td//*[text()='TASK_NAME']]/td[2]//i[last()]";
	
	public static String REJECTED_TASK_NAME_TXT = "//*[@id='supplier-inbox-grid']//tr[td//strong[text()='TASK_NAME']]/td[strong[text()='Rejected: ']]";
	public static String OPENED_REJECTED_TASK_NAME_TXT = "//*[@id='supplier-inbox-grid']//tr[td//span[text()='TASK_NAME']]/td[strong[text()='Rejected: ']]";

	public static final String TASK_SEARCH_INP = "//div[@class='supplier-portal-search-container']/input";
	public static final String TASK_SEARCH_BTN = "//div[@class='supplier-portal-search-icon-container']/span[@class='fa fa-search']";
	public static final String TASK_NAME_LST = "//*[@id='supplier-inbox-grid']//tr[1]/td//*[text()='TASK_NAME']";

	public static final String SUPPLIER_PORTAL_LBL = "//span[@class='scs-supplierportal-title']";
	public static final By DOCUMENT_LOAD_STATUS = By.xpath("//div[@class='html_page_contents page-image chrome']");	
	public static final String FORM_NAME = "//div[@class='scs-form-title ng-binding']";

	public static final String DOCUMENT_NAME = "//span[@class='scs-selected-doc-name']/span";
	public static final String SELECT_FILE_BTN = "//div[@aria-label='Select File...']";
	public static final String DOCUMENT_UPLOAD_INP =  "//input[@type='file']";
	public static final String DOCUMENT_UPLOAD_BTN = "//div[@class='ui-dialog-buttonset']/button[text()='UPLOAD']";
	public static final String DOCUMENT_SUBMIT_BTN = "//div[@class='ui-dialog-buttonset']/button[text()='SUBMIT']";

	public static final String TASK_NAME_CLM = "//th[@data-title='Task Name']";
	
	public static final String ACK_SIGN_BTN = "//div[i[@class='fa fa-edit']]/span[text()='SIGN']";
	
	public static final String CANCEL_FORM_BTN = "//button[@id='scs-cancel-form-button']";
	public static final String CANCEL_DOCUMENT_BTN = "//button[@class='scs-cancel-button-popup scs-button scs-gray-button ui-button ui-corner-all ui-widget']";

	public static final String NO_FILE_POP_UP_OK_BTN = "//div[div/span[text()='No File Selected']]//button[text()='OK']";
	
	public static final String COLUMN_NAMES_TBL = "//*[@id='supplier-inbox-grid']//table//th";
	public static final String SUPP_PRTL_COLUMN_VALUE = "//div[contains(@class,'k-grid-content')]//td[COLUMNINDEXNO]";
	public static final String SUPP_PRTL_TASK_DETS = "//div[contains(@class,'k-grid-content')]//span[text()='TASKDETAIL']";
	public static final String SUPP_PRTL_TASK_COUNT = "//div[contains(@class,'supplier-portal-task-grid-total')]";
	public static final String SUPP_PRTL_TOTAL_TASK_COUNT = "//span[@ng-bind='totalTaskCount']";
	public static final String DUE_BY_COLMN_NAME = "//*[@id='supplier-inbox-grid']//a[text()='Due By']/span";
	public static final String SUPP_COLMN_NAMES = "//*[@id='supplier-inbox-grid']//a[text()='COLUMNNAME']";
	public static final String SUPP_COLMN_ASC_DSC_SYMBL = "//*[@id='supplier-inbox-grid']//a[text()='COLUMNNAME']/span";
	public static final String SUPP_STATUS_COLMN_SYMBL = "//*[@id='supplier-inbox-grid']//span[contains(@class,'scs-expiry-tooltip')]";
	public static final String SUPP_GRID_ROW_COUNT = "//*[@id='supplier-inbox-grid']//div[contains(@class,'k-grid-content')]//table//tr";
	public static final String SINGLE_SUPP_USR_HEADER = "//*[@id='partnerportalheader']//span[contains(@class, 'scs-single-supplier-user')]";
	public static final String SUPP_RSRC_DDL = "//*[@id='partnerportalheader']//span[contains(@class, 'k-dropdown-wrap')]";
	public static final String RSRC_SEARCH_TXT = "//*[@id='scs-selected-partner-list']//input";
	public static final String DROPDWN_OPTN = "//div[@class='scs-single-select-item-text'][contains(.,'DROPDOWNOPTION')]";
	public static final String SUPP_PRTL_REFRESH_BTN = "//div[@class='supplier-portal-refresh-button-container']//span";
	public static final String SUPP_PRTL_REFRESH_TOOLTIP = "//div[contains(@class, 'k-tooltip-content')][text()='Refresh']";
	public static final String SUBMIT_POPUP_CLOSE_BTN = "//*[@id='scs-popup']//i[contains(@class,'close')]";
	public static final String SUBMIT_POPUP_OK_BTN = "//button[contains(@class, 'scs-ok-button-popup')]";
	public static final String HIST_ICON = "//span[@class='scs-inbox-task-hist-icon']/i";
	public static final String HIST_ASSGNMNT_INFO = "//div[contains(@class, 'task-hist-summary')]";
	public static final String HIST_DUE_BY_INFO = "//div[contains(@class, 'task-hist-dueby')]";
	public static final String HIST_CLOSE_BTN = "//div[@class='k-window-actions']//i[text()='clear']";
	public static final String TASK_ROW_IN_GRID = "//*[@id='supplier-inbox-grid']//tr[td//*[text()='TASK_NAME']]";
	public static final String TASK_CELL_IN_GRID = "//*[@id='supplier-inbox-grid']//td//*[text()='TASK_NAME']";
}


package com.project.safetychain.webapp.pageobjects;

public class RecordSignoffPageLocators {

	/**id*/
	public static final String TOTAL_RECORD_COUNT = "scs-totalrecords";
	public static final String SIGN_RECORD_POPUP_BTN = "scs-signoffpopup-button-sign";
	public static final String UNSIGNED_RECS_COUNT = "scs-unsignednumbers";
	public static final String SIGNED_RECS_COUNT = "scs-signednumbers";
	public static final String CORRECTN_LIST_PANEL = "scs-noRecordCorrectListPanel";
	public static final String RECORD_SIGNOFF_HEADER = "scs-analytics-header";
	
	public static final String CMPLTD_ON_FROM_TXT = "CompletedOnFrom";
	public static final String CMPLTD_ON_TO_TXT = "CompletedOnTo";
	
	/**className*/
	
	/**xpath*/
	public static final String NEXT_BTN = "//*[@id='scs-steps-buttons-holder']//span[contains(text(),'NEXT')]";
	public static final String SIGN_ALL_BTN = "//*[@id='scs-steps-buttons-holder']//span[contains(text(),'SIGN ALL')]";
	public static final String MSG_POPUP_CLOSE_BTN = "//*[@class='ui-dialog-buttonset']/button";
	public static final String FILTER_COLUMNS = "//*[@id='scs-sign-off-filter-menu']/li[contains(@class,'filterItem')]";
	public static final String FILTER_SEARCH_TXT = "//*[@id='scs-sign-off-filter-menu']//ul[contains(@style,'display: block')]//div[@class='searchWrap']/input";
	public static final String FILTER_SEARCH_BTN = "//*[@id='scs-sign-off-filter-menu']//ul[contains(@style,'display: block')]//div[@class='searchButton']";
	public static final String FILTER_VALUE_CHK = "//*[@id='scs-sign-off-filter-menu']//table//td[text()='FILTERVALUE']/preceding::td/input";
	public static final String APPLY_FILTER_POPUP_BTN = "//*[@id='scs-sign-off-filter-menu']//ul[contains(@style,'display: block')]//button[contains(@class, 'scs-filter-button-popup')]";
	public static final String CLEAR_ALL_FILTER_BTN = "//*[@id='scs-clearAll-filters']/button";
	public static final String SIGNOFF_CMMT_TXA = "//*[@id='scs-signoffpopup-commentsfield']/textarea";
	public static final String SELECT_ALL_RECS_CHK = "//*[@id='scs-allsignoff-record-grid']//input[@id='scs-grid-select-all']";
	public static final String RECORD_SUMM_HEADERS = "//div[contains(@class,'scs-summary-holder')]/div[contains(@class,'scs-summary-left')]";
	public static final String RECORD_SUMM_HEADER_NM = "//div[contains(@class,'scs-summary-holder')][INDEX_NO]/div[contains(@class,'scs-summary-left')]";
	public static final String RECORD_SUMM_HEADER_DETS = "//div[contains(@class,'scs-summary-holder')][INDEX_NO]/div[contains(@class,'scs-summary-right')]";
	public static final String COMPLIANT_RECS_COUNT = "//*[@id='scs-analytics-compliant']//div[contains(@class,'scs-counttext')]/span";
	public static final String NONCOMPLIANT_RECS_COUNT = "//*[@id='scs-analytics-noncompliant']//div[contains(@class,'scs-counttext')]/span";
	public static final String RECORD_SIGN_ICON = "//i[contains(@class, 'scs-grid-signature-icon')]";
	public static final String RECORD_SIGNOFF_DETS = "//div[@id='scs-recordsignoff-details-window']/div";
	public static final String RECORD_SIGNOFF_CLOSE_BTN = "//div[contains(@class,'k-window-titlebar')]//a[contains(@class,'Close')]";
	public static final String UNRESOLVD_RECS_COUNT = "//*[@id='scs-unresolvedtab']/div[@id='scs-unresolvedbadge']";
	public static final String RESOLVD_RECS_COUNT = "//*[@id='scs-resolvedtab']/div[@id='scs-unresolvedbadge']";
	public static final String SHOW_CORRECTNS_BTN = "//*[@id='scs-show-corrections']/button";
	public static final String SUMM_DATE_RANGE = "//*[@id='scs-summary-container']//div[contains(text(),'Date Range')]/following-sibling::div//span";
	public static final String CMPLTD_ON_FROM_LBL = "//*[@id='scs-date-time-picker']//label[contains(text(),'From')]";
	public static final String REVW_RECS_LNK = "//*[@id='breadCrumb']//a[contains(text(), 'Review')]";
	
	public static final String CLEAR_FILTER_POPUP_BTN = "//*[@id='scs-sign-off-filter-menu']//ul[contains(@style,'display: block')]//button[contains(@class, 'cancel-button-popup')]";
	public static final String CORRECTNS_PANEL_HEADER = "//*[@id='scs-analytics-corrections']/div[@class='scs-panel-header']";
	
}

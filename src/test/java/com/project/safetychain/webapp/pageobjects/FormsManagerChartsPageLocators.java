package com.project.safetychain.webapp.pageobjects;

public class FormsManagerChartsPageLocators {

	public static final String ASSOCIATE_BTN= "//*[@id='scs-associate-chart-btn']";
	public static final String VALUE_SELECT = "//li[contains(.,'VALUE')]";
	public static final String CHARTNAME_INPUT = "//*[@class='scs-popup-field-element']//input";
	public static final String SAVE_BTN = "//button[contains(.,'Save') or contains(.,'SAVE')]";
	public static final String ASSOCIATE_NEW_CHART_SAVE_BTN = "//button[contains(.,'SAVE')]";
	public static final String FIELDS_HEADER = "//*[@id='scs-charts-fields-panel'][contains(.,'HEADER')]";
	public static final String SELECT_MULTI_FIELDS = "//*[@class='ng-star-inserted']//label[contains(.,'FIELDNAME')]/input[@type='checkbox']";
	public static final String RESOURCE_SELECT = "//td[contains(.,'RESOURCE')]/preceding-sibling::td/input[@type='checkbox']";
	public static final String SAMPLE_SIZE_INPUT = "//td[contains(.,'RESOURCE')]/following-sibling::td[4]//input[@role='spinbutton']";
	public static final String VARIATION_INPUT = "//td[contains(.,'RESOURCE')]/following-sibling::td[4]//input[@role='spinbutton']";
	public static final String MEAN_INPUT = "//td[contains(.,'RESOURCE')]/following-sibling::td[3]//input[@role='spinbutton']";
	public static final String CALCULATOR_BTN = "//td[contains(.,'RESOURCE')]/following-sibling::td//i[@id='scs-charts-calc-icon']";
	public static final String COUNT_NUMBER = "//li[span[contains(.,'COUNT')]]";
	public static final String CALCULATE_BTN = "//button[contains(.,'CALCULATE')]";
	public static final String APPLY_BTN = "//button[contains(.,'APPLY')]";	
	public static final String ASSOCIATED_CHART = "//*[@class='scs-list-view-name-container']//div[contains(text(),'ASSOCIATEDCHART')]";
	public static final String SEARCH_INPUT = "//*[@class='k-popup k-list-container k-reset scs-dropdown-popup']//span/input";	
	public static final String FILTER_DRD = "//*[@id='scs-charts-add-filter-container']//div[label[contains(.,'VALUE')]]/div//span[@class='k-icon k-i-arrow-s']";
	
	//charts
	public static final String FM_CHART_CLOSE = "//button[contains(text(),'Close')]";
	public static final String FM_CHART_POPUP_OK_BUTTON ="//button[contains(text(),'Ok')]";
	public static final String FM_CHART_DELETE_CHART_BUTTON = "//button[contains(text(),'DELETE CHART')]";
	public static final String FM_CHART_RESOURCE_LOCATION = "//span[@role='listbox']";
	public static final String FM_OVERVIEW_CHARTS_TAB = "//*[@class='scs-form-details-tabstrip']//li[contains(.,'Charts')]";
	public static final String FM_CHARTS_FIELD_DDL = "//*[@class = 'scs-form-manager-chart-conntrolFields-dropdown k-widget k-dropdown k-header ng-pristine ng-valid ng-touched']";
	public static final String FM_CHART_MEAN_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//div[contains(@class,'k-grid-content')]//td[contains(text(), 'RESOURCE_NAME')]/..//td[5]//span[@class=\"k-link k-link-increase\"]";
	public static final String FM_CHART_VARIANCE_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//div[contains(@class,'k-grid-content')]//td[contains(text(), 'RESOURCE_NAME')]/..//td[6]//span[@class=\"k-link k-link-increase\"]";
	public static final String FM_CHART_SELECT_RESOURCE = "//*[@id='scs-forms-manager-resources-grid']//div[contains(@class,'k-grid-content')]//td[contains(text(), 'RESOURCE_NAME')]/..//td[1]//input[@type=\"checkbox\"]";
	
	public static final String SELECT_CHART_FOR_FORM = "//*[@id='scs-charts-list-container']//div[text()='CHART_NAME']";
	public static final String RESOURCE_GRID_INPUT_VAL = "//div[contains(@class, 'k-grid-content')]//td[contains(text(), 'RESOURCE_NAME')]/..//td[COLUMNINDEXNO]//input";
	public static final String RESOURCE_HEADER_INPUT_VAL = "//div[contains(@class, 'k-grid-header')]//th[COLUMNINDEXNO]//input";
	public static final String COLUMN_NAMES_TBL = "//div[contains(@class, 'k-grid-header')]//th";
	public static final String FM_RESOURCETAB_SAVE_BTN = "//*[@id='scs-charts-tab-buttons-panel']/div/button[1]";
	public static final String SEARCH_FORMNAME_INPUT = "//*[@id='scs-forms-manager-overview-grid']//td[2]//input";

	
	public static final String FM_RESOURCETAB_FORM_CLOSE_BUTTON = "//button[@class='scs-form-details-btn']";
	public static final String FM_CHART_RESOURCES_DEFAULT_MEAN_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//th[@aria-colindex ='5']/.//span[@class='k-link k-link-increase']";
	public static final String FM_CHART_RESOURCES_DEFAULT_VARIANCE_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//th[@aria-colindex ='6']/.//span[@class='k-link k-link-increase']";
	public static final String FM_CHART_RESOURCES_SAMPLESIZE_UP_ARROW = "//*[@id='scs-forms-manager-resources-grid']//div[contains(@class,'k-grid-content')]//td[contains(text(), 'RESOURCE_NAME')]/..//td[6]//span[@class='k-link k-link-increase']";
			
	public static final String FM_CHART_RESOURCES_DEFAULT_CHECKBOX = "//*[@id='scs-forms-manager-resources-grid']//div/..//thead/tr[1]/th[contains(text(), 'Default')]/.//input[@type='checkbox']";
	public static final String FM_CHART_RESOURCES_DEFAULTCHECKPOPUP = "//div[@class='k-content k-window-content k-dialog-content']/..//button[contains(text(),'OK')]";
	public static final String FM_CHART_RESOURCES_DEFAULT_COMMENT ="//*[@id='scs-forms-manager-resources-grid']//div/..//thead/tr[1]/th[contains(text(), 'Comment')]/.//input";
	
	public static final String BY_DATE = "//input[@value = 'byDate']";
	public static final String START_DATE_TEXT = "//label[contains(text(), 'START DATE')]/following-sibling::div[@id='scs-chart-filter-dropdown']//input";
	public static final String END_DATE_TEXT = "//label[contains(text(), 'END DATE')]/following-sibling::div[@id='scs-chart-filter-dropdown']//input";
	public static final String START_TIME = "//label[contains(text(), 'START TIME')]/following-sibling::div[@id='scs-chart-filter-dropdown']//input";
	public static final String END_TIME = "//label[contains(text(), 'END TIME')]/following-sibling::div[@id='scs-chart-filter-dropdown']//input";
	
}



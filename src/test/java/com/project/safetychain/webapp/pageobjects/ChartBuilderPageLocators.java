package com.project.safetychain.webapp.pageobjects;

public class ChartBuilderPageLocators {
	
	public static final String ADD_NEW_CHART_BTN = "//*[@id='scs-admin-manage-chart-list']/div[contains(.,'Add New Chart')]";
	public static final String CHART_TYPE_LST = "//li[contains(.,'DDLOPTIONVALUE')]";
	public static final String CHART_TYPE_INPUT = "//*[@class='scs-manage-chart-field-row-value']//input";
	public static final String ENABLE_CHART = "//*[@class='k-switch-container']//span[@class='k-switch-label-on']";
	public static final String RULE_SELECT = "//label[@for='RULES']";
	public static final String SAVE_BTN = "//button[contains(.,'SAVE')]";
	
	public static final String CHART_TYPE_DRP_DWN_BTN = "//span[@class='k-icon k-i-arrow-s']";
	public static final String CHART_TYPE_LIST = "//div[@class='k-list-scroller']//span";
	public static final String CHART_TYPE_LIST_OPTN = "//div[@class='k-list-scroller']//span[text()='OPTION']";
	public static final String CHART_TYPE_SEARCH_INPUT ="//span[@class='k-list-filter']/input";
	
	public static final String ENABLE_DISABLE_STATUS = "//span[@class='k-switch-container']";
	public static final String ENABLE_DISABLE_BUTTON = "//*[@class='k-switch-container']//span[@class='k-switch-label-on']";
	
	public static final String ENABLD_CHART_TMPLT_NAME = "//div[@class='scs-list-view-name-container']/span[text()='CHART_NAME']";
	public static final String DISABLD_CHART_TMPLT_NAME = "//div[@class='scs-list-view-name-container scs-list-view-title-disabled']/span[text()='CHART_NAME']";
	
	public static final String ENABLED_CHART_TMPLT = "//span[text()='CHART_NAME']/ancestor::div[@class='scs-list-view-name-container']/..";
	public static final String DISABLED_CHART_TMPLT= "//span[text()='CHART_NAME']/ancestor::div[@class='scs-list-view-name-container scs-list-view-title-disabled']/..";
	public static final String ANY_CHART_TMPLT_IN_LIST = "//span[text()='CHART_NAME']/ancestor::div[1]/..";
	
	public static final String SLECTED_CHART_TITLE = "//div[@id='scs-admin-manage-chart-selected-title']";
	
	public static final String ALL_PRIMARY_RULE_CHKBOXES = "//label[contains(@for,'Primary')]/preceding-sibling::input";//"//input[contains(@id,'PrimaryChartRule')]";
	public static final String ALL_SECONDARY_RULE_CHKBOXES = "//label[contains(@for,'Secondary')]/preceding-sibling::input";//"//input[contains(@id,'SecondaryChartRule')]";
	public static final String CLEAR_BTN = "//button[contains(.,'CLEAR')]"; 
	
	//Pop Ups
	public static final String POPUP_TITLE = "//div[@class='k-window-title k-dialog-title']";
	public static final String POPUP_MSG = "//div[@class='k-content k-window-content k-dialog-content']";
	public static final String POPUP_OK_BTN = "//button[text()=' Ok ' or text()=' OK ' ]";
	
	public static final String CREATED_BY_LABEL = "(//span[@class='scs-manage-chart-note'])[1]";
	public static final String MODIFIED_BY_LABEL = "(//span[@class='scs-manage-chart-note'])[2]";
	public static final String LOGGED_IN_USERNAME = "//div[contains(@class,'scs-user-name')]";
}

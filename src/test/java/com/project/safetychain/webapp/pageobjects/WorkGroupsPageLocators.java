package com.project.safetychain.webapp.pageobjects;

public class WorkGroupsPageLocators {

	/**id*/
	
	/**className*/
	public static final String SAVE_BTN = "scs-manage-workgroup-button-save";

	/**xpath*/
//	public static final String ADD_WORKGROUP_LNK = "//*[@id='scs-mwg-list-view-data']//i[@class='fa fa-plus-circle']";
	public static final String ADD_WORKGROUP_LNK = "//*[@class='scs-bottom-button-container']//i[@class='fa fa-plus-circle']";
//	public static final String ADD_WORKGROUP_TXT = "//*[@id='scs-mwg-list-view-data']//input";
	public static final String ADD_WORKGROUP_TXT = "//*[contains(@class,'scs-list-view-menu')]//input";
	public static final String ADD_USR_CHK = "//*[text()='USERNAME']/preceding-sibling::td/input[@type='checkbox']";
//	public static final String SAVE_BTN = "//div[@class=\"scs-mwg-save-container\"]/div[text()='SAVE']";
	public static final String CANCEL_BTN = "//div[@class=\"scs-mwg-save-container\"]/div[text()='Cancel']";
	public static final String VIEW_WORKGROUP_LST = "//div[contains(@class,\"scs-list-view-name-container\")][contains(.,'WORKGROUPNAME')]";
//	public static final String SEARCH_USER_TXT = "//*[@class='scs-mwg-search-container']/input";
	public static final String SEARCH_USER_TXT = "//*[@class='scs-manage-workgroup-univ-search']//input";
//	public static final String SEARCH_USER_BTN = "//*[@class='scs-mwg-search-container']//span[contains(@class,'fa-search')]";
	public static final String SEARCH_USER_BTN = "//*[@class='scs-manage-workgroup-univ-search']//span[contains(@class,'fa-search')]";
	public static final String HOMEPAGE_LINK = "//*[@id='breadcrumb']//a[text()='Home']";
}

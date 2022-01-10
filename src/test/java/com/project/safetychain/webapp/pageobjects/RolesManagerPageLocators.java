package com.project.safetychain.webapp.pageobjects;

public class RolesManagerPageLocators {

	/**id*/
	
	
	/**className*/
	public static final String SEARCH_BTN = "scs-manage-roles-search-button";
	

	/**xpath*/
	public static final String ADD_ROLES_LNK = "//*[@id='scs-manage-role-list-view-data']//i[@class='fa fa-plus-circle']";
	public static final String ADD_ROLES_TXT = "//*[@id='scs-manage-role-list-view-data']//input";
	public static final String SAVE_BTN = "//*[@id='scs-admintools-right-panel']//div[text()='SAVE']";
	
	public static final String VIEW_ROLES_LST = "//div[contains(@class,'scs-list-view-name-container')][contains(.,'ROLESNAME')]";
	public static final String SEARCH_TXT = "//*[contains(@class, 'scs-manage-roles-search')]//input";
	public static final String PERMISSION_DDL_LST = "//div[contains(@class,'list-container')]//li[text()='PERMISSION']";
	public static final String GRID_COLUMNS = "//table//th[contains(@class,'k-header')]";
	public static final String COLUMN_SETTINGS_LNK = "//table//a[@class='k-link'][text()='COLUMNNAME']/preceding-sibling::a";
	public static final String POPUP_OPTION_MNU = "//ul[contains(@class, 'k-menu-vertical')]/li[contains(@class, 'k-item')]//span[text()='POPUPOPTION']";
	public static final String FILTER_TXT = "//*[@class='k-filter-menu']//input[@title='Value']";
	public static final String FILTER_BTN = "//*[@class='k-filter-menu']//button[@type='submit']";
	public static final String GRID_COLUMN_VALUE = "//div[contains(@class, 'k-grid-content')]//table//td[COLUMNINDEXNO]";

}

package com.project.safetychain.webapp.pageobjects;

public class ValidationsPageLocators {

	/**id*/
	public static final String NEW_VLDTN_BTN = "scs-new-validation-btn";
	public static final String VLDTN_GRID_NAME = "scs-validations-name";
	public static final String VLDTN_BREADCRUMB = "breadcrumb";
	public static final String VLDTN_TITLE = "scs-validations-title";
	public static final String VLDTN_LOCN_FILTER = "scs-validations-location-filter";

	/**className*/
	
	/**xpath*/
	public static final String COLUMN_NAMES_TBL = "//*[@id='scs-validations-grid-container']//table//th";
	public static final String COLUMN_NAMES_TXT = "//*[@id='scs-validations-grid-container']//tr[contains(@class,'k-filter-row')]//td[COLUMNINDEXNO]//input";
	public static final String VLDTN_COLUMN_VALUE = "//div[contains(@class,'k-grid-content')]//td[COLUMNINDEXNO]";
	public static final String GRID_LOCATION_DDL = "//*[@id='scs-validations-location-filter']//span[contains(@class,'arrow')]";
	public static final String LOCATION_SEARCH_TXT = "//span[contains(@class,'k-list-filter')]//input";
	public static final String LOCATION_SEARCH_BTN = "//span[@class='k-list-filter']//span[contains(@class, 'search')]";
	public static final String DROPDWN_OPTN = "//li[contains(.,'DROPDOWNOPTION')]";
	public static final String CLEAR_GRID_FILTER_BTN = "//button[contains(@class,'k-clear-button-visible')]";
	public static final String GRID_STATUS_DDL = "//*[@id='scs-validations-grid-container']//span[contains(@class,'arrow-s')]";
	public static final String CLEAR_COLUMN_FILTER_BTN = "//*[@id='scs-validations-grid-container']//td[COLUMNINDEXNO]//button[contains(@class,'k-clear-button-visible')]";
	
	public static final String GRID_PAGINTN_OPTNS = "//*[@id='scs-validations-grid-container']//kendo-pager-page-sizes//option";
	public static final String NEXT_PAGINTN_LNK = "//*[@id='scs-validations-grid-container']//kendo-pager-next-buttons/a[contains(@title, 'next')]";
	public static final String PREVIOUS_PAGINTN_LNK = "//*[@id='scs-validations-grid-container']//kendo-pager-prev-buttons/a[contains(@title, 'previous')]";
	public static final String FIRST_PAGINTN_LNK = "//*[@id='scs-validations-grid-container']//kendo-pager-prev-buttons/a[contains(@title, 'first')]";
	public static final String PAGE_SIZE_DDL = "//*[@id='scs-validations-grid-container']//kendo-pager-page-sizes/select";
	public static final String POPUP_OPTION_MNU = "//div[contains(@class, 'columnmenu-popup')]//div[contains(text(),'POPUPOPTION')]";
	public static final String COLUMN_SETTINGS_LNK = "//span[@class='k-link'][text()='COLUMNNAME']/preceding-sibling::kendo-grid-column-menu/a";
	public static final String GRID_COLUMN_VALUE = "//div[contains(@class, 'k-grid-content')]//table//td[COLUMNINDEXNO]";
	public static final String POPUP_COLUMN_SET_OPTN = "//div[contains(@class, 'k-columnmenu-item-content')]//span[text()='COLUMNNAME']/preceding-sibling::input";
	public static final String POPUP_COLUMN_APPLY_BTN = "//div[contains(@class, 'columnmenu-popup')]//button[text()='Apply']";


}

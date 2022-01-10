package com.project.safetychain.webapp.pageobjects;

public class UserManagerPageLocators {

	/**id*/
	
	/**className*/

	/**xpath*/
	public static final String ADD_NEW_USER_BTN = "//div[@id=\"scs-manage-users-tabstrip\"]//button[text()=\"ADD NEW USER\"]";
	public static final String SAVE_BTN = "//button[text()=\"save\"]";
	public static final String ADD_USR_CHK = "//*[text()='USERNAME']/preceding-sibling::td/input[@type='checkbox']";
	public static final String USER_DETAILS_TXT = "//div[@id=\"scs-manage-user-add-edit-user\"]//label[contains(text(),\"TEXTBOX\")]/../..//input";
	public static final String TIMEZONE_DDL = "//*[@id=\"scs-manage-user-add-edit-user\"]//kendo-dropdownlist[@name=\"TimeZone\"]//span[@class=\"k-select\"]";
	public static final String TIMEZONE_LST = "//li[@class=\"k-item ng-star-inserted\"][contains(.,\"DDLOPTIONVALUE\")]";
	public static final String COLUMN_NAMES_TBL = "//*[@id='scs-admin-user-internal-grid']//thead//th";
	public static final String COLUMN_NAMES_TXT = "//*[@id=\"scs-admin-user-internal-grid\"]//table//tr[contains(@class,\"ng-star-inserted\")]/td[COLUMNINDEXNO]//input";
	public static final String USR_COLUMN_VALUE = "//*[@id=\"scs-admin-user-internal-grid\"]//table/tbody//td[COLUMNINDEXNO]";
	public static final String WINDOW_TITLE = "//div[contains(@class,\"k-dialog-title\")]";
	public static final String WINDOW_CLOSE_BTN = "//div[contains(@class,'k-window-actions')]/a[contains(@class,'k-dialog-close')]";
	public static final String LOCATION_TEXT_ALL = "//kendo-multiselect[@name='location']//li[span[text()='ALL']]";
	public static final String LOCATION_ALL_REMOVE = "//kendo-multiselect[@name='location']//span[@class='k-icon k-i-close']";
	public static final String ALERT_MSG_TEXT = "//span[contains(@class, 'scs-manage-user-add-edit-user-alertMsg')]";
	public static final String NO_RECORDS_AVAILBLE_FOR_USER = "//div[contains(@class, 'k-grid-content')]//tr[contains(@class, 'k-grid-norecords')]";
	
    /** SC-- InternalUser xpath*/
	
	public static final String INTERNAL_TAB = "//*[@id= 'k-tabstrip-tab-0'][contains(.,'Internal')]";
	
	/** SC-- Location Filter */
	
	public static final String LOCATION_FILTER= "//*[@id= 'scs-manage-user-location-dropdown']";
	
	/** SC--  Grid Filter  */
	
	public static final String FILTERS = "//div[@class='k-grid-header-wrap']//table//tr//th[@aria-colindex=NUMBER]";
	
	/** SC-- UserName    */
	
	public static final String USER_NAME_INPUT="//*[@id='scs-manage-user-add-edit-user-username']";
	
	public static final String ADD_USER_WINDOW = "//div[text()='Add User']/../..";
	
	public static final String ERROR_WINDOW= "//div[text()='Username must be unique']";
	
	public static final String OK_BUTTON= "//button[contains(text(),'OK')]";
	
	/** SC-- Email     */
	
	
	public static final String EMAIL_INPUT="//div[@class='scs-manage-user-add-edit-user-fieldElement']/input[@formcontrolname='Email']";
	
	public static final String ERROR_EMAIL_WINDOW="//div[text()='Please input a valid email address!']";
	
	/** SC-- RequiredField   */
	
	public static final String REQUIREDFIELD= "//div[@class='scs-manage-user-add-edit-user-fieldLabel scs-manage-user-add-edit-user-reqdfieldLabel']/label[@for='REQUIREDFIELDNAME']";
    
	/** SC-- User-Icon   */
	
	public static final String USER_ICON="//div[@id= 'scs-user-settings']/div[@class= 'scs-user-icon']";
	
	public static final String LOGOUT="/html/body/div[8]/div[2]/div/ul//li[3]/span";
	
	public static final String SUPPLIER_LOGIN_BTN="//div/button[text()= 'SUPPLIER LOGIN']";
	
	public static final String LOGIN_BTN= "//button[text()= 'LOGIN']";
	
	public static final String CREATE_PASSWORD_BTN="//button[text()= 'CREATE PASSWORD']";
	
	/**SupplierUser xpath*/
	public static final String SUPPLIER_TAB = "//*[@id='k-tabstrip-tab-1'][contains(.,'Supplier')]";
	public static final String SUPP_COLUMN_NAMES_TBL = "//*[@id='scs-admin-user-supplier-grid']//thead//th";
	public static final String SUPP_COLUMN_NAMES_TXT = "//*[@id='scs-admin-user-supplier-grid']//table//tr[contains(@class,'ng-star-inserted')]/td[COLUMNINDEXNO]//input";
	public static final String SUPP_USR_COLUMN_VALUE = "//*[@id='scs-admin-user-supplier-grid']//table/tbody//td[COLUMNINDEXNO]";

	
	public static final String HOVER_TBL = "//tr[td[2 and text()='USERNAME']]";
	public static final String USER_CALLOUT_MNU = "//i[contains(@class,'scs-callout-button')]";
	
	public static final String UNLOCK_USER_OPTN = "//li[contains(@class,'scs-callout-menu-item')]//span[text()='Unlock User']";
	public static final String UNLOCK_USER_YES_BTN = "//div[kendo-dialog-titlebar/div[text()='Unlock User']]//button[text()='Yes']";

	public static final String DEACTIVATE_USER_OPTN = "//li[contains(@class,'scs-callout-menu-item')]//span[text()='Deactivate User']";
	public static final String DEACTIVATE_USER_YES_BTN = "//div[kendo-dialog-titlebar/div[text()='Deactivate User']]//button[text()='Yes']";
	
	public static final String REACTIVATE_USER_OPTN = "//li[contains(@class,'scs-callout-menu-item')]//span[text()='Reactivate User']";
	public static final String REACTIVATE_USER_YES_BTN = "//div[kendo-dialog-titlebar/div[text()='Reactivate User']]//button[text()='Yes']";
}
